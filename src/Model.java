import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

public class Model implements IMVCModel {
	
	List<IPoint> datas;
	Collection<ICategory> dt;
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNbLines() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public void setLines(List<IPoint> lines) {
		// TODO Auto-generated method stub
		datas=lines;
	}

	@Override
	public void addLine(IPoint element) {
		// TODO Auto-generated method stub
		datas.add(element);
	}

	@Override
	public void addAllLine(List<IPoint> element) {
		datas.addAll(element);

	}

	@Override
	public Iterator<IPoint> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadFromFile(String datafile) {
		try {
			datas = new CsvToBeanBuilder<IPoint>(Files.newBufferedReader(Paths.get(datafile)))
			        .withSeparator(',')
			        .withType(IPoint.class)
			        .build().parse();
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void loadFromString(String data) {
		// TODO Auto-generated method stub
		List<String> point = new ArrayList<String>();
		String[] ligne = data.split(";");
		for (String l : ligne) {
			point.add(l);
		}
		
		
	}

	@Override
	public IColumn defaultXCol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IColumn defaultYCol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCategory(ICategory classe) {
		dt.add(classe);

	}

	@Override
	public Collection<ICategory> allCategories() {
		// TODO Auto-generated method stub
		return dt;
	}

	@Override
	public int nbColumns() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<IColumn> getNormalizableColumns() {
		// TODO Auto-generated method stub
		return null;
	}

}
