/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.billoday.mandelbrot_git;

import java.io.IOException;


/**
 * This is the main Class in Mandelbrot. It sets up starting parameters for drawing the Mandelbrot chart. 
 * 
 * It uses the recursive mathematical formula Z = Z**2 +C
 * 
 * Displays the initial chart which you can then begin to zoom in on by selecting a new pixel to centre the chart on. 
 * @author billoday
 * 
 * 
 * 
 */
public class Mandelbrot_git {
        
    /**
     * @param args the command line arguments
     * @throws java.io.IOException throws an error if IO exception
     */
    public static void main(String[] args) throws IOException {
        
        // TODO code application logic here
         Parameters  p = new Parameters();
 
         DisplayScreen ds = new DisplayScreen(p);
    }

}
