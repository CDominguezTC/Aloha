package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloTurnos;
import com.google.gson.Gson;
import java.io.File;
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
 * Esta clase permite controlar los eventos de Turnos contiene Insert - Update,
 * Delete, Read
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorTurnos {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd Turnos
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Insert(HttpServletRequest request) {
        if ("".equals(request.getParameter("id"))) {
            ModeloTurnos modelo = new ModeloTurnos();
            modelo.setId(0);
            modelo.setCodigo(request.getParameter("codigo"));
            modelo.setDescripcion(request.getParameter("nombre"));
            modelo.setHora_inicio(request.getParameter("horainicio"));
            modelo.setHora_fin(request.getParameter("horafin"));
            modelo.setTeorico(request.getParameter("teorico"));
            modelo.setHora_inicioBreak(request.getParameter("horainiciobreak"));
            modelo.setHora_finBreak(request.getParameter("horafinbreak"));
            modelo.setDescanso(request.getParameter("tiempobreak"));
            modelo.setTiempo_maximo_entrada(request.getParameter("tiempograciaae"));
            modelo.setTiempo_minimo_salida(request.getParameter("tiempograciaas"));
            modelo.setTiempo_minimo_entrada(request.getParameter("tiempograciade"));
            modelo.setTiempo_maximo_salida(request.getParameter("tiempograciads"));
            modelo.setTolerancia_despues_entrada(request.getParameter("aproximacionae"));
            modelo.setTolerancia_antes_salir(request.getParameter("aproximacionds"));
            modelo.setHora_inicio_diurno(request.getParameter("horainiciodiurno"));
            modelo.setHora_inicio_nocturno(request.getParameter("horainicionocturno"));
            modelo.setTurno_noche(request.getParameter("turnonocturnos"));
            modelo.setTurno_extra(request.getParameter("turnoextra"));
            modelo.setDescuentaBreak(request.getParameter("turnodescuento"));
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("INSERT INTO `turnotiempos`("
                            + "`codigo`,"
                            + "`descripcion`,"
                            + "`hora_inicio`,"
                            + "`hora_fin`,"
                            + "`teorico`,"
                            + "`tolerancia_despues_entrada`,"
                            + "`tolerancia_antes_salir`,"
                            + "`tiempo_minimo_entrada`,"
                            + "`tiempo_maximo_entrada`,"
                            + "`tiempo_minimo_salida`,"
                            + "`tiempo_maximo_salida`,"
                            + "`descanso`,"
                            + "`hora_inicio_diurno`,"
                            + "`hora_inicio_nocturno`,"
                            + "`turno_noche`,"
                            + "`hora_inicio_break`,"
                            + "`hora_fin_break`,"
                            + "`descuenta_break`,"
                            + "`turno_extra`) "
                            + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                    SQL.setString(1, modelo.getCodigo());
                    SQL.setString(2, modelo.getDescripcion());
                    SQL.setString(3, modelo.getHora_inicio());
                    SQL.setString(4, modelo.getHora_fin());
                    SQL.setString(5, modelo.getTeorico());
                    SQL.setString(6, modelo.getTolerancia_despues_entrada());
                    SQL.setString(7, modelo.getTolerancia_antes_salir());
                    SQL.setString(8, modelo.getTiempo_minimo_entrada());
                    SQL.setString(9, modelo.getTiempo_maximo_entrada());
                    SQL.setString(10, modelo.getTiempo_minimo_salida());
                    SQL.setString(11, modelo.getTiempo_maximo_salida());
                    SQL.setString(12, modelo.getDescanso());
                    SQL.setString(13, modelo.getHora_inicio_diurno());
                    SQL.setString(14, modelo.getHora_inicio_nocturno());
                    SQL.setString(15, modelo.getTurno_noche());
                    SQL.setString(16, modelo.getHora_inicioBreak());
                    SQL.setString(17, modelo.getHora_finBreak());
                    SQL.setString(18, modelo.getDescuentaBreak());
                    SQL.setString(19, modelo.getTurno_extra());
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
            ModeloTurnos modelo = new ModeloTurnos();
            modelo.setId(Integer.parseInt(request.getParameter("id")));
            modelo.setCodigo(request.getParameter("codigo"));
            modelo.setDescripcion(request.getParameter("nombre"));
            modelo.setHora_inicio(request.getParameter("horainicio"));
            modelo.setHora_fin(request.getParameter("horafin"));
            modelo.setTeorico(request.getParameter("teorico"));
            modelo.setHora_inicioBreak(request.getParameter("horainiciobreak"));
            modelo.setHora_finBreak(request.getParameter("horafinbreak"));
            modelo.setDescanso(request.getParameter("tiempobreak"));
            modelo.setTiempo_maximo_entrada(request.getParameter("tiempograciaae"));
            modelo.setTiempo_minimo_salida(request.getParameter("tiempograciaas"));
            modelo.setTiempo_minimo_entrada(request.getParameter("tiempograciade"));
            modelo.setTiempo_maximo_salida(request.getParameter("tiempograciads"));
            modelo.setTolerancia_despues_entrada(request.getParameter("aproximacionae"));
            modelo.setTolerancia_antes_salir(request.getParameter("aproximacionds"));
            modelo.setHora_inicio_diurno(request.getParameter("horainiciodiurno"));
            modelo.setHora_inicio_nocturno(request.getParameter("horainicionocturno"));
            modelo.setTurno_noche(request.getParameter("turnonocturnos"));
            modelo.setTurno_extra(request.getParameter("turnoextra"));
            modelo.setDescuentaBreak(request.getParameter("turnodescuento"));
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("UPDATE `turnotiempos` SET "
                            + "`codigo` = ?,"
                            + "`descripcion` = ?,"
                            + "`hora_inicio` = ?,"
                            + "`hora_fin` = ?,"
                            + "`teorico` = ?,"
                            + "`tolerancia_despues_entrada` = ?,"
                            + "`tolerancia_antes_salir` = ?,"
                            + "`tiempo_minimo_entrada` = ?,"
                            + "`tiempo_maximo_entrada` = ?,"
                            + "`tiempo_minimo_salida` = ?,"
                            + "`tiempo_maximo_salida` = ?,"
                            + "`descanso` = ?,"
                            + "`hora_inicio_diurno` = ?,"
                            + "`hora_inicio_nocturno` = ?,"
                            + "`turno_noche` = ?,"
                            + "`hora_inicio_break` = ?,"
                            + "`hora_fin_break` = ?,"
                            + "`descuenta_break` = ?,"
                            + "`turno_extra` = ? "
                            + "WHERE `id` = ?;");
                    SQL.setString(1, modelo.getCodigo());
                    SQL.setString(2, modelo.getDescripcion());
                    SQL.setString(3, modelo.getHora_inicio());
                    SQL.setString(4, modelo.getHora_fin());
                    SQL.setString(5, modelo.getTeorico());
                    SQL.setString(6, modelo.getTolerancia_despues_entrada());
                    SQL.setString(7, modelo.getTolerancia_antes_salir());
                    SQL.setString(8, modelo.getTiempo_minimo_entrada());
                    SQL.setString(9, modelo.getTiempo_maximo_entrada());
                    SQL.setString(10, modelo.getTiempo_minimo_salida());
                    SQL.setString(11, modelo.getTiempo_maximo_salida());
                    SQL.setString(12, modelo.getDescanso());
                    SQL.setString(13, modelo.getHora_inicio_diurno());
                    SQL.setString(14, modelo.getHora_inicio_nocturno());
                    SQL.setString(15, modelo.getTurno_noche());
                    SQL.setString(16, modelo.getHora_inicioBreak());
                    SQL.setString(17, modelo.getHora_finBreak());
                    SQL.setString(18, modelo.getDescuentaBreak());
                    SQL.setString(19, modelo.getTurno_extra());
                    SQL.setInt(20, modelo.getId());
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
     * Permite la eliminar un dato en la tabla de Turnos
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Delete(HttpServletRequest request) {
        if (!"".equals(request.getParameter("id"))) {
            String idtmp = request.getParameter("id");
            ModeloTurnos modelo = new ModeloTurnos();
            modelo.setId(Integer.parseInt(request.getParameter("id")));

            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("DELETE FROM "
                            + "`turnotiempos` "
                            + "WHERE `id` = ?;");
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
     * Permite listar la información de la tabla de Turnos Metodo Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    private LinkedList<ModeloTurnos> Read() {
        LinkedList<ModeloTurnos> listModeloTurnos = new LinkedList<ModeloTurnos>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "id, "
                    + "codigo, "
                    + "descripcion,"
                    + "tipo_turno,"
                    + "hora_inicio,"
                    + "hora_fin,"
                    + "teorico,"
                    + "tolerancia_despues_entrada,"
                    + "tolerancia_antes_salir,"
                    + "tiempo_breack,"
                    + "limite_turno,"
                    + "gener_extras_entrada,"
                    + "tiempo_minimo_entrada,"
                    + "tiempo_maximo_entrada,"
                    + "genera_extras_salida,"
                    + "tiempo_minimo_salida,"
                    + "tiempo_maximo_salida,"
                    + "redondeo_entrada,"
                    + "sentido_entrada,"
                    + "redondeo_salida,"
                    + "sentido_salida,"
                    + "descanso,"
                    + "sentido_descanso,"
                    + "conceptos,"
                    + "sentido_concepto, "
                    + "hora_inicio_diurno, "
                    + "hora_inicio_nocturno, "
                    + "hora_inicio_break, "
                    + "hora_fin_break, "
                    + "descuenta_break, "
                    + "turno_extra, "
                    + "turno_noche "
                    + "FROM turnotiempos ORDER BY descripcion");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloTurnos modeloTurnos = new ModeloTurnos();
                modeloTurnos.setId(res.getInt("id"));
                modeloTurnos.setCodigo(res.getString("codigo"));
                modeloTurnos.setDescripcion(res.getString("descripcion"));
                modeloTurnos.setTipo_turno(res.getString("tipo_turno"));
                modeloTurnos.setHora_inicio(res.getString("hora_inicio"));
                modeloTurnos.setHora_fin(res.getString("hora_fin"));
                modeloTurnos.setTeorico(res.getString("teorico"));
                modeloTurnos.setTolerancia_despues_entrada(res.getString("tolerancia_despues_entrada"));
                modeloTurnos.setTolerancia_antes_salir(res.getString("tolerancia_antes_salir"));
                modeloTurnos.setTiempo_breack(res.getString("tiempo_breack"));
                modeloTurnos.setLimite_turno(res.getString("limite_turno"));
                modeloTurnos.setGener_extras_entrada(res.getString("gener_extras_entrada"));
                modeloTurnos.setTiempo_minimo_entrada(res.getString("tiempo_minimo_entrada"));
                modeloTurnos.setTiempo_maximo_entrada(res.getString("tiempo_maximo_entrada"));
                modeloTurnos.setGenera_extras_salida(res.getString("genera_extras_salida"));
                modeloTurnos.setTiempo_minimo_salida(res.getString("tiempo_minimo_salida"));
                modeloTurnos.setTiempo_maximo_salida(res.getString("tiempo_maximo_salida"));
                modeloTurnos.setRedondeo_entrada(res.getString("redondeo_entrada"));
                modeloTurnos.setSentido_entrada(res.getString("sentido_entrada"));
                modeloTurnos.setRedondeo_salida(res.getString("redondeo_salida"));
                modeloTurnos.setSentido_salida(res.getString("sentido_salida"));
                modeloTurnos.setDescanso(res.getString("descanso"));
                modeloTurnos.setSentido_descanso(res.getString("sentido_descanso"));
                modeloTurnos.setConceptos(res.getString("conceptos"));
                modeloTurnos.setSentido_concepto(res.getString("sentido_concepto"));
                modeloTurnos.setHora_inicio_diurno(res.getString("hora_inicio_diurno"));
                modeloTurnos.setHora_inicio_nocturno(res.getString("hora_inicio_nocturno"));
                modeloTurnos.setHora_inicioBreak(res.getString("hora_inicio_break"));
                modeloTurnos.setHora_finBreak(res.getString("hora_fin_break"));
                modeloTurnos.setTurno_noche(res.getString("turno_noche"));
                modeloTurnos.setTurno_extra(res.getString("turno_extra"));
                modeloTurnos.setDescuentaBreak(res.getString("descuenta_break"));
                listModeloTurnos.add(modeloTurnos);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listModeloTurnos;
    }

    /**
     * Permite listar la información de la tabla de Turnos
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
            LinkedList<ModeloTurnos> listmodemlo;
            listmodemlo = Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<td>Codigo</td>";
            out += "<td>Nombre</td>";
            out += "<td>Hora Ini</td>";
            out += "<td>Hora Fin</td>";
            out += "<td>Teorico</td>";
            out += "<td>Hora Ini Br</td>";
            out += "<td>Hora Fin Br</td>";
            out += "<td>Descanso</td>";
            out += "<td>Tiempo GAE</td>";
            out += "<td>Tiempo GAS</td>";
            out += "<td>Tiempo GDE</td>";
            out += "<td>Tiempo GDS</td>";
            out += "<td>Aprox AE</td>";
            out += "<td>Aprox DS</td>";
            out += "<td>Opciones</td>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloTurnos modeloTurnos : listmodemlo) {
                out += "<tr>";
                out += "<td>" + modeloTurnos.getCodigo() + "</td>";
                out += "<td>" + modeloTurnos.getDescripcion() + "</td>";
                out += "<td>" + modeloTurnos.getHora_inicio() + "</td>";
                out += "<td>" + modeloTurnos.getHora_fin() + "</td>";
                out += "<td>" + modeloTurnos.getTeorico() + "</td>";
                out += "<td>" + modeloTurnos.getHora_inicioBreak() + "</td>";
                out += "<td>" + modeloTurnos.getHora_finBreak() + "</td>";
                out += "<td>" + modeloTurnos.getDescanso() + "</td>";
                out += "<td>" + modeloTurnos.getTiempo_maximo_entrada() + "</td>";
                out += "<td>" + modeloTurnos.getTiempo_minimo_salida() + "</td>";
                out += "<td>" + modeloTurnos.getTiempo_minimo_entrada() + "</td>";
                out += "<td>" + modeloTurnos.getTiempo_maximo_salida() + "</td>";
                out += "<td>" + modeloTurnos.getTolerancia_despues_entrada() + "</td>";
                out += "<td>" + modeloTurnos.getTolerancia_antes_salir() + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloTurnos.getId() + "\"";
                out += "data-codigo=\"" + modeloTurnos.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloTurnos.getDescripcion() + "\"";
                out += "data-horainicio=\"" + modeloTurnos.getHora_inicio() + "\"";
                out += "data-horafin=\"" + modeloTurnos.getHora_fin() + "\"";
                out += "data-teorico=\"" + modeloTurnos.getTeorico() + "\"";
                out += "data-turnonocturno=\"" + modeloTurnos.getTurno_noche() + "\"";
                out += "data-turnoextra=\"" + modeloTurnos.getTurno_extra() + "\"";
                out += "data-descuentobreak=\"" + modeloTurnos.getDescuentaBreak() + "\"";
                out += "data-horainiciobreak=\"" + modeloTurnos.getHora_inicioBreak() + "\"";
                out += "data-horafinbreak=\"" + modeloTurnos.getHora_finBreak() + "\"";
                out += "data-tiempobreak=\"" + modeloTurnos.getDescanso() + "\"";
                out += "data-tiempograciaae=\"" + modeloTurnos.getTiempo_maximo_entrada() + "\"";
                out += "data-tiempograciaas=\"" + modeloTurnos.getTiempo_minimo_salida() + "\"";
                out += "data-tiempograciade=\"" + modeloTurnos.getTiempo_minimo_entrada() + "\"";
                out += "data-tiempograciads=\"" + modeloTurnos.getTiempo_maximo_salida() + "\"";
                out += "data-aproximacionae=\"" + modeloTurnos.getTolerancia_despues_entrada() + "\"";
                out += "data-aproximacionds=\"" + modeloTurnos.getTolerancia_antes_salir() + "\"";
                out += "data-horainiciodiurno=\"" + modeloTurnos.getHora_inicio_diurno() + "\"";
                out += "data-horainicionocturno=\"" + modeloTurnos.getHora_inicio_nocturno() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloTurnos.getId() + "\"";
                out += "type=\"button\"><i id=\"IdClonar\" name=\"Clonar\" class=\"fa fa-trash\"></i> </button>";
                //Boton Clonar
                out += "<button class=\"SetFormulario btn btn-info btn-xs\"title=\"Clonar\"";
                out += "data-id=\"\"";
                out += "data-codigo=\"" + modeloTurnos.getCodigo() + "\"";
                out += "data-nombre=\"Turno Clonado\"";
                out += "data-horainicio=\"" + modeloTurnos.getHora_inicio() + "\"";
                out += "data-horafin=\"" + modeloTurnos.getHora_fin() + "\"";
                out += "data-teorico=\"" + modeloTurnos.getTeorico() + "\"";
                out += "data-turnonocturno=\"" + modeloTurnos.getTurno_noche() + "\"";
                out += "data-turnoextra=\"" + modeloTurnos.getTurno_extra() + "\"";
                out += "data-descuentobreak=\"" + modeloTurnos.getDescuentaBreak() + "\"";
                out += "data-horainiciobreak=\"" + modeloTurnos.getHora_inicioBreak() + "\"";
                out += "data-horafinbreak=\"" + modeloTurnos.getHora_finBreak() + "\"";
                out += "data-tiempobreak=\"" + modeloTurnos.getDescanso() + "\"";
                out += "data-tiempograciaae=\"" + modeloTurnos.getTiempo_maximo_entrada() + "\"";
                out += "data-tiempograciaas=\"" + modeloTurnos.getTiempo_minimo_entrada() + "\"";
                out += "data-tiempograciade=\"" + modeloTurnos.getTiempo_minimo_salida() + "\"";
                out += "data-tiempograciads=\"" + modeloTurnos.getTiempo_maximo_salida() + "\"";
                out += "data-aproximacionae=\"" + modeloTurnos.getTolerancia_despues_entrada() + "\"";
                out += "data-aproximacionds=\"" + modeloTurnos.getTolerancia_antes_salir() + "\"";
                out += "data-horainiciodiurno=\"" + modeloTurnos.getHora_inicio_diurno() + "\"";
                out += "data-horainicionocturno=\"" + modeloTurnos.getHora_inicio_nocturno() + "\"";
                out += "type=\"button\"><i id=\"IdClonar\" name=\"Clonar\" class=\"fa fa-copy\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }

    public String tmp(HttpServletRequest request) {
        ModeloTurnos modeloTurnos = new ModeloTurnos();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "id, "
                    + "codigo, "
                    + "descripcion,"
                    + "tipo_turno,"
                    + "hora_inicio,"
                    + "hora_fin,"
                    + "teorico,"
                    + "tolerancia_despues_entrada,"
                    + "tolerancia_antes_salir,"
                    + "tiempo_breack,"
                    + "limite_turno,"
                    + "gener_extras_entrada,"
                    + "tiempo_minimo_entrada,"
                    + "tiempo_maximo_entrada,"
                    + "genera_extras_salida,"
                    + "tiempo_minimo_salida,"
                    + "tiempo_maximo_salida,"
                    + "redondeo_entrada,"
                    + "sentido_entrada,"
                    + "redondeo_salida,"
                    + "sentido_salida,"
                    + "descanso,"
                    + "sentido_descanso,"
                    + "conceptos,"
                    + "sentido_concepto, "
                    + "hora_inicio_diurno, "
                    + "hora_inicio_nocturno, "
                    + "hora_inicio_break, "
                    + "hora_fin_break, "
                    + "descuenta_break, "
                    + "turno_extra, "
                    + "turno_noche "
                    + "FROM turnotiempos "
                    + "WHERE codigo = ? ORDER BY descripcion");
            SQL.setString(1, request.getParameter("codigo"));
            ResultSet res = SQL.executeQuery();
            while (res.next()) {                
                modeloTurnos.setId(res.getInt("id"));
                modeloTurnos.setCodigo(res.getString("codigo"));
                modeloTurnos.setDescripcion(res.getString("descripcion"));
                modeloTurnos.setTipo_turno(res.getString("tipo_turno"));
                modeloTurnos.setHora_inicio(res.getString("hora_inicio"));
                modeloTurnos.setHora_fin(res.getString("hora_fin"));
                modeloTurnos.setTeorico(res.getString("teorico"));
                modeloTurnos.setTolerancia_despues_entrada(res.getString("tolerancia_despues_entrada"));
                modeloTurnos.setTolerancia_antes_salir(res.getString("tolerancia_antes_salir"));
                modeloTurnos.setTiempo_breack(res.getString("tiempo_breack"));
                modeloTurnos.setLimite_turno(res.getString("limite_turno"));
                modeloTurnos.setGener_extras_entrada(res.getString("gener_extras_entrada"));
                modeloTurnos.setTiempo_minimo_entrada(res.getString("tiempo_minimo_entrada"));
                modeloTurnos.setTiempo_maximo_entrada(res.getString("tiempo_maximo_entrada"));
                modeloTurnos.setGenera_extras_salida(res.getString("genera_extras_salida"));
                modeloTurnos.setTiempo_minimo_salida(res.getString("tiempo_minimo_salida"));
                modeloTurnos.setTiempo_maximo_salida(res.getString("tiempo_maximo_salida"));
                modeloTurnos.setRedondeo_entrada(res.getString("redondeo_entrada"));
                modeloTurnos.setSentido_entrada(res.getString("sentido_entrada"));
                modeloTurnos.setRedondeo_salida(res.getString("redondeo_salida"));
                modeloTurnos.setSentido_salida(res.getString("sentido_salida"));
                modeloTurnos.setDescanso(res.getString("descanso"));
                modeloTurnos.setSentido_descanso(res.getString("sentido_descanso"));
                modeloTurnos.setConceptos(res.getString("conceptos"));
                modeloTurnos.setSentido_concepto(res.getString("sentido_concepto"));
                modeloTurnos.setHora_inicio_diurno(res.getString("hora_inicio_diurno"));
                modeloTurnos.setHora_inicio_nocturno(res.getString("hora_inicio_nocturno"));
                modeloTurnos.setHora_inicioBreak(res.getString("hora_inicio_break"));
                modeloTurnos.setHora_finBreak(res.getString("hora_fin_break"));
                modeloTurnos.setTurno_noche(res.getString("turno_noche"));
                modeloTurnos.setTurno_extra(res.getString("turno_extra"));
                modeloTurnos.setDescuentaBreak(res.getString("descuenta_break"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        resultado = new Gson().toJson(modeloTurnos);

        return resultado;
    }
}
