
package avaruustomuttaja.kappale;

public class Vektori {
    private double x;
    private double y;
    
    public Vektori(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void muutaNopeus(double x, double y) {
        this.x += x;
        this.y += y;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }   

    public void setVektori(double x, double y) {
        this.x = x;
        this.y = y;
    }

    
    
}
