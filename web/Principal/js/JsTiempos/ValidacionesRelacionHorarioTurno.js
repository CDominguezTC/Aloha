$(function ()
{
    var Lunes = "N";
    var Martes = "N";
    var Miercoles = "N";
    var Jueves = "N";
    var Viernes = "N";
    var Sabado = "N";
    var Domingo = "N";
    var Festivo = "N";

    $('#IdLunes').click(function () {
        if ($(this).prop('checked') === true)
        {
            Lunes = "Lunes";
        } else
        {
            Lunes = "N";
        }
    });
    $('#IdMartes').click(function () {
        if ($(this).prop('checked') === true)
        {
            Martes = "Martes";
        } else
        {
            Martes = "N";
        }
    });
    $('#IdMiercoles').click(function () {
        if ($(this).prop('checked') === true)
        {
            Miercoles = "Miercoles";
        } else
        {
            Miercoles = "N";
        }
    });
    $('#IdJueves').click(function () {
        if ($(this).prop('checked') === true)
        {
            Jueves = "Jueves";
        } else
        {
            Jueves = "N";
        }
    });
    $('#IdViernes').click(function () {
        if ($(this).prop('checked') === true)
        {
            Viernes = "Viernes";
        } else
        {
            Viernes = "N";
        }
    });
    $('#IdSabado').click(function () {
        if ($(this).prop('checked') === true)
        {
            Sabado = "Sabado";
        } else
        {
            Sabado = "N";
        }
    });
    $('#IdDomingo').click(function () {
        if ($(this).prop('checked') === true)
        {
            Domingo = "Domingo";
        } else
        {
            Domingo = "N";
        }
    });
    $('#IdFestivo').click(function () {
        if ($(this).prop('checked') === true)
        {
            Festivo = "Festivo";
        } else
        {
            Festivo = "N";
        }
    });


    $(document).ready(function () {
        LoadTabla();
        LoadGrupoHorario();
        LoadTurnos();
        validacionBtn();
    });

    function LoadGrupoHorario() {
        var Frm = "GrupoHorarioJSP";
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
                $('#IdGrupo_Horario').html(resul);
                $('#IdGrupo_Horario_Modal').html(resul);
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
    function LoadTurnos() {
        var Frm = "TurnosJSP";
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
                $('#IdTurno').html(resul);

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

    $(document).on('click', '.SetFormulario', function () {
        LimpiarCampos();
        $('#Id').val($(this).data('id'));
        $('#IdGrupo_Horario').val($(this).data('idgrupoturnos'));
        $('#IdTurno').val($(this).data('idturnos'));
        if ($(this).data('dia') === "Lunes") {
            $('#IdLunes').prop("checked", true);
        }
        if ($(this).data('dia') === "Martes") {
            $('#IdMartes').prop("checked", true);
        }
        if ($(this).data('dia') === "Miercoles") {
            $('#IdMiercoles').prop("checked", true);
        }
        if ($(this).data('dia') === "Jueves") {
            $('#IdJueves').prop("checked", true);
        }
        if ($(this).data('dia') === "Viernes") {
            $('#IdViernes').prop("checked", true);
        }
        if ($(this).data('dia') === "Sabado") {
            $('#IdSabado').prop("checked", true);
        }
        if ($(this).data('dia') === "Domingo") {
            $('#IdDomingo').prop("checked", true);
        }
        if ($(this).data('dia') === "Festivo") {
            $('#IdFestivo').prop("checked", true);
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
    }

    function ValidaCampo()
    {
        var contcheck = 0;
        
        var res = false;                
        if ($('#IdGrupo_Horario').val().toString() !== "0")
        {
            if ($('#IdTurno').val().toString() !== "0")
            {
                res = true;
            }
        }
        
        if (Lunes === "N")
        {
            contcheck = contcheck + 1; 
        }
        if (Martes === "N")
        {
            contcheck = contcheck + 1; 
        }
        if (Miercoles === "N")
        {
            contcheck = contcheck + 1; 
        }
        if (Jueves === "N")
        {
            contcheck = contcheck + 1; 
        }
        if (Viernes === "N")
        {
            contcheck = contcheck + 1; 
        }
        if (Sabado === "N")
        {
            contcheck = contcheck + 1; 
        }
        if (Domingo === "N")
        {
            contcheck = contcheck + 1; 
        }
        if (Festivo === "N")
        {
            contcheck = contcheck + 1; 
        }
        
        if (contcheck === 8)
        {
            res = false;
        }
        
        return res;
    }

    function  LimpiarCampos()
    {
        $('#Id').val('');
        $('#IdGrupo_Horario').val('0');
        $('#IdTurno').val('0');        
        $('#IdLunes').prop("checked", false);
        $('#IdMartes').prop("checked", false);
        $('#IdMiercoles').prop("checked", false);
        $('#IdJueves').prop("checked", false);
        $('#IdViernes').prop("checked", false);
        $('#IdSabado').prop("checked", false);
        $('#IdDomingo').prop("checked", false);
        $('#IdFestivo').prop("checked", false);
    }

    $('#IdAgregar').click(function (e)
    {
        LimpiarCampos();
    });

    $('#IdGuardar').click(function (e)
    {
        if (ValidaCampo() === true)
        {
            var Frm = "GrupoTurnosTurnosJSP";
            var Id = $('#Id').val();
            var IdGrupoHorario = $('#IdGrupo_Horario').val();
            var IdHorario = $('#IdTurno').val();
            var Accion = "Upload";
            var data = {
                frm: Frm,
                id: Id,
                idgrupohorario: IdGrupoHorario,
                idhorario: IdHorario,
                lunes:Lunes,
                martes:Martes,
                miercoles:Miercoles,
                jueves:Jueves,
                viernes:Viernes,
                sabado:Sabado,
                domingo:Domingo,
                festivo:Festivo,                
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
                        title: 'Resultado',
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
        } else
        {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Verifica todos los campos.'
            });
        }
    });


    $('#IdClonarVarios').click(function (e)
    {
        var CheckBoxSelect = new Array();
        $('input[type=checkbox]:checked').each(function () {
            CheckBoxSelect.push($(this).val());
        });
        if (CheckBoxSelect.length > 0) {
            if ($('#IdGrupo_Horario_Modal').val() !== null)
            {
                var Frm = "GrupoTurnosTurnosJSP";
                var IdGrupoHorario = $('#IdGrupo_Horario_Modal').val();
                var Accion = "Upload";
                var data = {
                    frm: Frm,
                    checkbox: CheckBoxSelect,
                    idgrupohorario: IdGrupoHorario,
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
            }
        } else
        {
            Swal.fire({
                icon: 'question',
                title: 'Error',
                text: 'Por favor seleccione un item a duplicar.'
            });
        }
    });
    $('#IdEliminarVarios').click(function (e)
    {
        var CheckBoxSelect = new Array();
        $('input[type=checkbox]:checked').each(function () {
            CheckBoxSelect.push($(this).val());
        });

        if (CheckBoxSelect.length > 0) {
            var Frm = "GrupoTurnosTurnosJSP";
            var Accion = "Delete";
            var data = {
                frm: Frm,
                checkbox: CheckBoxSelect,
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
                        title: 'Eliminado',
                        text: 'Registro Eliminado Satisfactoriamente.'
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
                icon: 'question',
                title: 'Error',
                text: 'Por favor seleccione un item a eliminar.'
            });
        }

    });

    $(document).on('click', '.SetEliminar', function () {
//        if (ValidaCampo() === true)
//        {
        var Frm = "GrupoTurnosTurnosJSP";
        var Id = $(this).data('id');
        var Accion = "Delete";
        var data = {
            frm: Frm,
            id: Id,
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

    function LoadTabla()
    {
        var Frm = "GrupoTurnosTurnosJSP";
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
