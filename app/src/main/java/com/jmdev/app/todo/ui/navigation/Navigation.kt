package com.jmdev.app.todo.ui.navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jmdev.app.todo.ui.navigation.core.LocalNavigationController
import com.jmdev.app.todo.ui.navigation.core.Routes
import com.jmdev.app.todo.ui.screens.History
import com.jmdev.app.todo.ui.screens.Home
import com.jmdev.app.todo.ui.screens.Info
import com.jmdev.app.todo.ui.theme.ToDoExpressTheme
import com.jmdev.app.todo.ui.viewmodel.AppViewModel

@Composable
fun Navigation(appViewModel: AppViewModel) {

    val navController = rememberNavController()
    ToDoExpressTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CompositionLocalProvider(LocalNavigationController provides navController) {
                NavHost(
                    startDestination = Routes.Home.route,
                    navController = navController,
                    enterTransition = {
                        scaleIn(spring(Spring.DampingRatioNoBouncy))
                    },
                    exitTransition = {
                        scaleOut(spring(Spring.DampingRatioNoBouncy))
                    },
                    popEnterTransition = {
                        scaleIn(spring(Spring.DampingRatioNoBouncy))
                    },
                    popExitTransition = {
                        scaleOut(spring(Spring.DampingRatioNoBouncy))
                    }
                ) {
                    composable(Routes.Home.route) {
                        Home(appViewModel = appViewModel)
                    }
                    composable(Routes.History.route) {
                        History(appViewModel = appViewModel)
                    }
                    composable(Routes.Info.route) {
                        Info()
                    }
                }
            }
        }
    }
}