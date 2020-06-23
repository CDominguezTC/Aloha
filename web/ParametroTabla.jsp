<%-- 
    Document   : ParametroTabla
    Created on : 11/06/2020, 08:05:59 AM
    Author     : Diego Fdo Guzman B
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html>
        <head>        
            <%@include file="Principal/Head.html" %>        
            <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
            <script type="text/javascript" src="Principal/js/JsVisitantes/ValidacionesParametroTabla.js" ></script>
        </head>
        <body class="nav-md">
            <%@include file="Principal/Body.html" %>
            <!-- Contenido -->
            <div class="right_col" role="main">
                <div class="">
                    <div class="page-title">
                        <div class="title_left">
                            <h3>Parametros Tabla</h3>
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
                                                <input type="hidden" id="id_tablaOld" name="id_tablaOld">
                                                <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                    <label for="id_tabla">Tabla</label>
                                                    <select id="id_tabla" class="form-control" required>
                                                        <option value="" disabled selected>Seleccione</option>
                                                    </select>
                                                </div>
                                                <input type="hidden" id="id_vencimientoOld" name="id_tablaOld">
                                                <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                    <label for="id_modulo">Modulo</label>
                                                    <select id="id_modulo" class="form-control" required>
                                                        <option value="" disabled selected>Seleccione</option>
                                                    </select>
                                                </div>    

                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                    <label for="" style="visibility:hidden">---</label>
                                                    <button class="btn btn-primary btn-md" type="button" id="Idbuscar" name="frmm" value="BuscarParametroTabla"><i class="fa fa-search"></i> Buscar</button>
                                                </div> 

                                                <!-- Formulario Configuración -->      

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
                                                        <th>Id</th>   
                                                        <th>Campo DB</th>                                                    
                                                        <th>Nombre Visible</th>
                                                        <th>Tipo</th>
                                                        <th>Referencia</th>
                                                        <th>Visualizar</th>
                                                        <th>Habilitar</th>   
                                                        <th>Obligatorio</th>
                                                        <th>Lista</th>                                                    
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
                                                        </td>                                                    
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <!-- /Tabla -->
                                            <br/><br/>
                                        </div>
                                        <div class="ln_solid"></div>
                                        <div class="form-group"> 
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-5">
                                                <button class="btn btn-success btn-sm" type="button" id="IdGuardar1" name="frm" value="Aplicar"><i class="fa fa-save"></i> Guardar</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /Segunda Sección-->
                            <br/><br/><br/><br/><br/>
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

            <script>



                $('#myDatepicker').datetimepicker({
                    format: 'YYYY-MM-DD',
                    locale: 'es'
                });

            </script>
        </body>
    </html>
