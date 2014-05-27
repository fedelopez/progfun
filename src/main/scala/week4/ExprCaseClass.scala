package week4

/**
 * Functional programming approach to tackle decomposition
 *
 * @author fede
 */
trait ExprCaseClass {

  def eval: Int = this match {
    case ExprCaseClassNumber(n) => n
    case ExprCaseClassSum(e1, e2) => e1.eval + e2.eval
  }

  def show: String = this match {
    case ExprCaseClassNumber(n) => n.toString
    case ExprCaseClassSum(e1, e2) => "(" + e1.show + " + " + e2.show + ")"
  }

}

case class ExprCaseClassNumber(n: Int) extends ExprCaseClass

case class ExprCaseClassSum(e1: ExprCaseClass, e2: ExprCaseClass) extends ExprCaseClass

object Main {

  def main(args: Array[String]) {
    val sumEight: ExprCaseClassSum = new ExprCaseClassSum(ExprCaseClassNumber(3), new ExprCaseClassNumber(5))
    val sum10: ExprCaseClassSum = new ExprCaseClassSum(ExprCaseClassNumber(6), new ExprCaseClassNumber(4))
    val sum: ExprCaseClassSum = new ExprCaseClassSum(sumEight, sum10)

    print(sum.eval)
    print(sum.show)
  }


}

