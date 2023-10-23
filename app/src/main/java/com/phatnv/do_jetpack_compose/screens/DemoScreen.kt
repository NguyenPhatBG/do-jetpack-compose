package com.phatnv.do_jetpack_compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.phatnv.do_jetpack_compose.R
import com.phatnv.do_jetpack_compose.models.PhotographItem
import com.phatnv.do_jetpack_compose.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoScreen(items: List<PhotographItem>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {
        item {
            TopAppBar(
                title = {
                    Text(
                        text = "The gallery",
                        style = AppTheme.typography.h1,
                        color = AppTheme.colors.primary
                    )
                },
                modifier = Modifier.background(Color.Transparent)
            )
        }
        items(items) { item ->
            GalleryItem(item)
        }
    }
}

@Composable
fun GalleryItem(item: PhotographItem) {
    Column(
        modifier = Modifier.padding(AppTheme.dimensions.paddingMedium)
    ) {
        Text(
            text = item.description,
            style = AppTheme.typography.body,
            color = AppTheme.colors.textPrimary,
            modifier = Modifier.padding(AppTheme.dimensions.paddingSmall)
        )
//        AsyncImage(
//            model = item.photoUrl,
//            contentDescription = null,
//            modifier = Modifier.size(256.dp)
//        )
        AsyncImage(
            model = "https://delasign.com/delasignBlack.png",
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "The design logo",
            modifier = Modifier.size(72.dp),
        )
        Text(
            text = item.author,
            style = AppTheme.typography.caption,
            color = AppTheme.colors.textSecondary,
            modifier = Modifier.padding(AppTheme.dimensions.paddingSmall)
        )
    }
}