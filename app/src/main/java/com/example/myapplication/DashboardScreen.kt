package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.viewmodel.UserProfileViewModel
import com.google.android.gms.analytics.ecommerce.Product

@Composable
fun DashboardScreen(
    name: String,
    navController: NavController,
    scannedProducts: SnapshotStateList<Product>,
    userProfileViewModel: UserProfileViewModel

) {

    val imageUri: Uri? = userProfileViewModel.profileImageUri.value

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
                            navController.navigate("scannerScreen")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Manually Enter Product Details") },
                        onClick = {
                            showOptions = false
                            navController.navigate("manualEntryScreen")
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Background image
            Image(
                painter = painterResource(id = R.drawable.background_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f) // reduce opacity
            )

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {

                Spacer(modifier = Modifier.height(8.dp))

                // Profile Info
                Row(verticalAlignment = Alignment.CenterVertically) {
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
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No Photo", fontSize = 15.sp)
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        horizontalAlignment = Alignment.End // Align items inside the column to the end (right)
                    ) {
                        Text(text = "Hello $name", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Button(
                            onClick = { /* Chat with AI */ },
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Text("Chat with AI")
                        }
                        Button(
                            onClick = { /* Your Products */ },
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Text("Your products...")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Google Calendar Section with updated light brown and dark brown colors
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(Intent.ACTION_EDIT).apply {
                                type = "vnd.android.cursor.item/event"
                                putExtra("title", "Beauty Routine")
                                putExtra(
                                    "description",
                                    "Add your skincare routine, product reminders, or appointments"
                                )
                                putExtra("eventLocation", "Home") // Optional
                            }

                            // Safely start activity
                            if (intent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(context, "No Calendar app found", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD5C2A1)) // Light brown background
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "📅 Add your Google Calendar",
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF6B4F32) // Dark brown text
                        )
                        Text(
                            "Sync your schedule for better planning.",
                            color = Color(0xFF6B4F32) // Dark brown text
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

// Extra Dashboard Features
                Text(
                    "Tips for You",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF704214) // Brown color for heading
                )
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD2B79A)) // Light brown background
                ) {
                    Text(
                        "Drink 8 glasses of water and don’t forget sunscreen!",
                        modifier = Modifier.padding(16.dp),
                        color = Color.Black // Black tip text
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { /* Navigate to Expiring Products */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("\u26A0 Expiring Products", color = Color.White)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { /* Navigate to Alternative Uses */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD2A66B)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Alternative uses for Expired Products")
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
                    onClick = { navController.navigate("alternateList") },
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

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { /* Navigate to Explore New Looks */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD2A66B)),
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text("Explore new looks")
                    }
                }
            }
        }
    }
}
