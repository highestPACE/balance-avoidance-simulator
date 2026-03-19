package simulation;

public abstract class BalancingController {

    public abstract double controlWheelAngularAcceleration(double length, double angle, double angularVelocity,
	    double dt);
}
