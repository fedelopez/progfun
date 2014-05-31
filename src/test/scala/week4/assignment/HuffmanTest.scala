package week4.assignment

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import week4.assignment.Huffman.{Bit, CodeTree, Fork, Leaf}

@RunWith(classOf[JUnitRunner])
class HuffmanTest extends FunSuite {

  import Huffman.chars
  import Huffman.times
  import Huffman.weight
  import Huffman.increaseOccurrences
  import Huffman.makeOrderedLeafList
  import Huffman.singleton
  import Huffman.combine
  import Huffman.until
  import Huffman.createCodeTree
  import Huffman.decode
  import Huffman.encode

  trait TestSets {
    val leafA: Leaf = new Leaf('a', 8)
    val leafD: Leaf = new Leaf('d', 2)
    val left: Fork = new Fork(leafA, leafD, List('a', 'd'), 10)

    val leafB: Leaf = new Leaf('b', 3)
    val leafC: Leaf = new Leaf('c', 1)
    val right: Fork = new Fork(leafB, leafC, List('b', 'c'), 4)

    val tree: CodeTree = new Fork(left, right, List('a', 'd', 'b', 'c'), 14)
  }

  test("weight: on leaf") {
    new TestSets {
      assert(weight(leafD) == 2)
    }
  }

  test("weight: on tree") {
    new TestSets {
      val actual: CodeTree = new Fork(leafA, leafD, List('a', 'd'), 10)

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
      val actual: List[Char] = chars(leafD)
      assert(actual.size == 1)
      assert(actual.head === leafD.char)
    }
  }

  test("chars: on tree") {
    new TestSets {
      val actual: List[Char] = chars(new Fork(leafA, leafD, List('a', 'd'), 10))

      assert(actual.size == 2)
      assert(actual.contains(leafA.char))
      assert(actual.contains(leafD.char))
    }
  }

  test("chars: on two level tree") {
    new TestSets {
      val actual: List[Char] = chars(tree)

      assert(actual.size == 4)
      assert(actual.contains('a'))
      assert(actual.contains('d'))
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

  test("times: multiple characters") {
    val list: List[Char] = List('a', 'd', 'a', 'd', 'b', 'c', 'b', 'a')

    val actual: List[(Char, Int)] = times(list)

    assert(actual.size == 4)

    val first: (Char, Int) = actual.apply(0)
    assert(first._1 === 'a')
    assert(first._2 === 3)

    val second: (Char, Int) = actual.apply(1)
    assert(second._1 === 'd')
    assert(second._2 === 2)

    val third: (Char, Int) = actual.apply(2)
    assert(third._1 === 'b')
    assert(third._2 === 2)

    val fourth: (Char, Int) = actual.apply(3)
    assert(fourth._1 === 'c')
    assert(fourth._2 === 1)
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


  test("makeOrderedLeafList: one element") {
    val res: List[Leaf] = makeOrderedLeafList(List(('a', 3)))

    assert(res.size == 1)

    val leaf: Leaf = res.apply(0)
    assert(leaf.char === 'a')
    assert(leaf.weight === 3)
  }

  test("makeOrderedLeafList: two elements") {
    val res: List[Leaf] = makeOrderedLeafList(List(('a', 3), ('b', 2)))

    assert(res.size == 2)

    val leaf1: Leaf = res.apply(0)
    assert(leaf1.char === 'b')
    assert(leaf1.weight === 2)

    val leaf2: Leaf = res.apply(1)
    assert(leaf2.char === 'a')
    assert(leaf2.weight === 3)
  }

  test("makeOrderedLeafList: three elements") {
    val res: List[Leaf] = makeOrderedLeafList(List(('a', 3), ('f', 1), ('b', 2)))

    assert(res.size == 3)

    val leaf1: Leaf = res.apply(0)
    assert(leaf1.char === 'f')
    assert(leaf1.weight === 1)

    val leaf2: Leaf = res.apply(1)
    assert(leaf2.char === 'b')
    assert(leaf2.weight === 2)

    val leaf3: Leaf = res.apply(2)
    assert(leaf3.char === 'a')
    assert(leaf3.weight === 3)
  }

  /**
   * https://class.coursera.org/progfun-004/forum/thread?thread_id=820
   * is empty list a singleton? no
   */
  test("singleton: empty list") {
    new TestSets {
      assert(singleton(List()) === false)
    }
  }

  test("singleton: leafs only") {
    new TestSets {
      val list: List[Leaf] = List(leafA, leafB, leafC)
      assert(singleton(list) === false)
    }
  }

  test("singleton: leafs and fork") {
    new TestSets {
      val list: List[CodeTree] = List(right, leafD, leafA)
      assert(singleton(list) === false)
    }
  }

  test("singleton: forks only") {
    new TestSets {
      val list: List[CodeTree] = List(right, left)
      assert(singleton(list) === false)
    }
  }

  test("singleton: true") {
    new TestSets {
      val list: List[CodeTree] = List(tree)
      assert(singleton(list) === true)
    }
  }

  test("combine: if list has less than two elements, that list should be returned unchanged") {
    new TestSets {
      val result: List[CodeTree] = combine(List(tree))

      assert(result.size === 1)

      val codeTree: CodeTree = result.apply(0)
      assert(weight(codeTree) === weight(tree))
    }
  }

  test("combine: two leafs") {
    new TestSets {
      val result: List[CodeTree] = combine(List(leafB, leafA))

      assert(result.size === 1)

      val codeTree: CodeTree = result.apply(0)
      assert(weight(codeTree) === weight(leafA) + weight(leafB))
    }
  }

  test("combine: four leafs") {
    new TestSets {
      val result: List[CodeTree] = combine(List(leafC, leafD, leafB, leafA))

      assert(result.size === 3)

      assert(weight(result.apply(0)) === weight(leafC) + weight(leafD))
      val charsAt0: List[Char] = chars(result.apply(0))
      assert(charsAt0.size === 2)
      assert(charsAt0.apply(0) === leafC.char)
      assert(charsAt0.apply(1) === leafD.char)

      assert(weight(result.apply(1)) === weight(leafB))
      val charsAt1: List[Char] = chars(result.apply(1))
      assert(charsAt1.size === 1)
      assert(charsAt1.apply(0) === leafB.char)

      assert(weight(result.apply(2)) === weight(leafA))
      val charsAt2: List[Char] = chars(result.apply(2))
      assert(charsAt2.size === 1)
      assert(charsAt2.apply(0) === leafA.char)

    }
  }

  test("until") {
    new TestSets {
      val trees: List[CodeTree] = List(leafC, leafD, leafB, leafA)

      val result: List[CodeTree] = until(singleton, combine)(trees)

      assert(result.size === 1)

      val codeTree: CodeTree = result.apply(0)
      assert(weight(codeTree) === weight(leafA) + weight(leafB) + weight(leafC) + weight(leafD))
      assert(chars(codeTree) === List(leafC.char, leafD.char, leafB.char, leafA.char))
    }
  }

  test("createCodeTree") {
    new TestSets {

      val result: CodeTree = createCodeTree(List('a', 'd', 'a', 'd', 'b', 'c', 'b', 'a'))
      assert(weight(result) === 8)
    }
  }

  test("decode") {
    new TestSets {
      val result: List[Char] = decode(Huffman.frenchCode, Huffman.secret)
      print(result)
    }
  }

  test("encode") {
    new TestSets {
      val result: List[Bit] = encode(Huffman.frenchCode)(List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))

      println("Actual   : " + result)
      println("Expected : " + Huffman.secret)

      assert(result.length === Huffman.secret.length)
      assert(result.equals(Huffman.secret))
    }
  }

}
