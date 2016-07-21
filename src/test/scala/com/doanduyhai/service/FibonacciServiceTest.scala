package com.doanduyhai.service

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class FibonacciServiceTest extends FlatSpec with Matchers{

  "FibonacciService" should "raise exception for negative input" in {
    //Given
    val rank = -1

    //When
    val ex = intercept[IllegalArgumentException] {
      FibonacciService.computeFibonacci(rank)
    }

    //Then
    ex.getMessage should be(s"The argument (-1) for Fibonacci suite should be strictly positive")
  }

  "FibonacciService" should "raise exception for zero input" in {
    //Given
    val rank = 0

    //When
    val ex = intercept[IllegalArgumentException] {
      FibonacciService.computeFibonacci(rank)
    }

    //Then
    ex.getMessage should be(s"The argument (0) for Fibonacci suite should be strictly positive")
  }

  "FibonacciService" should "return correct result for 1" in {
    //Given
    val rank = 1

    //When
    val result: List[Long] = FibonacciService.computeFibonacci(rank)

    //Then
    result should be (List(0))
  }


  "FibonacciService" should "return correct result for 2" in {
    //Given
    val rank = 2

    //When
    val result: List[Long] = FibonacciService.computeFibonacci(rank)

    //Then
    result should be (List(0, 1))
  }

  "FibonacciService" should "return correct result for 10" in {
    //Given
    val rank = 10

    //When
    val result: List[Long] = FibonacciService.computeFibonacci(rank)

    //Then
    result should be (List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34))
  }

  "FibonacciService" should "be able to compute a huge suite" in {
    //Given
    val rank = 1000000

    //When
    val result: List[Long] = FibonacciService.computeFibonacci(rank)

    //Then
    result.length should be (1000000)
  }
}
