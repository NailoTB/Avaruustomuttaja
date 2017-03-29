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

/**
 *
 * @author Wagahai
 */
public class Avaruustomuttaja {

    public static void main(String[] args) throws InterruptedException {

        ArrayList<Kappale> kappaleet = new ArrayList<>();
        kappaleet.add((new Kappale(500, 300, 300, 1, 0)));
        kappaleet.add((new Kappale(100, 500, 220)));
        kappaleet.add((new Kappale(50, 250, 100, 1, 0)));
        kappaleet.add((new Kappale(200, 400, 400)));
        kappaleet.add((new Kappale(20, 300, 220)));
        Luonnonlait lait = new Luonnonlait();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(kappaleet, lait);
        SwingUtilities.invokeLater(kayttoliittyma);
        kayttoliittyma.piirraUusiks();
    }
}
