package equipeH2;

import java.util.ArrayList;
import java.util.List;

public class Column {
    protected String name;
    protected DataSet data;
    protected boolean normalizable;
    protected IValueNormalizer normalizerType;



    public String getName(){return this.name;}
    public DataSet getDataSet(){return this.data;}
    public boolean isNormalizable(){return this.normalizable;}

}
