package model;


import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import Interface.IPoint;


public class Titanic extends IPoint{
	
	    
	    @CsvBindByName(column = "PassengerId")
	   public int PassengerId;
	    @CsvBindByName(column = "Survived")
	   public int Survived;
	    @CsvBindByName(column = "Pclass")
	   public int Pclass;
	   @CsvBindByName(column = "Name")
	   public String Name;
	   @CsvBindByName(column = "Sex")
	   public String Sex;
	   @CsvBindByName(column = "Age")
	   public double Age;
	   @CsvBindByName(column = "SibSp")
	   public int SibSp;
	   @CsvBindByName(column = "Parch")
	   public int Parch;
	   @CsvBindByName(column = "Ticket")
	   public String Ticket;
	   @CsvBindByName(column = "Fare")
	   public double Fare;
	   @CsvBindByName(column = "Cabin")
	   public String Cabin;
	   @CsvBindByName(column = "Embarked")
	   public char Embarked;

		public int getPassengerId() {
			return PassengerId;
		}

		public void setPassengerId(int passengerId) {
			PassengerId = passengerId;
		}

		public int getSurvived() {
			return Survived;
		}

		public void setSurvived(int survived) {
			Survived = survived;
		}

		public int getPclass() {
			return Pclass;
		}

		public void setPclass(int pclass) {
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

		public void setAge(int age) {
			Age = age;
		}

		public int getSibSp() {
			return SibSp;
		}

		public void setSibSp(int sibSp) {
			SibSp = sibSp;
		}

		public int getParch() {
			return Parch;
		}

		public void setParch(int parch) {
			Parch = parch;
		}

		public String getTicket() {
			return Ticket;
		}

		public void setTicket(String ticket) {
			Ticket = ticket;
		}

		public double getFare() {
			return Fare;
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
}
