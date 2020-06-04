/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Herramienta.Herramienta;
import Modelo.ModeloCentro_costo;
import Modelo.ModeloConsumo;
import Modelo.ModeloGrupo_consumo;
import Modelo.ModeloHorario_consumo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorConsumo {

    Herramienta herramienta = new Herramienta();
    String user;
    String resultado;
    Connection con;
    PreparedStatement SQL = null;

    ConexionBdMysql conexion = new ConexionBdMysql();

    public String GetDatoSemana(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> ValoresFechas = new ArrayList<>();
        ArrayList<String> Valores = new ArrayList<>();
        String jsonString = "[{\"Fechas\":[\"2020-05-28\",\"2020-05-29\"],\"Valores\":[\"5\",\"6\"]}]";
        String fechas = null;
        String Valoress = null;
        String ListaColores = null;
        String BordeColores = null;

        try {
            //Obtenemos las fechas del dia de hoy
            String datetoday = herramienta.getDateToday();
            String fechaTemporal;
            Calendar FechaInical = Calendar.getInstance();
            Calendar FechaAnterior = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            FechaInical.setTime(sdf.parse(datetoday));
            FechaAnterior.add(Calendar.DAY_OF_MONTH, -6);
            long daysBetween = 0;
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("SELECT "
                        + "COUNT(fecha_consumo) AS VALOR FROM `consumo` "
                        + "WHERE "
                        + "fecha_consumo >= ? AND "
                        + "fecha_consumo <= ? ;");
                ResultSet res = null;
                while (FechaAnterior.before(FechaInical)) {
                    if (daysBetween == 0) {
                        FechaAnterior.add(Calendar.DAY_OF_MONTH, 0);
                        fechaTemporal = herramienta.getCalendarString(FechaAnterior);
                        SQL.setString(1, fechaTemporal + " 00:00:00");
                        SQL.setString(2, fechaTemporal + " 23:59:59");
                        res = SQL.executeQuery();
                        if (res.next()) {
                            fechas = fechaTemporal;
                            Valoress = res.getString("VALOR");
                            ListaColores = herramienta.getNumColor("0.2");
                            BordeColores = herramienta.getNumColor("1");
                        }
                    } else {
                        FechaAnterior.add(Calendar.DAY_OF_MONTH, 1);
                        fechaTemporal = herramienta.getCalendarString(FechaAnterior);
                        SQL.setString(1, fechaTemporal + " 00:00:00");
                        SQL.setString(2, fechaTemporal + " 23:59:59");
                        res = SQL.executeQuery();
                        if (res.next()) {
                            fechas = fechas + "," + fechaTemporal;
                            Valoress = Valoress + "," + res.getString("VALOR");
                            ListaColores = ListaColores + "_" + herramienta.getNumColor("0.2");
                            BordeColores = BordeColores + "_" + herramienta.getNumColor("1");
                        }
                    }
                    daysBetween++;
                }
                res.close();
                SQL.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorConsumo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ControladorConsumo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Controladores.ControladorConsumo.GetDatoSemana() " + ex);
        }
        JsonParser parser = new JsonParser();
        jsonString = "[{\"Fechas\":[\"" + fechas + "\"],\"Valores\":[\"" + Valoress + "\"],\"Color\":[\"" + ListaColores + "\"],\"Borde\":[\"" + BordeColores + "\"]}]";
        JsonArray gsonArr = parser.parse(jsonString).getAsJsonArray();
        resultado = gsonArr.toString();
        return resultado;
    }
/**
 * Perminte generar las consultas de data  Consumo
 *@param response request
 * 
 *
 */
    public String GetDatoConsumo(HttpServletRequest request, HttpServletResponse response) {
        String jsonString = "[{\"Fechas\":[\"2020-05-28\",\"2020-05-29\"],\"Valores\":[\"5\",\"6\"]}]";
        String Consumo = null;
        String Valoress = null;
        String ListaColores = null;
        String BordeColores = null;
        int Contador = 0;
        try {
            String datetoday = herramienta.getDateToday();
            LinkedList<ModeloHorario_consumo> listaHorario_consumo = new LinkedList<>();
            ControladorHorario_consumo controladorHorario_consumo = new ControladorHorario_consumo();
            listaHorario_consumo = controladorHorario_consumo.Read("S");
            if (listaHorario_consumo.size() > 0) {
                con = conexion.abrirConexion();
                ResultSet res = null;
                SQL = con.prepareStatement("SELECT "
                        + "COUNT(id_horario_consumo) AS VALOR "
                        + "FROM `consumo` "
                        + "WHERE id_horario_consumo = ? AND "
                        + "fecha_consumo >= ? AND "
                        + "fecha_consumo <= ? ");
                for (ModeloHorario_consumo modeloHorario_consumo : listaHorario_consumo) {
                    SQL.setInt(1, modeloHorario_consumo.getId());
                    SQL.setString(2, datetoday + " 00:00:00");
                    SQL.setString(3, datetoday + " 23:59:59");
                    res = SQL.executeQuery();
                    if (res.next()) {
                        if (Contador == 0) {
                            Valoress = res.getString("VALOR");
                            Consumo = modeloHorario_consumo.getNombre();
                            ListaColores = herramienta.getNumColor("0.2");
                            BordeColores = herramienta.getNumColor("1");
                            Contador++;
                        } else {
                            Valoress = Valoress + "," + res.getString("VALOR");
                            Consumo = Consumo + "," + modeloHorario_consumo.getNombre();
                            ListaColores = ListaColores + "_" + herramienta.getNumColor("0.2");
                            BordeColores = BordeColores + "_" + herramienta.getNumColor("1");
                            Contador++;
                        }
                    }
                }
                res.close();
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Controladores.ControladorConsumo.GetDatoConsumo() " + e);
        }
        // Obtain Array
        JsonParser parser = new JsonParser();
        jsonString = "[{\"Consumo\":[\"" + Consumo + "\"],\"Valores\":[\"" + Valoress + "\"],\"Color\":[\"" + ListaColores + "\"],\"Borde\":[\"" + BordeColores + "\"]}]";
        JsonArray gsonArr = parser.parse(jsonString).getAsJsonArray();
        resultado = gsonArr.toString();
        return resultado;
    }
/**
 * Perminte generar las consultas centro de costo
 *@param response request
 * 
 *
 */
    public String GetDatoCentroCosto(HttpServletRequest request, HttpServletResponse response) {
        String jsonString = "[{\"Fechas\":[\"2020-05-28\",\"2020-05-29\"],\"Valores\":[\"5\",\"6\"]}]";
        String CentroCosto = null;
        String Valoress = null;
        String ListaColores = null;
        String BordeColores = null;
        int Contador = 0;
        try {
            String datetoday = herramienta.getDateToday();
            LinkedList<ModeloCentro_costo> listaCentro_costo = new LinkedList<>();
            ControladorCentro_costo controladorCentro_costo = new ControladorCentro_costo();                    
            listaCentro_costo = controladorCentro_costo.Read("S");
            if (listaCentro_costo.size() > 0) {
                con = conexion.abrirConexion();
                ResultSet res = null;
                SQL = con.prepareStatement("SELECT "
                        + "COUNT(id_centro_costo) AS VALOR "
                        + "FROM `consumo` "
                        + "WHERE id_centro_costo = ? AND "
                        + "fecha_consumo >= ? AND "
                        + "fecha_consumo <= ? ");
                for (ModeloCentro_costo modeloCentro_costo : listaCentro_costo) {
                    SQL.setInt(1, modeloCentro_costo.getId());
                    SQL.setString(2, datetoday + " 00:00:00");
                    SQL.setString(3, datetoday + " 23:59:59");
                    res = SQL.executeQuery();
                    if (res.next()) {
                        if (Contador == 0) {
                            Valoress = res.getString("VALOR");
                            CentroCosto = modeloCentro_costo.getNombre();
                            ListaColores = herramienta.getNumColor("0.2");
                            BordeColores = herramienta.getNumColor("1");
                            Contador++;
                        } else {
                            Valoress = Valoress + "," + res.getString("VALOR");
                            CentroCosto = CentroCosto + "," + modeloCentro_costo.getNombre();
                            ListaColores = ListaColores + "_" + herramienta.getNumColor("0.2");
                            BordeColores = BordeColores + "_" + herramienta.getNumColor("1");
                            Contador++;
                        }
                    }
                }
                res.close();
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Controladores.ControladorConsumo.GetDatoConsumo() " + e);
        }
        // Obtain Array
        JsonParser parser = new JsonParser();
        jsonString = "[{\"CentroCosto\":[\"" + CentroCosto + "\"],\"Valores\":[\"" + Valoress + "\"],\"Color\":[\"" + ListaColores + "\"],\"Borde\":[\"" + BordeColores + "\"]}]";
        JsonArray gsonArr = parser.parse(jsonString).getAsJsonArray();
        resultado = gsonArr.toString();
        return resultado;
    }
/**
 * Perminte generar las consultas de grupo consumo
 *@param response request
 * 
 *
 */
    public String GetDatoGrupoConsumo(HttpServletRequest request, HttpServletResponse response) {
        String jsonString = "[{\"Fechas\":[\"2020-05-28\",\"2020-05-29\"],\"Valores\":[\"5\",\"6\"]}]";
        String GrupoConsumo = null;
        String Valoress = null;
        String ListaColores = null;
        String BordeColores = null;
        int Contador = 0;
        try {
            String datetoday = herramienta.getDateToday();
            LinkedList<ModeloGrupo_consumo> listaGrupo_consumos = new LinkedList<>();
            ControladorGrupo_consumo controladorGrupo_consumo= new ControladorGrupo_consumo();
            listaGrupo_consumos = controladorGrupo_consumo.Read("S");
            if (listaGrupo_consumos.size() > 0) {
                con = conexion.abrirConexion();
                ResultSet res = null;
                SQL = con.prepareStatement("SELECT "
                        + "COUNT(id_grupo_consumo) AS VALOR "
                        + "FROM `consumo` "
                        + "WHERE id_grupo_consumo = ? AND "
                        + "fecha_consumo >= ? AND "
                        + "fecha_consumo <= ? ");
                for (ModeloGrupo_consumo modeloGrupo_consumo : listaGrupo_consumos) {
                    SQL.setInt(1, modeloGrupo_consumo.getId());
                    SQL.setString(2, datetoday + " 00:00:00");
                    SQL.setString(3, datetoday + " 23:59:59");
                    res = SQL.executeQuery();
                    if (res.next()) {
                        if (Contador == 0) {
                            Valoress = res.getString("VALOR");
                            GrupoConsumo = modeloGrupo_consumo.getNombre();
                            ListaColores = herramienta.getNumColor("0.2");
                            BordeColores = herramienta.getNumColor("1");
                            Contador++;
                        } else {
                            Valoress = Valoress + "," + res.getString("VALOR");
                            GrupoConsumo = GrupoConsumo + "," + modeloGrupo_consumo.getNombre();
                            ListaColores = ListaColores + "_" + herramienta.getNumColor("0.2");
                            BordeColores = BordeColores + "_" + herramienta.getNumColor("1");
                            Contador++;
                        }
                    }
                }
                res.close();
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Controladores.ControladorConsumo.GetDatoConsumo() " + e);
        }
        // Obtain Array
        JsonParser parser = new JsonParser();
        jsonString = "[{\"GrupoConsumo\":[\"" + GrupoConsumo + "\"],\"Valores\":[\"" + Valoress + "\"],\"Color\":[\"" + ListaColores + "\"],\"Borde\":[\"" + BordeColores + "\"]}]";
        JsonArray gsonArr = parser.parse(jsonString).getAsJsonArray();
        resultado = gsonArr.toString();
        return resultado;
    }

}
