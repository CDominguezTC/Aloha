$(function () {
    $("#header").load("Principal/Head.html");
    $("#script").load("Principal/Script.html");

    $('#myDatepicker').datetimepicker();

    $('#myDatepickerfechainical').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: 'es'

    });

    $('#myDatepickerfechafinal').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: 'es'

    });

    var tipopersona = "NULL";
    var cantidadconsumo = 0;
    var select = document.querySelector('#IdTipoPersona');
    select.addEventListener('change', function () {
        if ($('#IdTipoPersona').val())
        {
            tipopersona = $('#IdTipoPersona').val();
            cantidadconsumo = 0;
            $('#IdEmpleadoAutoriza').val('');
            $('#IdNombreEmpleadoAutoriza').val('');
            $('#IdCC').val('');
            $('#IdCentroCosto').val('');
            $('#IdEmpleadoAutorizado').val('');
            $('#IdNombreEmpleadoAutorizado').val('');
        }
    });

    $(document).ready(function () {
        LoadTablaPersonasAutoriza();
        LoadTablaPersonasAutorizada();
        LoadhorarioConsumo();
    });

    $(document).on('click', '.SetFormularioAutoriza', function () {
        if (tipopersona === 'NULL') {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Por favor selecione el tipo de persona'
            });
        } else
        {
            if (tipopersona === 'VISITANTE') {
                $('#IdEmpleadoAutoriza').val($(this).data('id'));
                $('#IdNombreEmpleadoAutoriza').val($(this).data('nombre') + " " + $(this).data('apellido'));
                $('#IdCC').val($(this).data('centrocosto'));
                $('#IdCentroCosto').val($(this).data('centrocostonombre'));
                cantidadconsumo = $(this).data('cantidadconsumo');                
            }
            if (tipopersona === 'EMPLEADO') {
                $('#IdEmpleadoAutoriza').val($(this).data('id'));
                $('#IdNombreEmpleadoAutoriza').val($(this).data('nombre') + " " + $(this).data('apellido'));
            }

        }
    });

    $(document).on('click', '.SetFormularioAutorizado', function () {
        if (tipopersona === 'NULL') {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Por favor selecione el tipo de persona'
            });
        } else
        {
            if (tipopersona === 'VISITANTE') {
                $('#IdEmpleadoAutorizado').val($(this).data('id'));
                $('#IdNombreEmpleadoAutorizado').val($(this).data('nombre') + " " + $(this).data('apellido'));
            }
            if (tipopersona === 'EMPLEADO') {
                $('#IdEmpleadoAutorizado').val($(this).data('id'));
                $('#IdNombreEmpleadoAutorizado').val($(this).data('nombre') + " " + $(this).data('apellido'));
                $('#IdCC').val($(this).data('centrocosto'));
                $('#IdCentroCosto').val($(this).data('centrocostonombre'));
            }

        }
    });

    function LoadTablaPersonasAutoriza()
    {
        var Frm = "AutorizacionConsumoJSP";
        var Accion = "ReadPersonaAutoriza";
        var data = {
            frm: Frm,
            accion: Accion
        };
        enableGif();
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                disableGif();
                $('#IdTablaPersonaAutoriza').html(resul);
                $('#IdTablaPersonaAutoriza').dataTable({
                    responsive: true,
                    language: {
                        "decimal": "",
                        "emptyTable": "No hay información",
                        "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
                        "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
                        "infoFiltered": "(Filtrado de _MAX_ total entradas)",
                        "infoPostFix": "",
                        "thousands": ",",
                        "lengthMenu": "Mostrar _MENU_ Entradas",
                        "loadingRecords": "Cargando...",
                        "processing": "Procesando...",
                        "search": "Buscar:",
                        "zeroRecords": "Sin resultados encontrados",
                        "paginate": {
                            "first": "Primero",
                            "last": "Ultimo",
                            "next": "Siguiente",
                            "previous": "Anterior"
                        }
                    }
                    , "autoWidth": true
                    , "destroy": true
                    , "info": true
                    , "JQueryUI": true
                    , "ordering": true
                    , "paging": true
                    , "scrollY": "500px"
                    , "scrollCollapse": true
                    , "pageLength": 5
                    , "iDisplayLength": 5
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                disableGif();
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
    function LoadhorarioConsumo()
    {
        var Frm = "HorarioConsumoJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        enableGif();
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                disableGif();
                $('#IdTipoConsumo').html(resul);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                disableGif();
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
    function LoadTablaPersonasAutoriza()
    {
        var Frm = "AutorizacionConsumoJSP";
        var Accion = "ReadPersonaAutoriza";
        var data = {
            frm: Frm,
            accion: Accion
        };
        enableGif();
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                disableGif();
                $('#IdTablaPersonaAutoriza').html(resul);
                $('#IdTablaPersonaAutoriza').dataTable({
                    responsive: true,
                    language: {
                        "decimal": "",
                        "emptyTable": "No hay información",
                        "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
                        "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
                        "infoFiltered": "(Filtrado de _MAX_ total entradas)",
                        "infoPostFix": "",
                        "thousands": ",",
                        "lengthMenu": "Mostrar _MENU_ Entradas",
                        "loadingRecords": "Cargando...",
                        "processing": "Procesando...",
                        "search": "Buscar:",
                        "zeroRecords": "Sin resultados encontrados",
                        "paginate": {
                            "first": "Primero",
                            "last": "Ultimo",
                            "next": "Siguiente",
                            "previous": "Anterior"
                        }
                    }
                    , "autoWidth": true
                    , "destroy": true
                    , "info": true
                    , "JQueryUI": true
                    , "ordering": true
                    , "paging": true
                    , "scrollY": "500px"
                    , "scrollCollapse": true
                    , "pageLength": 5
                    , "iDisplayLength": 5
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                disableGif();
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
    function LoadTablaPersonasAutorizada()
    {
        var Frm = "AutorizacionConsumoJSP";
        var Accion = "ReadPersonaAutorizada";
        var data = {
            frm: Frm,
            accion: Accion
        };
        enableGif();
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                disableGif();
                $('#IdTablaPersonaAutorizada').html(resul);
                $('#IdTablaPersonaAutorizada').dataTable({
                    responsive: true,
                    language: {
                        "decimal": "",
                        "emptyTable": "No hay información",
                        "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
                        "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
                        "infoFiltered": "(Filtrado de _MAX_ total entradas)",
                        "infoPostFix": "",
                        "thousands": ",",
                        "lengthMenu": "Mostrar _MENU_ Entradas",
                        "loadingRecords": "Cargando...",
                        "processing": "Procesando...",
                        "search": "Buscar:",
                        "zeroRecords": "Sin resultados encontrados",
                        "paginate": {
                            "first": "Primero",
                            "last": "Ultimo",
                            "next": "Siguiente",
                            "previous": "Anterior"
                        }
                    }
                    , "autoWidth": true
                    , "destroy": true
                    , "info": true
                    , "JQueryUI": true
                    , "ordering": true
                    , "paging": true
                    , "scrollY": "500px"
                    , "scrollCollapse": true
                    , "pageLength": 5
                    , "iDisplayLength": 5

                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                disableGif();
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

    function ValidaCampo()
    {
        var res = false;
        if ($('#IdTipoPersona').val() !== null)
        {
            if ($('#IdEmpleadoAutoriza').val() !== "")
            {
                if ($('#IdEmpleadoAutorizado').val() !== "")
                {
                    if ($('#IdCC').val() !== "")
                    {
                        if ($('#IdFechaInicial').val() !== "")
                        {
                            if ($('#IdFechaFinal').val() !== "")
                            {
                                if ($('#IdTipoConsumo').val() !== "")
                                {
                                    if ($('#IdMotivo').val() !== "")
                                    {
                                        if ($('#IdCantidad').val() !== "")
                                        {
                                            res = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    $('#IdGuardar').click(function (e)
    {
        if (ValidaCampo() === true)
        {
            var Frm = "AutorizacionConsumoJSP";
            var TipoPersona = $('#IdTipoPersona').val();
            var EmpleadoAutoriza = $('#IdEmpleadoAutoriza').val();
            var PersonaAutorizada = $('#IdEmpleadoAutorizado').val();
            var CentroCosto = $('#IdCC').val();
            var FechaInicial = $('#IdFechaInicial').val();
            var FechaFinal = $('#IdFechaFinal').val();
            var TipoConsumo = $('#IdTipoConsumo').val();
            var Motivo = $('#IdMotivo').val();
            var Cantidad = $('#IdCantidad').val();
            var Accion = "Upload";
            var data = {
                frm: Frm,
                tipopersona: TipoPersona,
                empleadoautorizado: EmpleadoAutoriza,
                personaautorizada: PersonaAutorizada,
                centrocosto: CentroCosto,
                fechainicial: FechaInicial,
                fechafinal: FechaFinal,
                tipoconsumo: TipoConsumo,
                motivo: Motivo,
                cantidad: Cantidad,
                accion: Accion
            };
            if (tipopersona === 'NULL') {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Por favor selecione el tipo de persona'
                });
            } else
            {
                if (tipopersona === 'VISITANTE') {
                    if (cantidadconsumo >= $('#IdCantidad').val()) {
                        $.ajax({
                            type: "POST",
                            url: "ServletAlohaTiempos",
                            data: data,
                            success: function (resul, textStatus, jqXHR)
                            {
                                Swal.fire({
                                    icon: 'success',
                                    title: 'Guardado',
                                    text: 'Registro Guardado Satisfactoriamente.'
                                });
                                disableGif();
                                //alert(resul);
                                LimpiarCampos();
                                LoadTabla();
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                disableGif();
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

                    } else
                    {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'La persona no tien la cantidad de consumos solicitados.'
                        });
                    }
                } else
                {
                    if (tipopersona === 'EMPLEADO') {
                        $.ajax({
                            type: "POST",
                            url: "ServletAlohaTiempos",
                            data: data,
                            success: function (resul, textStatus, jqXHR)
                            {
                                Swal.fire({
                                    icon: 'success',
                                    title: 'Guardado',
                                    text: 'Registro Guardado Satisfactoriamente.'
                                });
                                disableGif();
                                //alert(resul);
                                LimpiarCampos();
                                LoadTabla();
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                disableGif();
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
                }

            }


//            var Frm = "CargosJSP";
//            var Id = $('#Id').val();
//            var Codigo = $('#IdCodigo').val();
//            var Nombre = $('#IdNombre').val();
//            var Accion = "Upload";
//            var data = {
//                frm: Frm,
//                id: Id,
//                codigo: Codigo,
//                nombre: Nombre,
//                accion: Accion
//            };
//            enableGif();
//            $.ajax({
//                type: "POST",
//                url: "ServletAlohaTiempos",
//                data: data,
//                success: function (resul, textStatus, jqXHR)
//                {
//                    Swal.fire({
//                        icon: 'success',
//                        title: 'Guardado',
//                        text: 'Registro Guardado Satisfactoriamente.'
//                    });
//                    disableGif();
//                    //alert(resul);
//                    LimpiarCampos();
//                    LoadTabla();
//                },
//                error: function (jqXHR, textStatus, errorThrown) {
//                    disableGif();
//                    if (jqXHR.status === 0) {
//                        alert('Not connect: Verify Network.');
//                    } else if (jqXHR.status === 404) {
//                        alert('Requested page not found [404]');
//                    } else if (jqXHR.status === 500) {
//                        alert('Internal Server Error [500].');
//                    } else if (textStatus === 'parsererror') {
//                        alert('Requested JSON parse failed.');
//                    } else if (textStatus === 'timeout') {
//                        alert('Time out error.');
//                    } else if (textStatus === 'abort') {
//                        alert('Ajax request aborted.');
//                    } else {
//                        alert('Uncaught Error: ' + jqXHR.responseText);
//                    }
//                }
//            });
        } else
        {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Verifica todos los campos.'
            });
            //alert("Favor de completar todos los campos");
        }
    });
    function enableGif()
    {
        window.onload = document.getElementById("espera").style = "display: block";
        window.onload = document.getElementById("Principal").style = "display: none"
    }
    function disableGif()
    {
        window.onload = document.getElementById("espera").style = "display: none";
        window.onload = document.getElementById("Principal").style = "display: enable"
    }
});

