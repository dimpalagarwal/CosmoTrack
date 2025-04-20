package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

@Composable
fun SignupScreen(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var privacyAccepted by remember { mutableStateOf(false) }

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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))


            // Title
            Text(
                text = "SIGN UP",
                fontSize = 24.sp,
                color = Color(0xFFA23E48),
                modifier = Modifier.padding(top = 40.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // First Name & Last Name Fields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name", color = Color.Black) },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name", color = Color.Black) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color.Black) },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.Black) },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Confirm Password Field
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password", color = Color.Black) },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
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
                        password.isNotEmpty() && confirmPassword == password && privacyAccepted
                    ) {

                        // Firebase Signup
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        context,
                                        "Signup Successful!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.navigate("basicDetailsScreen")
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Signup Failed: ${task.exception?.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    } else {
                        Toast.makeText(
                            context,
                            "Please fill all fields correctly!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFC87F4F)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign Up", fontSize = 30.sp, color = Color.White)
            }
        }
    }
}


