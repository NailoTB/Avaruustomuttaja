/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruustomuttaja.logiikka;

import avaruustomuttaja.kappale.Kappale;

/**
 *
 * @author Wagahai
 */
public class Laskuapuri {

    private double g = 6.674;

    public Laskuapuri() {

    }

    /**
     * Metodi laskee kahden kappaleen välisen x-suuntaisen etäisyyden.
     *
     * @param vetaja Ensimmäinen kappale.
     * @param kappale Toinen kappale.
     * @return kappaleiden x-suuntainen etäisyys.
     */
    public double xPaikkaMuutoksenLaskija(Kappale vetaja, Kappale kappale) {
        double deltapaikkaX = Math.abs(vetaja.getPaikkaX() - kappale.getPaikkaX());
        return deltapaikkaX;
    }

    /**
     * Metodi laskee kahden kappaleen välisen y-suuntaisen etäisyyden.
     *
     * @param vetaja Ensimmäinen kappale.
     * @param kappale Toinen kappale.
     * @return kappaleiden y-suuntainen etäisyys.
     */
    public double yPaikkaMuutoksenLaskija(Kappale vetaja, Kappale kappale) {
        double deltapaikkaY = Math.abs(vetaja.getPaikkaY() - kappale.getPaikkaY());
        return deltapaikkaY;
    }

    /**
     * Metodi laskee kahden kappaleen välisen kulman trigonometrisesti.
     *
     * @param deltaPaikkaX Kappaleiden välinen x-suuntainen etäisyys.
     * @param deltaPaikkaY Kappaleiden välinen y-suuntainen etäisyys.
     * @return kappaleiden välinen kulma
     */
    public double kulmanLaskija(double deltaPaikkaX, double deltaPaikkaY) {
        if (deltaPaikkaY == 0) {
            return Math.PI / 2;
        }
        double kulma = Math.atan(deltaPaikkaX / deltaPaikkaY);
        return kulma;
    }

    /**
     * Metodi laskee kahden kappaleen välisen voiman Newtonin painovoimalaista.
     *
     * @param kappale Kappale, johon painovoima lasketaan.
     * @param vetaja Kappale, joka "vetää" toista kappaletta.
     * @param etaisyys Kappaleiden välinen etäisyys.
     * @return vetajan aiheuttaman voiman suuruus kappaleeseen
     */
    public double voimanLaskija(Kappale kappale, Kappale vetaja, double etaisyys) {
        double voima = g * vetaja.getMassa() / Math.pow(etaisyys, 2);
        return voima;
    }

    /**
     * Metodi laskee kahden kappaleen etäisyyden Pythagoraan lauseesta.
     *
     * @param deltaPaikkaX Kahden kappaleen x-suuntainen etäisyys.
     * @param deltaPaikkaY Kahden kappaleen y-suuntainen etäisyys.
     * @return kahden kappaleen etäisyys
     */
    public double etaisyydenLaskija(double deltaPaikkaX, double deltaPaikkaY) {
        double etaisyys = Math.sqrt(Math.pow(deltaPaikkaX, 2) + Math.pow(deltaPaikkaY, 2));
        return etaisyys;
    }

    /**
     * Metodi laskee voiman X-komponentin.
     * <p>
     * Metodi laskee X-komponentin suuruuden trigonometrisesti
     *
     * @param voima Kokonaisvoiman suuruus
     * @param kulma Kahden kappaleen välinen kulma
     * @return voiman X-komponentin suuruus
     */
    public double voimanXKomponentinLaskija(double voima, double kulma) {
        double voimaX = Math.sqrt(Math.pow(voima, 2) - Math.pow(voima * Math.cos(kulma), 2));
        return voimaX;
    }

    /**
     * Metodi laskee voiman Y-komponentin.
     * <p>
     * Metodi laskee Y-komponentin suuruuden trigonometrisesti
     *
     * @param voima Kokonaisvoiman suuruus
     * @param kulma Kahden kappaleen välinen kulma
     * @return voiman Y-komponentin suuruus
     */
    public double voimanYKomponentinLaskija(double voima, double kulma) {
        double voimaY = Math.sqrt(Math.pow(voima, 2) - Math.pow(voima * Math.sin(kulma), 2));
        return voimaY;
    }
}
