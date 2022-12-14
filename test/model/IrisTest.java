package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IrisTest {
    protected DataSet iris = new DataSet();

    Iris point;

    @Before
    public void setup(){
        iris.loadFromFiles("./src/data/iris.csv", Iris.class);
         String[] tampon ={"23.4", "22.4", "56.1", "30.0"};
        List<String> data = new ArrayList<>(List.of(tampon));

        iris.addIris(data);
        point = (Iris) iris.getLines().get(150);
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

        point.setGroup("Setosa");

        Assert.assertEquals("Setosa", point.getGroup());
    }

    @Test
    public void testIrisToString() {
        String test = "Iris{sepalLength=23.4,\n sepalWidth=22.4,\n petalLength=56.1,\n petalWidth=30.0,\n variety='null'}";
        Assert.assertEquals(iris.getLines().get(150).toString(), test);
    }

    @Test
    public void testIrisClassified() {
        Assert.assertFalse(point.getClassified());
        point.setClassified(true);
        Assert.assertTrue(point.getClassified());
    }
}
