package week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Exercise taken from the Counting Change Recursion problem  by Martin Odersky.
 * <p/>
 * Write a recursive function that counts how many different ways you can make change for an amount, given a list of coin denominations.
 * <p/>
 * For example, there are 3 ways to give change for 4 if you have coins with denomination 1 and 2: 1+1+1+1, 1+1+2, 2+2.
 * <p/>
 * The code in this implementation has been done in Java to compare it with the Scala one and evaluate:
 * <p/>
 * - the effort required
 * - the code expressiveness when dealing with recursion algorithms
 * - the legibility code legibility
 */
public class CountingChange4J {

    public static void main(String[] args) {
        System.out.println("Change for $4 with $1 and $2 is: " + new CountingChange4J().countChange(4, Arrays.asList(1, 2)));
    }

    public int countChange(int amount, List<Integer> coins) {
        Collections.sort(coins);
        return doCountChange(coins.get(0), 0, amount, coins);
    }

    private int doCountChange(int coin, int currentAmount, int amount, List<Integer> coins) {
        int total = 0;
        if (currentAmount == amount) {
            total = 1;
        } else if (currentAmount + coin <= amount) {
            for (Integer currentCoin : coins) {
                List<Integer> newCoins = new ArrayList<>(coins);
                int fromIndex = newCoins.indexOf(currentCoin);
                if (currentCoin != coin && fromIndex > -1) {
                    newCoins = newCoins.subList(fromIndex, newCoins.size());
                }
                int outstanding = currentCoin + currentAmount;
                total += doCountChange(currentCoin, outstanding, amount, newCoins);
            }
        }
        return total;
    }
}
