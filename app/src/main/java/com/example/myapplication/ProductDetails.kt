package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.R

val Maroon = Color(0xFF800020)
val LightMaroon = Color(0xFFD8B4B4)
val MaroonCard = Color(0xFFB85C5C)
val SoftWhite = Color(0xFFFFF8F7)

data class ProductDetails(
    val name: String,
    val expiryDays: Int,
    val ingredients: String,
    val barcode: String
)

@Composable
fun ProductDetailsScreen(product: ProductDetails, modifier: Modifier = Modifier, navController: NavController = rememberNavController()) {

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.background_image),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxSize()
//                .alpha(0.4f)
//        )

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

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
            Text(product.name, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Maroon)
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
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Maroon)
            ) {
                Text("Notify before expiry", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { /* Handle alternate use */ },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Maroon) // A brownish shade, or choose any
            ) {
                Text("Alternate Use", color = Color.White)
            }

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
            containerColor = if (selected) Maroon else LightMaroon,
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
        colors = CardDefaults.cardColors(containerColor = MaroonCard)
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
        colors = CardDefaults.cardColors(containerColor = Maroon.copy(alpha = 0.9f))
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
            colors = ButtonDefaults.buttonColors(containerColor = Maroon.copy(alpha = 0.85f)),
            modifier = Modifier.weight(1f)
        ) {
            Text("Edit", color = Color.White)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = { },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB03030)),
            modifier = Modifier.weight(1f)
        ) {
            Text("Delete", color = Color.White)
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
