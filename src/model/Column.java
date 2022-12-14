package model;
import java.util.ArrayList;
import java.util.List;

import Interface.IPoint;
import Interface.IvalueNormalizer;
import normalizer.BooleanNormalizer;
import normalizer.EnumNormalizer;
import normalizer.NumberNormalizer;


public  class Column{
	protected String name;
	protected String type;
	protected IvalueNormalizer normalizer;
	protected DataSet data;
	protected boolean isNormalizable ;
	
	public boolean isNormalizable() {
		return isNormalizable;
	}

	public Column(String name, String type,DataSet data) {
		this.name = name;
		this.type=type;
		this.data=data;
		setNormaliser(type);
		
	}

	private double[] amplitude() {
		double min =1000000.0;
		double max=0.0;

		for(IPoint ip:data.lines) {

					double value=(double) ip.getValue(this);
					if(value>max) {
						max=value;

					} else if(value<min) {
						min=value;
					}
				}
		return new double[] {min,max};
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the normalizer
	 */
	public IvalueNormalizer getNormalizer() {
		return normalizer;
	}
	private void setNormaliser(String type) {
			if(type.equals("int")||type.equals("double")) {
				this.normalizer=new NumberNormalizer(this.amplitude());
				this.isNormalizable=true;
			}
			if(type.equals("boolean")) {
				this.normalizer=new BooleanNormalizer();
				this.isNormalizable=true;
			}else if (type.equals("class java.lang.String")){
				this.normalizer=new EnumNormalizer(this);
				this.isNormalizable=true;
			}

	}
	public Object getNormalizedValue(IPoint iPoint) {
		if(isNormalizable) return normalizer.normalize(iPoint.getValue(this));
		return null;
	}
	public Object getDenormalizedValue(IPoint point) {
		if(point.getValue(this)==null)return null;
		if(isNormalizable) return normalizer.denormalize((double)getNormalizedValue(point));
		return null;
	}
	public List<Object>getALLDataCol(){
		List<Object>rslt=new ArrayList<>();
		for(IPoint ip: data.lines){
			rslt.add(ip.getValue(this));

		}
		return rslt;
	}
	@Override
	public String toString() {
		return "Column [Name=" + name + ", type=" + type + ", Normalizer=" + normalizer
				+ ", isNormalizable=" + isNormalizable + "]";
	}

}
