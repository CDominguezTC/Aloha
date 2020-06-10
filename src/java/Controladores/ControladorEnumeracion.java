/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Herramienta.Herramienta;
import Modelo.ModeloEnumeracion;
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
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorEnumeracion {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;
    //ControladorEnumeracion controladorEnumeracion = new ControladorEnumeracion();
    Herramienta herramienta = new Herramienta();

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * enumeracion
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 21/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloEnumeracion modeloEnumeracion = new ModeloEnumeracion();
        modeloEnumeracion.setModelo_enumeracion(getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("id_enumeracion")))));
        modeloEnumeracion.setCampo(request.getParameter("campo"));
        modeloEnumeracion.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            modeloEnumeracion.setEstado("S");
            resultado = Insert(modeloEnumeracion);
        } else {
            modeloEnumeracion.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloEnumeracion);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: enumeracion
     *
     * @author: Diego Fernando Guzman
     * @param Modelo
     * @return String
     * @version: 21/05/2020
     */
    public String Insert(ModeloEnumeracion modeloEnumeracion) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO enumeracion("
                        + "id_enumeracion, "
                        + "campo, "
                        + "estado)"
                        + " VALUE (?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setInt(1, modeloEnumeracion.getModelo_enumeracion().getId());
                SQL.setString(2, modeloEnumeracion.getCampo());
                SQL.setString(3, modeloEnumeracion.getEstado());
                System.out.println(modeloEnumeracion);
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.");
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladorenumeracion" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorenumeracion" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:enumeracion
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 21/05/2020
     */
    public String Update(ModeloEnumeracion modeloEnumeracion) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloEnumeracion.getEstado())) {
                    SQL = con.prepareStatement("UPDATE enumeracion SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloEnumeracion.getEstado());
                    SQL.setInt(2, modeloEnumeracion.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE enumeracion SET "
                            + "id_enumeracion = ?, "
                            + "campo = ?"
                            + " WHERE id = ? ");
                    SQL.setInt(1, modeloEnumeracion.getModelo_enumeracion().getId());
                    SQL.setString(2, modeloEnumeracion.getCampo());
                    SQL.setInt(3, modeloEnumeracion.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorenumeracion" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorenumeracion" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * llena un modelo que viene con datos de un request para ser Eliminado
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 21/05/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloEnumeracion modeloEnumeracion = new ModeloEnumeracion();
            modeloEnumeracion.setId(Integer.parseInt(request.getParameter("id")));
            modeloEnumeracion.setEstado("N");
            resultado = Update(modeloEnumeracion);
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla enumeracion dependiendo de un ID
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 21/05/2020
     */
    public ModeloEnumeracion getModelo(Integer Id) {
        ModeloEnumeracion modeloEnumeracion = new ModeloEnumeracion();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "id_enumeracion, "
                    + "campo, "
                    + "estado"
                    + " FROM enumeracion"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloEnumeracion.setId(res.getInt("id"));
                modeloEnumeracion.setModelo_enumeracion(getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_enumeracion")))));
                modeloEnumeracion.setCampo(res.getString("campo"));
                modeloEnumeracion.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorenumeracion" + e);
        }
        return modeloEnumeracion;
    }

    /**
     * Llena un Listado de la tabla enumeracion
     *
     * @author: Diego Fernando Guzman
     * @param vacio
     * @return LinkedList<ModeloEnumeracion>
     * @version: 21/05/2020
     */
    public LinkedList<ModeloEnumeracion> Read(String estado, String id_enumeracion) throws SQLException {
        LinkedList<ModeloEnumeracion> ListaModeloEnumeracion = new LinkedList<ModeloEnumeracion>();
        con = conexion.abrirConexion();
        String Where = "";
        if (!"0".contentEquals(id_enumeracion)) {
            Where = "and id_enumeracion = " + id_enumeracion;
        }
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "id_enumeracion, "
                    + "campo, "
                    + "estado"
                    + " FROM enumeracion"
                    + " WHERE estado = ? "
                    + Where);
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloEnumeracion modeloEnumeracion = new ModeloEnumeracion();
                modeloEnumeracion.setId(res.getInt("id"));
                modeloEnumeracion.setModelo_enumeracion(getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_enumeracion")))));
                modeloEnumeracion.setCampo(res.getString("campo"));
                modeloEnumeracion.setEstado(res.getString("estado"));
                ListaModeloEnumeracion.add(modeloEnumeracion);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorenumeracion" + e);
        }
        return ListaModeloEnumeracion;
    }

    /**
     * Llena un Listado de la tabla enumeracion en una cadena con
     * caracteristicas HTML
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 21/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String out = null;
        String estado = "S";
        if (request.getParameter("estado") != null) {
            estado = "N";
        }
        try {
            String id_enumeracion = request.getParameter("id_campo");
            LinkedList<ModeloEnumeracion> ListaModeloEnumeracion = Read(estado, id_enumeracion);
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                //out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloEnumeracion modeloEnumeracion : ListaModeloEnumeracion) {
                    out += "<option value=\"" + modeloEnumeracion.getId() + "\"> " + modeloEnumeracion.getCampo() + "</option>";
                }
            } else {
                out = "";
                out += "<thead>";
                out += "<tr>";
                out += "<th>Enumeracion Padre</th>";
                out += "<th>Campo</th>";
                out += "<th>Opciones</th>";
                out += "</tr>";
                out += "</thead>";
                out += "<tbody>";
                for (ModeloEnumeracion modeloEnumeracion : ListaModeloEnumeracion) {
                    out += "<tr>";
                    out += "<td>" + modeloEnumeracion.getModelo_enumeracion().getCampo() + "</td>";
                    out += "<td>" + modeloEnumeracion.getCampo() + "</td>";
                    out += "<td class=\"text-center\">";
// Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                    out += "data-id=\"" + modeloEnumeracion.getId()+ "\"";
                    out += "data-id_enumeracion=\"" + modeloEnumeracion.getModelo_enumeracion().getId()+ "\"";
                    out += "data-campo=\"" + modeloEnumeracion.getCampo() + "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
//Boton Eliminar
                    out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                    out += "data-id=\"" + modeloEnumeracion.getId()+ "\"";
                    out += "data-id_enumeracion=\"" + modeloEnumeracion.getModelo_enumeracion().getId()+ "\"";
                    out += "data-campo=\"" + modeloEnumeracion.getCampo() + "\"";
                    out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                    out += "</td>";
                    out += "</tr>";
                }
                out += "</tbody>";
            }
        } catch (Exception e) {
            System.out.println("Error en la generacion Html en Controladorenumeracion" + e);
        }
        return out;
    }

}
