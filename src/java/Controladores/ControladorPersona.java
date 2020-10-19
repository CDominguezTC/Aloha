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
import Modelo.ModeloTemplate;
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
    ControladorGrupo_horario controladorGrupo_horario = new ControladorGrupo_horario();
    ControladorTurnos controladorTurno_tiempo = new ControladorTurnos();
    ControladorDepartamento controladorDepartamento = new ControladorDepartamento();
    ControladorArea controladorArea = new ControladorArea();
    ControladorCiudad controladorCiudad = new ControladorCiudad();
    ControladorCentro_costo controladorCentro_costo = new ControladorCentro_costo();
    ControladorCargo controladorCargo = new ControladorCargo();
    ControladorGrupo_consumo controladorGrupo_consumo = new ControladorGrupo_consumo();
    ControladorImagen controladorImagen = new ControladorImagen();
    ControladorTemplate controladorTemplate = new ControladorTemplate();
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
            case "Tiempos":
                resultado = InsertTiempos(request, response);
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
    public String Delete(HttpServletRequest request, HttpServletResponse response) {
        if (!"".equals(request.getParameter("id"))) {
            ModeloPersona modeloPersona = new ModeloPersona();
            modeloPersona.setId(Integer.parseInt(request.getParameter("id")));
            modeloPersona.setEstado("N");
            resultado = UpdateCasino(modeloPersona);
            if (resultado == "1") {
                resultado = "2";
            }
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
            case "Tiempos":
                resultado = ReadTiempos(request, response);
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
                    + "id_grupo_consumo"
                    + " FROM persona"
                    + " WHERE identificacion = ? AND "
                    + "estado = ?");
            SQL.setString(1, request.getParameter("cedula"));
            SQL.setString(2, "S");
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
                modelo.setCantidad_consumo(res.getInt("cantidad_consumo"));
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
        modeloPersona.setConsumo_hoteleria(request.getParameter("consumoh"));
        modeloPersona.setCantidad_consumo(Integer.parseInt(herramienta.validaString(request.getParameter("cantidad_consumo"))));
        modeloPersona.setTipo_persona(request.getParameter("tipopersona"));
        modeloPersona.setModelo_centro_costo(controladorCentro_costo.getModelo(Integer.parseInt(request.getParameter("centrocosto"))));
        modeloPersona.setModelo_empresa_trabaja(controladorEmpresa.getModelo(Integer.parseInt(request.getParameter("empresa"))));
        modeloPersona.setModelo_cargo(controladorCargo.getModelo(Integer.parseInt(request.getParameter("cargo"))));
        modeloPersona.setModelo_grupo_consumo(controladorGrupo_consumo.getModelo(Integer.parseInt(request.getParameter("grupoconsumo"))));
        modeloPersona.setCantidad_consumo(Integer.parseInt(herramienta.validaString(request.getParameter("noconsumos"))));
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
                resultado = controladorImagen.Insert(Huellas, Foto, firma, getModelo(i), "Insert");
                resultado = controladorTemplate.Insert(Template, IdTemplate, getModelo(i), "Insert");
            }
        } else {
            modeloPersona.setId(Integer.parseInt(request.getParameter("id")));
            resultado = UpdateCasino(modeloPersona);
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
                resultado = controladorImagen.Insert(Huellas, Foto, firma, getModelo(i), "Update");
                resultado = controladorTemplate.Insert(Template, IdTemplate, getModelo(i), "Update");
                resultado = "4";
            }
        }
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
            out += "<th>Hoteleria</th>";
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
                if ("S".equals(modeloPersonas.getConsumo_hoteleria())) {
                    out += "<td>Si</td>";
                } else {
                    out += "<td>No</td>";
                }
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\" data-toggle=\"modal\" data-target=\"#ModalFormulario\"data-whatever=\"@getbootstrap\"";
                out += "data-id=\"" + modeloPersonas.getId() + "\"";
                out += "data-tipodoc=\"" + modeloPersonas.getTipo_identificacion() + "\"";
                out += "data-cedula=\"" + modeloPersonas.getIdentificacion() + "\"";
                out += "data-nombre=\"" + modeloPersonas.getNombres() + "\"";
                out += "data-apellido=\"" + modeloPersonas.getApellidos() + "\"";
                out += "data-empresa=\"" + modeloPersonas.getModelo_empresa_trabaja().getId() + "\"";
                out += "data-cantidadconsumo=\"" + modeloPersonas.getCantidad_consumo() + "\"";
                out += "data-cargo=\"" + modeloPersonas.getModelo_cargo().getId() + "\"";
                out += "data-centrocosto=\"" + modeloPersonas.getModelo_centro_costo().getId() + "\"";
                out += "data-grupoconsumo=\"" + modeloPersonas.getModelo_grupo_consumo().getId() + "\"";
                out += "data-consume=\"" + modeloPersonas.getConsumo_casino() + "\"";
                out += "data-noconsumos=\"" + modeloPersonas.getCantidad_consumo() + "\"";
                out += "data-observacion=\"" + modeloPersonas.getObservacion() + "\"";
                //out += "data-consumeh=\"" + modeloPersonas.getConsumo_hoteleria() + "\"";
                if ("S".equals(modeloPersonas.getConsumo_hoteleria())) {
                    out += "data-consumeh=\"2\"";
                } else {
                    out += "data-consumeh=\"1\"";
                }
                
                //Campos de Imagenes
