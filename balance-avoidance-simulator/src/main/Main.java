package main;

import simulation.*;
import rendering.*;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
	Robot robot = new Robot(0.1, 0.1, 10, 0);

	double dt = 0.001;

	Simulation simulation = new Simulation(robot);

	JFrame frame = new JFrame("Balancing robot");
	SimulationPanel panel = new SimulationPanel(simulation);

	frame.add(panel);
	frame.setSize(800, 600);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);

	runFor(Double.POSITIVE_INFINITY, dt, simulation, panel);
    }

    private static void runFor(double duration, double dt, Simulation simulation, SimulationPanel panel) {
	for (int t = 0; t < duration / dt; ++t) {
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
