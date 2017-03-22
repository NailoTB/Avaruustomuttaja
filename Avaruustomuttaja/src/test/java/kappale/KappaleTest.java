package kappale;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class KappaleTest {

    Kappale uusikappale;
    Random randomoija;

    @Before
    public void setUp() {
        uusikappale = new Kappale(3.33, 2, 1, 2.8, 3.1);
        randomoija = new Random();
    }

    @Test
    public void kappaleLuodaanOikein() {
        assertEquals(3.33, uusikappale.getMassa(), 0.0);
        assertEquals(2, uusikappale.getPaikkaX(), 0.0);
        assertEquals(1, uusikappale.getPaikkaY(), 0.0);
    }

    @Test
    public void vektoriLuodaanOikein() {
        assertEquals(2.8, uusikappale.getNopeusX(), 0.0);
        assertEquals(3.1, uusikappale.getNopeusY(), 0.0);
    }

    @Test
    public void negatiivisillaArvoillaKappaleLuodaanOikein() {
        Kappale negatiivinenkappale = new Kappale(1, -2, -5, -4.6, -0.5);
        assertEquals(-2, negatiivinenkappale.getPaikkaX(), 0.0);
        assertEquals(-5, negatiivinenkappale.getPaikkaY(), 0.0);
        assertEquals(-0.5, negatiivinenkappale.getNopeusY(), 0.0);
        assertEquals(-4.6, negatiivinenkappale.getNopeusX(), 0.0);

    }

    @Test
    public void nollaVektoriLuodaanOikein() {
        Kappale uusikappale2 = new Kappale(2.3, 2, 5);
        assertEquals(0, uusikappale2.getNopeusX(), 0.0);
        assertEquals(0, uusikappale2.getNopeusY(), 0.0);
    }

    @Test
    public void paikkaEiMuutuNollallaSekunnilla() {
        uusikappale.muutaPaikka(0);
        assertEquals(2, uusikappale.getPaikkaX(), 0.0);
        assertEquals(1, uusikappale.getPaikkaY(), 0.0);
    }

    @Test
    public void paikkaMuuttuuOikeinYhdellaSekunnilla() {
        uusikappale.muutaPaikka(1);
        assertEquals(2 + 2.8, uusikappale.getPaikkaX(), 0.0);
        assertEquals(1 + 3.1, uusikappale.getPaikkaY(), 0.0);
    }

    @Test
    public void paikkaMuuttuuOikeinSatunnaisellaAjalla() {
        double aika = randomoija.nextInt();
        uusikappale.muutaPaikka(aika);
        assertEquals(2 + (2.8 * aika), uusikappale.getPaikkaX(), 0.0);
        assertEquals(1 + (3.1 * aika), uusikappale.getPaikkaY(), 0.0);
    }

    @Test
    public void nopeusMuuttuuOikein() {
        double muutosX = randomoija.nextInt();
        double muutosY = randomoija.nextInt();
        uusikappale.muutaNopeus(muutosX, muutosY);
        assertEquals(2.8 + muutosX, uusikappale.getNopeusX(), 0.0);
        assertEquals(3.1 + muutosY, uusikappale.getNopeusY(), 0.0);
    }

    @Test
    public void nopeusMuuttuuOikeinNegatiivisella() {
        double muutosX = randomoija.nextInt() * -1;
        double muutosY = randomoija.nextInt();
        uusikappale.muutaNopeus(muutosX, muutosY);
        assertEquals(2.8 + muutosX, uusikappale.getNopeusX(), 0.0);
        assertEquals(3.1 + muutosY, uusikappale.getNopeusY(), 0.0);
    }

}
