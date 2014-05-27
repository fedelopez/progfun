package week4

import week3.{Empty, NonEmpty}

/**
 * In Scala arrays are not covariant. The example below does not compile whereas in Java this would compile fine
 * and throw a runtime exception.
 *
 * @author fede
 */
object ProblematicArray {

  def main(args: Array[String]) {
    val a: Array[NonEmpty] = Array(new NonEmpty(1, Empty, Empty))
    //    val b: Array[IntSet] = a todo: uncomment reveals compile error in Scala, as it is not covariant
    //    b(0) = Empty
    //    val s: NonEmpty = a(0)
  }

}
