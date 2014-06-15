package week7

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class PouringTest extends FunSuite {

  test("moves") {
    val problem = new Pouring(Vector(4, 7))
    problem.moves foreach println
  }

  test("path sets") {
    val problem = new Pouring(Vector(4, 7))
    problem.pathSets.take(3).toList foreach println
  }

  test("solution: 6") {
    val problem = new Pouring(Vector(4, 7))
    problem.solution(6) foreach println
  }


}
