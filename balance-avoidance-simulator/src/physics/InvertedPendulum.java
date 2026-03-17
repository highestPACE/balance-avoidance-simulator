package physics;

public class InvertedPendulum {
    
    private static final double G = 9.81;

    public static double calcAngularAcceleration(double angle, double length) {
	return Math.sin(angle) * G / length;
    }
}
