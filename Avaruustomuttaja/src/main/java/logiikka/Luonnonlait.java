package logiikka;

import kappale.Kappale;
import java.util.ArrayList;

public class Luonnonlait {

    private double G = 6; //6.674*10^-11
    private double T;

    public static void main(String[] args) {
    }

    public Luonnonlait() {
        T = 1;
    }

    public Luonnonlait(double aika_askel) {
        T = aika_askel;
        if (T <= 0) {
            T = 1;
        }
    }

    public double getAika() {
        return T;
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
            
            deltaNopeusX += suuntaX * T * G * vetaja.getMassa() / Math.pow((vetaja.getPaikkaX() - kappale.getPaikkaX()), 2);
            deltaNopeusY += suuntaY * T * G * vetaja.getMassa() / Math.pow((vetaja.getPaikkaY() - kappale.getPaikkaY()), 2);
        }

        kappale.muutaNopeus(deltaNopeusX, deltaNopeusY);
    }

    public boolean onkoMassaa(Kappale kappale) {
        return kappale.getMassa() > 0;
    }

    public void kappaleetLiikkuu(ArrayList<Kappale> kappaleet) {
        for (Kappale kappale : kappaleet) {
            kappale.muutaPaikka(T);
        }
    }
}
