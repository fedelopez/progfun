package week1

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class CountingChangeTest extends FunSuite {

  import CountingChange.countChange

  test("countChange: example given in instructions") {
    assert(countChange(4, List(1, 2)) === 3)
  }

  test("countChange: no coins for amount > 0") {
    assert(countChange(999, List()) === 0)
  }

  test("countChange: coins with no value are ignored") {
    assert(countChange(4, List(-1, 0, 1, 2)) === 3)
  }

  test("countChange: do nothing when coins with no value") {
    assert(countChange(4, List(-1, 0, -2)) === 0)
  }

  test("countChange: sorted CHF") {
    assert(countChange(300, List(5, 10, 20, 50, 100, 200, 500)) === 1022)
  }

  test("countChange: no possible change") {
    assert(countChange(301, List(5, 10, 20, 50, 100, 200, 500)) === 0)
  }

  test("countChange: unsorted CHF") {
    assert(countChange(300, List(500, 5, 50, 100, 20, 200, 10)) === 1022)
  }

  test("countChange: amount 2 with one coin of 1") {
    assert(countChange(2, List(1)) === 1)
  }

  test("countChange: amount 2 with one coin of 2") {
    assert(countChange(2, List(2)) === 1)
  }

  test("countChange: amount 8 with one coin of 2") {
    assert(countChange(8, List(2)) === 1)
  }

  test("countChange: amount 2 with 1, 2") {
    assert(countChange(2, List(1, 2)) === 2)
  }

}
