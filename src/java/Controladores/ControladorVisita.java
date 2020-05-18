/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloVisita;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorVisita {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;
    ControladorPersona controladorPersona = new ControladorPersona();
    ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
    ControladorPersona controladorPersona = new ControladorPersona();
    ControladorArea controladorArea = new ControladorArea();
    ControladorVehiculo controladorVehiculo = new ControladorVehiculo();
    ControladorUsuario controladorUsuario = new ControladorUsuario();
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * visita
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloVisita modeloVisita = new ModeloVisita();
        modeloVisita.setModelo_persona_visitante(controladorPersona.getModelo(Integer.parseInt(request.getParameter("Modelo_persona_visitante"))));
        modeloVisita.setModelo_empresa_visitante(controladorEmpresa.getModelo(Integer.parseInt(request.getParameter("Modelo_empresa_visitante"))));
        modeloVisita.setModelo_persona_visitada(controladorPersona.getModelo(Integer.parseInt(request.getParameter("Modelo_persona_visitada"))));
        modeloVisita.setModelo_area_visitada(controladorArea.getModelo(Integer.parseInt(request.getParameter("Modelo_area_visitada"))));
        modeloVisita.setTipo_visita(request.getParameter("tipo_visita"));
        modeloVisita.setNumero_tarjeta(request.getParameter("numero_tarjeta"));
        modeloVisita.setFecha_hora_entrada(request.getParameter("fecha_hora_entrada"));
        modeloVisita.setFecha_hora_salida(request.getParameter("fecha_hora_salida"));
        modeloVisita.setEstado_visita(request.getParameter("estado_visita"));
        modeloVisita.setModelo_vehiculo(controladorVehiculo.getModelo(Integer.parseInt(request.getParameter("Modelo_vehiculo"))));
        modeloVisita.setObservacion(request.getParameter("observacion"));
        modeloVisita.setModelo_usuario_ingreso(controladorUsuario.getModelo(Integer.parseInt(request.getParameter("Modelo_usuario_ingreso"))));
        modeloVisita.setModelo_usuario_salida(controladorUsuario.getModelo(Integer.parseInt(request.getParameter("Modelo_usuario_salida"))));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloVisita);
        } else {
            modeloVisita.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloVisita);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: visita
     *
     * @author: Diego Fernando Guzman
     * @param Modelo
     * @return String
     * @version: 11/05/2020
     */
    public String Insert(ModeloVisita modeloVisita) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO visita("
                        + "id_persona_visitante, "
                        + "id_empresa_visitante, "
                        + "id_persona_visitada, "
                        + "id_area_visitada, "
                        + "tipo_visita, "
                        + "numero_tarjeta, "
                        + "fecha_hora_entrada, "
                        + "fecha_hora_salida, "
                        + "estado_visita, "
                        + "id_vehiculo, "
                        + "observacion, "
                        + "id_usuario_ingreso, "
                        + "id_usuario_salida)"
                        + " VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setInt(1, modeloVisita.getModelo_persona_visitante().getId());
                SQL.setInt(2, modeloVisita.getModelo_empresa_visitante().getId());
                SQL.setInt(3, modeloVisita.getModelo_persona_visitada().getId());
                SQL.setInt(4, modeloVisita.getModelo_area_visitada().getId());
                SQL.setString(5, modeloVisita.getTipo_visita());
                SQL.setString(6, modeloVisita.getNumero_tarjeta());
                SQL.setDate(7, modeloVisita.getFecha_hora_entrada());
                SQL.setDate(8, modeloVisita.getFecha_hora_salida());
                SQL.setString(9, modeloVisita.getEstado_visita());
                SQL.setInt(10, modeloVisita.getModelo_vehiculo().getId());
                SQL.setString(11, modeloVisita.getObservacion());
                SQL.setInt(12, modeloVisita.getModelo_usuario_ingreso().getId());
                SQL.setInt(13, modeloVisita.getModelo_usuario_salida().getId());
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
                System.out.println("Error en la consulta SQL Insert en Controladorvisita" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorvisita" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:visita
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public String Update(ModeloVisita modeloVisita) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloVisita.getEstado())) {
                    SQL = con.prepareStatement("UPDATE visita SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setInt(1, modeloVisita.getEstado());
                    SQL.setInt(2, modeloVisita.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE visita SET "
                            + "id_persona_visitante = ?, "
                            + "id_empresa_visitante = ?, "
                            + "id_persona_visitada = ?, "
                            + "id_area_visitada = ?, "
                            + "tipo_visita = ?, "
                            + "numero_tarjeta = ?, "
                            + "fecha_hora_entrada = ?, "
                            + "fecha_hora_salida = ?, "
                            + "estado_visita = ?, "
                            + "id_vehiculo = ?, "
                            + "observacion = ?, "
                            + "id_usuario_ingreso = ?, "
                            + "id_usuario_salida = ?"
                            + " WHERE id = ? ");
                    SQL.setInt(1, modeloVisita.getModelo_persona_visitante().getId());
                    SQL.setInt(2, modeloVisita.getModelo_empresa_visitante().getId());
                    SQL.setInt(3, modeloVisita.getModelo_persona_visitada().getId());
                    SQL.setInt(4, modeloVisita.getModelo_area_visitada().getId());
                    SQL.setString(5, modeloVisita.getTipo_visita());
                    SQL.setString(6, modeloVisita.getNumero_tarjeta());
                    SQL.setDate(7, modeloVisita.getFecha_hora_entrada());
                    SQL.setDate(8, modeloVisita.getFecha_hora_salida());
                    SQL.setString(9, modeloVisita.getEstado_visita());
                    SQL.setInt(10, modeloVisita.getModelo_vehiculo().getId());
                    SQL.setString(11, modeloVisita.getObservacion());
                    SQL.setInt(12, modeloVisita.getModelo_usuario_ingreso().getId());
                    SQL.setInt(13, modeloVisita.getModelo_usuario_salida().getId());
                    SQL.setInt(14, modeloVisita.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorvisita" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorvisita" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * llena un modelo que viene con datos de un request para ser Eliminado
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloVisita modeloVisita = new ModeloVisita();
            modeloVisita.setId(Integer.parseInt(request.getParameter("id")));
            modeloVisita.setEstado("N");
            resultado = Update(modeloVisita);
        }
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla visita dependiendo de un ID
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public ModeloVisita getModelo(Integer Id) {
        ModeloVisita modeloVisita = new ModeloVisita();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "id_persona_visitante, "
                    + "id_empresa_visitante, "
                    + "id_persona_visitada, "
                    + "id_area_visitada, "
                    + "tipo_visita, "
                    + "numero_tarjeta, "
                    + "fecha_hora_entrada, "
                    + "fecha_hora_salida, "
                    + "estado_visita, "
                    + "id_vehiculo, "
                    + "observacion, "
                    + "id_usuario_ingreso, "
                    + "id_usuario_salida"
                    + " FROM visita"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloVisita.setId(res.getInt("id"));
                modeloVisita.setModelo_persona_visitante(controladorPersona.getModelo(res.getInt("id_persona_visitante")));
                modeloVisita.setModelo_empresa_visitante(controladorEmpresa.getModelo(res.getInt("id_empresa_visitante")));
                modeloVisita.setModelo_persona_visitada(controladorPersona.getModelo(res.getInt("id_persona_visitada")));
                modeloVisita.setModelo_area_visitada(controladorArea.getModelo(res.getInt("id_area_visitada")));
                modeloVisita.setTipo_visita(res.getString("tipo_visita"));
                modeloVisita.setNumero_tarjeta(res.getString("numero_tarjeta"));
                modeloVisita.setFecha_hora_entrada(res.getDate("fecha_hora_entrada"));
                modeloVisita.setFecha_hora_salida(res.getDate("fecha_hora_salida"));
                modeloVisita.setEstado_visita(res.getString("estado_visita"));
                modeloVisita.setModelo_vehiculo(controladorVehiculo.getModelo(res.getInt("id_vehiculo")));
                modeloVisita.setObservacion(res.getString("observacion"));
                modeloVisita.setModelo_usuario_ingreso(controladorUsuario.getModelo(res.getInt("id_usuario_ingreso")));
                modeloVisita.setModelo_usuario_salida(controladorUsuario.getModelo(res.getInt("id_usuario_salida")));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorvisita" + e);
        }
        return modeloVisita;
    }

    /**
     * Llena un Listado de la tabla visita
     *
     * @author: Diego Fernando Guzman
     * @param vacio
     * @return LinkedList<ModeloVisita>
     * @version: 11/05/2020
     */
    public LinkedList<ModeloVisita> Read(String estado) throws SQLException {
        LinkedList<ModeloVisita> ListaModeloVisita = new LinkedList<ModeloVisita>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "id_persona_visitante, "
                    + "id_empresa_visitante, "
                    + "id_persona_visitada, "
                    + "id_area_visitada, "
                    + "tipo_visita, "
                    + "numero_tarjeta, "
                    + "fecha_hora_entrada, "
                    + "fecha_hora_salida, "
                    + "estado_visita, "
                    + "id_vehiculo, "
                    + "observacion, "
                    + "id_usuario_ingreso, "
                    + "id_usuario_salida"
                    + " FROM visita"
                    + " WHERE estado = ? ");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloVisita modeloVisita = new ModeloVisita();
                modeloVisita.setId(res.getInt("id"));
                modeloVisita.setModelo_persona_visitante(controladorPersona.getModelo(res.getInt("id_persona_visitante")));
                modeloVisita.setModelo_empresa_visitante(controladorEmpresa.getModelo(res.getInt("id_empresa_visitante")));
                modeloVisita.setModelo_persona_visitada(controladorPersona.getModelo(res.getInt("id_persona_visitada")));
                modeloVisita.setModelo_area_visitada(controladorArea.getModelo(res.getInt("id_area_visitada")));
                modeloVisita.setTipo_visita(res.getString("tipo_visita"));
                modeloVisita.setNumero_tarjeta(res.getString("numero_tarjeta"));
                modeloVisita.setFecha_hora_entrada(res.getDate("fecha_hora_entrada"));
                modeloVisita.setFecha_hora_salida(res.getDate("fecha_hora_salida"));
                modeloVisita.setEstado_visita(res.getString("estado_visita"));
                modeloVisita.setModelo_vehiculo(controladorVehiculo.getModelo(res.getInt("id_vehiculo")));
                modeloVisita.setObservacion(res.getString("observacion"));
                modeloVisita.setModelo_usuario_ingreso(controladorUsuario.getModelo(res.getInt("id_usuario_ingreso")));
                modeloVisita.setModelo_usuario_salida(controladorUsuario.getModelo(res.getInt("id_usuario_salida")));
                ListaModeloVisita.add(modeloVisita);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorvisita" + e);
        }
        return ListaModeloVisita;
    }

    /**
     * Llena un Listado de la tabla visita en una cadena con caracteristicas
     * HTML
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 11/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String out = null;
        String estado = "S";
        if (request.getParameter("estado") != null) {
            estado = "N";
        }
        try {
            LinkedList<ModeloVisita> ListaModeloVisita = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>VISITANTE</th>";
            out += "<th>EMPRESA</th>";
            out += "<th>EMPLEADO VISITADO</th>";
            out += "<th>AREA VISITADA</th>";
            out += "<th>TIPO VISITA</th>";
            out += "<th>TARJETA</th>";
            out += "<th>ENTRADA</th>";
            out += "<th>SALIDA</th>";
            out += "<th>ESTADO</th>";
            out += "<th>VEHICULO</th>";
            out += "<th>OBSERVACION</th>";
            out += "<th>IN. REGISTRADO POR</th>";
            out += "<th>SAL. REGISTRADO POR</th>";
            out += "<th>Opciones</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloVisita modeloVisita : ListaModeloVisita) {
                out += "<tr>";
                out += "<td>" + modeloVisita.getModelo_persona_visitante().getString() + "</td>";
                out += "<td>" + modeloVisita.getModelo_empresa_visitante().getString() + "</td>";
                out += "<td>" + modeloVisita.getModelo_persona_visitada().getString() + "</td>";
                out += "<td>" + modeloVisita.getModelo_area_visitada().getString() + "</td>";
                out += "<td>" + modeloVisita.getTipo_visita() + "</td>";
                out += "<td>" + modeloVisita.getNumero_tarjeta() + "</td>";
                out += "<td>" + modeloVisita.getFecha_hora_entrada() + "</td>";
                out += "<td>" + modeloVisita.getFecha_hora_salida() + "</td>";
                out += "<td>" + modeloVisita.getEstado_visita() + "</td>";
                out += "<td>" + modeloVisita.getModelo_vehiculo().getString() + "</td>";
                out += "<td>" + modeloVisita.getObservacion() + "</td>";
                out += "<td>" + modeloVisita.getModelo_usuario_ingreso().getString() + "</td>";
                out += "<td>" + modeloVisita.getModelo_usuario_salida().getString() + "</td>";
                out += "<td class=\"text-center\">";
// Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id_persona_visitante=\"" + modeloVisita.getModelo_persona_visitante().getString() + "\"";
                out += "data-id_empresa_visitante=\"" + modeloVisita.getModelo_empresa_visitante().getString() + "\"";
                out += "data-id_persona_visitada=\"" + modeloVisita.getModelo_persona_visitada().getString() + "\"";
                out += "data-id_area_visitada=\"" + modeloVisita.getModelo_area_visitada().getString() + "\"";
                out += "data-tipo_visita=\"" + modeloVisita.getTipo_visita() + "\"";
                out += "data-numero_tarjeta=\"" + modeloVisita.getNumero_tarjeta() + "\"";
                out += "data-fecha_hora_entrada=\"" + modeloVisita.getFecha_hora_entrada() + "\"";
                out += "data-fecha_hora_salida=\"" + modeloVisita.getFecha_hora_salida() + "\"";
                out += "data-estado_visita=\"" + modeloVisita.getEstado_visita() + "\"";
                out += "data-id_vehiculo=\"" + modeloVisita.getModelo_vehiculo().getString() + "\"";
                out += "data-observacion=\"" + modeloVisita.getObservacion() + "\"";
                out += "data-id_usuario_ingreso=\"" + modeloVisita.getModelo_usuario_ingreso().getString() + "\"";
                out += "data-id_usuario_salida=\"" + modeloVisita.getModelo_usuario_salida().getString() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
//Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id_persona_visitante=\"" + modeloVisita.getModelo_persona_visitante().getString() + "\"";
                out += "data-id_empresa_visitante=\"" + modeloVisita.getModelo_empresa_visitante().getString() + "\"";
                out += "data-id_persona_visitada=\"" + modeloVisita.getModelo_persona_visitada().getString() + "\"";
                out += "data-id_area_visitada=\"" + modeloVisita.getModelo_area_visitada().getString() + "\"";
                out += "data-tipo_visita=\"" + modeloVisita.getTipo_visita() + "\"";
                out += "data-numero_tarjeta=\"" + modeloVisita.getNumero_tarjeta() + "\"";
                out += "data-fecha_hora_entrada=\"" + modeloVisita.getFecha_hora_entrada() + "\"";
                out += "data-fecha_hora_salida=\"" + modeloVisita.getFecha_hora_salida() + "\"";
                out += "data-estado_visita=\"" + modeloVisita.getEstado_visita() + "\"";
                out += "data-id_vehiculo=\"" + modeloVisita.getModelo_vehiculo().getString() + "\"";
                out += "data-observacion=\"" + modeloVisita.getObservacion() + "\"";
                out += "data-id_usuario_ingreso=\"" + modeloVisita.getModelo_usuario_ingreso().getString() + "\"";
                out += "data-id_usuario_salida=\"" + modeloVisita.getModelo_usuario_salida().getString() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e) {
            System.out.println("Error en la generacion Html en Controladorvisita" + e);
        }
        return out;
    }

    public boolean GuardarImagen(String FotoBase64, String Documento) throws IOException {

        BufferedImage image = null;
        byte[] imageByte = null;

        BASE64Decoder decoder = new BASE64Decoder();

        imageByte = decoder.decodeBuffer(FotoBase64);

        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        try {
            image = ImageIO.read(bis);
            bis.close();
            String Barra = "92";
            int d = Integer.parseInt(Barra);
            char x = (char) (d);
            String RutaCompleta = null;

            RutaCompleta = "RUTA " + Documento + ".bmp";

            System.out.println(RutaCompleta);

            // write the image to a file
            File outputfile = new File(RutaCompleta);
            ImageIO.write(image, "bmp", outputfile);

        } catch (Exception e) {
            System.out.println(e);
            bis.close();
        }

        return true;
    }

}
