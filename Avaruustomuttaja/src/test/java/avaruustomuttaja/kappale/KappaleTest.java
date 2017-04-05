package avaruustomuttaja.kappale;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class KappaleTest {

    Kappale kappale;
    Random randomoija;

    @Before
    public void setUp() {
        kappale = new Kappale(3.33, 2, 1, 2.8, 3.1);
        randomoija = new Random();
    }

    @Test
    public void kappaleLuodaanOikein() {
        assertEquals(3.33, kappale.getMassa(), 0.0);
        assertEquals(2, kappale.getPaikkaX(), 0.0);
        assertEquals(1, kappale.getPaikkaY(), 0.0);
    }

    
    @Test
    public void massaMuuttuuOikein() {
        kappale.muutaMassa(20);
        assertEquals(3.33 + 20, kappale.getMassa(), 0.0001);
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
    public void paikkaMuuttuuOikein() {
        kappale.muutaPaikka();
        assertEquals(2 + 2.8, kappale.getPaikkaX(), 0.0);
        assertEquals(1 + 3.1, kappale.getPaikkaY(), 0.0);
    }

    @Test
    public void nopeusMuuttuuOikein() {
        double muutosX = randomoija.nextInt();
        double muutosY = randomoija.nextInt();
        kappale.muutaNopeus(muutosX, muutosY);
        assertEquals(2.8 + muutosX, kappale.getNopeusX(), 0.0);
        assertEquals(3.1 + muutosY, kappale.getNopeusY(), 0.0);
    }

    @Test
    public void nopeusMuuttuuOikeinNegatiivisella() {
        double muutosX = randomoija.nextInt() * -1;
        double muutosY = randomoija.nextInt();
        kappale.muutaNopeus(muutosX, muutosY);
        assertEquals(2.8 + muutosX, kappale.getNopeusX(), 0.0);
        assertEquals(3.1 + muutosY, kappale.getNopeusY(), 0.0);
    }

    @Test
    public void leveysLasketaanOikein() {
        Kappale uusikappale = new Kappale(30, 0, 0);
        assertEquals(3, uusikappale.laskeLeveys(), 0.001);
    }

    @Test
    public void leveysLasketaanOikeinHyvin() {
        Kappale uusikappale = new Kappale(3, 0, 0);
        assertEquals(0.3, uusikappale.laskeLeveys(), 0.001);
    }

}
