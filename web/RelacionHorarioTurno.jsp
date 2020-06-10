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
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsTiempos/ValidacionesRelacionHorarioTurno.js" ></script>   
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
        <!-- Contenido -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Relacion Grupo Turno - Turnos</h3>
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
                                        <div class="row">
                                            <input type="hidden" id="Id" name="Id">
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="grupo_horario">Grupo Turno</label>
                                                <select id="IdGrupo_Horario" class="form-control" required>
                                                    <option value="" disabled selected>Seleccione</option>                                                    
                                                </select>
                                            </div>

                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="turno">Turno</label>
                                                <select id="IdTurno" class="form-control" required>
                                                    <option value="" disabled selected>Seleccione</option>                                                    
                                                </select>
                                            </div>
                                            <div class="col-md-12 col-sm-12 col-xs-12 form-group" style="text-align: center">
                                                <label for="tipo_id">Dias de la semana </label>
                                                <select id="IdDia" class="form-control" required>
                                                    <option value="0">Seleccione</option>
                                                    <option value="1">Lunes</option>
                                                    <option value="2">Martes</option>
                                                    <option value="3">Miercoles</option>
                                                    <option value="4">Jueves</option>                                                                    
                                                    <option value="5">Viernes</option>                                                                    
                                                    <option value="6">Sabado</option>                                                                    
                                                    <option value="7">Domingo</option>                                                                    
                                                    <option value="8">Festivo</option>                                                                    
                                                </select>
                                            </div>
                                        </div>                                            
                                    </div>
                                    <!-- Botones Formulario -->                                    
                                    <div class="ln_solid"></div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-4">
                                                <button class="btn btn-primary btn-sm" type="button" id="IdAgregar" name="Agregar"><i class="fa fa-plus"></i> Agregar</button>
                                                <button class="btn btn-success btn-sm" type="button" id="IdGuardar" name="Guardar"><i class="fa fa-save"></i> Guardar</button>                                                
                                                <button class="btn btn-danger btn-sm" type="reset"><i class="fa fa-close"></i> Cancelar</button>
                                            </div>
                                            <div class="row">
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
                                                    <td>Selecione</td>
                                                    <td>Grupo Turno</td>
                                                    <td>Turno</td>
                                                    <td>Dia</td>            
                                                    <td>Opciones</td>
                                                </tr>
                                            </thead>
                                            <tbody>                                                
                                            </tbody>
                                        </table>
                                        <!-- /Tabla -->                                        
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-4">
                                                <button class="btn btn-danger btn-sm" type="button" id="IdEliminarVarios" name="EliminarVarios"><i class="fa fa-trash"></i> Eliminar Seleccionados</button>
                                                <button class="btn btn-info btn-sm" type="button" id="IdGetModal" name="ClonarVarios" data-toggle="modal" data-target="#exampleModal" ><i class="fa fa-copy"></i> Clonar Seleccionados</button>
                                            </div>                                            
                                            <div class="row">
                                            </div> 
                                        </div>
                                    </div> 
                                </div>                    
                            </div>
                        </div>
                        <!-- /Segunda Sección-->
                        <!-- Modal-->
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Nuevo Grupo Turno</h5>                                        
                                    </div>
                                    <div class="modal-body">
                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                            <label for="grupo_horario_modal">Nuevo Grupo Turno</label>
                                            <select id="IdGrupo_Horario_Modal" class="form-control" required>
                                                <option value="" disabled selected>Seleccione</option>                                                    
                                            </select>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary" id="IdClonarVarios">Clonar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /Modal-->                        
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