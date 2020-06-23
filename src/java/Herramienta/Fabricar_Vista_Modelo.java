/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramienta;

import Conexiones.ConexionBdMysql;
import Controladores.ControladorEnumeracion;
import Controladores.ControladorParametro_tabla;
import Modelo.ModeloEnumeracion;
import Modelo.ModeloParametro_tabla;
import Modelo.ModeloPersona;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SE ENCARGARA DE GENERAR DIFERENTES VISTAS DEPENDIENDO DEL MODELO Y DE LOS
 * PARAMETROS
 *
 * @author Diego Fdo Guzman B
 * @version 2020-06-05
 * @
 */
public class Fabricar_Vista_Modelo {

    Connection con;
    //PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    String Modales_Externos = "";
    ControladorParametro_tabla controladorParametro_tabla = new ControladorParametro_tabla();
    ControladorEnumeracion controladorEnumeracion = new ControladorEnumeracion();
    LinkedList<ModeloParametro_tabla> ListaModeloParametro_tabla_General = new LinkedList<ModeloParametro_tabla>();

    public String Crear_Tabla_Modelo(String Tabla, String Modulo) throws SQLException {
        
        
        con = conexion.abrirConexion();
        controladorParametro_tabla.EnviarConexion(con);
        controladorEnumeracion.EnviarConexion(con);
        
   
        
        System.err.println("Inicio creacion Modelo: " + new Date());
        String out = "";
        String Encabezado = "";
        Encabezado += "<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\">";
        Encabezado += "<div class=\"modal-dialog modal-lg\" role=\"document\">";
        Encabezado += "<div class=\"modal-content\">";
        Encabezado += "<div class=\"modal-header\">";
        Encabezado += "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>";
        Encabezado += "<h4 class=\"modal-title\" id=\"myModalLabel\">Formulario</h4>";
        Encabezado += "</div>";
        Encabezado += "<div class=\"modal-body\">";
        Encabezado += "<div class=\"row\">";
        System.err.println("Inicio Consulta Listado de Parametros Tabla: " + new Date());
        LinkedList<ModeloParametro_tabla> ListaModeloParametro_tabla = controladorParametro_tabla.Read("S", Tabla, Modulo);
        System.err.println("Fin Consulta Listado de Parametros Tabla: " + new Date());
        
        ListaModeloParametro_tabla_General = ListaModeloParametro_tabla;
        
        System.err.println("Inicio recorrido Listado de Parametros Tabla: " + new Date());
        for (ModeloParametro_tabla modeloParametro_tabla : ListaModeloParametro_tabla) {

            //Muestra u Oculta
            String Visible = "style=\"display: block\"";
            if (modeloParametro_tabla.getVisualizar().contentEquals("No")) {
                Visible = "style=\"display: none\"";
            }
            out += "<div class=\"col-md-4 col-sm-12 col-xs-12 form-group\" " + Visible + " >";
            out += "<label> " + modeloParametro_tabla.getNombre_visible() + "</label>";
            System.err.println("Inicio creacion Modelo: " + new Date());
            out += Tipo_Campo(modeloParametro_tabla);
            System.err.println("Inicio creacion Modelo: " + new Date());
            out += "</div>";

        }
        System.err.println("Fin recorrido Listado de Parametros Tabla: " + new Date());
        Encabezado += out;
        Encabezado += "</div>";
        Encabezado += "</div>";
        Encabezado += "<div class=\"modal-footer\">";
        Encabezado += "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\"><i class=\"fa fa-close\"></i> Cerrar</button>";
        Encabezado += "<button type=\"button\" class=\"btn btn-success\"><i class=\"fa fa-save\"></i> Guardar</button>";
        Encabezado += "</div>";
        Encabezado += "</div>";
        Encabezado += "</div>";
        Encabezado += "</div>";
        Encabezado += Modales_Externos;
        System.err.println("Fin creacion Modelo: " + new Date());
        
        con.close();
        
        return Encabezado;
    }

