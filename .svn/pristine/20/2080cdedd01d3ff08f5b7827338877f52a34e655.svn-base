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
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
       
        <!-- Contenido -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Liquidación</h3>
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

                 <div class="clearfix"></div>
                  <div class="row">
                    <!-- Configuración Liquidacion -->
                    <div class="col-md-3 col-sm-12 col-xs-12">
                      <div class="x_panel" style="height: 660px;">
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

                           <!-- Formulario -->
                            <form id="id_form"> 

                              <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                  <label for="periodo">Periodo</label>
                                   <select id="periodo" class="form-control" required>
                                      <option value="" disabled selected>Seleccione</option>
                                      <option value="1">LIBRE</option>
                                   </select>
                                </div>
                              </div>

                              <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                  <label for="fecha_inicial">Fecha Inicial</label>
                                     <div class="form-group">
                                          <div class='input-group date' id='myDatepicker1'>
                                              <input type='text' class="form-control" required/>
                                              <span class="input-group-addon">
                                                 <span class="glyphicon glyphicon-calendar"></span>
                                              </span>
                                          </div>
                                      </div>
                                </div>
                              </div>

                              <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                  <label for="fecha_final">Fecha Final</label> 
                                     <div class="form-group">
                                          <div class='input-group date' id='myDatepicker2'>
                                              <input type='text' class="form-control" required/>
                                              <span class="input-group-addon">
                                                 <span class="glyphicon glyphicon-calendar"></span>
                                              </span>
                                          </div>
                                      </div>
                                </div>
                              </div>
                              <br/>
                              <!-- Tabla -->
                              <table id="datatable" class="table table-striped table-bordered">
                                  <thead>
                                    <tr>
                                      <th>Cedula</th>
                                      <th>Empleado</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    <tr>
                                      <td>1144078608</td>
                                      <td>JULIAN ARISTIZABAL</td>
                                    </tr>
                                    <tr>
                                      <td>1144078608</td>
                                      <td>JULIAN ARISTIZABAL</td>
                                    </tr>
                                    <tr>
                                      <td>1144078608</td>
                                      <td>JULIAN ARISTIZABAL</td>
                                    </tr>
                                    <tr>
                                      <td>1144078608</td>
                                      <td>JULIAN ARISTIZABAL</td>
                                    </tr>
                                  </tbody>
                              </table>
                                <!-- /Tabla -->
                            </form> 
                            <!-- /Formulario --> 
                          <br/>
                        </div>
                      </div>
                    </div>
                    <!-- /Configuración Liquidacion -->

                  <!-- Tabs -->  
                  <div class="col-md-9 col-sm-12 col-xs-12">
                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
                      <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab_content1" id="marcaciones-tab" role="tab" data-toggle="tab" aria-expanded="true">Marcaciones</a></li>
                        <li role="presentation" class=""><a href="#tab_content2" role="tab" id="liquidacion-tab" data-toggle="tab" aria-expanded="false">Liquidación</a></li>
                      </ul>
                      <div id="myTabContent" class="tab-content">
                        <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="marcaciones-tab">
                          <!-- Marcaciones -->
                          <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                              <div class="x_title">
                                <h2>Marcaciones</h2>
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
                                <!-- Formulario Marcaciones -->
                                <form id="marcaciones_form">

                                  <div class="row">

                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                      <label for="fecha_registro">Fecha Registro</label>
                                       <div class="form-group">
                                          <div class='input-group date' id='myDatepicker3'>
                                            <input type='text' class="form-control" required disabled/>
                                              <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                              </span>
                                          </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                      <label for="sentido">Sentido</label>
                                        <select id="sentido" class="form-control" required disabled>
                                            <option value="" disabled selected>Seleccione</option>
                                            <option value="1">Sentido1</option>
                                         </select>
                                    </div>
                                  </div>

                                  <div class="row">

                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                      <label for="hora_registro">Hora Registro</label>
                                        <div class="form-group">
                                            <div class='input-group date' id='myDatepicker4'>
                                                <input type='text' class="form-control" required/>
                                                  <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-time"></span>
                                                  </span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                      <label for="observacion">Observación</label>
                                      <textarea id="observacion" required="required" name="observacion" class="form-control col-md-7 col-xs-12" style="height:90px;"></textarea>
                                    </div>

                                  </div>  

                                </form>
                                <!-- /Formulario Marcaciones -->
                                <br/>
                                  <!-- Tabla -->
                                  <table id="datatable-fixed-header" class="table table-striped table-bordered">
                                    <thead>
                                      <tr>
                                        <th>Id</th>
                                        <th>Fecha Registro</th>
                                        <th>Hora Registro</th>
                                        <th>Sentido Registro</th>
                                        <th>Dispositivo</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                  </table>
                                  <!-- /Tabla -->

                                  <div class="row">
                                    <div class="col-md-9 col-sm-6 col-xs-12 form-group">
                                      <button class="btn btn-primary btn-sm" type="submit"><i class="fa fa-external-link"></i> Exportar</button>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-xs-12 form-group">
                                      <div class="checkbox">
                                        <label>
                                          <input type="checkbox" value=""> Ver marcaciones Invalidas
                                        </label>
                                      </div>
                                    </div>
                                  </div>

                                  <!-- Botones -->
                                  <div class="ln_solid"></div>
                                    <div class="row">
                                      <div class="form-group">
                                        <div class="col-md-8 col-sm-6 col-xs-12 col-md-offset-0">
                                          <button class="btn btn-primary btn-sm" type="submit" form="marcaciones_form"><i class="fa fa-plus"></i> </button>
                                          <button class="btn btn-warning btn-sm" type="button" form="marcaciones_form"><i class="fa fa-edit"></i> </button>
                                          <button class="btn btn-danger btn-sm" type="reset"   form="marcaciones_form"><i class="fa fa-close"></i> </button>
                                          <button class="btn btn-success btn-sm" type="submit" form="marcaciones_form" disabled><i class="fa fa-save"></i> </button>
                                          <button class="btn btn-danger btn-sm" type="submit"   form="marcaciones_form" disabled><i class="fa fa-trash"></i> </button>
                                        </div>
                                      </div>
                                    </div>  
                                    <!-- /Botones -->
                              </div>
                            </div>
                          </div>
                          <!-- /Marcaciones -->                      
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="liquidacion-tab">
                          <!-- Liquidación-->
                          <div class="clearfix"></div>
                          <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
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
                                <!-- Tabla -->
                                <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
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
                                      <tr>
                                        <td>CEDULA DE CIUDADANIA</td>
                                        <td>1144078608</td>
                                        <td>JULIAN</td>
                                        <td>ARISTIZABAL</td>
                                        <td>1144078608</td>
                                      </tr>
                                      <tr>
                                        <td>CEDULA DE CIUDADANIA</td>
                                        <td>1144078608</td>
                                        <td>JULIAN</td>
                                        <td>ARISTIZABAL</td>
                                        <td>1144078608</td>
                                      </tr>
                                      <tr>
                                        <td>CEDULA DE CIUDADANIA</td>
                                        <td>1144078608</td>
                                        <td>JULIAN</td>
                                        <td>ARISTIZABAL</td>
                                        <td>1144078608</td>
                                      </tr>
                                      <tr>
                                        <td>CEDULA DE CIUDADANIA</td>
                                        <td>1144078608</td>
                                        <td>JULIAN</td>
                                        <td>ARISTIZABAL</td>
                                        <td>1144078608</td>
                                      </tr>
                                    </tbody>
                                  </table>
                                  <br/>
                                  <!-- /Tabla -->
                                  <div class="row">
                                    <div class="col-md-9 col-sm-6 col-xs-12 form-group">
                                      <div class="checkbox">
                                        <label>
                                          <input type="checkbox" value=""> Liquida Todos
                                        </label>
                                      </div>
                                    </div>
                                    <div class="row">
                                    <div class="form-group">
                                      <div class="col-md-3 col-sm-6 col-xs-12">
                                          <button class="btn btn-primary btn-sm" type="submit" name="aplicar"><i class="fa fa-external-link"></i> Exportar</button> 
                                          <button class="btn btn-success btn-sm" type="submit"><i class="fa fa-calculator"></i> Calcular</button>
                                      </div>
                                    </div>
                                    </div>  
                                  </div>

                                </div>
                              </div>                    
                            </div>
                          </div>
                          <!-- /Liquidación-->
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- /Tabs -->  
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
                format: 'DD.MM.YYYY'
            });

            $('#myDatepicker2').datetimepicker({
                format: 'DD.MM.YYYY'
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
