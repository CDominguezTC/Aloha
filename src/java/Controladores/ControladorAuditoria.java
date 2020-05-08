package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloAuditoria;
import Modelo.ModeloUsuarios;
import Tools.Tools;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Esta clase permite controlar los eventos de Auditoria
 *
 * @author Julian A Aristizabal
 * @version: 07/05/2020
 *
 */
public class ControladorAuditoria
{

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql ();

    /**
     * Permite la inserción de los datos en la tabla Bd Auditoria
     *
     * @author Julian A Aristizabal
     * @param operacion 
     * @param tabla 
     * @param usua 
     * @param idmodi 
     * @param observacion 
     * @return String
     * @version: 07/05/2020
     */
    public String Insert (String operacion, String tabla, String usua, int idmodi, String observacion)
    {

        Tools tl = new Tools ();
        ModeloUsuarios modU = new ModeloUsuarios ();

        String fecha = tl.formatoFechaHora ();
        ControladorUsuarios controladorU = new ControladorUsuarios ();
        int idusua = controladorU.idUsuario (usua);

        modU = controladorU.getModelos (idusua);
        if (idmodi == 3001)
        {
            idmodi = idusua;
        }
        ModeloAuditoria modelo = new ModeloAuditoria (
                0,
                operacion,
                tabla,
                fecha,
                modU,
                idmodi,
                observacion
        );
        try
        {
            con = conexion.abrirConexion ();
            try
            {

                SQL = con.prepareStatement ("INSERT INTO `auditoria`("
                        + "`operacion`,"
                        + "`tabla`,"
                        + "`fecha`,"
                        + "`id_usuario`,"
                        + "`registro_modificado`,"
                        + "`observacion`) "
                        + "VALUES (?,?,?,?,?,?);");
                SQL.setString (1, modelo.getOperacion ());
                SQL.setString (2, modelo.getTabla ());
                SQL.setString (3, modelo.getFecha ());
                SQL.setInt (4, modelo.getUsuario ().getId ());
                SQL.setInt (5, modelo.getRegistro_modificado ());
                SQL.setString (6, modelo.getObservacion ());
                //String pw = tl.encriptar(modelo.getPassword());
                //SQL.setString(3, pw);
                if (SQL.executeUpdate () > 0)
                {

                    resultado = "1";
                    SQL.close ();
                    con.close ();
                }
            } catch (SQLException e)
            {
                System.err.println ("Error en el proceso: " + e.getMessage ());
                resultado = "-2";
                SQL.close ();
                con.close ();
            }
        } catch (SQLException e)
        {
            System.err.println ("Error en el proceso: " + e.getMessage ());
            resultado = "-3";
        }
        return resultado;
    }

    /**
     * La lista es para llenar el modelo con los datos de acuerdo al tipo de consulta, el String es el que pinta el select y las opciones
     *
     * @author Julian A Aristizabal
     * @param request 
     * @param response  
     * @return String
     * @version: 07/05/2020
     */
    public String Read (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String out = null;
        try
        {
            ControladorUsuarios controladorU = new ControladorUsuarios ();
            LinkedList<ModeloUsuarios> listmoUsr;
            listmoUsr = controladorU.Read ();
            response.setContentType ("text/html;charset=UTF-8");
            out = "";
            out += "<option value=\"\" disabled selected>Seleccione</option>";
            out += "<option value=\"todos\">Todos</option>";
            for (ModeloUsuarios modeloUsua : listmoUsr)
            {
                out += "<option value=\"" + modeloUsua.getId () + "\"> " + modeloUsua.getNombre () + "</option>";
            }
        } catch (Exception e)
        {
            System.err.println ("Error en el proceso de la tabla: " + e.getMessage ());
        }
        return out;
    }

    /**
     * Permite listar la información de la tabla de Auditoria  Metodo Private
     *
     * @author Julian A Aristizabal
     * @param  usr 
     * @param  fini  
     * @param  ffin  
     * @return LinkedList
     * @version: 07/05/2020
     */
    private LinkedList<ModeloAuditoria> Read (String usr, String fini, String ffin)
    {

        LinkedList<ModeloAuditoria> modeloAud = new LinkedList<ModeloAuditoria> ();
        ModeloUsuarios modU = new ModeloUsuarios ();
        ControladorUsuarios controladorU = new ControladorUsuarios ();
        con = conexion.abrirConexion ();
        Date date = null;
        Timestamp timestamp = null;
        SimpleDateFormat dt1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        try
        {
            if ("".equals (usr))
            {
                SQL = con.prepareStatement ("SELECT id, operacion, tabla, fecha, id_usuario, registro_modificado, observacion FROM auditoria");

            }
            else
            {
                if ("todos".equals (usr))
                {
                    SQL = con.prepareStatement ("SELECT id, operacion, tabla, fecha, id_usuario, registro_modificado, observacion FROM auditoria WHERE fecha BETWEEN '" + fini + " 00:00:00' AND '" + ffin + " 23:59:59'");
                }
                else
                {
                    SQL = con.prepareStatement ("SELECT id, operacion, tabla, fecha, id_usuario, registro_modificado, observacion FROM auditoria WHERE id_usuario = " + usr + " AND fecha BETWEEN '" + fini + " 00:00:00' AND '" + ffin + " 23:59:59'");
                }
            }

            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {

                ModeloAuditoria modeloA = new ModeloAuditoria ();
                modeloA.setId (res.getInt ("id"));
                modeloA.setOperacion (res.getString ("operacion"));
                modeloA.setTabla (res.getString ("tabla"));
                timestamp = res.getTimestamp (4);
                if (timestamp != null)
                {
                    date = new java.util.Date (timestamp.getTime ());                 
                    modeloA.setFecha (dt1.format (date));
                }
                modU = controladorU.getModelos (res.getInt ("id_usuario"));
                modeloA.setUsuario (modU);
                modeloA.setRegistro_modificado (res.getInt ("registro_modificado"));
                modeloA.setObservacion (res.getString ("observacion"));
                modeloAud.add (modeloA);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.err.println ("Error buscando el dato solicitado: " + e.getSQLState ());
        }
        return modeloAud;
    }

    /**
     * Permite listar la información de la tabla de Auditoria
     *
     * @author Julian A Aristizabal
     * @param request 
     * @param response 
     * @param usr
     * @param ffin 
     * @param fini 
     * @return String
     * @version: 07/05/2020
     */
    public String readTabla (HttpServletRequest request, HttpServletResponse response, String usr, String fini, String ffin) throws ServletException, IOException
    {

        String out = null;
        try
        {

            LinkedList<ModeloAuditoria> listmoAu;
            listmoAu = Read (usr, fini, ffin);
            response.setContentType ("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Operacion</th>";
            out += "<th>Tabla</th>";
            out += "<th>Fecha</th>";
            out += "<th>Usuario</th>";
            out += "<th>Registro Modificado</th>";
            out += "<th>Observacion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloAuditoria modeloA : listmoAu)
            {
                out += "<tr>";
                out += "<td>" + modeloA.getOperacion () + "</td>";
                out += "<td>" + modeloA.getTabla () + "</td>";
                out += "<td>" + modeloA.getFecha () + "</td>";
                out += "<td>" + modeloA.getUsuario ().getNombre () + "</td>";
                out += "<td>" + modeloA.getRegistro_modificado () + "</td>";
                out += "<td>" + modeloA.getObservacion () + "</td>";
                out += "</tr>";
            }
            out += "</tbody>";

        } catch (Exception e)
        {

            System.err.println ("Error en el proceso de la tabla: " + e.getMessage ());
        }
        return out;
    }
}
