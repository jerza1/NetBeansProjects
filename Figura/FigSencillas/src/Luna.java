//Luna.java
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
public class Luna extends Punto {
    private int radio;
    public Luna(int x, int y, int r){
        super(x, y); setRadio(r);
    }
    public void setRadio(int r){ radio = r > 0 ? r : 0;}
    public void dibuja(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        int x, y;
        x = getX(); y = getY();
        GradientPaint gp2 = new GradientPaint (x, y, Color.yellow,x + radio, y + radio, Color.darkGray);
        g2.setPaint(gp2);
        Ellipse2D e1 = new Ellipse2D.Double(x, y, radio, radio);
        Ellipse2D e2 = new Ellipse2D.Double(x + radio/3, y, radio, radio);
        Area a1 = new Area(e1);
        Area a2 = new Area(e2);
        a1.subtract(a2);
        g2.fill(a1);
    }   
}