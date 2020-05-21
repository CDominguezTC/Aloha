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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            System.out.println(date);
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

}
