package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("signin") { SignInScreen(navController) }
        composable("signup") { SignupScreen(navController) }
    }
}

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF0DA), Color(0xFFE39562))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hey, gorgeous!",
                fontSize = 24.sp,
                color = Color(0xFF5D4037)
            )
            Text(
                text = "welcome to",
                fontSize = 14.sp,
                color = Color(0xFF5D4037)
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "appname",
                fontSize = 28.sp,
                color = Color(0xFFC47F6E)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.heart_image),
                contentDescription = "Heart Image",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("signup") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5D1B8)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Sign up", fontSize = 18.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { navController.navigate("signin") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5D1B8)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Sign in", fontSize = 18.sp, color = Color.Black)
            }
        }
    }
}

