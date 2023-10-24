package com.phatnv.do_jetpack_compose.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.phatnv.do_jetpack_compose.R
import com.phatnv.do_jetpack_compose.model.PhotographItem
import com.phatnv.do_jetpack_compose.theme.AppTheme
import kotlinx.coroutines.launch

enum class ScreensRoute {
    SCREEN_1, SCREEN_2, SCREEN_3
}
data class MenuItem(
    val route: ScreensRoute,
    val icon: ImageVector,
    val title: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val items = listOf(
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
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val drawerScreens = listOf(
        MenuItem(ScreensRoute.SCREEN_1, Icons.Filled.Home , stringResource(id = R.string.home_screen)),
        MenuItem(ScreensRoute.SCREEN_2, Icons.Filled.ShoppingCart, stringResource(id = R.string.product_screen)),
        MenuItem(ScreensRoute.SCREEN_3, Icons.Filled.Person, stringResource(id = R.string.about_screen)),
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.size(150.dp),
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                        );
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    drawerScreens.forEach {
                        NavigationDrawerItem(
                            label = { Text(text = it.title) },
                            icon = { Icon(imageVector = it.icon, contentDescription = null) },
                            selected = false,
                            onClick = {

                            }
                        )
                    }
                }
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )

                        }
                    },
                    title = {
                        Text(
                            "Top app bar",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Bottom app bar",
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) { innerPadding ->
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
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
        AsyncImage(
            model = item.photoUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1280f / 847f),
        )
        Text(
            text = item.author,
            style = AppTheme.typography.caption,
            color = AppTheme.colors.textSecondary,
            modifier = Modifier.padding(AppTheme.dimensions.paddingSmall)
        )
    }
}