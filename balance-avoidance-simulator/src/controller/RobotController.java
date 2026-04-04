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

    private double test(Robot robot) {
	double maxBreakAngle = calcMaxAngle(robot);
	System.out.println("LinVel: " + robot.getWheelLinearVelocity());
	// robot = new Robot(0.1, 0.1, Double.POSITIVE_INFINITY, 0);
	return achieveAngle(maxBreakAngle, robot);
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

	    double maxBreak = -direction * robot.getWheelMaxAngularAcceleration() * robot.getWheelRadius();

	    double bufferFactor = 7;
	    breakAngle *= bufferFactor;
	    maxBreak = calcAccelerationForAngle(maxBreakAngle / bufferFactor);

	    double x2 = robot.getXPosition() + tMin * robot.getWheelLinearVelocity()
		    + ((tMin * tMin) / 2) * (achieveAngle(maxBreakAngle, robot) * robot.getWheelRadius()) - Math
			    .pow(robot.getWheelLinearVelocity()
				    + tMin * (achieveAngle(maxBreakAngle, robot) * robot.getWheelRadius()), 2)
			    / (2 * maxBreak);

	    // System.out.println(x2);

	    if (direction < 0 && x2 < desiredPosition || direction > 0 && x2 > desiredPosition) {
		/*
		 * System.out.println( "Breaking: Pos: " + robot.getXPosition() + ", LinVel: " +
		 * robot.getWheelLinearVelocity() + ", Angle: " + robot.getAngle() + ", Acc: " +
		 * achieveAngle(breakAngle, robot));
		 */
		return achieveAngle(breakAngle, robot);
	    }
	}

	double desLinAcc = (2 / (tMin * tMin))
		* (desiredPosition - robot.getXPosition() - tMin * robot.getWheelLinearVelocity());
	/*
	 * System.out.println("Accelerating: Pos: " + robot.getXPosition() +
	 * ", LinVel: " + robot.getWheelLinearVelocity() + ", Angle: " +
	 * robot.getAngle() + ", Acc: " +
	 * achieveAngle(calcAngleForAcceleration(desLinAcc), robot));
	 */
	return achieveAngle(calcAngleForAcceleration(desLinAcc), robot);
    }

    public double getTMin() {
	return tMin;
    }

    public void setTMin(double tMin) {
	this.tMin = tMin;
    }
}
