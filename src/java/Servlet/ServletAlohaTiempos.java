/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controladores.ControladorArea;
import Controladores.ControladorAsociacion_grupo_consumo_horario_consumo;
import Controladores.ControladorAsociacion_grupo_vencimiento;
import Controladores.ControladorAuditoria;
import Controladores.ControladorAutoriza_consumo;
import Controladores.ControladorCargo;
import Controladores.ControladorCargo_hoteleria;
import Controladores.ControladorCiudad;
import Controladores.ControladorCentro_costo;
import Controladores.ControladorConsumo;
import Controladores.ControladorConsumo_hoteleria;
import Controladores.ControladorDependencia;
import Controladores.ControladorDispositivos;
import Controladores.ControladorEmpresa;
import Controladores.ControladorEnumeracion;
import Controladores.ControladorFestivo;
import Controladores.ControladorFuncion;
import Controladores.ControladorGrupoTurnos_Turnos;
import Controladores.ControladorGrupo_consumo;
import Controladores.ControladorGrupo_horario;
//import Controladores.ControladorGrupoTurnos_Turnos;
import Controladores.ControladorHorario_consumo;
import Controladores.ControladorImagen;
import Controladores.ControladorInicioSesion;
import Controladores.ControladorLiquidacionCasino;
import Controladores.ControladorLog_error;
import Controladores.ControladorParametro_tabla;
import Controladores.ControladorPeriodos;
import Controladores.ControladorPermisos;
import Controladores.ControladorPersona;
import Controladores.ControladorRol;
import Controladores.ControladorTemplate;
import Controladores.ControladorTipo_consumo;
import Controladores.ControladorTurnos;
import Controladores.ControladorUsuario;
import Controladores.ControladorVencimiento;
import Controladores.ControladorVisita;
import Herramienta.Herramienta;
import Modelo.ModeloPersona;
import Modelo.ModeloUsuario;
import Tools.Tools;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ServletAlohaTiempos extends HttpServlet {

    HttpSession session;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /*
         * try (PrintWriter out = response.getWriter ())
         * {
         * out.println ("<!DOCTYPE html>");
         * out.println ("<html>");
         * out.println ("<head>");
         * out.println ("<title>Servlet ServletAlohaTiempos</title>");
         * out.println ("</head>");
         * out.println ("<body>");
         * out.println ("<h1>Servlet ServletAlohaTiempos at " +
         * request.getContextPath () + "</h1>");
         * out.println ("</body>");
         * out.println ("</html>");
         * }
         */
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        String Formulario = request.getParameter("frmm");
        Herramienta herramienta = new Herramienta();
        String Resultado = "";
        String Accion = null;
        String respuesta = "";
        session = request.getSession(false);
        if (session != null) {
            //String name = (String)session.getAttribute("name");  
            respuesta = "true";
            //out.print("Hello, "+name+" Welcome to Profile");  
        } else {

            respuesta = "false";
            //out.print("Please login first");  
            //request.getRequestDispatcher("login.html").include(request, response);  
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        response.getWriter().write(respuesta);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ControladorLiquidacionCasino controladorLiquidacionCasino = new ControladorLiquidacionCasino();
        try {
            response.setContentType("text/html;charset=UTF-8");
            String Formulario = request.getParameter("frm");
            Herramienta herramienta = new Herramienta();
            String Resultado = "";
            String Accion = null;
            switch (Formulario) {
                case "IndexJSP":
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Login":
                            //En este punto se accesoa al controlador para la validacion de los datos del user
                            Resultado = "true";
                            ControladorInicioSesion controladorIni = new ControladorInicioSesion();
                            Resultado = controladorIni.autenticacion(request);
                            if ("true".equals(Resultado)) {
                                String usuario = request.getParameter("user");
                                //Instancio del controlador y el modelo de usuarios
                                ControladorUsuario controladorUsuario = new ControladorUsuario();
                                ModeloUsuario modeloUsuario = new ModeloUsuario();
                                modeloUsuario = controladorUsuario.getModelo(controladorUsuario.idUsuario(usuario));
                                //String pw = request.getParameter("pass");
                                HttpSession session = request.getSession();
                                session.setAttribute("usuario", usuario);
                                // agrego el modelo al session
                                session.setAttribute("modelousuario", modeloUsuario);
                            }
                            break;
                    }

                    break;
                case "LogErrorJSP":
                    ControladorLog_error log = new ControladorLog_error();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Read":
                            try {
                                Resultado = log.Read();
                            } catch (Exception e) {
                                ControladorLog_error error = new ControladorLog_error();
                                error.insertarError((String) session.getAttribute("usuario"), e.getMessage());
                            }
                            
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;
                case "Permisos":
                    Accion = request.getParameter("accion");
                    Tools tl = new Tools();
                    Resultado = tl.validoItem(request.getParameter("user"), Accion);
                    break;
                case "Password":
                    Accion = request.getParameter("accion");
                    switch (Accion) {

                        case "Descifrar":
                            String passw = request.getParameter("pass");
                            try {
                                tl = new Tools();
                                String con = tl.desencriptar(passw);

                                if (!"".equals(con)) {
                                    Resultado = con;
                                } else {
                                    Resultado = "false";
                                }

                            } catch (Exception e) {
                            }
                            break;

                        case "ValidarUsr":
                            String usr = request.getParameter("login");
                            try {
                                ControladorUsuario controladorU = new ControladorUsuario();
                                String resul = controladorU.validoLogin(usr);

                                if (!"false".equals(resul)) {
                                    Resultado = resul;
                                } else {
                                    Resultado = "false";
                                }

                            } catch (Exception e) {
                            }

                            break;

                        case "ValidarPw":
                            String psw = request.getParameter("passw");
                            String idusr = request.getParameter("idus");
                            try {
                                ControladorUsuario controladorU = new ControladorUsuario();
                                String resul = controladorU.validoPassword(idusr, psw);

                                if (!"false".equals(resul)) {
                                    Resultado = resul;
                                } else {
                                    Resultado = "false";
                                }

                            } catch (Exception e) {
                            }

                            break;

                        case "CambiarPw":
                            String pasw = request.getParameter("passwo");
                            String idusur = request.getParameter("idusu");
                            try {
                                ControladorUsuario controladorU = new ControladorUsuario();
                                String resul = controladorU.actualizoPassword(idusur, pasw);

                                if (!"false".equals(resul)) {
                                    Resultado = resul;
                                } else {
                                    Resultado = "false";
                                }

                            } catch (Exception e) {
                            }
                            break;

                        case "CambiarPwUs":
                            String usu = request.getParameter("usuario");
                            String npass = request.getParameter("npass");
                            try {
                                ControladorUsuario controladorU = new ControladorUsuario();
                                String resul = controladorU.actualizoPasswordUser(usu, npass);

                                if (!"false".equals(resul)) {
                                    Resultado = resul;
                                } else {
                                    Resultado = "false";
                                }

                            } catch (Exception e) {
                            }
                            break;
                    }
                    break;
                case "AreasJSP":
                    ControladorArea controladorAreas = new ControladorArea();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorAreas.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorAreas.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorAreas.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;
                case "EmpresaJSP":
                    ControladorEmpresa controladorEmpresas = new ControladorEmpresa();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorEmpresas.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorEmpresas.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorEmpresas.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;
                case "HorarioConsumoJSP":
                    ControladorHorario_consumo controladorHorarioConsumo = new ControladorHorario_consumo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorHorarioConsumo.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorHorarioConsumo.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorHorarioConsumo.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;
                case "CiudadJSP":
                    ControladorCiudad controladorCiudades = new ControladorCiudad();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorCiudades.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorCiudades.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorCiudades.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;

                case "TurnosJSP":
                    ControladorTurnos controladorTurnos = new ControladorTurnos();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorTurnos.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorTurnos.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorTurnos.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;

                case "CargosJSP":
                    ControladorCargo controladorCargo = new ControladorCargo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorCargo.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorCargo.Delete(request, response);
                            break;
                        //case "ReadTiempos":
                        case "Read":
                            Resultado = controladorCargo.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;

                case "CentroCostoJSP":
                    ControladorCentro_costo controladorCentroCosto = new ControladorCentro_costo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorCentroCosto.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorCentroCosto.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorCentroCosto.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "GrupoConsumoJSP":
                    ControladorGrupo_consumo controladorGrupoConsumo = new ControladorGrupo_consumo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorGrupoConsumo.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorGrupoConsumo.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorGrupoConsumo.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "AsocGrupoConsumoJSP":
                    ControladorAsociacion_grupo_consumo_horario_consumo controladorAsocGrupoConsumo = new ControladorAsociacion_grupo_consumo_horario_consumo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorAsocGrupoConsumo.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorAsocGrupoConsumo.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorAsocGrupoConsumo.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "TipoConsumoJSP":
                    ControladorTipo_consumo controladorTipoConsumo = new ControladorTipo_consumo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorTipoConsumo.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorTipoConsumo.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorTipoConsumo.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "FestivoJSP":
                    ControladorFestivo controladorFestivo = new ControladorFestivo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorFestivo.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorFestivo.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorFestivo.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "PermisosJSP":
                    ControladorPermisos controladorPer = new ControladorPermisos();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Read":
                            Resultado = controladorPer.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Combo Cargado");
                            break;
                        case "PermisosAsignados":
                            String rol = request.getParameter("rolU");
                            Resultado = controladorPer.permisosAsignados(request, response, rol);
                            PrintWriter pw2 = response.getWriter();
                            pw2.write(Resultado);
                            System.out.println(pw2.checkError() ? "Error al cargar la lista" : "Lista Cargada");
                            break;
                        case "PermisosNoAsignados":
                            String rolU = request.getParameter("rolU");
                            Resultado = controladorPer.permisosNoAsignados(request, response, rolU);
                            PrintWriter pw3 = response.getWriter();
                            pw3.write(Resultado);
                            System.out.println(pw3.checkError() ? "Error al cargar la lista" : "Lista Cargada");
                            break;
                        case "ListarPermisos":
                            //String user = request.getParameter ("user");
                            Resultado = controladorPer.listarPermisosAll(request, response);
                            PrintWriter pw4 = response.getWriter();
                            pw4.write(Resultado);
                            System.out.println(pw4.checkError() ? "Error al cargar la lista" : "Lista Cargada");
                            break;
                        case "LeoItems":
                            String[] myJsonData = request.getParameterValues("elements[]");
                            String idUsr = request.getParameter("usr");
                            //String players = request.getParameter("elements");
                            //String[] s = players.split(",");
                            Resultado = controladorPer.insertarPermisos(myJsonData, idUsr);
                            break;
                    }
                    break;
                case "RolesJSP":
                    ControladorRol controladorRol = new ControladorRol();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorRol.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorRol.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorRol.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "UsuariosJSP":
                    ControladorUsuario controladorU = new ControladorUsuario();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorU.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorU.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorU.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "PersonasJSP":
                    ControladorPersona controladorPersonas = new ControladorPersona();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorPersonas.Insert(request, response);
                            break;
                        case "Search":
                            Resultado = controladorPersonas.Search(request);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(Resultado);
                            Accion = "Plano";
                            break;
                        case "Delete":
                            Resultado = controladorPersonas.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorPersonas.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "AutorizacionConsumoJSP":
                    ControladorAutoriza_consumo controladorAutoriza_consumo = new ControladorAutoriza_consumo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorAutoriza_consumo.Insert(request, response);
                            break;
                        case "ReadPersonaAutoriza":
                            Resultado = controladorAutoriza_consumo.ReadPersonaAutoriza(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                        case "ReadPersonaAutorizada":
                            Resultado = controladorAutoriza_consumo.ReadPersonaAutorizado(request, response);
                            PrintWriter pww = response.getWriter();
                            pww.write(Resultado);
                            System.out.println(pww.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                        case "Read":
                            Resultado = controladorAutoriza_consumo.Read(request, response);
                            PrintWriter pwww = response.getWriter();
                            pwww.write(Resultado);
                            System.out.println(pwww.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;

                    }
                    break;
                case "ImagenesJSP":
                    ControladorImagen controladorImagen = new ControladorImagen();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Search":
                            Resultado = controladorImagen.Search(request, response);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(Resultado);
                            Accion = "Plano";
                            break;
                    }
                    break;
                case "TemplateJSP":
                    ControladorTemplate controladorTemplate = new ControladorTemplate();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Search":
                            Resultado = controladorTemplate.Search(request, response);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(Resultado);
                            Accion = "Plano";
                            break;
                    }
                    break;
                case "DispositivosJSP":
                    ControladorDispositivos controladorDispositvos = new ControladorDispositivos();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorDispositvos.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorDispositvos.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorDispositvos.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "DependenciasJSP":
                    ControladorDependencia controladorDependencias = new ControladorDependencia();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorDependencias.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorDependencias.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorDependencias.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;
                case "GrupoHorarioJSP":
                    ControladorGrupo_horario controladorGrupo_horario = new ControladorGrupo_horario();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorGrupo_horario.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorGrupo_horario.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorGrupo_horario.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;
                case "FuncionesJSP":
                    ControladorFuncion controladorFunciones = new ControladorFuncion();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorFunciones.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorFunciones.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorFunciones.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;
                case "PeriodosJSP":
                    ControladorPeriodos controladorPeriodos = new ControladorPeriodos();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorPeriodos.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorPeriodos.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorPeriodos.ReadPeriodos(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;
                case "CargosHoteleriaJSP":
                    ControladorCargo_hoteleria controladorCargos = new ControladorCargo_hoteleria();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorCargos.Insert(request, response);
                            break;
//                        case "Insert":
//                            Resultado = controladorCargos.Update(request, response);
//                            break;
                        case "Delete":
                            Resultado = controladorCargos.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorCargos.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;
                case "ConsumoHoteleriaJSP":
                    ControladorConsumo_hoteleria controladorConsumoHoteleria = new ControladorConsumo_hoteleria();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorConsumoHoteleria.Insert(request, response);
                            break;
                        case "Read":
                            Resultado = controladorConsumoHoteleria.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "ConsumoJSP":
                    ControladorConsumo controladorConsumo = new ControladorConsumo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "GetDatoSemana":
                            Resultado = controladorConsumo.GetDatoSemana(request, response);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(Resultado);
                            Accion = "Plano";
                            break;
                        case "GetDatoConsumo":
                            Resultado = controladorConsumo.GetDatoConsumo(request, response);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(Resultado);
                            Accion = "Plano";
                            break;
                        case "GetDatoCentroCosto":
                            Resultado = controladorConsumo.GetDatoCentroCosto(request, response);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(Resultado);
                            Accion = "Plano";
                            break;
                        case "GetDatoGrupoConsumo":
                            Resultado = controladorConsumo.GetDatoGrupoConsumo(request, response);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(Resultado);
                            Accion = "Plano";
                            break;
                    }
                    break;
                case "GenerarLiquidacionHoteleria":
                    //ControladorLiquidacionH controladorLiquidacionCasino = new ControladorLiquidacionCasino();
                    controladorLiquidacionCasino.Select("GenerarLiquidacionHoteleria", request, response);
                    Accion = "Plano";
                    break;
                case "GenerarLiquidacionCasino":
                    //ControladorLiquidacionCasino controladorLiquidacionCasino = new ControladorLiquidacionCasino();
                    controladorLiquidacionCasino.Select("GenerarLiquidacionCasino", request, response);
                    Accion = "Plano";
                    break;

                //controladorCargoH.Select("GenerarLiquidacionHoteleria", request, response);
                //Accion = "Plano";
                //break;
                //controladorCargoH.Select("GenerarLiquidacionHoteleria", request, response);
                //Accion = "Plano";
                //break;
//                case "BuscarPersona":
//                    ModeloPersona modeloPersonas = new ModeloPersona();                    
//                    ControladorPersona controladorPersonas1 = new ControladorPersona();
//                    modeloPersonas = controladorPersonas1.GetPersonaCedula(request, response);
//                    request.setAttribute("id", modeloPersonas.getId());
//                    request.setAttribute("nombre", modeloPersonas.getNombres() + " " + modeloPersonas.getApellidos());
//                    request.setAttribute("cedula", modeloPersonas.getIdentificacion());
//                    request.setAttribute("observacion", modeloPersonas.getObservacion());
//                    RequestDispatcher rd;
//                    rd = request.getRequestDispatcher("RegistroCargos.jsp");
//                    rd.forward(request, response);
//                    Accion = "Plano";
//                    break;
                case "UtilidadesJSP":
                    //ControladorCargos controladorCargos = new ControladorCargo ();
                    Accion = request.getParameter("accion");
                    String usua = request.getParameter("usr");
                    switch (Accion) {

                        case "UserActivo":
                            Tools tool = new Tools();
                            Resultado = tool.editarUserAct(request, response, usua);
                            /*
                            * PrintWriter pw = response.getWriter ();
                            * pw.write (Resultado);
                            * System.out.println (pw.checkError () ? "Error al
                            * cargar la lista" : "UserActivo Cargado");
                             */
                            //Resultado = controladorCargos.Insert (request);
                            break;
                    }
                    break;
                case "Auditoria":
                    ControladorAuditoria controladorAud = new ControladorAuditoria();
                    Accion = request.getParameter("accion");
                    //String usua = request.getParameter ("usr");
                    switch (Accion) {

                        case "Insert":
                            //Tools tool = new Tools ();
                            String operacion = request.getParameter("operacion");
                            String tabla = request.getParameter("tabla");
                            String usu = request.getParameter("usua");
                            int regmo = Integer.parseInt(request.getParameter("id"));
                            String observa = request.getParameter("observacion");
                            Resultado = controladorAud.Insert(operacion, tabla, usu, regmo, observa);
                            /*
                            * PrintWriter pw = response.getWriter ();
                            * pw.write (Resultado);
                            * System.out.println (pw.checkError () ? "Error al
                            * cargar la lista" : "UserActivo Cargado");
                             */
                            //Resultado = controladorCargos.Insert (request);
                            break;
                        case "Read":
                            Resultado = controladorAud.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Combo Cargado");
                            break;
                        case "ReadReg":
                            String usr = request.getParameter("usuario");
                            String fini = request.getParameter("fechai");
                            String ffin = request.getParameter("fechaf");
                            Resultado = controladorAud.readTabla(request, response, usr, fini, ffin);
                            PrintWriter pw2 = response.getWriter();
                            pw2.write(Resultado);
                            System.out.println(pw2.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "GrupoTurnosTurnosJSP":
                    ControladorGrupoTurnos_Turnos controladorGrupoTurnos_Turnos = new ControladorGrupoTurnos_Turnos();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorGrupoTurnos_Turnos.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorGrupoTurnos_Turnos.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorGrupoTurnos_Turnos.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;
                case "Registro_VisitaJSP":
                    ControladorVisita visita = new ControladorVisita();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = visita.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = visita.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = visita.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                        case "Valida_Tipo_Visita":
                            Resultado = visita.Valida_Tipo_Visita(request, response);
                            pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                        case "Modal_Visita":
                            Resultado = visita.Campos_Visita(request, response);
                            pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                        case "buscar":
//                            int numPagina = Integer.parseInt(request.getParameter("page"));
//                            int numRegistros = Integer.parseInt(request.getParameter("rows"));
//                            int numPaginaInicio = numPagina * numRegistros - numRegistros;
//                            int numPaginaFin = numPagina * numRegistros;
//                            LinkedList<ModeloPersona> lstPersona = visita.buscar(numPaginaInicio, numPaginaFin);
//                            Gson gson = new Gson();
//                            
//                            ResultadoJson resultadoJson = new ResultadoJson();
//                            resultadoJson.setLstLista(lstPersona);
//                            resultadoJson.setNumPaginado(numPagina);
//                            resultadoJson.setNumRegistros(lstPersona.size());
//                            resultadoJson.setNumPaginadaciones((int) Math.floorDiv(resultadoJson.getNumRegistros() / numRegistros, numPagina));
//                            int iRes = resultadoJson.getNumRegistros() % numRegistros;
//                            if(iRes > 0)
//                            {
//                                int iNu = resultadoJson.getNumPaginadaciones();
//                                resultadoJson.setNumPaginadaciones(iNu+1);                                
//                            }
//                            pw = response.getWriter();
//                            pw.print(gson.toJson(resultadoJson));
//                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Personas grulla");
//                            pw.close();
                            break;
                    }
                    break;
                case "EnumeracionJSP":
                    ControladorEnumeracion controladorEnumeracion = new ControladorEnumeracion();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorEnumeracion.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = controladorEnumeracion.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = controladorEnumeracion.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "Asociacion_Grupo_VencimientoJSP":
                    ControladorAsociacion_grupo_vencimiento asociacion_grupo_vencimiento = new ControladorAsociacion_grupo_vencimiento();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = asociacion_grupo_vencimiento.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = asociacion_grupo_vencimiento.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = asociacion_grupo_vencimiento.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;
                case "VencimientoJSP":
                    ControladorVencimiento vencimiento = new ControladorVencimiento();
                    //ControladorVisita visita = new ControladorVisita();
                    Accion = request.getParameter("accion");
                    PrintWriter pw = null;
                    switch (Accion) {
                        case "Upload":
                            Resultado = vencimiento.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = vencimiento.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = vencimiento.Read(request, response);
                            pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                        case "ReadPersona":
                            Resultado = vencimiento.Read_Persona(request, response);
                            pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                case "ParametroTablaJSP":
                    ControladorParametro_tabla parametro_tabla = new ControladorParametro_tabla();
                    //ControladorVisita visita = new ControladorVisita();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = parametro_tabla.Insert(request, response);
                            break;
                        case "Delete":
                            Resultado = parametro_tabla.Delete(request, response);
                            break;
                        case "Read":
                            Resultado = parametro_tabla.Read(request, response);
                            pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }

                    break;
            }
            if (!"Plano".equals(Accion)) {
                String respuesta = herramienta.GetDescrpCode(Resultado);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                if ("Password".equals(Formulario) || "UserActivo".equals(Accion)) {
                    response.getWriter().write(Resultado);
                } else {
                    response.getWriter().write(respuesta);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServletAlohaTiempos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para el control de procesos de AlohaTiempos";
    }// </editor-fold>

}
