Counting Change
===============

Exercise taken from the [Functional Programming Principles in Scala](https://class.coursera.org/progfun-004)  **Counting Change** Recursion problem  by Martin Odersky.

The problem is as follows:

*Write a recursive function that counts how many different ways you can make change for an amount, given a list of coin denominations.*

*For example, there are 3 ways to give change for 4 if you have coins with denomination 1 and 2: 1+1+1+1, 1+1+2, 2+2.*

*Do this exercise by implementing the countChange function in Main.scala. This function takes an amount to change, and a list of unique denominations for the coins.*

*Its signature is as follows:*

    **def countChange(money: Int, coins: List[Int]): Int**

*Once again, you can make use of functions isEmpty, head and tail on the list of integers coins.*

**Hint:** *Think of the degenerate cases. How many ways can you give change for 0 CHF? How many ways can you give change for >0 CHF, if you have no coins?*

The code in this implementation has been done in Java to compare with Scala:

* the effort required
* the code expressiveness when dealing with recursion algorithms
* the legibility code legibility