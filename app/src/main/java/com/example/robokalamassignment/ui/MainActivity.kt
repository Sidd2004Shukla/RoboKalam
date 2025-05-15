package com.example.robokalamassignment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.robokalamassignment.RobokalamApp
import com.example.robokalamassignment.ui.navigation.Routes
import com.example.robokalamassignment.ui.screens.about.AboutScreen
import com.example.robokalamassignment.ui.screens.dashboard.DashboardScreen
import com.example.robokalamassignment.ui.screens.login.LoginScreen
import com.example.robokalamassignment.ui.screens.portfolio.PortfolioScreen
import com.example.robokalamassignment.ui.screens.quote.QuoteScreen
import com.example.robokalamassignment.ui.screens.splash.SplashScreen
import com.example.robokalamassignment.ui.theme.RobokalamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as RobokalamApp
        
        setContent {
            RobokalamTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    NavHost(
                        navController = navController,
                        startDestination = Routes.SPLASH
                    ) {
                        composable(Routes.SPLASH) {
                            SplashScreen(navController = navController, app = app)
                        }
                        composable(Routes.LOGIN) {
                            LoginScreen(navController = navController, app = app)
                        }
                        composable(Routes.DASHBOARD) {
                            DashboardScreen(navController = navController, app = app)
                        }
                        composable(Routes.PORTFOLIO) {
                            PortfolioScreen(navController = navController, app = app)
                        }
                        composable(Routes.QUOTE) {
                            QuoteScreen(navController = navController, app = app)
                        }
                        composable(Routes.ABOUT) {
                            AboutScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
} 