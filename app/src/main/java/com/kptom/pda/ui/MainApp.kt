package com.kptom.pda.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kptom.pda.common.Argument
import com.kptom.pda.ui.details.DetailsScreen
import com.kptom.pda.ui.home.HomeScreen
import com.kptom.pda.ui.users.UsersScreen

@Preview
@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) { backStackEntry ->
            HomeScreen(
                action1 = {
                    // In order to discard duplicated navigation events, we check the Lifecycle
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate(Routes.USERS)
                    }
                }
            )
        }
        composable(Routes.USERS) { backStackEntry ->
            UsersScreen(
                onUserClick = { username ->
                    // In order to discard duplicated navigation events, we check the Lifecycle
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Routes.DETAIL}/$username")
                    }
                }
            )
        }
        composable(
            route = "${Routes.DETAIL}/{${Argument.USERNAME}}",
            arguments = listOf(
                navArgument(Argument.USERNAME) {
                    type = NavType.StringType
                }
            ),
        ) {
            DetailsScreen()
        }
    }
}
