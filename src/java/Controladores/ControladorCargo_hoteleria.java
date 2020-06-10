/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloCargo_hoteleria;
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
 * @author Carlos A Dominguez D
 */
public class ControladorCargo_hoteleria {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * cargo_hoteleria
     *
     * @param response
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloCargo_hoteleria modeloCargo_hoteleria = new ModeloCargo_hoteleria();
        modeloCargo_hoteleria.setTipo_cargo(request.getParameter("nombre"));
        modeloCargo_hoteleria.setValor_cargo(Integer.parseInt(request.getParameter("valor")));
        modeloCargo_hoteleria.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloCargo_hoteleria);
        } else {
            modeloCargo_hoteleria.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloCargo_hoteleria);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: cargo_hoteleria
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param Modelo
     * @return String
     * @version: 15/05/2020
     */
    public String Insert(ModeloCargo_hoteleria modeloCargo_hoteleria) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO cargo_hoteleria("
                        + "tipo_cargo, "
                        + "valor_cargo, "
                        + "estado) "
                        + "VALUE (?,?,?);", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloCargo_hoteleria.getTipo_cargo());
                SQL.setInt(2, modeloCargo_hoteleria.getValor_cargo());
                SQL.setString(3, "S");
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
                System.out.println("Error en la consulta SQL Insert en Controladorcargo_hoteleria" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorcargo_hoteleria" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:cargo_hoteleria
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Update(ModeloCargo_hoteleria modeloCargo_hoteleria) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloCargo_hoteleria.getEstado())) {
                    SQL = con.prepareStatement("UPDATE cargo_hoteleria SET "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloCargo_hoteleria.getEstado());
                    SQL.setInt(2, modeloCargo_hoteleria.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE cargo_hoteleria SET "
                            + "tipo_cargo = ?, "
                            + "valor_cargo = ? "
                            + "WHERE id = ? ");
                    SQL.setString(1, modeloCargo_hoteleria.getTipo_cargo());
                    SQL.setInt(2, modeloCargo_hoteleria.getValor_cargo());
                    SQL.setInt(3, modeloCargo_hoteleria.getId());
                }

                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorcargo_hoteleria" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorcargo_hoteleria" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * llena un modelo que viene con datos de un request para ser Eliminado
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloCargo_hoteleria modeloCargo_hoteleria = new ModeloCargo_hoteleria();
            modeloCargo_hoteleria.setId(Integer.parseInt(request.getParameter("id")));
            modeloCargo_hoteleria.setEstado("N");
            resultado = Update(modeloCargo_hoteleria);
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla cargo_hoteleria dependiendo de un ID
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public ModeloCargo_hoteleria getModelo(Integer Id) throws SQLException {
        ModeloCargo_hoteleria modeloCargo_hoteleria = new ModeloCargo_hoteleria();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "tipo_cargo, "
                    + "valor_cargo, "
                    + "estado"
                    + " FROM cargo_hoteleria"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloCargo_hoteleria.setId(res.getInt("id"));
                modeloCargo_hoteleria.setTipo_cargo(res.getString("tipo_cargo"));
                modeloCargo_hoteleria.setValor_cargo(res.getInt("valor_cargo"));
                modeloCargo_hoteleria.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorcargo_hoteleria" + e);
        }
        return modeloCargo_hoteleria;
    }

    /**
     * Llena un Listado de la tabla cargo_hoteleria
     *
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @param vacio
     * @return LinkedList<ModeloCargo_hoteleria>
     * @version: 15/05/2020
     */
    public LinkedList<ModeloCargo_hoteleria> Read(String estado) throws SQLException {
        LinkedList<ModeloCargo_hoteleria> ListaModeloCargo_hoteleria = new LinkedList<ModeloCargo_hoteleria>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "tipo_cargo, "
                    + "valor_cargo, "
                    + "estado "
                    + "FROM cargo_hoteleria "
                    + "WHERE estado = ? "
                    + "ORDER BY `tipo_cargo` ");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloCargo_hoteleria modeloCargo_hoteleria = new ModeloCargo_hoteleria();
                modeloCargo_hoteleria.setId(res.getInt("id"));
                modeloCargo_hoteleria.setTipo_cargo(res.getString("tipo_cargo"));
                modeloCargo_hoteleria.setValor_cargo(res.getInt("valor_cargo"));
                modeloCargo_hoteleria.setEstado(res.getString("estado"));
                ListaModeloCargo_hoteleria.add(modeloCargo_hoteleria);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorcargo_hoteleria" + e);
        }
        return ListaModeloCargo_hoteleria;
    }

    /**
     * Llena un Listado de la tabla cargo_hoteleria en una cadena con
     * caracteristicas HTML
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String out = null;
        String estado = "S";
        if (request.getParameter("estado") != null) {
            estado = "N";
        }
        try {
            LinkedList<ModeloCargo_hoteleria> ListaModeloCargo_hoteleria = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloCargo_hoteleria modeloCargo_hoteleria : ListaModeloCargo_hoteleria) {
                    out += "<option value=\"" + modeloCargo_hoteleria.getId() + "\"> " + modeloCargo_hoteleria.getTipo_cargo()+ "</option>";
                }
            } else {                
                response.setContentType("text/html;charset=UTF-8");
                out = "";
                out += "<thead>";
                out += "<tr>";
                out += "<th>Cargo</th>";
                out += "<th>Valor</th>";
                out += "<th>Opciones</th>";
                out += "</tr>";
                out += "</thead>";
                out += "<tbody>";
                for (ModeloCargo_hoteleria modeloCargo_hoteleria : ListaModeloCargo_hoteleria) {
                    out += "<tr>";
                    out += "<td>" + modeloCargo_hoteleria.getTipo_cargo() + "</td>";
                    out += "<td>" + modeloCargo_hoteleria.getValor_cargo() + "</td>";
                    out += "<td class=\"text-center\">";
// Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                    out += "data-id=\"" + modeloCargo_hoteleria.getId() + "\"";
                    out += "data-cargo=\"" + modeloCargo_hoteleria.getTipo_cargo() + "\"";
                    out += "data-valor=\"" + modeloCargo_hoteleria.getValor_cargo() + "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
//Boton Eliminar
                    out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                    out += "data-id=\"" + modeloCargo_hoteleria.getId() + "\"";
                    out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                    out += "</td>";
                    out += "</tr>";
                }
                out += "</tbody>";
            }
        } catch (Exception e) {
            System.out.println("Error en la generacion Html en Controladorcargo_hoteleria" + e);
        }
        return out;

    }

}
