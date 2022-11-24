package Interface;

import model.Column;

public abstract class IPoint {
	
	
	public Object getValue(Column col) {
		
		try {
			return getClass().getField(col.getName()).get(this);
		} catch (Exception e) {
			e.printStackTrace();return null;
		}
		
	}

	
	public Double getNormalized(Column colx) {
		if(colx.isNormalizable()) {
			return (Double) colx.getNormalizedValue(this);
		}
		return 0.0;
		
		
	}}
