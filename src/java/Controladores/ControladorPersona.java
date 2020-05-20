/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Herramienta.Herramienta;
import Modelo.ModeloImagen;
import Modelo.ModeloPersona;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.nio.cs.ext.GB18030;

/**
 * Esta clase permite controlar los eventos de Personas contrine Insert -
 * Update, Delete, Read
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorPersona {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;
    ControladorDependencia controladorDependencia = new ControladorDependencia();
    ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
    ControladorGrupoTurnos controladorGrupo_horario = new ControladorGrupoTurnos();
    ControladorTurnos controladorTurno_tiempo = new ControladorTurnos();
    ControladorDepartamento controladorDepartamento = new ControladorDepartamento();
    ControladorArea controladorArea = new ControladorArea();
    ControladorCiudad controladorCiudad = new ControladorCiudad();
    ControladorCentro_costo controladorCentro_costo = new ControladorCentro_costo();
    ControladorCargo controladorCargo = new ControladorCargo();
    ControladorGrupo_consumo controladorGrupo_consumo = new ControladorGrupo_consumo();
    ControladorImagen controladorImagen = new ControladorImagen();
    Herramienta herramienta = new Herramienta();
    int i;

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd
     * Personas, el insert es fucnional para cada uno de los modulos
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) {
        String Modulo = request.getParameter("modulo");
        switch (Modulo) {
            case "Casino":
                resultado = InsertCasino(request, response);
                break;
        }
        return resultado;
    }

    /**
     *
     * /**
     * Permite la eliminar un dato en la tabla de las Personas
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Delete(HttpServletRequest request) {
        if (!"".equals(request.getParameter("id"))) {
            ModeloPersona modeloPersona = new ModeloPersona();
            modeloPersona.setId(Integer.parseInt(request.getParameter("id")));
            modeloPersona.setEstado("N");
            resultado = UpdateCasino(modeloPersona);
        }
        return resultado;
    }

    /**
     * Permite listar la información de la tabla de Personas
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @param response
     * @return String
     * @version: 07/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) {
        String Modulo = request.getParameter("modulo");
        switch (Modulo) {
            case "Casino":
                resultado = ReadCasino(request, response);
                break;
        }
        return resultado;
    }

    /**
     * Permite listar la información de la tabla de Personas identificado el ID
     *
     * @author: Carlos A Dominguez D
     * @param Id
     * @return ModeloEmpresa
     * @version: 07/05/2020
     */
    ModeloPersona getModelo(int Id) {
        String resultado = null;
        ModeloPersona modelo = new ModeloPersona();
        con = conexion.abrirConexion();
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
                    + "id_grupo_consumo"
                    + " FROM persona"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
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
                modelo.setModelo_empresa_trabaja(controladorEmpresa.getModelo(Integer.parseInt(res.getString("id_empresa_trabaja"))));
                modelo.setModelo_grupo_consumo(controladorGrupo_consumo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_grupo_consumo")))));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return modelo;
    }

    /**
     * Permite listar la información de la tabla de Personas identificado el
     * Numero de Cedula de la persona
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @param response
     * @return ModeloEmpresa
     * @version: 07/05/2020
     */
    public ModeloPersona GetPersonaCedula(HttpServletRequest request, HttpServletResponse response) {
        String resultado = null;
        ModeloPersona modelo = new ModeloPersona();
        con = conexion.abrirConexion();
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
                    + "id_grupo_consumo"
                    + " FROM persona"
                    + " WHERE identificacion = ? ");
            SQL.setString(1, request.getParameter("cedula"));
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modelo.setId(res.getInt("id"));
                modelo.setTipo_identificacion(res.getString("tipoIdentificacion"));
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
                modelo.setTarjeta_acceso(res.getString("tarjeta_acceso"));
                modelo.setCodigo_nomina(res.getString("codigo_nomina"));
                modelo.setEstado(res.getString("estado"));
                modelo.setModelo_dependencia(controladorDependencia.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_dependencia")))));
                modelo.setModelo_empresa_seguridad_social(controladorEmpresa.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_empresa_seguridad_social")))));
                modelo.setModelo_grupo_horario(controladorGrupo_horario.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_grupo_horario")))));
                modelo.setModelo_turno(controladorTurno_tiempo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_turno")))));
                //modelo.setModelo_departamento(controladorDepartamento.res.getString("id_departamento"));
                modelo.setModelo_area(controladorArea.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_area")))));
                modelo.setModelo_ciudad(controladorCiudad.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_ciudad")))));
                modelo.setModelo_centro_costo(controladorCentro_costo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_centro_costo")))));
                modelo.setModelo_cargo(controladorCargo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_cargo")))));
                modelo.setModelo_empresa_trabaja(controladorEmpresa.getModelo(Integer.parseInt(res.getString("id_empresa_trabaja"))));
                modelo.setModelo_grupo_consumo(controladorGrupo_consumo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_grupo_consumo")))));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return modelo;
    }

    /**
     * Permite la eliminar un dato en la tabla de Personas en el modulo de
     * Casino
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    private String InsertCasino(HttpServletRequest request, HttpServletResponse response) {

        ModeloPersona modeloPersona = new ModeloPersona();
        modeloPersona.setTipo_identificacion(request.getParameter("tipodoc"));
        modeloPersona.setIdentificacion(request.getParameter("cedula"));
        modeloPersona.setNombres(request.getParameter("nombre"));
        modeloPersona.setApellidos(request.getParameter("apellido"));
        modeloPersona.setObservacion(request.getParameter("observacion"));
        modeloPersona.setConsumo_casino(request.getParameter("consumo"));
        modeloPersona.setModelo_centro_costo(controladorCentro_costo.getModelo(Integer.parseInt(request.getParameter("centrocosto"))));
        modeloPersona.setModelo_empresa_trabaja(controladorEmpresa.getModelo(Integer.parseInt(request.getParameter("empresa"))));
        modeloPersona.setModelo_grupo_consumo(controladorGrupo_consumo.getModelo(Integer.parseInt(request.getParameter("grupoconsumo"))));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = InsertCasino(modeloPersona);
            if ("1".equals(resultado)) {
                String[] Huellas = {request.getParameter("huella0"),
                    request.getParameter("huella1"),
                    request.getParameter("huella2"),
                    request.getParameter("huella3"),
                    request.getParameter("huella4"),
                    request.getParameter("huella5"),
                    request.getParameter("huella6"),
                    request.getParameter("huella7"),
                    request.getParameter("huella8"),
                    request.getParameter("huella9")};
                String IdTemplate = request.getParameter("idtemplates");
                String Template = request.getParameter("templates10");
                String Foto = request.getParameter("foto");
                String firma = request.getParameter("firma");
                resultado = controladorImagen.Insert(Huellas, Foto, firma, getModelo(i));             
            }
        } else {
            modeloPersona.setId(Integer.parseInt(request.getParameter("id")));
            resultado = UpdateCasino(modeloPersona);
        }

//        if ("".equals(request.getParameter("id"))) {
//            ModeloPersona modelo = new ModeloPersona();
//            modelo.setId(0);
//            modelo.setTipo_identificacion(request.getParameter("tipodoc"));
//            modelo.setIdentificacion(request.getParameter("cedula"));
//            modelo.setNombres(request.getParameter("nombre"));
//            modelo.setApellidos(request.getParameter("apellido"));
//            modelo.setModelo_empresa_trabaja(controladorEmpresas.getModelo(Integer.parseInt(request.getParameter("empresa"))));
//            modelo.setModelo_centro_costo(controladorCentroCosto.getModelo(Integer.parseInt(request.getParameter("centrocosto"))));
//            modelo.setConsumo_casino(request.getParameter("consumo"));
//            modelo.setModelo_grupo_consumo(controladorGrupoConsumo.getModelo(Integer.parseInt(request.getParameter("grupoconsumo"))));
//            modelo.setObservacion(request.getParameter("observacion"));
//
//            try {
//                con = conexion.abrirConexion();
//                try {
//                    SQL = con.prepareStatement("INSERT INTO `persona`("
//                            + "`tipoIdentificacion`,"
//                            + "`identificacion`,"
//                            + "`nombres`,"
//                            + "`apellidos`,"
//                            + "`Id_EmpresaTrabaja`,"
//                            + "`centroCostoId`,"
//                            + "`observaciones`,"
//                            + "`consumocasino`,"
//                            + "`grupoConsumo`)"
//                            + " VALUE (?,?,?,?,?,?,?,?,?);", SQL.RETURN_GENERATED_KEYS);
//                    SQL.setString(1, modelo.getTipoIdentificacion());
//                    SQL.setString(2, modelo.getIdentificacion());
//                    SQL.setString(3, modelo.getNombres());
//                    SQL.setString(4, modelo.getApellidos());
//                    SQL.setInt(5, modelo.getModeloEmpresa().getId());
//                    SQL.setInt(6, modelo.getModeloCentroCosto().getId());
//                    SQL.setString(7, modelo.getObservaciones());
//                    SQL.setString(8, modelo.getConsumocasino());
//                    SQL.setInt(9, modelo.getModeloGrupoConsumo().getId());
//                    if (SQL.executeUpdate() > 0) {
//                        ControladorAuditoria auditoria = new ControladorAuditoria();
//                        try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
//                            if (generatedKeys.next()) {
//                                int i = (int) generatedKeys.getLong(1);
//                                auditoria.Insert("insertar", "persona", request.getParameter("nombreU"), i, "Se inserto el registro personacasino.");
//                            }
//                        }
//                        resultado = "1";
//                        SQL.close();
//                        con.close();
//                    }
//                } catch (SQLException e) {
//                    System.out.println(e);
//                    resultado = "-2";
//                    SQL.close();
//                    con.close();
//                }
//            } catch (SQLException e) {
//                System.out.println(e);
//                resultado = "-3";
//            }
//        } else {
//            ModeloPersona modelo = new ModeloPersona();
//            modelo.setId(0);
//            modelo.setTipo_identificacion(request.getParameter("tipodoc"));
//            modelo.setIdentificacion(request.getParameter("cedula"));
//            modelo.setNombres(request.getParameter("nombre"));
//            modelo.setApellidos(request.getParameter("apellido"));
//            modelo.setModelo_empresa_trabaja(controladorEmpresas.getModelo(Integer.parseInt(request.getParameter("empresa"))));
//            modelo.setModelo_centro_costo(controladorCentroCosto.getModelo(Integer.parseInt(request.getParameter("centrocosto"))));
//            modelo.setConsumo_casino(request.getParameter("consumo"));
//            modelo.setModelo_grupo_consumo(controladorGrupoConsumo.getModelo(Integer.parseInt(request.getParameter("grupoconsumo"))));
//            modelo.setObservacion(request.getParameter("observacion"));
//
//            try {
//                con = conexion.abrirConexion();
//                try {
//                    SQL = con.prepareStatement("UPDATE `persona`  SET "
//                            + "`tipoIdentificacion` = ?,"
//                            + "`identificacion` = ?,"
//                            + "`nombres` = ?,"
//                            + "`apellidos` = ?,"
//                            + "`Id_EmpresaTrabaja` = ?,"
//                            + "`centroCostoId` = ?,"
//                            + "`observaciones` = ?,"
//                            + "`consumocasino` = ?,"
//                            + "`grupoConsumo` = ?"
//                            + " WHERE `Id` = ?;");
//                    SQL.setString(1, modelo.getTipoIdentificacion());
//                    SQL.setString(2, modelo.getIdentificacion());
//                    SQL.setString(3, modelo.getNombres());
//                    SQL.setString(4, modelo.getApellidos());
//                    SQL.setInt(5, modelo.getModeloEmpresa().getId());
//                    SQL.setInt(6, modelo.getModeloCentroCosto().getId());
//                    SQL.setString(7, modelo.getObservaciones());
//                    SQL.setString(8, modelo.getConsumocasino());
//                    SQL.setInt(9, modelo.getModeloGrupoConsumo().getId());
//                    SQL.setInt(10, modelo.getId());
//                    if (SQL.executeUpdate() > 0) {
//                        resultado = "1";
//                        SQL.close();
//                        con.close();
//                    }
//                } catch (SQLException e) {
//                    System.out.println(e);
//                    resultado = "-2";
//                    SQL.close();
//                    con.close();
//                }
//            } catch (SQLException e) {
//                System.out.println(e);
//                resultado = "-3";
//            }
//        }
        return resultado;
    }

    /**
     * Permite listar la información de la tabla de personas en el modulo de
     * casino
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @param response
     * @return String
     * @version: 07/05/2020
     */
    private String ReadCasino(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        try {
            LinkedList<ModeloPersona> listaPersonas = new LinkedList<ModeloPersona>();
            listaPersonas = Read("S");
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Cedula</th>";
            out += "<th>Nombre</th>";
            out += "<th>Empresa</th>";
            out += "<th>CentroCosto</th>";
            out += "<th>GrupoConsumo</th>";
            out += "<th>Casino</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloPersona modeloPersonas : listaPersonas) {
                out += "<tr>";
                out += "<td>" + modeloPersonas.getIdentificacion() + "</td>";
                out += "<td>" + modeloPersonas.getNombres() + " " + modeloPersonas.getApellidos() + "</td>";
                out += "<td>" + modeloPersonas.getModelo_empresa_trabaja().getNombre() + "</td>";
                out += "<td>" + modeloPersonas.getModelo_centro_costo().getNombre() + "</td>";
                out += "<td>" + modeloPersonas.getModelo_grupo_consumo().getNombre() + "</td>";
                if ("1".equals(modeloPersonas.getConsumo_casino())) {
                    out += "<td>No</td>";
                } else {
                    out += "<td>Si</td>";
                }
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\" data-toggle=\"modal\" data-target=\"#ModalFormulario\"data-whatever=\"@getbootstrap\"";
                out += "data-id=\"" + modeloPersonas.getId() + "\"";
                out += "data-tipodoc=\"" + modeloPersonas.getTipo_identificacion() + "\"";
                out += "data-cedula=\"" + modeloPersonas.getIdentificacion() + "\"";
                out += "data-nombre=\"" + modeloPersonas.getNombres() + "\"";
                out += "data-apellido=\"" + modeloPersonas.getApellidos() + "\"";
                out += "data-empresa=\"" + modeloPersonas.getModelo_empresa_trabaja().getId() + "\"";
                out += "data-centrocosto=\"" + modeloPersonas.getModelo_centro_costo().getId() + "\"";
                out += "data-grupoconsumo=\"" + modeloPersonas.getModelo_grupo_consumo().getId() + "\"";
                out += "data-consume=\"" + modeloPersonas.getConsumo_casino() + "\"";
                out += "data-observacion=\"" + modeloPersonas.getObservacion() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloPersonas.getId() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i></button>";
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
     * Permite listar la información de la tabla de las Personas Metodo Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    private LinkedList<ModeloPersona> Read(String estado) {
        LinkedList<ModeloPersona> listaModeloPersonas = new LinkedList<ModeloPersona>();
        con = conexion.abrirConexion();
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
                    + "WHERE `estado` = ? ;");
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

    /**
     * Permite consultar los datos de la Personas mediante elnumero de cedula,
     * retonna un json
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 08/05/2020
     */
    public String Search(HttpServletRequest request) {
        String cedula = request.getParameter("cedula");
        ModeloPersona modelo = new ModeloPersona();
        con = conexion.abrirConexion();
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
                    + "id_grupo_consumo"
                    + " FROM persona"
                    + " WHERE identificacion = ? ");
            SQL.setString(1, cedula);

            ResultSet res = SQL.executeQuery();
            while (res.next()) {

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
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        resultado = new Gson().toJson(modelo);
        return resultado;
    }

    private String InsertCasino(ModeloPersona modeloPersona) {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO persona("
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
                        + "tarjeta_acceso, "
                        + "codigo_nomina, "
                        + "estado, "
                        + "id_centro_costo, "
                        + "id_empresa_trabaja, "
                        + "id_grupo_consumo)"
                        + " VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloPersona.getTipo_identificacion());
                SQL.setString(2, modeloPersona.getIdentificacion());
                SQL.setString(3, modeloPersona.getNombres());
                SQL.setString(4, modeloPersona.getApellidos());
                SQL.setString(5, modeloPersona.getEmail());
                SQL.setString(6, modeloPersona.getDireccion());
                SQL.setString(7, modeloPersona.getTelefono());
                SQL.setString(8, modeloPersona.getRh());
                SQL.setString(9, modeloPersona.getTipo_persona());
                SQL.setString(10, modeloPersona.getRecibe_visitas());
                SQL.setString(11, modeloPersona.getNombre_eps());
                SQL.setString(12, modeloPersona.getNombre_arl());
                SQL.setString(13, modeloPersona.getAcceso_restringido());
                SQL.setString(14, modeloPersona.getObservacion());
                SQL.setString(15, modeloPersona.getConsumo_casino());
                SQL.setString(16, modeloPersona.getTarjeta_acceso());
                SQL.setString(17, modeloPersona.getCodigo_nomina());
                SQL.setString(18, "S");
                SQL.setInt(19, modeloPersona.getModelo_centro_costo().getId());
                SQL.setInt(20, modeloPersona.getModelo_empresa_trabaja().getId());
                SQL.setInt(21, modeloPersona.getModelo_grupo_consumo().getId());
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.");
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladorpersona" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorpersona" + e);
            resultado = "-3";
        }
        return resultado;

    }

    /**
     * Actualiza los datos en la base de datos de la tabla:persona
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 19/05/2020
     */
    public String UpdateCasino(ModeloPersona modeloPersona) {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloPersona.getEstado())) {
                    SQL = con.prepareStatement("UPDATE persona SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloPersona.getEstado());
                    SQL.setInt(2, modeloPersona.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE persona SET "
                            + "tipo_identificacion = ?, "
                            + "identificacion = ?, "
                            + "nombres = ?, "
                            + "apellidos = ?, "
                            + "observacion = ?, "
                            + "consumo_casino = ?, "
                            + "id_centro_costo = ?, "
                            + "id_empresa_trabaja = ?, "
                            + "id_grupo_consumo = ? "
                            + "WHERE id = ? ");
                    SQL.setString(1, modeloPersona.getTipo_identificacion());
                    SQL.setString(2, modeloPersona.getIdentificacion());
                    SQL.setString(3, modeloPersona.getNombres());
                    SQL.setString(4, modeloPersona.getApellidos());
                    SQL.setString(5, modeloPersona.getObservacion());
                    SQL.setString(6, modeloPersona.getConsumo_casino());
                    SQL.setInt(7, modeloPersona.getModelo_centro_costo().getId());
                    SQL.setInt(8, modeloPersona.getModelo_empresa_trabaja().getId());
                    SQL.setInt(9, modeloPersona.getModelo_grupo_consumo().getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Controladores.ControladorPersonas.UpdateCasino() " + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Controladores.ControladorPersonas.UpdateCasino() " + e);
            resultado = "-3";
        }
        return resultado;

    }
}
