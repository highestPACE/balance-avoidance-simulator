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
	robot.setWheelAngularAcceleration(achievePosition(0, robot));
    }

    private double achieveAngle(double desiredAngle, Robot robot) {
	return (Physics.G * Math.sin(robot.getAngle()) - ((2 * robot.getLength()) / (tMin * tMin))
		* (desiredAngle - robot.getAngle() - tMin * robot.getAngularVelocity()))
		/ (Math.cos(robot.getAngle()) * robot.getWheelRadius());
    }

    private double achievePosition(double desiredPosition, Robot robot) {
	double linAcc = linearAcceleration(desiredPosition, robot);
	double desAngle = angleForLinearAcceleration(linAcc);
	desAngle = test(desiredPosition, desAngle, -Math.PI / 2, Math.PI / 2, 1, robot);
	return achieveAngle(desAngle, robot);
    }

    private double linearAcceleration(double desiredPosition, Robot robot) {
	return (2 / (tMin * tMin)) * (desiredPosition - robot.getXPosition() - tMin * robot.getWheelLinearVelocity());
    }

    private double angleForLinearAcceleration(double linearAcceleration) {
	return Math.atan(linearAcceleration / Physics.G);
    }

    private double test(double desiredPosition, double start, double lowerBound, double upperBound, int iterationsCount,
	    Robot robot) {
	if (iterationsCount < 1) {
	    return start;
	}

	Robot robotCopy = robot.copy();
	robotCopy.setWheelAngularAcceleration(achieveAngle(start, robot));

	Simulation simulation = new Simulation(robotCopy);
	double dt = 0.001;
	simulation.step(dt);

	double newLinAcc = linearAcceleration(desiredPosition, robotCopy);
	double newAngle = angleForLinearAcceleration(newLinAcc);

	if (newAngle == start) {
	    return start;
	}

	double newStart;
	double newLowerBound;
	double newUpperBound;
	if (newAngle < start) {
	    newStart = (start + lowerBound) / 2;
	    newLowerBound = lowerBound;
	    newUpperBound = start;
	} else {
	    newStart = (start + upperBound) / 2;
	    newLowerBound = start;
	    newUpperBound = upperBound;
	}

	if (iterationsCount == 1) {
	    return newStart;
	} else {
	    return test(desiredPosition, newStart, newLowerBound, newUpperBound, iterationsCount - 1, robot);
	}
    }

    public double getTMin() {
	return tMin;
    }

    public void setTMin(double tMin) {
	this.tMin = tMin;
    }
}
