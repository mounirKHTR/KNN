package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DistanceTest {
    DataSet pokemon = new DataSet();
    DataSet iris = new DataSet();
    DataSet titanic = new DataSet();
    Distance distance = new Distance();

    @Before
    public void setup() throws IOException {
        pokemon.loadFromfiles("./src/data/pokemon_suspect1.csv", Pokemon.class);
        iris.loadFromfiles("./src/data/iris.csv", Iris.class);
        titanic.loadFromfiles("./src/data/titanic.csv", Titanic.class);
    }

    @After
    public void clear() {
        pokemon.clear();
        iris.clear();
        titanic.clear();
    }

    @Test
    public void testEuclidian() {
        Assert.assertEquals(0.0, distance.EuclidianDistanceBetween(pokemon.lines.get(2), pokemon.lines.get(34), pokemon.Data), 0.0);
    }

    @Test
    public void testManhattan() {
        Assert.assertEquals(0.0, distance.ManhattanDistanceBetween(pokemon.lines.get(2), pokemon.lines.get(34), pokemon.Data), 0.0);
    }
}
