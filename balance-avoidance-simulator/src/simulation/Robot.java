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

 // Angle is in [0, 2pi] (0 is pointing to the top)
    public double getAngle() {
	return angle;
    }

    // Angle gets translated into [0, 2pi] (0 is pointing to the top)
    public void setAngle(double angle) {
	this.angle = ((angle % (2 * Math.PI)) + 2 * Math.PI) % (2 * Math.PI);
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
