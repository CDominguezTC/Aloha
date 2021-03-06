/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloPersona;
import Modelo.ModeloTemplate;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorTemplate {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String user;

    /**
     * Permite la insecion de los template en la base de datos
     *
     *
     * @param Template
     * @param IdTemplate
     * @param modelo
     * @param opcion
     * @return
     */
    public String Insert(String Template, String IdTemplate, ModeloPersona modelo, String opcion) {
        LinkedList<ModeloTemplate> listamodeloTemplate = new LinkedList<>();
        listamodeloTemplate = Generar_lista_template(Template, IdTemplate, modelo);
        if (!listamodeloTemplate.isEmpty()) {
            if ("Insert".equals(opcion)) {
                resultado = Insert(listamodeloTemplate);
            }
            if ("Update".equals(opcion)) {
                resultado = Update(listamodeloTemplate);
            }

        }
        return resultado;
    }

    /**
     * Genera el listado de los template que se guardaran en la base de datos
     *
     * @param Template
     * @param IdTemplate
     * @param modelo
     * @return
     */
    private LinkedList<ModeloTemplate> Generar_lista_template(String Template, String IdTemplate, ModeloPersona modelo) {
        LinkedList<ModeloTemplate> listaModeloTemplates = new LinkedList<>();
        String[] idtemplate = (IdTemplate.replace("[", "").replace("]", "")).split(",");
        String[] template = (Template.replace("[", "").replace("]", "")).split(",");
        for (int i = 0; i < idtemplate.length; i++) {
            if (!"".equals(idtemplate[i])) {
                ModeloTemplate modeloTemplate = new ModeloTemplate();
                modeloTemplate.setModelo_persona(modelo);
                modeloTemplate.setNumero_plantilla(idtemplate[i]);
                modeloTemplate.setPlantilla(template[i]);
                modeloTemplate.setTipo_plantilla("Huella");
                modeloTemplate.setEstado("S");
                listaModeloTemplates.add(modeloTemplate);
            }
        }
        return listaModeloTemplates;
    }

    private String Insert(LinkedList<ModeloTemplate> listamodeloTemplate) {
        try {
            con = conexion.abrirConexion();
            for (ModeloTemplate modeloTemplate : listamodeloTemplate) {
                try {
                    SQL = con.prepareStatement("INSERT INTO `template`("
                            + "`id_persona`,"
                            + "`tipo_plantilla`,"
                            + "`numero_plantilla`,"
                            + "`plantilla`,"
                            + "`estado`) "
                            + "VALUE (?,?,?,?,?);");
                    SQL.setInt(1, modeloTemplate.getModelo_persona().getId());
                    SQL.setString(2, modeloTemplate.getTipo_plantilla());
                    SQL.setString(3, modeloTemplate.getNumero_plantilla());
                    SQL.setString(4, modeloTemplate.getPlantilla());
                    SQL.setString(5, modeloTemplate.getEstado());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                    }
                } catch (SQLException e) {
                    System.out.println("Controladores.ControladorImagen.Insert() " + e);
                    resultado = "-2";
                }
            }
            SQL.close();
            con.close();
            return resultado;
        } catch (SQLException ex) {
            System.out.println("Controladores.ControladorImagen.Insert() " + ex);
        }
        return resultado;
    }

    public LinkedList<ModeloTemplate> getModelo(Integer id) {
        ControladorPersona controladorPersona = new ControladorPersona();
        LinkedList<ModeloTemplate> listaModeloTemplates = new LinkedList<ModeloTemplate>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`id_persona`,"
                    + "`tipo_plantilla`,"
                    + "`numero_plantilla`,"
                    + "`plantilla`,"
                    + "`estado` "
                    + "FROM `template`"
                    + "WHERE id_persona = ?;");
            SQL.setInt(1, id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloTemplate modeloTemplate = new ModeloTemplate();
                modeloTemplate.setId(res.getInt("id"));
                modeloTemplate.setModelo_persona(controladorPersona.getModelo(id));
                modeloTemplate.setTipo_plantilla(res.getString("tipo_plantilla"));
                modeloTemplate.setNumero_plantilla(res.getString("numero_plantilla"));
                modeloTemplate.setPlantilla(res.getString("plantilla"));
                modeloTemplate.setEstado(res.getString("estado"));
                listaModeloTemplates.add(modeloTemplate);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listaModeloTemplates;
    }

    private String Update(LinkedList<ModeloTemplate> listaModeloTemplates) {
        resultado = Delete(listaModeloTemplates);
        if ("1".equals(resultado)) {
            resultado = Insert(listaModeloTemplates);
        }
        return resultado;
    }

    private String Delete(LinkedList<ModeloTemplate> listaModeloTemplates) {
        try {
            con = conexion.abrirConexion();
            for (ModeloTemplate modeloTemplate : listaModeloTemplates) {
                try {
                    SQL = con.prepareStatement("DELETE FROM `template` "
                            + "WHERE `id_persona` = ?;");
                    SQL.setInt(1, modeloTemplate.getModelo_persona().getId());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                    }
                    resultado = "1";
                } catch (SQLException e) {
                    System.out.println("Controladores.ControladorTemplate.Delete() " + e);
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Controladores.ControladorTemplate.Delete() " + e);
            resultado = "-3";
        }
        return resultado;
    }

    public String Search(HttpServletRequest request, HttpServletResponse response) {
        String IdPersona = request.getParameter("idpersona");
        LinkedList<ModeloTemplate> listaModeloTemplates = new LinkedList<>();
        listaModeloTemplates = getModelo(Integer.parseInt(IdPersona));
        resultado = new Gson().toJson(listaModeloTemplates);
        return resultado;
    }

}
