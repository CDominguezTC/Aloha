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
            out += "<option value=\"\" disabled selected>Seleccione</option>";
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
            out += "<option value=\"\" disabled selected>Seleccione</option>";
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
            out += "<option value=\"\" disabled selected>Seleccione</option>";
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
                    + "WHERE us.id = ?");
            
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
                    + "INNER JOIN permisos p ON p.id = pu.id_permiso WHERE us.id = ?)");
            
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
                        
            SQL = con.prepareStatement("SELECT id, nombre from permisos ORDER BY id");
            
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

            SQL = con.prepareStatement("INSERT INTO `usuarios`("
                    + "`nombre`,"
                    + "`login`,"
                    + "`password`)"
                    + "VALUE (?,?,?);");
            /*SQL.setString(1, modelo.getNombre());
            SQL.setString(2, modelo.getLogin());
            String pw = tl.encriptar(modelo.getPassword());*/
            SQL.setString(3, usr);
            if (SQL.executeUpdate() > 0){

                resultado = "true";
                SQL.close();
                con.close();
            }
        } catch (SQLException e){
        
            System.err.println("Error en el proceso: " + e.getMessage());
            //resultado = "-2";

        }
        int can = items.length;
        //System.err.println("can: "+can);
        for (int i = 0; i < can; i++) {
            //System.out.println(items[i]);
        }
        
        return resultado;
    }
}
