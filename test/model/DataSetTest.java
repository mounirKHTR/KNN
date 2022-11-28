package model;
import org.junit.*;

import java.util.ArrayList;

public class DataSetTest {

    DataSet pokemon = new DataSet();
    DataSet iris = new DataSet();
    DataSet titanic = new DataSet();

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
    public void testDataSet() {
        Assert.assertEquals(100, pokemon.getNbLines());
        Assert.assertEquals(150, iris.getNbLines());
        Assert.assertEquals(891, titanic.getNbLines());


        pokemon.setName("Pokemon");
        Assert.assertEquals("Pokemon", pokemon.getTitle());


        pokemon.addLine(iris.lines.get(0));
        Assert.assertEquals(pokemon.lines.get(pokemon.lines.size()-1), iris.lines.get(0));

        pokemon.addAllLine(titanic.lines);
        Assert.assertEquals(pokemon.lines.get(pokemon.lines.size()-1), titanic.lines.get(titanic.lines.size()-1));

        pokemon.setLines(iris.lines);
        Assert.assertEquals(pokemon.lines, iris.lines);

        Assert.assertEquals(Pokemon.class, pokemon.getCategory());
        Assert.assertEquals(Titanic.class, titanic.getCategory());
        Assert.assertEquals(Iris.class, iris.getCategory());

    }

    @Test
    public void testPokemon() {
        Assert.assertEquals("Milotic", pokemon.getValue(54, pokemon.data.get(0)));
        Assert.assertEquals(85.0, pokemon.getValue(67, pokemon.data.get(1)));
        Assert.assertEquals(3840.0, pokemon.getValue(59, pokemon.data.get(2)));
        Assert.assertEquals(255.0, pokemon.getValue(20, pokemon.data.get(3)));
        Assert.assertEquals(50.0, pokemon.getValue(30, pokemon.data.get(4)));
        Assert.assertEquals(1000000.0, pokemon.getValue(53, pokemon.data.get(5)));
        Assert.assertEquals(62.0, pokemon.getValue(42, pokemon.data.get(6)));
        Assert.assertEquals(70.0, pokemon.getValue(73, pokemon.data.get(7)));
        Assert.assertEquals(45.0, pokemon.getValue(87, pokemon.data.get(8)));
        Assert.assertNull(pokemon.getValue(61, pokemon.data.get(9)));
        Assert.assertEquals("", pokemon.getValue(90, pokemon.data.get(10)));
        Assert.assertEquals(16.0, pokemon.getValue(99, pokemon.data.get(11)));
        Assert.assertEquals(false, pokemon.getValue(12, pokemon.data.get(12)));
    }

    @Test
    public  void testIris()  {
        Assert.assertEquals(5.6, iris.getValue(69, iris.data.get(0)));
        Assert.assertEquals(2.7, iris.getValue(83, iris.data.get(1)));
        Assert.assertEquals(5.1, iris.getValue(149, iris.data.get(2)));
        Assert.assertEquals(1.3, iris.getValue(99, iris.data.get(3)));
        Assert.assertEquals("Setosa", iris.getValue(31, iris.data.get(4)));
    }

    @Test
    public void testTitanic() {
        Assert.assertEquals(811.0, titanic.getValue(810, titanic.data.get(0)));
        Assert.assertEquals(0.0, titanic.getValue(734, titanic.data.get(1)));
        Assert.assertEquals(1.0, titanic.getValue(268, titanic.data.get(2)));
        Assert.assertEquals("Pinsky, Mrs. (Rosa)", titanic.getValue(190, titanic.data.get(3)));
        Assert.assertEquals("female", titanic.getValue(399, titanic.data.get(4)));
        Assert.assertEquals(32.0, titanic.getValue(519, titanic.data.get(5)));
        Assert.assertEquals(0.0, titanic.getValue(632, titanic.data.get(6)));
        Assert.assertEquals(0.0, titanic.getValue(687, titanic.data.get(7)));
        Assert.assertEquals("113784", titanic.getValue(339, titanic.data.get(8)));
        Assert.assertEquals(135.6333, titanic.getValue(269, titanic.data.get(9)));
        Assert.assertEquals("", titanic.getValue(229, titanic.data.get(10)));
        Assert.assertEquals("S", titanic.getValue(232, titanic.data.get(11)));
    }


}
