

public class DistanceEuclidienne implements IDistance{

	@Override
	public double distanceBetween(IPoint p1, IPoint p2) {
		double total = 0;
		total += (Math.pow(p1.getCaptureRate() - p2.getCaptureRate(),2))/MethodeKnn.amplCap;
		total += (Math.pow((double)p1.getSpeed() - (double)p2.getSpeed(),2))/MethodeKnn.amplSpd;
		total += (Math.pow(p1.getBaseEggSteps() - p2.getBaseEggSteps(),2))/MethodeKnn.amplEgg;
		total += (Math.pow((double)p1.getExperienceGrowth() - (double)p2.getExperienceGrowth(),2))/MethodeKnn.amplExp;
		return total;
	}

}
