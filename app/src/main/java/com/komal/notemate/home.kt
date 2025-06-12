package com.komal.notemate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun homeScreen(navController: NavController){
    var mood by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Vibe Today?",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp).align(Alignment.CenterHorizontally)
                .offset(y = 12.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box {
            Button(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth(0.9f)) {
                Text("CHOOSE")
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "Arrow Down"
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
            {
                DropdownMenuItem(text={ Text("Nostalgia",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    textAlign = TextAlign.Center) },
                    onClick ={
                    navController.navigate("nostalgia")})
                Divider(
                    color = Color.Gray,
                    thickness = 3.dp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                DropdownMenuItem(text={ Text("Dance",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    textAlign = TextAlign.Center) },
                    onClick ={
                        navController.navigate("happy")})
                Divider(
                    color = Color.Gray,
                    thickness = 3.dp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                DropdownMenuItem(text={ Text("Retro",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    textAlign = TextAlign.Center) },
                    onClick ={
                        navController.navigate("nostalgia")})
                Divider(
                    color = Color.Gray,
                    thickness = 3.dp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}