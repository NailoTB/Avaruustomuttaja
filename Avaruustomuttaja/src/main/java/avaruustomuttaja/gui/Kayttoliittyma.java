package avaruustomuttaja.gui;

import avaruustomuttaja.kappale.Kappale;
import avaruustomuttaja.logiikka.Luonnonlait;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private ArrayList<Kappale> kappaleet;
    Piirtoalusta piirtoalusta;
    Luonnonlait lait;

    public Kayttoliittyma() {
    }

    public Kayttoliittyma(ArrayList<Kappale> kappaleet, Luonnonlait lait) {
        this.kappaleet = kappaleet;
        piirtoalusta = new Piirtoalusta(this.kappaleet);
        this.lait = lait;
    }

    @Override
    public void run() {
        frame = new JFrame("Avaruustomuttaja");
        frame.setPreferredSize(new Dimension(1000, 1000));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.add(piirtoalusta);

    }

    public void piirraUusiks() throws InterruptedException {
        while (true) {
            for (Kappale kappale : kappaleet) {
                lait.gravitaatio(kappale, kappaleet);
            }
            this.lait.kappaleetLiikkuu(kappaleet);

            piirtoalusta.repaint();
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }

    public JFrame getFrame() {
        return frame;
    }
}
