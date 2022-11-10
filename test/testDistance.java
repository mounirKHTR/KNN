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

}
