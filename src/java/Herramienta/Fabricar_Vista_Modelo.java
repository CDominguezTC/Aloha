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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;

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
    StringBuilder Modales_Externos_Sb = new StringBuilder();
    ControladorParametro_tabla controladorParametro_tabla = new ControladorParametro_tabla();
    ControladorEnumeracion controladorEnumeracion = new ControladorEnumeracion();
    LinkedList<ModeloParametro_tabla> ListaModeloParametro_tabla_General = new LinkedList<ModeloParametro_tabla>();
    HttpServletRequest request = null;

    public void Share_Request(HttpServletRequest request1) {
        request = request1;
        con = conexion.abrirConexion();
        controladorParametro_tabla.SendRequest(request);
        controladorEnumeracion.SendRequest(request);
        controladorParametro_tabla.EnviarConexion(con);
        controladorEnumeracion.EnviarConexion(con);
    }

    public String Crear_Tabla_Modelo(String Tabla, String Modulo) throws SQLException {

//        con = conexion.abrirConexion();
//        controladorParametro_tabla.SendRequest(request);
//        controladorEnumeracion.SendRequest(request);
//        controladorParametro_tabla.EnviarConexion(con);
//        controladorEnumeracion.EnviarConexion(con);
        StringBuilder Out_Sb = new StringBuilder();
        StringBuilder Encabezado_Sb = new StringBuilder();
        String Elementos = "Elementos";

        Encabezado_Sb
                .append("<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\">")
                .append("<div class=\"modal-dialog modal-lg\" role=\"document\">")
                .append("<div class=\"modal-content\">")
                .append("<div class=\"modal-header\">")
                .append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>")
                .append("<h4 class=\"modal-title\" id=\"myModalLabel\">Formulario</h4>")
                .append("</div>")
                .append("<div class=\"modal-body\">")
                .append("<div class=\"row\">");

        LinkedList<ModeloParametro_tabla> ListaModeloParametro_tabla = controladorParametro_tabla.Read("S", Tabla, Modulo);

        ListaModeloParametro_tabla_General = ListaModeloParametro_tabla;

        for (ModeloParametro_tabla modeloParametro_tabla : ListaModeloParametro_tabla) {

            //Muestra u Oculta
            String Visible = "style=\"display: block\"";
            if (modeloParametro_tabla.getVisualizar().contentEquals("No")) {
                Visible = "style=\"display: none\"";
            }

            Out_Sb
                    .append("<div class=\"col-md-4 col-sm-12 col-xs-12 form-group\" ").append(Visible).append(" >")
                    .append("<label> ").append(modeloParametro_tabla.getNombre_visible()).append("</label>")
                    .append(Tipo_Campo(modeloParametro_tabla, Modulo, Elementos))
                    .append("</div>");
        }
        Encabezado_Sb
                .append(Out_Sb)
                .append("</div>")
                .append("</div>")
                .append("<div class=\"modal-footer\">")
                .append("<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\"><i class=\"fa fa-close\"></i> Cerrar</button>")
                .append("<button type=\"button\" class=\"btn btn-success\"><i class=\"fa fa-save\"></i> Guardar</button>")
                .append("</div>")
                .append("</div>")
                .append("</div>")
                .append("</div>")
                .append(Modales_Externos_Sb);

        con.close();

        return Encabezado_Sb + "";
    }

    public String Tipo_Campo(ModeloParametro_tabla modeloParametro_tabla, String Modulo, String name) throws SQLException {

        StringBuilder Out_Sb = new StringBuilder();
        String Tipo_Campo = modeloParametro_tabla.getTipo_campo();
        String Campo_dB = modeloParametro_tabla.getNombre_campo_bd();
        String Obligatorio = modeloParametro_tabla.getObligatorio();

        if (Tipo_Campo.contains("Integer")) {
            Out_Sb.append("<input name=\"").append(name).append("\" Tipo_Campo=\"Integer\" Pertenencia=\"").append(Campo_dB).append("\" Obligatorio=\"").append(Obligatorio).append("\" type=\"number\" class=\"form-control\" id=\"Campo_").append(modeloParametro_tabla.getId()).append("_nuevo\" min=\"0\" required>");
        } else if (Tipo_Campo.contains("String")) {
            Out_Sb.append("<input name=\"").append(name).append("\" Tipo_Campo=\"String\" Pertenencia=\"").append(Campo_dB).append("\" Obligatorio=\"").append(Obligatorio).append("\" type=\"text\" class=\"form-control\" id=\"Campo_").append(modeloParametro_tabla.getId()).append("_nuevo\" min=\"0\" required>");
        } else if (Tipo_Campo.contains("Lista")) {
            Out_Sb
                    .append("<select name=\"").append(name).append("\" Tipo_Campo=\"Lista\" Pertenencia=\"").append(Campo_dB).append("\" Obligatorio=\"").append(Obligatorio).append("\" id=\"Lista_").append(modeloParametro_tabla.getId()).append("\" class=\"form-control\" required>")
                    .append("<option value=\"0\" selected disabled>Seleccione</option>");
            LinkedList<ModeloEnumeracion> ListaModeloEnumeracion = controladorEnumeracion.Read("S", modeloParametro_tabla.getLista());
            int i = 1;
            for (ModeloEnumeracion modeloEnumeracion : ListaModeloEnumeracion) {
                Out_Sb.append("<option value=\"").append(i).append("\">").append(modeloEnumeracion.getCampo()).append("</option>");
                i++;
            }
            Out_Sb.append("</select>");

        } else if (Tipo_Campo.contains("Date")) {

            Out_Sb
                    .append("<div class=\"form-group\">")
                    .append("<div class=\"input-group date\" id=\"myDatepicker\">")
                    .append("<input name=\"").append(name).append("\" Tipo_Campo=\"Date\" Pertenencia=\"").append(Campo_dB).append("\" Obligatorio=\"").append(Obligatorio).append("\" type=\"text\" class=\"form-control\" id=\"Campo_").append(modeloParametro_tabla.getId()).append("_nuevo\" name=\"").append(modeloParametro_tabla.getId()).append("_nuevo\" />")
                    .append("<span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-calendar\"></span></span>")
                    .append("</div>")
                    .append("</div>");
        } else if (Tipo_Campo.contains("Modelo")) {

            Out_Sb
                    .append("<div class=\"form-group\">")
                    .append("<div class='input-group date' id='visiblemodal").append(modeloParametro_tabla.getNombre_visible()).append("' data-toggle=\"modal\" data-target=\".bs-").append(modeloParametro_tabla.getId()).append("-modal-lg\">")
                    .append("<input type=\"hidden\" id=\"id_Campo_").append(modeloParametro_tabla.getId()).append("_nuevo\" name=\"id_Campo_").append(modeloParametro_tabla.getId()).append("_nuevo\">")
                    .append("<input name=\"").append(name).append("\" Tipo_Campo=\"Moedelo\" Pertenencia=\"").append(Campo_dB).append("\" Obligatorio=\"").append(Obligatorio).append("\" type='text' class=\"form-control\" id=\"Campo_").append(modeloParametro_tabla.getId()).append("_nuevo\" name=\"").append(modeloParametro_tabla.getNombre_visible()).append("_nuevo\" style=\"text-align:center;\" required/>")
                    .append("<span class=\"input-group-addon\">")
                    .append("<span class=\"glyphicon glyphicon-search\"></span>")
                    .append("</div>")
                    .append("</div>");

            String id_Tabla_Html = "IdTabla_" + modeloParametro_tabla.getId() + "_" + modeloParametro_tabla.getTabla_referencia();
            String id_parametro_tabla = modeloParametro_tabla.getId() + "";

            Modales_Externos_Sb
                    .append("<div id_parametro_tabla=\"").append(id_parametro_tabla).append("\" tabla=\"").append(id_Tabla_Html).append("\" modulo=\"").append(Modulo).append("\" id=\"Busqueda_").append(modeloParametro_tabla.getId()).append(modeloParametro_tabla.getTabla_referencia()).append("\" class=\"modal fade bs-").append(modeloParametro_tabla.getId()).append("-modal-lg\" tabindex=\"-1\" role=\"dialog\" aria-hidden=\"true\">")
                    .append("<div class=\"modal-dialog modal-lg\">")
                    .append("<div class=\"modal-content\">")
                    .append("<div class=\"modal-header\">")
                    .append("<h4 class=\"modal-title\"><b>").append(modeloParametro_tabla.getNombre_visible()).append("</b></h4>")
                    .append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span aria-hidden=\"true\">×</span>")
                    .append("</button>")
                    .append("</div>")
                    .append("<div class=\"modal-body\">")
                    //.append("<table id=\"").append(id_Tabla_Html).append("\" class=\"table table-responsive table-bordered\">")
                    .append("<table id=\"").append(id_Tabla_Html).append("\" class=\"table table-striped table-bordered\" style=\"width:100%\">")
                    .append(Tabla_Seleccionable(modeloParametro_tabla, Modulo))
                    .append("</table>")
                    .append("</div>")
                    .append("<div class=\"modal-footer\">")
                    .append("<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Cerrar</button>")
                    .append("</div>")
                    .append("</div>")
                    .append("</div>")
                    .append("</div>");

        }

        return Out_Sb + "";

    }

    public String Tabla_Seleccionable(ModeloParametro_tabla modeloParametro_tabla, String Modulo) {

        StringBuilder Out_Sb = new StringBuilder();
        Out_Sb
                .append("<thead>")
                .append("<tr>");
                //.append("<th>Opcion</th>");

        LinkedList<Object> Tabla = new LinkedList<>();

        //con = conexion.abrirConexion();
        try {
            PreparedStatement SQL = con.prepareStatement("SELECT * FROM " + modeloParametro_tabla.getTabla_referencia());
            ResultSet res = SQL.executeQuery();

            //get metadata
            ResultSetMetaData meta = null;
            meta = res.getMetaData();

            LinkedList<ModeloEnumeracion> ListaModeloEnumeracion = controladorEnumeracion.Read("S", "campo," + modeloParametro_tabla.getTabla_referencia());
            String id_tabla_enumeracion = "";
            for (ModeloEnumeracion modeloEnumeracion : ListaModeloEnumeracion) {
                id_tabla_enumeracion = modeloEnumeracion.getId().toString();
            }

            controladorParametro_tabla.Consultar(modeloParametro_tabla.getTabla_referencia(), id_tabla_enumeracion, Modulo);
            ListaModeloParametro_tabla_General = controladorParametro_tabla.Read("S", id_tabla_enumeracion, Modulo);

            //get column names
            int colCount = meta.getColumnCount();
            LinkedList<String> cols = new LinkedList<String>();
            for (int index = 1; index <= colCount; index++) {
                cols.add(meta.getColumnName(index));
                for (ModeloParametro_tabla modeloParametro_tabla1 : ListaModeloParametro_tabla_General) {
                    if (modeloParametro_tabla1.getNombre_campo_bd().contentEquals(meta.getColumnName(index)) && modeloParametro_tabla1.getVisualizar().contentEquals("Si")) {
                        Out_Sb.append("<th nowrap>").append(modeloParametro_tabla1.getNombre_visible()).append("</th>");
                    }
                }
            }

            Out_Sb
                    .append("</tr>")
                    .append("</thead>");
//                    .append("<tbody>")
//                    .append("<tr>");
//
//            for (int index = 1; index <= colCount; index++) {
//                cols.add(meta.getColumnName(index));
//                for (ModeloParametro_tabla modeloParametro_tabla1 : ListaModeloParametro_tabla_General) {
//                    if (modeloParametro_tabla1.getNombre_campo_bd().contentEquals(meta.getColumnName(index)) && modeloParametro_tabla1.getVisualizar().contentEquals("Si")) {
//                        Out_Sb.append("<td></td>");
//                    }
//                }
//            }
//
//            Out_Sb.append("</tr>")
//                    .append("</tbody>");

            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorvencimiento" + e);
        }

        return Out_Sb + "";
    }

    public String Datos_Tabla_Dinamica(ModeloParametro_tabla modeloParametro_tabla, String Modulo) {

        StringBuilder Out_Sb = new StringBuilder();
        Out_Sb
                .append("<thead>")
                .append("<tr>");
        //.append("<th>Opcion</th>");

        //con = conexion.abrirConexion();
        try {
            PreparedStatement SQL = con.prepareStatement("SELECT * FROM " + modeloParametro_tabla.getTabla_referencia());
            ResultSet res = SQL.executeQuery();

            //get metadata
            ResultSetMetaData meta = null;
            meta = res.getMetaData();

            LinkedList<ModeloEnumeracion> ListaModeloEnumeracion = controladorEnumeracion.Read("S", "campo," + modeloParametro_tabla.getTabla_referencia());
            String id_tabla_enumeracion = "";
            for (ModeloEnumeracion modeloEnumeracion : ListaModeloEnumeracion) {
                id_tabla_enumeracion = modeloEnumeracion.getId().toString();
            }

            controladorParametro_tabla.Consultar(modeloParametro_tabla.getTabla_referencia(), id_tabla_enumeracion, Modulo);
            ListaModeloParametro_tabla_General = controladorParametro_tabla.Read("S", id_tabla_enumeracion, Modulo);

            //get column names
            int colCount = meta.getColumnCount();
            LinkedList<String> cols = new LinkedList<String>();
            for (int index = 1; index <= colCount; index++) {
                cols.add(meta.getColumnName(index));
//                if (meta.getColumnName(index).contentEquals("id")) {
//                    Out_Sb.append("<th style='display:none;'>id</th>");
//                }
                for (ModeloParametro_tabla modeloParametro_tabla1 : ListaModeloParametro_tabla_General) {
                    if (modeloParametro_tabla1.getNombre_campo_bd().contentEquals(meta.getColumnName(index)) && modeloParametro_tabla1.getVisualizar().contentEquals("Si")) {
                        Out_Sb.append("<th nowrap>").append(modeloParametro_tabla1.getNombre_visible()).append("</th>");
                    }
                }
            }

            Out_Sb
                    .append("</tr>")
                    .append("</thead>")
                    .append("<tbody>");

            //fetch out rows
            LinkedList<HashMap<String, Object>> rows = new LinkedList<HashMap<String, Object>>();

            while (res.next()) {

                Out_Sb
                        .append("<tr>");
                //.append("<td class=\"text-center\">")
                //                        .append("<button class=\"SetFormularioTabla btn btn-warning btn-xs\"title=\"Editar\"")
                //                        .append("type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-clipboard\"></i> </button>")
                //.append("</td>");

                int i = 0;
                HashMap<String, Object> row = new HashMap<String, Object>();
                for (String colName : cols) {
                    Object val = res.getObject(colName);
                    row.put(colName, val);

//                    if(colName.contentEquals("id"))
//                    {
//                        Out_Sb.append("<td style='display:none;'>").append(val.toString()).append("</td>");
//                    }
                    for (ModeloParametro_tabla modeloParametro_tabla1 : ListaModeloParametro_tabla_General) {
                        if (modeloParametro_tabla1.getNombre_campo_bd().contentEquals(colName) && modeloParametro_tabla1.getVisualizar().contentEquals("Si")) {
                            if (val == null) {
                                Out_Sb.append("<td> - </td>");
                            } else {
                                Out_Sb.append("<td nowrap>").append(val.toString()).append("</td>");
                            }

                        }
                    }
                }
                rows.add(row);
                Out_Sb.append("</tr>");

            }
            Out_Sb.append("</tbody>");

            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorvencimiento" + e);
        }

        return Out_Sb + "";
    }

    public String Datos_Tabla_Dinamica_Gson(ModeloParametro_tabla modeloParametro_tabla, String Modulo) {

        StringBuilder Out_Sb = new StringBuilder();

        Gson gson = new GsonBuilder().serializeNulls().create();
        
        //con = conexion.abrirConexion();
        try {
            PreparedStatement SQL = con.prepareStatement("SELECT * FROM " + modeloParametro_tabla.getTabla_referencia());
            ResultSet res = SQL.executeQuery();

            //get metadata
            ResultSetMetaData meta = null;
            meta = res.getMetaData();

            LinkedList<ModeloEnumeracion> ListaModeloEnumeracion = controladorEnumeracion.Read("S", "campo," + modeloParametro_tabla.getTabla_referencia());
            String id_tabla_enumeracion = "";
            for (ModeloEnumeracion modeloEnumeracion : ListaModeloEnumeracion) {
                id_tabla_enumeracion = modeloEnumeracion.getId().toString();
            }

            controladorParametro_tabla.Consultar(modeloParametro_tabla.getTabla_referencia(), id_tabla_enumeracion, Modulo);
            ListaModeloParametro_tabla_General = controladorParametro_tabla.Read("S", id_tabla_enumeracion, Modulo);

            //get column names
            int colCount = meta.getColumnCount();
            LinkedList<String> cols = new LinkedList<String>();
            

            LinkedList<HashMap<String, Object>> Columnas = new LinkedList<HashMap<String, Object>>();
            
            for (int index = 1; index <= colCount; index++) {
                
                HashMap<String, Object> columna = new HashMap<String, Object>();
                
                cols.add(meta.getColumnName(index));

                for (ModeloParametro_tabla modeloParametro_tabla1 : ListaModeloParametro_tabla_General) {
                    if (modeloParametro_tabla1.getNombre_campo_bd().contentEquals(meta.getColumnName(index)) && modeloParametro_tabla1.getVisualizar().contentEquals("Si")) {
                        columna.put("data", meta.getColumnName(index));
                        Columnas.add(columna);
                    }
                }
                
            }            

            //fetch out rows
            LinkedList<HashMap<String, Object>> rows = new LinkedList<HashMap<String, Object>>();

            while (res.next()) {

                int i = 0;
                HashMap<String, Object> row = new HashMap<String, Object>();
                for (String colName : cols) {
                    Object val = res.getObject(colName);
                    row.put(colName, val);
                }
                rows.add(row);

            }
            
            String COLUMNAS = gson.toJson(Columnas);
            String FILAS = gson.toJson(rows).replace("*", "");
            Out_Sb.append(COLUMNAS).append("*").append(FILAS);

            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorvencimiento" + e);
        }

        return Out_Sb + "";
    }

    public String Datos_Tabla_Dinamica_Copia(ModeloParametro_tabla modeloParametro_tabla, String Modulo) {

        StringBuilder Out_Sb = new StringBuilder();
        Out_Sb
                .append("<thead>")
                .append("<tr>")
                .append("<th>Opcion</th>");

        LinkedList<Object> Tabla = new LinkedList<>();

        //con = conexion.abrirConexion();
        try {
            PreparedStatement SQL = con.prepareStatement("SELECT * FROM " + modeloParametro_tabla.getTabla_referencia());
            ResultSet res = SQL.executeQuery();

            //get metadata
            ResultSetMetaData meta = null;
            meta = res.getMetaData();

            LinkedList<ModeloEnumeracion> ListaModeloEnumeracion = controladorEnumeracion.Read("S", "campo," + modeloParametro_tabla.getTabla_referencia());
            String id_tabla_enumeracion = "";
            for (ModeloEnumeracion modeloEnumeracion : ListaModeloEnumeracion) {
                id_tabla_enumeracion = modeloEnumeracion.getId().toString();
            }

            controladorParametro_tabla.Consultar(modeloParametro_tabla.getTabla_referencia(), id_tabla_enumeracion, Modulo);
            ListaModeloParametro_tabla_General = controladorParametro_tabla.Read("S", id_tabla_enumeracion, Modulo);

            //get column names
            int colCount = meta.getColumnCount();
            LinkedList<String> cols = new LinkedList<String>();
            for (int index = 1; index <= colCount; index++) {
                cols.add(meta.getColumnName(index));
//                if (meta.getColumnName(index).contentEquals("id")) {
//                    Out_Sb.append("<th style='display:none;'>id</th>");
//                }
                for (ModeloParametro_tabla modeloParametro_tabla1 : ListaModeloParametro_tabla_General) {
                    if (modeloParametro_tabla1.getNombre_campo_bd().contentEquals(meta.getColumnName(index)) && modeloParametro_tabla1.getVisualizar().contentEquals("Si")) {
                        Out_Sb.append("<th nowrap>").append(modeloParametro_tabla1.getNombre_visible()).append("</th>");
                    }
                }
            }

            Out_Sb
                    .append("</tr>")
                    .append("</thead>")
                    .append("<tbody>");

            //fetch out rows
            LinkedList<HashMap<String, Object>> rows = new LinkedList<HashMap<String, Object>>();

            while (res.next()) {

                Out_Sb
                        .append("<tr>")
                        .append("<td class=\"text-center\">")
                        //                        .append("<button class=\"SetFormularioTabla btn btn-warning btn-xs\"title=\"Editar\"")
                        //                        .append("type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-clipboard\"></i> </button>")
                        .append("</td>");

                int i = 0;
                HashMap<String, Object> row = new HashMap<String, Object>();
                for (String colName : cols) {
                    Object val = res.getObject(colName);
                    row.put(colName, val);

//                    if(colName.contentEquals("id"))
//                    {
//                        Out_Sb.append("<td style='display:none;'>").append(val.toString()).append("</td>");
//                    }
                    for (ModeloParametro_tabla modeloParametro_tabla1 : ListaModeloParametro_tabla_General) {
                        if (modeloParametro_tabla1.getNombre_campo_bd().contentEquals(colName) && modeloParametro_tabla1.getVisualizar().contentEquals("Si")) {
                            if (val == null) {
                                Out_Sb.append("<td> - </td>");
                            } else {
                                Out_Sb.append("<td nowrap>").append(val.toString()).append("</td>");
                            }

                        }
                    }
                }
                rows.add(row);
                Out_Sb.append("</tr>");

            }
            Out_Sb.append("</tbody>");

            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorvencimiento" + e);
        }

        return Out_Sb + "";
    }

}
