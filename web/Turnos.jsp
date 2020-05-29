<%-- 
    Document   : Turnos
    Created on : 13/12/2019, 11:25:30 AM
    Author     : Frankie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %> 
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsTiempos/ValidacionesTurnos.js" ></script> 
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
        <!-- Contenido -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Turnos</h3>
                    </div>

                    <div class="title_right">
                        <!--div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search"> 
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Buscar...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                                </span>
                            </div>
                        </div-->
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
                                <!-- Formulario Registro -->
                                <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
                                    <div align="center" id="espera" style="display: none">
                                        <img src="Principal/images/loading_dash.gif">
                                    </div>
                                    <div id="Principal">
                                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                            <li role="presentation" class="active"><a href="#tab_content1" id="registro-tab" role="tab" data-toggle="tab" aria-expanded="true">Registro</a>
                                            </li>
                                            <li role="presentation" class=""><a href="#tab_content2" role="tab" id="configuracion-tab" data-toggle="tab" aria-expanded="false">Configuración</a>
                                            </li>
                                        </ul>
                                        <div id="myTabContent" class="tab-content">
                                            <input type="hidden" class="form-control" id="Id" name="id" >
                                            <!-- Registro -->
                                            <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="registro-tab">
                                                <div class="row">

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="codigo">Código</label>
                                                        <input type="number" class="form-control" id="IdCodigo" name="codigo" min="0" required>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="nombre">Nombre</label>
                                                        <input type="text" class="form-control" id="IdNombre" required="required">
                                                    </div>

                                                    <!-- Configuracion Turnos -->  
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <div class="x_panel">
                                                            <div class="x_title">
                                                                <h2>Configuracion Turno</h2>
                                                                <ul class="nav navbar-right panel_toolbox">
                                                                    <li><a></a>
                                                                    </li>
                                                                    <li class="dropdown">
                                                                        <a></i></a>
                                                                    </li>
                                                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                                                    </li>
                                                                </ul>
                                                                <div class="clearfix"></div>
                                                            </div>
                                                            <div class="x_content">
                                                                <div class="row">
                                                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                        <label for="hora_inicio">Hora Inicio</label>
                                                                        <div class="form-group">
                                                                            <div class='input-group date' id='myDatepicker1'>
                                                                                <input type='text' class="form-control" id="IdHoraInicio" name="HoraInicio" style="text-align:center;" required/>
                                                                                <span class="input-group-addon">
                                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                                </span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                        <label for="hora_fin">Hora Fin</label>
                                                                        <div class="form-group">
                                                                            <div class='input-group date' id='myDatepicker2'>
                                                                                <input type='text' class="form-control" id="IdHoraFin" name="HoraFin" style="text-align:center;" required/>
                                                                                <span class="input-group-addon">
                                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                                </span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                        <label for="teorico">Teorico</label>
                                                                        <div class="form-group">
                                                                            <div class='input-group date' id='myDatepicker3'>
                                                                                <input type='text' class="form-control" id="IdTeorico" name="Teorico" style="text-align:center;" required/>
                                                                                <span class="input-group-addon">
                                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                                </span>
                                                                            </div>
                                                                        </div>
                                                                    </div> 

                                                                    <div class="form-group">
                                                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"></label>
                                                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                                                            <div id="Turnos" class="btn-group" data-toggle="buttons">

                                                                                <input type="checkbox" value="" id="IdTurnoNocturno" class="flat"> Turno Nocturno  
                                                                                <label style="visibility:hidden">---</label>
                                                                                <input type="checkbox" value="" id="IdTurnoExtra"  class="flat"> Turno Extra

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="ln_solid"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- /Empleado Visitado -->  

                                                    <!-- Vehiculo -->  
                                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                                        <div class="x_panel">
                                                            <div class="x_title">
                                                                <h2>Configuracion Break</h2>
                                                                <ul class="nav navbar-right panel_toolbox">
                                                                    <li><a></a>
                                                                    </li>
                                                                    <li class="dropdown">
                                                                        <a></i></a>
                                                                    </li>
                                                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                                                    </li>
                                                                </ul>
                                                                <div class="clearfix"></div>
                                                            </div>
                                                            <div class="x_content">
                                                                <div class="row">

                                                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                        <label for="Placa">Hora Inicio Break</label>
                                                                        <div class="form-group">
                                                                            <div class='input-group date' id='myDatepicker4'>
                                                                                <input type='text' class="form-control" id="IdHoraInicioBreack" name="HoraInicioBreack" style="text-align:center;" required/>
                                                                                <span class="input-group-addon">
                                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                                </span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                        <label for="Color">Hora Fin Break</label>
                                                                        <div class="form-group">
                                                                            <div class='input-group date' id='myDatepicker5'>
                                                                                <input type='text' class="form-control" id="IdHoraFinBreack" name="HoraFinBreack" style="text-align:center;" required/>
                                                                                <span class="input-group-addon">
                                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                                </span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                        <label for="Marca">Tiempo Break</label>
                                                                        <div class="form-group">
                                                                            <div class='input-group date' id='myDatepicker6'>
                                                                                <input type='text' class="form-control" id="IdTiempoBreack" name="TiempoBreack" style="text-align:center;" required/>
                                                                                <span class="input-group-addon">
                                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                                </span>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="form-group">
                                                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"></label>
                                                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                                                            <div id="Break" class="btn-group" data-toggle="buttons">

                                                                                <label for="" style="visibility:hidden">---</label>  
                                                                                <input type="checkbox" value="" id="IdDescuentoBreack" class="flat"> Descuenta Break

                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                </div>
                                                                <div class="ln_solid"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- /Vehiculo -->  
                                                </div>
                                            </div>
                                            <!-- /Registro -->

                                            <!-- Configuracion -->
                                            <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="configuracion-tab">
                                                <div class="row">    

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="gracia_ae">Tiempo de Gracia AE</label>
                                                        <div class="form-group">
                                                            <div>
                                                                <input type='number' class="form-control" id="IdTiempoGraciaAE" name="TiempoGraciaAE"style="text-align:center;" required/>                                                                
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="gracia_as">Tiempo de Gracia AS</label>
                                                        <div class="form-group">
                                                            <div>
                                                                <input type='number' class="form-control" id="IdTiempoGraciaAS" name="TiempoGraciaAS" style="text-align:center;" required/>                                                                
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="gracia_de">Tiempo de Gracia DE</label>
                                                        <div class="form-group">
                                                            <div>
                                                                <input type='number' class="form-control" id="IdTiempoGraciaDE" name="TiempoGraciaDE" style="text-align:center;" required/>                                                                
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="gracia_ds">Tiempo de Gracia DS</label>
                                                        <div class="form-group">
                                                            <div>
                                                                <input type='number' class="form-control" id="IdTiempoGraciaDS" name="TiempoGraciaDS" style="text-align:center;" required/>                                                                
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="aprox_ae">Aproximación AE</label>
                                                        <div class="form-group">
                                                            <div>
                                                                <input type='number' class="form-control" id="IdAproximacionAE" name="AproximacionAE" style="text-align:center;" required/>                                                                
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="aprox_ds">Aproximación DS</label>
                                                        <div class="form-group">
                                                            <div>
                                                                <input type='number' class="form-control" id="IdAproximacionDS" name="AproximacionDS" style="text-align:center;" required/>                                                                
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="aprox_ae">Hora de Inicio Diurno</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker7'>
                                                                <input type='text' class="form-control" id="IdHoraInicioDiurno" name="HoraInicioDiurno" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="aprox_ds">Hora de Inicio Nocturno</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker8'>
                                                                <input type='text' class="form-control" id="IdHoraInicioNocturno" name="HoraInicioNocturno" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>    
                                            </div>
                                            <!-- /Configuracion -->
                                        </div>
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
                                        <table id="datatable" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>                                                    
                                                    <td>Codigo</td>
                                                    <td>Nombre</td>
                                                    <td>Hora Ini</td>
                                                    <td>Hora Fin</td>
                                                    <td>Teorico</td>
                                                    <td>Hora Ini Br</td>
                                                    <td>Hora Fin Br</td>
                                                    <td>Descanso</td>
                                                    <td>Tiempo GAE</td>
                                                    <td>Tiempo GAS</td>
                                                    <td>Tiempo GDE</td>
                                                    <td>Tiempo GDS</td>
                                                    <td>Aprox AE</td>
                                                    <td>Aprox DS</td>
                                                    <td>Opciones</td>
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
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>                                                    
                                                    <td class="text-center">
                                                        <button class="SetFormulario btn btn-warning btn-md" 
                                                                data-id=""
                                                                data-codigo=""
                                                                data-nombre=""                                                                
                                                                type="button" id="IdModificar" name="Modificar">Editar</button>
                                                        <button class="SetEliminar btn btn-dark btn-md" 
                                                                data-id=""
                                                                data-codigo=""
                                                                data-nombre=""                                                                
                                                                type="button" id="IdEliminar" name="Eliminar"></button>
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
        <!-- Inicializamos Datetimepicker -->
        <script>
            $('#myDatepicker').datetimepicker();

            $('#myDatepicker1').datetimepicker({
                format: 'HH:mm'
            });

            $('#myDatepicker2').datetimepicker({
                format: 'HH:mm'
            });

            $('#myDatepicker3').datetimepicker({
                format: 'HH:mm'
            });
            $('#myDatepicker4').datetimepicker({
                format: 'HH:mm'
            });
            $('#myDatepicker5').datetimepicker({
                format: 'HH:mm'
            });
            $('#myDatepicker6').datetimepicker({
                format: 'HH:mm'
            });
            $('#myDatepicker7').datetimepicker({
                format: 'HH:mm'
            });
            $('#myDatepicker8').datetimepicker({
                format: 'HH:mm'
            });
        </script>
    </body>
</html>
