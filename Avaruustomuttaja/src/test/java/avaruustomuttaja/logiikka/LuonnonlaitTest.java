/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruustomuttaja.logiikka;

import java.util.ArrayList;
import avaruustomuttaja.kappale.Kappale;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wagahai
 */
public class LuonnonlaitTest {

    Luonnonlait lait;
    Kappale kappale;
    Kappale kappale2;
    Kappale kappale3;
    ArrayList<Kappale> kappaleet;

    @Before
    public void setUp() {
        lait = new Luonnonlait();
        kappaleet = new ArrayList<>();
        kappale = new Kappale(7, 5, -2, 4, 0);
        kappaleet.add((new Kappale(5, 1, 0)));
        kappaleet.add(kappale);
    }

    @Test
    public void laitLuodaanOikein() {
        assertNotNull(lait);
        assertSame(lait, lait);
    }

    @Test
    public void kappaleKokeeVainVahanPainovoimaaKaukana() {
        kappaleet = new ArrayList<>();
        kappale = new Kappale(7, 0, 0);
        kappaleet.add(kappale);
        kappaleet.add((new Kappale(50, 1000, 1000)));
        lait.gravitaatio(kappale, kappaleet);
        assertEquals(0, kappale.getNopeusY(), 0.001);
        assertEquals(0, kappale.getNopeusX(), 0.001);
    }

    @Test
    public void xDeltaLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        assertEquals(4, lait.xPaikkaMuutoksenLaskija(kappale, kappale2), 0.001);
    }

    @Test
    public void yDeltaLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        assertEquals(2, lait.yPaikkaMuutoksenLaskija(kappale, kappale2), 0.001);
    }

    @Test
    public void kulmaLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        double deltapaikkaX = Math.abs(kappale2.getPaikkaX() - kappale.getPaikkaX());
        double deltapaikkaY = Math.abs(kappale2.getPaikkaY() - kappale.getPaikkaY());
        assertEquals(1.107, lait.kulmanLaskija(deltapaikkaX, deltapaikkaY), 0.001);
    }

    @Test
    public void etaisyysLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        double deltapaikkaX = Math.abs(kappale2.getPaikkaX() - kappale.getPaikkaX());
        double deltapaikkaY = Math.abs(kappale2.getPaikkaY() - kappale.getPaikkaY());
        assertEquals(4.472, lait.etaisyydenLaskija(deltapaikkaX, deltapaikkaY), 0.001);
    }

    @Test
    public void kokonaisvoimaLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        double deltapaikkaX = Math.abs(kappale2.getPaikkaX() - kappale.getPaikkaX());
        double deltapaikkaY = Math.abs(kappale2.getPaikkaY() - kappale.getPaikkaY());
        double etaisyys = lait.etaisyydenLaskija(deltapaikkaX, deltapaikkaY);
        assertEquals(1.6685, lait.voimanLaskija(kappale, kappale2, etaisyys), 0.001);
    }

    @Test
    public void voimanYKomponenttiLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        double deltapaikkaX = lait.xPaikkaMuutoksenLaskija(kappale2, kappale);
        double deltapaikkaY = lait.yPaikkaMuutoksenLaskija(kappale2, kappale);
        double etaisyys = lait.etaisyydenLaskija(deltapaikkaX, deltapaikkaY);
        double kulma = lait.kulmanLaskija(deltapaikkaX, deltapaikkaY);
        double voima = lait.voimanLaskija(kappale, kappale2, etaisyys);
        assertEquals(1.49235, lait.voimanYKomponentinLaskija(voima, kulma), 0.001);
    }

    @Test
    public void voimanXKomponenttiLasketaanOikein() {
        kappale2 = kappaleet.get(0);
        double deltapaikkaX = Math.abs(kappale2.getPaikkaX() - kappale.getPaikkaX());
        double deltapaikkaY = Math.abs(kappale2.getPaikkaY() - kappale.getPaikkaY());
        double etaisyys = lait.etaisyydenLaskija(deltapaikkaX, deltapaikkaY);
        double kulma = lait.kulmanLaskija(deltapaikkaX, deltapaikkaY);
        double voima = lait.voimanLaskija(kappale, kappale2, etaisyys);
        assertEquals(0.7464, lait.voimanXKomponentinLaskija(voima, kulma), 0.001);
    }

    @Test
    public void kappaleetLiikkuvatOikein() {
        lait.kappaleetLiikkuu(kappaleet);
        kappale2 = kappaleet.get(0);
        assertEquals(kappale2.getPaikkaX() + kappale2.getNopeusX(), kappale2.getPaikkaX(), 0.00);
        assertEquals(kappale2.getPaikkaY() + kappale2.getNopeusY(), kappale2.getPaikkaY(), 0.00);
    }

    @Test
    public void kulmanLaskijaPalauttaaNollanKunOsoittajaNolla() {
        assertEquals(0, lait.kulmanLaskija(0, 5), 0.0);
    }

    @Test
    public void onkoMassaaToimii() {
        Kappale massaton = new Kappale(-2, 4, -3);
        assertFalse(lait.onkoMassaa(massaton));
    }

    @Test
    public void onkoMassaaToimiiNollassa() {
        Kappale massaton = new Kappale(0, 4, -3);
        assertFalse(lait.onkoMassaa(massaton));
    }

    @Test
    public void onkoMassaaToimiiHyvin() {
        Kappale massaton = new Kappale(20, 4, -3);
        assertTrue(lait.onkoMassaa(massaton));
    }

}
