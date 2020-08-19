package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloAsociacion_grupo_consumo_horario_consumo;
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
 * Esta clase permite controlar los eventos de AsocGrupoConsumo
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorAsociacion_grupo_consumo_horario_consumo {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;
    ControladorGrupo_consumo controladorGrupo_consumo = new ControladorGrupo_consumo();
    ControladorHorario_consumo controladorHorario_consumo = new ControladorHorario_consumo();

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * asociacion_grupo_consumo_horario_consumo
     *
     * @param response
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 16/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloAsociacion_grupo_consumo_horario_consumo modeloAsociacion_grupo_consumo_horario_consumo = new ModeloAsociacion_grupo_consumo_horario_consumo();
        modeloAsociacion_grupo_consumo_horario_consumo.setModelo_grupo_consumo(controladorGrupo_consumo.getModelo(Integer.parseInt(request.getParameter("grupo_consumo"))));
        modeloAsociacion_grupo_consumo_horario_consumo.setModelo_horario_consumo(controladorHorario_consumo.getModelo(Integer.parseInt(request.getParameter("horario_consumo"))));
        modeloAsociacion_grupo_consumo_horario_consumo.setCosto_consumo(Integer.parseInt(request.getParameter("costo_consumo")));
        modeloAsociacion_grupo_consumo_horario_consumo.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloAsociacion_grupo_consumo_horario_consumo);
        } else {
            modeloAsociacion_grupo_consumo_horario_consumo.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloAsociacion_grupo_consumo_horario_consumo);
            if ("1".equals(resultado)) {
                resultado = "4";
            }
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla:
     * asociacion_grupo_consumo_horario_consumo
     *
     * @param modeloAsociacion_grupo_consumo_horario_consumo
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @return String
     * @version: 16/05/2020
     */
    public String Insert(ModeloAsociacion_grupo_consumo_horario_consumo modeloAsociacion_grupo_consumo_horario_consumo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO asociacion_grupo_consumo_horario_consumo("
                        + "id_grupo_consumo, "
                        + "id_horario_consumo, "
                        + "costo_consumo, "
                        + "estado)"
                        + " VALUE (?,?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setInt(1, modeloAsociacion_grupo_consumo_horario_consumo.getModelo_grupo_consumo().getId());
                SQL.setInt(2, modeloAsociacion_grupo_consumo_horario_consumo.getModelo_horario_consumo().getId());
                SQL.setInt(3, modeloAsociacion_grupo_consumo_horario_consumo.getCosto_consumo());
                SQL.setString(4, "S");
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "asociacion_grupo_consumo_horario_consumo", user, i, "Se inserto el registro.", "", "");
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladorasociacion_grupo_consumo_horario_consumo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorasociacion_grupo_consumo_horario_consumo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la
     * tabla:asociacion_grupo_consumo_horario_consumo
     *
     * @param modeloAsociacion_grupo_consumo_horario_consumo
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @return String
     * @version: 16/05/2020
     */
    public String Update(ModeloAsociacion_grupo_consumo_horario_consumo modeloAsociacion_grupo_consumo_horario_consumo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloAsociacion_grupo_consumo_horario_consumo.getEstado())) {
                    SQL = con.prepareStatement("UPDATE asociacion_grupo_consumo_horario_consumo SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloAsociacion_grupo_consumo_horario_consumo.getEstado());
                    SQL.setInt(2, modeloAsociacion_grupo_consumo_horario_consumo.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE asociacion_grupo_consumo_horario_consumo SET "
                            + "id_grupo_consumo = ?, "
                            + "id_horario_consumo = ?, "
                            + "costo_consumo = ? "
                            + "WHERE id = ? ");
                    SQL.setInt(1, modeloAsociacion_grupo_consumo_horario_consumo.getModelo_grupo_consumo().getId());
                    SQL.setInt(2, modeloAsociacion_grupo_consumo_horario_consumo.getModelo_horario_consumo().getId());
                    SQL.setInt(3, modeloAsociacion_grupo_consumo_horario_consumo.getCosto_consumo());
                    SQL.setInt(4, modeloAsociacion_grupo_consumo_horario_consumo.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorasociacion_grupo_consumo_horario_consumo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorasociacion_grupo_consumo_horario_consumo" + e);
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
            ModeloAsociacion_grupo_consumo_horario_consumo modeloAsociacion_grupo_consumo_horario_consumo = new ModeloAsociacion_grupo_consumo_horario_consumo();
            modeloAsociacion_grupo_consumo_horario_consumo.setId(Integer.parseInt(request.getParameter("id")));
            modeloAsociacion_grupo_consumo_horario_consumo.setEstado("N");
            resultado = Update(modeloAsociacion_grupo_consumo_horario_consumo);
            if ("1".equals(resultado)) {
                resultado = "2";
            }
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla asociacion_grupo_consumo_horario_consumo
     * dependiendo de un ID
     *
     * @param Id
     * @author: Carlos Arturo Dominguez Diaz
     * @return String
     * @version: 16/05/2020
     */
    public ModeloAsociacion_grupo_consumo_horario_consumo getModelo(Integer Id) {
        ModeloAsociacion_grupo_consumo_horario_consumo modeloAsociacion_grupo_consumo_horario_consumo = new ModeloAsociacion_grupo_consumo_horario_consumo();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "id_grupo_consumo, "
                    + "id_horario_consumo, "
                    + "costo_consumo, "
                    + "estado"
                    + " FROM asociacion_grupo_consumo_horario_consumo"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloAsociacion_grupo_consumo_horario_consumo.setId(res.getInt("id"));
                modeloAsociacion_grupo_consumo_horario_consumo.setModelo_grupo_consumo(controladorGrupo_consumo.getModelo(res.getInt("id_grupo_consumo")));
                modeloAsociacion_grupo_consumo_horario_consumo.setModelo_horario_consumo(controladorHorario_consumo.getModelo(res.getInt("id_horario_consumo")));
                modeloAsociacion_grupo_consumo_horario_consumo.setCosto_consumo(res.getInt("costo_consumo"));
                modeloAsociacion_grupo_consumo_horario_consumo.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorasociacion_grupo_consumo_horario_consumo" + e);
        }
        return modeloAsociacion_grupo_consumo_horario_consumo;
    }

    /**
     * Llena un Listado de la tabla asociacion_grupo_consumo_horario_consumo
     *
     * @return
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @version: 16/05/2020
     */
    public LinkedList<ModeloAsociacion_grupo_consumo_horario_consumo> Read(String estado) throws SQLException {
        LinkedList<ModeloAsociacion_grupo_consumo_horario_consumo> ListaModeloAsociacion_grupo_consumo_horario_consumo = new LinkedList<ModeloAsociacion_grupo_consumo_horario_consumo>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "id_grupo_consumo, "
                    + "id_horario_consumo, "
                    + "costo_consumo, "
                    + "estado "
                    + "FROM asociacion_grupo_consumo_horario_consumo "
                    + "WHERE estado = ?");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloAsociacion_grupo_consumo_horario_consumo modeloAsociacion_grupo_consumo_horario_consumo = new ModeloAsociacion_grupo_consumo_horario_consumo();
                modeloAsociacion_grupo_consumo_horario_consumo.setId(res.getInt("id"));
                modeloAsociacion_grupo_consumo_horario_consumo.setModelo_grupo_consumo(controladorGrupo_consumo.getModelo(res.getInt("id_grupo_consumo")));
                modeloAsociacion_grupo_consumo_horario_consumo.setModelo_horario_consumo(controladorHorario_consumo.getModelo(res.getInt("id_horario_consumo")));
                modeloAsociacion_grupo_consumo_horario_consumo.setCosto_consumo(res.getInt("costo_consumo"));
                modeloAsociacion_grupo_consumo_horario_consumo.setEstado(res.getString("estado"));
                ListaModeloAsociacion_grupo_consumo_horario_consumo.add(modeloAsociacion_grupo_consumo_horario_consumo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorasociacion_grupo_consumo_horario_consumo" + e);
        }
        return ListaModeloAsociacion_grupo_consumo_horario_consumo;
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
        String estado = "S";
        if (request.getParameter("estado") != null) {
            estado = "N";
        }
        try {
            LinkedList<ModeloAsociacion_grupo_consumo_horario_consumo> listModeloAsocGrupoConsumos;
            listModeloAsocGrupoConsumos = Read(estado);
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
            for (ModeloAsociacion_grupo_consumo_horario_consumo modeloAsocGrupoConsumo : listModeloAsocGrupoConsumos) {
                out += "<tr>";
                out += "<td>" + modeloAsocGrupoConsumo.getModelo_grupo_consumo().getNombre() + "</td>";
                out += "<td>" + modeloAsocGrupoConsumo.getModelo_horario_consumo().getNombre() + "</td>";
                out += "<td>" + modeloAsocGrupoConsumo.getCosto_consumo()+ "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modeloAsocGrupoConsumo.getId() + "\"";
                out += "data-idgrupoconsumo=\"" + modeloAsocGrupoConsumo.getModelo_grupo_consumo().getId() + "\"";
                out += "data-idhorarioconsumo=\"" + modeloAsocGrupoConsumo.getModelo_horario_consumo().getId() + "\"";
                out += "data-valor=\"" + modeloAsocGrupoConsumo.getCosto_consumo()+ "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloAsocGrupoConsumo.getId() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (SQLException e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }
}
