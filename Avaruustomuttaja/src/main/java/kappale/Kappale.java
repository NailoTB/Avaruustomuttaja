
package kappale;

import java.util.ArrayList;


public class Kappale {
    private double massa;
    private double paikkax;
    private double paikkay;
    private Vektori nopeus;
    
    public Kappale(double massa, double paikkax, double paikkay) {
        this.massa = massa;
        this.paikkax = paikkax;
        this.paikkay = paikkay;
        this.nopeus = new Vektori(0, 0);
    }
    public Kappale(double massa, double paikkax, double paikkay, double nopeusx, double nopeusy) {
        this.massa = massa;
        this.paikkax = paikkax;
        this.paikkay = paikkay;
        this.nopeus = new Vektori(nopeusx, nopeusy);
    }
    public double getMassa() {
        return massa;
    }

    public void setMassa(double massa) {
        this.massa = massa;
    }

    public double getPaikkaX() {
        return paikkax;
    }

    public double getPaikkaY() {
        return paikkay;
    }

    public void muutaPaikka(double aika) {
        this.paikkax += this.nopeus.getX() * aika;
        this.paikkay += this.nopeus.getY() * aika;
    }
    
    public void muutaNopeus(double muutos1, double muutos2) {
        this.nopeus.muutaNopeus(muutos1, muutos2);
    }

}
