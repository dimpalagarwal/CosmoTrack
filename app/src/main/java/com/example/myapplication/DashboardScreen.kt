package com.example.myapplication

import android.content.Intent
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter

@Composable
fun DashboardScreen(
    name: String,
    imageUri: Uri?,
    navController: NavController
) {
    var showOptions by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            Box {
                FloatingActionButton(
                    onClick = { showOptions = !showOptions },
                    containerColor = Color(0xFFC87F4F)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }

                DropdownMenu(
                    expanded = showOptions,
                    onDismissRequest = { showOptions = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Scanner") },
                        onClick = {
                            showOptions = false
                            // Start real scanner
                            navController.navigate("scannerScreen")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Manually Enter Product Details") },
                        onClick = {
                            showOptions = false
                            // Navigate to Manual Entry
                            navController.navigate("manualEntryScreen")
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
            // Profile Row
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

            // Google Calendar Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(Intent.ACTION_EDIT).apply {
                            type = "vnd.android.cursor.item/event"
                            putExtra("title", "Beauty Routine")
                            putExtra("description", "Add your skincare routine, product reminders, or appointments")
                            putExtra("eventLocation", "Home") // Optional
                        }

                        // Safely start activity
                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "No Calendar app found", Toast.LENGTH_SHORT).show()
                        }
                    },

                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("📅 Add your Google Calendar", fontWeight = FontWeight.Medium)
                    Text("Sync your schedule for better planning.")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Extra Dashboard Features (Example)
            Text("Tips for You", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
            ) {
                Text(
                    "Drink 8 glasses of water and don’t forget sunscreen!",
                    modifier = Modifier.padding(16.dp)
                )
            }

            
                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { navController.navigate("expiringSoon") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("\u26A0 Expiring Products", color = Color.White)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {navController.navigate("alternateList") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD2A66B)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Alternative uses for Expired Products")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { navController.navigate("exploreNewLooks") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD2A66B)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Explore new looks")
                }
            }
        }
    }
}
