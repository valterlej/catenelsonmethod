package cate.nelson;

/**
 * Representa um par de valores (X,Y)
 * 
 * @author Valter Estevam
 */
public class Par {

    /**
     * Valor para o atributo do solo
     */
    private double x;
    
    /**
     * Valor para o rendimento relativo
     */
    private double y;  

    public Par(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }
    
}
