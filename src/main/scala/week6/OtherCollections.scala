package week6

/**
 * @author fede
 */
object OtherCollections {

  def isPrime(n: Int): Boolean = {
    val range: Range = 2 until n
    range.forall(x => n % x != 0)
  }

}
