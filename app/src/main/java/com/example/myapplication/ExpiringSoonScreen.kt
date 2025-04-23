package com.example.myapplication

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Help
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.rpc.Help

@Preview(showBackground = true)
@Composable
fun ExpiringSoonScreenPreview() {
    val navController = rememberNavController()
    ExpiringSoonScreen(navController = navController)
}

@Composable
fun ProductItem(name: String, @DrawableRes imageRes: Int, daysLeft: Int) {
    // Determine color based on expiry
    val borderColor = when {
        daysLeft < 15 -> Color.Red
        daysLeft <= 30 -> Color(0xFFFFA500) // Orange
        else -> Color(0xFF4CAF50) // Green
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .width(100.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(borderColor)
                .padding(4.dp) // Border effect
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Text(
            text = "$daysLeft days left",
            fontSize = 10.sp,
            color = borderColor
        )
    }
}


@Composable
fun DividerWithShadow() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.Gray.copy(alpha = 0.3f), shape = RoundedCornerShape(1.dp))
            .shadow(4.dp, shape = RoundedCornerShape(1.dp))
    )
}
@Composable
fun ExpiringSoonScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    Scaffold(
        bottomBar = {
            DashboardBottomNavigationBar(navController)
        },
        containerColor = Color(0xFFF7EBED)
    ) { innerPadding ->

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7EBED))
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())

        ) {
        // 🔷 Top Thin Bar with Icons Only
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFF800020))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )

            }
            Text(
                text = "Expiring Soon",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )

            IconButton(onClick = { /* Profile click */ }) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color.White
                )
            }
        }

        // 🔍 Search Bar Below Top Bar
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = {
                Text("Search", color = Color.Black) // Black placeholder text
            },
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFE0E0E0),
                unfocusedContainerColor = Color(0xFFE0E0E0),
                disabledContainerColor = Color(0xFFE0E0E0),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

// Divider with shadow
        DividerWithShadow()

        Spacer(modifier = Modifier.height(12.dp))


        val items = listOf(
            Triple("Nivea Moisturiser", R.drawable.moisturizer, 10),
            Triple("Lakme Lipstick", R.drawable.lipstick, 25),
            Triple("Swiss Beauty Concealer", R.drawable.concealer, 40),
            Triple("Coronation Shampoo", R.drawable.shampoo, 7),
            Triple("Simple Face Wash", R.drawable.simple_face_wash, 31)
        )
        val redItems = items.filter { it.third < 15 }
        val orangeItems = items.filter { it.third in 15..30 }
        val greenItems = items.filter { it.third > 30 }

        if (redItems.isNotEmpty()) {
            ExpirySection("Expiring in < 15 Days", redItems, Color.Red)
        }
        if (orangeItems.isNotEmpty()) {
            ExpirySection("Expiring in 15-30 Days", orangeItems, Color(0xFFFFA500))
        }
        if (greenItems.isNotEmpty()) {
            ExpirySection("Expiring in > 30 Days", greenItems, Color(0xFF4CAF50))
        }
        val filteredItems = items.filter { it.first.contains(searchText, ignoreCase = true) }
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(3),
//            modifier = Modifier.heightIn(max = 1000.dp),
//            verticalArrangement = Arrangement.spacedBy(12.dp),
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            items(items) { (name, imageRes, daysLeft) ->
//                ProductItem(name = name, imageRes = imageRes,daysLeft = daysLeft)

    }
    }
}
@Composable
fun ExpirySection(title: String, items: List<Triple<String, Int, Int>>, color: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = color,
            modifier = Modifier.padding(bottom = 8.dp),
            textAlign = TextAlign.Center
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),

            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { (name, imageRes, daysLeft) ->
                ProductItem(name = name, imageRes = imageRes, daysLeft = daysLeft)
            }
        }
    }
}



