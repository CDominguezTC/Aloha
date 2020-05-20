package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloGrupoTurnos;
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
 * Esta clase permite controlar los eventos de Grupo Turnos contrine Insert -
 * Update, Delete, Read
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorGrupoTurnos {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd Grupo
     * Turnos
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Insert(HttpServletRequest request) {
        if ("".equals(request.getParameter("id"))) {
            ModeloGrupoTurnos modelo = new ModeloGrupoTurnos(
                    0,
                    request.getParameter("codigo"),
                    request.getParameter("nombre")
            );
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("INSERT INTO grupohorario(codigo,descripcion) VALUE (?,?)");
                    SQL.setString(1, modelo.getCodigo());
                    SQL.setString(2, modelo.getDescripcion());
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
        } else {
            ModeloGrupoTurnos modelo = new ModeloGrupoTurnos(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("codigo"),
                    request.getParameter("nombre")
            );
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("UPDATE grupohorario SET codigo = ?, descripcion = ? WHERE id = ?;");
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
     * Esta metodo permintem eliminar un dato de la base de datos de la tabla
     * Grupo Turnos
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return string
     * @version: 06-08-2020
     *
     */
    public String Delete(HttpServletRequest request) {
        if (!"".equals(request.getParameter("id"))) {
            String idtmp = request.getParameter("id");
            ModeloGrupoTurnos modelo = new ModeloGrupoTurnos();
            modelo.setId(Integer.parseInt(request.getParameter("id")));
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("DELETE FROM `grupohorario` "
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
     * Permite listar la información de la tabla de Areas Metodo Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    private LinkedList<ModeloGrupoTurnos> Read() {
        LinkedList<ModeloGrupoTurnos> listModeloGrupoTurnos = new LinkedList<ModeloGrupoTurnos>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`, "
                    + "`codigo`, "
                    + "`descripcion` "
                    + "FROM `grupohorario`;");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloGrupoTurnos modelo = new ModeloGrupoTurnos();
                modelo.setId(res.getInt("id"));
                modelo.setCodigo(res.getString("codigo"));
                modelo.setDescripcion(res.getString("descripcion"));
                listModeloGrupoTurnos.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listModeloGrupoTurnos;
    }

    /**
     * Permite listar la información de la tabla de Areas
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
            LinkedList<ModeloGrupoTurnos> listmoGrupoTurnos;
            listmoGrupoTurnos = Read();
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloGrupoTurnos modeloGrupoTurnos : listmoGrupoTurnos) {
                    out += "<option value=\"" + modeloGrupoTurnos.getId() + "\"> " + modeloGrupoTurnos.getDescripcion() + "</option>";
                }
            } else {

                out = "";
                out += "<thead>";
                out += "<tr>";
                out += "<th>Id</th>";
                out += "<th>Codigo</th>";
                out += "<th>Nombre</th>";
                out += "<th>Opcion</th>";
                out += "</tr>";
                out += "</thead>";
                out += "<tbody>";
                for (ModeloGrupoTurnos modeloGrupoTurnos : listmoGrupoTurnos) {
                    out += "<tr>";
                    out += "<td>" + modeloGrupoTurnos.getId() + "</td>";
                    out += "<td>" + modeloGrupoTurnos.getCodigo() + "</td>";
                    out += "<td>" + modeloGrupoTurnos.getDescripcion() + "</td>";
                    out += "<td class=\"text-center\">";
                    // Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                    out += "data-id=\"" + modeloGrupoTurnos.getId() + "\"";
                    out += "data-codigo=\"" + modeloGrupoTurnos.getCodigo() + "\"";
                    out += "data-nombre=\"" + modeloGrupoTurnos.getDescripcion() + "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                    //Boton Eliminar
                    out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                    out += "data-id=\"" + modeloGrupoTurnos.getId() + "\"";
                    out += "data-codigo=\"" + modeloGrupoTurnos.getCodigo() + "\"";
                    out += "data-nombre=\"" + modeloGrupoTurnos.getDescripcion() + "\"";
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

    /**
     * Permite listar la información de la tabla de Grupo Turnos identificadno
     * el ID
     *
     * @author: Carlos A Dominguez D
     * @param Id
     * @return ModeloEmpresa
     * @version: 11/05/2020
     */
    ModeloGrupoTurnos getModelo(int Id) {
        ModeloGrupoTurnos modelo = new ModeloGrupoTurnos();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`, "
                    + "`codigo`, "
                    + "`descripcion` "
                    + "FROM `grupo_horario`"
                    + " WHERE id = ?;");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                res.first();
                modelo.setId(res.getInt("Id"));
                modelo.setCodigo(res.getString("codigo"));
                modelo.setDescripcion(res.getString("descripcion"));

            } else {
                modelo.setId(0);
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
