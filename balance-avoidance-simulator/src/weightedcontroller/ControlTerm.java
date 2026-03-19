package weightedcontroller;

public class ControlTerm {

    private final String name;
    private double value;
    private double weight;

    public ControlTerm(String name, double value, double weight) {
	this.name = name;
	this.value = value;
	this.weight = weight;
    }

    public String getName() {
	return name;
    }

    public double getValue() {
	return value;
    }

    public double getWeight() {
	return weight;
    }

    public void setValue(double value) {
	this.value = value;
    }

    public void setWeight(double weight) {
	this.weight = weight;
    }

    public double contribution() {
	return weight * value;
    }
}