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

  test("sentenceOccurrences: abcd e") {
    assert(sentenceOccurrences(List("abcd", "e")) === List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1)))
  }

  test("sentenceOccurrences: abcd ea ac") {
    assert(sentenceOccurrences(List("abcd", "ea", "ac")) === List(('a', 3), ('b', 1), ('c', 2), ('d', 1), ('e', 1)))
  }

  test("dictionaryByOccurrences.get: eat") {
    assert(dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))).map(_.toSet) === Some(Set("ate", "eat", "tea")))
  }

  test("word anagrams: married") {
    assert(wordAnagrams("married").toSet === Set("married", "admirer"))
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

    val res = List(('a', 1))
    assert(subtract(ad, d) === res)
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
    val toSet: Set[Anagrams.Occurrences] = combinations(abba).toSet

    //Set(List((a,1)), List((b,1)), List((b,2), (a,2)), List((b,2)), List(), List((b,1), (a,2)), List((a,2)))
    //Set(List((a,1)), List((b,1)), List((b,2)), List((a,1), (b,1)), List(), List((a,2), (b,1)), List((a,2)), List((a,1), (b,2)), List((a,2), (b,2)))

    assert(toSet === abbacomb.toSet)
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
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }
}
