/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloPersonas;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.nio.cs.ext.GB18030;

/**
 * Esta clase permite controlar los eventos de Personas contrine Insert -
 * Update, Delete, Read
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorPersonas {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    ControladorEmpresas controladorEmpresas = new ControladorEmpresas();
    ControladorCentroCosto controladorCentroCosto = new ControladorCentroCosto();
    ControladorGrupoConsumo controladorGrupoConsumo = new ControladorGrupoConsumo();
    ControladorCargos controladorCargos = new ControladorCargos();

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd
     * Personas, el insert es fucnional para cada uno de los modulos
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Insert(HttpServletRequest request) {
        String Modulo = request.getParameter("modulo");
        switch (Modulo) {
            case "Casino":
                resultado = InsertCasino(request);
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
            String idtmp = request.getParameter("id");
            ModeloPersonas modelo = new ModeloPersonas();
            modelo.setId(Integer.parseInt(request.getParameter("id")));
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("DELETE FROM `persona` "
                            + "WHERE `Id` = ?;");
                    SQL.setInt(1, modelo.getId());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "2";
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    resultado = "-4";
                }
                SQL.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
                resultado = "-3";
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
    ModeloPersonas getModelo(int Id) {
        String resultado = null;
        ModeloPersonas modelo = new ModeloPersonas();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`tipoIdentificacion`,"
                    + "`identificacion`,"
                    + "`nombres`,"
                    + "`apellidos`,"
                    + "`Id_EmpresaTrabaja`,"
                    + "`centroCostoId`,"
                    + "`observaciones`,"
                    + "`consumocasino`,"
                    + "`grupoConsumo`"
                    + "FROM `persona`"
                    + "WHERE id = ?;");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modelo.setId(res.getInt("id"));
                modelo.setTipoIdentificacion(res.getString("tipoIdentificacion"));
                modelo.setIdentificacion(res.getString("identificacion"));
                modelo.setNombres(res.getString("nombres"));
                modelo.setApellidos(res.getString("apellidos"));
                modelo.setModeloEmpresa(controladorEmpresas.getModelo(Integer.parseInt(res.getString("Id_EmpresaTrabaja"))));
                modelo.setModeloCentroCosto(controladorCentroCosto.getModelo(Integer.parseInt(res.getString("centroCostoId"))));
                modelo.setObservaciones(res.getString("observaciones"));
                modelo.setConsumocasino(res.getString("consumocasino"));
                modelo.setModeloGrupoConsumo(controladorGrupoConsumo.getModelo(Integer.parseInt(res.getString("grupoConsumo"))));
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
    public ModeloPersonas GetPersonaCedula(HttpServletRequest request, HttpServletResponse response) {
        String resultado = null;
        ModeloPersonas modelo = new ModeloPersonas();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`tipoIdentificacion`,"
                    + "`identificacion`,"
                    + "`nombres`,"
                    + "`apellidos`,"
                    + "`Id_EmpresaTrabaja`,"
                    + "`centroCostoId`,"
                    + "`observaciones`,"
                    + "`consumocasino`,"
                    + "`grupoConsumo`"
                    + "FROM `persona`"
                    + " WHERE identificacion = ?;");
            SQL.setString(1, request.getParameter("cedula"));
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modelo.setId(res.getInt("id"));
                modelo.setTipoIdentificacion(res.getString("tipoIdentificacion"));
                modelo.setIdentificacion(res.getString("identificacion"));
                modelo.setNombres(res.getString("nombres"));
                modelo.setApellidos(res.getString("apellidos"));
                modelo.setModeloEmpresa(controladorEmpresas.getModelo(Integer.parseInt(res.getString("Id_EmpresaTrabaja"))));
                modelo.setModeloCentroCosto(controladorCentroCosto.getModelo(Integer.parseInt(res.getString("centroCostoId"))));
                modelo.setObservaciones(res.getString("observaciones"));
                modelo.setConsumocasino(res.getString("consumocasino"));
                modelo.setModeloGrupoConsumo(controladorGrupoConsumo.getModelo(Integer.parseInt(res.getString("grupoConsumo"))));
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
    private String InsertCasino(HttpServletRequest request) {
        if ("".equals(request.getParameter("id"))) {
            ModeloPersonas modelo = new ModeloPersonas();
            modelo.setId(0);
            modelo.setTipoIdentificacion(request.getParameter("tipodoc"));
            modelo.setIdentificacion(request.getParameter("cedula"));
            modelo.setNombres(request.getParameter("nombre"));
            modelo.setApellidos(request.getParameter("apellido"));
            modelo.setModeloEmpresa(controladorEmpresas.getModelo(Integer.parseInt(request.getParameter("empresa"))));
            modelo.setModeloCentroCosto(controladorCentroCosto.getModelo(Integer.parseInt(request.getParameter("centrocosto"))));
            modelo.setConsumocasino(request.getParameter("consumo"));
            modelo.setModeloGrupoConsumo(controladorGrupoConsumo.getModelo(Integer.parseInt(request.getParameter("grupoconsumo"))));
            modelo.setObservaciones(request.getParameter("observacion"));
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("INSERT INTO `persona`("
                            + "`tipoIdentificacion`,"
                            + "`identificacion`,"
                            + "`nombres`,"
                            + "`apellidos`,"
                            + "`Id_EmpresaTrabaja`,"
                            + "`centroCostoId`,"
                            + "`observaciones`,"
                            + "`consumocasino`,"
                            + "`grupoConsumo`)"
                            + " VALUE (?,?,?,?,?,?,?,?,?);", SQL.RETURN_GENERATED_KEYS);
                    SQL.setString(1, modelo.getTipoIdentificacion());
                    SQL.setString(2, modelo.getIdentificacion());
                    SQL.setString(3, modelo.getNombres());
                    SQL.setString(4, modelo.getApellidos());
                    SQL.setInt(5, modelo.getModeloEmpresa().getId());
                    SQL.setInt(6, modelo.getModeloCentroCosto().getId());
                    SQL.setString(7, modelo.getObservaciones());
                    SQL.setString(8, modelo.getConsumocasino());
                    SQL.setInt(9, modelo.getModeloGrupoConsumo().getId());
                    if (SQL.executeUpdate() > 0) {
                        ControladorAuditoria auditoria = new ControladorAuditoria();
                        try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int i = (int) generatedKeys.getLong(1);
                                auditoria.Insert("insertar", "persona", request.getParameter("nombreU"), i, "Se inserto el registro.");
                            }
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                resultado = "-3";
            }
        } else {
            ModeloPersonas modelo = new ModeloPersonas();
            modelo.setId(Integer.parseInt(request.getParameter("id")));
            modelo.setTipoIdentificacion(request.getParameter("tipodoc"));
            modelo.setIdentificacion(request.getParameter("cedula"));
            modelo.setNombres(request.getParameter("nombre"));
            modelo.setApellidos(request.getParameter("apellido"));
            modelo.setModeloEmpresa(controladorEmpresas.getModelo(Integer.parseInt(request.getParameter("empresa"))));
            modelo.setModeloCentroCosto(controladorCentroCosto.getModelo(Integer.parseInt(request.getParameter("centrocosto"))));
            modelo.setConsumocasino(request.getParameter("consumo"));
            modelo.setModeloGrupoConsumo(controladorGrupoConsumo.getModelo(Integer.parseInt(request.getParameter("grupoconsumo"))));
            modelo.setObservaciones(request.getParameter("observacion"));

            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("UPDATE `persona`  SET "
                            + "`tipoIdentificacion` = ?,"
                            + "`identificacion` = ?,"
                            + "`nombres` = ?,"
                            + "`apellidos` = ?,"
                            + "`Id_EmpresaTrabaja` = ?,"
                            + "`centroCostoId` = ?,"
                            + "`observaciones` = ?,"
                            + "`consumocasino` = ?,"
                            + "`grupoConsumo` = ?"
                            + " WHERE `Id` = ?;");
                    SQL.setString(1, modelo.getTipoIdentificacion());
                    SQL.setString(2, modelo.getIdentificacion());
                    SQL.setString(3, modelo.getNombres());
                    SQL.setString(4, modelo.getApellidos());
                    SQL.setInt(5, modelo.getModeloEmpresa().getId());
                    SQL.setInt(6, modelo.getModeloCentroCosto().getId());
                    SQL.setString(7, modelo.getObservaciones());
                    SQL.setString(8, modelo.getConsumocasino());
                    SQL.setInt(9, modelo.getModeloGrupoConsumo().getId());
                    SQL.setInt(10, modelo.getId());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                resultado = "-3";
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
        String cedula = request.getParameter("cedula");
        String out = null;
        try {
            LinkedList<ModeloPersonas> listaPersonas = new LinkedList<ModeloPersonas>();
            listaPersonas = Read(cedula);
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
            for (ModeloPersonas modeloPersonas : listaPersonas) {
                out += "<tr>";
                out += "<td>" + modeloPersonas.getIdentificacion() + "</td>";
                out += "<td>" + modeloPersonas.getNombres() + " " + modeloPersonas.getApellidos() + "</td>";
                out += "<td>" + modeloPersonas.getModeloEmpresa().getNombre() + "</td>";
                out += "<td>" + modeloPersonas.getModeloCentroCosto().getDescripcion() + "</td>";
                out += "<td>" + modeloPersonas.getModeloGrupoConsumo().getDescripcion() + "</td>";
                if ("1".equals(modeloPersonas.getCalienteSiNo())) {
                    out += "<td>No</td>";
                } else {
                    out += "<td>Si</td>";
                }
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\" data-toggle=\"modal\" data-target=\"#ModalFormulario\"data-whatever=\"@getbootstrap\"";
                out += "data-id=\"" + modeloPersonas.getId() + "\"";
                out += "data-tipodoc=\"" + modeloPersonas.getTipoIdentificacion() + "\"";
                out += "data-cedula=\"" + modeloPersonas.getIdentificacion() + "\"";
                out += "data-nombre=\"" + modeloPersonas.getNombres() + "\"";
                out += "data-apellido=\"" + modeloPersonas.getApellidos() + "\"";
                out += "data-empresa=\"" + modeloPersonas.getModeloEmpresa().getId() + "\"";
                out += "data-centrocosto=\"" + modeloPersonas.getModeloCentroCosto().getId() + "\"";
                out += "data-grupoconsumo=\"" + modeloPersonas.getModeloGrupoConsumo().getId() + "\"";
                out += "data-consume=\"" + modeloPersonas.getConsumocasino() + "\"";
                out += "data-observacion=\"" + modeloPersonas.getObservaciones() + "\"";
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\" data-toggle=\"modal\" data-target=\"#ModalFormulario\"data-whatever=\"@getbootstrap\"";
                out += "data-id=\"" + modeloPersonas.getId() + "\"";
                out += "data-tipodoc=\"" + modeloPersonas.getTipoIdentificacion() + "\"";
                out += "data-cedula=\"" + modeloPersonas.getIdentificacion() + "\"";
                out += "data-nombre=\"" + modeloPersonas.getNombres() + "\"";
                out += "data-apellido=\"" + modeloPersonas.getApellidos() + "\"";
                out += "data-empresa=\"" + modeloPersonas.getModeloEmpresa().getId() + "\"";
                out += "data-centrocosto=\"" + modeloPersonas.getModeloCentroCosto().getId() + "\"";
                out += "data-grupoconsumo=\"" + modeloPersonas.getModeloGrupoConsumo().getId() + "\"";
                out += "data-consume=\"" + modeloPersonas.getConsumocasino() + "\"";
                out += "data-observacion=\"" + modeloPersonas.getObservaciones() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloPersonas.getId() + "\"";
                out += "data-tipodoc=\"" + modeloPersonas.getTipoIdentificacion() + "\"";
                out += "data-cedula=\"" + modeloPersonas.getIdentificacion() + "\"";
                out += "data-nombre=\"" + modeloPersonas.getNombres() + "\"";
                out += "data-apellido=\"" + modeloPersonas.getApellidos() + "\"";
                out += "data-empresa=\"" + modeloPersonas.getModeloEmpresa().getId() + "\"";
                out += "data-centrocosto=\"" + modeloPersonas.getModeloCentroCosto().getId() + "\"";
                out += "data-grupoconsumo=\"" + modeloPersonas.getModeloGrupoConsumo().getId() + "\"";
                out += "data-consume=\"" + modeloPersonas.getConsumocasino() + "\"";
                out += "data-observacion=\"" + modeloPersonas.getObservaciones() + "\"";
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloPersonas.getId() + "\"";
                out += "data-tipodoc=\"" + modeloPersonas.getTipoIdentificacion() + "\"";
                out += "data-cedula=\"" + modeloPersonas.getIdentificacion() + "\"";
                out += "data-nombre=\"" + modeloPersonas.getNombres() + "\"";
                out += "data-apellido=\"" + modeloPersonas.getApellidos() + "\"";
                out += "data-empresa=\"" + modeloPersonas.getModeloEmpresa().getId() + "\"";
                out += "data-centrocosto=\"" + modeloPersonas.getModeloCentroCosto().getId() + "\"";
                out += "data-grupoconsumo=\"" + modeloPersonas.getModeloGrupoConsumo().getId() + "\"";
                out += "data-consume=\"" + modeloPersonas.getConsumocasino() + "\"";
                out += "data-observacion=\"" + modeloPersonas.getObservaciones() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
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
    private LinkedList<ModeloPersonas> Read(String cedula) {
        LinkedList<ModeloPersonas> listaModeloPersonas = new LinkedList<ModeloPersonas>();
        con = conexion.abrirConexion();
        try {
            if (cedula == null) {
                SQL = con.prepareStatement("SELECT "
                        + "`id`,"
                        + "`tipoIdentificacion`,"
                        + "`identificacion`,"
                        + "`nombres`,"
                        + "`apellidos`,"
                        + "`Id_EmpresaTrabaja`,"
                        + "`centroCostoId`,"
                        + "`observaciones`,"
                        + "`consumocasino`,"
                        + "`grupoConsumo`"
                        + "FROM `persona`;");
            }
            if (cedula != null) {
                SQL = con.prepareStatement("SELECT "
                        + "`id`,"
                        + "`tipoIdentificacion`,"
                        + "`identificacion`,"
                        + "`nombres`,"
                        + "`apellidos`,"
                        + "`Id_EmpresaTrabaja`,"
                        + "`centroCostoId`,"
                        + "`observaciones`,"
                        + "`consumocasino`,"
                        + "`grupoConsumo`"
                        + "FROM `persona`"
                        + "WHERE `identificacion` = ? ;");
                SQL.setString(1, cedula);
            }

            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloPersonas modelo = new ModeloPersonas();
                modelo.setId(res.getInt("id"));
                modelo.setTipoIdentificacion(res.getString("tipoIdentificacion"));
                modelo.setIdentificacion(res.getString("identificacion"));
                modelo.setNombres(res.getString("nombres"));
                modelo.setApellidos(res.getString("apellidos"));
                modelo.setModeloEmpresa(controladorEmpresas.getModelo(Integer.parseInt(res.getString("Id_EmpresaTrabaja"))));
                modelo.setModeloCentroCosto(controladorCentroCosto.getModelo(Integer.parseInt(res.getString("centroCostoId"))));
                modelo.setObservaciones(res.getString("observaciones"));
                modelo.setConsumocasino(res.getString("consumocasino"));
                modelo.setModeloGrupoConsumo(controladorGrupoConsumo.getModelo(Integer.parseInt(res.getString("grupoConsumo"))));
                //modelo.setListModeloCargoses (controladorCargos.getListModelo (modelo.getId ()));

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
        String Modulo = request.getParameter("modulo");
        switch (Modulo) {
            case "Casino":
                resultado = SearchCasino(request);
                break;
        }
        return resultado;
    }

    public String SearchCasino(HttpServletRequest request) {
        String cedula = request.getParameter("cedula");
        ModeloPersonas modelo = new ModeloPersonas();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`tipoIdentificacion`,"
                    + "`identificacion`,"
                    + "`nombres`,"
                    + "`apellidos`,"
                    + "`Id_EmpresaTrabaja`,"
                    + "`centroCostoId`,"
                    + "`observaciones`,"
                    + "`consumocasino`,"
                    + "`grupoConsumo`"
                    + "FROM `persona`"
                    + "WHERE `identificacion` = ? ;");
            SQL.setString(1, cedula);

            ResultSet res = SQL.executeQuery();
            while (res.next()) {

                modelo.setId(res.getInt("id"));
                modelo.setTipoIdentificacion(res.getString("tipoIdentificacion"));
                modelo.setIdentificacion(res.getString("identificacion"));
                modelo.setNombres(res.getString("nombres"));
                modelo.setApellidos(res.getString("apellidos"));
                modelo.setModeloEmpresa(controladorEmpresas.getModelo(Integer.parseInt(res.getString("Id_EmpresaTrabaja"))));
                modelo.setModeloCentroCosto(controladorCentroCosto.getModelo(Integer.parseInt(res.getString("centroCostoId"))));
                modelo.setObservaciones(res.getString("observaciones"));
                modelo.setConsumocasino(res.getString("consumocasino"));
                modelo.setModeloGrupoConsumo(controladorGrupoConsumo.getModelo(Integer.parseInt(res.getString("grupoConsumo"))));
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
}
