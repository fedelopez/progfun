package week6

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import week6.CombinatorialSearch.Person

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class CombinatorialSearchTest extends FunSuite {

  import OtherCollections.isPrime
  import CombinatorialSearch.scalarProduct

  trait TestSets {
    val xs = Array(1, 2, 3, 44)

    val list = List(1, 2, 3, 4)

    val s = "Hello World"
  }

  test("pairs") {
    val n = 7
    val seq = (1 until n) map (i => (1 until i) map (j => (i, j)))
    println(seq)
    println(seq.flatten)
  }

  test("pairs simplified") {
    val n = 7
    val seq = (1 until n) flatMap (i => (1 until i) map (j => (i, j)))
    println(seq)
  }

  test("sum of pair is prime from list of pairs") {
    val n = 7
    val seq = (1 until n) flatMap (i => (1 until i) map (j => (i, j)))
    val filtered = seq.filter((pair) => isPrime(pair._1 + pair._2))
    println(filtered)
  }

  test("for-expression with no side effects") {
    val p1 = new Person("John", 20)
    val p2 = new Person("Mary", 25)
    val p3 = new Person("Fred", 6)
    val p4 = new Person("Graham", 45)

    val persons = List(p1, p2, p3, p4)

    val res1: List[String] = for (p <- persons if p.age > 20) yield p.name
    val res2: List[String] = persons filter (p => p.age > 20) map (p => p.name)

    println(res1)
    assert(res1.equals(res2))
  }

  test("sum of pair is prime from list of pairs with for-expression") {
    val n = 7
    val result = for {
      i <- 1 until n
      j <- 1 until i
      if isPrime(i + j)
    } yield (i, j)

    println(result)
  }

  test("scalar product of 2 vectors using for-expression") {
    assert(scalarProduct(Vector(1, 2, 3), Vector(2, 4, 6)) == 28)
  }
}
