package week6

import scala.io.Source

/**
 * @author fede
 */
object PhoneKeys {

  val in = Source.fromFile(getClass.getResource("dictionary.txt").getFile)
  val words = in.getLines.toList filter (word => word forall (c => c.isLetter))
  val mnem = Map('2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL", '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")


  /**
   * Design a method "translate" that produces all phrases of words that can serve as mnemonics for the phone number
   */
  def translate(phoneNumber: String): Set[String] =
    encode(phoneNumber) map (_ mkString " ")

  def encode(phoneNumber: String): Set[List[String]] =
    if (phoneNumber.isEmpty) Set(List())
    else {
      for {
        split <- 1 to phoneNumber.length
        word <- wordsForNum(phoneNumber take split)
        rest <- encode(phoneNumber drop split)
      } yield word :: rest
    }.toSet

  /**
   * Invert the mnem map to give a map from chars, i.e. 'A' -> 2, 'B' -> 2, ... 'Z' -> 9
   */
  val charCode: Map[Char, Char] =
    for ((digit, str) <- mnem; ltr <- str) yield ltr -> digit

  /**
   * Maps a word to the string digits it represents, e.g. "Java" -> "5282"
   */
  def wordCode(word: String): String =
    word.toUpperCase map charCode

  /**
   * A map from digit string to all the words it can represent, e.g. "5282" -> List("Java", "Lava", "Kata", ...)
   */
  val wordsForNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue Seq()


}
