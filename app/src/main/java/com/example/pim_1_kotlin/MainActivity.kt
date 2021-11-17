package com.example.pim_1_kotlin

import android.content.Context
import android.content.res.Configuration
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.pim_1_kotlin.ui.theme.PIM_1_KotlinTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainActivityViewModel


    companion object {
        lateinit var appContext: Context
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainActivity.appContext = applicationContext

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getStartups()

        setContent {
            Navigation()
        }
    }


    @Composable
    fun StartupList(liveStartups: LiveData<List<Startup>>, navController: NavController) {

        PIM_1_KotlinTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Startup Name Generator") },
                        backgroundColor = Color.White,
                        actions = {
                            IconButton(onClick = { navController.navigate(Screen.SavedScreen.withArgs()) }) {
                                Icon(Icons.Filled.Menu, "forwardIcon")
                            }
                        },
                    )
                }
            ) {
                val startups by liveStartups.observeAsState(initial = emptyList())

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    startups.forEachIndexed { index, startup ->
                        Log.d("index", "$index")
                        item {
                            StartupCard(
                                startup = startup,
                                index = index,
                                lastIndex = startups.lastIndex,
                                navController = navController
                            )
                        }

                    }
                }
            }
        }

    }


    @Composable
    fun StartupCard(startup: Startup, index: Int, lastIndex: Int, navController: NavController) {

        if (index == lastIndex) {
            viewModel.getStartups()
        }
        Row(modifier = Modifier.padding(all = 0.dp)) {
            Surface(
                shape = MaterialTheme.shapes.large, elevation = 1.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(vertical = 30.dp, horizontal = 20.dp)
                ) {
                    Text(
                        text = startup.name,
                        style = MaterialTheme.typography.body2
                    )

                    var gray by remember {
                        mutableStateOf(true)
                    }


                    if (navController.currentDestination?.route == Screen.MainScreen.route) {
                        IconButton(
                            onClick = {
                                startup.saved = !startup.saved

                            },
                        ) {
                            Icon(
                                Icons.Outlined.Favorite,
                                contentDescription = "Favorite",
                                modifier = Modifier.size(24.dp),
                                tint = (if (!startup.saved) Color.Gray else Color.Red)
                            )
                        }
                    }


                }
            }

        }
        Spacer(modifier = Modifier.height(4.dp))
    }


    @Composable
    fun SavedSuggestions(liveStartups: LiveData<List<Startup>>, navController: NavController) {

        PIM_1_KotlinTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Saved Suggestions") },
                        backgroundColor = Color.White,
                        navigationIcon = {
                            IconButton(onClick = { navController.navigate(Screen.MainScreen.withArgs()) }) {
                                Icon(Icons.Filled.ArrowBack, "backIcon")
                            }
                        },
                    )
                }
            ) {

                val startups by liveStartups.observeAsState(initial = emptyList())

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    startups.forEachIndexed { index, startup ->
                        if (startup.saved) {
                            item {
                                StartupCard(
                                    startup = startup,
                                    index = index,
                                    lastIndex = startups.lastIndex,
                                    navController = navController
                                )
                            }
                        }
                    }
                }

            }
        }

    }


    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
            composable(route = Screen.MainScreen.route) {
                StartupList(liveStartups = viewModel.liveStartups, navController = navController)
            }
            composable(
                route = Screen.SavedScreen.route

            ) { entry ->
                SavedSuggestions(
                    liveStartups = viewModel.liveStartups,
                    navController = navController
                )
            }

        }
    }

}

