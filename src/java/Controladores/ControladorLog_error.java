/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.Pool;
import Modelo.ModeloLog_error;
import Modelo.ModeloUsuario;
import Tools.Tools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 *
 * @author Julian A Aristizabal
 */
public class ControladorLog_error {
    
    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    
    /**
     * Inserta los datos en la base de datos de la tabla: log_error
     *
     * @author: Julian A Aristizabal
     * @param user, error
     * @return boolean
     * @version: 6/AGO/2020
     */
    public boolean insertarError (String user, String error) throws SQLException{
        
        Pool metodospool = new Pool();
        Tools tl = new Tools();
        try {
            con = metodospool.dataSource.getConnection();
            SQL = con.prepareStatement("INSERT INTO log_error("
                        + "id_usuario, "
                        + "fecha, "
                        + "error) "
                        + "VALUE (?,?,?)");
                        
            SQL.setInt(1, idUsuario(user));
            SQL.setString(2, tl.formatoFechaHora());
            SQL.setString(3, error);
            if (SQL.executeUpdate() > 0) {
                SQL.close();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error insertarError en ControladorLog_error: " + e.getMessage());
            
        }finally{
            try {
                if(con != null){
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error cerrando conexion en ControladorLog_error: " + e.getMessage());
                
            }
        }
                
        return false;
    }
    
    /**
     * Llena un Listado de la tabla log_error
     *
     * @author: Julian A Aristizabal
     * @param vacio
     * @return LinkedList<ModeloLog_error>
     * @version: 6/AGO/2020
     */
    public LinkedList<ModeloLog_error> readReg() throws SQLException {
        
        LinkedList<ModeloLog_error> ListaModeloLog = new LinkedList<ModeloLog_error>();
        ModeloUsuario modU = new ModeloUsuario();
        ControladorUsuario controladorU = new ControladorUsuario();
        
        Pool metodospool = new Pool();

        try {
            con = metodospool.dataSource.getConnection();
            SQL = con.prepareStatement("SELECT id, "
                    + "id_usuario, "
                    + "fecha, "
                    + "error "
                    + "FROM log_error");
            
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloLog_error modeloLogError = new ModeloLog_error();
                modeloLogError.setId(res.getInt("id"));
                modU = controladorU.getModelo(res.getInt("id_usuario"));
                modeloLogError.setUsuario(modU);
                modeloLogError.setFecha(res.getString("fecha"));
                modeloLogError.setError(res.getString("error"));
                ListaModeloLog.add(modeloLogError);
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorempresa: " + e.getMessage());
        }finally{
            try {
                if(con != null){
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error en la consulta SQL GetModelo en Controladorempresa: " + e.getMessage());
                //JOptionPane.showMessageDialog(null, "Error en la funcion. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return ListaModeloLog;
    }
    
    /**
     * Permite listar la información de la tabla de log_error
     *
     * @author: Julian A Aristizabal
     * @return String
     * @version: 6/AGO/2020
     */
    public String Read() throws Exception{
        
        String resgson = "";
        
        try {
            LinkedList<ModeloLog_error> listmodelo;
            listmodelo = readReg();
            
            Gson gson = new GsonBuilder().serializeNulls().create();
            resgson = gson.toJson(listmodelo);
        } catch (Exception e) {
        }
        return resgson;
    }
    
    /**
     * Retorna el id del usuario al que le resulto el error.
     *
     * @author: Julian A Aristizabal
     * @param nuser
     * @return id
     * @version: 6/AGO/2020
     */
    private int idUsuario (String nuser) throws SQLException{
        
        int id = 0;
        Pool metodospool = new Pool();
        
        try {
            con = metodospool.dataSource.getConnection();
            SQL = con.prepareStatement("SELECT id FROM usuario WHERE login = ?");
            
            SQL.setString(1, nuser);
            
            ResultSet rs = SQL.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
                    
        } catch (SQLException e) {
            System.out.println("Error idUsuario en ControladorLog_error: " + e.getMessage());
        }
        
        return id;
        
    }
}
