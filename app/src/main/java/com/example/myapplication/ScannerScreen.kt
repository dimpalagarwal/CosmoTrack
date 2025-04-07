package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@SuppressLint("ContextCastToActivity")
@Composable
fun ScannerScreen(onScanned: (String) -> Unit = {}) {
    val context = LocalContext.current as Activity

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            if (result.contents != null) {
                Log.d("ScannerScreen", "Scanned: ${result.contents}")
                onScanned(result.contents)
                // You can navigate or update state here
            } else {
                Log.d("ScannerScreen", "Scan cancelled")
            }
            context.finish() // Close the scanner screen
        }
    )

    LaunchedEffect(Unit) {
        scanLauncher.launch(
            ScanOptions().apply {
                setPrompt("Scan a beauty product QR code")
                setBeepEnabled(true)
                setOrientationLocked(false)
                setBarcodeImageEnabled(true)
            }
        )
    }
}