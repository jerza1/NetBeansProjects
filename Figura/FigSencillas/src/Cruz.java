//Cruz.java
import java.awt.*;
public class Cruz extends Punto{
    private int alto;
    public Cruz(int x, int y, int a){
        super(x, y); setAlto(a);
    }
    public void setAlto(int a) {alto = a > 0 ? a : 0;}
    public int getAlto(){ return alto;}
    public void dibuja(Graphics g){
        int x, y;
        x = getX(); y = getY();
        Rectangulo r = new Rectangulo(x, y,alto,(alto*2));
        Rectangulo r1 = new Rectangulo(x+ alto/3, y, alto/3, alto*2);
        Rectangulo r2 = new Rectangulo(x, y+alto/3, alto, alto/3);
        g.setColor(Color.GRAY); r.dibuja(g);
        g.setColor(Color.BLACK);
        r1.dibuja(g); r2.dibuja(g);
        
    }
    
    
}