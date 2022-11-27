package model;


import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import Interface.IPoint;


public class Titanic extends IPoint{
	
	    
	    @CsvBindByName(column = "PassengerId")
	    public double passengerId;
	    @CsvBindByName(column = "Survived")
	    public double survived;
	    @CsvBindByName(column = "Pclass")
	    public double pClass;
	    @CsvBindByName(column = "Name")
	    public String name;
	    @CsvBindByName(column = "Sex")
	    public String sex;
	    @CsvBindByName(column = "Age")
	    public double age;
	    @CsvBindByName(column = "SibSp")
	    public double sibSp;
	    @CsvBindByName(column = "Parch")
	    public double parch;
	    @CsvBindByName(column = "Ticket")
	    public String ticket;
	    @CsvBindByName(column = "Fare")
	    public double fare;
	    @CsvBindByName(column = "Cabin")
	    public String cabin;
	    @CsvBindByName(column = "Embarked")
	    public String embarked;
		public boolean classified = true;

		public double getPassengerId() {
			return passengerId;
		}

		public void setPassengerId(int passengerId) {
			this.passengerId = passengerId;
		}

		public double getSurvived() {
			return survived;
		}

		public void setSurvived(double survived) {
			this.survived = survived;
		}

		public double getPclass() {
			return pClass;
		}

		public void setPclass(double pclass) {
			pClass = pclass;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public double getAge() {
			return age;
		}

		public void setFare(double fare) {
			this.fare = fare;
		}

		public String getCabin() {
			return cabin;
		}

		public void setCabin(String cabin) {
			this.cabin = cabin;
		}

		public String getEmbarked() {
			return embarked;
		}

		public void setEmbarked(String embarked) {
			this.embarked = embarked;
		}


	@Override
	public String toString() {
		return "Titanic{" +
				"PassengerId=" + passengerId +
				", Survived=" + survived +
				", Pclass=" + pClass +
				", Name='" + name + '\'' +
				", Sex='" + sex + '\'' +
				", Age=" + age +
				", SibSp=" + sibSp +
				", Parch=" + parch +
				", Ticket='" + ticket + '\'' +
				", Fare=" + fare +
				", Cabin='" + cabin + '\'' +
				", Embarked=" + embarked +
				'}';
	}

	@Override
	public String getGroup() {
		return this.getEmbarked();
	}

	@Override
	public void setGroup(String group) {
		this.embarked = group;
	}

	@Override
	public List<String> getAllGroup() {
		List<String> groups = new ArrayList<>();
		groups.add("S");
		groups.add("C");
		groups.add("Q");
		return groups;
	}

	@Override
	public Titanic add(List<String> fields) {
		passengerId = Double.parseDouble(fields.get(0));
		survived = Double.parseDouble(fields.get(1));
		pClass = Double.parseDouble(fields.get(2));
		name = fields.get(3);
		sex = fields.get(4);
		age = Double.parseDouble(fields.get(5));
		sibSp = Double.parseDouble(fields.get(6));
		parch = Double.parseDouble(fields.get(7));
		ticket = fields.get(8);
		fare = Double.parseDouble(fields.get(9));
		cabin = fields.get(10);
		return this;
	}

	public void setClassified(boolean b) {
		classified = b;
	}

	@Override
	public boolean getClassified() {
		return classified;
	}
}
