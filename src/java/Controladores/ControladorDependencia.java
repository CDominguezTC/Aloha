package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloDependencia;
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
 * Esta clase permite controlar los eventos de Dependencias
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorDependencia {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * dependencia
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloDependencia modeloDependencia = new ModeloDependencia();
        modeloDependencia.setCodigo(request.getParameter("codigo"));
        modeloDependencia.setNombre(request.getParameter("nombre"));
        modeloDependencia.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloDependencia);
        } else {
            modeloDependencia.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloDependencia);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: dependencia
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param Modelo
     * @return String
     * @version: 15/05/2020
     */
    public String Insert(ModeloDependencia modeloDependencia) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO dependencia("
                        + "codigo, "
                        + "nombre, "
                        + "estado)"
                        + " VALUE (?,?,?);", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloDependencia.getCodigo());
                SQL.setString(2, modeloDependencia.getNombre());
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
                System.out.println("Error en la consulta SQL Insert en Controladordependencia" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladordependencia" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:dependencia
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Update(ModeloDependencia modeloDependencia) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {

                if ("N".equals(modeloDependencia.getEstado())) {
                    SQL = con.prepareStatement("UPDATE dependencia SET "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloDependencia.getEstado());
                    SQL.setInt(2, modeloDependencia.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE dependencia SET "
                            + "codigo = ?, "
                            + "nombre = ? "
                            + "WHERE id = ? ");
                    SQL.setString(1, modeloDependencia.getCodigo());
                    SQL.setString(2, modeloDependencia.getNombre());
                    SQL.setInt(3, modeloDependencia.getId());
                }

                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladordependencia" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladordependencia" + e);
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
            ModeloDependencia modeloDependencia = new ModeloDependencia();
            modeloDependencia.setId(Integer.parseInt(request.getParameter("id")));
            modeloDependencia.setEstado("N");
            resultado = Update(modeloDependencia);
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla dependencia dependiendo de un ID
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public ModeloDependencia getModelo(Integer Id) {
        ModeloDependencia modeloDependencia = new ModeloDependencia();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado"
                    + " FROM dependencia"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                res.first();
                modeloDependencia.setId(res.getInt("id"));
                modeloDependencia.setCodigo(res.getString("codigo"));
                modeloDependencia.setNombre(res.getString("nombre"));
                modeloDependencia.setEstado(res.getString("estado"));
            } else {
                modeloDependencia.setId(0);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladordependencia" + e);
        }
        return modeloDependencia;
    }

    /**
     * Llena un Listado de la tabla dependencia
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param vacio
     * @return LinkedList<ModeloDependencia>
     * @version: 15/05/2020
     */
    public LinkedList<ModeloDependencia> Read(String estado) throws SQLException {
        LinkedList<ModeloDependencia> ListaModeloDependencia = new LinkedList<ModeloDependencia>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado "
                    + "FROM dependencia "
                    + "WHERE estado = ?");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloDependencia modeloDependencia = new ModeloDependencia();
                modeloDependencia.setId(res.getInt("id"));
                modeloDependencia.setCodigo(res.getString("codigo"));
                modeloDependencia.setNombre(res.getString("nombre"));
                modeloDependencia.setEstado(res.getString("estado"));
                ListaModeloDependencia.add(modeloDependencia);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladordependencia" + e);
        }
        return ListaModeloDependencia;
    }

    /**
     * Permite listar la información de la tabla de Dependencias
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
            LinkedList<ModeloDependencia> listmoDependencias;
            listmoDependencias = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloDependencia modeloDependencia : listmoDependencias) {
                    out += "<option value=\"" + modeloDependencia.getId() + "\"> " + modeloDependencia.getNombre() + "</option>";
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
                for (ModeloDependencia modeloDependencia : listmoDependencias) {
                    out += "<tr>";
                    out += "<td>" + modeloDependencia.getCodigo() + "</td>";
                    out += "<td>" + modeloDependencia.getNombre() + "</td>";
                    out += "<td class=\"text-center\">";
                    // Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                    out += "data-id=\"" + modeloDependencia.getId() + "\"";
                    out += "data-codigo=\"" + modeloDependencia.getCodigo() + "\"";
                    out += "data-nombre=\"" + modeloDependencia.getNombre() + "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                    //Boton Eliminar
                    out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                    out += "data-id=\"" + modeloDependencia.getId() + "\"";
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
