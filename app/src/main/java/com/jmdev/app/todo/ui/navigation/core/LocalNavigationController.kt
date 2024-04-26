package com.jmdev.app.todo.ui.navigation.core

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavigationController = staticCompositionLocalOf<NavController> {
    error("NavController not provided")
}