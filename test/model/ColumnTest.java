package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ColumnTest {

    DataSet dataTest = new DataSet();
    Column col = new Column("colTest", "typeTest", dataTest);
    DataSet titanic = new DataSet();
    Titanic point;

    String test = "Column [Name=colTest, type=typeTest, Normalizer=null, isNormalizable=false]";

    @Before
    public void setup(){
        titanic.loadFromFiles("./src/data/titanic.csv", Titanic.class);
        String[] tampon = new String[]{"3.0", "1.0", "3.2", "Jean", "Male", "24.0", "3.0", "2.0", "ticket", "23.0", "5.0"};

        List<String> data = new ArrayList<>(List.of(tampon));

        titanic.addTitanic(data);
        point = (Titanic) titanic.getLines().get(891);

    }

    @Test
    public void testColumn() {
        Assert.assertNull(col.getNormalizer());

        Assert.assertEquals(3.2, point.getValue(titanic.data.get(2)));

        Assert.assertEquals(1.1, titanic.data.get(2).getNormalizedValue(point));
        Assert.assertEquals(3.2, titanic.data.get(2).getDenormalizedValue(point));

        Assert.assertEquals(test, col.toString());
    }

}
