package model;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
	public String getName() {
		return Name;
	}
	public Column(String name, String type,DataSet data) {
		this.Name = name;
		this.type=type;
		setNormaliser(type);
		this.data=data;
		
		
	}
	
	public double[] amplitude(){
		return null;
		
	}
	
	public void setNormaliser(String type) {
			if(type.equals("int")||type.equals("double")) {
				this.Normalizer=new Number_Normalizer();
			}
			if(type.equals("boolean")) {
				this.Normalizer=new Boolean_Normalizer();
			}
		
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
