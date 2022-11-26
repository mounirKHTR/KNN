package model;

import Interface.IPoint;

import java.util.*;

public class MethodeKnn {
    public Map<Double, IPoint> sortEuclidian(IPoint pointDonne, List<IPoint> listePoint, List<Column> listColumn) {

        Distance distance = new Distance();
        Map<Double, IPoint> resultat = new LinkedHashMap<>();
        Map<Double, IPoint> tampon = new HashMap<>();

        for (IPoint points : listePoint) {
            tampon.put(distance.EuclidianDistanceBetween(pointDonne, points, listColumn), points);
        }

        return getIPointDoubleMap(resultat, tampon);
    }

    public Map<Double, IPoint> sortManhattan(IPoint pointDonne, List<IPoint> listePoint, List<Column> listColumn) {

        Distance distance = new Distance();
        Map<Double, IPoint> resultat = new LinkedHashMap<>();
        Map<Double, IPoint> tampon = new HashMap<>();

        for (IPoint points : listePoint) {
            tampon.put(distance.ManhattanDistanceBetween(pointDonne, points, listColumn), points);
        }

        return getIPointDoubleMap(resultat, tampon);
    }

    private Map<Double, IPoint> getIPointDoubleMap(Map<Double, IPoint> resultat, Map<Double, IPoint> tampon) {
        List<Map.Entry<Double, IPoint>> list = new ArrayList<>(tampon.entrySet());
        list.sort(Map.Entry.comparingByKey());

        for (Map.Entry<Double, IPoint> entry : list) {
            resultat.put(entry.getKey(), entry.getValue());
        }

        return resultat;
    }

    public List<IPoint> getNearestNeigbhour(Map<Double, IPoint> listNeigbhour, int nearestNeigbhour) {
        List<IPoint> resultat = new ArrayList<>();

        LinkedList<IPoint> tampon = new LinkedList<>(listNeigbhour.values());

        for (int i = 0; i < nearestNeigbhour; ++i) {
            resultat.add(tampon.get(i));
        }

        return resultat;
    }

    public String mostvalue(List<IPoint> nearest) {
        String rslt="";
        Map<String, Integer> groupCount = new HashMap<String, Integer>();

        List<String> tamp = new ArrayList<>();
        for (IPoint i : nearest
        ) {
            tamp.add(i.getGroup());
        }
        for (String word : tamp) {
            Integer count = groupCount.get(word);
            groupCount.put(word, (count == null) ? 1 : count + 1);
        }
        int max=0;
        for (String groupe: groupCount.keySet()
             ) {
                if(groupCount.get(groupe)>max){
                    rslt=groupe;
                    max=groupCount.get(groupe);
                }
        }return rslt;
    }
}
