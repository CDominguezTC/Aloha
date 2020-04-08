/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloCargos;
import Modelo.ModeloConsumo;
import Tools.GenerarExcel;
import Tools.Tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorCargos
{

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql ();

//    public String IniServicio (HttpServletRequest request)
//    {
//        LinkedList<ModeloCargos> modeloCargoses = new LinkedList<ModeloCargos> ();
//        ControladorPersonas controladorPersonas = new ControladorPersonas ();
//        Tools tools = new Tools ();
//        if ("".equals (request.getParameter ("idhoteleria")))
//        {
//
//            ModeloCargos modelo = new ModeloCargos ();
//            modelo.setId (0);
//            modelo.setModeloPersonas (controladorPersonas.getModelo (Integer.parseInt (request.getParameter ("id"))));
//            modelo.setTipoCargo (request.getParameter ("consumohoteleria"));
//            modelo.setValorCargo (Integer.parseInt (request.getParameter ("valorhoteleria")));
//            modelo.setFechaInicioCargo (tools.getDate ());
////            if ("1HN".equals (modelo.getTipoCargo ()))
////            {
////                modelo.setEstadoCargo ("I");
////            }
////            else
////            {
////                modelo.setEstadoCargo ("A");
////            }
//            modelo.setEstadoCargo ("A");
//            modeloCargoses.add (modelo);
//
//        }
//        else
//        {
//            ModeloCargos modelo = new ModeloCargos ();
//            modelo.setId (Integer.parseInt (request.getParameter ("idhoteleria")));
//            modelo.setModeloPersonas (controladorPersonas.getModelo (Integer.parseInt (request.getParameter ("id"))));
//            modelo.setTipoCargo (request.getParameter ("consumohoteleria"));
//            modelo.setValorCargo (Integer.parseInt (request.getParameter ("valorhoteleria")));
//            modelo.setFechaInicioCargo (tools.getDate ());
////            if ("1HN".equals (modelo.getTipoCargo ()))
////            {
////                modelo.setEstadoCargo ("I");
////            }
////            else
////            {
////                modelo.setEstadoCargo ("A");
////            }
//            modelo.setEstadoCargo ("A");
//            modeloCargoses.add (modelo);
//        }
//        if ("".equals (request.getParameter ("idadicional")))
//        {
//
//            ModeloCargos modelo = new ModeloCargos ();
//            modelo.setId (0);
//            modelo.setModeloPersonas (controladorPersonas.getModelo (Integer.parseInt (request.getParameter ("id"))));
//            modelo.setTipoCargo (request.getParameter ("consumoadicional"));
//            modelo.setValorCargo (Integer.parseInt (request.getParameter ("valoradicional")));
//            modelo.setFechaInicioCargo (tools.getDate ());
////            if ("1AN".equals (modelo.getTipoCargo ()))
////            {
////                modelo.setEstadoCargo ("I");
////            }
////            else
////            {
////                modelo.setEstadoCargo ("A");
////            }
//            modelo.setEstadoCargo ("A");
//            modeloCargoses.add (modelo);
//
//        }
//        else
//        {
//            ModeloCargos modelo = new ModeloCargos ();
//            modelo.setId (Integer.parseInt (request.getParameter ("idadicional")));
//            modelo.setModeloPersonas (controladorPersonas.getModelo (Integer.parseInt (request.getParameter ("id"))));
//            modelo.setTipoCargo (request.getParameter ("consumoadicional"));
//            modelo.setValorCargo (Integer.parseInt (request.getParameter ("valoradicional")));
//            modelo.setFechaInicioCargo (tools.getDate ());
////            if ("1AN".equals (modelo.getTipoCargo ()))
////            {
////                modelo.setEstadoCargo ("I");
////            }
////            else
////            {
////                modelo.setEstadoCargo ("A");
////            }
//            modelo.setEstadoCargo ("A");
//            modeloCargoses.add (modelo);
//        }
//
//        try
//        {
//            for (ModeloCargos modelo : modeloCargoses)
//            {
//                con = conexion.abrirConexion ();
//                if (modelo.getId () == 0)
//                {
//                    try
//                    {
//                        SQL = con.prepareStatement ("INSERT INTO `cargoshoteleria`("
//                                + "`IdPersona`,"
//                                + "`TipoCargo`,"
//                                + "`ValorCargo`,"
//                                + "`FechaInicioCargo`,"
//                                + "`EstadoCargo`) "
//                                + "VALUE (?,?,?,?,?);");
//                        SQL.setInt (1, modelo.getModeloPersonas ().getId ());
//                        SQL.setString (2, modelo.getTipoCargo ());
//                        SQL.setInt (3, modelo.getValorCargo ());
//                        SQL.setString (4, modelo.getFechaInicioCargo ());
//                        SQL.setString (5, modelo.getEstadoCargo ());
//                        if (SQL.executeUpdate () > 0)
//                        {
//                            resultado = "1";
//
//                        }
//                    } catch (SQLException e)
//                    {
//                        System.out.println (e);
//                        SQL.close ();
//                        con.close ();
//                    }
//                }
//                else
//                {
//                    try
//                    {
//                        SQL = con.prepareStatement ("UPDATE `cargoshoteleria` SET "
//                                + "`IdPersona` = ?, "
//                                + "`TipoCargo` = ?, "
//                                + "`ValorCargo` = ?, "
//                                + "`EstadoCargo` = ? "
//                                + "WHERE `Id` = ?;");
//                        SQL.setInt (1, modelo.getModeloPersonas ().getId ());
//                        SQL.setString (2, modelo.getTipoCargo ());
//                        SQL.setInt (3, modelo.getValorCargo ());
//                        SQL.setString (4, modelo.getEstadoCargo ());
//                        SQL.setInt (5, modelo.getId ());
//                        if (SQL.executeUpdate () > 0)
//                        {
//                            resultado = "1";
//                        }
//                    } catch (SQLException e)
//                    {
//                        System.out.println (e);
//                    }
//                }
//
//                SQL.close ();
//                con.close ();
//            }
//        } catch (SQLException e)
//        {
//            try
//            {
//                System.out.println (e);
//                SQL.close ();
//                con.close ();
//            } catch (SQLException ex)
//            {
//                Logger.getLogger (ControladorCargos.class.getName ()).log (Level.SEVERE, null, ex);
//            }
//        }
//        return resultado;
//    }
//    LinkedList<ModeloCargos> getListModelo (int id)
//    {
//        LinkedList<ModeloCargos> listModeloCargoses = new LinkedList<> ();
//        ControladorPersonas controladorPersonas = new ControladorPersonas ();
//        try
//        {
//            con = conexion.abrirConexion ();
//            SQL = con.prepareStatement ("SELECT "
//                    + "`Id`,"
//                    + "`IdPersona`,"
//                    + "`TipoCargo`,"
//                    + "`ValorCargo`,"
//                    + "`FechaInicioCargo`,"
//                    + "`FechaFinCargo`,"
//                    + "`EstadoCargo` "
//                    + "FROM `cargoshoteleria` "
//                    + "WHERE IdPersona = ? AND EstadoCargo = ?;");
//            SQL.setInt (1, id);
//            SQL.setString (2, "A");
//            ResultSet res = SQL.executeQuery ();
//            while (res.next ())
//            {
//                ModeloCargos modeloCargos = new ModeloCargos ();
//                modeloCargos.setId (res.getInt ("Id"));
//                modeloCargos.setModeloPersonas (controladorPersonas.getModelo (res.getInt ("IdPersona")));
//                modeloCargos.setTipoCargo (res.getString ("TipoCargo"));
//                modeloCargos.setValorCargo (res.getInt ("ValorCargo"));
//                modeloCargos.setFechaInicioCargo (res.getString ("FechaInicioCargo"));
//                modeloCargos.setFechaFinCargo (res.getString ("FechaFinCargo"));
//                modeloCargos.setEstadoCargo (res.getString ("EstadoCargo"));
//                listModeloCargoses.add (modeloCargos);
//            }
//            res.close ();
//            SQL.close ();
//            con.close ();
//        } catch (SQLException e)
//        {
//            System.out.println ("Error en la seleccion del cargo " + e);
//        }
//        return listModeloCargoses;
//    }
//
//    public String FinServicio (HttpServletRequest request)
//    {
//        LinkedList<ModeloCargos> modeloCargoses = new LinkedList<ModeloCargos> ();
//        ControladorPersonas controladorPersonas = new ControladorPersonas ();
//        Tools tools = new Tools ();
//        if (!"".equals (request.getParameter ("idhoteleria")))
//        {
//            ModeloCargos modelo = new ModeloCargos ();
//            modelo.setId (Integer.parseInt (request.getParameter ("idhoteleria")));
//            modelo.setModeloPersonas (controladorPersonas.getModelo (Integer.parseInt (request.getParameter ("id"))));
//            modelo.setFechaFinCargo (tools.getDate ());
//            modelo.setEstadoCargo ("I");
//            modeloCargoses.add (modelo);
//        }
//        if (!"".equals (request.getParameter ("idadicional")))
//        {
//
//            ModeloCargos modelo = new ModeloCargos ();
//            modelo.setId (Integer.parseInt (request.getParameter ("idadicional")));
//            modelo.setModeloPersonas (controladorPersonas.getModelo (Integer.parseInt (request.getParameter ("id"))));
//            modelo.setFechaFinCargo (tools.getDate ());
//            modelo.setEstadoCargo ("I");
//            modeloCargoses.add (modelo);
//        }
//
//        try
//        {
//            for (ModeloCargos modelo : modeloCargoses)
//            {
//                con = conexion.abrirConexion ();
//
//                try
//                {
//                    SQL = con.prepareStatement ("UPDATE `cargoshoteleria`  SET "
//                            + "`FechaFinCargo` = ?, "
//                            + "`EstadoCargo` = ? "
//                            + "WHERE `Id` = ?;");
//                    SQL.setString (1, modelo.getFechaFinCargo ());
//                    SQL.setString (2, modelo.getEstadoCargo ());
//                    SQL.setInt (3, modelo.getId ());
//                    if (SQL.executeUpdate () > 0)
//                    {
//                        resultado = "1";
//                    }
//                } catch (SQLException e)
//                {
//                    System.out.println (e);
//                }
//                SQL.close ();
//                con.close ();
//            }
//        } catch (SQLException e)
//        {
//            Logger.getLogger (ControladorCargos.class.getName ()).log (Level.SEVERE, null, e);
//        }
//        return resultado;
//    }
    public void Select (String generarLiquidacionHoteleria, HttpServletRequest request, HttpServletResponse response)
    {
        switch (generarLiquidacionHoteleria)
        {
            case "GenerarLiquidacionHoteleria":
                String SQLReporte = "SELECT "
                        + "`personaCedula` AS \"IDENTIFICACION\","
                        + "`personaNombre` AS \"PERSONA\","                        
                        + "`personaCargo` AS \"CARGO\","
                        + "`centrodecostoNombre` AS \"CENTRO_COSTO_NOMBRE\","                        
                        + "`grupoconsumoNombre` AS \"GRUPO_CONSUMO_NOMBRE\","                        
                        + "`cargoshoteleriaNombre` AS \"CARGO_HOTELERIA\","
                        + "`cargoshoteleriaValor` AS \"VALOR\","
                        + "`Fechaconsumo` AS \" FECHA_CONSUMO\","
                        + "`diaconsumo` AS \"DIA_COSNUMO\""
                        + "FROM `consumohoteleria`"
                        + "WHERE Fechaconsumo >= '" + request.getParameter ("FechaIni") + "' AND Fechaconsumo <= '" + request.getParameter ("FechaFin") + " 23:59:59' order by Fechaconsumo";
                try
                {
                    String UrlArchivo = "C:\\Zred\\AlohaFiles\\LIQUIDACION_HOTELERIA.xls";//request.getParameter("PlantillaUrl");                
                    String newQuery = SQLReporte;
                    //ControladorExcel controladorExcel = new ControladorExcel();
                    GenerarExcel generarExcel = new GenerarExcel ();
                    String archivo = generarExcel.GenerarExcel (UrlArchivo, newQuery);
                    downloadFile (response, archivo);

                } //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
                catch (Exception ex)
                {
                    //Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

        }

    }
    
      protected void downloadFile (HttpServletResponse response, String filePath)
            throws ServletException, IOException
    {

        File fileToDownload = new File (filePath);
        FileInputStream fileInputStream = new FileInputStream (fileToDownload);

        ServletOutputStream out = response.getOutputStream ();
        String mimeType = new MimetypesFileTypeMap ().getContentType (filePath);

        response.setContentType (mimeType);
        response.setContentLength (fileInputStream.available ());
        response.setHeader ("Content-Disposition", "attachment; filename=\""
                + fileToDownload.getName () + "\"");

        int c;
        while ((c = fileInputStream.read ()) != -1)
        {
            out.write (c);
        }
        out.flush ();
        out.close ();
        fileInputStream.close ();
    }

    private LinkedList<ModeloCargos> getItems (HttpServletRequest request)
    {
        Tools tools = new Tools ();
        LinkedList<ModeloCargos> listModeloCargoses = new LinkedList<> ();
        ControladorPersonas controladorPersonas = new ControladorPersonas ();
        try
        {
            con = conexion.abrirConexion ();
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`IdPersona`,"
                    + "`TipoCargo`,"
                    + "`ValorCargo`,"
                    + "`FechaInicioCargo`,"
                    + "`FechaFinCargo`,"
                    + "`EstadoCargo` "
                    + "FROM `cargoshoteleria` "
                    + "WHERE `FechaFinCargo` > ? OR `FechaFinCargo` IS null;");
            SQL.setString (1, request.getParameter ("FechaFin"));
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloCargos modeloCargos = new ModeloCargos ();
                modeloCargos.setId (res.getInt ("Id"));
                modeloCargos.setModeloPersonas (controladorPersonas.getModelo (res.getInt ("IdPersona")));
                modeloCargos.setTipoCargo (res.getString ("TipoCargo"));
                modeloCargos.setValorCargo (res.getInt ("ValorCargo"));
                modeloCargos.setFechaInicioCargo (res.getString ("FechaInicioCargo"));
                if (res.getString ("FechaFinCargo") == null)
                {
                    modeloCargos.setFechaFinCargo (tools.getDate ());
                }
                else
                {
                    modeloCargos.setFechaFinCargo (res.getString ("FechaFinCargo"));
                }

                modeloCargos.setEstadoCargo (res.getString ("EstadoCargo"));
                listModeloCargoses.add (modeloCargos);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en la seleccion del cargo " + e);
        }
        return listModeloCargoses;
    }

    public LinkedList<ModeloCargos> getModelo ()
    {
        Tools tools = new Tools ();
        LinkedList<ModeloCargos> listModeloCargoses = new LinkedList<> ();
        ControladorPersonas controladorPersonas = new ControladorPersonas ();
        try
        {
            con = conexion.abrirConexion ();
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`TipoCargo`,"
                    + "`ValorCargo`"
                    + "FROM `cargoshoteleria`; ");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloCargos modeloCargos = new ModeloCargos ();
                modeloCargos.setId (res.getInt ("Id"));
                modeloCargos.setTipoCargo (res.getString ("TipoCargo"));
                modeloCargos.setValorCargo (res.getInt ("ValorCargo"));
                listModeloCargoses.add (modeloCargos);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en la seleccion del cargo " + e);
        }
        return listModeloCargoses;
    }

    private void LiquidaCargos (LinkedList<ModeloCargos> listmodeloCargos)
    {
        Calendar calendar = Calendar.getInstance ();
        Tools tools = new Tools ();
        Date FechaInicio = null;
        Date FechaFin = null;
        int DiasEStancia;
        for (ModeloCargos modeloCargo : listmodeloCargos)
        {
            FechaInicio = tools.ParseFecha (modeloCargo.getFechaInicioCargo ());
            FechaFin = tools.ParseFecha (modeloCargo.getFechaFinCargo ());
            long fechainicio = FechaInicio.getTime ();
            long fechafin = FechaFin.getTime ();
            long diferencia = fechafin - fechainicio;
            DiasEStancia = (int) TimeUnit.DAYS.convert (diferencia, TimeUnit.MILLISECONDS);
            System.out.println (DiasEStancia);
            for (int i = 0; i <= DiasEStancia; i++)
            {
                calendar.setTime (FechaInicio);
                calendar.add (Calendar.DAY_OF_YEAR, i);
                System.out.println (calendar.getTime ());
            }

        }
    }

    public String Insert (HttpServletRequest request)
    {
        String Id = request.getParameter ("Id");
        String[] Consume = request.getParameterValues ("Consume");
        System.out.println (Id);
        System.out.println (Consume.length);
        if (Consume != null)
        {
            Tools tools = new Tools ();
            ControladorPersonas controladorPersonas = new ControladorPersonas ();
            ControladorCargos controladorCargos = new ControladorCargos ();
            LinkedList<ModeloConsumo> modeloConsumos = new LinkedList<> ();
            for (String Consume1 : Consume)
            {
                if (!"".equals (Consume1))
                {
                    ModeloConsumo modeloConsumo = new ModeloConsumo ();
                    modeloConsumo.setModeloPersonas (controladorPersonas.getModelo (Integer.parseInt (Id)));
                    modeloConsumo.setModeloCargos (controladorCargos.getModelo (Integer.parseInt (Consume1)));
                    modeloConsumo.setFechaconsumo (tools.getDate ());
                    modeloConsumo.setDiaconsumo (tools.getdiaMarcacion (tools.ParseFecha (modeloConsumo.getFechaconsumo ())));
                    modeloConsumos.add (modeloConsumo);
                }
            }
            if (modeloConsumos.size () > 0)
            {
                try
                {
                    con = conexion.abrirConexion ();
                    for (ModeloConsumo modeloConsumo : modeloConsumos)
                    {
                        try
                        {
                            SQL = con.prepareStatement ("INSERT INTO `consumohoteleria`("
                                    + "`personaId`,"
                                    + "`personaNombre`,"
                                    + "`personaCedula`,"
                                    + "`personaCargo`,"
                                    + "`centrodecostoId`,"
                                    + "`centrodecostoNombre`,"
                                    + "`grupoconsumoId`,"
                                    + "`grupoconsumoNombre`,"
                                    //+ "`horarioconsumoId`,"
                                    //+ "`horarioconsumoNombre`,"
                                    + "`cargoshoteleriaId`,"
                                    + "`cargoshoteleriaNombre`,"
                                    + "`cargoshoteleriaValor`,"
                                    + "`Fechaconsumo`,"
                                    + "`diaconsumo`) "
                                    + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?);");
                            SQL.setInt (1, modeloConsumo.getModeloPersonas ().getId ());
                            SQL.setString (2, modeloConsumo.getModeloPersonas ().getNombres () + " " + modeloConsumo.getModeloPersonas ().getApellidos ());
                            SQL.setString (3, modeloConsumo.getModeloPersonas ().getIdentificacion ());
                            SQL.setString (4, modeloConsumo.getModeloPersonas ().getObservaciones ());
                            SQL.setInt (5, modeloConsumo.getModeloPersonas ().getModeloCentroCosto ().getId ());
                            SQL.setString (6, modeloConsumo.getModeloPersonas ().getModeloCentroCosto ().getDescripcion ());
                            SQL.setInt (7, modeloConsumo.getModeloPersonas ().getModeloGrupoConsumo ().getId ());
                            SQL.setString (8, modeloConsumo.getModeloPersonas ().getModeloGrupoConsumo ().getDescripcion ());
                            SQL.setInt (9, modeloConsumo.getModeloCargos ().getId ());
                            SQL.setString (10, modeloConsumo.getModeloCargos ().getTipoCargo ());
                            SQL.setInt (11, modeloConsumo.getModeloCargos ().getValorCargo ());
                            SQL.setString (12, modeloConsumo.getFechaconsumo ());
                            SQL.setString (13, modeloConsumo.getDiaconsumo ());
                            if (SQL.executeUpdate () > 0)
                            {
                                resultado = "1";
                            }

                        } catch (SQLException e)
                        {
                            resultado = "-1";
                            System.out.println ("Controladores.ControladorCargos.Insert()" + e);
                        }
                    }
                    SQL.close ();
                    con.close ();
                } catch (SQLException ex)
                {
                    System.out.println ("Controladores.ControladorCargos.Insert()" + ex);
                    resultado = "-1";
                }
            }
        }
        return resultado;
    }

    private ModeloCargos getModelo (int Id)
    {
        Tools tools = new Tools ();
        ModeloCargos modeloCargos = new ModeloCargos ();
        ControladorPersonas controladorPersonas = new ControladorPersonas ();
        try
        {
            con = conexion.abrirConexion ();
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`TipoCargo`,"
                    + "`ValorCargo`"
                    + "FROM `cargoshoteleria`"
                    + "WHERE Id = ?; ");
            SQL.setInt (1, Id);
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                modeloCargos.setId (res.getInt ("Id"));
                modeloCargos.setTipoCargo (res.getString ("TipoCargo"));
                modeloCargos.setValorCargo (res.getInt ("ValorCargo"));
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en la seleccion del cargo " + e);
        }
        return modeloCargos;
    }

    public String Update (HttpServletRequest request)
    {
        if ("".equals (request.getParameter ("id")))
        {
            ModeloCargos modelo = new ModeloCargos ();
            modelo.setTipoCargo (request.getParameter ("nombre"));
            modelo.setValorCargo (Integer.parseInt (request.getParameter ("cantidad")));
            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("INSERT INTO `cargoshoteleria`("
                            + "`TipoCargo`,"
                            + "`ValorCargo`) "
                            + "VALUE (?,?);");
                    SQL.setString (1, modelo.getTipoCargo ());
                    SQL.setInt (2, modelo.getValorCargo ());
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
            ModeloCargos modelo = new ModeloCargos ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));
            modelo.setTipoCargo (request.getParameter ("nombre"));
            modelo.setValorCargo (Integer.parseInt (request.getParameter ("cantidad")));
            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("UPDATE `cargoshoteleria`  SET "
                            + "`TipoCargo` = ?,"
                            + "`ValorCargo` = ? "
                            + "WHERE `Id` = ?;");
                    SQL.setString (1, modelo.getTipoCargo ());
                    SQL.setInt (2, modelo.getValorCargo ());
                    SQL.setInt (3, modelo.getId ());
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
            ModeloCargos modelo = new ModeloCargos ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));

            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("DELETE FROM `cargoshoteleria` "
                            + "WHERE `Id` = ?;");
                    SQL.setInt (1, modelo.getId ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "2";
                    }
                } catch (SQLException e)
                {
                    resultado = "-4";
                }
                SQL.close ();
                con.close ();
            } catch (SQLException e)
            {
                resultado = "-3";
            }
        }
        return resultado;
    }

    public String Read (HttpServletRequest request, HttpServletResponse response)
    {
        String resultado = null;
        LinkedList<ModeloCargos> listModeloCargoses = new LinkedList<ModeloCargos> ();
        con = conexion.abrirConexion ();
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`TipoCargo`,"
                    + "`ValorCargo`"
                    + "FROM `cargoshoteleria`;");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloCargos modelo = new ModeloCargos ();
                modelo.setId (res.getInt ("Id"));
                modelo.setTipoCargo (res.getString ("TipoCargo"));
                modelo.setValorCargo (res.getInt ("ValorCargo"));
                //modelo.setListModeloCargoses (controladorCargos.getListModelo (modelo.getId ()));

                listModeloCargoses.add (modelo);
            }
            res.close ();
            SQL.close ();
            con.close ();
            resultado = GetTabla (listModeloCargoses);
        } catch (SQLException e)
        {
            System.out.println (e);
        }
        return resultado;
    }

    private String GetTabla (LinkedList<ModeloCargos> modelo)
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
            out += "<th>Nombre</th>";
            out += "<th>Valor</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloCargos modeloCargos : modelo)
            {
                out += "<tr>";
                out += "<td>" + modeloCargos.getTipoCargo () + "</td>";
                out += "<td>" + modeloCargos.getValorCargo () + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\" data-toggle=\"modal\" data-target=\"#ModalFormulario\"data-whatever=\"@getbootstrap\"";
                out += "data-id=\"" + modeloCargos.getId () + "\"";
                out += "data-tipocargo=\"" + modeloCargos.getTipoCargo () + "\"";
                out += "data-valor=\"" + modeloCargos.getValorCargo () + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloCargos.getId () + "\"";
                out += "data-tipocargo=\"" + modeloCargos.getTipoCargo () + "\"";
                out += "data-valor=\"" + modeloCargos.getValorCargo () + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
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
}
