/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloRol;
import Modelo.ModeloUsuario;
import Tools.Tools;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import com.google.gson.Gson;
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
    ControladorLog_error log = new ControladorLog_error();
    ControladorInicioSesion controladorInicio;
    ArrayList<String> registroNew = new ArrayList<String>();

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
        ModeloRol modeloRol = new ModeloRol();
        ControladorRol controladorRol = new ControladorRol();

        modeloUsuario.setNombre(request.getParameter("nombre"));
        registroNew.add(request.getParameter("nombre"));
        modeloUsuario.setLogin(request.getParameter("login"));
        registroNew.add(request.getParameter("login"));
        modeloUsuario.setPassword(request.getParameter("password"));
        registroNew.add(request.getParameter("password"));
        modeloUsuario.setEstado("S");
        modeloRol = controladorRol.getModelo(Integer.parseInt(request.getParameter("rol")));
        registroNew.add(request.getParameter("rol"));
        modeloUsuario.setRol(modeloRol);
        HttpSession session = request.getSession();
        if ("".equals(request.getParameter("id"))) {            
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloUsuario);
        } else {
            user = (String) session.getAttribute("usuario");
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
            try {
                if (!loginRepetido(modeloUsuario.getLogin())) {
                    con = conexion.abrirConexion();
                    SQL = con.prepareStatement("INSERT INTO usuario("
                            + "nombre, "
                            + "login, "
                            + "password, "
                            + "id_rol)"
                            + " VALUES (?,?,?,?)", SQL.RETURN_GENERATED_KEYS);
                    SQL.setString(1, modeloUsuario.getNombre());
                    SQL.setString(2, modeloUsuario.getLogin());
                    String pw = tl.encriptar(modeloUsuario.getPassword());
                    SQL.setString(3, pw);
                    SQL.setInt(4, modeloUsuario.getRol().getId());
                    if (SQL.executeUpdate() > 0) {
                        ControladorAuditoria auditoria = new ControladorAuditoria();
                        try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int i = (int) generatedKeys.getLong(1);
                                auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.", "", "");
                            }
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } else {
                    resultado = "-1";
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en ControladorUsuario: " + e.getMessage());
                try {
                    log.insertarError(user, "Error Controladores.ControladorUsuario.Insert(): " + e.getMessage());
                } catch (Exception ex) {
                }
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en ControladorUsuario: " + e.getMessage());
            log.insertarError(user, "Error Controladores.ControladorUsuario.Insert(): " + e.getMessage());
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
        if ("".equals(user)) {
            user = controladorInicio.user_act;
        }
        String modeloNew = "", modeloOld = "";
        //auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.");
        String operacion = "", observacion = "", dato_old = "", dato_new = "";
        int idmodificado = 0;
        try {
            con = conexion.abrirConexion();
            PreparedStatement pst = null;
            try {

                if ("N".equals(modeloUsuario.getEstado())) {
                    pst = con.prepareStatement("UPDATE usuario SET "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    pst.setString(1, modeloUsuario.getEstado());
                    pst.setInt(2, modeloUsuario.getId());

                    operacion = "eliminar";
                    idmodificado = modeloUsuario.getId();
                    observacion = "Se elimino el registro.";
                } else {

                    pst = con.prepareStatement("UPDATE usuario SET "
                            + "nombres = ?, "
                            + "logina = ?, "
                            + "password = ?, "
                            + "id_rol = ?, "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    //SQL.setInt(1, modeloUsuario.getId());
                    pst.setString(1, modeloUsuario.getNombre());
                    pst.setString(2, modeloUsuario.getLogin());
                    String pw = tl.encriptar(modeloUsuario.getPassword());
                    pst.setString(3, pw);
                    //SQL.setString(4, modeloUsuario.getPassword());
                    pst.setInt(4, modeloUsuario.getRol().getId());
                    pst.setString(5, modeloUsuario.getEstado());
                    pst.setInt(6, modeloUsuario.getId());

                    operacion = "actualizar";
                    idmodificado = modeloUsuario.getId();
                    observacion = "Se actualizo el registro.";

                    ModeloUsuario modeloUsuarioOld = new ModeloUsuario();
                    modeloUsuarioOld = getModelo(modeloUsuario.getId());

                    Gson gson = new GsonBuilder().serializeNulls().create();
                    modeloNew = gson.toJson(modeloUsuario);
                    modeloOld = gson.toJson(modeloUsuarioOld);
                                                                                                 
                    /*if (!modeloUsuarioOld.getNombre().equals(modeloUsuario.getNombre())) {
                        dato_old = "Nombre: " + modeloUsuarioOld.getNombre() + " ";
                        dato_new = "Nombre: " + modeloUsuario.getNombre() + " ";
                    }

                    if (!modeloUsuarioOld.getLogin().equals(modeloUsuario.getLogin())) {
                        dato_old += "Login: " + modeloUsuarioOld.getLogin() + " ";
                        dato_new += "Login: " + modeloUsuario.getLogin() + " ";
                    }

                    if (!modeloUsuarioOld.getRol().getId().equals(modeloUsuario.getRol().getId())) {
                        dato_old += "Rol: " + modeloUsuarioOld.getRol().getNombre();
                        dato_new += "Rol: " + modeloUsuario.getRol().getNombre();
                    }*/

                }

                if (pst.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    auditoria.reciboModelos(modeloNew, modeloOld, operacion, "usuario", user, idmodificado, observacion);
                    //auditoria.Insert(operacion, "usuario", user, idmodificado, observacion, dato_old, dato_new);
                    resultado = "1";
                    pst.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error Controladores.ControladorUsuario.Update(): " + e.getMessage());
                log.insertarError(user, "Error Controladores.ControladorUsuario.Update(): " + e.getMessage());
                resultado = "-2";
                pst.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error Controladores.ControladorUsuario.Update(): " + e.getMessage());
            log.insertarError(user, "Error Controladores.ControladorUsuario.Update(): " + e.getMessage());
            resultado = "-3";
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error Controladores.ControladorUsuario.Update(): " + e.getMessage());
                //insertarError(user, "Controladores.ControladorLog_error.readReg(): " + e.getMessage());
                //JOptionPane.showMessageDialog(null, "Error en la funcion. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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
        ModeloRol modeloRol = new ModeloRol();
        ControladorRol controladorRol = new ControladorRol();
        Tools tl = new Tools();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id, "
                    + "nombre, "
                    + "login, "
                    + "password, "
                    + "estado, "
                    + "id_rol"
                    + " FROM usuario"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloUsuario.setId(res.getInt("id"));
                modeloUsuario.setNombre(res.getString("nombre"));
                modeloUsuario.setLogin(res.getString("login"));
                String pw = "";
                try {
                    pw = tl.desencriptar(res.getString("password"));
                } catch (Exception e) {
                }                
                modeloUsuario.setPassword(pw);
                modeloUsuario.setEstado(res.getString("estado"));
                modeloRol = controladorRol.getModelo(res.getInt("id_rol"));
                modeloUsuario.setRol(modeloRol);

            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error Controladores.ControladorUsuario.getModelo(): " + e.getMessage());
            log.insertarError(user, "Error Controladores.ControladorUsuario.getModelo(): " + e.getMessage());
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
        ModeloRol modeloRol = new ModeloRol();
        ControladorRol controladorRol = new ControladorRol();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id, "
                    + "nombre, "
                    + "login, "
                    + "password, "
                    + "id_rol, "
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
                modeloRol = controladorRol.getModelo(res.getInt("id_rol"));
                modeloUsuario.setRol(modeloRol);
                modeloUsuario.setEstado(res.getString("estado"));
                ListaModeloUsuario.add(modeloUsuario);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error Controladores.ControladorUsuario.LinkedListRead(): " + e.getMessage());
            log.insertarError(user, "Error Controladores.ControladorUsuario.LinkedListRead(): " + e.getMessage());

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
    public String Read(HttpServletRequest request, HttpServletResponse response) throws SQLException {
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
            out += "<th>Rol</th>";
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
                outsb.append("<td>").append(modeloUsua.getRol().getNombreRol()).append("</td>");
                outsb.append("<td class=\"text-center\">");

                // Boton Editar
                outsb.append("<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"");
                outsb.append("data-id=\"").append(modeloUsua.getId()).append("\"");
                outsb.append("data-nombre=\"").append(modeloUsua.getNombre()).append("\"");
                outsb.append("data-login=\"").append(modeloUsua.getLogin()).append("\"");
                outsb.append("data-password=\"").append(modeloUsua.getPassword()).append("\"");
                outsb.append("data-rol=\"").append(modeloUsua.getRol().getId()).append("\"");
                outsb.append("data-nrol=\"").append(modeloUsua.getRol().getNombreRol()).append("\"");
                outsb.append("type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>");

                //Boton Eliminar
                outsb.append("<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"");
                outsb.append("data-id=\"").append(modeloUsua.getId()).append("\"");
                outsb.append("data-nombre=\"").append(modeloUsua.getNombre()).append("\"");
                outsb.append("data-login=\"").append(modeloUsua.getLogin()).append("\"");
                outsb.append("data-password=\"").append(modeloUsua.getPassword()).append("\"");
                outsb.append("data-rol=\"").append(modeloUsua.getRol().getId()).append("\"");
                outsb.append("data-nrol=\"").append(modeloUsua.getRol().getNombreRol()).append("\"");
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

        } catch (SQLException e) {

            System.err.println("Error Controladores.ControladorUsuario.StringRead(): " + e.getMessage());
            log.insertarError(user, "Error Controladores.ControladorUsuario.StringRead(): " + e.getMessage());
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
    public String validoLogin(String logi) throws SQLException {

        String resp = "false";
        ResultSet rs = null;
        con = conexion.abrirConexion();

        try {
            String consulta = "SELECT id FROM usuario WHERE login = ?";
            SQL = con.prepareStatement(consulta);

            SQL.setString(1, logi);

            rs = SQL.executeQuery();

            if (rs.absolute(1)) {
                resp = "true";
                return resp;
            }

            rs.close();
            SQL.close();
            con.close();

        } catch (SQLException e) {

            System.err.println("Error Controladores.ControladorUsuario.validoLogin(): " + e.getMessage());
            log.insertarError(user, "Error Controladores.ControladorUsuario.validoLogin(): " + e.getMessage());

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
    public String validoPassword(String id, String pw) throws SQLException {

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

        } catch (SQLException e) {

            System.err.println("Error Controladores.ControladorUsuario.validoPassword(): " + e.getMessage());
            log.insertarError(user, "Error Controladores.ControladorUsuario.validoPassword(): " + e.getMessage());
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
    public String actualizoPassword(String id, String pw) throws SQLException {

        String resp = "false";
        ResultSet rs = null;
        con = conexion.abrirConexion();
        Tools tl = new Tools();
        int idmodificado = 0;
        idmodificado = Integer.parseInt(id);
        if ("".equals(user)) {
            user = controladorInicio.user_act;
        }

        try {
            String consulta = "UPDATE usuario SET password = ? WHERE id = ?";
            SQL = con.prepareStatement(consulta);

            String clave = tl.encriptar(pw);
            SQL.setString(1, clave);
            SQL.setString(2, id);

            if (SQL.executeUpdate() > 0) {
                resp = "true";
                ControladorAuditoria auditoria = new ControladorAuditoria();

                auditoria.Insert("actualizar", "usuario", user, idmodificado, "Se cambio la contraseña.", "", "");
                return resp;
            }

            rs.close();
            SQL.close();
            con.close();

        } catch (SQLException e) {

            System.err.println("Error Controladores.ControladorUsuario.actualizoPassword(): " + e.getMessage());
            log.insertarError(user, "Error Controladores.ControladorUsuario.actualizoPassword(): " + e.getMessage());
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
    public String actualizoPasswordUser(String logi, String pw) throws SQLException {

        String resp = "false";
        ResultSet rs = null;
        int idmodificado = 0;
        con = conexion.abrirConexion();
        Tools tl = new Tools();
        if ("".equals(user)) {
            user = controladorInicio.user_act;
        }

        try {
            String consulta = "UPDATE usuario SET password = ? WHERE login = ?";
            SQL = con.prepareStatement(consulta);

            String clave = tl.encriptar(pw);
            SQL.setString(1, clave);
            SQL.setString(2, logi);

            if (SQL.executeUpdate() > 0) {
                resp = "true";
                ControladorAuditoria auditoria = new ControladorAuditoria();
                idmodificado = idUsuario(logi);
                auditoria.Insert("actualizar", "usuario", user, idmodificado, "Se cambio la contraseña.", "", "");
                return resp;
            }

            rs.close();
            SQL.close();
            con.close();

        } catch (SQLException e) {

            System.err.println("Error Controladores.ControladorUsuario.actualizoPasswordUser(): " + e.getMessage());
            log.insertarError(user, "Error Controladores.ControladorUsuario.actualizoPasswordUser(): " + e.getMessage());
        }

        return resp;
    }

    /**
     * Permite obtener el Id del usuario
     *
     * @author Julian A Aristizabal
     * @param name
     * @return Integer
     * @version: 07/05/2020
     */
    public int idUsuario(String name) throws SQLException {

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

            System.err.println("Error Controladores.ControladorUsuario.idUsuario(): " + e.getMessage());
            log.insertarError(user, "Error Controladores.ControladorUsuario.idUsuario(): " + e.getMessage());
        }

        return idU;
    }

    /**
     * Valida si el login ya existe en la bd
     *
     * @author Julian A Aristizabal
     * @param login
     * @return Integer
     * @version: 07/05/2020
     */
    public boolean loginRepetido(String login) throws SQLException {

        boolean resp = false;
        con = conexion.abrirConexion();
        try {

            SQL = con.prepareStatement("SELECT id FROM usuario WHERE login = ?");
            SQL.setString(1, login);
            ResultSet res = SQL.executeQuery();
            if (res.absolute(1)) {
                resp = true;
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {

            System.err.println("Error Controladores.ControladorUsuario.loginRepetido(): " + e.getMessage());
            log.insertarError(user, "Error Controladores.ControladorUsuario.loginRepetido(): " + e.getMessage());
        }

        return resp;
    }
}
