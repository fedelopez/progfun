package week1

/**
 * @author fede
 */
object CountingChange {

  def countChange(amount: Int, coins: List[Int]): Int = {

    def doCountChange(coin: Int, currentAmount: Int, coins: List[Int]): Int = {
      var total = 0
      if (currentAmount == amount) total = 1
      else if (coin + currentAmount <= amount) {
        coins.foreach((currentCoin) => {
          total += doCountChange(currentCoin, currentCoin + currentAmount, coins.filter((aCoin) => currentCoin >= coin))
        })
      }
      total
    }

    val goodCoins = coins.filter((coin) => coin > 0).sorted
    if (goodCoins.isEmpty) 0
    else {
      doCountChange(goodCoins.head, 0, goodCoins)
    }
  }

}
