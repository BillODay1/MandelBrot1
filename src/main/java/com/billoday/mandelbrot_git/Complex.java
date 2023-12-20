/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.billoday.mandelbrot_git;

/**
 * Performs operations on complex number in the form a+bi where a is real and b is imaginary
 * @author billoday
 */
public class Complex {
    
    private final double real;
    private final double imaginary;
    
    /**
     *  
     * @param real real part of a complex number a
     * @param imaginary imaginary part of a complex number b
     */
    public Complex (double real, double imaginary) {
        this.real=real;
        this.imaginary=imaginary;
    }
    
    /**
     * Real part of complex number
     * @return real
     */
    public double real () {
        return real;
    }
    
    /**
     * Imaginary part of complex number
     * @return imaginary
     */
    public double imaginary() {
        return imaginary;
    }


    /**
     * The square of a complex number  (a + bi)^2 = (a+bi)(a+bi)
                                                                     = a^2 + abi +abi +(bi)^2
                                                                     = a^2 + 2abi +b^2(i^2) 
                                                                     = a^2 - b^2 + 2abi
     * @return square of a complex number
     */

    public Complex square () {
        double realpart = real*real - imaginary*imaginary;
        double impart = 2*real*imaginary;
        Complex square = new Complex(realpart,impart);
        return square;
    }
    
    /**
     * Addition of complex constant
     * @param constant a complex number
     * 
     * @return addition of constant to a complex number
     */
    public Complex add (Complex constant) {
        double realpart = real+constant.real();
        double impart =imaginary+constant.imaginary();
        Complex add = new Complex(realpart,impart);
        return add;
    }
    
    /**
     * Displays the complex number as a string e.g  "2 +3i"
     * @return String value of a complex number
     */
    public String show() {
        StringBuilder show = new StringBuilder();
            show.append(String.valueOf(real) );
            show.append(" + " );
            show.append(String.valueOf(imaginary) );
            show.append("i");
        return show.toString();
    } 
    
    /**
     * Absolute value of complex number | a+bi | square root of  (a**2 +b**2)
     * @return absolute value of a complex number
     */
    public double abs() {
        return Math.sqrt(real*real + imaginary*imaginary);
    }

    public Complex multiply(Complex Y) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}
