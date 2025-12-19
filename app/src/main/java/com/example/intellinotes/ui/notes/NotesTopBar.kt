package com.example.intellinotes.ui.notes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.intellinotes.R

@Composable
fun NotesTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            shape = CircleShape,
            tonalElevation = 2.dp
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_left_chevron),
                    contentDescription = "Back"
                )
            }
        }

        Surface(
            shape = CircleShape,
            tonalElevation = 2.dp
        ) {
            IconButton(
                onClick = { /* menu later */ },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_three_dots),
                    contentDescription = "More"
                )
            }
        }
    }
}