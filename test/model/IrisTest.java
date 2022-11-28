package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IrisTest {
    DataSet iris = new DataSet();

    Iris point = new Iris();

    @Before
    public void setup(){
        iris.loadFromFiles("./src/data/iris.csv", Iris.class);
        String[] tampon = new String[]{"23.4", "22.4", "56.1", "30.0"};
        List<String> data = new ArrayList<>(List.of(tampon));

        iris.addPoint(data, point);
    }

    @Test
    public void testIrisAdd() {
        Assert.assertEquals(iris.getValue(150, iris.data.get(0)), point.getSepalLength());
        Assert.assertEquals(iris.getValue(150, iris.data.get(1)), point.getSepalWidth());
        Assert.assertEquals(iris.getValue(150, iris.data.get(2)), point.getPetalLength());
        Assert.assertEquals(iris.getValue(150, iris.data.get(3)), point.getPetalWidth());
        Assert.assertNull(point.getVariety());
    }

    @Test
    public void testIrisGroups() {
        List<String> test = point.getAllGroup();

        Assert.assertEquals("Setosa", test.get(0));
        Assert.assertEquals("Virginica", test.get(1));
        Assert.assertEquals("Versicolor", test.get(2));

        Assert.assertNull(point.getGroup());
    }

    @Test
    public void testIrisToString() {
        String test = "Iris{sepalLength=23.4, sepalWidth=22.4, petalLength=56.1, petalWidth=30.0, variety='null'}";
        Assert.assertEquals(test, point.toString());
    }
}
