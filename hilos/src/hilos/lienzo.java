/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jerza
 */
public class lienzo extends javax.swing.JPanel implements Runnable {
    int x = getWidth()/2;
    int y = getHeight()/2;
    Thread hilo;
    public lienzo() {
        initComponents();
        hilo = new Thread(this);
    }
    public void paint (Graphics g){
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.red);
        g.fillOval(x, y,30,30);
    }
    public void inicio(){
        hilo.start();
    }
    public void pausa(){
        hilo.suspend();
    }
    public void continuar(){
        hilo.resume();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public void run(){
        try{
            while(true){
                while(x < getWidth()-30 && y < getHeight()-10){
                    Thread.sleep(50);
                    x+=10; y+=10;
                    repaint();
                }
                while(x > 10 && y > 10){
                    Thread.sleep(50);
                    x-=10; y-=10;
                    repaint();
                }
            }
            
        }catch(Exception e){
            System.out.print("sucedio un error: "+e.getMessage());
        }
    }
}
