package com.phatnv.do_jetpack_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
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
        Text("Only $roundedOnlyNumber", color = Color.Black)
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
                        TextDecoration.Underline, TextDecoration.LineThrough
                    )
                ),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
            ),
            overflow = TextOverflow.Ellipsis,
        )
        RangeSlider(value = sliderPositionInRange,
            steps = 5,
            valueRange = 0f..100f,
            onValueChange = { range -> sliderPositionInRange = range },
            onValueChangeFinished = {
                Log.d("Finish: ", "$minValue, $maxValue")
            })
        Spacer(modifier = Modifier.size(30.dp))
        Divider(thickness = 1.dp, color = Color.Black)
        Spacer(modifier = Modifier.size(30.dp))
        var sliderPosition by remember { mutableStateOf(15f) }
        SliderWithLabel(
            value = sliderPosition,
            finiteEnd = false,
            labelMinWidth = 30.dp,
            valueRange = 0f..100f,
            onRadiusChange = {
                sliderPosition = it
            },
        )
    }
}

// ============================ End Slider  ============================ //
// ============================ Start Slider With SliderLabel  ============================ //
@Composable
fun SliderWithLabel(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    finiteEnd: Boolean,
    labelMinWidth: Dp = 24.dp,
    onRadiusChange: (Float) -> Unit
) {
    Column {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val offset = getSliderOffset(
                value = value, valueRange = valueRange, boxWidth = maxWidth,
                // Since we use a padding of 4.dp on either sides of the SliderLabel
                // we need to account for this in our calculation
                labelWidth = labelMinWidth + 8.dp
            )
            val endValueText =
                if (!finiteEnd && value >= valueRange.endInclusive) "${value.toInt()}+" else value.toInt()
                    .toString()

            SliderLabel(label = valueRange.start.toInt().toString(), minWidth = labelMinWidth)

            if (value > valueRange.start) {
                SliderLabel(
                    label = endValueText,
                    minWidth = labelMinWidth,
                    modifier = Modifier.padding(start = offset)
                )
            }
        }
        Slider(
            enabled = true,
            steps = 5,
            value = value,
            onValueChange = {
                onRadiusChange(it)
            },
            valueRange = valueRange,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun SliderLabel(label: String, minWidth: Dp, modifier: Modifier = Modifier) {
    Text(
        label, textAlign = TextAlign.Center, color = Color.White,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(4.dp)
            )
            .padding(4.dp)
            .defaultMinSize(minWidth = minWidth),
    )
}

private fun getSliderOffset(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    boxWidth: Dp,
    labelWidth: Dp,
): Dp {
    val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
    val positionFraction = calcFraction(valueRange.start, valueRange.endInclusive, coerced)
    return (boxWidth - labelWidth) * positionFraction
}

// Calculate the 0..1 fraction that "pos" value presents between "a" and "b"
private fun calcFraction(a: Float, b: Float, pos: Float) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(0f, 1f)
// ============================ End Slider With SliderLabel  ============================ //
