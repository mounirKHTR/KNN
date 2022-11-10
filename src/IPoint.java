
public abstract class IPoint {
	
	public Object getValue(Icolumn col) {
		
		try {
			return getClass().getField(col.getName()).get(this);
		} catch (Exception e) {
			e.printStackTrace();return null;
		}
		
	}

	
	public Double getNormalized(Icolumn colx) {
		if(colx.isNormalizable()) {
			return colx.getNormalizedValue(this);
		}
		return null;
	}}
