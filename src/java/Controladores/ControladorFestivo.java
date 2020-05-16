package Controladores;

import Conexiones.ConexionBdMysql;
import Herramienta.Herramienta;
import Modelo.ModeloFestivo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Esta clase permite controlar los eventos de los dias Festivos
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorFestivo {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;
    Herramienta herramienta = new Herramienta();

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * festivo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloFestivo modeloFestivo = new ModeloFestivo();
        modeloFestivo.setFecha(herramienta.getStringDate(request.getParameter("fecha")));
        modeloFestivo.setNota(request.getParameter("nota"));
        modeloFestivo.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloFestivo);
        } else {
            modeloFestivo.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloFestivo);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: festivo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param Modelo
     * @return String
     * @version: 15/05/2020
     */
    public String Insert(ModeloFestivo modeloFestivo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO festivo("
                        + "fecha, "
                        + "nota, "
                        + "estado)"
                        + " VALUE (?,?,?);", SQL.RETURN_GENERATED_KEYS);
                SQL.setDate(1, herramienta.getUtilDate_SqlDate(modeloFestivo.getFecha()));
                SQL.setString(2, modeloFestivo.getNota());
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
                System.out.println("Error en la consulta SQL Insert en Controladorfestivo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorfestivo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:festivo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Update(ModeloFestivo modeloFestivo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {

                if ("N".equals(modeloFestivo.getEstado())) {
                    SQL = con.prepareStatement("UPDATE festivo SET "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloFestivo.getEstado());
                    SQL.setInt(2, modeloFestivo.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE festivo SET "
                            + "fecha = ?, "
                            + "nota = ? "                            
                            + " WHERE id = ? ");
                    SQL.setDate(1, herramienta.getUtilDate_SqlDate(modeloFestivo.getFecha()));
                    SQL.setString(2, modeloFestivo.getNota());                    
                    SQL.setInt(3, modeloFestivo.getId());
                }

                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorfestivo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorfestivo" + e);
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
            ModeloFestivo modeloFestivo = new ModeloFestivo();
            modeloFestivo.setId(Integer.parseInt(request.getParameter("id")));
            modeloFestivo.setEstado("N");
            resultado = Update(modeloFestivo);
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla festivo dependiendo de un ID
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public ModeloFestivo getModelo(Integer Id) throws SQLException {
        ModeloFestivo modeloFestivo = new ModeloFestivo();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "fecha, "
                    + "nota, "
                    + "estado"
                    + " FROM festivo"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloFestivo.setId(res.getInt("id"));
                modeloFestivo.setFecha(res.getDate("fecha"));
                modeloFestivo.setNota(res.getString("nota"));
                modeloFestivo.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorfestivo" + e);
        }
        return modeloFestivo;
    }

    /**
     * Llena un Listado de la tabla festivo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param vacio
     * @return LinkedList<ModeloFestivo>
     * @version: 15/05/2020
     */
    public LinkedList<ModeloFestivo> Read(String estado) throws SQLException {
        LinkedList<ModeloFestivo> ListaModeloFestivo = new LinkedList<ModeloFestivo>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "fecha, "
                    + "nota, "
                    + "estado "
                    + "FROM festivo "
                    + "WHERE estado = ?");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloFestivo modeloFestivo = new ModeloFestivo();
                modeloFestivo.setId(res.getInt("id"));
                modeloFestivo.setFecha(res.getDate("fecha"));
                modeloFestivo.setNota(res.getString("nota"));
                modeloFestivo.setEstado(res.getString("estado"));
                ListaModeloFestivo.add(modeloFestivo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorfestivo" + e);
        }
        return ListaModeloFestivo;
    }

    /**
     * Permite listar la información de la tabla de Festivos
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
            LinkedList<ModeloFestivo> listmoFestivos;
            listmoFestivos = Read(estado);
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Fecha</th>";
            out += "<th>Descripcion</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloFestivo modeloFestivo : listmoFestivos) {
                out += "<tr>";
                out += "<td>" + modeloFestivo.getFecha() + "</td>";
                out += "<td>" + modeloFestivo.getNota()+ "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modeloFestivo.getId() + "\"";
                out += "data-fecha=\"" + modeloFestivo.getFecha() + "\"";
                out += "data-nombre=\"" + modeloFestivo.getNota()+ "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloFestivo.getId() + "\"";                
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
