package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloHorario_consumo;
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
 * Esta clase permite controlar los eventos de Horario Consumo contrine Insert -
 * Update, Delete, Read, GetModel
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorHorario_consumo {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;
    ControladorTipo_consumo controladorTipo_consumo = new ControladorTipo_consumo();

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * horario_consumo
     *
     * @param response
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 16/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloHorario_consumo modeloHorario_consumo = new ModeloHorario_consumo();
        modeloHorario_consumo.setCodigo(request.getParameter("codigo"));
        modeloHorario_consumo.setNombre(request.getParameter("nombre"));
        modeloHorario_consumo.setHora_inicio(request.getParameter("hora_inicio"));
        modeloHorario_consumo.setHora_fin(request.getParameter("hora_fin"));
        modeloHorario_consumo.setCantidad_consumo(Integer.parseInt(request.getParameter("cantidad_consumo")));
        modeloHorario_consumo.setLunes(request.getParameter("lunes"));
        modeloHorario_consumo.setMartes(request.getParameter("martes"));
        modeloHorario_consumo.setMiercoles(request.getParameter("miercoles"));
        modeloHorario_consumo.setJueves(request.getParameter("jueves"));
        modeloHorario_consumo.setViernes(request.getParameter("viernes"));
        modeloHorario_consumo.setSabado(request.getParameter("sabado"));
        modeloHorario_consumo.setDomingo(request.getParameter("domingo"));
        modeloHorario_consumo.setFestivo(request.getParameter("festivo"));
        modeloHorario_consumo.setModelo_tipo_consumo(controladorTipo_consumo.getModelo(Integer.parseInt(request.getParameter("tipo_consumo"))));
        modeloHorario_consumo.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloHorario_consumo);
        } else {
            modeloHorario_consumo.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloHorario_consumo);
            if ("1".equals(resultado)) {
                resultado = "4";
            }
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: horario_consumo
     *
     * @param modeloHorario_consumo
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @return String
     * @version: 16/05/2020
     */
    public String Insert(ModeloHorario_consumo modeloHorario_consumo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO horario_consumo("
                        + "codigo, "
                        + "nombre, "
                        + "hora_inicio, "
                        + "hora_fin, "
                        + "cantidad_consumo, "
                        + "lunes, "
                        + "martes, "
                        + "miercoles, "
                        + "jueves, "
                        + "viernes, "
                        + "sabado, "
                        + "domingo, "
                        + "festivo, "
                        + "id_tipo_consumo, "
                        + "estado) "
                        + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloHorario_consumo.getCodigo());
                SQL.setString(2, modeloHorario_consumo.getNombre());
                SQL.setString(3, modeloHorario_consumo.getHora_inicio());
                SQL.setString(4, modeloHorario_consumo.getHora_fin());
                SQL.setInt(5, modeloHorario_consumo.getCantidad_consumo());
                SQL.setString(6, modeloHorario_consumo.getLunes());
                SQL.setString(7, modeloHorario_consumo.getMartes());
                SQL.setString(8, modeloHorario_consumo.getMiercoles());
                SQL.setString(9, modeloHorario_consumo.getJueves());
                SQL.setString(10, modeloHorario_consumo.getViernes());
                SQL.setString(11, modeloHorario_consumo.getSabado());
                SQL.setString(12, modeloHorario_consumo.getDomingo());
                SQL.setString(13, modeloHorario_consumo.getFestivo());
                SQL.setInt(14, modeloHorario_consumo.getModelo_tipo_consumo().getId());
                SQL.setString(15, "S");
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
                System.out.println("Error en la consulta SQL Insert en Controladorhorario_consumo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorhorario_consumo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:horario_consumo
     *
     * @param modeloHorario_consumo
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @return String
     * @version: 16/05/2020
     */
    public String Update(ModeloHorario_consumo modeloHorario_consumo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloHorario_consumo.getEstado())) {
                    SQL = con.prepareStatement("UPDATE horario_consumo SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloHorario_consumo.getEstado());
                    SQL.setInt(2, modeloHorario_consumo.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE horario_consumo SET "
                            + "codigo = ?, "
                            + "nombre = ?, "
                            + "hora_inicio = ?, "
                            + "hora_fin = ?, "
                            + "cantidad_consumo = ?, "
                            + "lunes = ?, "
                            + "martes = ?, "
                            + "miercoles = ?, "
                            + "jueves = ?, "
                            + "viernes = ?, "
                            + "sabado = ?, "
                            + "domingo = ?, "
                            + "festivo = ?, "
                            + "id_tipo_consumo = ? "
                            + "WHERE id = ? ");
                    SQL.setString(1, modeloHorario_consumo.getCodigo());
                    SQL.setString(2, modeloHorario_consumo.getNombre());
                    SQL.setString(3, modeloHorario_consumo.getHora_inicio());
                    SQL.setString(4, modeloHorario_consumo.getHora_fin());
                    SQL.setInt(5, modeloHorario_consumo.getCantidad_consumo());
                    SQL.setString(6, modeloHorario_consumo.getLunes());
                    SQL.setString(7, modeloHorario_consumo.getMartes());
                    SQL.setString(8, modeloHorario_consumo.getMiercoles());
                    SQL.setString(9, modeloHorario_consumo.getJueves());
                    SQL.setString(10, modeloHorario_consumo.getViernes());
                    SQL.setString(11, modeloHorario_consumo.getSabado());
                    SQL.setString(12, modeloHorario_consumo.getDomingo());
                    SQL.setString(13, modeloHorario_consumo.getFestivo());
                    SQL.setInt(14, modeloHorario_consumo.getModelo_tipo_consumo().getId());
                    SQL.setInt(15, modeloHorario_consumo.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorhorario_consumo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorhorario_consumo" + e);
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
            ModeloHorario_consumo modeloHorario_consumo = new ModeloHorario_consumo();
            modeloHorario_consumo.setId(Integer.parseInt(request.getParameter("id")));
            modeloHorario_consumo.setEstado("N");
            resultado = Update(modeloHorario_consumo);
            if ("1".equals(resultado)) {
                resultado = "2";
            }
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla horario_consumo dependiendo de un ID
     *
     * @param Id
     * @author: Carlos Arturo Dominguez uest
     * @return String
     * @version: 16/05/2020
     */
    public ModeloHorario_consumo getModelo(Integer Id) {
        ModeloHorario_consumo modeloHorario_consumo = new ModeloHorario_consumo();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "hora_inicio, "
                    + "hora_fin, "
                    + "cantidad_consumo, "
                    + "lunes, "
                    + "martes, "
                    + "miercoles, "
                    + "jueves, "
                    + "viernes, "
                    + "sabado, "
                    + "domingo, "
                    + "festivo, "
                    + "id_tipo_consumo, "
                    + "estado"
                    + " FROM horario_consumo"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloHorario_consumo.setId(res.getInt("id"));
                modeloHorario_consumo.setCodigo(res.getString("codigo"));
                modeloHorario_consumo.setNombre(res.getString("nombre"));
                modeloHorario_consumo.setHora_inicio(res.getString("hora_inicio"));
                modeloHorario_consumo.setHora_fin(res.getString("hora_fin"));
                modeloHorario_consumo.setCantidad_consumo(res.getInt("cantidad_consumo"));
                modeloHorario_consumo.setLunes(res.getString("lunes"));
                modeloHorario_consumo.setMartes(res.getString("martes"));
                modeloHorario_consumo.setMiercoles(res.getString("miercoles"));
                modeloHorario_consumo.setJueves(res.getString("jueves"));
                modeloHorario_consumo.setViernes(res.getString("viernes"));
                modeloHorario_consumo.setSabado(res.getString("sabado"));
                modeloHorario_consumo.setDomingo(res.getString("domingo"));
                modeloHorario_consumo.setFestivo(res.getString("festivo"));
                modeloHorario_consumo.setModelo_tipo_consumo(controladorTipo_consumo.getModelo(res.getInt("id_tipo_consumo")));
                modeloHorario_consumo.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorhorario_consumo" + e);
        }
        return modeloHorario_consumo;
    }

    /**
     * Llena un Listado de la tabla horario_consumo
     *
     * @return
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @version: 16/05/2020
     */
    public LinkedList<ModeloHorario_consumo> Read(String estado) throws SQLException {
        LinkedList<ModeloHorario_consumo> ListaModeloHorario_consumo = new LinkedList<>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "nombre, "
                    + "hora_inicio, "
                    + "hora_fin, "
                    + "cantidad_consumo, "
                    + "lunes, "
                    + "martes, "
                    + "miercoles, "
                    + "jueves, "
                    + "viernes, "
                    + "sabado, "
                    + "domingo, "
                    + "festivo, "
                    + "id_tipo_consumo, "
                    + "estado "
                    + "FROM horario_consumo "
                    + "WHERE estado = ?");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloHorario_consumo modeloHorario_consumo = new ModeloHorario_consumo();
                modeloHorario_consumo.setId(res.getInt("id"));
                modeloHorario_consumo.setCodigo(res.getString("codigo"));
                modeloHorario_consumo.setNombre(res.getString("nombre"));
                modeloHorario_consumo.setHora_inicio(res.getString("hora_inicio"));
                modeloHorario_consumo.setHora_fin(res.getString("hora_fin"));
                modeloHorario_consumo.setCantidad_consumo(res.getInt("cantidad_consumo"));
                modeloHorario_consumo.setLunes(res.getString("lunes"));
                modeloHorario_consumo.setMartes(res.getString("martes"));
                modeloHorario_consumo.setMiercoles(res.getString("miercoles"));
                modeloHorario_consumo.setJueves(res.getString("jueves"));
                modeloHorario_consumo.setViernes(res.getString("viernes"));
                modeloHorario_consumo.setSabado(res.getString("sabado"));
                modeloHorario_consumo.setDomingo(res.getString("domingo"));
                modeloHorario_consumo.setFestivo(res.getString("festivo"));
                modeloHorario_consumo.setModelo_tipo_consumo(controladorTipo_consumo.getModelo(res.getInt("id_tipo_consumo")));
                modeloHorario_consumo.setEstado(res.getString("estado"));
                ListaModeloHorario_consumo.add(modeloHorario_consumo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorhorario_consumo" + e);
        }
        return ListaModeloHorario_consumo;
    }

    /**
     * Permite listar la información de la tabla de Horario Consumo
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
            LinkedList<ModeloHorario_consumo> listmoHorarioConsumos;
            listmoHorarioConsumos = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloHorario_consumo modeloHorario_consumo : listmoHorarioConsumos) {
                    out += "<option value=\"" + modeloHorario_consumo.getId() + "\"> " + modeloHorario_consumo.getNombre() + "</option>";
                }
            } else {
                out = "";
                out += "<thead>";
                out += "<tr>";
                out += "<th>Codigo</th>";
                out += "<th>Nombre</th>";
                out += "<th>Hora Inicio</th>";
                out += "<th>Hora Fin</th>";
                out += "<th>No Consumos</th>";
                out += "<th>TipoConsumo</th>";
                out += "<th>L</th>";
                out += "<th>M</th>";
                out += "<th>Mx</th>";
                out += "<th>J</th>";
                out += "<th>V</th>";
                out += "<th>S</th>";
                out += "<th>D</th>";
                out += "<th>F</th>";
                out += "<th>Opcion</th>";
                out += "</tr>";
                out += "</thead>";
                out += "<tbody>";
                for (ModeloHorario_consumo modelo : listmoHorarioConsumos) {
                    out += "<tr>";
                    out += "<td>" + modelo.getCodigo() + "</td>";
                    out += "<td>" + modelo.getNombre() + "</td>";
                    out += "<td>" + modelo.getHora_inicio() + "</td>";
                    out += "<td>" + modelo.getHora_fin() + "</td>";
                    out += "<td>" + modelo.getCantidad_consumo() + "</td>";
                    out += "<td>" + modelo.getModelo_tipo_consumo().getNombre() + "</td>";
                    out += "<td>" + modelo.getLunes() + "</td>";
                    out += "<td>" + modelo.getMartes() + "</td>";
                    out += "<td>" + modelo.getMiercoles() + "</td>";
                    out += "<td>" + modelo.getJueves() + "</td>";
                    out += "<td>" + modelo.getViernes() + "</td>";
                    out += "<td>" + modelo.getSabado() + "</td>";
                    out += "<td>" + modelo.getDomingo() + "</td>";
                    out += "<td>" + modelo.getFestivo() + "</td>";
                    out += "<td class=\"text-center\">";
                    // Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                    out += "data-id=\"" + modelo.getId() + "\"";
                    out += "data-codigo=\"" + modelo.getCodigo() + "\"";
                    out += "data-nombre=\"" + modelo.getNombre() + "\"";
                    out += "data-horaincio=\"" + modelo.getHora_inicio() + "\"";
                    out += "data-horafin=\"" + modelo.getHora_fin() + "\"";
                    out += "data-cantidadconsumos=\"" + modelo.getCantidad_consumo() + "\"";
                    out += "data-lunes=\"" + modelo.getLunes() + "\"";
                    out += "data-martes=\"" + modelo.getMartes() + "\"";
                    out += "data-miercoles=\"" + modelo.getMiercoles() + "\"";
                    out += "data-jueves=\"" + modelo.getJueves() + "\"";
                    out += "data-viernes=\"" + modelo.getViernes() + "\"";
                    out += "data-sabado=\"" + modelo.getSabado() + "\"";
                    out += "data-domingo=\"" + modelo.getDomingo() + "\"";
                    out += "data-festivo=\"" + modelo.getFestivo() + "\"";
                    out += "data-tipoconsumo=\"" + modelo.getModelo_tipo_consumo().getId() + "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                    //Boton Eliminar
                    out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                    out += "data-id=\"" + modelo.getId() + "\"";
                    out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                    out += "</td>";
                    out += "</tr>";
                }
                out += "</tbody>";
            }
        } catch (SQLException e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }
}
