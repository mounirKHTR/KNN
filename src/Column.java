import java.util.ArrayList;
import java.util.List;

public  class Column{
	protected String Name;
	protected String type;
	protected IvalueNormalizer Normalizer;
	protected List<?>ligne;
	protected boolean isNormalizable;
	
	public Column(String name, String type,ArrayList<?> data) {
		this.Name = name;
		this.type = type;
		this.ligne=data;
		isNormalizable=type.equals("INTEGER")||type.equals("DOUBLE")||type.equals("ENUM");
		
	}
	public void setNormaliser(IvalueNormalizer valueNormalizer) {
		this.Normalizer=valueNormalizer;
	}
	public Object getNormalizedValue(IPoint point) {
		if(isNormalizable) return Normalizer.normalize(point);
		return null;
	}
	public Object getDenormalizedValue(IPoint point) {
		if(isNormalizable) return Normalizer.denormalize((double)getNormalizedValue(point));
		return null;
	}
	
}
