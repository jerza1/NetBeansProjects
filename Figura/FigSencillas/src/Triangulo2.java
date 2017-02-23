//Triangulo2.java
import java.awt.*;

public class Triangulo2 extends Punto{
    private double alto, base;
    public Triangulo2(int x, int y, double a,double b){
        super(x, y); setAlto(a); setBase(b);
    }
    public void setAlto(double a) {alto = a > 0 ? a : 0;}
    public void setBase(double b) {base = b > 0 ? b : 0;}
    
    public void dibuja(Graphics g){
            int xp[]=new int[3];
            xp[0]=(int)super.getX();
            xp[1] = (int)super.getX()+(int)(base);
            xp[2] = (int)super.getX()+(int)(base/2);
            int yp[] = new int [3];
            yp[0] = (int)super.getY();
            yp[1] = (int)super.getY();
            yp[2] = (int)super.getY()+(int)alto;
            
            g.fillPolygon(xp,yp,3);
    }
}

