package week1

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class SumsTest extends FunSuite {

  import Sums.sumInts
  import Sums.sum
  import Sums.id
  import Sums.fact
  import Sums.tailRecursiveSum
  import Sums.tailRecursiveFact

  test("sumInts") {
    assert(sumInts(4, 9) === 39)
  }

  test("tailRecursiveSum") {
    assert(tailRecursiveSum(x => x * x, 3, 5) === 50)
  }

  test("sum") {
    assert(sumInts(4, 9) === 39)

    assert(tailRecursiveSum(x => x * x, 3, 5) === 50)

    assert(sum(id, 4, 9) === 39)

    def cube = (x: Int) => x * x * x
    assert(sum(cube, 4, 9) === 1989)

    assert(fact(4) === 24)
  }

  test("sum cube") {
    def cube = (x: Int) => x * x * x
    assert(sum(cube, 4, 9) === 1989)
  }

  test("factorial") {
    assert(fact(4) === 24)
  }

  test("sum factorial") {
    assert(sum(fact, 3, 4) === 30)
  }

  test("tailRecursive fact") {
    assert(tailRecursiveFact(5) === 120)
  }
}
