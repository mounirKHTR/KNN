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
import utils.Subject;
import Interface.IvalueNormalizer.NormalizerTypes;

import java.util.ArrayList;
import java.util.List;

public  class DataSet extends Subject {
	protected String name;
	protected List<Column> data = new ArrayList<>();
	protected List<IPoint> lines = new ArrayList<>();
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
        public void addIris(List<String> fields) {
			Iris i = new Iris();
            lines.add(i.add(fields));
			notifyObservers();
        }
	public void addPokemon(List<String> fields) {
		Pokemon i = new Pokemon();
		lines.add(i.add(fields));
		notifyObservers();
	}
	public void addTitanic(List<String> fields) {
		Titanic i = new Titanic();
		lines.add(i.add(fields));
		notifyObservers();
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

	}


	}







