package simulation;

public class PIDBalancingController {

    public double control(double pFactor, double iFactor, double dFactor, double angle, double angularVelocity) {
	return - pFactor * angle - dFactor * angularVelocity; // Does not use I-Term
    }
}