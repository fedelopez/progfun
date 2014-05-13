package week2

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class Lecture27OperatorsTest extends FunSuite {

  test("rationals add") {
    val rational1 = new Lecture27Operators(3, 2)
    val rational2 = new Lecture27Operators(4, 2)

    val res = rational1 + rational2
    assert(res.numer === 14)
    assert(res.denom === 4)
  }

  test("rationals substract") {
    val rational1 = new Lecture27Operators(3, 2)
    val rational2 = new Lecture27Operators(4, 3)

    val res = rational1 - rational2
    assert(res.numer === 1)
    assert(res.denom === 6)

  }

  test("rationals neg") {
    val rational1 = new Lecture27Operators(3, 2)

    val res = -rational1
    assert(res.numer === -3)
    assert(res.denom === 2)

  }

}
