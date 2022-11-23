package Normalizer;
import Interface.IvalueNormalizer;

public  class Number_Normalizer implements IvalueNormalizer{
	double min;
	double max;
	
	
	public Number_Normalizer(double [] ampli) {
		
		this.min = ampli[0];
		this.max = ampli[1];
	}

	@Override
	public double normalize(Object value) {
		
		return (double)Math.round(((double)value-min)/(max-min)*100)/100;
	}

	@Override
	public Object denormalize(double value) {
		return value*(max-min)+min;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}
	

}
