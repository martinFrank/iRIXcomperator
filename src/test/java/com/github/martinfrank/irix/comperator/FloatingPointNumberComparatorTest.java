package com.github.martinfrank.irix.comperator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FloatingPointNumberComparatorTest
{


    @Test
    public void normalPermyriadTest(){
        //given
        double first = 1; //normal float number
        double second = addPermyriad(first);

        //then
        assertTrue( FloatingPointNumberComparator.isApproximatelyEqual(first, second) );
    }

    @Test
    public void smallPermyriadTest(){
        //given
        double first = 0.9e-130; //small float number
        double second = addPermyriad(first);

        //then
        assertTrue( FloatingPointNumberComparator.isApproximatelyEqual(first, second) );
    }

    @Test
    public void bigPermyriadTest(){
        //given
        double first = 0.9E130; //big float number
        double second = addPermyriad(first);

        //then
        assertTrue( FloatingPointNumberComparator.isApproximatelyEqual(first, second) );
    }

    @Test (expected = IllegalArgumentException.class)
    public void tooBigNumberTest(){
        //given
        double first = 0.9E151; //big float number
        double second = addPermyriad(first);

        //then
        FloatingPointNumberComparator.isApproximatelyEqual(first, second);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tooSmallNumberTest(){
        //given
        double first = 0.9E-151; //big float number
        double second = addPermyriad(first);

        //then
        FloatingPointNumberComparator.isApproximatelyEqual(first, second);
    }

    @Test
    public void smallFloatPermyriadTest(){
        //given
        float first = 0.9E-40f; //small float number
        float second = addPermyriad(first);

        //then
        assertTrue( FloatingPointNumberComparator.isApproximatelyEqual(first, second) );
    }

    @Test
    public void bigFloatPermyriadTest(){
        //given
        float first = 0.9E35f; //big float number
        float second = addPermyriad(first);

        //then
        assertTrue( FloatingPointNumberComparator.isApproximatelyEqual(first, second) );
    }

    @Test
    public void overZeroPermyriadTest(){
        //given
        double first = 0.5;
        double second = -0.4999;

        //when
        double approximatelyFactor = 2;

        //then
        assertTrue( FloatingPointNumberComparator.isApproximatelyEqual(first, second, approximatelyFactor) );
    }

    @Test
    public void bigRangeTest(){
        //given
        double first = 1;
        double second = 10;

        //when
        double approximatelyFactor = 10;

        //then
        assertTrue( FloatingPointNumberComparator.isApproximatelyEqual(first, second, approximatelyFactor) );
    }


    private static double addPermyriad(double base) {
        double permyriad = 0.0001; // see https://en.wikipedia.org/wiki/Basis_point#Permyriad
        return base + base * permyriad;
    }

    private static float addPermyriad(float base) {
        float permyriad = 0.0001f;
        return base + base * permyriad;
    }


}
