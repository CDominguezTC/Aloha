package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloArea;
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
 * Esta clase permite controlar los eventos de Areas
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorArea {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * area
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloArea modeloArea = new ModeloArea();
        modeloArea.setCodigo(request.getParameter("codigo"));
        modeloArea.setNombre(request.getParameter("nombre"));
        modeloArea.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            resultado = Insert(modeloArea);
        } else {
            modeloArea.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloArea);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: area
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param Modelo
     * @return String
     * @version: 15/05/2020
     */
    public String Insert(ModeloArea modeloArea) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO area("
                        + "codigo, "
                        + "nombre, "
                        + "estado)"
                        + " VALUE (?,?,?)");
                SQL.setString(1, modeloArea.getCodigo());
                SQL.setString(2, modeloArea.getNombre());
                SQL.setString(3, modeloArea.getEstado());
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladorarea" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorarea" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:area
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Update(ModeloArea modeloArea) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("UPDATE area SET "
                        + "codigo = ?, "
                        + "nombre = ?, "
                        + "estado = ?"
                        + " WHERE id = ? ");
                SQL.setString(1, modeloArea.getCodigo());
                SQL.setString(2, modeloArea.getNombre());
                SQL.setString(3, modeloArea.getEstado());
                SQL.setInt(4, modeloArea.getId());
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorarea" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorarea" + e);
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
            ModeloArea modeloArea = new ModeloArea();
            modeloArea.setId(Integer.parseInt(request.getParameter("id")));
            resultado = DeleteModelo(modeloArea);
        }
        return resultado;
    }

    /**
     * Elimina los datos en la base de datos de la tabla: area
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String DeleteModelo(ModeloArea modeloArea) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("DELETE FROM area "
                        + " WHERE id = ? ");
                SQL.setInt(1, modeloArea.getId());
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Delete en Controladorarea" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Delete en Controladorarea" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla area dependiendo de un ID
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public ModeloArea getModelo(Integer Id) throws SQLException {
        ModeloArea modeloArea = new ModeloArea();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado"
                    + " FROM area"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloArea.setId(res.getInt("id"));
                modeloArea.setCodigo(res.getString("codigo"));
                modeloArea.setNombre(res.getString("nombre"));
                modeloArea.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorarea" + e);
        }
        return modeloArea;
    }

    /**
     * Llena un Listado de la tabla area
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param vacio
     * @return LinkedList<ModeloArea>
     * @version: 15/05/2020
     */
    public LinkedList<ModeloArea> Read() throws SQLException {
        LinkedList<ModeloArea> ListaModeloArea = new LinkedList<ModeloArea>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado"
                    + " FROM area");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloArea modeloArea = new ModeloArea();
                modeloArea.setId(res.getInt("id"));
                modeloArea.setCodigo(res.getString("codigo"));
                modeloArea.setNombre(res.getString("nombre"));
                modeloArea.setEstado(res.getString("estado"));
                ListaModeloArea.add(modeloArea);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorarea" + e);
        }
        return ListaModeloArea;
    }

    /**
     * Permite listar la informacion de la tabla de Areas
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
        try {
            LinkedList<ModeloArea> listmoAreases;
            ControladorArea controladorAreas = new ControladorArea();
            listmoAreases = controladorAreas.Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Codigo</th>";
            out += "<th>Nombre</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloArea modeloAreas : listmoAreases) {
                out += "<tr>";
                out += "<td>" + modeloAreas.getCodigo() + "</td>";
                out += "<td>" + modeloAreas.getNombre()+ "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloAreas.getId() + "\"";
                out += "data-codigo=\"" + modeloAreas.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloAreas.getNombre()+ "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloAreas.getId() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }
}
