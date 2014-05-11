package week2

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class Lecture24FixedPointsTest extends FunSuite {

  import Lecture24FixedPoints.sqrt
  import Lecture24FixedPoints.sqrtWithAverageDamp

  test("fixed points") {
    println(sqrt(81))
    println(sqrtWithAverageDamp(81))
    assert(sqrt(81) === sqrtWithAverageDamp(81))
  }

}
