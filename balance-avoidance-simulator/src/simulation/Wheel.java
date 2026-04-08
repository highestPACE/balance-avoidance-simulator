package simulation;

public class Wheel {

    private final double radius;
    private double angularVelocity = 0;
    private double angularAcceleration = 0;
    private double maxAngularAcceleration = Double.POSITIVE_INFINITY;

    public Wheel(double radius) {
	this.radius = radius;
    }

    public double getRadius() {
	return radius;
    }

    public double getAngularVelocity() {
	return angularVelocity;
    }
    
    protected void setAngularVelocity(double angularVelocity) {
	this.angularVelocity = angularVelocity;
    }

    public double getAngularAcceleration() {
	return angularAcceleration;
    }

    protected void setAngularAcceleration(double angularAcceleration) {
	this.angularAcceleration = Math.max(Math.min(getMaxAngularAcceleration(),
	angularAcceleration), -getMaxAngularAcceleration());
    }

    public double getMaxAngularAcceleration() {
	return maxAngularAcceleration;
    }

    public void setMaxAngularAcceleration(double maxAngularAcceleration) {
	this.maxAngularAcceleration = maxAngularAcceleration;
    }

}
