

public class DistanceManhattan implements IDistance{

	@Override
	public double distanceBetween(IPoint p1, IPoint p2) {
		double total = 0;
		total += (p1.getCaptureRate() - p2.getCaptureRate())/MethodeKnn.amplCap;
		total += ((double)p1.getSpeed() - (double)p2.getSpeed())/MethodeKnn.amplSpd;
		total += (p1.getBaseEggSteps() - p2.getBaseEggSteps())/MethodeKnn.amplEgg;
		total += ((double)p1.getExperienceGrowth() - (double)p2.getExperienceGrowth())/MethodeKnn.amplExp;
		return total;
	}

}
