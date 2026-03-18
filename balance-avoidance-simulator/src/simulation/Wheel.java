package simulation;

public class Wheel {

    private final double radius;
    private double angularVelocity = 0;
    private double angularAcceleration = 0;
    private double maxAngularAcceleration = 10;
    
    public Wheel(double radius) {
	this.radius = radius;
    }
    
    public void simulate(double dt) {
	angularVelocity += getAngularAcceleration() * dt;
    }
    
    public double calcLinearVelocity() {
	return getAngularVelocity() * getRadius();
    }
    
    public double calcLinearAcceleration() {
	return getAngularAcceleration() * getRadius();
    }
    
    public double getRadius() {
        return radius;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }
    
    public double getAngularAcceleration() {
	return angularAcceleration;
    }
    
    public void setAngularAcceleration(double angularAcceleration) {
	// this.angularAcceleration = Math.max(Math.min(getMaxAngularAcceleration(), angularAcceleration), -getMaxAngularAcceleration());
	this.angularAcceleration = angularAcceleration;
    }

    public double getMaxAngularAcceleration() {
        return maxAngularAcceleration;
    }

    public void setMaxAngularAcceleration(double maxAngularAcceleration) {
        this.maxAngularAcceleration = maxAngularAcceleration;
    }
    
}
