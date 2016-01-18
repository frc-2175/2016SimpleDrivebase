package org.usfirst.frc.team2175.robot;

/**
 * This is a simple class for calculating a new control curve with a "deadband"
 * around zero. This is especially useful with joysticks (to eliminate
 * insignificant values near the base position), but could theoretically be used
 * with any input method.
 *
 * @author Max Haland
 *
 */

public class DeadbandCalculator {

    /**
     * This method used the other methods within DeadbandCalculator to actually
     * provide the desired output values. The values are calculated according to
     * the new control curve defined by the size of the deadband.
     *
     * @param input
     *            Input to deadband
     * @param deadbandSize
     *            Size of the deadband (between zero and one)
     *
     * @return Returns a value based off of the newly defined control curve
     *         properties.
     */

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

    /**
     * Determine whether some value is above a given threshold.
     *
     * @param input
     *            Value to use
     * @param threshold
     *            Threshold to compare against
     * @return boolean representing the truthiness of the comparison
     */

    public boolean isAboveThreshold(double input, double threshold) {
        return Math.abs(input) >= threshold;
    }

    /**
     * Calculates the sign (i.e. positive or negative) of a given value
     *
     * @param value
     *            value to determine sign of
     * @return sign of the value in the form of a 1 or -1
     */

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
        return input * (curveSlope + curveIntercept);
    }
}
