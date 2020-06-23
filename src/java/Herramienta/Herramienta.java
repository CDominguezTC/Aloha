/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramienta;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Carlos A Dominguez D
 */
public class Herramienta {

    public Date getDate() {
        Date utilDate = new Date(); //fecha actual
        long lnMilisegundos = utilDate.getTime();
        Date sqlDate = new Date(lnMilisegundos);
        return sqlDate;
    }

    public String GetDescrpCode(String cod) {
        String resp = "";//"Error";
        switch (cod) {
            case "0":
                resp = "Falla general del sistema contacte su proveedor";
                break;
            case "1":
                resp = "Registro Guardado";
                break;
            case "2":
                resp = "Registro Eliminado";
                break;
            case "3":
                resp = "Error,  La persona no puede autorizar esa cantidad de consumos";
                break;
            case "-1":
                resp = "El registro ya existe";
                break;
            case "-2":
                resp = "Error al guardar el registro";
                break;
            case "-3":
                resp = "Error en el evento";
                break;
            case "-4":
                resp = "Error al eliminar el registro";
                break;
            case "99":
                resp = "Empy";
                break;
            case "true":
                resp = "true";
                break;
        }
        return resp;
    }

    public Timestamp getDateTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    /**
     * Permite la conversion de un String a Util.Date
     *
     * @autor Carlos A Dominguez diaz
     * @version 15/05/2020
     * @param FechaString
     * @return
     */
    public Date getStringDate(String FechaString) {
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(FechaString);
        } catch (ParseException ex) {
            Logger.getLogger(Herramienta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    /**
     * Permite la conversion de un Util.Date a un Sql.Date
     *
     * @param fecha
     * @autor Carlos A Dominguez diaz
     * @version 15/05/2020
     * @return
     */
    public java.sql.Date getUtilDate_SqlDate(Date fecha) {
        java.sql.Date sDate = new java.sql.Date(fecha.getTime());
        return sDate;
    }

    /**
     * Validamos si el string esta null
     *
     * @param string
     * @return
     */
    public String validaString(String string) {
        if (string == null) {
            string = "0";
        }
        return string;
    }

    /**
     * Permite definir el dia que paso el evento
     *
     * @autor Carlos A Dominguez diaz
     * @version 23/05/2020
     * @param fecha
     * @return
     */
    public String getNombreDia(String fecha) {
        Date FechaEvento = getStringDate(fecha);
        String diaMarcacion = null;
        if (FechaEvento != null) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(FechaEvento);
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

    /**
     * Permite obtener la fecha del sistema en el momento de la consulta
     *
     * @return
     */
    public String getDateToday() {
        String fecha;
        Calendar c = Calendar.getInstance();
        fecha = Integer.toString(c.get(Calendar.YEAR)) + "-" + Integer.toString(c.get(Calendar.MONTH) + 1) + "-" + Integer.toString(c.get(Calendar.DATE));
        return fecha;
    }

    public String getDateString(Date dia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCalendarString(Calendar fechacalendar) {
        String fechastring = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (fechacalendar != null) {
            fechastring = sdf.format(fechacalendar.getTime());
        }
        return fechastring;
    }

    public String getNumColor(String tipo) {
        int numero = 0;
        String res = null;
        Set<Integer> alreadyUsedNumbers = new HashSet<>();
        while (alreadyUsedNumbers.size() < 3) {
            numero = (int) (Math.random() * 255 + 1);
            if (!alreadyUsedNumbers.contains(numero)) {
                if (alreadyUsedNumbers.isEmpty()) {
                    res = "rgba(" + String.valueOf(numero);
                } else {
                    res = res + "," + String.valueOf(numero);
                }
                alreadyUsedNumbers.add(numero);
            }
        }
        res = res + ","+tipo+")";
        return res;
    }
    /**
     * Permite convertir un String en fecha (Date).
     * @param fecha Cadena de fecha dd/MM/yyyy
     * @return Objeto Date
     * @autor Diego Fernando GUzman
     * @version 02/06/2020
     */
    public static Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
}
