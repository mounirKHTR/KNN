package Normalizer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Interface.IvalueNormalizer;
import model.Column;

public class Enum_Normalizer implements IvalueNormalizer {

    protected List<Object> brut=new ArrayList<>();
    protected Map<String, Double> map;


    public Enum_Normalizer(Column colx) {
        brut=colx.getALLDataCol();
        this.map = new HashMap<>();
        this.getNormalizedMap();
    }

    public Map<String, Double> getMap(){
        return this.map;
    }

    public void getNormalizedMap() {
        for (int i = 0; i< this.brut.size(); i++) {
            if(this.map.containsKey(this.brut.get(i))) i=i;
            else this.map.put((String)this.brut.get(i), (double) i / (double) (this.brut.size()-1)); // exmple 1 / 3 = 0.33
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
        return this.brut.get((int) (value *  (this.brut.size()-1)));  //on veut recup l'indice (exemple -> 0.33 * 3 = 1)
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