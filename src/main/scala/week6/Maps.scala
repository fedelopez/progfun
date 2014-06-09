package week6

/**
 * @author fede
 */
object Maps {

  val romanNumerals = Map('I' -> 1, 'V' -> 5, 'X' -> 10)
  val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")

  def main(args: Array[String]) {
    val option: Option[String] = capitalOfCountry get "Andorra"
    //capitalOfCountry("Andorra") this would throw a NoSuchElementException
    val option2: Option[String] = capitalOfCountry get "US"
    println(option)
    println(option2)
    println(showCapital("Andorra"))
    println(showCapital("US"))
  }

  def showCapital(country: String) = capitalOfCountry.get(country) match {
    case Some(capital) => capital
    case None => s"Unknown country: $country"
  }
}
