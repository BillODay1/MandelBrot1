/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.billoday.mandelbrot_git;

/**
 * This does the iterations upto maximum and provides a smooted number to colour the chart
 * It escapes if the absolute of complex  number greater than  2 
 * @author billoday
 */
public class Iterate {

    private final int count;
    private final boolean mset;
    private double smooth;
    public Iterate(Complex c , int Iterations) {
        Complex citer =c;
        smooth=0;
         int i;
         for (i=1;i<Iterations;i++) {
             citer=citer.square().add(c);
             //System.out.println(i +": " + citer.show());
             if (citer.abs() > 2)  {
                 smooth=i+1-Math.log(Math.log(citer.abs()))/Math.log(2);
                 break;
             }
         }
         mset = i==Iterations;
         count=i;
    }
    
    /**
     * Count of iterations up to escape or maximum whichever is smaller
     * @return count
     */
    public int count () {
        return count;
    }
    
    /**
     * Is the complex number part of the mandelbrot set
     * @return true or false
     */
    public boolean mset() {
        return mset;
    }
    
    /**
     * Provides a mathematically smoothed umber to calculate colour based on number of iterations
     * @return smoothed number
     */
    public double smooth() {
        return smooth;
    }
}
