package simulation;

import physics.Physics;

public class BalancingController {

    public static double controlWheelAngularAcceleration(double length, double angle, double angularVelocity,
	    double dt) {
	if (angle == 0) {
	    return 0; // Result of Formula in else
	} else if (angle == Math.PI / 2) {
	    return Double.POSITIVE_INFINITY;
	} else if (angle == (3 * Math.PI) / 2) {
	    return Double.NEGATIVE_INFINITY;
	} else {
	    return (Physics.G * Math.sin(angle) + length * ((angle / (dt * dt)) + (angularVelocity / dt)))
		    / Math.cos(angle);
	}
    }
}
