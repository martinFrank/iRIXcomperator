package com.github.martinfrank.irix.comperator;

import java.util.logging.Logger;

/**
 * used to compare two floating point numbers (double, float) and check if they are approximately equal
 * <p>
 * Limitiations through IEEE754 makes comparison for very small/very big numbers (+/-1E+/-150) imprecise
 * and thus throw an IllegalArgumentException
 */
public class FloatingPointNumberComparator {

    private static final Logger LOGGER = Logger.getLogger(FloatingPointNumberComparator.class.getName());
    public static final double DEFAULT_APPROXIMATELY_FACTOR = 0.001;
    private static final double BIG_MAX = 1E150;
    private static final double BIG_MIN = -1E150;
    private static final double SMALL_MAX = 1E-150;
    private static final double SMALL_MIN = -1E-150;
    private static final String ERROR_MESSAGE = "this method is cannot be applied to very %s numbers " +
            "(see IEEE754 (https://ieeexplore.ieee.org/document/8766229))";

    /**
     * compares two float point values for ApproximityEquallity
     *
     * @param first  floating point number
     * @param second floating point number
     * @return true if difference between first and second smaller than 0.1%
     * @throws IllegalArgumentException when first/second out of range (+/-1E+/-150)
     */
    public static boolean isApproximatelyEqual(double first, double second) {
        return isApproximatelyEqual(first, second, DEFAULT_APPROXIMATELY_FACTOR);
    }

    /**
     * compares two float point values for ApproximityEquallity
     *
     * @param first               floating point number
     * @param second              floating point number
     * @param approximatelyFactor factor to determine approximation: for 1% set to 0.01, for 0.1% set to 0.001 and so on...
     * @return true if first minus second smaller than approximation
     * @throws IllegalArgumentException when first/second out of range (+/-1E+/-150)
     */
    public static boolean isApproximatelyEqual(double first, double second, double approximatelyFactor) {
        validateInput(first, second);
        double approximatelyRange = first * approximatelyFactor;
        LOGGER.fine("first :" + first);
        LOGGER.fine("second:" + second);
        LOGGER.fine("range: " + approximatelyRange);
        LOGGER.fine("diff:  " + (first - second));
        return Math.pow((first - second), 2) < Math.pow(approximatelyRange, 2); //absolut values using squares
    }

    private static void validateInput(double first, double second) {
        if ((first > BIG_MAX || first < BIG_MIN) || (second > BIG_MAX || second < BIG_MIN)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, "big"));
        }
        if ((first < SMALL_MAX && first > SMALL_MIN) || (second < SMALL_MAX && second > SMALL_MIN)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, "small"));
        }
    }
}
