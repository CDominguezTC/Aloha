/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloPersona;
import Modelo.ModeloGrupoTurnos;
import Modelo.ModeloGrupoTurnos_Turnos;
import Modelo.ModeloLiquidacion;
import Modelo.ModeloTurnos;
import Tools.Tools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorLiquidacion 
{
    Tools tools = new Tools(); 
    private long TotalHorasTeoricof;
    public List<ModeloLiquidacion> liquidacion(List<ModeloPersona> modeloEmpleados, Date FechaInicial, Date FechaFinal) 
    {
        //Cambio CADD 25-05-2018 Generamos el modelo de entradas y salidas
        List<ModeloLiquidacion> modeloLiquidaciones = new ArrayList<ModeloLiquidacion>();
        modeloLiquidaciones = getModeloLiquidacion(modeloEmpleados, FechaInicial, FechaFinal);    
        //Cambio CADD 13-11-2018 Asignamos los turnos a las marcacion de lo empleados
        modeloLiquidaciones = getHorario(modeloLiquidaciones);    
        //Cambio CADD 25-05-2018 Generamos la diferencia de horas entre entrada y salida
        modeloLiquidaciones = getDiferenciahoras(modeloLiquidaciones);
        //Cambio CADD 25-05-2018 Generamos las novedades
        modeloLiquidaciones = getNovedadesHorasDiurnasNocturnas(modeloLiquidaciones);
        //Cambio CADD 01-08-2018 Generamos Extras
        modeloLiquidaciones = getHorasExtras(modeloLiquidaciones);
        //Cambio CADD 03-09-2018 Llegada tarde 
        modeloLiquidaciones = getEntradaTardia(modeloLiquidaciones);
        //Cambio CADD 03-09-2018 Slida Temprana
        modeloLiquidaciones = getSalidaTemprana(modeloLiquidaciones);        

        return modeloLiquidaciones;
    }

    private List<ModeloLiquidacion> getDiferenciahoras(List<ModeloLiquidacion> modeloLiquidaciones)
    {                
        for (ModeloLiquidacion modeloLiquidacion1 : modeloLiquidaciones)
        {
            if((modeloLiquidacion1.getHoraInicio() != null)&& (modeloLiquidacion1.getHoraFin()!= null))
            {
                try 
                {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                
                    Date fechaInicial = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio() +" "+ modeloLiquidacion1.getHoraInicio());
                    Date fechaFinal = simpleDateFormat.parse(modeloLiquidacion1.getFechaFin()+" "+ modeloLiquidacion1.getHoraFin());
                    Map numehoras = NumeroHoras(fechaInicial,fechaFinal);
                    String Horas = String.format("%02d", Long.parseLong(numehoras.get("horas").toString()));
                    String Minutos = String.format("%02d", Long.parseLong(numehoras.get("minutos").toString()));
                    String Segundos = String.format("%02d", Long.parseLong(numehoras.get("segundos").toString()));
                    modeloLiquidacion1.setHoraDiferencia(Horas+":"+Minutos+":"+Segundos);
                }
                catch (ParseException ex)
                {
                    Logger.getLogger(ControladorLiquidacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                modeloLiquidacion1.setHoraDiferencia("00:00:00");
            }
        }
        return modeloLiquidaciones;        
    }

    private List<ModeloLiquidacion> getHorario(List<ModeloLiquidacion> modeloLiquidaciones) 
    {
        Tools tools = new Tools();        
        //List<ModeloLiquidacion> modeloLiquidacion = new ArrayList<ModeloLiquidacion>();                 
        
        List<ModeloGrupoTurnos_Turnos> modelogrupoTurnos_Turnos = new ArrayList<ModeloGrupoTurnos_Turnos>();        
        ControladorGrupoTurnos_Turnos controladorGrupoTurnos_Turnos = new ControladorGrupoTurnos_Turnos();
         
        ModeloTurnos modeloTurnos = new ModeloTurnos();
        ControladorTurnos controladorTurnos = new ControladorTurnos();
        
        for (ModeloLiquidacion modeloLiquidacion1 : modeloLiquidaciones)
        {
            if ((modeloLiquidacion1.getHoraInicio() != null)&&(modeloLiquidacion1.getHoraFin() != null))
            {
                Date HoraInicio = tools.getHoraDate(modeloLiquidacion1.getHoraInicio());
                //Date HoraFin = tools.getHoraDate(modeloLiquidacion1.getHoraFin());
                String diaMarcacion = tools.getdiaMarcacion(modeloLiquidacion1.getFechaInicio());
                //modelogrupoTurnos_Turnos = controladorGrupoTurnos_Turnos.SearchIdGrupoHorario(modeloLiquidacion1.getIdGrupoTurno(),diaMarcacion);
                for (ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos : modelogrupoTurnos_Turnos) 
                {
                    //modeloTurnos = controladorTurnos.SearchId(modeloGrupoTurnos_Turnos.getIdHorario());
                    Date horaFinTurno = null;
                    Date horaInicioTurno = tools.getHoraDate(modeloTurnos.getHora_inicio());

                    Date AntesEntrar;
                    Date DespuesEntrar;


                    Calendar calendarAntesEntrar = Calendar.getInstance();
                    calendarAntesEntrar.setTime(horaInicioTurno);
                    calendarAntesEntrar.add(calendarAntesEntrar.MINUTE,-Integer.parseInt(modeloTurnos.getTolerancia_antes_salir()));
                    AntesEntrar = calendarAntesEntrar.getTime();

                    Calendar calendarDespuesEntrar = Calendar.getInstance();
                    calendarDespuesEntrar.setTime(horaInicioTurno);
                    calendarDespuesEntrar.add(calendarDespuesEntrar.MINUTE,+Integer.parseInt(modeloTurnos.getTolerancia_despues_entrada()));
                    DespuesEntrar = calendarDespuesEntrar.getTime();

                    if ("S".equals(modeloTurnos.getTurno_noche()))
                    {
                        horaFinTurno = tools.getHoraDate("23:59");
                        if ((AntesEntrar.compareTo(HoraInicio) <= 0) && (DespuesEntrar.compareTo(HoraInicio) > 0))                                
                        {                        
                            modeloLiquidacion1.setModeloTurnos(modeloTurnos);
                            modeloLiquidacion1.setIdTurno(modeloTurnos.getId());
                            modeloLiquidacion1.setTunro(modeloTurnos.getDescripcion());
                        }
                    }
                    else
                    {
                        horaFinTurno = tools.getHoraDate(modeloTurnos.getHora_fin());
                        if ((AntesEntrar.compareTo(HoraInicio) <= 0) && (DespuesEntrar.compareTo(HoraInicio) > 0))                                
                        {
                            modeloLiquidacion1.setModeloTurnos(modeloTurnos);
                            modeloLiquidacion1.setIdTurno(modeloTurnos.getId());
                            modeloLiquidacion1.setTunro(modeloTurnos.getDescripcion());
                        }
                    }  
                    if(modeloLiquidacion1.getTunro() == null)
                    {
                        //modeloTurnos = controladorTurnos.SearchId(11);
                        //modeloTurnos = controladorTurnos.SearchName("DEFECTO");
                        modeloLiquidacion1.setModeloTurnos(modeloTurnos);
                        modeloLiquidacion1.setIdTurno(modeloTurnos.getId());
                        modeloLiquidacion1.setTunro(modeloTurnos.getDescripcion());
                    }
                }                
            }            
            else
            {
                if ((modeloLiquidacion1.getHoraInicio() == null))
                {
                    modeloLiquidacion1.setTunro("--?--");
                }
                if ((modeloLiquidacion1.getHoraFin() == null))
                {
                    modeloLiquidacion1.setTunro("--?--");
                }
                
            }
        }
        return modeloLiquidaciones;  
    }

    private List<ModeloLiquidacion> getModeloLiquidacion(List<ModeloPersona> modeloEmpleados, Date FechaInicial, Date FechaFinal) 
    {
        ModeloGrupoTurnos modeloGrupoTurnos = new ModeloGrupoTurnos();
        ControladorGrupoTurnos controladorGrupoTurnos = new ControladorGrupoTurnos();
        
//        ModeloTurnos modeloTurnos = new ModeloTurnos();
//        ControladorTurnos controladorTurnos = new ControladorTurnos();
                
        Tools tools = new Tools();
        PreparedStatement SQL = null;
        List<ModeloLiquidacion> modeloLiquidaciones = new ArrayList<ModeloLiquidacion>();
        Connection con;
        ResultSet res = null;
        ResultSet restmp = null;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            for (ModeloPersona modeloEmpleado : modeloEmpleados)
            {
                SQL = con.prepareStatement("SELECT id ,id_empleado, fecha_marcacion,estado_marcacion,nombre_dispositivo,observacion "
                        + "FROM marcacion WHERE id_empleado = ? AND fecha_marcacion >= ? AND fecha_marcacion <= ? AND estado_marcacion <> 'Invalido' ORDER BY fecha_marcacion;");
                SQL.setInt(1, modeloEmpleado.getId());            
                SQL.setString(2, tools.formatFechaIniConsulta(FechaInicial));
                SQL.setString(3, tools.formatFechaFinConsulta(FechaFinal));
                res = SQL.executeQuery();    
                String banEntrada = null;
                String banSalida = null;
                int conn = 0;
                int consalida = 0;
                while (res.next())
                {
                    conn++;                                             
                    ModeloLiquidacion modeloLiquidacion = new ModeloLiquidacion();
                    //Cambio para mejorar el orden de los registros de entrada y salidad                     
                    if("Entrada".equals(res.getString("Estado_marcacion")))
                    {
                        modeloLiquidacion.setIdEmpleado(modeloEmpleado.getId());
                        modeloLiquidacion.setCedula(modeloEmpleado.getIdentificacion());
                        modeloLiquidacion.setNombreEmpleado(modeloEmpleado.getNombres() +" " + modeloEmpleado.getApellidos());                                                
                        modeloLiquidacion.setIdGrupoTurno(modeloEmpleado.getId_Grupo_Horario());
                        //modeloGrupoTurnos = controladorGrupoTurnos.SearchId(modeloEmpleado.getId_Grupo_Horario());
                        modeloLiquidacion.setGrupoTurno(modeloGrupoTurnos.getDescripcion());
                        modeloLiquidacion.setFechaInicio(res.getDate("fecha_marcacion"));
                        modeloLiquidacion.setHoraInicio(tools.formaHoraMarcaciones(res.getTime("fecha_marcacion")));
                        modeloLiquidacion.setBandera(conn);
                        banEntrada = "S";
                        consalida = conn;  
                        modeloLiquidaciones.add(modeloLiquidacion);   
                        //modeloLiquidaciones = getHorario(modeloLiquidaciones);
                    }
                    if("Salida".equals(res.getString("Estado_marcacion")))
                    {
                        if ("S".equals(banEntrada))
                        {                            
                            for (ModeloLiquidacion modeloLiquidacion1 : modeloLiquidaciones)
                            {
                                if (modeloLiquidacion1.getBandera() == consalida)
                                {
//                                    int r = res.getInt("id_empleado");
//                                    System.out.print(r);
                                    if (modeloLiquidacion1.getIdEmpleado() == res.getInt("id_empleado"))
                                    {
                                        modeloLiquidacion1.setFechaFin(res.getDate("fecha_marcacion"));
                                        modeloLiquidacion1.setHoraFin(tools.formaHoraMarcaciones(res.getTime("fecha_marcacion")));    
                                        banEntrada = "N";
                                    }
                                    
                                }                                
                            }                            
                        }
                        else
                        {
                            //buscamos la marcacion de entrda antes de la fecha 
                            SQL = con.prepareStatement("SELECT id ,id_empleado, fecha_marcacion,estado_marcacion,nombre_dispositivo,observacion "
                                    + "FROM marcacion WHERE id_empleado = ? AND fecha_marcacion <= ? AND estado_marcacion <> 'Invalido' ORDER BY fecha_marcacion DESC LIMIT 1;");
                            SQL.setInt(1, modeloEmpleado.getId());            
                            SQL.setString(2, tools.formatFechaIniConsulta(res.getDate("fecha_marcacion")));                            
                            restmp = SQL.executeQuery();
                            while (restmp.next())
                            {
                                if ("Entrada".equals(restmp.getString("Estado_marcacion")))
                                {
                                    modeloLiquidacion.setCedula(modeloEmpleado.getIdentificacion());
                                    modeloLiquidacion.setNombreEmpleado(modeloEmpleado.getNombres() +" " + modeloEmpleado.getApellidos());                                                
                                    modeloLiquidacion.setIdGrupoTurno(modeloEmpleado.getId_Grupo_Horario());
                                    //modeloGrupoTurnos = controladorGrupoTurnos.SearchId(modeloEmpleado.getId_Grupo_Horario());
                                    modeloLiquidacion.setGrupoTurno(modeloGrupoTurnos.getDescripcion());
                                    modeloLiquidacion.setFechaInicio(restmp.getDate("fecha_marcacion"));
                                    modeloLiquidacion.setHoraInicio(tools.formaHoraMarcaciones(restmp.getTime("fecha_marcacion")));
                                    modeloLiquidacion.setBandera(conn);
                                    banEntrada = "S";
                                    consalida = conn;  
                                    
                                   
                                    modeloLiquidacion.setCedula(modeloEmpleado.getIdentificacion());
                                    modeloLiquidacion.setNombreEmpleado(modeloEmpleado.getNombres() +" " + modeloEmpleado.getApellidos());
                                    modeloLiquidacion.setFechaFin(res.getDate("fecha_marcacion"));
                                    modeloLiquidacion.setHoraFin(tools.formaHoraMarcaciones(res.getTime("fecha_marcacion")));                                                
                                    modeloLiquidacion.setBandera(conn);
                                    banSalida = "S";                            
                            
                                    modeloLiquidaciones.add(modeloLiquidacion);   
                                    //modeloLiquidaciones = getHorario(modeloLiquidaciones);                                    
                                }
                                else
                                {
                                    //**//
                                    modeloLiquidacion.setCedula(modeloEmpleado.getIdentificacion());
                                    modeloLiquidacion.setNombreEmpleado(modeloEmpleado.getNombres() +" " + modeloEmpleado.getApellidos());
                                    modeloLiquidacion.setFechaFin(res.getDate("fecha_marcacion"));
                                    modeloLiquidacion.setHoraFin(tools.formaHoraMarcaciones(res.getTime("fecha_marcacion")));                                                
                                    modeloLiquidacion.setBandera(conn);
                                    banSalida = "S";
                                    modeloLiquidaciones.add(modeloLiquidacion);                                    
                                }
                            }                            
                        }                            
                    }
                }
            }
            res.close();
            SQL.close();
            con.close();
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
        }                     
        return modeloLiquidaciones;
    }

    private List<ModeloLiquidacion> getNovedadesHorasDiurnasNocturnas(List<ModeloLiquidacion> modeloLiquidacion) 
    {
        //clase que nos permite la generacion de las novedades de horas diurnas o nocturnas
        try 
        {            
            for (ModeloLiquidacion modeloLiquidacion1 : modeloLiquidacion)
            {
                if((modeloLiquidacion1.getHoraInicio() != null)&& (modeloLiquidacion1.getHoraFin()!= null))
                {
                    Date HoraInicioDiurno;
                    Date HoraInicioNocturno;
                    Date HoraIncioMarcacion;
                    Date HoraFinMarcacion; 
                    Date HoraDescuento;
                    Date Comdin;
                    Long HNO1L = 00000000000L;
                    Long HNO2L = 00000000000L;
                    Long THLL = 00000000000L;
                    Long THNOL = 00000000000L;
                    Long THDOL = 00000000000L;
                    Long THD = 00000000000L;
                    String Horas;
                    String Minutos;
                    String Segundos;
                    //formateamos las fechas 
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (modeloLiquidacion1.getIdTurno() == 0)
                    {
                        //generamos la diferencias de horas con la entrada y la salida del empleado 
                        HoraIncioMarcacion = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getHoraInicio());
                        HoraFinMarcacion = simpleDateFormat.parse(modeloLiquidacion1.getFechaFin()+" "+modeloLiquidacion1.getHoraFin());
                        THLL = NumeroHorasLong(HoraIncioMarcacion,HoraFinMarcacion); 
                    }   
                    else
                    {
                        HoraIncioMarcacion = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" 00:00:00");
                        HoraFinMarcacion = simpleDateFormat.parse(modeloLiquidacion1.getFechaFin()+" 00:00:00");
                        if ((HoraFinMarcacion.getTime()) == (HoraIncioMarcacion.getTime())) 
                        {
                            //Validamos si tiene nocturnas de 00:00:00 a 06:00:00
                            HoraIncioMarcacion = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getHoraInicio());
                            HoraFinMarcacion = simpleDateFormat.parse(modeloLiquidacion1.getFechaFin()+" "+modeloLiquidacion1.getHoraFin());
                            HoraInicioDiurno =  simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_inicio_diurno()+":00");
                            HoraInicioNocturno = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_inicio_nocturno()+":00");
                            HoraDescuento = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getDescanso()+":00");
                            //generamos la diferencias de horas Nocturnas 
                            if ((HoraIncioMarcacion.getTime()) < (HoraInicioDiurno.getTime()))
                            {
                                if ((HoraFinMarcacion.getTime()) < (HoraIncioMarcacion.getTime()))
                                {
                                    HNO1L = NumeroHorasLong(HoraIncioMarcacion,HoraFinMarcacion);                        
                                }
                                else
                                {
                                    HNO1L = NumeroHorasLong(HoraIncioMarcacion,HoraInicioDiurno);                        
                                }                        
                            }
                            if (HoraInicioNocturno.getTime() < HoraFinMarcacion.getTime())
                            {
                                if (HoraInicioNocturno.getTime() < HoraIncioMarcacion.getTime())
                                {
                                    HNO2L = NumeroHorasLong(HoraIncioMarcacion,HoraFinMarcacion);
                                }
                                else
                                {
                                    HNO2L = NumeroHorasLong(HoraInicioNocturno,HoraFinMarcacion);
                                }
                            }     
                            //generamos la cantidad de horas laboradas 
                            THLL = NumeroHorasLong(HoraIncioMarcacion,HoraFinMarcacion); 
                            //obtenermos la cantidad de horas nocturnas
                            if (HNO1L != null)
                            {
                                THNOL = (HNO1L + HNO2L);
                            }
                            else
                            {
                                if (HNO2L != null)
                                {
                                    THNOL = (HNO1L + HNO2L);
                                }
                            }
                            // obtenermos la cantidad de horas a descontar por almuerzo
                            THD = NumeroHorasLong(HoraDescuento,simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" 00:00:00"));
                            if (THLL > THNOL)
                            {
                                THDOL = THLL - THNOL - THD; 
                            }
                            else
                            {
                                THDOL = THNOL - THLL - THD; 
                            }
                            Map THNO = NumeroHorasString(THNOL);
                            Horas = String.format("%02d", Long.parseLong(THNO.get("horas").toString()));
                            Minutos = String.format("%02d", Long.parseLong(THNO.get("minutos").toString()));
                            Segundos = String.format("%02d", Long.parseLong(THNO.get("segundos").toString()));
                            modeloLiquidacion1.setHoraNocturnas(Horas+":"+Minutos+":"+Segundos);

                            Map THDO = NumeroHorasString(THDOL);;
                            Horas = String.format("%02d", Long.parseLong(THDO.get("horas").toString()));
                            Minutos = String.format("%02d", Long.parseLong(THDO.get("minutos").toString()));
                            Segundos = String.format("%02d", Long.parseLong(THDO.get("segundos").toString()));
                            modeloLiquidacion1.setHoraDiurnas(Horas+":"+Minutos+":"+Segundos);
                            
                            Map THDE = NumeroHorasString(THD);;
                            Horas = String.format("%02d", Long.parseLong(THDE.get("horas").toString()));
                            Minutos = String.format("%02d", Long.parseLong(THDE.get("minutos").toString()));
                            Segundos = String.format("%02d", Long.parseLong(THDE.get("segundos").toString()));
                            modeloLiquidacion1.setHoraDescuento(Horas+":"+Minutos+":"+Segundos);
                        }
                        else
                        {
                            HoraIncioMarcacion = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getHoraInicio());
                            HoraFinMarcacion = simpleDateFormat.parse(modeloLiquidacion1.getFechaFin()+" "+modeloLiquidacion1.getHoraFin());

                            HoraInicioDiurno =  simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_inicio_diurno()+":00");
                            HoraInicioNocturno = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_inicio_nocturno()+":00"); 
                            if ((HoraIncioMarcacion.getTime()) < (HoraInicioDiurno.getTime()))
                            {
                                HNO1L = NumeroHorasLong(HoraIncioMarcacion,HoraInicioDiurno);                                             
                            }
                            if ((HoraIncioMarcacion.getTime()) > (HoraInicioNocturno.getTime()))
                            {
                                Comdin = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" 24:00:00");
                                HNO1L = NumeroHorasLong(HoraIncioMarcacion,Comdin);                                             
                            }
                            if ((HoraIncioMarcacion.getTime()) < (HoraInicioNocturno.getTime()))
                            {
                                Comdin = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" 24:00:00");                                             
                                HNO1L = NumeroHorasLong(HoraInicioNocturno,Comdin);                                             
                            }
                            HoraInicioDiurno =  simpleDateFormat.parse(modeloLiquidacion1.getFechaFin()+" "+modeloLiquidacion1.getModeloTurnos().getHora_inicio_diurno()+":00");
                            HoraInicioNocturno = simpleDateFormat.parse(modeloLiquidacion1.getFechaFin()+" "+modeloLiquidacion1.getModeloTurnos().getHora_inicio_nocturno()+":00"); 
                            if ((HoraFinMarcacion.getTime()) < (HoraInicioDiurno.getTime()))
                            {
                                Comdin = simpleDateFormat.parse(modeloLiquidacion1.getFechaFin()+" 00:00:00");
                                HNO2L = NumeroHorasLong(Comdin,HoraFinMarcacion);                                             
                            }
                            else
                            {
                                THDOL = NumeroHorasLong(HoraFinMarcacion, HoraInicioDiurno);
                                Comdin = simpleDateFormat.parse(modeloLiquidacion1.getFechaFin()+" 00:00:00");
                                HNO2L = NumeroHorasLong(Comdin, HoraInicioDiurno);
                            }
                            if ((HoraFinMarcacion.getTime()) > (HoraInicioNocturno.getTime()))
                            {
                                HNO1L = NumeroHorasLong(HoraIncioMarcacion,HoraInicioNocturno);                                             
                            }
                            THLL = NumeroHorasLong(HoraIncioMarcacion,HoraFinMarcacion); 
                            if (HNO1L != null)
                            {
                                THNOL = (HNO1L + HNO2L);
                            }
                            else
                            {
                                if (HNO2L != null)
                                {
                                    THNOL = (HNO1L + HNO2L);
                                }
                            }                    
                            if (THLL > THNOL)
                            {
                                THDOL = THLL - THNOL; 
                            }
                            else
                            {
                                THDOL = THNOL - THLL; 
                            }
                            Map THNO = NumeroHorasString(THNOL);
                            Horas = String.format("%02d", Long.parseLong(THNO.get("horas").toString()));
                            Minutos = String.format("%02d", Long.parseLong(THNO.get("minutos").toString()));
                            Segundos = String.format("%02d", Long.parseLong(THNO.get("segundos").toString()));
                            modeloLiquidacion1.setHoraNocturnas(Horas+":"+Minutos+":"+Segundos);

                            Map THDO = NumeroHorasString(THDOL);;
                            Horas = String.format("%02d", Long.parseLong(THDO.get("horas").toString()));
                            Minutos = String.format("%02d", Long.parseLong(THDO.get("minutos").toString()));
                            Segundos = String.format("%02d", Long.parseLong(THDO.get("segundos").toString()));
                            modeloLiquidacion1.setHoraDiurnas(Horas+":"+Minutos+":"+Segundos);                    
                        }
                    }
                }
                else
                {
                    modeloLiquidacion1.setHoraNocturnas("00:00:00");
                    modeloLiquidacion1.setHoraDiurnas("00:00:00");
                    modeloLiquidacion1.setHoraDescuento("00:00:00");
                }
             }
        }
        catch (ParseException e) 
        {
            System.out.println(e);
        }        
        return  modeloLiquidacion;
    }

    private Map NumeroHoras(Date fechaInicial, Date fechaFinal) 
    {
        Map resultadoMap = new HashMap();        
        Date fechaMayor = null;
        Date fechaMenor = null;       
        if (fechaInicial.compareTo(fechaFinal) > 0)
        {
            fechaMayor = fechaInicial;
            fechaMenor = fechaFinal;
        }
        else
        {
            fechaMayor = fechaFinal;
            fechaMenor = fechaInicial;
        }
        long diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();             
        long segundos = diferenciaMils / 1000;
        long horas = segundos / 3600;                
        segundos -= horas*3600;        
        long minutos = segundos /60;
        segundos -= minutos*60;                
        resultadoMap.put("horas",Long.toString(horas));
        resultadoMap.put("minutos",Long.toString(minutos));
        resultadoMap.put("segundos",Long.toString(segundos));        
        return resultadoMap;
    }
    
    
        
    private Long NumeroHorasLong(Date fechaInicial, Date fechaFinal) 
    {
        Long resultado;
        Date fechaMayor = null;
        Date fechaMenor = null;       
        if (fechaInicial.compareTo(fechaFinal) > 0)
        {
            fechaMayor = fechaInicial;
            fechaMenor = fechaFinal;
        }
        else
        {
            fechaMayor = fechaFinal;
            fechaMenor = fechaInicial;
        }
        resultado = fechaMayor.getTime() - fechaMenor.getTime();                     
        return resultado;
    }

    private Map NumeroHorasString(Long HolasLong)
    {
        Map resultadoMap = new HashMap();   
        long segundos = HolasLong / 1000;
        long horas = segundos / 3600;                
        segundos -= horas*3600;        
        long minutos = segundos /60;
        segundos -= minutos*60;                
        resultadoMap.put("horas",Long.toString(horas));
        resultadoMap.put("minutos",Long.toString(minutos));
        resultadoMap.put("segundos",Long.toString(segundos));
        return resultadoMap;
    }

    private List<ModeloLiquidacion> getHorasExtras(List<ModeloLiquidacion> modeloLiquidacion) 
    {
        /*
        Conceptos de horas Extras
        Horas Extras Diurnas
        Horas Extras Nocturnos
        Horas Extras Festivas Diurnas
        Horas Extras Festivas Nocturnos
        Horas Extras Domincales Diurnas
        Horas Extras Dominicales Nocturnos
        */
        Long HD = 00000000000L;    //Horas Diuranas
        Long HN = 00000000000L;    //Horas Nocturnas
        Long HED = 00000000000L;    //Horas Extras Diurnas
        Long HEN = 00000000000L;    //Horas Extras Nocturnas
        Long HEFD = 00000000000L;   //Horas Extras Festivas Diurnas
        Long HEFN = 00000000000L;   //Horas Extras Festivas Nocturnas
        Long HEDD = 00000000000L;   //Horas Extras Dominicales Diurnas
        Long HEDN = 00000000000L;   //Horas Extras Dominicales Nocturnas
        Long HET = 00000000000L;    //Horas Extras Dominicales Diurnas
        Long HST = 00000000000L;    //Horas Extras Dominicales Nocturnas
        Long THL = 00000000000L;    //Horas Extras Dominicales Nocturnas
        
        Date FechaRegistroEntrada;
        Date FechaRegistroSalida;
        Date FechaInicioTurno;
        Date FechaFinTurno;
        
        Date FechaTGAntesEntrarTurno;
        Date FechaTGDespuesSalirTurno;
        Date FechaTGDespuesEntrarTurno;
        Date FechaTGAntesSalirTurno;
        
        Date FechaInicioDiaNocturno;
        Date FechaFinNocturno;
        Date FechaInicioNocturno;        
        Date FechaFinDiaNocturno;
        
        for (ModeloLiquidacion modeloLiquidacion1 : modeloLiquidacion)
        {
            try
            {
                //Obtenemos los valores para los calvulos de las horas estos vienen del momdelo
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                
                FechaRegistroEntrada = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getHoraInicio());
                FechaRegistroSalida = simpleDateFormat.parse(modeloLiquidacion1.getFechaFin()+" "+modeloLiquidacion1.getHoraFin());
                FechaInicioTurno = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_inicio()+":00");
                FechaFinTurno = simpleDateFormat.parse(modeloLiquidacion1.getFechaFin()+" "+modeloLiquidacion1.getModeloTurnos().getHora_fin()+":00");
                                
                FechaTGAntesEntrarTurno = tools.getRestaMinutos(FechaInicioTurno, Integer.parseInt(modeloLiquidacion1.getModeloTurnos().getTiempo_maximo_entrada()));                        
                FechaTGDespuesEntrarTurno = tools.getSumaMinutos(FechaInicioTurno, Integer.parseInt(modeloLiquidacion1.getModeloTurnos().getTiempo_minimo_entrada()));
                FechaTGAntesSalirTurno = tools.getRestaMinutos(FechaFinTurno, Integer.parseInt(modeloLiquidacion1.getModeloTurnos().getTiempo_minimo_salida()));
                FechaTGDespuesSalirTurno = tools.getSumaMinutos(FechaFinTurno, Integer.parseInt(modeloLiquidacion1.getModeloTurnos().getTiempo_maximo_salida()));
                
                // obtenemos la cantidad de horas laboradas del empleado 
                
                THL = NumeroHorasLong(FechaRegistroEntrada, FechaRegistroSalida);
                
                //Validamos si el turno es de transnocho 
                if ("S".equals(modeloLiquidacion1.getModeloTurnos().getTurno_noche()))
                {
                    
                }
                else
                {
                    FechaInicioDiaNocturno = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" 00:00:00");
                    FechaFinNocturno = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_inicio_diurno()+":00");
                    FechaInicioNocturno = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_inicio_nocturno()+":00");
                    FechaFinDiaNocturno = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" 23:59:59");
                    
                    //Validamso las Extras nocturnas 
                    if (FechaRegistroEntrada.compareTo(FechaFinNocturno) == -1) 
                    {
                        HEN = HEN + NumeroHorasLong(FechaRegistroEntrada, FechaFinNocturno);
                    }
                    if (FechaRegistroSalida.compareTo(FechaInicioNocturno) == 1) 
                    {
                        HEN = HEN + NumeroHorasLong(FechaRegistroEntrada, FechaFinNocturno);
                    }                    
                    
                }
                
                //Validamos si el empleado llego antes del tiempo de gracia a la entradad 
                
                if(FechaRegistroEntrada.getTime() < FechaTGAntesEntrarTurno.getTime())
                {
                    HED = HED + NumeroHorasLong(FechaRegistroEntrada, FechaInicioTurno);
                }
                if(FechaRegistroEntrada.getTime() > FechaTGDespuesEntrarTurno.getTime())
                {
                    HET = HET + + NumeroHorasLong(FechaRegistroEntrada, FechaInicioTurno);
                }
                if(FechaRegistroSalida.getTime() < FechaTGAntesSalirTurno.getTime())
                {
                    HST = HST + + NumeroHorasLong(FechaRegistroSalida, FechaFinTurno);
                }
                if(FechaRegistroSalida.getTime() > FechaTGDespuesSalirTurno.getTime())
                {
                    HED = HED + NumeroHorasLong(FechaRegistroSalida, FechaFinTurno);                    
                }
                
                //Validamos el mayor valor valor y restamos 
                if (HED > HEN)
                {
                    
                }
                
                
                Map  MTHL = NumeroHorasString(THL);
                Map  MHED = NumeroHorasString(HED);
                Map  MHET = NumeroHorasString(HET);
                Map  MHST = NumeroHorasString(HST);
                Map  MHEN = NumeroHorasString(HEN);
                
                System.out.println("THL : " + MTHL);
                System.out.println("HED : " + MHED);
                System.out.println("HET : " + MHET);
                System.out.println("HST : " + MHST);
                System.out.println("HEN : " + MHEN);
                
                
            }
            catch (ParseException ex) 
            {
                System.out.println("Error" + ex);
                Logger.getLogger(ControladorLiquidacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
//        try 
//        {            
//            for (ModeloLiquidacion modeloLiquidacion1 : modeloLiquidacion)
//            {
//                if((modeloLiquidacion1.getHoraInicio() != null)&& (modeloLiquidacion1.getHoraFin()!= null))
//                {
//                    Date TotalHorasTeorico;
//                    Date TotalHorasMarcacion;
//                    Date TotalHorasDescuento;
//                    Date TotalHorasTmp;
//                    Long THE = 00000000000L;    //Total Horas Extras
//                    Long THD = 00000000000L;    //Total Horas Diurnas
//                    Long THR = 00000000000L;    //Total Horas Retardo
//                    String Horas;
//                    String Minutos;
//                    String Segundos;
//                    Map HE;
//                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//                    TotalHorasMarcacion = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getHoraDiferencia());
//                    TotalHorasTeorico = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getTeorico()+":00");
//                    TotalHorasDescuento = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getDescanso()+":00");
//                    TotalHorasTmp = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" 00:00:00");
//                    /*
//                    CADD 14-06-2019
//                    Modificacion carlos para calcular las horas a dicionales tomanando como parametros los factores de tiempos de gracias 
//                    del turnos.
//                    */
//                    Date HorasInicioJornada;
//                    Date HorasFinJornada;
//                    Date HorasEntradaTurno;
//                    Date HorasSalidaTurno;
//                    Date HorasTGAntesEntrarTurno;
//                    Date HorasTGDespuesSalirTurno;
//                    Date HorasTGDespuesEntrarTurno;
//                    Date HorasTGAntesSalirTurno;
//                    
//                    HorasInicioJornada = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getHoraInicio()+":00");
//                    HorasFinJornada = simpleDateFormat.parse(modeloLiquidacion1.getFechaFin()+" "+modeloLiquidacion1.getHoraFin()+":00");                    
//                    
//                    HorasEntradaTurno = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_inicio()+":00");
//                    HorasSalidaTurno = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_fin()+":00");
//                    
//                    //Tiempos de graciaa Antes de Entrar
//                    HorasTGAntesEntrarTurno = tools.getRestaMinutos(HorasEntradaTurno, Integer.parseInt(modeloLiquidacion1.getModeloTurnos().getTiempo_maximo_entrada()));
//                    
//                    //Tiempos de graciaa Despues de Entrar                    
//                    HorasTGDespuesEntrarTurno = tools.getSumaMinutos(HorasEntradaTurno, Integer.parseInt(modeloLiquidacion1.getModeloTurnos().getTiempo_minimo_entrada()));
//                    
//                    //Tiempos de graciaa Antes de salir
//                    HorasTGAntesSalirTurno = tools.getRestaMinutos(HorasSalidaTurno, Integer.parseInt(modeloLiquidacion1.getModeloTurnos().getTiempo_minimo_salida()));
//                                        
//                    //Tiempos de graciaa Despues de salir
//                    HorasTGDespuesSalirTurno = tools.getSumaMinutos(HorasSalidaTurno, Integer.parseInt(modeloLiquidacion1.getModeloTurnos().getTiempo_maximo_salida()));
//                    
//                    //Validamos si la hora de entrada es menor a la hora del tiempo de gracia entrada           
////                    if (HorasInicioJornada.getTime() < HorasTGAntesEntrarTurno.getTime())
////                    {   
////                        //Acumulamos en horas adicionales
////                        THE = THE + NumeroHorasLong(HorasInicioJornada, HorasEntradaTurno);                     
////                        Map  p1 = NumeroHorasString(THE);
////                    }
////                    
////                    //Validamos si la hora de entrada es mayor a la hora del tiempo de gracia entrada
////                    if (HorasInicioJornada.getTime() > HorasTGDespuesEntrarTurno.getTime())
////                    {   
////                        //Acumulamos en horas descuentos
////                        THR = THR - NumeroHorasLong(HorasInicioJornada, HorasTGDespuesEntrarTurno);   
////                    }
////                    
////                    //Validamos si la hora de Salidad es menor a la hora del tiempo de gracia salidad
////                    if (HorasFinJornada.getTime() < HorasTGAntesSalirTurno.getTime())
////                    {   
////                        //Acumulamos en horas tarde
////                        THR = THR - NumeroHorasLong(HorasFinJornada, HorasTGAntesSalirTurno);  
////                    }
////                    
////                    //Validamos si la hora de Salida es mayor a la hora del tiempo de gracia salidad
////                    if (HorasFinJornada.getTime() > HorasTGDespuesSalirTurno.getTime())
////                    {   
////                        //Acumulamos en horas adicionales
////                        THE = THE + NumeroHorasLong(HorasFinJornada, HorasTGDespuesSalirTurno);                     
////                        Map  p1 = NumeroHorasString(THE);
////                    }                    
//                    
//                    /*
//                    CADD 14-06-2019
//                    Modificacion carlos para calcular las horas a dicionales tomanando como parametros los factores de tiempos de gracias 
//                    del turnos.
//                    */                     
//                    
//                    
//                    //Tenemos en cuenta el teorico para la generacion de horas a dicionales
//                    if(TotalHorasTeorico.getTime() < TotalHorasMarcacion.getTime())
//                    {
//                        
//                        if (HorasInicioJornada.getTime() < HorasTGAntesEntrarTurno.getTime())
//                        {   
//                            //Acumulamos en horas adicionales
//                            THE = THE + NumeroHorasLong(HorasInicioJornada, HorasEntradaTurno);
//                        }
//
//                        //Validamos si la hora de entrada es mayor a la hora del tiempo de gracia entrada
//                        if (HorasInicioJornada.getTime() > HorasTGDespuesEntrarTurno.getTime())
//                        {   
//                            //Acumulamos en horas descuentos
//                            THD = THD - NumeroHorasLong(HorasInicioJornada, HorasTGDespuesEntrarTurno);   
//                        }
//
//                        //Validamos si la hora de Salidad es menor a la hora del tiempo de gracia salidad
//                        if (HorasFinJornada.getTime() < HorasTGAntesSalirTurno.getTime())
//                        {   
//                            //Acumulamos en horas tarde
//                            THD = THD - NumeroHorasLong(HorasFinJornada, HorasTGAntesSalirTurno);  
//                        }
//
//                        //Validamos si la hora de Salida es mayor a la hora del tiempo de gracia salidad
//                        if (HorasFinJornada.getTime() > HorasTGDespuesSalirTurno.getTime())
//                        {   
//                            //Acumulamos en horas adicionales
//                            THE = THE + NumeroHorasLong(HorasFinJornada, HorasSalidaTurno);
//                        }   
//                    
//                    
//                    
////                        THE = THE +NumeroHorasLong(TotalHorasTeorico, TotalHorasMarcacion);
////                        
////                        Map  p1 = NumeroHorasString(THE);
//                        
////                        THD = THD + NumeroHorasLong(TotalHorasDescuento, TotalHorasTmp);
////                        
////                        Map  p2 = NumeroHorasString(THD);
//                        /*Validamos los valores CADD Cambio 10-06-2019*/
//                        
//                        if ((THE - THD) < 0)
//                        {
//                            System.out.println("Negativo");
//                            HE = NumeroHorasString(THE - THE);
//                        }
//                        else
//                        {
//                            System.out.println("Positivo");
//                            HE = NumeroHorasString(THE - THD);
//                        }                       
//                        
//                        //Map  p3 = NumeroHorasString(HE);
//                        Horas = String.format("%02d", Long.parseLong(HE.get("horas").toString()));
//                        Minutos = String.format("%02d", Long.parseLong(HE.get("minutos").toString()));
//                        Segundos = String.format("%02d", Long.parseLong(HE.get("segundos").toString()));
//                        modeloLiquidacion1.setHoraExtras(Horas+":"+Minutos+":"+Segundos);
//                    }
//                    else
//                    {
//                        modeloLiquidacion1.setHoraExtras("00:00:00");
//                    }
//                }
//                else
//                {
//                    modeloLiquidacion1.setHoraExtras("00:00:00");
//                }
//            }   
//        }
//        catch(ParseException e)
//        {
//            System.out.println(e);
//        }        
        return modeloLiquidacion;
    }

    private List<ModeloLiquidacion> getEntradaTardia(List<ModeloLiquidacion> modeloLiquidacion) 
    {
        try 
        {            
            for (ModeloLiquidacion modeloLiquidacion1 : modeloLiquidacion)
            {
                if((modeloLiquidacion1.getHoraInicio() != null)&& (modeloLiquidacion1.getHoraFin()!= null))
                {
                    Date HoraInicioTurno;
                    Date HoraMarcacionEmpledo;
                    Long ET = 00000000000L;
                    String Horas;
                    String Minutos;
                    String Segundos;
                    Map ResulET;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    HoraInicioTurno = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_inicio()+":00");
                    HoraMarcacionEmpledo = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getHoraInicio());

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(HoraInicioTurno);
                    calendar.add(calendar.MINUTE,-Integer.parseInt(modeloLiquidacion1.getModeloTurnos().getTolerancia_despues_entrada()));
                    HoraInicioTurno = calendar.getTime();


                    if(HoraMarcacionEmpledo.getTime() > HoraInicioTurno.getTime())
                    {
                        ET = NumeroHorasLong(HoraMarcacionEmpledo, simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_inicio()+":00"));                    
                        ResulET = NumeroHorasString(ET);
                        Horas = String.format("%02d", Long.parseLong(ResulET.get("horas").toString()));
                        Minutos = String.format("%02d", Long.parseLong(ResulET.get("minutos").toString()));
                        Segundos = String.format("%02d", Long.parseLong(ResulET.get("segundos").toString()));
                        modeloLiquidacion1.setHoraEntradaTardia(Horas+":"+Minutos+":"+Segundos);
                    }
                    else
                    {
                        modeloLiquidacion1.setHoraEntradaTardia("00:00:00");
                    }
                }
                else
                {
                    modeloLiquidacion1.setHoraEntradaTardia("00:00:00");
                }
            }   
        }
        catch(ParseException e)
        {
        }        
        return modeloLiquidacion;
    }

    private List<ModeloLiquidacion> getSalidaTemprana(List<ModeloLiquidacion> modeloLiquidacion) 
    {
        try 
        {            
            for (ModeloLiquidacion modeloLiquidacion1 : modeloLiquidacion)
            {
                if((modeloLiquidacion1.getHoraInicio() != null)&& (modeloLiquidacion1.getHoraFin()!= null))
                {
                    Date HoraFinTurno;
                    Date HoraMarcacionEmpledo;
                    Long ET = 00000000000L;
                    String Horas;
                    String Minutos;
                    String Segundos;
                    Map ResulET;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    HoraFinTurno = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_fin()+":00");
                    HoraMarcacionEmpledo = simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getHoraFin());

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(HoraFinTurno);
                    calendar.add(calendar.MINUTE,-Integer.parseInt(modeloLiquidacion1.getModeloTurnos().getTiempo_minimo_salida()));
                    HoraFinTurno = calendar.getTime();


                    if(HoraMarcacionEmpledo.getTime() < HoraFinTurno.getTime())
                    {
                        ET = NumeroHorasLong(HoraMarcacionEmpledo, simpleDateFormat.parse(modeloLiquidacion1.getFechaInicio()+" "+modeloLiquidacion1.getModeloTurnos().getHora_fin()+":00"));                    
                        ResulET = NumeroHorasString(ET);
                        Horas = String.format("%02d", Long.parseLong(ResulET.get("horas").toString()));
                        Minutos = String.format("%02d", Long.parseLong(ResulET.get("minutos").toString()));
                        Segundos = String.format("%02d", Long.parseLong(ResulET.get("segundos").toString()));
                        modeloLiquidacion1.setHoraSalidaTemprana(Horas+":"+Minutos+":"+Segundos);
                    }
                    else
                    {
                        modeloLiquidacion1.setHoraSalidaTemprana("00:00:00");
                    }
                }
                else
                {
                    modeloLiquidacion1.setHoraSalidaTemprana("00:00:00");
                }
            }   
        }
        catch(ParseException e)
        {
        }        
        return modeloLiquidacion;   
    }
}





