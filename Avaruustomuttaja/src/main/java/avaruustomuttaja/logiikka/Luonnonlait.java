package avaruustomuttaja.logiikka;

import avaruustomuttaja.kappale.Kappale;
import java.util.ArrayList;

public class Luonnonlait {

    private double g = 6.674;
    private Laskuapuri laske;

    /**
     * Luonnonlait luokan konstruktori.
     */
    public Luonnonlait() {
        laske = new Laskuapuri();
    }

    /**
     * Metodi laskee kappaleiden väliset gravitaatiovuorovaikutukset.
     * <p>
     * Metodi käy läpi kaikki mahdolliset kappaleet ja laskee jokaisen kappaleen
     * vaikutuksten summan ja lisää sen toiseen kappaleeseen.
     *
     * @param kappale Kappale, johon muiden kappaleiden vuorovaikutus lasketaan
     * @param kappaleet Lista kaikista kappaleista
     */
    public void gravitaatio(Kappale kappale, ArrayList<Kappale> kappaleet) {
        double deltaNopeusX = 0;
        double deltaNopeusY = 0;

        for (Kappale vetaja : kappaleet) {
            if (vetaja == kappale) {
                continue;
            }

            int suuntaX = 1;
            int suuntaY = 1;
            double deltapaikkaX = laske.xPaikkaMuutoksenLaskija(vetaja, kappale);
            double deltapaikkaY = laske.yPaikkaMuutoksenLaskija(vetaja, kappale);
            double etaisyys = laske.etaisyydenLaskija(deltapaikkaX, deltapaikkaY);
            double kulma = laske.kulmanLaskija(deltapaikkaX, deltapaikkaY);
            double voima = laske.voimanLaskija(kappale, vetaja, etaisyys);

            if (vetaja.getPaikkaX() < kappale.getPaikkaX()) {
                suuntaX = -1;
            }
            if (vetaja.getPaikkaY() < kappale.getPaikkaY()) {
                suuntaY = -1;
            }

            deltaNopeusX += suuntaX * laske.voimanXKomponentinLaskija(voima, kulma);
            deltaNopeusY += suuntaY * laske.voimanYKomponentinLaskija(voima, kulma);

        }

        kappale.muutaNopeus(deltaNopeusX, deltaNopeusY);
    }

    /**
     * Metodi käy kappaleet läpi ja tarkistaa, ovatko kappaleet
     * törmäysetäisyydellä.
     *
     * @param kappale Tällä hetkellä käsiteltävä kappale.
     * @param kappaleet Muut kappaleet.
     */
    public void tormays(Kappale kappale, ArrayList<Kappale> kappaleet) {
        for (Kappale tormaaja : kappaleet) {
            if (tormaaja == kappale) {
                continue;
            }

            double deltapaikkaX = laske.xPaikkaMuutoksenLaskija(tormaaja, kappale);
            double deltapaikkaY = laske.yPaikkaMuutoksenLaskija(tormaaja, kappale);
            if (laske.etaisyydenLaskija(deltapaikkaX, deltapaikkaY) < tormaaja.laskeLeveys()
                    || laske.etaisyydenLaskija(deltapaikkaX, deltapaikkaY) < kappale.laskeLeveys()) {
                liikemaaranSailyminen(tormaaja, kappale);
            }
        }
    }

    /**
     * Metodi yhdistää pienemmän kappaleen isompaan ja laskee isomman kappaleen
     * liikemäärän säilymisen.
     *
     * @param kappale1 Ensimmäinen kappale.
     * @param kappale2 Toinen kappale.
     */
    public void liikemaaranSailyminen(Kappale kappale1, Kappale kappale2) {
        double nopeusX = 0;
        double nopeusY = 0;

        nopeusX = (kappale1.getMassa() * kappale1.getNopeusX()
                + kappale2.getMassa() * kappale2.getNopeusX())
                / (kappale1.getMassa() + kappale2.getMassa());

        nopeusY = (kappale1.getMassa() * kappale1.getNopeusY()
                + kappale2.getMassa() * kappale2.getNopeusY())
                / (kappale1.getMassa() + kappale2.getMassa());

        kappaleidenYhdistyminen(kappale1, kappale2);
        kappale1.setNopeus(nopeusX, nopeusY);
    }

    /**
     * Metodi yhdistää pienemmän kappaleen isompaan.
     *
     * @param kappale1 Ensimmäinen kappale.
     * @param kappale2 Toinen kappale.
     */
    public void kappaleidenYhdistyminen(Kappale kappale1, Kappale kappale2) {
        kappale1.muutaMassa(kappale2.getMassa());
        kappale2.muutaMassa(-kappale2.getMassa());
    }

    /**
     * Metodi liikuttaa kaikkia kappaleita askeleen verran.
     *
     * @param kappaleet Kaikki kappaleet.
     */
    public void kappaleetLiikkuuAskeleen(ArrayList<Kappale> kappaleet) {

        for (Kappale kappale : kappaleet) {
            gravitaatio(kappale, kappaleet);
        }

        for (Kappale kappale : kappaleet) {
            tormays(kappale, kappaleet);
        }

        for (Kappale kappale : kappaleet) {
            kappale.muutaPaikka();
        }
    }
}
