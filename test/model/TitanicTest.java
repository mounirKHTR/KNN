package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TitanicTest {

    DataSet titanic = new DataSet();

    Titanic point = new Titanic();

    @Before
    public void setup(){
        titanic.loadFromFiles("./src/data/titanic.csv", Titanic.class);
        String[] tampon = new String[]{"3.0", "1.0", "3.2", "Jean", "Male", "24.0", "3.0", "2.0", "ticket", "23.0", "C 23"};
        List<String> data = new ArrayList<>(List.of(tampon));

        titanic.addTitanic(data);
    }

    @Test
    public void testAddTitanicPoint() {
        Assert.assertEquals(3.0,titanic.getValue(891,titanic.data.get(0)));
        Assert.assertEquals(1.0,titanic.getValue(891,titanic.data.get(1)));
        Assert.assertEquals(3.2,titanic.getValue(891,titanic.data.get(2)));
        Assert.assertEquals("Jean",titanic.getValue(891,titanic.data.get(3)));
        Assert.assertEquals("Male",titanic.getValue(891,titanic.data.get(4)));
        Assert.assertEquals(24.0,titanic.getValue(891,titanic.data.get(5)));
        Assert.assertEquals(3.0,titanic.getValue(891,titanic.data.get(6)));
        Assert.assertEquals(2.0,titanic.getValue(891,titanic.data.get(7)));
        Assert.assertEquals("ticket",titanic.getValue(891,titanic.data.get(8)));
        Assert.assertEquals(23.0,titanic.getValue(891,titanic.data.get(9)));
        Assert.assertEquals("C 23",titanic.getValue(891,titanic.data.get(10)));
    }

    @Test
    public void testTitanicGroups() {
        Titanic point = new Titanic();
        List<String> groups = point.getAllGroup();

        Assert.assertEquals("S", groups.get(0));
        Assert.assertEquals("C", groups.get(1));
        Assert.assertEquals("Q", groups.get(2));

        Assert.assertEquals(""+null, point.getGroup());
    }

    @Test
    public void testTitanicToString() {
        String test = "Titanic{PassengerId=3.0, Survived=1.0, Pclass=3.2, Name='Jean', Sex='Male', Age=24.0, SibSp=3.0, Parch=2.0, Ticket='ticket', Fare=23.0, Cabin='5.0', Embarked=null}";
        Assert.assertEquals(test, point.toString());
    }
}