<%-- 
    Document   : RelacionHorarioTurno
    Created on : 07-nov-2019, 16:27:04
    Author     : Carlos A Dominguez D
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
                        <h3>Grupo Horario Vs Turnos</h3>
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

                                    <div class="row">

                                        <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                            <label for="grupo_horario">Grupo Horario</label>
                                            <select id="grupo_horario" class="form-control" required>
                                                <option value="" disabled selected>Seleccione</option>
                                                <option value="1">Grupo de turno por defecto</option>
                                            </select>
                                        </div>

                                        <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                            <label for="horario">Horario</label>
                                            <select id="horario" class="form-control" required disabled>
                                                <option value="" disabled selected>Seleccione</option>
                                                <option value="1">Turno por defecto</option>
                                            </select>
                                        </div>
                                    </div>      

                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dia_semana">Dias Semana</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <select class="select2_multiple form-control" multiple="multiple" required disabled>
                                                <option disabled>Seleccione (Varios)</option>
                                                <option>Lunes</option>
                                                <option>Martes</option>
                                                <option>Miercoles</option>
                                                <option>Jueves</option>
                                                <option>Viernes</option>
                                                <option>Sabado</option>
                                                <option>Domingo</option>
                                                <option>Festivo</option>
                                            </select>
                                        </div>
                                    </div>
                                    <!-- Botones Formulario -->
                                    <div class="ln_solid"></div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-4">
                                                <button class="btn btn-primary btn-sm" type="button" disabled><i class="fa fa-plus"></i> Adicionar</button>
                                                <button type="button" class="btn btn-dark btn-sm" disabled><i class="fa fa-eraser"></i> Limpiar</button>
                                                <button type="button" class="btn btn-danger btn-sm" disabled><i class="fa fa-trash-o"></i> Limpiar Todo</button>
                                            </div>
                                        </div>
                                    </div>  
                                    <!-- /Botones Formulario -->
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
                                                    <th>Grupo Horario</th>
                                                    <th>Horario</th>
                                                    <th>Dia Semana</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Grupo de turno por defecto</td>
                                                    <td>Turno por defecto</td>
                                                    <td>Lunes</td>
                                                </tr>
                                                <tr>
                                                    <td>Grupo de turno por defecto</td>
                                                    <td>Turno por defecto</td>
                                                    <td>Martes</td>
                                                </tr>
                                                <tr>
                                                    <td>Grupo de turno por defecto</td>
                                                    <td>Turno por defecto</td>
                                                    <td>Miercoles</td>
                                                </tr>
                                                <tr>
                                                    <td>Grupo de turno por defecto</td>
                                                    <td>Turno por defecto</td>
                                                    <td>Jueves</td>
                                                </tr>
                                                <tr>
                                                    <td>Grupo de turno por defecto</td>
                                                    <td>Turno por defecto</td>
                                                    <td>Viernes</td>
                                                </tr>
                                                <tr>
                                                    <td>Grupo de turno por defecto</td>
                                                    <td>Turno por defecto</td>
                                                    <td>Sabado</td>
                                                </tr>
                                                <tr>
                                                    <td>Grupo de turno por defecto</td>
                                                    <td>Turno por defecto</td>
                                                    <td>Domingo</td>
                                                </tr>
                                                <tr>
                                                    <td>Grupo de turno por defecto</td>
                                                    <td>Turno por defecto</td>
                                                    <td>Festivo</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <!-- /Tabla -->
                                        <!-- Botones Tabla -->
                                        <div class="ln_solid"></div>
                                        <div class="row">
                                            <div class="form-group">
                                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-4">
                                                    <button class="btn btn-primary btn-sm" type="button"><i class="fa fa-plus"></i> Agregar</button>
                                                    <button type="button" class="btn btn-success btn-sm" disabled><i class="fa fa-save"></i> Guardar</button>
                                                    <button type="button" class="btn btn-danger btn-sm" disabled><i class="fa fa-trash"></i> Eliminar</button>
                                                </div>
                                                <div class="row">
                                                </div> 
                                            </div>
                                        </div>  
                                        <!-- /Botones Tabla -->
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
    </body>
</html>

