/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Herramienta.Herramienta;
import Modelo.ModeloVencimiento;
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
public class ControladorVencimiento {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;
    ControladorPersona controladorPersona = new ControladorPersona();
    ControladorEnumeracion controladorEnumeracion = new ControladorEnumeracion();
    Herramienta herramienta = new Herramienta();

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * vencimiento
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 28/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloVencimiento modeloVencimiento = new ModeloVencimiento();
        modeloVencimiento.setModelo_persona(controladorPersona.getModelo(Integer.parseInt(request.getParameter("Modelo_persona"))));
        modeloVencimiento.setModelo_enumeracion_vencimiento(controladorEnumeracion.getModelo(Integer.parseInt(request.getParameter("Modelo_enumeracion_vencimiento"))));
        modeloVencimiento.setFecha_vencimiento(request.getParameter("fecha_vencimiento"));
        modeloVencimiento.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            modeloVencimiento.setEstado("S");
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloVencimiento);
        } else {
            modeloVencimiento.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloVencimiento);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: vencimiento
     *
     * @author: Diego Fernando Guzman
     * @param Modelo
     * @return String
     * @version: 28/05/2020
     */
    public String Insert(ModeloVencimiento modeloVencimiento) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO vencimiento("
                        + "id_persona, "
                        + "id_enumeracion_vencimiento, "
                        + "fecha_vencimiento, "
                        + "estado)"
                        + " VALUE (?,?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setInt(1, modeloVencimiento.getModelo_persona().getId());
                SQL.setInt(2, modeloVencimiento.getModelo_enumeracion_vencimiento().getId());
                SQL.setString(3, modeloVencimiento.getFecha_vencimiento());
                SQL.setString(4, modeloVencimiento.getEstado());
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
                System.out.println("Error en la consulta SQL Insert en Controladorvencimiento" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorvencimiento" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:vencimiento
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 28/05/2020
     */
    public String Update(ModeloVencimiento modeloVencimiento) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloVencimiento.getEstado())) {
                    SQL = con.prepareStatement("UPDATE vencimiento SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloVencimiento.getEstado());
                    SQL.setInt(2, modeloVencimiento.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE vencimiento SET "
                            + "id_persona = ?, "
                            + "id_enumeracion_vencimiento = ?, "
                            + "fecha_vencimiento = ?"
                            + " WHERE id = ? ");
                    SQL.setInt(1, modeloVencimiento.getModelo_persona().getId());
                    SQL.setInt(2, modeloVencimiento.getModelo_enumeracion_vencimiento().getId());
                    SQL.setString(3, modeloVencimiento.getFecha_vencimiento());
                    SQL.setString(4, modeloVencimiento.getEstado());
                    SQL.setInt(5, modeloVencimiento.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorvencimiento" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorvencimiento" + e);
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
     * @version: 28/05/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloVencimiento modeloVencimiento = new ModeloVencimiento();
            modeloVencimiento.setId(Integer.parseInt(request.getParameter("id")));
            modeloVencimiento.setEstado("N");
            resultado = Update(modeloVencimiento);
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla vencimiento dependiendo de un ID
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 28/05/2020
     */
    public ModeloVencimiento getModelo(Integer Id) {
        ModeloVencimiento modeloVencimiento = new ModeloVencimiento();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "id_persona, "
                    + "id_enumeracion_vencimiento, "
                    + "fecha_vencimiento, "
                    + "estado"
                    + " FROM vencimiento"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloVencimiento.setId(res.getInt("id"));
                modeloVencimiento.setModelo_persona(controladorPersona.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_persona")))));
                modeloVencimiento.setModelo_enumeracion_vencimiento(controladorEnumeracion.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_enumeracion_vencimiento")))));
                modeloVencimiento.setFecha_vencimiento(res.getString("fecha_vencimiento"));
                modeloVencimiento.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorvencimiento" + e);
        }
        return modeloVencimiento;
    }

    /**
     * Llena un Listado de la tabla vencimiento
     *
     * @author: Diego Fernando Guzman
     * @param vacio
     * @return LinkedList<ModeloVencimiento>
     * @version: 28/05/2020
     */
    public LinkedList<ModeloVencimiento> Read(String estado) throws SQLException {
        LinkedList<ModeloVencimiento> ListaModeloVencimiento = new LinkedList<ModeloVencimiento>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "id_persona, "
                    + "id_enumeracion_vencimiento, "
                    + "fecha_vencimiento, "
                    + "estado"
                    + " FROM vencimiento"
                    + " WHERE estado = ? ");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloVencimiento modeloVencimiento = new ModeloVencimiento();
                modeloVencimiento.setId(res.getInt("id"));
                modeloVencimiento.setModelo_persona(controladorPersona.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_persona")))));
                modeloVencimiento.setModelo_enumeracion_vencimiento(controladorEnumeracion.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_enumeracion_vencimiento")))));
                modeloVencimiento.setFecha_vencimiento(res.getString("fecha_vencimiento"));
                modeloVencimiento.setEstado(res.getString("estado"));
                ListaModeloVencimiento.add(modeloVencimiento);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorvencimiento" + e);
        }
        return ListaModeloVencimiento;
    }

    /**
     * Llena un Listado de la tabla vencimiento en una cadena con
     * caracteristicas HTML
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 28/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String out = null;
        String estado = "S";
        if (request.getParameter("estado") != null) {
            estado = "N";
        }
        try {
            LinkedList<ModeloVencimiento> ListaModeloVencimiento = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Persona</th>";
            out += "<th>Item</th>";
            out += "<th>Vencimiento</th>";
            out += "<th>Opciones</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloVencimiento modeloVencimiento : ListaModeloVencimiento) {
                out += "<tr>";
                out += "<td>" + modeloVencimiento.getModelo_persona().getNombres() + " " + modeloVencimiento.getModelo_persona().getApellidos() + "</td>";
                out += "<td>" + modeloVencimiento.getModelo_enumeracion_vencimiento().getCampo() + "</td>";
                out += "<td>" + modeloVencimiento.getFecha_vencimiento() + "</td>";
                out += "<td class=\"text-center\">";
// Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id_persona=\"" + modeloVencimiento.getModelo_persona().getNombres() + " " + modeloVencimiento.getModelo_persona().getApellidos() + "\"";
                out += "data-id_enumeracion_vencimiento=\"" + modeloVencimiento.getModelo_enumeracion_vencimiento().getCampo() + "\"";
                out += "data-fecha_vencimiento=\"" + modeloVencimiento.getFecha_vencimiento() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
//Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id_persona=\"" + modeloVencimiento.getModelo_persona().getNombres() + " " + modeloVencimiento.getModelo_persona().getApellidos() + "\"";
                out += "data-id_enumeracion_vencimiento=\"" + modeloVencimiento.getModelo_enumeracion_vencimiento().getCampo() + "\"";
                out += "data-fecha_vencimiento=\"" + modeloVencimiento.getFecha_vencimiento() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e) {
            System.out.println("Error en la generacion Html en Controladorvencimiento" + e);
        }
        return out;
    }

}
