<%-- 
    Document   : Vencimiento
    Created on : 28/05/2020, 02:27:52 PM
    Author     : Diego Fdo Guzman B
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>        
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsVisitantes/ValidacionesVencimiento.js" ></script>
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
        <!-- Contenido -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Vencimientos</h3>
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
                                            <input type="hidden" id="id_personaOld" name="id_personaOld">

                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <input type="hidden" id="id_persona" name="id_persona">
                                                <label for="id_persona">Persona </label>
                                                <div class="form-group">
                                                    <div class='input-group date' id='visiblemodalpersona' data-toggle="modal" data-target=".bs-persona-modal-lg">
                                                        <input type='text' class="form-control" id="nombre_persona" name="nombre_persona" style="text-align:center;" required/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-search"></span>
                                                        </span>
                                                    </div>
                                                </div>                                                
                                            </div>

                                            <div id="BusquedaPersona" class="modal fade bs-persona-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
                                                <div class="modal-dialog modal-lg">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel"><b>Persona</b></h4>
                                                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <table id="IdTablaPersona" class="table table-responsive table-bordered">
                                                            </table>                                                        
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>                                                        
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>


                                            <!--div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="id_persona">Persona</label>
                                                <select id="id_persona" class="form-control" required>
                                                    <option value="" disabled selected>Seleccione</option>
                                                </select>
                                            </div-->
                                            <input type="hidden" id="id_vencimientoOld" name="id_vencimientoOld">
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="id_vencimiento">Campo Vencimiento</label>
                                                <select id="id_vencimiento" class="form-control" required>
                                                    <option value="" disabled selected>Seleccione</option>
                                                </select>
                                            </div>
                                            <input type="hidden" id="fecha_vencimientoOld" name="fecha_vencimientoOld">
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">                                               
                                                <label for="Fecha">Fecha Vencimiento</label>
                                                <div class="form-group">
                                                    <div class='input-group date' id='myDatepicker'>
                                                        <input type="text" class="form-control" id="fecha_vencimiento" name="fecha_vencimiento"/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!--Inincio de Modals-->


                                        <!-- Formulario Configuración -->      
                                        <!-- Botones -->
                                        <div class="ln_solid"></div>
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-4">
                                                <button class="btn btn-primary btn-sm" type="button" id="IdAgregar" name="Agregar"><i class="fa fa-plus"></i> Agregar</button>
                                                <button class="btn btn-success btn-sm" type="button" id="IdGuardar" name="Guardar"><i class="fa fa-save"></i> Guardar</button>                                                
                                                <button class="btn btn-danger btn-sm" type="button" id="IdCancelar" name="Cancelar"><i class="fa fa-close"></i> Cancelar</button>
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
                                                    <th>Identificacion</th>                                                    
                                                    <th>Persona</th>
                                                    <th>Item</th>
                                                    <th>Vencimiento</th>
                                                    <th>Opciones</th>   
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
                                            </tbody>
                                        </table>
                                        <!-- /Tabla -->
                                        <br/><br/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /Segunda Sección-->
                        <br/><br/><br/><br/><br/>
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

        <script>

            $('#myDatepicker').datetimepicker({
                format: 'YYYY-MM-DD',
                locale: 'es'
            });

        </script>
    </body>
</html>
