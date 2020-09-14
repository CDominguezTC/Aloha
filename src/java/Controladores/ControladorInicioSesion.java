package Controladores;

import Conexiones.ConexionBdMysql;
import Tools.Tools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Esta clase permite controlar la autenticacion de los usarios en el sistema de
 * control Aloha
 *
 * @author Julian A Aristizabal
 * @version: 07/05/2020
 */
public class ControladorInicioSesion {
    static String user_act = "";
    HttpServletRequest request2;
    /**
     * Permite Permite la autenticacion del usuario
     *
     * @author Julian A Aristizabal
     * @param request
     * @return String
     * @version: 07/05/2020
     */
    public String autenticacion(HttpServletRequest request) {

        String resultado = "";
        PreparedStatement SQL = null;
        ResultSet rs = null;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        Tools tl = new Tools();
        String usuario = request.getParameter("user");
        String pw = request.getParameter("pass");
        try {
            String consulta = "SELECT id FROM usuario WHERE login = ? AND password = ?";
            SQL = con.prepareStatement(consulta);
            SQL.setString(1, usuario);
            String clave = tl.encriptar(pw);
            SQL.setString(2, clave);
            rs = SQL.executeQuery();
            if (rs.absolute(1)) {
                resultado = "true";
                request2 = request;
                user_act = usuario;
            } else {
                resultado = "-2";
            }
            rs.close();
            SQL.close();
            con.close();

        } catch (Exception e) {
            System.err.println("Controladores.ControladorInicioSesion.autenticacion(): " + e.getMessage());
        }
        return resultado;
    }
    
    public String usuarioActivo(){
        
        HttpSession misession= (HttpSession) request2.getSession();
        String user_ac = "";
        
        user_ac = misession.getAttribute("usuario").toString();
        
        return user_ac;
    }

}
