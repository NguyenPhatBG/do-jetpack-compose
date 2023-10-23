package com.phatnv.do_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phatnv.do_jetpack_compose.models.PhotographItem
import com.phatnv.do_jetpack_compose.screens.DemoScreen
import com.phatnv.do_jetpack_compose.ui.theme.AppTheme

val photographItems = listOf(
    PhotographItem(
        description = "Green water and a boat",
        photoUrl = "https://images.unsplash.com/photo-1596324121712-5bbc14482174?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=880&q=80",
        author = "Photograph by Phat Nguyen"
    ),
    PhotographItem(
        description = "Rain drops on a flower",
        photoUrl = "https://images.unsplash.com/photo-1555662800-92f44b37a43d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=909&q=80",
        author = "Photograph by iOS"
    ),
    PhotographItem(
        description = "Green roof in front of the blue sky",
        photoUrl = "https://images.unsplash.com/photo-1512977851705-67ee4bf294f4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=860&q=80",
        author = "Photograph by Android"
    )
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.primary,
                    shadowElevation = 2.dp
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    DemoScreen(photographItems)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        DemoScreen(photographItems)
    }
}