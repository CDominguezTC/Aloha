/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Julian A Aristizabal
 */
public class ControladorAuditoria {
    
    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    
    
}
