import model.Iris;
import model.Pokemon;

public interface IDistance {
	
	public double distanceBetween(Pokemon p1, Pokemon p2);
	
	public double distanceBetween(DatasetIris dt, Iris p1, Iris p2,Number_Normalizer norm);
	
}
