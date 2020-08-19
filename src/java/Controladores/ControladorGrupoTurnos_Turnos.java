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
import javax.servlet.http.HttpSession;
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
    String user;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();
    ControladorGrupo_horario controladorGrupoTurnos = new ControladorGrupo_horario();
    ControladorTurnos controladorTurnos = new ControladorTurnos();

    /**
     * Permite insertar la informcion envidsada desde la vista por el usurio a
     * la base de datos.
     *
     * @author: Carlos A Dominguez D
     * @param request este es enviado desdes la interface
     * @return String retorna si la inservcion fue satistfacoria en la base de
     * datos o si no es satisfactoria
     * @version: 12/05/2020
     */
    public String Insert(HttpServletRequest request, HttpServletResponse response) {
        LinkedList<ModeloGrupoTurnos_Turnos> ListaModeloGrupoTurnos_Turnos = new LinkedList<>();

        ControladorGrupo_horario controladorGrupo_horario = new ControladorGrupo_horario();
        ControladorTurnos controladorTurnos = new ControladorTurnos();

        ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos = new ModeloGrupoTurnos_Turnos();
        modeloGrupoTurnos_Turnos.setIdModelo_Grupo_Turnos(controladorGrupo_horario.getModelo(Integer.parseInt(request.getParameter("idgrupohorario"))));
        modeloGrupoTurnos_Turnos.setIdModelo_Turnos(controladorTurnos.getModelo(Integer.parseInt(request.getParameter("idhorario"))));

        if ("Lunes".equals(request.getParameter("lunes"))) {
            ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos_Lunes = new ModeloGrupoTurnos_Turnos();
            modeloGrupoTurnos_Turnos_Lunes.setIdModelo_Grupo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos());
            modeloGrupoTurnos_Turnos_Lunes.setIdModelo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Turnos());
            modeloGrupoTurnos_Turnos_Lunes.setDia_Semana(request.getParameter("lunes"));
            ListaModeloGrupoTurnos_Turnos.add(modeloGrupoTurnos_Turnos_Lunes);
        }
        if ("Martes".equals(request.getParameter("martes"))) {
            ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos_Martes = new ModeloGrupoTurnos_Turnos();
            modeloGrupoTurnos_Turnos_Martes.setIdModelo_Grupo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos());
            modeloGrupoTurnos_Turnos_Martes.setIdModelo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Turnos());
            modeloGrupoTurnos_Turnos_Martes.setDia_Semana(request.getParameter("martes"));
            ListaModeloGrupoTurnos_Turnos.add(modeloGrupoTurnos_Turnos_Martes);
        }
        if ("Miercoles".equals(request.getParameter("miercoles"))) {
            ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos_Miercoles = new ModeloGrupoTurnos_Turnos();
            modeloGrupoTurnos_Turnos_Miercoles.setIdModelo_Grupo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos());
            modeloGrupoTurnos_Turnos_Miercoles.setIdModelo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Turnos());
            modeloGrupoTurnos_Turnos_Miercoles.setDia_Semana(request.getParameter("miercoles"));
            ListaModeloGrupoTurnos_Turnos.add(modeloGrupoTurnos_Turnos_Miercoles);
        }
        if ("Jueves".equals(request.getParameter("jueves"))) {
            ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos_Jueves = new ModeloGrupoTurnos_Turnos();
            modeloGrupoTurnos_Turnos_Jueves.setIdModelo_Grupo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos());
            modeloGrupoTurnos_Turnos_Jueves.setIdModelo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Turnos());
            modeloGrupoTurnos_Turnos_Jueves.setDia_Semana(request.getParameter("jueves"));
            ListaModeloGrupoTurnos_Turnos.add(modeloGrupoTurnos_Turnos_Jueves);
        }
        if ("Viernes".equals(request.getParameter("viernes"))) {
            ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos_Viernes = new ModeloGrupoTurnos_Turnos();
            modeloGrupoTurnos_Turnos_Viernes.setIdModelo_Grupo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos());
            modeloGrupoTurnos_Turnos_Viernes.setIdModelo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Turnos());
            modeloGrupoTurnos_Turnos_Viernes.setDia_Semana(request.getParameter("viernes"));
            ListaModeloGrupoTurnos_Turnos.add(modeloGrupoTurnos_Turnos_Viernes);
        }
        if ("Sabado".equals(request.getParameter("sabado"))) {
            ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos_Sabado = new ModeloGrupoTurnos_Turnos();
            modeloGrupoTurnos_Turnos_Sabado.setIdModelo_Grupo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos());
            modeloGrupoTurnos_Turnos_Sabado.setIdModelo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Turnos());
            modeloGrupoTurnos_Turnos_Sabado.setDia_Semana(request.getParameter("sabado"));
            ListaModeloGrupoTurnos_Turnos.add(modeloGrupoTurnos_Turnos_Sabado);
        }
        if ("Domingo".equals(request.getParameter("domingo"))) {
            ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos_Domingo = new ModeloGrupoTurnos_Turnos();
            modeloGrupoTurnos_Turnos_Domingo.setIdModelo_Grupo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos());
            modeloGrupoTurnos_Turnos_Domingo.setIdModelo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Turnos());
            modeloGrupoTurnos_Turnos_Domingo.setDia_Semana(request.getParameter("domingo"));
            ListaModeloGrupoTurnos_Turnos.add(modeloGrupoTurnos_Turnos_Domingo);
        }
        if ("Festivo".equals(request.getParameter("festivo"))) {
            ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos_Festivo = new ModeloGrupoTurnos_Turnos();
            modeloGrupoTurnos_Turnos_Festivo.setIdModelo_Grupo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos());
            modeloGrupoTurnos_Turnos_Festivo.setIdModelo_Turnos(modeloGrupoTurnos_Turnos.getIdModelo_Turnos());
            modeloGrupoTurnos_Turnos_Festivo.setDia_Semana(request.getParameter("festivo"));
            ListaModeloGrupoTurnos_Turnos.add(modeloGrupoTurnos_Turnos_Festivo);
        }

        if (ListaModeloGrupoTurnos_Turnos.size() > 0) {
            for (ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turno : ListaModeloGrupoTurnos_Turnos) {
                if ("".equals(request.getParameter("id"))) {
                    HttpSession session = request.getSession();
                    user = (String) session.getAttribute("usuario");
                    resultado = Insert(modeloGrupoTurnos_Turno);
                } else {
                    modeloGrupoTurnos_Turno.setId(Integer.parseInt(request.getParameter("id")));
                    resultado = Update(modeloGrupoTurnos_Turno);
                }
            }
        }
        return resultado;
    }

    private String Insert(ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turno) {

        try {
            con = conexion.abrirConexion();
            try {
                SQL = con.prepareStatement("INSERT INTO `grupo_horario_horario`("
                        + "`id_grupo_horario`,"
                        + "`id_horario`,"
                        + "`dia_semana`,"
                        + "`estado`) "
                        + "VALUE (?,?,?,?);", SQL.RETURN_GENERATED_KEYS);
                SQL.setInt(1, modeloGrupoTurnos_Turno.getIdModelo_Grupo_Turnos().getId());
                SQL.setInt(2, modeloGrupoTurnos_Turno.getIdModelo_Turnos().getId());
                SQL.setString(3, modeloGrupoTurnos_Turno.getDia_Semana());
                SQL.setString(4, "S");
                if (SQL.executeUpdate() > 0) {
                    ControladorAuditoria auditoria = new ControladorAuditoria();
                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int i = (int) generatedKeys.getLong(1);
                            auditoria.Insert("insertar", "grupo_horario_horario", user, i, "Se inserto el registro.", "", "");
                        }
                        resultado = "1";
                        SQL.close();
                        con.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Controladores.ControladorGrupoTurnos_Turnos.Insert() " + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Controladores.ControladorGrupoTurnos_Turnos.Insert() " + e);
            resultado = "-3";
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
            listaModelo_Grupo_Turnos_Turnos = Read("S");
            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<td>Grupo Turno</td>";
            out += "<td>Turno</td>";
            out += "<td>Dia</td>";
            out += "<td>Opciones</td>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos : listaModelo_Grupo_Turnos_Turnos) {
                out += "<tr>";
                out += "<td>" + modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos().getNombre() + "</td>";
                out += "<td>" + modeloGrupoTurnos_Turnos.getIdModelo_Turnos().getDescripcion() + "</td>";
                out += "<td class=\"text-center\">" + modeloGrupoTurnos_Turnos.getDia_Semana() + "</td>";
                out += "<td class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-sm\"title=\"Editar\"";
                out += "data-id=\"" + modeloGrupoTurnos_Turnos.getId() + "\"";
                out += "data-idgrupoturnos=\"" + modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos().getId() + "\"";
                out += "data-idturnos=\"" + modeloGrupoTurnos_Turnos.getIdModelo_Turnos().getId() + "\"";
                out += "data-dia=\"" + modeloGrupoTurnos_Turnos.getDia_Semana() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> </button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-danger btn-sm\"title=\"Eliminar\"";
                out += "data-id=\"" + modeloGrupoTurnos_Turnos.getId() + "\"";
                out += "type=\"button\"><i id=\"IdClonar\" name=\"Clonar\" class=\"fa fa-trash\"></i> </button>";
                //Boton Clonar
//                out += "<button class=\"SetFormulario btn btn-info btn-sm\"title=\"Clonar\"";
//                out += "data-id=\"\"";
//                out += "data-idgrupoturnos=\"" + modeloGrupoTurnos_Turnos.getIdModelo_Grupo_Turnos().getId() + "\"";
//                out += "data-idturnos=\"" + modeloGrupoTurnos_Turnos.getIdModelo_Turnos().getId() + "\"";
//                out += "data-dia=\"" + modeloGrupoTurnos_Turnos.getDia_Semana() + "\"";
//                out += "type=\"button\"><i id=\"IdClonar\" name=\"Clonar\" class=\"fa fa-copy\"></i> </button>";
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
    private LinkedList<ModeloGrupoTurnos_Turnos> Read(String estado) {
        LinkedList<ModeloGrupoTurnos_Turnos> listaModeloGrupoTurnos_Turnos = new LinkedList<ModeloGrupoTurnos_Turnos>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`, "
                    + "`id_grupo_horario`, "
                    + "`id_horario`, "
                    + "`dia_semana`, "
                    + "`estado` "
                    + "FROM "
                    + "`grupo_horario_horario` "
                    + "WHERE estado = ? ;");
            SQL.setString(1, estado);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos = new ModeloGrupoTurnos_Turnos();
                modeloGrupoTurnos_Turnos.setId(res.getInt("id"));
                modeloGrupoTurnos_Turnos.setIdModelo_Grupo_Turnos(controladorGrupoTurnos.getModelo(res.getInt("id_grupo_horario")));
                modeloGrupoTurnos_Turnos.setIdModelo_Turnos(controladorTurnos.getModelo(res.getInt("id_horario")));
                modeloGrupoTurnos_Turnos.setDia_Semana(res.getString("dia_semana"));
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

    /**
     * Permite obtener el modelo de datos este metodo requiere de entrada el Id
     * del modelo
     *
     * @author: Carlos A Dominguez D
     * @param Id Valor de Campo enteropara realizar la consulta de datos por Id
     * @return ModeloGrupoTurnos_Turnos Retorna el modelo de los datos en su
     * formato Modelos
     * @version: 12/05/2020
     */
    public ModeloGrupoTurnos_Turnos getModelo(int Id) {
        ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos = new ModeloGrupoTurnos_Turnos();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`IdGrupoHorario`,"
                    + "`IdHorario`,"
                    + "`diaSeman` "
                    + "FROM `grupohorario_horario`"
                    + "WHERE id = ?;");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {

                modeloGrupoTurnos_Turnos.setId(res.getInt("id"));
                modeloGrupoTurnos_Turnos.setIdModelo_Grupo_Turnos(controladorGrupoTurnos.getModelo(res.getInt("IdGrupoHorario")));
                modeloGrupoTurnos_Turnos.setIdModelo_Turnos(controladorTurnos.getModelo(res.getInt("IdHorario")));
                modeloGrupoTurnos_Turnos.setDia_Semana(res.getString("diaSeman"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return modeloGrupoTurnos_Turnos;
    }

    private String Update(ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turno) {
        try {
            con = conexion.abrirConexion();
            try {
                if ("N".equals(modeloGrupoTurnos_Turno.getEstado())) {
                    SQL = con.prepareStatement("UPDATE grupo_horario_horario SET "
                            + " estado = ? "
                            + " WHERE id = ? ");
                    SQL.setString(1, modeloGrupoTurnos_Turno.getEstado());
                    SQL.setInt(2, modeloGrupoTurnos_Turno.getId());
                } else {
                    SQL = con.prepareStatement("UPDATE `grupo_horario_horario` SET "
                            + "`id_grupo_horario` = ?, "
                            + "`id_horario` = ?, "
                            + "`dia_semana` = ? "                            
                            + "WHERE `id` = ?;");
                    SQL.setInt(1, modeloGrupoTurnos_Turno.getIdModelo_Grupo_Turnos().getId());
                    SQL.setInt(2, modeloGrupoTurnos_Turno.getIdModelo_Turnos().getId());
                    SQL.setString(3, modeloGrupoTurnos_Turno.getDia_Semana());                    
                    SQL.setInt(4, modeloGrupoTurnos_Turno.getId());                    
                }
                if (SQL.executeUpdate() > 0) {
                    resultado = "4";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Controladores.ControladorGrupoTurnos_Turnos.Update() " + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Controladores.ControladorGrupoTurnos_Turnos.Update() " + e);
            resultado = "-3";
        }
        return resultado;
    }

    public String Delete(HttpServletRequest request, HttpServletResponse response) {
         if (!"".equals(request.getParameter("id"))) {
            ModeloGrupoTurnos_Turnos modeloGrupoTurnos_Turnos = new ModeloGrupoTurnos_Turnos();
            modeloGrupoTurnos_Turnos.setId(Integer.parseInt(request.getParameter("id")));
            modeloGrupoTurnos_Turnos.setEstado("N");
            resultado = Update(modeloGrupoTurnos_Turnos);
            if (resultado.equals("4"))
            {
                resultado = "2";                
            }
        }
        return resultado;
    }

}
