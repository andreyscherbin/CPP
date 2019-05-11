package com.bsuir.vmsis

object Goldbach {

  def goldbach(number: Int): List[Int] = number match {
    case 2 => Nil
    case number if isOdd(number) => Nil
    case number => {
   
       /*      
        1. Найдем все простые числа между 2 и number       
        2. Начнем с максимального простого числа, отнимем его от number, если полученнное число является простым значит останавливаемся на нем и возвращаем         
        3. Если нет смотрим следущее максимальное число из списка       
       */

      val resultList = numbers(number, primeNumbers((2 to number).reverse))
      resultList.foreach(println)
      resultList
    }
  }
  
  private def numbers(number: Int, primeNumbers: List[Int]): List[Int] = primeNumbers match {
    case maxPrime :: listPrime if primeNumbers contains (number - maxPrime) => List(number - maxPrime, maxPrime)     // вызов метода apply обьекта - компаньона класса List ( List это обьект который может рассматриваться как функция если у него есть метод apply)
    case maxPrime :: listPrime => numbers(number, listPrime)
    case _ => throw new IllegalArgumentException(s"It should not have happened!!")
  }

  private def isOdd(number: Int): Boolean = {            // вернет True если n - нечетное
    number % 2 != 0
  }
  
  def primeNumbers(range: Range): List[Int] = range.filter(number => isPrime(number)).toList

  def isPrime(number: Int): Boolean =              // Функция проверки на простоту числа n
    if (number < 2)
      false
    else
      !((2 to Math.sqrt(number).toInt).exists (number % _ == 0))   //Перебор делителей от 2 до sqrt(number)// (По определению число n является простым лишь в том сл
                                                                   //учае, если оно не делится без остатка на 2 и другие целые числа, кроме 1 и самого себя.)
}                                                                  //