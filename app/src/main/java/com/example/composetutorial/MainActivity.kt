package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetutorial.user_details.UserDetailsScreen
import com.example.composetutorial.users_list.UsersListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApplication()
        }
    }
}

@Composable
fun MainApplication() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationKeys.SCREEN.USERS_LIST_SCREEN
    ) {
        composable(NavigationKeys.SCREEN.USERS_LIST_SCREEN) { UsersListScreen(navController) }
        composable(
            route = "${NavigationKeys.SCREEN.USER_DETAILS_SCREEN}/{${NavigationKeys.ARG.USER_ID}}",
            arguments = listOf(navArgument(NavigationKeys.ARG.USER_ID) { type = NavType.IntType })
        ) { navBackStackEntry ->
            UserDetailsScreen(
                navController,
                navBackStackEntry.arguments?.getInt(NavigationKeys.ARG.USER_ID)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UsersListScreen(null)
}