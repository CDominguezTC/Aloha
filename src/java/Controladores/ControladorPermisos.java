/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloPermisos;
import Modelo.ModeloUsuarios;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julian A Aristizabal
 */
public class ControladorPermisos {
    
    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
            
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        String out = null;
        try {
            
            ControladorUsuarios controladorU = new ControladorUsuarios();
            LinkedList<ModeloUsuarios> listmoUsr;            
            listmoUsr = controladorU.Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<option value=\"\" disabled selected>Seleccione</option>";
            for (ModeloUsuarios modeloUsua : listmoUsr){
            
                //out += "<tr>";
                //<option value="1">JULIAN A. ARISTIZABAL</option>
                out += "<option value=\"" + modeloUsua.getId() + "\"> "+ modeloUsua.getNombre() + "</option>";

            }
            //out += "</select>";

        } catch (Exception e){
        
            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return out;
    }
    
    public String ReadPU(HttpServletRequest request, HttpServletResponse response, String usr) throws ServletException, IOException {
                
        String out = null;
        try {
                        
            LinkedList<ModeloPermisos> listmoPer;            
            listmoPer = readPermisosAsig(usr);
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            //out += "<option value=\"\" disabled selected>Seleccione</option>";
            for (ModeloPermisos modeloPer : listmoPer){
            
                //out += "<tr>";
                //<option value="1">JULIAN A. ARISTIZABAL</option>
                out += "<option value=\"" + modeloPer.getId() + "\"> "+ modeloPer.getNombre() + "</option>";

            }
            //out += "</select>";

        } catch (Exception e){
        
            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return out;
    }
    
    public String ReadPNoU(HttpServletRequest request, HttpServletResponse response, String usr) throws ServletException, IOException {
                
        String out = null;
        try {
            
            
            LinkedList<ModeloPermisos> listmoPer;            
            listmoPer = readPermisosNoAsig(usr);
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            //out += "<option value=\"\" disabled selected>Seleccione</option>";
            for (ModeloPermisos modeloPer : listmoPer){
            
                //out += "<tr>";
                //<option value="1">JULIAN A. ARISTIZABAL</option>
                out += "<option value=\"" + modeloPer.getId() + "\"> "+ modeloPer.getNombre() + "</option>";

            }
            //out += "</select>";

        } catch (Exception e){
        
            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return out;
    }
    
    public String ReadTodosP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        String out = null;
        try {
            
            
            LinkedList<ModeloPermisos> listmoPer;            
            listmoPer = readTodosP();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            //out += "<option value=\"\" disabled selected>Seleccione</option>";
            for (ModeloPermisos modeloPer : listmoPer){
            
                //out += "<tr>";
                //<option value="1">JULIAN A. ARISTIZABAL</option>
                out += "<option value=\"" + modeloPer.getId() + "\"> "+ modeloPer.getNombre() + "</option>";

            }
            //out += "</select>";

        } catch (Exception e){
        
            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return out;
    }
    
    public LinkedList<ModeloPermisos> readPermisosAsig(String usua){
            
        LinkedList<ModeloPermisos> modeloPer = new LinkedList<ModeloPermisos>();
        con = conexion.abrirConexion();
        try {
                        
            SQL = con.prepareStatement("SELECT p.id, p.nombre FROM permisos p INNER JOIN permisosxusuarios pu ON p.id = pu.id_permiso INNER JOIN usuarios us ON us.id = pu.id_usuario "
                    + "WHERE us.id = ? ORDER BY p.nombre");
            
            SQL.setString(1, usua);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
            
                ModeloPermisos modeloPermi = new ModeloPermisos();
                modeloPermi.setId(res.getInt("id"));
                modeloPermi.setNombre(res.getString("nombre"));                               
                modeloPer.add(modeloPermi);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e){
            
            System.err.println("Error buscando el dato solicitado: " + e.getSQLState());
            //JOptionPane.showMessageDialog(null, "Error buscando el dato solicitado: " + e.getSQLState());
        }
        return modeloPer;
    }
    
    public LinkedList<ModeloPermisos> readPermisosNoAsig(String usua){
            
        LinkedList<ModeloPermisos> modeloPer = new LinkedList<ModeloPermisos>();
        con = conexion.abrirConexion();
        try {
                        
            SQL = con.prepareStatement("SELECT p.id, p.nombre FROM permisos p WHERE p.id NOT IN (SELECT pu.id_permiso FROM permisosxusuarios pu INNER JOIN usuarios us ON us.Id = pu.id_usuario "
                    + "INNER JOIN permisos p ON p.id = pu.id_permiso WHERE us.id = ?) ORDER BY p.nombre");
            
            SQL.setString(1, usua);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
            
                ModeloPermisos modeloPermi = new ModeloPermisos();
                modeloPermi.setId(res.getInt("id"));
                modeloPermi.setNombre(res.getString("nombre"));                               
                modeloPer.add(modeloPermi);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e){
            
            System.err.println("Error buscando el dato solicitado: " + e.getSQLState());
            //JOptionPane.showMessageDialog(null, "Error buscando el dato solicitado: " + e.getSQLState());
        }
        return modeloPer;
    }
    
    public LinkedList<ModeloPermisos> readTodosP(){
            
        LinkedList<ModeloPermisos> modeloPer = new LinkedList<ModeloPermisos>();
        con = conexion.abrirConexion();
        try {
                        
            SQL = con.prepareStatement("SELECT id, nombre from permisos ORDER BY nombre");
            
            //SQL.setString(1, usua);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
            
                ModeloPermisos modeloPermi = new ModeloPermisos();
                modeloPermi.setId(res.getInt("id"));
                modeloPermi.setNombre(res.getString("nombre"));                               
                modeloPer.add(modeloPermi);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e){
            
            System.err.println("Error buscando el dato solicitado: " + e.getSQLState());
            //JOptionPane.showMessageDialog(null, "Error buscando el dato solicitado: " + e.getSQLState());
        }
        return modeloPer;
    }
    
    public String insertarPermisos(String[] items, String usr){
        
        resultado = "false";
        
        con = conexion.abrirConexion();
        try{
            
            int can = items.length;
            int ins = 0;
            //System.err.println("can: "+can);
            if(eliminoPermisos(usr)){
                            
                for (int i = 0; i < can; i++) {
                    
                    //if(!"Seleccione".equals(items[i])){
                    //System.out.println(items[i]);
                    int idp = idPermiso(items[i].replaceAll("\\s",""));

                    SQL = con.prepareStatement("INSERT INTO permisosxusuarios (id_permiso, id_usuario) VALUES (?,?)");                                        
                    SQL.setInt(1, idp);
                    SQL.setInt(2, Integer.parseInt(usr));

                    if (SQL.executeUpdate() > 0){

                        ins++;
                    }

                    //}                    
                }
            }else{
                return "false";
            }
            
            if(can == ins){
                resultado = "true";
            }else{
                resultado = "false";
            }

        } catch (SQLException e){
        
            System.err.println("Error en el proceso: " + e.getMessage());
            //resultado = "-2";
        }
                
        return resultado;
    }
    
    private boolean eliminoPermisos(String user){
        
        con = conexion.abrirConexion();
        try{

            SQL = con.prepareStatement("DELETE FROM permisosxusuarios WHERE id_usuario = ?");
            SQL.setString(1, user);
            if (SQL.executeUpdate() > 0){

                return true;
            }
        } catch (SQLException e){

            System.err.println("Error en el proceso: " + e.getMessage());
            //resultado = "-2";
        }finally{
            try {
                SQL.close();
                con.close();
            } catch (Exception e) {
            }            
        }        
        
        return false;
    }
    
    private int idPermiso(String permiso){
        
        con = conexion.abrirConexion();
        int resul = 0;
        try {
            SQL = con.prepareStatement("SELECT id FROM permisos WHERE nombre = ?");
            SQL.setString(1, permiso);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                resul = res.getInt("id");
            }
        } catch (Exception e) {
        }
        
        return resul;
    }
}
