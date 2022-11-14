
public  class Number_Normalizer implements IValueNormalizer{
    private double min;
    private double max;
     
    public Number_Normalizer() {
		super();
		this.min = Double.MAX_VALUE;
		this.max = 0;
	}

	@Override
    public double normalize(Object value) {    
        return ((double)value-min)/(max-min) ;
    }

    @Override
    public Object denormalize(double value) {
        return value*(max-min)+min;
    }

	/**
	 * @return the min
	 */
	public double getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(double min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(double max) {
		this.max = max;
	}
    
    

}
