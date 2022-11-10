import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import java.util.ArrayList;
import java.util.List;

public  class DataSet {
    protected String name;
    protected List<Column> Data = new ArrayList<>();
    protected List<IPoint> lines = new ArrayList<>();
    
    
    public String getTitle() {return this.name;}
    
    public int getNbLines()  {
    	return this.lines.size();
    }
    
    public void addLine(IPoint element) {
    	this.lines.add(element);
    }
    
    public void addAllLine(List<IPoint> elements) {
    	for(IPoint points: elements) {
    		this.lines.add(points);
    	}
    }
    
    public void setLines(List<IPoint> lines) {this.lines = lines;}
    
    	public static List<IPoint> loadRaw(String path, Class classe)throws IllegalStateException, IOException {
            return new CsvToBeanBuilder<IPoint>(Files.newBufferedReader(Paths.get(path))).withSeparator(',')
                    .withType(classe).build().parse();
        }
	}
    
    /*
	+Classified(IClassifieur): void
     */
 



