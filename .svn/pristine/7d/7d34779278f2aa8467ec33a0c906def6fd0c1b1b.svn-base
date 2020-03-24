/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloCentroCosto;
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
public class ControladorCentroCosto
{

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    public String Insert(HttpServletRequest request)
    {
        if ("".equals(request.getParameter("id")))
        {
            ModeloCentroCosto modelo = new ModeloCentroCosto(
                    0,
                    request.getParameter("codigo"),
                    request.getParameter("descripcion")
            );
            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("INSERT INTO `centrocosto`("
                            + "`nombre`,"
                            + "`codigoInterno`)"
                            + "VALUE (?,?);");
                    SQL.setString(1, modelo.getDescripcion());
                    SQL.setString(2, modelo.getCodigo());
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
            ModeloCentroCosto modelo = new ModeloCentroCosto(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("codigo"),
                    request.getParameter("descripcion")
            );
            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("UPDATE `centrocosto` SET "
                            + "`nombre` = ?, "
                            + "`codigoInterno` = ? "
                            + "WHERE `id` = ?;");
                    SQL.setString(1, modelo.getDescripcion());
                    SQL.setString(2, modelo.getCodigo());
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
            ModeloCentroCosto modelo = new ModeloCentroCosto();
            modelo.setId(Integer.parseInt(request.getParameter("id")));

            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("DELETE FROM `centrocosto` "
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

    public LinkedList<ModeloCentroCosto> Read()
    {
        LinkedList<ModeloCentroCosto> listModeloCentroCostos = new LinkedList<ModeloCentroCosto>();
        con = conexion.abrirConexion();
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`nombre`,"
                    + "`codigoInterno`,"
                    + "`definicionId` "
                    + "FROM `centrocosto`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloCentroCosto modelo = new ModeloCentroCosto();
                modelo.setId(res.getInt("id"));
                modelo.setCodigo(res.getString("codigoInterno"));
                modelo.setDescripcion(res.getString("nombre"));
                listModeloCentroCostos.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return listModeloCentroCostos;
    }

    public String Read(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String out = null;
        try
        {
            LinkedList<ModeloCentroCosto> listmoCentroCosto;
            ControladorCentroCosto controladorCentroCosto = new ControladorCentroCosto();
            listmoCentroCosto = controladorCentroCosto.Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Codigo</th>";
            out += "<th>Nombre</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloCentroCosto modeloCentroCosto : listmoCentroCosto)
            {
                out += "<tr>";
                out += "<td>" + modeloCentroCosto.getCodigo() + "</td>";
                out += "<td>" + modeloCentroCosto.getDescripcion() + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloCentroCosto.getId() + "\"";
                out += "data-codigo=\"" + modeloCentroCosto.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloCentroCosto.getDescripcion() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloCentroCosto.getId() + "\"";
                out += "data-codigo=\"" + modeloCentroCosto.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloCentroCosto.getDescripcion() + "\"";
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

    ModeloCentroCosto getModelo(int Id)
    {        
        ModeloCentroCosto modelo = new ModeloCentroCosto();
        con = conexion.abrirConexion();
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`nombre`,"
                    + "`codigoInterno`,"
                    + "`definicionId` "
                    + "FROM `centrocosto`"
                    + "WHERE id = ?;");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {     
                modelo.setId(res.getInt("id"));
                modelo.setCodigo(res.getString("codigoInterno"));
                modelo.setDescripcion(res.getString("nombre"));        
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

//  public boolean Update(ModeloCentroCosto modelo)
//    {
//        boolean resulInser = false;
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try 
//        {
//            PreparedStatement SQL;
//            SQL = con.prepareStatement("UPDATE centrocosto SET codigoInterno = ?, nombre = ? WHERE id = ?;");
//            SQL.setString(1, modelo.getCodigo());
//            SQL.setString(2, modelo.getDescripcion());            
//            SQL.setInt(3, modelo.getId());
//            SQL.executeUpdate();
//            resulInser = true;
//            SQL.close();
//            con.close();
//        } 
//        catch (SQLException e) 
//        {
//            JOptionPane.showMessageDialog(null, "Error al actualizar el centro de costo " + e);
//        }
//        return resulInser;        
//    }
