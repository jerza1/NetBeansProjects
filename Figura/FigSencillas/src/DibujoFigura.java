//Prueba.java
import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;

public  class DibujoFigura extends JFrame{
        private Punto [] fig;
        DibujoFigura(String title){
            super(title); setSize(800, 600); 
            setVisible(true);
	    Estrella e = new Estrella(200, 50, 20);
            Luna l = new Luna(600, 100, 70); 
            fig = new Punto[2]; 
            fig[0] =  e; fig[1] = l;
        }
public  void paint(Graphics g){
    super.paint(g);
    Nube nu1 = new Nube(20, 90, 30);
    Rectangulo rec = new Rectangulo(0, 0, 800, 600);
    for(int i = 0; i < 100; i++){
    rec.dibuja(g);
    nu1.dibuja( g );
    nu1.setX(i);
    rec.setX(i);
     fig[0].dibuja(g); fig[1].dibuja(g);
     try{  Thread.sleep(50);
        }catch(InterruptedException ex){;}
    }
}

	public static void main(String[] args) {
		DibujoFigura D = new DibujoFigura("Dibujo BRFJ");
		D.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
	}
}
