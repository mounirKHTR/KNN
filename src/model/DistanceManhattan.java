package model;
public class DistanceManhattan implements IDistance{

	

	@Override
	public double distanceBetween(Pokemon p1, Pokemon p2) {
		double total = 0;
		total += (p1.getCaptureRate() - p2.getCaptureRate());
		total += ((double)p1.getSpeed() - (double)p2.getSpeed());
		total += (p1.getBaseEggSteps() - p2.getBaseEggSteps());
		total += ((double)p1.getExperienceGrowth() - (double)p2.getExperienceGrowth());
		return total;
	}

	@Override
	public double distanceBetween(DatasetIris dt, Iris p1, Iris p2,Number_Normalizer norm) {
		double total = 0;
		total += (double)norm.normalize(p1.getSepalLength()) - (double)norm.normalize(p2.getSepalLength());
		total += (double)norm.normalize(p1.getSepalWidth()) - (double)norm.normalize(p2.getSepalWidth());
		total += (double)norm.normalize(p1.getPetalLength()) - (double)norm.normalize(p2.getPetalLength());
		total += (double)norm.normalize(p1.getPetalWidth()) - (double)norm.normalize(p2.getPetalWidth());
		
		return total;
	}

}
