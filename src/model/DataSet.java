package model;
import java.io.IOException;
import java.lang.reflect.Field;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import Interface.IPoint;

public  class DataSet {
	protected String name;
	protected List<Column> Data = new ArrayList<>();
	protected List<IPoint> lines = new ArrayList<>();

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
	/*
	public List<?> getColumnData(Column colx){
		return null; //ne pas tester pour le moment
	}
	 */

	public void setLines(List<IPoint> lines) {this.lines = lines;}

	public void loadFromfiles(String path, Class <? extends IPoint> classe) throws IllegalStateException, IOException{
			Field [] attribut=classe.getFields();
			for(Field a:attribut) {
				Data.add(new Column(a.getName(),a.getType().toString(),this));
			}
		lines=new CsvToBeanBuilder<IPoint>(Files.newBufferedReader(Paths.get(path))).withSeparator(',')
				.withType(classe).build().parse();

	}

	public void clear() {
		this.lines.clear(); this.Data.clear();
	}

	public Object getValue(int index, Column column) {
		return this.lines.get(index).getValue(column);
	}


	/*public static void main(String[] args) throws IllegalStateException, IOException  {
		DataSet pk=new DataSet();
		pk.loadFromfiles("./src/data/pokemon_suspect1.csv", Pokemon.class);
		System.out.println(""+pk.lines.toString()+pk.Data);
		DataSet ir=new DataSet();
		ir.loadFromfiles("./src/data/iris.csv", Iris.class);
		System.out.println(""+ir.getNbLines()+ir.Data);
		DataSet ti=new DataSet();
		ti.loadFromfiles("./src/data/titanic.csv", Titanic.class);
		System.out.println(""+ti.getNbLines()+ti.Data);
		
		NormalizerTypes[] tabnormal =NormalizerTypes.values();
		for(NormalizerTypes n:tabnormal) {
			System.out.println(n.getNom());
	}

	 */
		
}





