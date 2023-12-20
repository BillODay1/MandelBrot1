/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.billoday.mandelbrot_git;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

/**
 * Display screen for Mandelbrot chart. 
 * @author billoday
 */
public class DisplayScreen {

private JFrame mframe;
private final JButton submit = new JButton();
private final JButton saveimage=new JButton();
private final JButton reset=new JButton();
private JTable bounds;
private  Parameters p;
private ManRange mr;
private DrawImage di;
private BufferedImage drawing;
private boolean firsttime =true;
    
public DisplayScreen(Parameters p) throws IOException {
    this.p=p;

    redrawScreen();
    
  
    
    submit.addActionListener((ActionEvent e) -> {
        try {
            submitAction();
        } catch (IOException ex) {
            Logger.getLogger(DisplayScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    
    saveimage.addActionListener((ActionEvent si) -> {
        System.out.println(" save image ");
        String filename = Double.toString(p.getxlower()) + Double.toString(p.getxupper()) + Double.toString(p.getylower()) +
                Double.toString(p.getyupper());
        try {
            ImageIO.write(drawing, "png", new File(filename));
        } catch (IOException ex) {
            Logger.getLogger(DisplayScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    
    reset.addActionListener((ActionEvent rp) -> {
        Parameters np = new Parameters();
        DisplayScreen.this.p = np;
        try {
            redrawScreen();
        } catch (IOException ex) {
            Logger.getLogger(DisplayScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    
}

    private void outputScreen(BufferedImage bi) {
        System.out.println(" Output Screen ");
        mframe= new JFrame("Mandelbrot Picture");
        mframe.setLayout(new GridBagLayout());
        mframe.setSize(1500,1500);
        mframe.setLocationRelativeTo(null);
        mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints c =new GridBagConstraints();
        c.fill=GridBagConstraints.BOTH;
        
        /**
        Create Panel for displaying and changing parameters
        */
        
        JPanel complexbounds = new JPanel();
        
        complexbounds.setPreferredSize(new Dimension(400,60));
        complexbounds.setBackground(Color.decode("#99FF99"));
        
        String [] columnnames = {"Type ", " Axis" , "Lower","Upper","Iterations","Width","Height"};
        String [][] complex ={ {"Real", "x","-2","2","1000","500","500"},{"Imaginary","y","-2","2"," "," "," "}};
        complex[0][2]=Double.toString(p.getxlower());
        complex[0][3]=Double.toString(p.getxupper());
        complex[0][4]=String.valueOf(p.getiterations());
        complex[0][5]=String.valueOf(p.getwidth());
        complex[0][6]=String.valueOf(p.getheight());
        complex[1][2]=Double.toString(p.getylower());
        complex[1][3]=Double.toString(p.getyupper());
        complex[1][4]=" ";
        complex[1][5]=" ";
        complex[1][6]=" ";
        
        bounds = new JTable(complex,columnnames);
        
        TableColumnModel cm = bounds.getColumnModel();
        cm.getColumn(0).setPreferredWidth(50);
        cm.getColumn(1).setPreferredWidth(4);
        cm.getColumn(2).setPreferredWidth(10);
        cm.getColumn(3).setPreferredWidth(10);
        cm.getColumn(4).setPreferredWidth(10);
        cm.getColumn(5).setPreferredWidth(10);
        cm.getColumn(6).setPreferredWidth(10);
        
        JScrollPane sp = new JScrollPane(bounds);
        complexbounds.add(sp);
        
        /**
         * Create panel to display Image and listen for mouse clicks
         */
        
        JPanel mandelbrot = new JPanel();
        ImageIcon icon =new ImageIcon(bi);
        mandelbrot.add(new JLabel(icon),BorderLayout.AFTER_LINE_ENDS);
        mandelbrot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("Mouse Clicked " +me.getClickCount() +" X " +me.getPoint().toString());
                try {
                    centreonClick(me.getPoint());
                } catch (IOException ex) {
                    Logger.getLogger(DisplayScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void centreonClick(Point point) throws IOException {
                double xrange=p.getxupper()-p.getxlower();
                double yrange=p.getyupper()-p.getylower();
                double newreal=point.x*xrange/p.getwidth()+p.getxlower();
                double newim=point.y*yrange/p.getheight()+p.getylower();
                p.setxlower(newreal-xrange/4);
                p.setxupper(newreal+xrange/4);
                p.setylower(newim-yrange/4);
                p.setyupper(newim+yrange/4);
                redrawScreen();
            }
        });
        
        /**
         * Create Panel to submit, save or reset the chart
         */
        
        JPanel message = new JPanel(); 
        submit.setText("Submit");
        saveimage.setText("Save Image");
        reset.setText("Reset ");
        message.add(submit);
        message.add(saveimage);
        message.add(reset);
        
        /*
        Position panels based on constraints
        */
        c.gridx=0;
        c.gridy=0;
     //   c.gridheight=3;
        c.anchor=GridBagConstraints.NORTHWEST;
        mframe.add(complexbounds,c);
        
        
        c.gridx=0;
        c.gridy=1;
        c.anchor=GridBagConstraints.CENTER;
        mframe.add(mandelbrot,c);
        
        c.gridx=0;
        c.gridy=2;
        c.gridheight=1;
        c.anchor=GridBagConstraints.SOUTHEAST;
        mframe.add(message,c);
        
        mframe.setVisible(true);
        
    }

    private void submitAction() throws IOException {
        validatenewParameters();
        redrawScreen();
    }

    private void validatenewParameters() {

        p.setxlower(Double.parseDouble(bounds.getValueAt(0, 2).toString()));
        p.setxupper(Double.parseDouble(bounds.getValueAt(0, 3).toString()));
        p.setylower(Double.parseDouble(bounds.getValueAt(1, 2).toString()));
        p.setyupper(Double.parseDouble(bounds.getValueAt(1, 3).toString()));
        p.setiterations(Integer.parseInt(bounds.getValueAt(0, 4).toString()));
        p.setwidth(Integer.parseInt(bounds.getValueAt(0, 5).toString()));
        p.setheight(Integer.parseInt(bounds.getValueAt(0, 6).toString()));

    }
/**
 * Redraw the screen based on new parameters
 * @throws IOException 
 */
    private void redrawScreen() throws IOException {
        mr = new ManRange(p);
        di = new DrawImage(mr);
        if (!firsttime) {
            mframe.dispose();
        }else {
            firsttime=false;
        }
        this.drawing=di.getImage();
        outputScreen(di.getImage());

    }
    
    
}
