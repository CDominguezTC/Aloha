package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloFestivo;
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
 * Esta clase permite controlar los eventos de los dias Festivos
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorFestivo
{

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql ();

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd Festivos
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
            ModeloFestivo modelo = new ModeloFestivo ();
            modelo.setId (0);
            modelo.setFecha (request.getParameter ("fecha"));
            modelo.setDescripcion (request.getParameter ("nombre"));
            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("INSERT INTO `festivos`("
                            + "`Fecha`,"
                            + "`Nota`) "
                            + "VALUE (?,?);");
                    SQL.setString (1, modelo.getFecha ());
                    SQL.setString (2, modelo.getDescripcion ());
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
            ModeloFestivo modelo = new ModeloFestivo ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));
            modelo.setFecha (request.getParameter ("fecha"));
            modelo.setDescripcion (request.getParameter ("nombre"));

            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("UPDATE `festivos`  SET "
                            + "`Fecha` = ?, "
                            + "`Nota` = ? "
                            + "WHERE `Id` = ?;");
                    SQL.setString (1, modelo.getFecha ());
                    SQL.setString (2, modelo.getDescripcion ());
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

    /**
     * Permite la eliminar un dato en la tabla de Festivos
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
            ModeloFestivo modelo = new ModeloFestivo ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));

            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("DELETE FROM `festivos` "
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
     * Permite listar la información de la tabla de Festivos Metodo Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    private LinkedList<ModeloFestivo> Read ()
    {
        LinkedList<ModeloFestivo> listModeloFestivo = new LinkedList<ModeloFestivo> ();
        con = conexion.abrirConexion ();
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`Fecha`,"
                    + "`Nota` "
                    + "FROM `festivos`;");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloFestivo modelo = new ModeloFestivo ();
                modelo.setId (res.getInt ("id"));
                modelo.setFecha (res.getString ("Fecha"));
                modelo.setDescripcion (res.getString ("Nota"));
                listModeloFestivo.add (modelo);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println (e);
        }
        return listModeloFestivo;
    }

    /**
     * Permite listar la información de la tabla de Festivos
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
            LinkedList<ModeloFestivo> listmoFestivos;
            listmoFestivos = Read ();
            response.setContentType ("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Fecha</th>";
            out += "<th>Descripcion</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloFestivo modeloFestivo : listmoFestivos)
            {
                out += "<tr>";
                out += "<td>" + modeloFestivo.getFecha () + "</td>";
                out += "<td>" + modeloFestivo.getDescripcion () + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modeloFestivo.getId () + "\"";
                out += "data-fecha=\"" + modeloFestivo.getFecha () + "\"";
                out += "data-nombre=\"" + modeloFestivo.getDescripcion () + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloFestivo.getId () + "\"";
                out += "data-fecha=\"" + modeloFestivo.getFecha () + "\"";
                out += "data-nombre=\"" + modeloFestivo.getDescripcion () + "\"";
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
}
