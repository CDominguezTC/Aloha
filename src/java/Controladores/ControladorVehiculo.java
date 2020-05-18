/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloVehiculo;
import Modelo.ModeloVisita;
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
 * @author Diego Fdo Guzman B
 */
public class ControladorVehiculo {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    ControladorPersona controladorPersona = new ControladorPersona();

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * vehiculo
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloVehiculo modeloVehiculo = new ModeloVehiculo();
        modeloVehiculo.setPlaca_vehiculo(request.getParameter("placa_vehiculo"));
        modeloVehiculo.setColor_vehiculo(request.getParameter("color_vehiculo"));
        modeloVehiculo.setMarca_vehiculo(request.getParameter("marca_vehiculo"));
        modeloVehiculo.setTipo_vehiculo(request.getParameter("tipo_vehiculo"));
        modeloVehiculo.setModelo_persona_responsable(controladorPersona.getModelo(Integer.parseInt(request.getParameter("Modelo_persona_responsable"))));
        modeloVehiculo.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            resultado = Insert(modeloVehiculo);
        } else {
            modeloVehiculo.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloVehiculo);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: vehiculo
     *
     * @author: Diego Fernando Guzman
     * @param Modelo
     * @return String
     * @version: 11/05/2020
     */
    public String Insert(ModeloVehiculo modeloVehiculo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO vehiculo("
                        + "placa_vehiculo, "
                        + "color_vehiculo, "
                        + "marca_vehiculo, "
                        + "tipo_vehiculo, "
                        + "id_persona_responsable, "
                        + "estado)"
                        + " VALUE = (?,?,?,?,?,?)");
                SQL.setString(1, modeloVehiculo.getPlaca_vehiculo());
                SQL.setString(2, modeloVehiculo.getColor_vehiculo());
                SQL.setString(3, modeloVehiculo.getMarca_vehiculo());
                SQL.setString(4, modeloVehiculo.getTipo_vehiculo());
                SQL.setInt(5, modeloVehiculo.getModelo_persona_responsable().getId());
                SQL.setString(6, modeloVehiculo.getEstado());
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladorvehiculo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorvehiculo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:vehiculo
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public String Update(ModeloVehiculo modeloVehiculo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("UPDATE vehiculo SET "
                        + "placa_vehiculo = ?, "
                        + "color_vehiculo = ?, "
                        + "marca_vehiculo = ?, "
                        + "tipo_vehiculo = ?, "
                        + "id_persona_responsable = ?, "
                        + "estado = ?"
                        + " WHERE id = ? ");
                SQL.setString(1, modeloVehiculo.getPlaca_vehiculo());
                SQL.setString(2, modeloVehiculo.getColor_vehiculo());
                SQL.setString(3, modeloVehiculo.getMarca_vehiculo());
                SQL.setString(4, modeloVehiculo.getTipo_vehiculo());
                SQL.setInt(5, modeloVehiculo.getModelo_persona_responsable().getId());
                SQL.setString(6, modeloVehiculo.getEstado());
                SQL.setInt(7, modeloVehiculo.getId());
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorvehiculo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorvehiculo" + e);
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
     * @version: 11/05/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloVehiculo modeloVehiculo = new ModeloVehiculo();
            modeloVehiculo.setId(Integer.parseInt(request.getParameter("id")));
            resultado = DeleteModelo(modeloVehiculo);
        }
        return resultado;
    }

    /**
     * Elimina los datos en la base de datos de la tabla: vehiculo
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public String DeleteModelo(ModeloVehiculo modeloVehiculo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("DELETE FROM vehiculo "
                        + " WHERE id = ? ");
                SQL.setInt(1, modeloVehiculo.getId());
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Delete en Controladorvehiculo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Delete en Controladorvehiculo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla vehiculo dependiendo de un ID
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public ModeloVehiculo getModelo(Integer Id) throws SQLException {
        ModeloVehiculo modeloVehiculo = new ModeloVehiculo();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "placa_vehiculo, "
                    + "color_vehiculo, "
                    + "marca_vehiculo, "
                    + "tipo_vehiculo, "
                    + "id_persona_responsable, "
                    + "estado"
                    + " FROM vehiculo"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloVehiculo.setId(res.getInt("id"));
                modeloVehiculo.setPlaca_vehiculo(res.getString("placa_vehiculo"));
                modeloVehiculo.setColor_vehiculo(res.getString("color_vehiculo"));
                modeloVehiculo.setMarca_vehiculo(res.getString("marca_vehiculo"));
                modeloVehiculo.setTipo_vehiculo(res.getString("tipo_vehiculo"));
                modeloVehiculo.setModelo_persona_responsable(controladorPersona.getModelo(res.getInt("id_persona_responsable")));
                modeloVehiculo.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorvehiculo" + e);
        }
        return modeloVehiculo;
    }

    /**
     * Llena un Listado de la tabla vehiculo
     *
     * @author: Diego Fernando Guzman
     * @param vacio
     * @return LinkedList<ModeloVehiculo>
     * @version: 11/05/2020
     */
    public LinkedList<ModeloVehiculo> Read() throws SQLException {
        LinkedList<ModeloVehiculo> ListaModeloVehiculo = new LinkedList<ModeloVehiculo>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "placa_vehiculo, "
                    + "color_vehiculo, "
                    + "marca_vehiculo, "
                    + "tipo_vehiculo, "
                    + "id_persona_responsable, "
                    + "estado"
                    + " FROM vehiculo");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloVehiculo modeloVehiculo = new ModeloVehiculo();
                modeloVehiculo.setId(res.getInt("id"));
                modeloVehiculo.setPlaca_vehiculo(res.getString("placa_vehiculo"));
                modeloVehiculo.setColor_vehiculo(res.getString("color_vehiculo"));
                modeloVehiculo.setMarca_vehiculo(res.getString("marca_vehiculo"));
                modeloVehiculo.setTipo_vehiculo(res.getString("tipo_vehiculo"));
                modeloVehiculo.setModelo_persona_responsable(controladorPersona.getModelo(res.getInt("id_persona_responsable")));
                modeloVehiculo.setEstado(res.getString("estado"));
                ListaModeloVehiculo.add(modeloVehiculo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorvehiculo" + e);
        }
        return ListaModeloVehiculo;
    }

    /**
     * Llena un Listado de la tabla vehiculo en una cadena con caracteristicas
     * HTML
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String out = null;
        try {
            ControladorVehiculo controladorVehiculo = new ControladorVehiculo();
            LinkedList<ModeloVehiculo> ListaModeloVehiculo = controladorVehiculo.Read();
            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Id</th>";
            out += "<th>PLACA</th>";
            out += "<th>COLOR</th>";
            out += "<th>MARCA</th>";
            out += "<th>TIPO</th>";
            out += "<th>RESPONSABLE</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloVehiculo modeloVehiculo : ListaModeloVehiculo) {
                out += "<tr>";
                out += "<td>" + modeloVehiculo.getId() + "</td>";
                out += "<td>" + modeloVehiculo.getPlaca_vehiculo() + "</td>";
                out += "<td>" + modeloVehiculo.getColor_vehiculo() + "</td>";
                out += "<td>" + modeloVehiculo.getMarca_vehiculo() + "</td>";
                out += "<td>" + modeloVehiculo.getTipo_vehiculo() + "</td>";
                out += "<td>" + modeloVehiculo.getModelo_persona_responsable().getString() + "</td>";
                out += "<td class=\"text-center\">";
// Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloVehiculo.getId() + "\"";
                out += "data-placa_vehiculo=\"" + modeloVehiculo.getPlaca_vehiculo() + "\"";
                out += "data-color_vehiculo=\"" + modeloVehiculo.getColor_vehiculo() + "\"";
                out += "data-marca_vehiculo=\"" + modeloVehiculo.getMarca_vehiculo() + "\"";
                out += "data-tipo_vehiculo=\"" + modeloVehiculo.getTipo_vehiculo() + "\"";
                out += "data-id_persona_responsable=\"" + modeloVehiculo.getModelo_persona_responsable().getString() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
//Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloVehiculo.getId() + "\"";
                out += "data-placa_vehiculo=\"" + modeloVehiculo.getPlaca_vehiculo() + "\"";
                out += "data-color_vehiculo=\"" + modeloVehiculo.getColor_vehiculo() + "\"";
                out += "data-marca_vehiculo=\"" + modeloVehiculo.getMarca_vehiculo() + "\"";
                out += "data-tipo_vehiculo=\"" + modeloVehiculo.getTipo_vehiculo() + "\"";
                out += "data-id_persona_responsable=\"" + modeloVehiculo.getModelo_persona_responsable().getString() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e) {
            System.out.println("Error en la generacion Html en Controladorvehiculo" + e);
        }
        return out;
    }

}
