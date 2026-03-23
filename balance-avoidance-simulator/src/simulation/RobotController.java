package simulation;

import physics.Physics;

public class RobotController {

    private double tMin;

    public RobotController(double tMin) {
	setTMin(tMin);
    }

    public double controlWheelAngularAcceleration(double radius, double length, double angle, double angularVelocity) {
	return achieveBalance(radius, length, angle, angularVelocity);
    }

    private double achieveBalance(double radius, double length, double angle, double angularVelocity) {
	return (Physics.G * Math.sin(angle) + ((2 * length) / (tMin * tMin)) * (angle + tMin * angularVelocity))
		/ (Math.cos(angle) * radius);
    }

    public double getTMin() {
	return tMin;
    }

    public void setTMin(double tMin) {
	this.tMin = tMin;
    }
}
