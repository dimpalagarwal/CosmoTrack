package com.example.myapplication

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun ScannerScreen(navController: NavController)
 {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    //
    // 1. Gallery QR‑scan launcher (ML Kit)
    //
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { pickedUri ->
            coroutineScope.launch {
                val result = decodeBarcodeFromImage(context, pickedUri)
                if (result != null) {
                    navController.navigate("productDetails/$result")
                } else {
                    Toast.makeText(context, "No QR code found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //
    // 2. Camera QR‑scan launcher (ZXing)
    //
    val cameraLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        val code = result?.contents
        if (!code.isNullOrBlank()) {
            navController.navigate("productDetails/$code")
        } else {
            Toast.makeText(context, "Scan cancelled or failed", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Gallery button
        Button(onClick = { galleryLauncher.launch("image/*") }) {
            Text("Select Image from Gallery")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Camera button
        Button(onClick = {
            // Launch ZXing's built‑in camera scanner
            val options = ScanOptions().apply {
                setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                setPrompt("Point your camera at the QR code")
                setBeepEnabled(true)
                setOrientationLocked(true)
            }
            cameraLauncher.launch(options)
        }) {
            Text("Scan with Camera")
        }
    }
}

suspend fun decodeBarcodeFromImage(context: Context, uri: Uri): String? {
    return try {
        val image = InputImage.fromFilePath(context, uri)
        val scanner = BarcodeScanning.getClient()
        val barcodes = scanner.process(image).await()
        barcodes.firstOrNull()?.rawValue
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
