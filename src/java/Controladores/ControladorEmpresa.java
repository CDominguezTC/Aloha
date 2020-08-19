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
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 * Esta clase permite controlar los eventos de Empresas
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorEmpresa {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;
    ControladorLog_error log;

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * empresa
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModeloEmpresa modeloEmpresa = new ModeloEmpresa();
        modeloEmpresa.setNombre(request.getParameter("nombre"));
        modeloEmpresa.setNit(request.getParameter("nit"));
        modeloEmpresa.setDireccion(request.getParameter("direccion"));
        modeloEmpresa.setContacto(request.getParameter("contacto"));
        modeloEmpresa.setEmail(request.getParameter("email"));
        modeloEmpresa.setTelefono(request.getParameter("telefono"));
        modeloEmpresa.setExt(request.getParameter("ext"));
        modeloEmpresa.setCiudad(request.getParameter("ciudad"));
        modeloEmpresa.setObservacion(request.getParameter("observacion"));
        modeloEmpresa.setEstado(request.getParameter("estado"));
        if ("".equals(request.getParameter("id"))) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(modeloEmpresa);
        } else {
            modeloEmpresa.setId(Integer.parseInt(request.getParameter("id")));
            resultado = Update(modeloEmpresa);
        }
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: empresa
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param Modelo
     * @return String
     * @version: 15/05/2020
     */
    public String Insert(ModeloEmpresa modeloEmpresa) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO empresa("
                        + "nombre, "
                        + "nit, "
                        + "direccion, "
                        + "contacto, "
                        + "email5, "
                        + "telefono, "
                        + "ext, "
                        + "ciudad, "
                        + "observacion, "
                        + "estado)"
                        + " VALUE  (?,?,?,?,?,?,?,?,?,?);", SQL.RETURN_GENERATED_KEYS);
                SQL.setString(1, modeloEmpresa.getNombre());
                SQL.setString(2, modeloEmpresa.getNit());
                SQL.setString(3, modeloEmpresa.getDireccion());
                SQL.setString(4, modeloEmpresa.getContacto());
                SQL.setString(5, modeloEmpresa.getEmail());
                SQL.setString(6, modeloEmpresa.getTelefono());
                SQL.setString(7, modeloEmpresa.getExt());
                SQL.setString(8, modeloEmpresa.getCiudad());
                SQL.setString(9, modeloEmpresa.getObservacion());
                SQL.setString(10, "S");
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.", "", "");
                        }
                    }
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en ControladorEmpresa" + e.getMessage());
                log.insertarError(user, "Error en la consulta SQL Insert en Controladorempresa" + e.getMessage());
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorempresa" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:empresa
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Update(ModeloEmpresa modeloEmpresa) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {

                if ("N".equals(modeloEmpresa.getEstado())) {
                    SQL = con.prepareStatement("UPDATE empresa SET "
                            + "estado = ?"
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloEmpresa.getEstado());
                    SQL.setInt(2, modeloEmpresa.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE empresa SET "
                            + "nombre = ?, "
                            + "nit = ?, "
                            + "direccion = ?, "
                            + "contacto = ?, "
                            + "email = ?, "
                            + "telefono = ?, "
                            + "ext = ?, "
                            + "ciudad = ?, "
                            + "observacion = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloEmpresa.getNombre());
                    SQL.setString(2, modeloEmpresa.getNit());
                    SQL.setString(3, modeloEmpresa.getDireccion());
                    SQL.setString(4, modeloEmpresa.getContacto());
                    SQL.setString(5, modeloEmpresa.getEmail());
                    SQL.setString(6, modeloEmpresa.getTelefono());
                    SQL.setString(7, modeloEmpresa.getExt());
                    SQL.setString(8, modeloEmpresa.getCiudad());
                    SQL.setString(9, modeloEmpresa.getObservacion());
                    SQL.setInt(10, modeloEmpresa.getId());
                }

                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorempresa" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorempresa" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Llena un Listado de la tabla empresa
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param vacio
     * @return LinkedList<ModeloEmpresa>
     * @version: 15/05/2020
     */
    public LinkedList<ModeloEmpresa> Read(String estado) throws SQLException {
        LinkedList<ModeloEmpresa> ListaModeloEmpresa = new LinkedList<ModeloEmpresa>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "nombre, "
                    + "nit, "
                    + "direccion, "
                    + "contacto, "
                    + "email, "
                    + "telefono, "
                    + "ext, "
                    + "ciudad, "
                    + "observacion, "
                    + "estado"
                    + " FROM empresa"
                    + " WHERE estado = ?");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloEmpresa modeloEmpresa = new ModeloEmpresa();
                modeloEmpresa.setId(res.getInt("id"));
                modeloEmpresa.setNombre(res.getString("nombre"));
                modeloEmpresa.setNit(res.getString("nit"));
                modeloEmpresa.setDireccion(res.getString("direccion"));
                modeloEmpresa.setContacto(res.getString("contacto"));
                modeloEmpresa.setEmail(res.getString("email"));
                modeloEmpresa.setTelefono(res.getString("telefono"));
                modeloEmpresa.setExt(res.getString("ext"));
                modeloEmpresa.setCiudad(res.getString("ciudad"));
                modeloEmpresa.setObservacion(res.getString("observacion"));
                modeloEmpresa.setEstado(res.getString("estado"));
                ListaModeloEmpresa.add(modeloEmpresa);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorempresa" + e);
        }
        return ListaModeloEmpresa;
    }

    /**
     * llena un modelo que viene con datos de un request para ser Eliminado
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (!"".equals(request.getParameter("id"))) {
            ModeloEmpresa modeloEmpresa = new ModeloEmpresa();
            modeloEmpresa.setId(Integer.parseInt(request.getParameter("id")));
            modeloEmpresa.setEstado("N");
            resultado = Update(modeloEmpresa);
        }
        return resultado;
    }

    /**
     * Elimina los datos en la base de datos de la tabla: empresa
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public String DeleteModelo(ModeloEmpresa modeloEmpresa) throws SQLException {
        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("DELETE FROM empresa "
                        + " WHERE id = ? ");
                SQL.setInt(1, modeloEmpresa.getId());
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Delete en Controladorempresa" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Delete en Controladorempresa" + e);
            resultado = "-3";
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
    public String Read(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        String estado = "S";
        if (request.getParameter("estado") != null) {
            estado = "N";
        }
        try {
            LinkedList<ModeloEmpresa> listmodelo;
            listmodelo = Read(estado);
            response.setContentType("text/html;charset=UTF-8");
            String parametro = request.getParameter("evento");
            if ("Select".equals(parametro)) {
                out = "";
                out += "<option value=\"0\" selected>Seleccione</option>";
                for (ModeloEmpresa modeloEmpresa : listmodelo) {
                    out += "<option value=\"" + modeloEmpresa.getId() + "\"> " + modeloEmpresa.getNombre()+ "</option>";
                }
            } else {
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
                for (ModeloEmpresa modelo : listmodelo) {
                    out += "<tr>";
                    out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getNit() + "</td>";
                    out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getNombre() + "</td>";
                    out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getDireccion() + "</td>";
                    out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getContacto() + "</td>";
                    out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getTelefono() + "</td>";
                    out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getExt() + "</td>";
                    out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getEmail() + "</td>";
                    out += "<td WIDTH = \"10\" HEIGHT=\"0\" class=\"text-center\">";
                    // Boton Editar
                    out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                    out += "data-id=\"" + modelo.getId() + "\"";
                    out += "data-nit=\"" + modelo.getNit() + "\"";
                    out += "data-nombre=\"" + modelo.getNombre() + "\"";
                    out += "data-direccion=\"" + modelo.getDireccion() + "\"";
                    out += "data-contacto=\"" + modelo.getContacto() + "\"";
                    out += "data-telefono=\"" + modelo.getTelefono() + "\"";
                    out += "data-extension=\"" + modelo.getExt() + "\"";
                    out += "data-email=\"" + modelo.getEmail() + "\"";
                    out += "data-observacion=\"" + modelo.getObservacion() + "\"";
                    out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i></button>";
                    //Boton Eliminar                
                    out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                    out += "data-id=\"" + modelo.getId() + "\"";
                    out += "data-nit=\"" + modelo.getNit() + "\"";
                    out += "data-nombre=\"" + modelo.getNombre() + "\"";
                    out += "data-direccion=\"" + modelo.getDireccion() + "\"";
                    out += "data-contacto=\"" + modelo.getContacto() + "\"";
                    out += "data-telefono=\"" + modelo.getTelefono() + "\"";
                    out += "data-extension=\"" + modelo.getExt() + "\"";
                    out += "data-email=\"" + modelo.getEmail() + "\"";
                    out += "data-observacion=\"" + modelo.getObservacion() + "\"";
                    out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i></button>";
                    out += "</td>";
                    out += "</tr>";
                }
                out += "</tbody>";
            }
        } catch (Exception e) {

            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }

    /**
     * Retorna un modelo de la tabla empresa dependiendo de un ID
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param request
     * @return String
     * @version: 15/05/2020
     */
    public ModeloEmpresa getModelo(Integer Id) {
        ModeloEmpresa modeloEmpresa = new ModeloEmpresa();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "nombre, "
                    + "nit, "
                    + "direccion, "
                    + "contacto, "
                    + "email, "
                    + "telefono, "
                    + "ext, "
                    + "ciudad, "
                    + "observacion, "
                    + "estado"
                    + " FROM empresa"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                res.first();
                modeloEmpresa.setId(res.getInt("id"));
                modeloEmpresa.setNombre(res.getString("nombre"));
                modeloEmpresa.setNit(res.getString("nit"));
                modeloEmpresa.setDireccion(res.getString("direccion"));
                modeloEmpresa.setContacto(res.getString("contacto"));
                modeloEmpresa.setEmail(res.getString("email"));
                modeloEmpresa.setTelefono(res.getString("telefono"));
                modeloEmpresa.setExt(res.getString("ext"));
                modeloEmpresa.setCiudad(res.getString("ciudad"));
                modeloEmpresa.setObservacion(res.getString("observacion"));
                modeloEmpresa.setEstado(res.getString("estado"));
            } else {
                modeloEmpresa.setId(0);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorempresa" + e);
        }
        return modeloEmpresa;
    }

}
