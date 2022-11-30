package normalizer;
import Interface.IvalueNormalizer;

public  class NumberNormalizer implements IvalueNormalizer{
	protected double min;
	protected double max;
	
	
	public NumberNormalizer(double [] ampli) {
		
		this.min = ampli[0];
		this.max = ampli[1];
	}

	@Override
	public double normalize(Object value) {
		
		return (double)Math.round(((double)value-min)/(max-min)*100)/100;
	}

	@Override
	public Object denormalize(double value) {
		return (double) Math.round((value*(max-min)+min)*100)/100;
	}


	

}
