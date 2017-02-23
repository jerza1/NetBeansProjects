//Estrella.java
import java.awt.*;
public class Estrella extends Punto {
    private int altura;
    public Estrella(int x, int y, int a){
        super(x, y); setAltura(a);
    }
    public void setAltura(int a) {altura = a > 0 ? a : 0;}
    public int getAltura(){ return altura;}
    public void dibuja(Graphics g){
        int x, y;
        x = getX(); y = getY();
        Triangulo2 t2 = new Triangulo2(x, y , altura, altura);
        Triangulo t1 = new Triangulo(x + altura/2, y - altura/3, altura, altura);
        g.setColor(Color.RED);
        t1.dibuja(g); t2.dibuja(g); 
    }
    
    
}
