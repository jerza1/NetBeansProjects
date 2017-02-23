package clases;

import javax.swing.JOptionPane;

/**
 *
 * @author jerza
 */
public class Revisar {
    clases.conexion bd = new clases.conexion();
 /**/
  
    public void revisar_deuda(){
        String host = conexion_trans.login.h.getText();
 String puerto = conexion_trans.login.p.getText();
 String base = conexion_trans.login.b.getText();
 String user = conexion_trans.login.u.getText();
 char pass[] = conexion_trans.login.c.getPassword();
        String id = conexion_trans.inicio.i.getText();
        try{
            int d, pg,ps;
            bd.conectarBaseDeDatos(host, puerto, base, user, pass);
            //bd.conectarBaseDeDatos();
            
            bd.resultado = bd.sentencia.executeQuery("SELECT * FROM cliente WHERE id = "+id);
            if(bd.resultado != null){
                while(bd.resultado.next()){
                conexion_trans.revisar.nom.setText(bd.resultado.getString("nombre"));
                conexion_trans.revisar.deu.setText(bd.resultado.getString("deuda"));
                }}
            else{  JOptionPane.showMessageDialog(null, "Ningun Dato Encontrado", "Mensaje de Informacion", JOptionPane.INFORMATION_MESSAGE); }
            
            bd.resultado = bd.sentencia.executeQuery("SELECT sum(monto) AS deuda FROM prestamo WHERE cliente_id = "+id);
            if(bd.resultado != null ){
                while(bd.resultado.next()){conexion_trans.revisar.pres.setText(bd.resultado.getString("deuda"));}}
            else{JOptionPane.showMessageDialog(null, "Ningun Dato Encontrado", "Mensaje de Informacion", JOptionPane.INFORMATION_MESSAGE);}
             
            bd.resultado = bd.sentencia.executeQuery("select sum(monto) as deuda from pago where cliente_id ="+id);
            if(bd.resultado != null){
                while(bd.resultado.next()){
                    conexion_trans.revisar.pagos.setText(bd.resultado.getString("deuda")); } }
            else{ JOptionPane.showMessageDialog(null, "Ningun Dato Encontrado", "Mensaje de Informacion", JOptionPane.INFORMATION_MESSAGE);}
            
            if("".equals(conexion_trans.revisar.deu.getText())){  d = 0;  conexion_trans.revisar.deu.setText("0");} 
            else  {d = Integer.parseInt(conexion_trans.revisar.deu.getText());}
            if("".equals(conexion_trans.revisar.pagos.getText())){ pg = 0;  conexion_trans.revisar.pagos.setText("0");}
            else{ pg = Integer.parseInt(conexion_trans.revisar.pagos.getText());}
            if("".equals(conexion_trans.revisar.pres.getText())){ ps = 0;  conexion_trans.revisar.pres.setText("0");}
            else{ ps = Integer.parseInt(conexion_trans.revisar.pres.getText());}
            
           if(d == (ps-pg)){
                JOptionPane.showMessageDialog(null, "Los datos son correctos");
            }
            else{    JOptionPane.showMessageDialog(null, "Los datos no son correctos");      }
           bd.commit();
            
            } catch(Exception e) {e.printStackTrace();}
    }
}
