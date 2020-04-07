/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Julian A Aristizabal
 */
public class ControladorInicioSesion {
    
    final String secretKey = "Tecno.4458714c";
    
    public String autenticacion (HttpServletRequest request){
        
        String resultado = "";
        PreparedStatement SQL = null;
        ResultSet rs = null;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();        
        con = conexionBdMysql.abrirConexion();
        
        String usuario = request.getParameter("user");
        String pw = request.getParameter("pass");
        
        try {
            String consulta = "SELECT * FROM usuarios WHERE login = ? AND password = ?";            
            SQL = con.prepareStatement(consulta);
            
            SQL.setString(1, usuario);            
            String clave = encriptar(pw);
            //System.out.println(clave);
            SQL.setString(2, clave);
            
            rs = SQL.executeQuery();
            
            //if(rs.next()){                
            if(rs.absolute(1)){
                resultado = "true";
            }else{
                resultado = "-2";
            }
            
            rs.close();
            SQL.close();
            con.close();
            
        } catch (Exception e) {
            
            System.err.println("Controladores.ControladorInicioSesion.autenticacion(): " + e.getMessage());
        }
        
        //return false;
        return resultado;
    }
    
    public String encriptar(String texto){
            
        String base64EncryptedString = "";

        try
        {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex)
        {
        }
        return base64EncryptedString;
    }

    public String desencriptar(String textoEncriptado) throws Exception {
        
        String base64EncryptedString = "";

        try
        {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex)
        {
        }
        return base64EncryptedString;
    }
    
}
