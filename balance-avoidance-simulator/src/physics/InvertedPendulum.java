package physics;

public class InvertedPendulum {

    public static double calcAngularAcceleration(double angle, double length) {
	return Math.sin(angle) * Physics.G / length;
    }

    public static double calcAngularAcceleration(double angle, double length, double wheelAngularAcceleration) {
	return (Physics.G * Math.sin(angle) - wheelAngularAcceleration * Math.cos(angle)) / length;
    }
}
