package model;
import Interface.IPoint;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DataSetTest {
    @Test
    public void testDataSet() throws IOException {
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
        Assert.assertEquals("Milotic", pokemon.getValue(54, pokemon.Data.get(0)));
        Assert.assertEquals(85, pokemon.getValue(67, pokemon.Data.get(1)));
        Assert.assertEquals(3840, pokemon.getValue(59, pokemon.Data.get(2)));
        Assert.assertEquals(255.0, pokemon.getValue(20, pokemon.Data.get(3)));
        Assert.assertEquals(50, pokemon.getValue(30, pokemon.Data.get(4)));
        Assert.assertEquals(1000000, pokemon.getValue(53, pokemon.Data.get(5)));
        Assert.assertEquals(62, pokemon.getValue(42, pokemon.Data.get(6)));
        Assert.assertEquals(70, pokemon.getValue(73, pokemon.Data.get(7)));
        Assert.assertEquals(45, pokemon.getValue(87, pokemon.Data.get(8)));
        Assert.assertNull(pokemon.getValue(61, pokemon.Data.get(9)));
        Assert.assertEquals("", pokemon.getValue(90, pokemon.Data.get(10)));
        Assert.assertEquals(16.0, pokemon.getValue(99, pokemon.Data.get(11)));
        Assert.assertEquals(false, pokemon.getValue(12, pokemon.Data.get(12)));
    }

    @Test
    public  void testIris() throws IOException {
        DataSet iris = new DataSet();
        iris.loadFromfiles("./src/data/iris.csv", Iris.class);
        Assert.assertEquals(5.6, iris.getValue(69, iris.Data.get(0)));
        Assert.assertEquals(2.7, iris.getValue(83, iris.Data.get(1)));
        Assert.assertEquals(5.1, iris.getValue(149, iris.Data.get(2)));
        Assert.assertEquals(1.3, iris.getValue(99, iris.Data.get(3)));
        Assert.assertEquals("Setosa", iris.getValue(31, iris.Data.get(4)));
    }

    @Test
    public void testTitanic() throws IOException {
        DataSet titanic = new DataSet();
        titanic.loadFromfiles("./src/data/titanic.csv", Titanic.class);
        Assert.assertEquals(811, titanic.getValue(810, titanic.Data.get(0)));
        Assert.assertEquals(0, titanic.getValue(734, titanic.Data.get(1)));
        Assert.assertEquals(1, titanic.getValue(268, titanic.Data.get(2)));
        Assert.assertEquals("Pinsky, Mrs. (Rosa)", titanic.getValue(190, titanic.Data.get(3)));
        Assert.assertEquals("female", titanic.getValue(399, titanic.Data.get(4)));
        Assert.assertEquals(32.0, titanic.getValue(519, titanic.Data.get(5)));
        Assert.assertEquals(0, titanic.getValue(632, titanic.Data.get(6)));
        Assert.assertEquals(0, titanic.getValue(687, titanic.Data.get(7)));
        Assert.assertEquals("113784", titanic.getValue(339, titanic.Data.get(8)));
        Assert.assertEquals(135.6333, titanic.getValue(269, titanic.Data.get(9)));
        Assert.assertEquals("", titanic.getValue(229, titanic.Data.get(10)));
        Assert.assertEquals('S', titanic.getValue(232, titanic.Data.get(11)));
    }


}
