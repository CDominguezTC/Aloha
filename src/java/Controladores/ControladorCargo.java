package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloCargo;
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
 * Esta clase permite controlar los eventos de Cargos
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorCargo {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * cargo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloCargo modeloCargo = new ModeloCargo();
        modeloCargo.setCodigo(request.getParameter("codigo"));
        modeloCargo.setNombre(request.getParameter("nombre"));
        modeloCargo.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloCargo);
        } else {
            modeloCargo.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloCargo);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: cargo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param Modelo
     * @return String
     * @version: 15/05/2020
     */
    public String Insert(ModeloCargo modeloCargo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO cargo("
                        + "codigo, "
                        + "nombre, "
                        + "estado)"
                        + " VALUE (?,?,?);", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloCargo.getCodigo());
                SQL.setString(2, modeloCargo.getNombre());
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
                System.out.println("Error en la consulta SQL Insert en Controladorcargo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorcargo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:cargo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Update(ModeloCargo modeloCargo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloCargo.getEstado())) {
                    SQL = con.prepareStatement("UPDATE cargo SET "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloCargo.getEstado());
                    SQL.setInt(2, modeloCargo.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE cargo SET "
                            + "codigo = ?, "
                            + "nombre = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloCargo.getCodigo());
                    SQL.setString(2, modeloCargo.getNombre());
                    SQL.setInt(3, modeloCargo.getId());
                }

                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorcargo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorcargo" + e);
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
            ModeloCargo modeloCargo = new ModeloCargo();
            modeloCargo.setId(Integer.parseInt(request.getParameter("id")));
            modeloCargo.setEstado("N");
            resultado = Update(modeloCargo);
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla cargo dependiendo de un ID
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public ModeloCargo getModelo(Integer Id) {
        ModeloCargo modeloCargo = new ModeloCargo();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado"
                    + " FROM cargo"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                res.first();
                modeloCargo.setId(res.getInt("id"));
                modeloCargo.setCodigo(res.getString("codigo"));
                modeloCargo.setNombre(res.getString("nombre"));
                modeloCargo.setEstado(res.getString("estado"));
            } else {
                modeloCargo.setId(0);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorcargo" + e);
        }
        return modeloCargo;
    }

    /**
     * Llena un Listado de la tabla cargo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param vacio
     * @return LinkedList<ModeloCargo>
     * @version: 15/05/2020
     */
    public LinkedList<ModeloCargo> Read(String estado) throws SQLException {
        LinkedList<ModeloCargo> ListaModeloCargo = new LinkedList<ModeloCargo>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado "
                    + "FROM cargo "
                    + "WHERE estado = ?");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloCargo modeloCargo = new ModeloCargo();
                modeloCargo.setId(res.getInt("id"));
                modeloCargo.setCodigo(res.getString("codigo"));
                modeloCargo.setNombre(res.getString("nombre"));
                modeloCargo.setEstado(res.getString("estado"));
                ListaModeloCargo.add(modeloCargo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorcargo" + e);
        }
        return ListaModeloCargo;
    }

    /**
     * Llena un Listado de la tabla cargo en una cadena con caracteristicas HTML
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
            LinkedList<ModeloCargo> listmoCargos;
            listmoCargos = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloCargo modeloCargo : listmoCargos) {
                    out += "<option value=\"" + modeloCargo.getId() + "\"> " + modeloCargo.getNombre() + "</option>";
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
                for (ModeloCargo modeloCargos : listmoCargos) {
                    out += "<tr>";
                    out += "<td>" + modeloCargos.getCodigo() + "</td>";
                    out += "<td>" + modeloCargos.getNombre() + "</td>";
                    out += "<td class=\"text-center\">";
                    // Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\" data-toggle=\"modal\" data-target=\"#ModalFormulario\"data-whatever=\"@getbootstrap\"";
                    out += "data-id=\"" + modeloCargos.getId() + "\"";
                    out += "data-codigo=\"" + modeloCargos.getCodigo() + "\"";
                    out += "data-nombre=\"" + modeloCargos.getNombre() + "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                    //Boton Eliminar
                    out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                    out += "data-id=\"" + modeloCargos.getId() + "\"";
                    out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                    out += "</td>";
                    out += "</tr>";
                }
                out += "</tbody>";
            }
//            PrintWriter pw = response.getWriter();
//            pw.write(out);
//            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
        } catch (Exception e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }

}
