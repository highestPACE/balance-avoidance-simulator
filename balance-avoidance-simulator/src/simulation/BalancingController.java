package simulation;

import java.time.Duration;
import java.time.LocalDateTime;

import physics.Physics;

public class BalancingController {
    
    private Robot robot;
    private LocalDateTime lastControl = LocalDateTime.now();
    
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
	    double dt = Duration.between(lastControl, LocalDateTime.now()).toMillis();
	    robot.setWheelAngularAcceleration((Physics.G * Math.sin(angle) + (angle / (dt * length))) / Math.cos(angle));
	}
	lastControl = LocalDateTime.now();
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }
    
}
