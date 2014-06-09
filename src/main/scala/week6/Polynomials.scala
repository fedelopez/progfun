package week6

/**
 * @author fede
 */
object Polynomials {

  class Poly(val terms0: Map[Int, Double]) {
    val terms = terms0 withDefaultValue 0.0

    def this(bindings: (Int, Double)*) = this(bindings.toMap)

    def +(other: Poly) = new Poly(terms ++ (other.terms map adjustCoefficient))

    def adjustCoefficient(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      exp -> (coeff + terms(exp))
    }

    /**
     * same operation than "+" but using fold left
     */
    def plus(other: Poly) = new Poly(other.terms.foldLeft(terms)(addTerm))

    def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
      val (exp, coeff) = term
      terms + (exp -> (coeff + terms(exp)))
    }

    override def toString = (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
  }


}
