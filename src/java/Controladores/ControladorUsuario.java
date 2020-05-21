/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloUsuario;
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
import javax.servlet.http.HttpSession;

/**
 * Esta clase permite controlar los eventos de los Usuarios contrine Insert -
 * Update, Delete, Read
 *
 * @author Julian A Aristizabal
 * @version: 07/05/2020
 */
public class ControladorUsuario {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user = "";

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd Usuarios
     *
     * @author Julian A Aristizabal
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        ModeloUsuario modeloUsuario = new ModeloUsuario();
        modeloUsuario.setNombre(request.getParameter("nombre"));
        modeloUsuario.setLogin(request.getParameter("login"));
        modeloUsuario.setPassword(request.getParameter("password"));
        modeloUsuario.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloUsuario);
        } else {
            modeloUsuario.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloUsuario);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: usuario
     *
     * @author: Julian A Aristizabal
     * @param Modelo
     * @return String
     * @version: 15/5/2020
     */
    public String Insert(ModeloUsuario modeloUsuario) throws SQLException {
        Tools tl = new Tools();
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO usuario("
                        + "nombre, "
                        + "login, "
                        + "password)"
                        + " VALUES (?,?,?)", SQL.RETURN_GENERATED_KEYS);                
                SQL.setString(1, modeloUsuario.getNombre());
                SQL.setString(2, modeloUsuario.getLogin());
                String pw = tl.encriptar(modeloUsuario.getPassword());
                SQL.setString(3, pw);
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                        try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int i = (int) generatedKeys.getLong(1);
                                auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.");
                            }
                        }
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladorusuario" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorusuario" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:usuario
     *
     * @author: Julian A Aristizabal
     * @param request
     * @return String
     * @version: 15/5/2020
     */
    public String Update(ModeloUsuario modeloUsuario) throws SQLException {
        Tools tl = new Tools();
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloUsuario.getEstado())) {
                    SQL = con.prepareStatement("UPDATE usuario SET "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloUsuario.getEstado());
                    SQL.setInt(2, modeloUsuario.getId());
                } else {

                    SQL = con.prepareStatement("UPDATE usuario SET "
                            + "nombre = ?, "
                            + "login = ?, "
                            + "password = ?, "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    //SQL.setInt(1, modeloUsuario.getId());
                    SQL.setString(1, modeloUsuario.getNombre());
                    SQL.setString(2, modeloUsuario.getLogin());
                    String pw = tl.encriptar(modeloUsuario.getPassword());
                    SQL.setString(3, pw);
                    //SQL.setString(4, modeloUsuario.getPassword());
                    SQL.setString(4, modeloUsuario.getEstado());
                    SQL.setInt(5, modeloUsuario.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorusuario" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorusuario" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * llena un modelo que viene con datos de un request para ser Eliminado
     *
     * @author: Julian A Aristizabal
     * @param request
     * @return String
     * @version: 15/5/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloUsuario modeloUsuario = new ModeloUsuario();
            modeloUsuario.setId(Integer.parseInt(request.getParameter("id")));
            modeloUsuario.setEstado("N");
            resultado = Update(modeloUsuario);
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla usuario dependiendo de un ID
     *
     * @author: Julian A Aristizabal
     * @param request
     * @return String
     * @version: 15/5/2020
     */
    public ModeloUsuario getModelo(Integer Id) throws SQLException {
        ModeloUsuario modeloUsuario = new ModeloUsuario();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "nombre, "
                    + "login, "
                    + "password, "
                    + "estado"
                    + " FROM usuario"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloUsuario.setId(res.getInt("id"));
                modeloUsuario.setNombre(res.getString("nombre"));
                modeloUsuario.setLogin(res.getString("login"));
                modeloUsuario.setPassword(res.getString("password"));
                modeloUsuario.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorusuario" + e);
        }
        return modeloUsuario;
    }

    /**
     * Llena un Listado de la tabla usuario
     *
     * @author: Julian A Aristizabal
     * @param estado
     * @return LinkedList<ModeloUsuario>
     * @version: 15/5/2020
     */
    public LinkedList<ModeloUsuario> Read(String estado) throws SQLException {
        LinkedList<ModeloUsuario> ListaModeloUsuario = new LinkedList<ModeloUsuario>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id, "
                    + "nombre, "
                    + "login, "
                    + "password, "
                    + "estado"
                    + " FROM usuario "
                    + "WHERE estado = ?");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloUsuario modeloUsuario = new ModeloUsuario();
                modeloUsuario.setId(res.getInt("id"));
                modeloUsuario.setNombre(res.getString("nombre"));
                modeloUsuario.setLogin(res.getString("login"));
                modeloUsuario.setPassword(res.getString("password"));
                modeloUsuario.setEstado(res.getString("estado"));
                ListaModeloUsuario.add(modeloUsuario);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorusuario" + e);
        }
        return ListaModeloUsuario;
    }
    
        /**
     * Permite listar la información de la tabla de Usuarios
     *
     * @author Julian A Aristizabal
     * @param request
     * @param response
     * @return String
     * @version: 07/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String out = null;
        String estado = "S";
        if (request.getParameter("estado") != null) {
            estado = "N";
        }
        StringBuilder outsb = new StringBuilder();
        try {

            LinkedList<ModeloUsuario> listmoUsr;
            listmoUsr = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
                        
            out += "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Nombre</th>";
            out += "<th>Login</th>";
            out += "<th>Password</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            outsb.append(out);
            for (ModeloUsuario modeloUsua : listmoUsr) {
                                
                outsb.append("<tr>");
                outsb.append("<td>").append(modeloUsua.getNombre()).append("</td>");
                outsb.append("<td>").append(modeloUsua.getLogin()).append("</td>");
                outsb.append("<td>").append(modeloUsua.getPassword()).append("</td>");
                outsb.append("<td class=\"text-center\">");
                
                // Boton Editar
                outsb.append("<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"");
                outsb.append("data-id=\"").append(modeloUsua.getId()).append("\"");
                outsb.append("data-nombre=\"").append(modeloUsua.getNombre()).append("\"");
                outsb.append("data-login=\"").append(modeloUsua.getLogin()).append("\"");
                outsb.append("data-password=\"").append(modeloUsua.getPassword()).append("\"");
                outsb.append("type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>");

                //Boton Eliminar
                outsb.append("<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"");
                outsb.append("data-id=\"").append(modeloUsua.getId()).append("\"");
                outsb.append("data-nombre=\"").append(modeloUsua.getNombre()).append("\"");
                outsb.append("data-login=\"").append(modeloUsua.getLogin()).append("\"");
                outsb.append("data-password=\"").append(modeloUsua.getPassword()).append("\"");
                outsb.append("type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>");
                outsb.append("</td>");
                outsb.append("</tr>");
            }
            /*
            for (ModeloUsuario modeloUsua : listmoUsr) {

                out += "<tr>";
                out += "<td>" + modeloUsua.getNombre() + "</td>";
                out += "<td>" + modeloUsua.getLogin() + "</td>";
                out += "<td>" + modeloUsua.getPassword() + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modeloUsua.getId() + "\"";
                out += "data-nombre=\"" + modeloUsua.getNombre() + "\"";
                out += "data-login=\"" + modeloUsua.getLogin() + "\"";
                out += "data-password=\"" + modeloUsua.getPassword() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloUsua.getId() + "\"";
                out += "data-nombre=\"" + modeloUsua.getNombre() + "\"";
                out += "data-login=\"" + modeloUsua.getLogin() + "\"";
                out += "data-password=\"" + modeloUsua.getPassword() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
            */            
            outsb.append("</tbody>");
            

        } catch (Exception e) {

            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return outsb.toString();
    }

    /**
     * Permite la Validacion del login usuario en el sistema
     *
     * @author Julian A Aristizabal
     * @param log
     * @return String
     * @version: 07/05/2020
     */
    public String validoLogin(String log) {

        String resp = "false";
        ResultSet rs = null;
        con = conexion.abrirConexion();

        try {
            String consulta = "SELECT id FROM usuario WHERE login = ?";
            SQL = con.prepareStatement(consulta);

            SQL.setString(1, log);

            rs = SQL.executeQuery();

            if (rs.absolute(1)) {
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

    /**
     * Permite la Validacion del Password del usuario en el sistema
     *
     * @author Julian A Aristizabal
     * @param id
     * @param pw
     * @return String
     * @version: 07/05/2020
     */
    public String validoPassword(String id, String pw) {

        String resp = "false";
        ResultSet rs = null;
        con = conexion.abrirConexion();
        Tools tl = new Tools();

        try {
            String consulta = "SELECT password FROM usuario WHERE id = ?";
            SQL = con.prepareStatement(consulta);

            String clave = tl.encriptar(pw);
            SQL.setString(1, id);

            rs = SQL.executeQuery();

            if (rs.absolute(1)) {
                if (clave.equals(rs.getString("password"))) {
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

    /**
     * Permite la actuzlizacion del Password del usuario en el sistema
     *
     * @author Julian A Aristizabal
     * @param id
     * @param pw
     * @return String
     * @version: 07/05/2020
     */
    public String actualizoPassword(String id, String pw) {

        String resp = "false";
        ResultSet rs = null;
        con = conexion.abrirConexion();
        Tools tl = new Tools();

        try {
            String consulta = "UPDATE usuario SET password = ? WHERE id = ?";
            SQL = con.prepareStatement(consulta);

            String clave = tl.encriptar(pw);
            SQL.setString(1, clave);
            SQL.setString(2, id);

            if (SQL.executeUpdate() > 0) {
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

    /**
     * Permite la actuzlizacion del Password y del login del usuario en el
     * sistema
     *
     * @author Julian A Aristizabal
     * @param log
     * @param pw
     * @return String
     * @version: 07/05/2020
     */
    public String actualizoPasswordUser(String log, String pw) {

        String resp = "false";
        ResultSet rs = null;
        con = conexion.abrirConexion();
        Tools tl = new Tools();

        try {
            String consulta = "UPDATE usuario SET password = ? WHERE login = ?";
            SQL = con.prepareStatement(consulta);

            String clave = tl.encriptar(pw);
            SQL.setString(1, clave);
            SQL.setString(2, log);

            if (SQL.executeUpdate() > 0) {
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

    /**
     * Permite obtener el Id del usuario
     *
     * @author Julian A Aristizabal
     * @param name
     * @param pw
     * @return Integer
     * @version: 07/05/2020
     */
    public int idUsuario(String name) {

        int idU = 0;
        con = conexion.abrirConexion();
        try {

            SQL = con.prepareStatement("SELECT id FROM usuario WHERE login = ?");
            SQL.setString(1, name);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {

                idU = res.getInt("id");
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {

            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return idU;
    }
}