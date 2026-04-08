package controller;

import physics.Physics;
import simulation.Simulation;
import simulation.Robot;

public class RobotController {

    private final Robot robot;
    private double tMin;

    public RobotController(Robot robot, double tMin) {
	this.robot = robot;
	setTMin(tMin);
    }

    public void controlWheelAngularAcceleration() {
	robot.setWheelAngularAcceleration(achieveAngle(maxAngle(robot.getWheelMaxAngularAcceleration()), robot));
    }

    private double achieveAngle(double desiredAngle, Robot robot) {
	desiredAngle = Math.max(Math.min(desiredAngle, maxAngle(robot.getWheelMaxAngularAcceleration())),
		-maxAngle(robot.getWheelMaxAngularAcceleration()));
	return (Physics.G * Math.sin(robot.getAngle()) - ((2 * robot.getLength()) / (tMin * tMin))
		* (desiredAngle - robot.getAngle() - tMin * robot.getAngularVelocity()))
		/ (Math.cos(robot.getAngle()) * robot.getWheelRadius());
    }

    private double achievePosition(double desiredPosition, Robot robot) {
	int desDirection = (int) Math.signum(desiredPosition - robot.getXPosition());
	double maxBreakAngle = -desDirection * maxAngle(robot.getWheelMaxAngularAcceleration());

	Robot robotCopy = robot.copy();
	int newDesDirection = (int) Math.signum(desiredPosition - robotCopy.getXPosition());

	Simulation simulation = new Simulation(robotCopy);
	double dt = 0.001;

	double maxAngVel = 0;
	double desWheelAngAcc;

	int counter = 0;
	do {
	    ++counter;

	    desWheelAngAcc = achieveAngle(maxBreakAngle / 2, robotCopy);
	    robotCopy.setWheelAngularAcceleration(desWheelAngAcc);
	    simulation.step(dt);

	    if ((int) Math.signum(desWheelAngAcc) == -desDirection
		    && Math.abs(robotCopy.getAngularVelocity()) > maxAngVel) {
		maxAngVel = Math.abs(robotCopy.getAngularVelocity());
	    }

	    newDesDirection = (int) Math.signum(desiredPosition - robotCopy.getXPosition());
	    if (newDesDirection != 0 && newDesDirection != desDirection) {
		break;
	    }
	} while (Math.abs(robotCopy.getAngularVelocity()) > maxAngVel / 100
		|| (int) Math.signum(desWheelAngAcc) == desDirection);

	System.out.println(counter);

	int velDirection = (int) Math.signum(robotCopy.getWheelLinearVelocity());
	double stopPosition = stopPosition(robotCopy.getWheelAngularAcceleration() * robotCopy.getWheelRadius(),
		robotCopy);
	int afterStopDesDirection = (int) Math.signum(desiredPosition - stopPosition);
	if ((velDirection != desDirection && (newDesDirection == 0 || newDesDirection == desDirection))
		|| afterStopDesDirection == 0 || afterStopDesDirection == desDirection) {
	    double desLinAcc = linearAcceleration(desiredPosition, robot);
	    double desAngle = angleForLinearAcceleration(desLinAcc);
	    return achieveAngle(desAngle, robot);
	} else {
	    double desLinBreak = linearBreaking(desiredPosition, robot);
	    double desAngle = angleForLinearAcceleration(desLinBreak);
	    return achieveAngle(15 * desAngle, robot);
	}
    }

    public static double maxAngle(double maxAngularAcceleration) {
	return angleForLinearAcceleration(maxAngularAcceleration);
    }

    private double linearAcceleration(double desiredPosition, Robot robot) {
	return (2 / (tMin * tMin)) * (desiredPosition - robot.getXPosition() - tMin * robot.getWheelLinearVelocity());
    }

    private double linearBreaking(double desiredPosition, Robot robot) {
	Double linBreak = Math.pow(robot.getWheelLinearVelocity(), 2) / (2 * (robot.getXPosition() - desiredPosition));
	if (linBreak.equals(Double.NaN)) {
	    linBreak = 0.;
	}
	return linBreak;
    }

    private static double angleForLinearAcceleration(double linearAcceleration) {
	return Math.atan(linearAcceleration / Physics.G);
    }

    private double stopPosition(double linearAcceleration, Robot robot) {
	return robot.getXPosition() - Math.pow(robot.getWheelLinearVelocity(), 2) / (2 * linearAcceleration);
    }

    public double getTMin() {
	return tMin;
    }

    public void setTMin(double tMin) {
	this.tMin = tMin;
    }
}
