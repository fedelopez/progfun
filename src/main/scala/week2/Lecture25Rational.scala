package week2

/**
 * @author fede
 */
class Lecture25Rational(x: Int, y: Int) {
  require(y > 0, "denominator must not be greater than zero")

  def this(x: Int) = this(x, 1)

  private val g = math.abs(gcd(x, y))

  val numer = x / g

  val denom = y / g

  def neg = new Lecture25Rational(-numer, denom)

  def add(that: Lecture25Rational) =
    new Lecture25Rational((numer * that.denom) + (denom * that.numer), denom * that.denom)

  def substract(that: Lecture25Rational) = add(that.neg)

  def less(that: Lecture25Rational): Boolean = numer * that.denom < that.numer * denom

  def max(that: Lecture25Rational) = if (less(that)) that else this

  override def toString: String = x + "/" + y

  /**
   * Greatest common divisor
   */
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

}
