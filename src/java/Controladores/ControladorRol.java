/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloRol;
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
 * @author Julian A Aristizabal
 */
public class ControladorRol {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user = "";

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla rol
     *
     * @author: Julian A Aristizabal
     * @param request
     * @return String
     * @version: 8/6/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloRol modeloRol = new ModeloRol();
        modeloRol.setNombre(request.getParameter("nombre"));
        modeloRol.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloRol);
        } else {
            modeloRol.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloRol);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: rol
     *
     * @author: Julian A Aristizabal
     * @param Modelo
     * @return String
     * @version: 8/6/2020
     */
    public String Insert(ModeloRol modeloRol) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO rol("
                        + "nombre) "
                        //+ "estado)"
                        + "VALUES (?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloRol.getNombre());
                //SQL.setString(2, modeloRol.getEstado());
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "rol", user, i, "Se inserto el registro.");
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controlador rol: " + e.getMessage());
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controlador rol: " + e.getMessage());
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:rol
     *
     * @author: Julian A Aristizabal
     * @param request
     * @return String
     * @version: 8/6/2020
     */
    public String Update(ModeloRol modeloRol) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloRol.getEstado())) {
                    SQL = con.prepareStatement("UPDATE rol SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloRol.getEstado());
                    SQL.setInt(2, modeloRol.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE rol SET "
                            + "nombre = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloRol.getNombre());                    
                    SQL.setInt(2, modeloRol.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controlador rol: " + e.getMessage());
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controlador rol: " + e.getMessage());
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
     * @version: 8/6/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloRol modeloRol = new ModeloRol();
            modeloRol.setId(Integer.parseInt(request.getParameter("id")));
            modeloRol.setEstado("N");
            resultado = Update(modeloRol);
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla rol dependiendo de un ID
     *
     * @author: Julian A Aristizabal
     * @param request
     * @return String
     * @version: 8/6/2020
     */
    public ModeloRol getModelo(Integer Id) {
        ModeloRol modeloRol = new ModeloRol();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "nombre, "
                    + "estado"
                    + " FROM rol"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloRol.setId(res.getInt("id"));
                modeloRol.setNombre(res.getString("nombre"));
                modeloRol.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controlador rol: " + e.getMessage());
        }
        return modeloRol;
    }

    /**
     * Llena un Listado de la tabla rol
     *
     * @author: Julian A Aristizabal
     * @param vacio
     * @return LinkedList<ModeloRol>
     * @version: 8/6/2020
     */
    public LinkedList<ModeloRol> Read(String estado) throws SQLException {
        LinkedList<ModeloRol> ListaModeloRol = new LinkedList<ModeloRol>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "nombre, "
                    + "estado"
                    + " FROM rol"
                    + " WHERE estado = ? ORDER BY nombre");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloRol modeloRol = new ModeloRol();
                modeloRol.setId(res.getInt("id"));
                modeloRol.setNombre(res.getString("nombre"));
                modeloRol.setEstado(res.getString("estado"));
                ListaModeloRol.add(modeloRol);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controlador rol: " + e.getMessage());
        }
        return ListaModeloRol;
    }

    /**
     * Llena un Listado de la tabla rol en una cadena con caracteristicas HTML
     *
     * @author: Julian A Aristizabal
     * @param request
     * @return String
     * @version: 8/6/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String out = null;
        String estado = "S";
        if (request.getParameter("estado") != null) {
            estado = "N";
        }
        StringBuilder outsb = new StringBuilder();
        try {
            LinkedList<ModeloRol> ListaModeloRol = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>nombre</th>";
            out += "<th>Opciones</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            outsb.append(out);
            
            for (ModeloRol modeloRol : ListaModeloRol) {
                
                outsb.append("<tr>");
                outsb.append("<td>").append(modeloRol.getNombre()).append("</td>");
                //out += "<td>" + modeloRol.getNombre() + "</td>";
                outsb.append("<td class=\"text-center\">");
                
                // Boton Editar
                outsb.append("<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"");
                outsb.append("data-id=\"").append(modeloRol.getId()).append("\"");
                outsb.append("data-nombre=\"").append(modeloRol.getNombre()).append("\"");
                outsb.append("type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>");
                
                //Boton Eliminar
                outsb.append("<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"");
                outsb.append("data-id=\"").append(modeloRol.getId()).append("\"");
                outsb.append("data-nombre=\"").append(modeloRol.getNombre()).append("\"");
                outsb.append("type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>");
                outsb.append("</td>");
                outsb.append("</tr>");
            }
            
            outsb.append("</tbody>");
            
        } catch (Exception e) {
            System.out.println("Error en la generacion Html en Controladorrol" + e);
        }
        return outsb.toString();
    }

}
