/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruustomuttaja.gui;

import avaruustomuttaja.kappale.Kappale;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JSlider;

public class LisaaKappale implements MouseListener {

    Simuloija simuloija;
    JSlider massaSlaideri;
    JSlider xNopeusSlaideri;
    JSlider yNopeusSlaideri;
    
    /**
     * LisaaKappale - toiminnon konstruktori.
     *
     * @param simuloija tällä hetkellä käytettävä simuloija.
     * @param massaSlaideri slaideri, josta massa luetaan.
     * @param xNopeusSlaideri slaideri, josta x-suuntainen nopeus luetaan.
     * @param yNopeusSlaideri slaideri, josta y-suuntainen nopeus luetaan.
     */
    public LisaaKappale(Simuloija simuloija, JSlider massaSlaideri, JSlider xNopeusSlaideri, JSlider yNopeusSlaideri) {
        this.simuloija = simuloija;
        this.massaSlaideri = massaSlaideri;
        this.xNopeusSlaideri = xNopeusSlaideri;
        this.yNopeusSlaideri = yNopeusSlaideri;
    }
    /**
     * Metodi lisaa kappaleen, kun hiirellä klikataan.
     *
     * @param e hiiritapahtuma.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        simuloija.paallaPois();
        int x = e.getX() - 12;
        int y = e.getY() - 31;
        simuloija.lisaaKappale(new Kappale(this.massaSlaideri.getValue(), x, y, this.xNopeusSlaideri.getValue(), this.yNopeusSlaideri.getValue()));
        simuloija.paallaPois();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
