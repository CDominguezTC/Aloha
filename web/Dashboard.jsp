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
                <div class="row">
                    <div class="col-md-12 col-sm-12 ">
                        <div class="x_panel">
                            <div class="dashboard_graph">
                                <div class="row x_title">
                                    <div class="col-md-6">
                                        <h3>Consumos Registrados en las ultima semana</h3>
                                    </div>                                    
                                </div>
                                <div class="x_content">   
                                    <div class="chart-container" style="position: relative; height:10; width:20">                                    
                                        <canvas id="IdBarras"width="10" height="5"></canvas>
                                    </div>
                                </div>                                
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                </div>                               
                <br />
                <div class="row">
                    <div class="col-md-4 col-sm-4 ">
                        <div class="x_panel tile fixed_height_320">
                            <div class="x_title">
                                <h2>Consumos hoy </h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <a class="dropdown-item" href="#">Settings 1</a>
                                            <a class="dropdown-item" href="#">Settings 2</a>
                                        </div>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <div class="chart-container" style="position: relative; height:10; width:20">                                    
                                    <canvas id="IdBarrasHorizotales"width="10" height="5"></canvas>
                                </div>                            
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4 ">
                        <div class="x_panel tile fixed_height_320 overflow_hidden">
                            <div class="x_title">
                                <h2>Consumos Centro de Costo hoy</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <a class="dropdown-item" href="#">Settings 1</a>
                                            <a class="dropdown-item" href="#">Settings 2</a>
                                        </div>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <div class="chart-container" style="position: relative; height:10; width:20">                                    
                                    <canvas id="IdDoughnut"width="10" height="5"></canvas>
                                </div>                            
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4 ">
                        <div class="x_panel tile fixed_height_320 overflow_hidden">
                            <div class="x_title">
                                <h2>Consumos Grupo Consumo hoy</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <a class="dropdown-item" href="#">Settings 1</a>
                                            <a class="dropdown-item" href="#">Settings 2</a>
                                        </div>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <div class="x_content">
                                    <div class="chart-container" style="position: relative; height:10; width:20">                                    
                                        <canvas id="IdPie" width="10" height="5"></canvas>
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
        </div>
        <!-- /Contenido -->
        <!-- Footer -->
        <footer>
            <div class="clearfix"></div>
        </footer>
        <!-- Footer --> 

        <script src="Principal/vendors/jquery/dist/jquery.min.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/bootstrap/dist/js/bootstrap.bundle.min.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/fastclick/lib/fastclick.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/nprogress/nprogress.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/Chart.js/dist/Chart.min.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/gauge.js/dist/gauge.min.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/iCheck/icheck.min.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/skycons/skycons.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/Flot/jquery.flot.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>
        <script src="Principal/vendors/Flot/jquery.flot.pie.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>
        <script src="Principal/vendors/Flot/jquery.flot.time.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>
        <script src="Principal/vendors/Flot/jquery.flot.stack.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>
        <script src="Principal/vendors/Flot/jquery.flot.resize.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/flot.orderbars/js/jquery.flot.orderBars.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>
        <script src="Principal/vendors/flot-spline/js/jquery.flot.spline.min.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>
        <script src="Principal/vendors/flot.curvedlines/curvedLines.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/DateJS/build/date.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/jqvmap/dist/jquery.vmap.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>
        <script src="Principal/vendors/jqvmap/dist/maps/jquery.vmap.world.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>
        <script src="Principal/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/vendors/moment/min/moment.min.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>
        <script src="Principal/vendors/bootstrap-daterangepicker/daterangepicker.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>

        <script src="Principal/build/js/custom.min.js" type="e5bd706aae507cbe905b6040-text/javascript"></script>
        <script src="Principal/cloudflare/rocket-loader.min.js" data-cf-settings="e5bd706aae507cbe905b6040-|49" defer=""></script> 
    </body>    
</html>
