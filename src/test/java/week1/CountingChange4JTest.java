package week1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class CountingChange4JTest {

    private CountingChange4J countingChange;

    @Before
    public void setup() {
        countingChange = new CountingChange4J();
    }

    @Test
    public void amountFourWithOneCoin() {
        Assert.assertEquals(1, countingChange.countChange(4, Arrays.asList(1)));
    }

    @Test
    public void amountFourWithFourCoins() {
        Assert.assertEquals(5, countingChange.countChange(4, Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void amount10WithOneCoin() {
        Assert.assertEquals(1, countingChange.countChange(10, Arrays.asList(1)));
    }

    @Test
    public void amount10WithOneCoinOf2And5() {
        Assert.assertEquals(2, countingChange.countChange(10, Arrays.asList(2, 5)));
    }

    @Test
    public void amount8WithOneCoinOf2And3() {
        Assert.assertEquals(2, countingChange.countChange(10, Arrays.asList(2, 3)));
    }

    @Test
    public void amount100WithOneCoinOf1And50() {
        Assert.assertEquals(3, countingChange.countChange(100, Arrays.asList(1, 50)));
    }

    @Test
    public void amount100WithOneCoinOf5And50And75() {
        Assert.assertEquals(4, countingChange.countChange(100, Arrays.asList(5, 50, 75)));
    }

    @Test
    public void amount105WithOneCoinOf5And50And75() {
        Assert.assertEquals(4, countingChange.countChange(105, Arrays.asList(5, 50, 75)));
    }

    @Test
    public void amountFourWithOneTwoCoins() {
        Assert.assertEquals(3, countingChange.countChange(4, Arrays.asList(1, 2)));
    }

    @Test
    public void amountFourWithThreeCoins() {
        Assert.assertEquals(4, countingChange.countChange(4, Arrays.asList(1, 2, 3)));
    }

    @Test
    public void amountFiveWithOneTwoCoins() {
        Assert.assertEquals(3, countingChange.countChange(5, Arrays.asList(1, 2)));
    }

    @Test
    public void amountSixWithOneTwoCoins() {
        Assert.assertEquals(4, countingChange.countChange(6, Arrays.asList(1, 2)));
    }

    @Test
    public void amountFiveWithOneTwoThreeCoins() {
        Assert.assertEquals(5, countingChange.countChange(5, Arrays.asList(1, 2, 3)));
    }

    @Test
    public void multiplesOf5() {
        Assert.assertEquals(1022, countingChange.countChange(300, Arrays.asList(5, 10, 20, 50, 100, 200, 500)));
    }

    @Test
    public void multiplesOf5Unsorted() {
        Assert.assertEquals(1022, countingChange.countChange(300, Arrays.asList(5, 10, 20, 50, 500, 200, 100)));
    }
}
