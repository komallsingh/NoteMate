package com.komal.notemate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
// Top
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun NoteMate() {
    val songs = remember {
        mutableStateListOf(
            Song("Heat Waves", ""),
            Song("The Night We Met", ""),
            Song("Memories", ""),
            Song("Photograph", "")
        )
    }
    var showDialog by remember { mutableStateOf(false) }
    //var editid by remember  { mutableStateOf<Int?>(null) }
    var newSong by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp), Arrangement.Top
    ) {
        Text(
            text = "Your Own Music Notebook",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally).height(40.dp).offset(y=12.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(songs) { song ->
                SongItem(song)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Button(
            onClick = {
                //songs.add(Song("New Song ${songs.size + 1}", ""))
                showDialog=true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp).offset(y=-20.dp)
        ) {
            Text("Add Your Song")
        }
        //SHOW DIALOG
        if(showDialog){
            androidx.compose.material3.AlertDialog(
                onDismissRequest ={
                    showDialog=false
                    newSong=""
                },
                confirmButton={
                    Button(
                        onClick = {
                            if(newSong.isNotBlank()){
                                songs.add(Song(newSong,""))
                                newSong=""
                                showDialog=false
                            }
                        },
                        modifier=Modifier.fillMaxWidth()
                    ) {
                        Text("Add")
                    }
                },
                title = {Text("Enter your Song")},
                text = {
                    OutlinedTextField(
                        value=newSong,
                        onValueChange = {
                            newSong=it
                        },
                        label={ Text("Your Song") }
                    )
                }
            )
        }




        }
}

@Composable
fun SongItem(song: Song) {
    var description: String by remember { mutableStateOf(song.description) }
    var isFavorite by remember { mutableStateOf(song.isFavorite) }
    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (song.name == "Heat Waves") {
                Image(
                    painter = painterResource(id = R.drawable.heatwaves),
                    contentDescription = "Heat Waves Cover",
                    modifier = Modifier.fillMaxSize()
                        .size(180.dp)
                        .padding(end = 5.dp)
                )
            }
            if (song.name == "The Night We Met") {
                Image(
                    painter = painterResource(id = R.drawable.night),
                    contentDescription = "Night Cover",
                    modifier = Modifier.fillMaxSize()
                        .size(180.dp)
                        .padding(end = 5.dp)
                )
            }
            if(song.name=="Memories"){
                Image(painterResource(id = R.drawable.memo),
                contentDescription = "Memories Cover",
                    modifier = Modifier.fillMaxWidth().size(180.dp).padding(end=5.dp))
            }
            if (song.name == "Photograph") {
                Image(
                    painter = painterResource(id = R.drawable.photo),
                    contentDescription = "Photo Cover",
                    modifier = Modifier.fillMaxSize()
                        .size(180.dp)
                        .padding(end = 5.dp)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(song.name, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Icon",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                IconButton(onClick = {
                    isFavorite = !isFavorite
                    song.isFavorite = isFavorite
                }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.LightGray
                    )
                }

                //Text(text = song.name)
            }
            OutlinedTextField(
                    value = description,
                    onValueChange = {
                        description = it
                        song.description = it
                    },
                    label = { Text("Write what you feel!") },
                    modifier = Modifier.fillMaxWidth().padding(20.dp)
                )
        }
    }
}

data class Song(
    val name: String,
    var description: String,
    var isEditing: Boolean = false,
    var isFavorite: Boolean = false
)
