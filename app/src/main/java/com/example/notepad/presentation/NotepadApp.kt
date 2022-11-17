package com.example.notepad.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notepad.navigation.EditScreen
import com.example.notepad.navigation.HomeScreen
import com.example.notepad.navigation.NotepadNavHost
import com.example.notepad.presentation.edit.EditScreen
import com.example.notepad.presentation.home.HomeScreen

@Composable
fun NotepadApp() {
    val navController = rememberNavController()
    NotepadNavHost(navController = navController)
}
