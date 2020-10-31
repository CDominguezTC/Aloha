/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloPersona;
import Modelo.ModeloMarcaciones;
import Modelo.ModeloRegistro_tiempo;
import Tools.Tools;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorMarcaciones {
    
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        
        String resultado = "";
        ModeloMarcaciones modeloMarcaciones = new ModeloMarcaciones();
        modeloMarcaciones.setId_persona(Integer.parseInt(request.getParameter("idpersona")));
        modeloMarcaciones.setFecha_marcacion(request.getParameter("fecharegistro"));
        modeloMarcaciones.setEstado_marcacion(request.getParameter("estadomarcacion"));
        modeloMarcaciones.setObservacion_personal(request.getParameter("observacion"));
        
        if ("".equals(request.getParameter("id"))) {

            resultado = Insert(modeloMarcaciones, true);
            if("true".equals(resultado)){
                resultado = ReadMarcaciones(request, response);
            }
        } else {
            modeloMarcaciones.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloMarcaciones);
            if("true".equals(resultado)){
                resultado = ReadMarcaciones(request, response);
            }
        }
        return resultado;
        
    }

    public String Insert(ModeloMarcaciones modeloMarcaciones, boolean mmanual) {
        Tools tools = new Tools();
        String resulInser = "false";
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            PreparedStatement SQL;
            SQL = con.prepareStatement("INSERT INTO marcacion(id_persona, fecha_marcacion, "
                    + "estado_marcacion, nombre_dispositivo, observacion, observacion_personal) VALUE (?,?,?,?,?,?)");
            SQL.setInt(1, modeloMarcaciones.getId_persona());
            SQL.setString(2, modeloMarcaciones.getFecha_marcacion());
            SQL.setString(3, modeloMarcaciones.getEstado_marcacion());
            SQL.setString(4, modeloMarcaciones.getNombre_dispositivo());
            if(mmanual){
                SQL.setString(5, "MANUAL");
            }else{
                SQL.setString(5, null);
            }            
            SQL.setString(6, modeloMarcaciones.getObservacion_personal());
            if (SQL.executeUpdate() > 0) {
                resulInser = "true";
            }            
            SQL.close();
            con.close();
        } catch (SQLException e) {           
            System.err.println("Error en Controladores.ControladorMarcaciones.Insert(): " + e.getMessage());            
            resulInser = "-2";
        }
        return resulInser;
    }

    public String Update(ModeloMarcaciones modeloMarcaciones) {
        Tools tools = new Tools();
        String resulInser = "false";
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            PreparedStatement SQL;
            if ("Invalido".equals(modeloMarcaciones.getEstado_marcacion())) {
                SQL = con.prepareStatement("UPDATE marcacion SET estado_marcacion = ? WHERE id = ?;");
                SQL.setString(1, modeloMarcaciones.getEstado_marcacion());
                SQL.setInt(2, modeloMarcaciones.getId());
            } else {
                SQL = con.prepareStatement("UPDATE marcacion SET fecha_marcacion = ?,"
                        + "estado_marcacion = ?, observacion = ?, observacion_personal = ? WHERE id = ?;");
                SQL.setString(1, modeloMarcaciones.getFecha_marcacion());
                SQL.setString(2, modeloMarcaciones.getEstado_marcacion());
                //SQL.setString(3, modeloMarcaciones.getNombre_dispositivo());
                SQL.setString(3, "MODIFICADO");
                SQL.setString(4, modeloMarcaciones.getObservacion_personal());
                SQL.setInt(5, modeloMarcaciones.getId());
            }
            if (SQL.executeUpdate() > 0) {
                resulInser = "true";
                SQL.close();
                con.close();
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el registro " + e);
        }
        return resulInser;
    }

    public ModeloMarcaciones Search(int id) {
        Tools tools = new Tools();
        ModeloMarcaciones modeloMarcaciones = new ModeloMarcaciones();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            PreparedStatement SQL = con.prepareStatement("SELECT id, id_persona, fecha_marcacion, "
                    + "estado_marcacion, nombre_dispositivo, observacion, observacion_personal FROM marcacion where id = ?;");
            SQL.setInt(1, id);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloMarcaciones.setId(res.getInt("id"));
                modeloMarcaciones.setId(res.getInt("id_persona"));
                modeloMarcaciones.setFecha_marcacion(res.getString("fecha_marcacion"));
                //modeloMarcaciones.setHora_marcacion(tools.formaHoraMarcaciones(res.getTime("fecha_marcacion")));
                modeloMarcaciones.setEstado_marcacion(res.getString("estado_marcacion"));
                modeloMarcaciones.setEstado_marcacion(res.getString("nombre_dispositivo"));
                modeloMarcaciones.setObservacion(res.getString("observacion"));
                modeloMarcaciones.setObservacion_personal(res.getString("observacion_personal"));

            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
        }
        return modeloMarcaciones;
    }

    public List<ModeloPersona> Read(String Clave) {
        String forSql = "%" + Clave + "%";
        PreparedStatement SQL = null;
        List<ModeloPersona> modeloEmpleados = new ArrayList<ModeloPersona>();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            if (Clave != null) {
                SQL = con.prepareStatement("SELECT id,tipoIdentificacion,identificacion,"
                        + "nombres,apellidos,tipoPersona,cod_nomina,id_Dependencias,id_Empresa,id_Grupo_Horario,"
                        + "id_Areas,id_Ciudad,id_Centro_Costos,observaciones FROM persona "
                        + "WHERE identificacion LIKE ? OR nombres LIKE ? OR apellidos LIKE ? AND tipoPersona LIKE ? ORDER BY nombres ");
                SQL.setString(1, forSql);
                SQL.setString(2, forSql);
                SQL.setString(3, forSql);
                SQL.setString(4, "EMPLEADO");
            } else {
                SQL = con.prepareStatement("SELECT id,tipoIdentificacion,identificacion,"
                        + "nombres,apellidos,tipoPersona,cod_nomina,id_Dependencias,id_Empresa,id_Grupo_Horario,"
                        + "id_Areas,id_Ciudad,id_Centro_Costos,observaciones FROM persona WHERE tipoPersona LIKE ? ORDER BY nombres ");
                SQL.setString(1, "EMPLEADO");
            }
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloPersona modeloEmpleado = new ModeloPersona();
                /*modeloEmpleado.setId(res.getInt("id"));
                modeloEmpleado.setTipoIdentificacion(res.getString("tipoIdentificacion"));
                modeloEmpleado.setIdentificacion(res.getString("identificacion"));
                modeloEmpleado.setNombres(res.getString("nombres"));
                modeloEmpleado.setApellidos(res.getString("apellidos"));
                modeloEmpleado.setTipoPersona(res.getString("tipoPersona"));
                modeloEmpleado.setCod_nomina(res.getString("cod_nomina"));
                modeloEmpleado.setId_Dependencias(res.getInt("id_Dependencias"));
                modeloEmpleado.setId_Empresa(res.getInt("id_Empresa"));
                modeloEmpleado.setId_Grupo_Horario(res.getInt("id_Grupo_Horario"));
                modeloEmpleado.setId_Areas(res.getInt("id_Areas"));
                modeloEmpleado.setId_Ciudad(res.getInt("id_Ciudad"));
                modeloEmpleado.setId_Centro_Costos(res.getInt("id_Centro_Costos"));
                modeloEmpleado.setObservaciones(res.getString("observaciones"));*/
                modeloEmpleados.add(modeloEmpleado);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error buscandp el dato solicitado " + e);
        }
        return modeloEmpleados;
    }

    public boolean Delete(ModeloPersona modeloEmpleados) {
        boolean resulDelete = false;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            PreparedStatement SQL = con.prepareStatement("DELETE FROM persona WHERE id = ?;");
            SQL.setInt(1, modeloEmpleados.getId());
            SQL.executeUpdate();
            resulDelete = true;
            SQL.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar el empleado " + e);
        }
        return resulDelete;
    }

    public List<ModeloPersona> Read(String IdDepen, String IdCentroCos, String IdEmpresa) {
        PreparedStatement SQL = null;
        List<ModeloPersona> modeloEmpleados = new ArrayList<ModeloPersona>();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,tipoIdentificacion,identificacion,"
                    + "nombres,apellidos,tipoPersona,cod_nomina,id_Dependencias,id_Empresa,id_Grupo_Horario,"
                    + "id_Areas,id_Ciudad,id_Centro_Costos,observaciones FROM persona "
                    + "WHERE id_Dependencias LIKE ? OR id_Centro_Costos LIKE ? OR id_Empresa LIKE ? AND tipoPersona LIKE ? ORDER BY nombres ");
            if ("0".equals(IdDepen)) {
                SQL.setString(1, null);
            } else {
                SQL.setString(1, IdDepen);
            }

            if ("0".equals(IdCentroCos)) {
                SQL.setString(2, null);
            } else {
                SQL.setString(2, IdCentroCos);
            }

            if ("0".equals(IdEmpresa)) {
                SQL.setString(3, null);
            } else {
                SQL.setString(3, IdEmpresa);
            }
            SQL.setString(4, "EMPLEADO");

            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloPersona modeloEmpleado = new ModeloPersona();
                modeloEmpleado.setId(res.getInt("id"));
