package rendering;

import simulation.Simulation;

import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends JPanel {

    private final Simulation simulation;

    public SimulationPanel(Simulation simulation) {
	this.simulation = simulation;
	setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);

	Graphics g2 = (Graphics2D) g;

	int width = getWidth();
	int height = getHeight();

	double groundY = height / 2;
	double scale = 1000;
	double wheelRadius = simulation.getWheelRadius();

	double wheelX = simulation.getRobotXPosition() * scale + (width / 2);
	double wheelY = groundY - wheelRadius * scale;

	double topX = wheelX + Math.sin(simulation.getRobotAngle()) * simulation.getRobotLength() * scale;
	double topY = wheelY - Math.cos(simulation.getRobotAngle()) * simulation.getRobotLength() * scale;

	// ground
	g2.drawLine(0, (int) groundY, width, (int) groundY);

	// Wheel
	g2.drawOval((int) (wheelX - (wheelRadius * scale)), (int) (wheelY - (wheelRadius * scale)),
		(int) (2 * wheelRadius * scale), (int) (2 * wheelRadius * scale));

	// Body
	g2.drawLine((int) wheelX, (int) wheelY, (int) topX, (int) topY);

	// Text
	g2.drawString(String.format("Robot length: %.3f", simulation.getRobotLength()), 10, 10);
	g2.drawString(String.format("Wheel radius: %.3f", simulation.getWheelRadius()), 10, 20);
	g2.drawString(String.format("Robot x-position: %.3f", simulation.getRobotXPosition()), 10, 30);
	g2.drawString(String.format("Robot angle: %.3f", simulation.getRobotAngle()), 10, 40);
	g2.drawString(String.format("Robot angular velocity: %.3f", simulation.getRobotAngularVelocity()), 10, 50);
	g2.drawString(String.format("Robot angular acceleration: %.3f", simulation.getRobotAngularVelocity()), 10, 60);
	g2.drawString(String.format("Wheel linear velocity: %.3f", simulation.getWheelLinearVelocity()), 10, 70);
	g2.drawString(String.format("Wheel angular velocity: %.3f", simulation.getWheelAngularVelocity()), 10, 80);
	g2.drawString(String.format("Wheel angular acceleration: %.3f", simulation.getWheelAngularAcceleration()), 10,
		90);
    }
}
