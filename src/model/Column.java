package model;
import java.util.ArrayList;
import java.util.List;

import Interface.IPoint;
import Interface.IvalueNormalizer;
import Interface.IvalueNormalizer.NormalizerTypes;
import Normalizer.Boolean_Normalizer;
import Normalizer.Number_Normalizer;


public  class Column{
	protected String Name;
	protected String type;
	protected IvalueNormalizer Normalizer;
	protected DataSet data;
	protected boolean isNormalizable ;
	
	public boolean isNormalizable() {
		return isNormalizable;
	}

	public Column(String name, String type,DataSet data) {
		this.Name = name;
		this.type=type;
		this.data=data;
		setNormaliser(type);
		
	}

	public double[] amplitude() {
		double min =1000000;
		double max=0;

		for(IPoint ip:data.lines) {

					double value=(double) ip.getValue(this);
					if(value>max) {
						max=value;

					}if(value<min) {
						min=value;
					}
				}
		return new double[] {min,max};
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @return the normalizer
	 */
	public IvalueNormalizer getNormalizer() {
		return Normalizer;
	}
	public void setNormaliser(String type) {
			if(type.equals("int")||type.equals("double")) {
				this.Normalizer=new Number_Normalizer(this.amplitude());
				this.isNormalizable=true;
			}
			if(type.equals("boolean")) {
				this.Normalizer=new Boolean_Normalizer();
				this.isNormalizable=true;
			}

	}
	public Object getNormalizedValue(IPoint iPoint) {
		if(isNormalizable) return Normalizer.normalize(iPoint.getValue(this));
		return null;
	}
	public Object getDenormalizedValue(IPoint point) {
		if(point.getValue(this).equals(null))return null;
		if(isNormalizable) return Normalizer.denormalize((double)getNormalizedValue(point));
		return null;
	}
	@Override
	public String toString() {
		return "Column [Name=" + Name + ", type=" + type + ", Normalizer=" + Normalizer
				+ ", isNormalizable=" + isNormalizable + "]";
	}

}
