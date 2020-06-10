/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Herramienta.Herramienta;
import Modelo.ModeloPersona;
import Modelo.ModeloVisita;
import com.google.gson.Gson;
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
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;

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
    ControladorArea controladorArea = new ControladorArea();
    ControladorVehiculo controladorVehiculo = new ControladorVehiculo();
    ControladorUsuario controladorUsuario = new ControladorUsuario();
    ControladorDependencia controladorDependencia = new ControladorDependencia();
    ControladorCiudad controladorCiudad = new ControladorCiudad();
    ControladorCargo controladorCargo = new ControladorCargo();
    ControladorCentro_costo controladorCentro_costo = new ControladorCentro_costo();
    ControladorGrupo_consumo controladorGrupo_consumo = new ControladorGrupo_consumo();
    Herramienta herramienta = new Herramienta();

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
//        modeloVisita.setFecha_hora_entrada(request.getParameter("fecha_hora_entrada"));
//        modeloVisita.setFecha_hora_salida(request.getParameter("fecha_hora_salida"));
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
                    SQL.setString(1, modeloVisita.getEstado());
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
                out += "<td>" + modeloVisita.getModelo_persona_visitante().getNombres() + " " + modeloVisita.getModelo_persona_visitante().getApellidos() + "</td>";
                out += "<td>" + modeloVisita.getModelo_empresa_visitante().getNombre() + "</td>";
                out += "<td>" + modeloVisita.getModelo_persona_visitada().getNombres() + " " + modeloVisita.getModelo_persona_visitada().getApellidos() + "</td>";
                out += "<td>" + modeloVisita.getModelo_area_visitada().getNombre() + "</td>";
                out += "<td>" + modeloVisita.getTipo_visita() + "</td>";
                out += "<td>" + modeloVisita.getNumero_tarjeta() + "</td>";
                out += "<td>" + modeloVisita.getFecha_hora_entrada() + "</td>";
                out += "<td>" + modeloVisita.getFecha_hora_salida() + "</td>";
                out += "<td>" + modeloVisita.getEstado_visita() + "</td>";
                out += "<td>" + modeloVisita.getModelo_vehiculo().getPlaca_vehiculo() + "</td>";
                out += "<td>" + modeloVisita.getObservacion() + "</td>";
                out += "<td>" + modeloVisita.getModelo_usuario_ingreso().getNombre() + "</td>";
                out += "<td>" + modeloVisita.getModelo_usuario_salida().getNombre() + "</td>";
                out += "<td class=\"text-center\">";
// Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id_persona_visitante=\"" + modeloVisita.getModelo_persona_visitante().getNombres() + " " + modeloVisita.getModelo_persona_visitante().getApellidos() + "\"";
                out += "data-id_empresa_visitante=\"" + modeloVisita.getModelo_empresa_visitante().getNombre() + "\"";
                out += "data-id_persona_visitada=\"" + modeloVisita.getModelo_persona_visitada().getNombres() + " " + modeloVisita.getModelo_persona_visitada().getApellidos() + "\"";
                out += "data-id_area_visitada=\"" + modeloVisita.getModelo_area_visitada().getNombre() + "\"";
                out += "data-tipo_visita=\"" + modeloVisita.getTipo_visita() + "\"";
                out += "data-numero_tarjeta=\"" + modeloVisita.getNumero_tarjeta() + "\"";
                out += "data-fecha_hora_entrada=\"" + modeloVisita.getFecha_hora_entrada() + "\"";
                out += "data-fecha_hora_salida=\"" + modeloVisita.getFecha_hora_salida() + "\"";
                out += "data-estado_visita=\"" + modeloVisita.getEstado_visita() + "\"";
                out += "data-id_vehiculo=\"" + modeloVisita.getModelo_vehiculo().getPlaca_vehiculo() + "\"";
                out += "data-observacion=\"" + modeloVisita.getObservacion() + "\"";
                out += "data-id_usuario_ingreso=\"" + modeloVisita.getModelo_usuario_ingreso().getNombre() + "\"";
                out += "data-id_usuario_salida=\"" + modeloVisita.getModelo_usuario_salida().getNombre() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
//Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id_persona_visitante=\"" + modeloVisita.getModelo_persona_visitante().getNombres() + " " + modeloVisita.getModelo_persona_visitante().getApellidos() + "\"";
                out += "data-id_empresa_visitante=\"" + modeloVisita.getModelo_empresa_visitante().getNombre() + "\"";
                out += "data-id_persona_visitada=\"" + modeloVisita.getModelo_persona_visitada().getNombres() + " " + modeloVisita.getModelo_persona_visitada().getApellidos() + "\"";
                out += "data-id_area_visitada=\"" + modeloVisita.getModelo_area_visitada().getNombre() + "\"";
                out += "data-tipo_visita=\"" + modeloVisita.getTipo_visita() + "\"";
                out += "data-numero_tarjeta=\"" + modeloVisita.getNumero_tarjeta() + "\"";
                out += "data-fecha_hora_entrada=\"" + modeloVisita.getFecha_hora_entrada() + "\"";
                out += "data-fecha_hora_salida=\"" + modeloVisita.getFecha_hora_salida() + "\"";
                out += "data-estado_visita=\"" + modeloVisita.getEstado_visita() + "\"";
                out += "data-id_vehiculo=\"" + modeloVisita.getModelo_vehiculo().getPlaca_vehiculo() + "\"";
                out += "data-observacion=\"" + modeloVisita.getObservacion() + "\"";
                out += "data-id_usuario_ingreso=\"" + modeloVisita.getModelo_usuario_ingreso().getNombre() + "\"";
                out += "data-id_usuario_salida=\"" + modeloVisita.getModelo_usuario_salida().getNombre() + "\"";
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

    public String valida_tipo_visita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer Id_persona = Integer.valueOf(request.getParameter("id_persona"));
        String Tipo_Visita = request.getParameter("tipo_visita");

        resultado = new Gson().toJson(resultado);
        return resultado;
    }

    public static String CompararTemplates(String Template_Vivo, String Template_Bd) throws Exception {
        JNative commNative;
        String ret;
        StringBuffer regTemplateBuf;
        StringBuffer verTemplateBuf;
        commNative = null;
        ret = "";
        regTemplateBuf = new StringBuffer();
        verTemplateBuf = new StringBuffer();
        regTemplateBuf.append(Template_Vivo.replace("\"", ""));
        verTemplateBuf.append(Template_Bd);
        commNative = new JNative("matchdll", "process");
        commNative.setRetVal(Type.INT);
        commNative.setParameter(0, Type.STRING, regTemplateBuf.toString());
        commNative.setParameter(1, Type.STRING, verTemplateBuf.toString());
        commNative.invoke();
        ret = commNative.getRetVal();

        return ret;
    }

    /**
     * Permite listar la información de la tabla de las Personas Metodo Private
     *
     * @author: Diego Fernando Guzman
     * @return LinkedList
     * @version: 29/05/2020
     */
    public LinkedList<ModeloPersona> Read_Lista_Persona(String estado, String Where) {
        LinkedList<ModeloPersona> listaModeloPersonas = new LinkedList<ModeloPersona>();
        con = conexion.abrirConexion();

        String SqlWhere = "";
        switch (Where) {
            case "Persona":
                SqlWhere = "";
                break;
            case "Empleado":
                SqlWhere = "";
                break;
        }

        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "tipo_identificacion, "
                    + "identificacion, "
                    + "nombres, "
                    + "apellidos, "
                    + "email, "
                    + "direccion, "
                    + "telefono, "
                    + "rh, "
                    + "tipo_persona, "
                    + "recibe_visitas, "
                    + "nombre_eps, "
                    + "nombre_arl, "
                    + "acceso_restringido, "
                    + "observacion, "
                    + "consumo_casino, "
                    + "cantidad_consumo, "
                    + "tarjeta_acceso, "
                    + "codigo_nomina, "
                    + "estado, "
                    + "id_dependencia, "
                    + "id_empresa_seguridad_social, "
                    + "id_grupo_horario, "
                    + "id_turno, "
                    + "id_departamento, "
                    + "id_area, "
                    + "id_ciudad, "
                    + "id_centro_costo, "
                    + "id_cargo, "
                    + "id_empresa_trabaja, "
                    + "id_grupo_consumo "
                    + " FROM persona "
                    + "WHERE estado = ? "
                    + SqlWhere);
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloPersona modelo = new ModeloPersona();
                modelo.setId(res.getInt("id"));
                modelo.setTipo_identificacion(res.getString("tipo_identificacion"));
                modelo.setIdentificacion(res.getString("identificacion"));
                modelo.setNombres(res.getString("nombres"));
                modelo.setApellidos(res.getString("apellidos"));
                modelo.setEmail(res.getString("email"));
                modelo.setDireccion(res.getString("direccion"));
                modelo.setTelefono(res.getString("telefono"));
                modelo.setRh(res.getString("rh"));
                modelo.setTipo_persona(res.getString("tipo_persona"));
                modelo.setRecibe_visitas(res.getString("recibe_visitas"));
                modelo.setNombre_eps(res.getString("nombre_eps"));
                modelo.setNombre_arl(res.getString("nombre_arl"));
                modelo.setAcceso_restringido(res.getString("acceso_restringido"));
                modelo.setObservacion(res.getString("observacion"));
                modelo.setConsumo_casino(res.getString("consumo_casino"));
                modelo.setCantidad_consumo(res.getInt("cantidad_consumo"));
                modelo.setTarjeta_acceso(res.getString("tarjeta_acceso"));
                modelo.setCodigo_nomina(res.getString("codigo_nomina"));
                modelo.setEstado(res.getString("estado"));
                modelo.setModelo_dependencia(controladorDependencia.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_dependencia")))));
                modelo.setModelo_empresa_seguridad_social(controladorEmpresa.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_empresa_seguridad_social")))));
                //modelo.setModelo_grupo_horario(controladorGrupo_horario.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_grupo_horario")))));
                //modelo.setModelo_turno(controladorTurno_tiempo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_turno")))));
                //modelo.setModelo_departamento(controladorDepartamento.res.getString("id_departamento"));
                modelo.setModelo_area(controladorArea.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_area")))));
                modelo.setModelo_ciudad(controladorCiudad.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_ciudad")))));
                modelo.setModelo_centro_costo(controladorCentro_costo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_centro_costo")))));
                modelo.setModelo_cargo(controladorCargo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_cargo")))));
                modelo.setModelo_empresa_trabaja(controladorEmpresa.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_empresa_trabaja")))));
                modelo.setModelo_grupo_consumo(controladorGrupo_consumo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_grupo_consumo")))));
//                //Datos de Imagenes y templates
//                modelo.setLista_Modelo_Imagenes(controladorImagen.getListaModelo(modelo.getId()));
//                modelo.setLista_Modelo_Template(controladorTemplate.getModelo(modelo.getId()));

                listaModeloPersonas.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listaModeloPersonas;
    }

}
