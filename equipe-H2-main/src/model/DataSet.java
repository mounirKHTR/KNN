package model;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.opencsv.bean.CsvToBeanBuilder;

import Interface.IPoint;
import utils.Subject;
import Interface.IvalueNormalizer.NormalizerTypes;


import java.util.ArrayList;
import java.util.List;

public  class DataSet extends Subject {
	static double amp_base_egg_steps;
	static double amp_capture_rate;
	static double amp_experience_growth;
	static double amp_speed;
	protected String name;
	static List<Pokemon> datas;
	protected Pokemon p;
	protected List<Column> data = new ArrayList<>();
	protected List<IPoint> lines = new ArrayList<>();
	protected List<Column> col = new ArrayList<>();
	protected Class<?> category;
	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {return this.name;}

	public int getNbLines()  {
		return this.lines.size();
	}

	public void addLine(IPoint element) {
		this.lines.add(element);
	}

	public void addAllLine(List<IPoint> elements) {
		this.lines.addAll(elements);
	}

	public ArrayList<Object> getColumnData(Column colx){
		ArrayList<Object>rslt=new ArrayList<>();
		for(IPoint ip:lines) {
		rslt.add(ip.getValue(colx));
		}
		return rslt;
	}
	
	public List<Column> getData() {
		return data;
	}

	public List<IPoint> getLines() {
        return lines;
    }

    public void setLines(List<IPoint> lines) {
        notifyObservers();
        this.lines = lines;
    }

	public Class<?> getCategory() {
		return category;
	}

	public void loadFromFiles(String path, Class <? extends IPoint> classe){
        data.clear();
		try {
			lines = new CsvToBeanBuilder<IPoint>(Files.newBufferedReader(Paths.get(path))).withSeparator(',')
					.withType(classe).build().parse();

		} catch (IllegalStateException | IOException e) {
			System.out.println("erreur de chargement du fichier");
		}
        Field[] attribut=classe.getFields();
        for(Field a:attribut) {
            data.add(new Column(a.getName(),a.getType().toString(),this));

        }
	this.category=classe;
		notifyObservers();
    }
	public void clear() {
		this.lines.clear(); this.data.clear();
	}
        public void addPoint(List<String> fields, IPoint point) {
            lines.add(point.add(fields));
        }

	public Object getValue(int index, Column column) {
		return this.lines.get(index).getValue(column);
	}
	public static void main(String[] args) throws IllegalStateException, IOException  {
		DataSet pk=new DataSet();
		pk.loadFromFiles("./src/data/pokemon_suspect1.csv", Pokemon.class);
		System.out.println(""+pk.getLines()+pk.data);
		DataSet ir=new DataSet();
		ir.loadFromFiles("./src/data/iris.csv", Iris.class);
		System.out.println(""+ir.getNbLines()+ir.data);
		DataSet ti=new DataSet();
		ti.loadFromFiles("./src/data/titanic.csv", Titanic.class);
		System.out.println(""+ti.getNbLines()+ti.data);
		System.out.println(ir.lines.get(8).getValue(ir.data.get(0)));
		System.out.println(ir.data.get(0).getNormalizedValue(ir.lines.get(8)));
		System.out.println(ir.data.get(0).getDenormalizedValue(ir.lines.get(8)));
		double[] ampli=ir.data.get(2).amplitude();
		System.out.println(""+ampli[0]+" "+ampli[1]);
		System.out.println(ir.lines.get(8).getValue(ir.data.get(1)).getClass().toString());
		
		System.out.println(pk);
		pk.computeAndSetAmps();
		Pokemon stdCase = new Pokemon();
		stdCase.setName("Ho-oh");
		stdCase.setAttack(130);
		stdCase.setBase_egg_steps(30720);
		stdCase.setCapture_rate(3.0);
		stdCase.setDefense(90);
		stdCase.setExperience_growth(1250000);
		stdCase.setHp(106);
		stdCase.setSp_attack(154);
		stdCase.setSp_defense(90);
		stdCase.setSpeed(199.0);
		pk.kNN(5, stdCase);
		double result = pk.createNewGen("./src/data/pokemon_suspect1.csv", 5);
		System.out.println("Robustesse : " + Math.round(result) + "%.");

	}
	public void computeAndSetAmps() {
		List<Double> mins = getMinOrMax(false);
		List<Double> maxs = getMinOrMax(true);
		
		DataSet.amp_base_egg_steps = maxs.get(0).intValue() - mins.get(0).intValue();
		DataSet.amp_capture_rate = maxs.get(1).doubleValue() - mins.get(1).doubleValue();
		DataSet.amp_experience_growth = maxs.get(2).intValue() - mins.get(2).intValue();
		DataSet.amp_speed = maxs.get(3).doubleValue() - mins.get(3).doubleValue();
	}

