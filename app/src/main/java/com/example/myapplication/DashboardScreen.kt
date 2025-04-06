package com.example.myapplication

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.ui.platform.LocalContext

@Composable
fun DashboardScreen(
    name: String,
    imageUri: Uri?,
    navController: NavController
) {
    var showOptions by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            Box {
                FloatingActionButton(
                    onClick = { showOptions = !showOptions },
                    containerColor = Color(0xFFC87F4F)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }

                // Popup options when FAB is clicked
                DropdownMenu(
                    expanded = showOptions,
                    onDismissRequest = { showOptions = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Scanner") },
                        onClick = {
                            showOptions = false
                            // TODO: Navigate to Scanner Screen
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Manually Enter Product Details") },
                        onClick = {
                            showOptions = false
                            // TODO: Navigate to Manual Entry Screen
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            // Profile Info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Profile Photo",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No Photo", fontSize = 10.sp)
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Calendar section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* TODO: Link Google Calendar */ },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("📅 Add your Google Calendar", fontWeight = FontWeight.Medium)
                    Text("Sync your schedule for better planning.")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Optional Dashboard Content
            Text("Today's Tips", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
            ) {
                Text(
                    "Stay hydrated and never skip sunscreen!",
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Recently Added Products", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            // Placeholder product cards
            repeat(2) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5))
                ) {
                    Text(
                        text = "Product ${it + 1}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}


