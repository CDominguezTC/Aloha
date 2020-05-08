package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloEmpresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Esta clase permite controlar los eventos de Empresas
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorEmpresas
{

    String resultado = "";

    /**
     * Permite la inserción o actualización de los datos en la tabla Bd Empresas
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Insert (HttpServletRequest request)
    {
        ModeloEmpresa modeloEmpresa = new ModeloEmpresa ();
        try
        {
            System.out.println (request.getParameter ("id"));
            if ("".equals (request.getParameter ("id")))
            {
                modeloEmpresa.setId (0);
                modeloEmpresa.setNit (request.getParameter ("nit"));
                modeloEmpresa.setNombre (request.getParameter ("nombre"));
                modeloEmpresa.setDireccion (request.getParameter ("direccion"));
                modeloEmpresa.setContacto (request.getParameter ("contacto"));
                modeloEmpresa.setTelefono (request.getParameter ("telefono"));
                modeloEmpresa.setExt (request.getParameter ("extension"));
                modeloEmpresa.setEmail (request.getParameter ("email"));
                modeloEmpresa.setObservacion (request.getParameter ("observacion"));
                Connection con;
                ConexionBdMysql conexionBdMysql = new ConexionBdMysql ();
                con = conexionBdMysql.abrirConexion ();
                PreparedStatement SQL;
                SQL = con.prepareStatement ("INSERT INTO `empresa`("
                        + "`nombre`,"
                        + "`nit`,"
                        + "`direccion`,"
                        + "`contacto`,"
                        + "`email`,"
                        + "`telefono`,"
                        + "`ext`,"
                        + "`observacion`)"
                        + " VALUE (?,?,?,?,?,?,?,?);");
                SQL.setString (1, modeloEmpresa.getNombre ());
                SQL.setString (2, modeloEmpresa.getNit ());
                SQL.setString (3, modeloEmpresa.getDireccion ());
                SQL.setString (4, modeloEmpresa.getContacto ());
                SQL.setString (5, modeloEmpresa.getEmail ());
                SQL.setString (6, modeloEmpresa.getTelefono ());
                SQL.setString (7, modeloEmpresa.getExt ());
                SQL.setString (8, modeloEmpresa.getObservacion ());
                SQL.executeUpdate ();
                resultado = "1";
                SQL.close ();
                con.close ();
            }
            else
            {
                modeloEmpresa.setId (Integer.parseInt (request.getParameter ("id")));
                modeloEmpresa.setNit (request.getParameter ("nit"));
                modeloEmpresa.setNombre (request.getParameter ("nombre"));
                modeloEmpresa.setDireccion (request.getParameter ("direccion"));
                modeloEmpresa.setContacto (request.getParameter ("contacto"));
                modeloEmpresa.setTelefono (request.getParameter ("telefono"));
                modeloEmpresa.setExt (request.getParameter ("extension"));
                modeloEmpresa.setEmail (request.getParameter ("email"));
                modeloEmpresa.setObservacion (request.getParameter ("observacion"));
                Connection con;
                ConexionBdMysql conexionBdMysql = new ConexionBdMysql ();
                con = conexionBdMysql.abrirConexion ();
                PreparedStatement SQL;
                SQL = con.prepareStatement ("UPDATE `empresa`  SET "
                        + "`nombre` = ?,"
                        + "`nit` = ?,"
                        + "`direccion` = ?,"
                        + "`contacto` = ?,"
                        + "`email` = ?, "
                        + "`telefono` = ?,"
                        + "`ext` = ?, "
                        + "`observacion` = ? "
                        + "WHERE `id` = ?;");
                SQL.setString (1, modeloEmpresa.getNombre ());
                SQL.setString (2, modeloEmpresa.getNit ());
                SQL.setString (3, modeloEmpresa.getDireccion ());
                SQL.setString (4, modeloEmpresa.getContacto ());
                SQL.setString (5, modeloEmpresa.getEmail ());
                SQL.setString (6, modeloEmpresa.getTelefono ());
                SQL.setString (7, modeloEmpresa.getExt ());
                SQL.setString (8, modeloEmpresa.getObservacion ());
                SQL.setInt (9, modeloEmpresa.getId ());
                SQL.executeUpdate ();
                resultado = "1";
                SQL.close ();
                con.close ();
            }
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog (null, "Error al guardar la empresa " + e);
            resultado = "-1";
        }
        return resultado;
    }

    /**
     * Permite listar la información de la tabla de Empresas Metodo Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    public LinkedList<ModeloEmpresa> Read ()
    {
        PreparedStatement SQL = null;
        LinkedList<ModeloEmpresa> modeloEmpresa = new LinkedList<ModeloEmpresa> ();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql ();
        con = conexionBdMysql.abrirConexion ();
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`id`,"
                    + "`nombre`,"
                    + "`nit`,"
                    + "`direccion`,"
                    + "`contacto`,"
                    + "`email`,"
                    + "`telefono`,"
                    + "`ext`,"
                    + "`observacion` "
                    + "FROM `empresa`;");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloEmpresa modeloEmpresas = new ModeloEmpresa ();
                modeloEmpresas.setId (res.getInt ("id"));
                modeloEmpresas.setNombre (res.getString ("nombre"));
                modeloEmpresas.setNit (res.getString ("nit"));
                modeloEmpresas.setDireccion (res.getString ("direccion"));
                modeloEmpresas.setContacto (res.getString ("contacto"));
                modeloEmpresas.setEmail (res.getString ("email"));
                modeloEmpresas.setTelefono (res.getString ("telefono"));
                modeloEmpresas.setExt (res.getString ("ext"));
                modeloEmpresas.setObservacion (res.getString ("observacion"));
                modeloEmpresa.add (modeloEmpresas);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog (null, "Error buscandp el dato solicitado " + e);
        }
        return modeloEmpresa;
    }

    /**
     * Permite la eliminar un dato en la tabla de Empresas
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String Delete (HttpServletRequest request)
    {

        ModeloEmpresa modeloEmpresa = new ModeloEmpresa ();
        modeloEmpresa.setId (Integer.parseInt (request.getParameter ("id")));
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql ();
        con = conexionBdMysql.abrirConexion ();
        try
        {
            PreparedStatement SQL = con.prepareStatement ("DELETE FROM empresa WHERE id = ?;");
            SQL.setInt (1, modeloEmpresa.getId ());
            SQL.executeUpdate ();
            resultado = "2";
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog (null, "Error al borrar la empresa " + e);
            resultado = "-2";
        }
        return resultado;
    }

    /**
     * Permite listar la información de la tabla de Empresas
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @param response
     * @return String
     * @version: 07/05/2020
     */
    public String Read (HttpServletRequest request, HttpServletResponse response)
    {

        String out = null;
        try
        {
            LinkedList<ModeloEmpresa> listmodelo;
            listmodelo = Read ();
            response.setContentType ("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Nit</th>";
            out += "<th>Nombre</th>";
            out += "<th>Direccion</th>";
            out += "<th>Contacto</th>";
            out += "<th>Telefono</th>";
            out += "<th>extension</th>";
            out += "<th>Email</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloEmpresa modelo : listmodelo)
            {
                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getNit () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getNombre () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getDireccion () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getContacto () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getTelefono () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getExt () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getEmail () + "</td>";
                out += "<td WIDTH = \"10\" HEIGHT=\"0\" class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modelo.getId () + "\"";
                out += "data-nit=\"" + modelo.getNit () + "\"";
                out += "data-nombre=\"" + modelo.getNombre () + "\"";
                out += "data-direccion=\"" + modelo.getDireccion () + "\"";
                out += "data-contacto=\"" + modelo.getContacto () + "\"";
                out += "data-telefono=\"" + modelo.getTelefono () + "\"";
                out += "data-extension=\"" + modelo.getExt () + "\"";
                out += "data-email=\"" + modelo.getEmail () + "\"";
                out += "data-observacion=\"" + modelo.getObservacion () + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i></button>";
                //Boton Eliminar                
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modelo.getId () + "\"";
                out += "data-nit=\"" + modelo.getNit () + "\"";
                out += "data-nombre=\"" + modelo.getNombre () + "\"";
                out += "data-direccion=\"" + modelo.getDireccion () + "\"";
                out += "data-contacto=\"" + modelo.getContacto () + "\"";
                out += "data-telefono=\"" + modelo.getTelefono () + "\"";
                out += "data-extension=\"" + modelo.getExt () + "\"";
                out += "data-email=\"" + modelo.getEmail () + "\"";
                out += "data-observacion=\"" + modelo.getObservacion () + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i></button>";
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
     * Permite listar la información de la tabla de Empresas identificadno el ID
     *
     * @author: Carlos A Dominguez D
     * @param Id
     * @return ModeloEmpresa
     * @version: 07/05/2020
     */
    ModeloEmpresa getModelo (Integer Id)
    {

        ModeloEmpresa modeloEmpresas = new ModeloEmpresa ();
        PreparedStatement SQL = null;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql ();
        con = conexionBdMysql.abrirConexion ();
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`id`,"
                    + "`nombre`,"
                    + "`nit`,"
                    + "`direccion`,"
                    + "`contacto`,"
                    + "`email`,"
                    + "`telefono`,"
                    + "`ext`,"
                    + "`observacion` "
                    + "FROM `empresa`"
                    + "WHERE id = ?;");
            SQL.setInt (1, Id);
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                modeloEmpresas.setId (res.getInt ("id"));
                modeloEmpresas.setNombre (res.getString ("nombre"));
                modeloEmpresas.setNit (res.getString ("nit"));
                modeloEmpresas.setDireccion (res.getString ("direccion"));
                modeloEmpresas.setContacto (res.getString ("contacto"));
                modeloEmpresas.setEmail (res.getString ("email"));
                modeloEmpresas.setTelefono (res.getString ("telefono"));
                modeloEmpresas.setExt (res.getString ("ext"));
                modeloEmpresas.setObservacion (res.getString ("observacion"));
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog (null, "Error buscandp el dato solicitado " + e);
        }
        return modeloEmpresas;
    }
}
