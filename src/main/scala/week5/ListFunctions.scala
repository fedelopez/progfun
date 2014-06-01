package week5

/**
 * @author fede
 */
object ListFunctions {

  /**
   * Last takes steps proportional to the length of the list
   */
  def last[T](xs: List[T]): T = xs match {
    case List() => throw new Error("last on empty list")
    case List(x) => x
    case y :: ys => last(ys)

  }

  def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("last on empty list")
    case List(x) => List()
    case y :: ys => y :: init(ys)
  }

  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case List() => ys
    case z :: zs => z :: concat(zs, ys)
  }

  /**
   * complexity n * n (first n for the concat, second n for the reverse)
   */
  def reverse[T](xs: List[T]): List[T] = xs match {
    case List() => xs
    case z :: zs => reverse(zs) ++ List(z) //concatenation is linear: e.g. takes time proportional to the length of the list
  }

  def mergeSort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (first, second) = xs splitAt n
      merge(mergeSort(first), mergeSort(second))
    }
  }

  def merge[T](xs: List[T], ys: List[T])(implicit ord: Ordering[T]): List[T] = (xs, ys) match {
    case (Nil, zs) => zs
    case (zs, Nil) => zs
    case (x :: xs1, y :: ys1) => if (ord.lt(x, y)) x :: merge(xs1, ys) else y :: merge(xs, ys1)
  }

  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case (y :: ys) => y * y :: squareList(ys)
  }

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: tail =>
      val (first, rest) = xs span (y => y == x)
      first :: pack(rest)
  }

}
