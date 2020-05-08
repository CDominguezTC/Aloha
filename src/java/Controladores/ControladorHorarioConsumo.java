package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloHorarioConsumo;
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
 * Esta clase permite controlar los eventos de Horario Consumo contrine Insert -
 * Update, Delete, Read, GetModel
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorHorarioConsumo
{

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql ();
    ControladorTipoConsumo controladorTipoConsumo = new ControladorTipoConsumo ();

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd Horario
     * Consumo
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Insert (HttpServletRequest request)
    {
        if ("".equals (request.getParameter ("id")))
        {
            ModeloHorarioConsumo modelo = new ModeloHorarioConsumo ();
            modelo.setCodigo (request.getParameter ("codigo"));
            modelo.setNombre (request.getParameter ("nombre"));
            modelo.setHorainicio (request.getParameter ("horainicio"));
            modelo.setHorafin (request.getParameter ("horafin"));
            modelo.setCantidadconsumos (request.getParameter ("cantidadconsumos"));
            modelo.setLunes (request.getParameter ("lunes"));
            modelo.setMartes (request.getParameter ("martes"));
            modelo.setMiercoles (request.getParameter ("miercoles"));
            modelo.setJueves (request.getParameter ("jueves"));
            modelo.setViernes (request.getParameter ("viernes"));
            modelo.setSabado (request.getParameter ("sabado"));
            modelo.setDomingo (request.getParameter ("domingo"));
            modelo.setFestivo (request.getParameter ("festivo"));
            modelo.setModeloTipoConsumo (controladorTipoConsumo.getModelo (Integer.parseInt (request.getParameter ("tipoconsumo"))));
            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("INSERT INTO `horarioconsumo`("
                            + "`codigo`,"
                            + "`nombre`,"
                            + "`horainicio`,"
                            + "`horafin`,"
                            + "`cantidadconsumos`,"
                            + "`lunes`,"
                            + "`martes`,"
                            + "`miercoles`,"
                            + "`jueves`,"
                            + "`viernes`,"
                            + "`sabado`,"
                            + "`domingo`,"
                            + "`festivo`,"
                            + "`tipoConsumo`) "
                            + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                    SQL.setString (1, modelo.getCodigo ());
                    SQL.setString (2, modelo.getNombre ());
                    SQL.setString (3, modelo.getHorainicio ());
                    SQL.setString (4, modelo.getHorafin ());
                    SQL.setString (5, modelo.getCantidadconsumos ());
                    SQL.setString (6, modelo.getLunes ());
                    SQL.setString (7, modelo.getMartes ());
                    SQL.setString (8, modelo.getMiercoles ());
                    SQL.setString (9, modelo.getJueves ());
                    SQL.setString (10, modelo.getViernes ());
                    SQL.setString (11, modelo.getSabado ());
                    SQL.setString (12, modelo.getDomingo ());
                    SQL.setString (13, modelo.getFestivo ());
                    SQL.setInt (14, modelo.getModeloTipoConsumo ().getId ());
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
            ModeloHorarioConsumo modelo = new ModeloHorarioConsumo ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));
            modelo.setCodigo (request.getParameter ("codigo"));
            modelo.setNombre (request.getParameter ("nombre"));
            modelo.setHorainicio (request.getParameter ("horainicio"));
            modelo.setHorafin (request.getParameter ("horafin"));
            modelo.setCantidadconsumos (request.getParameter ("cantidadconsumos"));
            modelo.setLunes (request.getParameter ("lunes"));
            modelo.setMartes (request.getParameter ("martes"));
            modelo.setMiercoles (request.getParameter ("miercoles"));
            modelo.setJueves (request.getParameter ("jueves"));
            modelo.setViernes (request.getParameter ("viernes"));
            modelo.setSabado (request.getParameter ("sabado"));
            modelo.setDomingo (request.getParameter ("domingo"));
            modelo.setFestivo (request.getParameter ("festivo"));
            modelo.setModeloTipoConsumo (controladorTipoConsumo.getModelo (Integer.parseInt (request.getParameter ("tipoconsumo"))));
            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("UPDATE `horarioconsumo`  SET "
                            + "`codigo` = ?, "
                            + "`nombre` = ?, "
                            + "`horainicio` = ?, "
                            + "`horafin` = ?, "
                            + "`cantidadconsumos` = ?, "
                            + "`lunes` = ?, "
                            + "`martes` = ?, "
                            + "`miercoles` = ?, "
                            + "`jueves` = ?, "
                            + "`viernes` = ?, "
                            + "`sabado` = ?, "
                            + "`domingo` = ?, "
                            + "`festivo` = ?, "
                            + "`tipoConsumo` = ? "
                            + "WHERE `id` = ?;");
                    SQL.setString (1, modelo.getCodigo ());
                    SQL.setString (2, modelo.getNombre ());
                    SQL.setString (3, modelo.getHorainicio ());
                    SQL.setString (4, modelo.getHorafin ());
                    SQL.setString (5, modelo.getCantidadconsumos ());
                    SQL.setString (6, modelo.getLunes ());
                    SQL.setString (7, modelo.getMartes ());
                    SQL.setString (8, modelo.getMiercoles ());
                    SQL.setString (9, modelo.getJueves ());
                    SQL.setString (10, modelo.getViernes ());
                    SQL.setString (11, modelo.getSabado ());
                    SQL.setString (12, modelo.getDomingo ());
                    SQL.setString (13, modelo.getFestivo ());
                    SQL.setInt (14, modelo.getModeloTipoConsumo ().getId ());
                    SQL.setInt (15, modelo.getId ());
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
            } catch (Exception e)
            {
                System.out.println (e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    /**
     * Permite la eliminar un dato en la tabla de Grupo Consumo
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Delete (HttpServletRequest request)
    {
        if (!"".equals (request.getParameter ("id")))
        {
            String idtmp = request.getParameter ("id");
            ModeloHorarioConsumo modelo = new ModeloHorarioConsumo ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));

            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("DELETE FROM `horarioconsumo` "
                            + "WHERE `Id` = ?;");
                    SQL.setInt (1, modelo.getId ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "2";
                    }
                } catch (SQLException e)
                {
                    System.out.println (e);
                    resultado = "-2";
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

    /**
     * Permite listar la información de la tabla de Horario Consumo Metodo
     * Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    public LinkedList<ModeloHorarioConsumo> Read ()
    {
        LinkedList<ModeloHorarioConsumo> listModeloHorarioConsumoes = new LinkedList<ModeloHorarioConsumo> ();
        con = conexion.abrirConexion ();
        try
        {
            SQL = con.prepareStatement ("SELECT"
                    + "`id`,"
                    + "`codigo`,"
                    + "`nombre`,"
                    + "`horainicio`,"
                    + "`horafin`,"
                    + "`cantidadconsumos`,"
                    + "`lunes`,"
                    + "`martes`,"
                    + "`miercoles`,"
                    + "`jueves`,"
                    + "`viernes`,"
                    + "`sabado`,"
                    + "`domingo`,"
                    + "`festivo`,"
                    + "`tipoConsumo` "
                    + "FROM `horarioconsumo`;");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloHorarioConsumo modelo = new ModeloHorarioConsumo ();
                modelo.setId (res.getInt ("id"));
                modelo.setCodigo (res.getString ("codigo"));
                modelo.setNombre (res.getString ("nombre"));
                modelo.setHorainicio (res.getString ("horainicio"));
                modelo.setHorafin (res.getString ("horafin"));
                modelo.setCantidadconsumos (res.getString ("cantidadconsumos"));
                modelo.setLunes (res.getString ("lunes"));
                modelo.setMartes (res.getString ("martes"));
                modelo.setMiercoles (res.getString ("miercoles"));
                modelo.setJueves (res.getString ("jueves"));
                modelo.setViernes (res.getString ("viernes"));
                modelo.setSabado (res.getString ("sabado"));
                modelo.setDomingo (res.getString ("domingo"));
                modelo.setFestivo (res.getString ("festivo"));
                modelo.setModeloTipoConsumo (controladorTipoConsumo.getModelo (Integer.parseInt (res.getString ("tipoConsumo"))));
                listModeloHorarioConsumoes.add (modelo);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println (e);
        }
        return listModeloHorarioConsumoes;
    }

    /**
     * Permite listar la información de la tabla de Horario Consumo
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @param response
     * @return String
     * @version: 07/05/2020
     */
    public String Read (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String out = null;
        try
        {
            LinkedList<ModeloHorarioConsumo> listmoHorarioConsumos;
            listmoHorarioConsumos = Read ();
            response.setContentType ("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Codigo</th>";
            out += "<th>Nombre</th>";
            out += "<th>Hora Inicio</th>";
            out += "<th>Hora Fin</th>";
            out += "<th>No Consumos</th>";
            out += "<th>TipoConsumo</th>";
            out += "<th>L</th>";
            out += "<th>M</th>";
            out += "<th>Mx</th>";
            out += "<th>J</th>";
            out += "<th>V</th>";
            out += "<th>S</th>";
            out += "<th>D</th>";
            out += "<th>F</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloHorarioConsumo modelo : listmoHorarioConsumos)
            {
                out += "<tr>";
                out += "<td>" + modelo.getCodigo () + "</td>";
                out += "<td>" + modelo.getNombre () + "</td>";
                out += "<td>" + modelo.getHorainicio () + "</td>";
                out += "<td>" + modelo.getHorafin () + "</td>";
                out += "<td>" + modelo.getCantidadconsumos () + "</td>";
                out += "<td>" + modelo.getModeloTipoConsumo ().getNombre () + "</td>";
                out += "<td>" + modelo.getLunes () + "</td>";
                out += "<td>" + modelo.getMartes () + "</td>";
                out += "<td>" + modelo.getMiercoles () + "</td>";
                out += "<td>" + modelo.getJueves () + "</td>";
                out += "<td>" + modelo.getViernes () + "</td>";
                out += "<td>" + modelo.getSabado () + "</td>";
                out += "<td>" + modelo.getDomingo () + "</td>";
                out += "<td>" + modelo.getFestivo () + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modelo.getId () + "\"";
                out += "data-codigo=\"" + modelo.getCodigo () + "\"";
                out += "data-nombre=\"" + modelo.getNombre () + "\"";
                out += "data-horaincio=\"" + modelo.getHorainicio () + "\"";
                out += "data-horafin=\"" + modelo.getHorafin () + "\"";
                out += "data-cantidadconsumos=\"" + modelo.getCantidadconsumos () + "\"";
                out += "data-lunes=\"" + modelo.getLunes () + "\"";
                out += "data-martes=\"" + modelo.getMartes () + "\"";
                out += "data-miercoles=\"" + modelo.getMiercoles () + "\"";
                out += "data-jueves=\"" + modelo.getJueves () + "\"";
                out += "data-viernes=\"" + modelo.getViernes () + "\"";
                out += "data-sabado=\"" + modelo.getSabado () + "\"";
                out += "data-domingo=\"" + modelo.getDomingo () + "\"";
                out += "data-festivo=\"" + modelo.getFestivo () + "\"";
                out += "data-tipoconsumo=\"" + modelo.getModeloTipoConsumo ().getId () + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modelo.getId () + "\"";
                out += "data-codigo=\"" + modelo.getCodigo () + "\"";
                out += "data-nombre=\"" + modelo.getNombre () + "\"";
                out += "data-horaincio=\"" + modelo.getHorainicio () + "\"";
                out += "data-horafin=\"" + modelo.getHorafin () + "\"";
                out += "data-cantidadconsumos=\"" + modelo.getCantidadconsumos () + "\"";
                out += "data-lunes=\"" + modelo.getLunes () + "\"";
                out += "data-martes=\"" + modelo.getMartes () + "\"";
                out += "data-miercoles=\"" + modelo.getMiercoles () + "\"";
                out += "data-jueves=\"" + modelo.getJueves () + "\"";
                out += "data-viernes=\"" + modelo.getViernes () + "\"";
                out += "data-sabado=\"" + modelo.getSabado () + "\"";
                out += "data-domingo=\"" + modelo.getDomingo () + "\"";
                out += "data-festivo=\"" + modelo.getFestivo () + "\"";
                out += "data-tipoconsumo=\"" + modelo.getModeloTipoConsumo ().getId () + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e)
        {
            System.out.println ("Error en el proceso de la tabla " + e.getMessage ());
        }
        return out;
    }

    /**
     * Permite listar la información de la tabla de horario Consumo
     * identificadno el ID
     *
     * @author: Carlos A Dominguez D
     * @param Id
     * @return ModeloEmpresa
     * @version: 07/05/2020
     */
    ModeloHorarioConsumo getModelo (int Id)
    {
        ModeloHorarioConsumo modelo = new ModeloHorarioConsumo ();
        con = conexion.abrirConexion ();
        try
        {
            SQL = con.prepareStatement ("SELECT"
                    + "`id`,"
                    + "`codigo`,"
                    + "`nombre`,"
                    + "`horainicio`,"
                    + "`horafin`,"
                    + "`cantidadconsumos`,"
                    + "`lunes`,"
                    + "`martes`,"
                    + "`miercoles`,"
                    + "`jueves`,"
                    + "`viernes`,"
                    + "`sabado`,"
                    + "`domingo`,"
                    + "`festivo`,"
                    + "`tipoConsumo` "
                    + "FROM `horarioconsumo`"
                    + "WHERE id = ?;");
            SQL.setInt (1, Id);
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                modelo.setId (res.getInt ("id"));
                modelo.setCodigo (res.getString ("codigo"));
                modelo.setNombre (res.getString ("nombre"));
                modelo.setHorainicio (res.getString ("horainicio"));
                modelo.setHorafin (res.getString ("horafin"));
                modelo.setCantidadconsumos (res.getString ("cantidadconsumos"));
                modelo.setLunes (res.getString ("lunes"));
                modelo.setMartes (res.getString ("martes"));
                modelo.setMiercoles (res.getString ("miercoles"));
                modelo.setJueves (res.getString ("jueves"));
                modelo.setViernes (res.getString ("viernes"));
                modelo.setSabado (res.getString ("sabado"));
                modelo.setDomingo (res.getString ("domingo"));
                modelo.setFestivo (res.getString ("festivo"));
                modelo.setModeloTipoConsumo (controladorTipoConsumo.getModelo (Integer.parseInt (res.getString ("tipoConsumo"))));
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
