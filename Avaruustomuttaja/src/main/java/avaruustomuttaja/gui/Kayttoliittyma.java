package avaruustomuttaja.gui;

import avaruustomuttaja.kappale.Kappale;
import avaruustomuttaja.logiikka.Luonnonlait;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private ArrayList<Kappale> kappaleet;
    Piirtoalusta piirtoalusta;
    Luonnonlait lait;
    boolean paalla;
    Simuloija simuloija;

    /**
     * Kayttoliittyman konstruktori.
     *
     * @param lait kayttoliittymassa käytettävä Luonnonlait-luokka.
     * @param kappaleet Lista kaikista kappaleista
     */
    public Kayttoliittyma(ArrayList<Kappale> kappaleet, Luonnonlait lait) {
        this.kappaleet = kappaleet;
        piirtoalusta = new Piirtoalusta(this.kappaleet);
        this.lait = lait;
        this.paalla = true;
        this.simuloija = new Simuloija(lait, piirtoalusta, kappaleet);
    }

    @Override
    public void run() {
        frame = new JFrame("Avaruustomuttaja");
        frame.setPreferredSize(new Dimension(1280, 720));

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
        JPanel panel = new JPanel(new GridLayout(8, 1));
        JButton pausenappi = new JButton("Pause / Play");
        JButton resetnappi = new JButton("Reset");

        Reset reset = new Reset(this.simuloija);
        PausePlay paallePois = new PausePlay(this.simuloija);

        pausenappi.addActionListener(paallePois);
        resetnappi.addActionListener(reset);

        JSlider massaSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
        JSlider xNopeusSlider = new JSlider(JSlider.HORIZONTAL, -15, 15, 0);
        JSlider yNopeusSlider = new JSlider(JSlider.HORIZONTAL, -15, 15, 0);

        massaSlider.setMajorTickSpacing(50);
        massaSlider.setPaintTicks(true);
        massaSlider.setPaintLabels(true);
        xNopeusSlider.setMajorTickSpacing(5);
        xNopeusSlider.setPaintTicks(true);
        xNopeusSlider.setPaintLabels(true);
        yNopeusSlider.setMajorTickSpacing(5);
        yNopeusSlider.setPaintTicks(true);
        yNopeusSlider.setPaintLabels(true);
        panel.add(pausenappi);
        panel.add(resetnappi);
        panel.add(new JLabel("X-suuntainen nopeus: ", SwingConstants.CENTER));
        panel.add(xNopeusSlider);
        panel.add(new JLabel("Y-suuntainen nopeus: ", SwingConstants.CENTER));
        panel.add(yNopeusSlider);
        panel.add(new JLabel("Massa: ", SwingConstants.CENTER));
        panel.add(massaSlider);
        frame.addMouseListener(new LisaaKappale(this.simuloija, massaSlider, xNopeusSlider, yNopeusSlider));

        return panel;
    }

    /**
     * Käskee simuloijaa simuloimaan ja piirtämään alustan uudestaan.
     *
     * @throws InterruptedException tämmöne errori
     */
    public void simuloi() throws InterruptedException {
        this.simuloija.piirraUusiks();
    }

    public JFrame getFrame() {
        return frame;
    }
}
