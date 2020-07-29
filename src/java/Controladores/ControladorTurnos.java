package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloTurno_tiempo;
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
import javax.servlet.http.HttpSession;

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
    String user;

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * turno_tiempo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 09/06/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloTurno_tiempo modeloTurno_tiempo = new ModeloTurno_tiempo();

        modeloTurno_tiempo.setId(0);
        modeloTurno_tiempo.setCodigo(request.getParameter("codigo"));
        modeloTurno_tiempo.setDescripcion(request.getParameter("nombre"));
        modeloTurno_tiempo.setHora_inicio(request.getParameter("horainicio"));
        modeloTurno_tiempo.setHora_fin(request.getParameter("horafin"));
        modeloTurno_tiempo.setTeorico(request.getParameter("teorico"));
        modeloTurno_tiempo.setHora_inicio_break(request.getParameter("horainiciobreak"));
        modeloTurno_tiempo.setHora_fin_break(request.getParameter("horafinbreak"));
        modeloTurno_tiempo.setDescanso(request.getParameter("tiempobreak"));
        modeloTurno_tiempo.setTiempo_maximo_entrada(request.getParameter("tiempograciaae"));
        modeloTurno_tiempo.setTiempo_minimo_salida(request.getParameter("tiempograciaas"));
        modeloTurno_tiempo.setTiempo_minimo_entrada(request.getParameter("tiempograciade"));
        modeloTurno_tiempo.setTiempo_maximo_salida(request.getParameter("tiempograciads"));
        modeloTurno_tiempo.setTolerancia_despues_entrada(request.getParameter("aproximacionae"));
        modeloTurno_tiempo.setTolerancia_antes_salir(request.getParameter("aproximacionds"));
        modeloTurno_tiempo.setHora_inicio_diurno(request.getParameter("horainiciodiurno"));
        modeloTurno_tiempo.setHora_inicio_nocturno(request.getParameter("horainicionocturno"));
        modeloTurno_tiempo.setTurno_noche(request.getParameter("turnonocturnos"));
        modeloTurno_tiempo.setTurno_extra(request.getParameter("turnoextra"));
        modeloTurno_tiempo.setDescuenta_break(request.getParameter("turnodescuento"));
        //Datos pendientes 2 Face
        modeloTurno_tiempo.setTipo_turno(request.getParameter("tipo_turno"));
        modeloTurno_tiempo.setTiempo_break(request.getParameter("tiempobreak"));
        modeloTurno_tiempo.setLimite_turno(request.getParameter("tiempobreak"));
        modeloTurno_tiempo.setGenera_extras_entrada(request.getParameter("genera_extras_entrada"));
        modeloTurno_tiempo.setGenera_extras_salida(request.getParameter("genera_extras_salida"));
        modeloTurno_tiempo.setRedondeo_entrada(request.getParameter("redondeo_entrada"));
        modeloTurno_tiempo.setSentido_entrada(request.getParameter("sentido_entrada"));
        modeloTurno_tiempo.setRedondeo_salida(request.getParameter("redondeo_salida"));
        modeloTurno_tiempo.setSentido_salida(request.getParameter("sentido_salida"));
        modeloTurno_tiempo.setSentido_descanso(request.getParameter("sentido_descanso"));
        modeloTurno_tiempo.setConceptos(request.getParameter("conceptos"));
        modeloTurno_tiempo.setSentido_concepto(request.getParameter("sentido_concepto"));
        modeloTurno_tiempo.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloTurno_tiempo);
        } else {
            modeloTurno_tiempo.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloTurno_tiempo);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: turno_tiempo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param Modelo
     * @return String
     * @version: 09/06/2020
     */
    public String Insert(ModeloTurno_tiempo modeloTurno_tiempo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO turno_tiempo("
                        + "codigo, "
                        + "descripcion, "
                        //+ "tipo_turno, "
                        + "hora_inicio, "
                        + "hora_fin, "
                        + "teorico, "
                        + "tolerancia_despues_entrada, "
                        + "tolerancia_antes_salir, "
                        //+ "tiempo_break, "
                        //+ "limite_turno, "
                        //+ "genera_extras_entrada, "
                        + "tiempo_minimo_entrada, "
                        + "tiempo_maximo_entrada, "
                        + "genera_extras_salida, "
                        + "tiempo_minimo_salida, "
                        + "tiempo_maximo_salida, "
                        //+ "redondeo_entrada, "
                        //+ "sentido_entrada, "
                        //+ "redondeo_salida, "
                        //+ "sentido_salida, "
                        + "descanso, "
                        //                        + "sentido_descanso, "
                        //                        + "conceptos, "
                        //                        + "sentido_concepto, "
                        + "hora_inicio_diurno, "
                        + "hora_inicio_nocturno, "
                        + "turno_noche, "
                        + "hora_inicio_break, "
                        + "hora_fin_break, "
                        + "descuenta_break, "
                        + "turno_extra, "
                        + "estado)"
                        + " VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloTurno_tiempo.getCodigo());
                SQL.setString(2, modeloTurno_tiempo.getDescripcion());
                //SQL.setString(3, modeloTurno_tiempo.getTipo_turno());
                SQL.setString(3, modeloTurno_tiempo.getHora_inicio());
                SQL.setString(4, modeloTurno_tiempo.getHora_fin());
                SQL.setString(5, modeloTurno_tiempo.getTeorico());
                SQL.setString(6, modeloTurno_tiempo.getTolerancia_despues_entrada());
                SQL.setString(7, modeloTurno_tiempo.getTolerancia_antes_salir());
//                SQL.setString(9, modeloTurno_tiempo.getTiempo_break());
//                SQL.setString(10, modeloTurno_tiempo.getLimite_turno());
//                SQL.setString(11, modeloTurno_tiempo.getGenera_extras_entrada());
                SQL.setString(8, modeloTurno_tiempo.getTiempo_minimo_entrada());
                SQL.setString(9, modeloTurno_tiempo.getTiempo_maximo_entrada());
                SQL.setString(10, modeloTurno_tiempo.getGenera_extras_salida());
                SQL.setString(11, modeloTurno_tiempo.getTiempo_minimo_salida());
                SQL.setString(12, modeloTurno_tiempo.getTiempo_maximo_salida());
//                SQL.setString(17, modeloTurno_tiempo.getRedondeo_entrada());
//                SQL.setString(18, modeloTurno_tiempo.getSentido_entrada());
//                SQL.setString(19, modeloTurno_tiempo.getRedondeo_salida());
//                SQL.setString(20, modeloTurno_tiempo.getSentido_salida());
                SQL.setString(13, modeloTurno_tiempo.getDescanso());
//                SQL.setString(22, modeloTurno_tiempo.getSentido_descanso());
//                SQL.setString(23, modeloTurno_tiempo.getConceptos());
//                SQL.setString(24, modeloTurno_tiempo.getSentido_concepto());
                SQL.setString(14, modeloTurno_tiempo.getHora_inicio_diurno());
                SQL.setString(15, modeloTurno_tiempo.getHora_inicio_nocturno());
                SQL.setString(16, modeloTurno_tiempo.getTurno_noche());
                SQL.setString(17, modeloTurno_tiempo.getHora_inicio_break());
                SQL.setString(18, modeloTurno_tiempo.getHora_fin_break());
                SQL.setString(19, modeloTurno_tiempo.getDescuenta_break());
                SQL.setString(20, modeloTurno_tiempo.getTurno_extra());
                SQL.setString(21, "S");
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
                System.out.println("Error en la consulta SQL Insert en Controladorturno_tiempo" + e.getMessage());
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorturno_tiempo" + e.getMessage());
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:turno_tiempo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 09/06/2020
     */
    public String Update(ModeloTurno_tiempo modeloTurno_tiempo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloTurno_tiempo.getEstado())) {
                    SQL = con.prepareStatement("UPDATE turno_tiempo SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloTurno_tiempo.getEstado());
                    SQL.setInt(2, modeloTurno_tiempo.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE turno_tiempo SET "
                            + "codigo = ?, "
                            + "descripcion = ?, "
                            //+ "tipo_turno = ?, "
                            + "hora_inicio = ?, "
                            + "hora_fin = ?, "
                            + "teorico = ?, "
                            + "tolerancia_despues_entrada = ?, "
                            + "tolerancia_antes_salir = ?, "
                            //                            + "tiempo_break = ?, "
                            //                            + "limite_turno = ?, "
                            //                            + "genera_extras_entrada = ?, "
                            + "tiempo_minimo_entrada = ?, "
                            + "tiempo_maximo_entrada = ?, "
                            // + "genera_extras_salida = ?, "
                            + "tiempo_minimo_salida = ?, "
                            + "tiempo_maximo_salida = ?, "
                            //                            + "redondeo_entrada = ?, "
                            //                            + "sentido_entrada = ?, "
                            //                            + "redondeo_salida = ?, "
                            //                            + "sentido_salida = ?, "
                            + "descanso = ?, "
                            //                            + "sentido_descanso = ?, "
                            //                            + "conceptos = ?, "
                            //                            + "sentido_concepto = ?, "
                            + "hora_inicio_diurno = ?, "
                            + "hora_inicio_nocturno = ?, "
                            + "turno_noche = ?, "
                            + "hora_inicio_break = ?, "
                            + "hora_fin_break = ?, "
                            + "descuenta_break = ?, "
                            + "turno_extra = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloTurno_tiempo.getCodigo());
                    SQL.setString(2, modeloTurno_tiempo.getDescripcion());
                    //SQL.setString(3, modeloTurno_tiempo.getTipo_turno());
                    SQL.setString(3, modeloTurno_tiempo.getHora_inicio());
                    SQL.setString(4, modeloTurno_tiempo.getHora_fin());
                    SQL.setString(5, modeloTurno_tiempo.getTeorico());
                    SQL.setString(6, modeloTurno_tiempo.getTolerancia_despues_entrada());
                    SQL.setString(7, modeloTurno_tiempo.getTolerancia_antes_salir());
                    //SQL.setString(9, modeloTurno_tiempo.getTiempo_break());
                    //SQL.setString(10, modeloTurno_tiempo.getLimite_turno());
                    //SQL.setString(11, modeloTurno_tiempo.getGenera_extras_entrada());
                    SQL.setString(8, modeloTurno_tiempo.getTiempo_minimo_entrada());
                    SQL.setString(9, modeloTurno_tiempo.getTiempo_maximo_entrada());
                    //SQL.setString(10, modeloTurno_tiempo.getGenera_extras_salida());
                    SQL.setString(10, modeloTurno_tiempo.getTiempo_minimo_salida());
                    SQL.setString(11, modeloTurno_tiempo.getTiempo_maximo_salida());
                    //SQL.setString(17, modeloTurno_tiempo.getRedondeo_entrada());
                    //SQL.setString(18, modeloTurno_tiempo.getSentido_entrada());
                    //SQL.setString(19, modeloTurno_tiempo.getRedondeo_salida());
                    //SQL.setString(20, modeloTurno_tiempo.getSentido_salida());
                    SQL.setString(12, modeloTurno_tiempo.getDescanso());
                    //SQL.setString(22, modeloTurno_tiempo.getSentido_descanso());
                    //SQL.setString(23, modeloTurno_tiempo.getConceptos());
                    //SQL.setString(24, modeloTurno_tiempo.getSentido_concepto());
                    SQL.setString(13, modeloTurno_tiempo.getHora_inicio_diurno());
                    SQL.setString(14, modeloTurno_tiempo.getHora_inicio_nocturno());
                    SQL.setString(15, modeloTurno_tiempo.getTurno_noche());
                    SQL.setString(16, modeloTurno_tiempo.getHora_inicio_break());
                    SQL.setString(17, modeloTurno_tiempo.getHora_fin_break());
                    SQL.setString(18, modeloTurno_tiempo.getDescuenta_break());
                    SQL.setString(19, modeloTurno_tiempo.getTurno_extra());
                    SQL.setInt(20, modeloTurno_tiempo.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "4";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorturno_tiempo" + e.getMessage());
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorturno_tiempo" + e.getMessage());
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
     * @version: 09/06/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloTurno_tiempo modeloTurno_tiempo = new ModeloTurno_tiempo();
            modeloTurno_tiempo.setId(Integer.parseInt(request.getParameter("id")));
            modeloTurno_tiempo.setEstado("N");
            resultado = Update(modeloTurno_tiempo);
            if (resultado.equals("4")) {
                resultado = "2";
            }
        }
        return resultado;
    }

    /**
     * Elimina los datos en la base de datos de la tabla: turno_tiempo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 09/06/2020
     */
    public String DeleteModelo(ModeloTurno_tiempo modeloTurno_tiempo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("DELETE FROM turno_tiempo "
                        + " WHERE id = ? ");
                SQL.setInt(1, modeloTurno_tiempo.getId());
                if (SQL.executeUpdate() > 0) {
                    resultado = "";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Delete en Controladorturno_tiempo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Delete en Controladorturno_tiempo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla turno_tiempo dependiendo de un ID
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 09/06/2020
     */
    public ModeloTurno_tiempo getModelo(Integer Id) {
        ModeloTurno_tiempo modeloTurno_tiempo = new ModeloTurno_tiempo();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "descripcion, "
                    + "tipo_turno, "
                    + "hora_inicio, "
                    + "hora_fin, "
                    + "teorico, "
                    + "tolerancia_despues_entrada, "
                    + "tolerancia_antes_salir, "
                    + "tiempo_break, "
                    + "limite_turno, "
                    + "genera_extras_entrada, "
                    + "tiempo_minimo_entrada, "
                    + "tiempo_maximo_entrada, "
                    + "genera_extras_salida, "
                    + "tiempo_minimo_salida, "
                    + "tiempo_maximo_salida, "
                    + "redondeo_entrada, "
                    + "sentido_entrada, "
                    + "redondeo_salida, "
                    + "sentido_salida, "
                    + "descanso, "
                    + "sentido_descanso, "
                    + "conceptos, "
                    + "sentido_concepto, "
                    + "hora_inicio_diurno, "
                    + "hora_inicio_nocturno, "
                    + "turno_noche, "
                    + "hora_inicio_break, "
                    + "hora_fin_break, "
                    + "descuenta_break, "
                    + "turno_extra, "
                    + "estado"
                    + " FROM turno_tiempo"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloTurno_tiempo.setId(res.getInt("id"));
                modeloTurno_tiempo.setCodigo(res.getString("codigo"));
                modeloTurno_tiempo.setDescripcion(res.getString("descripcion"));
                modeloTurno_tiempo.setTipo_turno(res.getString("tipo_turno"));
                modeloTurno_tiempo.setHora_inicio(res.getString("hora_inicio"));
                modeloTurno_tiempo.setHora_fin(res.getString("hora_fin"));
                modeloTurno_tiempo.setTeorico(res.getString("teorico"));
                modeloTurno_tiempo.setTolerancia_despues_entrada(res.getString("tolerancia_despues_entrada"));
                modeloTurno_tiempo.setTolerancia_antes_salir(res.getString("tolerancia_antes_salir"));
                modeloTurno_tiempo.setTiempo_break(res.getString("tiempo_break"));
                modeloTurno_tiempo.setLimite_turno(res.getString("limite_turno"));
                modeloTurno_tiempo.setGenera_extras_entrada(res.getString("genera_extras_entrada"));
                modeloTurno_tiempo.setTiempo_minimo_entrada(res.getString("tiempo_minimo_entrada"));
                modeloTurno_tiempo.setTiempo_maximo_entrada(res.getString("tiempo_maximo_entrada"));
                modeloTurno_tiempo.setGenera_extras_salida(res.getString("genera_extras_salida"));
                modeloTurno_tiempo.setTiempo_minimo_salida(res.getString("tiempo_minimo_salida"));
                modeloTurno_tiempo.setTiempo_maximo_salida(res.getString("tiempo_maximo_salida"));
                modeloTurno_tiempo.setRedondeo_entrada(res.getString("redondeo_entrada"));
                modeloTurno_tiempo.setSentido_entrada(res.getString("sentido_entrada"));
                modeloTurno_tiempo.setRedondeo_salida(res.getString("redondeo_salida"));
                modeloTurno_tiempo.setSentido_salida(res.getString("sentido_salida"));
                modeloTurno_tiempo.setDescanso(res.getString("descanso"));
                modeloTurno_tiempo.setSentido_descanso(res.getString("sentido_descanso"));
                modeloTurno_tiempo.setConceptos(res.getString("conceptos"));
                modeloTurno_tiempo.setSentido_concepto(res.getString("sentido_concepto"));
                modeloTurno_tiempo.setHora_inicio_diurno(res.getString("hora_inicio_diurno"));
                modeloTurno_tiempo.setHora_inicio_nocturno(res.getString("hora_inicio_nocturno"));
                modeloTurno_tiempo.setTurno_noche(res.getString("turno_noche"));
                modeloTurno_tiempo.setHora_inicio_break(res.getString("hora_inicio_break"));
                modeloTurno_tiempo.setHora_fin_break(res.getString("hora_fin_break"));
                modeloTurno_tiempo.setDescuenta_break(res.getString("descuenta_break"));
                modeloTurno_tiempo.setTurno_extra(res.getString("turno_extra"));
                modeloTurno_tiempo.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorturno_tiempo" + e);
        }
        return modeloTurno_tiempo;
    }

    /**
     * Llena un Listado de la tabla turno_tiempo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param vacio
     * @return LinkedList<ModeloTurno_tiempo>
     * @version: 09/06/2020
     */
    public LinkedList<ModeloTurno_tiempo> Read() throws SQLException {
        LinkedList<ModeloTurno_tiempo> ListaModeloTurno_tiempo = new LinkedList<ModeloTurno_tiempo>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "codigo, "
                    + "descripcion, "
                    + "tipo_turno, "
                    + "hora_inicio, "
                    + "hora_fin, "
                    + "teorico, "
                    + "tolerancia_despues_entrada, "
                    + "tolerancia_antes_salir, "
                    + "tiempo_break, "
                    + "limite_turno, "
                    + "genera_extras_entrada, "
                    + "tiempo_minimo_entrada, "
                    + "tiempo_maximo_entrada, "
                    + "genera_extras_salida, "
                    + "tiempo_minimo_salida, "
                    + "tiempo_maximo_salida, "
                    + "redondeo_entrada, "
                    + "sentido_entrada, "
                    + "redondeo_salida, "
                    + "sentido_salida, "
                    + "descanso, "
                    + "sentido_descanso, "
                    + "conceptos, "
                    + "sentido_concepto, "
                    + "hora_inicio_diurno, "
                    + "hora_inicio_nocturno, "
                    + "turno_noche, "
                    + "hora_inicio_break, "
                    + "hora_fin_break, "
                    + "descuenta_break, "
                    + "turno_extra, "
                    + "estado"
                    + " FROM turno_tiempo");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloTurno_tiempo modeloTurno_tiempo = new ModeloTurno_tiempo();
                modeloTurno_tiempo.setId(res.getInt("id"));
                modeloTurno_tiempo.setCodigo(res.getString("codigo"));
                modeloTurno_tiempo.setDescripcion(res.getString("descripcion"));
                modeloTurno_tiempo.setTipo_turno(res.getString("tipo_turno"));
                modeloTurno_tiempo.setHora_inicio(res.getString("hora_inicio"));
                modeloTurno_tiempo.setHora_fin(res.getString("hora_fin"));
                modeloTurno_tiempo.setTeorico(res.getString("teorico"));
                modeloTurno_tiempo.setTolerancia_despues_entrada(res.getString("tolerancia_despues_entrada"));
                modeloTurno_tiempo.setTolerancia_antes_salir(res.getString("tolerancia_antes_salir"));
                modeloTurno_tiempo.setTiempo_break(res.getString("tiempo_break"));
                modeloTurno_tiempo.setLimite_turno(res.getString("limite_turno"));
                modeloTurno_tiempo.setGenera_extras_entrada(res.getString("genera_extras_entrada"));
                modeloTurno_tiempo.setTiempo_minimo_entrada(res.getString("tiempo_minimo_entrada"));
                modeloTurno_tiempo.setTiempo_maximo_entrada(res.getString("tiempo_maximo_entrada"));
                modeloTurno_tiempo.setGenera_extras_salida(res.getString("genera_extras_salida"));
                modeloTurno_tiempo.setTiempo_minimo_salida(res.getString("tiempo_minimo_salida"));
                modeloTurno_tiempo.setTiempo_maximo_salida(res.getString("tiempo_maximo_salida"));
                modeloTurno_tiempo.setRedondeo_entrada(res.getString("redondeo_entrada"));
                modeloTurno_tiempo.setSentido_entrada(res.getString("sentido_entrada"));
                modeloTurno_tiempo.setRedondeo_salida(res.getString("redondeo_salida"));
                modeloTurno_tiempo.setSentido_salida(res.getString("sentido_salida"));
                modeloTurno_tiempo.setDescanso(res.getString("descanso"));
                modeloTurno_tiempo.setSentido_descanso(res.getString("sentido_descanso"));
                modeloTurno_tiempo.setConceptos(res.getString("conceptos"));
                modeloTurno_tiempo.setSentido_concepto(res.getString("sentido_concepto"));
                modeloTurno_tiempo.setHora_inicio_diurno(res.getString("hora_inicio_diurno"));
                modeloTurno_tiempo.setHora_inicio_nocturno(res.getString("hora_inicio_nocturno"));
                modeloTurno_tiempo.setTurno_noche(res.getString("turno_noche"));
                modeloTurno_tiempo.setHora_inicio_break(res.getString("hora_inicio_break"));
                modeloTurno_tiempo.setHora_fin_break(res.getString("hora_fin_break"));
                modeloTurno_tiempo.setDescuenta_break(res.getString("descuenta_break"));
                modeloTurno_tiempo.setTurno_extra(res.getString("turno_extra"));
                modeloTurno_tiempo.setEstado(res.getString("estado"));
                ListaModeloTurno_tiempo.add(modeloTurno_tiempo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorturno_tiempo" + e);
        }
        return ListaModeloTurno_tiempo;
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
            LinkedList<ModeloTurno_tiempo> listaModeloTurnos;
            listaModeloTurnos = Read();
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloTurno_tiempo modeloTurnos : listaModeloTurnos) {
                    out += "<option value=\"" + modeloTurnos.getId() + "\"> " + modeloTurnos.getDescripcion() + "</option>";
                }
            } else {

                out = "";
                out += "<thead>";
                out += "<tr>";
                out += "<td>Codigo</td>";
                out += "<td>Nombre</td>";
                out += "<td>Hora Ini</td>";
                out += "<td>Hora Fin</td>";
                out += "<td>Teorico</td>";
                out += "<td>Opciones</td>";
                out += "</tr>";
                out += "</thead>";
                out += "<tbody>";
                for (ModeloTurno_tiempo modeloTurnos : listaModeloTurnos) {
                    out += "<tr>";
                    out += "<td>" + modeloTurnos.getCodigo() + "</td>";
                    out += "<td>" + modeloTurnos.getDescripcion() + "</td>";
                    out += "<td>" + modeloTurnos.getHora_inicio() + "</td>";
                    out += "<td>" + modeloTurnos.getHora_fin() + "</td>";
                    out += "<td>" + modeloTurnos.getTeorico() + "</td>";                    
                    out += "<td class=\"text-center\">";
                    // Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                    out += "data-id=\"" + modeloTurnos.getId() + "\"";
                    out += "data-codigo=\"" + modeloTurnos.getCodigo() + "\"";
                    out += "data-nombre=\"" + modeloTurnos.getDescripcion() + "\"";
                    out += "data-horainicio=\"" + modeloTurnos.getHora_inicio() + "\"";
                    out += "data-horafin=\"" + modeloTurnos.getHora_fin() + "\"";
                    out += "data-teorico=\"" + modeloTurnos.getTeorico() + "\"";
                    out += "data-turnonocturno=\"" + modeloTurnos.getTurno_noche() + "\"";
                    out += "data-turnoextra=\"" + modeloTurnos.getTurno_extra() + "\"";
                    out += "data-descuentobreak=\"" + modeloTurnos.getDescuenta_break() + "\"";
                    out += "data-horainiciobreak=\"" + modeloTurnos.getHora_inicio_break() + "\"";
                    out += "data-horafinbreak=\"" + modeloTurnos.getHora_fin_break() + "\"";
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
                    out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                    out += "data-id=\"" + modeloTurnos.getId() + "\"";
                    out += "type=\"button\"><i id=\"IdClonar\" name=\"Clonar\" class=\"fa fa-trash\"></i> </button>";
                    //Boton Clonar
                    out += "<button class=\"SetFormulario btn btn-info btn-sm\"title=\"Clonar\"";
                    out += "data-id=\"\"";
                    out += "data-codigo=\"" + modeloTurnos.getCodigo() + "\"";
                    out += "data-nombre=\"Turno Clonado\"";
                    out += "data-horainicio=\"" + modeloTurnos.getHora_inicio() + "\"";
                    out += "data-horafin=\"" + modeloTurnos.getHora_fin() + "\"";
                    out += "data-teorico=\"" + modeloTurnos.getTeorico() + "\"";
                    out += "data-turnonocturno=\"" + modeloTurnos.getTurno_noche() + "\"";
                    out += "data-turnoextra=\"" + modeloTurnos.getTurno_extra() + "\"";
                    out += "data-descuentobreak=\"" + modeloTurnos.getDescuenta_break() + "\"";
                    out += "data-horainiciobreak=\"" + modeloTurnos.getHora_inicio_break() + "\"";
                    out += "data-horafinbreak=\"" + modeloTurnos.getHora_fin_break() + "\"";
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
            }
        } catch (Exception e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }
}
