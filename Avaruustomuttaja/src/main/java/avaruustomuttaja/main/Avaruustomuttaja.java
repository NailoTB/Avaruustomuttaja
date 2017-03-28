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
    	public static void main(String [] args) throws InterruptedException{
        
            
        ArrayList<Kappale> kappaleet = new ArrayList<>();
        kappaleet.add((new Kappale(5, 100, 100, 1, 0)));
        kappaleet.add((new Kappale(10, 280, 280)));
        Luonnonlait lait = new Luonnonlait(1);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(kappaleet, lait);
        SwingUtilities.invokeLater(kayttoliittyma);
        kayttoliittyma.piirraUusiks();
        }
}
