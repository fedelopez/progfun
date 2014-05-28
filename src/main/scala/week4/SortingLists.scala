package week4

/**
 * Insertion sort complexity relative to list with len N is N * N => not very good!
 *
 * @author fede
 */
object SortingLists {

  def isort(xs: scala.List[Int]): scala.List[Int] = xs match {
    case scala.List() => scala.List()
    case head :: tail => insert(head, isort(tail))
  }

  def insert(x: Int, xs: scala.List[Int]): scala.List[Int] = xs match {
    case scala.List() => scala.List(x)
    case head :: tail => if (x > head) head :: insert(x, tail) else x :: xs
  }

  def main(args: Array[String]) {
    val res: scala.List[Int] = isort(scala.List(4, 1, 9, 2, -1, 200, 5, 4, 3, 513))
    print(res)
  }

}
