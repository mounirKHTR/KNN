import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;


public class DatasetIris implements IDataset{
	List<Iris> set;
	static double amplSepLen =0;
	static double amplSepWid =0;
	static double amplPetLen =0;
	static double amplPetWid =0;
	
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
		try {
			set = new CsvToBeanBuilder<Iris>(Files.newBufferedReader(Paths.get(datafile)))
			        .withSeparator(',')
			        .withType(Iris.class)
			        .build().parse();
		} catch (IllegalStateException | IOException e) {
			System.out.println("erreur de chargement du fichier");
		}
		/*for (Iris i : set) {
			
		}*/
	}
	
	public static void main(String[] args) {
		DatasetIris dt = new DatasetIris();
		dt.loadFromFile("iris.csv");
		System.out.println(dt.set.size());
	}

}
