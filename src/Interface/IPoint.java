package Interface;
import java.util.List;

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
		
	}

	public abstract String getGroup();
	public abstract List<String> getAllGroup();
	public abstract IPoint add(List<String> fields);
}
