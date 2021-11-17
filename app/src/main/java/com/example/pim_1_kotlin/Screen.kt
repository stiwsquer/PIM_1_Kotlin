package com.example.pim_1_kotlin

sealed class Screen(val route: String){
    object MainScreen: Screen("main_screen")
    object SavedScreen: Screen("saved_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {arg  ->
                append("/$arg")
            }
        }
    }
}