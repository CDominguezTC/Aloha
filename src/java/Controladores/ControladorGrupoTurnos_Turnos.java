package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloGrupoTurnos_Turnos;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Esta clase permite controlar los eventos de Grupo Turnos contrine Insert -
 * Update, Delete, Read
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorGrupoTurnos_Turnos {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    ControladorGrupoTurnos controladorGrupoTurnos = new ControladorGrupoTurnos();
    ControladorTurnos controladorTurnos = new ControladorTurnos();

    public String Insert(HttpServletRequest request) {
        if ("".equals(request.getParameter("id"))) {
            ModeloGrupoTurnos_Turnos modelo_GrupoTurnos_Turnos = new ModeloGrupoTurnos_Turnos();
            modelo_GrupoTurnos_Turnos.setId(0);
            modelo_GrupoTurnos_Turnos.setIdModelo_Grupo_Turnos(controladorGrupoTurnos.getModelo(Integer.parseInt(request.getParameter("idgrupohorario"))));
            modelo_GrupoTurnos_Turnos.setIdModelo_Turnos(controladorTurnos.getModelo(Integer.parseInt(request.getParameter("idhorario"))));
            modelo_GrupoTurnos_Turnos.setDia_Semana(request.getParameter("dia"));
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("INSERT INTO `grupohorario_horario`("
                            + "`IdGrupoHorario`,"
                            + "`IdHorario`,"
                            + "`diaSeman`) "
                            + "VALUE ("
                            + "?,?,?);");
                    SQL.setInt(1, modelo_GrupoTurnos_Turnos.getIdModelo_Grupo_Turnos().getId());
                    SQL.setInt(2, modelo_GrupoTurnos_Turnos.getIdModelo_Turnos().getId());
                    SQL.setString(3, modelo_GrupoTurnos_Turnos.getDia_Semana());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                resultado = "-3";
            }
        } else {
            ModeloGrupoTurnos_Turnos modelo_GrupoTurnos_Turnos = new ModeloGrupoTurnos_Turnos();
            modelo_GrupoTurnos_Turnos.setId(Integer.parseInt(request.getParameter("id")));
            modelo_GrupoTurnos_Turnos.setIdModelo_Grupo_Turnos(controladorGrupoTurnos.getModelo(Integer.parseInt(request.getParameter("idgrupohorario"))));
            modelo_GrupoTurnos_Turnos.setIdModelo_Turnos(controladorTurnos.getModelo(Integer.parseInt(request.getParameter("idhorario"))));
            modelo_GrupoTurnos_Turnos.setDia_Semana(request.getParameter("dia"));
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("UPDATE `grupohorario_horario` SET "
                            + "`IdGrupoHorario` = ?,"
                            + "`IdHorario` = ?,"
                            + "`diaSeman` = ?"
                            + " WHERE `id` = ?;");
                    SQL.setInt(1, modelo_GrupoTurnos_Turnos.getIdModelo_Grupo_Turnos().getId());
                    SQL.setInt(2, modelo_GrupoTurnos_Turnos.getIdModelo_Turnos().getId());
                    SQL.setString(3, modelo_GrupoTurnos_Turnos.getDia_Semana());
                    SQL.setInt(4, modelo_GrupoTurnos_Turnos.getId());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    public String Delete(HttpServletRequest request) {
        LinkedList<ModeloGrupoTurnos_Turnos> ListaModeloGrupoTurnos_Turnos = new LinkedList<>();
        String[] checkboxselect = request.getParameterValues("checkbox[]");
        if (checkboxselect != null) {
            for (String checkboxselect1 : checkboxselect) {
                if (!"".equals(checkboxselect1)) {
                    String idtmp = checkboxselect1;
                    ModeloGrupoTurnos_Turnos modelo = new ModeloGrupoTurnos_Turnos();
                    modelo.setId(Integer.parseInt(checkboxselect1));
                    ListaModeloGrupoTurnos_Turnos.add(modelo);
                }
            }
        } else {
            if (!"".equals(request.getParameter("id"))) {
                String idtmp = request.getParameter("id");
                ModeloGrupoTurnos_Turnos modelo = new ModeloGrupoTurnos_Turnos();
                modelo.setId(Integer.parseInt(request.getParameter("id")));
                ListaModeloGrupoTurnos_Turnos.add(modelo);
            }
        }

        if (ListaModeloGrupoTurnos_Turnos.size() > 0) {
            try {
                con = conexion.abrirConexion();
                try {
                    for (ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos : ListaModeloGrupoTurnos_Turnos) {
                        SQL = con.prepareStatement("DELETE FROM `grupohorario_horario` "
                                + "WHERE `Id` = ?;");
                        SQL.setInt(1, modeloGrupoTurnos_Turnos.getId());
                        if (SQL.executeUpdate() > 0) {
                            resultado = "2";
                        }
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    resultado = "-2";
                }
                SQL.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    /**
     * Permite listar la información de la tabla de Relacion Horario - Turnso
     *
     * @author: Carlos A Dominguez D
     * @param request este es enviado desdes la interface
     * @param response este es enviado desde la interface
     * @return String retorna el estilo de la tabla en un String
     * @version: 07/05/2020
     */
    public String Read(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        try {
            LinkedList<ModeloGrupoTurnos_Turnos> listaModelo_Grupo_Turnos_Turnos = new LinkedList<>();
            listaModelo_Grupo_Turnos_Turnos = Read();
            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<td>Seleccione</td>";
            out += "<td>Grupo Turno</td>";
            out += "<td>Turno</td>";
            out += "<td>Dia</td>";
            out += "<td>Opciones</td>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos : listaModelo_Grupo_Turnos_Turnos) {
                out += "<tr>";
                out += "<td><input type=\"checkbox\" id=\"" + modeloGrupoTurnos_Turnos.getId() + "\" name=\"" + modeloGrupoTurnos_Turnos.getId() + "\" value=\"" + modeloGrupoTurnos_Turnos.getId() + "\"/></td>";
                out += "<td>" + modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos().getDescripcion() + "</td>";
                out += "<td>" + modeloGrupoTurnos_Turnos.getIdModelo_Turnos().getDescripcion() + "</td>";
                out += "<td class=\"text-center\">" + modeloGrupoTurnos_Turnos.getDia_Semana() + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"title=\"Editar\"";
                out += "data-id=\"" + modeloGrupoTurnos_Turnos.getId() + "\"";
                out += "data-idgrupoturnos=\"" + modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos().getId() + "\"";
                out += "data-idturnos=\"" + modeloGrupoTurnos_Turnos.getIdModelo_Turnos().getId() + "\"";
                out += "data-dia=\"" + modeloGrupoTurnos_Turnos.getDia_Semana() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-xs\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloGrupoTurnos_Turnos.getId() + "\"";
                out += "type=\"button\"><i id=\"IdClonar\" name=\"Clonar\" class=\"fa fa-trash\"></i> </button>";
                //Boton Clonar
                out += "<button class=\"SetFormulario btn btn-info btn-xs\"title=\"Clonar\"";
                out += "data-id=\"\"";
                out += "data-idgrupoturnos=\"" + modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos().getId() + "\"";
                out += "data-idturnos=\"" + modeloGrupoTurnos_Turnos.getIdModelo_Turnos().getId() + "\"";
                out += "data-dia=\"" + modeloGrupoTurnos_Turnos.getDia_Semana() + "\"";
                out += "type=\"button\"><i id=\"IdClonar\" name=\"Clonar\" class=\"fa fa-copy\"></i> </button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";

        } catch (Exception e) {
            System.out.println("Error en el proceso de la tabla " + e.getMessage());
        }
        return out;
    }

    /**
     * Permite listar la información de la tabla de Turnos Metodo Private
     *
     * @author: Carlos A Dominguez D
     * @return LinkedList
     * @version: 07/05/2020
     */
    private LinkedList<ModeloGrupoTurnos_Turnos> Read() {
        LinkedList<ModeloGrupoTurnos_Turnos> listaModeloGrupoTurnos_Turnos = new LinkedList<ModeloGrupoTurnos_Turnos>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`IdGrupoHorario`,"
                    + "`IdHorario`,"
                    + "`diaSeman` "
                    + "FROM `grupohorario_horario`;");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos = new ModeloGrupoTurnos_Turnos();
                modeloGrupoTurnos_Turnos.setId(res.getInt("id"));
                modeloGrupoTurnos_Turnos.setIdModelo_Grupo_Turnos(controladorGrupoTurnos.getModelo(res.getInt("IdGrupoHorario")));
                modeloGrupoTurnos_Turnos.setIdModelo_Turnos(controladorTurnos.getModelo(res.getInt("IdHorario")));
                modeloGrupoTurnos_Turnos.setDia_Semana(res.getString("diaSeman"));
                listaModeloGrupoTurnos_Turnos.add(modeloGrupoTurnos_Turnos);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listaModeloGrupoTurnos_Turnos;
    }

}
