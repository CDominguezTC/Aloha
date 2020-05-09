<%-- 
    Document   : HorarioConsumo
    Created on : 11-mar-2020, 14:59:24
    Author     : Carlos A Dominguez D
--%>

<%@page import="Controladores.ControladorTipoConsumo"%>
<%@page import="Modelo.ModeloTipoConsumo"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>        
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsTiempos/ValidacionesHorarioConsumo.js" ></script>         
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
        <!-- Contenidos -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Horario Consumo</h3>
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
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="codigo">Código</label>
                                                <input type="number" class="form-control" id="IdCodigo" name="Codigo" required="required">
                                            </div>
                                            <input type="hidden" id="IdNombreOld" name="NombreOld">
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="nombre">Nombre</label>
                                                <input type="text" class="form-control" id="IdNombre" name="Nombre" required="required">
                                            </div>
                                            
                                             
                                            <!-- <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="codigo">Hora Inicio</label>
                                                <input type="text" class="form-control" id="IdHoraInicio" name="HoraInicio" required="required">
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="nombre">Hora Fin</label>
                                                <input type="text" class="form-control" id="IdHoraFin" name="HoraFin" required="required">
                                            </div>-->
                                            <input type="hidden" id="IdHoraInicioOld" name="HoraInicioOld">
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="HoraInicio">Hora Inicio</label>
                                                <div class="form-group">
                                                    <div class='input-group date' id='myDatepicker2'>
                                                        <input type='text' class="form-control" id="IdHoraInicio" name="HoraInicio" style="text-align:center;" required/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-time"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <input type="hidden" id="IdHoraFinOld" name="HoraFinOld">
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="HoraFin">Hora Fin</label>
                                                <div class="form-group">
                                                    <div class='input-group date' id='myDatepicker3'>
                                                        <input type='text' class="form-control"  id="IdHoraFin" name="HoraFin"  style="text-align:center;" required/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-time"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <input type="hidden" id="IdNoConsumosOld" name="CantidadConsumoOld">
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="codigo">Cantidad de Consumos</label>
                                                <input type="number" class="form-control" id="IdNoConsumos" name="CantidadConsumo" required="required">
                                            </div>
                                            <input type="hidden" id="IdTipoConsumoOld" name="TipoConsumoOld">
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="nombre">Tipo Consumo</label>
                                                <select id="IdTipoConsumo" class="form-control" required>
                                                        <option value="-1" disabled selected>Seleccione</option>
                                                        <%
                                                            LinkedList<ModeloTipoConsumo> linkedListModeloTipoConsumos;
                                                            ControladorTipoConsumo controladorTipoConsumo = new ControladorTipoConsumo();
                                                            linkedListModeloTipoConsumos = controladorTipoConsumo.Read();
                                                            for (ModeloTipoConsumo modeloTipoConsumo : linkedListModeloTipoConsumos)
                                                            {
                                                        %>  
                                                        <option value=<%=modeloTipoConsumo.getId()%>><%=modeloTipoConsumo.getNombre()%></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>
                                            </div>                                                                                        
                                        </div>
                                        <div class="row" style="text-align: center">
                                            <input type="hidden" id="IdLunesOld" name="LunesOld">
                                            <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                <label for="tipo_id">Lunes</label>
                                                <select id="IdLunes" class="form-control" required>                                                    
                                                    <option value="N">No</option>
                                                    <option value="S">Si</option>                                                                    
                                                </select>
                                            </div>
                                            <input type="hidden" id="IdMartesOld" name="MartesOld">
                                            <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                <label for="tipo_id">Martes</label>
                                                <select id="IdMartes" class="form-control" required>                                                    
                                                    <option value="N">No</option>
                                                    <option value="S">Si</option>                                                                    
                                                </select>
                                            </div>
                                            <input type="hidden" id="IdMiercolesOld" name="MiercolesOld">
                                            <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                <label for="tipo_id">Miercoles</label>
                                                <select id="IdMiercoles" class="form-control" required>                                                    
                                                    <option value="N">No</option>
                                                    <option value="S">Si</option>                                                                    
                                                </select>
                                            </div>
                                            <input type="hidden" id="IdJuevesOld" name="JuevesOld">
                                            <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                <label for="tipo_id">Jueves</label>
                                                <select id="IdJueves" class="form-control" required>                                                    
                                                    <option value="N">No</option>
                                                    <option value="S">Si</option>                                                                    
                                                </select>
                                            </div>
                                            <input type="hidden" id="IdViernesOld" name="ViernesOld">
                                            <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                <label for="tipo_id">Viernes</label>
                                                <select id="IdViernes" class="form-control" required>                                                    
                                                    <option value="N">No</option>
                                                    <option value="S">Si</option>                                                                    
                                                </select>
                                            </div>
                                            <input type="hidden" id="IdSabadoOld" name="SabadoOld">
                                            <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                <label for="tipo_id">Sabado</label>
                                                <select id="IdSabado" class="form-control" required>                                                    
                                                    <option value="N">No</option>
                                                    <option value="S">Si</option>                                                                    
                                                </select>
                                            </div>
                                            <input type="hidden" id="IdDomingoOld" name="DomingoOld">
                                            <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                <label for="tipo_id">Domingo</label>
                                                <select id="IdDomingo" class="form-control" required>                                                    
                                                    <option value="N">No</option>
                                                    <option value="S">Si</option>                                                                    
                                                </select>
                                            </div>
                                            <input type="hidden" id="IdFestivoOld" name="FestivoOld">
                                            <div class="col-md-3 col-sm-12 col-xs-12 form-group" style="position: relative;">                                                
                                                <label for="tipo_id">Festivo</label>
                                                <select id="IdFestivo" class="form-control" required>                                                    
                                                    <option value="N">No</option>
                                                    <option value="S">Si</option>                                                                    
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Formulario Configuración -->      
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
                                                    <th>Codigo</th>
                                                    <th>Nombre</th>
                                                    <th>Hora Inicio</th>
                                                    <th>Hora Fin</th>
                                                    <th>No Consumos</th>
                                                    <th>TipoConsumo</th>
                                                    <th>L</th>
                                                    <th>M</th>
                                                    <th>Mx</th>
                                                    <th>J</th>
                                                    <th>V</th>
                                                    <th>S</th>
                                                    <th>D</th>
                                                    <th>F</th>         
                                                    <th>Opcion</th>
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
                format: 'hh:mm:ss A'
                
             });
        
             $('#myDatepicker3').datetimepicker({
                format: 'hh:mm:ss A'
             });
           
         </script>
    </body>
</html>
