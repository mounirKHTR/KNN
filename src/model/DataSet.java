package model;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import Interface.IPoint;
import utils.Subject;

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

	public List<Object> getColumnData(Column colx){
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
			i = i.add(fields);
			i.classified = false;
            lines.add(i.add(fields));
			notifyObservers();
        }
	public void addPokemon(List<String> fields) {
		Pokemon i = new Pokemon();
		i = i.add(fields);
		i.classified = false;
		lines.add(i.add(fields));
		notifyObservers();
	}
	public void addTitanic(List<String> fields) {
		Titanic i = new Titanic();
		i = i.add(fields);
		i.classified = false;
		lines.add(i.add(fields));
		notifyObservers();
	}


	public Object getValue(int index, Column column) {
		return this.lines.get(index).getValue(column);
	}

	public void classify(List<Column> col, int k,boolean choice) {
		MethodeKnn knn = new MethodeKnn();
		for (IPoint i : this.getLines()) {
			if (!i.getClassified()) {
				if (choice) {
					i.setGroup(knn.mostvalue(knn.getNearestNeigbhour(knn.sortEuclidian(i,this.getLines(),col),k)));
					i.setClassified(true);
					notifyObservers();
				} else if (!choice) {
					i.setGroup(knn.mostvalue(knn.getNearestNeigbhour(knn.sortManhattan(i,this.getLines(),col),k)));
					i.setClassified(true);
					notifyObservers();
				}
			}
		}

	}

	public static void main(String[] args) throws IllegalStateException  {
		DataSet pk=new DataSet();
		pk.loadFromFiles("./src/data/pokemon_suspect1.csv", Pokemon.class);
		List<String> field = new ArrayList<String>();
		field.add("1");
		field.add("120");
		field.add("20000");
		field.add("1");
		field.add("1");
		field.add("1");
		field.add("1");
		field.add("1");
		field.add("1");
		field.add("1");
		field.add("1");
		field.add("1");
		MethodeKnn knn = new MethodeKnn();
		pk.addPokemon(field);
		
		System.out.println(field.toString());
		
		List<Column> col = new ArrayList<Column>();
		col.add(pk.getData().get(1));
		System.out.println(knn.getNearestNeigbhour(knn.sortEuclidian(pk.getLines().get(pk.getNbLines()-1),pk.getLines(),col),3));
		pk.classify(col,1,true);
		//System.out.println(pk.getLines().get(pk.getNbLines()-1));

		System.out.println(pk.getLines());
		Collections.shuffle(pk.getLines());
		System.out.println(pk.getLines());
	}


	}







