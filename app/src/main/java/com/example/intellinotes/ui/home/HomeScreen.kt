package com.example.intellinotes.ui.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.intellinotes.R
import com.example.intellinotes.ui.theme.Montserrat
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider

@Composable
fun HomeScreen(navController: NavController) {

//    val query by viewModel.searchQuery.collectAsState()

    val folders = listOf(
        Folder("Notes", 5, R.drawable.yellow_folder), // Assuming you have an ic_folder icon
        Folder("Recently Deleted", 2, R.drawable.yellow_bin), // Assuming an ic_delete icon
        Folder("Work", 12, R.drawable.yellow_folder),
        Folder("Personal", 8, R.drawable.yellow_folder),
    )

    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Surface(
                shape = CircleShape,
                color = Color.White,
                tonalElevation = 2.dp,
                shadowElevation = 2.dp
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add_new_folder),
                        contentDescription = "New folder",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Box(modifier = Modifier.width(12.dp))

            Surface(
                shape = RoundedCornerShape(50),
                color = Color.White,
                tonalElevation = 2.dp,
                shadowElevation = 2.dp
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .height(40.dp)
                        .clickable { }         // 👈 makes it oval/circle
                        .background(Color.White),        // 👈 fill
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Edit",
                        fontFamily = Montserrat,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(R.color.dark_303030)
                    )
                }
            }
        }

        Box(modifier = Modifier.padding(start = 8.dp, top = 24.dp)) {
            Text(
                text = "Folders",
                fontFamily = Montserrat,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.dark_303030)
            )
        }

        Box(modifier = Modifier.padding(start = 8.dp  )){
            Text(
                text = "cloud sync",
                fontSize = 16.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.dark_909090)
            )
        }

        Box(modifier = Modifier.padding(start = 8.dp, top = 12.dp)){
            Text(
                text = "Cloud",
                fontSize = 20.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.dark_303030)
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(folders) {folder ->
                    FolderListItem(folder = folder)
                    if (folder != folders.last()) {
                        HorizontalDivider(
                            color = colorResource(R.color.yellow_tint),
                            thickness = 1.dp,
                            modifier = Modifier.padding(start = 56.dp)
                        )
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier.weight(1f) // **CRUCIAL:** Takes up all remaining vertical space
        )

        Row {
            NotesSearchBar(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp,
                        vertical = 26.dp)
                    .size(50.dp)
            )
            Surface(
                shape = CircleShape,
                color = Color.White,
                tonalElevation = 2.dp,
                shadowElevation = 2.dp,
                modifier = Modifier.align(Alignment.CenterVertically)
                    .padding(end = 16.dp)
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_new_note),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

    }


}

data class Folder(
    val name: String,
    val count: Int,
    val iconResId: Int // Resource ID for the folder icon
)