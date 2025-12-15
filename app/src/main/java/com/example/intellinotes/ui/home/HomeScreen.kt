package com.example.intellinotes.ui.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.intellinotes.R
import com.example.intellinotes.ui.theme.Montserrat

@Composable
fun HomeScreen(navController: NavController) {

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
                        .clickable {  }         // 👈 makes it oval/circle
                        .background(Color.White),        // 👈 fill
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Edit",
                        fontFamily = Montserrat,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }

}