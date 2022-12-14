package model;

import Interface.IPoint;

import java.util.*;

public class MethodeKnn {
    public Map<Double, IPoint> sortEuclidian(IPoint pointDonne, List<IPoint> listePoint, List<Column> listColumn) {

        Distance distance = new Distance();
        Map<Double, IPoint> resultat = new LinkedHashMap<>();
        Map<Double, IPoint> tampon = new HashMap<>();

        for (IPoint points : listePoint) {
            if(pointDonne != points) {
                tampon.put(distance.euclidianDistanceBetween(pointDonne, points, listColumn), points);
            }
        }

        return getIPointDoubleMap(resultat, tampon);
    }

    public Map<Double, IPoint> sortManhattan(IPoint pointDonne, List<IPoint> listePoint, List<Column> listColumn) {

        Distance distance = new Distance();
        Map<Double, IPoint> resultat = new LinkedHashMap<>();
        Map<Double, IPoint> tampon = new HashMap<>();

        for (IPoint points : listePoint) {
            if(pointDonne != points) {
                tampon.put(distance.manhattanDistanceBetween(pointDonne, points, listColumn), points);
            }
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
        Map<String, Integer> groupCount = new HashMap<>();

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

            for (Map.Entry<String,Integer> entry : groupCount.entrySet()) {
                String groupe = entry.getKey();
                Integer valeur = entry.getValue();
                if(valeur>max){
                    rslt=groupe;
                    max=valeur;
                }

            }
        return rslt;
        }

        public String executeKnn(IPoint point,boolean method,int voisin,List<IPoint> listePoint, List<Column> listColumn){
        if(method) {
            return mostvalue(getNearestNeigbhour(this.sortEuclidian(point, listePoint, listColumn), voisin));
        }
        else {
            return mostvalue(getNearestNeigbhour(this.sortManhattan(point,listePoint,listColumn),voisin));
        }
        //else return null; //faire un null object

    }
}

