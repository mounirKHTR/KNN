package model;

import com.opencsv.bean.CsvBindByName;

import Interface.IPoint;

public class Pokemon extends IPoint {
	

	    @CsvBindByName(column = "name") 
	    public String name;
	    @CsvBindByName(column = "attack")
	    public double attack;
	    @CsvBindByName(column = "base_egg_steps")
	    public double baseEggSteps;
	    @CsvBindByName(column = "capture_rate")
	    public double captureRate;
	    @CsvBindByName(column = "defense")
	    public double defense;
	    @CsvBindByName(column = "experience_growth")
	    public double experienceGrowth;
	    @CsvBindByName(column = "hp")
	    public double hp;
	    @CsvBindByName(column = "sp_attack")
	    public double spAttack;
	    @CsvBindByName(column = "sp_defense") 
	    public double spDefense;
	    @CsvBindByName(column = "type")
	    public String type;
	    @CsvBindByName(column = "type2")
	    public String type2;
	    @CsvBindByName(column = "speed")
	    public double speed;
	    @CsvBindByName(column = "is_legendary")
	    public boolean isLegendary;

	    public Pokemon() {
	    }

	    /*public Pokemon (String name,double attack, double baseEggSteps,double captureRate,double defense,double experienceGrowth,
	            double hp,double spAttack, double spDefense, String type, String type2, double speed, boolean isLegendary) {
	        this.attack = attack;
	        this.baseEggSteps = baseEggSteps;
	        this.captureRate = captureRate;
	        this.defense = defense;
	        this.experienceGrowth = experienceGrowth;
	        this.hp = hp;
	        this.isLegendary = isLegendary;
	        this.name = name;
	        this.spAttack = spAttack;
	        this.spDefense = spDefense;
	        this.speed = speed;
	        this.type = type;
	        this.type2 = type2;
	    }*/
	    public String getName() {
	        return name;
	    }

	    public double getAttack() {
	        return attack;
	    }

	    public double getBaseEggSteps() {
	        return baseEggSteps;
	    }
	    
	    public double getCaptureRate() {
	        return captureRate;
	    }

	    public double getDefense() {
	        return defense;
	    }

	    public double getExperienceGrowth() {
	        return experienceGrowth;
	    }

	    public double getHp() {
	        return hp;
	    }

	    public double getSpAttack() {
	        return spAttack;
	    }

	    public double getSpDefense() {
	        return spDefense;
	    }

	    public String getType() {
	        return type;
	    }

	    public String getType2() {
	        return type2;
	    }

	    public double getSpeed() {
	        return speed;
	    }

	    public boolean isLegendary() {
	        return isLegendary;
	    }



	  

	    @Override
	    public String toString() {
	        return "Pokemon [nom=" + name + ", attack=" + attack + ", baseEggSteps=" + baseEggSteps + ", captureRate="
	                + captureRate + ", defense=" + defense + ", experienceGrowth=" + experienceGrowth + ", hp=" + hp
	                + ", spAttack=" + spAttack + ", spDefense=" + spDefense + ", type=" + type + ", type2=" + type2
	                + ", speed=" + speed + ", isLegendary=" + isLegendary + "]";

	    }
	

}