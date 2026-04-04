package simulation;

import physics.InvertedPendulum;
import physics.Physics;

public class Robot {

    private final double length;
    private final Wheel wheel;
    private double angle = 0;
    private double angularVelocity = 0;
    private double xPosition = 0;

    public Robot(double length, double wheelRadius) {
	this.length = length;
	this.wheel = new Wheel(wheelRadius);
    }

    public Robot(double length, double wheelRadius, double angle) {
	this(length, wheelRadius);
	setAngle(angle);
    }

    public Robot(double length, double wheelRadius, double wheelAngularVelocity, double wheelAngularAcceleration,
	    double wheelMaxAngularAcceleration, double angle, double angularVelocity, double xPosition) {
	this(length, wheelRadius, angle);
	setWheelAngularVelocity(wheelAngularVelocity);
	setWheelAngularAcceleration(wheelAngularAcceleration);
	setWheelMaxAngularAcceleration(wheelMaxAngularAcceleration);
	setAngularVelocity(angularVelocity);
	setXPosition(xPosition);
    }

    public Robot copy() {
	return new Robot(getLength(), getWheelRadius(), getWheelAngularVelocity(), getWheelAngularAcceleration(),
		getWheelMaxAngularAcceleration(), getAngle(), getAngularVelocity(), getXPosition());
    }

    public double getLength() {
	return length;
    }

    // Angle is in [-pi, pi] (0 is pointing to the top)
    public double getAngle() {
	return angle;
    }

    // Angle gets translated into [-pi, pi] (0 is pointing to the top)
    protected void setAngle(double angle) {
	double tmp = ((angle % (2 * Math.PI)) + 2 * Math.PI) % (2 * Math.PI);
	if (tmp <= Math.PI) {
	    this.angle = tmp;
	} else {
	    this.angle = tmp - 2 * Math.PI;
	}
    }

    public double getAngularVelocity() {
	return angularVelocity;
    }

    protected void setAngularVelocity(double angularVelocity) {
	this.angularVelocity = angularVelocity;
    }

    public double getAngularAcceleration() {
	return InvertedPendulum.calcAngularAcceleration(getAngle(), getLength(), getWheelAngularAcceleration());
    }

    public double getXPosition() {
	return xPosition;
    }

    protected void setXPosition(double xPosition) {
	this.xPosition = xPosition;
    }

    public double getWheelRadius() {
	return wheel.getRadius();
    }

    public double getWheelAngularVelocity() {
	return wheel.getAngularVelocity();
    }

    protected void setWheelAngularVelocity(double angularVelocity) {
	wheel.setAngularVelocity(angularVelocity);
    }

    public double getWheelAngularAcceleration() {
	return wheel.getAngularAcceleration();
    }

    protected void setWheelAngularAcceleration(double angularAcceleration) {
	wheel.setAngularAcceleration(angularAcceleration);
    }

    public double getWheelLinearVelocity() {
	return Physics.calcLinearVelocity(getWheelAngularVelocity(), getWheelRadius());
    }

    public double getWheelMaxAngularAcceleration() {
	return wheel.getMaxAngularAcceleration();
    }

    public void setWheelMaxAngularAcceleration(double maxAngularAcceleration) {
	wheel.setMaxAngularAcceleration(maxAngularAcceleration);
    }
}
