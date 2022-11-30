package normalizer;
import Interface.IvalueNormalizer;

public class BooleanNormalizer implements IvalueNormalizer {

    @Override
    public double normalize(Object value) {
        if(value.equals(true))return 1.0;
        return 0.0;
    }

    @Override
    public Object denormalize(double value) {
        return value==1.0;
    }

}
