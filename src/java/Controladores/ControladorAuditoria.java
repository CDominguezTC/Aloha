/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloAuditoria;
import Tools.Tools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Julian A Aristizabal
 */
public class ControladorAuditoria {
    
    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    
    public String Insert(HttpServletRequest request){
        
        Tools tl = new Tools ();
        
        if ("".equals(request.getParameter("id"))){
        
        }
        ModeloAuditoria modelo = new ModeloAuditoria(
                0,
                request.getParameter("operacion"),
                request.getParameter("tabla"),
                request.getParameter("fecha"),
                Integer.parseInt(request.getParameter("idUsr")),
                Integer.parseInt(request.getParameter("regmodi")),
                request.getParameter("observacion")
        );
        try{

            con = conexion.abrirConexion();
            try{

                SQL = con.prepareStatement("INSERT INTO `auditoria`("
                        + "`operacion`,"
                        + "`tabla`,"
                        + "`fecha`,"
                        + "`id_usuario`,"
                        + "`id_modificado`)"
                        + "VALUES (?,?,?,?,?);");
                SQL.setString(1, modelo.getOperacion());
                SQL.setString(2, modelo.getTabla());
                //String pw = tl.encriptar(modelo.getPassword());
                //SQL.setString(3, pw);
                if (SQL.executeUpdate() > 0){

                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e)
            {
                System.err.println("Error en el proceso: " + e.getMessage());
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e)
        {
            System.err.println("Error en el proceso: " + e.getMessage());
            resultado = "-3";
        }
        
        
        return resultado;
    }
    
    private String validarCampos(HttpServletRequest request){
        
        return "";
    }
}
