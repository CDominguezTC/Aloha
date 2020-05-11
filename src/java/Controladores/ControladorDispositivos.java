package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloDispositivos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Esta clase permite controlar los eventos de Dispositivos
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorDispositivos {

    String resultado = "";

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd
     * Dispositivos
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Insert(HttpServletRequest request) {

        ModeloDispositivos modeloDispositivos = new ModeloDispositivos();
        try {
            System.out.println(request.getParameter("id"));
            if ("".equals(request.getParameter("id"))) {
                modeloDispositivos.setId(0);
                modeloDispositivos.setNumeroDispositivo(Integer.parseInt(request.getParameter("nodispositivo")));
                modeloDispositivos.setNombre(request.getParameter("nombre"));
                modeloDispositivos.setDireccionIP(request.getParameter("ip"));
                modeloDispositivos.setPuerto(request.getParameter("puertoDispositivo"));
                modeloDispositivos.setModo(request.getParameter("modo"));
                modeloDispositivos.setTipoLector(request.getParameter("tipolector"));
                modeloDispositivos.setActivo(request.getParameter("activo"));
                modeloDispositivos.setSerie(request.getParameter("serie"));
                modeloDispositivos.setLicencia(request.getParameter("licencia"));
                modeloDispositivos.setImpresora(request.getParameter("impresora"));
                modeloDispositivos.setEncabezadoImpresion(request.getParameter("encabezadoimpresion"));
                modeloDispositivos.setUtilizaMenu(request.getParameter("utilizamenu"));
                modeloDispositivos.setIpControladora(request.getParameter("ipcontroladora"));
                modeloDispositivos.setPuertoControladora(request.getParameter("puertocontroladora"));                
                modeloDispositivos.setEvento(request.getParameter("evento"));                
                Connection con;
                ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
                con = conexionBdMysql.abrirConexion();
                PreparedStatement SQL = null;
                SQL = con.prepareStatement("INSERT INTO `dispositivo`("
                        + "`numeroDispositivo`,"
                        + "`nombre`,"
                        + "`direccionIP`,"
                        + "`puerto`,"
                        + "`modo`,"
                        + "`ipControladora`,"
                        + "`puertoControladora`,"
                        + "`tipoLector`,"
                        + "`Activo`,"
                        + "`Serie`,"
                        + "`Licencia`,"
                        + "`Impresora`,"
                        + "`EncabezadoImpresion`,"
                        + "`UtilizaMenu`,"
                        + "`Evento`) "
                        + " VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);", SQL.RETURN_GENERATED_KEYS);
                SQL.setInt(1, modeloDispositivos.getNumeroDispositivo());
                SQL.setString(2, modeloDispositivos.getNombre());
                SQL.setString(3, modeloDispositivos.getDireccionIP());
                SQL.setString(4, modeloDispositivos.getPuerto());
                SQL.setString(5, modeloDispositivos.getModo());
                SQL.setString(6, modeloDispositivos.getIpControladora());
                SQL.setString(7, modeloDispositivos.getPuertoControladora());
                SQL.setString(8, modeloDispositivos.getTipoLector());
                SQL.setString(9, modeloDispositivos.getActivo());
                SQL.setString(10, modeloDispositivos.getSerie());
                SQL.setString(11, modeloDispositivos.getLicencia());
                SQL.setString(12, modeloDispositivos.getImpresora());
                SQL.setString(13, modeloDispositivos.getEncabezadoImpresion());
                SQL.setString(14, modeloDispositivos.getUtilizaMenu());
                SQL.setString(15, modeloDispositivos.getEvento());
                if (SQL.executeUpdate() > 0){
                    ControladorAuditoria auditoria = new ControladorAuditoria();                        
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int)generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "dispositivo", request.getParameter("nombreU"), i, "Se inserto el registro.");
                        }
                    }
                    resultado = "1";
                    SQL.close();
                    con.close();
                }

            } else {
                modeloDispositivos.setId(Integer.parseInt(request.getParameter("id")));
                modeloDispositivos.setNumeroDispositivo(Integer.parseInt(request.getParameter("nodispositivo")));
                modeloDispositivos.setNombre(request.getParameter("nombre"));
                modeloDispositivos.setDireccionIP(request.getParameter("ip"));
                modeloDispositivos.setPuerto(request.getParameter("puertoDispositivo"));
                modeloDispositivos.setModo(request.getParameter("modo"));
                modeloDispositivos.setTipoLector(request.getParameter("tipolector"));
                modeloDispositivos.setActivo(request.getParameter("activo"));
                modeloDispositivos.setSerie(request.getParameter("serie"));
                modeloDispositivos.setLicencia(request.getParameter("licencia"));
                modeloDispositivos.setImpresora(request.getParameter("impresora"));
                modeloDispositivos.setEncabezadoImpresion(request.getParameter("encabezadoimpresion"));
                modeloDispositivos.setIpControladora(request.getParameter("ipcontroladora"));
                modeloDispositivos.setPuertoControladora(request.getParameter("puertocontroladora"));
                modeloDispositivos.setUtilizaMenu(request.getParameter("utilizamenu"));
                modeloDispositivos.setEvento(request.getParameter("evento"));
                Connection con;
                ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
                con = conexionBdMysql.abrirConexion();

                PreparedStatement SQL;
                SQL = con.prepareStatement("UPDATE `dispositivo`  SET "
                        + "`numeroDispositivo` = ?,"
                        + "`nombre` = ?,"
                        + "`direccionIP` = ?,"
                        + "`puerto` = ?,"
                        + "`modo` = ?,"
                        + "`ipControladora` = ?,"
                        + "`puertoControladora` = ?,"
                        + "`tipoLector` = ?,"
                        + "`Activo` = ?,"
                        + "`Serie` = ?,"
                        + "`Licencia` = ?,"
                        + "`Impresora` = ?,"
                        + "`EncabezadoImpresion` = ?,"
                        + "`UtilizaMenu` = ?,"
                        + "`Evento` = ?"
                        + "WHERE `id` = ?;");
                SQL.setInt(1, modeloDispositivos.getNumeroDispositivo());
                SQL.setString(2, modeloDispositivos.getNombre());
                SQL.setString(3, modeloDispositivos.getDireccionIP());
                SQL.setString(4, modeloDispositivos.getPuerto());
                SQL.setString(5, modeloDispositivos.getModo());
                SQL.setString(6, modeloDispositivos.getIpControladora());
                SQL.setString(7, modeloDispositivos.getPuertoControladora());
                SQL.setString(8, modeloDispositivos.getTipoLector());
                SQL.setString(9, modeloDispositivos.getActivo());
                SQL.setString(10, modeloDispositivos.getSerie());
                SQL.setString(11, modeloDispositivos.getLicencia());
                SQL.setString(12, modeloDispositivos.getImpresora());
                SQL.setString(13, modeloDispositivos.getEncabezadoImpresion());
                SQL.setString(14, modeloDispositivos.getUtilizaMenu());
                SQL.setString(15, modeloDispositivos.getEvento());
                SQL.setInt(16, modeloDispositivos.getId());
                SQL.executeUpdate();
                resultado = "1";
                SQL.close();
                con.close();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la empresa " + e);
            resultado = "-1";
        }
        return resultado;
    }

    /**
     * Permite listar la información de la tabla de Dispositivos Metodo Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    private LinkedList<ModeloDispositivos> Read() {
        PreparedStatement SQL = null;
        LinkedList<ModeloDispositivos> modeloDispositivos = new LinkedList<ModeloDispositivos>();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`numeroDispositivo`,"
                    + "`nombre`,"
                    + "`direccionIP`,"
                    + "`puerto`,"
                    + "`modo`,"
                    + "`ipControladora`,"
                    + "`puertoControladora`,"
                    + "`tipoLector`,"
                    + "`Activo`,"
                    + "`Serie`,"
                    + "`Licencia`,"
                    + "`Impresora`,"
                    + "`EncabezadoImpresion`,"
                    + "`UtilizaMenu`, "
                    + "`Evento` "
                    + "FROM `dispositivo`;");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloDispositivos modeloDispositivoss = new ModeloDispositivos();
                modeloDispositivoss.setId(res.getInt("id"));
                modeloDispositivoss.setNumeroDispositivo(res.getInt("numeroDispositivo"));
                modeloDispositivoss.setNombre(res.getString("nombre"));
                modeloDispositivoss.setDireccionIP(res.getString("direccionIP"));
                modeloDispositivoss.setPuerto(res.getString("puerto"));
                modeloDispositivoss.setModo(res.getString("modo"));
                modeloDispositivoss.setIpControladora(res.getString("ipControladora"));
                modeloDispositivoss.setPuertoControladora(res.getString("puertoControladora"));
                modeloDispositivoss.setTipoLector(res.getString("tipoLector"));
                modeloDispositivoss.setActivo(res.getString("Activo"));
                modeloDispositivoss.setSerie(res.getString("Serie"));
                modeloDispositivoss.setLicencia(res.getString("Licencia"));
                modeloDispositivoss.setImpresora(res.getString("Impresora"));
                modeloDispositivoss.setEncabezadoImpresion(res.getString("EncabezadoImpresion"));
                modeloDispositivoss.setUtilizaMenu(res.getString("UtilizaMenu"));
                modeloDispositivoss.setEvento(res.getString("Evento"));
                modeloDispositivos.add(modeloDispositivoss);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error buscandp el dato solicitado " + e);
        }
        return modeloDispositivos;
    }

    /**
     * Permite la eliminar un dato en la tabla de Dispositivos
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Delete(HttpServletRequest request) {
        ModeloDispositivos modeloDispositivos = new ModeloDispositivos();
        modeloDispositivos.setId(Integer.parseInt(request.getParameter("id")));
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            PreparedStatement SQL = con.prepareStatement("DELETE FROM dispositivo WHERE id = ?;");
            SQL.setInt(1, modeloDispositivos.getId());
            SQL.executeUpdate();
            resultado = "2";
            SQL.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar la empresa " + e);
            resultado = "-2";
        }
        return resultado;
    }

    /**
     * Permite listar la información de la tabla de Dispositivos
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @param response
     * @return String
     * @version: 07/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        try {
            LinkedList<ModeloDispositivos> listmodelo;
            listmodelo = Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>NoDispositivo</th>";
            out += "<th>Nombre</th>";
            out += "<th>Ip</th>";
            out += "<th>Puerto</th>";
            out += "<th>Serie</th>";
            out += "<th>Utiliza Menu</th>";
            out += "<th>Activo</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloDispositivos modelo : listmodelo) {
                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getNumeroDispositivo() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getNombre() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getDireccionIP() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getPuerto() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getSerie() + "</td>";
                if ("1".equals(modelo.getUtilizaMenu())) {
                    out += "<td WIDTH = \"0\" HEIGHT=\"0\">Si</td>";
                } else {
                    out += "<td WIDTH = \"0\" HEIGHT=\"0\">No</td>";
                }
                if ("1".equals(modelo.getActivo())) {
                    out += "<td WIDTH = \"0\" HEIGHT=\"0\">Si</td>";
                } else {
                    out += "<td WIDTH = \"0\" HEIGHT=\"0\">No</td>";
                }
                out += "<td WIDTH = \"10\" HEIGHT=\"0\" class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modelo.getId() + "\"";
                out += "data-nodispositivo=\"" + modelo.getNumeroDispositivo() + "\"";
                out += "data-nombre=\"" + modelo.getNombre() + "\"";
                out += "data-ip=\"" + modelo.getDireccionIP() + "\"";
                out += "data-puertodispositivo=\"" + modelo.getPuerto() + "\"";
                out += "data-modo=\"" + modelo.getModo() + "\"";
                out += "data-tipolector=\"" + modelo.getTipoLector() + "\"";
                out += "data-activo=\"" + modelo.getActivo() + "\"";
                out += "data-serie=\"" + modelo.getSerie() + "\"";
                out += "data-licencia=\"" + modelo.getLicencia() + "\"";
                out += "data-impresora=\"" + modelo.getImpresora() + "\"";
                out += "data-encabezadoimpresion=\"" + modelo.getEncabezadoImpresion() + "\"";
                out += "data-utilizamenu=\"" + modelo.getUtilizaMenu() + "\"";
                out += "data-ipcontroladora=\"" + modelo.getIpControladora() + "\"";
                out += "data-puertocontroladora=\"" + modelo.getPuertoControladora() + "\"";
                out += "data-evento=\"" + modelo.getEvento() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i></button>";
                //Boton Eliminar                
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modelo.getId() + "\"";
                out += "data-nodispositivo=\"" + modelo.getNumeroDispositivo() + "\"";
                out += "data-nombre=\"" + modelo.getNombre() + "\"";
                out += "data-ip=\"" + modelo.getDireccionIP() + "\"";
                out += "data-puertodispositivo=\"" + modelo.getPuerto() + "\"";
                out += "data-modo=\"" + modelo.getModo() + "\"";
                out += "data-tipolector=\"" + modelo.getTipoLector() + "\"";
                out += "data-activo=\"" + modelo.getActivo() + "\"";
                out += "data-serie=\"" + modelo.getSerie() + "\"";
                out += "data-licencia=\"" + modelo.getLicencia() + "\"";
                out += "data-impresora=\"" + modelo.getImpresora() + "\"";
                out += "data-encabezadoimpresion=\"" + modelo.getEncabezadoImpresion() + "\"";
                out += "data-utilizamenu=\"" + modelo.getUtilizaMenu() + "\"";
                out += "data-ipcontroladora=\"" + modelo.getIpControladora() + "\"";
                out += "data-puertocontroladora=\"" + modelo.getPuertoControladora() + "\"";
                out += "data-evento=\"" + modelo.getEvento() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i></button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e) {
            System.out.println("Error en el kproceso de la tabla " + e.getMessage());
        }
        return out;
    }

    /**
     * Permite listar la información de la tabla de Dispositivos indetificado
     * por ID
     *
     * @author: Carlos A Dominguez D
     * @param Id
     * @return ModeloDispositivos
     * @version: 07/05/2020
     */
    public ModeloDispositivos getModelo(Integer Id) {
        ModeloDispositivos modeloDispositivoss = new ModeloDispositivos();
        PreparedStatement SQL = null;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`numeroDispositivo`,"
                    + "`nombre`,"
                    + "`direccionIP`,"
                    + "`puerto`,"
                    + "`modo`,"
                    + "`ipControladora`,"
                    + "`puertoControladora`,"
                    + "`tipoLector`,"
                    + "`Activo`,"
                    + "`Serie`,"
                    + "`Licencia`,"
                    + "`Impresora`,"
                    + "`EncabezadoImpresion`,"
                    + "`UtilizaMenu` "
                    + "`Evento` "
                    + "FROM `dispositivo`;");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloDispositivoss.setId(res.getInt("id"));
                modeloDispositivoss.setNumeroDispositivo(res.getInt("numeroDispositivo"));
                modeloDispositivoss.setNombre(res.getString("nombre"));
                modeloDispositivoss.setDireccionIP(res.getString("direccionIP"));
                modeloDispositivoss.setPuerto(res.getString("puerto"));
                modeloDispositivoss.setModo(res.getString("modo"));
                modeloDispositivoss.setIpControladora(res.getString("ipControladora"));
                modeloDispositivoss.setPuertoControladora(res.getString("puertoControladora"));
                modeloDispositivoss.setTipoLector(res.getString("tipoLector"));
                modeloDispositivoss.setActivo(res.getString("Activo"));
                modeloDispositivoss.setSerie(res.getString("Serie"));
                modeloDispositivoss.setLicencia(res.getString("Licencia"));
                modeloDispositivoss.setImpresora(res.getString("Impresora"));
                modeloDispositivoss.setEncabezadoImpresion(res.getString("EncabezadoImpresion"));
                modeloDispositivoss.setUtilizaMenu(res.getString("UtilizaMenu"));
                modeloDispositivoss.setEvento(res.getString("Evento"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error buscandp el dato solicitado " + e);
        }
        return modeloDispositivoss;
    }
}
