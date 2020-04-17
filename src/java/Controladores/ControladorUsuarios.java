/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloUsuarios;
import Tools.Tools;
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
public class ControladorUsuarios {
    
    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    
    public LinkedList<ModeloUsuarios> Read(){
            
        LinkedList<ModeloUsuarios> modeloUsr = new LinkedList<ModeloUsuarios>();
        con = conexion.abrirConexion();
        try {
                        
            SQL = con.prepareStatement("SELECT id, nombre, login, password FROM usuarios");
            /*SQL = con.prepareStatement("SELECT "
                    + "`id`, "
                    + "`nombre`, "
                    + "`login`, "
                    + "`password`, "
                    + "FROM `usuarios`;");*/
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
            
                ModeloUsuarios modeloUs = new ModeloUsuarios();
                modeloUs.setId(res.getInt("id"));
                modeloUs.setNombre(res.getString("nombre"));
                modeloUs.setLogin(res.getString("login"));
                modeloUs.setPassword(res.getString("password"));                
                modeloUsr.add(modeloUs);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e){
            
            System.err.println("Error buscando el dato solicitado: " + e.getSQLState());
            //JOptionPane.showMessageDialog(null, "Error buscando el dato solicitado: " + e.getSQLState());
        }
        return modeloUsr;
    }
    
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        String out = null;
        try {
        
            LinkedList<ModeloUsuarios> listmoUsr;            
            listmoUsr = Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Nombre</th>";
            out += "<th>Login</th>";
            out += "<th>Password</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloUsuarios modeloUsua : listmoUsr){
            
                out += "<tr>";
                out += "<td>" + modeloUsua.getNombre() + "</td>";
                out += "<td>" + modeloUsua.getLogin()+ "</td>";
                out += "<td>" + modeloUsua.getPassword()+ "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modeloUsua.getId() + "\"";                
                out += "data-nombre=\"" + modeloUsua.getNombre() + "\"";
                out += "data-login=\"" + modeloUsua.getLogin()+ "\"";
                out += "data-password=\"" + modeloUsua.getPassword() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloUsua.getId() + "\"";
                out += "data-nombre=\"" + modeloUsua.getNombre() + "\"";
                out += "data-login=\"" + modeloUsua.getLogin()+ "\"";
                out += "data-password=\"" + modeloUsua.getPassword() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";

        } catch (Exception e){
        
            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return out;
    }
    
