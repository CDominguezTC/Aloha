/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloUsuarios;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julian A Aristizabal
 */
public class ControladorPermisos {
    
    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
            
    public String Read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        String out = null;
        try {
            
            ControladorUsuarios controladorU = new ControladorUsuarios();
            LinkedList<ModeloUsuarios> listmoUsr;            
            listmoUsr = controladorU.Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<option value=\"\" disabled selected>Seleccione</option>";
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
    
    public String ReadPU(HttpServletRequest request, HttpServletResponse response, String usr) throws ServletException, IOException {
                
        String out = null;
        try {
            
            ControladorUsuarios controladorU = new ControladorUsuarios();
            LinkedList<ModeloUsuarios> listmoUsr;            
            listmoUsr = controladorU.Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<option value=\"\" disabled selected>Seleccione</option>";
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
}
