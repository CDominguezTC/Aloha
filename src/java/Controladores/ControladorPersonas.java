/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloCargos;
import Modelo.ModeloPersonas;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorPersonas
{

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql ();
    ControladorEmpresas controladorEmpresas = new ControladorEmpresas ();
    ControladorCentroCosto controladorCentroCosto = new ControladorCentroCosto ();
    ControladorGrupoConsumo controladorGrupoConsumo = new ControladorGrupoConsumo ();
    ControladorCargos controladorCargos = new ControladorCargos ();

    public String Insert (HttpServletRequest request)
    {
        if ("".equals (request.getParameter ("id")))
        {
            ModeloPersonas modelo = new ModeloPersonas ();
            modelo.setId (0);
            modelo.setTipoIdentificacion (request.getParameter ("tipodoc"));
            modelo.setIdentificacion (request.getParameter ("cedula"));
            modelo.setNombres (request.getParameter ("nombre"));
            modelo.setApellidos (request.getParameter ("apellido"));
            modelo.setModeloEmpresa (controladorEmpresas.getModelo (Integer.parseInt (request.getParameter ("empresa"))));
            modelo.setModeloCentroCosto (controladorCentroCosto.getModelo (Integer.parseInt (request.getParameter ("centrocosto"))));
            modelo.setConsumocasino (request.getParameter ("consumo"));
            modelo.setModeloGrupoConsumo (controladorGrupoConsumo.getModelo (Integer.parseInt (request.getParameter ("grupoconsumo"))));
            modelo.setObservaciones (request.getParameter ("observacion"));
            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("INSERT INTO `persona`("
                            + "`tipoIdentificacion`,"
                            + "`identificacion`,"
                            + "`nombres`,"
                            + "`apellidos`,"
                            + "`Id_EmpresaTrabaja`,"
                            + "`centroCostoId`,"
                            + "`observaciones`,"
                            + "`consumocasino`,"
                            + "`grupoConsumo`)"
                            + " VALUE (?,?,?,?,?,?,?,?,?);");
                    SQL.setString (1, modelo.getTipoIdentificacion ());
                    SQL.setString (2, modelo.getIdentificacion ());
                    SQL.setString (3, modelo.getNombres ());
                    SQL.setString (4, modelo.getApellidos ());
                    SQL.setInt (5, modelo.getModeloEmpresa ().getId ());
                    SQL.setInt (6, modelo.getModeloCentroCosto ().getId ());
                    SQL.setString (7, modelo.getObservaciones ());
                    SQL.setString (8, modelo.getConsumocasino ());
                    SQL.setInt (9, modelo.getModeloGrupoConsumo ().getId ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "1";
                        SQL.close ();
                        con.close ();
                    }
                } catch (SQLException e)
                {
                    System.out.println (e);
                    resultado = "-2";
                    SQL.close ();
                    con.close ();
                }
            } catch (SQLException e)
            {
                System.out.println (e);
                resultado = "-3";
            }
        }
        else
        {
            ModeloPersonas modelo = new ModeloPersonas ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));
            modelo.setTipoIdentificacion (request.getParameter ("tipodoc"));
            modelo.setIdentificacion (request.getParameter ("cedula"));
            modelo.setNombres (request.getParameter ("nombre"));
            modelo.setApellidos (request.getParameter ("apellido"));
            modelo.setModeloEmpresa (controladorEmpresas.getModelo (Integer.parseInt (request.getParameter ("empresa"))));
            modelo.setModeloCentroCosto (controladorCentroCosto.getModelo (Integer.parseInt (request.getParameter ("centrocosto"))));
            modelo.setConsumocasino (request.getParameter ("consumo"));
            modelo.setModeloGrupoConsumo (controladorGrupoConsumo.getModelo (Integer.parseInt (request.getParameter ("grupoconsumo"))));
            modelo.setObservaciones (request.getParameter ("observacion"));

            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("UPDATE `persona`  SET "
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
                    SQL.setString (1, modelo.getTipoIdentificacion ());
                    SQL.setString (2, modelo.getIdentificacion ());
                    SQL.setString (3, modelo.getNombres ());
                    SQL.setString (4, modelo.getApellidos ());
                    SQL.setInt (5, modelo.getModeloEmpresa ().getId ());
                    SQL.setInt (6, modelo.getModeloCentroCosto ().getId ());
                    SQL.setString (7, modelo.getObservaciones ());
                    SQL.setString (8, modelo.getConsumocasino ());
                    SQL.setInt (9, modelo.getModeloGrupoConsumo ().getId ());
                    SQL.setInt (10, modelo.getId ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "1";
                        SQL.close ();
                        con.close ();
                    }
                } catch (SQLException e)
                {
                    System.out.println (e);
                    resultado = "-2";
                    SQL.close ();
                    con.close ();
                }
            } catch (SQLException e)
            {
                System.out.println (e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    public String Delete (HttpServletRequest request)
    {
        if (!"".equals (request.getParameter ("id")))
        {
            String idtmp = request.getParameter ("id");
            ModeloPersonas modelo = new ModeloPersonas ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));

            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("DELETE FROM `persona` "
                            + "WHERE `Id` = ?;");
                    SQL.setInt (1, modelo.getId ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "2";
                    }
                } catch (SQLException e)
                {
                    System.out.println (e);
                    resultado = "-4";
                }
                SQL.close ();
                con.close ();
            } catch (SQLException e)
            {
                System.out.println (e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    public String Read (HttpServletRequest request, HttpServletResponse response)
    {
        String resultado = null;
        LinkedList<ModeloPersonas> listModeloPersonases = new LinkedList<ModeloPersonas> ();
        con = conexion.abrirConexion ();
        try
        {
            SQL = con.prepareStatement ("SELECT "
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
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloPersonas modelo = new ModeloPersonas ();
                modelo.setId (res.getInt ("id"));
                modelo.setTipoIdentificacion (res.getString ("tipoIdentificacion"));
                modelo.setIdentificacion (res.getString ("identificacion"));
                modelo.setNombres (res.getString ("nombres"));
                modelo.setApellidos (res.getString ("apellidos"));
                modelo.setModeloEmpresa (controladorEmpresas.getModelo (Integer.parseInt (res.getString ("Id_EmpresaTrabaja"))));
                modelo.setModeloCentroCosto (controladorCentroCosto.getModelo (Integer.parseInt (res.getString ("centroCostoId"))));
                modelo.setObservaciones (res.getString ("observaciones"));
                modelo.setConsumocasino (res.getString ("consumocasino"));
                modelo.setModeloGrupoConsumo (controladorGrupoConsumo.getModelo (Integer.parseInt (res.getString ("grupoConsumo"))));
                //modelo.setListModeloCargoses (controladorCargos.getListModelo (modelo.getId ()));

                listModeloPersonases.add (modelo);
            }
            res.close ();
            SQL.close ();
            con.close ();
            resultado = GetTabla (listModeloPersonases);
        } catch (SQLException e)
        {
            System.out.println (e);
        }
        return resultado;
    }

    public String Get (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String resulatado = null;
        LinkedList<ModeloPersonas> listModelos = new LinkedList<ModeloPersonas> ();
        con = conexion.abrirConexion ();
        try
        {
            SQL = con.prepareStatement ("SELECT "
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
            SQL.setString (1, request.getParameter ("cedula"));
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloPersonas modelo = new ModeloPersonas ();
                modelo.setId (res.getInt ("id"));
                modelo.setTipoIdentificacion (res.getString ("tipoIdentificacion"));
                modelo.setIdentificacion (res.getString ("identificacion"));
                modelo.setNombres (res.getString ("nombres"));
                modelo.setApellidos (res.getString ("apellidos"));
                modelo.setModeloEmpresa (controladorEmpresas.getModelo (Integer.parseInt (res.getString ("Id_EmpresaTrabaja"))));
                modelo.setModeloCentroCosto (controladorCentroCosto.getModelo (Integer.parseInt (res.getString ("centroCostoId"))));
                modelo.setObservaciones (res.getString ("observaciones"));
                modelo.setConsumocasino (res.getString ("consumocasino"));
                modelo.setModeloGrupoConsumo (controladorGrupoConsumo.getModelo (Integer.parseInt (res.getString ("grupoConsumo"))));
//                modelo.setListModeloCargoses (controladorCargos.getListModelo (modelo.getId ()));
                listModelos.add (modelo);
            }
            res.close ();
            SQL.close ();
            con.close ();
            if (listModelos.isEmpty ())
            {
                resulatado = "99";
            }
            else
            {
                resulatado = GetTabla (listModelos);
            }

        } catch (SQLException e)
        {
            System.out.println (e);
        }
        return resulatado;
    }

    private String GetTabla (LinkedList<ModeloPersonas> modelo)
    {
        String out = null;
        try
        {
            //LinkedList<ModeloPersonas> listmoPersonas;
            //listmoPersonas = Read ();
            //response.setContentType ("text/html;charset=UTF-8");

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
            for (ModeloPersonas modeloPersonas : modelo)
            {
                out += "<tr>";
                out += "<td>" + modeloPersonas.getIdentificacion () + "</td>";
                out += "<td>" + modeloPersonas.getNombres () + " " + modeloPersonas.getApellidos () + "</td>";
                out += "<td>" + modeloPersonas.getModeloEmpresa ().getNombre () + "</td>";
                out += "<td>" + modeloPersonas.getModeloCentroCosto ().getDescripcion () + "</td>";
                out += "<td>" + modeloPersonas.getModeloGrupoConsumo ().getDescripcion () + "</td>";
                if ("1".equals (modeloPersonas.getCalienteSiNo ()))
                {
                    out += "<td>No</td>";
                }
                else
                {
                    out += "<td>Si</td>";
                }
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\" data-toggle=\"modal\" data-target=\"#ModalFormulario\"data-whatever=\"@getbootstrap\"";
                out += "data-id=\"" + modeloPersonas.getId () + "\"";
                out += "data-tipodoc=\"" + modeloPersonas.getTipoIdentificacion () + "\"";
                out += "data-cedula=\"" + modeloPersonas.getIdentificacion () + "\"";
                out += "data-nombre=\"" + modeloPersonas.getNombres () + "\"";
                out += "data-apellido=\"" + modeloPersonas.getApellidos () + "\"";
                out += "data-empresa=\"" + modeloPersonas.getModeloEmpresa ().getId () + "\"";
                out += "data-centrocosto=\"" + modeloPersonas.getModeloCentroCosto ().getId () + "\"";
                out += "data-grupoconsumo=\"" + modeloPersonas.getModeloGrupoConsumo ().getId () + "\"";
                out += "data-consume=\"" + modeloPersonas.getConsumocasino () + "\"";
                out += "data-observacion=\"" + modeloPersonas.getObservaciones () + "\"";
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\" data-toggle=\"modal\" data-target=\"#ModalFormulario\"data-whatever=\"@getbootstrap\"";
                out += "data-id=\"" + modeloPersonas.getId () + "\"";
                out += "data-tipodoc=\"" + modeloPersonas.getTipoIdentificacion () + "\"";
                out += "data-cedula=\"" + modeloPersonas.getIdentificacion () + "\"";
                out += "data-nombre=\"" + modeloPersonas.getNombres () + "\"";
                out += "data-apellido=\"" + modeloPersonas.getApellidos () + "\"";
                out += "data-empresa=\"" + modeloPersonas.getModeloEmpresa ().getId () + "\"";
                out += "data-centrocosto=\"" + modeloPersonas.getModeloCentroCosto ().getId () + "\"";
                out += "data-grupoconsumo=\"" + modeloPersonas.getModeloGrupoConsumo ().getId () + "\"";
                out += "data-consume=\"" + modeloPersonas.getConsumocasino () + "\"";
                out += "data-observacion=\"" + modeloPersonas.getObservaciones () + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloPersonas.getId () + "\"";
                out += "data-tipodoc=\"" + modeloPersonas.getTipoIdentificacion () + "\"";
                out += "data-cedula=\"" + modeloPersonas.getIdentificacion () + "\"";
                out += "data-nombre=\"" + modeloPersonas.getNombres () + "\"";
                out += "data-apellido=\"" + modeloPersonas.getApellidos () + "\"";
                out += "data-empresa=\"" + modeloPersonas.getModeloEmpresa ().getId () + "\"";
                out += "data-centrocosto=\"" + modeloPersonas.getModeloCentroCosto ().getId () + "\"";
                out += "data-grupoconsumo=\"" + modeloPersonas.getModeloGrupoConsumo ().getId () + "\"";
                out += "data-consume=\"" + modeloPersonas.getConsumocasino () + "\"";
                out += "data-observacion=\"" + modeloPersonas.getObservaciones () + "\"";
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloPersonas.getId () + "\"";
                out += "data-tipodoc=\"" + modeloPersonas.getTipoIdentificacion () + "\"";
                out += "data-cedula=\"" + modeloPersonas.getIdentificacion () + "\"";
                out += "data-nombre=\"" + modeloPersonas.getNombres () + "\"";
                out += "data-apellido=\"" + modeloPersonas.getApellidos () + "\"";
                out += "data-empresa=\"" + modeloPersonas.getModeloEmpresa ().getId () + "\"";
                out += "data-centrocosto=\"" + modeloPersonas.getModeloCentroCosto ().getId () + "\"";
                out += "data-grupoconsumo=\"" + modeloPersonas.getModeloGrupoConsumo ().getId () + "\"";
                out += "data-consume=\"" + modeloPersonas.getConsumocasino () + "\"";
                out += "data-observacion=\"" + modeloPersonas.getObservaciones () + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
//                out += "<button class=\"SetFormularioId btn btn-info btn-xs\"title=\"Opciones\"";
//                out += "data-id=\"" + modeloPersonas.getId () + "\"";
//                if (modeloPersonas.getListModeloCargoses ().isEmpty ())
//                {
//                    out += "data-idhoteleria=\"0\"";                    
//                }
//                else
//                {
//                    for (ModeloCargos modeloCargos : modeloPersonas.getListModeloCargoses ())
//                    {
//                        if ("1HN".equals (modeloCargos.getTipoCargo ()) || "2HS".equals (modeloCargos.getTipoCargo ()))
//                        {
//                            out += "data-idhoteleria=\"" + modeloCargos.getId () + "\"";
//                            out += "data-idconsumehoteleria=\"" + modeloCargos.getTipoCargo () + "\"";
//                            out += "data-idvalorhoteleria=\"" + modeloCargos.getValorCargo () + "\"";
//                            out += "data-fechainiciohoteleria=\"" + modeloCargos.getFechaInicioCargo () + "\"";
//                            out += "data-fechafinhoteleria=\"" + modeloCargos.getFechaFinCargo () + "\"";
//                            out += "data-estadohoteleria=\"" + modeloCargos.getEstadoCargo () + "\"";
//                        }
//                        if ("1AN".equals (modeloCargos.getTipoCargo ()) || "2AS".equals (modeloCargos.getTipoCargo ()))
//                        {
//                            out += "data-idadicional=\"" + modeloCargos.getId () + "\"";
//                            out += "data-idconsumeadicional=\"" + modeloCargos.getTipoCargo () + "\"";
//                            out += "data-idvaloradicional=\"" + modeloCargos.getValorCargo () + "\"";
//                            out += "data-fechainicioadicional=\"" + modeloCargos.getFechaInicioCargo () + "\"";
//                            out += "data-fechafinadicional=\"" + modeloCargos.getFechaFinCargo () + "\"";
//                            out += "data-estadoadicional=\"" + modeloCargos.getEstadoCargo () + "\"";
//                        }
//                    }
//                }
//                out += "type=\"button\"><i id=\"IdOpciones\" name=\"Opciones\" class=\"fa fa-shopping-cart\" data-toggle=\"modal\" data-target=\"#ModalGt4001\" data-whatever=\"@getbootstrap\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
//            PrintWriter pw = response.getWriter();
//            pw.write(out);
//            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
        } catch (Exception e)
        {
            System.out.println ("Error en el proceso de la tabla " + e.getMessage ());
        }
        return out;
    }

    ModeloPersonas getModelo (int id)
    {
        String resultado = null;
        ModeloPersonas modelo = new ModeloPersonas ();
        con = conexion.abrirConexion ();
        try
        {
            SQL = con.prepareStatement ("SELECT "
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
            SQL.setInt (1, id);
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                modelo.setId (res.getInt ("id"));
                modelo.setTipoIdentificacion (res.getString ("tipoIdentificacion"));
                modelo.setIdentificacion (res.getString ("identificacion"));
                modelo.setNombres (res.getString ("nombres"));
                modelo.setApellidos (res.getString ("apellidos"));
                modelo.setModeloEmpresa (controladorEmpresas.getModelo (Integer.parseInt (res.getString ("Id_EmpresaTrabaja"))));
                modelo.setModeloCentroCosto (controladorCentroCosto.getModelo (Integer.parseInt (res.getString ("centroCostoId"))));
                modelo.setObservaciones (res.getString ("observaciones"));
                modelo.setConsumocasino (res.getString ("consumocasino"));
                modelo.setModeloGrupoConsumo (controladorGrupoConsumo.getModelo (Integer.parseInt (res.getString ("grupoConsumo"))));
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println (e);
        }
        return modelo;
    }

    public ModeloPersonas GetPersonaCedula (HttpServletRequest request, HttpServletResponse response)
    {        
        String resultado = null;
        ModeloPersonas modelo = new ModeloPersonas ();
        con = conexion.abrirConexion ();
        try
        {
            SQL = con.prepareStatement ("SELECT "
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
            SQL.setString (1, request.getParameter ("cedula"));
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                modelo.setId (res.getInt ("id"));
                modelo.setTipoIdentificacion (res.getString ("tipoIdentificacion"));
                modelo.setIdentificacion (res.getString ("identificacion"));
                modelo.setNombres (res.getString ("nombres"));
                modelo.setApellidos (res.getString ("apellidos"));
                modelo.setModeloEmpresa (controladorEmpresas.getModelo (Integer.parseInt (res.getString ("Id_EmpresaTrabaja"))));
                modelo.setModeloCentroCosto (controladorCentroCosto.getModelo (Integer.parseInt (res.getString ("centroCostoId"))));
                modelo.setObservaciones (res.getString ("observaciones"));
                modelo.setConsumocasino (res.getString ("consumocasino"));
                modelo.setModeloGrupoConsumo (controladorGrupoConsumo.getModelo (Integer.parseInt (res.getString ("grupoConsumo"))));
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println (e);
        }
        return modelo;
    }
}
