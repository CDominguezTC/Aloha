/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Julian A Aristizabal
 */
public class ControladorAuditoria {
    
    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    
    public String Insert(String operacion, String tabla, String usua, int idmodi, String observacion ){
        
        Tools tl = new Tools ();
        ModeloUsuarios modU = new ModeloUsuarios();
        
        String fecha = tl.formatoFechaHora();
        ControladorUsuarios controladorU = new ControladorUsuarios();
        int idusua = controladorU.idUsuario(usua);
        
        modU = controladorU.getModelos(idusua);
        if(idmodi == 3001){
            idmodi = idusua;
        }
        //int idmodi = ;
        /*if ("".equals(request.getParameter("id"))){
        
        }*/
        ModeloAuditoria modelo = new ModeloAuditoria(
                0,
                operacion,
                tabla,
                fecha,
                modU,
                idmodi,
                observacion
        );
        try{

            con = conexion.abrirConexion();
            try{

                SQL = con.prepareStatement("INSERT INTO `auditoria`("
                        + "`operacion`,"
                        + "`tabla`,"
                        + "`fecha`,"
                        + "`id_usuario`,"
                        + "`registro_modificado`,"
                        + "`observacion`) "
                        + "VALUES (?,?,?,?,?,?);");
                SQL.setString(1, modelo.getOperacion());
                SQL.setString(2, modelo.getTabla());
                SQL.setString(3, modelo.getFecha());
                SQL.setInt(4, modelo.getUsuario().getId());
                SQL.setInt(5, modelo.getRegistro_modificado());
                SQL.setString(6, modelo.getObservacion());
                //String pw = tl.encriptar(modelo.getPassword());
                //SQL.setString(3, pw);
                if (SQL.executeUpdate() > 0){
                    
                    resultado = "1";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e)
            {
                System.err.println("Error en el proceso: " + e.getMessage());
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e)
        {
            System.err.println("Error en el proceso: " + e.getMessage());
            resultado = "-3";
        }
        
        
        return resultado;
    }
    
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        String out = null;
        try {
            
            ControladorUsuarios controladorU = new ControladorUsuarios();
            LinkedList<ModeloUsuarios> listmoUsr;            
            listmoUsr = controladorU.Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<option value=\"\" disabled selected>Seleccione</option>";
            out += "<option value=\"todos\">Todos</option>";
            for (ModeloUsuarios modeloUsua : listmoUsr){
            
                //out += "<tr>";
                //<option value="1">JULIAN A. ARISTIZABAL</option>
                out += "<option value=\"" + modeloUsua.getId() + "\"> "+ modeloUsua.getNombre() + "</option>";

            }
            //out += "</select>";

        } catch (Exception e){
        
            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return out;
    }
    
    public LinkedList<ModeloAuditoria> Read(){
            
        LinkedList<ModeloAuditoria> modeloAud = new LinkedList<ModeloAuditoria>();
        ModeloUsuarios modU = new ModeloUsuarios();
        ControladorUsuarios controladorU = new ControladorUsuarios();
        con = conexion.abrirConexion();
        Date date = null;
        Timestamp timestamp = null;
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
                        
            SQL = con.prepareStatement("SELECT id, operacion, tabla, fecha, id_usuario, registro_modificado, observacion FROM auditoria");

            ResultSet res = SQL.executeQuery();
            while (res.next()) {
            
                ModeloAuditoria modeloA = new ModeloAuditoria();
                modeloA.setId(res.getInt("id"));
                modeloA.setOperacion(res.getString("operacion"));
                modeloA.setTabla(res.getString("tabla"));
                timestamp = res.getTimestamp(4);
                if (timestamp != null){
                    date = new java.util.Date(timestamp.getTime());                                        
                    //System.out.println("Fecha: " + dt1.format(date));
                    modeloA.setFecha(dt1.format(date));
                }                                    
                //modeloA.setFecha(res.getString("fecha"));
                modU = controladorU.getModelos(res.getInt("id_usuario"));
                modeloA.setUsuario(modU);
                modeloA.setRegistro_modificado(res.getInt("registro_modificado"));
                modeloA.setObservacion(res.getString("observacion"));             
                modeloAud.add(modeloA);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e){
            
            System.err.println("Error buscando el dato solicitado: " + e.getSQLState());
            //JOptionPane.showMessageDialog(null, "Error buscando el dato solicitado: " + e.getSQLState());
        }
        return modeloAud;
    }
    
    public String readTabla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        String out = null;
        try {
            
            LinkedList<ModeloAuditoria> listmoAu;            
            listmoAu = Read();
            response.setContentType("text/html;charset=UTF-8");

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
            for (ModeloAuditoria modeloA : listmoAu){
            
                out += "<tr>";
                out += "<td>" + modeloA.getOperacion()+ "</td>";
                out += "<td>" + modeloA.getTabla()+ "</td>";
                out += "<td>" + modeloA.getFecha()+ "</td>";
                out += "<td>" + modeloA.getUsuario().getNombre()+ "</td>";
                out += "<td>" + modeloA.getRegistro_modificado()+ "</td>";
                out += "<td>" + modeloA.getObservacion()+ "</td>";
                /*out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modeloUsua.getId() + "\"";                
                out += "data-nombre=\"" + modeloUsua.getNombre() + "\"";
                out += "data-login=\"" + modeloUsua.getLogin()+ "\"";
                out += "data-password=\"" + modeloUsua.getPassword() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloUsua.getId() + "\"";
                out += "data-nombre=\"" + modeloUsua.getNombre() + "\"";
                out += "data-login=\"" + modeloUsua.getLogin()+ "\"";
                out += "data-password=\"" + modeloUsua.getPassword() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> </button>";
                out += "</td>";*/
                out += "</tr>";
            }
            out += "</tbody>";

        } catch (Exception e){
        
            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return out;
    }
}
