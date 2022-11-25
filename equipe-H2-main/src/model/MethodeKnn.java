package model;

import Interface.IPoint;

import java.util.*;

public class MethodeKnn {
    public Map<Double, IPoint> sortEuclidian(IPoint pointDonne, List<IPoint> listePoint, List<Column> listColumn) {

        Distance distance = new Distance();
        Map<Double, IPoint> resultat = new LinkedHashMap<>();
        Map<Double, IPoint> tampon = new HashMap<>();

        for(IPoint points: listePoint){
            tampon.put(distance.EuclidianDistanceBetween(pointDonne, points, listColumn), points);
        }

        return getIPointDoubleMap(resultat, tampon);
    }

    public Map<Double, IPoint> sortManhattan(IPoint pointDonne, List<IPoint> listePoint, List<Column> listColumn) {

        Distance distance = new Distance();
        Map<Double, IPoint> resultat = new LinkedHashMap<>();
        Map<Double, IPoint> tampon = new HashMap<>();

        for(IPoint points: listePoint){
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

    public List<IPoint> getNearestNeigbhour(Map<Double, IPoint> listeNearestNeigbhour, int nearestNeigbhour) {
        List<IPoint> resultat = new ArrayList<>();

        LinkedList<IPoint> tampon = (LinkedList<IPoint>) listeNearestNeigbhour.values();

        for(int i = 0; i < nearestNeigbhour; ++i) {
            resultat.add(tampon.get(i));
        }

        return resultat;
    }
}
