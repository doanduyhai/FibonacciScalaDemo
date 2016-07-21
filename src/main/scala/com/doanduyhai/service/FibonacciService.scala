package com.doanduyhai.service

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object FibonacciService {

  /**
    * Compute Fibonacci suite.
    * <br/>
    * We special case where rank = 1 to kick off recursion.
    * <br/>
    * In order to be able to use tail-recursion and not facing StackOverflow when rank is getting large, we
    * need to accumulate the computed values during each recursion into a mutable map
    *
    * @param rank rank for Fibonacci suite to compute. Rank starts at 1 and not 0
    * @return The list of Fibonacci number, in reversed order
    */
  def computeFibonacci(rank: Int): List[BigInt] = {

    val fibonacciList: ListBuffer[BigInt] = ListBuffer.empty[BigInt]

    @tailrec
    def fibonacciTailRec(last: Long, accumulator: Long, rank: Int): Unit = {
      rank match {
        case x if x <= 0 => throw new IllegalArgumentException(s"The argument ($rank) for Fibonacci suite should be strictly positive")
        case 1 => {
          fibonacciList.append(last)
          ()
        }
        case _ => {
          fibonacciList.append(last)
          fibonacciTailRec(accumulator, last + accumulator, rank - 1)
        }
      }
    }
    fibonacciTailRec(0, 1, rank)
    fibonacciList.toList
  }
}
