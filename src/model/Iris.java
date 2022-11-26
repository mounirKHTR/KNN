package model;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import Interface.IPoint;

public class Iris extends IPoint{
	
	@CsvBindByName(column = "sepal.length")
    public double sepalLength;
    @CsvBindByName(column = "sepal.width")
    public double sepalWidth;
    @CsvBindByName(column = "petal.length")
    public double petalLength;
    @CsvBindByName(column = "petal.width")
    public double petalWidth;
    @CsvBindByName(column = "variety")
    public String variety;
	public boolean classified = true;
	
    /*public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String variety) {
		super();
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
		this.variety = variety;
	}*/

	public double getSepalLength() {
		return sepalLength;
	}

	public void setSepalLength(double sepalLength) {
		this.sepalLength = sepalLength;
	}

	public double getSepalWidth() {
		return sepalWidth;
	}

	public void setSepalWidth(double sepalWidth) {
		this.sepalWidth = sepalWidth;
	}

	public double getPetalLength() {
		return petalLength;
	}

	public void setPetalLength(double petalLength) {
		this.petalLength = petalLength;
	}

	public double getPetalWidth() {
		return petalWidth;
	}


	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Iris{" +
				"sepalLength=" + sepalLength +
				", sepalWidth=" + sepalWidth +
				", petalLength=" + petalLength +
				", petalWidth=" + petalWidth +
				", variety='" + variety + '\'' +
				'}';
	}

	@Override
	public String getGroup() {
		return variety;
	}

	@Override
	public List<String> getAllGroup() {
		List<String> groups = new ArrayList<String>();
		groups.add("Setosa");
		groups.add("Virginica");
		groups.add("Versicolor");
		return groups;
	}

	@Override
	public Iris add(List<String> fields) {
		sepalLength = Double.parseDouble(fields.get(0));
		sepalWidth = Double.parseDouble(fields.get(1));
		petalLength = Double.parseDouble(fields.get(2));
		petalWidth = Double.parseDouble(fields.get(3));
		return this;
	}
	public void setPetalWidth(double petalWidth) {
		this.petalWidth = petalWidth;
	}

}
