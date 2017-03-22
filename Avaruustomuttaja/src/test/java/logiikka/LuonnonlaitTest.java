/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;
import kappale.Kappale;
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
        kappale = new Kappale(7.4, 5.3, -2.5, 4.7, -0.2);
        kappaleet.add((new Kappale(5.0, 1, 0)));
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
    }
}
