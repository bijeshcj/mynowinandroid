package com.example.mynowinandroid.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynowinandroid.ui.following.FollowingRoute
import com.example.mynowinandroid.ui.foryou.ForYouRoute

@Composable
fun NiaNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NiaDestinations.FOR_YOU_ROUTE,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NiaDestinations.FOR_YOU_ROUTE) {
            ForYouRoute(modifier)
        }
        composable(NiaDestinations.EPISODES_ROUTE) {
            Text("EPISODES", modifier)
        }
        composable(NiaDestinations.SAVED_ROUTE) {
            Text("SAVED", modifier)
        }
        composable(NiaDestinations.FOLLOWING_ROUTE) {
            FollowingRoute(
                navigateToTopic = { navController.navigate(NiaDestinations.TOPICS_ROUTE) },
                modifier = modifier,
            )
        }
        composable(NiaDestinations.TOPICS_ROUTE) {
            Text("TOPICS", modifier)
        }
    }
}
