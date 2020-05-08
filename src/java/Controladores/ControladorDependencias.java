package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloDependencias;
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
 * Esta clase permite controlar los eventos de Dependencias
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorDependencias
{

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql ();

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd
     * Dependencias
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
            ModeloDependencias modelo = new ModeloDependencias (
                    0,
                    request.getParameter ("codigo"),
                    request.getParameter ("nombre")
            );
            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("INSERT INTO dependencia(codigo,descripcion) VALUE (?,?)");
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
            ModeloDependencias modelo = new ModeloDependencias (
                    Integer.parseInt (request.getParameter ("id")),
                    request.getParameter ("codigo"),
                    request.getParameter ("nombre")
            );
            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("UPDATE dependencia SET codigo = ?, descripcion = ? WHERE id = ?;");
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
     * Permite la eliminar un dato en la tabla de Dependencias
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
            ModeloDependencias modelo = new ModeloDependencias ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));

            try
            {
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("DELETE FROM `dependencia` "
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
     * Permite listar la información de la tabla de Dependencias Metodo Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    private LinkedList<ModeloDependencias> Read ()
    {
        LinkedList<ModeloDependencias> listModeloDependencias = new LinkedList<ModeloDependencias> ();
        con = conexion.abrirConexion ();
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`id`, "
                    + "`codigo`, "
                    + "`descripcion` "
                    + "FROM `dependencia`;");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloDependencias modelo = new ModeloDependencias ();
                modelo.setId (res.getInt ("id"));
                modelo.setCodigo (res.getString ("codigo"));
                modelo.setDescripcion (res.getString ("descripcion"));
                listModeloDependencias.add (modelo);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println (e);
        }
        return listModeloDependencias;
    }

    /**
     * Permite listar la información de la tabla de Dependencias
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
            LinkedList<ModeloDependencias> listmoDependencias;
            listmoDependencias = Read ();
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
            for (ModeloDependencias modeloDependencia : listmoDependencias)
            {
                out += "<tr>";
                out += "<td>" + modeloDependencia.getId () + "</td>";
                out += "<td>" + modeloDependencia.getCodigo () + "</td>";
                out += "<td>" + modeloDependencia.getDescripcion () + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloDependencia.getId () + "\"";
                out += "data-codigo=\"" + modeloDependencia.getCodigo () + "\"";
                out += "data-nombre=\"" + modeloDependencia.getDescripcion () + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloDependencia.getId () + "\"";
                out += "data-codigo=\"" + modeloDependencia.getCodigo () + "\"";
                out += "data-nombre=\"" + modeloDependencia.getDescripcion () + "\"";
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
