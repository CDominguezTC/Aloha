/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var Arreglo = "";

$(function ()
{
    $(document).ready(function () {
        //Cancelar();
        //LoadTabla();

        Load_Campo_Tabla();
        Load_Campo_Modulo();


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
                    document.getElementById("visiblemodalpersona").disabled = true;
                    document.getElementById("nombre_persona").disabled = true;
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

    $('#IdGuardar1').click(function (e)
    {
        Arreglo = "";
        Recorrer_Tabla();
        if (ValidaCampo() === true)
        {
            var Frm = "ParametroTablaJSP";

            var datas = Arreglo;
            var Accion = "Upload";
            var data = {
                frm: Frm,
                datas: datas,
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
//                    if (Id != "") {
//                        //console.log("Ingreso a id no null " + Id + "!");
//                        auditoriaReg("actualizar");
//                    }
                    disableGif();
                    //alert(resul);
                    //Cancelar();
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

    $('#Idbuscar').click(function (e)
    {
        LoadTabla();
    });

    function Recorrer_Tabla()
    {
        var id_tabla = $('#id_tabla').val();
        var id_modulo = $('#id_modulo').val();

        //obtengo Input Nuevo Nombre
        var id_campo = null;
        var nombre_campo_db = null;
        var input_Nombre_Visible = null;
        var input_Tipo_Campo = null;
        var input_Tabla_Referencia = null;
        var visualizar = null;
        var habilitar = null;
        var obligatorio = null;
        var lista = null;



        var table = document.getElementById("datatable");
        for (var i = 1, row; row = table.rows[i]; i++)
        {
            //iterate through rows 
            //rows would be accessed using the "row" variable assigned in the for loop 
            for (var j = 0, col; col = row.cells[j]; j++)
            {
                if (j === 0) {
                    id_campo = document.getElementById("datatable").rows[i].cells[j].innerText;
                }
                if (j === 1) {
                    nombre_campo_db = document.getElementById("datatable").rows[i].cells[j].innerText;
                }
                if (j === 2) {
                    input_Nombre_Visible = document.getElementById("datatable").rows[i].cells[j].firstChild.value;
                }
                if (j === 3) {
                    input_Tipo_Campo = document.getElementById("datatable").rows[i].cells[j].firstChild.value;
                }
                if (j === 4) {
                    input_Tabla_Referencia = document.getElementById("datatable").rows[i].cells[j].firstChild.value;                   
                }
                if (j === 5) {
                    var vis = "Visualizar_" + id_campo;
                    if ($('#' + vis).prop('checked')) {
                        visualizar = "Si";
                    } else
                    {
                        visualizar = "No";
                    }
                }
                if (j === 6) {
                    var hab = "Habilitar_" + id_campo;
                    if ($('#' + hab).prop('checked')) {
                        habilitar = "Si";
                    } else
                    {
                        habilitar = "No";
                    }
                }
                if (j === 7) {
                    var obl = "Obligatorio_" + id_campo;
                    if ($('#' + obl).prop('checked')) {
                        obligatorio = "Si";
                    } else
                    {
                        obligatorio = "No";
                    }
                }
                if (j === 8) {
                    var select = document.getElementById("Lista_" + id_campo); //El <select>
                    var value = select.value; //El valor seleccionado
                    var lista = select.options[select.selectedIndex].innerText; //El texto de la opción seleccionada
                }
            }
            Arreglo = Arreglo + id_campo + "," + id_tabla + "," + id_modulo + "," + nombre_campo_db + "," + input_Nombre_Visible + "," + input_Tipo_Campo + "," + input_Tabla_Referencia + "," + visualizar + "," + habilitar + "," + obligatorio + "," + value + ";";

        }

    }



    function LoadTabla()
    {
        var Frm = "ParametroTablaJSP";
        var Accion = "Read";
        var id_tabla = $('#id_tabla').val();
        var id_modulo = $('#id_modulo').val();
        var nombre_tabla = $('#id_tabla').find('option:selected').text();
        var evento = "Select";
        var id_campo = "1";

        var data = {
            frm: Frm,
            accion: Accion,
            id_tabla: id_tabla,
            id_modulo: id_modulo,
            nombre_tabla: nombre_tabla,
            evento: evento,
            id_campo: id_campo
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


    function Load_Campo_Tabla() {
        var Frm = "EnumeracionJSP";
        var Evento = "Select";
        var Accion = "Read";
        var Id_campo = "18";
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
                $('#id_tabla').html(resul);

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

    function Load_Campo_Modulo() {
        var Frm = "EnumeracionJSP";
        var Evento = "Select";
        var Accion = "Read";
        var Id_campo = "57";
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
                $('#id_modulo').html(resul);
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
        var id_persona = $('#id_persona').val();
        var id_vencimiento = $('#id_vencimiento').val();
        var fecha_vencimiento = $('#fecha_vencimiento').val();


        if (id_persona !== null)
        {
            if (id_vencimiento !== null)
            {
                if (fecha_vencimiento !== null)
                {
                    res = true;
                }
            }
        }
        return res;
    }

    function  LimpiarCampos()
    {
        $('#Id').val('');
        $('#id_persona').val('');
        $('#id_personaOld').val('');
        $('#id_vencimiento').val('');
        $('#id_vencimientoOld').val('');
        $('#fecha_vencimiento').val('');
        $('#fecha_vencimientoOld').val('');

        document.getElementById("visiblemodalpersona").disabled = false;
        document.getElementById("nombre_persona").disabled = false;
        document.getElementById("id_vencimiento").disabled = false;
        document.getElementById("fecha_vencimiento").disabled = false;
        document.getElementById("IdGuardar").disabled = false;
    }

    function  Cancelar()
    {
        $('#Id').val('');
        $('#id_persona').val('');
        $('#nombre_persona').val('');
        $('#id_personaOld').val('');
        $('#id_vencimiento').val('');
        $('#id_vencimientoOld').val('');
        $('#fecha_vencimiento').val('');
        $('#fecha_vencimientoOld').val('');


        document.getElementById("visiblemodalpersona").disabled = true;
        document.getElementById("nombre_persona").disabled = true;
        document.getElementById("id_vencimiento").disabled = true;
        document.getElementById("fecha_vencimiento").disabled = true;
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
        var id_persona = $('#id_persona').val() + " - " + $('#id_persona').find('option:selected').text();
        var id_personaOld = $('#id_personaOld').val();
        var id_vencimiento = $('#id_vencimiento').val() + " - " + $('#id_vencimiento').find('option:selected').text();
        var id_vencimientoOld = $('#id_vencimientoOld').val();
        var fecha_vencimiento = $('#fecha_vencimiento').val();
        var fecha_vencimientoOld = $('#fecha_vencimientoOld').val();


        var Accion = "Insert";
        var Operacion;

        if (modo === "actualizar") {

            if (id_persona != id_personaOld) {
                Observacion = "Persona: " + id_personaOld + " > " + id_persona + " ";
            }

            if (id_vencimiento != id_vencimientoOld) {
                Observacion += "Item: " + id_vencimientoOld + " > " + id_vencimiento;
            }

            if (fecha_vencimiento != fecha_vencimientoOld) {
                Observacion += "Vencimiento: " + fecha_vencimientoOld + " > " + fecha_vencimiento;
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
            tabla: "asociacion_grupo_vencimiento",
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
