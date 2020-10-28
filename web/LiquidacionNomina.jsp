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

            .center {
                display: block;
                margin-left: auto;
                margin-right: auto;
                border-radius: 50%;
                pointer-events:none;
            }


            input[type="text"]:disabled {
                background: #ffffff;
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
                        <!--Modal info empleado-->
                        <div class="modal fade" id="modalDetalleEmpleado" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabel">Detalle empleado</h4>
                                    </div>
                                    <div class="modal-body">

                                        <div class="row">
                                            <input type="hidden" id="Id" name="Id">

                                            <div class="profile_img">
                                                <div id="crop-avatar">
                                                    <!-- Img Foto-->
                                                    <img class="img-responsive avatar-view center" src="Principal/images/user.png" alt="Foto" title="Foto" height="200" width="200">
                                                </div>
                                            </div>

                                            </br>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Tipo_Id">Tipo Identificacion</label>
                                                <input type="text" class="form-control" id="Tipo_Id" name="Tipo_Id" min="0" disabled>
                                            </div>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Identificacion">Identificacion</label>
                                                <input type="text" class="form-control" id="Identificacion" required="required" disabled>
                                            </div>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Nombre">Nombre</label>
                                                <input type="text" class="form-control" id="Nombre" required="required" disabled>
                                            </div>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Apellido">Apellido</label>
                                                <input type="text" class="form-control" id="Apellido" name="Apellido" min="0" disabled>
                                            </div>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="codigo">Codigo</label>
                                                <input type="text" class="form-control" id="codigo" name="codigo" min="0" disabled>
                                            </div>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Cargo_Persona">Cargo Persona</label>
                                                <input type="text" class="form-control" id="Cargo_Persona" name="Cargo_Persona" min="0" disabled>
                                            </div>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Empresa">Empresa</label>
                                                <input type="text" class="form-control" id="Empresa" name="Empresa" min="0" disabled>
                                            </div>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Dependencia">Dependencia</label>
                                                <input type="text" class="form-control" id="Dependencia" name="Dependencia" min="0" disabled>
                                            </div>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Area">Area</label>
                                                <input type="text" class="form-control" id="Area" name="Area" min="0" disabled>
                                            </div>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Grupo_Horario">Grupo Horario</label>
                                                <input type="text" class="form-control" id="Grupo_Horario" name="Grupo_Horario" min="0" disabled>
                                            </div>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Ciudad">Ciudad</label>
                                                <input type="text" class="form-control" id="Ciudad" name="Ciudad" min="0" disabled>
                                            </div>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Observacion">Observacion</label>
                                                <input type="text" class="form-control" id="Observacion" name="Observacion" min="0" disabled>
                                            </div>

                                            <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label for="Firma">Firma</label>
                                                <select id="Firma" class="form-control" required>
                                                    <option value="" disabled selected>Seleccione</option>
                                                    <option value="1">Si</option>
                                                    <option value="1">No</option>
                                                </select>
                                            </div>

                                            <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label for="Huella">Huella</label>
                                                <select id="Huella" class="form-control" required>
                                                    <option value="" disabled selected>Seleccione</option>
                                                    <option value="1">Si</option>
                                                    <option value="1">No</option>
                                                </select>
                                            </div>

                                        </div>
                                    </div>

                                    <!--div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-close"></i> Cerrar</button>
                                        <button type="button" class="btn btn-success"><i class="fa fa-save"></i> Guardar</button>
                                    </div-->

                                </div>
                            </div>
                        </div>
                    </div>
                    <!--/Modal info empleado-->
                    <!--Modal editar marcacion-->
                    <div class="modal fade" id="modalEditarMarcacion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Editar Marcacion</h4>
                                </div>
                                <div class="modal-body">

                                    <div class="row">
                                        <input type="hidden" id="IdMarcacionE" name="Id">

                                        <div class='col-md-6 col-sm-6 col-xs-12'>
                                            <label for="Fecha">Fecha</label>
                                            <div class="form-group">
                                                <div class='input-group date' id='myDatepicker3'>
                                                    <input id="IdFechaM" type='text' class="form-control" required onkeydown="return false"  />
                                                    <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                            <label for="Hora">Hora</label>
                                            <div class="form-group">
                                                <div class='input-group date' id='myDatepicker4'>
                                                    <input id="IdHoraM" type='text' class="form-control" style="text-align:center;" required/>
                                                    <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-time"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label for="Sentido">Sentido</label>
                                            <select id="IdSentidoM" class="form-control" required>
                                                <option value="" disabled selected>Seleccione</option>
                                                <option value="Entrada">Entrada</option>
                                                <option value="Salida-Int">Salida-Int</option>
                                                <option value="Entrada-Int">Entrada-Int</option>                                                
                                                <option value="Salida">Salida</option>                                                                                                
                                            </select>
                                        </div>

                                        <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                            <label for="Observacion">Observacion</label>
                                            <textarea id="IdObservacionM" type="text" class="form-control" id="Observacion" name="Observacion" style="height:100px;"></textarea>
                                        </div>

                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-close"></i> Cerrar</button>
                                    <button id="IdEliminarM" type="button" class="btn btn-warning"><i class="fa fa-trash"></i> Eliminar</button>
                                    <button id="IdGuardarM" type="button" class="btn btn-success"><i class="fa fa-save"></i> Guardar</button>
                                    
                                </div>

                            </div>
                        </div>
                    </div>
                    <!--/Modal editar marcacion-->

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
                                                                <!--th class="size">Seleccione</th-->
                                                                <th>Fecha</th>
                                                                <th>Hora Entrada</th>
                                                                <th>Hora Salida</th>
                                                                <th>Reloj</th>
                                                                <th class="size">Detalle</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>                                                                                                                        
                                                                <!--td></td-->
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
                                                                    <button class="btn btn-primary btn-sm btn-block" id="IdAgregarMar" type="button" form="marcaciones_form"><i class="fa fa-plus"> Agregar<br/>Marcacion</i></button>
                                                                </div>
                                                                <!--div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                    <button class="btn btn-warning btn-sm btn-block" type="button" form="marcaciones_form"><i class="fa fa-edit"> Editar<br/>Marcacion</i></button>
                                                                </div>
                                                                <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                    <button class="btn btn-danger btn-sm btn-block"  type="button" form="marcaciones_form"><i class="fa fa-trash"> Eliminar<br/>Marcacion</i></button>
                                                                </div-->                                                                      
                                                                <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                    <button class="btn btn-dark btn-sm btn-block" id="IdConsultarEli" type="button" form="marcaciones_form"><i class="fa fa-filter"> Consutar<br/>Marcaciones<br/>Eliminadas</i></button>
                                                                </div>                                                                      
                                                                <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                    <button class="btn btn-info btn-sm btn-block" type="button" form="marcaciones_form" id="IdSeleccionarMa"><i class="fa fa-user"> Seleccionar<br/>Empleados</i></button>
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
                                                                        <button class="btn btn-info btn-sm btn-block" type="button" form="marcaciones_form" id="IdSeleccionarLi"><i class="fa fa-user"> Seleccionar<br/>Empleados</i></button>
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
            format: 'YYYY-MM-DD',
            locale: 'es'
        });
        $('#myDatepicker4').datetimepicker({
            format: 'HH:mm'
        });
    </script>
</body>
</html>