    ModeloUsuarios getModelo(int Id) {
    
        ModeloUsuarios modelo = new ModeloUsuarios();
        con = conexion.abrirConexion();
        try{
        
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`nombre`,"
                    + "`login`,"
                    + "`password` "
                    + "FROM `usuarios`"
                    + "WHERE id = ?;");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()){
            
                modelo.setId(res.getInt("id"));
                modelo.setNombre(res.getString("nombre"));
                modelo.setLogin(res.getString("login"));
                modelo.setPassword(res.getString("password"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e){
        
            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }
        return modelo;
    }
    
    public String Insert(HttpServletRequest request){
        
        Tools tl = new Tools ();
        if ("".equals(request.getParameter("id"))){
             
            ModeloUsuarios modelo = new ModeloUsuarios(
                    0,
                    request.getParameter("nombre"),
                    request.getParameter("login"),
                    request.getParameter("password")
            );
            try{

                con = conexion.abrirConexion();
                try{

                    SQL = con.prepareStatement("INSERT INTO `usuarios`("
                            + "`nombre`,"
                            + "`login`,"
                            + "`password`)"
                            + "VALUE (?,?,?);");
                    SQL.setString(1, modelo.getNombre());
                    SQL.setString(2, modelo.getLogin());
                    String pw = tl.encriptar(modelo.getPassword());
                    SQL.setString(3, pw);
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
        } else{

            ModeloUsuarios modelo = new ModeloUsuarios(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("nombre"),
                    request.getParameter("login"),
                    request.getParameter("password")
            );
            try{

                con = conexion.abrirConexion();
                try{

                    SQL = con.prepareStatement("UPDATE `usuarios` SET "
                            + "`nombre` = ?, "
                            + "`login` = ?, "
                            + "`password` = ? "
                            + "WHERE `id` = ?;");
                    SQL.setString(1, modelo.getNombre());
                    SQL.setString(2, modelo.getLogin());
                    String pw = tl.encriptar(modelo.getPassword());
                    SQL.setString(3, pw);
                    SQL.setInt(4, modelo.getId());
                    if (SQL.executeUpdate() > 0){

                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } catch (SQLException e){

                    System.err.println("Error en el proceso: " + e.getMessage());
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e){

                System.err.println("Error en el proceso: " + e.getMessage());
                resultado = "-3";
            }
        }
        
        return resultado;
    }
    
    public String Delete(HttpServletRequest request){
    
        if (!"".equals(request.getParameter("id"))){
        
            //String idtmp = request.getParameter("id");
            ModeloUsuarios modelo = new ModeloUsuarios();
            modelo.setId(Integer.parseInt(request.getParameter("id")));

            try{
            
                con = conexion.abrirConexion();
                try{
                
                    SQL = con.prepareStatement("DELETE FROM `usuarios` "
                            + "WHERE `id` = ?;");
                    SQL.setInt(1, modelo.getId());
                    if (SQL.executeUpdate() > 0){
                    
                        resultado = "2";
                    }
                } catch (SQLException e){
                
                    System.err.println("Error en el proceso: " + e.getMessage());
                    resultado = "-2";
                }
                SQL.close();
                con.close();
            } catch (SQLException e){
            
                System.err.println("Error en el proceso: " + e.getMessage());
                resultado = "-3";
            }
        }
        return resultado;
    }
    
    public String validoLogin(String log){
        
        String resp = "false";
        ResultSet rs = null;      
        con = conexion.abrirConexion();

        try {
            String consulta = "SELECT id FROM usuarios WHERE login = ?";            
            SQL = con.prepareStatement(consulta);
            
            SQL.setString(1, log);            
            
            rs = SQL.executeQuery();
             
            if(rs.absolute(1)){
                resp = "true";
                return resp;
            }
            
            rs.close();
            SQL.close();
            con.close();
            
        } catch (Exception e) {
            
            System.err.println("Controladores.ControladorInicioSesion.autenticacion(): " + e.getMessage());
        }
        
        return resp;
    }
    
    public String validoPassword(String id, String pw){
        
        String resp = "false";
        ResultSet rs = null;      
        con = conexion.abrirConexion();
        Tools tl = new Tools();
        
        try {
            String consulta = "SELECT password FROM usuarios WHERE id = ?";            
            SQL = con.prepareStatement(consulta);
            
            String clave = tl.encriptar(pw);
            SQL.setString(1, id);            
            
            rs = SQL.executeQuery();
             
            if(rs.absolute(1)){
                if(clave.equals(rs.getString("password"))){
                    resp = "true";
                    return resp;
                }
                
            }
            
            rs.close();
            SQL.close();
            con.close();
            
        } catch (Exception e) {
            
            System.err.println("Controladores.ControladorInicioSesion.autenticacion(): " + e.getMessage());
        }
        
        return resp;
    }
    
    public String actualizoPassword(String id, String pw){
        
        String resp = "false";
        ResultSet rs = null;      
        con = conexion.abrirConexion();
        Tools tl = new Tools();
        
        try {
            String consulta = "UPDATE usuarios SET password = ? WHERE id = ?";            
            SQL = con.prepareStatement(consulta);
            
            String clave = tl.encriptar(pw);
            SQL.setString(1, clave); 
            SQL.setString(2, id);            
            
            if (SQL.executeUpdate() > 0){
                resp = "true";
                return resp;
            }
            
            rs.close();
            SQL.close();
            con.close();
            
        } catch (Exception e) {
            
            System.err.println("Controladores.ControladorInicioSesion.autenticacion(): " + e.getMessage());
        }
        
        return resp;
    }
}
