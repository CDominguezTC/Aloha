<%-- 
    Document   : RegistrarVisita
    Created on : 22/04/2020, 11:39:01 AM
    Author     : Frankie
--%>

<%@page import="Controladores.ControladorEmpresa"%>
<%@page import="Modelo.ModeloEmpresa"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Principal/Head.html" %> 


        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js"></script>
        <script type="text/javascript" src="Principal/js/JsVisitantes/ValidacionesVisita.js"></script>    
        
        <!-- script type="text/javascript" src="Principal/js/JsVisitantes/jquery.jqGrid-4.4.3/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="Principal/js/JsVisitantes/jquery.jqGrid-4.4.3/js/i18n/grid.locale-es.js"></script>
        <script type="text/javascript" src="Principal/js/JsVisitantes/jquery.jqGrid-4.4.3/js/jquery.jqGrid.min.js"></script>        
        <link rel="stylesheet" type="text/css" media="screen" href="Principal/js/JsVisitantes/jquery.jqGrid-4.4.3/css/ui.jqgrid.css" -->
        
    




        

        <!-- Kit FontAwesome para Botones (Mientras) -->
        <script src='https://kit.fontawesome.com/a076d05399.js'></script>
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

                max-height: 270px;
                max-width: 270px;
                width: 245px;
                height: 245px;

            }


        </style>

    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>

        <script type="text/javascript" >


        </script> 

        <!-- Contenido -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Registrar Visita</h3>
                    </div>
                </div>

                <div class="clearfix"></div>
                <div class="">

                    <!-- Tabs -->  
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="" role="tabpanel" data-example-id="togglable-tabs">
                            <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                <li role="presentation" class="active"><a href="#tab_content1" id="Visitante-tab" role="tab" data-toggle="tab" aria-expanded="true">Visitante</a>
                                </li>
                                <li role="presentation" class=""><a href="#tab_content2" role="tab" id="Equipos-tab" data-toggle="tab" aria-expanded="false">Equipos</a>
                                </li>
                                <li role="presentation" class=""><a href="#tab_content3" role="tab" id="Acceso-tab" data-toggle="tab" aria-expanded="false">Acceso</a>
                                </li>
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="Visitante-tab">

                                    <!-- Datos del Visitante -->   
                                    <div class="col-md-8 col-sm-8 col-xs-12">
                                        <div class="x_panel">
                                            <div class="x_title">
                                                <h2>Datos del Visitante</h2>
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

                                            <div align="center" id="espera" style="display: none">
                                                <img src="Principal/images/loading_dash.gif">                                                            
                                            </div>
                                            <div id="Principal">

                                                <div class="x_content">

                                                    <form id="empleados_form1"> 

                                                        <div class="row">

                                                            <!--div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                <label for="tipo_id">Tipo Documento</label>
                                                                <select id="tipo_id" class="form-control" required>
                                                                    <option value="" disabled selected>Seleccione</option>
                                                                    <option value="1">CEDULA DE CIUDADANIA</option>
                                                                </select>
                                                            </div -->

                                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group" style="display: none">
                                                                <input type="text" class="form-control" id="Id" name="Id" min="0">
                                                            </div>

                                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                <label for="Documento">Documento</label>
                                                                <input type="text" class="form-control" id="IdDocumento" name="Documento" min="0" required>
                                                            </div>

                                                            <div class="col-md-8 col-sm-12 col-xs-12 form-group">
                                                                <label for="Nombre">Nombre</label>
                                                                <input type="text" class="form-control" id="IdNombre" required="required">
                                                            </div>


                                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                <label for="id_empresa_visitante">Empresa Visitante</label>
                                                                <input type="text" id="id_empresa_visitante" class="form-control" required disabled>
                                                                <!--option value="0" selected>Seleccione</option-->                                                                                                
                                                            </div>

                                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                <label for="id_empresa_ssocial">Empresa S-Social</label>
                                                                <input type="text" id="id_empresa_ssocial" class="form-control" required disabled>                                                                    
                                                            </div>

                                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                <label for="tipo_persona">Tipo de Persona</label>
                                                                <input id="tipo_persona" class="form-control" required disabled>
                                                            </div>

                                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                <label for="tipo_visitante">Tipo de Visita</label>

                                                                <select id="tipo_visitante" class="form-control" required>
                                                                    <option value="" disabled selected>Seleccione</option>
                                                                </select>

                                                            </div>

                                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                <label for="NroTarjeta">Nro Tarjeta</label>
                                                                <input type="number" class="form-control" id="IdNroTarjeta" name="NroTarjeta" min="0" required>
                                                            </div>

                                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                <label for="Modal">Modal</label>
                                                                <!-- Se activa el Modal-->
                                                                <select class="form-control" id="myselect">
                                                                    <option>Seleccione</option>
                                                                    <option value="Nuevo">Nuevo</option>
                                                                </select>
                                                            </div>

                                                            <!-- div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                                <label for="tipo_trabajo">Tipo de Trabajo</label>
                                                                <select id="tipo_trabajo" class="form-control" required>
                                                                    <option value="" disabled selected>Seleccione</option>
                                                                    <option value="1"></option>
                                                                </select>
                                                            </div -->

                                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                                <label for="obs_visitante">Observ. Visitante</label>
                                                                <textarea readonly id="obs_visitante" class="form-control" required>
                                                                </textarea>
                                                            </div>

                                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                                <label for="obs_ingreso">Observ. Ingreso</label>
                                                                <textarea id="obs_ingreso" class="form-control" required>
                                                                </textarea>
                                                            </div>


                                                            <!------------------------------------------------------------------>
                                                            <div>
                                                                <table id="list2"></table>
                                                                <div id="pager2"></div>
                                                            </div>
                                                            <!------------------------------------------------------------------>

                                                            <div id="Modal_Automatico_Persona">

                                                            </div>




                                                        </div>
                                                    </form>

                                                    <!-- Botones -->
                                                    <div class="ln_solid"></div>
                                                    <div class="form-group">
                                                        <div class="col-md-12 col-sm-12 col-xs-12 col-md-offset-3">
                                                            <button class="btn btn-primary btn-sm" type="button" id="IdRegistro" name="Registro"><i class="fa fa-plus"></i> Nuevo Registro</button>
                                                            <button class="btn btn-success btn-sm" type="button" id="IdGuardar" name="Guardar"><i class="fa fa-save"></i> Grabar Visita</button> 
                                                            <button class="btn btn-warning btn-sm" type="button" id="IdImprimir" name="Imprimir"><i class="fa fa-print"></i> Imprimir</button> 
                                                        </div>
                                                    </div>
                                                    <!-- /Botones -->

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Datos del Visitante -->  

                                    <!-- Capturar Foto_Huella Visitante  style="height: 415px;"-->   
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
                                                        <br>
                                                        <img id="IdImagen" class="img-responsive avatar-view center" src="Principal/images/user.png" alt="Foto-Huella" title="Captura Foto - Huella">
                                                    </div>
                                                </div>

                                                <!-- Ignorar -->
                                                <label for="Invisible1" id="Invisible" style="visibility:hidden">-</label>
                                                <br>
                                                <!-- /Ignorar -->


                                                <!--Inincio de Modals-->
                                                <div class='input-group date' id='visiblemodalvencimientos' data-toggle="modal" data-target=".bs-vencimiento-modal-lg"></div>
                                                <div id="Vencimientos" class="modal fade bs-vencimiento-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
                                                    <div class="modal-dialog modal-lg">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h4 class="modal-title" id="myModalLabelVencimientos"><b>Vencimientos</b></h4>
                                                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <table id="IdTablaVencimientos" class="table table-responsive table-bordered">
                                                                </table>                                                        
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>                                                        
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <!-- Botones -->
                                                <div class="ln_solid"></div>
                                                <div class="form-group">
                                                    <div class="col-md-11 col-sm-11 col-xs-12 col-md-offset-2">
                                                        <button class="btn btn-primary btn-sm" type="button" onclick="openCamera();" id="IdFoto" name="Foto"><i class="fa fa-camera"></i>  Foto</button>
                                                        <button class="btn btn-success btn-sm" type="button" onclick="openCapturadorHuella();" id="IdHuella" name="Huella"><i class="fas fa-fingerprint"></i>  Huella</button> 
                                                        <button class="btn btn-warning btn-sm" type="button" onclick="openCapturadorFirma();"  id="IdFirma" name="Firma"><i class="fas fa-signature"></i>  Firma</button> 
                                                    </div>
                                                </div>

                                                <div style="display: none">
                                                    <!--div-->                                                    
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

                                    <!-- Empleado Visitado -->  
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <div class="x_panel">
                                            <div class="x_title">
                                                <h2>Empleado Visitado</h2>
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

                                                <form id="empleados_form2"> 

                                                    <div class="row">

                                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                            <label for="empleado_visitado">Empleado Visitado</label>
                                                            <select id="empleado_visitado" class="form-control" required>
                                                                <option value="" disabled selected>Seleccione</option>
                                                                <option value="1"></option>
                                                            </select>
                                                        </div>

                                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                            <label for="area_visitada">Area Visitada</label>
                                                            <select id="area_visitada" class="form-control" required>
                                                                <option value="" disabled selected>Seleccione</option>
                                                                <option value="1"></option>
                                                            </select>
                                                        </div>

                                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                            <label for="Extension">Extension Telefonica</label>
                                                            <input type="number" class="form-control" id="IdExtension" name="Extension" placeholder="Ej: 101" required="required">
                                                        </div>

                                                    </div>
                                                </form>

                                                <div class="ln_solid"></div>

                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Empleado Visitado -->  

                                    <!-- Vehiculo -->  
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <div class="x_panel">
                                            <div class="x_title">
                                                <h2>Vehiculo</h2>
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

                                                <form id="empleados_form3"> 
                                                    <div class="row">

                                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                            <label for="Placa">Placa  Vehiculo</label>
                                                            <input type="text" class="form-control" id="IdPlaca" name="Placa" required="required">
                                                        </div>


                                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                            <label for="nombre">Color Vehiculo</label>
                                                            <input type="text" class="form-control" id="IdColor" name="Color" required="required">
                                                        </div>


                                                        <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                            <label for="nombre">Marca Vehiculo</label>
                                                            <input type="text" class="form-control" id="IdMarca" name="Marca" required="required">
                                                        </div>

                                                    </div>
                                                </form>

                                                <div class="ln_solid"></div>

                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Vehiculo -->  
                                    <div class="clearfix"></div> 
                                </div>



                                <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="Equipos-tab">
                                </div>

                                <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="Acceso-tab">
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /Tabs -->  
                </div>

            </div>
            <div class="clearfix"></div>
        </div>
        <!-- /Contenido -->

        <!-- Footer -->
        <footer>
            <div class="pull-right">
            </div>
            <div class="clearfix"></div>
        </footer>
        <!-- /Footer -->


        <%@include file="Principal/Script.html" %>  
        <%@include file="Principal/js/JsVisitantes/fotohuellafrima.html" %> 

        <script>

            //    $('#myselect').change(function () {
            //        var opval = $(this).val();
            //        if (opval == "Nuevo") {
            //            $('#myModal').modal("show");
            //        }
            //    });


            $('#myDatepicker').datetimepicker({
                format: 'YYYY-MM-DD',
                locale: 'es'
            });

        </script>


    </body>
</html>
