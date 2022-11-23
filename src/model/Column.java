package model;

import Interface.IPoint;
import Interface.IvalueNormalizer;
import Normalizer.Boolean_Normalizer;
import Normalizer.Number_Normalizer;

public  class Column{
	protected String nameColumn;
	protected String type;
	protected IvalueNormalizer normalizer;
	protected DataSet data;
	protected boolean isNormalizable ;
	
	public boolean isNormalizable() {
		return isNormalizable;
	}
	public String getNameColumn() {
		return nameColumn;
	}
	public Column(String nameColumn, String type, DataSet data) {
		this.nameColumn = nameColumn;
		this.type=type;
		setNormaliser(type);
		this.data=data;
	}
	
	public double[] amplitude(){
		return new double[]{};
	}
	
	public void setNormaliser(String type) {
			if(type.equals("int")||type.equals("double")) {
				this.normalizer =new Number_Normalizer();
			}
			if(type.equals("boolean")) {
				this.normalizer =new Boolean_Normalizer();
			}
		
	}
	public Object getNormalizedValue(IPoint iPoint) {
		if(isNormalizable) return normalizer.normalize(iPoint);
		return null;
	}
	public Object getDenormalizedValue(IPoint point) {
		if(isNormalizable) return normalizer.denormalize((double)getNormalizedValue(point));
		return null;
	}
	@Override
	public String toString() {
		return "Column [Name=" + nameColumn + ", type=" + type + ", Normalizer=" + normalizer
				+ ", isNormalizable=" + isNormalizable + "]";
	}
	
}
