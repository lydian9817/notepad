package com.example.notepad.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notepad.presentation.edit.EditScreen
import com.example.notepad.presentation.home.HomeScreen
import com.example.notepad.presentation.settings.SettingsScreen
import com.example.notepad.presentation.splash.SplashScreen
import com.example.notepad.presentation.util.Screen

@Composable
fun NotepadNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                goToHomeScreen = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.SplashScreen.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                onClickAddNote = {
                    navController.navigate(Screen.EditScreen.route)
                },
                onNoteClick = { id ->
                    navController.navigate(
                        Screen.EditScreen.route +
                                "?noteId=$id"
                    )
                },
                goToSettingsScreen = {
                    navController.navigate(Screen.SettingsScreen.route)
                }
            )
        }

        composable(
            route = Screen.EditScreen.route +
                    "?noteId={noteId}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            EditScreen(
                onClickSaveNote = {
                    navController.navigateUp()
                },
                onClickBackButton = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = Screen.SettingsScreen.route
        ) {
            SettingsScreen(
                onGoBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
