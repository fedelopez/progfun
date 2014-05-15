package week3

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IntSetsTest extends FunSuite {


  test("non empty with empty child") {

    val t1 = new NonEmpty(3, Empty, Empty)

    val t2 = t1 incl 4

    print(t2)

  }

  test("union on empty") {

    val t1 = new NonEmpty(3, Empty, Empty)
    val t2 = t1 incl 4

    val res1 = Empty union t1

    assert(res1 contains 3)
    assert(!(res1 contains 4))

    val res2 = Empty union t2

    assert(res2 contains 3)
    assert(res2 contains 4)
  }

  test("union on non empty") {

    val t1 = new NonEmpty(3, Empty, Empty)
    val t2 = t1 incl 4

    val s1 = new NonEmpty(7, Empty, Empty)
    val s2 = s1 incl 9

    val res1 = t2 union s2

    println(res1)

    assert(res1 contains 3)
    assert(res1 contains 4)
    assert(res1 contains 7)
    assert(res1 contains 9)

    val res2 = Empty union t2

    assert(res2 contains 3)
    assert(res2 contains 4)
  }

}
