package countchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Counting Change
 * <p/>
 * Write a recursive function that counts how many different ways you can make change for an amount, given a list of coin denominations.
 * For example, there are 3 ways to give change for 4 if you have coins with denomination 1 and 2: 1+1+1+1, 1+1+2, 2+2.
 * <p/>
 * Do this exercise by implementing the function in CountChange.java. This function takes an amount to change, and a list of unique denominations for the coins.
 */
public class CountChange {

    public static void main(String[] args) {
        System.out.println("Change for $4 with $1 and $2 is: " + new CountChange().countChange(4, Arrays.asList(1, 2)));
    }

    public int countChange(int amount, List<Integer> coins) {
        return doCountChange(coins.get(0), 0, amount, coins);
    }

    private int doCountChange(int coin, int currentAmount, int amount, List<Integer> coins) {
        int total = 0;
        int outstanding = coin + currentAmount;
        if (outstanding > amount) return 0;
        else if (outstanding == amount) return 1;
        else {
            for (Integer currentCoin : coins) {
                outstanding = currentCoin + currentAmount;
                if (outstanding == amount) {
                    total += 1;
                } else if (outstanding < amount) {
                    List<Integer> newCoins = new ArrayList<>(coins);
                    if (currentCoin != coin && newCoins.contains(coin)) {
                        newCoins.remove((Integer) coin);
                    }
                    total += doCountChange(currentCoin, outstanding, amount, newCoins);
                }
            }
        }
        return total;
    }
}
