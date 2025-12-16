package com.example.intellinotes.ui.notes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intellinotes.R
import com.example.intellinotes.ui.theme.Montserrat

@Composable
fun NotesScreen(
    folderId: String,
    onNoteClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp, start = 8.dp, end = 8.dp)
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
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
                        painter = painterResource(R.drawable.ic_left_chevron),
                        contentDescription = "backButton",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Surface(
                shape = CircleShape,
                color = Color.White,
                tonalElevation = 2.dp,
                shadowElevation = 2.dp
            ){
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_three_dots),
                        contentDescription = "more",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Box(modifier = Modifier.padding(start = 8.dp, top = 24.dp)) {
            Text(
                text = folderId,
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


    }
}