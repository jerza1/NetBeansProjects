package clases;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jerza
 */
public class Buscar {
    clases.conexion bd = new clases.conexion();
    int i= 0;
    public void buscar_cliente(String nombre){
        String host = conexion_trans.login.h.getText();
 String puerto = conexion_trans.login.p.getText();
 String base = conexion_trans.login.b.getText();
 String user = conexion_trans.login.u.getText();
 char pass[] = conexion_trans.login.c.getPassword();
       try{ 
          bd.conectarBaseDeDatos(host, puerto, base, user, pass);
          //bd.conectarBaseDeDatos();
          i=0;
        bd.resultado=bd.sentencia.executeQuery("SELECT * FROM cliente WHERE nombre ='"+nombre+"'");
        if(bd.resultado != null){
            while(bd.resultado.next()){
                conexion_trans.inicio.Mitabla.setValueAt(""+bd.resultado.getString("id"),i,0);
                conexion_trans.inicio.Mitabla.setValueAt(""+bd.resultado.getString("nombre"),i,1); 
                i=i+1;
            }
        }   
        else{
            JOptionPane.showMessageDialog(null, "Ningun Dato Encontrado", "Mensaje de Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
        bd.commit();
       }    catch(Exception e){e.printStackTrace();}
    }
}
