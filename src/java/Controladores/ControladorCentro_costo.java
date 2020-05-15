/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloCentro_costo;
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
 * Esta clase permite controlar los eventos de Centro de Costo
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorCentro_costo {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd Centro
     * de costo
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        ModeloCentro_costo modeloCentro_costo = new ModeloCentro_costo();
        modeloCentro_costo.setCodigo(request.getParameter("codigo"));
        modeloCentro_costo.setNombre(request.getParameter("nombre"));
        modeloCentro_costo.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            resultado = Insert(modeloCentro_costo);
        } else {
            modeloCentro_costo.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloCentro_costo);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: centro_costo
     *
     * @author: Diego Fernando Guzman
     * @param Modelo
     * @return String
     * @version: 11/05/2020
     */
    public String Insert(ModeloCentro_costo modeloCentro_costo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO centro_costo("
                        + "codigo, "
                        + "nombre, "
                        + "estado)"
                        + " VALUE (?,?,?)");
                SQL.setString(1, modeloCentro_costo.getCodigo());
                SQL.setString(2, modeloCentro_costo.getNombre());
                SQL.setString(3, modeloCentro_costo.getEstado());
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladorcentro_costo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorcentro_costo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:centro_costo
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public String Update(ModeloCentro_costo modeloCentro_costo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloCentro_costo.getEstado())) {
                    SQL = con.prepareStatement("UPDATE centro_costo SET "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloCentro_costo.getEstado());
                    SQL.setInt(2, modeloCentro_costo.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE centro_costo SET "
                            + "codigo = ?, "
                            + "nombre = ?, "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloCentro_costo.getCodigo());
                    SQL.setString(2, modeloCentro_costo.getNombre());
                    SQL.setString(3, modeloCentro_costo.getEstado());
                    SQL.setInt(4, modeloCentro_costo.getId());
                }

                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorcentro_costo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorcentro_costo" + e);
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
            ModeloCentro_costo modeloCentro_costo = new ModeloCentro_costo();
            modeloCentro_costo.setId(Integer.parseInt(request.getParameter("id")));
            modeloCentro_costo.setEstado("N");
            resultado = Update(modeloCentro_costo);
        }
        return resultado;
    }


    /**
     * Retorna un modelo de la tabla centro_costo dependiendo de un ID
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public ModeloCentro_costo getModelo(Integer Id) {
        ModeloCentro_costo modeloCentro_costo = new ModeloCentro_costo();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado"
                    + " FROM centro_costo"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloCentro_costo.setId(res.getInt("id"));
                modeloCentro_costo.setCodigo(res.getString("codigo"));
                modeloCentro_costo.setNombre(res.getString("nombre"));
                modeloCentro_costo.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorcentro_costo" + e);
        }
        return modeloCentro_costo;
    }

    /**
     * Llena un Listado de la tabla centro_costo
     *
     * @author: Diego Fernando Guzman
     * @param vacio
     * @return LinkedList<ModeloCentro_costo>
     * @version: 11/05/2020
     */
    public LinkedList<ModeloCentro_costo> Read( String estado) throws SQLException {
        LinkedList<ModeloCentro_costo> ListaModeloCentro_costo = new LinkedList<ModeloCentro_costo>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado "
                    + "FROM centro_costo"
                    + "WHERE estado = ?");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloCentro_costo modeloCentro_costo = new ModeloCentro_costo();
                modeloCentro_costo.setId(res.getInt("id"));
                modeloCentro_costo.setCodigo(res.getString("codigo"));
                modeloCentro_costo.setNombre(res.getString("nombre"));
                modeloCentro_costo.setEstado(res.getString("estado"));
                ListaModeloCentro_costo.add(modeloCentro_costo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorcentro_costo" + e);
        }
        return ListaModeloCentro_costo;
    }

    /**
     * Llena un Listado de la tabla centro_costo en una cadena con
     * caracteristicas HTML
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String out = null;
        String estado = "S";
        if (!"null".equals(request.getParameter("estado"))) {
            estado = "N";
        }
        
        try {
            LinkedList<ModeloCentro_costo> ListaModeloCentro_costo = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Codigo</th>";
            out += "<th>Nombe</th>";
            out += "<th>Opciones</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloCentro_costo modeloCentro_costo : ListaModeloCentro_costo) {
                out += "<tr>";
                out += "<td>" + modeloCentro_costo.getCodigo() + "</td>";
                out += "<td>" + modeloCentro_costo.getNombre() + "</td>";
                out += "<td class=\"text-center\">";
// Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloCentro_costo.getId() + "\"";
                out += "data-codigo=\"" + modeloCentro_costo.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloCentro_costo.getNombre() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
//Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloCentro_costo.getId() + "\"";
                out += "data-codigo=\"" + modeloCentro_costo.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloCentro_costo.getNombre() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e) {
            System.out.println("Error en la generacion Html en Controladorcentro_costo" + e);
        }
        return out;
    }

}
