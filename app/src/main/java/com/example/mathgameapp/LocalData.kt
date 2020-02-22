package com.example.mathgameapp

class LocalData {

    companion object{

        val listFullCards = ArrayList<CardItem>()

        init {

            listFullCards.add(CardItem(R.drawable.like, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.search,R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.star, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.play, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.smartwatch, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.hold, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.diamond, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.music, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.camera, R.drawable.question, false))




            listFullCards.add(CardItem(R.drawable.like, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.search, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.star, R.drawable.question,false))
            listFullCards.add(CardItem(R.drawable.play, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.smartwatch, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.hold, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.diamond, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.music, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.camera, R.drawable.question, false))


            listFullCards.shuffle()
        }

        fun fillArray(): ArrayList<CardItem>{
            return listFullCards
        }
    }
}