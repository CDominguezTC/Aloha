/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $("#header").load("Principal/Head.html");
    $("#script").load("Principal/Script.html");
});
$(function () {

    $(document).ready(function () {
        Load_Tipo_Visita();
        //validacionBtn();
    });
    $('#myselect').change(function () {
        var opval = $(this).val();
        if (opval == "Nuevo") {

            //  }
            //});

            //$(document).ready(function () {
            //    $('#myModal').on('show.bs.modal', function (e) {
            var Frm = "Registro_VisitaJSP";
            var Accion = "Modal_Visita";
            var IdPersona = $('#Id').val();
            var data = {
                frm: Frm,
                accion: Accion,
            };
            enableGif();
            $.ajax({
                type: "POST",
                url: "ServletAlohaTiempos",
                dataType: 'html',
                data: data,
                success: function (resul, textStatus, jqXHR)
                {
                    $('#Modal_Automatico_Persona').html(resul);
                    var tables = document.getElementsByTagName("table");
                    $('#myModal').modal("show");
                    estilo_Tablas(tables);
                    disableGif();
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
//BUSCA LA PERSONA EN LA BASE DE DATOS Y LLENA LOS CAMPOS CORRESPONDIENTES CON SUS HUELLAS E IMAGENES
    $(document).ready(function () {

        $("#IdDocumento").blur(function () {
            var Frm = "PersonasJSP";
            var Cedula = $('#IdDocumento').val();
            var Accion = "Search";
            var Modulo = "Casino";
            if (Cedula !== "")
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
                        if (jqXHR.responseText !== "{}") {

                            $('#Id').val(resul.id);
                            $('#IdTipoDoc').val(resul.tipoIdentificacion);
                            $('#IdDocumento').val(resul.identificacion);
                            $('#IdNombre').val(resul.nombres + ' ' + resul.apellidos);
                            $('#id_empresa_visitante').val(resul.Modelo_empresa_trabaja.nombre);
                            $('#id_empresa_ssocial').val(resul.Modelo_empresa_seguridad_social.nombre);
                            $('#tipo_persona').val(resul.tipo_persona);
                            $('#obs_visitante').val(resul.observacion);
                            Load_Imagenes(resul.Lista_Modelo_Imagenes);
                            Load_Templates(resul.Lista_Modelo_Template);
                            //MostrarImagen();
                            /*$('#IdCentroCosto').val(resul.modeloCentroCosto.id);
                             $('#IdConsume').val(resul.consumocasino);
                             $('#IdGrupoConsumo').val(resul.modeloGrupoConsumo.id);
                             */
                        } else
                        {
                            MensajeCrear();
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
    });
    //DEPENDIENDO DEL TIPO DE VISITA ASI MISMO VALIDA QUE TIPO DE INGORMACION TENDRA EN CUENTA PARA LA PERSONA
    $(document).ready(function () {

        $("#tipo_visitante").change(function () {
            var Frm = "Registro_VisitaJSP";
            var Tipo_Visita = $('#tipo_visitante').val();
            var Accion = "Valida_Tipo_Visita";
            var IdPersona = $('#Id').val();
            var data = {
                frm: Frm,
                tipo_visita: Tipo_Visita,
                accion: Accion,
                id_persona: IdPersona,
            };
            enableGif();
            $.ajax({
                type: "POST",
                url: "ServletAlohaTiempos",
                dataType: 'html',
                data: data,
                success: function (resul, textStatus, jqXHR)
                {
                    var Arreglo = new Array();
                    Arreglo = resul.split(",");
                    if (Arreglo[0] === "Si")
                    {
                        $('#Vencimientos').modal('show'); // abrir                        
                        $('#IdTablaVencimientos').html(Arreglo[1]);
                    }
                    disableGif();
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
    function estilo_Tablas(tables)
    {

        for (var i = 0; i < tables.length; i++) {
            var table = tables[i];
            if (table.id.toString().startsWith("IdTabla_"))
            {
                console.log("TABLA: " + table.id);
                $('#' + table.id).dataTable({
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
                    //, "autoWidth": false
                    //, "destroy": true
                    , "info": true
                            //, "JQueryUI": true
                            //, "ordering": true
                    , "paging": true
                            //, "scrollY": "500px"
                            //, "scrollCollapse": true

                });
            }
        }
    }

    function MensajeCrear()
    {
        Swal.fire({
            title: 'No Existe',
            text: "¿Desea Crearlo?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí',
            cancelButtonText: 'No, cancelar'
        }).then((result) => {
            if (result.value) {
                enableGif();
                $('#myModal').modal("show");
                disableGif();
//                $.ajax({
//                    type: "POST",
//                    url: "ServletAlohaTiempos",
//                    data: data,
//                    success: function (resul, textStatus, jqXHR)
//                    {
//                        disableGif();
//                        Swal.fire({
//                            icon: 'success',
//                            title: 'Eliminado',
//                            text: 'Registro Eliminado Satisfactoriamente.'
//                        });
//                        auditoriaReg("eliminar");
//                        //alert(resul);
//                        LimpiarCampos();
//                        LoadTabla();
//                    },
//                    error: function (jqXHR, textStatus, errorThrown) {
//                        disableGif();
//                        if (jqXHR.status === 0) {
//                            alert('Not connect: Verify Network.');
//                        } else if (jqXHR.status === 404) {
//                            alert('Requested page not found [404]');
//                        } else if (jqXHR.status === 500) {
//                            alert('Internal Server Error [500].');
//                        } else if (textStatus === 'parsererror') {
//                            alert('Requested JSON parse failed.');
//                        } else if (textStatus === 'timeout') {
//                            alert('Time out error.');
//                        } else if (textStatus === 'abort') {
//                            alert('Ajax request aborted.');
//                        } else {
//                            alert('Uncaught Error: ' + jqXHR.responseText);
//                        }
//                    }
//                });
            } else
            {
                Limpiar_Formulario();
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

    function enableGif() {

        window.onload = document.getElementById("espera").style = "display: block";
        window.onload = document.getElementById("Principal").style = "display: none";
    }

    function disableGif() {

        window.onload = document.getElementById("espera").style = "display: none";
        window.onload = document.getElementById("Principal").style = "display: enable";
    }


    function Load_Templates(lista_template)
    {

//Template
        var idtemplates;
        var templat10;
        var c = 0;
//        lista_template = resul.Lista_Modelo_Template;
        if (Object.keys(lista_template).length !== 0)
        {
            for (var i = 0; i < lista_template.length; i++)
            {
                var template = lista_template[i];
                if (c === 0) {
                    idtemplates = template.numero_plantilla;
                    templat10 = template.plantilla;
                    c++;
                } else
                {
                    idtemplates = idtemplates + "," + template.numero_plantilla;
                    templat10 = templat10 + "," + template.plantilla;
                    c++;
                }

            }
            idtemplates = "[" + idtemplates + "]";
            templat10 = "[" + templat10 + "]";
            $('#IdTemplate').val(idtemplates);
            $('#IdTemplate_10').val(templat10);
        }
    }
    function Load_Imagenes(lista_imagenes)
    {
//Imagenes
//lista_imagenes = resul.Lista_Modelo_Imagenes;
        if (Object.keys(lista_imagenes).length !== 0)
        {
            for (var i = 0; i < lista_imagenes.length; i++)
            {
                var imagen = lista_imagenes[i];
                if (imagen.numero_imagen === 0)
                {
                    $('#IdHuella_0').val(imagen.imagen + "," + imagen.numero_imagen);
                }
                if (imagen.numero_imagen === 1)
                {
                    $('#IdHuella_1').val(imagen.imagen + "," + imagen.numero_imagen);
                }
                if (imagen.numero_imagen === 2)
                {
                    $('#IdHuella_2').val(imagen.imagen + "," + imagen.numero_imagen);
                }
                if (imagen.numero_imagen === 3)
                {
                    $('#IdHuella_3').val(imagen.imagen + "," + imagen.numero_imagen);
                }
                if (imagen.numero_imagen === 4)
                {
                    $('#IdHuella_4').val(imagen.imagen + "," + imagen.numero_imagen);
                }
                if (imagen.numero_imagen === 5)
                {
                    $('#IdHuella_5').val(imagen.imagen + "," + imagen.numero_imagen);
                }
                if (imagen.numero_imagen === 6)
                {
                    $('#IdHuella_6').val(imagen.imagen + "," + imagen.numero_imagen);
                }
                if (imagen.numero_imagen === 7)
                {
                    $('#IdHuella_7').val(imagen.imagen + "," + imagen.numero_imagen);
                }
                if (imagen.numero_imagen === 8)
                {
                    $('#IdHuella_8').val(imagen.imagen + "," + imagen.numero_imagen);
                }
                if (imagen.numero_imagen === 9)
                {
                    $('#IdHuella_9').val(imagen.imagen + "," + imagen.numero_imagen);
                }
                if (imagen.numero_imagen === 20)
                {
                    $('#IdSRCImagen').val(imagen.imagen);
                    var img = document.createElement('img');
                    img.src = imagen.imagen;
                    img.width = 100;
                    img.height = 100;
                    document.getElementById("IdImagen").src = img.src;
                }
                if (imagen.numero_imagen === 30)
                {
                    $('#IdFirmaBase64').val(imagen.imagen);
                }
            }
        }
    }

    function Limpiar_Formulario()
    {
        $('#Id').val('');
        $('#IdTipoDoc').val('');
        $('#IdDocumento').val('');
        $('#IdNombre').val('');
        $('#id_empresa_visitante').val('');
        $('#id_empresa_ssocial').val('');
        $('#tipo_persona').val('');
        $('#obs_visitante').val('');
        $('#IdHuella_0').val('');
        $('#IdHuella_1').val('');
        $('#IdHuella_2').val('');
        $('#IdHuella_3').val('');
        $('#IdHuella_4').val('');
        $('#IdHuella_5').val('');
        $('#IdHuella_6').val('');
        $('#IdHuella_7').val('');
        $('#IdHuella_8').val('');
        $('#IdHuella_9').val('');
        $('#IdSRCImagen').val('');
        $('#IdFirmaBase64').val('');
    }

/**
    var tblClientes = $("#tblClientes");
    var lstClientes = "";
    var idCliente = 0;
    $(document).ready(function () {

        jQuery("#list2").jqGrid({
            url: 'ServletAlohaTiempos',
            datatype: "json",
            colNames: ["id", "Nombres", "Apellidos"],
            colModel: [{
                    name: "id",
                    index: "1",
                    hidden: true
                },
                {
                    name: "Nombres",
                    index: "2",
                },
                {
                    name: "Apellidos",
                    index: "3",
                }],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#pager2',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            caption: "JSON Example"
        });
        jQuery("#list2").jqGrid('navGrid', '#pager2', {edit: false, add: false, del: false});


    });

*/

});





