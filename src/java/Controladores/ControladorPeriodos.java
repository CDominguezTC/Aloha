package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloPeriodo;
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
 * Esta clase permite controlar los Periodos contrine Insert - Update, Delete,
 * Read
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorPeriodos {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * periodo
     *
     * @author: Carlos A Dominguez Diaz
     * @param request
     * @return String
     * @version: 25/07/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloPeriodo modeloPeriodo = new ModeloPeriodo();
        modeloPeriodo.setCodigo(request.getParameter("codigo"));
        modeloPeriodo.setNombre(request.getParameter("nombre"));
        modeloPeriodo.setFecha_inicio(request.getParameter("fechaInicio"));
        modeloPeriodo.setFecha_fin(request.getParameter("fechaFin"));
        modeloPeriodo.setObservacion(request.getParameter("observacion"));
        //modeloPeriodo.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloPeriodo);
        } else {
            modeloPeriodo.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloPeriodo);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: periodo
     *
     * @author: Carlos A Dominguez Diaz
     * @param Modelo
     * @return String
     * @version: 25/07/2020
     */
    public String Insert(ModeloPeriodo modeloPeriodo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO periodo("
                        + "codigo, "
                        + "nombre, "
                        + "fecha_inicio, "
                        + "fecha_fin, "
                        + "observacion, "
                        + "estado)"
                        + " VALUE (?,?,?,?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloPeriodo.getCodigo());
                SQL.setString(2, modeloPeriodo.getNombre());
                SQL.setString(3, modeloPeriodo.getFecha_inicio());
                SQL.setString(4, modeloPeriodo.getFecha_fin());
                SQL.setString(5, modeloPeriodo.getObservacion());
                SQL.setString(6, "S");
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.", "", "");
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladorperiodo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorperiodo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:periodo
     *
     * @author: Carlos A Dominguez Diaz
     * @param request
     * @return String
     * @version: 25/07/2020
     */
    public String Update(ModeloPeriodo modeloPeriodo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloPeriodo.getEstado())) {
                    SQL = con.prepareStatement("UPDATE periodo SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloPeriodo.getEstado());
                    SQL.setInt(2, modeloPeriodo.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE periodo SET "
                            + "codigo = ?, "
                            + "nombre = ?, "
                            + "fecha_inicio = ?, "
                            + "fecha_fin = ?, "
                            + "observacion = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloPeriodo.getCodigo());
                    SQL.setString(2, modeloPeriodo.getNombre());
                    SQL.setString(3, modeloPeriodo.getFecha_inicio());
                    SQL.setString(4, modeloPeriodo.getFecha_fin());
                    SQL.setString(5, modeloPeriodo.getObservacion());
                    SQL.setInt(6, modeloPeriodo.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "4";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorperiodo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorperiodo" + e);
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
     * @version: 25/07/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloPeriodo modeloPeriodo = new ModeloPeriodo();
            modeloPeriodo.setId(Integer.parseInt(request.getParameter("id")));
            modeloPeriodo.setEstado("N");
            resultado = Update(modeloPeriodo);
            if (resultado.equals("4")) {
                resultado = "2";
            }
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla periodo dependiendo de un ID
     *
     * @author: Carlos A Dominguez Diaz
     * @param request
     * @return String
     * @version: 25/07/2020
     */
    public ModeloPeriodo getModelo(Integer Id) {
        ModeloPeriodo modeloPeriodo = new ModeloPeriodo();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "fecha_inicio, "
                    + "fecha_fin, "
                    + "observacion, "
                    + "estado"
                    + " FROM periodo"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloPeriodo.setId(res.getInt("id"));
                modeloPeriodo.setCodigo(res.getString("codigo"));
                modeloPeriodo.setNombre(res.getString("nombre"));
                modeloPeriodo.setFecha_inicio(res.getString("fecha_inicio"));
                modeloPeriodo.setFecha_fin(res.getString("fecha_fin"));
                modeloPeriodo.setObservacion(res.getString("observacion"));
                modeloPeriodo.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorperiodo" + e);
        }
        return modeloPeriodo;
    }

    /**
     * Llena un Listado de la tabla periodo
     *
     * @author: Carlos A Dominguez Diaz
     * @param vacio
     * @return LinkedList<ModeloPeriodo>
     * @version: 25/07/2020
     */
    public LinkedList<ModeloPeriodo> Read(String estado) throws SQLException {
        LinkedList<ModeloPeriodo> ListaModeloPeriodo = new LinkedList<ModeloPeriodo>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "fecha_inicio, "
                    + "fecha_fin, "
                    + "observacion, "
                    + "estado"
                    + " FROM periodo"
                    + " WHERE estado = ? ");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloPeriodo modeloPeriodo = new ModeloPeriodo();
                modeloPeriodo.setId(res.getInt("id"));
                modeloPeriodo.setCodigo(res.getString("codigo"));
                modeloPeriodo.setNombre(res.getString("nombre"));
                modeloPeriodo.setFecha_inicio(res.getString("fecha_inicio"));
                modeloPeriodo.setFecha_fin(res.getString("fecha_fin"));
                modeloPeriodo.setObservacion(res.getString("observacion"));
                modeloPeriodo.setEstado(res.getString("estado"));
                ListaModeloPeriodo.add(modeloPeriodo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorperiodo" + e);
        }
        return ListaModeloPeriodo;
    }

    /**
     * Permite listar la información de la tabla de Peridos
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @param response
     * @return String
     * @version: 07/05/2020
     */
    public String ReadPeriodos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String out = null;
        try {
            LinkedList<ModeloPeriodo> listmoPeriodos;
            ControladorPeriodos controladorPeriodos = new ControladorPeriodos();
            listmoPeriodos = controladorPeriodos.Read("S");
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloPeriodo modeloPeriodo : listmoPeriodos) {
                    out += "<option value=\"" + modeloPeriodo.getId() + "\" data-fini=\"" + modeloPeriodo.getFecha_inicio()+ "\" data-ffin=\"" + modeloPeriodo.getFecha_fin()+ "\"> " + modeloPeriodo.getNombre() + "</option>";
                }
            } else {
                out = "";
                out += "<thead>";
                out += "<tr>";
                out += "<th>Id</th>";
                out += "<th>Codigo</th>";
                out += "<th>Nombre</th>";
                out += "<th>Fecha Inicio</th>";
                out += "<th>Fecha Fin</th>";
                out += "<th>Observaciones</th>";
                out += "<th>Opciones</th>";
                out += "</tr>";
                out += "</thead>";
                out += "<tbody>";
                for (ModeloPeriodo modeloPeriodos : listmoPeriodos) {
                    out += "<tr>";
                    out += "<td>" + modeloPeriodos.getId() + "</td>";
                    out += "<td>" + modeloPeriodos.getCodigo() + "</td>";
                    out += "<td>" + modeloPeriodos.getNombre() + "</td>";
                    out += "<td>" + modeloPeriodos.getFecha_inicio() + "</td>";
                    out += "<td>" + modeloPeriodos.getFecha_fin() + "</td>";
                    out += "<td>" + modeloPeriodos.getObservacion() + "</td>";
                    out += "<td class=\"text-center\">";
                    // Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                    out += "data-id=\"" + modeloPeriodos.getId() + "\"";
                    out += "data-codigo=\"" + modeloPeriodos.getCodigo() + "\"";
                    out += "data-nombre=\"" + modeloPeriodos.getNombre() + "\"";
                    out += "data-fechainicio=\"" + modeloPeriodos.getFecha_inicio() + "\"";
                    out += "data-fechafin=\"" + modeloPeriodos.getFecha_fin() + "\"";
                    out += "data-observacion=\"" + modeloPeriodos.getObservacion() + "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                    //Boton Eliminar
                    out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                    out += "data-id=\"" + modeloPeriodos.getId() + "\"";
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
}
