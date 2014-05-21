package week3.assignment

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TweetSetSuite extends FunSuite {

  trait TestSets {
    val a = new Tweet("a", "a body", 20)
    val b = new Tweet("b", "b body", 20)
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)

    val set1 = new Empty
    val set2 = set1.incl(a)
    val set3 = set2.incl(b)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }

  def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  }

  def size(set: TweetSet): Int = asSet(set).size

  test("filter: on empty set") {
    new TestSets {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5") {
    new TestSets {
      val result = set5.filter(tw => tw.user == "a")
      assert(size(result) === 1)
      assert(result.contains(a))
    }
  }

  test("filter: > 8 on set5") {
    new TestSets {
      val result = set5.filter(tw => tw.retweets > 8)
      assert(size(result) === 3)
      assert(result.contains(a))
      assert(result.contains(b))
      assert(result.contains(d))
    }
  }

  test("union: set4c and set4d") {
    new TestSets {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1)") {
    new TestSets {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2)") {
    new TestSets {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("union: with set4c") {
    new TestSets {
      val e = new Tweet("e", "e body", 12)
      val f = new Tweet("f", "f body", 15)

      val set6 = new Empty
      val set7 = set6.incl(e)
      val set8 = set7.incl(f)
      val set9 = set8.incl(a)

      val res = set4c.union(set9)

      assert(size(res) === 5)
      assert(res.contains(e))
      assert(res.contains(f))
      assert(res.contains(a))
      assert(res.contains(b))
      assert(res.contains(c))
    }
  }

  test("descending: set5") {
    new TestSets {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  test("descending: other sets") {
    val a1 = new Tweet("a", "a body", 3)
    val b1 = new Tweet("b", "b body", 25)
    val c1 = new Tweet("c", "c body", 7)
    val d1 = new Tweet("d", "d body", 1)

    val set11 = new Empty
    val set21 = set11.incl(a1)
    val set31 = set21.incl(b1)
    val set4c1 = set31.incl(c1)
    val set4d1 = set4c1.incl(d1)
    val set51 = set4d1.incl(d1)

    val trends = set51.descendingByRetweet
    assert(trends.head.user == b1.user)
    assert(trends.tail.head.user == c1.user)
    assert(trends.tail.tail.head.user == a1.user)
    assert(trends.tail.tail.tail.head.user == d1.user)
  }

  test("descending: empty") {
    new TestSets {
      val trends = set1.descendingByRetweet
      assert(trends.isEmpty)
    }
  }

  test("mostRetweeted: empty") {
    new TestSets {
      intercept[NoSuchElementException] {
        set1.mostRetweeted
      }
    }
  }

  test("mostRetweeted: set5") {
    new TestSets {
      val result = set5.mostRetweeted
      assert(result === a)
    }
  }
}
