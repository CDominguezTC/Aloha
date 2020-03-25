<%-- 
    Document   : Festivos
    Created on : 9/03/2020, 08:57:14 AM
    Author     : Frankie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>    
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <!--script type="text/javascript" src="Principal/js/JsTiempos/ValidacionesLiquidacionCasino.js" ></script-->         
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
        <!-- Contenido -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Festivos</h3>
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
                                <form class="form-horizontal form-label-left input_mask" action="ServletAlohaTiempos" method="POST" name="LiquidacionCasinoJSP" id="IdComprasJSP" >                                                            
                                    <div align="center" id="espera" style="display: none">
                                        <img src="Principal/images/loading_dash.gif">
                                    </div>
                                    <div id="Principal">
                                        <div class="row">
                                            <input type="hidden" id="Id" name="Id">
                                            <div class='col-md-6 col-sm-6 col-xs-12'>
                                                <label for="Fecha">Fecha Inicial</label>
                                                <div class="form-group">
                                                    <div class='input-group date' id='myDatepicker1'>
                                                        <input type="text" class="form-control" id="IdFechaIni" name="FechaIni"/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class='col-md-6 col-sm-6 col-xs-12'>
                                                <label for="Fecha">Fecha Final</label>
                                                <div class="form-group">
                                                    <div class='input-group date' id='myDatepicker2'>
                                                        <input type="text" class="form-control" id="IdFechaFin" name="FechaFin"/>
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
                                        <div class="col-lg-12" style="text-align: center">
                                            <button type="submit" class="btn btn-primary" id="IdGenerarPlano" name="frm" value="GenerarLiquidacionCasino" >Generar Liquidacion</button>                                                                                
                                        </div>
                                        <!-- /Botones -->
                                    </div>
                                </form>
                                <!-- /Formulario Registro -->
                            </div>
                        </div>
                        <!-- /Primera Sección-->
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

            $('#myDatepicker').datetimepicker();

            $('#myDatepicker2').datetimepicker({
                format: 'YYYY-MM-DD',
                minDate: new Date(),
                locale: 'es'

            });

        </script>
    </body>
</html>

