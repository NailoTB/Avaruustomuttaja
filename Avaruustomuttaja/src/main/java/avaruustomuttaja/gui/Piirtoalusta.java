package avaruustomuttaja.gui;

import avaruustomuttaja.kappale.Kappale;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel {

    ArrayList<Kappale> kappaleet;
    
    public Piirtoalusta(ArrayList<Kappale> kappaleet) {
        super.setBackground(Color.WHITE);
        this.kappaleet = kappaleet;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        for(Kappale kappale : kappaleet) {
            kappale.piirra(graphics);
        }
    }
    
}
