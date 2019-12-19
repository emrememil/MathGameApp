package com.example.mathgameapp

interface MainView {

    fun requestData(listCards: ArrayList<CardItem>)
    fun cardClickOk(idImgCard: Int)
    fun cardClickFail()
    fun sendActionMain(cardOption: Int, indexArray: Int)


}