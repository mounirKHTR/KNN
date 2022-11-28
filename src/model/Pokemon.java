package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
		public boolean classified = true;

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

		@Override
		public String getGroup() {
			if (!classified) {
				return null;
			}else if (isLegendary()) {
				return "Legendary";
			}
			return "Common";
		}

	@Override
	public void setGroup(String group) {
		this.isLegendary = Objects.equals(group, "Legendary");

	}

	@Override
		public List<String> getAllGroup() {
			List<String> groups = new ArrayList<>();
			groups.add("Legendary");
			groups.add("Common");
			return groups;
		}

		@Override
		public Pokemon add(List<String> fields) {
			name = fields.get(0);
			attack = Double.parseDouble(fields.get(1));
			baseEggSteps = Double.parseDouble(fields.get(2));
			captureRate = Double.parseDouble(fields.get(3));
			defense = Double.parseDouble(fields.get(4));
			experienceGrowth = Double.parseDouble(fields.get(5));
			hp = Double.parseDouble(fields.get(6));
			spAttack = Double.parseDouble(fields.get(7));
			spDefense = Double.parseDouble(fields.get(8));
			type = fields.get(9);
			type2 = fields.get(10);
			speed = Double.parseDouble(fields.get(11));
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
