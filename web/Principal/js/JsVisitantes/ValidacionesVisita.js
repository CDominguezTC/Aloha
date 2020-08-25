/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var idInput;
var Identificacion_Global
var Nombres_Cedula;
var Apellidos_Cedula;
var Genero_Cedula;
var Rh_Cedula;
var Fecha_Cedula;


$(function () {
    $("#header").load("Principal/Head.html");
    $("#script").load("Principal/Script.html");
});
$(function () {

    $(document).ready(function () {
        Load_Tipo_Visita();
        //validacionBtn();
    });



    $(document).on('click', '.SetFormularioTabla', function () {

        alert($(this).data('id'));
        //$('#id_persona').val($(this).data('id'));
        //$('#nombre_persona').val($(this).data('nombre_persona'));
        //$('#BusquedaPersona').modal('hide');

    });


    $('#myselect').change(function () {
        var opval = $(this).val();
        if (opval === "Nuevo") {
            AbrirModal();
        }
    });

    function AbrirModal() {

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
                $('#myModal').modal("show");

                Datos_Tablas();

                $("input").click(function () {
                    idInput = $(this).attr('id');


                });
                var elementos;
                var Obligatorio;


                $("button").click(function () {

                    var Tabla = "Persona";
                    //var TipoDato = new Array();
                    var Campos = new Array();
                    var Datos = new Array();
                    var Dato;
                    var TipoDato;
                    var DatosCompletos = true;

                    elementos = document.getElementsByName('Elementos');
                    for (var i = 0; i < elementos.length; i++) {
                        console.log(elementos[i].getAttribute("Tipo_Campo"));
                        console.log(elementos[i].getAttribute("Pertenencia"));
                        console.log(elementos[i].getAttribute("Obligatorio"));



                        Obligatorio = elementos[i].getAttribute("Obligatorio");
                        if (Obligatorio === "Si") {
                            if (elementos[i].value === null || elementos[i].value === "") {
                                elementos[i].style.backgroundColor = "pink";
                                DatosCompletos = false;
                            }
                        }

                        TipoDato = elementos[i].getAttribute("Tipo_Campo");
                        if (TipoDato === "Integer") {
                            Dato = $('#' + elementos[i].id).val();
                            if (Dato === "" || Dato === "null") {
                                Dato = "null";
                            }
                        }
                        if (TipoDato === "String") {
                            Dato = $('#' + elementos[i].id).val();
                            if (Dato === "" || Dato === "null") {
                                Dato = "null";
                            } else {
                                Dato = "'" + Dato + "'";
                            }
                        }
                        if (TipoDato === "Lista") {
                            Dato = $('#' + elementos[i].id).find('option:selected').text();
                            if (Dato === "" || Dato === "null" || Dato === "Seleccione") {
                                Dato = "null";
                            } else {
                                Dato = "'" + Dato + "'";
                            }
                        }
                        if (TipoDato === "Date") {
                            Dato = $('#' + elementos[i].id).val();
                            if (Dato === "" || Dato === "null") {
                                Dato = "null";
                            } else {
                                Dato = "'" + Dato + "'";
                            }
                        }
                        if (TipoDato === "Moedelo") {
                            Dato = $('#id_' + elementos[i].id).val();
                            if (Dato === "" || Dato === "null") {
                                Dato = "null";
                            }
                        }
                        if (elementos[i].getAttribute("Pertenencia") !== "id") {
                            Campos.push(elementos[i].getAttribute("Pertenencia"));
                            if (elementos[i].getAttribute("Pertenencia") === "estado") {
                                Dato = "S";
                            }
                            Datos.push(Dato);
                            console.log($('#' + elementos[i].id).val());
                        }

                    }
                    if (DatosCompletos) {
                        GuardarPersona(Tabla, Campos, Datos);
                    }

                });



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
    ;
//BUSCA LA PERSONA EN LA BASE DE DATOS Y LLENA LOS CAMPOS CORRESPONDIENTES CON SUS HUELLAS E IMAGENES
    $(document).ready(function () {

        $("#IdDocumento").blur(function () {


            ValidarCadenaIdentificacion();




                    var Frm = "PersonasJSP";
            var Cedula = Identificacion_Global;
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



    function Datos_Tablas()
    {
        var curModal;

        $(function () {
            return $(".modal").on("show.bs.modal", function () {

                curModal = this;
                var id_parametro_tabla = curModal.getAttribute("id_parametro_tabla");
                var modulo = curModal.getAttribute("modulo");
                var tabla = curModal.getAttribute("tabla");
                Load_Datos_Tabla_GSON(tabla, modulo, id_parametro_tabla, curModal);

            });
        });
    }


    function Load_Datos_Tabla_Html(Tabla, modulo, id_parametro_tabla, curModal)
    {
        var Frm = "Registro_VisitaJSP";
        var Accion = "Datos_Tabla";

        var data = {
            frm: Frm,
            accion: Accion,
            modulo: modulo,
            id_parametro_tabla: id_parametro_tabla
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
                $('#' + Tabla).html(resul);
                $('#' + Tabla).dataTable({
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

                var tables = $('.dataTable').DataTable();
                $('.dataTable tbody').on('click', 'tr', function () {
                    var tableData = tables.table($(this).parents('table'));
                    var arr = tableData.row(this).data();
                    var mydata = JSON.parse(JSON.stringify(arr));
                    console.log(mydata);
                    $(curModal).modal("hide");

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

    function Load_Datos_Tabla_GSON(Tabla, modulo, id_parametro_tabla, curModal)
    {
        var Frm = "Registro_VisitaJSP";
        var Accion = "Datos_Tabla";

        var data = {
            frm: Frm,
            accion: Accion,
            modulo: modulo,
            id_parametro_tabla: id_parametro_tabla
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
                var datos = JSON.parse(resul.split("*")[1]);
                var titulos = JSON.parse(resul.split("*")[0]);

                var dataObject = {
                    columns: titulos,
                    data: datos
                };

                $('#' + Tabla).dataTable({

                    data: dataObject.data,
                    //responsive: true,
                    //order: [[1, 'asc']],
                    columns: dataObject.columns,
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
                    , "deferRender": true
                    , "scrollY": 200
                    , "scrollCollapse": true
                    , "scroller": true
                    , "autoWidth": true
                    , "destroy": true
                    , "scrollX": true

                });


                var tables = $('.dataTable').DataTable();
                $('.dataTable tbody').on('click', 'tr', function () {
                    var tableData = tables.table($(this).parents('table'));
                    tableData.columns.adjust().draw();
                    var arr = tableData.row(this).data();
                    var mydata = JSON.parse(JSON.stringify(arr));
                    console.log(mydata);
                    $(curModal).modal("hide");
                    $('#id_' + idInput).val(mydata.id);
                    $('#' + idInput).val(mydata.nombre);

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
                AbrirModal();
                //disableGif();
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


    function LoadTablaDinamica(Tabla) {

        var Frm = "Registro_VisitaJSP";
        var Accion = "LLenarTablaDinamica";
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
                var datos = JSON.parse(resul.split(";")[1]);
                var titulos = JSON.parse(resul.split(";")[0]);

                var dataObject = {
                    columns: titulos,
                    data: datos
                };
                var columns = [];

                $('#datatable-responsive').dataTable({

                    data: dataObject.data,
                    responsive: true,
                    order: [[1, 'asc']],
                    columns: dataObject.columns,
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
                    , "deferRender": true
                    , "scrollY": 200
                    , "scrollCollapse": true
                    , "scroller": true
                    , "autoWidth": false
                    , "destroy": true

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

    function GuardarPersona(Tabla, Campos, Datos) {

        var Frm = "Registro_VisitaJSP";
        var Accion = "Crear";
        var data = {
            frm: Frm,
            accion: Accion,
            tabla: Tabla,
            campos: Campos,
            datos: Datos
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
                    text: 'Registro Guardado Satisfactoriamente.'
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

    function LLenarCamposVisita() {
        $('#IdDocumento').val(Identificacion_Global);
    }

    function ValidarCadenaIdentificacion() {
        var Identificacion = $('#IdDocumento').val();
        var Areglocedula = Identificacion.split(",");
        var TamAreglo = Areglocedula.length;
        if (TamAreglo === 9 || TamAreglo === 1)
        {
            Identificacion_Global = Areglocedula[0];
            Apellidos_Cedula = Areglocedula[1] + " " + Areglocedula[2];
            Nombres_Cedula = Areglocedula[3] + " " + Areglocedula[4];
            Genero_Cedula = Areglocedula[5];
            Rh_Cedula = Areglocedula[7];
        }
        else{
            Identificacion_Global = Identificacion;
        }
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





