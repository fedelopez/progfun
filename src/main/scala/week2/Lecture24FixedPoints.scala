package week2

/**
 * @author fede
 */
object Lecture24FixedPoints {

  val tolerance = 0.0001

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {

    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }

    iterate(firstGuess)
  }

  def sqrt(x: Double): Double =
    fixedPoint(y => (y + x / y) / 2)(1.0)

  def sqrtWithAverageDamp(x: Double): Double =
    fixedPoint(averageDamp(y => x / y))(1.0)

  def isCloseEnough(x: Double, y: Double) =
    abs((x - y) / x) / x < tolerance

  def abs(x: Double): Double = if (x < 0) -x else x

  /**
   * Technique of stabilizing by averaging
   */
  def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

}
