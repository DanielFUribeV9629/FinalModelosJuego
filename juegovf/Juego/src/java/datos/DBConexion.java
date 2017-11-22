

package datos;


import java.sql.*;
/**
 *
 * @author alejo
 */
public class DBConexion {
    static String bd = "juego";
    static String login = "postgres";
    static String password = "daniel";
    static String url = "jdbc:postgresql://localhost:5433/"+bd;
    static String mensaje = "";
    
    Connection conexion = null;
    
    public DBConexion() {
        try{
                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection(url,login,password);
                conexion.setAutoCommit(false);

                if (conexion!=null){
                        System.out.println("Conexión a base de datos "+bd+" OK");
                }
        }catch(SQLException e){
                mensaje = e.getMessage()+ "error";
        }catch(ClassNotFoundException e){
                mensaje = e.getMessage()+"error 2";
        }
    }

    public static String getMensaje() {
        return mensaje;
    }

    public static void setMensaje(String mensaje) {
        DBConexion.mensaje = mensaje;
    }
        
    public Connection getConexion(){
        return conexion;
    }

    public void desconectar(){
        conexion = null;
    }

}
