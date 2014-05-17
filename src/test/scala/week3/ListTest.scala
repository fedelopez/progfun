package week3

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ListTest extends FunSuite {

  test("nth") {
    val list1 = new Cons(56, new Cons(89, new Cons(9, new Nil)))
    assert(nth(0, list1) === 56)
    assert(nth(1, list1) === 89)
    assert(nth(2, list1) === 9)
  }

  def nth[T](n: Int, list: List[T]): T = {
    def iterate(n: Int, currentIndex: Int, list: List[T]): T = {
      if (n == currentIndex) list.head
      else if (currentIndex > n) throw new IndexOutOfBoundsException
      else iterate(n, currentIndex + 1, list.tail)
    }
    iterate(n, 0, list)
  }
}
