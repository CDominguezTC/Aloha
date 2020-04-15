/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Tools.Tools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Julian A Aristizabal
 */
public class ControladorInicioSesion {
        
    
    public String autenticacion (HttpServletRequest request){
        
        String resultado = "";
        PreparedStatement SQL = null;
        ResultSet rs = null;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();        
        con = conexionBdMysql.abrirConexion();
        
        Tools tl = new Tools();
        
        String usuario = request.getParameter("user");
        String pw = request.getParameter("pass");
        
        try {
            String consulta = "SELECT * FROM usuarios WHERE login = ? AND password = ?";            
            SQL = con.prepareStatement(consulta);
            
            SQL.setString(1, usuario);            
            String clave = tl.encriptar(pw);
            //System.out.println(clave);
            SQL.setString(2, clave);
            
            rs = SQL.executeQuery();
            
            //if(rs.next()){                
            if(rs.absolute(1)){
                resultado = "true";
            }else{
                resultado = "-2";
            }
            
            rs.close();
            SQL.close();
            con.close();
            
        } catch (Exception e) {
            
            System.err.println("Controladores.ControladorInicioSesion.autenticacion(): " + e.getMessage());
        }
        
        //return false;
        return resultado;
    }
    

    
}
