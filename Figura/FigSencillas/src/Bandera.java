//Bandera.java
import java.awt.*;
public class Bandera extends Punto {
    private int alto;
    public Bandera(int x, int y, int a){
        super(x, y); setAlto(a);
    }
    public void setAlto(int a) {alto = a > 0 ? a : 0;}
    public int getAlto(){ return alto;}
    public void dibuja(Graphics g){
        int x, y;
        x = getX(); y = getY();
        Rectangulo r = new Rectangulo(x, y, alto/3, alto*2);
        TrianguloRect tri = new TrianguloRect(x+ alto/3, y, alto*2, alto);
        g.setColor(Color.BLACK); r.dibuja(g); 
        g.setColor(Color.BLUE); tri.dibuja(g);
    }
    
    
}
