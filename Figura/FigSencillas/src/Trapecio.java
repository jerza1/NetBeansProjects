//Trapecio.java
import java.awt.*;

public class Trapecio extends Punto {
   private double baseMenor, baseMayor, altura;
    public Trapecio(int x, int y, double bmn, double bmy, double a){
        super(x, y); setMenor(bmn); setMayor(bmy); setAltura(a);
    }
    public void setMenor(double bmn){
        baseMenor = bmn > 0 ? bmn : 0;
    }
    public void setMayor(double bmy){
        baseMayor = bmy > 0 ? bmy : 0;
    }
    public void setAltura(double a) {altura = a > 0 ? a : 0;}
    public void dibuja(Graphics g){
        int xp[]=new int[4];
            xp[0]=(int)super.getX();
            xp[1] = (int)super.getX()+(int)baseMayor;
            xp[2] = ((int)super.getX()+((int)baseMayor-(int)(baseMenor))/2)+(int)baseMenor;
            xp[3] = (int)super.getX()+((int)baseMayor-(int)(baseMenor))/2;
            int yp[] = new int [4];
            yp[0] = (int)super.getY();
            yp[1] = (int)super.getY();
            yp[2] = (int)super.getY()+ (int)altura;
            yp[3] = (int)super.getY()+ (int)altura;
            
            g.fillPolygon(xp,yp,4);
    }
    
}
