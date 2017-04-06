package avaruustomuttaja.gui;

import avaruustomuttaja.kappale.Kappale;
import avaruustomuttaja.logiikka.Luonnonlait;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
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
        frame.setPreferredSize(new Dimension(1000, 500));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.add(piirtoalusta);
        container.add(luoValikko(), BorderLayout.EAST);
    }

    private JPanel luoValikko() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        JSlider massaSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 10);
        massaSlider.setMajorTickSpacing(200);
        massaSlider.setPaintTicks(true);
        massaSlider.setPaintLabels(true);
        
        panel.add(new JLabel("Massa: "));
        panel.add(massaSlider);
        return panel;
    }

    public void piirraUusiks() throws InterruptedException {
        while (true) {
            this.lait.kappaleetLiikkuuAskeleen(kappaleet);

            piirtoalusta.repaint();
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }

    public JFrame getFrame() {
        return frame;
    }
}