//                if (modeloPersonas.getLista_Modelo_Imagenes() != null) {
//                    for (ModeloImagen modeloImagen : modeloPersonas.getLista_Modelo_Imagenes()) {
//                        if (modeloImagen.getNumero_imagen() == 0) {
//                            out += "data-huella_0=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 1) {
//                            out += "data-huella_1=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 2) {
//                            out += "data-huella_2=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 3) {
//                            out += "data-huella_3=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 4) {
//                            out += "data-huella_4=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 5) {
//                            out += "data-huella_5=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 6) {
//                            out += "data-huella_6=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 7) {
//                            out += "data-huella_7=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 8) {
//                            out += "data-huella_8=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 9) {
//                            out += "data-huella_9=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        //Campos de Imagenes foto
//                        if (modeloImagen.getNumero_imagen() == 20) {
//                            out += "data-foto=\"" + modeloImagen.getImagen() + "\"";
//                        }
//                        //Campos de Imagenes firma                
//                        if (modeloImagen.getNumero_imagen() == 30) {
//                            out += "data-firma=\"" + modeloImagen.getImagen() + "\"";
//                        }
//                    }
//                }
//                //Campos de templates
//                String IdTemplates = "";
//                String Templates_10 = "";
//                int c = 0;
//                if (modeloPersonas.getLista_Modelo_Template() != null) {
//                    for (ModeloTemplate modeloTemplate : modeloPersonas.getLista_Modelo_Template()) {
//                        if (c == 0) {
//                            IdTemplates = modeloTemplate.getNumero_plantilla();
//                            Templates_10 = modeloTemplate.getPlantilla();
//                            c++;
//                        } else {
//                            IdTemplates = IdTemplates + "," + modeloTemplate.getNumero_plantilla();
//                            Templates_10 = Templates_10 + "," + modeloTemplate.getPlantilla();
//                            c++;
//                        }
//                    }
//                    IdTemplates = "[" + IdTemplates + "]";
//                    Templates_10 = "[" + Templates_10 + "]";
//                }
//                out += "data-idtemplate=\"[" + IdTemplates + "]\"";
//                out += "data-template10=\"" + Templates_10 + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
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
    public LinkedList<ModeloPersona> Read(String estado) {
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
                    + "id_grupo_consumo, "
                    + "consumo_hoteleria "
                    + " FROM persona "
                    + "WHERE `estado` = ? AND"
                    + "`tipo_persona` = ?;");
            SQL.setString(1, estado);
            SQL.setString(2, "EMPLEADO");
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
                modelo.setModelo_grupo_horario(controladorGrupo_horario.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_grupo_horario")))));
                modelo.setModelo_turno(controladorTurno_tiempo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_turno")))));
                //modelo.setModelo_departamento(controladorDepartamento.getModelo(herramienta.validaString(res.getString("id_departamento"))));
                modelo.setModelo_area(controladorArea.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_area")))));
                modelo.setModelo_ciudad(controladorCiudad.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_ciudad")))));
                modelo.setModelo_centro_costo(controladorCentro_costo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_centro_costo")))));
                modelo.setModelo_cargo(controladorCargo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_cargo")))));
                modelo.setModelo_empresa_trabaja(controladorEmpresa.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_empresa_trabaja")))));
                modelo.setModelo_grupo_consumo(controladorGrupo_consumo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_grupo_consumo")))));
                modelo.setConsumo_hoteleria(res.getString("consumo_hoteleria"));
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
                    + "FROM persona "
                    + "WHERE identificacion = ? AND "
                    + "estado = ?");
            SQL.setString(1, cedula);
            SQL.setString(2, "S");
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
                modelo.setCantidad_consumo(res.getInt("cantidad_consumo"));
                modelo.setTarjeta_acceso(res.getString("tarjeta_acceso"));
                modelo.setCodigo_nomina(res.getString("codigo_nomina"));
                modelo.setEstado(res.getString("estado"));
                modelo.setModelo_dependencia(controladorDependencia.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_dependencia")))));
                modelo.setModelo_empresa_seguridad_social(controladorEmpresa.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_empresa_seguridad_social")))));
                modelo.setModelo_grupo_horario(controladorGrupo_horario.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_grupo_horario")))));
                modelo.setModelo_turno(controladorTurno_tiempo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_turno")))));
                //modelo.setModelo_departamento(controladorDepartamento.getM res.getString("id_departamento"));
                modelo.setModelo_area(controladorArea.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_area")))));
                modelo.setModelo_ciudad(controladorCiudad.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_ciudad")))));
                modelo.setModelo_centro_costo(controladorCentro_costo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_centro_costo")))));
                modelo.setModelo_cargo(controladorCargo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_cargo")))));
                modelo.setModelo_empresa_trabaja(controladorEmpresa.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_empresa_trabaja")))));
                modelo.setModelo_grupo_consumo(controladorGrupo_consumo.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_grupo_consumo")))));
                //estos son los datos multimedia pendient por implmentar 
                modelo.setLista_Modelo_Imagenes(controladorImagen.getListaModelo(modelo.getId()));
                modelo.setLista_Modelo_Template(controladorTemplate.getModelo(modelo.getId()));
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
                        + "cantidad_consumo, "
                        + "tarjeta_acceso, "
                        + "codigo_nomina, "
                        + "estado, "
                        + "id_centro_costo, "
                        + "id_empresa_trabaja, "
                        + "id_grupo_consumo, "
                        + "id_cargo, "
                        + "consumo_hoteleria) "
                        + " VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", SQL.RETURN_GENERATED_KEYS);
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
                SQL.setInt(22, modeloPersona.getModelo_cargo().getId());
                SQL.setInt(16, modeloPersona.getCantidad_consumo());
                SQL.setString(17, modeloPersona.getTarjeta_acceso());
                SQL.setString(18, modeloPersona.getCodigo_nomina());
                SQL.setString(19, "S");
                SQL.setInt(20, modeloPersona.getModelo_centro_costo().getId());
                SQL.setInt(21, modeloPersona.getModelo_empresa_trabaja().getId());
                SQL.setInt(22, modeloPersona.getModelo_grupo_consumo().getId());
                SQL.setInt(23, modeloPersona.getModelo_cargo().getId());
                SQL.setString(24, modeloPersona.getConsumo_hoteleria());
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.", "", "");
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
                            + "cantidad_consumo = ?, "
                            + "id_centro_costo = ?, "
                            + "id_empresa_trabaja = ?, "
                            + "id_grupo_consumo = ?, "
                            + "id_cargo = ?, "
                            + "consumo_hoteleria = ? "
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
                    SQL.setInt(10, modeloPersona.getModelo_cargo().getId());
                    SQL.setInt(11, modeloPersona.getId());
                    SQL.setInt(7, modeloPersona.getCantidad_consumo());
                    SQL.setInt(8, modeloPersona.getModelo_centro_costo().getId());
                    SQL.setInt(9, modeloPersona.getModelo_empresa_trabaja().getId());
                    SQL.setInt(10, modeloPersona.getModelo_grupo_consumo().getId());
                    SQL.setInt(11, modeloPersona.getModelo_cargo().getId());
                    SQL.setString(12, modeloPersona.getConsumo_hoteleria());
                    SQL.setInt(13, modeloPersona.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    i = modeloPersona.getId();
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

    private String ReadTiempos(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        try {
            LinkedList<ModeloPersona> listaPersonas = new LinkedList<ModeloPersona>();
            listaPersonas = Read("S");
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("SelectPersonasLiquidacionTiempos".equals(parametro)) {
                out = "";
                out += "<thead>";
                out += "<tr>";
                out += "<th>Nombre Empleado</th>";                                               
                out += "<th class=size>Detalle</th>";                
                out += "</tr>";
                out += "</thead>";
                out += "<tbody>";
                for (ModeloPersona modeloPersonas : listaPersonas) {
                    out += "<tr>";                    
                    out += "<td class=\"datoEmp\" data-idpersona=\"" + modeloPersonas.getId()+ "\" data-ced=\"" + modeloPersonas.getIdentificacion()+ "\">" + modeloPersonas.getNombresApellido()+ "</td>";                    
                    out += "<td class=\"text-center\">"; 
                    out += "<button class=\"SetEmpleado btn-secondary btn-sm\" form=\"marcaciones_form\"";
                    //out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\" data-toggle=\"modal\" data-target=\"#ModalFormulario\"data-whatever=\"@getbootstrap\"";
                    out += "data-id=\"" + modeloPersonas.getId() + "\"";
                    out += "data-tipodoc=\"" + modeloPersonas.getTipo_identificacion() + "\"";
                    out += "data-cedula=\"" + modeloPersonas.getIdentificacion() + "\"";
                    out += "data-nombre=\"" + modeloPersonas.getNombres() + "\"";
                    out += "data-apellido=\"" + modeloPersonas.getApellidos() + "\"";
                    out += "data-codigonomina=\"" + modeloPersonas.getCodigo_nomina() + "\"";
                    out += "data-cargo=\"" + modeloPersonas.getModelo_cargo().getId() + "\"";
                    out += "data-empresa=\"" + modeloPersonas.getModelo_empresa_trabaja().getId() + "\"";
                    out += "data-dependencia=\"" + modeloPersonas.getModelo_dependencia().getId() + "\"";
                    out += "data-centrocosto=\"" + modeloPersonas.getModelo_centro_costo().getId() + "\"";
                    out += "data-area=\"" + modeloPersonas.getModelo_area().getId() + "\"";
                    out += "data-grupohorario=\"" + modeloPersonas.getModelo_grupo_horario().getId() + "\"";
                    out += "data-ciudad=\"" + modeloPersonas.getModelo_ciudad().getId() + "\"";
                    out += "data-observacion=\"" + modeloPersonas.getObservacion() + "\"";                    
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-desktop\"></i> </button>";
                    out += "</td>";
                    out += "</tr>";
                }
                out += "</tbody>";
            } else {
                out = "";
                out += "<thead>";
                out += "<tr>";
                out += "<th>Cedula</th>";
                out += "<th>Nombre</th>";
                out += "<th>Empresa</th>";
                out += "<th>CentroCosto</th>";
                out += "<th>Dependencia</th>";
                out += "<th>Area</th>";
                out += "<th>GrupoHorario</th>";
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
                    out += "<td>" + modeloPersonas.getModelo_dependencia().getNombre() + "</td>";
                    out += "<td>" + modeloPersonas.getModelo_area().getNombre() + "</td>";
                    out += "<td>" + modeloPersonas.getModelo_grupo_horario().getNombre() + "</td>";
                    out += "<td class=\"text-center\">";
                    // Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\" data-toggle=\"modal\" data-target=\"#ModalFormulario\"data-whatever=\"@getbootstrap\"";
                    out += "data-id=\"" + modeloPersonas.getId() + "\"";
                    out += "data-tipodoc=\"" + modeloPersonas.getTipo_identificacion() + "\"";
                    out += "data-cedula=\"" + modeloPersonas.getIdentificacion() + "\"";
                    out += "data-nombre=\"" + modeloPersonas.getNombres() + "\"";
                    out += "data-apellido=\"" + modeloPersonas.getApellidos() + "\"";
                    out += "data-codigonomina=\"" + modeloPersonas.getCodigo_nomina() + "\"";
                    out += "data-cargo=\"" + modeloPersonas.getModelo_cargo().getId() + "\"";
                    out += "data-empresa=\"" + modeloPersonas.getModelo_empresa_trabaja().getId() + "\"";
                    out += "data-dependencia=\"" + modeloPersonas.getModelo_dependencia().getId() + "\"";
                    out += "data-centrocosto=\"" + modeloPersonas.getModelo_centro_costo().getId() + "\"";
                    out += "data-area=\"" + modeloPersonas.getModelo_area().getId() + "\"";
                    out += "data-grupohorario=\"" + modeloPersonas.getModelo_grupo_horario().getId() + "\"";
                    out += "data-ciudad=\"" + modeloPersonas.getModelo_ciudad().getId() + "\"";
                    out += "data-observacion=\"" + modeloPersonas.getObservacion() + "\"";
                    //Campos de Imagenes
//                if (modeloPersonas.getLista_Modelo_Imagenes() != null) {
//                    for (ModeloImagen modeloImagen : modeloPersonas.getLista_Modelo_Imagenes()) {
//                        if (modeloImagen.getNumero_imagen() == 0) {
//                            out += "data-huella_0=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 1) {
//                            out += "data-huella_1=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 2) {
//                            out += "data-huella_2=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 3) {
//                            out += "data-huella_3=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 4) {
//                            out += "data-huella_4=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 5) {
//                            out += "data-huella_5=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 6) {
//                            out += "data-huella_6=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 7) {
//                            out += "data-huella_7=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 8) {
//                            out += "data-huella_8=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        if (modeloImagen.getNumero_imagen() == 9) {
//                            out += "data-huella_9=\"" + modeloImagen.getImagen() + "," + modeloImagen.getNumero_imagen() + "\"";
//                        }
//                        //Campos de Imagenes foto
//                        if (modeloImagen.getNumero_imagen() == 20) {
//                            out += "data-foto=\"" + modeloImagen.getImagen() + "\"";
//                        }
//                        //Campos de Imagenes firma                
//                        if (modeloImagen.getNumero_imagen() == 30) {
//                            out += "data-firma=\"" + modeloImagen.getImagen() + "\"";
//                        }
//                    }
//                }
//                //Campos de templates
//                String IdTemplates = "";
//                String Templates_10 = "";
//                int c = 0;
//                if (modeloPersonas.getLista_Modelo_Template() != null) {
//                    for (ModeloTemplate modeloTemplate : modeloPersonas.getLista_Modelo_Template()) {
//                        if (c == 0) {
//                            IdTemplates = modeloTemplate.getNumero_plantilla();
//                            Templates_10 = modeloTemplate.getPlantilla();
//                            c++;
//                        } else {
//                            IdTemplates = IdTemplates + "," + modeloTemplate.getNumero_plantilla();
//                            Templates_10 = Templates_10 + "," + modeloTemplate.getPlantilla();
//                            c++;
//                        }
//                    }
//                    IdTemplates = "[" + IdTemplates + "]";
//                    Templates_10 = "[" + Templates_10 + "]";
//                }
//                out += "data-idtemplate=\"[" + IdTemplates + "]\"";
//                out += "data-template10=\"" + Templates_10 + "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                    //Boton Eliminar
                    out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                    out += "data-id=\"" + modeloPersonas.getId() + "\"";
                    out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i></button>";
                    out += "</td>";
                    out += "</tr>";
                }
                out += "</tbody>";
            }
        } catch (Exception e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }

    private String InsertTiempos(HttpServletRequest request, HttpServletResponse response) {
        ModeloPersona modeloPersona = new ModeloPersona();
        modeloPersona.setTipo_identificacion(request.getParameter("tipodoc"));
        modeloPersona.setIdentificacion(request.getParameter("cedula"));
        modeloPersona.setNombres(request.getParameter("nombre"));
        modeloPersona.setApellidos(request.getParameter("apellido"));
        modeloPersona.setModelo_empresa_trabaja(controladorEmpresa.getModelo(Integer.parseInt(request.getParameter("empresa"))));
        modeloPersona.setModelo_dependencia(controladorDependencia.getModelo(Integer.parseInt(request.getParameter("dependencia"))));
        modeloPersona.setModelo_cargo(controladorCargo.getModelo(Integer.parseInt(request.getParameter("cargo"))));
        modeloPersona.setModelo_centro_costo(controladorCentro_costo.getModelo(Integer.parseInt(request.getParameter("centrocosto"))));
        modeloPersona.setCodigo_nomina(request.getParameter("codigo"));
        modeloPersona.setModelo_area(controladorArea.getModelo(Integer.parseInt(request.getParameter("area"))));
        modeloPersona.setModelo_ciudad(controladorCiudad.getModelo(Integer.parseInt(request.getParameter("ciudad"))));
        modeloPersona.setModelo_grupo_horario(controladorGrupo_horario.getModelo(Integer.parseInt(request.getParameter("grupohorario"))));
        modeloPersona.setObservacion(request.getParameter("observacion"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = InsertTiempos(modeloPersona);
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
                resultado = controladorImagen.Insert(Huellas, Foto, firma, getModelo(i), "Insert");
                resultado = controladorTemplate.Insert(Template, IdTemplate, getModelo(i), "Insert");
            }
        } else {
            modeloPersona.setId(Integer.parseInt(request.getParameter("id")));
            resultado = UpdateTiempos(modeloPersona);
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
                resultado = controladorImagen.Insert(Huellas, Foto, firma, getModelo(i), "Update");
                resultado = controladorTemplate.Insert(Template, IdTemplate, getModelo(i), "Update");
                resultado = "4";
            }
        }
        return resultado;
    }

    private String UpdateTiempos(ModeloPersona modeloPersona) {
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
                    SQL = con.prepareStatement("UPDATE `persona` SET "
                            + "  `tipo_identificacion` = ?, "
                            + "  `identificacion` = ?, "
                            + "  `nombres` = ?, "
                            + "  `apellidos` = ?, "
                            + "  `direccion` = ?, "
                            + "  `telefono` = ?, "
                            + "  `rh` = ?, "
                            + "  `tipo_persona` = ?, "
                            + "  `observacion` = ?, "
                            + "  `codigo_nomina` = ?, "
                            + "  `estado` = ?, "
                            + "  `id_empresa_trabaja` = ?, "
                            + "  `id_dependencia` = ?, "
                            + "  `id_cargo` = ?, "
                            + "  `id_centro_costo` = ?, "
                            + "  `id_area` = ?, "
                            + "  `id_ciudad` = ?, "
                            + "  `id_grupo_horario` = ? "
                            + "WHERE `id` = ?;");
                    SQL.setString(1, modeloPersona.getTipo_identificacion());
                    SQL.setString(2, modeloPersona.getIdentificacion());
                    SQL.setString(3, modeloPersona.getNombres());
                    SQL.setString(4, modeloPersona.getApellidos());
                    SQL.setString(5, modeloPersona.getDireccion());
                    SQL.setString(6, modeloPersona.getTelefono());
                    SQL.setString(7, modeloPersona.getRh());
                    SQL.setString(8, "EMPLEADO");
                    SQL.setString(9, modeloPersona.getObservacion());
                    SQL.setString(10, modeloPersona.getCodigo_nomina());
                    SQL.setString(11, "S");
                    SQL.setInt(12, modeloPersona.getModelo_empresa_trabaja().getId());
                    SQL.setInt(13, modeloPersona.getModelo_dependencia().getId());
                    SQL.setInt(14, modeloPersona.getModelo_cargo().getId());
                    SQL.setInt(15, modeloPersona.getModelo_centro_costo().getId());
                    SQL.setInt(16, modeloPersona.getModelo_area().getId());
                    SQL.setInt(17, modeloPersona.getModelo_ciudad().getId());
                    SQL.setInt(18, modeloPersona.getModelo_grupo_horario().getId());
                    SQL.setInt(19, modeloPersona.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    i = modeloPersona.getId();
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Controladores.ControladorPersona.UpdateTiempos() " + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Controladores.ControladorPersona.UpdateTiempos() " + e);
            resultado = "-3";
        }
        return resultado;
    }

    private String InsertTiempos(ModeloPersona modeloPersona) {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO `persona`("
                        + "`tipo_identificacion`, "
                        + "`identificacion`, "
                        + "`nombres`, "
                        + "`apellidos`, "
                        + "`email`, "
                        + "`direccion`, "
                        + "`telefono`, "
                        + "`rh`, "
                        + "`tipo_persona`, "
                        + "`observacion`, "
                        + "`codigo_nomina`, "
                        + "`estado`, "
                        + "`id_empresa_trabaja`, "
                        + "`id_dependencia`, "
                        + "`id_cargo`, "
                        + "`id_centro_costo`, "
                        + "`id_area`, "
                        + "`id_ciudad`, "
                        + "`id_grupo_horario`) "
                        + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloPersona.getTipo_identificacion());
                SQL.setString(2, modeloPersona.getIdentificacion());
                SQL.setString(3, modeloPersona.getNombres());
                SQL.setString(4, modeloPersona.getApellidos());
                SQL.setString(5, modeloPersona.getEmail());
                SQL.setString(6, modeloPersona.getDireccion());
                SQL.setString(7, modeloPersona.getTelefono());
                SQL.setString(8, modeloPersona.getRh());
                SQL.setString(9, "EMPLEADO");
                SQL.setString(10, modeloPersona.getObservacion());
                SQL.setString(11, modeloPersona.getCodigo_nomina());
                SQL.setString(12, "S");
                SQL.setInt(13, modeloPersona.getModelo_empresa_trabaja().getId());
                SQL.setInt(14, modeloPersona.getModelo_dependencia().getId());
                SQL.setInt(15, modeloPersona.getModelo_cargo().getId());
                SQL.setInt(16, modeloPersona.getModelo_centro_costo().getId());
                SQL.setInt(17, modeloPersona.getModelo_area().getId());
                SQL.setInt(18, modeloPersona.getModelo_ciudad().getId());
                SQL.setInt(19, modeloPersona.getModelo_grupo_horario().getId());
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.", "", "");
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Controladores.ControladorPersona.InsertTiempos() " + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Controladores.ControladorPersona.InsertTiempos() " + e);
            resultado = "-3";
        }
        return resultado;
    }
}
