import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestIris {
	
	DistanceEuclidienne dE;
	DistanceManhattan dM;
	List<IColumn> colI;
	DatasetIris dtI;
	
	@BeforeEach
	void init() {
		dE = new DistanceEuclidienne() ;
		dM = new DistanceManhattan();
		colI = new ArrayList<IColumn>();
		dtI = new DatasetIris(dE, colI);
		dtI.loadFromFile("iris.csv");
	}
	
	@Test
	void testChargementIris() {
		assertEquals(150,dtI.getNbLines());
		assertEquals(5.1,dtI.getSet().get(0).getSepalLength());
		assertEquals(3.5,dtI.getSet().get(0).getSepalWidth());
		assertEquals(1.4,dtI.getSet().get(0).getPetalLength());
		assertEquals(0.2,dtI.getSet().get(0).getPetalWidth());
	}
	
	@Test
	void testgetNormalizingValue() {
		assertEquals(dtI.getNorm().get(0).getMax()+ "|" + dtI.getNorm().get(0).getMin(),"7.9|4.3");
		assertEquals(dtI.getNorm().get(1).getMax()+ "|" + dtI.getNorm().get(1).getMin(),"4.4|2.0");
		assertEquals(dtI.getNorm().get(2).getMax()+ "|" + dtI.getNorm().get(2).getMin(),"6.9|1.0");
		assertEquals(dtI.getNorm().get(3).getMax()+ "|" + dtI.getNorm().get(3).getMin(),"2.5|0.1");
	}
	
	@Test
	void testNormalize() {
		System.out.println(dtI.getNorm().get(0).getMax());
		System.out.println(dtI.getNorm().get(0).getMin());
		System.out.println(dtI.getSet().get(0).getSepalLength());
		System.out.println(dtI.getNorm().get(0).normalize(dtI.getSet().get(0).getSepalLength()));
		System.out.println((5.1-4.3)/(3.6));
		assertEquals(Math.round(dtI.getNorm().get(0).normalize(dtI.getSet().get(0).getSepalLength())),0.22);
	}

	@Test
	void testDistanceEuclIris() {
		assertEquals(Math.ceil(dE.distanceBetween(dtI,dtI.getSet().get(0),dtI.getSet().get(1),dtI.getNorm().get(0))*1000)/1000,0.539);
		
		assertEquals(Math.ceil(dE.distanceBetween(dtI,dtI.getSet().get(0),dtI.getSet().get(1),dtI.getNorm().get(0))*1000)/1000,0.539);
	}
	
	@Test
	void testDistanceManhIris() {	
		assertEquals(Math.ceil(dM.distanceBetween(dtI,dtI.getSet().get(0),dtI.getSet().get(1),dtI.getNorm().get(0))*100)/100,0.7);
	}
	
	

}
