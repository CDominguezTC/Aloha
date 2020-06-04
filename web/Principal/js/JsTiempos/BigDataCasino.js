$(function () {
    $("#header").load("Principal/Head.html");
    $("#script").load("Principal/Script.html");

    var LabelBar;
    var DataBar;
    var ColorBar;
    var BordeBar;

    var LabelBarH;
    var DataBarH;
    var ColorBarH;
    var BordeBarH;

    var LabelDoughnut;
    var DataDoughnut;
    var ColorDoughnut;
    var BordeDoughnut;

    var LabelPie;
    var DataPie;
    var ColorPie;
    var BordePie;


    $(document).ready(function ()
    {
        //Obtenemos los datos a presentar   
        $(document).ready(function () {
            LoadDatosConsumosSemana();
            LoadDatosConsumos();
            LoadDatosCentoCosto()
            LoadDatosGrupoConsumo()
        });


        function LoadDatosConsumosSemana() {
            var Frm = "ConsumoJSP";
            var Accion = "GetDatoSemana";
            var data = {
                frm: Frm,
                accion: Accion
            };
            $.ajax({
                type: "POST",
                url: "ServletAlohaTiempos",
                data: data,
                success: function (resul, textStatus, jqXHR) {
                    if (Object.keys(resul).length !== 0)
                    {
                        for (var i = 0; i < resul.length; i++)
                        {
                            var Datos = resul[i];
                            LabelBar = Datos.Fechas;
                            LabelBar = LabelBar.toString().split(',');
                            DataBar = Datos.Valores;
                            DataBar = DataBar.toString().split(',');
                            ColorBar = Datos.Color;
                            ColorBar = ColorBar.toString().split('_');
                            BordeBar = Datos.Borde;
                            BordeBar = ColorBar;
                        }
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    if (jqXHR.status === 0) {
                        alert('Not connect: Verify Network.');
                    } else if (jqXHR.status === 404) {
                        alert('Requested page not found [404]');
                    } else if (jqXHR.status === 500) {
                        alert('Internal Server Error [500].');
                    } else if (textStatus === 'parsererror') {
                        alert('Requested JSON parse failed.');
                    } else if (textStatus === 'timeout') {
                        alert('Time out error.');
                    } else if (textStatus === 'abort') {
                        alert('Ajax request aborted.');
                    } else {
                        alert('Uncaught Error: ' + jqXHR.responseText);
                    }
                }
            });
        }

        function LoadDatosConsumos() {
            var Frm = "ConsumoJSP";
            var Accion = "GetDatoConsumo";
            var data = {
                frm: Frm,
                accion: Accion
            };
            $.ajax({
                type: "POST",
                url: "ServletAlohaTiempos",
                data: data,
                success: function (resul, textStatus, jqXHR) {
                    if (Object.keys(resul).length !== 0)
                    {
                        for (var i = 0; i < resul.length; i++)
                        {
                            var Datos = resul[i];
                            LabelBarH = Datos.Consumo;
                            LabelBarH = LabelBarH.toString().split(',');
                            DataBarH = Datos.Valores;
                            DataBarH = DataBarH.toString().split(',');
                            ColorBarH = Datos.Color;
                            ColorBarH = ColorBarH.toString().split('_');
                            BordeBarH = Datos.Borde;
                            BordeBarH = ColorBarH;
                        }
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    if (jqXHR.status === 0) {
                        alert('Not connect: Verify Network.');
                    } else if (jqXHR.status === 404) {
                        alert('Requested page not found [404]');
                    } else if (jqXHR.status === 500) {
                        alert('Internal Server Error [500].');
                    } else if (textStatus === 'parsererror') {
                        alert('Requested JSON parse failed.');
                    } else if (textStatus === 'timeout') {
                        alert('Time out error.');
                    } else if (textStatus === 'abort') {
                        alert('Ajax request aborted.');
                    } else {
                        alert('Uncaught Error: ' + jqXHR.responseText);
                    }
                }
            });
        }

        function LoadDatosCentoCosto() {
            var Frm = "ConsumoJSP";
            var Accion = "GetDatoCentroCosto";
            var data = {
                frm: Frm,
                accion: Accion
            };
            $.ajax({
                type: "POST",
                url: "ServletAlohaTiempos",
                data: data,
                success: function (resul, textStatus, jqXHR) {
                    if (Object.keys(resul).length !== 0)
                    {
                        for (var i = 0; i < resul.length; i++)
                        {
                            var Datos = resul[i];
                            LabelDoughnut = Datos.CentroCosto;
                            LabelDoughnut = LabelDoughnut.toString().split(',');
                            DataDoughnut = Datos.Valores;
                            DataDoughnut = DataDoughnut.toString().split(',');
                            ColorDoughnut = Datos.Color;
                            ColorDoughnut = ColorDoughnut.toString().split('_');
                            BordeDoughnut = Datos.Borde;
                            BordeDoughnut = ColorDoughnut;
                        }
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    if (jqXHR.status === 0) {
                        alert('Not connect: Verify Network.');
                    } else if (jqXHR.status === 404) {
                        alert('Requested page not found [404]');
                    } else if (jqXHR.status === 500) {
                        alert('Internal Server Error [500].');
                    } else if (textStatus === 'parsererror') {
                        alert('Requested JSON parse failed.');
                    } else if (textStatus === 'timeout') {
                        alert('Time out error.');
                    } else if (textStatus === 'abort') {
                        alert('Ajax request aborted.');
                    } else {
                        alert('Uncaught Error: ' + jqXHR.responseText);
                    }
                }
            });
        }

        function LoadDatosGrupoConsumo() {
            var Frm = "ConsumoJSP";
            var Accion = "GetDatoGrupoConsumo";
            var data = {
                frm: Frm,
                accion: Accion
            };
            $.ajax({
                type: "POST",
                url: "ServletAlohaTiempos",
                data: data,
                success: function (resul, textStatus, jqXHR) {
                    if (Object.keys(resul).length !== 0)
                    {
                        for (var i = 0; i < resul.length; i++)
                        {
                            var Datos = resul[i];
                            LabelPie = Datos.GrupoConsumo;
                            LabelPie = LabelPie.toString().split(',');
                            DataPie = Datos.Valores;
                            DataPie = DataPie.toString().split(',');
                            ColorPie = Datos.Color;
                            ColorPie = ColorPie.toString().split('_');
                            BordePie = Datos.Borde;
                            BordePie = ColorPie;
                        }
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    if (jqXHR.status === 0) {
                        alert('Not connect: Verify Network.');
                    } else if (jqXHR.status === 404) {
                        alert('Requested page not found [404]');
                    } else if (jqXHR.status === 500) {
                        alert('Internal Server Error [500].');
                    } else if (textStatus === 'parsererror') {
                        alert('Requested JSON parse failed.');
                    } else if (textStatus === 'timeout') {
                        alert('Time out error.');
                    } else if (textStatus === 'abort') {
                        alert('Ajax request aborted.');
                    } else {
                        alert('Uncaught Error: ' + jqXHR.responseText);
                    }
                }
            });
        }

        window.onload = function () {
            var ctx = document.getElementById('IdBarras').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: LabelBar,
                    datasets: [{
                            label: '# Consumos por Dia',
                            data: DataBar,
                            backgroundColor: ColorBar,
                            borderColor: BordeBar,
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        xAxes: [{
                                gridLines: {
                                    display: false
                                }
                            }],
                        yAxes: [{
                                gridLines: {
                                    display: false
                                }
                            }]
                    }
                }
            });
            //BarrasHorizotales
            var ctx = document.getElementById('IdBarrasHorizotales').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'horizontalBar',
                data: {
                    labels: LabelBarH,
                    datasets: [{
                            label: '# Consumos Hoy',
                            data: DataBarH,
                            backgroundColor: ColorBarH,
                            borderColor: BordeBarH,
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        xAxes: [{
                                gridLines: {
                                    display: false
                                }
                            }],
                        yAxes: [{
                                gridLines: {
                                    display: false
                                }
                            }]
                    }
                }
            });
            //doughnut
            var ctx = document.getElementById('IdDoughnut').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'doughnut',
                data: {
                    labels: LabelDoughnut,
                    datasets: [{
                            label: '# Consumos Centro Costo',
                            data: DataDoughnut,
                            backgroundColor: ColorDoughnut,
                            borderColor: BordeDoughnut,
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        xAxes: [{
                                gridLines: {
                                    display: false
                                }
                            }],
                        yAxes: [{
                                gridLines: {
                                    display: false
                                }
                            }]
                    }
                }
            });
            //Pie
            var ctx = document.getElementById('IdPie').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: LabelPie,
                    datasets: [{
                            label: '# Consumos Gruspo Consumo',
                            data: DataPie,
                            backgroundColor: ColorPie,
                            borderColor: BordePie,
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        xAxes: [{
                                gridLines: {
                                    display: false
                                }
                            }],
                        yAxes: [{
                                gridLines: {
                                    display: false
                                }
                            }]
                    }
                }
            });
        };
    });
});