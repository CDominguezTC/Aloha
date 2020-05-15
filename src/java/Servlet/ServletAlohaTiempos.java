/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controladores.ControladorArea;
import Controladores.ControladorAsocGrupoConsumo;
import Controladores.ControladorAuditoria;
import Controladores.ControladorCargo;
import Controladores.ControladorCiudad;
import Controladores.ControladorCentro_costo;
import Controladores.ControladorDependencia;
import Controladores.ControladorDispositivos;
import Controladores.ControladorEmpresa;
import Controladores.ControladorFestivo;
import Controladores.ControladorFunciones;
import Controladores.ControladorGrupoConsumo;
import Controladores.ControladorGrupoTurnos;
import Controladores.ControladorGrupoTurnos_Turnos;
import Controladores.ControladorHorarioConsumo;
import Controladores.ControladorInicioSesion;
import Controladores.ControladorLiquidacionCasino;
import Controladores.ControladorPeriodos;
import Controladores.ControladorPermisos;
import Controladores.ControladorPersonas;
import Controladores.ControladorTipoConsumo;
import Controladores.ControladorTurnos;
import Controladores.ControladorUsuarios;
import Herramienta.Herramienta;
import Modelo.ModeloPersonas;
import Tools.Tools;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
        HttpSession session = request.getSession(false);
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
                                //String pw = request.getParameter("pass");
                                HttpSession session = request.getSession();
                                session.setAttribute("usuario", usuario);
                            }
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
                                ControladorUsuarios controladorU = new ControladorUsuarios();
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
                                ControladorUsuarios controladorU = new ControladorUsuarios();
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
                                ControladorUsuarios controladorU = new ControladorUsuarios();
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
                                ControladorUsuarios controladorU = new ControladorUsuarios();
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
                    ControladorHorarioConsumo controladorHorarioConsumo = new ControladorHorarioConsumo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorHorarioConsumo.Insert(request);
                            break;
                        case "Delete":
                            Resultado = controladorHorarioConsumo.Delete(request);
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
                            Resultado = controladorTurnos.Insert(request);
                            break;
                        case "Delete":
                            Resultado = controladorTurnos.Delete(request);
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
                        case "ReadTiempos":
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
                    ControladorGrupoConsumo controladorGrupoConsumo = new ControladorGrupoConsumo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorGrupoConsumo.Insert(request);
                            break;
                        case "Delete":
                            Resultado = controladorGrupoConsumo.Delete(request);
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
                    ControladorAsocGrupoConsumo controladorAsocGrupoConsumo = new ControladorAsocGrupoConsumo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorAsocGrupoConsumo.Insert(request);
                            break;
                        case "Delete":
                            Resultado = controladorAsocGrupoConsumo.Delete(request);
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
                    ControladorTipoConsumo controladorTipoConsumo = new ControladorTipoConsumo();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorTipoConsumo.Insert(request);
                            break;
                        case "Delete":
                            Resultado = controladorTipoConsumo.Delete(request);
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
                        case "ReadPU":
                            String usr = request.getParameter("user");
                            Resultado = controladorPer.ReadPU(request, response, usr);
                            PrintWriter pw2 = response.getWriter();
                            pw2.write(Resultado);
                            System.out.println(pw2.checkError() ? "Error al cargar la lista" : "Lista Cargada");
                            break;
                        case "ReadPNoU":
                            String user = request.getParameter("user");
                            Resultado = controladorPer.ReadPNoU(request, response, user);
                            PrintWriter pw3 = response.getWriter();
                            pw3.write(Resultado);
                            System.out.println(pw3.checkError() ? "Error al cargar la lista" : "Lista Cargada");
                            break;
                        case "ReadTodosP":
                            //String user = request.getParameter ("user");
                            Resultado = controladorPer.ReadTodosP(request, response);
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
                case "UsuariosJSP":
                    ControladorUsuarios controladorU = new ControladorUsuarios();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorU.Insert(request);
                            break;
                        case "Delete":
                            Resultado = controladorU.Delete(request);
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
                    ControladorPersonas controladorPersonas = new ControladorPersonas();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorPersonas.Insert(request);
                            break;
                        case "Search":
                            Resultado = controladorPersonas.Search(request);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(Resultado);
                            Accion = "Plano";
                            break;
                        case "Delete":
                            Resultado = controladorPersonas.Delete(request);
                            break;
                        case "Read":
                            Resultado = controladorPersonas.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    break;
                case "DispositivosJSP":
                    ControladorDispositivos controladorDispositvos = new ControladorDispositivos();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorDispositvos.Insert(request,response);
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
                case "GrupoTurnosJSP":
                    ControladorGrupoTurnos controladorGrupoTurnos = new ControladorGrupoTurnos();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorGrupoTurnos.Insert(request);
                            break;
                        case "Delete":
                            Resultado = controladorGrupoTurnos.Delete(request);
                            break;
                        case "Read":
                            Resultado = controladorGrupoTurnos.Read(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    
                    break;
                case "FuncionesJSP":
                    ControladorFunciones controladorFunciones = new ControladorFunciones();
                    Accion = request.getParameter("accion");
                    switch (Accion) {
                        case "Upload":
                            Resultado = controladorFunciones.Insert(request);
                            break;
                        case "Delete":
                            Resultado = controladorFunciones.Delete(request);
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
                            Resultado = controladorPeriodos.Insert(request);
                            break;
                        case "Delete":
                            Resultado = controladorPeriodos.Delete(request);
                            break;
                        case "Read":
                            Resultado = controladorPeriodos.ReadPeriodos(request, response);
                            PrintWriter pw = response.getWriter();
                            pw.write(Resultado);
                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                            break;
                    }
                    
                    break;
//                case "CargosHoteleriaJSP":
//                    ControladorCargo controladorCargos = new ControladorCargo();
//                    Accion = request.getParameter("accion");
//                    switch (Accion) {
//                        case "Upload":
//                            Resultado = controladorCargos.Insert(request);
//                            break;
//                        case "Insert":
//                            Resultado = controladorCargos.Update(request);
//                            break;
//                        case "Delete":
//                            Resultado = controladorCargos.Delete(request);
//                            break;
//                        case "Read":
//                            Resultado = controladorCargos.Read(request, response);
//                            PrintWriter pw = response.getWriter();
//                            pw.write(Resultado);
//                            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
//                            break;
//                    }
//                    
//                    break;
                case "GenerarLiquidacionCasino":
                    ControladorLiquidacionCasino controladorLiquidacionCasino = new ControladorLiquidacionCasino();
                    controladorLiquidacionCasino.Select("GenerarLiquidacionCasino", request, response);
                    Accion = "Plano";
                    break;
//                case "GenerarLiquidacionHoteleria":
//                    ControladorCargo controladorCargoH = new ControladorCargo();
//                    controladorCargoH.Select("GenerarLiquidacionHoteleria", request, response);
//                    Accion = "Plano";
//                    break;
                case "BuscarPersona":
                    ModeloPersonas modeloPersonas = new ModeloPersonas();
                    ControladorPersonas controladorPersonas1 = new ControladorPersonas();
                    modeloPersonas = controladorPersonas1.GetPersonaCedula(request, response);
                    request.setAttribute("id", modeloPersonas.getId());
                    request.setAttribute("nombre", modeloPersonas.getNombres() + " " + modeloPersonas.getApellidos());
                    request.setAttribute("cedula", modeloPersonas.getIdentificacion());
                    request.setAttribute("observacion", modeloPersonas.getObservaciones());
                    RequestDispatcher rd;
                    rd = request.getRequestDispatcher("RegistroCargos.jsp");
                    rd.forward(request, response);
                    Accion = "Plano";
                    break;
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
                            Resultado = controladorGrupoTurnos_Turnos.Insert(request);
                            break;
                        case "Delete":
                            Resultado = controladorGrupoTurnos_Turnos.Delete(request);
                            break;
                        case "Read":
                            Resultado = controladorGrupoTurnos_Turnos.Read(request, response);
                            PrintWriter pw = response.getWriter();
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
