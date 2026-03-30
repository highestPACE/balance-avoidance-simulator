package controller;

import physics.Physics;
import simulation.Robot;

public class RobotController {

    private double tMin;

    public RobotController(double tMin) {
	setTMin(tMin);
    }

    public double controlWheelAngularAcceleration(Robot robot) {
	return achievePosition(1, robot);
    }

    private double achieveAngle(double desiredAngle, Robot robot) {
	return (Physics.G * Math.sin(robot.getAngle()) - ((2 * robot.getLength()) / (tMin * tMin))
		* (desiredAngle - robot.getAngle() - tMin * robot.getAngularVelocity()))
		/ (Math.cos(robot.getAngle()) * robot.getWheelRadius());
    }

    private double calcAngleForAcceleration(double linearAcceleration) {
	return Math.atan(linearAcceleration / Physics.G);
    }

    private double calcMaxAngle(Robot robot) {
	return calcAngleForAcceleration(robot.getWheelMaxAngularAcceleration() * robot.getWheelRadius());
    }

    private double achievePosition(double desiredPosition, Robot robot) {
	double desLinAcc = (2 / (tMin * tMin))
		* (desiredPosition - robot.getXPosition() - tMin * robot.getWheelLinearVelocity());
	double phi1 = calcAngleForAcceleration(desLinAcc);
	double xDotDot0 = achieveAngle(phi1, robot) * robot.getWheelRadius();
	double maxAngle = calcMaxAngle(robot);
	double maxAcc = robot.getWheelMaxAngularAcceleration() * robot.getWheelRadius();
	if (robot.getXPosition() < desiredPosition) {
	    maxAngle = -maxAngle;
	    maxAcc = -maxAcc;
	}
	double x2 = robot.getXPosition() + tMin * robot.getWheelLinearVelocity() + ((tMin * tMin) / 2) * (achieveAngle(maxAngle, robot) * robot.getWheelRadius())
		- Math.pow(robot.getWheelLinearVelocity() + tMin * (achieveAngle(maxAngle, robot) * robot.getWheelRadius()), 2)
		/ (2 * maxAcc);
	if ((robot.getXPosition() < desiredPosition && x2 > desiredPosition)
		|| (robot.getXPosition() > desiredPosition && x2 < desiredPosition)) {
	    return achieveAngle(maxAngle, robot);
	}
	return achieveAngle(phi1, robot);
    }

    public double getTMin() {
	return tMin;
    }

    public void setTMin(double tMin) {
	this.tMin = tMin;
    }
}
