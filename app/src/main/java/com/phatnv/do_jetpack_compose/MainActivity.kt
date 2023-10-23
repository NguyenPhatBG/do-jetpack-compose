package com.phatnv.do_jetpack_compose

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.util.LinkifyCompat
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
                    TextExample()
                }
            }
        }
    }
}


@Composable
fun TextMeasure(text: String) {
    Text(
        text,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.error, shape = CircleShape)
            .badgeLayout(),
        color = Color.White,
    )
}

@Composable
fun Hyperlink(modifier: Modifier = Modifier, text: String? = null, mask: Int = Linkify.WEB_URLS) {
    val context = LocalContext.current
    val customLinkifyTextView = remember {
        TextView(context)
    }
    AndroidView(factory = { customLinkifyTextView }, modifier = modifier) { textView ->
        // https://kotlinlang.org/docs/null-safety.html#what-s-next
        textView.text = text ?: ""
        LinkifyCompat.addLinks(textView, mask)
        Linkify.addLinks(
            textView,
            Patterns.PHONE,
            "tel:",
            Linkify.sPhoneNumberMatchFilter,
            Linkify.sPhoneNumberTransformFilter,
        )
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}

// Made the following modifier using Modifier.layout
fun Modifier.badgeLayout() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    // based on the expectation of only one line of text
    val minPadding = placeable.height / 4

    val with = maxOf(placeable.width + minPadding, placeable.height)
    layout(with, placeable.height) {
        placeable.place((with - placeable.width) / 2, 0)
    }
}

fun Context.showToast(message: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, length).show()
}

private const val TAG_URL = "ANNOTATION_TAG_URL"

fun attachLink(
    source: String, segment: String, link: String
): AnnotatedString {
    // Builder to attach metadata(link)
    val builder = AnnotatedString.Builder()
    // Load current text into the builder
    builder.append(source)

    // Get the start of the span "My website"
    val start = source.indexOf(segment)
    // Get the end of the span
    val end = start + segment.length
    // Create a hyperlink text style
    val hyperlinkStyle = SpanStyle(
        color = Color.Blue, textDecoration = TextDecoration.Underline
    )

    // Style "My website" to make it look like a link
    builder.addStyle(hyperlinkStyle, start, end)
    // Attach the link to the span. We can then access it via the TAG_URL
    builder.addStringAnnotation(TAG_URL, link, start, end)

    return builder.toAnnotatedString()
}


@OptIn(ExperimentalTextApi::class)
@Preview(showBackground = true)
@Composable
fun TextExample() {
    /**
     * https://github.com/DmytroShuba/Custom-Theme-In-Jetpack-Compose
     * https://github.com/Foso/Jetpack-Compose-Playground/blob/master/app/src/main/java/de/jensklingenberg/jetpackcomposeplayground/mysamples/github/foundation/TextExample.kt
     * https://stackoverflow.com/questions/69987940/jetpack-compose-custom-badge-with-dynamic-text-size-with-circleshape-is-not-draw
     * https://dmytroshuba.com/blog/complete-guide-to-rich-text-in-jetpack-compose/
     * https://developer.android.com/jetpack/compose/text?hl=vi
     * https://developer.android.com/jetpack/compose/text/display-text
     * */
    val layoutResult = remember {
        mutableStateOf<TextLayoutResult?>(null)
    }
    val context = LocalContext.current
    val offset = Offset(5.0f, 10.0f)
    Column(modifier = Modifier.padding(10.dp)) {
        Text("Just Text")
        Text("Text with cursive font", style = TextStyle(fontFamily = FontFamily.Cursive))
        Text(
            text = "Text with LineThrough",
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "Text with underline",
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        // Text combine, clickable
        Text(text = "Text with underline, line through and bold", style = TextStyle(
            textDecoration = TextDecoration.combine(
                listOf(
                    TextDecoration.Underline,
                    TextDecoration.LineThrough,
                )
            ),
            fontWeight = FontWeight.Bold,
        ), modifier = Modifier.clickable {
            // By default:
            // Toast.makeText(context, "Hello World!", Toast.LENGTH_SHORT).show();
            context.showToast("Hello World!")
        })
        // Text Overflow
        Text(
            text = "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        // AnnotatedString/RichText
        Text(
            text = "Hello World!", style = TextStyle(
                fontSize = 24.sp, shadow = Shadow(
                    color = Color.Blue, offset = offset, blurRadius = 3f
                )
            )
        )
        // Layout measure
        // convenience API of creating a custom LayoutModifier modifier, without having to create
        // a class or an object that implements the LayoutModifier interface.
        Row(modifier = Modifier.padding(all = 10.dp)) {
            TextMeasure("1")
            Spacer(modifier = Modifier.size(10.dp))
            TextMeasure("10")
            Spacer(modifier = Modifier.size(10.dp))
            TextMeasure("100")
            Spacer(modifier = Modifier.size(10.dp))
            TextMeasure("1000")
        }
        // Hyperlink
        Hyperlink(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = "Good URL: https://www.google.com/"
        )
        // Clickable text
        val uriHandler = LocalUriHandler.current
        val text = "Here is my website"
        val link = "https://www.google.com/"
        val annotatedString = attachLink(
            source = text, segment = "my website", link = link,
        )
        ClickableText(text = annotatedString, onClick = {
            annotatedString.getStringAnnotations(
                TAG_URL, it, it
            ).firstOrNull()?.let { url ->
                uriHandler.openUri(url.item)
            }
        })
        Spacer(modifier = Modifier.size(10.dp))
        // Display text from resource
        Text(stringResource(R.string.hello_world))
        Spacer(modifier = Modifier.size(10.dp))
        // Gradient text
        val gradientColors = listOf(Color.Cyan, Color.Gray, Color.Red, Color.Green)
        Text(
            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradientColors
                )
            )
        )
        Spacer(modifier = Modifier.size(10.dp))
        // TextStyle, SpanStyle
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("H")
            }
            append("ello ")

            withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
                append("W")
            }
            append("orld")
        })
        Spacer(modifier = Modifier.size(10.dp))
        // Gradient RichText (Opacity in a span of text)
        val rainbowColors = listOf(Color.Red, Color.Yellow, Color.Blue, Color.Magenta)
        Text(text = buildAnnotatedString {
            append("Do not allow people to dim your shine\n")
            withStyle(
                SpanStyle(
                    brush = Brush.linearGradient(
                        colors = rainbowColors
                    ), alpha = .5f
                )
            ) {
                append("because they are blinded")
            }
            append("\nTell them to put some sunglasses on. ❤️")
        })
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .background(
                    color = Color.Gray.copy(alpha = .2f)
                )
                .border(
                    width = 1.dp,
                    color = Color.Red,
                    shape = RoundedCornerShape(5.dp),
                )
                .padding(10.dp)
        ) {
            Text(buildString {
                repeat(20) {
                    append("_")
                    append("|")
                }
            }, letterSpacing = 4.sp, onTextLayout = { layoutResult.value = it })
            Spacer(modifier = Modifier.size(10.dp))
            // Paragraph (ParagraphStyle)
            val reusableModifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(vertical = 12.dp)
                .offset(x = 20.dp)
            Text(buildAnnotatedString {
                withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("Hello\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold, color = Color.White
                        )
                    ) {
                        append("World\n")
                    }
                    append("Compose")
                }
            }, modifier = Modifier.then(reusableModifier.clickable {  }))
        }

    }
}