    public String Tipo_Campo(ModeloParametro_tabla modeloParametro_tabla) throws SQLException {
        System.err.println("Inicio Validacion tipo campo : " + new Date());
        String out = "";
        String Tipo_Campo = modeloParametro_tabla.getTipo_campo();

        if (Tipo_Campo.contains("Integer")) {
            out += "<input type=\"number\" class=\"form-control\" id=\"Campo_" + modeloParametro_tabla.getId() + "_nuevo\" min=\"0\" required>";
        } else if (Tipo_Campo.contains("String")) {
            out += "<input type=\"text\" class=\"form-control\" id=\"Campo_" + modeloParametro_tabla.getId() + "_nuevo\" min=\"0\" required>";
        } else if (Tipo_Campo.contains("Lista")) {
            out += "<select id=\"Lista_"  + modeloParametro_tabla.getId() + "\" class=\"form-control\" required>";
            out += "<option value=\"0\" selected disabled>Seleccione</option>";
            LinkedList<ModeloEnumeracion> ListaModeloEnumeracion = controladorEnumeracion.Read("S", modeloParametro_tabla.getLista());
            int i = 1;
            for (ModeloEnumeracion modeloEnumeracion : ListaModeloEnumeracion) {
                out += "<option value=\"" + i + "\">" + modeloEnumeracion.getCampo() + "</option>";
                i++;
            }
            out += "</select>";

        } else if (Tipo_Campo.contains("Date")) {
            out += "<div class=\"form-group\">";
            out += "<div class=\"input-group date\" id=\"myDatepicker\">";
            out += "<input type=\"text\" class=\"form-control\" id=\"Campo_" + modeloParametro_tabla.getId() + "_nuevo\" name=\"" + modeloParametro_tabla.getId() + "_nuevo\" />";
            out += "<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-calendar\"></span></span>";
            out += "</div>";
            out += "</div>";
        } else if (Tipo_Campo.contains("Modelo")) {
            out += "<div class=\"form-group\">";
            out += "<div class='input-group date' id='visiblemodal" + modeloParametro_tabla.getNombre_visible() + "' data-toggle=\"modal\" data-target=\".bs-" + modeloParametro_tabla.getId() + "-modal-lg\">";
            out += "<input type='text' class=\"form-control\" id=\"Campo_" + modeloParametro_tabla.getId() + "_nuevo\" name=\"" + modeloParametro_tabla.getNombre_visible() + "_nuevo\" style=\"text-align:center;\" required/>";
            out += "<span class=\"input-group-addon\">";
            out += "<span class=\"glyphicon glyphicon-search\"></span>";
            out += "</div>";
            out += "</div>";
            //out += "</div>";

            Modales_Externos += "<div id=\"Busqueda" + modeloParametro_tabla.getId() + "\" class=\"modal fade bs-" + modeloParametro_tabla.getId() + "-modal-lg\" tabindex=\"-1\" role=\"dialog\" aria-hidden=\"true\">";
            Modales_Externos += "<div class=\"modal-dialog modal-lg\">";
            Modales_Externos += "<div class=\"modal-content\">";
            Modales_Externos += "<div class=\"modal-header\">";
            Modales_Externos += "<h4 class=\"modal-title\"><b>" + modeloParametro_tabla.getNombre_visible() + "</b></h4>";
            Modales_Externos += "<button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span aria-hidden=\"true\">×</span>";
            Modales_Externos += "</button>";
            Modales_Externos += "</div>";
            Modales_Externos += "<div class=\"modal-body\">";
            Modales_Externos += "<table id=\"IdTabla_" + modeloParametro_tabla.getId() + "\" class=\"table table-responsive table-bordered\">";
            Modales_Externos += Tabla_Seleccionable(modeloParametro_tabla);
            Modales_Externos += "</table>";
            Modales_Externos += "</div>";
            Modales_Externos += "<div class=\"modal-footer\">";
            Modales_Externos += "<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Cerrar</button>";
            Modales_Externos += "</div>";
            Modales_Externos += "</div>";
            Modales_Externos += "</div>";
            Modales_Externos += "</div>";

        }
        System.err.println("Fin Validacion tipo campo : " + new Date());
        return out;

    }

    public String Tabla_Seleccionable(ModeloParametro_tabla modeloParametro_tabla) {
        System.err.println("Inicio Consulta de registros de la tabla : " + new Date());
        String out = "";
        out += "<thead>";
        out += "<tr>";
        LinkedList<Object> Tabla = new LinkedList<>();

        //con = conexion.abrirConexion();
        try {
            PreparedStatement SQL = con.prepareStatement("SELECT * FROM " + modeloParametro_tabla.getTabla_referencia());
            ResultSet res = SQL.executeQuery();
            ResultSetMetaData rsmd = null;
            rsmd = res.getMetaData();

            //get metadata
            ResultSetMetaData meta = null;
            meta = res.getMetaData();

            //get column names
            int colCount = meta.getColumnCount();
            LinkedList<String> cols = new LinkedList<String>();
            for (int index = 1; index <= colCount; index++) {
                cols.add(meta.getColumnName(index));
                //for (ModeloParametro_tabla modeloParametro_tabla : ListaModeloParametro_tabla_General) {
                //    if (modeloParametro_tabla.getNombre_campo_bd().contentEquals(meta.getColumnName(index)) && modeloParametro_tabla.getVisualizar().contentEquals("Si")) {
                        out += "<th nowrap>" + modeloParametro_tabla.getNombre_visible() + "</th>";
                //    }

                //}

                //System.err.println(meta.getColumnName(index));
            }

            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";

            //fetch out rows
            LinkedList<HashMap<String, Object>> rows = new LinkedList<HashMap<String, Object>>();

            while (res.next()) {
                out += "<tr>";
                HashMap<String, Object> row = new HashMap<String, Object>();
                for (String colName : cols) {
                    Object val = res.getObject(colName);
                    row.put(colName, val);

                    //for (ModeloParametro_tabla modeloParametro_tabla : ListaModeloParametro_tabla_General) {
                    //    if (modeloParametro_tabla.getNombre_campo_bd().contentEquals(colName) && modeloParametro_tabla.getVisualizar().contentEquals("Si")) {
                            if (val == null) {
                                out += "<td>" + " - " + "</td>";
                            } else {
                                out += "<td nowrap>" + val.toString() + "</td>";
                            }
                    //    }
                    //}
                }
                rows.add(row);
                out += "</tr>";
            }

            out += "</tbody>";


            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorvencimiento" + e);
        }

        System.err.println("Fin Consulta de registros de la tabla : " + new Date());
        return out;
    }

}
