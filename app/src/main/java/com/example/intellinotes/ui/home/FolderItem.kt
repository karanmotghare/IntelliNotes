package com.example.intellinotes.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intellinotes.R

@Composable
fun FolderItem(
    folder : FolderUiModel,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ){

        Icon(
            painter = painterResource(folder.iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .height(28.dp)
                .width(28.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = folder.title,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = folder.count.toString(),
            color = colorResource(R.color.dark_909090)
        )

        Icon(
            painter = painterResource(R.drawable.ic_chevron_right),
            contentDescription = "right click",
            tint = colorResource(R.color.dark_909090),
            modifier = Modifier
                .height(18.dp)
                .width(18.dp)
        )
    }
}