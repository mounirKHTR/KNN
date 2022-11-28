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

        pokemon.addPoint(data, point);
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
    public void testGetGroups() {
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
    public void testToString() {
        String test = "Pokemon [nom=Poney, attack=23.0, baseEggSteps=120.0, captureRate=12.23, defense=34.3, experienceGrowth=23.3, hp=23.9, spAttack=13.4, spDefense=23.5, type=feu, type2=eau, speed=239.0, isLegendary=false]";
        Assert.assertEquals(test, point.toString());
    }
}
