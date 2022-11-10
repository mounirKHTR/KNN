

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
	public double distanceBetween( Iris p1, Iris p2) {
		double total = 0;
		total += (double)p1.getSepalLength() - (double)p2.getSepalLength();
		total += (double)p1.getSepalWidth() - (double)p2.getSepalWidth();
		total += (double)p1.getPetalLength() - (double)p2.getPetalLength();
		total += (double)p1.getPetalWidth() - (double)p2.getPetalWidth();
		
		return total;
	}

}
