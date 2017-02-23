package clases;

import javax.swing.JOptionPane;

/**
 *
 * @author jerza
 */
public class Eliminar {
    clases.conexion bd = new clases.conexion();
    clases.Mostrar m = new clases.Mostrar();
    public void eliminar_cliente( String ide ){
        String host = conexion_trans.login.h.getText();
        String puerto = conexion_trans.login.p.getText();
        String base = conexion_trans.login.b.getText();
        String user = conexion_trans.login.u.getText();
        char pass[] = conexion_trans.login.c.getPassword();
        try{
            bd.conectarBaseDeDatos(host, puerto, base, user, pass);
            //bd.conectarBaseDeDatos();
            
            bd.sentencia.execute("delete from cliente WHERE id = ('"+ide+"')");
            bd.commit();
            //bd.sentencia.execute("commit");
            JOptionPane.showMessageDialog(null, "El Cliente fue Eliminado Correctamente", ".::MENSAJE::.", JOptionPane.INFORMATION_MESSAGE);
            //m.mostrar_datos();
            
        } catch(Exception e){e.printStackTrace();}
    }
    public void eliminar_pago( String ide, String deuda, String id ){
        String host = conexion_trans.login.h.getText();
 String puerto = conexion_trans.login.p.getText();
 String base = conexion_trans.login.b.getText();
 String user = conexion_trans.login.u.getText();
 char pass[] = conexion_trans.login.c.getPassword();
        try{
            bd.conectarBaseDeDatos(host, puerto, base, user, pass);
            //bd.conectarBaseDeDatos();
            bd.sentencia.execute("delete from pago WHERE id=('"+ide+"')");
            bd.commit();
            JOptionPane.showMessageDialog(null, "Operacion realizaada correctamente", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            bd.sentencia.execute("UPDATE cliente SET deuda = cliente.deuda +"+deuda+" WHERE id = '"+id+"'");
            bd.commit();
            //m.mostrar_pagos();
        } catch(Exception e){}
    }
    public void eliminar_prestamo( String ide, String deuda, String id ){
        String host = conexion_trans.login.h.getText();
        String puerto = conexion_trans.login.p.getText();
        String base = conexion_trans.login.b.getText();
        String user = conexion_trans.login.u.getText();
        char pass[] = conexion_trans.login.c.getPassword();
        try{
            bd.conectarBaseDeDatos(host, puerto, base, user, pass);
            //bd.conectarBaseDeDatos();
            bd.sentencia.execute("delete from prestamo WHERE id = '"+ide+"'");
            bd.commit();
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            bd.sentencia.execute("UPDATE cliente SET deuda = cliente.deuda - "+deuda+" WHERE id = '"+id+"'");
            bd.commit();
            //m.mostrar_prestamos();
        } catch(Exception e){e.printStackTrace();}
    }
}
