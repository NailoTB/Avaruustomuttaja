/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruustomuttaja.main;

import avaruustomuttaja.gui.Kayttoliittyma;
import avaruustomuttaja.kappale.Kappale;
import avaruustomuttaja.logiikka.Luonnonlait;
import java.util.ArrayList;
import javax.swing.SwingUtilities;


public class Avaruustomuttaja {
    /**
     * Main metodi.
     * @param args Magiaa.
     * @throws InterruptedException Errori.
     */
    public static void main(String[] args) throws InterruptedException {

        ArrayList<Kappale> kappaleet = new ArrayList<>();
        kappaleet.add((new Kappale(150, 300, 300)));
        kappaleet.add((new Kappale(50, 200, 100, 1, 0)));
        kappaleet.add((new Kappale(20, 400, 200, -2, 0)));

        Luonnonlait lait = new Luonnonlait();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(kappaleet, lait);
        SwingUtilities.invokeLater(kayttoliittyma);
        kayttoliittyma.simuloi();
    }
}
