package week7

/**
 * @author fede
 */
object Streams {

  /**
   * function that returns (lo until hi).toStream directly
   */
  def streamRange(lo: Int, hi: Int): Stream[Int] =
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange(lo + 1, hi))

  /**
   * same function as streamRange but produces a list
   *
   * The functions have almost identical structure yet they evaluate quite diﬀerently
   *
   * ▶ listRange(start, end) will produce a list with end - start elements and return it.
   * ▶ streamRange(start, end) returns a single object of type Stream with start as head element.
   * ▶ The other elements are only computed when they are needed, where “needed” means that someone calls tail on the stream.
   */
  def listRange(lo: Int, hi: Int): List[Int] =
    if (lo >= hi) Nil
    else lo :: listRange(lo + 1, hi)

  /**
   * Infinite streams
   */
  def from(n: Int): Stream[Int] = n #:: from(n + 1)

  /**
   * The Sieve of Eratosthenes is an ancient technique to calculate prime numbers.
   * The idea is as follows:
   *
   * ▶ Start with all integers from 2, the first prime number.
   * Eliminate all multiples of 2.
   * ▶ The first element of the resulting list is 3, a prime number.
   * Eliminate all multiples of 3.
   * Iterate forever. At each step, the first number in the list is a prime number and we eliminate all its multiples.
   */
  def sieveOfEratosthenes(s: Stream[Int]): Stream[Int] =
    s.head #:: sieveOfEratosthenes(s.tail filter (_ % s.head != 0))

  /**
   * Our previous algorithm for square roots always used a isGoodEnough test to tell when to terminate the iteration.
   * With streams we can now express the concept of a converging sequence without having to worry about when to terminate it:
   */
  def sqrtStream(x: Double): Stream[Double] = {
    def improve(guess: Double) = (guess + x / guess) / 2
    lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
    guesses
  }
}
