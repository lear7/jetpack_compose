package com.lear.compose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lear.compose.common.Argument
import com.lear.compose.ui.details.DetailsScreen
import com.lear.compose.ui.home.HomeScreen
import com.lear.compose.ui.users.UsersScreen
import com.lear.compose.widgets.FpsCounterGraph

@Preview
@Composable
fun MainApp() {
    val TAG = "lihua"

    val navController = rememberNavController()
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.HOME
        ) {
            composable(Routes.HOME) { backStackEntry ->
                HomeScreen(
                    action1 = {
                        navController.navigate(Routes.USERS)
                    }
                )
            }
            composable(Routes.USERS) { backStackEntry ->
                UsersScreen(
                    onUserClick = { username ->
                        navController.navigate("${Routes.DETAIL}/$username")
                        // // In order to discard duplicated navigation events, we check the Lifecycle
                        // if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        //     Timber.i("redirecting...")
                        //     navController.navigate("${Routes.DETAIL}/$username")
                        // } else {
                        //     Timber.i("redirect not permitted, current state is: ${backStackEntry.lifecycle.currentState}")
                        // }
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
        // 放到底部左下角
        FpsCounterGraph(modifier = Modifier
            .align(Alignment.BottomStart)
            .height(40.dp))
    }
}
