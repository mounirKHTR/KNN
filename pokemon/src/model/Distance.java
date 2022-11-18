package model;
import java.util.List;

import Interface.IDistance;
import Interface.IPoint;

public class Distance implements IDistance {

	public double EuclidianDistanceBetween(IPoint i1, IPoint i2, List<Column> col) {
		double rsl = 0.0;
		for(Column icol: col) {
			rsl += Math.abs(i1.getNormalized(icol) - i2.getNormalized(icol) + 0.0);
		}
		return rsl;
	}
	
	public double ManhattanDistanceBetween(IPoint i1, IPoint i2,List<Column> col) {
		double rsl = 0.0;
		for(Column icol: col) {
			rsl += Math.sqrt(Math.pow(i1.getNormalized(icol) - i2.getNormalized(icol) + 0.0, 2));
		}
		return rsl;
	}

}