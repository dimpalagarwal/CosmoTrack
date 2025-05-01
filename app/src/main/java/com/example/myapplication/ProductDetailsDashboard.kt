package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Product(val name: String, val imageRes: Int, var quantity: Int = 1)

@Composable
fun ProductDashboardScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    val allProducts = remember {
        mutableStateListOf(
            Product("Shampoo", R.drawable.shampoo),
            Product("Concealer", R.drawable.concealer),
            Product("Moisturizer", R.drawable.moisturizer),
            Product("Face Wash", R.drawable.simple_face_wash),
            Product("Vaseline", R.drawable.vaseline),
            Product("Toner", R.drawable.toner)
        )
    }

    val filteredProducts = allProducts.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        bottomBar = {
            DetailsBottomNavigationBar(navController)
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF8F7))
                .padding(16.dp)
                .padding(innerPadding)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF800020), shape = RoundedCornerShape(16.dp))
                    .padding(vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "My Makeup Shelf",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search products") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Product Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(filteredProducts) { product ->
                    ProductCard(product)
                }
            }
        }
    }
}

@Composable
fun DetailsBottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFF800020)
    ) {
        val navItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            unselectedIconColor = Color.White,
            selectedTextColor = Color.White,
            unselectedTextColor = Color.White,
            indicatorColor = Color(0xFF888888)
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = { navController.navigate("dashboardScreen") },  // Keep the current screen
            colors = navItemColors
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Products") },
            label = { Text("Products") },
            selected = true,
            onClick = { navController.navigate("ProductDetailsDashboard") },  // Navigate to ProductDetailsDashboard
            colors = navItemColors
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Reminders") },
            label = { Text("Reminders") },
            selected = false,
            onClick = { navController.navigate("expiringSoon") },  // Example for Reminders screen
            colors = navItemColors
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Face, contentDescription = "Looks") },
            label = { Text("Looks") },
            selected = false,
            onClick = { navController.navigate("exploreNewLooks") },  // ✅ Correct
            colors = navItemColors
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = { navController.navigate("Profile") },  // Navigate to UserProfileScreen
            colors = navItemColors
        )
    }
}


@Composable
fun ProductCard(product: Product) {
    var quantity by remember { mutableStateOf(product.quantity) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.9f),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7EBED)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF800020),
//                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Quantity Control Row
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleIcon("-", onClick = {
                    if (quantity > 1) quantity--
                })

                Text(
                    text = quantity.toString(),
                    fontSize = 16.sp,
                    color = Color(0xFF800020),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                CircleIcon("+", onClick = {
                    quantity++
                })
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { /* Navigate to details */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF800020))
            ) {
                Text("Details", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun CircleIcon(symbol: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(text = symbol, fontSize = 18.sp, color = Color(0xFF800020))
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProductDashboardScreen() {
//    val navController = rememberNavController()
//    ProductDashboardScreen(navController = navController)
//}