package model;
import Interface.IPoint;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DataSetTest {
    @Test
    public void testDataSetNumberLines() throws IOException {
        DataSet pokemon = new DataSet();
        DataSet iris = new DataSet();
        DataSet titanic = new DataSet();
        pokemon.loadFromfiles("./src/data/pokemon_suspect1.csv", Pokemon.class);
        iris.loadFromfiles("./src/data/iris.csv", Iris.class);
        titanic.loadFromfiles("./src/data/titanic.csv", Titanic.class);
		Assert.assertEquals(100, pokemon.getNbLines());
        Assert.assertEquals(150, iris.getNbLines());
        Assert.assertEquals(891, titanic.getNbLines());
    }

    @Test
    public void testPokemon() throws IOException {
        DataSet pokemon = new DataSet();
        pokemon.loadFromfiles("./src/data/pokemon_suspect1.csv", Pokemon.class);
        Assert.assertTrue(pokemon.getValue(54, pokemon.Data.get(0)).equals("Milotic"));
        Assert.assertTrue(pokemon.getValue(67, pokemon.Data.get(1)).equals(85));
        Assert.assertTrue(pokemon.getValue(59, pokemon.Data.get(2)).equals(3840));
        Assert.assertTrue(pokemon.getValue(20, pokemon.Data.get(3)).equals(255.0));
        Assert.assertTrue(pokemon.getValue(30, pokemon.Data.get(4)).equals(50));
        Assert.assertTrue(pokemon.getValue(53, pokemon.Data.get(5)).equals(1000000));
        Assert.assertTrue(pokemon.getValue(42, pokemon.Data.get(6)).equals(62));
        Assert.assertTrue(pokemon.getValue(73, pokemon.Data.get(7)).equals(70));
        Assert.assertTrue(pokemon.getValue(87, pokemon.Data.get(8)).equals(45));
        Assert.assertTrue(pokemon.getValue(61, pokemon.Data.get(9)) == (null));
        Assert.assertTrue(pokemon.getValue(90, pokemon.Data.get(10)).equals(""));
        Assert.assertTrue(pokemon.getValue(99, pokemon.Data.get(11)).equals(16.0));
        Assert.assertTrue(pokemon.getValue(12, pokemon.Data.get(12)).equals(false));
    }

    @Test
    public  void testIris() throws IOException {
        DataSet iris = new DataSet();
        iris.loadFromfiles("./src/data/iris.csv", Iris.class);
        Assert.assertTrue(iris.getValue(69, iris.Data.get(0)).equals(5.6));
        Assert.assertTrue(iris.getValue(83, iris.Data.get(1)).equals(2.7));
        Assert.assertTrue(iris.getValue(149, iris.Data.get(2)).equals(5.1));
        Assert.assertTrue(iris.getValue(99, iris.Data.get(3)).equals(1.3));
        Assert.assertTrue(iris.getValue(31, iris.Data.get(4)).equals("Setosa"));
    }

    @Test
    public void testTitanic() throws IOException {
        DataSet titanic = new DataSet();
        titanic.loadFromfiles("./src/data/titanic.csv", Titanic.class);
        Assert.assertTrue(titanic.getValue(810, titanic.Data.get(0)).equals(811));
        Assert.assertTrue(titanic.getValue(734, titanic.Data.get(1)).equals(0));
        Assert.assertTrue(titanic.getValue(268, titanic.Data.get(2)).equals(1));
        Assert.assertTrue(titanic.getValue(190, titanic.Data.get(3)).equals("Pinsky, Mrs. (Rosa)"));
        Assert.assertTrue(titanic.getValue(399, titanic.Data.get(4)).equals("female"));
        Assert.assertTrue(titanic.getValue(519, titanic.Data.get(5)).equals(32.0));
        Assert.assertTrue(titanic.getValue(632, titanic.Data.get(6)).equals(0));
        Assert.assertTrue(titanic.getValue(687, titanic.Data.get(7)).equals(0));
        Assert.assertTrue(titanic.getValue(339, titanic.Data.get(8)).equals("113784"));
        Assert.assertTrue(titanic.getValue(269, titanic.Data.get(9)).equals(135.6333));
        Assert.assertTrue(titanic.getValue(229, titanic.Data.get(10)).equals(""));
        Assert.assertTrue(titanic.getValue(232, titanic.Data.get(11)).equals('S'));
    }


}
