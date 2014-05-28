package week4.assignment

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import week4.assignment.Huffman.{CodeTree, Fork, Leaf}

@RunWith(classOf[JUnitRunner])
class HuffmanTest extends FunSuite {

  import Huffman.chars
  import Huffman.weight

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

}
