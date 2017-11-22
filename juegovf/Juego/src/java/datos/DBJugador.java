/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Jugador;
import logica.Jugador;
import logica.Puntaje;

/**
 *
 * @author lenovo
 */
public class DBJugador {
    DBConexion cn;

    public DBJugador() {
        cn = new DBConexion();
    }
     public boolean verificarJugador(String usuario,String contraseña) throws SQLException{

         PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT nickname,e_mail, password"+
                                                                     "FROM jugador" );
        
        ResultSet res =pstm.executeQuery();
         if(res.next()){
             return ((res.getString("nickname").equals(usuario) || (res.getString("e_mail").equals(usuario))
                     && res.getString("password").equals(contraseña)));
         }
         return false;
     }
     public Jugador[] mostrarJugador() throws SQLException{
         int registros = 0,i=0;

        try{
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT count(1) as cont" +
                                                                            " FROM jugador ");
            
            ResultSet res = pstm.executeQuery();

            res.next();
            registros = res.getInt("cont");
            res.close();	
            
            
        }catch(SQLException e){
            System.out.println(e);
        }
        Jugador[] jugador = new Jugador[registros];
         PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT *"+
                                                                     "FROM jugador" );

        ResultSet res = pstm.executeQuery();

        while(res.next()){
            jugador[i]=new Jugador();
            jugador[i].setId((int)res.getInt("id"));
            jugador[i].setNombre(res.getString("nombre"));
            jugador[i].setPais(res.getInt("pais"));
            jugador[i].setE_mail(res.getString("e_mail"));
            jugador[i].setNickname(res.getString("nickname"));
            jugador[i].setApellido(res.getString("apellido"));
            i++;
        }
        return jugador;
     }
     public void registrarjugador (Jugador jugador) throws SQLException{
         PreparedStatement pstm = cn.getConexion().prepareStatement("INSERT INTO jugador (nickname,password,nombre, apellido,"+
                                                                     "e_mail,fecha_nacimiento,pais,avatar) VALUES (?,?,?,?,?,?,?,?); commit;"); 
         pstm.setString(1, jugador.getNickname());
         pstm.setString(2, jugador.getPassword());
         pstm.setString(3, jugador.getNombre());
         pstm.setString(4, jugador.getApellido());
         pstm.setString(5, jugador.getE_mail());
         pstm.setDate(6, jugador.getFecha_Nacimiento());
         pstm.setInt(7, jugador.getPais());
         pstm.setString(8, jugador.getAvatar());
         pstm.executeUpdate();
            try {
                if (pstm != null) {
                    pstm.close();
                }
     }catch (SQLException sqle) {
            System.out.println("El proceso de base de datos ha fallado.");
            System.out.println("Razón: " + sqle.getMessage());
     }
}
     public void registrarPuntaje(Puntaje puntaje) throws SQLException{
          PreparedStatement pstm = cn.getConexion().prepareStatement("Insert into puntaje values ("+
                                                                     puntaje.getId()+","+
                                                                     puntaje.getJugador()+","+
                                                                     puntaje.getJugador()+");"); 
         pstm.executeUpdate();

            pstm = cn.getConexion().prepareStatement("select last_insert_id()");
            ResultSet res = pstm.executeQuery();
            res.next();
            res.close();
     }

}
        
        
