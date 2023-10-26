@file:OptIn(ExperimentalMaterial3Api::class)

package com.phatnv.do_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phatnv.do_jetpack_compose.ui.theme.DojetpackcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DojetpackcomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    // https://semicolonspace.com/android-compose-button-corner-style/
                    ButtonExample()
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ButtonExample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Create new a account")
        }
        FilledTonalButton(onClick = { /*TODO*/ }) {
            Text(text = "Less prominent than Button")
        }
        OutlinedButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Add to Cart",
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Add to Cart")
        }
        OutlinedIconButton(
            onClick = { /*TODO*/ },
            border = BorderStroke(1.dp, Color.Blue),
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                Icons.Filled.Favorite, tint = Color.Red, contentDescription = null
            )
        }
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(text = "Elevated button")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Text Button")
        }
        IconButton(modifier = Modifier.then(Modifier.size(24.dp)), onClick = { /*TODO*/ }) {
            Icon(
                Icons.Filled.Search,
                "contentDescription",
            )
        }
        Icon(
            Icons.Filled.Search,
            contentDescription = null,
            modifier = Modifier.clickable { }
        )
        // Progress Button (loading)
        var loading by remember {
            mutableStateOf(false)
        }
        ProgressButtonExample(
            loading = loading,
            onClick = { loading = !loading },
        )
        // How to set drawable as a background to image in jetpack compose?
        Box {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.clickable {
                    println("Button Clicked!")
                }
            )
        }
        // Floating action button
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingActionButton(
                onClick = {},
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(alignment = Alignment.BottomEnd),
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    }
}


@Composable
fun ProgressButtonExample(loading: Boolean, onClick: () -> Unit, enabled: Boolean = true) {
    Box {
        ProgressButton(
            onClick = onClick,
            loading = loading,
            color = Color.Black,
            progressColor = Color.White,
            enabled = enabled,
            modifier = Modifier
                .padding(16.dp)
                .height(46.dp)
                .align(Alignment.Center),
        ) {
            Text(text = "Refresh", color = Color.White)
        }
    }
}

@Composable
fun ProgressButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    color: Color,
    progressColor: Color,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    val contentAlpha by animateFloatAsState(targetValue = if (loading) 0f else 1f)
    val loadingAlpha by animateFloatAsState(targetValue = if (loading) 1f else 0f)
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        enabled = enabled,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(size = 16.dp)
                    .graphicsLayer {
                        alpha = loadingAlpha
                    },
                color = progressColor,
                strokeWidth = 2.dp,
            )
            Box(modifier = Modifier.graphicsLayer {
                alpha = contentAlpha
            }) {
                content()
            }
        }
    }
}