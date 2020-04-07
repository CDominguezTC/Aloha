/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

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

/**
 *
 * @author Carlos A Dominguez D
 */
public class Tools
{

    public String SimpleDateFormatTime (Time horaInicio)
    {
        SimpleDateFormat format = new SimpleDateFormat ("HH:mm:ss");
        return format.format (horaInicio);
    }

    public String formatFechaIniConsulta (Date FachaInicial)
    {
        String resulFormat = null;

        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
        resulFormat = (format.format (FachaInicial) + " 00:00:00");

        return resulFormat;
    }

    public String formatFechaFinConsulta (Date FechaFinal)
    {
        String resulFormat = null;
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
        resulFormat = (format.format (FechaFinal) + " 23:59:59");
        return resulFormat;
    }

    public String formaHoraMarcaciones (Time time)
    {
        String resulFormat = null;
        SimpleDateFormat format = new SimpleDateFormat ("HH:mm:ss");
        resulFormat = (format.format (time));
        return resulFormat;
    }

    public String formaFechaHoraMarcaciones (Date fecha_marcacion, String hora_marcacion)
    {
        String resulFormat = null;
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
        resulFormat = (format.format (fecha_marcacion)) + " " + hora_marcacion;
        return resulFormat;
    }

    public int getHoraEntero (String horaInicio)
    {
        int hora = 0, minuto = 0, segundo, res;
        if (horaInicio != null)
        {
            StringTokenizer stk = new StringTokenizer (horaInicio, ":");
            int split = stk.countTokens ();
            if (split == 3)
            {
                while (stk.hasMoreElements ())
                {
                    hora = Integer.parseInt (stk.nextToken ()) * 60;
                    minuto = Integer.parseInt (stk.nextToken ());
                    segundo = Integer.parseInt (stk.nextToken ());
                }
            }
            else
            {
                while (stk.hasMoreElements ())
                {
                    hora = Integer.parseInt (stk.nextToken ()) * 60;
                    minuto = Integer.parseInt (stk.nextToken ());
                }
            }
            res = hora + minuto;
        }
        else
        {
            res = 0;
        }
        return res;
    }

    public String formatHoraIntString (float DiferenciaHoras)
    {
        String hora = "00:", minuto = "00", segundo = "00", res = "00:00:00";

        DiferenciaHoras = DiferenciaHoras / 60;
        double rr = (Math.round (DiferenciaHoras * 100.0) / 100.0);
        //DiferenciaHoras = (float) (Math.round(DiferenciaHoras * 100.0)/100.0);
        res = String.valueOf (rr);
        StringTokenizer stk = new StringTokenizer (res, ".");
        int numTokens = stk.countTokens ();
        if (numTokens >= 1)
        {
            while (stk.hasMoreElements ())
            {
                hora = String.format ("%02d", Integer.parseInt (stk.nextToken ()));
                double minu = (Double.parseDouble (stk.nextToken ()) * 60) / 100;
                minu = (Math.round (minu * 1d) / 1d);
                int ddd = (int) minu;
                if (Integer.toString (ddd).length () == 1)
                {
                    minuto = String.format ("%02d", (ddd * 10));
                }
                else
                {
                    minuto = String.format ("%02d", (ddd));
                }
                segundo = "00";
            }
        }
        else
        {
            hora = stk.nextToken ();
        }
        res = hora + ":" + minuto + ":" + segundo;
        return res;
    }

    public String getdiaMarcacion (Date fechaInicio)
    {
        String diaMarcacion = null;
        if (fechaInicio != null)
        {
            GregorianCalendar cal = new GregorianCalendar ();
            cal.setTime (fechaInicio);
            int numdia = cal.get (Calendar.DAY_OF_WEEK);
            switch (numdia)
            {
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

    public Date getHoraDate (String horaInicio)
    {
        Date retunDate = null;
        if (horaInicio != null)
        {
            try
            {
                DateFormat dateFormat = new SimpleDateFormat ("HH:mm");
                retunDate = dateFormat.parse (horaInicio);
            } catch (ParseException ex)
            {
                Logger.getLogger (Tools.class.getName ()).log (Level.SEVERE, null, ex);
            }
        }
        return retunDate;
    }

    public Date getRestaMinutos (Date valorTurno, int valor)
    {
        Date result;
        Calendar calendar = Calendar.getInstance ();
        calendar.setTime (valorTurno);
        calendar.set (Calendar.MINUTE, calendar.get (Calendar.MINUTE) - valor);
        result = calendar.getTime ();
        return result;

    }

    public Date getSumaMinutos (Date valorTurno, int valor)
    {
        Date result;
        Calendar calendar = Calendar.getInstance ();
        calendar.setTime (valorTurno);
        calendar.set (Calendar.MINUTE, calendar.get (Calendar.MINUTE) + valor);
        result = calendar.getTime ();
        return result;

    }

    public String getDate ()
    {
        Date objDate = new Date (); // Sistema actual La fecha y la hora se asignan a objDate 

        System.out.println (objDate);
        String strDateFormat = "yyyy-MM-dd"; // El formato de fecha está especificado  
        SimpleDateFormat objSDF = new SimpleDateFormat (strDateFormat);
        String fecha = objSDF.format (objDate);
//        String fecha = null;
//        Calendar calendar = Calendar.getInstance ();
//        fecha = Integer.toString (calendar.get (Calendar.YEAR)) +"-"+ Integer.toString (calendar.get (Calendar.MONTH) + 1)+"-"+Integer.toString (calendar.get (Calendar.DATE));
        return fecha;
    }

    public Date ParseFecha (String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat ("yyyy-MM-dd");
        Date fechaDate = null;
        try
        {
            fechaDate = formato.parse (fecha);
        } catch (ParseException ex)
        {
            System.out.println (ex);
        }
        return fechaDate;
    }
}
