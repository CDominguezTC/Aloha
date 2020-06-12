/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloPermisos;
import Modelo.ModeloRol;
import Modelo.ModeloUsuario;
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
    
    /**
     * Permite listar en un select los usuarios activos
     *
     * @author Julian A Aristizabal
     * @param request
     * @param response
     * @return String
     * @version: 21/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String out = null;
        StringBuilder outsb = new StringBuilder();
        try {

            ControladorRol controladorRol = new ControladorRol();
            LinkedList<ModeloRol> listRol;
            listRol = controladorRol.Read("S");
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<option value=\"\" disabled selected>Seleccione</option>";
            outsb.append(out);
            for (ModeloRol modeloRol : listRol) {

                //out += "<option value=\"" + modeloUsua.getId() + "\"> " + modeloUsua.getNombre() + "</option>";
                outsb.append("<option value=\"").append(modeloRol.getId()).append("\"> ").append(modeloRol.getNombre()).append("</option>");

            }
            //out += "</select>";

        } catch (Exception e) {

            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return outsb.toString();
    }
    
    /**
     * Permite listar en un select los permisos asignados al usuario consultado
     *
     * @author Julian A Aristizabal
     * @param request
     * @param response
     * @param rol
     * @return String
     * @version: 21/05/2020
     */
    public String permisosAsignados(HttpServletRequest request, HttpServletResponse response, String rol) throws ServletException, IOException {

        String out = null;
        StringBuilder outsb = new StringBuilder();
        try {

            LinkedList<ModeloPermisos> listmoPer;
            listmoPer = readPermisosAsig(rol);
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            //out += "<option value=\"\" disabled selected>Seleccione</option>";
            outsb.append(out);
            for (ModeloPermisos modeloPer : listmoPer) {

                //out += "<option value=\"" + modeloPer.getId() + "\"> " + modeloPer.getNombre() + "</option>";
                outsb.append("<option value=\"").append(modeloPer.getId()).append("\"> ").append(modeloPer.getNombre()).append("</option>");

            }
            //out += "</select>";

        } catch (Exception e) {

            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return outsb.toString();
    }
    
    /**
     * Permite listar en un select los permisos no asignados al usuario consultado
     *
     * @author Julian A Aristizabal
     * @param request
     * @param response
     * @param usr
     * @return String
     * @version: 21/05/2020
     */
    public String permisosNoAsignados(HttpServletRequest request, HttpServletResponse response, String rol) throws ServletException, IOException {

        String out = null;
        StringBuilder outsb = new StringBuilder();
        try {

            LinkedList<ModeloPermisos> listmoPer;
            listmoPer = readPermisosNoAsig(rol);
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            //out += "<option value=\"\" disabled selected>Seleccione</option>";
            outsb.append(out);
            for (ModeloPermisos modeloPer : listmoPer) {

                //out += "<option value=\"" + modeloPer.getId() + "\"> " + modeloPer.getNombre() + "</option>";
                outsb.append("<option value=\"").append(modeloPer.getId()).append("\"> ").append(modeloPer.getNombre()).append("</option>");

            }
            //out += "</select>";

        } catch (Exception e) {

            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return outsb.toString();
    }
    
    /**
     * Permite listar en un select todos los permisos almacenados en la tabla
     *
     * @author Julian A Aristizabal
     * @param request
     * @param response
     * @return String
     * @version: 21/05/2020
     */
    public String listarPermisosAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String out = null;
        StringBuilder outsb = new StringBuilder();
        try {

            LinkedList<ModeloPermisos> listmoPer;
            listmoPer = readTodosP();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            //out += "<option value=\"\" disabled selected>Seleccione</option>";
            outsb.append(out);
            for (ModeloPermisos modeloPer : listmoPer) {

                //out += "<option value=\"" + modeloPer.getId() + "\"> " + modeloPer.getNombre() + "</option>";
                outsb.append("<option value=\"").append(modeloPer.getId()).append("\"> ").append(modeloPer.getNombre()).append("</option>");

            }
            //out += "</select>";

        } catch (Exception e) {

            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return outsb.toString();
    }
    
    /**
     * Permite listar la información de la tabla permiso que estan asignados
     *
     * @author Julian A Aristizabal
     * @param usua
     * @return LinkedList
     * @version: 21/05/2020
     */
    public LinkedList<ModeloPermisos> readPermisosAsig(String rol) {

        LinkedList<ModeloPermisos> modeloPer = new LinkedList<ModeloPermisos>();
        con = conexion.abrirConexion();
        try {

            SQL = con.prepareStatement("SELECT p.id, p.nombre FROM permiso p "
                    + "INNER JOIN permiso_x_rol pr ON p.id = pr.id_permiso "
                    + "INNER JOIN rol r ON r.id = pr.id_rol "
                    + "WHERE r.id = ?");

            SQL.setString(1, rol);
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
        } catch (SQLException e) {

            System.err.println("Error buscando el dato solicitado: " + e.getSQLState());
            //JOptionPane.showMessageDialog(null, "Error buscando el dato solicitado: " + e.getSQLState());
        }
        return modeloPer;
    }
    
    /**
     * Permite listar la información de la tabla permiso que no estan asignados
     *
     * @author Julian A Aristizabal
     * @param rol
     * @return LinkedList
     * @version: 21/05/2020
     */
    public LinkedList<ModeloPermisos> readPermisosNoAsig(String rol) {

        LinkedList<ModeloPermisos> modeloPer = new LinkedList<ModeloPermisos>();
        con = conexion.abrirConexion();
        try {

            SQL = con.prepareStatement("SELECT p.id, p.nombre FROM permiso p WHERE p.id NOT IN (SELECT pr.id_permiso FROM permiso_x_rol pr INNER JOIN rol r ON r.id = pr.id_rol "
                    + "INNER JOIN permiso p ON p.id = pr.id_permiso WHERE r.id = ?)");

            SQL.setString(1, rol);
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
        } catch (SQLException e) {

            System.err.println("Error buscando el dato solicitado: " + e.getSQLState());
            //JOptionPane.showMessageDialog(null, "Error buscando el dato solicitado: " + e.getSQLState());
        }
        return modeloPer;
    }
    
    /**
     * Permite listar toda la información de la tabla permiso
     *
     * @author Julian A Aristizabal
     * @return LinkedList
     * @version: 21/05/2020
     */
    public LinkedList<ModeloPermisos> readTodosP() {

        LinkedList<ModeloPermisos> modeloPer = new LinkedList<ModeloPermisos>();
        con = conexion.abrirConexion();
        try {

            SQL = con.prepareStatement("SELECT id, nombre FROM permiso ORDER BY id");

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
        } catch (SQLException e) {

            System.err.println("Error buscando el dato solicitado: " + e.getSQLState());
            //JOptionPane.showMessageDialog(null, "Error buscando el dato solicitado: " + e.getSQLState());
        }
        return modeloPer;
    }
    
    /**
     * Permite insertar los permisos configurados en la tabla permiso_x_usuario
     *
     * @author Julian A Aristizabal
     * @param items[]
     * @param usr
     * @return String
     * @version: 21/05/2020
     */
    public String insertarPermisos(String[] items, String usr) {

        resultado = "false";

        con = conexion.abrirConexion();
        try {

            int can = items.length;
            int ins = 0;
            //System.err.println("can: "+can);
            if (eliminoPermisos(usr)) {

                for (int i = 0; i < can; i++) {

                    //if(!"Seleccione".equals(items[i])){
                    //System.out.println(items[i]);
                    int idp = idPermiso(items[i].replaceAll("\\s", ""));

                    SQL = con.prepareStatement("INSERT INTO permiso_x_rol (id_permiso, id_rol) VALUES (?,?)");
                    SQL.setInt(1, idp);
                    SQL.setInt(2, Integer.parseInt(usr));

                    if (SQL.executeUpdate() > 0) {

                        ins++;
                    }

                    //}                    
                }
            } else {
                return "false";
            }

            if (can == ins) {
                resultado = "true";
            } else {
                resultado = "false";
            }

        } catch (SQLException e) {

            System.err.println("Error en el proceso: " + e.getMessage());
            //resultado = "-2";
        }

        return resultado;
    }
    
    /**
     * Permite borrar los permisos en permiso_x_usuario del usuario seleccionado
     *
     * @author Julian A Aristizabal
     * @param user
     * @return boolean
     * @version: 21/05/2020
     */
    private boolean eliminoPermisos(String user) {

        con = conexion.abrirConexion();
        try {

            SQL = con.prepareStatement("DELETE FROM permiso_x_rol WHERE id_rol = ?");
            SQL.setString(1, user);
            if (SQL.executeUpdate() > 0) {

                return true;
            }
        } catch (SQLException e) {

            System.err.println("Error en el proceso: " + e.getMessage());
            //resultado = "-2";
        } finally {
            try {
                SQL.close();
                con.close();
            } catch (Exception e) {
            }
        }

        return false;
    }
    
    /**
     * Permite seleccionar el id del permiso a insertar
     *
     * @author Julian A Aristizabal
     * @param permiso
     * @return int
     * @version: 21/05/2020
     */
    private int idPermiso(String permiso) {

        con = conexion.abrirConexion();
        int resul = 0;
        try {
            SQL = con.prepareStatement("SELECT id FROM permiso WHERE nombre = ?");
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
