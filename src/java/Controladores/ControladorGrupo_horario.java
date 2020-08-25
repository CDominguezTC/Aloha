package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloGrupo_horario;
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
public class ControladorGrupo_horario {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * grupo_horario
     *
     * @author: Carlos A Dominguez Diaz
     * @param request
     * @return String
     * @version: 25/06/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloGrupo_horario modeloGrupo_horario = new ModeloGrupo_horario();
        modeloGrupo_horario.setCodigo(request.getParameter("codigo"));
        modeloGrupo_horario.setNombre(request.getParameter("nombre"));
        modeloGrupo_horario.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloGrupo_horario);
        } else {
            modeloGrupo_horario.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloGrupo_horario);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: grupo_horario
     *
     * @author: Carlos A Dominguez Diaz
     * @param Modelo
     * @return String
     * @version: 25/06/2020
     */
    public String Insert(ModeloGrupo_horario modeloGrupo_horario) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO grupo_horario("
                        + "codigo, "
                        + "nombre, "
                        + "estado)"
                        + " VALUE (?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloGrupo_horario.getCodigo());
                SQL.setString(2, modeloGrupo_horario.getNombre());
                SQL.setString(3, "S");
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "grupo_horario", user, i, "Se inserto el registro.", "", "");
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladorgrupo_horario" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorgrupo_horario" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:grupo_horario
     *
     * @author: Carlos A Dominguez Diaz
     * @param request
     * @return String
     * @version: 25/06/2020
     */
    public String Update(ModeloGrupo_horario modeloGrupo_horario) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloGrupo_horario.getEstado())) {
                    SQL = con.prepareStatement("UPDATE grupo_horario SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloGrupo_horario.getEstado());
                    SQL.setInt(2, modeloGrupo_horario.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE grupo_horario SET "
                            + "codigo = ?, "
                            + "nombre = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloGrupo_horario.getCodigo());
                    SQL.setString(2, modeloGrupo_horario.getNombre());
                    SQL.setInt(3, modeloGrupo_horario.getId());                    
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "4";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorgrupo_horario" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorgrupo_horario" + e);
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
            ModeloGrupo_horario modeloGrupo_horario = new ModeloGrupo_horario();
            modeloGrupo_horario.setId(Integer.parseInt(request.getParameter("id")));
            modeloGrupo_horario.setEstado("N");
            resultado = Update(modeloGrupo_horario);
            if (resultado.equals("4"))
            {
                resultado = "2";
            }
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla grupo_horario dependiendo de un ID
     *
     * @author: Carlos A Dominguez Diaz
     * @param request
     * @return String
     * @version: 25/06/2020
     */
    public ModeloGrupo_horario getModelo(Integer Id) {
        ModeloGrupo_horario modeloGrupo_horario = new ModeloGrupo_horario();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado"
                    + " FROM grupo_horario"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloGrupo_horario.setId(res.getInt("id"));
                modeloGrupo_horario.setCodigo(res.getString("codigo"));
                modeloGrupo_horario.setNombre(res.getString("nombre"));
                modeloGrupo_horario.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorgrupo_horario" + e);
        }
        return modeloGrupo_horario;
    }

    /**
     * Llena un Listado de la tabla grupo_horario
     *
     * @author: Carlos A Dominguez Diaz
     * @param vacio
     * @return LinkedList<ModeloGrupo_horario>
     * @version: 25/06/2020
     */
    public LinkedList<ModeloGrupo_horario> Read(String estado) throws SQLException {
        LinkedList<ModeloGrupo_horario> ListaModeloGrupo_horario = new LinkedList<ModeloGrupo_horario>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "estado"
                    + " FROM grupo_horario"
                    + " WHERE estado = ? ");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloGrupo_horario modeloGrupo_horario = new ModeloGrupo_horario();
                modeloGrupo_horario.setId(res.getInt("id"));
                modeloGrupo_horario.setCodigo(res.getString("codigo"));
                modeloGrupo_horario.setNombre(res.getString("nombre"));
                modeloGrupo_horario.setEstado(res.getString("estado"));
                ListaModeloGrupo_horario.add(modeloGrupo_horario);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorgrupo_horario" + e);
        }
        return ListaModeloGrupo_horario;
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
            LinkedList<ModeloGrupo_horario> listmoGrupoTurnos;
            listmoGrupoTurnos = Read("S");
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloGrupo_horario modeloGrupoTurnos : listmoGrupoTurnos) {
                    out += "<option value=\"" + modeloGrupoTurnos.getId() + "\"> " + modeloGrupoTurnos.getNombre()+ "</option>";
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
                for (ModeloGrupo_horario modeloGrupoTurnos : listmoGrupoTurnos) {
                    out += "<tr>";
                    out += "<td>" + modeloGrupoTurnos.getCodigo() + "</td>";
                    out += "<td>" + modeloGrupoTurnos.getNombre()+ "</td>";
                    out += "<td class=\"text-center\">";
                    // Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                    out += "data-id=\"" + modeloGrupoTurnos.getId() + "\"";
                    out += "data-codigo=\"" + modeloGrupoTurnos.getCodigo() + "\"";
                    out += "data-nombre=\"" + modeloGrupoTurnos.getNombre()+ "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                    //Boton Eliminar
                    out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                    out += "data-id=\"" + modeloGrupoTurnos.getId() + "\"";
                    out += "data-codigo=\"" + modeloGrupoTurnos.getCodigo() + "\"";
                    out += "data-nombre=\"" + modeloGrupoTurnos.getNombre()+ "\"";
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
