package week2

/**
 * @author fede
 */
class Lecture27Operators(x: Int, y: Int) {

  val numer = x

  val denom = y

  def unary_- = new Lecture27Operators(-numer, denom)

  def +(that: Lecture27Operators) =
    new Lecture27Operators((numer * that.denom) + (denom * that.numer), denom * that.denom)

  def -(that: Lecture27Operators) = this + -that

  override def toString: String = x + "/" + y

}
