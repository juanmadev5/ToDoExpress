package com.jmdev.app.todo.ui.navigation.core

sealed class Routes (val route: String) {
    data object Home : Routes("home")
    data object Info : Routes("info")
    data object History : Routes("taskHistory")
}