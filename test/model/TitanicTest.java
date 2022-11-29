package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TitanicTest {

    DataSet titanic = new DataSet();

    Titanic point;

    @Before
    public void setup(){
        titanic.loadFromFiles("./src/data/titanic.csv", Titanic.class);
        String[] tampon = new String[]{"3.0", "1.0", "3.2", "Jean", "Male", "24.0", "3.0", "2.0", "ticket", "23.0", "C 23"};
        List<String> data = new ArrayList<>(List.of(tampon));

        titanic.addTitanic(data);
        point = (Titanic) titanic.getLines().get(891);
    }

    @Test
    public void testAddTitanicPoint() {
        Assert.assertEquals(titanic.getValue(891, titanic.data.get(0)), point.getPassengerId());
        Assert.assertEquals(titanic.getValue(891, titanic.data.get(1)), point.getSurvived());
        Assert.assertEquals(titanic.getValue(891, titanic.data.get(2)), point.getPclass());
        Assert.assertEquals(titanic.getValue(891, titanic.data.get(3)), point.getName());
        Assert.assertEquals(titanic.getValue(891, titanic.data.get(4)), point.getSex());
        Assert.assertEquals(titanic.getValue(891, titanic.data.get(5)), point.getAge());
        Assert.assertEquals(titanic.getValue(891, titanic.data.get(6)), point.getSibSp());
        Assert.assertEquals(titanic.getValue(891, titanic.data.get(7)), point.getParch());
        Assert.assertEquals(titanic.getValue(891, titanic.data.get(8)), point.getTicket());
        Assert.assertEquals(titanic.getValue(891, titanic.data.get(9)), point.getFare());
        Assert.assertEquals(titanic.getValue(891, titanic.data.get(10)), point.getCabin());
    }

    @Test
    public void testTitanicGroups() {
        Titanic point = new Titanic();
        List<String> groups = point.getAllGroup();

        Assert.assertEquals("S", groups.get(0));
        Assert.assertEquals("C", groups.get(1));
        Assert.assertEquals("Q", groups.get(2));

        Assert.assertNull(point.getGroup());
    }

    @Test
    public void testTitanicToString() {
        String test = "Titanic{PassengerId=3.0,\n" +
                " Survived=1.0,\n" +
                " Pclass=3.2,\n" +
                " Name='Jean',\n" +
                " Sex='Male',\n" +
                " Age=24.0,\n" +
                " SibSp=3.0,\n" +
                " Parch=2.0,\n" +
                " Ticket='ticket',\n" +
                " Fare=23.0,\n" +
                " Cabin='C 23',\n" +
                " Embarked=null}";
        Assert.assertEquals(titanic.getLines().get(891).toString(), test);
    }
}