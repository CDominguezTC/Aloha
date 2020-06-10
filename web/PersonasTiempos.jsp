<%-- 
    Document   : RegistrarVisita
    Created on : 22/04/2020, 11:39:01 AM
    Author     : Frankie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Principal/Head.html" %> 
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

        </style>
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
        <!-- Contenido -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Registrar Visita</h3>
                    </div>
                </div>

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
                        <div class="x_content">

                            <form id="empleados_form"> 

                                <div class="row">

                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="tipo_id">Tipo Documento</label>
                                        <select id="tipo_id" class="form-control" required>
                                            <option value="" disabled selected>Seleccione</option>
                                            <option value="1">CEDULA DE CIUDADANIA</option>
                                        </select>
                                    </div>

                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="Documento">Documento</label>
                                        <input type="number" class="form-control" id="IdDocumento" name="Documento" min="0" required>
                                    </div>

                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="Nombre">Nombre</label>
                                        <input type="text" class="form-control" id="IdNombre" required="required">
                                    </div>

                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="tipo_persona">Tipo de Persona</label>
                                        <select id="tipo_persona" class="form-control" required disabled>
                                            <option value="" disabled selected>Seleccione</option>
                                            <option value="1"></option>
                                        </select>
                                    </div>

                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="tipo_visitante">Tipo de Visitante</label>
                                        <select id="tipo_visitante" class="form-control" required>
                                            <option value="" disabled selected>Seleccione</option>
                                            <option value="1">GENERAL</option>
                                        </select>
                                    </div>

                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="NroTarjeta">Nro Tarjeta</label>
                                        <input type="number" class="form-control" id="IdNroTarjeta" name="NroTarjeta" min="0" required>
                                    </div>

                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="tipo_trabajo">Tipo de Trabajo</label>
                                        <select id="tipo_trabajo" class="form-control" required>
                                            <option value="" disabled selected>Seleccione</option>
                                            <option value="1"></option>
                                        </select>
                                    </div>

                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="tipo_empresav">Empresa Visitante</label>
                                        <select id="tipo_empresav" class="form-control" required disabled>
                                            <option value="" disabled selected>Seleccione</option>
                                            <option value="1"></option>
                                        </select>
                                    </div>

                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="tipo_empresas">Empresa S-Social</label>
                                        <select id="tipo_empresas" class="form-control" required disabled>
                                            <option value="" disabled selected>Seleccione</option>
                                            <option value="1"></option>
                                        </select>
                                    </div>

                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                        <label for="obs_visitante">Observ. Visitante</label>
                                        <select id="obs_visitante" class="form-control" required>
                                            <option value="" disabled selected>Seleccione</option>
                                            <option value="1"></option>
                                        </select>
                                    </div>

                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                        <label for="obs_ingreso">Observ. S-Ingreso</label>
                                        <select id="obs_ingreso" class="form-control" required>
                                            <option value="" disabled selected>Seleccione</option>
                                            <option value="1"></option>
                                        </select>
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
                <!-- /Datos del Visitante -->   

                <!-- Capturar Foto_Huella Visitante -->   
                <div class="col-md-4 col-sm-4 col-xs-12">
                    <div class="x_panel" style="height: 415px;">
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
                                    <img class="img-responsive avatar-view center" src="Principal/images/user.png" alt="Foto-Huella" title="Captura Foto - Huella" height="250" width="250">
                                </div>
                            </div>
                            
                            <!-- Ignorar -->
                            <label for="Invisible" id="Invisible" style="visibility:hidden">-</label>
                            <!-- /Ignorar -->

                            <!-- Botones -->
                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                    <button class="btn btn-primary btn-sm" type="button" id="IdFoto" name="Foto"><i class="fa fa-camera"></i>  Foto</button>
                                    <button class="btn btn-warning btn-sm" type="button" id="IdHuella" name="Huella"><i class="fas fa-fingerprint"></i>  Huella</button> 
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

                            <form id="empleados_form"> 

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

                            <form id="empleados_form"> 
                                <div class="row">

                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                        <label for="Placa">Placa  Vehiculo</label>
                                        <input type="text" class="form-control" id="IdPlaca" name="Placa" required="required">
                                    </div>


                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                        <label for="Color">Color Vehiculo</label>
                                        <input type="text" class="form-control" id="IdColor" name="Color" required="required">
                                    </div>


                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                        <label for="Marca">Marca Vehiculo</label>
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
        </div>
        
        <!-- Footer -->
        <footer>
            <div class="clearfix"></div>
        </footer>
        <!-- Footer -->
        <%@include file="Principal/Script.html" %>  
    </body>
</html>
