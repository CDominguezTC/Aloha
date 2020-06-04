/**
 * Envia Informacion al servlet para llenar un listado dependiendo de una enumeracion general
 *
 * @author: Diego Fernando Guzman
 * @param request
 * @return String
 * @version: 21/05/2020
 */


$(function ()
{
    $(document).ready(function () {

        Cancelar();

        //document.getElementById("id_enumeracion").disabled = true;
        //document.getElementById("IdNombre").disabled = true;
        //document.getElementById("IdGuardar").disabled = true;


        LoadTabla();
        Load_Todos();
        Load_Tipo_Persona();
        Load_Tipo_Visita();
        Load_Si_No();
        //validacionBtn();

    });

    $(document).on('click', '.SetFormulario', function () {
        $('#Id').val($(this).data('id'));
        $('#id_enumeracion').val($(this).data('id_enumeracion'));
        $('#id_enumeracionOld').val($(this).data('id_enumeracion') + " - " + $('#id_enumeracion').find('option:selected').text());
        $('#IdNombre').val($(this).data('campo'));
        $('#IdNombreOld').val($(this).data('campo'));

        document.getElementById("id_enumeracion").disabled = false;
        document.getElementById("IdNombre").disabled = false;
        document.getElementById("IdGuardar").disabled = false;
        window.scroll(0, 0);

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
                    document.getElementById("IdAgregar").disabled = true;
                    //evt.preventDefault();
                } else
                {
                    document.getElementById("IdAgregar").disabled = false;
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

    $('#IdGuardar').click(function (e)
    {
        if (ValidaCampo() === true)
        {
            var Frm = "EnumeracionJSP";
            var Id = $('#Id').val();
            var Id_enumeracion = $('#id_enumeracion').val();
            var Campo = $('#IdNombre').val();
            var Accion = "Upload";
            var data = {
                frm: Frm,
                id: Id,
                id_enumeracion: Id_enumeracion,
                campo: Campo,
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
                    if (Id != "") {
                        //console.log("Ingreso a id no null " + Id + "!");
                        auditoriaReg("actualizar");
                    }
                    disableGif();
                    //alert(resul);
                    LimpiarCampos();
                    LoadTabla();
                    Load_Todos();

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

        $('#Id').val($(this).data('id'));
        $('#id_enumeracion').val($(this).data('id_enumeracion'));
        $('#IdNombre').val($(this).data('campo'));

        var Frm = "EnumeracionJSP";
        var Id = $('#Id').val();
        var Id_enumeracion = $('#id_enumeracion').val();
        var Campo = $('#IdNombre').val();
        var Accion = "Delete";
        var data = {
            frm: Frm,
            id: Id,
            id_enumeracion: Id_enumeracion,
            campo: Campo,
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
                        auditoriaReg("eliminar");
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
        var Frm = "EnumeracionJSP";
        var Accion = "Read";
        var Id_campo = "0";
        var data = {
            frm: Frm,
            accion: Accion,
            id_campo: Id_campo
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

                validacionBtn();
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

    }

    /**
     * Recolecta los Todos los datos de la tabla enumeracion
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 21/05/2020
     */

    function Load_Todos() {
        var Frm = "EnumeracionJSP";
        var Evento = "Select";
        var Accion = "Read";
        var Id_campo = "0";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion,
            id_campo: Id_campo
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
                $('#id_enumeracion').html(resul);

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

    /**
     * Recolecta los tipos de personas para ser adicionados al select tipo Persona
     *
     * @author: Diego Fernando Guzman
     * @param request
     * @return String
     * @version: 21/05/2020
     */

    function Load_Tipo_Persona() {
        var Frm = "EnumeracionJSP";
        var Evento = "Select";
        var Accion = "Read";
        var Id_campo = "1";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion,
            id_campo: Id_campo
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
                $('#tipo_persona').html(resul);

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


    function Load_Tipo_Visita() {
        var Frm = "EnumeracionJSP";
        var Evento = "Select";
        var Accion = "Read";
        var Id_campo = "5";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion,
            id_campo: Id_campo
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
                $('#tipo_visitante').html(resul);

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

    function Load_Si_No() {
        var Frm = "EnumeracionJSP";
        var Evento = "Select";
        var Accion = "Read";
        var Id_campo = "10";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion,
            id_campo: Id_campo
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
                $('#IdConsume').html(resul);

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
        if ($('#id_enumeracion').val() !== "")
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
        $('#id_enumeracion').val('');
        $('#IdNombre').val('');
        $('#id_enumeracionOld').val('');
        $('#IdNombreOld').val('');

        document.getElementById("id_enumeracion").disabled = false;
        document.getElementById("IdNombre").disabled = false;
        document.getElementById("IdGuardar").disabled = false;


    }

    function  Cancelar()
    {
        $('#Id').val('');
        $('#id_enumeracion').val('');
        $('#IdNombre').val('');
        $('#id_enumeracionOld').val('');
        $('#IdNombreOld').val('');

        document.getElementById("id_enumeracion").disabled = true;
        document.getElementById("IdNombre").disabled = true;
        //document.getElementById("IdAgregar").disabled = true;
        document.getElementById("IdGuardar").disabled = true;

    }

    $('#IdAgregar').click(function (e)
    {
        LimpiarCampos();
    });

    $('#IdCancelar').click(function (e)
    {
        Cancelar();
    });


    function auditoriaReg(modo) {


        var Observacion = "";
        var NamUs = document.getElementById('usering').innerHTML
        var Id = $('#Id').val();
        var Frm = "Auditoria";
        var id_enumeracion = $('#id_enumeracion').val() + " - " + $('#id_enumeracion').find('option:selected').text();
        var IdNombre = $('#IdNombre').val();
        var id_enumeracionOld = $('#id_enumeracionOld').val();
        var IdNombreOld = $('#IdNombreOld').val();


        var Accion = "Insert";
        var Operacion;

        if (modo === "actualizar") {

            if (id_enumeracion != id_enumeracionOld) {
                Observacion = "Enumeracion: " + id_enumeracionOld + " > " + id_enumeracion + " ";
            }

            if (IdNombre != IdNombreOld) {
                Observacion += "Nombre: " + IdNombreOld + " > " + IdNombre;
            }
            Operacion = "actualizar";

        } else if (modo === "eliminar") {
            Observacion = "Se elimino el registro."
            Operacion = "eliminar";
            //console.log("Id: " + Id);
        }

        var data = {
            frm: Frm,
            operacion: Operacion,
            tabla: "enumeracion",
            usua: NamUs,
            observacion: Observacion,
            id: Id,
            accion: Accion
        };
        enableGif();
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            data: data,
            success: function (resul, textStatus, jqXHR) {

                console.log("Auditoria realizada");
                /*Swal.fire({
                 icon: 'success',
                 title: 'Guardado',
                 text: 'Auditoria realizada.'
                 });*/

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
