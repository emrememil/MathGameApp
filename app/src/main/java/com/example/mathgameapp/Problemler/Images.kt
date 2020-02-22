package com.example.mathgameapp.Problemler

import android.media.Image
import com.example.mathgameapp.R

class Ornek(imageYol:Int, imageDeger: Int ){
    var yol = imageYol
    var deger = imageDeger
}

class Images {

    var imagesA = ArrayList<Ornek>()
    init {
        imagesA.add(Ornek(R.drawable.cat,10))
        imagesA.add(Ornek(R.drawable.whale,15))
        imagesA.add(Ornek(R.drawable.camel,5))
        imagesA.add(Ornek(R.drawable.dog,20))
        //imagesA.add(Ornek(R.drawable.bird,20))
        //imagesA.add(Ornek(R.drawable.diplodocus,20))

        imagesA.shuffle()
    }


}