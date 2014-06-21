package week7

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import week6.OtherCollections._

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class StreamsTest extends FunSuite {

  import Streams._

  test("lazy eval") {
    def lazyTest = {
      val x = {
        print("x")
        1
      }
      lazy val y = {
        print("y")
        2
      }
      def z = {
        print("z")
        3
      }

      z + y + x + z + y + x
    }

    lazyTest
  }

  test("lazy stream") {
    val i: Int = (streamRange(1000, 10000) filter isPrime) apply 1
    print(i)
  }

  test("stream of all natural numbers") {
    val nats = from(0)
    val apply: Int = nats.apply(10000)
    println(apply)
  }

  test("stream of multiples of 4") {
    val nats = from(0)
    val stream: Stream[Int] = nats map (_ * 4)
    val res: Int = stream.apply(16)
    println(res)

    val list: List[Int] = stream.take(100).toList
    println(list)
  }

  test("Sieve of Eratosthenes") {
    val primes = sieveOfEratosthenes(from(2))
    val take: Stream[Int] = primes.take(1000)
    println(take.toList)
  }

  test("sqrt") {
    val list: List[Double] = sqrtStream(4).take(10).toList
    println(list)

  }

  test("sqrt: add isGoodEnough later") {
    def isGoodEnough(guess: Double, x: Double) =
      math.abs((guess * guess - x) / x) < 0.0001

    val list: List[Double] = (sqrtStream(4) filter (isGoodEnough(_, 4))).take(10).toList
    println(list)
  }


}
