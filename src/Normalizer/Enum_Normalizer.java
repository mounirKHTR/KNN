package Normalizer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Interface.IvalueNormalizer;

public class Enum_Normalizer implements IvalueNormalizer {

    private Field[] fields;
    private Map<String, Double> map;

    public Enum_Normalizer(Class<?> type) {
        this.fields = type.getFields();
        this.map = new HashMap<String, Double>();
        this.getNormalizedMap();
    }

    public Map<String, Double> getMap(){
        return this.map;
    }

    public void getNormalizedMap() {
        for (int i = 0; i< this.fields.length; i++) {
            if(this.map.containsKey(this.fields[i].getName())) i=i;
            else this.map.put(this.fields[i].getName(), (double) i / (double) (this.fields.length-1)); // exmple 1 / 3 = 0.33
        }
    }

    @Override
    public double normalize(Object value) {
        if (value != null) {
            return this.map.get(value.toString());
        }
        return -1;
    }

    @Override
    public Object denormalize(double value) {
        return this.fields[(int) (value * (double) (this.fields.length-1))];  //on veut recup l'indice (exemple -> 0.33 * 3 = 1)
    }

    @Override
    public double getMin() {
        return 0;
    }

    @Override
    public double getMax() {
        return 0;
    }

}