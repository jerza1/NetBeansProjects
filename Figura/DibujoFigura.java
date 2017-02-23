//Prueba.java
import java.awt.*;
import javax.swing.JFrame;

public  class DibujoFigura extends JFrame{
	private Figura [] F;
        DibujoFigura(String title){
            super(title); setSize(800, 400); 
            getContentPane().setBackground(new Color(255, 255, 255));
            setVisible(true);
            Punto p = new Punto(10, 50);
            Circulo c = new Circulo(400, 200, 80);
            Rectangulo rec = new Rectangulo(100, 100, 250, 30);
            Triangulo tri = new Triangulo(200, 100, 40, 40);
            Elipse elip = new Elipse( 50, 50, 60, 80);
            
	    F = new Figura[5];
	    F[0] = p;
            F[1] = c;
            F[2] = rec;
            F[3] = tri;
            F[4] = elip;
        }
public  void paint(Graphics g){
    super.paint(g);
    Insets s = getInsets();
    g.translate(s.left, s.top);
    Color colores[] = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,
             Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK} ;
    for (int i = 0; i < F.length; i++ ) {
        g.setColor(colores[(int)(Math.random()*colores.length)]);
			F[i].dibuja(g);
    }
	}
	public static void main(String[] args) {
		DibujoFigura D = new DibujoFigura("Dibujo BRFJ");
		D.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
	}
}
