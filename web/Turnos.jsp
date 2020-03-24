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
                                <!-- h2>Configuración</h2-->
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

                                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
                                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                            <li role="presentation" class="active"><a href="#tab_content1" id="registro-tab" role="tab" data-toggle="tab" aria-expanded="true">Registro</a>
                                            </li>
                                            <li role="presentation" class=""><a href="#tab_content2" role="tab" id="configuracion-tab" data-toggle="tab" aria-expanded="false">Configuración</a>
                                            </li>
                                        </ul>
                                        <div id="myTabContent" class="tab-content">
                                            <!-- Registro -->
                                            <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="registro-tab">
                                                <div class="row">

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="codigo">Código</label>
                                                        <input type="number" class="form-control" id="codigo" name="codigo" min="0" required>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="nombre">Nombre</label>
                                                        <input type="text" class="form-control" id="nombre" required="required">
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="hora_inicio">Hora Inicio</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker2'>
                                                                <input type='text' class="form-control" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="hora_fin">Hora Fin</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker3'>
                                                                <input type='text' class="form-control" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="teorico">Teorico</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker4'>
                                                                <input type='text' class="form-control" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="tiempo_descanso">Tiempo Descanso</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker5'>
                                                                <input type='text' class="form-control" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="hora_diurno">Hora Inicio Diurno</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker6'>
                                                                <input type='text' class="form-control" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="hora_nocturno">Hora Inicio Nocturno</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker7'>
                                                                <input type='text' class="form-control" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                            <!-- /Registro -->

                                            <!-- Configuracion -->
                                            <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="configuracion-tab">
                                                <div class="row">    

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="gracia_ae">Tiempo de Gracia AE</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker8'>
                                                                <input type='text' class="form-control" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="gracia_as">Tiempo de Gracia AS</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker9'>
                                                                <input type='text' class="form-control" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="gracia_de">Tiempo de Gracia DE</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker10'>
                                                                <input type='text' class="form-control" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="gracia_ds">Tiempo de Gracia DS</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker11'>
                                                                <input type='text' class="form-control" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="aprox_ae">Aproximación AE</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker12'>
                                                                <input type='text' class="form-control" style="text-align:center;" required/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-time"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                        <label for="aprox_ds">Aproximación DS</label>
                                                        <div class="form-group">
                                                            <div class='input-group date' id='myDatepicker13'>
                                                                <input type='text' class="form-control" style="text-align:center;" required/>
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
                                    </div>

                                    <!-- Botones -->
                                    <div class="ln_solid"></div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-4">
                                                <button class="btn btn-primary btn-sm" type="button"><i class="fa fa-plus"></i> Agregar</button>
                                                <button class="btn btn-warning btn-sm" type="button"><i class="fa fa-edit"></i> Editar</button>
                                                <button class="btn btn-danger btn-sm" type="reset"><i class="fa fa-close"></i> Cancelar</button>
                                                <br/><br/>
                                                <div class="row">
                                                <div class="form-group">
                                                  <div class="col-md-8 col-sm-8 col-xs-12 col-md-offset-1">
                                                      <button class="btn btn-success btn-sm" type="button" disabled><i class="fa fa-save"></i> Guardar</button>
                                                      <button class="btn btn-dark btn-sm" type="button" disabled><i class="fa fa-trash"></i> Eliminar</button>
                                                  </div>
                                                </div>
                                              </div>         
                                            </div>
                                        </div>
                                    </div> 
                                    <!-- /Botones -->
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
                                        <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%" >
                                            <thead>
                                                <tr>
                                                    <th>Código</th>
                                                    <th>Nombre</th>
                                                    <th>Hora Inicio</th>
                                                    <th>Hora Fin</th>
                                                    <th>Teorico</th>
                                                    <th>Tiempo Descanso</th>
                                                    <th>Hora Inicio Diurno</th>
                                                    <th>Hora Inicio Nocturno</th>
                                                    <th>Tiempo de Gracia AE</th>
                                                    <th>Tiempo de Gracia AS</th>
                                                    <th>Tiempo de Gracia DE</th>
                                                    <th>Tiempo de Gracia DS</th>
                                                    <th>Aproximación AE</th>
                                                    <th>Aproximación DS</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="row">001</th>
                                                    <td>Turno por defecto</td>
                                                    <td>08:00</td>
                                                    <td>16:00</td>
                                                    <td>08:00</td>
                                                    <td>01:00</td>
                                                    <td>5</td>
                                                    <td>5</td>
                                                    <td>5</td>
                                                    <td>5</td>
                                                    <td>5</td>
                                                    <td>5</td>
                                                    <td>5</td>
                                                    <td>5</td>
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
          <div class="clearfix"></div>
        </footer>
        <!-- Footer -->
        <%@include file="Principal/Script.html" %>  
        <!-- Inicializamos Datetimepicker -->
        <script>
            $('#myDatepicker').datetimepicker();

            $('#myDatepicker2').datetimepicker({
                format: 'hh:mm A'
            });

            $('#myDatepicker3').datetimepicker({
                format: 'hh:mm A'
            });
            $('#myDatepicker4').datetimepicker({
                format: 'hh:mm A'
            });
            $('#myDatepicker5').datetimepicker({
                format: 'hh:mm A'
            });
            $('#myDatepicker6').datetimepicker({
                format: 'hh:mm A'
            });
            $('#myDatepicker7').datetimepicker({
                format: 'hh:mm A'
            });
            $('#myDatepicker8').datetimepicker({
                format: 'ss'
            });
            $('#myDatepicker9').datetimepicker({
                format: 'ss'
            });
            $('#myDatepicker10').datetimepicker({
                format: 'ss'
            });
            $('#myDatepicker11').datetimepicker({
                format: 'ss'
            });
            $('#myDatepicker12').datetimepicker({
                format: 'ss'
            });
            $('#myDatepicker13').datetimepicker({
                format: 'ss'
            });
        </script>
    </body>
</html>
