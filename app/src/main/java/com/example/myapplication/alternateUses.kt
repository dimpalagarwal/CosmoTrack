package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun AlternateUsesPreview() {
    val navController = rememberNavController()
    ExpirationDetailsScreen(navController = navController)
}

@Composable
fun ExpirationDetailsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFBE4D8), Color(0xFFF0A872))
                )
            )
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Bar Icons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) { IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
            )}
            Row {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(28.dp)
                        .clickable { /* Handle edit */ }
                )
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { /* Handle share */ }
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Expiration Alert Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFDD2C00))
                .padding(20.dp)
        ) {
            Text(
                text = "Shampoo is going to be expired in 30 days.",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Alternatives Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFE79B60))
                .padding(30.dp)
        ) {
            Column {
                Text(
                    text = "ALTERNATIVE WAYS TO USE:",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2D1F14)
                )
                Spacer(modifier = Modifier.height(20.dp))

                val uses = listOf(
                    "Shampoo can be used as body wash.",
                    "It can be used as hand wash.",
                    "It can be used to clean your makeup brushes.",
                    "It can be used to hand clean delicate fabrics like silk or wool.",
                    "It can be used to clean the grease and makeup stains on clothes.",
                    "It can also be used to clean tiles and floor."
                )

                uses.forEachIndexed { index, item ->
                    Text(
                        text = "${index + 1}. $item",
                        fontSize = 18.sp,
                        lineHeight = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Footer
        Text(
            text = "Don’t let it go to waste, reuse it with grace ♻️",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

