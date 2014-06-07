package week6

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class OtherCollectionsTest extends FunSuite {

  import OtherCollections.isPrime

  trait TestSets {
    val xs = Array(1, 2, 3, 44)

    val list = List(1, 2, 3, 4)

    val s = "Hello World"
  }

  test("map") {
    val xs = Array(1, 2, 3, 44)
    val mapped: Array[Int] = xs map (x => x * 2)
    mapped foreach println
  }

  test("exists, for all") {
    new TestSets {
      println(s"$s: ")
      val hasUpper: Boolean = s exists (c => c.isUpper)
      println(s"Has upper: $hasUpper")
      val allUpper: Boolean = s forall (c => c.isUpper)
      println(s"All upper: $allUpper")
    }
  }

  test("filter") {
    new TestSets {
      val sFiltered: String = s filter (c => c.isUpper)
      println(s"filter uppercase: $sFiltered")
    }
  }

  test("zip, unzip") {
    new TestSets {
      println("\nZip: ")
      val pairs: List[(Int, Char)] = list zip s
      println(pairs)
      println("\nUnzip: ")
      println(pairs unzip)
    }
  }

  test("flat map") {
    new TestSets {
      val flatMap: String = s flatMap (c => List(c, '.'))
      println("\nFlat map: ")
      println(flatMap)
    }
  }

  /**
   * list all combinations of numbers x and y where x is drawn from 1..M and y is drawn 1..N
   */
  test("combinations") {
    new TestSets {
      val M = 5
      val N = 3

      val seq = (1 to M) flatMap (x => (1 to N) map (y => (x, y)))

      println(seq)
    }
  }

  /**
   * compute the scalar product of 2 vectors
   */
  test("scalar product") {
    def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double = {
      val zip: Vector[(Double, Double)] = xs zip ys
      println("Zip: ")
      println(zip)
      val map: Vector[Double] = zip.map(xy => xy._1 * xy._2)
      println("Map: ")
      println(map)
      println("Sum: ")
      map.sum
    }

    println(scalarProduct(Vector(1, 2, 3), Vector(2, 4, 6)))
  }

  test("is prime") {

    assert(isPrime(5))
    assert(isPrime(7))
    assert(!isPrime(9))
    assert(!isPrime(12))

    val primes = List(3449, 3457, 3461, 3463, 3467, 3469, 3491, 3499, 3511, 3517, 3527, 3529, 3533, 3539, 3541, 3547, 3557, 3559, 3571)
    val allPrimes: Boolean = primes.forall(x => isPrime(x))
    assert(allPrimes)
  }
}
