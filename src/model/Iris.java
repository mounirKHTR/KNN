package model;

import com.opencsv.bean.CsvBindByName;

import Interface.IPoint;

public class Iris extends IPoint{
	
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
	
    public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String variety) {
		super();
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
		this.variety = variety;
	}


    
    
	
}
