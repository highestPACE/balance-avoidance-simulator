package controller;

import physics.Physics;
import simulation.Robot;

public class RobotController {

    private double tMin;

    public RobotController(double tMin) {
	setTMin(tMin);
    }

    public double controlWheelAngularAcceleration(Robot robot) {
	return achievePosition(0.1, robot);
    }

    private double achieveAngle(double desiredAngle, Robot robot) {
	return (Physics.G * Math.sin(robot.getAngle()) - ((2 * robot.getLength()) / (tMin * tMin))
		* (desiredAngle - robot.getAngle() - tMin * robot.getAngularVelocity()))
		/ (Math.cos(robot.getAngle()) * robot.getWheelRadius());
    }

    private double achievePosition(double desiredPosition, Robot robot) {
	double linearAcceleration = (2 / (tMin * tMin))
		* (desiredPosition - robot.getXPosition() - tMin * robot.getWheelLinearVelocity());
	double desiredAngle = Math.atan(linearAcceleration / Physics.G);
	return achieveAngle(desiredAngle, robot);
    }

    public double getTMin() {
	return tMin;
    }

    public void setTMin(double tMin) {
	this.tMin = tMin;
    }
}
