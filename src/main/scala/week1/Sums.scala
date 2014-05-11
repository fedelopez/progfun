package week1

/**
 * @author fede
 */
object Sums {

  def sumInts(a: Int, b: Int): Int =
    if (a > b) 0
    else a + sumInts(a + 1, b)

  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)

  def tailRecursiveSum(f: Int => Int, a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, f(a) + acc)
    }
    loop(a, 0)
  }

  def id(a: Int): Int = a

  def cube(a: Int): Int = a * a * a

  def fact(a: Int): Int = if (a == 0) 1 else a * fact(a - 1)

  def tailRecursiveFact(a: Int): Int = {

    def loop(a: Int, acc: Int): Int = {
      if (a == 0) acc * 1
      else loop(a - 1, a * acc)
    }

    loop(a, 1)
  }

}
