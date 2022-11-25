package model;


import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import Interface.IPoint;


public class Titanic extends IPoint{
	
	    
	    @CsvBindByName(column = "PassengerId")
	   public double PassengerId;
	    @CsvBindByName(column = "Survived")
	    double Survived;
	    @CsvBindByName(column = "Pclass")
	    double Pclass;
	    @CsvBindByName(column = "Name")
	    String Name;
	    @CsvBindByName(column = "Sex")
	    String Sex;
	    @CsvBindByName(column = "Age")
	    double Age;
	    @CsvBindByName(column = "SibSp")
	    double SibSp;
	    @CsvBindByName(column = "Parch")
	    double Parch;
	    @CsvBindByName(column = "Ticket")
	    String Ticket;
	    @CsvBindByName(column = "Fare")
	    double Fare;
	    @CsvBindByName(column = "Cabin")
	    String Cabin;
	    @CsvBindByName(column = "Embarked")
	    char Embarked;
	    
	    /*public Titanic(int PassengerId, int Survived, int Pclass, 
	            String Name, String Sex, int Age, int SibSp, int Parch, 
	            String Ticket, double Fare, String Cabin, char Embarked) {
	        
	        this.PassengerId = PassengerId;
	        this.Survived = Survived;
	        this.Pclass = Pclass;
	        this.Name = Name;
	        this.Sex= Sex;
	        this.Age = Age;
	        this.SibSp = SibSp;
	        this.Parch = Parch;
	        this.Ticket = Ticket;
	        this.Fare = Fare;
	        this.Cabin = Cabin;
	        this.Embarked = Embarked; 
	    }*/

		public double getPassengerId() {
			return PassengerId;
		}

		public void setPassengerId(int passengerId) {
			PassengerId = passengerId;
		}

		public double getSurvived() {
			return Survived;
		}

		public void setSurvived(double survived) {
			Survived = survived;
		}

		public double getPclass() {
			return Pclass;
		}

		public void setPclass(double pclass) {
			Pclass = pclass;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getSex() {
			return Sex;
		}

		public void setSex(String sex) {
			Sex = sex;
		}

		public double getAge() {
			return Age;
		}

		public void setFare(double fare) {
			Fare = fare;
		}

		public String getCabin() {
			return Cabin;
		}

		public void setCabin(String cabin) {
			Cabin = cabin;
		}

		public char getEmbarked() {
			return Embarked;
		}

		public void setEmbarked(char embarked) {
			Embarked = embarked;
		}


	@Override
	public String toString() {
		return "Titanic{" +
				"PassengerId=" + PassengerId +
				", Survived=" + Survived +
				", Pclass=" + Pclass +
				", Name='" + Name + '\'' +
				", Sex='" + Sex + '\'' +
				", Age=" + Age +
				", SibSp=" + SibSp +
				", Parch=" + Parch +
				", Ticket='" + Ticket + '\'' +
				", Fare=" + Fare +
				", Cabin='" + Cabin + '\'' +
				", Embarked=" + Embarked +
				'}';
	}

	@Override
	public String getGroup() {
		return "Embarked";
	}

	@Override
	public List<String> getAllGroup() {
		List<String> groups = new ArrayList<String>();
		groups.add("S");
		groups.add("C");
		groups.add("Q");
		return groups;
	}

	@Override
	public IPoint add(List<String> fields) {
		PassengerId = Double.parseDouble(fields.get(0));
		Survived = Double.parseDouble(fields.get(1));
		Pclass = Double.parseDouble(fields.get(2));
		Name = fields.get(3);
		Sex = fields.get(4);
		Age = Double.parseDouble(fields.get(5));
		SibSp = Double.parseDouble(fields.get(6));
		Parch = Double.parseDouble(fields.get(7));
		Ticket = fields.get(8);
		Fare = Double.parseDouble(fields.get(9));
		Cabin = fields.get(10);
		return this;
	}
}
