/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk10;

import gk10.MyImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Jakub Tomczuk
 */
public class GK10 extends JPanel {

    BufferedImage display = null;

    GK10() throws IOException {
        MyImage imagework = new MyImage();
        imagework.readImage("gray.jpg");

        Grayscale.change();
        display = ImageIO.read(new File("gray.jpg"));

        JFrame window = new JFrame("Frame");
        JMenuBar menuBar = new JMenuBar();
        JMenu editormenu = new JMenu("Przeksztacenia");
        JMenuItem erode = new JMenuItem("Erozja");
        JMenuItem dilate = new JMenuItem("Dylatacja");
        JMenuItem open = new JMenuItem("Otwarcie");
        JMenuItem closing = new JMenuItem("Zamknięcie");
        JMenuItem hitmiss = new JMenuItem("Hit or miss");

        erode.addActionListener((ActionEvent e) -> {
            Erosion.grayscaleImage(imagework);
            display = imagework.image;
            repaint();
        });

        dilate.addActionListener((ActionEvent e) -> {
            Dilation.grayscaleImage(imagework);
            display = imagework.image;
            repaint();
        });
        open.addActionListener((ActionEvent e) -> {
            Opening.grayscaleImage(imagework);
            display = imagework.image;
            repaint();
        });
        closing.addActionListener((ActionEvent e) -> {
            Closing.grayscaleImage(imagework);
            display = imagework.image;
            repaint();
        });
        hitmiss.addActionListener((ActionEvent e) -> {
            int k[] = null;
            k[0] = 1;
            k[1] = 2;
            k[2] = 0;
            HitMiss.binaryImage(imagework, k, 1);
            display = imagework.image;
            repaint();
        });

        window.setVisible(true);
        menuBar.setPreferredSize(new Dimension(imagework.width, 20));
        menuBar.add(editormenu);
        editormenu.add(erode);
        editormenu.add(dilate);
        editormenu.add(open);
        editormenu.add(closing);
        editormenu.add(hitmiss);

        window.add(menuBar);
        window.setJMenuBar(menuBar);
        window.add(this);

        window.setPreferredSize(new Dimension(imagework.width, imagework.height));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();

    }

    public static void main(String[] args) throws IOException {

        new GK10();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(display, 0, 0, null);
    }
}
