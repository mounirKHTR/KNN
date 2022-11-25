package model;


import com.opencsv.bean.CsvBindByName;

import Interface.IPoint;


public class Titanic extends IPoint{
	
	    
	    @CsvBindByName(column = "PassengerId")
	   public double PassengerId;
	    @CsvBindByName(column = "Survived")
	   public double Survived;
	    @CsvBindByName(column = "Pclass")
	   public double Pclass;
	   @CsvBindByName(column = "Name")
	   public String Name;
	   @CsvBindByName(column = "Sex")
	   public String Sex;
	   @CsvBindByName(column = "Age")
	   public double Age;
	   @CsvBindByName(column = "SibSp")
	   public double SibSp;
	   @CsvBindByName(column = "Parch")
	   public double Parch;
	   @CsvBindByName(column = "Ticket")
	   public String Ticket;
	   @CsvBindByName(column = "Fare")
	   public double Fare;
	   @CsvBindByName(column = "Cabin")
	   public String Cabin;
	   @CsvBindByName(column = "Embarked")
	   public char Embarked;
	    
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

		public void setAge(double age) {
			Age = age;
		}

		public double getSibSp() {
			return SibSp;
		}

		public void setSibSp(double sibSp) {
			SibSp = sibSp;
		}

		public double getParch() {
			return Parch;
		}

		public void setParch(double parch) {
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
}
