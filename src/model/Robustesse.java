package model;
import Interface.IPoint;

import java.io.IOException;
import java.util.*;

public class Robustesse {
    protected DataSet dataSet;
    protected int numberNeighboors;
    protected MethodeKnn knn;
    protected boolean euclidian;

    public Robustesse(DataSet dataSet, int numberNeighboors,boolean euclidian) {
        this.dataSet = dataSet;
        this.numberNeighboors = numberNeighboors;
        this.knn = new MethodeKnn();
        
    }

    public double robustesse() throws IllegalArgumentException, SecurityException{
        double hit = 0;
        int size = this.dataSet.getLines().size();
        Collections.shuffle(this.dataSet.getLines());
        List<IPoint> test = new ArrayList<>(this.dataSet.getLines());
        String rslt;
        for (int i = 0; i < size / 2; i++) {
            test.remove(i);
            rslt = knn.executeKnn(this.dataSet.getLines().get(i), euclidian, this.numberNeighboors, test, this.dataSet.getData());
            if (rslt.equals(this.dataSet.getLines().get(i).getGroup())) ++hit;
        }
        return hit / size * 100 * 2;
    }

}