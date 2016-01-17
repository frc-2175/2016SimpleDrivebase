package org.usfirst.frc.team2175.robot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DeadbandCalculatorTest {
    private static final double ALLOWED_DOUBLE_DELTA = 0.01;

    private DeadbandCalculator sut = new DeadbandCalculator();

    @Test
    public void testCalcLinearControlCurveSlope() throws Exception {
        double testShiftSize = .1;

        double expectedValue = 1.1111111;

        double actual = sut.calcLinearControlCurveSlope(testShiftSize);

        assertEquals("Incorrect value calculated.", expectedValue, actual, ALLOWED_DOUBLE_DELTA);

    }

    @Test
    public void testCalcSignOfValue_PositiveValue() throws Exception {
        double value = 4.4;
        double expectedValue = 1.0;

        double actual = sut.calcSignOfValue(value);

        assertEquals("Incorrect value calculated.", expectedValue, actual, ALLOWED_DOUBLE_DELTA);
    }

    @Test
    public void testCalcSignOfValue_NegativeValue() throws Exception {
        double value = -2.8;
        double expectedValue = -1.0;

        double actual = sut.calcSignOfValue(value);

        assertEquals("Incorrect value calculated.", expectedValue, actual, ALLOWED_DOUBLE_DELTA);

    }

    @Test
    public void testIsAboveThreshold_Above() throws Exception {
        double value = 2.8;
        double threshold = 3;
        boolean expectedValue = false;

        boolean actual = sut.isAboveThreshold(value, threshold);

        assertEquals("Incorrect value calculated.", expectedValue, actual);
    }

    @Test
    public void testIsAboveThreshold_Below() throws Exception {
        double value = 2.8;
        double threshold = 3;
        boolean expectedValue = false;

        boolean actual = sut.isAboveThreshold(value, threshold);

        assertEquals("Incorrect value calculated.", expectedValue, actual);
    }

    @Test
    public void testCalcLinearControlCurveIntercept_Postitive() throws Exception {
        double inputSign = 1;
        double shiftSize = 0.1;
        double expectedValue = -0.1;

        double actual = sut.calcLinearControlCurveIntercept(inputSign, shiftSize);

        assertEquals("Incorrect value calculated.", expectedValue, actual, ALLOWED_DOUBLE_DELTA);
    }

    public void testCalcLinearControlCurveIntercept_Negative() throws Exception {
        double inputSign = -1; // i.e. 1 or -1
        double shiftSize = 0.1;
        double expectedValue = 0.1;

        double actual = sut.calcLinearControlCurveIntercept(inputSign, shiftSize);

        assertEquals("Incorrect value calculated.", expectedValue, actual, ALLOWED_DOUBLE_DELTA);
    }

    @Test
    public void testCalcLinearControlCurve() throws Exception {

    }

}
