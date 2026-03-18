package main;

import simulation.*;
import rendering.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	Robot robot = new Robot(0.1, 0.1);
	robot.setAngle(Math.PI / 180);
	
	BalancingController controller = new BalancingController(robot);
	
	double dt = 0.001;
	
	Simulation simulation = new Simulation(dt);
	simulation.addObject(robot);
	
	JFrame frame = new JFrame("Balancing robot");
	SimulationPanel panel = new SimulationPanel(robot);
	
	frame.add(panel);
	frame.setSize(800, 600);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	
	while (true) {
	    controller.control();
	    simulation.step();
	    panel.repaint();
	    try {
		Thread.sleep((int)(dt * 1000));
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }
}
