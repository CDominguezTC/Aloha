//package Controladores;
//
//import Conexiones.ConexionBdMysql;
//import Modelo.ModeloPersona;
//import Modelo.ModeloIdentificacion;
//import java.util.List;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import javax.swing.JOptionPane;
//
///**
// * Esta clase permite controlar los eventos de Identificacion contrine Insert -
// * Update, Delete, Read
// *
// * @author: Carlos A Dominguez D
// * @version: 07/05/2020
// */
//public class ControladorIdentificacion {
//
//    /**
//     * Permite la inserción o actualización de los datos en la tabla Bd los
//     * Identificadores
//     *
//     * @author: Carlos A Dominguez D
//     * @param request
//     * @return String
//     * @version: 07/05/2020
//     */
//    public boolean Insert(ModeloIdentificacion modeloIdentificacion) {
//        boolean resulInser = false;
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try {
//            PreparedStatement SQL;
//            SQL = con.prepareStatement("INSERT INTO identificador(codidentificador,idpersona) VALUE (?,?);");
//            SQL.setString(1, modeloIdentificacion.getIdentificador());
//            SQL.setString(2, modeloIdentificacion.getIdPersona());
//            SQL.executeUpdate();
//            resulInser = true;
//            SQL.close();
//            con.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error al guardar el empleado " + e);
//        }
//        return resulInser;
//    }
//
//    public boolean Update(ModeloIdentificacion modeloIdentificacion) {
//        boolean resulInser = false;
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try {
//            PreparedStatement SQL;
//            SQL = con.prepareStatement("UPDATE identificador SET codidentificador = ?, idpersona = ? WHERE id = ?;");
//            SQL.setString(1, modeloIdentificacion.getIdentificador());
//            SQL.setString(2, modeloIdentificacion.getIdPersona());
//            SQL.setInt(3, modeloIdentificacion.getId());
//            SQL.executeUpdate();
//            resulInser = true;
//            SQL.close();
//            con.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error al actualizar el empleado " + e);
//        }
//        return resulInser;
//    }
//
//    public ModeloIdentificacion Search(String id) {
//        ModeloIdentificacion modeloIdentificacion = new ModeloIdentificacion();
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try {
//            PreparedStatement SQL = con.prepareStatement("SELECT id, codidentificador, idpersona FROM identificador"
//                    + " WHERE codidentificador = ?");
//            SQL.setString(1, id);
//            ResultSet res = SQL.executeQuery();
//            if (res.next()) {
//                modeloIdentificacion.setId(res.getInt("id"));
//                modeloIdentificacion.setIdentificador(res.getString("codidentificador"));
//                modeloIdentificacion.setIdPersona(res.getString("idpersona"));
//            }
//            res.close();
//            SQL.close();
//            con.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
//        }
//        return modeloIdentificacion;
//    }
//
//    public List<ModeloIdentificacion> Read(String Clave) {
//        String forSql = "%" + Clave + "%";
//        PreparedStatement SQL = null;
//        List<ModeloIdentificacion> modeloIdentificacions = new ArrayList<ModeloIdentificacion>();
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try {
//            if (Clave != null) {
//                SQL = con.prepareStatement("SELECT id, codidentificador, idpersona FROM identificador "
//                        + "WHERE codidentificador LIKE ? ORDER BY codidentificador");
//                SQL.setString(1, forSql);
//            } else {
//                SQL = con.prepareStatement("SELECT id, codidentificador, idpersona FROM identificador");
//            }
//            ResultSet res = SQL.executeQuery();
//            while (res.next()) {
//                ModeloIdentificacion modeloIdentificacion = new ModeloIdentificacion();
//                modeloIdentificacion.setId(res.getInt("id"));
//                modeloIdentificacion.setIdPersona(res.getString("idpersona"));
//                modeloIdentificacion.setIdentificador(res.getString("codidentificador"));
//                modeloIdentificacions.add(modeloIdentificacion);
//            }
//            res.close();
//            SQL.close();
//            con.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error buscandp el dato solicitado " + e);
//        }
//        return modeloIdentificacions;
//    }
//
//    public boolean Delete(ModeloIdentificacion modeloIdentificacion) {
//        boolean resulDelete = false;
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try {
//            PreparedStatement SQL = con.prepareStatement("DELETE FROM identificador WHERE id = ?;");
//            SQL.setInt(1, modeloIdentificacion.getId());
//            SQL.executeUpdate();
//            resulDelete = true;
//            SQL.close();
//            con.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error al borrar el empleado " + e);
//        }
//        return resulDelete;
//    }
//
//    public boolean Delete(ModeloPersona modeloEmpleados) {
//        boolean resulDelete = false;
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try {
//            PreparedStatement SQL = con.prepareStatement("DELETE FROM identificador WHERE idpersona = ?;");
//            SQL.setInt(1, modeloEmpleados.getId());
//            SQL.executeUpdate();
//            resulDelete = true;
//            SQL.close();
//            con.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error al borrar el empleado " + e);
//        }
//        return resulDelete;
//    }
//
//    public List<ModeloPersona> Read(String IdDepen, String IdCentroCos, String IdEmpresa) {
//        PreparedStatement SQL = null;
//        List<ModeloPersona> modeloEmpleados = new ArrayList<ModeloPersona>();
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try {
//            SQL = con.prepareStatement("SELECT id,tipoIdentificacion,identificacion,"
//                    + "nombres,apellidos,tipoPersona,cod_nomina,id_Dependencias,id_Empresa,id_Grupo_Horario,"
//                    + "id_Areas,id_Ciudad,id_Centro_Costos,observaciones FROM persona "
//                    + "WHERE id_Dependencias LIKE ? OR id_Centro_Costos LIKE ? OR id_Empresa LIKE ? AND tipoPersona LIKE ? ORDER BY nombres ");
//            if ("0".equals(IdDepen)) {
//                SQL.setString(1, null);
//            } else {
//                SQL.setString(1, IdDepen);
//            }
//
//            if ("0".equals(IdCentroCos)) {
//                SQL.setString(2, null);
//            } else {
//                SQL.setString(2, IdCentroCos);
//            }
//
//            if ("0".equals(IdEmpresa)) {
//                SQL.setString(3, null);
//            } else {
//                SQL.setString(3, IdEmpresa);
//            }
//            SQL.setString(4, "EMPLEADO");
//
//            ResultSet res = SQL.executeQuery();
//            while (res.next()) {
//                ModeloPersona modeloEmpleado = new ModeloPersona();
//                modeloEmpleado.setId(res.getInt("id"));
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
//            }
//            res.close();
//            SQL.close();
//            con.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error buscandp el dato solicitado " + e);
//        }
//        return modeloEmpleados;
//
//    }
//
//    public List<ModeloIdentificacion> SearchIdEmpleado(int id) {
//        List<ModeloIdentificacion> modeloIdentificaciones = new ArrayList<ModeloIdentificacion>();
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try {
//            PreparedStatement SQL = con.prepareStatement("SELECT id, codidentificador, idpersona FROM identificador"
//                    + " WHERE idpersona = ?");
//            SQL.setInt(1, id);
//            ResultSet res = SQL.executeQuery();
//            while (res.next()) {
//                ModeloIdentificacion modeloIdentificacion = new ModeloIdentificacion();
//                modeloIdentificacion.setId(res.getInt("id"));
//                modeloIdentificacion.setIdentificador(res.getString("codidentificador"));
//                modeloIdentificacion.setIdPersona(res.getString("idpersona"));
//                modeloIdentificaciones.add(modeloIdentificacion);
//            }
//            res.close();
//            SQL.close();
//            con.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
//        }
//        return modeloIdentificaciones;
//
//    }
//
//    public ModeloIdentificacion SearchIdentificador(String CodIdentificacion) {
//        ModeloIdentificacion modeloIdentificacion = new ModeloIdentificacion();
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try {
//            PreparedStatement SQL = con.prepareStatement("SELECT id, codidentificador, idpersona FROM identificador"
//                    + " WHERE codidentificador = ?");
//            SQL.setString(1, CodIdentificacion);
//            ResultSet res = SQL.executeQuery();
//            if (res.next()) {
//
//                modeloIdentificacion.setId(res.getInt("id"));
//                modeloIdentificacion.setIdentificador(res.getString("codidentificador"));
//                modeloIdentificacion.setIdPersona(res.getString("idpersona"));
//
//            }
//            res.close();
//            SQL.close();
//            con.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
//        }
//        return modeloIdentificacion;
//    }
//}
