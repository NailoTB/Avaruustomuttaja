
package kappale;

import java.util.ArrayList;


public class Kappale {
    private double massa;
    private double paikkax;
    private double paikkay;
    private double nopeusx;
    private double nopeusy;
    
    public Kappale(double massa, double paikkax, double paikkay) {
        this.massa = massa;
        this.paikkax = paikkax;
        this.paikkay = paikkay;
        this.nopeusx = 0;
        this.nopeusy = 0;
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

    public void muutaPaikka() {
        this.paikkax += this.nopeusx;
        this.paikkay += this.nopeusy;
    }
    
    public void muutaNopeus(double muutos1, double muutos2) {
        this.nopeusx += muutos1;
        this.nopeusy += muutos2;
    }

}
