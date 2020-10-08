<%-- 
    Document   : Liquidacion
    Created on : 13/12/2019, 11:38:57 AM
    Author     : Frankie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>      
        <style>
            .size{
                width: 30px;
            }
        </style>
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsTiempos/ValidacionesLiquidacionTiempos.js" ></script>  
        <!-- Contenidocarga -->
        <!-- Contenido -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Liquidación</h3>
                    </div>
                </div>
                <!-- Primera Sección-->
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <!--Modal Filtros-->
                        <div class="x_content">
                            <div id="IdModalFiltroPersonas" class="modal fade" role="dialog">
                                <div class="modal-dialog modal-lg">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Seleccion de Empleados</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                    <label for="filtro_empresa">Filtro Empresa</label>
                                                    <select id="IdEmpresa" class="form-control">
                                                        <option value="0" disabled selected>Seleccione</option>                                                            
                                                    </select>
                                                </div>

                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                    <label for="filtro_dependencia">Filtro Dependencia</label>
                                                    <select id="IdDependencia" class="form-control">
                                                        <option value="0" disabled selected>Seleccione</option>                                                            
                                                    </select>
                                                </div>

                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                    <label for="filtro_centro_costo">Filtro Centro Costo</label>
                                                    <select id="IdCentroCosto" class="form-control">
                                                        <option value="0" disabled selected>Seleccione</option>                                                            
                                                    </select>
                                                </div>

                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                    <label for="filtro_area">Filtro Area</label>
                                                    <select id="IdArea" class="form-control">
                                                        <option value="0" disabled selected>Seleccione</option>                                                            
                                                    </select>
                                                </div>

                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                    <label for="filtro_grupo_horario">Filtro Grupo Horario</label>
                                                    <select id="IdGrupoHorario" class="form-control">
                                                        <option value="0" disabled selected>Seleccione</option>                                                            
                                                    </select>
                                                </div>

                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                    <label for="filtro_ciudad">Filtro Ciudad</label>
                                                    <select id="IdCiudad" class="form-control">
                                                        <option value="0" disabled selected>Seleccione</option>                                                            
                                                    </select>
                                                </div>

                                            </div> 
                                        </div>
                                        <div class="modal-footer">                                                
                                            <button type="button" class="btn btn-round btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-round btn-primary"  id="IdFiltrarEmpleados" name="FiltrarEmpleados"><i class="fa fa-filter"></i> Seleccionar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>   
                        </div>
                        <!--/Modal Filtros-->
                        <div class="row">                                     
                            <div class="col-md-4 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <!--/Formulario de Parametros  style="height: 600px;"-->
                                    <div class="x_title">
                                        <h2>Parametros</h2>                                       
                                        <div class="clearfix"></div>
                                    </div>                                    
                                    <div class="x_content">
                                        <div class="row">
                                            <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                <label for="periodo">Periodo</label>
                                                <select id="IdPeriodo" class="form-control" required>
                                                    <option value="" disabled selected>Seleccione</option>                                                    
                                                </select>
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="fecha_inicial">Fecha Inicial</label>
                                                <div class="form-group">
                                                    <div class='input-group date' id='myDatepicker1'>
                                                        <input type='text' id="IdFechaInicioPeriodo" class="form-control" required/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>                                        
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="fecha_final">Fecha Final</label> 
                                                <div class="form-group">
                                                    <div class='input-group date' id='myDatepicker2'>
                                                        <input type='text' id="IdFechaFinPeriodo" class="form-control" required/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>                                       
                                        </div>
                                    </div>
                                    <!--/Formulario de Parametros-->
                                    <!--Formulario de Empleados-->                                    
                                    <div class="x_content">
                                        <!-- Formulario -->
                                        <form id="id_form">
                                            <table id="IdTablaEmpleados" class="table table-striped jambo_table bulk_action dt-responsive nowrap">                                                
                                            </table>                                            
                                        </form>                                         
                                        <br/>
                                    </div>
                                </div>
                            </div>                            
                            <div class="col-md-8 col-sm-12 col-xs-12">
                                <div class="" role="tabpane1" data-example-id="togglable-tabs">
                                    <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                        <li role="presentation" class="active"><a href="#tab_content1" id="marcaciones-tab" role="tab" data-toggle="tab" aria-expanded="true">Marcaciones</a></li>
                                        <li role="presentation" class=""><a href="#tab_content2" role="tab" id="liquidacion-tab" data-toggle="tab" aria-expanded="false">Liquidación</a></li>
                                    </ul>
                                    <div id="myTabContent" class="tab-content">
                                        <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="marcaciones-tab">                                            
                                            <div class="col-md-10 col-sm-12 col-xs-12">
                                                <div class="x_panel">
                                                    <div class="x_title">
                                                        <h2>Marcaciones</h2>                                                        
                                                        <div class="clearfix"></div>
                                                    </div>
                                                    <div class="x_content">
                                                        <table id="tablemarcaciones" class="table table-striped jambo_table bulk_action">
                                                            <thead>
                                                                <tr>
                                                                    <th class="size">Seleccione</th>
                                                                    <th>Fecha</th>
                                                                    <th>Hora</th>
                                                                    <th>Sentido</th>
                                                                    <th class="size">Detalle</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>                                                                                                                        
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                </tr>                                                                
                                                                <!--tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr>                                                                
                                                                <tr>                                                                                                                        
                                                                    <td class="text-center"><input type="checkbox" class="flat" value="1"></td>
                                                                    <td>01-07-2020</td>
                                                                    <td>07:30:00</td>
                                                                    <td>D</td>
                                                                    <td><button class="btn btn-secondary btn-sm" type="button" form="marcaciones_form"><i class="fa fa-desktop"></i></button></td>
                                                                </tr-->                                                                
                                                            </tbody>
                                                        </table>                                                         
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-2 col-sm-12 col-xs-12">
                                                <div class="x_panel">  
                                                    <div class="x_title">
                                                        <h2>Cotroles</h2>                                                        
                                                        <div class="clearfix"></div>
                                                    </div>
                                                    <div class="x_content">                                                                                                                                                                        
                                                        <div class="row">
                                                            <div class="form-group">
                                                                <div class="row" style="align-content: center">
                                                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                        <button class="btn btn-primary btn-sm btn-block" type="button" form="marcaciones_form"><i class="fa fa-plus"> Agregar<br/>Marcacion</i></button>
                                                                    </div>
                                                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                        <button class="btn btn-warning btn-sm btn-block" type="button" form="marcaciones_form"><i class="fa fa-edit"> Editar<br/>Marcacion</i></button>
                                                                    </div>
                                                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                        <button class="btn btn-danger btn-sm btn-block" type="button" form="marcaciones_form"><i class="fa fa-trash"> Eliminar<br/>Marcacion</i></button>
                                                                    </div>                                                                      
                                                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                        <button class="btn btn-dark btn-sm btn-block" type="button" form="marcaciones_form"><i class="fa fa-filter"> Consutar<br/>Marcaciones<br/>Eliminados</i></button>
                                                                    </div>                                                                      
                                                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                        <button class="btn btn-info btn-sm btn-block" type="button" form="marcaciones_form"><i class="fa fa-user"> Seleccionar<br/>Empleados</i></button>
                                                                    </div>                                                                                                                                          
                                                                </div>                                                                 
                                                            </div>
                                                        </div>  
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div role="tabpane1" class="tab-pane fade" id="tab_content2" aria-labelledby="liquidacion-tab">                                            
                                            <div class="clearfix"></div>
                                            <div class="row">
                                                <div class="col-md-10 col-sm-12 col-xs-12">
                                                    <div class="x_panel">
                                                        <div class="x_title">
                                                            <h2>Liquidación</h2>
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
                                                            <table id="tableliquidacion" class="table table-striped jambo_table bulk_action dt-responsive nowrap">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Tipo de Identificación</th>
                                                                        <th>Cedula</th>
                                                                        <th>Nombre</th>
                                                                        <th>Codigo</th>
                                                                        <th>Fecha Inicio </th>
                                                                        <th>Hora Inicio </th>
                                                                        <th>Fecha Fin </th>
                                                                        <th>Hora Fin </th>
                                                                        <th>Grupo Horario</th>
                                                                        <th>Turno </th>
                                                                        <th>HDO </th>
                                                                        <th>HNO</th>
                                                                        <th>HFDO</th>
                                                                        <th>HFNO</th>
                                                                        <th>HDDO</th>
                                                                        <th>HDNO</th>
                                                                        <th>HEDO</th>
                                                                        <th>HENO</th>
                                                                        <th>HEFO</th>
                                                                        <th>HEFO</th>
                                                                        <th>HEDO</th>
                                                                        <th>HEDO</th>
                                                                        <th>THL</th>
                                                                        <th>THO</th>
                                                                        <th>THE</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <td>CEDULA DE CIUDADANIA</td>
                                                                        <td>1144078608</td>
                                                                        <td>JULIAN</td>
                                                                        <td>12345</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>06:00:00</td>
                                                                        <td>01-07-2020</td>
                                                                        <td>14:00:00</td>
                                                                        <td>Planta</td>
                                                                        <td>06:00 a 14:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>
                                                                        <td>08:00:00</td>                                                                        
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>                    
                                                </div>
                                                <div class="col-md-2 col-sm-12 col-xs-12">
                                                    <div class="x_panel">  
                                                        <div class="x_title">
                                                            <h2>Cotroles</h2>                                                        
                                                            <div class="clearfix"></div>
                                                        </div>
                                                        <div class="x_content">                                                                                                                                                                        
                                                            <div class="row">
                                                                <div class="form-group">
                                                                    <div class="row" style="align-content: center">
                                                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                            <button class="btn btn-primary btn-sm btn-block" type="button" form="marcaciones_form"><i class="fa fa-cog"> Cacular<br/>Empleado</i></button>
                                                                        </div>
                                                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                            <button class="btn btn-warning btn-sm btn-block" type="button" form="marcaciones_form"><i class="fa fa-cogs"> Calcular<br/>Todos</i></button>
                                                                        </div>
                                                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                            <button class="btn btn-danger btn-sm btn-block" type="button" form="marcaciones_form"><i class="fa fa-cloud-upload"> Exportar<br/>Calculos</i></button>
                                                                        </div>      
                                                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                            <button class="btn btn-info btn-sm btn-block" type="button" form="marcaciones_form"><i class="fa fa-user"> Seleccionar<br/>Empleados</i></button>
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
                            </div>
                        </div>
                    </div
                </div>
            </div>
        </div>        
        <%@include file="Principal/Script.html" %>  
        <!-- Inicializamos Datetimepicker -->
        <script>
            $('#myDatepicker').datetimepicker();

            $('#myDatepicker1').datetimepicker({
                format: 'YYYY-MM-DD',
                locale: 'es'
            });

            $('#myDatepicker2').datetimepicker({
                format: 'YYYY-MM-DD',
                locale: 'es'
            });
            $('#myDatepicker3').datetimepicker({
                format: 'DD.MM.YYYY'
            });
            $('#myDatepicker4').datetimepicker({
                format: 'hh:mm A'
            });
        </script>
    </body>
</html>
