package week6

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class PolynomialsTest extends FunSuite {

  import Polynomials.Poly

  trait TestSets {
    val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
    val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))

    val p3 = new Poly(0 -> 3.0, 3 -> 7.0)
  }

  test("to string") {
    new TestSets {
      println(p1.toString)
      println(p2.toString)
    }
  }

  test("sum") {
    new TestSets {
      println(p1 + p2)
    }
  }
}
