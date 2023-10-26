@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.phatnv.do_jetpack_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.phatnv.do_jetpack_compose.ui.theme.DojetpackcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DojetpackcomposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        // https://semicolonspace.com/jetpack-compose-textfield/
                        // https://www.composables.com/tutorials/focus-text
                        // https://developer.android.com/jetpack/compose/touch-input/focus/change-focus-behavior
                        TextFieldExample()
                        Spacer(modifier = Modifier.height(20.dp))
                        TextFieldWithLabel()
                        Spacer(modifier = Modifier.height(20.dp))
                        TextFieldWithIcons()
                        Spacer(modifier = Modifier.height(20.dp))
                        TextFieldWithKeyboardActions()
                        Spacer(modifier = Modifier.height(20.dp))
                        TextFieldTogglePassword()
                        Spacer(modifier = Modifier.height(20.dp))
                        TextFieldValidationOrError()
                        Spacer(modifier = Modifier.height(20.dp))
                        TextFieldWithRef()
                    }
                }
            }
        }
    }
}

@Composable
fun TextFieldExample() {
    var value by remember {
        mutableStateOf("")
    }
    Column {
        TextField(
            value = value,
            onValueChange = { newText ->
                value = newText
            },
            singleLine = true,
        )
        Text(text = "Input Text: $value")
    }
}

@Composable
fun TextFieldWithLabel() {
    var value by remember {
        mutableStateOf("")
    }

    TextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        label = { Text(text = "Name") },
        placeholder = { Text(text = "Type your name") }
    )
}

@Composable
fun TextFieldWithIcons() {
    var value by remember {
        mutableStateOf("")
    }

    TextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email Icon"
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Person Icon"
            )
        },
        label = { Text(text = "Email") },
        placeholder = { Text(text = "Type your email") }
    )
}

@Composable
fun TextFieldWithKeyboardActions() {
    var value by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current.applicationContext
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        label = { Text(text = "Number") },
        placeholder = { Text(text = "Enter your number") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                Toast.makeText(context, "On Search Click: value = $value", Toast.LENGTH_SHORT)
                    .show()
            }
        )
    )
}

@Composable
fun TextFieldTogglePassword() {
    var value by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    TextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        label = { Text(text = "Password") },
        placeholder = { Text(text = "Enter your password") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Lock Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    imageVector = if (showPassword) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                    contentDescription = if (showPassword) "Show Password" else "Hide Password"
                )
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun TextFieldValidationOrError() {
    var value by remember {
        mutableStateOf("")
    }

    var isUserBelow18 by remember {
        mutableStateOf(false)
    }

    Column {
        TextField(
            value = value,
            onValueChange = { newText ->
                value = newText
                isUserBelow18 = false
            },
            label = { Text(text = "Age") },
            placeholder = { Text(text = "Enter your age") },
            isError = isUserBelow18,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // validate here
                    isUserBelow18 = validateAge(inputText = value)
                }
            )
        )

        if (isUserBelow18) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "You should be 18+",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

private fun validateAge(inputText: String): Boolean {
    return inputText.toInt() < 18
}

@Composable
fun TextFieldWithRef() {
    Row {
        val focusManager = LocalFocusManager.current
        Column {
            val (a, b, c) = FocusRequester.createRefs()
            TextField(
                modifier = Modifier
                    .focusRequester(a)
                    .focusProperties {
                        next = b
                    },
                value = "",
                onValueChange = {},
            )
            TextField(
                modifier = Modifier
                    .focusRequester(b)
                    .focusProperties {
                        previous = a
                        next = c
                    },
                value = "",
                onValueChange = {},
            )
            TextField(
                modifier = Modifier
                    .focusRequester(c)
                    .focusProperties {
                        previous = b
                    },
                value = "",
                onValueChange = {},
            )
        }
        Column {
            Button(onClick = {
                focusManager.moveFocus(FocusDirection.Previous)
            }) {
                Text("Previous")
            }
            Button(onClick = {
                focusManager.moveFocus(FocusDirection.Next)
            }) {
                Text("Next")
            }
        }
    }

}