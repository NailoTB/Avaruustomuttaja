package avaruustomuttaja.kappale;

import java.awt.Graphics;

public class Kappale {

    private double massa;
    private double paikkax;
    private double paikkay;
    private Vektori nopeus;
    
     /**
     * Pysähtyneen kappaleen konstruktori.
     *
     * @param massa Luotavan kappaleen massa.
     * @param paikkax Luotavan kappaleen x-sijainti.
     * @param paikkay Luotavan kappaleen y-sijainti.
     */
    public Kappale(double massa, double paikkax, double paikkay) {
        this.massa = massa;
        this.paikkax = paikkax;
        this.paikkay = paikkay;
        this.nopeus = new Vektori(0, 0);
    }
     /**
     * Liikkeessä olevan kappaleen konstruktori.
     *
     * @param massa Luotavan kappaleen massa.
     * @param paikkax Luotavan kappaleen x-sijainti.
     * @param paikkay Luotavan kappaleen y-sijainti.
     * @param nopeusx Luotavan kappaleen x-suuntainen nopeus.
     * @param nopeusy Luotavan kappaleen y-suuntainen nopeus.
     */
    public Kappale(double massa, double paikkax, double paikkay, double nopeusx, double nopeusy) {
        this.massa = massa;
        this.paikkax = paikkax;
        this.paikkay = paikkay;
        this.nopeus = new Vektori(nopeusx, nopeusy);
    }

    public double getMassa() {
        return massa;
    }
    
     /**
     * Metodi muuttaa kappaleen massaa parametrin verran.
     *
     * @param deltamassa Massanmuutos.
     */
    public void muutaMassa(double deltamassa) {
        this.massa += deltamassa;
    }

    public double getPaikkaX() {
        return paikkax;
    }

    public double getPaikkaY() {
        return paikkay;
    }

    public double getNopeusX() {
        return this.nopeus.getX();
    }

    public double getNopeusY() {
        return this.nopeus.getY();
    }

    public void setNopeus(double x, double y) {
        this.nopeus.setVektori(x, y);
    }
    
     /**
     * Metodi laskee kappaleen piirrettävän leveyden.
     *
     * @return Kappaleen leveys piirtoalustalla.
     */
    public double laskeLeveys() {
        double leveys = this.massa / 10;
        return leveys;
    }
     /**
     * Metodi laskee kappaleen paikan askeleen jälkeen.
     */
    public void muutaPaikka() {
        this.paikkax += this.nopeus.getX();
        this.paikkay += this.nopeus.getY();
    }
     /**
     * Metodi muuttaa kappaleen nopeutta muutoksen verran.
     * 
     * @param muutos1 Nopeuden muutos x-suunnassa.
     * @param muutos2 Nopeuden muutos y-suunnassa.
     */
    public void muutaNopeus(double muutos1, double muutos2) {
        this.nopeus.muutaNopeus(muutos1, muutos2);
    }
     /**
     * Metodi piirtää kappaleen.
     * 
     * @param graphics Käytettävä graphics luokka.
     */
    public void piirra(Graphics graphics) {
        int x = (int) this.paikkax;
        int y = (int) this.paikkay;
        int leveys = (int) laskeLeveys();
        graphics.fillOval(x - leveys/2, y - leveys/2, leveys, leveys);
    }

}
