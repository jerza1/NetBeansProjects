//Pino.java
import java.awt.*;
public class Pino extends Punto{
 private int alto;
    public Pino(int x, int y, int a){
        super(x, y); setAlto(a);
    }
    public void setAlto(int a) {alto = a > 0 ? a : 0;}
    public int getAlto(){ return alto;}
    public void dibuja(Graphics g){
        int x, y;
        x = getX(); y = getY();
        Rectangulo r = new Rectangulo(x-alto/8, y + alto/3, alto/4, alto/2);
        Triangulo t1 = new Triangulo(x, y, alto/2, alto/2);
        Triangulo t2 = new Triangulo(x, y-alto/8, alto/2, alto/2);
        Triangulo t3 = new Triangulo(x, y-alto/4, alto/2, alto/2);
        r.setColor(new Color(130, 89, 31));r.dibuja(g);
        g.setColor(new Color(0, 200, 0));t1.dibuja(g); t2.dibuja(g); t3.dibuja(g);
    }
    
    
}