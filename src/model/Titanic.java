package model;


import com.opencsv.bean.CsvBindByName;

import Interface.IPoint;


public class Titanic extends IPoint{
	
	    
	    @CsvBindByName(column = "PassengerId")
	    int PassengerId;
	    @CsvBindByName(column = "Survived")
	    int Survived;
	    @CsvBindByName(column = "Pclass")
	    int Pclass;
	    @CsvBindByName(column = "Name")
	    String Name;
	    @CsvBindByName(column = "Sex")
	    String Sex;
	    @CsvBindByName(column = "Age")
	    int Age;
	    @CsvBindByName(column = "SibSp")
	    int SibSp;
	    @CsvBindByName(column = "Parch")
	    int Parch;
	    @CsvBindByName(column = "Ticket")
	    String Ticket;
	    @CsvBindByName(column = "Fare")
	    double Fare;
	    @CsvBindByName(column = "Cabin")
	    String Cabin;
	    @CsvBindByName(column = "Embarked")
	    char Embarked;
	    
	    public Titanic(int PassengerId, int Survived, int Pclass, 
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
	    }

		
}
