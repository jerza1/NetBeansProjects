//Nube.java
import java.awt.*;
public class Nube extends Punto{
     private int radio;
    public  Nube( int x, int y, int r){
        super( x, y);
        setRadio(r);
    }
    public void setRadio(int r){this.radio = r > 0 ? r : 0;}
    public void dibuja(Graphics g){
        int x, y;
        x = getX(); y = getY();
        Circulo c1 = new Circulo(x, y+radio/2, radio);
        Circulo c2 = new Circulo(x+radio/2, y, radio);
        Circulo c3 = new Circulo(x+radio, y, radio);
        Circulo c4 = new Circulo(x+radio/2, y+radio/2, radio);
        Circulo c5 = new Circulo(x+radio, y+radio/2, radio);
        Circulo c6 = new Circulo(x+radio+radio/2, y+radio/2, radio);
        g.setColor(Color.GRAY);
        c1.dibuja(g); c2.dibuja(g); c3.dibuja(g); c4.dibuja(g); c5.dibuja(g); c6.dibuja(g);  
    }
    
    
}