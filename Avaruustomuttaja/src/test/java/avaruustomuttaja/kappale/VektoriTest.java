/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruustomuttaja.kappale;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wagahai
 */
public class VektoriTest {

    Kappale kappale;

    @Before
    public void setUp() {
        kappale = new Kappale(3.33, 2, 1, 2.8, 3.1);
    }

    @Test
    public void vektoriLuodaanOikein() {
        assertEquals(2.8, kappale.getNopeusX(), 0.0);
        assertEquals(3.1, kappale.getNopeusY(), 0.0);
    }

    @Test
    public void nollaVektoriLuodaanOikein() {
        Kappale uusikappale2 = new Kappale(2.3, 2, 5);
        assertEquals(0, uusikappale2.getNopeusX(), 0.0);
        assertEquals(0, uusikappale2.getNopeusY(), 0.0);
    }
}
