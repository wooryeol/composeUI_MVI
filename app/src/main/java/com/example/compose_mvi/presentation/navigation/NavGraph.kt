package com.example.compose_mvi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_mvi.presentation.catFact.CatFactScreen
import com.example.compose_mvi.presentation.catFact.CatFactViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "catFact") {
        composable("catFact") {
            val viewModal: CatFactViewModel = hiltViewModel()
            CatFactScreen(viewModal)
        }
    }
}