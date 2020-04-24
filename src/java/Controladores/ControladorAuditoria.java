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
    
    public String Insert(String operacion, String tabla, String usua, int idmodi, String observacion ){
        
        Tools tl = new Tools ();
        String fecha = tl.formatoFechaHora();
        ControladorUsuarios controladorU = new ControladorUsuarios();
        int idusua = controladorU.idUsuario(usua);
        if(idmodi == 3001){
            idmodi = idusua;
        }
        //int idmodi = ;
        /*if ("".equals(request.getParameter("id"))){
        
        }*/
        ModeloAuditoria modelo = new ModeloAuditoria(
                0,
                operacion,
                tabla,
                fecha,
                idusua,
                idmodi,
                observacion
        );
        try{

            con = conexion.abrirConexion();
            try{

                SQL = con.prepareStatement("INSERT INTO `auditoria`("
                        + "`operacion`,"
                        + "`tabla`,"
                        + "`fecha`,"
                        + "`id_usuario`,"
                        + "`registro_modificado`,"
                        + "`observacion`) "
                        + "VALUES (?,?,?,?,?,?);");
                SQL.setString(1, modelo.getOperacion());
                SQL.setString(2, modelo.getTabla());
                SQL.setString(3, modelo.getFecha());
                SQL.setInt(4, modelo.getId_usuario());
                SQL.setInt(5, modelo.getRegistro_modificado());
                SQL.setString(6, modelo.getObservacion());
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
