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
     *
     * @param args Magiaa.
     * @throws InterruptedException Errori.
     */
    public static void main(String[] args) throws InterruptedException {

        ArrayList<Kappale> kappaleet = new ArrayList<>();
        kappaleet.add((new Kappale(400, 500, 400)));
        kappaleet.add((new Kappale(30, 500, 100, 3, 0)));
        kappaleet.add((new Kappale(20, 500, 500, -4.5, 0)));
        kappaleet.add((new Kappale(20, 200, 400, 0, -2.5)));

        Luonnonlait lait = new Luonnonlait();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(kappaleet, lait);
        SwingUtilities.invokeLater(kayttoliittyma);
        kayttoliittyma.simuloi();
    }
}
