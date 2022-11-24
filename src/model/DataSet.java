package model;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import Interface.IPoint;
import utils.Subject;

import java.util.ArrayList;
import java.util.List;

public  class DataSet extends Subject{
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

	public void setLines(List<IPoint> lines) {
		notifyObservers();
		this.lines = lines;
		}
	
	public List<IPoint> getLines() {
		return lines;
	}

	public void loadFromFiles(String path, Class <? extends IPoint> classe){
		data.clear();
			Field [] attribut=classe.getFields();
			for(Field a:attribut) {
				data.add(new Column(a.getName(),a.getType().toString(),this));
			}
		try {
			lines = new CsvToBeanBuilder<IPoint>(Files.newBufferedReader(Paths.get(path))).withSeparator(',')
					.withType(classe).build().parse();
			notifyObservers();
		} catch (IllegalStateException | IOException e) {
			System.out.println("erreur de chargement du fichier");
		}

	}
	public static void main(String[] args){
		DataSet pk=new DataSet();
		pk.loadFromFiles("pokemon_suspect12.csv", Pokemon.class);
		System.out.println(""+pk.getNbLines()+pk.data);
		DataSet ir=new DataSet();
		ir.loadFromFiles("iris.csv", Iris.class);
		System.out.println(""+ir.getNbLines()+ir.data);
		DataSet ti=new DataSet();
		ti.loadFromFiles("titanic.csv", Titanic.class);
		System.out.println(""+ti.getNbLines()+ti.data);
	}
}