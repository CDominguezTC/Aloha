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
import java.util.LinkedList;
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
                out += "<button class=\"SetFormularioAutoriza btn btn-warning btn-xs\"title=\"Editar\"";
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
                out += "<button class=\"SetFormularioAutorizado btn btn-warning btn-xs\"title=\"Editar\"";
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
        ModeloAutoriza_consumo modeloAutoriza_consumo = new ModeloAutoriza_consumo();
        modeloAutoriza_consumo.setTipo_persona(request.getParameter("tipopersona"));
        modeloAutoriza_consumo.setModelo_persona_que_autoriza(controladorPersona.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("empleadoautorizado")))));
        modeloAutoriza_consumo.setModelo_persona_autorizada(controladorPersona.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("personaautorizada")))));
        modeloAutoriza_consumo.setModelo_centro_costo(controladorCentro_costo.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("centrocosto")))));
        modeloAutoriza_consumo.setFecha_autorizada(request.getParameter("fechainicial"));
        modeloAutoriza_consumo.setModelo_horario_consumo(controladorHorario_consumo.getModelo(Integer.parseInt(herramienta.validaString(request.getParameter("tipoconsumo")))));
        modeloAutoriza_consumo.setMotivo(request.getParameter("motivo"));
        modeloAutoriza_consumo.setCantidad_autorizada(Integer.parseInt(herramienta.validaString(request.getParameter("cantidad"))));
        modeloAutoriza_consumo.setFecha_registro(herramienta.getDateToday());

        HttpSession session = request.getSession();
        user = (String) session.getAttribute("usuario");
        modeloAutoriza_consumo.setModelo_usuario((ModeloUsuario) session.getAttribute("modelousuario"));
        resultado = Insert(modeloAutoriza_consumo);
        if ("1".equals(resultado))
        {
            int cantidadconsumo = modeloAutoriza_consumo.getModelo_persona_que_autoriza().getCantidad_consumo();
            int nuevoconsumo = cantidadconsumo - modeloAutoriza_consumo.getCantidad_autorizada();
            ModeloPersona modeloPersona = modeloAutoriza_consumo.getModelo_persona_que_autoriza();
            modeloPersona.setCantidad_consumo(nuevoconsumo);
            controladorPersona.UpdateCasino(modeloPersona);
        }
        return resultado;
    }

    private String Insert(ModeloAutoriza_consumo modeloAutoriza_consumo) {
        try {
            con = conexion.abrirConexion();
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
                        + "`fecha_registro`,"
                        + "`id_usuario`,"
                        + "`estado`) "
                        + "VALUE (?,?,?,?,?,?,?,?,?,?,?);", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloAutoriza_consumo.getTipo_persona());
                SQL.setInt(2, modeloAutoriza_consumo.getModelo_persona_que_autoriza().getId());
                SQL.setInt(3, modeloAutoriza_consumo.getModelo_persona_autorizada().getId());
                SQL.setInt(4, modeloAutoriza_consumo.getModelo_centro_costo().getId());
                SQL.setString(5, modeloAutoriza_consumo.getFecha_autorizada());
                SQL.setInt(6, modeloAutoriza_consumo.getModelo_horario_consumo().getId());
                SQL.setString(7, modeloAutoriza_consumo.getMotivo());
                SQL.setInt(8, modeloAutoriza_consumo.getCantidad_autorizada());
                SQL.setString(9, modeloAutoriza_consumo.getFecha_registro());
                SQL.setInt(10, modeloAutoriza_consumo.getModelo_usuario().getId());
                SQL.setString(11, "S");
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
                System.out.println("Error en la consulta SQL Insert en Controladorciudad" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorciudad" + e);
            resultado = "-3";
        }
        return resultado;
    }

}
