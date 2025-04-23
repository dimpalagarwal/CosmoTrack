package com.example.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    UserProfileScreen()
}

@Composable
fun UserProfileScreen() {
    val maroon = Color(0xFF800020)
    val softWhite = Color(0xFFF7EBED)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(softWhite)
    ) {
        // Top Profile Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(maroon)
                .padding(top = 40.dp, bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "User Image",
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(softWhite)
                        .padding(4.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text("Alex Morgan", color = softWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Makeup Enthusiast", color = softWhite, fontSize = 14.sp)

                Spacer(modifier = Modifier.height(20.dp))

                // Action Buttons Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProfileActionButton(icon = Icons.Default.Face, label = "My Routine", maroon = maroon, softWhite = softWhite)
                    ProfileActionButton(icon = Icons.Default.List, label = "Product List", maroon = maroon, softWhite = softWhite)
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Profile Info Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            ProfileInfoItem(title = "Skin Type", value = "Combination")
            Spacer(modifier = Modifier.height(16.dp))
            ProfileInfoItem(title = "Favorite Brands", value = "Maybelline, MAC, Nykaa")
            Spacer(modifier = Modifier.height(16.dp))
            ProfileInfoItem(title = "Skin Concerns", value = "Dry patches, Dullness")
            Spacer(modifier = Modifier.height(16.dp))
            ProfileInfoItem(title = "Routine", value = "Morning & Night")
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Bottom Buttons
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /* Add to favorites */ },
                    colors = ButtonDefaults.buttonColors(containerColor = maroon),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text("Add to favorites", color = softWhite)
                }

                Spacer(modifier = Modifier.width(12.dp))

                OutlinedButton(
                    onClick = { /* Share */ },
                    border = BorderStroke(1.dp, maroon),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Icon(Icons.Default.Share, contentDescription = "Share", tint = maroon)
                }
            }
        }
    }
}

@Composable
fun ProfileActionButton(
    icon: ImageVector,
    label: String,
    maroon: Color,
    softWhite: Color,
    onClick: () -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(50.dp)
                .background(softWhite, shape = CircleShape)
        ) {
            Icon(icon, contentDescription = label, tint = maroon)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 12.sp, color = softWhite)
    }
}

@Composable
fun ProfileInfoItem(title: String, value: String) {
    Column {
        Text(title, fontWeight = FontWeight.SemiBold, color = Color.Gray, fontSize = 13.sp)
        Text(value, fontWeight = FontWeight.Medium, fontSize = 15.sp, color = Color.Black)
    }
}
