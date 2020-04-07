<%-- 
    Document   : Empleados
    Created on : 13/12/2019, 11:31:01 AM
    Author     : Frankie
--%>

<%@page import="Controladores.ControladorCargos"%>
<%@page import="Modelo.ModeloCargos"%>
<%@page import="Modelo.ModeloPersonas"%>
<%@page import="Controladores.ControladorGrupoConsumo"%>
<%@page import="Modelo.ModeloGrupoConsumo"%>
<%@page import="Controladores.ControladorCentroCosto"%>
<%@page import="Modelo.ModeloCentroCosto"%>
<%@page import="Controladores.ControladorEmpresas"%>
<%@page import="Modelo.ModeloEmpresa"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %> 
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsTiempos/ValidacionesCargos.js" ></script>         
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>

        <!-- Contenidos -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Registro Hoteleria</h3>
                    </div>

                    <!-- <div class="title_right">
                       <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search"> 
                         <div class="input-group">
                           <input type="text" class="form-control" placeholder="Buscar...">
                           <span class="input-group-btn">
                             <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                           </span>
                         </div>
                       </div>
                     </div>-->

                </div>

                <!-- Primera Sección-->
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <!-- Segunda Sección-->
                        <div class="clearfix"></div>

                        <!-- /Segunda Sección-->

                        <br/>

                        <!-- Tercera Sección-->   
                        <div class="" role="tabpanel" data-example-id="togglable-tabs">
                            <div id="myTabContent" class="tab-content">
                                <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="empleados-tab">
                                    <div class="clearfix"></div>
                                    <div class="row">
                                        <!-- Empleados -->
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="x_panel">
                                                <div class="x_title">
                                                    <h2>Configuración</h2>
                                                    <ul class="nav navbar-right panel_toolbox">
                                                        <li><a></a></li>
                                                        <li class="dropdown">
                                                            <a><i>Ocultar</i></a>
                                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                                                    </ul>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <!-- Configuracion Empleados -->
                                                <div class="x_content">
                                                    <form action="ServletAlohaTiempos" name="RegistroCargosJSP" method="GET" id="IdRegistroCargosJSP"> 
                                                        <div align="center" id="espera" style="display: none">
                                                            <img src="Principal/images/loading_dash.gif">                                                            
                                                        </div>
                                                        <div id="Principal">
                                                            <div class="row">                                                                  
                                                                <input type="hidden" id="Id" name="Id" value="${id}">
                                                                <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                    <label for="cedula">Identificacion</label>
                                                                    <input type="text" class="form-control" id="IdCedula" value="${cedula}" name="cedula">
                                                                </div>                                                                                                                 
                                                                <div class="col-md-12 col-sm-12 col-xs-12 form-group">                                                                    
                                                                    <input type="hidden" class="form-control" id="Idfrmm" value="" name="frm">
                                                                </div>                                                                                                                 
                                                                <div class="col-md-12 col-sm-12 col-xs-12 form-group">                                                                
                                                                    <input type="hidden" class="form-control" id="IdAccion" value="" name="accion">
                                                                </div>                                                                                                                 
                                                            </div>                                                                                      
                                                        </div>                                                       
                                                        <div class="ln_solid"></div>
                                                        <div class="form-group"> 
                                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-5">
                                                                <button class="btn btn-primary btn-sm" type="submit" id="Idbuscar" name="frmm" value="BuscarPersona"><i class="fa fa-search"></i> Buscar</button>
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div>
                                                            <h3>Nombre : ${nombre}</h3>                                            
                                                            <h5>Cedula : ${cedula}</h5>                                            
                                                            <h5>Cargo  : ${observacion}</h5> 
                                                        </div>
                                                        <div>
                                                            <h3  style=" color: #006699; font-family: sans-serif; text-align: center; font-weight: bolder;">
                                                                Listado de Servicios Hoteleria
                                                            </h3>
                                                            <table class="table" id="Tabla"> 
                                                                <tr>
                                                                    <td><input type="checkbox" class="custom-control-input" id="IdSelect" name="Select" value="Select"><br></td>                                                                                                
                                                                    <td><div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                            <label for="tipo_id">Servicio</label>
                                                                            <select id="IdConsume" class="form-control" name="Consume">
                                                                                <option value="0" selected disabled>Seleccione</option>   
                                                                                <%
                                                                                    LinkedList<ModeloCargos> modeloCargos;
                                                                                    ControladorCargos controladorCargos = new ControladorCargos ();
                                                                                    modeloCargos = controladorCargos.getModelo ();
                                                                                    for (ModeloCargos modelo : modeloCargos)
                                                                                    {
                                                                                %>
                                                                                <option value="<%=modelo.getId ()%>"><%=modelo.getTipoCargo ()%></option>
                                                                                <%
                                                                                    }
                                                                                %>                                                                                
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                </tr>                                                         
                                                            </table>
                                                            <div class="text-center">                        
                                                                <div class="col-lg-12" style="text-align: center">
                                                                    <div class="btn btn-default" style="float: left" id="IdbtnDelete"> Eliminar Items</div>
                                                                    <div class="btn btn-success" style="float: right" id="btnNuevo"> Nuevo Item</div>
                                                                </div>                        
                                                            </div>                    
                                                        </div>
                                                        <div class="form-group"> 
                                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-5">
                                                                <button class="btn btn-primary btn-sm" type="button" id="IdAplicarAccion" name="frm" value="AplicarCargos"><i class="fa fa-search"></i>Aplicar Cargos</button>
                                                            </div>
                                                        </div>
                                                    </form>                                 
                                                </div>
                                            </div>
                                        </div>
                                    </div>                                    
                                </div>
                            </div>        
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /Contenido --> 
        <!-- Footer -->
        <footer>
            <div class="clearfix"></div>
        </footer>
        <!-- Footer -->        
        <%@include file="Principal/Script.html" %>  
        <script>

        </script>
    </body>
</html>
