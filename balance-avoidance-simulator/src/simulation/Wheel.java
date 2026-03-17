package simulation;

public class Wheel {

    private final double radius;
    private double angularVelocity = 0;
    private double angularAcceleration = 0;
    
    public Wheel(double radius) {
	this.radius = radius;
    }
    
    public void simulate(double dt) {
	angularVelocity += getAngularAcceleration() * dt;
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
	this.angularAcceleration = angularAcceleration;
    }
    
}
