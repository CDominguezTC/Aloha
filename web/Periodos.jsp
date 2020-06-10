<%-- 
    Document   : Periodos
    Created on : 07-nov-2019, 15:58:30
    Author     : Carlos A Dominguez D
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>      
         <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
         <script type="text/javascript" src="Principal/js/JsTiempos/ValidacionesPeriodos.js" ></script>
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
        <!-- Contenido -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Periodos</h3>
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
                                <!-- Formulario Registro -->
                                <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
                                    <div align="center" id="espera" style="display: none">
                                        <img src="Principal/images/loading_dash.gif">
                                    </div>
                                    
                                    <div id="Principal">    
                                    <input type="hidden" id="Id" name="Id">
                                                                     
                                    <div class="form-row">
                                        <div class="form-group col-md-6 col-sm-6 col-xs-12">
                                            <label for="Codigo">Código</label>
                                            <input type="number" class="form-control" id="IdCodigo" name="Codigo" min="0" required>
                                        </div>

                                        <div class="form-group col-md-6 col-sm-6 col-xs-12">
                                            <label for="Nombre">Nombre</label>
                                            <input type="text" class="form-control" id="IdNombre" name="Nombre" required="required">
                                        </div>
                                    </div>
                                    <div class='col-md-6 col-sm-6 col-xs-12'>
                                        <label for="FechaInicio">Fecha Inicio</label>
                                        <div class="form-group">
                                            <div class='input-group date' id='myDatepicker2'>
                                                <input type="text" class="form-control" id="IdFechaInicio" name="FechaInicio"/>
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class='col-md-6 col-sm-6 col-xs-12'>
                                        <label for="FechaFin">Fecha Fin</label>
                                        <div class="form-group">
                                            <div class='input-group date' id='myDatepicker3'>
                                                <input type='text' class="form-control" id="IdFechaFin" name="FechaFin"/>
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="Observacion">Observación</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <textarea class="form-control col-md-7 col-xs-12" id="IdObservacion" name="Observacion" style="height:100px;" required="required"></textarea>
                                        </div>
                                    </div>
                                    <!-- Botones -->
                                    <div class="ln_solid"></div>
                                    <div class="row">
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
                                                    <th>InterCod</th>
                                                    <th>Código</th>
                                                    <th>Nombre</th>
                                                    <th>Fecha Inicio</th>
                                                    <th>Fecha Fin</th>
                                                    <th>Observaciones</th>
                                                    <th>Opciones</th>
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
                                                <td class="text-center">
                                                    <button class="SetFormulario btn btn-warning btn-md" 
                                                            data-id=""
                                                            data-codigo=""
                                                            data-nombre=""
                                                            data-fechaInicio=""
                                                            data-fechaFin=""
                                                            data-observacion=""
                                                            type="button" id="IdModificar" name="Modificar"></button>
                                                    <button class="SetEliminar btn btn-dark btn-md" 
                                                            data-id=""
                                                            data-codigo=""
                                                            data-nombre=""
                                                            data-fechaFnicio=""
                                                            data-fechaFin=""
                                                            data-observacion=""                                                              
                                                            type="button" id="IdEliminar" name="Eliminar"></button>
                                                </td>                                                    
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
            format: 'YYYY-MM-DD',
            minDate: new Date(),
            locale: 'es'
            
        });

        $('#myDatepicker3').datetimepicker({
            format: 'YYYY-MM-DD',
            minDate: new Date(),
            locale: 'es',
            useCurrent: false 
        });
        
        $("#myDatepicker2").on("dp.change", function (e) {
            $('#myDatepicker3').data("DateTimePicker").minDate(e.date);
        });
        $("#myDatepicker3").on("dp.change", function (e) {
            $('#myDatepicker2').data("DateTimePicker").maxDate(e.date);
        });
                        
        </script>
    </body>
</html>
