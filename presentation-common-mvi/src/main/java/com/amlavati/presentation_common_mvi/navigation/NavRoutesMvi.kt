package com.amlavati.presentation_common_mvi.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument


private const val ROUTE_POSTS = "posts"
private const val ROUTE_POST = "posts/%s"
private const val ROUTE_USER = "users/%s"
private const val ARG_POST_ID = "postId"
private const val ARG_USER_ID = "userId"

sealed class NavRoutesMvi(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    object Posts : NavRoutesMvi(ROUTE_POSTS)

    object Post : NavRoutesMvi(
        route = String.format(ROUTE_POST, "{$ARG_POST_ID}"),
        arguments = listOf(navArgument(ARG_POST_ID) {
            type = NavType.LongType
        })
    ) {
        fun routeForPost(postInput: PostInputMvi): String =
            String.format(ROUTE_POST, postInput.postId)

        fun fromEntry(entry: NavBackStackEntry): PostInputMvi {
            return PostInputMvi(entry.arguments?.getLong(ARG_POST_ID) ?: 0L)
        }
    }

    object User : NavRoutesMvi(
        route = String.format(ROUTE_USER, "{$ARG_USER_ID}"),
        arguments = listOf(navArgument(ARG_USER_ID) {
            type = NavType.LongType
        })
    ) {
        fun routeForUser(userInput: UserInputMvi) = String.format(ROUTE_USER, userInput.userId)

        fun fromEntry(entry: NavBackStackEntry): UserInputMvi {
            return UserInputMvi(entry.arguments?.getLong(ARG_USER_ID) ?: 0L)
        }
    }

}