package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloAuditoria;
import Modelo.ModeloUsuario;
import Tools.Tools;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import java.util.Map;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.util.Arrays;

//import static org.junit.Assert.*;
/**
 * Esta clase permite controlar los eventos de Auditoria
 *
 * @author Julian A Aristizabal
 * @version: 07/05/2020
 *
 */
public class ControladorAuditoria {

    String resultado = "";
    Connection con;
    String user = "";
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    ControladorLog_error log = new ControladorLog_error();
    ControladorInicioSesion controladorInicio;

    /**
     * Permite la inserción de los datos en la tabla Bd Auditoria
     *
     * @author Julian A Aristizabal
     * @param operacion
     * @param tabla
     * @param usua
     * @param idmodi
     * @param observacion
     * @return String
     * @version: 07/05/2020
     */
    public String Insert(String operacion, String tabla, String usua, int idmodi, String observacion, String d_old, String d_new) throws SQLException {

//        ModeloUsuario modeloUsuarioOld = new ModeloUsuario();
//        ControladorUsuario contra = new ControladorUsuario();
//        modeloUsuarioOld = contra.getModelo(1);
//        Object obj = modeloUsuarioOld;
//        try {
//            toStringArray(obj);
//        } catch (Exception e) {
//        }
        if ("".equals(user)) {
            user = controladorInicio.user_act;
        }
        con = conexion.abrirConexion();
        try {

            Tools tl = new Tools();
            ModeloUsuario modU = new ModeloUsuario();

            String fecha = tl.formatoFechaHora();
            ControladorUsuario controladorU = new ControladorUsuario();
            int idusua = controladorU.idUsuario(usua);

            modU = controladorU.getModelo(idusua);
            if (idmodi == 3001) {
                idmodi = idusua;
            }
            ModeloAuditoria modelo = new ModeloAuditoria(
                    0,
                    operacion,
                    tabla,
                    fecha,
                    modU,
                    idmodi,
                    observacion,
                    d_old,
                    d_new
            );
            try {
                con = conexion.abrirConexion();
                try {

                    SQL = con.prepareStatement("INSERT INTO `auditoria`("
                            + "`operacion`,"
                            + "`tabla`,"
                            + "`fecha`,"
                            + "`id_usuario`,"
                            + "`registro_modificado`,"
                            + "`observacion`,"
                            + "`dato_old`,"
                            + "`dato_new`) "
                            + "VALUES (?,?,?,?,?,?,?,?)");
                    SQL.setString(1, modelo.getOperacion());
                    SQL.setString(2, modelo.getTabla());
                    SQL.setString(3, modelo.getFecha());
                    SQL.setInt(4, modelo.getUsuario().getId());
                    SQL.setInt(5, modelo.getRegistro_modificado());
                    SQL.setString(6, modelo.getObservacion());
                    SQL.setString(7, modelo.getDato_old());
                    SQL.setString(8, modelo.getDato_new());
                    //String pw = tl.encriptar(modelo.getPassword());
                    //SQL.setString(3, pw);
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Error en el proceso: " + e.getMessage());
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error en el proceso: " + e.getMessage());
                log.insertarError(user, "Error Controladores.ControladorAuditoria.Insert(): " + e.getMessage());
                resultado = "-3";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAuditoria.class.getName()).log(Level.SEVERE, null, ex);
            log.insertarError(user, "Error Controladores.ControladorAuditoria.Insert(): " + ex.getMessage());
        }
        return resultado;
    }

    /**
     * La lista es para llenar el modelo con los datos de acuerdo al tipo de
     * consulta, el String es el que pinta el select y las opciones
     *
     * @author Julian A Aristizabal
     * @param request
     * @param response
     * @return String
     * @version: 07/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if ("".equals(user)) {
            user = controladorInicio.user_act;
        }
        String out = null;
        StringBuilder outsb = new StringBuilder();
        try {
            ControladorUsuario controladorU = new ControladorUsuario();
            LinkedList<ModeloUsuario> listmoUsr;
            listmoUsr = controladorU.Read("S");
            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<option value=\"\" disabled selected>Seleccione</option>";
            out += "<option value=\"todos\">Todos</option>";
            outsb.append(out);
            for (ModeloUsuario modeloUsua : listmoUsr) {
                //out += "<option value=\"" + modeloUsua.getId() + "\"> " + modeloUsua.getNombre() + "</option>";
                outsb.append("<option value=\"").append(modeloUsua.getId()).append("\"> ").append(modeloUsua.getNombre()).append("</option>");
            }
        } catch (Exception e) {
            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
            log.insertarError(user, "Error Controladores.ControladorAuditoria.ReadString(): " + e.getMessage());
        }
        return outsb.toString();
    }

    /**
     * Permite listar la información de la tabla de Auditoria Metodo Private
     *
     * @author Julian A Aristizabal
     * @param usr
     * @param fini
     * @param ffin
     * @return LinkedList
     * @version: 07/05/2020
     */
    private LinkedList<ModeloAuditoria> Read(String usr, String fini, String ffin) throws SQLException {

        if ("".equals(user)) {
            user = controladorInicio.user_act;
        }

        LinkedList<ModeloAuditoria> modeloAud = new LinkedList<ModeloAuditoria>();
        ModeloUsuario modU = new ModeloUsuario();
        ControladorUsuario controladorU = new ControladorUsuario();
        con = conexion.abrirConexion();
        Date date = null;
        Timestamp timestamp = null;
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if ("".equals(usr)) {
                SQL = con.prepareStatement("SELECT a.id, a.operacion, a.tabla, a.fecha, a.id_usuario, a.registro_modificado, a.observacion, a.dato_old, a.dato_new "
                        + "FROM auditoria a "
                        + "INNER JOIN usuario u ON u.id = a.id_usuario "
                        + "WHERE u.estado = 'S'");

            } else {
                if ("todos".equals(usr)) {
                    SQL = con.prepareStatement("SELECT a.id, a.operacion, a.tabla, a.fecha, a.id_usuario, a.registro_modificado, a.observacion, a.dato_old, a.dato_new "
                            + "FROM auditoria a "
                            + "INNER JOIN usuario u ON u.id = a.id_usuario "
                            + "WHERE u.estado = 'S' AND a.fecha BETWEEN '" + fini + " 00:00:00' AND '" + ffin + " 23:59:59'");
                } else {
                    SQL = con.prepareStatement("SELECT a.id, a.operacion, a.tabla, a.fecha, a.id_usuario, a.registro_modificado, a.observacion, a.dato_old, a.dato_new  "
                            + "FROM auditoria a "
                            + "INNER JOIN usuario u ON u.id = a.id_usuario "
                            + "WHERE u.estado = 'S' AND a.id_usuario = " + usr + " AND a.fecha BETWEEN '" + fini + " 00:00:00' AND '" + ffin + " 23:59:59'");
                }
            }

            ResultSet res = SQL.executeQuery();
            while (res.next()) {

                ModeloAuditoria modeloA = new ModeloAuditoria();
                modeloA.setId(res.getInt("id"));
                modeloA.setOperacion(res.getString("operacion"));
                modeloA.setTabla(res.getString("tabla"));
                timestamp = res.getTimestamp(4);
                if (timestamp != null) {
                    date = new java.util.Date(timestamp.getTime());
                    modeloA.setFecha(dt1.format(date));
                }
                modU = controladorU.getModelo(res.getInt("id_usuario"));
                modeloA.setUsuario(modU);
                modeloA.setRegistro_modificado(res.getInt("registro_modificado"));
                modeloA.setObservacion(res.getString("observacion"));
                modeloA.setDato_old(res.getString("dato_old"));
                modeloA.setDato_new(res.getString("dato_new"));
                modeloAud.add(modeloA);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error buscando el dato solicitado: " + e.getSQLState());
            System.out.println("");
            log.insertarError(user, "Error Controladores.ControladorAuditoria.ReadLista(): " + e.getMessage());
        }
        return modeloAud;
    }

    /**
     * Permite listar la información de la tabla de Auditoria
     *
     * @author Julian A Aristizabal
     * @param request
     * @param response
     * @param usr
     * @param ffin
     * @param fini
     * @return String
     * @version: 07/05/2020
     */
    public String readTabla(HttpServletRequest request, HttpServletResponse response, String usr, String fini, String ffin) throws ServletException, IOException {

        String out = null;
        StringBuilder outsb = new StringBuilder();
        try {

            LinkedList<ModeloAuditoria> listmoAu;
            listmoAu = Read(usr, fini, ffin);
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Operacion</th>";
            out += "<th>Tabla</th>";
            out += "<th>Fecha</th>";
            out += "<th>Usuario</th>";
            out += "<th>Registro Modificado</th>";
            out += "<th>Observacion</th>";
            out += "<th>Dato Anterior</th>";
            out += "<th>Dato Nuevo</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            outsb.append(out);
            for (ModeloAuditoria modeloA : listmoAu) {

                outsb.append("<tr>");
                outsb.append("<td>").append(modeloA.getOperacion()).append("</td>");
                outsb.append("<td>").append(modeloA.getTabla()).append("</td>");
                outsb.append("<td>").append(modeloA.getFecha()).append("</td>");
                outsb.append("<td>").append(modeloA.getUsuario().getNombre()).append("</td>");
                outsb.append("<td>").append(modeloA.getRegistro_modificado()).append("</td>");
                outsb.append("<td>").append(modeloA.getObservacion()).append("</td>");
                outsb.append("<td>").append(modeloA.getDato_old()).append("</td>");
                outsb.append("<td>").append(modeloA.getDato_new()).append("</td>");
                outsb.append("</tr>");
            }
            outsb.append("</tbody>");
            /*for (ModeloAuditoria modeloA : listmoAu) {
                out += "<tr>";
                out += "<td>" + modeloA.getOperacion() + "</td>";
                out += "<td>" + modeloA.getTabla() + "</td>";
                out += "<td>" + modeloA.getFecha() + "</td>";
                out += "<td>" + modeloA.getUsuario().getNombre() + "</td>";
                out += "<td>" + modeloA.getRegistro_modificado() + "</td>";
                out += "<td>" + modeloA.getObservacion() + "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
             */

        } catch (Exception e) {

            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }
        return outsb.toString();
    }

    /**
     * Recibe los modelos para procesar diferencias
     *
     * @author Julian A Aristizabal
     * @param modeloNew
     * @param modeloOld
     * @return String
     * @version: 9/09/2020
     */
    public void reciboModelos(String modeloNew, String modeloOld, String operacion, String tabla, String usua, int idmodi, String observacion) throws SQLException{

        if ("".equals(user)) {
            user = controladorInicio.user_act;
        }
        StringBuilder datoN = new StringBuilder();
        StringBuilder datoO = new StringBuilder();
        try {

            //String datoN = "", datoO = "";
            Map<String, Object> retMap = new Gson().fromJson(
                    modeloNew, new TypeToken<Map<String, Object>>() {
                    }.getType()
            );
            Map<String, Object> retMap2 = new Gson().fromJson(
                    modeloOld, new TypeToken<Map<String, Object>>() {
                    }.getType()
            );

            MapDifference<String, Object> difference = Maps.difference(retMap, retMap2);
            LinkedList<String> campos = new LinkedList<String>();
            LinkedList<String> datos = new LinkedList<String>();
            //System.out.println("\n\nEntries differing\n--------------------------");                   
            //difference.entriesDiffering().forEach((key, value) -> System.out.println(key + ": " + value.leftValue() + " - " + value.rightValue()));
            difference.entriesDiffering().forEach((key, value) -> campos.add(key));
            difference.entriesDiffering().forEach((key, value) -> datos.add(value.leftValue() + "," + value.rightValue()));

            List<String> items = null;
            for (int i = 0; i < campos.size(); i++) {
                //System.out.println(datos.get(i));
                items = Arrays.asList(datos.get(i).split("\\s*,\\s*"));
                if (items.get(0).startsWith("{")) {

                    for (int u = 1; u <= ((items.size() / 2) - 1); u++) {
                        if (items.get(u).contains("nombre")) {
                            datoN.append(items.get(u)).append(System.getProperty("line.separator"));
                            //datoO.append(items.get(4)).append(System.getProperty("line.separator"));
                        }
                    }
                    for (int w = ((items.size() / 2) + 1); w <= items.size(); w++) {
                        if (items.get(w).contains("nombre")) {
                            datoO.append(items.get(w)).append(System.getProperty("line.separator"));
                        }
                    }

                    //System.out.println("Ok");
                } else {
                    datoN.append(campos.get(i)).append("=").append(items.get(0)).append(System.getProperty("line.separator"));
                    datoO.append(campos.get(i)).append("=").append(items.get(1)).append(System.getProperty("line.separator"));
                }
            }
            
            try {
                Insert(operacion, tabla, usua, idmodi, observacion, datoO.toString(), datoN.toString());
            } catch (Exception e) {

                log.insertarError(user, "Error Controladores.ControladorAuditoria.reciboModelos(): " + e.getMessage());
                System.out.println("Error insertado: " + e.getMessage());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.insertarError(user, "Error Controladores.ControladorAuditoria.reciboModelos(): " + e.getMessage());
            System.out.println("Error insertado: " + e.getMessage());
        }
        /*System.out.println("datoN: " + datoN);
        System.out.println("datoO: " + datoO);*/

    }

    public String[] toStringArray(Object carts) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SQLException {

        List<Method> getters = getGetters(carts.getClass());
        String[] result = new String[getters.size()];
        for (int i = 0; i < getters.size(); i++) {
            Object value = getters.get(i).invoke(carts);
            //Object value3 = getters.get(i).getName();
            result[i] = String.valueOf(value);
        }

        for (int i = 0; i < getters.size(); i++) {
            System.out.println(result[i]);
        }

        return result;
    }

    private List<Method> getGetters(Class clazz) throws SecurityException {
        List<Method> getters = new ArrayList<Method>();

        for (Method method : clazz.getMethods()) {
            if (method.getName().startsWith("get") && !"getClass".equals(method.getName())) {
                getters.add(method);
            }
        }
        return getters;
    }
}
