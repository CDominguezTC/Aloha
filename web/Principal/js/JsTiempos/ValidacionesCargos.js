$(function () {
    $("#header").load("Principal/Head.html");
    $("#script").load("Principal/Script.html");
});

$(function ()
{
    $(document).ready(function () {
        LoadTabla();
        validacionBtn();
    });

    $(document).on('click', '.SetFormulario', function () {
        $('#Id').val($(this).data('id'));
        $('#IdNombre').val($(this).data('tipocargo'));
        $('#IdCodigo').val($(this).data('valor'));
    });


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
    }

    $('#IdAgregar').click(function (e)
    {
        LimpiarCampos();
    });

    $('#IdGuardar').click(function (e)
    {
        if (ValidaCampo() === true)
        {
            var Frm = "CargosTiemposJSP";
            var Id = $('#Id').val();
            var Codigo = $('#IdCodigo').val();
            var Nombre = $('#IdNombre').val();
            var Accion = "Upload";
            var data = {
                frm: Frm,
                id: Id,
                codigo: Codigo,
                nombre: Nombre,
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
        var Frm = "CargosTiemposJSP";
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
        var Frm = "CargosTiemposJSP";
        var Accion = "ReadTiempos";
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
                    , "autoWidth": false
                    , "destroy": true
                    , "info": true
                    , "JQueryUI": true
                    , "ordering": true
                    , "paging": true
                    , "scrollY": "500px"
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
