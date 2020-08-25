/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Herramienta.Herramienta;
import Modelo.ModeloAsociacion_grupo_vencimientio;
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
public class ControladorAsociacion_grupo_vencimiento {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;
    ControladorEnumeracion controladorEnumeracion = new ControladorEnumeracion();
    Herramienta herramienta = new Herramienta();

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * asociacion_grupo_vencimientio
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 21/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloAsociacion_grupo_vencimientio modeloAsociacion_grupo_vencimientio = new ModeloAsociacion_grupo_vencimientio();
        modeloAsociacion_grupo_vencimientio.setModelo_enumeracion_grupo(controladorEnumeracion.getModelo(Integer.parseInt(request.getParameter("id_grupo"))));
        modeloAsociacion_grupo_vencimientio.setModelo_enumeracion_vencimiento(controladorEnumeracion.getModelo(Integer.parseInt(request.getParameter("id_vencimiento"))));
        modeloAsociacion_grupo_vencimientio.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            modeloAsociacion_grupo_vencimientio.setEstado("S");
            resultado = Insert(modeloAsociacion_grupo_vencimientio);
        } else {
            modeloAsociacion_grupo_vencimientio.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloAsociacion_grupo_vencimientio);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla:
     * asociacion_grupo_vencimientio
     *
     * @author: Diego Fernando Guzman
     * @param Modelo
     * @return String
     * @version: 21/05/2020
     */
    public String Insert(ModeloAsociacion_grupo_vencimientio modeloAsociacion_grupo_vencimientio) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO asociacion_grupo_vencimientio("
                        + "id_enumeracion_grupo, "
                        + "id_enumeracion_vencimiento, "
                        + "estado)"
                        + " VALUE (?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setInt(1, modeloAsociacion_grupo_vencimientio.getModelo_enumeracion_grupo().getId());
                SQL.setInt(2, modeloAsociacion_grupo_vencimientio.getModelo_enumeracion_vencimiento().getId());
                SQL.setString(3, modeloAsociacion_grupo_vencimientio.getEstado());
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "asociacion_grupo_vencimientio", user, i, "Se inserto el registro.", "", "");
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladorasociacion_grupo_vencimientio" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorasociacion_grupo_vencimientio" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la
     * tabla:asociacion_grupo_vencimientio
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 21/05/2020
     */
    public String Update(ModeloAsociacion_grupo_vencimientio modeloAsociacion_grupo_vencimientio) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloAsociacion_grupo_vencimientio.getEstado())) {
                    SQL = con.prepareStatement("UPDATE asociacion_grupo_vencimientio SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloAsociacion_grupo_vencimientio.getEstado());
                    SQL.setInt(2, modeloAsociacion_grupo_vencimientio.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE asociacion_grupo_vencimientio SET "
                            + "id_enumeracion_grupo = ?, "
                            + "id_enumeracion_vencimiento = ?"
                            + " WHERE id = ? ");
                    SQL.setInt(1, modeloAsociacion_grupo_vencimientio.getModelo_enumeracion_grupo().getId());
                    SQL.setInt(2, modeloAsociacion_grupo_vencimientio.getModelo_enumeracion_vencimiento().getId());
                    SQL.setInt(3, modeloAsociacion_grupo_vencimientio.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorasociacion_grupo_vencimientio" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorasociacion_grupo_vencimientio" + e);
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
            ModeloAsociacion_grupo_vencimientio modeloAsociacion_grupo_vencimientio = new ModeloAsociacion_grupo_vencimientio();
            modeloAsociacion_grupo_vencimientio.setId(Integer.parseInt(request.getParameter("id")));
            modeloAsociacion_grupo_vencimientio.setEstado("N");
            resultado = Update(modeloAsociacion_grupo_vencimientio);
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla asociacion_grupo_vencimientio dependiendo
     * de un ID
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 21/05/2020
     */
    public ModeloAsociacion_grupo_vencimientio getModelo(Integer Id) {
        ModeloAsociacion_grupo_vencimientio modeloAsociacion_grupo_vencimientio = new ModeloAsociacion_grupo_vencimientio();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "id_enumeracion_grupo, "
                    + "id_enumeracion_vencimiento, "
                    + "estado"
                    + " FROM asociacion_grupo_vencimientio"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloAsociacion_grupo_vencimientio.setId(res.getInt("id"));
                modeloAsociacion_grupo_vencimientio.setModelo_enumeracion_grupo(controladorEnumeracion.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_enumeracion_grupo")))));
                modeloAsociacion_grupo_vencimientio.setModelo_enumeracion_vencimiento(controladorEnumeracion.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_enumeracion_vencimiento")))));
                modeloAsociacion_grupo_vencimientio.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorasociacion_grupo_vencimientio" + e);
        }
        return modeloAsociacion_grupo_vencimientio;
    }

    /**
     * Llena un Listado de la tabla asociacion_grupo_vencimientio
     *
     * @author: Diego Fernando Guzman
     * @param vacio
     * @return LinkedList<ModeloAsociacion_grupo_vencimientio>
     * @version: 21/05/2020
     */
    public LinkedList<ModeloAsociacion_grupo_vencimientio> Read(String estado, String id_enumeracion) throws SQLException {
        LinkedList<ModeloAsociacion_grupo_vencimientio> ListaModeloAsociacion_grupo_vencimientio = new LinkedList<ModeloAsociacion_grupo_vencimientio>();
        con = conexion.abrirConexion();
        String Where = "";
        if (!"0".contentEquals(id_enumeracion)) {
            Where = "and id_enumeracion_grupo = " + id_enumeracion;
        }
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "id_enumeracion_grupo, "
                    + "id_enumeracion_vencimiento, "
                    + "estado"
                    + " FROM asociacion_grupo_vencimientio"
                    + " WHERE estado = ? "
                    + Where);
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloAsociacion_grupo_vencimientio modeloAsociacion_grupo_vencimientio = new ModeloAsociacion_grupo_vencimientio();
                modeloAsociacion_grupo_vencimientio.setId(res.getInt("id"));
                modeloAsociacion_grupo_vencimientio.setModelo_enumeracion_grupo(controladorEnumeracion.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_enumeracion_grupo")))));
                modeloAsociacion_grupo_vencimientio.setModelo_enumeracion_vencimiento(controladorEnumeracion.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_enumeracion_vencimiento")))));
                modeloAsociacion_grupo_vencimientio.setEstado(res.getString("estado"));
                ListaModeloAsociacion_grupo_vencimientio.add(modeloAsociacion_grupo_vencimientio);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorasociacion_grupo_vencimientio" + e);
        }
        return ListaModeloAsociacion_grupo_vencimientio;
    }

    /**
     * Llena un Listado de la tabla asociacion_grupo_vencimientio en una cadena
     * con caracteristicas HTML
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
            LinkedList<ModeloAsociacion_grupo_vencimientio> ListaModeloAsociacion_grupo_vencimientio = Read(estado, "0");
            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Grupo</th>";
            out += "<th>Campo Vencimiento</th>";
            out += "<th>Opciones</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloAsociacion_grupo_vencimientio modeloAsociacion_grupo_vencimientio : ListaModeloAsociacion_grupo_vencimientio) {
                out += "<tr>";
                out += "<td>" + modeloAsociacion_grupo_vencimientio.getModelo_enumeracion_grupo().getCampo() + "</td>";
                out += "<td>" + modeloAsociacion_grupo_vencimientio.getModelo_enumeracion_vencimiento().getCampo() + "</td>";
                out += "<td class=\"text-center\">";
// Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloAsociacion_grupo_vencimientio.getId() + "\"";
                out += "data-id_enumeracion_grupo=\"" + modeloAsociacion_grupo_vencimientio.getModelo_enumeracion_grupo().getId() + "\"";
                out += "data-id_enumeracion_vencimiento=\"" + modeloAsociacion_grupo_vencimientio.getModelo_enumeracion_vencimiento().getId() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
//Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloAsociacion_grupo_vencimientio.getId() + "\"";
                out += "data-id_enumeracion_grupo=\"" + modeloAsociacion_grupo_vencimientio.getModelo_enumeracion_grupo().getId() + "\"";
                out += "data-id_enumeracion_vencimiento=\"" + modeloAsociacion_grupo_vencimientio.getModelo_enumeracion_vencimiento().getId()+ "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e) {
            System.out.println("Error en la generacion Html en Controladorasociacion_grupo_vencimientio" + e);
        }
        return out;
    }

}
