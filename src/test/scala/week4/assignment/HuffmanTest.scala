package week4.assignment

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import week4.assignment.Huffman.{CodeTree, Fork, Leaf}

@RunWith(classOf[JUnitRunner])
class HuffmanTest extends FunSuite {

  import Huffman.chars
  import Huffman.times
  import Huffman.weight
  import Huffman.increaseOccurrences

  trait TestSets {
    val leafA: Leaf = new Leaf('a', 8)
    val leafS: Leaf = new Leaf('s', 2)
    val left: Fork = new Fork(leafA, leafS, List('a', 's'), 10)

    val leafB: Leaf = new Leaf('b', 3)
    val leafC: Leaf = new Leaf('c', 1)
    val right: Fork = new Fork(leafB, leafC, List('b', 'c'), 4)

    val tree: CodeTree = new Fork(left, right, List('a', 's', 'b', 'c'), 14)
  }

  test("weight: on leaf") {
    new TestSets {
      assert(weight(leafS) == 2)
    }
  }

  test("weight: on tree") {
    new TestSets {
      val actual: CodeTree = new Fork(leafA, leafS, List('a', 's'), 10)

      assert(weight(actual) == 10)
    }
  }

  test("weight: on two level tree") {
    new TestSets {
      assert(weight(tree) == 14)
    }
  }

  test("chars: on leaf") {
    new TestSets {
      val actual: List[Char] = chars(leafS)
      assert(actual.size == 1)
      assert(actual.head === 's')
    }
  }

  test("chars: on tree") {
    new TestSets {
      val actual: List[Char] = chars(new Fork(leafA, leafS, List('a', 's'), 10))

      assert(actual.size == 2)
      assert(actual.contains('a'))
      assert(actual.contains('s'))
    }
  }

  test("chars: on two level tree") {
    new TestSets {
      val actual: List[Char] = chars(tree)

      assert(actual.size == 4)
      assert(actual.contains('a'))
      assert(actual.contains('s'))
      assert(actual.contains('b'))
      assert(actual.contains('c'))
    }
  }

  test("times") {
    val list: List[Char] = List('a', 'b', 'a')

    val actual: List[(Char, Int)] = times(list)

    assert(actual.size == 2)

    val first: (Char, Int) = actual.apply(0)
    assert(first._1 === 'a')
    assert(first._2 === 2)

    val second: (Char, Int) = actual.apply(1)
    assert(second._1 === 'b')
    assert(second._2 === 1)
  }

  test("increaseOccurrences: empty list") {
    val res: List[(Char, Int)] = increaseOccurrences('a', List())

    assert(res.size == 1)
    val pair: (Char, Int) = res.apply(0)

    assert(pair._1 === 'a')
    assert(pair._2 === 1)
  }

  test("increaseOccurrences: element exists") {
    val res: List[(Char, Int)] = increaseOccurrences('a', List(('a', 1)))

    assert(res.size == 1)
    val pair: (Char, Int) = res.apply(0)

    assert(pair._1 === 'a')
    assert(pair._2 === 2)
  }

  test("increaseOccurrences: element exists at tail") {
    val res: List[(Char, Int)] = increaseOccurrences('a', List(('b', 3), ('a', 1)))

    assert(res.size == 2)

    val pair1: (Char, Int) = res.apply(0)
    assert(pair1._1 === 'b')
    assert(pair1._2 === 3)

    val pair2: (Char, Int) = res.apply(1)
    assert(pair2._1 === 'a')
    assert(pair2._2 === 2)
  }

  test("increaseOccurrences: element exists at middle") {
    val res: List[(Char, Int)] = increaseOccurrences('a', List(('b', 3), ('a', 1), ('c', 9)))

    assert(res.size == 3)

    val pair1: (Char, Int) = res.apply(0)
    assert(pair1._1 === 'b')
    assert(pair1._2 === 3)

    val pair2: (Char, Int) = res.apply(1)
    assert(pair2._1 === 'a')
    assert(pair2._2 === 2)

    val pair3: (Char, Int) = res.apply(2)
    assert(pair3._1 === 'c')
    assert(pair3._2 === 9)
  }


  test("increaseOccurrences: element does not exist") {
    val res: List[(Char, Int)] = increaseOccurrences('a', List(('b', 4)))

    assert(res.size == 2)

    val pair1: (Char, Int) = res.apply(0)
    assert(pair1._1 === 'b')
    assert(pair1._2 === 4)

    val pair2: (Char, Int) = res.apply(1)
    assert(pair2._1 === 'a')
    assert(pair2._2 === 1)
  }

}
