//Punto.java
import java.awt.*;

public class Punto extends Figura {
	private int x, y;
	private Color color;

	public Punto( ){
		color = new Color( 0,0,0 );
		setX( x );
		setY( y );
	}
	public Punto( Color c, int x, int y){
		color = new Color( 0,0,0 );
		setColor( c );
		setX( x );
		setY( y );
	}

	public void dibuja( Graphics g ){
		g.setColor( color );
		g.fillOval( x,y, 2, 2 );
		g.setColor( color.BLACK );
	}
	public void setX( int x ){
		this.x = x;
	}
	public void setY( int y ){
		this.y = y;
	}
	public int  getX(   ){
	    return this.x;
    }
    public int  getY(  ){
	    return this.y;
    }
    public void setColor( Color c ){
    	this.color = c;
    }
    public Color  getColor(  ){
	    return this.color;
    }

}