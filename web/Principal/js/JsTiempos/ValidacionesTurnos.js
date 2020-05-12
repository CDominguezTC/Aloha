$(function ()
{
    var turnonocturnos = "N";
    var turnoextra = "N";
    var turnodescuento = "N";
    $(document).ready(function () {
        LoadTabla();
//        validacionBtn();
    });

    $('#IdTurnoNocturno').click(function () {
        if ($(this).prop('checked') === true)
        {
            turnonocturnos = "S";
        } else
        {
            turnonocturnos = "N";
        }
    });
    $('#IdTurnoExtra').click(function () {
        if ($(this).prop('checked') === true)
        {
            turnoextra = "S";
        } else
        {
            turnoextra = "N";
        }
    });
    $('#IdDescuentoBreack').click(function () {
        if ($(this).prop('checked') === true)
        {
            turnodescuento = "S";
        } else
        {
            turnodescuento = "N";
        }
    });

    $(document).on('click', '.SetFormulario', function () {
        $('#IdTurnoNocturno').prop("checked", false);
        $('#IdTurnoExtra').prop("checked", false);
        $('#IdDescuentoBreack').prop("checked", false);

        turnonocturnos = "N";
        turnoextra = "N";
        turnodescuento = "N";

        $('#Id').val($(this).data('id'));
        $('#IdCodigo').val($(this).data('codigo'));
        $('#IdNombre').val($(this).data('nombre'));
        $('#IdHoraInicio').val($(this).data('horainicio'));
        $('#IdHoraFin').val($(this).data('horafin'));
        $('#IdTeorico').val($(this).data('teorico'));

        if ($(this).data('turnonocturno') === "S")
        {
            $('#IdTurnoNocturno').prop("checked", true);
            turnonocturnos = "S";
        }
        if ($(this).data('turnoextra') === "S")
        {
            $('#IdTurnoExtra').prop("checked", true);
            turnoextra = "S";
        }
        if ($(this).data('descuentobreak') === "S")
        {
            $('#IdDescuentoBreack').prop("checked", true);
            turnodescuento = "S";
        }

        $('#IdHoraInicioBreack').val($(this).data('horainiciobreak'));
        $('#IdHoraFinBreack').val($(this).data('horafinbreak'));
        $('#IdTiempoBreack').val($(this).data('tiempobreak'));
        $('#IdTiempoGraciaAE').val($(this).data('tiempograciaae'));
        $('#IdTiempoGraciaAS').val($(this).data('tiempograciaas'));
        $('#IdTiempoGraciaDE').val($(this).data('tiempograciade'));
        $('#IdTiempoGraciaDS').val($(this).data('tiempograciads'));
        $('#IdAproximacionAE').val($(this).data('aproximacionae'));
        $('#IdAproximacionDS').val($(this).data('aproximacionds'));
        $('#IdHoraInicioDiurno').val($(this).data('horainiciodiurno'));
        $('#IdHoraInicioNocturno').val($(this).data('horainicionocturno'));
    });

    function validacionBtn() {

        //alert("validacionBtn");
        var usuariof = "";
        $.ajax({
            type: "POST",
            url: "LoginServlet",
            data: "nombreU",
            success: function (data, textStatus, jqXHR) {

                var dt = data;
                //alert("dt: " + dt);

                if (dt != "false") {

                    usuariof = dt;
                    var path = window.location.pathname;
                    var page = path.split("/").pop();
                    //alert("validob page: " + page);
                    editoBotonG(usuariof, page);
                    editoBotonE(usuariof, page);
                    editoBotonB(usuariof, page);
                    //return dt;
                    //alert("uf metodo: " + usuariof);
                } else {

                    //alert("Ocurrio un error al traer el nombre del usuario activo.");
                    Swal.fire({
                        icon: 'warning',
                        title: 'Alerta',
                        text: 'Ocurrio un error al traer el nombre del usuario activo.'
                    }).then((result) => {
                        if (result.value) {
                            location.href = "Dashboard.jsp";
                        }
                    });

                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                //disableGif();
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

    function editoBotonG(usuariof, page) {

        var Frm = "Permisos";
        var User = usuariof;
        //alert("User guardar: " + traigoUserAc());
        //var Accion = "Empresa.Guardar";
        var Accion = page.replace('.jsp', '') + ".Guardar";
        var data = {
            frm: Frm,
            user: User,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            data: data,
            success: function (data, textStatus, jqXHR) {

                var dt = data;
                //alert("dt: " + dt);

                if (dt != "true") {

                    $("#IdGuardar").attr("disabled", "disabled");
                    //evt.preventDefault();
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                //disableGif();
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
        /*var botonEnviar = document.getElementById("");
         botonEnviar.disabled === true;*/
    }

    function editoBotonE(usuariof, page) {

        var Frm = "Permisos";
        var User = usuariof;
        //var Accion = "Empresa.Editar";
        var Accion = page.replace('.jsp', '') + ".Editar";
        var data = {
            frm: Frm,
            user: User,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            data: data,
            success: function (data, textStatus, jqXHR) {

                var dt = data;
                //alert("dt ed: " + dt);

                if (dt != "true") {

                    //alert("Entro if editar");
                    //$("#IdModificar").attr("disabled", "disabled");
                    $(".SetFormulario").addClass("disabled").prop("disabled", true);
                    //evt.preventDefault();
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                //disableGif();
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
        /*var botonEnviar = document.getElementById("");
         botonEnviar.disabled === true;*/
    }

    function editoBotonB(usuariof, page) {

        var Frm = "Permisos";
        var User = usuariof;
        //var Accion = "Empresa.Borrar";
        var Accion = page.replace('.jsp', '') + ".Borrar";
        var data = {
            frm: Frm,
            user: User,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            data: data,
            success: function (data, textStatus, jqXHR) {

                var dt = data;
                //alert("dt ed: " + dt);

                if (dt != "true") {

                    //alert("Entro if editar");
                    //$("#IdModificar").attr("disabled", "disabled");
                    $(".SetEliminar").addClass("disabled").prop("disabled", true);
                    //evt.preventDefault();
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                //disableGif();
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
        /*var botonEnviar = document.getElementById("");
         botonEnviar.disabled === true;*/
    }

    function ValidaCampo()
    {
        var res = false;
        if ($('#IdCodigo').val() !== "")
        {
            if ($('#IdNombre').val() !== "")
            {
                res = true;
            }
        }
        return res;
    }

    function  LimpiarCampos()
    {
        $('#Id').val('');
        $('#IdCodigo').val('');
        $('#IdNombre').val('');
        $('#IdHoraInicio').val('');
        $('#IdHoraFin').val('');
        $('#IdTeorico').val('');
        $('#IdHoraInicioBreack').val('');
        $('#IdHoraFinBreack').val('');
        $('#IdTiempoBreack').val('');
        $('#IdTiempoGraciaAE').val('');
        $('#IdTiempoGraciaAS').val('');
        $('#IdTiempoGraciaDE').val('');
        $('#IdTiempoGraciaDS').val('');
        $('#IdAproximacionAE').val('');
        $('#IdAproximacionDS').val('');
        $('#IdHoraInicioDiurno').val('');
        $('#IdHoraInicioNocturno').val('');
        $('#IdTurnoNocturno').prop("checked", false);
        $('#IdTurnoExtra').prop("checked", false);
        $('#IdDescuentoBreack').prop("checked", false);
    }

    $('#IdAgregar').click(function (e)
    {
        LimpiarCampos();
    });

    $('#IdGuardar').click(function (e)
    {
        if (ValidaCampo() === true)
        {
            var Frm = "TurnosJSP";
            var Id = $('#Id').val();
            var Codigo = $('#IdCodigo').val();
            var Nombre = $('#IdNombre').val();
            var HoraInicio = $('#IdHoraInicio').val();
            var HoraFin = $('#IdHoraFin').val();
            var Teorico = $('#IdTeorico').val();
            var HoraInicioBreak = $('#IdHoraInicioBreack').val();
            var HoraFinBreak = $('#IdHoraFinBreack').val();
            var TiempoBreak = $('#IdTiempoBreack').val();
            var TiempoGraciaAE = $('#IdTiempoGraciaAE').val();
            var TiempoGraciaAS = $('#IdTiempoGraciaAS').val();
            var TiempoGraciaDE = $('#IdTiempoGraciaDE').val();
            var TiempoGraciaDS = $('#IdTiempoGraciaDS').val();
            var AproximacionAE = $('#IdAproximacionAE').val();
            var AproximacionDS = $('#IdAproximacionDS').val();
            var HoraInicioDiurno = $('#IdHoraInicioDiurno').val();
            var HoraInicioNocturno = $('#IdHoraInicioNocturno').val();
            var Accion = "Upload";
            var data = {
                frm: Frm,
                id: Id,
                codigo: Codigo,
                nombre: Nombre,
                horainicio: HoraInicio,
                horafin: HoraFin,
                teorico: Teorico,
                horainiciobreak: HoraInicioBreak,
                horafinbreak: HoraFinBreak,
                tiempobreak: TiempoBreak,
                tiempograciaae: TiempoGraciaAE,
                tiempograciaas: TiempoGraciaAS,
                tiempograciade: TiempoGraciaDE,
                tiempograciads: TiempoGraciaDS,
                aproximacionae: AproximacionAE,
                aproximacionds: AproximacionDS,
                horainiciodiurno: HoraInicioDiurno,
                horainicionocturno: HoraInicioNocturno,
                turnonocturnos: turnonocturnos,
                turnoextra: turnoextra,
                turnodescuento: turnodescuento,
                accion: Accion
            };
            enableGif();
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
                text: 'Verifica todos los campos.'
            });
            //alert("Favor de completar todos los campos");
        }
    });

    $(document).on('click', '.SetEliminar', function () {
//        if (ValidaCampo() === true)
//        {
        var Frm = "TurnosJSP";
        var Id = $(this).data('id');
        var Codigo = $(this).data('codigo');
        var Nombre = $(this).data('nombre');
        var Accion = "Delete";
        var data = {
            frm: Frm,
            id: Id,
            codigo: Codigo,
            nombre: Nombre,
            accion: Accion
        };
        Swal.fire({
            title: '¿Estás seguro?',
            text: "¡No podrás revertir esto!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, eliminar',
            cancelButtonText: 'No, cancelar'
        }).then((result) => {
            if (result.value) {
                enableGif();
                $.ajax({
                    type: "POST",
                    url: "ServletAlohaTiempos",
                    data: data,
                    success: function (resul, textStatus, jqXHR)
                    {
                        disableGif();
                        Swal.fire({
                            icon: 'success',
                            title: 'Eliminado',
                            text: 'Registro Eliminado Satisfactoriamente.'
                        });
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
        });
    });


//        else
//        {
//            alert("Favor de completar todos los campos");
//        }

    //});

    $("#IdEliminar").click(function (e) {
        if (ValidaCampo() === true)
        {
            var Frm = "ComercialJSP";
            var Id = $('#Id').val();
            var Codigo = $('#IdCodigo').val();
            var Nombre = $('#IdNombre').val();
            var Notas = $('#IdNotas').val();
            var Accion = "Delete";
            var data = {
                frm: Frm,
                id: Id,
                codigo: Codigo,
                nombre: Nombre,
                notas: Notas,
                accion: Accion
            };
            enableGif();
            $.ajax({
                type: "POST",
                url: "ServletAlohaTiempos",
                data: data,
                success: function (resul, textStatus, jqXHR)
                {
                    disableGif();
                    Swal.fire({
                        icon: 'success',
                        title: 'Eliminado',
                        text: 'Registro Eliminado Satisfactoriamente.'
                    });
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
                text: 'Verifica todos los campos.'
            });
            //alert("Favor de completar todos los campos");
        }
    });

    function LoadTabla()
    {
        var Frm = "TurnosJSP";
        var Accion = "Read";
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
                $('#datatable').html(resul);
                $('#datatable').dataTable({
                    responsive: false,
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
                    , "autoWidth": false
                    , "destroy": true
                    , "info": true
                    , "JQueryUI": true
                    , "ordering": true
                    , "paging": true
                    , "scrollY": "500px"
                    , "scrollX": "500px"
                    , "scrollCollapse": true

                });
                //$('#datatable').dataTable().fnDestroy();

//                    alert(resul);
//                    LimpiarCampos();
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
        validacionBtn();
    }
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
