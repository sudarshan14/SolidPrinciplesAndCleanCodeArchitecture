package com.amlavati.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amlavati.cleanarchitecture.ui.theme.SolidPrinciplesTheme
import com.amlavati.common.navigation.NavRoutes
import com.amlavati.presentation_common_mvi.navigation.NavRoutesMvi
import com.amlavati.presentation_post.post.PostScreen
import com.amlavati.presentation_user.user.UserScreen
import com.amlavati.presntation_post_mvi.list.PostListScreenMvi
import com.amlavati.presntation_post_mvi.list.PostListViewModelMvi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SolidPrinciplesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    innerPadding.calculateTopPadding()
                    val navController =
                        rememberNavController()
                    App(navController = navController)

                }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    NavHost(
        navController, startDestination = NavRoutesMvi.Posts.route
    ) {
        composable(route = NavRoutesMvi.Posts.route) {
            val viewModelPostListMvi = hiltViewModel<PostListViewModelMvi>()
            val navigationEvent = viewModelPostListMvi.navigationEvent
            val viewState by viewModelPostListMvi.viewState.collectAsState()
            PostListScreenMvi(viewModelPostListMvi, viewState, navController, navigationEvent)
            //    PostListScreen(hiltViewModel(), navController)

        }
        composable(
            route = NavRoutesMvi.Post.route,
            arguments = NavRoutesMvi.Post.arguments
        ) {
            PostScreen(
                hiltViewModel(),
                NavRoutes.Post.fromEntry(it)
            )
        }
        composable(
            route = NavRoutesMvi.User.route,
            arguments = NavRoutesMvi.User.arguments
        ) {
            UserScreen(
                hiltViewModel(),
                NavRoutes.User.fromEntry(it)
            )
        }
    }
}

