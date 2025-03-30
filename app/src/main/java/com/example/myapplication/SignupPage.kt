package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SignupScreen(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var privacyAccepted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        // Title
        Text(
            text = "SIGN UP",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFA23E48),
            modifier = Modifier.padding(top = 40.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // First Name & Last Name Fields
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Confirm Password Field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Privacy & Policy Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = privacyAccepted, onCheckedChange = { privacyAccepted = it })
            Text(text = "I Agree with privacy and policy")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Sign-Up Button
        Button(
            onClick = {
                if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() &&
                    password.isNotEmpty() && confirmPassword == password && privacyAccepted) {
                    // Navigate back to Sign-In screen
                    navController.navigate("SignInScreen")
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFC87F4F)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sign Up",fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif)
        }
    }
}

