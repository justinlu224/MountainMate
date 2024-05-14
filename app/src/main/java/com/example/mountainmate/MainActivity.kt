package com.example.mountainmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mountainmate.ui.itemlist.ItemListScreen
import com.example.mountainmate.ui.schedule.ScheduleScreen
import com.example.mountainmate.ui.theme.MountainMateTheme
import dagger.hilt.android.AndroidEntryPoint

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector? = null) {
    data object Home : Screen("Home", R.string.home, Icons.Default.Home)
    data object Schedule : Screen("Schedule", R.string.schedule, Icons.Default.List)

    data object ItemList : Screen("ItemList", R.string.item_list)
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val screens = listOf<Screen>(Screen.Home, Screen.Schedule)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MountainMateTheme {
                MainScreen()
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun MainScreen() {
        val navController = rememberNavController()
        var showBottomBar by remember {
            mutableStateOf(true)
        }

        LaunchedEffect(key1 = Unit) {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                showBottomBar = destination.route in listOf(Screen.Home.route, Screen.Schedule.route)
            }
        }

        Scaffold(
            bottomBar = {

                if(!showBottomBar) return@Scaffold

                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    screens.forEachIndexed { _, screen ->
                        NavigationBarItem(
                            selected = currentDestination?.route == screen.route,
                            onClick = {
                                      navController.navigate(screen.route){
                                          popUpTo(navController.graph.findStartDestination().id) {
                                              saveState = true
                                          }
                                          launchSingleTop = true
                                          restoreState = true
                                      }
                            },
                            icon = {
                                Icon(imageVector = screen.icon!!, contentDescription = screen.route)
                            },
                            label = {
                                Text(
                                    text = stringResource(id = screen.resourceId)
                                )
                            }
                        )
                    }
                }
            }
        ) { paddingValues ->
            NavHost(navController = navController, startDestination = Screen.Home.route, modifier = Modifier.padding(paddingValues)) {
                composable(Screen.Home.route) {
                    HomeScreen()
                }
                composable(Screen.Schedule.route) {
                    ScheduleScreen(navController)
                }

                composable(Screen.ItemList.route) {
                    ItemListScreen()
                }
            }
        }
    }

    @Composable
    private fun HomeScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Home Screen",
                textAlign = TextAlign.Center
            )
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun PreviewMainScreen() {
        MainScreen()
    }
}
