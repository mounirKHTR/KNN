package Interface;
import java.util.List;

import model.Column;

public interface IDistance {
	
	public double manhattanDistanceBetween(IPoint i1, IPoint i2, List<Column> col);
	public double euclidianDistanceBetween(IPoint i1, IPoint i2, List<Column> col);
	

}
