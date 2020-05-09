$(document).ready(function ()
{
    $("#texto1").blur(function () {
        $(this).css("background-color", "#FFFFCC");
        var Frm = "prueba";
        var Cedula = $('#texto1').val();
        var Accion = "Get";
        var data = {
            frm: Frm,
            id: Cedula,
            accion: Accion
        };
        enableGif();
        $.ajax({
            type: "POST",
            url: "prueba",
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
//                    Swal.fire({
//                        icon: 'success',
//                        title: 'Guardado',
//                        text: 'Registro Guardado Satisfactoriamente.'
//                    });
//                    $('#Id').val($(this).data('id'));
//                    $('#IdTipoDoc').val($(this).data('tipodoc'));
//                    $('#IdCedula').val($(this).data('cedula'));
//                    $('#IdNombre').val($(this).data('nombre'));
//                    $('#IdApellido').val($(this).data('apellido'));
//                    $('#IdEmpresa').val($(this).data('empresa'));
//                    $('#IdCentroCosto').val($(this).data('centrocosto'));
//                    $('#IdConsume').val($(this).data('consume'));
//                    $('#IdGrupoConsumo').val($(this).data('grupoconsumo'));
//                    $('#IdObservacion').val($(this).data('observacion'));
                disableGif();
                //alert(resul);
                //LimpiarCampos();
                //LoadTabla();
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
    });

    $("#texto1").blur(function () {
        $(this).css("background-color", "#FFFFCC");
    });

    $("#texto2").blur(function () {
        $(this).hide("slow");
    });

    $('#IdGuardar').click(function (e)
    {
        var Frm = "prueba";
        var Id = $('#Id').val();       
        var Accion = "prueba";
        var data = {
            frm: Frm,
            id: Id,            
            accion: Accion
        };
        enableGif();
        $.ajax({
            type: "POST",
            url: "prueba",
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
    });
});


