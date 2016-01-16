package org.usfirst.frc.team2175.robot;

public class DeadbandCalculator {
    public double calcDeadbandedOutput(double input, double deadbandSize) {
        double output;

        if (isAboveThreshold(input, deadbandSize)) {
            double sign = calcSignOfValue(input);
            double newSlope = calcLinearControlCurveSlope(deadbandSize);
            double newIntercept = calcLinearControlCurveIntercept(sign, deadbandSize);

            output = calcLinearControlCurve(input, newSlope, newIntercept);
        } else {
            output = 0;
        }

        return output;
    }

    public boolean isAboveThreshold(double input, double threshold) {
        return Math.abs(input) >= threshold;
    }

    public double calcSignOfValue(double value) {
        return (Math.abs(value) / value);
    }

    public double calcLinearControlCurveSlope(double shiftSize) {
        return 1 / (1 - shiftSize);
    }

    public double calcLinearControlCurveIntercept(double inputSign, double shiftSize) {
        return -(inputSign * shiftSize);
    }

    public double calcLinearControlCurve(double input, double curveSlope, double curveIntercept) {
        return input * curveSlope + curveIntercept;
    }
}
