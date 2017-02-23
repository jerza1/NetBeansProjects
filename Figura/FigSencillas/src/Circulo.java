//Circulo.java
import java.awt.*;

public class Circulo extends Punto {
    private double radio;
    public  Circulo( int x, int y, double r){
        super( x, y);
        setRadio(r);
    }
    public void setRadio(double r){this.radio = r > 0 ? r : 0;}
    public void dibuja(Graphics g){
        g.fillOval((int)super.getX(), (int) super.getY(), (int)radio, (int)radio);
    }
}
