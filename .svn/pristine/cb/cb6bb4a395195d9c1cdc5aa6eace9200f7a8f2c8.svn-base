/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloAreas;
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
public class ControladorAreas {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    public String Insert(HttpServletRequest request) {
        if ("".equals(request.getParameter("id")))
        {
            ModeloAreas modelo = new ModeloAreas(
                    0,
                    request.getParameter("codigo"),
                    request.getParameter("nombre")
            );
            try
            {
                con = conexion.abrirConexion();
                try
                {                   
                    SQL = con.prepareStatement("INSERT INTO areas (codigo,descripcion)VALUE (?,?)");
                    SQL.setString(1, modelo.getCodigo());
                    SQL.setString(2, modelo.getDescripcion());
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
            ModeloAreas modelo = new ModeloAreas(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("codigo"),
                    request.getParameter("nombre")
            );
            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("UPDATE areas SET codigo = ?, descripcion = ? WHERE id = ?;");
                    SQL.setString(1, modelo.getCodigo());
                    SQL.setString(2, modelo.getDescripcion());
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
            } catch (Exception e)
            {
                System.out.println(e);
                resultado = "-3";
            }
        }
        return resultado;
    }
    
    public String Delete(HttpServletRequest request) {
        if (!"".equals(request.getParameter("id")))
        {
            String idtmp = request.getParameter("id");
            ModeloAreas modelo = new ModeloAreas();
            modelo.setId(Integer.parseInt(request.getParameter("id")));

            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("DELETE FROM `areas` "
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
    
    public LinkedList<ModeloAreas> Read() {
        LinkedList<ModeloAreas> listModeloAreases = new LinkedList<ModeloAreas>();        
        con = conexion.abrirConexion();        
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`id`, "
                    + "`codigo`, "
                    + "`descripcion` "
                    + "FROM `areas`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloAreas modelo = new ModeloAreas();
                modelo.setId(res.getInt("id"));
                modelo.setCodigo(res.getString("codigo"));
                modelo.setDescripcion(res.getString("descripcion"));                
                listModeloAreases.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return listModeloAreases;
    }

    public String ReadArea(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String out = null;
        try
        {
            LinkedList<ModeloAreas> listmoAreases;
            ControladorAreas controladorAreas = new ControladorAreas();
            listmoAreases = controladorAreas.Read();
            response.setContentType("text/html;charset=UTF-8");

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
            for (ModeloAreas modeloAreas : listmoAreases)
            {
                out += "<tr>";
                out += "<td>" + modeloAreas.getId() + "</td>";
                out += "<td>" + modeloAreas.getCodigo() + "</td>";
                out += "<td>" + modeloAreas.getDescripcion()+ "</td>";                
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloAreas.getId() + "\"";
                out += "data-codigo=\"" + modeloAreas.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloAreas.getDescripcion()+ "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloAreas.getId() + "\"";
                out += "data-codigo=\"" + modeloAreas.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloAreas.getDescripcion()+ "\"";
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
}
