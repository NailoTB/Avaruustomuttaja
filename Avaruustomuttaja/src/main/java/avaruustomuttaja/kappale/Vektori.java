
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

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    
    
}