package com.example.piggybank

import kotlin.random.Random
import kotlin.random.nextInt

object PiggyBank {

    var listOfCoinsAndBills = ArrayList<Money>()// список монеток/купюр

    var isBroken: Boolean = false // свойство, определяющее, разбита ли копилка

    fun putMoney(money: Money) {
        if (!isBroken) {
            listOfCoinsAndBills.add(money)
            println("Добавлено в копилку $money")
        } else {
            println("Вы разбили копилку, вы больше ничего положить туда не можете")
            return
        } // проверьте, не разбита ли копилка и добавьте монетку
    }

    fun shake(): Money? {
        var money: Money? = null
        val coinsList = listOfCoinsAndBills.filter { it.isCoin }
        if (!isBroken) {
            //println("Трясу, трясу, монетку хочу")
            if (coinsList.isNotEmpty()) {
                money = coinsList[Random.nextInt(coinsList.indices)]
            } else {
                return null
            }
        } else {
            println("Вы разбили копилку, больше оттуда ничего не вытрясти")
        }
        return money  //return null
    }

    fun smash(): ArrayList<Money> {
        isBroken = true
        return listOfCoinsAndBills
        // установить флаг, что копилка разбита true, и вернуть все монетки, которые были в копилке
    }
}

class Money(private val amount: Float, val isCoin: Boolean) {

    // создайте класс Money, который будет отражать сущность монетки/купюры с двумя полями: amount и isCoin
    companion object {
        val coins_10: Money = Money(0.1f, true)
        val coins_50: Money = Money(0.5f, true)
        val coins_100: Money = Money(1f, true)
        val bill_50: Money = Money(50f, false)
        val bill_100: Money = Money(100f, false)
        val bill_500: Money = Money(500f, false)
        val bill_1000: Money = Money(1000f, false)
    }

    override fun toString(): String {
        return if (amount == 0.1f) {
            "10 коп."
        } else if (amount == 0.5f) {
            "50 коп."
        } else {
            "${amount.toInt()} руб."
        }
    }
}
// переопределите метод toString() так, чтобы он возвращал строку типа "10 коп." или "1 руб.", если это монетка, и "100 руб.", если это купюра
// вы должны ограничить создание класса таким образом, чтобы можно было создать только ограниченный набор номиналов (см. задание)