package Normalizer;
import Interface.IvalueNormalizer;

public class Boolean_Normalizer implements IvalueNormalizer {

	@Override
	public double normalize(Object value) {
		if(value.equals(true))return 1.0;
		return 0.0;
	}

	@Override
	public Object denormalize(double value) {
		if(value==1.0)return true;
		return false;
	}

}
