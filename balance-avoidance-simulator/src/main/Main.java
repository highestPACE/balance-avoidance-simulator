package main;

import simulation.*;
import controller.*;
import rendering.*;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
	Robot robot = new Robot(0.1, 0.1, Math.PI / 4);
	double tMin = 0.1; // controller wants to ensure that goal is not met before tMin seconds went by
	RobotController controller = new RobotController(robot, tMin);

	double dt = 0.001;

	Simulation simulation = new Simulation(robot);

	JFrame frame = new JFrame("Balancing robot");
	SimulationPanel panel = new SimulationPanel(simulation);

	frame.add(panel);
	frame.setSize(800, 600);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);

	runFor(Double.POSITIVE_INFINITY, dt, simulation, controller, panel);
    }

    private static void runFor(double duration, double dt, Simulation simulation, RobotController controller,
	    SimulationPanel panel) {

	for (int t = 0; t < duration / dt; ++t) {
	    controller.controlWheelAngularAcceleration();
	    simulation.step(dt);
	    panel.repaint();
	    try {
		Thread.sleep((int) (dt * 1000));
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }
}
