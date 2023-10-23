package com.phatnv.do_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.outlined.Abc
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
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
                    // https://semicolonspace.com/jetpack-compose-dropdown-menu-material3/
                    // https://stackoverflow.com/questions/58875567/how-to-create-rounded-border-button-using-jetpack-compose
                    ShowDropdownExample()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowDropdownExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        DropdownBasicExample()
        Spacer(modifier = Modifier.size(10.dp))
        DropdownTextFieldExample()
        Spacer(modifier = Modifier.size(10.dp))
        DropdownOutlinedTextFieldExample()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownOutlinedTextFieldExample() {
    val options = listOf("Item 1", "Item 2", "Item 3")
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectOptionText by remember {
        mutableStateOf("")
    }
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }
    val icon = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown
    val focusManager = LocalFocusManager.current

    Box {
        OutlinedTextField(
            readOnly = true,
            value = selectOptionText,
            onValueChange = { selectOptionText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                }
                .onFocusChanged {
                    expanded = it.isFocused
                },
            label = {
                Text(
                    text = "Country",
                    style = LocalTextStyle.current.copy(
                        textDecoration = TextDecoration.None,
                        fontWeight = FontWeight.SemiBold,
                    )
                )
            },
            placeholder = {
                Text(text = "Select your option", style = TextStyle(color = Color.LightGray))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Abc,
                    contentDescription = "",
                    tint = Color.Magenta
                )
            },
            trailingIcon = {
                Icon(icon, "", tint = Color.Magenta)
            },
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                disabledTextColor = Color.Black,
                textColor = Color.Black,
            ),
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                focusManager.clearFocus()
            },
            modifier = Modifier.width(with(LocalDensity.current) {
                textFieldSize.width.toDp()
            })
        ) {
            options.forEach { label ->
                DropdownMenuItem(
                    text = {
                        Text(text = label)
                    },
                    onClick = {
                        selectOptionText = label
                        expanded = false
                        focusManager.clearFocus()
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownTextFieldExample() {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4")
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedOptionText by remember {
        mutableStateOf(options[0])
    }
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }
    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = {
            expanded = !expanded
        }, modifier = Modifier
            .fillMaxWidth()

    ) {
        TextField(
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .menuAnchor()
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                },
            value = selectedOptionText,
            onValueChange = { selectedOptionText = it },
            label = {
                Text(
                    text = "Label"
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded, onDismissRequest = {
                expanded = false
            }, modifier = Modifier.width(with(LocalDensity.current) {
                textFieldSize.width.toDp()
            })
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(text = selectionOption)
                    },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    modifier = Modifier.width(with(LocalDensity.current) {
                        textFieldSize.width.toDp()
                    })
                )
            }
        }
    }
}


@Composable
fun DropdownBasicExample() {
    var expanded by remember {
        mutableStateOf(false)
    }
    val items = listOf("A", "B", "C", "D")
    val disabledValue = "B"
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                Color.Gray, shape = RoundedCornerShape(5.dp)
            )
            .border(
                width = 1.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(5.dp),
            )
            .clickable { expanded = true }
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = items[selectedIndex]
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(text = {
                    val disableText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disableText)
                }, onClick = {
                    selectedIndex = index
                    expanded = false
                })
            }
        }
    }
}