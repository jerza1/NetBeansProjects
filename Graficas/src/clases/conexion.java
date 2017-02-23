package clases;


import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author jerza
 */
public class conexion {
    public static Connection conexion;
    public Statement sentencia;
    private Principal prin;
    public ResultSet resultado;
    //public void conectarBaseDeDatos(String host, String puerto, String base, String user, char contraseña[]){
    public void conectarBaseDeDatos(){
       // String pass = new String(contraseña);
        try{
            final String CONTROLADOR = "org.gjt.mm.mysql.Driver";
            
            Class.forName(CONTROLADOR);
           
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/horarios", "root", "2109zayer");
            //conexion = DriverManager.getConnection("jdbc:mysql://"+host+":"+puerto+"/"+base, user, pass);
            conexion.setAutoCommit(false);
            sentencia = conexion.createStatement();
        }
    catch(ClassNotFoundException ex1){
        JOptionPane.showMessageDialog(null,ex1.getMessage(), "Error del driver", JOptionPane.ERROR_MESSAGE);
    }
    catch(SQLException ex){
        JOptionPane.showMessageDialog(null,ex.getMessage(), "Error de acceso", JOptionPane.ERROR_MESSAGE);
    }
    }
    public void commit() {
		try {
			conexion.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (Exception ex) {

			}
		}
	}
    public void desconectarBaseDeDatos(){
        try{
            if(conexion != null){
                if(sentencia != null){
                    sentencia.close();
                }
                conexion.close();
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}