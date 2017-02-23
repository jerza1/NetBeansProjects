//DibujaFiguras.java
import java.awt.*;

import java.awt.Graphics;

import javax.swing.JFrame;


public  class DibujaFiguras extends JFrame{

	private Figura [] F;

	DibujaFiguras( String title ){

		super( title );

		setSize( 800, 400  );

		setVisible( true );

		Punto   p = new Punto  ( Color.RED, 200, 200 );

		Circulo c = new Circulo( Color.BLUE, 300, 300, 50  );


		F = new Figura[ 2 ];

		F[ 0 ] = p;

		F[ 1 ] = c;

	}

	public  void paint( Graphics g ){

		super.paint( g );


		for ( int i=0; i<F.length; i++ ) {

			F[ i ].dibuja( g );

		}

		g.drawLine( 5,5, 100, 100 );

		g.fillRect( 600,600, 50, 50 );

		g.fillRoundRect(600,50, 60,70, 50, 50);

		g.fillOval( 600, 200, 100, 50 );

	
}


}
