package com.example.mathgameapp

import android.util.Log

object LogicMemory {

    var option1: Int = 100
    var option2: Int = 100
    var indexArray1: Int = -1
    var indexArray2: Int = -2
    var countCard: Int = 0

    fun isCardsEquals(cardOption: Int, indexArray: Int): Boolean{

        countCard++
        if (option1 == 100){
            option1 = cardOption
            indexArray1 = indexArray
            return false
        }else{
            option2 = cardOption
            indexArray2 = indexArray
        }

        Log.d("TAG", "Comparando OPTION1 = $option1 OPTION2 = $option2 COUNT = $countCard  Index1: $indexArray1  Index2: $indexArray2")

        if (option1 == option2 && indexArray1 != indexArray2){
            if (countCard == 2) cleanStates()
            return true
        }else{
            if (countCard == 2) cleanStates()
            return false
        }
    }

    fun cleanStates(){
        Log.d("TAG","Limpiando Preferencias...")
        option1 = 100
        option2 = 100
        countCard = 0
        indexArray1 = -1
        indexArray2 = -2

    }

    fun clickOnCard(cardOption: Int, indexArray: Int): Boolean {
        return isCardsEquals(cardOption, indexArray)
    }
}