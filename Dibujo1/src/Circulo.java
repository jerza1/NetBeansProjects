import java.awt.*;

public class Circulo extends Punto {
	private double radio;

	public  Circulo( Color c, int x, int y, double r ){
		super( c , x, y );
		setRadio( r );
	}
	public void setRadio( double r ){
		this.radio = r>0?r:0;		
	}

	public void dibuja( Graphics g ){
		g.setColor( super.getColor() );
		g.fillOval( (int)(super.getX()-2*radio), (int)(super.getY()-2*radio), (int)radio, (int)radio );
		g.setColor( Color.BLACK );
	}
	
}
