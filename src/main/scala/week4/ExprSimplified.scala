package week4

/**
 * OO Decomposition
 *
 * @author fede
 */
trait ExprSimplified {
  def eval: Int
}

class ExprSimplifiedNumber(n: Int) extends ExprSimplified {
  def eval: Int = n
}

class ExprSimplifiedSum(e1: ExprSimplified, e2: ExprSimplified) extends ExprSimplified {
  def eval: Int = e1.eval + e2.eval
}

