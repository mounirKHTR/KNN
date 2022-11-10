

public class DistanceEuclidienne implements IDistance{

	@Override
	public double distanceBetween(Pokemon p1, Pokemon p2) {
		double total = 0;
		total += (Math.pow(p1.getCaptureRate() - p2.getCaptureRate(),2));
		total += (Math.pow((double)p1.getSpeed() - (double)p2.getSpeed(),2));
		total += (Math.pow(p1.getBaseEggSteps() - p2.getBaseEggSteps(),2));
		total += (Math.pow((double)p1.getExperienceGrowth() - (double)p2.getExperienceGrowth(),2));
		return Math.sqrt(total);
	}
	
	public double distanceBetween(Iris p1, Iris p2) {
		double total = 0;
		total += (Math.pow((double)p1.getSepalLength() - (double)p2.getSepalLength(),2));
		total += (Math.pow((double)p1.getSepalWidth() - (double)p2.getSepalWidth(),2));
		total += (Math.pow((double)p1.getPetalLength() - (double)p2.getPetalLength(),2));
		total += (Math.pow((double)p1.getPetalWidth() - (double)p2.getPetalWidth(),2));
		
		return Math.sqrt(total);
	}

}
