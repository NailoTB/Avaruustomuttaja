package avaruustomuttaja.gui;

import avaruustomuttaja.kappale.Kappale;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel {

    ArrayList<Kappale> kappaleet;

    /**
     * Piirtoalustan konstruktori.
     *
     * @param kappaleet kaikki kappaleet.
     */
    public Piirtoalusta(ArrayList<Kappale> kappaleet) {
        super.setBackground(Color.BLACK);
        this.kappaleet = kappaleet;
    }

    /**
     * Metodi asettaa piirtoalustalle piirrettävät kappaleet.
     *
     * @param uudetKappaleet piirtoalustalle asetettavat uudet kappaleet
     */
    public void setKappaleet(ArrayList<Kappale> uudetKappaleet) {
        this.kappaleet = uudetKappaleet;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (Kappale kappale : kappaleet) {
            kappale.piirra(graphics);
        }
    }

}
