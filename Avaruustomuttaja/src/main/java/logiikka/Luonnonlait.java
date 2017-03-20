
package logiikka;

import kappale.Kappale;
import java.util.ArrayList;

public class Luonnonlait {
    
    public void gravitaatio(Kappale kappale, ArrayList<Kappale> kappaleet) {
        double deltaNopeusX = 0;
        double deltaNopeusY = 0;

        for (Kappale vetaja : kappaleet) {
            
            deltaNopeusX += 6.674 * Math.pow(10, -11) * vetaja.getMassa()/ Math.pow((vetaja.getPaikkaX() - kappale.getPaikkaX()),2);
            deltaNopeusY += 6.674 * Math.pow(10, -11) * vetaja.getMassa()/ Math.pow((vetaja.getPaikkaY() - kappale.getPaikkaY()),2);
        }
        
        kappale.muutaNopeus(deltaNopeusX, deltaNopeusY);
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
