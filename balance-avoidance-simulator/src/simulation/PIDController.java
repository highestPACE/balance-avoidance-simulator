package simulation;

public class PIDController {

    public double control(double pFactor, double iFactor, double dFactor, double pValue, double iValue, double dValue) {
	return - pFactor * pValue - iFactor * iValue - dFactor * dValue;
    }
}