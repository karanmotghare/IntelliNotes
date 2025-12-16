package com.example.intellinotes.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intellinotes.ui.theme.Montserrat
import com.example.intellinotes.R

@Composable
fun FolderListItem(
    folder: Folder,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }//folderlistitem dont need to know about navigation so callback
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // To push the count/arrow to the end
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(folder.iconResId),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = colorResource(R.color.yellow_tint)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = folder.name,
                fontSize = 18.sp,
                fontFamily = Montserrat,
                color = colorResource(R.color.dark_303030)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = folder.count.toString(),
                fontSize = 16.sp,
                fontFamily = Montserrat,
                color = colorResource(R.color.dark_909090)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right), // Assuming you have an arrow icon
                contentDescription = null,
                tint = Color(0xFFC0C0C0), // Light gray arrow
                modifier = Modifier.size(20.dp)
            )
        }
    }
}