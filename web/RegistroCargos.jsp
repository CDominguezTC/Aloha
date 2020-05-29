<%-- 
    Document   : Empleados
    Created on : 13/12/2019, 11:31:01 AM
    Author     : Frankie
--%>

<%@page import="Controladores.ControladorCargo"%>
<%@page import="Modelo.ModeloCargo"%>
<%@page import="Modelo.ModeloPersona"%>
<%@page import="Controladores.ControladorGrupo_consumo"%>
<%@page import="Modelo.ModeloGrupo_consumo"%>
<%@page import="Controladores.ControladorCentro_costo"%>
<%@page import="Modelo.ModeloCentro_costo"%>
<%@page import="Controladores.ControladorEmpresa"%>
<%@page import="Modelo.ModeloEmpresa"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %> 
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsTiempos/ValidacionesRegistroCargosHoteleria.js" ></script>     
        
        <style>
  
            #Idbuscar{
                display: block;
                width: 100%;
            }

            #IdGuardar{
                display: block;
                width: 20%;
            }

        </style>
      
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
                                                                <input type="hidden" id="Id" name="Id" value="">
                                                                <div class="col-md-8 col-sm-12 col-xs-12 form-group">
                                                                    <label for="cedula">Identificacion</label>
                                                                    <input type="number" class="form-control" id="IdCedula" value="" name="cedula" min="0">
                                                                </div>        
                                                                
                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="" style="visibility:hidden">---</label>
                                                                    <button class="btn btn-primary btn-md" type="button" id="Idbuscar" name="frmm" value="BuscarPersona"><i class="fa fa-search"></i> Buscar</button>
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
                                                                <!--button class="btn btn-primary btn-sm" type="submit" id="Idbuscar" name="frmm" value="BuscarPersona"><i class="fa fa-search"></i> Buscar</button-->
                                                                <!--<button class="btn btn-primary btn-sm" type="button" id="Idbuscar" name="frmm" value="BuscarPersona"><i class="fa fa-search"></i> Buscar</button>-->
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div>
                                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                <label for="nombre">Nombre</label>
                                                                <input type="text" class="form-control" id="IdHNombre" value="" name="nombre" disabled="">
                                                            </div>
                                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                <label for="apellido">Apellido</label>
                                                                <input type="text" class="form-control" id="IdHApellido" value="" name="apellido" disabled="">
                                                            </div>    
                                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                <label for="Fecha">Fecha Solicitud</label>
                                                                <div class="form-group">
                                                                    <div class='input-group date' id='myDatepicker3'>
                                                                        <input type="text" class="form-control" id="IdFecha" name="fecha"/>
                                                                        <span class="input-group-addon">
                                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                                        </span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div>
                                                            <h3  style=" color: #006699; font-family: sans-serif; text-align: center; font-weight: bolder;">
                                                                Listado de Servicios Hoteleria
                                                            </h3>
                                                            <table id="datatable"class="table table-striped jambo_table bulk_action">
                                                                <thead>
                                                                    <tr class="headings">
                                                                        <th>Selecione</th>            
                                                                        <th>Servicio</th>
                                                                        <th>Valor</th>                                                                        
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>                                                                                                      
                                                                        <td></td>                                                        
                                                                        <td></td>                                                        
                                                                        <td></td>                                                        
                                                                    </tr>
                                                                </tbody>
                                                            </table>                                                                                                                                    
                                                        </div>
                                                        <div class="ln_solid"></div>
                                                        <div class="form-group"> 
                                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-5">
                                                                <button class="btn btn-success btn-sm" type="button" id="IdGuardar" name="frm" value="Aplicar"><i class="fa fa-save"></i> Guardar</button>
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

            $('#myDatepicker').datetimepicker();

            $('#myDatepicker3').datetimepicker({
                format: 'YYYY-MM-DD',
                locale: 'es'
            });

        </script>
    </body>
</html>
