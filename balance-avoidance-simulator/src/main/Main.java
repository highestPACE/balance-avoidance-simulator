package main;

import simulation.*;
import rendering.*;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
	Robot robot = new Robot(0.1, 0.1);
	robot.setAngle(Math.PI);

	double dt = 0.001;

	Simulation simulation = new Simulation(robot);

	JFrame frame = new JFrame("Balancing robot");
	SimulationPanel panel = new SimulationPanel(simulation);

	frame.add(panel);
	frame.setSize(800, 600);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);

	while (true) {
	    simulation.step(dt);
	    panel.repaint();
	    try {
		Thread.sleep((int) (1000 * dt));
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }
}
