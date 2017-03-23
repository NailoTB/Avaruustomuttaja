package avaruustomuttaja.logiikka;

import avaruustomuttaja.kappale.Kappale;
import java.util.ArrayList;

public class Luonnonlait {

    private double g = 6; //6.674*10^-11
    private double t;

    public Luonnonlait() {
        t = 1;
    }

    public Luonnonlait(double aikaAskel) {
        t = aikaAskel;
        if (t <= 0) {
            t = 1;
        }
    }

    public double getAika() {
        return t;
    }

    public void gravitaatio(Kappale kappale, ArrayList<Kappale> kappaleet) {
        double deltaNopeusX = 0;
        double deltaNopeusY = 0;

        for (Kappale vetaja : kappaleet) {
            int suuntaX = 1;
            int suuntaY = 1;

            if (vetaja.getPaikkaX() == kappale.getPaikkaX() && vetaja.getPaikkaY() == kappale.getPaikkaY()) {
                continue;
            }

            if (vetaja.getPaikkaX() < kappale.getPaikkaX()) {
                suuntaX = -1;
            }

            if (vetaja.getPaikkaY() < kappale.getPaikkaY()) {
                suuntaY = -1;
            }
            
            if (!(vetaja.getPaikkaX() == kappale.getPaikkaX())) {
                deltaNopeusX += suuntaX * t * g * vetaja.getMassa() / Math.pow((vetaja.getPaikkaX() - kappale.getPaikkaX()), 2);
            }
            
            if (!(vetaja.getPaikkaY() == kappale.getPaikkaY())) {
                deltaNopeusY += suuntaY * t * g * vetaja.getMassa() / Math.pow((vetaja.getPaikkaY() - kappale.getPaikkaY()), 2);
            }

        }

        kappale.muutaNopeus(deltaNopeusX, deltaNopeusY);
    }

    public boolean onkoMassaa(Kappale kappale) {
        return kappale.getMassa() > 0;
    }

    public void kappaleetLiikkuu(ArrayList<Kappale> kappaleet) {
        for (Kappale kappale : kappaleet) {
            kappale.muutaPaikka(t);
        }
    }
}
