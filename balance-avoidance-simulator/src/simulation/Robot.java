package simulation;

import physics.InvertedPendulum;

public class Robot {

    private final double length;
    private double angle = 0;
    private double angularVelocity = 0;
    
    public Robot(double length) {
	this.length = length;
    }
    
    public void simulate(double dt) {
	double angle = getAngle();
	double velocity = getAngularVelocity();
	double acceleration = InvertedPendulum.calcAngularAcceleration(angle, getLength());
	
	velocity += acceleration * dt;
	angle += velocity * dt;
	
	setAngularVelocity(velocity);
	setAngle(angle);
    }
    
    public double getLength() {
        return length;
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
}
