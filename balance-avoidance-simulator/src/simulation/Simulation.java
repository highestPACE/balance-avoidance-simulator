package simulation;

import physics.*;
import weightedcontroller.*;

public class Simulation {

    private final Robot robot;

    public Simulation(Robot robot) {
	this.robot = robot;
    }

    public void step(double dt) {
	double normalizedAngle = Math.abs(getRobotAngle());
	if (!(0 <= normalizedAngle && normalizedAngle <= Math.PI)) {
	    normalizedAngle -= 2 * Math.PI;
	}
	ControlTerm angleTerm = new ControlTerm("angle", 100, -normalizedAngle);
	ControlTerm positionTerm = new ControlTerm("position", 100, getRobotXPosition());
	WeightedController controller = new WeightedController();
	controller.addTerm(angleTerm);
	// controller.addTerm(positionTerm);

	double wheelAngAcc = controller.computeOutput();
	robot.getWheel().setAngularAcceleration(wheelAngAcc);

	double wheelAngVel = Physics.valueChange(getWheelAngularVelocity(), getWheelAngularAcceleration(), dt);
	robot.getWheel().setAngularVelocity(wheelAngVel);

	double robAngVel = Physics.valueChange(robot.getAngularVelocity(), getRobotAngularAcceleration(), dt);
	robot.setAngularVelocity(robAngVel);

	double robAng = Physics.valueChange(robot.getAngle(), robot.getAngularVelocity(), dt);
	robot.setAngle(robAng);

	double robXPosition = Physics.valueChange(robot.getXPosition(), getWheelLinearVelocity(), dt);
	robot.setXPosition(robXPosition);
    }

    public double getRobotLength() {
	return robot.getLength();
    }

    public double getWheelRadius() {
	return robot.getWheel().getRadius();
    }

    public double getRobotXPosition() {
	return robot.getXPosition();
    }

    public double getRobotAngle() {
	return robot.getAngle();
    }

    public double getRobotAngularVelocity() {
	return robot.getAngularVelocity();
    }

    public double getRobotAngularAcceleration() {
	return InvertedPendulum.calcAngularAcceleration(robot.getAngle(), robot.getLength(),
		robot.getWheel().getAngularAcceleration());
    }

    public double getWheelLinearVelocity() {
	return Physics.calcLinearVelocity(robot.getWheel().getAngularVelocity(), robot.getWheel().getRadius());
    }

    public double getWheelAngularVelocity() {
	return robot.getWheel().getAngularVelocity();
    }

    public double getWheelAngularAcceleration() {
	return robot.getWheel().getAngularAcceleration();
    }
}
