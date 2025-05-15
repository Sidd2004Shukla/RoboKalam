package com.example.robokalamassignment

import android.app.Application
import com.example.robokalamassignment.data.api.QuoteApi
import com.example.robokalamassignment.data.db.AppDatabase
import com.example.robokalamassignment.data.preferences.UserPreferences

class RobokalamApp : Application() {
    lateinit var database: AppDatabase
        private set
    
    lateinit var userPreferences: UserPreferences
        private set
    
    val quoteService by lazy {
        QuoteApi.create()
    }

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getDatabase(this)
        userPreferences = UserPreferences(this)
    }
} 