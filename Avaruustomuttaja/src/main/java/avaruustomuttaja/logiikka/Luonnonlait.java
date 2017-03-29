package avaruustomuttaja.logiikka;

import avaruustomuttaja.kappale.Kappale;
import java.util.ArrayList;

public class Luonnonlait {

    private double g = 6.674;

    public Luonnonlait() {
    }

    public void gravitaatio(Kappale kappale, ArrayList<Kappale> kappaleet) {
        double deltaNopeusX = 0;
        double deltaNopeusY = 0;

        for (Kappale vetaja : kappaleet) {
            if (vetaja == kappale) {
                continue;
            }

            int suuntaX = 1;
            int suuntaY = 1;
            double deltapaikkaX = xPaikkaMuutoksenLaskija(vetaja, kappale);
            double deltapaikkaY = yPaikkaMuutoksenLaskija(vetaja, kappale);
            double etaisyys = etaisyydenLaskija(deltapaikkaX, deltapaikkaY);
            double kulma = kulmanLaskija(deltapaikkaX, deltapaikkaY);
            double voima = voimanLaskija(kappale, vetaja, etaisyys);
            
            if (vetaja.getPaikkaX() < kappale.getPaikkaX()) {
                suuntaX = -1;
            }
            if (vetaja.getPaikkaY() < kappale.getPaikkaY()) {
                suuntaY = -1;
            }

            deltaNopeusX += suuntaX * voimanXKomponentinLaskija(voima, kulma);
            deltaNopeusY += suuntaY * voimanYKomponentinLaskija(voima, kulma);

        }

        kappale.muutaNopeus(deltaNopeusX, deltaNopeusY);
    }

    public double xPaikkaMuutoksenLaskija(Kappale vetaja, Kappale kappale) {
        double deltapaikkaX = Math.abs(vetaja.getPaikkaX() - kappale.getPaikkaX());
        return deltapaikkaX;
    }

    public double yPaikkaMuutoksenLaskija(Kappale vetaja, Kappale kappale) {
        double deltapaikkaY = Math.abs(vetaja.getPaikkaY() - kappale.getPaikkaY());
        return deltapaikkaY;
    }

    public double kulmanLaskija(double deltaPaikkaX, double deltaPaikkaY) {
        if (deltaPaikkaY == 0) {
            return 0;
        }
        double kulma = Math.atan(deltaPaikkaX / deltaPaikkaY);
        return kulma;
    }

    public double voimanLaskija(Kappale kappale, Kappale vetaja, double etaisyys) {
        double voima = g * vetaja.getMassa() / Math.pow(etaisyys, 2);
        return voima;
    }

    public double etaisyydenLaskija(double deltaPaikkaX, double deltaPaikkaY) {
        double etaisyys = Math.sqrt(Math.pow(deltaPaikkaX, 2) + Math.pow(deltaPaikkaY, 2));
        return etaisyys;
    }

    public double voimanXKomponentinLaskija(double voima, double kulma) {
        double voimaX = Math.sqrt(Math.pow(voima, 2) - Math.pow(voima * Math.cos(kulma), 2));
        return voimaX;
    }

    public double voimanYKomponentinLaskija(double voima, double kulma) {
        double voimaY = Math.sqrt(Math.pow(voima, 2) - Math.pow(voima * Math.sin(kulma), 2));
        return voimaY;
    }

    public boolean onkoMassaa(Kappale kappale) {
        return kappale.getMassa() > 0;
    }

    public void kappaleetLiikkuu(ArrayList<Kappale> kappaleet) {
        for (Kappale kappale : kappaleet) {
            kappale.muutaPaikka();
        }
    }
}
