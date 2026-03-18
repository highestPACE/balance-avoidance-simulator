package rendering;

import simulation.Robot;

import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends JPanel {

    private final simulation.Robot robot;
    
    public SimulationPanel(Robot robot) {
	this.robot = robot;
	setBackground(Color.WHITE);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	
	Graphics g2 = (Graphics2D)g;
	
	int width = getWidth();
	int height = getHeight();
	
	double groundY = height / 2;
	double scale = 1000;
	double wheelRadius = robot.getWheelRadius();
	
	double wheelX = robot.getXPosition() * scale + (width / 2);
	double wheelY = groundY - wheelRadius * scale;
	
	double topX = wheelX + Math.sin(robot.getAngle()) * robot.getLength() * scale;
	double topY = wheelY - Math.cos(robot.getAngle()) * robot.getLength() * scale;
	
	// ground
	g2.drawLine(0, (int)groundY, width, (int)groundY);
	
	// Wheel
	g2.drawOval((int)(wheelX - (wheelRadius * scale)), (int)(wheelY - (wheelRadius * scale)), (int)(2 * wheelRadius * scale), (int)(2 * wheelRadius * scale));
	
	// Body
	g2.drawLine((int)wheelX, (int)wheelY, (int)topX, (int)topY);
	
	// Text
	g2.drawString(String.format("Angular velocity of wheel: %.3f", robot.getWheelAngularVelocity()), 20, 20);
    }
}
