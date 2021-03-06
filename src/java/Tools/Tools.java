/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Conexiones.ConexionBdMysql;
import Controladores.ControladorUsuario;
import Modelo.ModeloUsuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Carlos A Dominguez D
 */
public class Tools {

    final String secretKey = "Tecno.4458714c";

    public String SimpleDateFormatTime(Time horaInicio) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(horaInicio);
    }

    public String formatFechaIniConsulta(Date FachaInicial) {
        String resulFormat = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        resulFormat = (format.format(FachaInicial) + " 00:00:00");

        return resulFormat;
    }

    public String formatFechaFinConsulta(Date FechaFinal) {
        String resulFormat = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        resulFormat = (format.format(FechaFinal) + " 23:59:59");
        return resulFormat;
    }

    public String formaHoraMarcaciones(Time time) {
        String resulFormat = null;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        resulFormat = (format.format(time));
        return resulFormat;
    }

    public String formaFechaHoraMarcaciones(Date fecha_marcacion, String hora_marcacion) {
        String resulFormat = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        resulFormat = (format.format(fecha_marcacion)) + " " + hora_marcacion;
        return resulFormat;
    }

    public int getHoraEntero(String horaInicio) {
        int hora = 0, minuto = 0, segundo, res;
        if (horaInicio != null) {
            StringTokenizer stk = new StringTokenizer(horaInicio, ":");
            int split = stk.countTokens();
            if (split == 3) {
                while (stk.hasMoreElements()) {
                    hora = Integer.parseInt(stk.nextToken()) * 60;
                    minuto = Integer.parseInt(stk.nextToken());
                    segundo = Integer.parseInt(stk.nextToken());
                }
            } else {
                while (stk.hasMoreElements()) {
                    hora = Integer.parseInt(stk.nextToken()) * 60;
                    minuto = Integer.parseInt(stk.nextToken());
                }
            }
            res = hora + minuto;
        } else {
            res = 0;
        }
        return res;
    }

    public String formatHoraIntString(float DiferenciaHoras) {
        String hora = "00:", minuto = "00", segundo = "00", res = "00:00:00";

        DiferenciaHoras = DiferenciaHoras / 60;
        double rr = (Math.round(DiferenciaHoras * 100.0) / 100.0);
        //DiferenciaHoras = (float) (Math.round(DiferenciaHoras * 100.0)/100.0);
        res = String.valueOf(rr);
        StringTokenizer stk = new StringTokenizer(res, ".");
        int numTokens = stk.countTokens();
        if (numTokens >= 1) {
            while (stk.hasMoreElements()) {
                hora = String.format("%02d", Integer.parseInt(stk.nextToken()));
                double minu = (Double.parseDouble(stk.nextToken()) * 60) / 100;
                minu = (Math.round(minu * 1d) / 1d);
                int ddd = (int) minu;
                if (Integer.toString(ddd).length() == 1) {
                    minuto = String.format("%02d", (ddd * 10));
                } else {
                    minuto = String.format("%02d", (ddd));
                }
                segundo = "00";
            }
        } else {
            hora = stk.nextToken();
        }
        res = hora + ":" + minuto + ":" + segundo;
        return res;
    }

    public String getdiaMarcacion(Date fechaInicio) {
        String diaMarcacion = null;
        if (fechaInicio != null) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(fechaInicio);
            int numdia = cal.get(Calendar.DAY_OF_WEEK);
            switch (numdia) {
                case 1:
                    diaMarcacion = "Domingo";
                    break;
                case 2:
                    diaMarcacion = "Lunes";
                    break;
                case 3:
                    diaMarcacion = "Martes";
                    break;
                case 4:
                    diaMarcacion = "Miercoles";
                    break;
                case 5:
                    diaMarcacion = "Jueves";
                    break;
                case 6:
                    diaMarcacion = "Viernes";
                    break;
                case 7:
                    diaMarcacion = "Sabado";
                    break;
            }
        }
        return diaMarcacion;
    }

    public Date getHoraDate(String horaInicio) {
        Date retunDate = null;
        if (horaInicio != null) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                retunDate = dateFormat.parse(horaInicio);
            } catch (ParseException ex) {
                Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retunDate;
    }

    public Date getRestaMinutos(Date valorTurno, int valor) {
        Date result;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(valorTurno);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - valor);
        result = calendar.getTime();
        return result;

    }

    public Date getSumaMinutos(Date valorTurno, int valor) {
        Date result;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(valorTurno);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + valor);
        result = calendar.getTime();
        return result;

    }

    public String getDate() {
        Date objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate 

        System.out.println(objDate);
        String strDateFormat = "yyyy-MM-dd"; // El formato de fecha está especificado  
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String fecha = objSDF.format(objDate);
//        String fecha = null;
//        Calendar calendar = Calendar.getInstance ();
//        fecha = Integer.toString (calendar.get (Calendar.YEAR)) +"-"+ Integer.toString (calendar.get (Calendar.MONTH) + 1)+"-"+Integer.toString (calendar.get (Calendar.DATE));
        return fecha;
    }

    public Date ParseFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return fechaDate;
    }

    public String validoItem(String user, String modulo) {

        PreparedStatement SQL = null;
        ResultSet rs = null;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();

        try {
            String consulta = "SELECT pe.nombre "
                    + "FROM permiso pe "
                    + "INNER JOIN permiso_x_rol r ON pe.id = r.id_permiso "
                    + "INNER JOIN rol rl ON rl.id = r.id_rol "
                    + "INNER JOIN usuario us ON rl.id = us.id_rol "
                    + "WHERE us.login = ? AND pe.nombre = ?";
            SQL = con.prepareStatement(consulta);

            SQL.setString(1, user);
            SQL.setString(2, modulo);

            rs = SQL.executeQuery();

            if (rs.absolute(1)) {
                return "true";
            }
            rs = SQL.executeQuery();
        } catch (Exception e) {
            System.out.println("Tools.Tools.validoItem() " +e.getMessage() );
        }

        return "false";
    }

    public String encriptar(String texto) {

        String base64EncryptedString = "";

        try {

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

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public String desencriptar(String textoEncriptado) throws Exception {

        String base64EncryptedString = "";

        try {

            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public String editarUserAct(HttpServletRequest request, HttpServletResponse response, String user) throws ServletException, IOException {

        String out = null;
        try {

            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<img id=\"idimg\" src=\"Principal/images/user.png\" alt=\"\">" + user + " ";
            out += "<span class=\"fa fa-angle-double-down\"></span>";

        } catch (Exception e) {

            System.err.println("Error en el proceso de la tabla: " + e.getMessage());
        }

        return out;
    }

    public String formatoFechaHora() {

        String fechahora = "";

        String fec;

        SimpleDateFormat forfecha = new SimpleDateFormat("yyyy-MM-dd");
        fec = forfecha.format(new Date());

        String hor;

        SimpleDateFormat forhora = new SimpleDateFormat("HH:mm:ss");
        hor = forhora.format(new Date());

        fechahora = fec + " " + hor;

        return fechahora;
    }
    
    public String modoMarcaciones(){
        String path = System.getProperty("java.class.path");
        
        String ruta = path.substring(0, path.indexOf("\\m")).replace("\\", "//");
        String modo = "";
        
        try {
            File file = new File(ruta + "//Configuracion_Aloha.txt");
            BufferedReader filein = null;
            if (file != null) {
                filein = new BufferedReader(new FileReader(file));
                String Linea;
                while (filein.ready()) {
                    Linea = filein.readLine();                          
                    StringTokenizer stk = new StringTokenizer(Linea, "=");
                    
                    while (stk.hasMoreTokens()) {
                        //String asig = stk.nextToken();
                        modo = stk.nextToken();                        
                    }                    
                }
            }else{
                modo = "error";
                System.out.println("No existe el archivo de configuracion de sentidos.");
            }
        } catch (Exception e) {
            modo = "error";
            System.out.println("Error ruta modo asignacion marcacion: " + e.getMessage());
        }
        
        return modo;                        
    }
}
