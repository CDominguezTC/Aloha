/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Herramienta.Herramienta;
import Modelo.ModeloCargo_hoteleria;
import Modelo.ModeloConsumo_hoteleria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorConsumo_hoteleria {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;
    ControladorPersona controladorPersona = new ControladorPersona();
    ControladorCentro_costo controladorCentro_costo = new ControladorCentro_costo();
    ControladorGrupo_consumo controladorGrupo_consumo = new ControladorGrupo_consumo();
    ControladorHorario_consumo controladorHorario_consumo = new ControladorHorario_consumo();
    ControladorCargo_hoteleria controladorCargo_hoteleria = new ControladorCargo_hoteleria();
    Herramienta herramienta = new Herramienta();

    /**
     * Dato que viene de la vista, valida si inserta o actualiza en la tabla
     * consumo_hoteleria
     *
     * @param response
     * @throws java.sql.SQLException
     * @author: Carlos A Dominguez D
     * @param request
     * @return String
     * @version: 22/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String[] IdCargosHoteleria = request.getParameterValues("idconsumo[]");
        String IdPersona = request.getParameter("id");
        String FechaServicio = request.getParameter("idfecha");

        LinkedList<ModeloConsumo_hoteleria> listaModeloConsumo_hotelerias = new LinkedList<>();
        for (String IdCargoHoteleria : IdCargosHoteleria) {
            ModeloConsumo_hoteleria modeloConsumo_hoteleria = new ModeloConsumo_hoteleria();
            modeloConsumo_hoteleria.setModelo_cargo_hoteleria(controladorCargo_hoteleria.getModelo(Integer.parseInt(IdCargoHoteleria)));
            modeloConsumo_hoteleria.setModelo_persona(controladorPersona.getModelo(Integer.parseInt(IdPersona)));
            modeloConsumo_hoteleria.setFecha_consumo(FechaServicio);
            modeloConsumo_hoteleria.setEstado("S");
            listaModeloConsumo_hotelerias.add(modeloConsumo_hoteleria);
        }
        if (listaModeloConsumo_hotelerias.size() > 0) {
            HttpSession session = request.getSession();
            user = (String) session.getAttribute("usuario");
            resultado = Insert(listaModeloConsumo_hotelerias);
        }

        return resultado;
    }

    /**
     * Inserta los datos en la base de datos de la tabla: consumo_hoteleria
     *
     * @param listaModeloConsumo_hotelerias
     * @author: Carlos A Dominguez D
     * @return String
     * @version: 22/05/2020
     */
    public String Insert(LinkedList<ModeloConsumo_hoteleria> listaModeloConsumo_hotelerias) {
        con = conexion.abrirConexion();
        try {
            for (ModeloConsumo_hoteleria modeloConsumo_hoteleria : listaModeloConsumo_hotelerias) {
                SQL = con.prepareStatement("INSERT INTO consumo_hoteleria("
                        + "id_persona, "
                        + "cargo_persona, "
                        + "id_centro_costo, "
                        + "id_cargo_hoteleria, "
                        + "cargo_hoteleria_valor, "
                        + "fecha_consumo, "
                        + "dia_consumo, "
                        + "estado)"
                        + " VALUE (?,?,?,?,?,?,?,?)", SQL.RETURN_GENERATED_KEYS);
                SQL.setInt(1, modeloConsumo_hoteleria.getModelo_persona().getId());
                SQL.setString(2, modeloConsumo_hoteleria.getModelo_persona().getModelo_cargo().getNombre());
                SQL.setInt(3, modeloConsumo_hoteleria.getModelo_persona().getModelo_centro_costo().getId());
                SQL.setInt(4, modeloConsumo_hoteleria.getModelo_cargo_hoteleria().getId());
                SQL.setInt(5, modeloConsumo_hoteleria.getModelo_cargo_hoteleria().getValor_cargo());
                SQL.setString(6, modeloConsumo_hoteleria.getFecha_consumo());
                SQL.setString(7, herramienta.getNombreDia(modeloConsumo_hoteleria.getFecha_consumo()));
                SQL.setString(8, modeloConsumo_hoteleria.getEstado());
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.");
                        }
                        resultado = "1";
                    }
                }
            }
            SQL.close();
            con.close();

        } catch (SQLException e) {
            try {
                System.out.println("Error en la consulta SQL Insert en Controladorconsumo_hoteleria" + e);
                resultado = "-2";
                SQL.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Controladores.ControladorConsumo_hoteleria.Insert() " + ex);
            }
        }
        return resultado;
    }

    public String Read(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        try {
            LinkedList<ModeloCargo_hoteleria> listaModeloCargo_hoteleria = new LinkedList<ModeloCargo_hoteleria>();
            ControladorCargo_hoteleria controladorCargo_hoteleria = new ControladorCargo_hoteleria();
            listaModeloCargo_hoteleria = controladorCargo_hoteleria.Read("S");
            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<thead>";
            out += "<tr class=\"headings\">";
            out += "<td>Seleccione</td>";
            out += "<td>Servicio</td>";
            out += "<td>Valor</td>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloCargo_hoteleria modeloCargo_hoteleria : listaModeloCargo_hoteleria) {
                out += "<tr>";
                out += "<td class=\"text-center\"><input type=\"checkbox\" id=\"" + modeloCargo_hoteleria.getId() + "\" name=\"" + modeloCargo_hoteleria.getId() + "\" class=\"flat\" value=\"" + modeloCargo_hoteleria.getId() + "\"/></td>";
                out += "<td>" + modeloCargo_hoteleria.getTipo_cargo() + "</td>";
                out += "<td>" + modeloCargo_hoteleria.getValor_cargo() + "</td>";
                out += "</tr>";
            }
            out += "</tbody>";

        } catch (Exception e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }

}
