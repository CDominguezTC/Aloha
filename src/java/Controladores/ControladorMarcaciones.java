/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloEmpresa;
import Modelo.ModeloPersonas;
import Modelo.ModeloMarcaciones;
import Tools.Tools;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorMarcaciones {

    public boolean Insert(ModeloMarcaciones modeloMarcaciones) {
        Tools tools = new Tools();
        boolean resulInser = false;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            PreparedStatement SQL;
            SQL = con.prepareStatement("INSERT INTO marcacion(id_empleado,fecha_marcacion,"
                    + "estado_marcacion,nombre_dispositivo,observacion,observacionPersonal) VALUE (?,?,?,?,?,?)");
            SQL.setInt(1, modeloMarcaciones.getId_empleado());
            SQL.setString(2, tools.formaFechaHoraMarcaciones(modeloMarcaciones.getFecha_marcacion(), modeloMarcaciones.getHora_marcacion()));
            SQL.setString(3, modeloMarcaciones.getEstado_marcacion());
            SQL.setString(4, modeloMarcaciones.getNombre_dispositivo());
            SQL.setString(5, "MANUAL");
            SQL.setString(6, modeloMarcaciones.getObservacionPersonal());
            SQL.executeUpdate();
            resulInser = true;
            SQL.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el registro" + e);
        }
        return resulInser;
    }

    public boolean Update(ModeloMarcaciones modeloMarcaciones) {
        Tools tools = new Tools();
        boolean resulInser = false;
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
                        + "estado_marcacion = ?,nombre_dispositivo = ?,observacion = ?,observacionPersonal = ? WHERE id = ?;");
                SQL.setString(1, tools.formaFechaHoraMarcaciones(modeloMarcaciones.getFecha_marcacion(), modeloMarcaciones.getHora_marcacion()));
                SQL.setString(2, modeloMarcaciones.getEstado_marcacion());
                SQL.setString(3, modeloMarcaciones.getNombre_dispositivo());
                SQL.setString(4, "MODIFICADO");
                SQL.setString(5, modeloMarcaciones.getObservacionPersonal());
                SQL.setInt(6, modeloMarcaciones.getId());
            }
            SQL.executeUpdate();
            resulInser = true;
            SQL.close();
            con.close();
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
            PreparedStatement SQL = con.prepareStatement("SELECT id, id_empleado, fecha_marcacion, "
                    + "estado_marcacion, nombre_dispositivo, observacion, observacionPersonal FROM marcacion  where id = ?;");
            SQL.setInt(1, id);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloMarcaciones.setId(res.getInt("id"));
                modeloMarcaciones.setFecha_marcacion(res.getDate("fecha_marcacion"));
                modeloMarcaciones.setHora_marcacion(tools.formaHoraMarcaciones(res.getTime("fecha_marcacion")));
                modeloMarcaciones.setEstado_marcacion(res.getString("estado_marcacion"));
                modeloMarcaciones.setObservacion(res.getString("observacion"));
                modeloMarcaciones.setObservacionPersonal(res.getString("observacionPersonal"));

            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
        }
        return modeloMarcaciones;
    }

    public List<ModeloPersonas> Read(String Clave) {
        String forSql = "%" + Clave + "%";
        PreparedStatement SQL = null;
        List<ModeloPersonas> modeloEmpleados = new ArrayList<ModeloPersonas>();
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
                ModeloPersonas modeloEmpleado = new ModeloPersonas();
                modeloEmpleado.setId(res.getInt("id"));
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
                modeloEmpleado.setObservaciones(res.getString("observaciones"));
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

    public boolean Delete(ModeloPersonas modeloEmpleados) {
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

    public List<ModeloPersonas> Read(String IdDepen, String IdCentroCos, String IdEmpresa) {
        PreparedStatement SQL = null;
        List<ModeloPersonas> modeloEmpleados = new ArrayList<ModeloPersonas>();
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
                ModeloPersonas modeloEmpleado = new ModeloPersonas();
                modeloEmpleado.setId(res.getInt("id"));
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
                modeloEmpleado.setObservaciones(res.getString("observaciones"));
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

    public ModeloPersonas SearchId(String idPersona) {
        ModeloPersonas modeloEmpleados = new ModeloPersonas();
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
                modeloEmpleados.setTipoIdentificacion(res.getString("tipoIdentificacion"));
                modeloEmpleados.setIdentificacion(res.getString("identificacion"));
                modeloEmpleados.setNombres(res.getString("nombres"));
                modeloEmpleados.setApellidos(res.getString("apellidos"));
                modeloEmpleados.setTipoPersona(res.getString("tipoPersona"));
                modeloEmpleados.setCod_nomina(res.getString("cod_nomina"));
                modeloEmpleados.setId_Dependencias(res.getInt("id_Dependencias"));
                modeloEmpleados.setId_Empresa(res.getInt("id_Empresa"));
                modeloEmpleados.setId_Grupo_Horario(res.getInt("id_Grupo_Horario"));
                modeloEmpleados.setId_Areas(res.getInt("id_Areas"));
                modeloEmpleados.setId_Ciudad(res.getInt("id_Ciudad"));
                modeloEmpleados.setId_Centro_Costos(res.getInt("id_Centro_Costos"));
                modeloEmpleados.setObservaciones(res.getString("observaciones"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
        }
        return modeloEmpleados;

    }

    public List<ModeloMarcaciones> SearchMarcaciones(int id, Date FechaInicial, Date FechaFinal, boolean verInvalidos) {
        Tools tools = new Tools();
        PreparedStatement SQL = null;
        List<ModeloMarcaciones> modeloMarcaciones = new ArrayList<ModeloMarcaciones>();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            if (verInvalidos == false) {
                SQL = con.prepareStatement("SELECT id ,id_empleado, fecha_marcacion,estado_marcacion,nombre_dispositivo,observacion "
                        + "FROM marcacion WHERE id_empleado = ? AND fecha_marcacion >= ? AND fecha_marcacion <= ?  AND estado_marcacion <> 'Invalido' ORDER BY fecha_marcacion;");
            } else {
                SQL = con.prepareStatement("SELECT id ,id_empleado, fecha_marcacion,estado_marcacion,nombre_dispositivo,observacion "
                        + "FROM marcacion WHERE id_empleado = ? AND fecha_marcacion >= ? AND fecha_marcacion <= ?   ORDER BY fecha_marcacion;");
            }
            SQL.setInt(1, id);
            SQL.setString(2, tools.formatFechaIniConsulta(FechaInicial));
            SQL.setString(3, tools.formatFechaFinConsulta(FechaFinal));
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloMarcaciones modeloMarcacion = new ModeloMarcaciones();
                modeloMarcacion.setId(res.getInt("id"));
                modeloMarcacion.setId_empleado(res.getInt("id_empleado"));
                modeloMarcacion.setFecha_marcacion(res.getDate("fecha_marcacion"));
                modeloMarcacion.setHora_marcacion(tools.formaHoraMarcaciones(res.getTime("fecha_marcacion")));
                modeloMarcacion.setEstado_marcacion(res.getString("estado_marcacion"));
                modeloMarcacion.setNombre_dispositivo(res.getString("nombre_dispositivo"));
                modeloMarcacion.setObservacion(res.getString("observacion"));
                modeloMarcaciones.add(modeloMarcacion);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
        }
        return modeloMarcaciones;

    }
}
