package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RobustesseTest {

    Robustesse robustesse;
    DataSet titanic = new DataSet();

    @Before
    public void setup() {
        titanic.loadFromFiles("./src/data/titanic.csv", Titanic.class);
        robustesse = new Robustesse<>(titanic, 3);
    }

    @Test
    public void testRobustesse() {
        double test = robustesse.robustesse();
        Assert.assertTrue(test <= 100.0);
        Assert.assertTrue(test >= 60);
    }
}
