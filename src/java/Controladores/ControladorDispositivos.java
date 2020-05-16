package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloDispositivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 * Esta clase permite controlar los eventos de Dispositivos
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorDispositivos {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * dispositivo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 14/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloDispositivo modeloDispositivo = new ModeloDispositivo();
        modeloDispositivo.setNumero(Integer.parseInt(request.getParameter("numero")));
        modeloDispositivo.setNombre(request.getParameter("nombre"));
        modeloDispositivo.setDireccion_ip(request.getParameter("direccion_ip"));
        modeloDispositivo.setPuerto(Integer.parseInt(request.getParameter("puerto")));
        modeloDispositivo.setModo(request.getParameter("modo"));
        modeloDispositivo.setIp_controladora(request.getParameter("ip_controladora"));
        modeloDispositivo.setPuerto_controladora(Integer.parseInt(request.getParameter("puerto_controladora")));
        modeloDispositivo.setTipo_lector(request.getParameter("tipo_lector"));
        modeloDispositivo.setActivo(request.getParameter("activo"));
        modeloDispositivo.setSerie(request.getParameter("serie"));
        modeloDispositivo.setLicencia(request.getParameter("licencia"));
        modeloDispositivo.setImpresora(request.getParameter("impresora"));
        modeloDispositivo.setEncabezado_impresion(request.getParameter("encabezado_impresion"));
        modeloDispositivo.setUtiliza_menu(request.getParameter("utiliza_menu"));
        modeloDispositivo.setEvento(request.getParameter("evento"));
        modeloDispositivo.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloDispositivo);
        } else {
            modeloDispositivo.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloDispositivo);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: dispositivo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param Modelo
     * @return String
     * @version: 14/05/2020
     */
    public String Insert(ModeloDispositivo modeloDispositivo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO dispositivo("
                        + "numero, "
                        + "nombre, "
                        + "direccion_ip, "
                        + "puerto, "
                        + "modo, "
                        + "ip_controladora, "
                        + "puerto_controladora, "
                        + "tipo_lector, "
                        + "activo, "
                        + "serie, "
                        + "licencia, "
                        + "impresora, "
                        + "encabezado_impresion, "
                        + "utiliza_menu, "
                        + "evento, "
                        + "estado)"
                        + " VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);", SQL.RETURN_GENERATED_KEYS);
                SQL.setInt(1, modeloDispositivo.getNumero());
                SQL.setString(2, modeloDispositivo.getNombre());
                SQL.setString(3, modeloDispositivo.getDireccion_ip());
                SQL.setInt(4, modeloDispositivo.getPuerto());
                SQL.setString(5, modeloDispositivo.getModo());
                SQL.setString(6, modeloDispositivo.getIp_controladora());
                SQL.setInt(7, modeloDispositivo.getPuerto_controladora());
                SQL.setString(8, modeloDispositivo.getTipo_lector());
                SQL.setString(9, modeloDispositivo.getActivo());
                SQL.setString(10, modeloDispositivo.getSerie());
                SQL.setString(11, modeloDispositivo.getLicencia());
                SQL.setString(12, modeloDispositivo.getImpresora());
                SQL.setString(13, modeloDispositivo.getEncabezado_impresion());
                SQL.setString(14, modeloDispositivo.getUtiliza_menu());
                SQL.setString(15, modeloDispositivo.getEvento());
                SQL.setString(16, "S");
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.");
                        }
                    }
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladordispositivo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladordispositivo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:dispositivo
     *
     * @param modeloDispositivo
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @return String
     * @version: 14/05/2020
     */
    public String Update(ModeloDispositivo modeloDispositivo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloDispositivo.getEstado())) {
                    SQL = con.prepareStatement("UPDATE dispositivo SET "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloDispositivo.getEstado());
                    SQL.setInt(2, modeloDispositivo.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE dispositivo SET "
                            + "numero = ?, "
                            + "nombre = ?, "
                            + "direccion_ip = ?, "
                            + "puerto = ?, "
                            + "modo = ?, "
                            + "ip_controladora = ?, "
                            + "puerto_controladora = ?, "
                            + "tipo_lector = ?, "
                            + "activo = ?, "
                            + "serie = ?, "
                            + "licencia = ?, "
                            + "impresora = ?, "
                            + "encabezado_impresion = ?, "
                            + "utiliza_menu = ?, "
                            + "evento = ? "                            
                            + " WHERE id = ? ");
                    SQL.setInt(1, modeloDispositivo.getNumero());
                    SQL.setString(2, modeloDispositivo.getNombre());
                    SQL.setString(3, modeloDispositivo.getDireccion_ip());
                    SQL.setInt(4, modeloDispositivo.getPuerto());
                    SQL.setString(5, modeloDispositivo.getModo());
                    SQL.setString(6, modeloDispositivo.getIp_controladora());
                    SQL.setInt(7, modeloDispositivo.getPuerto_controladora());
                    SQL.setString(8, modeloDispositivo.getTipo_lector());
                    SQL.setString(9, modeloDispositivo.getActivo());
                    SQL.setString(10, modeloDispositivo.getSerie());
                    SQL.setString(11, modeloDispositivo.getLicencia());
                    SQL.setString(12, modeloDispositivo.getImpresora());
                    SQL.setString(13, modeloDispositivo.getEncabezado_impresion());
                    SQL.setString(14, modeloDispositivo.getUtiliza_menu());
                    SQL.setString(15, modeloDispositivo.getEvento());
                    SQL.setInt(16, modeloDispositivo.getId());
                }

                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladordispositivo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladordispositivo" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Llena un Listado de la tabla dispositivo
     *
     * @param estado
     * @throws java.sql.SQLException
     * @author: Carlos Arturo Dominguez Diaz
     * @return LinkedList<ModeloDispositivo>
     * @version: 14/05/2020
     */
    public LinkedList<ModeloDispositivo> Read(String estado) throws SQLException {
        LinkedList<ModeloDispositivo> ListaModeloDispositivo = new LinkedList<ModeloDispositivo>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "numero, "
                    + "nombre, "
                    + "direccion_ip, "
                    + "puerto, "
                    + "modo, "
                    + "ip_controladora, "
                    + "puerto_controladora, "
                    + "tipo_lector, "
                    + "activo, "
                    + "serie, "
                    + "licencia, "
                    + "impresora, "
                    + "encabezado_impresion, "
                    + "utiliza_menu, "
                    + "evento, "
                    + "estado"
                    + " FROM dispositivo"
                    + " WHERE estado = ?");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloDispositivo modeloDispositivo = new ModeloDispositivo();
                modeloDispositivo.setId(res.getInt("id"));
                modeloDispositivo.setNumero(res.getInt("numero"));
                modeloDispositivo.setNombre(res.getString("nombre"));
                modeloDispositivo.setDireccion_ip(res.getString("direccion_ip"));
                modeloDispositivo.setPuerto(res.getInt("puerto"));
                modeloDispositivo.setModo(res.getString("modo"));
                modeloDispositivo.setIp_controladora(res.getString("ip_controladora"));
                modeloDispositivo.setPuerto_controladora(res.getInt("puerto_controladora"));
                modeloDispositivo.setTipo_lector(res.getString("tipo_lector"));
                modeloDispositivo.setActivo(res.getString("activo"));
                modeloDispositivo.setSerie(res.getString("serie"));
                modeloDispositivo.setLicencia(res.getString("licencia"));
                modeloDispositivo.setImpresora(res.getString("impresora"));
                modeloDispositivo.setEncabezado_impresion(res.getString("encabezado_impresion"));
                modeloDispositivo.setUtiliza_menu(res.getString("utiliza_menu"));
                modeloDispositivo.setEvento(res.getString("evento"));
                modeloDispositivo.setEstado(res.getString("estado"));
                ListaModeloDispositivo.add(modeloDispositivo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladordispositivo" + e);
        }
        return ListaModeloDispositivo;
    }

    /**
     * llena un modelo que viene con datos de un request para ser Eliminado
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 14/05/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloDispositivo modeloDispositivo = new ModeloDispositivo();
            modeloDispositivo.setId(Integer.parseInt(request.getParameter("id")));
            modeloDispositivo.setEstado("N");
            resultado = Update(modeloDispositivo);
        }
        return resultado;
    }

    /**
     * Elimina los datos en la base de datos de la tabla: dispositivo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 14/05/2020
     */
    public String DeleteModelo(ModeloDispositivo modeloDispositivo) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("DELETE FROM dispositivo "
                        + " WHERE id = ? ");
                SQL.setInt(1, modeloDispositivo.getId());
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Delete en Controladordispositivo" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Delete en Controladordispositivo" + e);
            resultado = "-3";
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
        String estado = "S";
        if (request.getParameter("estado") != null) {
            estado = "N";
        }
        try {
            LinkedList<ModeloDispositivo> listmodelo;
            listmodelo = Read(estado);
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
            for (ModeloDispositivo modelo : listmodelo) {
                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getNumero() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getNombre() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getDireccion_ip() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getPuerto() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getSerie() + "</td>";
                if ("1".equals(modelo.getUtiliza_menu())) {
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
                out += "data-nodispositivo=\"" + modelo.getNumero() + "\"";
                out += "data-nombre=\"" + modelo.getNombre() + "\"";
                out += "data-ip=\"" + modelo.getDireccion_ip() + "\"";
                out += "data-puertodispositivo=\"" + modelo.getPuerto() + "\"";
                out += "data-modo=\"" + modelo.getModo() + "\"";
                out += "data-tipolector=\"" + modelo.getTipo_lector() + "\"";
                out += "data-activo=\"" + modelo.getActivo() + "\"";
                out += "data-serie=\"" + modelo.getSerie() + "\"";
                out += "data-licencia=\"" + modelo.getLicencia() + "\"";
                out += "data-impresora=\"" + modelo.getImpresora() + "\"";
                out += "data-encabezadoimpresion=\"" + modelo.getEncabezado_impresion() + "\"";
                out += "data-utilizamenu=\"" + modelo.getUtiliza_menu() + "\"";
                out += "data-ipcontroladora=\"" + modelo.getIp_controladora() + "\"";
                out += "data-puertocontroladora=\"" + modelo.getPuerto_controladora() + "\"";
                out += "data-evento=\"" + modelo.getEvento() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i></button>";
                //Boton Eliminar                
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modelo.getId() + "\"";
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
     * Retorna un modelo de la tabla dispositivo dependiendo de un ID
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 14/05/2020
     */
    public ModeloDispositivo getModelo(Integer Id) throws SQLException {
        ModeloDispositivo modeloDispositivo = new ModeloDispositivo();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "numero, "
                    + "nombre, "
                    + "direccion_ip, "
                    + "puerto, "
                    + "modo, "
                    + "ip_controladora, "
                    + "puerto_controladora, "
                    + "tipo_lector, "
                    + "activo, "
                    + "serie, "
                    + "licencia, "
                    + "impresora, "
                    + "encabezado_impresion, "
                    + "utiliza_menu, "
                    + "evento, "
                    + "estado"
                    + " FROM dispositivo"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloDispositivo.setId(res.getInt("id"));
                modeloDispositivo.setNumero(res.getInt("numero"));
                modeloDispositivo.setNombre(res.getString("nombre"));
                modeloDispositivo.setDireccion_ip(res.getString("direccion_ip"));
                modeloDispositivo.setPuerto(res.getInt("puerto"));
                modeloDispositivo.setModo(res.getString("modo"));
                modeloDispositivo.setIp_controladora(res.getString("ip_controladora"));
                modeloDispositivo.setPuerto_controladora(res.getInt("puerto_controladora"));
                modeloDispositivo.setTipo_lector(res.getString("tipo_lector"));
                modeloDispositivo.setActivo(res.getString("activo"));
                modeloDispositivo.setSerie(res.getString("serie"));
                modeloDispositivo.setLicencia(res.getString("licencia"));
                modeloDispositivo.setImpresora(res.getString("impresora"));
                modeloDispositivo.setEncabezado_impresion(res.getString("encabezado_impresion"));
                modeloDispositivo.setUtiliza_menu(res.getString("utiliza_menu"));
                modeloDispositivo.setEvento(res.getString("evento"));
                modeloDispositivo.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladordispositivo" + e);
        }
        return modeloDispositivo;
    }

}
