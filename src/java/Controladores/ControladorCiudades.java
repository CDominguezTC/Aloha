/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Carlos A Dominguez D
 */
public class ControladorCiudades {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    public String Insert(HttpServletRequest request) {
        if ("".equals(request.getParameter("id")))
        {
            ModeloCiudad modelo = new ModeloCiudad(
                    0,
                    request.getParameter("codigo"),
                    request.getParameter("nombre")
            );
            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("INSERT INTO ciudad(codigo,descripcion) VALUE (?,?)");
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
            ModeloCiudad modelo = new ModeloCiudad(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("codigo"),
                    request.getParameter("nombre")
            );
            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("UPDATE ciudad SET codigo = ?, descripcion = ? WHERE id = ?;");
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
            ModeloCiudad modelo = new ModeloCiudad();
            modelo.setId(Integer.parseInt(request.getParameter("id")));

            try
            {
                con = conexion.abrirConexion();
                try
                {
                    SQL = con.prepareStatement("DELETE FROM `ciudad` "
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
    
      public LinkedList<ModeloCiudad> Read() {
        LinkedList<ModeloCiudad> listModeloCiudades = new LinkedList<ModeloCiudad>();        
        con = conexion.abrirConexion();        
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`id`, "
                    + "`codigo`, "
                    + "`descripcion` "
                    + "FROM `ciudad`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloCiudad modelo = new ModeloCiudad();
                modelo.setId(res.getInt("id"));
                modelo.setCodigo(res.getString("codigo"));
                modelo.setDescripcion(res.getString("descripcion"));                
                listModeloCiudades.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return listModeloCiudades;
    }
      
      public String ReadCiudad(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String out = null;
        try
        {
            LinkedList<ModeloCiudad> listmoCiudades;
            ControladorCiudades controladorCiudad = new ControladorCiudades();
            listmoCiudades = controladorCiudad.Read();
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
            for (ModeloCiudad modeloCiudad : listmoCiudades)
            {
                out += "<tr>";
                out += "<td>" + modeloCiudad.getId() + "</td>";
                out += "<td>" + modeloCiudad.getCodigo() + "</td>";
                out += "<td>" + modeloCiudad.getDescripcion()+ "</td>";                
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloCiudad.getId() + "\"";
                out += "data-codigo=\"" + modeloCiudad.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloCiudad.getDescripcion()+ "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloCiudad.getId() + "\"";
                out += "data-codigo=\"" + modeloCiudad.getCodigo() + "\"";
                out += "data-nombre=\"" + modeloCiudad.getDescripcion()+ "\"";
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


//    public ModeloCiudad Search(String codigo) {
//        ModeloCiudad modeloCiudad = new ModeloCiudad();
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try
//        {
//            PreparedStatement SQL = con.prepareStatement("SELECT id,codigo,descripcion FROM ciudad WHERE codigo = ?");
//            SQL.setString(1, codigo);
//            ResultSet res = SQL.executeQuery();
//            if (res.next())
//            {
//                modeloCiudad.setId(res.getInt("id"));
//                modeloCiudad.setCodigo(res.getString("codigo"));
//                modeloCiudad.setDescripcion(res.getString("descripcion"));
//            }
//            res.close();
//            SQL.close();
//            con.close();
//        } catch (SQLException e)
//        {
//            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
//        }
//        return modeloCiudad;
//    }

//    public ModeloCiudad SearchId(String Id) {
//        ModeloCiudad modeloCiudad = new ModeloCiudad();
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try
//        {
//            PreparedStatement SQL = con.prepareStatement("SELECT id,codigo,descripcion FROM ciudad WHERE id = ?");
//            SQL.setString(1, Id);
//            ResultSet res = SQL.executeQuery();
//            if (res.next())
//            {
//                modeloCiudad.setId(res.getInt("id"));
//                modeloCiudad.setCodigo(res.getString("codigo"));
//                modeloCiudad.setDescripcion(res.getString("descripcion"));
//            }
//            res.close();
//            SQL.close();
//            con.close();
//        } catch (SQLException e)
//        {
//            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
//        }
//        return modeloCiudad;
//    }

//    public ModeloCiudad SearchNombre(String Nombre) {
//        ModeloCiudad modeloCiudad = new ModeloCiudad();
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try
//        {
//            PreparedStatement SQL = con.prepareStatement("SELECT id,codigo,descripcion FROM ciudad WHERE descripcion = ?");
//            SQL.setString(1, Nombre);
//            ResultSet res = SQL.executeQuery();
//            if (res.next())
//            {
//                modeloCiudad.setId(res.getInt("id"));
//                modeloCiudad.setCodigo(res.getString("codigo"));
//                modeloCiudad.setDescripcion(res.getString("descripcion"));
//            }
//            res.close();
//            SQL.close();
//            con.close();
//        } catch (SQLException e)
//        {
//            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
//        }
//        return modeloCiudad;
//    }

//    public List<ModeloCiudad> Read(String Clave) {
//        String forSql = "%" + Clave + "%";
//        PreparedStatement SQL = null;
//        List<ModeloCiudad> modeloCiudad = new ArrayList<ModeloCiudad>();
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try
//        {
//            if (Clave != null)
//            {
//                SQL = con.prepareStatement("SELECT id,codigo,descripcion FROM ciudad WHERE codigo LIKE ? OR descripcion LIKE ? ORDER BY descripcion ");
//                SQL.setString(1, forSql);
//                SQL.setString(2, forSql);
//            } else
//            {
//                SQL = con.prepareStatement("SELECT id,codigo,descripcion FROM ciudad ORDER BY descripcion ");
//            }
//            ResultSet res = SQL.executeQuery();
//            while (res.next())
//            {
//                ModeloCiudad modeloCiudades = new ModeloCiudad();
//                modeloCiudades.setId(res.getInt("id"));
//                modeloCiudades.setCodigo(res.getString("codigo"));
//                modeloCiudades.setDescripcion(res.getString("descripcion"));
//                modeloCiudad.add(modeloCiudades);
//            }
//            res.close();
//            SQL.close();
//            con.close();
//        } catch (SQLException e)
//        {
//            JOptionPane.showMessageDialog(null, "Error buscandp el dato solicitado " + e);
//        }
//        return modeloCiudad;
//    }

//    public boolean Delete(ModeloCiudad modeloCiudad) {
//        boolean resulDelete = false;
//        Connection con;
//        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
//        con = conexionBdMysql.abrirConexion();
//        try
//        {
//            PreparedStatement SQL = con.prepareStatement("DELETE FROM Ciudad WHERE id = ?;");
//            SQL.setInt(1, modeloCiudad.getId());
//            SQL.executeUpdate();
//            resulDelete = true;
//            SQL.close();
//            con.close();
//        } catch (SQLException e)
//        {
//            JOptionPane.showMessageDialog(null, "Error al borrar la Ciudad " + e);
//        }
//        return resulDelete;
//    }
//}
