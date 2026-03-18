package physics;

public class Physics {

    public static final double G = 9.81;

    public static double valueChange(double value, double rate, double dt) {
	return value + rate * dt;
    }

    public static double calcLinearVelocity(double angularVelocity, double radius) {
	return angularVelocity * radius;
    }
}
