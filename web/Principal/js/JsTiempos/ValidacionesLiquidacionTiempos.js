$(function () {
    $(document).ready(function () {
        LoadEmpresas();
        LoadDependencia();
        LoadCentroCosto();
        LoadArea();
        LoadGrupoHorario();
        LoadCiudad();
        LoadPeriodos();
    });

    $('#IdPeriodo').on('change', function () {        
        var selected = $(this).find('option:selected');                
        $('#IdFechaInicioPeriodo').val(selected.data('fechainicio'));
        $('#IdFechaFinPeriodo').val(selected.data('fechafin'));
    });

    $("#IdModalFiltroPersonas").modal();

    $('#tableempleados').DataTable({
        "scrollCollapse": false,
        "paging": false,
        "autoWidth": false,
        "destroy": false,
        "info": true,
        "JQueryUI": false,
        "ordering": true,
        "paging": false,
        "scrollX": false,
        "scrollY": 300,
        "searching": false
    });
    $('#tablemarcaciones').DataTable({
        "scrollCollapse": false,
        "paging": false,
        "autoWidth": false,
        "destroy": false,
        "info": true,
        "JQueryUI": false,
        "ordering": false,
        "paging": false,
        "scrollX": false,
        "scrollY": 416,
        "searching": false
    });
    $('#tableliquidacion').DataTable({
        "scrollCollapse": false,
        "paging": false,
        "autoWidth": false,
        "destroy": false,
        "info": true,
        "JQueryUI": false,
        "ordering": false,
        "paging": false,
        "scrollX": true,
        "scrollY": 416,
        "searching": false
    });

    function LoadEmpresas() {
        var Frm = "EmpresaJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdEmpresa').html(resul);
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

    function LoadDependencia() {
        var Frm = "DependenciasJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdDependencia').html(resul);
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

    function LoadCentroCosto() {
        var Frm = "CentroCostoJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdCentroCosto').html(resul);
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

    function LoadArea() {
        var Frm = "AreasJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdArea').html(resul);
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
    function LoadGrupoHorario() {
        var Frm = "GrupoHorarioJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdGrupoHorario').html(resul);
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

    function LoadCiudad() {
        var Frm = "CiudadJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdCiudad').html(resul);
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
    function LoadPeriodos() {
        var Frm = "PeriodosJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdPeriodo').html(resul);
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

    $('#IdFiltrarEmpleados').click(function (e)
    {
        var Frm = "PersonasJSP";
        var IdEmpresa = $('#IdEmpresa').val();
        var IdDependencia = $('#IdDependencia').val();
        var IdCentroCosto = $('#IdCentroCosto').val();
        var IdArea = $('#IdArea').val();
        var IdGrupoHorario = $('#IdGrupoHorario').val();
        var IdCiudad = $('#IdCiudad').val();
        var Evento = "SelectPersonasLiquidacionTiempos";
        var Modulo = "Tiempos";
        var Accion = "Read";
        var data = {
            frm: Frm,
            idempresa: IdEmpresa,
            idempresa: IdDependencia,
            idcentrocosto: IdCentroCosto,
            idarea: IdArea,
            idgrupohorario: IdGrupoHorario,
            idciudad: IdCiudad,
            evento: Evento,
            modulo: Modulo,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdTablaEmpleados').html(resul);
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
    });

});



