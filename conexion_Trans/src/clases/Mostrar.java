
package clases;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jerza
 */
public class Mostrar {
 clases.conexion bd = new clases.conexion();
 /**/
  
    int i=0;
    public void mostrar_datos(){
        String host = conexion_trans.login.h.getText();
        String puerto = conexion_trans.login.p.getText();
        String base = conexion_trans.login.b.getText();
        String user = conexion_trans.login.u.getText();
        char pass[] = conexion_trans.login.c.getPassword();
        try{
            bd.conectarBaseDeDatos(host, puerto, base, user, pass);
            //bd.conectarBaseDeDatos();
           
            i=0;
            bd.resultado = bd.sentencia.executeQuery("SELECT * FROM cliente");
           
            if(bd.resultado != null){
                while(bd.resultado.next()){
                    conexion_trans.inicio.Mitabla.setValueAt(""+bd.resultado.getString("id"),i,0);
                    conexion_trans.inicio.Mitabla.setValueAt(""+bd.resultado.getString("nombre"),i,1);
                    conexion_trans.inicio.Mitabla.setValueAt(""+bd.resultado.getString("deuda"),i,2);
                    i=i+1;
                }
            }
            bd.commit();
        }   catch(Exception e) { JOptionPane.showMessageDialog(null,e.getMessage(), "Error del driver", JOptionPane.ERROR_MESSAGE);}
    }
        public void mostrar_pagos(){
        String host = conexion_trans.login.h.getText();
 String puerto = conexion_trans.login.p.getText();
 String base = conexion_trans.login.b.getText();
 String user = conexion_trans.login.u.getText();
 char pass[] = conexion_trans.login.c.getPassword();
        try{
            bd.conectarBaseDeDatos(host, puerto, base, user, pass);
            //bd.conectarBaseDeDatos();
            i=0;
            bd.resultado = bd.sentencia.executeQuery("SELECT * FROM pago, cliente WHERE cliente.id = cliente_id ORDER BY fecha");
            
            if(bd.resultado != null){
                while(bd.resultado.next()){
                    conexion_trans.pago.Mitabla.setValueAt(""+bd.resultado.getString("id"),i,0);
                    conexion_trans.pago.Mitabla.setValueAt(""+bd.resultado.getString("cliente_id"),i,1);
                    conexion_trans.prestamo.Mitabla.setValueAt(""+bd.resultado.getString("nombre"),i,2);
                    conexion_trans.pago.Mitabla.setValueAt(""+bd.resultado.getString("monto"),i,3);
                    conexion_trans.pago.Mitabla.setValueAt(""+bd.resultado.getString("fecha"),i,4);
                    i=i+1;
                }
            }
            bd.commit();
        }   catch(Exception e) {}
        
        }
        public void mostrar_prestamos(){
        String host = conexion_trans.login.h.getText();
 String puerto = conexion_trans.login.p.getText();
 String base = conexion_trans.login.b.getText();
 String user = conexion_trans.login.u.getText();
 char pass[] = conexion_trans.login.c.getPassword();
        try{
            bd.conectarBaseDeDatos(host, puerto, base, user, pass);
            //bd.conectarBaseDeDatos();
            i=0;
            bd.resultado = bd.sentencia.executeQuery("SELECT * FROM prestamo, cliente WHERE cliente.id = cliente_id ORDER BY fecha");
            
            if(bd.resultado != null){
                while(bd.resultado.next()){
                    conexion_trans.prestamo.Mitabla.setValueAt(""+bd.resultado.getString("id"),i,0);
                    conexion_trans.prestamo.Mitabla.setValueAt(""+bd.resultado.getString("cliente_id"),i,1);
                    conexion_trans.prestamo.Mitabla.setValueAt(""+bd.resultado.getString("nombre"),i,2);
                    conexion_trans.prestamo.Mitabla.setValueAt(""+bd.resultado.getString("monto"),i,3);
                    conexion_trans.prestamo.Mitabla.setValueAt(""+bd.resultado.getString("fecha"),i,4);
                    i=i+1;
                }
            }
            bd.commit();
        }   catch(Exception e) {}
        
        }

   
}   

