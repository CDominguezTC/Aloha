<%-- 
    Document   : Empleados
    Created on : 13/12/2019, 11:31:01 AM
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
                <h3>Empleados</h3>
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
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Filtros</h2>
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
                    <!-- Formulario Filtros -->
                    <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">

                      <!-- ChechBox -->
                      <div class="row">

                      <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                        <label for="filtro_dependencia">Filtro por Dependencia</label>
                          <select id="filtro_dependencia" class="form-control" required>
                            <option value="" disabled selected>Seleccione</option>
                            <option value="1">FILTRO</option>
                          </select>
                      </div>

                      <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                        <label for="filtro_costo">Filtro por Centro Costo</label>
                          <select id="filtro_costo" class="form-control" required>
                            <option value="" disabled selected>Seleccione</option>
                            <option value="1">FILTRO</option>
                          </select>
                      </div>
  
                      <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                        <label for="filtro_empresa">Filtro por Empresa</label>
                          <select id="filtro_empresa" class="form-control" required>
                            <option value="" disabled selected>Seleccione</option>
                            <option value="1">FILTRO</option>
                          </select>
                      </div>

                      </div> 
                      <!-- ChechBox -->     

                      <!-- Botones -->
                      <div class="ln_solid"></div>
                        <div class="row">
                          <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-5">
                                <button class="btn btn-primary btn-sm" type="submit" name="aplicar"><i class="fa fa-filter"></i> Aplicar</button>
                                <button class="btn btn-danger btn-sm" type="reset"><i class="fa fa-close"></i> Cancelar</button>
                            </div>
                          </div>
                        </div>  
                        <!-- /Botones -->
                    </form>
                    <!-- /Formulario Filtros -->
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
                              <th>Tipo de Identificación</th>
                              <th>Cedula</th>
                              <th>Nombre</th>
                              <th>Apellido</th>
                              <th>Código</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr>
                              <td>CEDULA DE CIUDADANIA</td>
                              <td>1144078608</td>
                              <td>JULIAN</td>
                              <td>ARISTIZABAL</td>
                              <td>1144078608</td>
                            </tr>
                          </tbody>
                        </table>
                        <!-- /Tabla -->
                      </div>
                    </div>                    
                  </div>
                </div>
                <!-- /Segunda Sección-->

                 <br/>

                <!-- Tercera Sección-->   
              <div class="" role="tabpanel" data-example-id="togglable-tabs">
                <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                  <li role="presentation" class="active"><a href="#tab_content1" id="empleados-tab" role="tab" data-toggle="tab" aria-expanded="true">Empleados</a></li>
                  <li role="presentation" class=""><a href="#tab_content2" role="tab" id="identificador-tab" data-toggle="tab" aria-expanded="false">Identificadores</a></li>
                </ul>

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

                                      <div class="row">
                                        <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                          <label for="tipo_id">Tipo de Identificación</label>
                                            <select id="tipo_id" class="form-control" required disabled>
                                              <option value="" disabled selected>Seleccione</option>
                                              <option value="1">CEDULA DE CIUDADANIA</option>
                                            </select>
                                        </div>

                                        <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                            <label for="cedula">Cedula</label>
                                            <input type="number" class="form-control" id="cedula" name="cedula" min="0" required>
                                        </div>

                                        <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                          <label for="nombre">Nombre</label>
                                          <input type="text" class="form-control" id="nombre" required="required">
                                        </div>

                                        <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                          <label for="apellido">Apellido</label>
                                          <input type="text" class="form-control" id="apellido" required="required">
                                        </div>

                                        <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                          <label for="codigo">Código</label>
                                          <input type="number" class="form-control" id="codigo" name="codigo" min="0" required>
                                        </div>

                                        <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                          <label for="empresa">Empresa</label>
                                            <select id="empresa" class="form-control" required disabled>
                                              <option value="" disabled selected>Seleccione</option>
                                              <option value="1">Tecno Control LTDA</option>
                                            </select>
                                        </div>

                                         <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                          <label for="dependencia">Dependencia</label>
                                            <select id="dependencia" class="form-control" required disabled>
                                              <option value="" disabled selected>Seleccione</option>
                                              <option value="1">Dependencia 1</option>
                                            </select>
                                        </div>

                                         <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                          <label for="centro_costo">Centro Costo</label>
                                            <select id="centro_costo" class="form-control" required disabled>
                                              <option value="" disabled selected>Seleccione</option>
                                              <option value="1">Centro de Costo 1</option>
                                            </select>
                                        </div>

                                         <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                          <label for="area">Area</label>
                                            <select id="area" class="form-control" required disabled>
                                              <option value="" disabled selected>Seleccione</option>
                                              <option value="1">Area1</option>
                                            </select>
                                        </div>

                                         <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                          <label for="grupo_horario">Grupo Horario</label>
                                            <select id="grupo_horario" class="form-control" required disabled>
                                              <option value="" disabled selected>Seleccione</option>
                                              <option value="1">Grupo Horario 1</option>
                                            </select>
                                        </div>

                                         <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                          <label for="Ciudad">Ciudad</label>
                                            <select id="Ciudad" class="form-control" required disabled>
                                              <option value="" disabled selected>Seleccione</option>
                                              <option value="1">Cali</option>
                                            </select>
                                        </div>

                                        <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                          <label for="observacion">Observación</label>
                                          <textarea id="observacion" required="required" name="observacion" class="form-control col-md-7 col-xs-12" style="height:90px;"></textarea>
                                        </div>

                                      </div>
                                    </form>
                                      <!-- /Configuracion Empleados-->

                                       <!-- Botones -->
                                        <div class="ln_solid"></div>
                                          <div class="row">
                                            <div class="form-group">
                                              <div class="col-md-8 col-sm-6 col-xs-12 col-md-offset-0">
                                                <button class="btn btn-primary btn-sm" type="submit" form="empleados_form"><i class="fa fa-plus"></i> </button>
                                                <button class="btn btn-warning btn-sm" type="button" form="empleados_form"><i class="fa fa-edit"></i> </button>
                                                <button class="btn btn-danger btn-sm" type="reset"   form="empleados_form"><i class="fa fa-close"></i> </button>
                                                <button type="submit" class="btn btn-success btn-sm" form="empleados_form" disabled><i class="fa fa-save"></i> </button>
                                                <button type="submit" class="btn btn-danger btn-sm"  form="empleados_form" disabled><i class="fa fa-trash"></i> </button>
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

                        <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="identificador-tab">
                            <!-- Identificadores -->
                            <div class="clearfix"></div>
                              <div class="row">
                                <!-- Tabla Identificadores -->
                                <div class="col-md-6 col-xs-12">
                                  <div class="x_panel" style="height: 350px;">
                                    <div class="x_title">
                                      <h2>Tabla</h2>
                                      <ul class="nav navbar-right panel_toolbox">
                                        <li><a></a></li>
                                        <li class="dropdown"><a><i>Ocultar</i></a>
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                                      </ul>
                                      <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                       <table id="datatable-fixed-header" class="table table-striped table-bordered">
                                        <thead>
                                          <tr>
                                            <th>Cedula Empleado</th>
                                            <th>Identificador</th>
                                          </tr>
                                        </thead>
                                        <tbody>
                                          <tr>
                                            <td>1144078608</td>
                                            <td>Identificador1</td>
                                          </tr>
                                          <tr>
                                            <td>1144078608</td>
                                            <td>Identificador2</td>
                                          </tr>
                                          <tr>
                                            <td>1144078608</td>
                                            <td>Identificador3</td>
                                          </tr>
                                        </tbody>
                                      </table>
                                    </div>
                                  </div>
                                </div>
                                <!-- /Tabla Identificadores-->

                              <!-- Configuracion Identificadores -->
                              <div class="col-md-6 col-xs-12">
                                <div class="x_panel" style="height: 350px;">
                                  <div class="x_title">
                                    <h2>Identificadores</h2>
                                     <ul class="nav navbar-right panel_toolbox">
                                        <li><a></a></li>
                                        <li class="dropdown"><a><i>Ocultar</i></a>
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                                      </ul>
                                    <div class="clearfix"></div>
                                  </div>
                                  <div class="x_content">
                                    
                                  <!-- Formulario -->
                                  <form id="id_form"> 
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                          <label for="cedula">Cedula</label>
                                          <input type="number" class="form-control" id="cedula" name="cedula" min="0" required>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                          <label for="codigo">Código</label>
                                          <input type="number" class="form-control" id="codigo" name="codigo" min="0" required>
                                        </div>
                                    </div>    
                                    <br/>

                                    <div class="row">
                                      <div class="form-group">
                                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-5">
                                          <button class="btn btn-primary btn-sm" type="submit" name="filtro_id"><i class="fa fa-filter"></i> Aplicar</button>
                                        </div>
                                      </div>
                                    </div>
                                  </form> 
                                  <!-- /Formulario -->  

                                    <!-- Botones -->
                                    <div class="ln_solid"></div>
                                      <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-0">
                                              <button class="btn btn-primary btn-sm" type="submit" form="id_form"><i class="fa fa-plus"></i></button>
                                              <button class="btn btn-warning btn-sm" type="button" form="id_form"><i class="fa fa-edit"></i></button>
                                              <button class="btn btn-danger btn-sm" type="reset"   form="id_form"><i class="fa fa-close"></i></button>
                                              <button type="submit" class="btn btn-success btn-sm" form="id_form" disabled><i class="fa fa-save"></i></button>
                                              <button type="submit" class="btn btn-danger btn-sm"  form="id_form" disabled><i class="fa fa-trash"></i></button>
                                            </div>
                                          </div>
                                        </div>  
                                    <!-- /Botones -->   
                                  </div>
                                </div>
                              </div>
                              <!-- /Configuracion Identificadores -->
                          </div>
                          <!-- Identificadores -->
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
