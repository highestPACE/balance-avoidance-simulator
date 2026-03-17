package simulation;

import java.util.ArrayList;

public class Simulation {

    private double t = 0;
    private double dt = 1;
    private ArrayList<Robot> objects = new ArrayList<Robot>(); 
    
    public Simulation(double dt) {
	setDt(dt);
    }
    
    public void step() {
	for (Robot o : objects) {
	    o.simulate(dt);
	}
    }
    
    public void addObject(Robot object) {
	objects.add(object);
    }
    
    public void removeObject(Robot object) {
	objects.remove(object);
    }

    public double getT() {
        return t;
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }
}