//                modeloEmpleado.setTipoIdentificacion(res.getString("tipoIdentificacion"));
//                modeloEmpleado.setIdentificacion(res.getString("identificacion"));
//                modeloEmpleado.setNombres(res.getString("nombres"));
//                modeloEmpleado.setApellidos(res.getString("apellidos"));
//                modeloEmpleado.setTipoPersona(res.getString("tipoPersona"));
//                modeloEmpleado.setCod_nomina(res.getString("cod_nomina"));
//                modeloEmpleado.setId_Dependencias(res.getInt("id_Dependencias"));
//                modeloEmpleado.setId_Empresa(res.getInt("id_Empresa"));
//                modeloEmpleado.setId_Grupo_Horario(res.getInt("id_Grupo_Horario"));
//                modeloEmpleado.setId_Areas(res.getInt("id_Areas"));
//                modeloEmpleado.setId_Ciudad(res.getInt("id_Ciudad"));
//                modeloEmpleado.setId_Centro_Costos(res.getInt("id_Centro_Costos"));
//                modeloEmpleado.setObservaciones(res.getString("observaciones"));
//                modeloEmpleados.add(modeloEmpleado);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error buscandp el dato solicitado " + e);
        }
        return modeloEmpleados;

    }

    public ModeloPersona SearchId(String idPersona) {
        ModeloPersona modeloEmpleados = new ModeloPersona();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            PreparedStatement SQL = con.prepareStatement("SELECT id, tipoIdentificacion,identificacion,"
                    + "nombres,apellidos,tipoPersona,cod_nomina,id_Dependencias,id_Empresa,id_Grupo_Horario,"
                    + "id_Areas,id_Ciudad,id_Centro_Costos,observaciones FROM persona WHERE id = ?");
            SQL.setString(1, idPersona);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloEmpleados.setId(res.getInt("id"));
//                modeloEmpleados.setTipoIdentificacion(res.getString("tipoIdentificacion"));
//                modeloEmpleados.setIdentificacion(res.getString("identificacion"));
//                modeloEmpleados.setNombres(res.getString("nombres"));
//                modeloEmpleados.setApellidos(res.getString("apellidos"));
//                modeloEmpleados.setTipoPersona(res.getString("tipoPersona"));
//                modeloEmpleados.setCod_nomina(res.getString("cod_nomina"));
//                modeloEmpleados.setId_Dependencias(res.getInt("id_Dependencias"));
//                modeloEmpleados.setId_Empresa(res.getInt("id_Empresa"));
//                modeloEmpleados.setId_Grupo_Horario(res.getInt("id_Grupo_Horario"));
//                modeloEmpleados.setId_Areas(res.getInt("id_Areas"));
//                modeloEmpleados.setId_Ciudad(res.getInt("id_Ciudad"));
//                modeloEmpleados.setId_Centro_Costos(res.getInt("id_Centro_Costos"));
//                modeloEmpleados.setObservaciones(res.getString("observaciones"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
        }
        return modeloEmpleados;

    }

    public LinkedList<ModeloMarcaciones> SearchMarcaciones(int id, String FechaInicial, String FechaFinal, boolean verInvalidos, boolean atras) {
        Tools tools = new Tools();
        PreparedStatement SQL = null;
        LinkedList<ModeloMarcaciones> modeloMarcaciones = new LinkedList<ModeloMarcaciones>();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            if (verInvalidos == true) {
                SQL = con.prepareStatement("SELECT id ,id_persona, fecha_marcacion, estado_marcacion, nombre_dispositivo, observacion, observacion_personal, estado "
                        //+ "FROM marcacion WHERE id_persona = ? AND fecha_marcacion >= ? AND fecha_marcacion <= ?  AND estado_marcacion <> 'Invalido' ORDER BY fecha_marcacion;");
                        + "FROM marcacion WHERE id_persona = ? AND fecha_marcacion BETWEEN ? AND ? AND estado = 'N' ORDER BY fecha_marcacion");
            } else {
                
                if(atras){
                    SQL = con.prepareStatement("SELECT id ,id_persona, fecha_marcacion, estado_marcacion, nombre_dispositivo, observacion, observacion_personal, estado "
                        + "FROM marcacion WHERE id_persona = ? AND estado_marcacion = 'Entrada' AND estado = 'S' AND fecha_marcacion BETWEEN ? AND ? ORDER BY fecha_marcacion DESC");
                }else{
                    SQL = con.prepareStatement("SELECT id ,id_persona, fecha_marcacion, estado_marcacion, nombre_dispositivo, observacion, observacion_personal, estado "
                        + "FROM marcacion WHERE id_persona = ? AND estado = 'S' AND fecha_marcacion BETWEEN ? AND ? ORDER BY fecha_marcacion");
                }
                
            }
            SQL.setInt(1, id);
            SQL.setString(2, FechaInicial);
            SQL.setString(3, FechaFinal);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloMarcaciones modeloMarcacion = new ModeloMarcaciones();
                modeloMarcacion.setId(res.getInt("id"));
                modeloMarcacion.setId_persona(res.getInt("id_persona"));
                modeloMarcacion.setFecha_marcacion(res.getString("fecha_marcacion"));
                //modeloMarcacion.setHora_marcacion(tools.formaHoraMarcaciones(res.getTime("fecha_marcacion")));
                modeloMarcacion.setEstado_marcacion(res.getString("estado_marcacion"));
                modeloMarcacion.setNombre_dispositivo(res.getString("nombre_dispositivo"));
                modeloMarcacion.setObservacion(res.getString("observacion"));
                modeloMarcacion.setObservacion_personal(res.getString("observacion_personal"));
                modeloMarcacion.setEstado(res.getString("estado"));
                modeloMarcaciones.add(modeloMarcacion);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            //JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
        }
        return modeloMarcaciones;

    }
    
    public LinkedList<ModeloRegistro_tiempo> searchMarcacionesRegTiempo() {
        
        LinkedList<ModeloRegistro_tiempo> modeloRegistroTiempo = new LinkedList<ModeloRegistro_tiempo>();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        PreparedStatement SQL = null;
        
        
        try {
            SQL = con.prepareStatement("SELECT id, codigo, CONCAT (fecha, \" \", hora) Fecha, campo1, campo2, campo3, campo4, dispositivo, estado "
                                     + "FROM registro_tiempo WHERE estado = 'S' ORDER by codigo, Fecha");
            
            ResultSet res = SQL.executeQuery();
            
            while (res.next()) {
                ModeloRegistro_tiempo modeloRegistroT = new ModeloRegistro_tiempo();
                modeloRegistroT.setId(res.getInt("id"));
                modeloRegistroT.setCodigo(res.getString("codigo"));
                modeloRegistroT.setFecha(res.getString("Fecha"));
                modeloRegistroT.setCampo1(res.getString("campo1"));
                modeloRegistroT.setCampo2(res.getString("campo2"));
                modeloRegistroT.setCampo3(res.getString("campo3"));
                modeloRegistroT.setCampo4(res.getString("campo4"));
                modeloRegistroT.setDispositivo(res.getString("dispositivo"));
                modeloRegistroT.setEstado(res.getString("estado"));
                modeloRegistroTiempo.add(modeloRegistroT);
            }
        } catch (SQLException e) {
            System.err.println("Error Controladores.ControladorMarcaciones.searchMarcacionesRegTiempo(): " + e.getMessage());           
        }
        
        return modeloRegistroTiempo;
    }
    
    public LinkedList<ModeloMarcaciones> buscoUltimaMarcacion(int id) {
        
        
        PreparedStatement SQL = null;
        LinkedList<ModeloMarcaciones> modeloMarcaciones = new LinkedList<ModeloMarcaciones>();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {                            
                
            SQL = con.prepareStatement("SELECT id, id_persona, fecha_marcacion, estado_marcacion, nombre_dispositivo, observacion, observacion_personal, estado "
                        + "FROM marcacion WHERE id_persona = ? AND estado = 'S' ORDER BY fecha_marcacion DESC LIMIT 1");
                                           
            SQL.setInt(1, id);            
            ResultSet res = SQL.executeQuery();
            
            while (res.next()) {
                ModeloMarcaciones modeloMarcacion = new ModeloMarcaciones();
                modeloMarcacion.setId(res.getInt("id"));
                modeloMarcacion.setId_persona(res.getInt("id_persona"));
                modeloMarcacion.setFecha_marcacion(res.getString("fecha_marcacion"));
                //modeloMarcacion.setHora_marcacion(tools.formaHoraMarcaciones(res.getTime("fecha_marcacion")));
                modeloMarcacion.setEstado_marcacion(res.getString("estado_marcacion"));
                modeloMarcacion.setNombre_dispositivo(res.getString("nombre_dispositivo"));
                modeloMarcacion.setObservacion(res.getString("observacion"));
                modeloMarcacion.setObservacion_personal(res.getString("observacion_personal"));
                modeloMarcacion.setEstado(res.getString("estado"));
                modeloMarcaciones.add(modeloMarcacion);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            //JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
        }
        return modeloMarcaciones;

    }
    
    public String ReadMarcaciones(HttpServletRequest request, HttpServletResponse response) {
        
        Tools tl = new Tools();
        System.out.println(tl.modoMarcaciones());
        
        String out = null;
        String id = request.getParameter("idpersona");
        String fecha_inicial = request.getParameter("fecha_inicial");
        String fecha_final = request.getParameter("fecha_final");
        String ver_Invalidos = request.getParameter("ver_invalidos");
        StringBuilder sbout = new StringBuilder();
        boolean ver_inv = false;

        if ("true".equals(ver_Invalidos)) {
            ver_inv = true;
        }

        try {
            LinkedList<ModeloMarcaciones> listaMarcaciones = new LinkedList<ModeloMarcaciones>();
            listaMarcaciones = SearchMarcaciones(Integer.parseInt(id), fecha_inicial, fecha_final, ver_inv, false);
            response.setContentType("text/html;charset=UTF-8");
                                                                        
            out = "";
            /*out += "<thead>";
            out += "<tr>";
            out += "<th class=\"size\">Seleccione</th>";
            out += "<th>Fecha</th>";
            out += "<th>Hora Entrada</th>";
            out += "<th>Hora Salida</th>";
            out += "<th>Sentido</th>";
            out += "<th class=\"size\">Detalle</th>";
            out += "</tr>";
            out += "</thead>";*/
            out += "<tbody>";
            sbout.append(out);
            //int contador = 1;
            if(ver_inv){
                
                for (ModeloMarcaciones modeloMarcaciones : listaMarcaciones) {
                    sbout.append("<tr>");                    
                    sbout.append("<td>" + modeloMarcaciones.getFecha_marcacion().substring(0, 10) + "</td>");
                    sbout.append("<td class=\"editarMarIn\" data-idm=\"" + modeloMarcaciones.getId() + "\">" + modeloMarcaciones.getFecha_marcacion().substring(11, 16) + "</td>");
                    sbout.append("<td>Eliminada</td>");
                    sbout.append("<td>" + modeloMarcaciones.getNombre_dispositivo()+ "</td>");
                    sbout.append("<td>" + modeloMarcaciones.getObservacion_personal()+ "</td>");
                    sbout.append("</tr>");                    
                }
            }else{
                
            
                for (int i = 0; i < listaMarcaciones.size(); i++) {
                    sbout.append("<tr>");
                    //sbout.append("<td class=\"text-center\"><input type=\"checkbox\" class=\"flat\" value=\"" + listaMarcaciones.get(i).getId() + "\"></td>");
                    if(!sbout.toString().contains(" data-idm=\"" + listaMarcaciones.get(i).getId() + "\"")){
                        //System.out.println("No tiene");
                        if(i == 0 && "Salida".equals(listaMarcaciones.get(i).getEstado_marcacion())){

                            //System.out.println(listaMarcaciones.get(i).getFecha_marcacion().substring(0, 10));
                            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(listaMarcaciones.get(i).getFecha_marcacion().substring(0, 10));                                              
                            Calendar calendar = Calendar.getInstance();	
                            calendar.setTime(date1); // Configuramos la fecha que se recibe
                            calendar.add(Calendar.DAY_OF_MONTH, -1);  // numero de días a añadir, o restar en caso de días<0
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
                            String undiaAntes = dateFormat.format(calendar.getTime());

                            String undiaAntesIn = undiaAntes + " 00:00:00";
                            String undiaAntesFi = undiaAntes + " 23:59:59";
                            LinkedList<ModeloMarcaciones> listaMarcacionAtras = new LinkedList<ModeloMarcaciones>();
                            listaMarcacionAtras = SearchMarcaciones(Integer.parseInt(id), undiaAntesIn, undiaAntesFi, ver_inv, true);

                            if(!listaMarcacionAtras.isEmpty()){
                                sbout.append("<td>" + undiaAntes + "</td>");
                                sbout.append("<td class=\"editarMar\" data-idm=\"" + listaMarcacionAtras.get(0).getId() + "\" data-fecham=\"" + listaMarcacionAtras.get(0).getFecha_marcacion().substring(0, 10) + "\" data-horam=\"" + listaMarcacionAtras.get(0).getFecha_marcacion().substring(11, 16)+ "\" data-sentidom=\"" + listaMarcacionAtras.get(0).getEstado_marcacion()+ "\" data-observacionm=\"" + listaMarcacionAtras.get(0).getObservacion_personal()+ "\">" + listaMarcacionAtras.get(0).getFecha_marcacion().substring(11, 16) + "</td>");
                                sbout.append("<td>--:--</td>");
                                sbout.append("<td>" + listaMarcaciones.get(i).getNombre_dispositivo() + "</td>");
                                sbout.append("<td>" + listaMarcaciones.get(i).getObservacion_personal()+ "</td>");
                                sbout.append("</tr>");
                                sbout.append("<td>" + listaMarcaciones.get(i).getFecha_marcacion().substring(0, 10) + "</td>");
                                sbout.append("<td>--:--</td>");
                                sbout.append("<td class=\"editarMar\" data-idm=\"" + listaMarcaciones.get(i).getId() + "\" data-fecham=\"" + listaMarcaciones.get(i).getFecha_marcacion().substring(0, 10) + "\" data-horam=\"" + listaMarcaciones.get(i).getFecha_marcacion().substring(11, 16)+ "\" data-sentidom=\"" + listaMarcaciones.get(i).getEstado_marcacion()+ "\" data-observacionm=\"" + listaMarcaciones.get(i).getObservacion_personal()+ "\">" + listaMarcaciones.get(i).getFecha_marcacion().substring(11, 16) + "</td>");
                                sbout.append("<td>" + listaMarcaciones.get(i).getNombre_dispositivo() + "</td>");
                                sbout.append("<td>" + listaMarcaciones.get(i).getObservacion_personal()+ "</td>");
                                sbout.append("</tr>");
                            }else{
                                sbout.append("<td>" + listaMarcaciones.get(i).getFecha_marcacion().substring(0, 10) + "</td>");
                                sbout.append("<td>--:--</td>");
                                sbout.append("<td class=\"editarMar\" data-idm=\"" + listaMarcaciones.get(i).getId() + "\" data-fecham=\"" + listaMarcaciones.get(i).getFecha_marcacion().substring(0, 10) + "\" data-horam=\"" + listaMarcaciones.get(i).getFecha_marcacion().substring(11, 16)+ "\" data-sentidom=\"" + listaMarcaciones.get(i).getEstado_marcacion()+ "\" data-observacionm=\"" + listaMarcaciones.get(i).getObservacion_personal()+ "\">" + listaMarcaciones.get(i).getFecha_marcacion().substring(11, 16) + "</td>");
                                sbout.append("<td>" + listaMarcaciones.get(i).getNombre_dispositivo() + "</td>");
                                sbout.append("<td>" + listaMarcaciones.get(i).getObservacion_personal()+ "</td>");
                                sbout.append("</tr>");
                            }                                                              
                        }
                        if(!sbout.toString().contains(" data-idm=\"" + listaMarcaciones.get(i).getId() + "\"")){


                            sbout.append("<td>" + listaMarcaciones.get(i).getFecha_marcacion().substring(0, 10) + "</td>");

                            if("Entrada".equals(listaMarcaciones.get(i).getEstado_marcacion()) || "Entrada-Int".equals(listaMarcaciones.get(i).getEstado_marcacion()) ){
                                sbout.append("<td class=\"editarMar\" data-idm=\"" + listaMarcaciones.get(i).getId() + "\" data-fecham=\"" + listaMarcaciones.get(i).getFecha_marcacion().substring(0, 10) + "\" data-horam=\"" + listaMarcaciones.get(i).getFecha_marcacion().substring(11, 16)+ "\" data-sentidom=\"" + listaMarcaciones.get(i).getEstado_marcacion()+ "\" data-observacionm=\"" + listaMarcaciones.get(i).getObservacion_personal()+ "\">" + listaMarcaciones.get(i).getFecha_marcacion().substring(11, 16) + "</td>");
                                //listaMarcaciones.;
                                if(i < (listaMarcaciones.size() - 1)){
                                    if("Salida".equals(listaMarcaciones.get(i+1).getEstado_marcacion()) || "Salida-Int".equals(listaMarcaciones.get(i+1).getEstado_marcacion())){

                                        if(!listaMarcaciones.get(i).getFecha_marcacion().substring(8, 10).equals(listaMarcaciones.get(i+1).getFecha_marcacion().substring(8, 10))){
                                            sbout.append("<td>--:--</td>");
                                        }else{
                                            sbout.append("<td class=\"editarMar\" data-idm=\"" + listaMarcaciones.get(i+1).getId() + "\" data-fecham=\"" + listaMarcaciones.get(i+1).getFecha_marcacion().substring(0, 10) + "\" data-horam=\"" + listaMarcaciones.get(i+1).getFecha_marcacion().substring(11, 16)+ "\" data-sentidom=\"" + listaMarcaciones.get(i+1).getEstado_marcacion()+ "\" data-observacionm=\"" + listaMarcaciones.get(i+1).getObservacion_personal()+ "\">" + listaMarcaciones.get(i+1).getFecha_marcacion().substring(11, 16) + "</td>");
                                        }

                                    }else{
                                        sbout.append("<td>--:--</td>");
                                    }

                                }else{
                                    sbout.append("<td>--:--</td>");
                                }

                            }else{                    
                                sbout.append("<td>--:--</td>");  
                                sbout.append("<td class=\"editarMar\" data-idm=\"" + listaMarcaciones.get(i).getId() + "\" data-fecham=\"" + listaMarcaciones.get(i).getFecha_marcacion().substring(0, 10) + "\" data-horam=\"" + listaMarcaciones.get(i).getFecha_marcacion().substring(11, 16)+ "\" data-sentidom=\"" + listaMarcaciones.get(i).getEstado_marcacion()+ "\" data-observacionm=\"" + listaMarcaciones.get(i).getObservacion_personal()+ "\">" + listaMarcaciones.get(i).getFecha_marcacion().substring(11, 16) + "</td>");
                            }
                            //sbout.append("<td>--:--</td>");
                            sbout.append("<td>" + listaMarcaciones.get(i).getNombre_dispositivo() + "</td>");
                            sbout.append("<td>" + listaMarcaciones.get(i).getObservacion_personal()+ "</td>");
                            sbout.append("</tr>");
                        }
                    }
                    //System.out.println(sbout);
                }
            }
            //System.out.println(sbout);
            /*for (ModeloMarcaciones modeloMarcaciones : listaMarcaciones) {
                sbout.append("<tr>");
                sbout.append("<td class=\"text-center\"><input type=\"checkbox\" class=\"flat\" value=\"" + modeloMarcaciones.getId() + "\"></td>");
                sbout.append("<td>" + modeloMarcaciones.getFecha_marcacion().substring(0, 10) + "</td>");
                
                if("Entrada".equals(modeloMarcaciones.getEstado_marcacion()) || "Entrada-Int".equals(modeloMarcaciones.getEstado_marcacion()) ){
                    sbout.append("<td>" + modeloMarcaciones.getFecha_marcacion().substring(11) + "</td>");
                    //listaMarcaciones.;
                    if(contador < listaMarcaciones.size()){
                        if("Salida".equals(listaMarcaciones.get(contador).getEstado_marcacion()) || "Salida-Int".equals(listaMarcaciones.get(contador).getEstado_marcacion())){
                            sbout.append("<td>" + listaMarcaciones.get(contador).getFecha_marcacion().substring(11) + "</td>");
                        }else{
                            sbout.append("<td>--:--</td>");
                        }
                        
                    }
                                        
                }else{                    
                    sbout.append("<td>--:--</td>");  
                    sbout.append("<td>" + modeloMarcaciones.getFecha_marcacion().substring(11) + "</td>");
                }
                //sbout.append("<td>--:--</td>");
                sbout.append("<td>" + modeloMarcaciones.getEstado_marcacion() + "</td>");
                sbout.append("<td>" + modeloMarcaciones.getObservacion() + "</td>");
                sbout.append("</tr>");
                
                contador++;
            }*/
            sbout.append("</tbody>");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return sbout.toString();
    }
    
    public String borrarMarcacion(String idm, HttpServletRequest request, HttpServletResponse response) {                
        
        try {
            PreparedStatement SQL = null;        
            Connection con;
            ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
            con = conexionBdMysql.abrirConexion();
            if("false".equals(request.getParameter("activar_mar"))){
                SQL = con.prepareStatement("UPDATE marcacion SET estado = 'N' WHERE id = ?");
            }else{
                SQL = con.prepareStatement("UPDATE marcacion SET estado = 'S' WHERE id = ?");
            }
            
            SQL.setString(1, idm);
            
            if (SQL.executeUpdate() > 0) {
                return ReadMarcaciones(request, response);
            }
            
            
        } catch (SQLException e) {
            
            System.out.println("Controladores.ControladorMarcaciones.borrarMarcacion(): " + e.getMessage());
            return "-2";
        }        
        
        return "false";
    }
    
    public String inactivarMarcacion(int idm) {                
        
        try {
            PreparedStatement SQL = null;        
            Connection con;
            ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
            con = conexionBdMysql.abrirConexion();
            
            SQL = con.prepareStatement("UPDATE registro_tiempo SET estado = 'N' WHERE id = ?");
            SQL.setInt(1, idm);
            
            if (SQL.executeUpdate() > 0) {
                return "true";
            }
            
            
        } catch (SQLException e) {
            
            System.out.println("Controladores.ControladorMarcaciones.borrarMarcacion(): " + e.getMessage());
            return "-2";
        }        
        
        return "false";
    }
    /*public String insertarMarcacion(HttpServletRequest request, HttpServletResponse response) {
        
        String id = request.getParameter("idpersona");
        String fecha_marcacion = request.getParameter("fecharegistro");
        String estado_marcacion = request.getParameter("estadomarcacion");
        String observacion = request.getParameter("observacion");
        
        try {
            PreparedStatement SQL = null;        
            Connection con;
            ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
            con = conexionBdMysql.abrirConexion();
            
            
            SQL = con.prepareStatement("INSERT INTO marcacion (id_persona, fecha_marcacion, estado_marcacion, nombre_dispositivo, "
                    + "observacion, observacion_personal)"
                    + "VALUES (?, ?, ?, ?, ?, ?)");
            SQL.setString(1, id);
            SQL.setString(2, fecha_marcacion);
            SQL.setString(3, estado_marcacion);
            SQL.setString(5, observacion);
            
            if (SQL.executeUpdate() > 0) {
                SQL.close();
                return "true";
                //return ReadMarcaciones(request, response);
            }
            
            
        } catch (SQLException e) {
            
            System.out.println("Controladores.ControladorMarcaciones.insertarMarcacion(): " + e.getMessage());
            return "-2";
        }        
        
        return "false";
    }*/
}
