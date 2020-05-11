package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloGrupoConsumo;
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
 * Esta clase permite controlar los eventos de Grupo Consumo
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorGrupoConsumo {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd Grupo
     * Consumo
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Insert(HttpServletRequest request) {
        if ("".equals(request.getParameter("id"))) {
            ModeloGrupoConsumo modelo = new ModeloGrupoConsumo(
                    0,
                    request.getParameter("codigo"),
                    request.getParameter("descripcion")
            );
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("INSERT INTO `grupoconsumo`("
                            + "`codigo`,"
                            + "`nombre`) "
                            + "VALUE (?,?);", SQL.RETURN_GENERATED_KEYS);
                    SQL.setString(1, modelo.getCodigo());
                    SQL.setString(2, modelo.getDescripcion());
                    if (SQL.executeUpdate() > 0) {
                        ControladorAuditoria auditoria = new ControladorAuditoria();
                        try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int i = (int) generatedKeys.getLong(1);
                                auditoria.Insert("insertar", "grupo_consumo", request.getParameter("nombreU"), i, "Se inserto el registro.");
                            }
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                resultado = "-3";
            }
        } else {
            ModeloGrupoConsumo modelo = new ModeloGrupoConsumo(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("codigo"),
                    request.getParameter("descripcion")
            );
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("UPDATE `grupoconsumo`  SET "
                            + "`codigo` = ?, "
                            + "`nombre` = ? "
                            + "WHERE `Id` = ?;");
                    SQL.setString(1, modelo.getCodigo());
                    SQL.setString(2, modelo.getDescripcion());
                    SQL.setInt(3, modelo.getId());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    /**
     * Permite la eliminar un dato en la tabla de Grupo Consumo
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Delete(HttpServletRequest request) {
        if (!"".equals(request.getParameter("id"))) {
            String idtmp = request.getParameter("id");
            ModeloGrupoConsumo modelo = new ModeloGrupoConsumo();
            modelo.setId(Integer.parseInt(request.getParameter("id")));

            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("DELETE FROM `grupoconsumo` "
                            + "WHERE `Id` = ?;");
                    SQL.setInt(1, modelo.getId());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "2";
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    resultado = "-2";
                }
                SQL.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    /**
     * Permite listar la información de la tabla de Grupo Consumo Metodo Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    public LinkedList<ModeloGrupoConsumo> Read() {
        LinkedList<ModeloGrupoConsumo> listModeloGrupoConsumos = new LinkedList<ModeloGrupoConsumo>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`, "
                    + "`codigo`, "
                    + "`nombre` "
                    + "FROM `grupoconsumo`;");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloGrupoConsumo modelo = new ModeloGrupoConsumo();
                modelo.setId(res.getInt("Id"));
                modelo.setCodigo(res.getString("codigo"));
                modelo.setDescripcion(res.getString("nombre"));
                listModeloGrupoConsumos.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listModeloGrupoConsumos;
    }

    /**
     * Permite listar la información de la tabla de Grupo Consumo
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
            LinkedList<ModeloGrupoConsumo> listmoCentroCosto;
            ControladorGrupoConsumo controladorCentroCosto = new ControladorGrupoConsumo();
            listmoCentroCosto = controladorCentroCosto.Read();
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
            for (ModeloGrupoConsumo modeloCentroCosto : listmoCentroCosto) {
                out += "<tr>";
                out += "<td>" + modeloCentroCosto.getCodigo() + "</td>";
                out += "<td>" + modeloCentroCosto.getDescripcion() + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modeloCentroCosto.getId() + "\"";
                out += "data-codigo=\"" + modeloCentroCosto.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloCentroCosto.getDescripcion() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloCentroCosto.getId() + "\"";
                out += "data-codigo=\"" + modeloCentroCosto.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloCentroCosto.getDescripcion() + "\"";
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

    /**
     * Permite listar la información de la tabla de Grupo Consumo identificadno
     * el ID
     *
     * @author: Carlos A Dominguez D
     * @param Id
     * @return ModeloEmpresa
     * @version: 07/05/2020
     */
    ModeloGrupoConsumo getModelo(int Id) {
        ModeloGrupoConsumo modelo = new ModeloGrupoConsumo();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`, "
                    + "`codigo`, "
                    + "`nombre` "
                    + " FROM `grupoconsumo`"
                    + " WHERE Id = ?;");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modelo.setId(res.getInt("Id"));
                modelo.setCodigo(res.getString("codigo"));
                modelo.setDescripcion(res.getString("nombre"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return modelo;
    }
}
