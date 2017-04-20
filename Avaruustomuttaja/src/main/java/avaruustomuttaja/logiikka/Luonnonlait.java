package avaruustomuttaja.logiikka;

import avaruustomuttaja.kappale.Kappale;
import java.util.ArrayList;

public class Luonnonlait {

    private double g = 6.674;
    private Laskuapuri laske;

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
     * @param kappale Isompi kappale.
     * @param kappaleet Pienempi kappale.
     */
    public void tormays(Kappale kappale, ArrayList<Kappale> kappaleet) {
        for (Kappale tormaaja : kappaleet) {
            if (tormaaja == kappale) {
                continue;
            }

            double deltapaikkaX = laske.xPaikkaMuutoksenLaskija(tormaaja, kappale);
            double deltapaikkaY = laske.yPaikkaMuutoksenLaskija(tormaaja, kappale);

            if (laske.etaisyydenLaskija(deltapaikkaX, deltapaikkaY) < tormaaja.laskeLeveys() - 1) {
                if (tormaaja.getMassa() >= kappale.getMassa()) {
                    liikemaaranSailyminen(tormaaja, kappale);
                } else {
                    liikemaaranSailyminen(kappale, tormaaja);
                }
            }
        }
    }

    /**
     * Metodi yhdistää pienemmän kappaleen isompaan ja laskee isomman kappaleen
     * liikemäärän säilymisen.
     *
     * @param isompi Isompi kappale.
     * @param pienempi Pienempi kappale.
     */
    public void liikemaaranSailyminen(Kappale isompi, Kappale pienempi) {
        double nopeusX = 0;
        double nopeusY = 0;

        nopeusX = (isompi.getMassa() * isompi.getNopeusX()
                + pienempi.getMassa() * pienempi.getNopeusX())
                / (isompi.getMassa() + pienempi.getMassa());

        nopeusY = (isompi.getMassa() * isompi.getNopeusY()
                + pienempi.getMassa() * pienempi.getNopeusY())
                / (isompi.getMassa() + pienempi.getMassa());

        kappaleidenYhdistyminen(isompi, pienempi);
        isompi.setNopeus(nopeusX, nopeusY);
    }

    /**
     * Metodi yhdistää pienemmän kappaleen isompaan.
     *
     * @param isompi Isompi kappale.
     * @param pienempi Pienempi kappale.
     */
    public void kappaleidenYhdistyminen(Kappale isompi, Kappale pienempi) {
        isompi.muutaMassa(pienempi.getMassa());
        pienempi.muutaMassa(-pienempi.getMassa());
    }

    public boolean onkoMassaa(Kappale kappale) {
        return kappale.getMassa() > 0;
    }

    public void kappaleetLiikkuuAskeleen(ArrayList<Kappale> kappaleet) {

        for (Kappale kappale : kappaleet) {
            gravitaatio(kappale, kappaleet);
            tormays(kappale, kappaleet);
        }
        for (Kappale kappale : kappaleet) {
            kappale.muutaPaikka();
        }
    }
}
