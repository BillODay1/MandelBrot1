/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.billoday.mandelbrot_git;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 * Creates a Time Series chart of an individual complex number going through the Mandelbrot formula
 * @author billoday
 */
public class TimeSeriesChart {
    
    double real,im;
    double z,z_re,z_im,new_re,new_im;
    final TimeSeries series = new TimeSeries (" Mandelbrot Numbers ");
    
    public TimeSeriesChart(double real,double im) {
        this.real=real;this.im=im;
        
        TimeSeries_AWT demo = new TimeSeries_AWT (" Mandelbrot Time Series");
        demo.pack();
        demo.setVisible(true);   
        
    }

    private XYDataset createdataset() {
        z_re=0;z_im=0;Second current = new Second();
        
        for (int i =1;i<100;i++){
           new_re = z_re*z_re - z_im * z_im + real;
           new_im = 2*z_re*z_im + im;
           z_re = new_re;
           z_im = new_im;  
           series.add(current, z_im);
           current= (Second) current.next();
            System.out.println(" i " +i + " real " + z_re + " im " + z_im);
        }
        return new TimeSeriesCollection (series);
    }

    public JFreeChart createChart  (final XYDataset dataset) {
        return ChartFactory.createTimeSeriesChart(" chart Real ", "Instance ", "Z real value", dataset,false,false,false);
    }

    public class TimeSeries_AWT extends ApplicationFrame {
        
        public TimeSeries_AWT( final String title ) {
            super ( title );
            XYDataset dataset = createdataset();
            JFreeChart chart = createChart (dataset);
            ChartPanel chartPanel = new ChartPanel (chart);
            chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );         
            chartPanel.setMouseZoomable( true , false );         
            setContentPane( chartPanel );
            
            
        }

        
            
        }
    }
   
