package Interface;

import java.util.List;

import model.Column;

public interface IDistance {
	
	public double ManhattanDistanceBetween(IPoint i1,IPoint i2, List<Column> col);
	public double EuclidianDistanceBetween(IPoint i1,IPoint i2, List<Column> col);
	

}
