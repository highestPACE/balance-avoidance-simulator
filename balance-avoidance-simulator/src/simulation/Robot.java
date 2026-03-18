package simulation;

import physics.InvertedPendulum;

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
    
    public void simulate(double dt) {
	wheel.simulate(dt);
	setXPosition(getXPosition() + wheel.calcLinearVelocity() * dt);
	
	double angle = getAngle();
	double velocity = getAngularVelocity();
	double acceleration = InvertedPendulum.calcAngularAcceleration(angle, getLength(), getWheelAngularAcceleration());
	
	velocity += acceleration * dt;
	angle += velocity * dt;
	
	setAngularVelocity(velocity);
	setAngle(angle);
    }
    
    public double getLength() {
        return length;
    }
    
    public double getWheelRadius() {
        return wheel.getRadius();
    }

    public double getWheelAngularVelocity() {
        return wheel.getAngularVelocity();
    }
    
    public double getWheelAngularAcceleration() {
        return wheel.getAngularAcceleration();
    }

    public void setWheelAngularAcceleration(double angularAcceleration) {
        wheel.setAngularAcceleration(angularAcceleration);
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle % (2 * Math.PI);
    }
    
    public double getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(double angularVelocity) {
        this.angularVelocity = angularVelocity;
    }
    
    public double getXPosition() {
	return xPosition;
    }
    
    public void setXPosition(double xPosition) {
	this.xPosition = xPosition;
    }
}
