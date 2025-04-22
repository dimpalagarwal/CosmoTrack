package com.example.myapplication

// Required Imports
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
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.myapplication.viewmodel.UserProfileViewModel
import com.google.android.gms.analytics.ecommerce.Product


@Composable
fun DashboardScreen(
    name: String,
    navController: NavController,
    scannedProducts: SnapshotStateList<Product>,
    userProfileViewModel: UserProfileViewModel) {
    var showOptions by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val imageUri: Uri? = userProfileViewModel.profileImageUri.value


    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        },

        floatingActionButton = {
            Box {
                FloatingActionButton(
                    onClick = { showOptions = !showOptions },
                    containerColor = Color(0xFFffffff)
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
        },
//        floatingActionButtonPosition = FabPosition.End, // Optional: or FabPosition.Center
//        isFloatingActionButtonDocked = true


    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFF6EBD9))
                .padding(16.dp)
        ) {
            TopGreetingSection(name = name)
            Spacer(modifier = Modifier.height(30.dp))
// Google Calendar Section with updated light brown and dark brown colors
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
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

                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "No Calendar app found", Toast.LENGTH_SHORT).show()
                        }
                    },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)), // White background
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "📅 Add your Google Calendar",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF6B4F32) // Dark brown text
                    )
                    Text(
                        "Sync your schedule for better planning.",
                        color = Color(0xFF000000) // Dark text
                    )
                }
            }


            Spacer(modifier = Modifier.height(4.dp))
            CardInfo(title = "💡 Tip of the Day", content = "Hydration is key – drink water & smile!")
            CardInfo(title = "🧴 Suggested Product", content = "Use your vitamin C serum today!")
            CardInfo(title = "🕓 2 products expiring soon", content = "1 skincare routine pending")
        }
    }
}

@Composable
fun TopGreetingSection(name: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = "User Photo",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text("Hello $name", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("You're glowing today!", color = Color.DarkGray)
            }
        }
        Button(
            onClick = { /* TODO: Open AI Chat */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD8D4FF))
        ) {
            Text("Chat with AI")
        }
    }
}


@Composable
fun CardInfo(title: String, content: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(content, color = Color.DarkGray)
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color(0xFF800020)
    ) {
        val navItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            unselectedIconColor = Color.White,
            selectedTextColor = Color.White,
            unselectedTextColor = Color.White,
            indicatorColor = Color(0xFF888888) // <-- light gray background for selected item
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = {},
            colors = navItemColors
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Products") },
            label = { Text("Products") },
            selected = false,
            onClick = {},
            colors = navItemColors
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Reminders") },
            label = { Text("Reminders") },
            selected = false,
            onClick = {},
            colors = navItemColors
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Face, contentDescription = "Looks") },
            label = { Text("Looks") },
            selected = false,
            onClick = {},
            colors = navItemColors
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = {},
            colors = navItemColors
        )
    }
}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun DashboardScreenPreview() {
//    val navController = rememberNavController()
//    DashboardScreen(navController = navController)
//}
