package controller;

import physics.Physics;
import simulation.Robot;

public class RobotController {

    private double tMin;

    public RobotController(double tMin) {
	setTMin(tMin);
    }

    public double controlWheelAngularAcceleration(Robot robot) {
	return achievePosition(10, robot);
    }

    private double achieveAngle(double desiredAngle, Robot robot) {
	double maxAngle = calcMaxAngle(robot);
	desiredAngle = Math.max(Math.min(desiredAngle, maxAngle), -maxAngle);
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
    
    private double calcAccelerationForAngle(double angle) {
	return Physics.G * Math.tan(angle);
    }

    private double achievePosition(double desiredPosition, Robot robot) {
	double direction = (int) Math.signum(desiredPosition - robot.getXPosition());

	if ((int) Math.signum(robot.getWheelLinearVelocity()) == direction) {
	    Double desLinBreak = -(Math.pow(robot.getWheelLinearVelocity(), 2)
		    / (2 * (desiredPosition - robot.getXPosition())));
	    if (desLinBreak.equals(Double.NaN)) {
		desLinBreak = 0.;
	    }

	    double breakAngle = calcAngleForAcceleration(desLinBreak);

	    double maxBreakAngle = -direction * calcMaxAngle(robot);
	    if (Math.abs(breakAngle) >= Math.abs(maxBreakAngle)) {
		return achieveAngle(breakAngle, robot);
	    }

	    double maxBreak = -direction * robot.getWheelMaxAngularAcceleration() * robot.getWheelRadius();
	    
	    double bufferFactor =  0.1458; // Number from testing
	    maxBreak = calcAccelerationForAngle(maxBreakAngle * bufferFactor);

	    double x2 = robot.getXPosition() + tMin * robot.getWheelLinearVelocity()
		    + ((tMin * tMin) / 2) * (achieveAngle(maxBreakAngle, robot) * robot.getWheelRadius()) - Math
			    .pow(robot.getWheelLinearVelocity()
				    + tMin * (achieveAngle(maxBreakAngle, robot) * robot.getWheelRadius()), 2)
			    / (2 * maxBreak);

	    if (direction < 0 && x2 < desiredPosition || direction > 0 && x2 > desiredPosition) {
		return achieveAngle(desLinBreak, robot);
	    }
	}

	double desLinAcc = (2 / (tMin * tMin))
		* (desiredPosition - robot.getXPosition() - tMin * robot.getWheelLinearVelocity());
	return achieveAngle(calcAngleForAcceleration(desLinAcc), robot);
    }

    public double getTMin() {
	return tMin;
    }

    public void setTMin(double tMin) {
	this.tMin = tMin;
    }
}
