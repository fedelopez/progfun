package week2

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import week1.Sums

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class Lecture22CurryingTest extends FunSuite {

  import Sums.fact
  import Lecture22Currying.sum
  import Lecture22Currying.product
  import Lecture22Currying.factorial
  import Lecture22Currying.mapReduce
  import Lecture22Currying.applyBetween

  test("currying sum") {
    def sumCurrying = sum(x => x)
    assert(sumCurrying(2, 5) === 14)
  }

  test("currying cubes") {
    def cubeCurrying = sum(x => x * x * x)
    assert(cubeCurrying(2, 5) === 224)
  }

  test("currying factorial") {
    def factCurrying = sum(fact)
    assert(factCurrying(2, 3) === 8)
  }

  test("currying factorial no middleman") {
    assert(sum(fact)(2, 3) === 8)
  }

  test("currying sum with multiple parameter lists") {
    def sumMPL(f: Int => Int)(a: Int, b: Int): Int =
      if (a > b) 0 else f(a) + sumMPL(f)(a + 1, b)

    assert(sumMPL(fact)(2, 3) === 8)
  }

  test("currying product") {
    def prodCurrying = product(x => x)
    assert(prodCurrying(2, 5) === 120)
  }

  test("currying product with factorial") {
    assert(product(fact)(2, 3) === 12)
  }

  test("factorial") {
    assert(factorial(5) === 120)
  }

  test("generalization sum with factorial") {
    def sumThem(x: Int, y: Int) = x + y
    assert(applyBetween(0)(sumThem)(fact)(2, 3) === 8)
    assert(applyBetween(0)(sumThem)(fact)(2, 3) === mapReduce(fact, sumThem, 0)(2, 3))
  }

  test("generalization: product with factorial") {
    def multiply(x: Int, y: Int) = x * y
    assert(applyBetween(1)(multiply)(fact)(2, 3) === 12)
    assert(applyBetween(0)(multiply)(fact)(2, 3) === mapReduce(fact, multiply, 0)(2, 3))
  }

}
