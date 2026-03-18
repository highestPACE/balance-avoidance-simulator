package simulation;

import physics.*;

public class Simulation {

    private final Robot robot;

    public Simulation(Robot robot) {
	this.robot = robot;
    }

    public void step(double dt) {
	double wheelAngAcc = BalancingController.controlWheelAngularAcceleration(dt, robot.getLength(),
		robot.getAngle());
	robot.getWheel().setAngularAcceleration(wheelAngAcc);

	double wheelAngVel = Physics.valueChange(robot.getWheel().getAngularVelocity(),
		robot.getWheel().getAngularAcceleration(), dt);
	robot.getWheel().setAngularVelocity(wheelAngVel);

	double wheelLinVel = Physics.calcLinearVelocity(robot.getWheel().getAngularVelocity(),
		robot.getWheel().getRadius());

	double robAngAcc = InvertedPendulum.calcAngularAcceleration(robot.getAngle(), robot.getLength(),
		robot.getWheel().getAngularAcceleration());

	double robAngVel = Physics.valueChange(robot.getAngularVelocity(), robAngAcc, dt);
	robot.setAngularVelocity(robAngVel);

	double robAng = Physics.valueChange(robot.getAngle(), robot.getAngularVelocity(), dt);
	robot.setAngle(robAng);

	double robXPosition = Physics.valueChange(robot.getXPosition(), wheelLinVel, dt);
	robot.setXPosition(robXPosition);
    }
}
