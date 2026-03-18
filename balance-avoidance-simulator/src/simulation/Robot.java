package simulation;

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
    
    public double getLength() {
        return length;
    }
    
    public Wheel getWheel() {
	return wheel;
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
