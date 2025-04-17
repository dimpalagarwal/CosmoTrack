package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.R

data class ProductDetails(
    val name: String,
    val expiryDays: Int,
    val ingredients: String,
    val barcode: String
)

@Composable
fun ProductDetailsScreen(product: ProductDetails, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.shampoo),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(product.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Expires in ${product.expiryDays} days", color = Color(0xFFB65D00))

        Spacer(modifier = Modifier.height(16.dp))
        ExpiryStatusRow()

        Spacer(modifier = Modifier.height(16.dp))
        IngredientsCard(product.ingredients)

        Spacer(modifier = Modifier.height(16.dp))
        BarcodeCard(product.barcode)

        Spacer(modifier = Modifier.height(16.dp))
        ActionButtons()

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Handle notify */ },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Notify before expiry")
        }
    }
}

@Composable
fun ExpiryStatusRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatusButton(">30 days", true)
        StatusButton("<30 days", false)
        StatusButton("Expired", false)
    }
}

@Composable
fun StatusButton(text: String, selected: Boolean) {
    Button(
        onClick = { },
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFF0066FF) else Color(0xFFCE946B),
            contentColor = Color.White
        )
    ) {
        Text(text)
    }
}

@Composable
fun IngredientsCard(ingredients: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD7824E))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("📋 Ingredients information", fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(ingredients, color = Color.White, fontSize = 14.sp)
        }
    }
}

@Composable
fun BarcodeCard(barcode: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFBD6635))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("📦 Barcode Number", fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Text(barcode, fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun ActionButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF6B041)),
            modifier = Modifier.weight(1f)
        ) {
            Text("Edit")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = { },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD65141)),
            modifier = Modifier.weight(1f)
        ) {
            Text("Delete")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailsPreview() {
    val previewProduct = ProductDetails(
        name = "Coronation",
        expiryDays = 64,
        ingredients = "Aqua, Sodium Laureth Sulfate, Cocamidopropyl Betaine, Glycerin, etc.",
        barcode = "123456789012"
    )

    MyApplicationTheme {
        ProductDetailsScreen(product = previewProduct)
    }
}
