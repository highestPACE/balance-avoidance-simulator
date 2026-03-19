package weightedcontroller;

import java.util.ArrayList;
import java.util.List;

public class WeightedController {

    private final List<ControlTerm> terms = new ArrayList<>();
    private double maxOutput = Double.POSITIVE_INFINITY;
    private boolean negateSum = true;

    public void addTerm(ControlTerm term) {
	terms.add(term);
    }

    public void clearTerms() {
	terms.clear();
    }

    public void setMaxOutput(double maxOutput) {
	this.maxOutput = maxOutput;
    }

    public void setNegateSum(boolean negateSum) {
	this.negateSum = negateSum;
    }

    public double computeOutput() {
	double sum = 0.0;

	for (ControlTerm term : terms) {
	    sum += term.contribution();
	}

	double output = negateSum ? -sum : sum;
	return clamp(output, -maxOutput, maxOutput);
    }

    private double clamp(double value, double min, double max) {
	return Math.max(min, Math.min(max, value));
    }

    public List<ControlTerm> getTerms() {
	return terms;
    }
}