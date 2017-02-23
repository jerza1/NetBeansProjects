//Elipse.java
import java.awt.*;
public class Elipse extends Punto{
    private double ancho, largo;
    public  Elipse(int x, int y, double a, double l){
        super(x, y); setAncho(a); setLargo(l);
    }
    public void setAncho(double a){ancho = a > 0 ? a : 0;}
    public void setLargo(double l){largo = l > 0 ? l : 0;}
    public void dibuja(Graphics g){
        g.fillOval((int)(super.getX()), (int)(super.getY()),(int) ancho, (int)largo );
    }

}
