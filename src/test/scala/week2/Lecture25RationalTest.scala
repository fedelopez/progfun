package week2

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class Lecture25RationalTest extends FunSuite {

  test("rationals add") {
    val rational1 = new Lecture25Rational(3, 2)
    val rational2 = new Lecture25Rational(4, 2)

    val res = rational1.add(rational2)
    assert(res.numer === 14)
    assert(res.denom === 4)

  }

  test("rationals substract") {
    val rational1 = new Lecture25Rational(3, 2)
    val rational2 = new Lecture25Rational(4, 3)

    val res = rational1.substract(rational2)
    assert(res.numer === 1)
    assert(res.denom === 6)

  }

  test("rationals substract from the slides") {
    val rational1 = new Lecture25Rational(1, 3)
    val rational2 = new Lecture25Rational(5, 7)
    val rational3 = new Lecture25Rational(3, 2)

    val res = rational1.substract(rational2).substract(rational3)
    assert(res.numer === -79)
    assert(res.denom === 42)

  }

  test("rationals neg") {
    val rational = new Lecture25Rational(3, 2).neg
    assert(rational.numer === -3)
    assert(rational.denom === 2)
  }

}