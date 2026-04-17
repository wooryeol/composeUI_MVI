package com.example.compose_mvi.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_mvi.feature.login.presentation.LoginScreen
import com.example.compose_mvi.feature.login.presentation.LoginViewModel
import com.example.compose_mvi.feature.menu.presentation.MenuScreen
import com.example.compose_mvi.feature.splash.presentation.SplashScreen
import com.example.compose_mvi.feature.splash.presentation.SplashViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            val viewModel: SplashViewModel = hiltViewModel()
            SplashScreen(viewModel, navController)
        }

        composable("login") {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(viewModel, navController)
        }

        composable("menu") {
            MenuScreen(navController)
        }

        composable("create") {
            Text("Create Screen")
        }

        composable("read") {
            Text("Read Screen")
        }

        composable("update") {
            Text("Update Screen")
        }

        composable("delete") {
            Text("Delete Screen")
        }
    }
}