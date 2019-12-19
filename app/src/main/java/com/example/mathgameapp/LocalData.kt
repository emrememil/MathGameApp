package com.example.mathgameapp

class LocalData {

    companion object{

        val listFullCards = ArrayList<CardItem>()

        init {

            listFullCards.add(CardItem(R.drawable.multiply, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.division,R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.plus, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.percentage, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.buyuktur, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.kucuktur, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.equal, R.drawable.question, false))




            listFullCards.add(CardItem(R.drawable.multiply, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.division, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.plus, R.drawable.question,false))
            listFullCards.add(CardItem(R.drawable.percentage, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.buyuktur, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.kucuktur, R.drawable.question, false))
            listFullCards.add(CardItem(R.drawable.equal, R.drawable.question, false))


            listFullCards.shuffle()
        }

        fun fillArray(): ArrayList<CardItem>{
            return listFullCards
        }
    }
}