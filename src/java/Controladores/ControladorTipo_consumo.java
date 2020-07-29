package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloTipo_consumo;
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
 * Esta clase permite controlar los eventos de Grupo Turnos contrine Insert -
 * Update, Delete, Read
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorTipo_consumo {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * tipo_consumo
     *
     * @param response
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 16/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloTipo_consumo modeloTipo_consumo = new ModeloTipo_consumo();
        modeloTipo_consumo.setNombre(request.getParameter("nombre"));
        modeloTipo_consumo.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        modeloTipo_consumo.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloTipo_consumo);
        } else {
            modeloTipo_consumo.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloTipo_consumo);
            if ("1".equals(resultado)) {
                resultado = "4";
            }
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: tipo_consumo
     *
     * @param modeloTipo_consumo
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @return String
     * @version: 16/05/2020
     */
    public String Insert(ModeloTipo_consumo modeloTipo_consumo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO tipo_consumo("
                        + "nombre, "
                        + "cantidad, "
                        + "estado)"
                        + " VALUE (?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloTipo_consumo.getNombre());
                SQL.setInt(2, modeloTipo_consumo.getCantidad());
                SQL.setString(3, "S");
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.");
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladortipo_consumo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladortipo_consumo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:tipo_consumo
     *
     * @param modeloTipo_consumo
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @return String
     * @version: 16/05/2020
     */
    public String Update(ModeloTipo_consumo modeloTipo_consumo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloTipo_consumo.getEstado())) {
                    SQL = con.prepareStatement("UPDATE tipo_consumo SET "
                            + "estado = ? "
                            + "WHERE id = ? ");
                    SQL.setString(1, modeloTipo_consumo.getEstado());
                    SQL.setInt(2, modeloTipo_consumo.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE tipo_consumo SET "
                            + "nombre = ?, "
                            + "cantidad = ? "
                            + "WHERE id = ? ");
                    SQL.setString(1, modeloTipo_consumo.getNombre());
                    SQL.setInt(2, modeloTipo_consumo.getCantidad());
                    SQL.setInt(3, modeloTipo_consumo.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladortipo_consumo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladortipo_consumo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * llena un modelo que viene con datos de un request para ser Eliminado
     *
     * @param response
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 16/05/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloTipo_consumo modeloTipo_consumo = new ModeloTipo_consumo();
            modeloTipo_consumo.setId(Integer.parseInt(request.getParameter("id")));
            modeloTipo_consumo.setEstado("N");
            resultado = Update(modeloTipo_consumo);
            if ("1".equals(resultado)) {
                resultado = "2";
            }

        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla tipo_consumo dependiendo de un ID
     *
     * @param Id
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @return String
     * @version: 16/05/2020
     */
    public ModeloTipo_consumo getModelo(Integer Id) throws SQLException {
        ModeloTipo_consumo modeloTipo_consumo = new ModeloTipo_consumo();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "nombre, "
                    + "cantidad, "
                    + "estado "
                    + "FROM tipo_consumo "
                    + "WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloTipo_consumo.setId(res.getInt("id"));
                modeloTipo_consumo.setNombre(res.getString("nombre"));
                modeloTipo_consumo.setCantidad(res.getInt("cantidad"));
                modeloTipo_consumo.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladortipo_consumo" + e);
        }
        return modeloTipo_consumo;
    }

    /**
     * Llena un Listado de la tabla tipo_consumo
     *
     * @return
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @version: 16/05/2020
     */
    public LinkedList<ModeloTipo_consumo> Read(String estado) throws SQLException {
        LinkedList<ModeloTipo_consumo> ListaModeloTipo_consumo = new LinkedList<ModeloTipo_consumo>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "nombre, "
                    + "cantidad, "
                    + "estado "
                    + "FROM tipo_consumo "
                    + "WHERE estado = ?");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloTipo_consumo modeloTipo_consumo = new ModeloTipo_consumo();
                modeloTipo_consumo.setId(res.getInt("id"));
                modeloTipo_consumo.setNombre(res.getString("nombre"));
                modeloTipo_consumo.setCantidad(res.getInt("cantidad"));
                modeloTipo_consumo.setEstado(res.getString("estado"));
                ListaModeloTipo_consumo.add(modeloTipo_consumo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladortipo_consumo" + e);
        }
        return ListaModeloTipo_consumo;
    }

    /**
     * Permite listar la información de la tabla de Tipo Consumo
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
            LinkedList<ModeloTipo_consumo> listmoTipoConsumo;
            listmoTipoConsumo = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloTipo_consumo modeloTipo_consumo : listmoTipoConsumo) {
                    out += "<option value=\"" + modeloTipo_consumo.getId() + "\"> " + modeloTipo_consumo.getNombre() + "</option>";
                }
            } else {
                out = "";
                out += "<thead>";
                out += "<tr>";
                out += "<th>Nomnre</th>";
                out += "<th>Cantidad</th>";
                out += "<th>Opcion</th>";
                out += "</tr>";
                out += "</thead>";
                out += "<tbody>";
                for (ModeloTipo_consumo modeloTipoConsumo : listmoTipoConsumo) {
                    out += "<tr>";
                    out += "<td>" + modeloTipoConsumo.getNombre() + "</td>";
                    out += "<td>" + modeloTipoConsumo.getCantidad() + "</td>";
                    out += "<td class=\"text-center\">";
                    // Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                    out += "data-id=\"" + modeloTipoConsumo.getId() + "\"";
                    out += "data-nombre=\"" + modeloTipoConsumo.getNombre() + "\"";
                    out += "data-cantidad=\"" + modeloTipoConsumo.getCantidad() + "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                    //Boton Eliminar
                    out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                    out += "data-id=\"" + modeloTipoConsumo.getId() + "\"";
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
