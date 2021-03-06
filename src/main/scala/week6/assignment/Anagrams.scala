package week6.assignment

import scala.io.Source

/**
 * @author fede
 */
object Anagrams {

  /** A word is simply a `String`. */
  type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]

  /**
   * `Occurrences` is a `List` of pairs of characters and positive integers saying
   * how often the character appears.
   * This list is sorted alphabetically w.r.t. to the character in each pair.
   * All characters in the occurrence list are lowercase.
   *
   * Any list of pairs of lowercase characters and their frequency which is not sorted
   * is **not** an occurrence list.
   *
   * Note: If the frequency of some character is zero, then that character should not be
   * in the list.
   */
  type Occurrences = List[(Char, Int)]

  /**
   * The dictionary is simply a sequence of words.
   * It is predefined and obtained as a sequence using the utility method `loadDictionary`.
   */
  val dictionary: List[Word] = Source.fromFile(getClass.getResource("linuxwords.txt").getFile).getLines.toList

  /**
   * Converts the word into its character occurrence list.
   *
   * Note: the uppercase and lowercase version of the character are treated as the
   * same character, and are represented as a lowercase character in the occurrence list.
   */
  def wordOccurrences(word: Word): Occurrences = {
    val lowercase: String = word.toLowerCase
    val res: Map[(Char, Int), String] = lowercase groupBy (c => (c, lowercase.count(char => char == c)))
    val map: Map[Char, Int] = for (tuple <- res) yield tuple._1
    map.toList.sortWith((t1, t2) => t1._1 < t2._1)
  }

  /** Converts a sentence into its character occurrence list. */
  def sentenceOccurrences(s: Sentence): Occurrences = {
    val map: List[Char] = s.flatMap(word => word)
    wordOccurrences(map mkString "")
  }

  /**
   * The `dictionaryByOccurrences` is a `Map` from different occurrences to a sequence of all
   * the words that have that occurrence count.
   * This map serves as an easy way to obtain all the anagrams of a word given its occurrence list.
   *
   * For example, the word "eat" has the following character occurrence list:
   *
   * `List(('a', 1), ('e', 1), ('t', 1))`
   *
   * Incidentally, so do the words "ate" and "tea".
   *
   * This means that the `dictionaryByOccurrences` map will contain an entry:
   *
   * List(('a', 1), ('e', 1), ('t', 1)) -> Seq("ate", "eat", "tea")
   *
   */
  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = {
    val tuples: List[(Occurrences, Word)] = for (word <- dictionary) yield wordOccurrences(word) -> word
    val grouped: Map[Occurrences, List[(Occurrences, Word)]] = tuples groupBy (x => x._1)
    (for (x <- grouped) yield x._1 -> x._2.map(z => z._2)).withDefaultValue(List())
  }

  /** Returns all the anagrams of a given word. */
  def wordAnagrams(word: Word): List[Word] =
    dictionaryByOccurrences.filter(tuple => tuple._2.exists(elem => elem.equalsIgnoreCase(word))).head._2

  /**
   * Returns the list of all subsets of the occurrence list.
   * This includes the occurrence itself, i.e. `List(('k', 1), ('o', 1))`
   * is a subset of `List(('k', 1), ('o', 1))`.
   * It also include the empty subset `List()`.
   *
   * Example: the subsets of the occurrence list `List(('a', 2), ('b', 2))` are:
   *
   * List(
   * List(),
   * List(('a', 1)),
   * List(('a', 2)),
   * List(('b', 1)),
   * List(('a', 1), ('b', 1)),
   * List(('a', 2), ('b', 1)),
   * List(('b', 2)),
   * List(('a', 1), ('b', 2)),
   * List(('a', 2), ('b', 2))
   * )
   *
   * Note that the order of the occurrence list subsets does not matter -- the subsets
   * in the example above could have been displayed in some other order.
   */
  def combinations(occurrences: Occurrences): List[Occurrences] = {
    val res = for {
      occurrence <- occurrences
      i <- 1 to occurrence._2
      tuple = (occurrence._1, i)

      others: Occurrences = occurrences.filter(t => tuple._1 < t._1)
      j <- 0 to others.length

      taken: Occurrences = others.take(j)

      otherCombinations: List[Occurrences] = combinations(taken)
      otherCombination <- otherCombinations

    } yield tuple :: otherCombination

    res.distinct ::: List(Nil)
  }

  /**
   * Subtracts occurrence list `y` from occurrence list `x`.
   *
   * The precondition is that the occurrence list `y` is a subset of
   * the occurrence list `x` -- any character appearing in `y` must
   * appear in `x`, and its frequency in `y` must be smaller or equal
   * than its frequency in `x`.
   *
   * Note: the resulting value is an occurrence - meaning it is sorted
   * and has no zero-entries.
   */
  def subtract(x: Occurrences, y: Occurrences): Occurrences =
    if (y.isEmpty) x
    else {
      val tuples = {
        for {
          occurrence <- x
          other = y find (y1 => y1._1 == occurrence._1)
          result = doSubtract(occurrence, other)
        } yield result
      }
      tuples.filter(elem => elem._2 != 0)
    }

  def doSubtract(original: (Char, Int), updated: Option[(Char, Int)]): (Char, Int) = {
    if (updated.isEmpty) original
    else (updated.get._1, original._2 - updated.get._2)
  }

  /**
   * Returns a list of all anagram sentences of the given sentence.
   *
   * An anagram of a sentence is formed by taking the occurrences of all the characters of
   * all the words in the sentence, and producing all possible combinations of words with those characters,
   * such that the words have to be from the dictionary.
   *
   * The number of words in the sentence and its anagrams does not have to correspond.
   * For example, the sentence `List("I", "love", "you")` is an anagram of the sentence `List("You", "olive")`.
   *
   * Also, two sentences with the same words but in a different order are considered two different anagrams.
   * For example, sentences `List("You", "olive")` and `List("olive", "you")` are different anagrams of
   * `List("I", "love", "you")`.
   *
   * Here is a full example of a sentence `List("Yes", "man")` and its anagrams for our dictionary:
   *
   * List(
   * List(en, as, my),
   * List(en, my, as),
   * List(man, yes),
   * List(men, say),
   * List(as, en, my),
   * List(as, my, en),
   * List(sane, my),
   * List(Sean, my),
   * List(my, en, as),
   * List(my, as, en),
   * List(my, sane),
   * List(my, Sean),
   * List(say, men),
   * List(yes, man)
   * )
   *
   * The different sentences do not have to be output in the order shown above - any order is fine as long as
   * all the anagrams are there. Every returned word has to exist in the dictionary.
   *
   * Note: in case that the words of the sentence are in the dictionary, then the sentence is the anagram of itself,
   * so it has to be returned in this list.
   *
   * Note: There is only one anagram of an empty sentence.
   *
   * todo: not working
   */
  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    val targetOccurrences: Occurrences = sentenceOccurrences(sentence)
    val occurrencesInDictionary: List[Occurrences] = combinations(targetOccurrences).filter(elem => dictionaryByOccurrences.contains(elem))

    val acc: List[List[Occurrences]] = sentenceAnagramsAcc(targetOccurrences, occurrencesInDictionary)
    val t = for {
      accRes: List[Occurrences] <- acc
      map: List[List[Word]] = accRes.map(dictionaryByOccurrences)
      listWord: List[Word] <- map
    } yield listWord

    t
  }

  def sentenceAnagramsAcc(targetOccurrences: Occurrences, occurrencesInDictionary: List[Occurrences]): List[List[Occurrences]] = {
    for {
      occurrence <- occurrencesInDictionary
      others = occurrencesInDictionary.filter(p => p != occurrence)
      i <- 1 to others.length
      take: List[Occurrences] = others.take(i)

      occurrenceList: List[Occurrences] = occurrence :: take
      if isCombination(occurrenceList, targetOccurrences)

    } yield occurrenceList
  }

  def isCombination(occurrenceList: List[Occurrences], target: Occurrences): Boolean = {
    if (occurrenceList.isEmpty) false
    else {
      val subtracted: Occurrences = subtract(target, occurrenceList.head)
      if (subtracted == target) {
        false
      }
      else {
        if (subtracted.size == 0) {
          true
        }
        else isCombination(occurrenceList.tail, subtracted)
      }
    }
  }
}
