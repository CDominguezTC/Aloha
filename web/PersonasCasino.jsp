<%-- 
    Document   : Empleados
    Created on : 13/12/2019, 11:31:01 AM
    Author     : Frankie
--%>

<%@page import="Controladores.ControladorGrupoConsumo"%>
<%@page import="Modelo.ModeloGrupoConsumo"%>
<%@page import="Controladores.ControladorCentroCosto"%>
<%@page import="Modelo.ModeloCentroCosto"%>
<%@page import="Controladores.ControladorEmpresas"%>
<%@page import="Modelo.ModeloEmpresa"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %> 
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsTiempos/ValidacionesPersonas.js" ></script>         
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>

        <!-- Contenidos -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Personas</h3>
                    </div>

                    <!-- <div class="title_right">
                       <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search"> 
                         <div class="input-group">
                           <input type="text" class="form-control" placeholder="Buscar...">
                           <span class="input-group-btn">
                             <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                           </span>
                         </div>
                       </div>
                     </div>-->

                </div>

                <!-- Primera Sección-->
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <!-- Segunda Sección-->
                        <div class="clearfix"></div>

                        <!-- /Segunda Sección-->

                        <br/>

                        <!-- Tercera Sección-->   
                        <div class="" role="tabpanel" data-example-id="togglable-tabs">

                            <div id="myTabContent" class="tab-content">
                                <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="empleados-tab">
                                    <div class="clearfix"></div>
                                    <div class="row">
                                        <!-- Empleados -->
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="x_panel">
                                                <div class="x_title">
                                                    <h2>Configuración</h2>
                                                    <ul class="nav navbar-right panel_toolbox">
                                                        <li><a></a></li>
                                                        <li class="dropdown">
                                                            <a><i>Ocultar</i></a>
                                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                                                    </ul>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <!-- Configuracion Empleados -->
                                                <div class="x_content">
                                                    <form id="empleados_form"> 
                                                        <div align="center" id="espera" style="display: none">
                                                            <img src="Principal/images/loading_dash.gif">                                                            
                                                        </div>
                                                        <div id="Principal">
                                                            <div class="row">
                                                                <input type="hidden" id="Id" name="Id">
                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="tipo_id">Tipo de Identificación</label>
                                                                    <select id="IdTipoDoc" class="form-control" required>
                                                                        <option value="0" selected disabled>Seleccione</option>
                                                                        <option value="1">Cedula de Ciudadania</option>
                                                                        <option value="2">Tarjeta de Identidad</option>
                                                                        <option value="3">Cedula de Extrangeria</option>                                                                    
                                                                    </select>
                                                                </div>

                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="cedula">Identificacion</label>
                                                                    <input type="number" class="form-control" id="IdCedula" name="cedula" min="0" required>
                                                                </div>

                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="nombre">Nombre</label>
                                                                    <input type="text" class="form-control" id="IdNombre" required="required">
                                                                </div>

                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="apellido">Apellido</label>
                                                                    <input type="text" class="form-control" id="IdApellido" required="required">
                                                                </div>
                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="empresa">Empresa</label>
                                                                    <select id="IdEmpresa" class="form-control" required>
                                                                        <option value="0" selected disabled>Seleccione</option>
                                                                        <%
                                                                            LinkedList<ModeloEmpresa> linkedListModeloEmpresas;
                                                                            ControladorEmpresas controladorEmpresas = new ControladorEmpresas();
                                                                            linkedListModeloEmpresas = controladorEmpresas.Read();
                                                                            for (ModeloEmpresa modeloEmpresa : linkedListModeloEmpresas)
                                                                            {
                                                                        %>  
                                                                        <option value=<%=modeloEmpresa.getId()%>><%=modeloEmpresa.getNombre()%></option>
                                                                        <%
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="centro_costo">Centro Costo</label>
                                                                    <select id="IdCentroCosto" class="form-control" required>
                                                                        <option value="0" selected disabled>Seleccione</option>
                                                                        <%
                                                                            LinkedList<ModeloCentroCosto> linkedListModeloCentroCosto;
                                                                            ControladorCentroCosto controladorCentroCosto = new ControladorCentroCosto();
                                                                            linkedListModeloCentroCosto = controladorCentroCosto.Read();
                                                                            for (ModeloCentroCosto modeloCentroCosto : linkedListModeloCentroCosto)
                                                                            {
                                                                        %>  
                                                                        <option value=<%=modeloCentroCosto.getId()%>><%=modeloCentroCosto.getDescripcion()%></option>
                                                                        <%
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>

                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="tipo_id">Consume Casino</label>
                                                                    <select id="IdConsume" class="form-control" required>
                                                                        <option value="0" selected disabled>Seleccione</option>                                                                    
                                                                        <option value="1">No</option>
                                                                        <option value="2">Si</option>                                                                    
                                                                    </select>
                                                                </div>
                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="tipo_id">Grupo Consumo</label>
                                                                    <select id="IdGrupoConsumo" class="form-control" required>
                                                                        <option value="0" selected disabled>Seleccione</option>
                                                                        <%
                                                                            LinkedList<ModeloGrupoConsumo> linkedListModeloGrupoConsumo;
                                                                            ControladorGrupoConsumo controladorGrupoConsumo = new ControladorGrupoConsumo();
                                                                            linkedListModeloGrupoConsumo = controladorGrupoConsumo.Read();
                                                                            for (ModeloGrupoConsumo modeloGrupoConsumo : linkedListModeloGrupoConsumo)
                                                                            {
                                                                        %>  
                                                                        <option value=<%=modeloGrupoConsumo.getId()%>><%=modeloGrupoConsumo.getDescripcion()%></option>
                                                                        <%
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>

                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="observacion">Observación</label>
                                                                    <textarea id="IdObservacion" required="required" name="observacion" class="form-control col-md-7 col-xs-12" style="height:90px;"></textarea>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                    <!-- /Configuracion Empleados-->

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
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Empleados -->
                                </div>


                            </div>

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
                                                        <th>Cedula</th>
                                                        <th>Nombre</th>
                                                        <th>Empresa</th>
                                                        <th>Centro Costo</th>                                                        
                                                        <th>Grupo Consumo</th>
                                                        <th>Consume Casino</th>
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
                                                        <td></td>                                                        
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <!-- /Tabla -->
                                        </div>
                                    </div>                    
                                </div>
                            </div>
                            <!-- Tercera Sección-->                  
                        </div>
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
