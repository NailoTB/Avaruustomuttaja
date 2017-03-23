/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruustomuttaja.logiikka;

import avaruustomuttaja.logiikka.Luonnonlait;
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
        lait = new Luonnonlait(2.5);
        kappaleet = new ArrayList<>();
        kappale = new Kappale(7, 5, -2, 4, 0);
        kappaleet.add((new Kappale(5, 1, 0)));
        kappaleet.add((new Kappale(10, -2, 5)));
        kappaleet.add(kappale);
    }

    @Test
    public void luonnonlaitLuodaanOikein() {
        assertEquals(2.5, lait.getAika(), 0.0);
    }

    @Test
    public void aikaEiNegatiivinen() {
        Luonnonlait lait2 = new Luonnonlait(-4);
        assertEquals(1, lait2.getAika(), 0.0);
    }

    @Test
    public void kappaleKokeeGravitaatiovuorovaikutuksenOikein() {
        lait.gravitaatio(kappale, kappaleet);
        assertEquals(-3.7487, kappale.getNopeusX(), 0.01);
        assertEquals(21.8112, kappale.getNopeusY(), 0.01);
    }

    @Test
    public void kappaleetLiikkuvatOikein() {
        lait.kappaleetLiikkuu(kappaleet);
        kappale2 = kappaleet.get(1);
        assertEquals(kappale2.getPaikkaX() + kappale2.getNopeusX() * 2.5, kappale2.getPaikkaX(), 0.00);
        assertEquals(kappale2.getPaikkaY() + kappale2.getNopeusY() * 2.5, kappale2.getPaikkaY(), 0.00);
    }

    @Test
    public void onkoMassaaToimii() {
        Kappale massaton = new Kappale(-2, 4, -3);
        assertFalse(lait.onkoMassaa(massaton));
    }
}
