package clases;

import javax.swing.JOptionPane;

/**
 *
 * @author jerza
 */
public class Actualizar {
  clases.conexion bd = new clases.conexion();
    public void actualizar_cliente(String elegir_id, String deuda){
        String host = conexion_trans.login.h.getText();
 String puerto = conexion_trans.login.p.getText();
 String base = conexion_trans.login.b.getText();
 String user = conexion_trans.login.u.getText();
 char pass[] = conexion_trans.login.c.getPassword();
        try{
            bd.conectarBaseDeDatos(host, puerto, base, user, pass);
            //bd.conectarBaseDeDatos();
            //bd.sentencia.execute("UPDATE    cliente     SET deuda='"+deuda+"' WHERE id='"+elegir_id+"'");
            bd.sentencia.execute("UPDATE cliente SET deuda = "+deuda+" WHERE id = "+elegir_id);
            JOptionPane.showMessageDialog(null, "Los datos del Cliente fueron actualizados", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            bd.commit();
        }  catch(Exception e){}
    }  
}