	public ArrayList<Double> getMinOrMax(boolean getMax) {
		Iterator<Pokemon> i = datas.iterator();
		Pokemon pkm;
		ArrayList<Double> l = null;
		if(i.hasNext()) {
			pkm = i.next();
			l = new ArrayList<Double>();
			l.add((double) pkm.baseEggSteps);
			l.add(pkm.captureRate);
			l.add((double) pkm.experienceGrowth);
			l.add(pkm.speed);
		}
		while(i.hasNext()) {
			pkm = (Pokemon) i.next();
			l = compareAndSet(l, pkm, getMax);
		}
		return l;
	}
	
	public ArrayList<Double> compareAndSet(ArrayList<Double> l, Pokemon pkm, boolean greaterThan) {
		if(!greaterThan) {
			if(pkm.baseEggSteps < l.get(0)) {
				l.set(0, (double) pkm.baseEggSteps);
			}
			if(pkm.captureRate < l.get(1)) {
				l.set(1, pkm.captureRate);
			}
			if(pkm.experienceGrowth < l.get(2)) {
				l.set(2, (double) pkm.experienceGrowth);
			}
			if(pkm.speed < l.get(3)) {
				l.set(3, pkm.speed);
			}
		}else {
			if(pkm.baseEggSteps > l.get(0)) {
				l.set(0, (double) pkm.baseEggSteps);
			}
			if(pkm.captureRate > l.get(1)) {
				l.set(1, pkm.captureRate);
			}
			if(pkm.experienceGrowth > l.get(2)) {
				l.set(2, (double) pkm.experienceGrowth);
			}
			if(pkm.speed > l.get(3)) {
				l.set(3, pkm.speed);
			}
		}
		
		return l;
	}
	public Map<Double, Pokemon> computeDist(Pokemon pkm){
		Map<Double, Pokemon> map = new HashMap<Double, Pokemon>();
		Distance de = new Distance();
		for(Pokemon pkm2 : datas) {
			map.put(de.EuclidianDistanceBetween(pkm, pkm2, col), pkm2);
		}
		return map;
	}
	
	public boolean kNN(int k, Pokemon stdCase) {
		Map<Double, Pokemon> map = computeDist(stdCase);
		ArrayList<Pokemon> nn = new ArrayList<Pokemon>();
		Pokemon pkm;
		int lgn=0;
		int nLgn=0;
		for(int i = 0; i < k; i++) {
			pkm = map.remove(Collections.min(map.keySet()));
			nn.add(pkm);
			if(pkm.isLegendary) {
				lgn++;
			}else {
				nLgn++;
			}
			System.out.println(pkm);
		}
		System.out.println(lgn + " - " + nLgn);
		if(lgn==nLgn) {
			Random rnd = new Random();
			return rnd.nextBoolean();
		} else {
			return lgn>nLgn?true:false;
		}
		
	}
	
	public double createNewGen(String url, int k) throws IOException {
		List<Pokemon> data = new CsvToBeanBuilder<Pokemon>(new FileReader(url))
		        .withSeparator(',')
		        .withType(Pokemon.class)
		        .build().parse();
		double totNumber = 0;
		double correct = 0;
		for(Pokemon pkm : data) {
			totNumber = totNumber + 1;
			System.out.println("Study -> "+pkm);
			if(kNN(k,pkm) == pkm.isLegendary) correct = correct + 1;
		}
		System.out.println(totNumber +" >-< " + correct);
		
		return (correct * 100) / totNumber;
	}

}







