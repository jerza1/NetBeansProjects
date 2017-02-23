package clases;


import javax.swing.JOptionPane;

/**
 *
 * @author jerza
 */
public class Guardar {
    clases.conexion bd = new clases.conexion();
    clases.Mostrar m = new clases.Mostrar();
    
    
    public void registrar_cliente(String id, String nombre, String deuda){
        String host = conexion_trans.login.h.getText();
        String puerto = conexion_trans.login.p.getText();
        String base = conexion_trans.login.b.getText();
        String user = conexion_trans.login.u.getText();
        char pass[] = conexion_trans.login.c.getPassword();
        try{
            bd.conectarBaseDeDatos(host, puerto, base, user, pass);
            //bd.conectarBaseDeDatos();
            bd.sentencia.execute("insert    into    cliente(id,nombre,deuda)    values ('"+id+"','"+nombre+"','"+deuda+"')");
            JOptionPane.showMessageDialog(null, "El Cliente fue Registrado Correctamente", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            bd.commit();
        } catch(Exception e){ JOptionPane.showMessageDialog(null,e.getMessage(), "Error de acceso", JOptionPane.ERROR_MESSAGE); }
    }
public void agregar_Pago(String id,String c_id, String monto, String fecha){
        String host = conexion_trans.login.h.getText();
        String puerto = conexion_trans.login.p.getText();
        String base = conexion_trans.login.b.getText();
        String user = conexion_trans.login.u.getText();
 char pass[] = conexion_trans.login.c.getPassword();
        try{
            bd.conectarBaseDeDatos(host, puerto, base, user, pass);
            //bd.conectarBaseDeDatos();
            bd.sentencia.execute("insert    into    pago   values (null,'"+c_id+"','"+fecha+"','"+monto+"')");
            bd.commit();
            bd.sentencia.execute("update cliente set deuda = cliente.deuda -'"+monto+"'WHERE id = '"+c_id+"'");
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
           // m.mostrar_pagos();
            bd.commit();
        } catch(Exception e){e.printStackTrace();}
}
public void agregar_Prestamo(String id,String c_id, String monto, String fecha){
        String host = conexion_trans.login.h.getText();
        String puerto = conexion_trans.login.p.getText();
        String base = conexion_trans.login.b.getText();
        String user = conexion_trans.login.u.getText();
 char pass[] = conexion_trans.login.c.getPassword();
        try{
            
            bd.conectarBaseDeDatos(host, puerto, base, user, pass);
            //bd.conectarBaseDeDatos();
            bd.sentencia.execute("insert    into    prestamo   values (null,'"+c_id+"','"+fecha+"','"+monto+"')");
            bd.commit();
            bd.sentencia.execute("update cliente set deuda = cliente.deuda +'"+monto+"'WHERE id = '"+c_id+"'");
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            
            //m.mostrar_prestamos();
            bd.commit();
        } catch(Exception e){e.printStackTrace();}
}
}
