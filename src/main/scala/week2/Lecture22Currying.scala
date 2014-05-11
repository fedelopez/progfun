package week2

/**
 * @author fede
 */
object Lecture22Currying {

  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    sumF
  }

  /**
   * Write a product function that calculates the product of the values of a function for the points of a given interval
   */
  def product(f: Int => Int): (Int, Int) => Int = {
    def prodF(a: Int, b: Int): Int = {
      if (a > b) 1
      else f(a) * prodF(a + 1, b)
    }
    prodF
  }

  def factorial(n: Int): Int = product(x => x)(1, n)

  /**
   * Write a function that generalizes both sum and product
   * My solution
   */
  def applyBetween(last: Int)(s: (Int, Int) => Int)(f: Int => Int): (Int, Int) => Int = {
    def prodF(a: Int, b: Int): Int = {
      if (a > b) last
      else s(f(a), prodF(a + 1, b))
    }
    prodF
  }

  /**
   * Write a function that generalizes both sum and product
   * Martin's solution with mapReduce
   */
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))

}
