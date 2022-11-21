package Normalizer;
import Interface.IvalueNormalizer;

public  class Number_Normalizer implements IvalueNormalizer{
	double min;
	double max;
	
	
	public Number_Normalizer() {
		
		this.min = 0;
		this.max = 0;
	}

	@Override
	public double normalize(Object value) {	
		return ((double)value-min)/max-min ;
	}

	@Override
	public Object denormalize(double value) {
		return value*(max-min)+min;
	}

}
