
public  class Number_Normalizer implements IvalueNormalizer{
	double min;
	double max;
	
	public Number_Normalizer(double min, double max) {
		super();
		this.min = min;
		this.max = max;
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
