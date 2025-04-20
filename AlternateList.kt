package com.example.myapplication

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush

@Preview(showBackground = true)
@Composable
fun AlternateListPreview() {
    AlternateList()
}
@Composable
fun AlternateList() {
    var searchText by remember { mutableStateOf("") }

    val items = listOf(
        Pair("Moisturiser", R.drawable.moisturizer),
        Pair("Lipstick", R.drawable.lipstick),
        Pair("Concealer", R.drawable.concealer),
        Pair("Shampoo", R.drawable.shampoo),
        Pair("Face Wash", R.drawable.simple_face_wash),
        Pair("Body Wash", R.drawable.dove_body_wash),
        Pair("Kajal/Kohl", R.drawable.kajal),
        Pair("Eye Shadow", R.drawable.eye_shadow)
    )

    val filteredItems = items.filter {it.first.contains(searchText, ignoreCase = true) }

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
                .padding(bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Search") },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            IconButton(onClick = { /* Profile */ }) {
                Icon(Icons.Default.Person, contentDescription = "Profile")
            }
        }

        Text(
            text = "Alternate Uses",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        filteredItems.forEach { (name, imageRes) ->
            ItemBar(itemName = name, imageResId = imageRes, onClick = { /* TODO */ })
        }
    }
}

@Composable
fun ItemBar(
    itemName: String,
    @DrawableRes imageResId: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFFFF8F0))
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = itemName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = itemName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF333333),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start
        )
    }
}

