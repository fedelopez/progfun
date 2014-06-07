package week6

/**
 * @author fede
 */
object CombinatorialSearch {

  case class Person(name: String, age: Int)

  def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double = {
    val res = for {
      (x, y) <- xs zip ys
    } yield x * y
    res.sum
  }

}
