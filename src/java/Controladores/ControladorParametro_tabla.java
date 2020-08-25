/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Herramienta.Herramienta;
import Modelo.ModeloEnumeracion;
import Modelo.ModeloParametro_tabla;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorParametro_tabla {

    String resultado = "";
    Connection con;
    //PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;
    ControladorEnumeracion controladorEnumeracion = new ControladorEnumeracion();
    Herramienta herramienta = new Herramienta();
    HttpServletRequest requestGeneral;
    HttpServletResponse responseGeneral;

    public String SendRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        user = (String) session.getAttribute("usuario");
        return user;
    }
    
    public void EnviarConexion(Connection connection)
    {
        controladorEnumeracion.EnviarConexion(connection);
        con = connection;        
    }

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * parametro_tabla
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 10/06/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        
        con = conexion.abrirConexion();
        EnviarConexion(con);
        
        ModeloParametro_tabla modeloParametro_tabla = new ModeloParametro_tabla();
        String[] Filas = request.getParameter("datas").split(";");
        for (String fila : Filas) {
            String[] Campos = fila.split(",");
            if (Campos.length > 0) {
                modeloParametro_tabla.setModelo_tabla(controladorEnumeracion.getModelo(Integer.parseInt(Campos[1])));
                modeloParametro_tabla.setModelo_modulo(controladorEnumeracion.getModelo(Integer.parseInt(Campos[2])));
                modeloParametro_tabla.setNombre_campo_bd(Campos[3]);
                modeloParametro_tabla.setNombre_visible(Campos[4]);
                modeloParametro_tabla.setTipo_campo(Campos[5]);
                modeloParametro_tabla.setTabla_referencia(Campos[6]);;
                modeloParametro_tabla.setVisualizar(Campos[7]);
                modeloParametro_tabla.setHabilitar(Campos[8]);
                modeloParametro_tabla.setObligatorio(Campos[9]);
                modeloParametro_tabla.setLista(Campos[10]);
                modeloParametro_tabla.setEstado("S");
                if ("".equals(Campos[0])) {
                    HttpSession session = request.getSession();
                    user = (String) session.getAttribute("usuario");
                    resultado = Insert(modeloParametro_tabla);
                } else {
                    modeloParametro_tabla.setId(Integer.parseInt(Campos[0]));
                    resultado = Update(modeloParametro_tabla);
                }
            }
        }
        con.close();
        
        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: parametro_tabla
     *
     * @author: Diego Fernando Guzman
     * @param Modelo
     * @return String
     * @version: 10/06/2020
     */
    public String Insert(ModeloParametro_tabla modeloParametro_tabla) throws SQLException {
        try {
            //con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement("INSERT INTO parametro_tabla("
                        + "id_tabla, "
                        + "id_modulo, "
                        + "nombre_campo_bd, "
                        + "nombre_visible, "
                        + "tipo_campo, "
                        + "tabla_referencia, "
                        + "visualizar, "
                        + "habilitar, "
                        + "obligatorio, "
                        + "lista, "
                        + "estado)"
                        + " VALUE (?,?,?,?,?,?,?,?,?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setInt(1, modeloParametro_tabla.getModelo_tabla().getId());
                SQL.setInt(2, modeloParametro_tabla.getModelo_modulo().getId());
                SQL.setString(3, modeloParametro_tabla.getNombre_campo_bd());
                SQL.setString(4, modeloParametro_tabla.getNombre_visible());
                SQL.setString(5, modeloParametro_tabla.getTipo_campo());
                SQL.setString(6, modeloParametro_tabla.getTabla_referencia());
                SQL.setString(7, modeloParametro_tabla.getVisualizar());
                SQL.setString(8, modeloParametro_tabla.getHabilitar());
                SQL.setString(9, modeloParametro_tabla.getObligatorio());
                SQL.setString(10, modeloParametro_tabla.getLista());
                SQL.setString(11, modeloParametro_tabla.getEstado());
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.");
                        }
                        resultado = "1";
                        SQL.close();
                        //con.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert en Controladorparametro_tabla" + e);
                resultado = "-2";
                SQL.close();
                //con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert en Controladorparametro_tabla" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * Actualiza los datos en la base de datos de la tabla:parametro_tabla
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 10/06/2020
     */
    public String Update(ModeloParametro_tabla modeloParametro_tabla) throws SQLException {
        try {
            //con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                if ("N".equals(modeloParametro_tabla.getEstado())) {
                    SQL = con.prepareStatement("UPDATE parametro_tabla SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloParametro_tabla.getEstado());
                    SQL.setInt(2, modeloParametro_tabla.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE parametro_tabla SET "
                            + "id_tabla = ?, "
                            + "id_modulo = ?, "
                            + "nombre_campo_bd = ?, "
                            + "nombre_visible = ?, "
                            + "tipo_campo = ?, "
                            + "tabla_referencia = ?, "
                            + "visualizar = ?, "
                            + "habilitar = ?, "
                            + "obligatorio = ?, "
                            + "lista = ?, "
                            + "estado = ? "
                            + " WHERE id = ? ");
                    SQL.setInt(1, modeloParametro_tabla.getModelo_tabla().getId());
                    SQL.setInt(2, modeloParametro_tabla.getModelo_modulo().getId());
                    SQL.setString(3, modeloParametro_tabla.getNombre_campo_bd());
                    SQL.setString(4, modeloParametro_tabla.getNombre_visible());
                    SQL.setString(5, modeloParametro_tabla.getTipo_campo());
                    SQL.setString(6, modeloParametro_tabla.getTabla_referencia());
                    SQL.setString(7, modeloParametro_tabla.getVisualizar());
                    SQL.setString(8, modeloParametro_tabla.getHabilitar());
                    SQL.setString(9, modeloParametro_tabla.getObligatorio());
                    SQL.setString(10, modeloParametro_tabla.getLista());
                    SQL.setString(11, modeloParametro_tabla.getEstado());
                    SQL.setInt(12, modeloParametro_tabla.getId());
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "1";
                    SQL.close();
                    //con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Update en Controladorparametro_tabla" + e);
                resultado = "-2";
                SQL.close();
                //con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update en Controladorparametro_tabla" + e);
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * llena un modelo que viene con datos de un request para ser Eliminado
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 10/06/2020
     */
    public String Delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        
        con = conexion.abrirConexion();
        EnviarConexion(con);
        
        if (!"".equals(request.getParameter("id"))) {
            ModeloParametro_tabla modeloParametro_tabla = new ModeloParametro_tabla();
            modeloParametro_tabla.setId(Integer.parseInt(request.getParameter("id")));
            modeloParametro_tabla.setEstado("N");
            resultado = Update(modeloParametro_tabla);
        }
        
        con.close();
        return resultado;
    }

    /**
     * Retorna un modelo de la tabla parametro_tabla dependiendo de un ID
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 10/06/2020
     */
    public ModeloParametro_tabla getModelo(Integer Id) {
        ModeloParametro_tabla modeloParametro_tabla = new ModeloParametro_tabla();
        if(con==null)
        {
            con = conexion.abrirConexion();
        }                
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "id_tabla, "
                    + "id_modulo, "
                    + "nombre_campo_bd, "
                    + "nombre_visible, "
                    + "tipo_campo, "
                    + "tabla_referencia, "
                    + "visualizar, "
                    + "habilitar, "
                    + "obligatorio, "
                    + "lista, "
                    + "estado"
                    + " FROM parametro_tabla"
                    + " WHERE id = ? ");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloParametro_tabla.setId(res.getInt("id"));
                modeloParametro_tabla.setModelo_tabla(controladorEnumeracion.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_tabla")))));
                modeloParametro_tabla.setModelo_modulo(controladorEnumeracion.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_modulo")))));
                modeloParametro_tabla.setNombre_campo_bd(res.getString("nombre_campo_bd"));
                modeloParametro_tabla.setNombre_visible(res.getString("nombre_visible"));
                modeloParametro_tabla.setTipo_campo(res.getString("tipo_campo"));
                modeloParametro_tabla.setTabla_referencia(res.getString("tabla_referencia"));
                modeloParametro_tabla.setVisualizar(res.getString("visualizar"));
                modeloParametro_tabla.setHabilitar(res.getString("habilitar"));
                modeloParametro_tabla.setObligatorio(res.getString("obligatorio"));
                modeloParametro_tabla.setLista(res.getString("lista"));
                modeloParametro_tabla.setEstado(res.getString("estado"));
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorparametro_tabla" + e);
        }
        return modeloParametro_tabla;
    }

    /**
     * Llena un Listado de la tabla parametro_tabla
     *
     * @author: Diego Fernando Guzman
     * @param vacio
     * @return LinkedList<ModeloParametro_tabla>
     * @version: 10/06/2020
     */
    public LinkedList<ModeloParametro_tabla> Read(String estado, String tabla, String modulo) throws SQLException {
        LinkedList<ModeloParametro_tabla> ListaModeloParametro_tabla = new LinkedList<ModeloParametro_tabla>();
        //con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("SELECT id,"
                    + "id_tabla, "
                    + "id_modulo, "
                    + "nombre_campo_bd, "
                    + "nombre_visible, "
                    + "tipo_campo, "
                    + "tabla_referencia, "                    
                    + "visualizar, "
                    + "habilitar, "
                    + "obligatorio, "
                    + "lista, "
                    + "estado"
                    + " FROM parametro_tabla"
                    + " WHERE estado = ?"
                    + " and id_tabla = ? "
                    + " and id_modulo = ?");
            SQL.setString(1, estado);
            SQL.setString(2, tabla);
            SQL.setString(3, modulo);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloParametro_tabla modeloParametro_tabla = new ModeloParametro_tabla();
                modeloParametro_tabla.setId(res.getInt("id"));
                modeloParametro_tabla.setModelo_tabla(controladorEnumeracion.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_tabla")))));
                modeloParametro_tabla.setModelo_modulo(controladorEnumeracion.getModelo(Integer.parseInt(herramienta.validaString(res.getString("id_modulo")))));
                modeloParametro_tabla.setNombre_campo_bd(res.getString("nombre_campo_bd"));
                modeloParametro_tabla.setNombre_visible(res.getString("nombre_visible"));
                modeloParametro_tabla.setTipo_campo(res.getString("tipo_campo"));
                modeloParametro_tabla.setTabla_referencia(res.getString("tabla_referencia"));
                modeloParametro_tabla.setVisualizar(res.getString("visualizar"));
                modeloParametro_tabla.setHabilitar(res.getString("habilitar"));
                modeloParametro_tabla.setObligatorio(res.getString("obligatorio"));
                modeloParametro_tabla.setLista(res.getString("lista"));
                modeloParametro_tabla.setEstado(res.getString("estado"));
                ListaModeloParametro_tabla.add(modeloParametro_tabla);
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorparametro_tabla" + e);
        }
        return ListaModeloParametro_tabla;
    }

    /**
     * Llena un Listado de la tabla parametro_tabla en una cadena con
     * caracteristicas HTML
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 10/06/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        con = conexion.abrirConexion();
        EnviarConexion(con);

        String id_enumeracion = request.getParameter("id_campo");
        String parametro = request.getParameter("evento");

        requestGeneral = request;
        responseGeneral = response;

        SendRequest(request);

        String out = null;
        String estado = "S";
        String id_tabla = request.getParameter("id_tabla");
        String nombre_tabla = request.getParameter("nombre_tabla").replace(" ", "");
        String id_modulo = request.getParameter("id_modulo");
        if (request.getParameter("estado") != null) {
            estado = "N";
        }
        try {

            Consultar(nombre_tabla, id_tabla, id_modulo);

            LinkedList<ModeloParametro_tabla> ListaModeloParametro_tabla = Read(estado, id_tabla, id_modulo);

            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Id</th>";
            out += "<th>Campo DB</th>";
            out += "<th>Nombre Visible</th>";
            out += "<th>Tipo</th>";
            out += "<th>Referencia</th>";
            out += "<th>Visualizar</th>";
            out += "<th>Habilitar</th>";
            out += "<th>Obligatorio</th>";
            out += "<th>Lista</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloParametro_tabla modeloParametro_tabla : ListaModeloParametro_tabla) {
                out += "<tr>";
                out += "<td>" + modeloParametro_tabla.getId() + "</td>";
                out += "<td>" + modeloParametro_tabla.getNombre_campo_bd() + "</td>";
                out += "<td>" + "<input type=\"text\" name=\"nombre_campo\" value=\"" + modeloParametro_tabla.getNombre_visible() + "\"> " + "</td>";
                out += "<td>" + "<input type=\"text\" name=\"tipo_campo\" value=\"" + modeloParametro_tabla.getTipo_campo() + "\"> " + "</td>";
                out += "<td>" + "<input type=\"text\" name=\"referencia\" value=\"" + modeloParametro_tabla.getTabla_referencia() + "\"> " + "</td>";
                out += ValidarCheck(modeloParametro_tabla.getVisualizar(), "Visualizar_" + modeloParametro_tabla.getId());
                out += ValidarCheck(modeloParametro_tabla.getHabilitar(), "Habilitar_" + modeloParametro_tabla.getId());
                out += ValidarCheck(modeloParametro_tabla.getObligatorio(), "Obligatorio_" + modeloParametro_tabla.getId());
                out += LlenarLista(modeloParametro_tabla.getLista(), "Lista_" + modeloParametro_tabla.getId());
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
        } catch (Exception e) {
            System.out.println("Error en la generacion Html en Controladorparametro_tabla" + e);
        }
        
        con.close();
        return out;
    }

    public String LlenarLista(String DatoLista, String Nombre) throws SQLException, ServletException, IOException {

        String ListaObtenida = controladorEnumeracion.Read(requestGeneral, responseGeneral);

        String Lista = "";
        Lista = "<td> ";
        Lista += "<select id=\"" + Nombre + "\" class=\"form-control\" required>";
        Lista += "<option value=\"0\">Seleccione</option>";

        Lista += ListaObtenida;
        if (DatoLista != null) {
            DatoLista = controladorEnumeracion.getModelo(Integer.valueOf(DatoLista)).getCampo();
            if (DatoLista != null) {
                Lista += "<option selected=\"true\" disabled=\"disabled\">" + DatoLista + "</option>";
            }
        }

        int value = 1;
//        for (ModeloEnumeracion modeloEnumeracion : ListaModeloEnumeracion) {
//            Lista += "<option value=\"" + value + "\">" + modeloEnumeracion.getCampo() + "</option>";
//            value++;
//        }

        Lista += "</select></td>";

        return Lista;
    }

    public String ValidarCheck(String Check, String Nombre) {

        if (Check.contentEquals("Si")) {
            Check = "<td class=\"text-center\"><input type=\"checkbox\" id=\"" + Nombre + "\" name=\"" + Nombre + "\" class=\"flat\" checked/></td>";
        } else {
            Check = "<td class=\"text-center\"><input type=\"checkbox\" id=\"" + Nombre + "\" name=\"" + Nombre + "\" class=\"flat\"/></td>";
        }

        return Check;
    }

    public void Consultar(String nombre_tabla, String id_tabla, String id_modulo) {
        String tabla_referencia = "";
        //con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {

            SQL = con.prepareStatement("describe " + nombre_tabla);

            ResultSet res = SQL.executeQuery();

            while (res.next()) {
                
                tabla_referencia = "";

                String nombre_campo = res.getString("Field");
                String tipo_campo = res.getString("Type");

                if (tipo_campo.contains("varchar") || tipo_campo.contains("text")) {
                    tipo_campo = "String";
                } else if (tipo_campo.contains("int")) {
                    tipo_campo = "Integer";
                } else if (tipo_campo.contains("date")) {
                    tipo_campo = "Date";
                }
                if (nombre_campo.startsWith("id_")) {
                    tipo_campo = "Modelo";
                    tabla_referencia = ConsultarReferencia(nombre_campo,nombre_tabla);
                }
                validar_Crear(nombre_campo, tipo_campo, id_tabla, id_modulo, tabla_referencia);

            }

            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorparametro_tabla" + e);
        }

    }
    
    public String ConsultarReferencia(String nombre_campo, String nombre_tabla)
    {
        String nombre_referencia = "";
        //con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            
            String sql = "SELECT "
                    + "TABLE_NAME, "
                    + "COLUMN_NAME, "
                    + "CONSTRAINT_NAME, "
                    + "REFERENCED_TABLE_NAME, "
                    + "REFERENCED_COLUMN_NAME "
                    + "FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE "
                    + "WHERE "
                    + "COLUMN_NAME = '" + nombre_campo + "' "
                    + "AND "
                    + "TABLE_NAME = '" + nombre_tabla + "'";

            SQL = con.prepareStatement(sql);

            ResultSet res = SQL.executeQuery();

            while (res.next()) {
                nombre_referencia = res.getString("REFERENCED_TABLE_NAME");
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorparametro_tabla" + e);
        }
        return nombre_referencia;
    }

    public void validar_Crear(String nombre_campo, String tipo_campo, String id_tabla, String id_modulo, String tabla_referencia) {
        //con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {

            SQL = con.prepareStatement("SELECT * FROM parametro_tabla "
                    + "WHERE"
                    + " nombre_campo_bd = '" + nombre_campo + "'"
                    + " and id_tabla = " + id_tabla
                    + " and id_modulo = " + id_modulo);
            ResultSet res1 = SQL.executeQuery();

            ModeloParametro_tabla modeloParametro_tabla = null;
            while (res1.next()) {
                modeloParametro_tabla = new ModeloParametro_tabla();
                modeloParametro_tabla.setId(res1.getInt("id"));
                modeloParametro_tabla.setModelo_tabla(controladorEnumeracion.getModelo(Integer.parseInt(id_tabla)));
                modeloParametro_tabla.setModelo_modulo(controladorEnumeracion.getModelo(Integer.parseInt(id_modulo)));
                modeloParametro_tabla.setNombre_campo_bd(res1.getString("nombre_campo_bd"));
                modeloParametro_tabla.setNombre_visible(res1.getString("nombre_visible"));
                modeloParametro_tabla.setTipo_campo(res1.getString("tipo_campo"));
                modeloParametro_tabla.setTabla_referencia(res1.getString("tabla_referencia"));
                modeloParametro_tabla.setVisualizar(res1.getString("visualizar"));
                modeloParametro_tabla.setHabilitar(res1.getString("habilitar"));
                modeloParametro_tabla.setObligatorio(res1.getString("obligatorio"));
                modeloParametro_tabla.setLista(res1.getString("lista"));
                modeloParametro_tabla.setEstado(res1.getString("estado"));
            }
            if (modeloParametro_tabla == null) {
                modeloParametro_tabla = new ModeloParametro_tabla();
                modeloParametro_tabla.setModelo_tabla(controladorEnumeracion.getModelo(Integer.parseInt(id_tabla)));
                modeloParametro_tabla.setModelo_modulo(controladorEnumeracion.getModelo(Integer.parseInt(id_modulo)));
                modeloParametro_tabla.setNombre_campo_bd(nombre_campo);
                modeloParametro_tabla.setNombre_visible(nombre_campo);
                modeloParametro_tabla.setTipo_campo(tipo_campo);
                modeloParametro_tabla.setTabla_referencia(tabla_referencia);
                modeloParametro_tabla.setVisualizar("Si");
                modeloParametro_tabla.setHabilitar("Si");
                modeloParametro_tabla.setObligatorio("No");
//                    modeloParametro_tabla.setLista(res.getString("lista"));
                modeloParametro_tabla.setEstado("S");
                Insert(modeloParametro_tabla);
            }

            res1.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorparametro_tabla" + e);
        }

    }

}
