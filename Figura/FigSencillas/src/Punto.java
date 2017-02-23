//Punto.java
import java.awt.*;

public class Punto extends Figura {
    private int x, y;
    private Color color;

    public Punto(){
    setX(x);  setY(y);
    }
    public Punto(int x, int y){
        setX(x);  setY(y);
    }
    public void dibuja(Graphics g){
        g.fillOval(x, y, 1, 1);
    }
    
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public int  getX(){return this.x; }
    public int  getY(){return this.y;}
    public void setColor(Color c){this.color = c;}
    public Color  getColor(){return this.color;}

}