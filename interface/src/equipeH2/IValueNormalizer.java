package equipeH2;

public interface IValueNormalizer {
    public enum NormalizerTypes {
        NUMBER_NORMALIZER, BOOLEAN_NORMALIZER, POKEMON_TYPE_NORMALIZER;
    }
    public double normalize(Object value);
    //public Object denormalize(double value);
}