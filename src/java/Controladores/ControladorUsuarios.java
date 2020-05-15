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

/**
 * Esta clase permite controlar los eventos de los Usuarios contrine Insert -
 * Update, Delete, Read
 *
 * @author Julian A Aristizabal
 * @version: 07/05/2020
 */
public class ControladorUsuarios {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    /**
     * Permite listar la información de la tabla de Usuarios Metodo Private
     *
     * @author Julian A Aristizabal
     * @return LinkedList
     * @version: 07/05/2020
     */
    public LinkedList<ModeloUsuario> Read() {

        LinkedList<ModeloUsuario> modeloUsr = new LinkedList<ModeloUsuario>();
        con = conexion.abrirConexion();
        try {

            SQL = con.prepareStatement("SELECT id, nombre, login, password, estado FROM usuario ORDER BY nombre");
            /*
             * SQL = con.prepareStatement("SELECT "
             * + "`id`, "
             * + "`nombre`, "
             * + "`login`, "
             * + "`password`, "
             * + "FROM `usuarios`;");
             */
            ResultSet res = SQL.executeQuery();
            while (res.next()) {

                ModeloUsuario modeloUs = new ModeloUsuario();
                modeloUs.setId(res.getInt("id"));
                modeloUs.setNombre(res.getString("nombre"));
                modeloUs.setLogin(res.getString("login"));
                modeloUs.setPassword(res.getString("password"));
                modeloUs.setPassword(res.getString("estado"));
                modeloUsr.add(modeloUs);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {

            System.err.println("Error buscando el dato solicitado: " + e.getSQLState());
            //JOptionPane.showMessageDialog(null, "Error buscando el dato solicitado: " + e.getSQLState());
        }
        return modeloUsr;
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
        try {

            LinkedList<ModeloUsuario> listmoUsr;
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

        } catch (Exception e) {

            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return out;
    }

    /**
     * Permite listar la información de la tabla de Usuarios identificadno el ID
     *
     * @author Julian A Aristizabal
     * @param Id
     * @return ModeloUsuarios
     * @version: 07/05/2020
     */
    public ModeloUsuario getModelos(int Id) {

        ModeloUsuario modelo = new ModeloUsuario();
        con = conexion.abrirConexion();
        try {

            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`nombre`,"
                    + "`login`,"
                    + "`password` "
                    + "FROM `usuario`"
                    + "WHERE id = ?;");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {

                modelo.setId(res.getInt("id"));
                modelo.setNombre(res.getString("nombre"));
                modelo.setLogin(res.getString("login"));
                modelo.setPassword(res.getString("password"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {

            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }
        return modelo;
    }

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd Usuarios
     *
     * @author Julian A Aristizabal
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Insert(HttpServletRequest request) {

        Tools tl = new Tools();
        if ("".equals(request.getParameter("id"))) {

            ModeloUsuario modelo = new ModeloUsuario(
                    0,
                    request.getParameter("nombre"),
                    request.getParameter("login"),
                    request.getParameter("password"),
                    request.getParameter("estado")
            );
            try {

                con = conexion.abrirConexion();
                try {

                    SQL = con.prepareStatement("INSERT INTO `usuarios`("
                            + "`nombre`,"
                            + "`login`,"
                            + "`password`)"
                            + "VALUE (?,?,?);", SQL.RETURN_GENERATED_KEYS);
                    SQL.setString(1, modelo.getNombre());
                    SQL.setString(2, modelo.getLogin());
                    String pw = tl.encriptar(modelo.getPassword());
                    SQL.setString(3, pw);
                    if (SQL.executeUpdate() > 0) {
                        ControladorAuditoria auditoria = new ControladorAuditoria();
                        try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int i = (int) generatedKeys.getLong(1);
                                auditoria.Insert("insertar", "usuarios", request.getParameter("nombreU"), i, "Se inserto el registro.");
                            }
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Error en el proceso: " + e.getMessage());
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error en el proceso: " + e.getMessage());
                resultado = "-3";
            }
        } else {

            ModeloUsuario modelo = new ModeloUsuario(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("nombre"),
                    request.getParameter("login"),
                    request.getParameter("password"),
                    request.getParameter("estado")
            );
            try {

                con = conexion.abrirConexion();
                try {

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
                    if (SQL.executeUpdate() > 0) {

                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } catch (SQLException e) {

                    System.err.println("Error en el proceso: " + e.getMessage());
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {

                System.err.println("Error en el proceso: " + e.getMessage());
                resultado = "-3";
            }
        }

        return resultado;
    }

    /**
     * Permite la eliminar un dato en la tabla de Usuarios
     *
     * @author Julian A Aristizabal
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Delete(HttpServletRequest request) {

        if (!"".equals(request.getParameter("id"))) {

            //String idtmp = request.getParameter("id");
            ModeloUsuario modelo = new ModeloUsuario();
            modelo.setId(Integer.parseInt(request.getParameter("id")));

            try {

                con = conexion.abrirConexion();
                try {

                    SQL = con.prepareStatement("DELETE FROM `usuarios` "
                            + "WHERE `id` = ?;");
                    SQL.setInt(1, modelo.getId());
                    if (SQL.executeUpdate() > 0) {

                        resultado = "2";
                    }
                } catch (SQLException e) {

                    System.err.println("Error en el proceso: " + e.getMessage());
                    resultado = "-2";
                }
                SQL.close();
                con.close();
            } catch (SQLException e) {

                System.err.println("Error en el proceso: " + e.getMessage());
                resultado = "-3";
            }
        }
        return resultado;
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
            String consulta = "SELECT id FROM usuarios WHERE login = ?";
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
            String consulta = "SELECT password FROM usuarios WHERE id = ?";
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
            String consulta = "UPDATE usuarios SET password = ? WHERE id = ?";
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
            String consulta = "UPDATE usuarios SET password = ? WHERE login = ?";
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
