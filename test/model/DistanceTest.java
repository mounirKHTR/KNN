package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DistanceTest {
    DataSet pokemon = new DataSet();
    DataSet iris = new DataSet();
    DataSet titanic = new DataSet();
    Distance distance = new Distance();

    @Before
    public void setup() {
        pokemon.loadFromFiles("./src/data/pokemon_suspect1.csv", Pokemon.class);
        iris.loadFromFiles("./src/data/iris.csv", Iris.class);
        titanic.loadFromFiles("./src/data/titanic.csv", Titanic.class);
    }

    @After
    public void clear() {
        pokemon.clear();
        iris.clear();
        titanic.clear();
    }

    @Test
    public void testEuclidian() {
        Assert.assertEquals(1.3533333333333335, distance.euclidianDistanceBetween(pokemon.lines.get(2), pokemon.lines.get(34), pokemon.data), 0.0);
    }

    @Test
    public void testManhattan() {
        Assert.assertEquals(1.3533333333333335, distance.manhattanDistanceBetween(pokemon.lines.get(2), pokemon.lines.get(34), pokemon.data), 0.0);
    }
}
