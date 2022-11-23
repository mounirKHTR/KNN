package model;
import java.io.IOException;
import java.lang.reflect.Field;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import Interface.IPoint;
import Interface.IvalueNormalizer.NormalizerTypes;

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
	
	public ArrayList<Object> getColumnData(Column colx){
		ArrayList<Object>rslt=new ArrayList<>();
		for(IPoint ip:lines) {
		rslt.add(ip.getValue(colx));
		}
		return rslt;
		
		
	}

	public void setLines(List<IPoint> lines) {this.lines = lines;}

	public void loadFromfiles(String path, Class <? extends IPoint> classe) throws IllegalStateException, IOException{
		lines=new CsvToBeanBuilder<IPoint>(Files.newBufferedReader(Paths.get(path))).withSeparator(',')
				.withType(classe).build().parse();
			
		
		Field [] attribut=classe.getFields();
			for(Field a:attribut) {
				Data.add(new Column(a.getName(),a.getType().toString(),this));
			}
		

	}
	public Object getValue(int index, Column column) {
		return this.lines.get(index).getValue(column);
	}
	/**
	 * @param args
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IllegalStateException, IOException  {
		DataSet pk=new DataSet();
		pk.loadFromfiles("./src/data/pokemon_suspect1.csv", Pokemon.class);
		System.out.println(""+pk.getNbLines()+pk.Data);
		DataSet ir=new DataSet();
		ir.loadFromfiles("./src/data/iris.csv", Iris.class);
		System.out.println(""+ir.getNbLines()+ir.Data);
		DataSet ti=new DataSet();
		ti.loadFromfiles("./src/data/titanic.csv", Titanic.class);
		System.out.println(""+ti.getNbLines()+ti.Data);
		System.out.println(ir.Data.get(0).getNormalizedValue(ir.lines.get(8)));
		double[] ampli=ir.Data.get(2).amplitude();
		System.out.println(""+ampli[0]+" "+ampli[1]);
		System.out.println(ir.lines.get(8).getValue(ir.Data.get(1)).getClass().toString());

	}
		
	}






