package avaruustomuttaja.logiikka;

import avaruustomuttaja.kappale.Kappale;
import java.util.ArrayList;

public class Luonnonlait {

    private double g = 6.674;

    public Luonnonlait() {
    }
/**
 * Metodi laskee kappaleiden väliset gravitaatiovuorovaikutukset.
 * <p>
 * Metodi käy läpi kaikki mahdolliset kappaleet ja laskee jokaisen kappaleen 
 * vaikutuksten summan ja lisää sen toiseen kappaleeseen.
 *
 * @param  kappale Kappale, johon muiden kappaleiden vuorovaikutus lasketaan
 * @param  kappaleet Lista kaikista kappaleista
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
/**
 * Metodi käy kappaleet läpi ja tarkistaa, ovatko 
 * kappaleet törmäysetäisyydellä.
 * 
 * @param  kappale Isompi kappale.
 * @param  kappaleet Pienempi kappale.
 */
    public void tormays(Kappale kappale, ArrayList<Kappale> kappaleet) {
        for (Kappale tormaaja : kappaleet) {
            if (tormaaja == kappale) {
                continue;
            }

            double deltapaikkaX = xPaikkaMuutoksenLaskija(tormaaja, kappale);
            double deltapaikkaY = yPaikkaMuutoksenLaskija(tormaaja, kappale);

            if (etaisyydenLaskija(deltapaikkaX, deltapaikkaY) < tormaaja.laskeLeveys()) {
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
 * @param  isompi Isompi kappale.
 * @param  pienempi Pienempi kappale.
 */
    public void liikemaaranSailyminen(Kappale isompi, Kappale pienempi) {
        double NopeusX = 0;
        double NopeusY = 0;

        NopeusX = (isompi.getMassa() * isompi.getNopeusX()
                + pienempi.getMassa() * pienempi.getNopeusX())
                / (isompi.getMassa() + pienempi.getMassa());

        NopeusY = (isompi.getMassa() * isompi.getNopeusY()
                + pienempi.getMassa() * pienempi.getNopeusY())
                / (isompi.getMassa() + pienempi.getMassa());

        kappaleidenYhdistyminen(isompi, pienempi);
        isompi.setNopeus(NopeusX, NopeusY);
    }
/**
 * Metodi yhdistää pienemmän kappaleen isompaan.
 * 
 * @param  isompi Isompi kappale.
 * @param  pienempi Pienempi kappale.
 */
    public void kappaleidenYhdistyminen(Kappale isompi, Kappale pienempi) {
        isompi.muutaMassa(pienempi.getMassa());
        pienempi.muutaMassa(-pienempi.getMassa());
    }
/**
 * Metodi laskee kahden kappaleen välisen x-suuntaisen etäisyyden.
 *
 * @param  vetaja Ensimmäinen kappale.
 * @param  kappale Toinen kappale.
 * @return kappaleiden x-suuntainen etäisyys.
 */
    public double xPaikkaMuutoksenLaskija(Kappale vetaja, Kappale kappale) {
        double deltapaikkaX = Math.abs(vetaja.getPaikkaX() - kappale.getPaikkaX());
        return deltapaikkaX;
    }
/**
 * Metodi laskee kahden kappaleen välisen y-suuntaisen etäisyyden.
 *
 * @param  vetaja Ensimmäinen kappale.
 * @param  kappale Toinen kappale.
 * @return kappaleiden y-suuntainen etäisyys.
 */
    public double yPaikkaMuutoksenLaskija(Kappale vetaja, Kappale kappale) {
        double deltapaikkaY = Math.abs(vetaja.getPaikkaY() - kappale.getPaikkaY());
        return deltapaikkaY;
    }
/**
 * Metodi laskee kahden kappaleen välisen kulman trigonometrisesti.
 *
 * @param  deltaPaikkaX Kappaleiden välinen x-suuntainen etäisyys.
 * @param  deltaPaikkaY Kappaleiden välinen y-suuntainen etäisyys.
 * @return kappaleiden välinen kulma
 */
    public double kulmanLaskija(double deltaPaikkaX, double deltaPaikkaY) {
        if (deltaPaikkaY == 0) {
            return Math.PI / 2;
        }
        double kulma = Math.atan(deltaPaikkaX / deltaPaikkaY);
        return kulma;
    }
/**
 * Metodi laskee kahden kappaleen välisen voiman Newtonin painovoimalaista.
 *
 * @param  kappale Kappale, johon painovoima lasketaan.
 * @param  vetaja Kappale, joka "vetää" toista kappaletta.
 * @param  etaisyys Kappaleiden välinen etäisyys.
 * @return vetajan aiheuttaman voiman suuruus kappaleeseen
 */
    public double voimanLaskija(Kappale kappale, Kappale vetaja, double etaisyys) {
        double voima = g * vetaja.getMassa() / Math.pow(etaisyys, 2);
        return voima;
    }
/**
 * Metodi laskee kahden kappaleen etäisyyden Pythagoraan lauseesta.
 *
 * @param  deltaPaikkaX Kahden kappaleen x-suuntainen etäisyys.
 * @param  deltaPaikkaY Kahden kappaleen y-suuntainen etäisyys.
 * @return kahden kappaleen etäisyys
 */
    public double etaisyydenLaskija(double deltaPaikkaX, double deltaPaikkaY) {
        double etaisyys = Math.sqrt(Math.pow(deltaPaikkaX, 2) + Math.pow(deltaPaikkaY, 2));
        return etaisyys;
    }
/**
 * Metodi laskee voiman X-komponentin.
 * <p>
 * Metodi laskee X-komponentin suuruuden trigonometrisesti
 *
 * @param  voima Kokonaisvoiman suuruus
 * @param  kulma Kahden kappaleen välinen kulma
 * @return voiman X-komponentin suuruus
 */
    public double voimanXKomponentinLaskija(double voima, double kulma) {
        double voimaX = Math.sqrt(Math.pow(voima, 2) - Math.pow(voima * Math.cos(kulma), 2));
        return voimaX;
    }
/**
 * Metodi laskee voiman Y-komponentin.
 * <p>
 * Metodi laskee Y-komponentin suuruuden trigonometrisesti
 *
 * @param  voima Kokonaisvoiman suuruus
 * @param  kulma Kahden kappaleen välinen kulma
 * @return voiman Y-komponentin suuruus
 */
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
