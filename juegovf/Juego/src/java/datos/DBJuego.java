/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;


import java.sql.*;
import logica.*;

public class DBJuego {
    DBConexion cn;

    public DBJuego() {
        cn = new DBConexion();
    }
     public Juego[] getJuegos() throws SQLException{
         int registros = 0,i=0;

        try{
            PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT count(1) as cont" +
                                                                            " FROM juego ");
            
            ResultSet res = pstm.executeQuery();

            res.next();
            registros = res.getInt("cont");
            res.close();	
            
            
        }catch(SQLException e){
            System.out.println(e);
        }
        Juego[] juego = new Juego[registros];
         PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT *"+
                                                                     "FROM juego" );

        ResultSet res = pstm.executeQuery();

        while(res.next()){
            juego[i]=new Juego();
            juego[i].setId((int)res.getInt("id"));
            juego[i].setNombre(res.getString("nombre"));
            juego[i].setPegi(res.getInt("pegi"));
            juego[i].setPortada(res.getString("portada"));
            juego[i].setDescripcion(res.getString("descripcion"));
            juego[i].setNum_jugadores(res.getInt("num_jugadores"));
            i++;
        }
        return juego;
     }
     public void añadirJuego(Juego juego) throws SQLException{
        
         PreparedStatement pstm = cn.getConexion().prepareStatement("INSERT INTO juego (nombre,pegi,descripcion, portada)"+
                                                                      "VALUES (?,?,?,?); commit;"); 
         pstm.setString(1, juego.getNombre());
         pstm.setInt(2, juego.getPegi());
         pstm.setString(3, juego.getDescripcion());
         pstm.setString(4, juego.getPortada());
try {
         pstm.executeUpdate();
            
                if (pstm != null) {
                    pstm.close();
                }
     }catch (SQLException sqle) {
            System.out.println("El proceso de base de datos ha fallado.");
            System.out.println("Razón: " + sqle.getMessage());
     }
     }
     public String getMensaje(){
        return cn.getMensaje();
    }
}
