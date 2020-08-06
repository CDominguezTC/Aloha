package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloGrupo_consumo;
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
 * Esta clase permite controlar los eventos de Grupo Consumo
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorGrupo_consumo {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * grupo_consumo
     *
     * @param response
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 16/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloGrupo_consumo modeloGrupo_consumo = new ModeloGrupo_consumo();
        modeloGrupo_consumo.setCodigo(request.getParameter("codigo"));
        modeloGrupo_consumo.setNombre(request.getParameter("nombre"));
        modeloGrupo_consumo.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloGrupo_consumo);
        } else {
            modeloGrupo_consumo.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloGrupo_consumo);
            if (resultado == "1") {
                resultado = "4";
            }
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: grupo_consumo
     *
     * @param modeloGrupo_consumo
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @return String
     * @version: 16/05/2020
     */
    public String Insert(ModeloGrupo_consumo modeloGrupo_consumo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO grupo_consumo("
                        + "codigo, "
                        + "nombre, "
                        + "estado)"
                        + " VALUE (?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloGrupo_consumo.getCodigo());
                SQL.setString(2, modeloGrupo_consumo.getNombre());
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
                System.out.println("Error en la consulta SQL Insert en Controladorgrupo_consumo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorgrupo_consumo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:grupo_consumo
     *
     * @param modeloGrupo_consumo
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @return String
     * @version: 16/05/2020
     */
    public String Update(ModeloGrupo_consumo modeloGrupo_consumo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {

                if ("N".equals(modeloGrupo_consumo.getEstado())) {
                    SQL = con.prepareStatement("UPDATE grupo_consumo SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloGrupo_consumo.getEstado());
                    SQL.setInt(2, modeloGrupo_consumo.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE grupo_consumo SET "
                            + "codigo = ?, "
                            + "nombre = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloGrupo_consumo.getCodigo());
                    SQL.setString(2, modeloGrupo_consumo.getNombre());
                    SQL.setInt(3, modeloGrupo_consumo.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorgrupo_consumo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorgrupo_consumo" + e);
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
            ModeloGrupo_consumo modeloGrupo_consumo = new ModeloGrupo_consumo();
            modeloGrupo_consumo.setId(Integer.parseInt(request.getParameter("id")));
            modeloGrupo_consumo.setEstado("N");
            resultado = Update(modeloGrupo_consumo);
            if ("1".equals(resultado)) {
                resultado = "2";                
            }
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla grupo_consumo dependiendo de un ID
     *
     * @param Id
     * @author: Carlos Arturo Dominguez Diaz
     * @return String
     * @version: 16/05/2020
     */
    public ModeloGrupo_consumo getModelo(Integer Id) {
        ModeloGrupo_consumo modeloGrupo_consumo = new ModeloGrupo_consumo();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado"
                    + " FROM grupo_consumo"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                res.first();
                modeloGrupo_consumo.setId(res.getInt("id"));
                modeloGrupo_consumo.setCodigo(res.getString("codigo"));
                modeloGrupo_consumo.setNombre(res.getString("nombre"));
                modeloGrupo_consumo.setEstado(res.getString("estado"));
            } else {
                modeloGrupo_consumo.setId(0);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorgrupo_consumo" + e);
        }
        return modeloGrupo_consumo;
    }

    /**
     * Llena un Listado de la tabla grupo_consumo
     *
     * @return LinkendList
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @version: 16/05/2020
     */
    public LinkedList<ModeloGrupo_consumo> Read(String estado) throws SQLException {
        LinkedList<ModeloGrupo_consumo> ListaModeloGrupo_consumo = new LinkedList<ModeloGrupo_consumo>();
        con = conexion.abrirConexion();

        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado "
                    + "FROM grupo_consumo "
                    + "WHERE estado = ?");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloGrupo_consumo modeloGrupo_consumo = new ModeloGrupo_consumo();
                modeloGrupo_consumo.setId(res.getInt("id"));
                modeloGrupo_consumo.setCodigo(res.getString("codigo"));
                modeloGrupo_consumo.setNombre(res.getString("nombre"));
                modeloGrupo_consumo.setEstado(res.getString("estado"));
                ListaModeloGrupo_consumo.add(modeloGrupo_consumo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Read en Controladorgrupo_consumo" + e);
        }
        return ListaModeloGrupo_consumo;
    }

    /**
     * Permite listar la información de la tabla de Grupo Consumo
     *
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
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
            LinkedList<ModeloGrupo_consumo> listmoGrupo_consumos;
            listmoGrupo_consumos = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloGrupo_consumo modeloGrupo_consumo : listmoGrupo_consumos) {
                    out += "<option value=\"" + modeloGrupo_consumo.getId() + "\"> " + modeloGrupo_consumo.getNombre() + "</option>";
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
                for (ModeloGrupo_consumo modeloCentroCosto : listmoGrupo_consumos) {
                    out += "<tr>";
                    out += "<td>" + modeloCentroCosto.getCodigo() + "</td>";
                    out += "<td>" + modeloCentroCosto.getNombre() + "</td>";
                    out += "<td class=\"text-center\">";
                    // Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                    out += "data-id=\"" + modeloCentroCosto.getId() + "\"";
                    out += "data-codigo=\"" + modeloCentroCosto.getCodigo() + "\"";
                    out += "data-nombre=\"" + modeloCentroCosto.getNombre() + "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                    //Boton Eliminar
                    out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                    out += "data-id=\"" + modeloCentroCosto.getId() + "\"";
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
