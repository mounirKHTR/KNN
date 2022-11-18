package model;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import Interface.IDataSet;
import Interface.IDistance;
import Interface.IPoint;
import utils.Subject;


public class DatasetPokemon extends Subject implements IDataSet{
	private List<Pokemon> set;
	private IDistance d;
	private List<Column> col;
	private List<Number_Normalizer> norm;
	private List<List<Pokemon>> dtDt = new ArrayList<>();
	
	
	public DatasetPokemon(IDistance d, List<Column> col) {
		super();
		this.d = d;
		this.col = col;
		norm = new ArrayList<Number_Normalizer>();
		norm.add(new Number_Normalizer());
		norm.add(new Number_Normalizer());
		norm.add(new Number_Normalizer());
		norm.add(new Number_Normalizer());
	}

	public Iterator<IPoint> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addDT(List<Pokemon> dt) {
		dtDt.addAll((Collection<? extends List<Pokemon>>) dt);
		notifyObservers(dtDt);
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return "Pokemon";
	}

	public int getNbLines() {
		// TODO Auto-generated method stub
		return set.size();
	}

	public void setLines(List<IPoint> lines) {
		// TODO Auto-generated method stub
	}

	public void addLine(IPoint element) {
		// TODO Auto-generated method stub
		set.add((Pokemon) element);
	}

	public void addAllLine(List<IPoint> element) {
		// TODO Auto-generated method stub
		set.add((Pokemon) element);
	}
	
	public void loadFromFile(String datafile) {
		try {
			set = new CsvToBeanBuilder<Pokemon>(Files.newBufferedReader(Paths.get(datafile)))
			        .withSeparator(',')
			        .withType(Pokemon.class)
			        .build().parse();
		} catch (IllegalStateException | IOException e) {
			System.out.println("erreur de chargement du fichier");
		}
		for (Pokemon i : set) {
			if (i.getSepalLength()>norm.get(0).getMax()) {
				norm.get(0).setMax(i.getSepalLength());
			} else if (i.getSepalLength()<norm.get(0).getMin()) {
				norm.get(0).setMin(i.getSepalLength());
			}
			if (i.getSepalWidth()>norm.get(1).getMax()) {
				norm.get(1).setMax(i.getSepalWidth());
			} else if (i.getSepalWidth()<norm.get(1).getMin()) {
				norm.get(1).setMin(i.getSepalWidth());
			}
			if (i.getPetalLength()>norm.get(2).getMax()) {
				norm.get(2).setMax(i.getPetalLength());
			} else if (i.getPetalLength()<norm.get(2).getMin()) {
				norm.get(2).setMin(i.getPetalLength());
			}
			if (i.getPetalWidth()>norm.get(3).getMax()) {
				norm.get(3).setMax(i.getPetalWidth());
			} else if (i.getPetalWidth()<norm.get(3).getMin()) {
				norm.get(3).setMin(i.getPetalWidth());
			}
		}
		dtDt.add(set);
		this.setSet(set);
	}
	
	/*public static void main(String[] args) {
		DistanceEuclidienne dE = new DistanceEuclidienne() ;
		List<Column> colI = new ArrayList<Column>();
		DatasetIris dt = new DatasetIris(dE, colI);
		dt.loadFromFile("iris.csv");
		
	}*/

	/**
	 * @return the set
	 */
	public List<Pokemon> getSet() {
		return set;
	}
	
	public void setSet(List<Pokemon> set) {
		this.set = set;
		notifyObservers(set);

	}

	/**
	 * @return the d
	 */
	public IDistance getD() {
		return d;
	}

	/**
	 * @return the col
	 */
	public List<Column> getCol() {
		return col;
	}

	/**
	 * @return the norm
	 */
	public List<Number_Normalizer> getNorm() {
		return norm;
	}

}
