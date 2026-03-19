package simulation;

import physics.Physics;

public class OptimalBalancingController extends BalancingController {

    @Override
    public double controlWheelAngularAcceleration(double length, double angle, double angularVelocity, double dt) {
	return (Physics.G * Math.sin(angle) + length * ((angle / (dt * dt)) + (angularVelocity / dt)))
		/ Math.cos(angle);
    }
}
