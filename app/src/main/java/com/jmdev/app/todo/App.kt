package com.jmdev.app.todo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        const val DB_NAME = "app-database"
    }
}