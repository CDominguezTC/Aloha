/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloPeriodos;
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
public class ControladorPeriodos {
    
    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    public String Insert(HttpServletRequest request) {
        if ("".equals(request.getParameter("id")))
        {
            ModeloPeriodos modelo = new ModeloPeriodos(
                    0,
                    request.getParameter("codigo"),
                    request.getParameter("nombre"),
                    request.getParameter("fechaInicio"),
                    request.getParameter("fechaFin"),
                    request.getParameter("observacion")               
            );
            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("INSERT INTO periodo(codigo,nombre,fechaInicio,fechaFin,observacion)VALUE (?,?,?,?,?)");
                     SQL.setString(1, modelo.getCodigo());
                     SQL.setString(2, modelo.getNombre());            
                     SQL.setString(3, modelo.getFechaInicio());            
                     SQL.setString(4, modelo.getFechaFin());            
                     SQL.setString(5, modelo.getObservacion());
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
            ModeloPeriodos modelo = new ModeloPeriodos(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("codigo"),
                    request.getParameter("nombre"),
                    request.getParameter("fechaInicio"),
                    request.getParameter("fechaFin"),
                    request.getParameter("observacion")
            );
            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("UPDATE periodo SET codigo = ?,nombre = ?,fechaInicio = ?,fechaFin = ?, observacion = ? WHERE id = ?;");
                     SQL.setString(1, modelo.getCodigo());
                     SQL.setString(2, modelo.getNombre());      
                     SQL.setString(3, modelo.getFechaInicio());      
                     SQL.setString(4, modelo.getFechaFin());      
                     SQL.setString(5, modelo.getObservacion());      
                     SQL.setInt(6, modelo.getId()); 
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
            ModeloPeriodos modelo = new ModeloPeriodos();
            modelo.setId(Integer.parseInt(request.getParameter("id")));

            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("DELETE FROM `periodo` "
                            + "WHERE `id` = ?;");
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
     
       public LinkedList<ModeloPeriodos> Read() {
        LinkedList<ModeloPeriodos> listModeloPeriodos = new LinkedList<ModeloPeriodos>();        
        con = conexion.abrirConexion();        
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`id`, "
                    + "`codigo`, "
                    + "`nombre`, "
                    + "`fechaInicio`, "
                    + "`fechaFin`, "
                    + "`observacion` "
                    + "FROM `periodo`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloPeriodos modelo = new ModeloPeriodos();
                modelo.setId(res.getInt("id"));
                modelo.setCodigo(res.getString("codigo"));
                modelo.setNombre(res.getString("nombre"));
                modelo.setFechaInicio(res.getString("fechaInicio"));
                modelo.setFechaFin(res.getString("fechaFin"));
                modelo.setObservacion(res.getString("observacion"));    
                listModeloPeriodos.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return listModeloPeriodos;
    } 
       
       public String ReadPeriodos(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String out = null;
        try
        {
            LinkedList<ModeloPeriodos> listmoPeriodos;
            ControladorPeriodos controladorPeriodos = new ControladorPeriodos();
            listmoPeriodos = controladorPeriodos.Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Id</th>";
            out += "<th>Codigo</th>";
            out += "<th>Nombre</th>";
            out += "<th>Fecha Inicio</th>";
            out += "<th>Fecha Fin</th>";
            out += "<th>Observaciones</th>";
            out += "<th>Opciones</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
             for (ModeloPeriodos modeloPeriodos : listmoPeriodos)
            {
                out += "<tr>";
                out += "<td>" + modeloPeriodos.getId() + "</td>";
                out += "<td>" + modeloPeriodos.getCodigo() + "</td>";
                out += "<td>" + modeloPeriodos.getNombre()+ "</td>";  
                out += "<td>" + modeloPeriodos.getFechaInicio()+ "</td>";   
                out += "<td>" + modeloPeriodos.getFechaFin()+ "</td>";   
                out += "<td>" + modeloPeriodos.getObservacion()+ "</td>";   
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloPeriodos.getId() + "\"";
                out += "data-codigo=\"" + modeloPeriodos.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloPeriodos.getNombre()+ "\"";
                out += "data-fechaInicio=\"" + modeloPeriodos.getFechaInicio()+ "\"";
                out += "data-fechaFin=\"" + modeloPeriodos.getFechaFin()+ "\"";
                out += "data-observacion=\"" + modeloPeriodos.getObservacion()+ "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloPeriodos.getId() + "\"";
                out += "data-codigo=\"" + modeloPeriodos.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloPeriodos.getNombre()+ "\"";
                out += "data-fechaInicio=\"" + modeloPeriodos.getFechaInicio()+ "\"";
                out += "data-fechaFin=\"" + modeloPeriodos.getFechaFin()+ "\"";
                out += "data-observacion=\"" + modeloPeriodos.getObservacion()+ "\"";
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
