<%-- 
    Document   : HorarioConsumo
    Created on : 11-mar-2020, 14:59:24
    Author     : Carlos A Dominguez D
--%>

<%@page import="Controladores.ControladorTipo_consumo"%>
<%@page import="Modelo.ModeloTipo_consumo"%>
<%@page import="Controladores.ControladorTipo_consumo"%>
<%@page import="Modelo.ModeloTipo_consumo"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>        
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsTiempos/ValidacionesAutorizaConsumos.js" ></script>         
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
        <!-- Contenidos -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Autoriza Consumos</h3>
                    </div>
                    <div class="title_right">
                        <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search"> 
                            <!--div class="input-group">
                                <input type="text" class="form-control" placeholder="Buscar...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                                </span>
                            </div-->
                        </div>
                    </div>
                </div>
                <!-- Primera Sección-->
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Configuración</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a></a>
                                    </li>
                                    <li class="dropdown">
                                        <a><i>Ocultar</i></a>
                                    </li>
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <br/>
                                <!-- Formulario Configuración -->
                                <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
                                    <div align="center" id="espera" style="display: none">
                                        <img src="Principal/images/loading_dash.gif">
                                    </div>
                                    <div id="Principal">
                                        <div class="row">
                                            <input type="hidden" id="Id" name="Id">
                                            <input type="hidden" id="IdCodigoOld" name="CodigoOld">
                                            <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                <label for="codigo">Tipo Persona</label>
                                                <select id="IdTipoPersona" name="TipoPersona" class="form-control" required onchange>
                                                    <option value="0" disabled selected="true">Seleccione</option>                                                          
                                                    <option value="EMPLEADO">EMPLEADO</option>
                                                    <option value="VISITANTE">VISITANTE</option>                                                    
                                                </select>
                                            </div>                                            
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <input type="hidden" id="IdEmpleadoAutoriza" name="EmpleadoAutoriza">
                                                <label for="empleadoautoriza">Empleado que Autoriza </label>
                                                <div class="form-group">
                                                    <div class='input-group date' id='visiblemodalpersonaautoriza' data-toggle="modal" data-target=".bs-empleadoautoriza-modal-lg">
                                                        <input type='text' class="form-control" id="IdNombreEmpleadoAutoriza" name="EmpleadoAutoriza" style="text-align:center;" required/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-search"></span>
                                                        </span>
                                                    </div>
                                                </div>                                                
                                            </div>     
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <input type="hidden" id="IdEmpleadoAutorizado" name="EmpleadoAutorizado">
                                                <label for="empleadoautorizado">Persona Autorizada</label>
                                                <div class="form-group">
                                                    <div class='input-group date' id='visiblemodalpersonaautorizado'data-toggle="modal" data-target=".bs-empleadoautorizado-modal-lg">
                                                        <input type='text' class="form-control" id="IdNombreEmpleadoAutorizado" name="EmpleadoAutorizado" style="text-align:center;" required/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-search"></span>
                                                        </span>
                                                    </div>
                                                </div> 
                                            </div>                                            
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <input type="hidden" id="IdCC" name="CC">
                                                <label for="centrocosto">Centro de Costos</label>
                                                <input type="text" class="form-control" id="IdCentroCosto" name="CentroCosto" required="required">
                                            </div>                                            
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="fechainicial">Fecha Inicial</label>                                                
                                                <div class="form-group">
                                                    <div class='input-group date' id='myDatepickerfechainical'>
                                                        <input type="text" class="form-control" id="IdFechaInicial" name="FechaInicial" required="required"/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="fechafinal">Fecha Final</label>                                                
                                                <div class="form-group">
                                                    <div class='input-group date' id='myDatepickerfechafinal'>
                                                        <input type="text" class="form-control" id="IdFechaFinal" name="FechaFinal" required="required"/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>                                            
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="tipoconsumo">Tipo Consumo</label>
                                                <select id="IdTipoConsumo" class="form-control" required>
                                                    <option value="0" disabled selected>Seleccione</option>
                                                </select>
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="motivo">Motivo</label>
                                                <input type="text" class="form-control" id="IdMotivo" name="Motivo" required="required">
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="cantidad">Cantidad</label>
                                                <input type="number" class="form-control" id="IdCantidad" name="Cantidad" required="required">
                                            </div>
                                        </div>                                        
                                        <!-- Formulario Configuración -->  
                                        <!--Inincio de Modals-->
                                        <div class="modal fade bs-empleadoautoriza-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
                                            <div class="modal-dialog modal-lg">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="myModalLabel"><b>Persona que Autoriza</b></h4>
                                                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <table id="IdTablaPersonaAutoriza" class="table table-responsive table-bordered">
                                                            <!--thead>
                                                                <tr>
                                                                    <th>Cedula</th>
                                                                    <th>Nombre</th>
                                                                    <th>Empresa</th>
                                                                    <th>Centro de Costo</th>                                                                    
                                                                    <th>Opcion</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>                                                    
                                                                    <td></td>                                     
                                                                    <td></td>                                     
                                                                    <td></td>                                     
                                                                    <td></td>                                                                                                         
                                                                    <td class="text-center">                                                                                                                                    
                                                                    </td>                                                    
                                                                </tr>
                                                            </tbody-->
                                                        </table>                                                        
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal fade bs-empleadoautorizado-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
                                            <div class="modal-dialog modal-lg">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="myModalLabel"><b>Persona Autorizada</b></h4>
                                                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <table id="IdTablaPersonaAutorizada" class="table table-responsive table-bordered">
                                                            <!--thead>
                                                                <tr>
                                                                    <th>Cedula</th>
                                                                    <th>Nombre</th>
                                                                    <th>Empresa</th>
                                                                    <th>Centro de Costo</th>                                                                    
                                                                    <th>Opcion</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>                                                    
                                                                    <td></td>                                     
                                                                    <td></td>                                     
                                                                    <td></td>                                     
                                                                    <td></td>                                                                                                         
                                                                    <td class="text-center">                                                                                                                                         
                                                                    </td>                                                    
                                                                </tr>
                                                            </tbody-->
                                                        </table>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--Final de Modals-->
                                        <!-- Botones -->
                                        <div class="ln_solid"></div>
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-4">
                                                <button class="btn btn-primary btn-sm" type="button" id="IdAgregar" name="Agregar"><i class="fa fa-plus"></i> Agregar</button>
                                                <button class="btn btn-success btn-sm" type="button" id="IdGuardar" name="Guardar"><i class="fa fa-save"></i> Guardar</button>                                                
                                                <button class="btn btn-danger btn-sm" type="reset"><i class="fa fa-close"></i> Cancelar</button>
                                                <br/><br/>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-8 col-sm-8 col-xs-12 col-md-offset-1">
                                                            <!--button class="btn btn-warning btn-sm" type="button"><i class="fa fa-edit"></i> Editar</button-->
                                                            <!--button class="btn btn-dark btn-sm" type="button" disabled><i class="fa fa-trash"></i> Eliminar</button-->
                                                        </div>
                                                    </div>
                                                </div>         
                                            </div>
                                        </div>
                                        <!-- /Botones -->
                                    </div>
                                </form>
                                <!-- /Formulario Registro -->
                            </div>
                        </div>
                        <!-- /Primera Sección-->

                        <!-- Segunda Sección-->
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>Tabla</h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a></a>
                                            </li>
                                            <li class="dropdown">
                                                <a><i>Ocultar</i></a>
                                            </li>
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <!-- Tabla -->
                                        <table id="datatable" class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>Tipo Persona</th>
                                                    <th>Empleado que Autoriza</th>
                                                    <th>Persona Autorizada</th>
                                                    <th>Centro Costo</th>
                                                    <th>Fecha Inical</th>
                                                    <th>Fecha Final</th>
                                                    <th>Tipo Consumo</th>
                                                    <th>Cantidad Consumo</th>
                                                    <th>Motivo</th>                                                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>                                                    
                                                    <td></td>                                     
                                                    <td></td>                                     
                                                    <td></td>                                     
                                                    <td></td>                                     
                                                    <td></td>                                     
                                                    <td></td>                                     
                                                    <td></td>                                     
                                                    <td></td>                                     
                                                    <td></td>                                                                                                                                         
                                                </tr>
                                            </tbody>
                                        </table>
                                        <!-- /Tabla -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /Segunda Sección-->
                    </div>
                </div>
            </div>
        </div>
        <!-- /Contenido -->
        <!-- Footer -->
        <footer>
            <br/>
            <div class="clearfix"></div>
        </footer>
        <!-- Footer -->
        <%@include file="Principal/Script.html" %>  

        <!-- Inicializamos Datetimepicker -->
        <script>

            $('#myDatepicker').datetimepicker();

            $('#myDatepicker2').datetimepicker({
                format: 'HH:mm'

            });

            $('#myDatepicker3').datetimepicker({
                format: 'HH:mm'
            });

        </script>
    </body>
</html>
