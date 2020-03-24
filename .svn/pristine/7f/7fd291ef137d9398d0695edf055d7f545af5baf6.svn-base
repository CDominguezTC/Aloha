/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloTipoConsumo;
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
 *
 * @author Carlos A Dominguez D
 */
public class ControladorTipoConsumo
{

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    public String Insert(HttpServletRequest request)
    {
        if ("".equals(request.getParameter("id")))
        {
            ModeloTipoConsumo modelo = new ModeloTipoConsumo(
                    0,
                    request.getParameter("nombre"),
                    Integer.parseInt(request.getParameter("cantidad"))
            );
            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("INSERT INTO `tipoconsumo`"
                            + "(`Nombre`,"
                            + "`Cantidad`) "
                            + "VALUE (?,?);");
                    SQL.setString(1, modelo.getNombre());
                    SQL.setInt(2, modelo.getCantidad());
                    if (SQL.executeUpdate() > 0)
                    {
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } catch (SQLException e)
                {
                    System.out.println(e);
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e)
            {
                System.out.println(e);
                resultado = "-3";
            }
        } else
        {
            ModeloTipoConsumo modelo = new ModeloTipoConsumo(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("nombre"),
                    Integer.parseInt(request.getParameter("cantidad"))
            );
            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("UPDATE `tipoconsumo`  SET "
                            + "`Nombre` = ?, "
                            + "`Cantidad` = ? "
                            + "WHERE `Id` = ?;");
                    SQL.setString(1, modelo.getNombre());
                    SQL.setInt(2, modelo.getCantidad());
                    SQL.setInt(3, modelo.getId());
                    if (SQL.executeUpdate() > 0)
                    {
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } catch (SQLException e)
                {
                    System.out.println(e);
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e)
            {
                System.out.println(e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    public String Delete(HttpServletRequest request)
    {
        if (!"".equals(request.getParameter("id")))
        {
            String idtmp = request.getParameter("id");
            ModeloTipoConsumo modelo = new ModeloTipoConsumo();
            modelo.setId(Integer.parseInt(request.getParameter("id")));

            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("DELETE FROM `tipoconsumo` "
                            + "WHERE `Id` = ?;");
                    SQL.setInt(1, modelo.getId());
                    if (SQL.executeUpdate() > 0)
                    {
                        resultado = "2";
                    }
                } catch (SQLException e)
                {
                    System.out.println(e);
                    resultado = "-2";
                }
                SQL.close();
                con.close();
            } catch (SQLException e)
            {
                System.out.println(e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    public LinkedList<ModeloTipoConsumo> Read()
    {
        LinkedList<ModeloTipoConsumo> listModeloTipoConsumo = new LinkedList<ModeloTipoConsumo>();
        con = conexion.abrirConexion();
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Nombre`,"
                    + "`Cantidad` "
                    + "FROM `tipoconsumo`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloTipoConsumo modelo = new ModeloTipoConsumo();
                modelo.setId(res.getInt("id"));
                modelo.setNombre(res.getString("Nombre"));
                modelo.setCantidad(res.getInt("Cantidad"));
                listModeloTipoConsumo.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return listModeloTipoConsumo;
    }

    public String Read(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String out = null;
        try
        {
            LinkedList<ModeloTipoConsumo> listmoTipoConsumo;
            listmoTipoConsumo = Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";            
            out += "<th>Nomnre</th>";
            out += "<th>Cantidad</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloTipoConsumo modeloTipoConsumo : listmoTipoConsumo)
            {
                out += "<tr>";                
                out += "<td>" + modeloTipoConsumo.getNombre() + "</td>";
                out += "<td>" + modeloTipoConsumo.getCantidad() + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloTipoConsumo.getId() + "\"";
                out += "data-nombre=\"" + modeloTipoConsumo.getNombre() + "\"";
                out += "data-cantidad=\"" + modeloTipoConsumo.getCantidad() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloTipoConsumo.getId() + "\"";
                out += "data-nombre=\"" + modeloTipoConsumo.getNombre() + "\"";
                out += "data-cantidad=\"" + modeloTipoConsumo.getCantidad() + "\"";
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
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
//        String frm = request.getParameter("frm");
//        System.out.println(frm);
//        processRequest(request, response);
        return out;
    }

    ModeloTipoConsumo getModelo(int id)
    {
        ModeloTipoConsumo modelo = new ModeloTipoConsumo();
        con = conexion.abrirConexion();
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Nombre`,"
                    + "`Cantidad` "
                    + "FROM `tipoconsumo`"
                    + "WHERE Id = ?;");
            SQL.setInt(1, id);
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {

                modelo.setId(res.getInt("id"));
                modelo.setNombre(res.getString("Nombre"));
                modelo.setCantidad(res.getInt("Cantidad"));

            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return modelo;
    }

}
