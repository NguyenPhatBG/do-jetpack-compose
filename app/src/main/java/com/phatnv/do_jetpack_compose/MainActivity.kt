@file:OptIn(ExperimentalMaterial3Api::class)

package com.phatnv.do_jetpack_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Abc
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.phatnv.do_jetpack_compose.ui.theme.DojetpackcomposeTheme
import kotlinx.coroutines.flow.filter

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
    val focusManager = LocalFocusManager.current
    var isFocused by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .wrapContentSize(Alignment.Center)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    isFocused = false
                    focusManager.clearFocus()
                })
            }
    ) {
        DropdownBasicExample()
        Spacer(modifier = Modifier.size(10.dp))
        DropdownTextFieldExample()
        Spacer(modifier = Modifier.size(10.dp))
        DropdownOutlinedTextFieldExample()
        Spacer(modifier = Modifier.size(10.dp))
        ExposedDropdownMenu(
            items = arrayOf("An", "Bình", "Hạnh", "Phúc"),
            onItemSelected = {}
        )
        Spacer(modifier = Modifier.size(10.dp))
        DropdownWithFilterOptions(
            focusManager,
            isFocused,
            onFocusChanged = {
                isFocused = it.isFocused
            },
        )
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
            .background(Color.Red)

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
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
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

// Exposed Dropdown Menu Stack
@Composable
fun ExposedDropdownMenu(
    items: Array<String>,
    selected: String = items[0],
    onItemSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectOptionText by remember {
        mutableStateOf(selected)
    }
    val interactionSource = remember { MutableInteractionSource() }
    LaunchedEffect(interactionSource) {
        interactionSource.interactions
            .filter { it is PressInteraction.Press }
            .collect {
                expanded = !expanded
            }
    }
    ExposedDropdownMenuStack(
        textField = {
            OutlinedTextField(
                value = selectOptionText,
                onValueChange = {
                    selectOptionText = it
                },
                interactionSource = interactionSource,
                readOnly = true,
                trailingIcon = {
                    val rotation by animateFloatAsState(if (expanded) 180F else 0F)
                    Icon(
                        rememberVectorPainter(Icons.Default.ArrowDropDown),
                        contentDescription = "Dropdown Arrow",
                        Modifier.rotate(rotation),
                    )
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            )
        },
        dropdownMenu = { boxWidth, itemHeight ->
            Box(
                Modifier
                    .width(boxWidth)
                    .wrapContentSize(Alignment.TopStart)
            ) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    items.forEach { item ->
                        val color = if (selectOptionText == item) Color.Blue else Color.Black
                        Column() {
                            DropdownMenuItem(
                                text = {
                                    Text(text = item, color = color)
                                },
                                modifier = Modifier
                                    .height(itemHeight)
                                    .width(boxWidth),
                                onClick = {
                                    expanded = false
                                    selectOptionText = item
                                    onItemSelected(item)
                                }
                            )
                            if (items[items.lastIndex] != item)
                                Divider(
                                    color = Color.LightGray,
                                    modifier = Modifier
                                        .height(.5.dp)
                                        .fillMaxWidth()
                                )
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun ExposedDropdownMenuStack(
    textField: @Composable () -> Unit,
    dropdownMenu: @Composable (boxWidth: Dp, itemHeight: Dp) -> Unit
) {
    SubcomposeLayout { constraints ->
        val textFieldPlaceable =
            subcompose(ExposedDropdownMenuSlot.TextField, textField).first().measure(constraints)
        val dropdownPlaceable = subcompose(ExposedDropdownMenuSlot.Dropdown) {
            dropdownMenu(textFieldPlaceable.width.toDp(), textFieldPlaceable.height.toDp())
        }.first().measure(constraints)
        layout(textFieldPlaceable.width, textFieldPlaceable.height) {
            textFieldPlaceable.placeRelative(0, 0)
            dropdownPlaceable.placeRelative(0, textFieldPlaceable.height)
        }
    }
}

private enum class ExposedDropdownMenuSlot { TextField, Dropdown }

// Dropdown with filter options
@Composable
fun DropdownWithFilterOptions(
    focusManager: FocusManager,
    isFocused: Boolean,
    onFocusChanged: (FocusState) -> Unit
) {
    val contextForToast = LocalContext.current.applicationContext
    val focusRequester = remember { FocusRequester() }
    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")

    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    // remember the selected item
    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }

    // box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        TextField(
            value = selectedItem,
            modifier = Modifier
                .menuAnchor()
                .border(
                    width = 1.dp,
                    color = if (isFocused) Color.Blue else Color.DarkGray,
                    shape = RoundedCornerShape(5.dp),
                )
                .focusRequester(focusRequester)
                .onFocusChanged {
                    onFocusChanged(it)
                },
            onValueChange = { selectedItem = it },
            label = { Text(text = "Label") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.Gray
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
        )

        // filter options based on text field value
        val filteringOptions =
            listItems.filter { it.contains(selectedItem, ignoreCase = true) }

        if (filteringOptions.isNotEmpty()) {
            // menu
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                // this is a column scope
                // all the items are added vertically
                filteringOptions.forEach { selectionOption ->
                    // menu item
                    DropdownMenuItem(
                        text = {
                            Text(text = selectionOption)
                        },
                        onClick = {
                            focusManager.clearFocus()
                            selectedItem = selectionOption
                            Toast.makeText(contextForToast, selectedItem, Toast.LENGTH_SHORT).show()
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
