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
import androidx.compose.foundation.layout.width
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
//import com.google.rpc.Help

@Preview(showBackground = true)
@Composable
fun ExpiringSoonScreenPreview() {
    val navController = rememberNavController()
    ExpiringSoonScreen(navController = navController)
}
@Composable
    fun ExpiringSoonScreen(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background (
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFF0DA), Color(0xFFE39562))
                    ))
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                )}
                TextField(
                    value = searchText,
                    onValueChange = {searchText = it},
                    placeholder = { Text("Search", color= Color.Black) },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )

                )

                IconButton(onClick = { /* Profile */ }) {
                    Icon(Icons.Default.Person, contentDescription = "Profile")
                }


            Text(
                text = "Expiring Soon",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )

            Spacer(modifier = Modifier.height(16.dp))

            val items = listOf(
                Pair("Nivea Moisturiser", R.drawable.moisturizer),
                Pair("Lakme Lipstick", R.drawable.lipstick),
                Pair("Swiss Beauty Concealer", R.drawable.concealer),
                Pair("Coronation Shampoo", R.drawable.shampoo),
                Pair("Simple Face Wash", R.drawable.simple_face_wash)
            )



            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.heightIn(max = 1000.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items) { (name, imageRes) ->
                    ProductItem(name = name, imageRes = imageRes)
                }

            }

        }
    }

    @Composable
    fun ProductItem(name: String, @DrawableRes imageRes: Int) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier

                .padding(8.dp)
                .width(100.dp),
        ) {

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = name,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }

    @Composable
    fun ProductGrid() {
        val products = listOf(
            Pair("Moisturiser", R.drawable.moisturizer),
            Pair("Lipstick", R.drawable.lipstick),
            Pair("Concealer", R.drawable.concealer),
            Pair("Shampoo", R.drawable.shampoo),
            Pair("Face Wash", R.drawable.simple_face_wash)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { (name, imageRes) ->
                ProductItem(name = name, imageRes = imageRes)
            }
        }
    }

