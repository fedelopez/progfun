package week1

/**
 * Exercise 1
 *
 * @author fede
 */
object PascalTriangle {

  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  def pascal(col: Int, row: Int): Int =
    if (col == row) 1
    else if (col == 0 || row == 1) 1
    else pascal(col - 1, row - 1) + pascal(col, row - 1)

}
