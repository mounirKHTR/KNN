package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PokemonTest {
    DataSet pokemon = new DataSet();

    Pokemon point;

    @Before
    public void setup(){
        pokemon.loadFromFiles("./src/data/pokemon_suspect1.csv", Pokemon.class);
        String[] tampon = new String[]{"Poney", "23.0", "120.0", "12.23", "34.3", "23.3", "23.9", "13.4", "23.5", "feu", "eau", "239.0"};
        List<String> data = new ArrayList<>(List.of(tampon));

        pokemon.addPokemon(data);
        point = (Pokemon) pokemon.getLines().get(100);
    }

    @Test
    public void testAddPokemon() {
        Assert.assertEquals(pokemon.getValue(100, pokemon.data.get(0)), point.getName());
        Assert.assertEquals(pokemon.getValue(100, pokemon.data.get(1)), point.getAttack());
        Assert.assertEquals(pokemon.getValue(100, pokemon.data.get(2)), point.getBaseEggSteps());
        Assert.assertEquals(pokemon.getValue(100, pokemon.data.get(3)), point.getCaptureRate());
        Assert.assertEquals(pokemon.getValue(100, pokemon.data.get(4)), point.getDefense());
        Assert.assertEquals(pokemon.getValue(100, pokemon.data.get(5)), point.getExperienceGrowth());
        Assert.assertEquals(pokemon.getValue(100, pokemon.data.get(6)), point.getHp());
        Assert.assertEquals(pokemon.getValue(100, pokemon.data.get(7)), point.getSpAttack());
        Assert.assertEquals(pokemon.getValue(100, pokemon.data.get(8)), point.getSpDefense());
        Assert.assertEquals(pokemon.getValue(100, pokemon.data.get(9)), point.getType());
        Assert.assertEquals(pokemon.getValue(100, pokemon.data.get(10)), point.getType2());
        Assert.assertEquals(pokemon.getValue(100, pokemon.data.get(11)), point.getSpeed());
    }

    @Test
    public void testPokemonGroups() {
        List<String> testGroup = point.getAllGroup();

        Assert.assertEquals("Legendary", testGroup.get(0));
        Assert.assertEquals("Common", testGroup.get(1));

        Assert.assertNull(point.getGroup());
        Assert.assertFalse(point.isLegendary());

        point.isLegendary = true;

        Assert.assertNull(point.getGroup());
        Assert.assertTrue(point.isLegendary());

        point.setGroup("Common");
        point.classified = true;

        Assert.assertEquals("Common", point.getGroup());
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

    @Test
    public void testPokemonClassified() {
        Assert.assertFalse(point.getClassified());
        point.setClassified(true);
        Assert.assertTrue(point.getClassified());
    }
}
