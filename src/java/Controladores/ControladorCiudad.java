package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloCiudad;
import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 * Esta clase permite controlar los eventos de las Ciudades
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorCiudad {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * ciudad
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 14/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloCiudad modeloCiudad = new ModeloCiudad();
        modeloCiudad.setCodigo(request.getParameter("codigo"));
        modeloCiudad.setNombre(request.getParameter("nombre"));
        modeloCiudad.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloCiudad);
        } else {
            modeloCiudad.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloCiudad);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: ciudad
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param Modelo
     * @return String
     * @version: 14/05/2020
     */
    public String Insert(ModeloCiudad modeloCiudad) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO ciudad("
                        + "codigo, "
                        + "nombre, "
                        + "estado)"
                        + " VALUE (?,?,?);", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloCiudad.getCodigo());
                SQL.setString(2, modeloCiudad.getNombre());
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
                System.out.println("Error en la consulta SQL Insert en Controladorciudad" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorciudad" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:ciudad
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 14/05/2020
     */
    public String Update(ModeloCiudad modeloCiudad) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloCiudad.getEstado())) {
                    SQL = con.prepareStatement("UPDATE ciudad SET "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloCiudad.getEstado());
                    SQL.setInt(2, modeloCiudad.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE ciudad SET "
                            + "codigo = ?, "
                            + "nombre = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloCiudad.getCodigo());
                    SQL.setString(2, modeloCiudad.getNombre());
                    SQL.setInt(3, modeloCiudad.getId());
                }

                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorciudad" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorciudad" + e);
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
     * @version: 14/05/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloCiudad modeloCiudad = new ModeloCiudad();
            modeloCiudad.setId(Integer.parseInt(request.getParameter("id")));
            modeloCiudad.setEstado("N");
            resultado = Update(modeloCiudad);
        }
        return resultado;
    }

    /**
     * Llena un Listado de la tabla ciudad
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param vacio
     * @return LinkedList<ModeloCiudad>
     * @version: 14/05/2020
     */
    public LinkedList<ModeloCiudad> Read(String estado) throws SQLException {
        LinkedList<ModeloCiudad> ListaModeloCiudad = new LinkedList<ModeloCiudad>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado"
                    + " FROM ciudad"
                    + " WHERE estado = ?");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloCiudad modeloCiudad = new ModeloCiudad();
                modeloCiudad.setId(res.getInt("id"));
                modeloCiudad.setCodigo(res.getString("codigo"));
                modeloCiudad.setNombre(res.getString("nombre"));
                modeloCiudad.setEstado(res.getString("estado"));
                ListaModeloCiudad.add(modeloCiudad);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorciudad" + e);
        }
        return ListaModeloCiudad;
    }

    /**
     * Permite listar la información de la tabla de Ciudad
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @param response
     * @return String
     * @version: 07/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String out = null;
        String estado = "S";
        if (request.getParameter("estado") != null) {
            estado = "N";
        }
        try {
            LinkedList<ModeloCiudad> listmoCiudades;
            listmoCiudades = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloCiudad modeloCiudad : listmoCiudades) {
                    out += "<option value=\"" + modeloCiudad.getId() + "\"> " + modeloCiudad.getNombre() + "</option>";
                }
            } else {
                out = "";
                out += "<thead>";
                out += "<tr>";
                out += "<th>Codigo</th>";
                out += "<th>Nombre</th>";
                out += "<th>Opcion</th>";
                out += "</tr>";
                out += "</thead>";
                out += "<tbody>";
                for (ModeloCiudad modeloCiudad : listmoCiudades) {
                    out += "<tr>";
                    out += "<td>" + modeloCiudad.getCodigo() + "</td>";
                    out += "<td>" + modeloCiudad.getNombre() + "</td>";
                    out += "<td class=\"text-center\">";
                    // Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                    out += "data-id=\"" + modeloCiudad.getId() + "\"";
                    out += "data-codigo=\"" + modeloCiudad.getCodigo() + "\"";
                    out += "data-nombre=\"" + modeloCiudad.getNombre() + "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                    //Boton Eliminar
                    out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                    out += "data-id=\"" + modeloCiudad.getId() + "\"";
                    out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                    out += "</td>";
                    out += "</tr>";
                }
                out += "</tbody>";
            }
        } catch (Exception e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }

    /**
     * Retorna un modelo de la tabla ciudad dependiendo de un ID
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 14/05/2020
     */
    public ModeloCiudad getModelo(Integer Id) {
        ModeloCiudad modeloCiudad = new ModeloCiudad();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado"
                    + " FROM ciudad"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                res.first();
                modeloCiudad.setId(res.getInt("id"));
                modeloCiudad.setCodigo(res.getString("codigo"));
                modeloCiudad.setNombre(res.getString("nombre"));
                modeloCiudad.setEstado(res.getString("estado"));
            } else {
                modeloCiudad.setId(0);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorciudad" + e);
        }
        return modeloCiudad;
    }

}
