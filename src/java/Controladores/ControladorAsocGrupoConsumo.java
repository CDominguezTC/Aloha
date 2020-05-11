package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloAsocGrupoConsumo;
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
 * Esta clase permite controlar los eventos de AsocGrupoConsumo
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorAsocGrupoConsumo {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    ControladorGrupoConsumo controladorGrupoConsumo = new ControladorGrupoConsumo();
    ControladorHorarioConsumo controladorHorarioConsumo = new ControladorHorarioConsumo();

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd
     * Asiciacion Grupo Consumo
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Insert(HttpServletRequest request) {

        if ("".equals(request.getParameter("id"))) {

            ModeloAsocGrupoConsumo modelo = new ModeloAsocGrupoConsumo();
            modelo.setId(0);
            modelo.setModeloGrupoConsumo(controladorGrupoConsumo.getModelo(Integer.parseInt(request.getParameter("grupoconsumo"))));
            modelo.setModeloHorarioConsumo(controladorHorarioConsumo.getModelo(Integer.parseInt(request.getParameter("horario"))));
            modelo.setCosto(Integer.parseInt(request.getParameter("valor")));
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("INSERT INTO `asocgrupohorario`("
                            + "`grupoconsumoid`,"
                            + "`horarioconsumoid`,"
                            + "`costo`) "
                            + "VALUE (?,?,?);", SQL.RETURN_GENERATED_KEYS);
                    SQL.setInt(1, modelo.getModeloGrupoConsumo().getId());
                    SQL.setInt(2, modelo.getModeloHorarioConsumo().getId());
                    SQL.setInt(3, modelo.getCosto());
                    if (SQL.executeUpdate() > 0) {
                        ControladorAuditoria auditoria = new ControladorAuditoria();
                        try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int i = (int) generatedKeys.getLong(1);
                                auditoria.Insert("insertar", "asocgrupohorario", request.getParameter("nombreU"), i, "Se inserto el registro.");
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
            ModeloAsocGrupoConsumo modelo = new ModeloAsocGrupoConsumo();
            modelo.setId(Integer.parseInt(request.getParameter("id")));
            modelo.setModeloGrupoConsumo(controladorGrupoConsumo.getModelo(Integer.parseInt(request.getParameter("grupoconsumo"))));
            modelo.setModeloHorarioConsumo(controladorHorarioConsumo.getModelo(Integer.parseInt(request.getParameter("horario"))));
            modelo.setCosto(Integer.parseInt(request.getParameter("valor")));
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("UPDATE `asocgrupohorario`  SET "
                            + "`grupoconsumoid` = ?, "
                            + "`horarioconsumoid` = ?, "
                            + "`costo` = ? "
                            + "WHERE `id` = ?;");
                    SQL.setInt(1, modelo.getModeloGrupoConsumo().getId());
                    SQL.setInt(2, modelo.getModeloHorarioConsumo().getId());
                    SQL.setInt(3, modelo.getCosto());
                    SQL.setInt(4, modelo.getId());
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
     * Permite la eliminar un dato en la tabla de Asociacion Grupo Consumo
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Delete(HttpServletRequest request) {
        if (!"".equals(request.getParameter("id"))) {
            String idtmp = request.getParameter("id");
            ModeloAsocGrupoConsumo modelo = new ModeloAsocGrupoConsumo();
            modelo.setId(Integer.parseInt(request.getParameter("id")));

            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("DELETE FROM `asocgrupohorario` "
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
     * Permite listar la información de la tabla de Asociacion Grupo Consumo
     * Metodo Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    private LinkedList<ModeloAsocGrupoConsumo> Read() {
        LinkedList<ModeloAsocGrupoConsumo> listModeloAsocGrupoConsumos = new LinkedList<ModeloAsocGrupoConsumo>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`grupoconsumoid`,"
                    + "`horarioconsumoid`,"
                    + "`costo` "
                    + "FROM `asocgrupohorario`;");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloAsocGrupoConsumo modelo = new ModeloAsocGrupoConsumo();
                modelo.setId(res.getInt("id"));
                modelo.setModeloGrupoConsumo(controladorGrupoConsumo.getModelo(res.getInt("grupoconsumoid")));
                modelo.setModeloHorarioConsumo(controladorHorarioConsumo.getModelo(res.getInt("horarioconsumoid")));
                modelo.setCosto(res.getInt("costo"));
                listModeloAsocGrupoConsumos.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listModeloAsocGrupoConsumos;
    }

    /**
     * Permite listar la información de la tabla de Asociacion Grupo Consumo
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String out = null;
        try {
            LinkedList<ModeloAsocGrupoConsumo> listModeloAsocGrupoConsumos;
            listModeloAsocGrupoConsumos = Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Grupo Consumo</th>";
            out += "<th>Horario</th>";
            out += "<th>Valor</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloAsocGrupoConsumo modeloAsocGrupoConsumo : listModeloAsocGrupoConsumos) {
                out += "<tr>";
                out += "<td>" + modeloAsocGrupoConsumo.getModeloGrupoConsumo().getDescripcion() + "</td>";
                out += "<td>" + modeloAsocGrupoConsumo.getModeloHorarioConsumo().getNombre() + "</td>";
                out += "<td>" + modeloAsocGrupoConsumo.getCosto() + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modeloAsocGrupoConsumo.getId() + "\"";
                out += "data-idgrupoconsumo=\"" + modeloAsocGrupoConsumo.getModeloGrupoConsumo().getId() + "\"";
                out += "data-idhorarioconsumo=\"" + modeloAsocGrupoConsumo.getModeloHorarioConsumo().getId() + "\"";
                out += "data-valor=\"" + modeloAsocGrupoConsumo.getCosto() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloAsocGrupoConsumo.getId() + "\"";
                out += "data-idgrupoconsumo=\"" + modeloAsocGrupoConsumo.getModeloGrupoConsumo().getId() + "\"";
                out += "data-idhorarioconsumo=\"" + modeloAsocGrupoConsumo.getModeloHorarioConsumo().getId() + "\"";
                out += "data-valor=\"" + modeloAsocGrupoConsumo.getCosto() + "\"";
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
