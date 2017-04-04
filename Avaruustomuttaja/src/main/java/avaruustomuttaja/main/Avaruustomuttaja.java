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
        kappaleet.add((new Kappale(1000, 500, 500)));
        kappaleet.add((new Kappale(200, 200, 800, -1, 0)));
//        kappaleet.add((new Kappale(100, 600, 200, 2, 3)));
//        kappaleet.add((new Kappale(300, 550, 10, 2, 3)));
        Luonnonlait lait = new Luonnonlait();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(kappaleet, lait);
        SwingUtilities.invokeLater(kayttoliittyma);
        kayttoliittyma.piirraUusiks();
    }
}
