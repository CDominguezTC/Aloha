package Controladores;

import Conexiones.ConexionBdMysql;
import Conexiones.Pool;
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
    String user;
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
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        
        ModeloTurnos modeloTurnos = new ModeloTurnos();        
        modeloTurnos.setCodigo(request.getParameter("codigo"));
        modeloTurnos.setDescripcion(request.getParameter("nombre"));
        modeloTurnos.setHora_inicio(request.getParameter("horainicio"));
        modeloTurnos.setHora_fin(request.getParameter("horafin"));
        modeloTurnos.setTeorico(request.getParameter("teorico"));
        modeloTurnos.setHora_inicioBreak(request.getParameter("horainiciobreak"));
        modeloTurnos.setHora_finBreak(request.getParameter("horafinbreak"));
        modeloTurnos.setDescanso(request.getParameter("tiempobreak"));
        modeloTurnos.setTiempo_maximo_entrada(request.getParameter("tiempograciaae"));
        modeloTurnos.setTiempo_minimo_salida(request.getParameter("tiempograciaas"));
        modeloTurnos.setTiempo_minimo_entrada(request.getParameter("tiempograciade"));
        modeloTurnos.setTiempo_maximo_salida(request.getParameter("tiempograciads"));
        modeloTurnos.setTolerancia_despues_entrada(request.getParameter("aproximacionae"));
        modeloTurnos.setTolerancia_antes_salir(request.getParameter("aproximacionds"));
        modeloTurnos.setHora_inicio_diurno(request.getParameter("horainiciodiurno"));
        modeloTurnos.setHora_inicio_nocturno(request.getParameter("horainicionocturno"));
        modeloTurnos.setTurno_noche(request.getParameter("turnonocturnos"));
        modeloTurnos.setTurno_extra(request.getParameter("turnoextra"));
        modeloTurnos.setDescuentaBreak(request.getParameter("turnodescuento"));
        
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloTurnos);
        } else {
            modeloTurnos.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloTurnos);
        }
        /*
        if ("".equals(request.getParameter("id"))) {
            
            try {
                con = conexion.abrirConexion();
                try {
                    
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
        */
        return resultado;
    }
    
    /**
     * Actualiza los datos en la base de datos de la tabla: turno_tiempo
     *
     * @author: Julian Aristizabal
     * @param request
     * @return String
     * @version: 14/07/2020
     */
    public String Insert(ModeloTurnos modeloT) throws SQLException {
        
        try {
            Pool metodospool = new Pool();            
            con = metodospool.dataSource.getConnection();
            
            SQL = con.prepareStatement("INSERT INTO turno_tiempo ("                                                
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
                    + "turno_extra) "                   
                    + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);", SQL.RETURN_GENERATED_KEYS);
            SQL.setString(1, modeloT.getCodigo());
            SQL.setString(2, modeloT.getDescripcion());
            SQL.setString(3, modeloT.getTipo_turno());
            SQL.setString(4, modeloT.getHora_inicio());
            SQL.setString(5, modeloT.getHora_fin());
            SQL.setString(6, modeloT.getTeorico());
            SQL.setString(7, modeloT.getTolerancia_despues_entrada());
            SQL.setString(8, modeloT.getTolerancia_antes_salir());
            SQL.setString(9, modeloT.getTiempo_breack());
            SQL.setString(10, modeloT.getLimite_turno());
            SQL.setString(11, modeloT.getGener_extras_entrada());            
            SQL.setString(12, modeloT.getTiempo_minimo_entrada());
            SQL.setString(13, modeloT.getTiempo_maximo_entrada());
            SQL.setString(14, modeloT.getGenera_extras_salida());            
            SQL.setString(15, modeloT.getTiempo_minimo_salida());
            SQL.setString(16, modeloT.getTiempo_maximo_salida());            
            SQL.setString(17, modeloT.getRedondeo_entrada());
            SQL.setString(18, modeloT.getSentido_entrada());
            SQL.setString(19, modeloT.getRedondeo_salida());
            SQL.setString(20, modeloT.getSentido_salida());            
            SQL.setString(21, modeloT.getDescanso());
            SQL.setString(22, modeloT.getSentido_descanso());            
            SQL.setString(23, modeloT.getConceptos());            
            SQL.setString(24, modeloT.getSentido_concepto());
            SQL.setString(25, modeloT.getHora_inicio_diurno());
            SQL.setString(26, modeloT.getHora_inicio_nocturno());
            SQL.setString(27, modeloT.getTurno_noche());
            SQL.setString(28, modeloT.getHora_inicioBreak());
            SQL.setString(29, modeloT.getHora_finBreak());
            SQL.setString(30, modeloT.getDescuentaBreak());
            SQL.setString(31, modeloT.getTurno_extra());            

            if (SQL.executeUpdate() > 0) {
                ControladorAuditoria auditoria = new ControladorAuditoria();
                try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int i = (int) generatedKeys.getLong(1);
                        auditoria.Insert("insertar", "turno_tiempo", user, i, "Se inserto el registro.");
                    }
                }
                resultado = "1";
                SQL.close();
                //con.close();
            }            
            
        } catch (Exception e) {
            System.out.println("Error en la consulta SQL Insert en ControladorTurnos: " + e.getMessage());
            resultado = "-2";
            SQL.close();
        }finally{
            try {
                if(con != null){
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error cerrando conexion en ControladorTurnos: " + e.getMessage());
                //JOptionPane.showMessageDialog(null, "Error en la funcion. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return resultado;
    }
    
    /**
     * Actualiza los datos en la base de datos de la tabla: turno_tiempo
     *
     * @author: Julian Aristizabal
     * @param request
     * @return String
     * @version: 14/07/2020
     */
    public String Update(ModeloTurnos modeloT) throws SQLException {
        
        Pool metodospool = new Pool();
        try {

            con = metodospool.dataSource.getConnection();
            try {

                if ("N".equals(modeloT.getEstado())) {
                    SQL = con.prepareStatement("UPDATE turno_tiempo SET "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloT.getEstado());
                    SQL.setInt(2, modeloT.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE turno_tiempo SET "
                            + "codigo = ?, "
                            + "descripcion = ?, "
                            + "tipo_turno = ?, "
                            + "hora_inicio = ?, "
                            + "hora_fin = ?, "
                            + "teorico = ?, "
                            + "tolerancia_despues_entrada = ?, "
                            + "tolerancia_antes_salir = ?, "
                            + "tiempo_break = ?, "
                            + "limite_turno = ?, "
                            + "genera_extras_entrada = ?, "
                            + "tiempo_minimo_entrada = ?, "
                            + "tiempo_maximo_entrada = ?, "
                            + "genera_extras_salida = ?, "
                            + "tiempo_minimo_salida = ?, "
                            + "tiempo_maximo_salida = ?, "
                            + "redondeo_entrada = ?, "
                            + "sentido_entrada = ?, "
                            + "redondeo_salida = ?, "
                            + "sentido_salida = ?, "
                            + "descanso = ?, "
                            + "sentido_descanso = ?, "
                            + "conceptos = ?, "
                            + "sentido_concepto = ?, "
                            + "hora_inicio_diurno = ?, "
                            + "hora_inicio_nocturno = ?, "
                            + "turno_noche = ?, "
                            + "hora_inicio_break = ?, "
                            + "hora_fin_break = ?, "
                            + "descuenta_break = ?, "
                            + "turno_extra = ? "                            
                            + "WHERE id = ? ");
                    
                    SQL.setString(1, modeloT.getCodigo());
                    SQL.setString(2, modeloT.getDescripcion());
                    SQL.setString(3, modeloT.getTipo_turno());
                    SQL.setString(4, modeloT.getHora_inicio());
                    SQL.setString(5, modeloT.getHora_fin());
                    SQL.setString(6, modeloT.getTeorico());
                    SQL.setString(7, modeloT.getTolerancia_despues_entrada());
                    SQL.setString(8, modeloT.getTolerancia_antes_salir());
                    SQL.setString(9, modeloT.getTiempo_breack());
                    SQL.setString(10, modeloT.getLimite_turno());
                    SQL.setString(11, modeloT.getGener_extras_entrada());            
                    SQL.setString(12, modeloT.getTiempo_minimo_entrada());
                    SQL.setString(13, modeloT.getTiempo_maximo_entrada());
                    SQL.setString(14, modeloT.getGenera_extras_salida());            
                    SQL.setString(15, modeloT.getTiempo_minimo_salida());
                    SQL.setString(16, modeloT.getTiempo_maximo_salida());            
                    SQL.setString(17, modeloT.getRedondeo_entrada());
                    SQL.setString(18, modeloT.getSentido_entrada());
                    SQL.setString(19, modeloT.getRedondeo_salida());
                    SQL.setString(20, modeloT.getSentido_salida());            
                    SQL.setString(21, modeloT.getDescanso());
                    SQL.setString(22, modeloT.getSentido_descanso());            
                    SQL.setString(23, modeloT.getConceptos());            
                    SQL.setString(24, modeloT.getSentido_concepto());
                    SQL.setString(25, modeloT.getHora_inicio_diurno());
                    SQL.setString(26, modeloT.getHora_inicio_nocturno());
                    SQL.setString(27, modeloT.getTurno_noche());
                    SQL.setString(28, modeloT.getHora_inicioBreak());
                    SQL.setString(29, modeloT.getHora_finBreak());
                    SQL.setString(30, modeloT.getDescuentaBreak());
                    SQL.setString(31, modeloT.getTurno_extra());
                    SQL.setInt(32, modeloT.getId());
                    
                }

                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    //con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en ControladorTurno: " + e.getMessage());
                resultado = "-2";
                SQL.close();
                //con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en ControladorTurno: " + e.getMessage());
            resultado = "-3";
        }finally{
            try {
                if(con != null){
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error cerrando conexion en ControladorTurnos: " + e.getMessage());
                //JOptionPane.showMessageDialog(null, "Error en la funcion. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        
        if (!"".equals(request.getParameter("id"))) {
            ModeloTurnos modeloEmpresa = new ModeloTurnos();
            modeloEmpresa.setId(Integer.parseInt(request.getParameter("id")));
            modeloEmpresa.setEstado("N");
            resultado = Update(modeloEmpresa);
        }
        return resultado;
        /*if (!"".equals(request.getParameter("id"))) {
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
        }*/
        
    }

    /**
     * Permite listar la información de la tabla de Turnos Metodo Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    private LinkedList<ModeloTurnos> Read(String estado) {
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
                    + "tiempo_break,"
                    + "limite_turno,"
                    + "genera_extras_entrada,"
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
                    + "FROM turno_tiempo WHERE estado = ? ORDER BY descripcion");
            SQL.setString(1, estado);
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
                modeloTurnos.setTiempo_breack(res.getString("tiempo_break"));
                modeloTurnos.setLimite_turno(res.getString("limite_turno"));
                modeloTurnos.setGener_extras_entrada(res.getString("genera_extras_entrada"));
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
        String estado = "S";
        StringBuilder outsb = new StringBuilder();
        try {
            LinkedList<ModeloTurnos> listaModeloTurnos;
            listaModeloTurnos = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                
                outsb.append("");
                outsb.append("<option value=\"0\" selected>Seleccione</option>");
                /*out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";*/
                
                for (ModeloTurnos modeloTurnos : listaModeloTurnos) {
                    outsb.append("<option value=\"").append(modeloTurnos.getId()).append("\"> ").append(modeloTurnos.getDescripcion()).append("</option>");
                    //out += "<option value=\"" + modeloTurnos.getId() + "\"> " + modeloTurnos.getDescripcion() + "</option>";
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
                outsb.append(out);
                for (ModeloTurnos modeloTurnos : listaModeloTurnos) {
                    
                    outsb.append("<tr>");
                    outsb.append("<td>").append(modeloTurnos.getCodigo()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getDescripcion()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getHora_inicio()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getHora_fin()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getTeorico()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getHora_inicioBreak()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getHora_finBreak()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getDescanso()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getTiempo_maximo_entrada()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getTiempo_minimo_salida()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getTiempo_minimo_entrada()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getTiempo_maximo_salida()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getTolerancia_despues_entrada()).append("</td>");
                    outsb.append("<td>").append(modeloTurnos.getTolerancia_antes_salir()).append("</td>");
                    outsb.append("<td class=\"text-center\">");
                    
                    // Boton Editar
                    outsb.append("<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"");
                    outsb.append("data-id=\"").append(modeloTurnos.getId()).append("\"");
                    outsb.append("data-codigo=\"").append(modeloTurnos.getCodigo()).append("\"");
                    outsb.append("data-nombre=\"").append(modeloTurnos.getDescripcion()).append("\"");
                    outsb.append("data-horainicio=\"").append(modeloTurnos.getHora_inicio()).append("\"");
                    outsb.append("data-horafin=\"").append(modeloTurnos.getHora_fin()).append("\"");
                    outsb.append("data-teorico=\"").append(modeloTurnos.getTeorico()).append("\"");
                    outsb.append("data-turnonocturno=\"").append(modeloTurnos.getTurno_noche()).append("\"");
                    outsb.append("data-turnoextra=\"").append(modeloTurnos.getTurno_extra()).append("\"");
                    outsb.append("data-descuentobreak=\"").append(modeloTurnos.getDescuentaBreak()).append("\"");
                    outsb.append("data-horainiciobreak=\"").append(modeloTurnos.getHora_inicioBreak()).append("\"");
                    outsb.append("data-horafinbreak=\"").append(modeloTurnos.getHora_finBreak()).append("\"");
                    outsb.append("data-tiempobreak=\"").append(modeloTurnos.getDescanso()).append("\"");
                    outsb.append("data-tiempograciaae=\"").append(modeloTurnos.getTiempo_maximo_entrada()).append("\"");
                    outsb.append("data-tiempograciaas=\"").append(modeloTurnos.getTiempo_minimo_salida()).append("\"");
                    outsb.append("data-tiempograciade=\"").append(modeloTurnos.getTiempo_minimo_entrada()).append("\"");
                    outsb.append("data-tiempograciads=\"").append(modeloTurnos.getTiempo_maximo_salida()).append("\"");
                    outsb.append("data-aproximacionae=\"").append(modeloTurnos.getTolerancia_despues_entrada()).append("\"");
                    outsb.append("data-aproximacionds=\"").append(modeloTurnos.getTolerancia_antes_salir()).append("\"");
                    outsb.append("data-horainiciodiurno=\"").append(modeloTurnos.getHora_inicio_diurno()).append("\"");
                    outsb.append("data-horainicionocturno=\"").append(modeloTurnos.getHora_inicio_nocturno()).append("\"");
                    outsb.append("type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>");                                       
                    
                    //Boton Eliminar
                    outsb.append("<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"");
                    outsb.append("data-id=\"").append(modeloTurnos.getId()).append("\"");
                    outsb.append("type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>");
                    
                    //Boton Clonar
                    outsb.append("<button class=\"SetFormulario btn btn-info btn-sm\"title=\"Clonar\"");
                    outsb.append("data-id=\"\"");
                    outsb.append("data-codigo=\"").append(modeloTurnos.getCodigo()).append("\"");
                    outsb.append("data-nombre=\"Turno Clonado\"");
                    outsb.append("data-horainicio=\"").append(modeloTurnos.getHora_inicio()).append("\"");
                    outsb.append("data-horafin=\"").append(modeloTurnos.getHora_fin()).append("\"");
                    outsb.append("data-teorico=\"").append(modeloTurnos.getTeorico()).append("\"");
                    outsb.append("data-turnonocturno=\"").append(modeloTurnos.getTurno_noche()).append("\"");
                    outsb.append("data-turnoextra=\"").append(modeloTurnos.getTurno_extra()).append("\"");
                    outsb.append("data-descuentobreak=\"").append(modeloTurnos.getDescuentaBreak()).append("\"");
                    outsb.append("data-horainiciobreak=\"").append(modeloTurnos.getHora_inicioBreak()).append("\"");
                    outsb.append("data-horafinbreak=\"").append(modeloTurnos.getHora_finBreak()).append("\"");
                    outsb.append("data-tiempobreak=\"").append(modeloTurnos.getDescanso()).append("\"");
                    outsb.append("data-tiempograciaae=\"").append(modeloTurnos.getTiempo_maximo_entrada()).append("\"");
                    outsb.append("data-tiempograciaas=\"").append(modeloTurnos.getTiempo_minimo_salida()).append("\"");
                    outsb.append("data-tiempograciade=\"").append(modeloTurnos.getTiempo_minimo_entrada()).append("\"");
                    outsb.append("data-tiempograciads=\"").append(modeloTurnos.getTiempo_maximo_salida()).append("\"");
                    outsb.append("data-aproximacionae=\"").append(modeloTurnos.getTolerancia_despues_entrada()).append("\"");
                    outsb.append("data-aproximacionds=\"").append(modeloTurnos.getTolerancia_antes_salir()).append("\"");
                    outsb.append("data-horainiciodiurno=\"").append(modeloTurnos.getHora_inicio_diurno()).append("\"");
                    outsb.append("data-horainicionocturno=\"").append(modeloTurnos.getHora_inicio_nocturno()).append("\"");
                    outsb.append("type=\"button\"><i id=\"IdClonar\" name=\"Clonar\" class=\"fa fa-copy\"></i> </button>");
                    outsb.append("</td>");
                    outsb.append("</tr>");
                    
                    /*
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
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
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
                    */
                }
                outsb.append("</tbody>");
                //out += "</tbody>";
            }
        } catch (Exception e) {
            System.out.println("Error en el proceso de la tabla: " + e.getMessage());
        }
        return outsb.toString();
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
                    + "tiempo_break,"
                    + "limite_turno,"
                    + "genera_extras_entrada,"
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
                modeloTurnos.setTiempo_breack(res.getString("tiempo_break"));
                modeloTurnos.setLimite_turno(res.getString("limite_turno"));
                modeloTurnos.setGener_extras_entrada(res.getString("genera_extras_entrada"));
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

    /**
     * Permite listar la información de la tabla turnos identificadno el ID
     *
     * @author: Carlos A Dominguez D
     * @param Id
     * @return ModeloEmpresa
     * @version: 11/05/2020
     */
    public ModeloTurnos getModelo(int Id) {
        ModeloTurnos modeloTurnos = new ModeloTurnos();;
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
                    + "tiempo_break,"
                    + "limite_turno,"
                    + "genera_extras_entrada,"
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
                    + "FROM turno_tiempo "
                    + "WHERE id = ? "
                    + "ORDER BY descripcion");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                res.first();
                modeloTurnos.setId(res.getInt("id"));
                modeloTurnos.setCodigo(res.getString("codigo"));
                modeloTurnos.setDescripcion(res.getString("descripcion"));
                modeloTurnos.setTipo_turno(res.getString("tipo_turno"));
                modeloTurnos.setHora_inicio(res.getString("hora_inicio"));
                modeloTurnos.setHora_fin(res.getString("hora_fin"));
                modeloTurnos.setTeorico(res.getString("teorico"));
                modeloTurnos.setTolerancia_despues_entrada(res.getString("tolerancia_despues_entrada"));
                modeloTurnos.setTolerancia_antes_salir(res.getString("tolerancia_antes_salir"));
                modeloTurnos.setTiempo_breack(res.getString("tiempo_break"));
                modeloTurnos.setLimite_turno(res.getString("limite_turno"));
                modeloTurnos.setGener_extras_entrada(res.getString("genera_extras_entrada"));
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
            } else {
                modeloTurnos.setId(0);
            }

            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return modeloTurnos;
    }
}
