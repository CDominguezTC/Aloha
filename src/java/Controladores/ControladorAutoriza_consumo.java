/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Herramienta.Herramienta;
import Modelo.ModeloAutoriza_consumo;
import Modelo.ModeloPersona;
import Modelo.ModeloUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorAutoriza_consumo {

    ControladorPersona controladorPersona = new ControladorPersona();
    ControladorTipo_consumo controladorTipo_consumo = new ControladorTipo_consumo();
    ControladorCentro_costo controladorCentro_costo = new ControladorCentro_costo();
    ControladorHorario_consumo controladorHorario_consumo = new ControladorHorario_consumo();
    ControladorUsuario controladorUsuario = new ControladorUsuario();
    ModeloUsuario modeloUsuario = new ModeloUsuario();
    Herramienta herramienta = new Herramienta();
    String user;
    String resultado;
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    /**
     * Permite cargar las persona que son tipo empleao en un tabla
     *
     * @param request
     * @param response
     * @return
     */
    public String ReadPersonaAutoriza(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        LinkedList<ModeloPersona> listaModeloPersonas = new LinkedList<>();
        ControladorPersona controladorPersona = new ControladorPersona();
        listaModeloPersonas = controladorPersona.Read("S");
        try {
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Cedula</th>";
            out += "<th>Nombre</th>";
            out += "<th>Empresa</th>";
            out += "<th>CentroCosto</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloPersona modeloPersonas : listaModeloPersonas) {
                out += "<tr>";
                out += "<td>" + modeloPersonas.getIdentificacion() + "</td>";
                out += "<td>" + modeloPersonas.getNombres() + " " + modeloPersonas.getApellidos() + "</td>";
                out += "<td>" + modeloPersonas.getModelo_empresa_trabaja().getNombre() + "</td>";
                out += "<td>" + modeloPersonas.getModelo_centro_costo().getNombre() + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormularioAutoriza btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modeloPersonas.getId() + "\"";
                out += "data-tipodoc=\"" + modeloPersonas.getTipo_identificacion() + "\"";
                out += "data-cedula=\"" + modeloPersonas.getIdentificacion() + "\"";
                out += "data-nombre=\"" + modeloPersonas.getNombres() + "\"";
                out += "data-apellido=\"" + modeloPersonas.getApellidos() + "\"";
                out += "data-empresa=\"" + modeloPersonas.getModelo_empresa_trabaja().getId() + "\"";
                out += "data-cargo=\"" + modeloPersonas.getModelo_cargo().getId() + "\"";
                out += "data-centrocosto=\"" + modeloPersonas.getModelo_centro_costo().getId() + "\"";
                out += "data-centrocostonombre=\"" + modeloPersonas.getModelo_centro_costo().getNombre() + "\"";
                out += "data-grupoconsumo=\"" + modeloPersonas.getModelo_grupo_consumo().getId() + "\"";
                out += "data-consume=\"" + modeloPersonas.getConsumo_casino() + "\"";
                out += "data-cantidadconsumo=\"" + modeloPersonas.getCantidad_consumo() + "\"";
                out += "data-observacion=\"" + modeloPersonas.getObservacion() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-clipboard\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }

    public String ReadPersonaAutorizado(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        LinkedList<ModeloPersona> listaModeloPersonas = new LinkedList<>();
        ControladorPersona controladorPersona = new ControladorPersona();
        listaModeloPersonas = controladorPersona.Read("S");
        try {
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Cedula</th>";
            out += "<th>Nombre</th>";
            out += "<th>Empresa</th>";
            out += "<th>CentroCosto</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloPersona modeloPersonas : listaModeloPersonas) {
                out += "<tr>";
                out += "<td>" + modeloPersonas.getIdentificacion() + "</td>";
                out += "<td>" + modeloPersonas.getNombres() + " " + modeloPersonas.getApellidos() + "</td>";
                out += "<td>" + modeloPersonas.getModelo_empresa_trabaja().getNombre() + "</td>";
                out += "<td>" + modeloPersonas.getModelo_centro_costo().getNombre() + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormularioAutorizado btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modeloPersonas.getId() + "\"";
                out += "data-tipodoc=\"" + modeloPersonas.getTipo_identificacion() + "\"";
                out += "data-cedula=\"" + modeloPersonas.getIdentificacion() + "\"";
                out += "data-nombre=\"" + modeloPersonas.getNombres() + "\"";
                out += "data-apellido=\"" + modeloPersonas.getApellidos() + "\"";
                out += "data-empresa=\"" + modeloPersonas.getModelo_empresa_trabaja().getId() + "\"";
                out += "data-cargo=\"" + modeloPersonas.getModelo_cargo().getId() + "\"";
                out += "data-centrocosto=\"" + modeloPersonas.getModelo_centro_costo().getId() + "\"";
                out += "data-centrocostonombre=\"" + modeloPersonas.getModelo_centro_costo().getNombre() + "\"";
                out += "data-grupoconsumo=\"" + modeloPersonas.getModelo_grupo_consumo().getId() + "\"";
                out += "data-consume=\"" + modeloPersonas.getConsumo_casino() + "\"";
                out += "data-observacion=\"" + modeloPersonas.getObservacion() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-clipboard\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * Autorizaciones de consumo
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 14/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) {
        try {

            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            String FechaInicial = request.getParameter("fechainicial");
            String FechaFinal = request.getParameter("fechafinal");
            Calendar calendafechainicial = Calendar.getInstance();
            Calendar calendafechafinal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            calendafechainicial.setTime(sdf.parse(FechaInicial));
            calendafechafinal.setTime(sdf.parse(FechaFinal));
            long daysBetween = 0;
            int CantidadConsumos = 0;
            int noconsumospersonaautoiiza = 0;
            String diaautorizado = "";
            String TipoPersona = "";
            ModeloPersona modeloPersona = new ModeloPersona();
            LinkedList<ModeloAutoriza_consumo> listaModeloAutoriza_consumos = new LinkedList<>();
            if (calendafechainicial.equals(calendafechafinal)) {
                ModeloAutoriza_consumo modeloAutoriza_consumo = new ModeloAutoriza_consumo();
                calendafechainicial.add(Calendar.DAY_OF_MONTH, 0);
                diaautorizado = herramienta.getCalendarString(calendafechainicial);
                modeloAutoriza_consumo.setTipo_persona(request.getParameter("tipopersona"));
                modeloAutoriza_consumo.setModelo_persona_que_autoriza(controladorPersona.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("empleadoautorizado")))));
                modeloAutoriza_consumo.setModelo_persona_autorizada(controladorPersona.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("personaautorizada")))));
                modeloAutoriza_consumo.setModelo_centro_costo(controladorCentro_costo.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("centrocosto")))));
                modeloAutoriza_consumo.setFecha_autorizada(diaautorizado);
                modeloAutoriza_consumo.setModelo_horario_consumo(controladorHorario_consumo.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("tipoconsumo")))));
                modeloAutoriza_consumo.setMotivo(request.getParameter("motivo"));
                modeloAutoriza_consumo.setCantidad_autorizada(Integer.parseInt(herramienta.validaString(request.getParameter("cantidad"))));
                modeloAutoriza_consumo.setCantidad_consumida(0);
                modeloAutoriza_consumo.setFecha_registro(herramienta.getDateToday());
                modeloAutoriza_consumo.setModelo_usuario((ModeloUsuario) session.getAttribute("modelousuario"));
                CantidadConsumos += modeloAutoriza_consumo.getCantidad_autorizada();
                noconsumospersonaautoiiza = modeloAutoriza_consumo.getModelo_persona_que_autoriza().getCantidad_consumo();
                TipoPersona = modeloAutoriza_consumo.getTipo_persona();
                modeloPersona = modeloAutoriza_consumo.getModelo_persona_que_autoriza();
                listaModeloAutoriza_consumos.add(modeloAutoriza_consumo);
            } else {
                while (calendafechainicial.before(calendafechafinal)) {
                    ModeloAutoriza_consumo modeloAutoriza_consumo = new ModeloAutoriza_consumo();
                    if (daysBetween == 0) {
                        calendafechainicial.add(Calendar.DAY_OF_MONTH, 0);
                        diaautorizado = herramienta.getCalendarString(calendafechainicial);
                        modeloAutoriza_consumo.setTipo_persona(request.getParameter("tipopersona"));
                        modeloAutoriza_consumo.setModelo_persona_que_autoriza(controladorPersona.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("empleadoautorizado")))));
                        modeloAutoriza_consumo.setModelo_persona_autorizada(controladorPersona.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("personaautorizada")))));
                        modeloAutoriza_consumo.setModelo_centro_costo(controladorCentro_costo.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("centrocosto")))));
                        modeloAutoriza_consumo.setFecha_autorizada(diaautorizado);
                        modeloAutoriza_consumo.setModelo_horario_consumo(controladorHorario_consumo.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("tipoconsumo")))));
                        modeloAutoriza_consumo.setMotivo(request.getParameter("motivo"));
                        modeloAutoriza_consumo.setCantidad_autorizada(Integer.parseInt(herramienta.validaString(request.getParameter("cantidad"))));
                        modeloAutoriza_consumo.setCantidad_consumida(0);
                        modeloAutoriza_consumo.setFecha_registro(herramienta.getDateToday());
                        modeloAutoriza_consumo.setModelo_usuario((ModeloUsuario) session.getAttribute("modelousuario"));
                        CantidadConsumos += modeloAutoriza_consumo.getCantidad_autorizada();
                        noconsumospersonaautoiiza = modeloAutoriza_consumo.getModelo_persona_que_autoriza().getCantidad_consumo();
                        TipoPersona = modeloAutoriza_consumo.getTipo_persona();
                        modeloPersona = modeloAutoriza_consumo.getModelo_persona_que_autoriza();
                    } else {
                        calendafechainicial.add(Calendar.DAY_OF_MONTH, 1);
                        diaautorizado = herramienta.getCalendarString(calendafechainicial);
                        modeloAutoriza_consumo.setTipo_persona(request.getParameter("tipopersona"));
                        modeloAutoriza_consumo.setModelo_persona_que_autoriza(controladorPersona.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("empleadoautorizado")))));
                        modeloAutoriza_consumo.setModelo_persona_autorizada(controladorPersona.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("personaautorizada")))));
                        modeloAutoriza_consumo.setModelo_centro_costo(controladorCentro_costo.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("centrocosto")))));
                        modeloAutoriza_consumo.setFecha_autorizada(diaautorizado);
                        modeloAutoriza_consumo.setModelo_horario_consumo(controladorHorario_consumo.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("tipoconsumo")))));
                        modeloAutoriza_consumo.setMotivo(request.getParameter("motivo"));
                        modeloAutoriza_consumo.setCantidad_autorizada(Integer.parseInt(herramienta.validaString(request.getParameter("cantidad"))));
                        modeloAutoriza_consumo.setCantidad_consumida(0);
                        modeloAutoriza_consumo.setFecha_registro(herramienta.getDateToday());
                        modeloAutoriza_consumo.setModelo_usuario((ModeloUsuario) session.getAttribute("modelousuario"));
                        CantidadConsumos += modeloAutoriza_consumo.getCantidad_autorizada();
                    }
                    listaModeloAutoriza_consumos.add(modeloAutoriza_consumo);
                    daysBetween++;
                }
            }
            if ("VISITANTE".equals(TipoPersona)) {
                if (CantidadConsumos < noconsumospersonaautoiiza) {
                    resultado = Insert(listaModeloAutoriza_consumos);
                    if ("1".equals(resultado)) {
                        int nuevoconsumo = noconsumospersonaautoiiza - CantidadConsumos;
                        modeloPersona.setCantidad_consumo(nuevoconsumo);
                        controladorPersona.UpdateCasino(modeloPersona);
                    }
                } else {
                    resultado = "3";
                }
            }
            if ("EMPLEADO".equals(TipoPersona)) {
                resultado = Insert(listaModeloAutoriza_consumos);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ControladorAutoriza_consumo.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    private String Insert(LinkedList<ModeloAutoriza_consumo> listamodeloAutoriza_consumo) {
        try {
            con = conexion.abrirConexion();
            for (ModeloAutoriza_consumo modeloAutoriza_consumo : listamodeloAutoriza_consumo) {
                try {
                    SQL = con.prepareStatement("INSERT INTO `autoriza_consumo`("
                            + "`tipo_persona`,"
                            + "`id_persona_que_autoriza`,"
                            + "`id_persona_autorizada`,"
                            + "`id_centro_costo`,"
                            + "`fecha_autorizada`,"
                            + "`id_horario_consumo`,"
                            + "`motivo`,"
                            + "`cantidad_autorizada`,"
                            + "`cantidad_consumida`,"
                            + "`fecha_registro`,"
                            + "`id_usuario`,"
                            + "`estado`) "
                            + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?);", SQL.RETURN_GENERATED_KEYS);
                    SQL.setString(1, modeloAutoriza_consumo.getTipo_persona());
                    SQL.setInt(2, modeloAutoriza_consumo.getModelo_persona_que_autoriza().getId());
                    SQL.setInt(3, modeloAutoriza_consumo.getModelo_persona_autorizada().getId());
                    SQL.setInt(4, modeloAutoriza_consumo.getModelo_centro_costo().getId());
                    SQL.setString(5, modeloAutoriza_consumo.getFecha_autorizada());
                    SQL.setInt(6, modeloAutoriza_consumo.getModelo_horario_consumo().getId());
                    SQL.setString(7, modeloAutoriza_consumo.getMotivo());
                    SQL.setInt(8, modeloAutoriza_consumo.getCantidad_autorizada());
                    SQL.setInt(9, modeloAutoriza_consumo.getCantidad_consumida());
                    SQL.setString(10, modeloAutoriza_consumo.getFecha_registro());
                    SQL.setInt(11, modeloAutoriza_consumo.getModelo_usuario().getId());                    
                    SQL.setString(12, "S");
                    if (SQL.executeUpdate() > 0) {
                        ControladorAuditoria auditoria = new ControladorAuditoria();
                        try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int i = (int) generatedKeys.getLong(1);
                                auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.");
                            }
                        }
                        resultado = "1";
                    }
                } catch (SQLException e) {
                    System.out.println("Error en la consulta SQL Insert en Controladorciudad" + e);
                    resultado = "-2";
                }
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorciudad" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Permite Genetar una tabla con los registros de la tabla de Autorizacion
     * consumo
     *
     * @param request
     * @param response
     * @return
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        String estado = "S";
        if (request.getParameter("estado") != null) {
            estado = "N";
        }
        try {
            LinkedList<ModeloAutoriza_consumo> listaAutoriza_consumos;
            listaAutoriza_consumos = Read(estado);
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Tipo Persona</th>";
            out += "<th>Autrizador</th>";
            out += "<th>Autorizado</th>";
            out += "<th>Centro Costo</th>";
            out += "<th>Consumo</th>";
            out += "<th>Fecha Autorizacion</th>";
            out += "<th>Cantidad Autrorizada</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloAutoriza_consumo modeloAutoriza_consumo : listaAutoriza_consumos) {
                out += "<tr>";
                out += "<td>" + modeloAutoriza_consumo.getTipo_persona() + "</td>";
                out += "<td>" + modeloAutoriza_consumo.getModelo_persona_que_autoriza().getNombresApellido() + "</td>";
                out += "<td>" + modeloAutoriza_consumo.getModelo_persona_autorizada().getNombresApellido() + "</td>";
                out += "<td>" + modeloAutoriza_consumo.getModelo_centro_costo().getNombre() + "</td>";
                out += "<td>" + modeloAutoriza_consumo.getModelo_horario_consumo().getNombre() + "</td>";
                out += "<td>" + modeloAutoriza_consumo.getFecha_autorizada() + "</td>";
                out += "<td>" + modeloAutoriza_consumo.getCantidad_autorizada() + "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }

    /**
     * Permite obtener un listado de todos los registros que tiene la tabla de
     * Autroizacion Consumos
     *
     * @param estado
     * @return
     */
    public LinkedList<ModeloAutoriza_consumo> Read(String estado) {
        LinkedList<ModeloAutoriza_consumo> listaModeloAutoriza_consumos = new LinkedList<>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`tipo_persona`,"
                    + "`id_persona_que_autoriza`,"
                    + "`id_persona_autorizada`,"
                    + "`id_centro_costo`,"
                    + "`fecha_autorizada`,"
                    + "`id_horario_consumo`,"
                    + "`motivo`,"
                    + "`cantidad_autorizada`,"
                    + "`cantidad_consumida`,"
                    + "`fecha_registro`,"
                    + "`id_usuario`,"
                    + "`estado` "
                    + "FROM `autoriza_consumo` "
                    + "WHERE estado = ?");
            SQL.setString(1, estado);
            try (ResultSet res = SQL.executeQuery()) {
                while (res.next()) {
                    ModeloAutoriza_consumo modeloAutoriza_consumo = new ModeloAutoriza_consumo();
                    modeloAutoriza_consumo.setId(res.getInt("id"));
                    modeloAutoriza_consumo.setTipo_persona(res.getString("tipo_persona"));
                    modeloAutoriza_consumo.setModelo_persona_que_autoriza(controladorPersona.getModelo(res.getInt("id_persona_que_autoriza")));
                    modeloAutoriza_consumo.setModelo_persona_autorizada(controladorPersona.getModelo(res.getInt("id_persona_autorizada")));
                    modeloAutoriza_consumo.setModelo_centro_costo(controladorCentro_costo.getModelo(res.getInt("id_centro_costo")));
                    modeloAutoriza_consumo.setFecha_autorizada(res.getString("fecha_autorizada"));
                    modeloAutoriza_consumo.setModelo_horario_consumo(controladorHorario_consumo.getModelo(res.getInt("id_horario_consumo")));
                    modeloAutoriza_consumo.setMotivo(res.getString("motivo"));
                    modeloAutoriza_consumo.setCantidad_autorizada(res.getInt("cantidad_autorizada"));
                    modeloAutoriza_consumo.setCantidad_consumida(res.getInt("cantidad_consumida"));
                    modeloAutoriza_consumo.setFecha_registro(res.getString("fecha_registro"));
                    modeloAutoriza_consumo.setModelo_usuario(controladorUsuario.getModelo(res.getInt("id_usuario")));
                    modeloAutoriza_consumo.setEstado(res.getString("estado"));
                    listaModeloAutoriza_consumos.add(modeloAutoriza_consumo);
                }
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorciudad" + e);
        }
        return listaModeloAutoriza_consumos;
    }

}
