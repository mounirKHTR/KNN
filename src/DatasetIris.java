import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;


public class DatasetIris implements IDataset{
	private List<Iris> set;
	private IDistance d;
	private List<IColumn> col;
	private List<Number_Normalizer> norm;
	
	
	public DatasetIris(IDistance d, List<IColumn> col) {
		super();
		this.d = d;
		this.col = col;
		norm = new ArrayList<Number_Normalizer>();
		norm.add(new Number_Normalizer());
		norm.add(new Number_Normalizer());
		norm.add(new Number_Normalizer());
		norm.add(new Number_Normalizer());
	}

	@Override
	public Iterator<IPoint> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Iris";
	}

	@Override
	public int getNbLines() {
		// TODO Auto-generated method stub
		return set.size();
	}

	@Override
	public void setLines(List<IPoint> lines) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLine(IPoint element) {
		// TODO Auto-generated method stub
		set.add((Iris) element);
	}

	@Override
	public void addAllLine(List<IPoint> element) {
		// TODO Auto-generated method stub
		set.addAll((Collection<? extends Iris>) element);
	}
	
	public void loadFromFile(String datafile) {
		/*double maxSepLen = 0;
		double minSepLen = Double.MAX_VALUE;
		double maxSepWid = 0;
		double minSepWid= Double.MAX_VALUE;
		double maxPetLen = 0;
		double minPetLen = Double.MAX_VALUE;
		double maxPetWid = 0;
		double minPetWid= Double.MAX_VALUE;*/
		try {
			set = new CsvToBeanBuilder<Iris>(Files.newBufferedReader(Paths.get(datafile)))
			        .withSeparator(',')
			        .withType(Iris.class)
			        .build().parse();
		} catch (IllegalStateException | IOException e) {
			System.out.println("erreur de chargement du fichier");
		}
		/*for (Number_Normalizer n : norm) {
			
		}*/
		for (Iris i : set) {
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
	}
	
	
	
	
	public static void main(String[] args) {
		DistanceEuclidienne dE = new DistanceEuclidienne() ;
		List<IColumn> colI = new ArrayList<IColumn>();
		DatasetIris dt = new DatasetIris(dE, colI);
		dt.loadFromFile("iris.csv");
		System.out.println(dt.getNorm().get(0).getMax()+ "|" + dt.getNorm().get(0).getMin());
		System.out.println(dt.getNorm().get(1).getMax()+ "|" + dt.getNorm().get(1).getMin());
		System.out.println(dt.getNorm().get(2).getMax()+ "|" + dt.getNorm().get(2).getMin());
		System.out.println(dt.getNorm().get(3).getMax()+ "|" + dt.getNorm().get(3).getMin());

		
	}

	/**
	 * @return the set
	 */
	public List<Iris> getSet() {
		return set;
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
	public List<IColumn> getCol() {
		return col;
	}

	/**
	 * @return the norm
	 */
	public List<Number_Normalizer> getNorm() {
		return norm;
	}
	
	
	
	

}
