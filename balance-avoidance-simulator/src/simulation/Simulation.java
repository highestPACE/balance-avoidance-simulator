package simulation;

import controller.*;
import physics.*;

public class Simulation {

    private final Robot robot;

    public Simulation(Robot robot) {
	this.robot = robot;
    }

    public void step(double dt) {
	double tMin = 0.1; // controller wants to ensure that goal is not met before tMin seconds went by
	RobotController controller = new RobotController(tMin);

	double wheelAngAcc = controller.controlWheelAngularAcceleration(getRobot());
	robot.setWheelAngularAcceleration(wheelAngAcc);

	double wheelAngVel = Physics.valueChange(robot.getWheelAngularVelocity(), robot.getWheelAngularAcceleration(),
		dt);
	robot.setWheelAngularVelocity(wheelAngVel);

	double robAngVel = Physics.valueChange(robot.getAngularVelocity(), robot.getAngularAcceleration(), dt);
	robot.setAngularVelocity(robAngVel);

	double robAng = Physics.valueChange(robot.getAngle(), robot.getAngularVelocity(), dt);
	robot.setAngle(robAng);

	double robXPosition = Physics.valueChange(robot.getXPosition(), robot.getWheelLinearVelocity(), dt);
	robot.setXPosition(robXPosition);
    }

    public Robot getRobot() {
	return robot;
    }
}
