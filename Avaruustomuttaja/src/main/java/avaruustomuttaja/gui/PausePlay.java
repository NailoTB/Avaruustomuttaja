/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruustomuttaja.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Wagahai
 */
public class PausePlay implements ActionListener {

    Simuloija simuloija;

    public PausePlay(Simuloija simuloija) {
        this.simuloija = simuloija;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.simuloija.paallaPois();
    }

}
