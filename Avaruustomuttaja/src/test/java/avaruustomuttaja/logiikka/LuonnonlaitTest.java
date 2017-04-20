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
    public void kappaleLiikkuuAskeleenOikein() { //parempi testi työn alla
        kappale2 = kappaleet.get(0);
        ArrayList<Kappale> uusKappale = new ArrayList<>();
        uusKappale.add(kappale2);
        lait.kappaleetLiikkuuAskeleen(uusKappale);
        assertEquals(kappale2.getPaikkaX() + kappale2.getNopeusX(), kappale2.getPaikkaX(), 0.00);
        assertEquals(kappale2.getPaikkaY() + kappale2.getNopeusY(), kappale2.getPaikkaY(), 0.00);
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

    @Test
    public void liikemaaraSailyyTormayksessa() {
        double vanhaNopeus = kappale.getNopeusX();
        double vanhaMassa1 = kappale.getMassa();
        double vanhaMassa2 = kappaleet.get(0).getMassa();
        lait.liikemaaranSailyminen(kappale, kappaleet.get(0));
        assertEquals(vanhaMassa1 * vanhaNopeus / (vanhaMassa1 + vanhaMassa2), kappale.getNopeusX(), 0.01);
    }

    @Test
    public void kappaleetYhdistyvatOikein() {
        double vanhaMassa1 = kappale.getMassa();
        double vanhaMassa2 = kappaleet.get(0).getMassa();
        lait.kappaleidenYhdistyminen(kappale, kappaleet.get(0));
        assertEquals(vanhaMassa1 + vanhaMassa2, kappale.getMassa(), 0.01);
        assertEquals(0, kappaleet.get(0).getMassa(), 0);
    }

}
