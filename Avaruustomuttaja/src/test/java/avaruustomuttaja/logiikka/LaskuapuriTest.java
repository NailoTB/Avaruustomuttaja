/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruustomuttaja.logiikka;

import avaruustomuttaja.kappale.Kappale;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wagahai
 */
public class LaskuapuriTest {
    
    Laskuapuri laske;
    Kappale kappale;
    Kappale kappale2;
    Kappale kappale3;
    ArrayList<Kappale> kappaleet;

    @Before
    public void setUp() {
        laske = new Laskuapuri();
        kappaleet = new ArrayList<>();
        kappale = new Kappale(7, 5, -2, 4, 0);
        kappaleet.add((new Kappale(5, 1, 0)));
        kappaleet.add(kappale);
    }
    

    @Test
    public void xDeltaLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        assertEquals(4, laske.xPaikkaMuutoksenLaskija(kappale, kappale2), 0.001);
    }

    @Test
    public void yDeltaLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        assertEquals(2, laske.yPaikkaMuutoksenLaskija(kappale, kappale2), 0.001);
    }

    @Test
    public void kulmaLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        double deltapaikkaX = Math.abs(kappale2.getPaikkaX() - kappale.getPaikkaX());
        double deltapaikkaY = Math.abs(kappale2.getPaikkaY() - kappale.getPaikkaY());
        assertEquals(1.107, laske.kulmanLaskija(deltapaikkaX, deltapaikkaY), 0.001);
    }

    @Test
    public void etaisyysLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        double deltapaikkaX = Math.abs(kappale2.getPaikkaX() - kappale.getPaikkaX());
        double deltapaikkaY = Math.abs(kappale2.getPaikkaY() - kappale.getPaikkaY());
        assertEquals(4.472, laske.etaisyydenLaskija(deltapaikkaX, deltapaikkaY), 0.001);
    }

    @Test
    public void kokonaisvoimaLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        double deltapaikkaX = Math.abs(kappale2.getPaikkaX() - kappale.getPaikkaX());
        double deltapaikkaY = Math.abs(kappale2.getPaikkaY() - kappale.getPaikkaY());
        double etaisyys = laske.etaisyydenLaskija(deltapaikkaX, deltapaikkaY);
        assertEquals(1.6685, laske.voimanLaskija(kappale, kappale2, etaisyys), 0.001);
    }

    @Test
    public void voimanYKomponenttiLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        double deltapaikkaX = laske.xPaikkaMuutoksenLaskija(kappale2, kappale);
        double deltapaikkaY = laske.yPaikkaMuutoksenLaskija(kappale2, kappale);
        double etaisyys = laske.etaisyydenLaskija(deltapaikkaX, deltapaikkaY);
        double kulma = laske.kulmanLaskija(deltapaikkaX, deltapaikkaY);
        double voima = laske.voimanLaskija(kappale, kappale2, etaisyys);
        assertEquals(0.7461, laske.voimanYKomponentinLaskija(voima, kulma), 0.001);
    }

    @Test
    public void voimanXKomponenttiLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        double deltapaikkaX = Math.abs(kappale2.getPaikkaX() - kappale.getPaikkaX());
        double deltapaikkaY = Math.abs(kappale2.getPaikkaY() - kappale.getPaikkaY());
        double etaisyys = laske.etaisyydenLaskija(deltapaikkaX, deltapaikkaY);
        double kulma = laske.kulmanLaskija(deltapaikkaX, deltapaikkaY);
        double voima = laske.voimanLaskija(kappale, kappale2, etaisyys);
        assertEquals(1.4923, laske.voimanXKomponentinLaskija(voima, kulma), 0.001);
    }
}
