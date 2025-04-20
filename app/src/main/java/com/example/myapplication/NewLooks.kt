package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.myapplication.R
import androidx.compose.ui.tooling.preview.Preview



//class ExploreLooksActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyApplicationTheme {
//                val navController = rememberNavController()
//                NavHost(navController = navController, startDestination = "explore_looks") {
//                    composable("explore_looks") {
//                        ExploreNewLooksScreen(navController)
//                    }
//                    composable(
//                        "look_detail/{lookName}",
//                        arguments = listOf(navArgument("lookName") { type = NavType.StringType })
//                    ) { backStackEntry ->
//                        val lookName = backStackEntry.arguments?.getString("lookName") ?: ""
//                        LookDetailScreen(lookName)
//                    }
//                }
//            }
//        }
//    }
//}
@Preview(showBackground = true)
@Composable
fun ExploreNewLooksScreenPreview() {
    val navController = rememberNavController()
    ExploreNewLooksScreen(navController = navController)
}

@Composable
fun ExploreNewLooksScreen(navController: NavController) {
    val looks = listOf(
        Look("Glam Makeup", id = com.example.myapplication.R.drawable.glam_look),
        Look("Soft Makeup", id = com.example.myapplication.R.drawable.soft_look),
        Look("Bold Lips", id = com.example.myapplication.R.drawable.bold_look),
        Look("Natural Makeup", id = com.example.myapplication.R.drawable.natural_look),
        Look("Smokey Eyes", id = com.example.myapplication.R.drawable.smokey_look),
        Look("Soft Glam Look", id = com.example.myapplication.R.drawable.soft_glam),
      )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCEEE2))
      //      .verticalScroll(rememberScrollState) //  Enable vertical scroll
            .padding(16.dp)
    )
    {
        // Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF5B3924)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Explore New Looks",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5B3924)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        //Lazy grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(looks) { look ->
                LookCard(look) {
                    navController.navigate("look_detail/${look.name}")
                }
            }
        }

            Spacer(modifier = Modifier.height(20.dp))
            // Page Indicators at the bottom
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(if (index == 0) Color(0xFF5B3924) else Color(0xFFE0CBB5))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }

@Composable
fun LookCard(look: Look, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = look.id),
            contentDescription = look.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = look.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF5B3924),
            modifier = Modifier
                .background(Color(0xFFE0CBB5), RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun LookDetailScreen(lookName: String) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCEEE2)),
        color = Color.Transparent
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Details for $lookName",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5B3924)
            )
        }
    }
}

data class Look(val name: String, val id: Int)
