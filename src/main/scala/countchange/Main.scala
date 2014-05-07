package countchange

/**
 * @author fede
 */
object Main {

  def countChange(amount: Int, coins: List[Int]): Int = {

    def doCountChange(result: Int, coin: Int, currentAmount: Int, coins: List[Int]): Int = {
      var total = 0
      if (currentAmount == amount) total = 1
      else if (currentAmount + coin <= amount) {
        for (currentCoin <- coins) {
          val outstanding = currentCoin + currentAmount
          total += doCountChange(total, currentCoin, outstanding, coins.filter((aCoin) => currentCoin >= coin))
        }
      }
      if (result == amount) print(s"Result found $result")
      total
    }

    val goodCoins = coins.filter((coin) => coin > 0).sorted
    if (goodCoins.isEmpty) 0
    else {
      doCountChange(0, goodCoins.head, 0, goodCoins)
    }
  }
}
