package week2

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 * - run the "test" command in the SBT console
 * - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetsTest extends FunSuite {

  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    def s4: Set = x => x == 2 || x == 4 || x == 6

    def s5: Set = x => x == 2 || x == 5 || x == 6 || x == 8

    def sBig: Set = x => x % 14 == 0 || x % 500 == 0
  }

  test("singletonSet with 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s1, 2), "Singleton")
      assert(!contains(s1, 3), "Singleton")
    }
  }

  test("singletonSet with 2") {
    new TestSets {
      assert(contains(s2, 2), "Singleton")
      assert(!contains(s2, 1), "Singleton")
      assert(!contains(s2, 3), "Singleton")
    }
  }

  test("union contains s1 and s2") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("union contains s1 and s3") {
    new TestSets {
      val s = union(s1, s3)
      assert(contains(s, 1), "Union 1")
      assert(!contains(s, 2), "Union 2")
      assert(contains(s, 3), "Union 3")
    }
  }

  test("intersect contains 2 and 6") {
    new TestSets {
      val s = intersect(s4, s5)
      assert(contains(s, 2), "Intersect 2")
      assert(contains(s, 6), "Intersect 6")
      assert(!contains(s, 4), "Intersect 5")
      assert(!contains(s, 5), "Intersect 5")
      assert(!contains(s, 8), "Intersect 8")
    }
  }

  test("intersect on singleton sets") {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersect 1")
      assert(!contains(s, 2), "Intersect 2")
    }
  }

  test("diff contains 4") {
    new TestSets {
      val s = diff(s4, s5)
      assert(contains(s, 4), "diff 4")
      assert(!contains(s, 2), "diff 2")
      assert(!contains(s, 6), "diff 6")
      assert(!contains(s, 5), "diff 5")
      assert(!contains(s, 8), "diff 8")
    }
  }

  test("filter even numbers") {
    new TestSets {
      def even: Int => Boolean = x => x % 2 == 0

      val s = filter(s5, even)

      assert(contains(s, 2), "filter 2")
      assert(contains(s, 6), "filter 6")
      assert(contains(s, 8), "filter 8")
      assert(!contains(s, 5), "filter 5")
    }
  }

  test("filter no matches numbers") {
    new TestSets {
      def condition: Int => Boolean = x => x == 12

      val s = filter(s5, condition)
      assert(!contains(s, 12), "filter 12")
      assert(!contains(s, 2), "filter 2")
      assert(!contains(s, 6), "filter 6")
      assert(!contains(s, 8), "filter 8")
      assert(!contains(s, 5), "filter 5")
    }
  }

  test("forall even numbers") {
    new TestSets {
      def condition: Int => Boolean = x => x % 2 == 0

      assert(forall(s4, condition), "forall s4")
      assert(forall(s2, condition), "forall s2")
      assert(!forall(s5, condition), "forall s5")
      assert(forall(sBig, condition), "forall sBig")
    }
  }

  test("exists") {
    new TestSets {
      def condition: Int => Boolean = x => x == 6

      assert(exists(s4, condition), "forall s4")
      assert(exists(s5, condition), "forall s5")
      assert(!exists(s1, condition), "forall s1")
      assert(!exists(s2, condition), "forall s2")
      assert(!exists(s3, condition), "forall s3")
    }
  }

  test("map") {
    new TestSets {
      def f: Int => Int = x => x + 1

      def s = map(s4, f)

      print(printSet(s))

      assert(contains(s, 3), "map 3")
      assert(contains(s, 5), "map 5")
      assert(contains(s, 7), "map 7")

      assert(!contains(s, 1), "map 1")
      assert(!contains(s, 2), "map 2")
      assert(!contains(s, 4), "map 4")
      assert(!contains(s, 6), "map 6")
      assert(!contains(s, 8), "map 6")
    }
  }

  test("more maps") {
    new TestSets {
      def f: Int => Int = x => x * x

      def s = map(s5, f)

      print(printSet(s))

      assert(contains(s, 4), "map 4")
      assert(contains(s, 25), "map 25")
      assert(contains(s, 36), "map 36")
      assert(contains(s, 64), "map 64")

      assert(!contains(s, 1), "map 1")
      assert(!contains(s, 2), "map 2")
      assert(!contains(s, 3), "map 4")
      assert(!contains(s, 6), "map 6")
      assert(!contains(s, 7), "map 6")
      assert(!contains(s, 8), "map 6")
      assert(!contains(s, 9), "map 6")
    }
  }

}
