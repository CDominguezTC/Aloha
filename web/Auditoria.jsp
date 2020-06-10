<%-- 
    Document   : Auditoria
    Created on : 22/04/2020, 09:21:01 AM
    Author     : Frankie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>          
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsTiempos/ValidacionesAuditoria.js" ></script> 
        <style>
          #IdVer{
            display: block;
            width: 100%;
          }
        </style>
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
        <!-- Contenido -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Auditoria</h3>
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
                                <h2>Datos</h2>
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
                                <!-- Formulario Configuración -->
                                <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
                                    <div align="center" id="espera" style="display: none">
                                        <img src="Principal/images/loading_dash.gif">
                                    </div>
                                    <div id="Principal">
                                        <div class="row">
                                            <input type="hidden" id="Id" name="Id">

                                            <div class="col-md-8 col-sm-12 col-xs-12 form-group">
                                                <label for="Usuario">Usuario</label>
                                                <select id="IdUsuariosA" class="form-control" required>
                                                    <option value="" disabled selected>Seleccione</option>
                                                    <option value="1">JULIAN A. ARISTIZABAL</option>
                                                </select>
                                            </div>

                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Auditoria" style="visibility:hidden">Auditoria</label>
                                                <button class="btn btn-primary btn-md" type="button" id="IdVer" name="Ver">Ver</button>
                                            </div>  

                                            <br/>
                                            
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


                                        </div>
                                        <!-- Formulario Configuración -->      
                                        <!-- Botones -->
                                        <div class="ln_solid"></div>
                                        <!--div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-5">
                                                <button class="btn btn-primary btn-md" type="button" id="IdQuitoTodos" name=""><i class="fa fa-angle-double-right"></i> </button>
                                                <button class="btn btn-primary btn-md" type="button" id="IdQuitoUno" name=""><i class="fa fa-angle-right"></i> </button>
                                                <button class="btn btn-success btn-md" type="button" id="IdGuardar" name="Guardar"><i class="fa fa-save"></i> </button>
                                                <button class="btn btn-primary btn-md" type="button" id="IdPasoUno" name=""><i class="fa fa-angle-left"></i> </button>
                                                <button class="btn btn-primary btn-md" type="button" id="IdPasoTodos" name=""><i class="fa fa-angle-double-left"></i> </button>
                                                <br/><br/>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-8 col-sm-8 col-xs-12 col-md-offset-1">
                                                            <!--button class="btn btn-warning btn-sm" type="button"><i class="fa fa-edit"></i> Editar</button-->
                                                            <!--button class="btn btn-dark btn-sm" type="button" disabled><i class="fa fa-trash"></i> Eliminar</button>
                                                        </div>
                                                    </div>
                                                </div>         
                                            </div>
                                        </div-->
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
                                        <table id="datatableAu" class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <!--th>Id</th-->
                                                    <th>Operacion</th>
                                                    <th>Tabla</th>
                                                    <th>Fecha</th>
                                                    <th>Usuario</th>
                                                    <th>Registro Modificado</th>
                                                    <th>Observacion</th>
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
                                                    <td></td> 
                                                    <!--td class="text-center">
                                                        <button class="SetFormulario btn btn-warning btn-md" 
                                                                data-id=""
                                                                data-nombre=""
                                                                data-login="" 
                                                                data-password="" 
                                                                type="button" id="IdModificar" name="Modificar">Editar</button>
                                                        <button class="SetEliminar btn btn-dark btn-md" 
                                                                data-id=""
                                                                data-nombre=""
                                                                data-login="" 
                                                                data-password=""                                                                
                                                                type="button" id="IdEliminar" name="Eliminar"></button>
                                                    </td-->                                                    
                                                </tr>
                                            </tbody>
                                        </table>
                                        <!-- /Tabla -->
                                        <br/><br/>
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
        <script>
            
        $('#myDatepicker').datetimepicker();

        $('#myDatepicker2').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: 'es'
        });

        $('#myDatepicker3').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: 'es'
        });
         
        </script>        
    </body>
</html>

