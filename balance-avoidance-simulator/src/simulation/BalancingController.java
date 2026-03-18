package simulation;

import physics.Physics;

public class BalancingController {
    
    private Robot robot;
    
    public BalancingController(Robot robot) {
	setRobot(robot);
    }
    
    public void control(double dt) {
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
	    robot.setWheelAngularAcceleration((Physics.G * Math.sin(angle) + (angle * length) / dt ) / Math.cos(angle));
	}
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }
    
}
