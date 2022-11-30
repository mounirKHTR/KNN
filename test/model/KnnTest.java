package model;

import Interface.IPoint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class KnnTest {
    DataSet pokemon = new DataSet();
    DataSet iris = new DataSet();
    DataSet titanic = new DataSet();
    MethodeKnn knn = new MethodeKnn();

    @Before
    public void setup() {
        pokemon.loadFromFiles("./src/data/pokemon_suspect1.csv", Pokemon.class);
        iris.loadFromFiles("./src/data/iris.csv", Iris.class);
        titanic.loadFromFiles("./src/data/titanic.csv", Titanic.class);
    }

    @Test
    public void testMethodeKnnEuclidian() {
        Map<Double, IPoint> test = knn.sortEuclidian(iris.getLines().get(0), iris.getLines(), iris.getData());
        Assert.assertEquals(123, test.size());

        List<IPoint> testNN = knn.getNearestNeigbhour(test, 3);

        Assert.assertEquals(iris.getLines().get(27), testNN.get(0));
        Assert.assertEquals(iris.getLines().get(17), testNN.get(1));
        Assert.assertEquals(iris.getLines().get(39), testNN.get(2));

        Assert.assertEquals(3, testNN.size());
    }

    @Test
    public void testMethodeKnnManhattan() {
        Map<Double, IPoint> test = knn.sortManhattan(iris.getLines().get(0), iris.getLines(), iris.getData());
        Assert.assertEquals(123, test.size());

        List<IPoint> testNN = knn.getNearestNeigbhour(test, 3);

        Assert.assertEquals(iris.getLines().get(27), testNN.get(0));
        Assert.assertEquals(iris.getLines().get(17), testNN.get(1));
        Assert.assertEquals(iris.getLines().get(39), testNN.get(2));

        Assert.assertEquals(3, testNN.size());
    }
}
