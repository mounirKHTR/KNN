import com.opencsv.bean.CsvBindByName;

public class Iris implements IPoint {
	
	@CsvBindByName(column = "sepal.length")
	private double sepalLength;
	@CsvBindByName(column = "sepal.width")
	private double sepalWidth;
	@CsvBindByName(column = "petal.length")
	private double petalLength;
	@CsvBindByName(column = "petal.width")
	private double petalWidth;
	@CsvBindByName(column = "variety")
	private double variety;

	@Override
	public double getNormalizedValue(IColumn col) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Iris getValue(IColumn col) {
		return null;
		
	}

}
