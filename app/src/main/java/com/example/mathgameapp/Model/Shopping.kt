package com.example.mathgameapp.Model

import android.provider.ContactsContract

class Shopping {
    var id:Int=0
    var isim:String?=null
    var sinif:Int=0
    var coin:Int=0
    var sure_gel:Int=0
    var coin_gel:Int=0

    constructor(){}

    constructor(id:Int, name:String, sinif:Int, coin:Int, suregel:Int, coingel:Int){
        this.id=id
        this.isim=name
        this.sinif=sinif
        this.coin=coin
        this.sure_gel=suregel
        this.coin_gel=coingel

    }
}