/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloFunciones;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorFunciones {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    public String Insert(HttpServletRequest request) {
        if ("".equals(request.getParameter("id")))
        {
            ModeloFunciones modelo = new ModeloFunciones(
                    0,
                    request.getParameter("nombre"),
                    request.getParameter("descripcion"),
                    request.getParameter("estado"),
                    request.getParameter("codReloj")
                    
            );
            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("INSERT INTO funciones(nombre,descripcion,codReloj) VALUE (?,?,?)");
                    SQL.setString(1, modelo.getNombre());
                    SQL.setString(2, modelo.getDescripcion());
                    SQL.setString(3, modelo.getCodReloj());
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
            ModeloFunciones modelo = new ModeloFunciones(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("nombre"),
                    request.getParameter("descripcion"),
                    request.getParameter("estado"),
                    request.getParameter("codReloj")
            );
            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("UPDATE funciones SET nombre = ?, descripcion = ?, codReloj = ?  WHERE id = ?;");
                    SQL.setString(1, modelo.getNombre());
                    SQL.setString(2, modelo.getDescripcion());
                    SQL.setString(3, modelo.getCodReloj());
                    SQL.setInt(4, modelo.getId());
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
    
    public String Delete(HttpServletRequest request) {
        if (!"".equals(request.getParameter("id")))
        {
            String idtmp = request.getParameter("id");
            ModeloFunciones modelo = new ModeloFunciones();
            modelo.setId(Integer.parseInt(request.getParameter("id")));

            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("DELETE FROM `funciones` "
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
    
     public LinkedList<ModeloFunciones> Read() {
        LinkedList<ModeloFunciones> listModeloFunciones = new LinkedList<ModeloFunciones>();        
        con = conexion.abrirConexion();        
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`id`, "
                    + "`nombre`, "
                    + "`descripcion`, "
                    + "`codReloj` "
                    + "FROM `funciones`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloFunciones modelo = new ModeloFunciones();
                modelo.setId(res.getInt("id"));
                modelo.setNombre(res.getString("nombre"));
                modelo.setDescripcion(res.getString("descripcion"));   
                modelo.setCodReloj(res.getString("codReloj"));   
                listModeloFunciones.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return listModeloFunciones;
    }
     
       public String ReadFunciones(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String out = null;
        try
        {
            LinkedList<ModeloFunciones> listmoFunciones;
            ControladorFunciones controladorFunciones = new ControladorFunciones();
            listmoFunciones = controladorFunciones.Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Id</th>";
            out += "<th>Nombre</th>";
            out += "<th>Descripcion</th>";
            out += "<th>Codigo Reloj</th>";   
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
             for (ModeloFunciones modeloFunciones : listmoFunciones)
            {
                out += "<tr>";
                out += "<td>" + modeloFunciones.getId() + "</td>";
                out += "<td>" + modeloFunciones.getNombre() + "</td>";
                out += "<td>" + modeloFunciones.getDescripcion()+ "</td>";  
                out += "<td>" + modeloFunciones.getCodReloj()+ "</td>";   
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloFunciones.getId() + "\"";
                out += "data-nombre=\"" + modeloFunciones.getNombre() + "\"";
                out += "data-descripcion=\"" + modeloFunciones.getDescripcion()+ "\"";
                out += "data-codreloj=\"" + modeloFunciones.getCodReloj()+ "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloFunciones.getId() + "\"";
                out += "data-nombre=\"" + modeloFunciones.getNombre() + "\"";
                out += "data-descripcion=\"" + modeloFunciones.getDescripcion()+ "\"";
                out += "data-codreloj=\"" + modeloFunciones.getCodReloj()+ "\"";
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

   