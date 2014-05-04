package countchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class CountChangeTest {

    private CountChange countChange;

    @Before
    public void setup() {
        countChange = new CountChange();
    }

    @Test
    public void amountFourWithOneCoin() {
        Assert.assertEquals(1, countChange.countChange(4, Arrays.asList(1)));
    }

    @Test
    public void amountFourWithOneTwoCoins() {
        Assert.assertEquals(3, countChange.countChange(4, Arrays.asList(1, 2)));
    }

    @Test
    public void amountFiveWithOneTwoCoins() {
        Assert.assertEquals(3, countChange.countChange(5, Arrays.asList(1, 2)));
    }

    @Test
    public void amountSixWithOneTwoCoins() {
        Assert.assertEquals(4, countChange.countChange(6, Arrays.asList(1, 2)));
    }


    @Test
    public void amountFiveWithOneTwoThreeCoins() {
        Assert.assertEquals(5, countChange.countChange(5, Arrays.asList(1, 2, 3)));
    }

    @Test
    public void multiplesOf5() {
        Assert.assertEquals(1022, countChange.countChange(300, Arrays.asList(5, 10, 20, 50, 100, 200, 500)));
    }
}
