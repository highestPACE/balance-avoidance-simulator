package simulation;

import physics.Physics;

public class BalancingController {

    public static double controlWheelAngularAcceleration(double dt, double length, double angle) {
	if (angle == 0) {
	    return 0;
	} else if (angle == Math.PI / 2) {
	    return Double.POSITIVE_INFINITY;
	} else if (angle == (3 * Math.PI) / 2) {
	    return Double.NEGATIVE_INFINITY;
	} else {
	    return (Physics.G * Math.sin(angle) + (angle * length) / dt) / Math.cos(angle);
	}
    }
}
