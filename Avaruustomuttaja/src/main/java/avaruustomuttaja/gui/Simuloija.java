/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruustomuttaja.gui;

import avaruustomuttaja.kappale.Kappale;
import avaruustomuttaja.logiikka.Luonnonlait;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Simuloija {

    boolean paalla;
    Luonnonlait lait;
    Piirtoalusta piirtoalusta;
    ArrayList<Kappale> kappaleet;

    /**
     * Simuloijan konstruktori.
     *
     * @param lait Lait, joiden mukaan toimitaan.
     * @param alusta Piirtoalusta, johon piirretään.
     * @param kappaleet Kaikki kappaleet, joita liikutetaan.
     */
    public Simuloija(Luonnonlait lait, Piirtoalusta alusta, ArrayList<Kappale> kappaleet) {
        this.paalla = true;
        this.lait = lait;
        this.piirtoalusta = alusta;
        this.kappaleet = kappaleet;
    }

    /**
     * Metodi liikuttaa kappaleita askeleen, ja piirtää alustan uudestaan.
     *
     * @throws InterruptedException errori
     */
    public void piirraUusiks() throws InterruptedException {
        while (true) {
            if (this.paalla) {
                this.lait.kappaleetLiikkuuAskeleen(this.kappaleet);
                piirtoalusta.repaint();
                poistaMassattomat();

                Toolkit.getDefaultToolkit().sync();
            }
            TimeUnit.MILLISECONDS.sleep(20);
        }
    }

    /**
     * Metodi lisää kappaleen listaan.
     *
     * @param kappale Lisättävä kappale.
     */
    public void lisaaKappale(Kappale kappale) {
        this.kappaleet.add(kappale);
    }

    /**
     * Metodi pysäyttää simuloinnin.
     *
     */
    public void paallaPois() {
        this.paalla = !this.paalla;
    }
    /**
     * Metodi käy kappaleet läpi ja poistaa massattomat.
     *
     */
    public void poistaMassattomat() {
        ArrayList<Kappale> uudetKappaleet = new ArrayList<>();
        if (this.paalla) {
            this.paalla = false;
        }
        for (Kappale verrattava : this.kappaleet) {
            if (verrattava.getMassa() > 0) {
                uudetKappaleet.add(verrattava);
            }
        }
        this.kappaleet = uudetKappaleet;
        this.piirtoalusta.setKappaleet(this.kappaleet);
        this.paalla = true;
    }
    /**
     * Metodi käy kappaleet läpi ja poistaa kaikki kappaleet.
     * Metodi resetoi simulaation.
     */
    public void poistaKappaleet() {
        if (this.paalla) {
            this.paalla = false;
        }
        this.kappaleet = new ArrayList<>();
        this.piirtoalusta.setKappaleet(this.kappaleet);
        this.paalla = true;
    }
}
