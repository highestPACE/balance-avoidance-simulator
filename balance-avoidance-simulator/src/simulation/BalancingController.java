package simulation;

import physics.Physics;

public class BalancingController {
    
    private Robot robot;
    
    public BalancingController(Robot robot) {
	setRobot(robot);
    }
    
    public void control() {
	double length = robot.getLength();
	double angle = robot.getAngle();
	if (angle == 0) {
	    robot.setWheelAngularAcceleration(0);
	} else if (angle == Math.PI / 2) {
	    robot.setWheelAngularAcceleration(10); // TODO maxAngularAcceleration
	} else  if (angle == (3 * Math.PI) / 2) {
	    robot.setWheelAngularAcceleration(-10); // TODO maxAngularAcceleration
	}
	else {
	    double dt = 0.001; // TODO
	    robot.setWheelAngularAcceleration((Physics.G * Math.sin(angle) + (angle / (dt * length))) / Math.cos(angle)); // TODO calc better value
	}
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }
    
}
