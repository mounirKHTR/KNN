import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testDistance {
	
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
	}

	@Test
	void testDistanceEuclIris() {
		assertEquals(Math.ceil(dE.distanceBetween(dtI.getSet().get(0),dtI.getSet().get(1))*1000)/1000,0.539);
	}
	
	@Test
	void testDistanceManhIris() {
		
		assertEquals(Math.ceil(dM.distanceBetween(dtI.getSet().get(0),dtI.getSet().get(1))*100)/100,0.7);
	}
	
	@Test
	void testgetNormalizingValue() {
		assertEquals(dtI.getNorm().get(0).getMax()+ "|" + dtI.getNorm().get(0).getMin(),"7.9|4.3");
		assertEquals(dtI.getNorm().get(1).getMax()+ "|" + dtI.getNorm().get(1).getMin(),"4.4|2.0");
		assertEquals(dtI.getNorm().get(2).getMax()+ "|" + dtI.getNorm().get(2).getMin(),"6.9|1.0");
		assertEquals(dtI.getNorm().get(3).getMax()+ "|" + dtI.getNorm().get(3).getMin(),"2.5|0.1");
	}

}
