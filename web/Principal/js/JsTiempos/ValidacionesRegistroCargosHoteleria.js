$(function () {
    $("#header").load("Principal/Head.html");
    $("#script").load("Principal/Script.html");
    var IdConsumo = new Array();

    function LoadTabla() {
        var Frm = "ConsumoHoteleriaJSP";
        var Accion = "Read";
        var data = {
            frm: Frm,
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
                $('#datatable').html(resul);
                $('#datatable').dataTable({
                    esponsive: true,
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
                    , "paging": false
                    , "scrollY": "500px"
                    , "scrollCollapse": true
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

    $('#Idbuscar').click(function (e)
    {
        var Frm = "PersonasJSP";
        var Cedula = $('#IdCedula').val();
        var Accion = "Search";
        var Modulo = "Casino";
        if (Cedula === "")
        {
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Por favor ingrese el numero de cedula corecto.'
            }).then((result) => {
            });
        } else
        {
            var data = {
                frm: Frm,
                cedula: Cedula,
                modulo: Modulo,
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
                    if (resul.id !== 0) {
                        $('#Id').val(resul.id);
                        $('#IdHNombre').val(resul.nombres);
                        $('#IdHApellido').val(resul.apellidos);
                        LoadTabla();
                    } else
                    {
                        $('#Id').val('');
                        $('#IdHNombre').val('');
                        $('#IdHApellido').val('');
                        Swal.fire({
                            icon: 'warning',
                            title: 'Alerta',
                            text: 'La personas con la identificacion. ' + Cedula + ' no existe en el sistema'
                        }).then((result) => {
                        });
                    }
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

    function ValidaCampo()
    {
        var res = false;
        if ($('#Id').val() !== "")
        {
            if ($('#IdFecha').val() !== "")
            {
                if (Object.keys(IdConsumo).length !== 0)
                {
                    res = true;
                }
            }
        }
        return res;
    }

    $('#IdGuardar').click(function (e) {
        $('input[type=checkbox]:checked').each(function () {
            IdConsumo.push($(this).val());
        });
        if (ValidaCampo() === true) {
            enableGif();
            if (IdConsumo.length > 0) {
                var Frm = "ConsumoHoteleriaJSP";
                var Id = $('#Id').val();
                var IdFecha = $('#IdFecha').val();
                var Accion = "Upload";
                var data = {
                    frm: Frm,
                    id: Id,
                    idfecha: IdFecha,
                    idconsumo: IdConsumo,
                    accion: Accion
                };
                $.ajax({
                    type: "POST",
                    url: "ServletAlohaTiempos",
                    data: data,
                    success: function (resul, textStatus, jqXHR)
                    {
                        Swal.fire({
                            icon: 'success',
                            title: 'Guardado',
                            text: resul
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
        } else
        {
            alert("Favor de completar todos los campos");
        }
    });

    function  LimpiarCampos()
    {
        $('#Id').val('');
        $('#IdCedula').val('');
        $('#IdHNombre').val('');
        $('#IdHApellido').val('');
        $('#IdFecha').val('');  
        IdConsumo = [];
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