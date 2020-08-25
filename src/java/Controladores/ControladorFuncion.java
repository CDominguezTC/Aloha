package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloFuncion;
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
 * Esta clase permite controlar los eventos de Funciones
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorFuncion {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * funcion
     *
     * @author: Carlos A Dominguez Diaz
     * @param request
     * @return String
     * @version: 25/06/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloFuncion modeloFuncion = new ModeloFuncion();
        modeloFuncion.setNombre(request.getParameter("nombre"));
        modeloFuncion.setDescripcion(request.getParameter("descripcion"));
        modeloFuncion.setCodigo_reloj(request.getParameter("codReloj"));
        modeloFuncion.setEstado("S");
        
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloFuncion);
        } else {
            modeloFuncion.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloFuncion);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: funcion
     *
     * @author: Carlos A Dominguez Diaz
     * @param Modelo
     * @return String
     * @version: 25/06/2020
     */
    public String Insert(ModeloFuncion modeloFuncion) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO funcion("
                        + "nombre, "
                        + "descripcion, "
                        + "codigo_reloj, "
                        + "estado)"
                        + " VALUE (?,?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloFuncion.getNombre());
                SQL.setString(2, modeloFuncion.getDescripcion());
                SQL.setString(3, modeloFuncion.getCodigo_reloj());
                SQL.setString(4, modeloFuncion.getEstado());
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "funcion", user, i, "Se inserto el registro.", "", "");
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladorfuncion" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorfuncion" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:funcion
     *
     * @author: Carlos A Dominguez Diaz
     * @param request
     * @return String
     * @version: 25/06/2020
     */
    public String Update(ModeloFuncion modeloFuncion) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloFuncion.getEstado())) {
                    SQL = con.prepareStatement("UPDATE funcion SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloFuncion.getEstado());
                    SQL.setInt(2, modeloFuncion.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE funcion SET "
                            + "nombre = ?, "
                            + "descripcion = ?, "
                            + "codigo_reloj = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloFuncion.getNombre());
                    SQL.setString(2, modeloFuncion.getDescripcion());
                    SQL.setString(3, modeloFuncion.getCodigo_reloj());
                    SQL.setInt(4, modeloFuncion.getId());                    
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "4";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorfuncion" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorfuncion" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * llena un modelo que viene con datos de un request para ser Eliminado
     *
     * @author: Carlos A Dominguez Diaz
     * @param request
     * @return String
     * @version: 25/06/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloFuncion modeloFuncion = new ModeloFuncion();
            modeloFuncion.setId(Integer.parseInt(request.getParameter("id")));
            modeloFuncion.setEstado("N");
            resultado = Update(modeloFuncion);
            if (resultado.equals("4"))
            {
                resultado = "2";                
            }
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla funcion dependiendo de un ID
     *
     * @author: Carlos A Dominguez Diaz
     * @param request
     * @return String
     * @version: 25/06/2020
     */
    public ModeloFuncion getModelo(Integer Id) {
        ModeloFuncion modeloFuncion = new ModeloFuncion();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "nombre, "
                    + "descripcion, "
                    + "codigo_reloj, "
                    + "estado"
                    + " FROM funcion"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloFuncion.setId(res.getInt("id"));
                modeloFuncion.setNombre(res.getString("nombre"));
                modeloFuncion.setDescripcion(res.getString("descripcion"));
                modeloFuncion.setCodigo_reloj(res.getString("codigo_reloj"));
                modeloFuncion.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorfuncion" + e);
        }
        return modeloFuncion;
    }

    /**
     * Llena un Listado de la tabla funcion
     *
     * @author: Carlos A Dominguez Diaz
     * @param vacio
     * @return LinkedList<ModeloFuncion>
     * @version: 25/06/2020
     */
    public LinkedList<ModeloFuncion> Read(String estado) throws SQLException {
        LinkedList<ModeloFuncion> ListaModeloFuncion = new LinkedList<ModeloFuncion>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "nombre, "
                    + "descripcion, "
                    + "codigo_reloj, "
                    + "estado"
                    + " FROM funcion"
                    + " WHERE estado = ? ");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloFuncion modeloFuncion = new ModeloFuncion();
                modeloFuncion.setId(res.getInt("id"));
                modeloFuncion.setNombre(res.getString("nombre"));
                modeloFuncion.setDescripcion(res.getString("descripcion"));
                modeloFuncion.setCodigo_reloj(res.getString("codigo_reloj"));
                modeloFuncion.setEstado(res.getString("estado"));
                ListaModeloFuncion.add(modeloFuncion);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorfuncion" + e);
        }
        return ListaModeloFuncion;
    }

    /**
     * Permite listar la información de la tabla de Funciones
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
            LinkedList<ModeloFuncion> listmoFunciones;
            listmoFunciones = Read("S");
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Nombre</th>";
            out += "<th>Descripcion</th>";
            out += "<th>Codigo Reloj</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloFuncion modeloFunciones : listmoFunciones) {
                out += "<tr>";
                out += "<td>" + modeloFunciones.getNombre() + "</td>";
                out += "<td>" + modeloFunciones.getDescripcion() + "</td>";
                out += "<td>" + modeloFunciones.getCodigo_reloj()+ "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloFunciones.getId() + "\"";
                out += "data-nombre=\"" + modeloFunciones.getNombre() + "\"";
                out += "data-descripcion=\"" + modeloFunciones.getDescripcion() + "\"";
                out += "data-codreloj=\"" + modeloFunciones.getCodigo_reloj()+ "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloFunciones.getId() + "\"";
                out += "data-nombre=\"" + modeloFunciones.getNombre() + "\"";
                out += "data-descripcion=\"" + modeloFunciones.getDescripcion() + "\"";
                out += "data-codreloj=\"" + modeloFunciones.getCodigo_reloj()+ "\"";
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
