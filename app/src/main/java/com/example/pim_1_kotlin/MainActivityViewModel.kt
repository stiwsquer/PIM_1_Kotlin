package com.example.pim_1_kotlin

import android.content.Context
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    val liveStartups = MutableLiveData<List<Startup>>()

    val context = MainActivity.appContext;

    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

    fun generateWord(): String {
            val randomIndex1: Int = rand(0, 2998);
            val randomIndex2: Int = rand(0, 2998);
            val word1: String =
                context.assets.open("words.txt").bufferedReader().useLines { it.elementAt(randomIndex1) }
            val word2: String =
                context.assets.open("words.txt").bufferedReader().useLines { it.elementAt(randomIndex2) }
           return "${word1.capitalize()}${word2.capitalize()}"

    }



    fun getStartups(){
        val startups = liveStartups.value?.toMutableList() ?: mutableListOf<Startup>()
        for (i in 20 downTo 0 step 1) {
         startups.addAll(
                listOf(
                    Startup(i , generateWord(), false)
                )
            )
        }

        liveStartups.value = startups;
    }
}


































//        when (nextPage){
//            0 -> startups.addAll(
//                listOf(
//                    Startup(1,  "DOG"),
//                    Startup(2,  "Cat"),
//                    Startup(3,  "Mouse"),
//                    Startup(4,  "Hamster"),
//                    Startup(5,  "Rabbit"),
//                    Startup(6,  "Fox"),
//                    Startup(7,  "Bear"),
//                    Startup(8,  "Panda"),
//                    Startup(9,  "Polar Bear"),
//                    Startup(10,  "Koala"),
//                    Startup(11,  "Tiger"),
//                    Startup(12,  "Lion"),
//                    Startup(13,  "Cow"),
//                    Startup(14, "Pig"),
//                    Startup(15, "Frog"),
//                    Startup(1,  "2Dog"),
//                    Startup(2,  "Cat"),
//                    Startup(3,  "Mouse"),
//                    Startup(4,  "Hamster"),
//                    Startup(5,  "Rabbit"),
//                    Startup(6,  "Fox"),
//                    Startup(7,  "Bear"),
//                    Startup(8,  "Panda"),
//                    Startup(9,  "Polar Bear"),
//                    Startup(10,  "Koala"),
//                    Startup(11,  "Tiger"),
//                    Startup(12,  "Lion"),
//                    Startup(13,  "Cow"),
//                    Startup(14, "Pig"),
//                    Startup(15, "Frog"),
//                    Startup(1,  "3Dog"),
//                    Startup(2,  "Cat"),
//                    Startup(3,  "Mouse"),
//                    Startup(4,  "Hamster"),
//                    Startup(5,  "Rabbit"),
//                    Startup(6,  "Fox"),
//                    Startup(7,  "Bear"),
//                    Startup(8,  "Panda"),
//                    Startup(9,  "Polar Bear"),
//                    Startup(10,  "Koala"),
//                    Startup(11,  "Tiger"),
//                    Startup(12,  "Lion"),
//                    Startup(13,  "Cow"),
//                    Startup(14, "Pig"),
//                    Startup(15, "Frog")
//                )
//            )
//            1 -> startups.addAll(
//                listOf(
//                    Startup(1,  "4Dog"),
//                    Startup(2,  "Cat"),
//                    Startup(3,  "Mouse"),
//                    Startup(4,  "Hamster"),
//                    Startup(5,  "Rabbit"),
//                    Startup(6,  "Fox"),
//                    Startup(7,  "Bear"),
//                    Startup(8,  "Panda"),
//                    Startup(9,  "Polar Bear"),
//                    Startup(10,  "Koala"),
//                    Startup(11,  "Tiger"),
//                    Startup(12,  "Lion"),
//                    Startup(13,  "Cow"),
//                    Startup(14, "Pig"),
//                    Startup(15, "Frog")
//                )
//            )
//
//            2 -> startups.addAll(
//                listOf(
//                    Startup(1,  "5Dog"),
//                    Startup(2,  "Cat"),
//                    Startup(3,  "Mouse"),
//                    Startup(4,  "Hamster"),
//                    Startup(5,  "Rabbit"),
//                    Startup(6,  "Fox"),
//                    Startup(7,  "Bear"),
//                    Startup(8,  "Panda"),
//                    Startup(9,  "Polar Bear"),
//                    Startup(10,  "Koala"),
//                    Startup(11,  "Tiger"),
//                    Startup(12,  "Lion"),
//                    Startup(13,  "Cow"),
//                    Startup(14, "Pig"),
//                    Startup(15, "Frog")
//                )
//            )
//
//            3 -> startups.addAll(
//                listOf(
//                    Startup(1,  "6Dog"),
//                    Startup(2,  "Cat"),
//                    Startup(3,  "Mouse"),
//                    Startup(4,  "Hamster"),
//                    Startup(5,  "Rabbit"),
//                    Startup(6,  "Fox"),
//                    Startup(7,  "Bear"),
//                    Startup(8,  "Panda"),
//                    Startup(9,  "Polar Bear"),
//                    Startup(10,  "Koala"),
//                    Startup(11,  "Tiger"),
//                    Startup(12,  "Lion"),
//                    Startup(13,  "Cow"),
//                    Startup(14, "Pig"),
//                    Startup(15, "Frog")
//                )
//            )
//
//            4 -> startups.addAll(
//                listOf(
//                    Startup(1,  "7Dog"),
//                    Startup(2,  "Cat"),
//                    Startup(3,  "Mouse"),
//                    Startup(4,  "Hamster"),
//                    Startup(5,  "Rabbit"),
//                    Startup(6,  "Fox"),
//                    Startup(7,  "Bear"),
//                    Startup(8,  "Panda"),
//                    Startup(9,  "Polar Bear"),
//                    Startup(10,  "Koala"),
//                    Startup(11,  "Tiger"),
//                    Startup(12,  "Lion"),
//                    Startup(13,  "Cow"),
//                    Startup(14, "Pig"),
//                    Startup(15, "Frog")
//                )
//            )
//        }