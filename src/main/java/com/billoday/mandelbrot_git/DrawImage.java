/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.billoday.mandelbrot_git;

import java.awt.Color;
import java.awt.image.BufferedImage;
/**
 * Draws a BufferedImage charting the Mandelbrot set within the range provided
 * @author billoday
 */

public class DrawImage {
    
    private final BufferedImage image;

    public DrawImage(ManRange r)  {
        
        Color myWhite = new Color(255,255,255);
        Color myBlack = new Color(0,0,0);
        image = new BufferedImage(r.points().length,r.points()[0].length,BufferedImage.TYPE_INT_RGB);
     
         Color itercolor = new Color(255,255,255);
                 
         for (int x=0;x<r.points().length;x++) {
             for (int y=0;y<r.points()[0].length;y++) {
                 if (r.itercount()[x][y]<r.iterations()) {
                     image.setRGB(x, y, Color.HSBtoRGB((float) (0.95f + 10*r.smooth()[x][y]), 0.6f, 1f) );
                 }
                 else {image.setRGB(x, y, myBlack.getRGB() );}
             }
         }
      }
    
    /**
     * Provides  the Buffered Image of the Mandelbrot set
     * @return BufferedImage
     */
    public BufferedImage  getImage() {
        return image;
    }
}
       
  

