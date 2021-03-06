package week4.assignment

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HuffmanTest extends FunSuite {

  import Huffman._

  trait TestSets {
    val leafA: Leaf = new Leaf('a', 8)
    val leafD: Leaf = new Leaf('d', 2)
    val left: Fork = new Fork(leafA, leafD, List('a', 'd'), 10)

    val leafB: Leaf = new Leaf('b', 3)
    val leafC: Leaf = new Leaf('c', 1)
    val right: Fork = new Fork(leafB, leafC, List('b', 'c'), 4)

    val tree: CodeTree = new Fork(left, right, List('a', 'd', 'b', 'c'), 14)
  }

  trait TestTrees {
    val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('d', 4), List('a', 'b', 'd'), 9)
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

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)))
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

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("code bits") {
    val codeTable: CodeTable = List(('h', List(0, 1, 1, 1, 0, 1)), ('u', List(1, 1, 1, 1, 0, 1)), ('f', List(1, 1, 1, 1, 0, 1)))

    assert(codeBits(codeTable)('h').equals(List(0, 1, 1, 1, 0, 1)))
    assert(codeBits(codeTable)('u').equals(List(1, 1, 1, 1, 0, 1)))
    assert(codeBits(codeTable)('f').equals(List(1, 1, 1, 1, 0, 1)))
  }

  test("code bits: element not found") {
    val codeTable: CodeTable = List(('h', List(0, 1, 1, 1, 0, 1)), ('u', List(1, 1, 1, 1, 0, 1)), ('f', List(1, 1, 1, 1, 0, 1)))

    intercept[NoSuchElementException] {
      codeBits(codeTable)('a')
    }
  }

  test("convert") {
    new TestSets {

      val codeTable: CodeTable = List(('h', List(0, 1, 1, 1, 0, 1)), ('u', List(1, 1, 1, 1, 0, 1)), ('f', List(1, 1, 1, 1, 0, 1)))

      val result: Huffman.CodeTable = convert(left)

      assert(result.length === 2)
      assert(codeBits(result)(leafA.char).equals(List(0)))
      assert(codeBits(result)(leafD.char).equals(List(1)))
    }
  }

  test("convert french code") {
    val result: Huffman.CodeTable = convert(frenchCode)
    print(result)
    assert(codeBits(result)('h').equals(List(0, 0, 1, 1, 1, 0, 1)))
  }

  test("merge code tables") {
    val result: CodeTable = mergeCodeTables(List(('f', List(0, 0, 1, 1, 0, 1))), List(('z', List(0, 0, 1, 1, 1, 0, 0, 0, 0))))

    assert(result.length == 2)
    assert(codeBits(result)('f').equals(List(0, 0, 1, 1, 0, 1)))
    assert(codeBits(result)('z').equals(List(0, 0, 1, 1, 1, 0, 0, 0, 0)))
  }

  test("quickEncode") {

    val result1: List[Bit] = encode(Huffman.frenchCode)(List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))
    val result2: List[Bit] = quickEncode(Huffman.frenchCode)(List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))

    println(result1)
    println(result2)

    assert(result1.length == result2.length)
    assert(result2.equals(Huffman.secret))
  }

}
