package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*
import android.app.DatePickerDialog
import android.widget.DatePicker

@Composable
fun SimpleManualEntryScreen(
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    var productName by remember { mutableStateOf("") }
    var manufactureDate by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val maroon = Color(0xFF800000)
    val white = Color(0xFFFFFFFF)
    val gray = Color(0xFFE0E0E0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Title("Add Product", maroon)

        SimpleInputField("Product Name", productName) { productName = it }
        DateInputField("Manufacture Date", manufactureDate) { manufactureDate = it }
        DateInputField("Expiry Date", expiryDate) { expiryDate = it }
        SimpleInputField("Notes", notes, singleLine = false) { notes = it }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SimpleButton("Save", maroon, white) { onSave() }
            SimpleButton("Cancel", gray, maroon) { onCancel() }
        }
    }
}

@Composable
fun Title(text: String, color: Color) {
    BasicText(
        text = text,
        style = TextStyle(color = color, fontSize = 22.sp),
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun SimpleInputField(label: String, value: String, singleLine: Boolean = true, onValueChange: (String) -> Unit) {
    Column {
        BasicText(text = label, style = TextStyle(fontSize = 14.sp, color = Color.DarkGray))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5))
                .padding(8.dp)
        )
    }
}

@Composable
fun DateInputField(label: String, date: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePicker = remember {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                onDateSelected(String.format("%02d/%02d/%04d", day, month + 1, year))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    Column {
        BasicText(text = label, style = TextStyle(fontSize = 14.sp, color = Color.DarkGray))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5))
                .padding(8.dp)
                .clickable { datePicker.show() }
        ) {
            BasicText(text = if (date.isNotEmpty()) date else "Tap to pick date", style = TextStyle(fontSize = 16.sp, color = Color.Black))
        }
    }
}

@Composable
fun SimpleButton(text: String, background: Color, textColor: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(background)
            .padding(vertical = 10.dp, horizontal = 24.dp)
            .clickable { onClick() }
    ) {
        BasicText(text = text, style = TextStyle(fontSize = 16.sp, color = textColor))
    }
}
