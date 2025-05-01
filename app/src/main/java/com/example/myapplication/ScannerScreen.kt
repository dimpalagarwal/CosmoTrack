package com.example.myapplication

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.viewmodel.ProductViewModel

@Composable
fun ScannerScreen(
    navController: NavController,
    productViewModel: ProductViewModel
) {
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            productViewModel.selectedProduct = ProductDetails(
                name = "Coronation",
                expiryDays = 30,
                ingredients = "Water, Alcohol, Aloe Vera",
                barcode = "007325465"
            )
            navController.navigate("productDetails")
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

        // Camera button — optional, remove if not needed
        Button(onClick = {
            // You can remove this button too if you're not scanning anymore
            navController.navigate("productDetails")
        }) {
            Text("Scan with Camera")
        }
    }
}
