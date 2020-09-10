<%-- 
    Document   : Empleados
    Created on : 13/12/2019, 11:31:01 AM
    Author     : Frankie
--%>

<%@page import="Controladores.ControladorCentro_costo"%>
<%@page import="Modelo.ModeloPersona"%>
<%@page import="Controladores.ControladorGrupo_consumo"%>
<%@page import="Modelo.ModeloGrupo_consumo"%>
<%@page import="Controladores.ControladorCentro_costo"%>
<%@page import="Modelo.ModeloCentro_costo"%>
<%@page import="Controladores.ControladorEmpresa"%>
<%@page import="Modelo.ModeloEmpresa"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %> 
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsTiempos/ValidacionesPersonasCasino.js" ></script>       
        <!-- Kit FontAwesome para Botones (Mientras)
        <script type="text/javascript" src="Principal/js/fontawesome/newjavascript.js"></script> -->
        <style>

            .center {
                display: block;
                margin-left: auto;
                margin-right: auto;
                border-radius: 50%;
                pointer-events:none;
            }

            #Invisible {
                cursor: default;
                -webkit-touch-callout: none;
                -webkit-user-select: none;
                -khtml-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }
            
            #IdImagen{
                
                max-height: 300px;
                max-width: 300px;
                width: 270px;
                height: 270px;
                
            }
            
            #IdFoto{
                display: block;
                width: 180px;
            }

        </style>


    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>

        <!-- Contenidos -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Personas Casino</h3>
                    </div>
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
                                        <div class="col-md-8 col-sm-12 col-xs-12">
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
                                                    <form> 
                                                        <div align="center" id="espera" style="display: none">
                                                            <img src="Principal/images/loading_dash.gif">                                                            
                                                        </div>
                                                        <div id="Principal">
                                                            <div class="row">                                                                  
                                                                <input type="hidden" id="Id" name="Id">
                                                                <input type="hidden" id="IdTipoDocOld" name="TipoDocOld">
                                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                                    <label for="tipo_id">Tipo de Identificación</label>
                                                                    <select id="IdTipoDoc" class="form-control" required>
                                                                        <option value="0" selected disabled>Seleccione</option>
                                                                        <option value="1">Cedula de Ciudadania</option>
                                                                        <option value="2">Tarjeta de Identidad</option>
                                                                        <option value="3">Cedula de Extranjeria</option>                                                                    
                                                                    </select>
                                                                </div>
                                                                <input type="hidden" id="IdCedulaOld" name="cedulaOld">
                                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                                    <label for="cedula">Identificacion</label>
                                                                    <input type="number" class="form-control" id="IdCedula" name="cedula" min="0" required>
                                                                </div>
                                                                <input type="hidden" id="IdNombreOld" name="NombreOld">
                                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                                    <label for="nombre">Nombre</label>
                                                                    <input type="text" class="form-control" id="IdNombre" value="">
                                                                </div>
                                                                <input type="hidden" id="IdApellidoOld" name="ApellidoOld">
                                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                                    <label for="apellido">Apellido</label>
                                                                    <input type="text" class="form-control" id="IdApellido">
                                                                </div>
                                                                <input type="hidden" id="IdEmpresaOld" name="EmpresaOld">
                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="empresa">Empresa</label>
                                                                    <select id="IdEmpresa" class="form-control" >
                                                                        <option value="0" selected>Seleccione</option>                                                                       
                                                                    </select>
                                                                </div>
                                                                <input type="hidden" id="IdCentroCostoOld" name="CentroCostoOld">
                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="centro_costo">Centro Costo</label>
                                                                    <select id="IdCentroCosto" class="form-control" >
                                                                        <option value="0" selected>Seleccione</option>                                                                        
                                                                    </select>
                                                                </div>
                                                                <input type="hidden" id="IdCargoOld" name="GrupoConsumoOld">
                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="tipo_id">Cargo Persona</label>
                                                                    <select id="IdCargo" class="form-control" >
                                                                        <option value="0" selected>Seleccione</option>                                                                       
                                                                    </select>
                                                                </div>
                                                                <input type="hidden" id="IdConsumeOld" name="ConsumeOld">
                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="tipo_id">Consume Casino</label>
                                                                    <select id="IdConsume" class="form-control" required>
                                                                        <option value="0" selected disabled>Seleccione</option>                                                                    
                                                                        <option value="1">No</option>
                                                                        <option value="2">Si</option>                                                                    
                                                                    </select>
                                                                </div>
                                                                <input type="hidden" id="IdGrupoConsumoOld" name="GrupoConsumoOld">
                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="tipo_id">Grupo Consumo</label>
                                                                    <select id="IdGrupoConsumo" class="form-control" >
                                                                        <option value="0" selected>Seleccione</option>                                                                       
                                                                    </select>
                                                                </div>   
                                                                <input type="hidden" id="IdNoConsumosOld" name="NombreOld">
                                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                    <label for="nombre">Cantidad de Consumos Autorizados</label>
                                                                    <input type="text" class="form-control" id="IdNoConsumos" value="0">
                                                                </div>
                                                                <input type="hidden" id="IdObservacionOld" name="ObservacionOld">
                                                                <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                                    <label for="observacion">Observación</label>
                                                                    <textarea id="IdObservacion" name="observacion" class="form-control col-md-7 col-xs-12" style="height:90px;"></textarea>
                                                                </div>
                                                            </div>
                                                            <div class="modal fade" id="ModalGt4001" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog" role="document">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="exampleModalLabel">Cargos Adicionales</h5>
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                <span aria-hidden="true">&times;</span>
                                                                            </button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <form>
                                                                                <div class="form-group">
                                                                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                                                                        <!--label for="recipient-name" class="col-form-label">Id:</label-->
                                                                                        <input type="hidden" class="form-control" id="IdHoteleria" name="Hoteleria">
                                                                                        <input type="hidden" class="form-control" id="IdAdicional" name="Adicional">
                                                                                    </div>     
                                                                                    <!--div class='col-md-12 col-sm-12 col-xs-12'>
                                                                                        <label for="Fecha">Fecha Inicio Cargos</label>
                                                                                        <div class="form-group">
                                                                                            <div class='input-group date' id='myDatepicker2'>
                                                                                                <input type="text" class="form-control" id="IdFechaInicioCargos" name="FechaInicioCargos"/>
                                                                                                <span class="input-group-addon">
                                                                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                                                                </span>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                                                        <label for="tipo_id">Hoteleria</label>
                                                                                        <select id="IdConsumeHoteleria" class="form-control" required>                                                                                            
                                                                                            <option value="1HN" selected>No</option>
                                                                                            <option value="2HS">Si</option>                                                                    
                                                                                        </select>
                                                                                    </div>
                                                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                                                        <label for="tipo_id">Valor Hoteria</label>
                                                                                        <input type="text" id="IdValorHoteleria" class="form-control" value="25000">
                                                                                    </div>
                                                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                                                        <label for="tipo_id">Adicional</label>
                                                                                        <select id="IdConsumeAdicional" class="form-control" required>                                                                                            
                                                                                            <option value="1AN" selected>No</option>
                                                                                            <option value="2AS">Si</option>                                                                    
                                                                                        </select>
                                                                                    </div>
                                                                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                                                        <label for="tipo_id">Valor Adicional</label>
                                                                                        <input type="text" id="IdValorAdicional" class="form-control" value="25000">
                                                                                    </div-->
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-primary" id="IdIniServicio" name="IniciarServicio">Inciar Cargos</button>
                                                                            <button type="button" class="btn btn-warning" id="IdFinServicio" name="TerminarServicio">Terminar Cargos</button>
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Salir</button>                                                     
                                                                        </div>                                            
                                                                    </div>
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
                                                            <button class="btn btn-danger btn-sm" type="reset" id="IdCancelar"><i class="fa fa-close"></i> Cancelar</button>
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
                                        <!-- Capturar Foto_Huella Visitante -->   
                                        <div class="col-md-4 col-sm-4 col-xs-12">
                                            <div class="x_panel">
                                                <div class="x_title">
                                                    <h2>Capturar</h2>
                                                    <ul class="nav navbar-right panel_toolbox">
                                                        <li><a></a>
                                                        </li>
                                                        <li class="dropdown">
                                                            <a></i></a>
                                                        </li>
                                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                                        </li>
                                                    </ul>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="x_content">

                                                    <div class="profile_img">
                                                        <div id="crop-avatar">
                                                            <!-- Img Captura Foto-Huella -->
                                                            <br><br>
                                                            <img id="IdImagen"class="img-responsive avatar-view center" src="Principal/images/user.png" alt="Foto-Huella" title="Captura Foto - Huella">
                                                        </div>
                                                    </div>

                                                    <!-- Ignorar -->
                                                    <label for="Invisible1" id="Invisible" style="visibility:hidden">-</label>
                                                    <br>
                                                    <label for="Invisible1" id="Invisible" style="visibility:hidden">-</label>
                                                    <br>
                                                    <!-- /Ignorar -->

                                                    <!-- Botones -->
                                                    <div class="ln_solid"></div>
                                                    <div class="form-group">
                                                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">                                                            
                                                            <button class="btn btn-primary btn-sm" type="button" onclick="openCamera();" id="IdFoto" name="Foto"><i class="fa fa-camera"></i>  Foto</button>
                                                            <!--button class="btn btn-success btn-sm" type="button" onclick="openCapturadorHuella();" id="IdHuella" name="Huella"><i class="fas fa-fingerprint"></i>  Huella</button--> 
                                                            <!--button class="btn btn-warning btn-sm" type="button" onclick="openCapturadorFirma();"  id="IdFirma" name="Firma"><i class="fas fa-signature"></i>  Firma</button-->                                                             
                                                        </div>
                                                    </div>
                                                    <!--
                                                    DIv Ocualto para los datos que son de tipo multiemdia
                                                    Foto
                                                    Huella
                                                    Firma 
                                                    CADD 19-05-2020
                                                    -->                                                    
                                                    <!--div  -->
                                                    <div style="display: none">                                                    
                                                        <div>
                                                            <input id="IdHuella_0" value="" name="Huella_0">
                                                            <input id="IdHuella_1" value="" name="Huella_1">
                                                            <input id="IdHuella_2" value="" name="Huella_2">
                                                            <input id="IdHuella_3" value="" name="Huella_3">
                                                            <input id="IdHuella_4" value="" name="Huella_4">
                                                            <input id="IdHuella_5" value="" name="Huella_5">
                                                            <input id="IdHuella_6" value="" name="Huella_6">
                                                            <input id="IdHuella_7" value="" name="Huella_7">
                                                            <input id="IdHuella_8" value="" name="Huella_8">
                                                            <input id="IdHuella_9" value="" name="Huella_9">
                                                        </div>

                                                        <div>
                                                            <input id="IdTemplate" value="" name="Template">                                                        
                                                            <input id="IdTemplate_10" value="" name="Template_10">                                                        
                                                        </div>
                                                        <div>
                                                            <input id="IdSRCImagen" value="" name="SRCImagen">                                                        
                                                            <input id="IdFirmaBase64" value="" name="FirmaBase64">   
                                                        </div>
                                                    </div>

                                                    <!-- /Botones -->

                                                </div>
                                            </div>
                                        </div>
                                        <!-- /Capturar Foto_Huella Visitante -->  
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
                                                        <th>CentroCosto</th>
                                                        <th>GrupoConsumo</th>
                                                        <th>Casino</th>
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
        <%@include file="Principal/js/JsVisitantes/fotohuellafrima.html" %>  
        <script>

            $('#myDatepicker').datetimepicker();

            $('#myDatepicker2').datetimepicker({
                format: 'YYYY-MM-DD',
                minDate: new Date(),
                locale: 'es'

            });

        </script>
    </body>
</html>
