
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="Principal/images/favicon.ico" type="image/ico" />
        <title>Gentelella Alela! | </title>

        <link href="Principal/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

        <link href="Principal/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

        <link href="Principal/vendors/nprogress/nprogress.css" rel="stylesheet">

        <link href="Principal/vendors/iCheck/skins/flat/green.css" rel="stylesheet">

        <link href="Principal/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">

        <link href="Principal/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet" />

        <link href="Principal/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

        <link href="Principal/build/css/custom.min.css" rel="stylesheet">

        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>


    <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
    <script type="text/javascript" src="Principal/js/JsTiempos/pruebas.js" ></script> 
    <style>
        canvas {
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
        }            
    </style>
</head>
<body class="nav-md">    
    <div class="container body">
        <div class="main_container">
            <div class="right_col" role="main">
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
            </div>


            <footer>
                <div class="pull-right">
                    Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
                </div>
                <div class="clearfix"></div>
            </footer>

        </div>
    </div>

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
    <script src="https://ajax.cloudflare.com/cdn-cgi/scripts/7089c43e/cloudflare-static/rocket-loader.min.js" data-cf-settings="e5bd706aae507cbe905b6040-|49" defer=""></script></body>
</html>
