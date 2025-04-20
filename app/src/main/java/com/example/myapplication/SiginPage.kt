package com.example.myapplication

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.platform.LocalContext


@Composable
fun SignInScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

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
            .fillMaxSize()
//            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = "LOGIN",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFA23E48)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Username Field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username", color = Color.Black) },
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = Color.Black) },
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            visualTransformation = PasswordVisualTransformation(), // 👈 hides the input
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Remember Me and Forgot Password
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = rememberMe, onCheckedChange = { rememberMe = it })
                Text(text = "Remember me")
            }
            Text(
                text = "Forgot Password",
                color = Color.Magenta,
                modifier = Modifier.clickable { /* Forgot Password Logic */ }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Login Button
        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                                navController.navigate("basicDetailsScreen")
                            } else {
                                Toast.makeText(context, "Login Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                } else {
                    Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC87F4F)),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Login", fontSize = 20.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Sign Up Navigation
        Row {
            Text(
                text = "Don't have an account? ",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "Sign Up",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFA23E48),
                modifier = Modifier.clickable { navController.navigate("signup") }
            )
        }

        Spacer(modifier = Modifier.weight(1f))
         }

    }

}
