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
import javax.servlet.http.HttpSession;
/**
 *
 * @author Julian A Aristizabal
 */
public class ControladorLog_error {
    
    String resultado = "";
    Connection con;
    String user = "";
    ControladorInicioSesion controladorInicio;
    //PreparedStatement SQL = null;
    
    /**
     * Inserta los datos en la base de datos de la tabla: log_error
     *
     * @author: Julian A Aristizabal
     * @param user, error
     * @return boolean
     * @version: 6/AGO/2020
     */
    public boolean insertarError (String usera, String error) throws SQLException{
        user = usera;
        Pool metodospool = new Pool();
        Tools tl = new Tools();
        try {
            PreparedStatement pst;
            con = metodospool.dataSource.getConnection();
            pst = con.prepareStatement("INSERT INTO log_error("
                        + "id_usuario, "
                        + "fecha, "
                        + "error) "
                        + "VALUES (?,?,?)");
                        
            pst.setInt(1, idUsuario(usera));
            pst.setString(2, tl.formatoFechaHora());
            pst.setString(3, error);
            if (pst.executeUpdate() > 0) {
                pst.close();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error insertarError en ControladorLog_error: " + e.getMessage());
            insertarError(user, error);
            
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
     * Llena un listado de la tabla log_error
     *
     * @author: Julian A Aristizabal
     * @param vacio
     * @return LinkedList<ModeloLog_error>
     * @version: 6/AGO/2020
     */
    public LinkedList<ModeloLog_error> readReg(String fini, String ffin) throws SQLException {
        
        
        if("".equals(user)){
            user = controladorInicio.user_act;
        }
        
        LinkedList<ModeloLog_error> ListaModeloLog = new LinkedList<ModeloLog_error>();
        ModeloUsuario modU = new ModeloUsuario();
        ControladorUsuario controladorU = new ControladorUsuario();
        
        Pool metodospool = new Pool();

        try {
            PreparedStatement pst;
            con = metodospool.dataSource.getConnection();
            
            if (fini == null || ffin == null || "".equals(fini) || "".equals(ffin)){
                pst = con.prepareStatement("SELECT id, "
                    + "id_usuario, "
                    + "fecha, "
                    + "error "
                    + "FROM log_error");
            }else{
                pst = con.prepareStatement("SELECT id, "
                    + "id_usuario, "
                    + "fecha, "
                    + "error "
                    + "FROM log_error WHERE fecha BETWEEN '" + fini + " 00:00:00' AND '" + ffin + " 23:59:59'");
            }
                                   
            ResultSet res = pst.executeQuery();
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
            pst.close();
        } catch (SQLException e) {
            System.out.println("Error Controladores.ControladorLog_error.readReg(): " + e.getMessage());
            insertarError(user, "Controladores.ControladorLog_error.readReg(): " + e.getMessage());
            
        }finally{
            try {
                if(con != null){
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error en la consulta SQL GetModelo en Controladorempresa: " + e.getMessage());
                insertarError(user, "Controladores.ControladorLog_error.readReg(): " + e.getMessage());
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
    public String Read(String fini, String ffin) throws Exception{
        
        String resgson = "";
        if("".equals(user)){
            user = controladorInicio.user_act;
        }
        
        try {
            LinkedList<ModeloLog_error> listmodelo;
            listmodelo = readReg(fini, ffin);
            
            Gson gson = new GsonBuilder().serializeNulls().create();
            resgson = gson.toJson(listmodelo);
        } catch (Exception e) {
            
            insertarError(user, "Controladores.ControladorLog_error.StringRead(): " + e.getMessage());
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
        if("".equals(user)){
            user = controladorInicio.user_act;
        }
        //Integer.parseInt(user);
        try {
            PreparedStatement pst;
            con = metodospool.dataSource.getConnection();
            pst = con.prepareStatement("SELECT id FROM usuario WHERE login = ?");
            
            pst.setString(1, nuser);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println("Error idUsuario en ControladorLog_error: " + e.getMessage());       
            insertarError(user, "Controladores.ControladorLog_error.idUsuario(): " + e.getMessage());
            
        }finally{
            try {
                if(con != null){
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error cerrando conexion: " + e.getMessage());
                //JOptionPane.showMessageDialog(null, "Error en la funcion. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }        
        return id;        
    }
    

}
