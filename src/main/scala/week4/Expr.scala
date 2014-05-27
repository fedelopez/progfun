package week4

/**
 * @author fede
 */
trait Expr {
  def isNumber: Boolean

  def isSum: Boolean

  def numValue: Int

  def leftOp: Expr

  def rightOp: Expr

  /**
   * Problem: writing all this classification and accessor methods becomes tedious!
   * Furthermore, adding new subclasses to Expr involves a quadratic increase in number of methods to add to all the
   * subclasses.
   */
  def eval(e: Expr): Int = {
    if (e.isNumber) e.numValue
    else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
    else throw new Error("Unkown expression " + e)
  }
}

class Number(n: Int) extends Expr {
  def isNumber: Boolean = true

  def isSum: Boolean = false

  def numValue: Int = n

  def leftOp: Expr = throw new Error("number.leftOp")

  def rightOp: Expr = throw new Error("number.rightOp")
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  def isNumber: Boolean = false

  def isSum: Boolean = true

  def numValue: Int = throw new Error("sum.numValue")

  def leftOp: Expr = e1

  def rightOp: Expr = e2


}

