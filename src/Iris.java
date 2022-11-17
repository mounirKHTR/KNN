import com.opencsv.bean.CsvBindByName;

import Interface.IPoint;

public class Iris extends IPoint {
	
	@CsvBindByName(column = "sepal.length")
	private double sepalLength;
	@CsvBindByName(column = "sepal.width")
	private double sepalWidth;
	@CsvBindByName(column = "petal.length")
	private double petalLength;
	@CsvBindByName(column = "petal.width")
	private double petalWidth;
	@CsvBindByName(column = "variety")
	private String variety;

	public double getNormalizedValue(Column col) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Object getValue(Column col) {
		return null;
		
	}

	/**
	 * @return the sepalLength
	 */
	public double getSepalLength() {
		return sepalLength;
	}

	/**
	 * @return the sepalWidth
	 */
	public double getSepalWidth() {
		return sepalWidth;
	}

	/**
	 * @return the petalLength
	 */
	public double getPetalLength() {
		return petalLength;
	}

	/**
	 * @return the petalWidth
	 */
	public double getPetalWidth() {
		return petalWidth;
	}

	/**
	 * @return the variety
	 */
	public String getVariety() {
		return variety;
	}
	
	

}
