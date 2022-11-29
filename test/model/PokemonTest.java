package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PokemonTest {
    DataSet pokemon = new DataSet();

    Pokemon point = new Pokemon();

    @Before
    public void setup(){
        pokemon.loadFromFiles("./src/data/pokemon_suspect1.csv", Pokemon.class);
        String[] tampon = new String[]{"Poney", "23.0", "120.0", "12.23", "34.3", "23.3", "23.9", "13.4", "23.5", "feu", "eau", "239.0"};
        List<String> data = new ArrayList<>(List.of(tampon));

        pokemon.addPokemon(data);
    }

    @Test
    public void testAddPokemon() {
        Assert.assertEquals("Poney", pokemon.getValue(100, pokemon.data.get(0)));
        Assert.assertEquals(23.0, pokemon.getValue(100, pokemon.data.get(1)));
        Assert.assertEquals(120.0, pokemon.getValue(100, pokemon.data.get(2)));
        Assert.assertEquals(12.23, pokemon.getValue(100, pokemon.data.get(3)));
        Assert.assertEquals(34.3, pokemon.getValue(100, pokemon.data.get(4)));
        Assert.assertEquals(23.3, pokemon.getValue(100, pokemon.data.get(5)));
        Assert.assertEquals(23.9, pokemon.getValue(100, pokemon.data.get(6)));
        Assert.assertEquals(13.4, pokemon.getValue(100, pokemon.data.get(7)));
        Assert.assertEquals(23.5, pokemon.getValue(100, pokemon.data.get(8)));
        Assert.assertEquals("feu", pokemon.getValue(100, pokemon.data.get(9)));
        Assert.assertEquals("eau", pokemon.getValue(100, pokemon.data.get(10)));
        Assert.assertEquals(239.0, pokemon.getValue(100, pokemon.data.get(11)));
    }

    @Test
    public void testPokemonGroups() {
        List<String> testGroup = point.getAllGroup();

        Assert.assertEquals("Legendary", testGroup.get(0));
        Assert.assertEquals("Common", testGroup.get(1));

        Assert.assertEquals("Common", point.getGroup());
        Assert.assertFalse(point.isLegendary());

        point.isLegendary = true;

        Assert.assertEquals("Legendary", point.getGroup());
        Assert.assertTrue(point.isLegendary());
    }

    @Test
    public void testPokemonToString() {
        String test = "Pokemon [nom=Poney,\n" +
                " attack=23.0,\n" +
                " baseEggSteps=120.0,\n" +
                " captureRate=12.23,\n" +
                " defense=34.3,\n" +
                " experienceGrowth=23.3,\n" +
                " hp=23.9,\n" +
                " spAttack=13.4,\n" +
                " spDefense=23.5,\n" +
                " type=feu,\n" +
                " type2=eau,\n" +
                " speed=239.0,\n" +
                " isLegendary=false]";
        Assert.assertEquals(pokemon.getLines().get(100).toString(), test);
    }
}
