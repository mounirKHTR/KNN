package model;
import java.util.ArrayList;
import java.util.List;

import Interface.IPoint;
import Interface.IvalueNormalizer;

public  class Column{
	protected String Name;
	protected String type;
	protected IvalueNormalizer Normalizer;
	
	protected boolean isNormalizable ;
	
	public boolean isNormalizable() {
		return isNormalizable;
	}
	public String getName() {
		return Name;
	}
	public Column(String name, String type) {
		this.Name = name;
		this.type = type;
		isNormalizable=type.equals("int")||type.equals("double")||type.equals("ENUM");
	}
	public void setNormaliser(IvalueNormalizer valueNormalizer) {
		this.Normalizer=valueNormalizer;
	}
	public Object getNormalizedValue(IPoint iPoint) {
		if(isNormalizable) return Normalizer.normalize(iPoint);
		return null;
	}
	public Object getDenormalizedValue(IPoint point) {
		if(isNormalizable) return Normalizer.denormalize((double)getNormalizedValue(point));
		return null;
	}
	@Override
	public String toString() {
		return "Column [Name=" + Name + ", type=" + type + ", Normalizer=" + Normalizer 
				+ ", isNormalizable=" + isNormalizable + "]";
	}
	
}
