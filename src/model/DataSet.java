package model;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import Interface.IPoint;

import java.util.ArrayList;
import java.util.List;

public  class DataSet {
	protected String name;
	protected List<Column> data = new ArrayList<>();
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
	
	public List<Column> getData() {
		return data;
	}

	public void setLines(List<IPoint> lines) {this.lines = lines;}
	
	public List<IPoint> getLines() {
		return lines;
	}

	public List<IPoint> loadFromFiles(String path, Class <? extends IPoint> classe){
			Field [] attribut=classe.getFields();
			for(Field a:attribut) {
				data.add(new Column(a.getName(),a.getType().toString()));
			}
		try {
			return new CsvToBeanBuilder<IPoint>(Files.newBufferedReader(Paths.get(path))).withSeparator(',')
					.withType(classe).build().parse();
		} catch (IllegalStateException | IOException e) {
			System.out.println("erreur de chargement du fichier");
			return null;
		}

	}
	public static void main(String[] args){
		DataSet pk=new DataSet();
		pk.lines=pk.loadFromFiles("./src/pokemon_suspect12.csv", Pokemon.class);
		System.out.println(""+pk.getNbLines()+pk.data);
		DataSet ir=new DataSet();
		ir.lines=ir.loadFromFiles("./src/iris.csv", Iris.class);
		System.out.println(""+ir.getNbLines()+ir.data);
		DataSet ti=new DataSet();
		ti.lines=ti.loadFromFiles("./src/titanic.csv", Titanic.class);
		System.out.println(""+ti.getNbLines()+ti.data);
	}
}