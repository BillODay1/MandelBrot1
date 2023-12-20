/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.billoday.mandelbrot_git;

/**
 *Sets the initial parameters and maximum number of iterations to escape. 
 * 
 * 
 * @author billoday
 * 
 * 
 */
 public class Parameters {
    
    private int height;  // how many pixels to draw  5120 * 2880 on 27" monitor
    private int width;
    private double xupper; 
    private double xlower;
    private double yupper;
    private double ylower;
    private int iterations;
    
    
    public Parameters() {
        this.height=1000;
        this.width=1000;
        this.xupper=2;
        this.xlower=-2;
        this.yupper=2;
        this.ylower=-2;
        this.iterations=200;
        
    }

    public int getwidth() {
        return width;
    }

    public int getheight() {
       return height;
    }

    public int getiterations() {
        return iterations;
    }

    public double getxlower() {
        return xlower;
    }

     public double getxupper() {
        return xupper;
    }

    public double getylower() {
        return ylower;
    }

    public double getyupper() {
       return yupper;
    }

    /**
     *
     * @param width size of display
     */
    public void setwidth(int width) {
        this.width=width;
    }

    /**
     *
     * @param height size of display
     */
    public void setheight(int height) {
        this.height=height;
    }
/**
     * @param iterations set the maximum number of iterations to be performed to reach escape.
    */
    public void setiterations(int iterations) {
        this. iterations=iterations;
    }
/**
     * @param xlower   should be set in the range -2 to 2
    */
    
    public void setxlower(double xlower) {
        this.xlower=xlower;
    }
/**
     * @param xupper should be set in the range -2 to 2
    */
    public void setxupper(double xupper) {
        this.xupper=xupper;
    }
/**
     * @param ylower should be set in the range -2 to 2
    */
    public void setylower(double ylower) {
        this.ylower=ylower;
    }
/**
     * @param yupper should be set in the range -2 to 2
    */
    public void setyupper(double yupper) {
       this.yupper=yupper;
    }
    
    
    
    
    
}
