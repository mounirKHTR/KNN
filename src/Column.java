
import java.util.ArrayList;
import java.util.List;

import Interface.IvalueNormalizer;

public class Column{
    protected String Name;
    protected String type;
    protected IvalueNormalizer Normalizer;
    protected List<?>ligne;
    
    public Column(String name, String type,ArrayList<?> data) {
        this.Name = name;
        this.type = type;
        this.ligne=data;
        
    }
    public void setNormaliser(IvalueNormalizer valueNormalizer) {
        this.Normalizer=valueNormalizer;
    }
    
    public boolean isNormalizable() {
        return this.type.equals();
    }
}