<%-- 
    Document   : Dashboard
    Created on : 13/12/2019, 11:50:07 AM
    Author     : Frankie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>        

        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsTiempos/BigDataCasino.js" ></script> 
        <style>
            canvas {
                -moz-user-select: none;
                -webkit-user-select: none;
                -ms-user-select: none;
            }            
        </style>
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
        <!-- Contenidos-->
        <div class="right_col" role="main">
            
                
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="dashboard_graph">
                            <div class="row x_title">
                                <div class="col-md-6">
                                     <h3>Consumos Registrados <small>en las ultimas semanas</small></h3>
                                </div>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="chart-container">                                    
                                    <canvas id="IdBarras" width="380" height="100"></canvas>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                             <div class="ln_solid"></div>
                        </div>
                    </div>
                </div>
            
                <br/>

                <div class="row">
                    <div class="col-md-4 col-sm-4 col-xs-12 ">
                        <div class="x_panel tile fixed_height_320">
                            <div class="x_title">
                                <h2>Consumos Registrados<small> Hoy</small></h2>
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
                                <div class="chart-container" style="position: relative; height:10; width:20">                                    
                                    <canvas id="IdBarrasHorizotales"width="10" height="5"></canvas>
                                </div>
                                <div class="ln_solid"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-12">
                        <div class="x_panel tile fixed_height_320 overflow_hidden">
                            <div class="x_title">
                                <h2>Consumos Centro de Costos<small> Hoy</small></h2>
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
                                <div class="chart-container" style="position: relative; height:10; width:20">                                    
                                    <canvas id="IdDoughnut"width="10" height="5"></canvas>
                                     <div class="ln_solid"></div>
                                </div>                            
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-12 ">
                        <div class="x_panel tile fixed_height_320 overflow_hidden">
                            <div class="x_title">
                                <h2>Consumos Grupo Consumo<small> Hoy</small></h2>
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
                                <div class="x_content">
                                    <div class="chart-container" style="position: relative; height:10; width:20">                                    
                                        <canvas id="IdPie" width="10" height="5"></canvas>
                                        <div class="ln_solid"></div>
                                    </div>                            
                                </div>
                            </div>
                        </div>
                    </div>
                </div>  
                <!--div class="page-title">
                    <div  style="text-align: center">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <img src="Principal/images/Logo-CapaAgua.png" alt="AlohaCasino"  class="Img-CapaAgua" width="500" height="500">
                        <div class="example" id="Text-CapaAgua" style="font-family: bauhaus;">ALOHA TCSAS</div>              
                    </div>
                </div-->
            
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
