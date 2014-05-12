package week2

/**
 * @author fede
 */
class Lecture25Rational(x: Int, y: Int) {

  def numer = x

  def denom = y

  def neg = new Lecture25Rational(numer * -1, denom)

  def add(rational: Lecture25Rational) =
    new Lecture25Rational((numer * rational.denom) + (denom * rational.numer), denom * rational.denom)

  def substract(rational: Lecture25Rational) = add(rational.neg)

  override def toString: String = x + "/" + y

}
