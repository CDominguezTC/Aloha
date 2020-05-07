package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloCiudad;
import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Esta clase permite controlar los eventos de las Ciudades
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorCiudades
{

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql ();

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd Ciudades
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
            ModeloCiudad modelo = new ModeloCiudad (
                    0,
                    request.getParameter ("codigo"),
                    request.getParameter ("nombre")
            );
            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("INSERT INTO ciudad(codigo,descripcion) VALUE (?,?)");
                    SQL.setString (1, modelo.getCodigo ());
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
            ModeloCiudad modelo = new ModeloCiudad (
                    Integer.parseInt (request.getParameter ("id")),
                    request.getParameter ("codigo"),
                    request.getParameter ("nombre")
            );
            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("UPDATE ciudad SET codigo = ?, descripcion = ? WHERE id = ?;");
                    SQL.setString (1, modelo.getCodigo ());
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
     * Permite la eliminar un dato en la tabla de Ciudades
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
            ModeloCiudad modelo = new ModeloCiudad ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));

            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("DELETE FROM `ciudad` "
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
     * Permite listar la información de la tabla de Ciudad Metodo Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    private LinkedList<ModeloCiudad> Read ()
    {
        LinkedList<ModeloCiudad> listModeloCiudades = new LinkedList<ModeloCiudad> ();
        con = conexion.abrirConexion ();
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`id`, "
                    + "`codigo`, "
                    + "`descripcion` "
                    + "FROM `ciudad`;");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloCiudad modelo = new ModeloCiudad ();
                modelo.setId (res.getInt ("id"));
                modelo.setCodigo (res.getString ("codigo"));
                modelo.setDescripcion (res.getString ("descripcion"));
                listModeloCiudades.add (modelo);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println (e);
        }
        return listModeloCiudades;
    }

    /**
     * Permite listar la información de la tabla de Ciudad
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
            LinkedList<ModeloCiudad> listmoCiudades;
            listmoCiudades = Read ();
            response.setContentType ("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Id</th>";
            out += "<th>Codigo</th>";
            out += "<th>Nombre</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloCiudad modeloCiudad : listmoCiudades)
            {
                out += "<tr>";
                out += "<td>" + modeloCiudad.getId () + "</td>";
                out += "<td>" + modeloCiudad.getCodigo () + "</td>";
                out += "<td>" + modeloCiudad.getDescripcion () + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloCiudad.getId () + "\"";
                out += "data-codigo=\"" + modeloCiudad.getCodigo () + "\"";
                out += "data-nombre=\"" + modeloCiudad.getDescripcion () + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloCiudad.getId () + "\"";
                out += "data-codigo=\"" + modeloCiudad.getCodigo () + "\"";
                out += "data-nombre=\"" + modeloCiudad.getDescripcion () + "\"";
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
