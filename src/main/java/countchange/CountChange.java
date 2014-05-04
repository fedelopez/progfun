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
        int total = 0;
        List<Integer> visitedCoins = new ArrayList<>(coins);
        for (int coin : coins) {
            total += doCountChange(total, coin, 0, amount, visitedCoins);
            if (visitedCoins.size() > 0) visitedCoins.remove(0);
        }
        return total;
    }

    private int doCountChange(int total, int coin, int currentAmount, int amount, List<Integer> coins) {
        int possibleAmount = coin + currentAmount;
        if (possibleAmount > amount) return 0;
        if (possibleAmount == amount) return 1;

        else if (possibleAmount < amount) {
            for (Integer currentCoin : coins) {
                if (currentCoin + currentAmount <= amount) {
                    total += doCountChange(total, currentCoin, possibleAmount, amount, coins);
                }
            }
        }
        return total;
    }
}
