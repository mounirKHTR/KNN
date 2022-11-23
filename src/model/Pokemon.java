package model;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import Interface.IPoint;

public class Pokemon extends IPoint {
	

	    @CsvBindByName(column = "name") 
	    public String name;
	    @CsvBindByName(column = "attack")
	    public int attack;
	    @CsvBindByName(column = "base_egg_steps")
	    public int baseEggSteps;
	    @CsvBindByName(column = "capture_rate")
	    public double captureRate;
	    @CsvBindByName(column = "defense")
	    public int defense;
	    @CsvBindByName(column = "experience_growth")
	    public int experienceGrowth;
	    @CsvBindByName(column = "hp")
	    public int hp;
	    @CsvBindByName(column = "sp_attack")
	    public int spAttack;
	    @CsvBindByName(column = "sp_defense") 
	    public int spDefense;
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
	    
	    public Pokemon (String name,int attack, int baseEggSteps,double captureRate,int defense,int experienceGrowth,
	            int hp,int spAttack, int spDefense, String type, String type2, double speed, boolean isLegendary) {
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
	    }
	    public String getName() {
	        return name;
	    }

	    public int getAttack() {
	        return attack;
	    }

	    public int getBaseEggSteps() {
	        return baseEggSteps;
	    }
	    
	    public double getCaptureRate() {
	        return captureRate;
	    }

	    public int getDefense() {
	        return defense;
	    }

	    public int getExperienceGrowth() {
	        return experienceGrowth;
	    }

	    public int getHp() {
	        return hp;
	    }

	    public int getSpAttack() {
	        return spAttack;
	    }

	    public int getSpDefense() {
	        return spDefense;
	    }

	    public String getType() {
	        return type;
	    }

	    public String     getType2() {
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

		@Override
		public String getGroup() {
			if (isLegendary()) {
				return "Legendary";
			}
			return "Common";
		}

		@Override
		public List<String> getAllGroup() {
			List<String> groups = new ArrayList<String>();
			groups.add("Legendary");
			groups.add("Common");
			return groups;
		}
	

}
