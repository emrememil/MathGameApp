package com.example.mathgameapp

import android.util.Log
import kotlin.random.Random


 class Question {



}

class Rastgele() {

    val operatorler : Array<String> = arrayOf("+", "-" , "*", "÷")
    var firstNumber=0
    var secondNumber=0
    var sign=""
    var answer=0
    var gelenLevel=0
    var cevap=0


    fun soruOlustur(lvl:Int){
        gelenLevel=lvl
        firstNumber= RandomSayiUret(lvl)
        secondNumber=RandomSayiUret(lvl)
        sign= signUret()
        answer=answerUret(firstNumber,sign,secondNumber)

    }

    private fun answerUret(gelenFirst: Int, gelenSign: String, gelenSecond: Int): Int {
         cevap=0
        when(gelenSign){

            "+" ->  cevap=gelenFirst+gelenSecond
            "-" ->  {
                if((gelenFirst-gelenSecond)<0){
                    soruOlustur(gelenLevel)
                }else{
                    cevap=gelenFirst-gelenSecond
                }
            }
            "*" -> {
                if((gelenFirst*gelenSecond) % 5 != 0){
                    soruOlustur(gelenLevel)
                }else{
                    cevap=gelenFirst*gelenSecond
                }
            }
            "÷" -> {
                if(gelenSecond!=0 && gelenFirst!=0){
                    if((gelenFirst%gelenSecond) != 0){
                        soruOlustur(gelenLevel)
                    }
                    else{
                        cevap=gelenFirst/gelenSecond
                    }
                }
                else{
                    soruOlustur(gelenLevel)
                }
            }
        }
        Log.e("EMRE","1.SAYI: "+gelenFirst.toString() + "'2.Sayı: "+ secondNumber.toString()+
                "İşaret: "+gelenSign + "Cevap: "+cevap.toString() )
        return cevap
    }

    private fun signUret(): String {
        val random = java.util.Random()
        var uretilen=operatorler[random.nextInt(operatorler.size)]
        return uretilen

    }

    private fun RandomSayiUret(gelenLevel: Int): Int {

        var uretilenSayi=0

        if (gelenLevel>=0 && gelenLevel<=2){
            uretilenSayi= (0..10).random()
        }

        else if (gelenLevel>=3 && gelenLevel<=5){
            uretilenSayi= (5..20).random()
        }

        else if (gelenLevel>=6 && gelenLevel<=8){
            uretilenSayi= (10..30).random()
        }

        else if (gelenLevel>=9 && gelenLevel<=11){
            uretilenSayi= (15..40).random()
        }

        return uretilenSayi
    }


}