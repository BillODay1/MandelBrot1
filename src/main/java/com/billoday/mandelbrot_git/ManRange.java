/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.billoday.mandelbrot_git;

/**
 * This class calculates the mandelbrot numbers within the range defined by parameters 
 * 
 * It also iterates through the formula upto the maximum iterations. 
 * 
 * Provides the DrawImage class with appropriate pixel information, whether the pixel is part of the manfelbrot Set and how many iterations to escape.
 * 
 * @author billoday
 */
public class ManRange {
    
        private final Boolean mset[][];
        private final int itercount[][];
        private final Complex points[][];
        private final double smooth[][];
        private final int iterations;
        private final float[] countofiterations;
        private final Parameters p;
        private final double xupper;
        private final double xlower;
        private final double yupper;
        private final double ylower;
        
       

    ManRange(Parameters p) {
        this.p=p;
        this.mset=new Boolean[p.getwidth()][p.getheight()];
        this.itercount= new int[p.getwidth()][p.getheight()];
        this.points=new Complex[p.getwidth()][p.getheight()];
        this.smooth=new double[p.getwidth()][p.getheight()];
        this.iterations=p.getiterations();
        this.xlower=p.getxlower();
        this.xupper=p.getxupper();
        this.ylower=p.getylower();
        this.yupper=p.getyupper();
        this.countofiterations=new float[this.iterations+1];
        
        calculatePointsinRange();
    }
    
    /**
     * Is the complex number to be charted part of the Mandelbrot set of complex numbers
     * @return true or false
     */
    public Boolean [][]mset () {
        return mset;
    }
    
    /**
     * Iteration count up to the maximum before number escapes to infinity
     * @return array of iteration counts in range
     */
    public int [][] itercount () {
        return itercount;
    }
    
    /**
     * All of the complex points tested in the range
     * @return array of all points sampled
     */
    public Complex [][] points () {
        return points;
    }
    
    /**
     * Smoothed number to smooth the colours based on number of iterations to escape
     * @return array of smothed numbers
     */
    public double[][] smooth() {
        return smooth;
    }
    
    /**
     * Maximum iterations to attempt
     * @return iteration count
     */
    public int iterations() {
        return iterations;
    }
    

    private void updateiterations(int count) {
        countofiterations[count]++;
    }
    

    private void calculatePointsinRange() {
    //    total=width*height;
        Complex z;
        
        //calculate # of points to be calculated in the range,  x axis this is (upper-lower)/width e.g (2-(-2))/5120 = 4/5120 =0.00078125
        //                                                                                                                                (1-(-1)/5120 = 2/5120 =0.000390625
        double zreal=xlower;
        double zim=ylower;
        double xinterval=(xupper-xlower)/p.getwidth();
        double yinterval =(yupper-ylower)/p.getheight();
        System.out.println(" Interval " +xinterval);
        for (int x=0;x<p.getwidth();x++ ) { 
            zreal = zreal+xinterval; 
            for (int y=0;y<p.getheight();y++) {
                 zim=zim+yinterval;
                 z=new Complex(zreal,zim);
                 Iterate m = new Iterate(z,iterations);
                 points[x][y] =z;
                 mset[x][y]=m.mset();
                 itercount[x][y]=m.count();
                 smooth[x][y]=m.smooth()/p.getiterations();
                 updateiterations(m.count());
            }
            zim=ylower; 
        }
    }
}
