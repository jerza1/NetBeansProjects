//Triangulo.java
import java.awt.*;

public class TrianguloRect extends Punto{
    private double lado1, lado2;
    public TrianguloRect(int x, int y, double l1,double l2){
        super(x, y); setBase(l1); setAltura(l2);
    }
    public void setBase(double l1) {lado1 = l1 > 0 ? l1 : 0;}
    public void setAltura(double l2) {lado2 = l2 > 0 ? l2 : 0;}
    
    public void dibuja(Graphics g){
            int xp[]=new int[3];
            xp[0]=(int)super.getX();
            xp[2] = (int)super.getX()+(int)(lado1);
            xp[1] = (int)super.getX();
            int yp[] = new int [3];
            yp[0] = (int)super.getY();
            yp[2] = (int)super.getY();
            yp[1] = (int)super.getY()+(int)(lado2);
            
            g.fillPolygon(xp,yp,3);
    }
}

