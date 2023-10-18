package com.phatnv.do_jetpack_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phatnv.do_jetpack_compose.ui.theme.DojetpackcomposeTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DojetpackcomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SliderExample()
                }
            }
        }
    }
}

// ============================ Start Slider  ============================ //
fun roundedDecimalNumber(number: Double): AnnotatedString {
    if (number.toInt() == 0 || number.toInt() == 100) {
        return buildAnnotatedString {
            withStyle(SpanStyle(textDecoration = TextDecoration.None)) {
                append(number.toInt().toString())
            }
        }
    }
    val formattedNumber = String.format(Locale.getDefault(), "%.2f", number)
    return buildAnnotatedString {
        withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
            append(formattedNumber)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SliderExample() {
    var sliderPosition by remember { mutableStateOf(0f) }
    val roundedOnlyNumber = roundedDecimalNumber(sliderPosition.toDouble())

    var sliderPositionInRange by remember { mutableStateOf(0f..100f) }
    val minValue = roundedDecimalNumber(sliderPositionInRange.start.toDouble())
    val maxValue = roundedDecimalNumber(sliderPositionInRange.endInclusive.toDouble())

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Only ${roundedOnlyNumber.toString()}", color = Color.Black)
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            enabled = true,
            steps = 3,
            valueRange = 0f..50f,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
        )
        Spacer(modifier = Modifier.size(30.dp))
        Divider(thickness = 1.dp, color = Color.Black)
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            text = "Range $minValue - $maxValue",
            style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.Underline,
                        TextDecoration.LineThrough
                    )
                ),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
            ),
            overflow = TextOverflow.Ellipsis,
        )
        RangeSlider(
            value = sliderPositionInRange,
            steps = 5,
            valueRange = 0f..100f,
            onValueChange = { range -> sliderPositionInRange = range },
            onValueChangeFinished = {
                Log.d("Finish: ", "$minValue, $maxValue")
            }
        )
    }
}

// ============================ End Slider  ============================ //

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DojetpackcomposeTheme {
        Greeting("Android")
    }
}