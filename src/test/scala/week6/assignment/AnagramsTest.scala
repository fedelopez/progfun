package week6.assignment

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * @author fede
 */
@RunWith(classOf[JUnitRunner])
class AnagramsTest extends FunSuite {

  import Anagrams._

  test("wordOccurrences: abcd") {
    val res: Anagrams.Occurrences = wordOccurrences("abcd")
    assert(res === List(('a', 1), ('b', 1), ('c', 1), ('d', 1)))
  }

  test("wordOccurrences: Robert") {
    assert(wordOccurrences("Robert") === List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1)))
  }

  test("wordOccurrences: uppercase") {
    val expected: List[(Char, Int)] = List(('i', 1), ('l', 1), ('n', 1), ('u', 1), ('x', 1))

    assert(wordOccurrences("LINUX") === expected)
    assert(wordOccurrences("lInUx") === expected)
  }

  test("sentenceOccurrences: abcd e") {
    assert(sentenceOccurrences(List("abcd", "e")) === List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1)))
  }

  test("sentenceOccurrences: abcd ea ac") {
    assert(sentenceOccurrences(List("abcd", "ea", "ac")) === List(('a', 3), ('b', 1), ('c', 2), ('d', 1), ('e', 1)))
  }

  test("dictionaryByOccurrences.get: eat") {
    assert(dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))).map(_.toSet) === Some(Set("ate", "eat", "tea")))
  }

  test("dictionaryByOccurrences: linux") {
    val linux = List(('i', 1), ('l', 1), ('n', 1), ('u', 1), ('x', 1))
    assert(dictionaryByOccurrences(linux) === List("Linux"))
  }

  test("dictionaryByOccurrences: unexisting") {
    assert(dictionaryByOccurrences(List(('i', 1))) === List())
    assert(dictionaryByOccurrences(List(('z', 1))) === List())
    assert(dictionaryByOccurrences(List(('q', 1))) === List())
    assert(dictionaryByOccurrences(List(('d', 2))) === List())
  }

  test("word anagrams: married") {
    assert(wordAnagrams("married").toSet === Set("married", "admirer"))
  }

  test("word anagrams: uppercase") {
    assert(wordAnagrams("LINUX").toSet === Set("Linux"))
    assert(wordAnagrams("linux").toSet === Set("Linux"))
  }

  test("word anagrams: player") {
    assert(wordAnagrams("player").toSet === Set("parley", "pearly", "player", "replay"))
  }

  test("subtract: lard - r") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val r = List(('r', 1))
    val lad = List(('a', 1), ('d', 1), ('l', 1))
    assert(subtract(lard, r) === lad)
  }

  test("subtract: lard - l") {
    val lard = List(('a', 1), ('d', 1), ('l', 2), ('r', 1))
    val l = List(('l', 2))
    val ard = List(('a', 1), ('d', 1), ('r', 1))
    assert(subtract(lard, l) === ard)
  }

  test("subtract: lard - empty") {
    val lard = List(('a', 1), ('d', 1), ('l', 2), ('r', 1))
    val l = List()
    assert(subtract(lard, l) === lard)
  }

  test("subtract: lard - rd") {
    val lard = List(('a', 1), ('d', 1), ('l', 2), ('r', 1))
    val rd = List(('d', 1), ('r', 1))

    val la = List(('a', 1), ('l', 2))
    assert(subtract(lard, rd) === la)
  }

  test("subtract: less frequency") {
    val ad = List(('a', 1), ('d', 2))
    val d = List(('d', 1))

    val res = List(('a', 1), ('d', 1))
    assert(subtract(ad, d) === res)
  }

  test("subtract: less frequency with 3 elements") {
    val tuples = List(('a', 1), ('d', 3), ('f', 4))
    val toSubtract = List(('d', 1), ('f', 3))

    val res = List(('a', 1), ('d', 2), ('f', 1))
    assert(subtract(tuples, toSubtract) === res)
  }

  test("subtract: both are equal") {
    val tuples = List(('d', 1), ('f', 3))
    val toSubtract = List(('d', 1), ('f', 3))

    assert(subtract(tuples, toSubtract).isEmpty)
  }

  test("combinations: []") {
    assert(combinations(Nil) === List(Nil))
  }

  test("combinations: [(a, 1)]") {
    val res: List[Anagrams.Occurrences] = combinations(List(('a', 1)))
    assert(res.length === 2)
    assert(res.contains(List()))
    assert(res.contains(List(('a', 1))))
  }

  test("combinations: [(a, 1), (b, 1)]") {
    val res: List[Anagrams.Occurrences] = combinations(List(('a', 1), ('b', 1)))
    assert(res.length === 4)
    assert(res.contains(List()))
    assert(res.contains(List(('a', 1))))
    assert(res.contains(List(('b', 1))))
    assert(res.contains(List(('a', 1), ('b', 1))))
  }

  test("combinations: abba") {
    val abba = List(('a', 2), ('b', 2))
    val abbacomb = List(
      List(),
      List(('a', 1)),
      List(('a', 2)),
      List(('b', 1)),
      List(('a', 1), ('b', 1)),
      List(('a', 2), ('b', 1)),
      List(('b', 2)),
      List(('a', 1), ('b', 2)),
      List(('a', 2), ('b', 2))
    )
    val toSet: Set[Occurrences] = combinations(abba).toSet

    assert(toSet === abbacomb.toSet)
  }

  test("sentenceAnagramsAcc") {

    val abba = List(('a', 2), ('b', 2))

    val occInDic1 = List(('a', 1))
    val occInDic2 = List(('a', 2), ('b', 1))
    val occInDic3 = List(('b', 1))

    val combinationsInDictionary: List[Occurrences] = List(occInDic3, occInDic2, occInDic1)

    val res: List[List[Occurrences]] = sentenceAnagramsAcc(abba, combinationsInDictionary)
    assert(res.length == 2)
    assert(res.contains(List(occInDic2, occInDic3)))
    assert(res.contains(List(occInDic3, occInDic2)))
  }

  test("isCombination") {

    val abba = List(('a', 2), ('b', 2))

    val occInDic1 = List(('a', 1))
    val occInDic2 = List(('a', 2), ('b', 1))
    val occInDic3 = List(('b', 1))

    assert(isCombination(List(occInDic2, occInDic3), abba))
    assert(!isCombination(List(occInDic1, occInDic3), abba))
    assert(!isCombination(List(occInDic1, occInDic2), abba))
    assert(!isCombination(List(occInDic1, occInDic2, occInDic3), abba))
  }

  test("isCombination: bug") {

    val abba = List(('a', 2), ('b', 2))

    val occInDic1 = List(('a', 1))
    val occInDic2 = List(('a', 2), ('b', 1))
    val occInDic3 = List(('b', 1))

    assert(!isCombination(List(occInDic2, occInDic1, occInDic3), abba))
  }

  test("sentence anagrams: []") {
    val sentence = List()
    assert(sentenceAnagrams(sentence) === List(Nil))
  }

  test("sentence anagrams: Linux rulez") {
    val sentence = List("Linux", "rulez")
    val anas = List(
      List("Rex", "Lin", "Zulu"),
      List("nil", "Zulu", "Rex"),
      List("Rex", "nil", "Zulu"),
      List("Zulu", "Rex", "Lin"),
      List("null", "Uzi", "Rex"),
      List("Rex", "Zulu", "Lin"),
      List("Uzi", "null", "Rex"),
      List("Rex", "null", "Uzi"),
      List("null", "Rex", "Uzi"),
      List("Lin", "Rex", "Zulu"),
      List("nil", "Rex", "Zulu"),
      List("Rex", "Uzi", "null"),
      List("Rex", "Zulu", "nil"),
      List("Zulu", "Rex", "nil"),
      List("Zulu", "Lin", "Rex"),
      List("Lin", "Zulu", "Rex"),
      List("Uzi", "Rex", "null"),
      List("Zulu", "nil", "Rex"),
      List("rulez", "Linux"),
      List("Linux", "rulez")
    )
    val anagrams: List[Sentence] = sentenceAnagrams(sentence)
    assert(anagrams.toSet === anas.toSet)
  }
}
