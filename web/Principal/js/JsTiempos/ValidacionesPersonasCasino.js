$(function () {
    $("#header").load("Principal/Head.html");
    $("#script").load("Principal/Script.html");
});

$(function () {

    $(document).ready(function () {
        LoadTabla();
        LoadCargo();
        LoadEmpresas();
        LoadCentroCosto();
        LoadGrupoConsumo();
        validacionBtn();

    });
    // cargamos los cargos al select 
    function LoadCargo() {
        var Frm = "CargosJSP";
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
                $('#IdCargo').html(resul);
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
    // cargamos las empresas al select 
    function LoadEmpresas() {
        var Frm = "EmpresaJSP";
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
                $('#IdEmpresa').html(resul);
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
    // cargamos los centros de costos al select 
    function LoadCentroCosto() {
        var Frm = "CentroCostoJSP";
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
                $('#IdCentroCosto').html(resul);
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
    // cargamos los grupo consumo al select     
    function LoadGrupoConsumo() {
        var Frm = "GrupoConsumoJSP";
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
                $('#IdGrupoConsumo').html(resul);
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

    $(document).ready(function () {

        $("#IdCedula").blur(function () {
            var Frm = "PersonasJSP";
            var Cedula = $('#IdCedula').val();
            var Accion = "Search";
            var Modulo = "Casino";
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
                    if (Object.keys(resul).length !== 0) {
                        $('#Id').val(resul.id);
                        $('#IdTipoDoc').val(resul.tipo_identificacion);
                        $('#IdCedula').val(resul.identificacion);
                        $('#IdNombre').val(resul.nombres);
                        $('#IdApellido').val(resul.apellidos);
                        $('#IdEmpresa').val(resul.Modelo_empresa_trabaja.id);
                        $('#IdCentroCosto').val(resul.Modelo_centro_costo.id);
                        $('#IdConsume').val(resul.consumo_casino);
                        $('#IdGrupoConsumo').val(resul.Modelo_grupo_consumo.id);
                        $('#IdObservacion').val(resul.observacion);
                        //Imagenes
                        var imagenes = resul.Lista_Modelo_Imagenes;
                        if (Object.keys(imagenes).length !== 0)
                        {
                            for (var i = 0; i < imagenes.length; i++)
                            {
                                var imagen = imagenes[i];
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
                                    document.getElementById("IdImagen").src = img.src;
                                }
                                if (imagen.numero_imagen === 30)
                                {
                                    $('#IdFirmaBase64').val(imagen.imagen);
                                }
                            }
                        }
                        //Template
                        var idtemplates;
                        var templat10;
                        var c = 0;
                        var lista_template = resul.Lista_Modelo_Template;
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
                    } else
                    {
                        $('#Id').val('');
                        $('#IdNombre').val('');
                        $('#IdApellido').val('');
                        $('#IdEmpresa').val('0');
                        $('#IdCentroCosto').val('0');
                        $('#IdConsume').val('0');
                        $('#IdGrupoConsumo').val('0');
                        $('#IdHuella_0').val('');
                        $('#IdHuella_1').val('');
                        $('#IdHuella_2').val('');
                        $('#IdHuella_3').val('');
                        $('#IdHuella_4').val('');
                        $('#IdHuella_5').val('');
                        $('#IdHuella_6').val('');
                        $('#IdHuella_7').val('');
                        $('#IdHuella_8').val('');
                        $('#IdTemplate').val('');
                        $('#IdTemplate_10').val('');
                        $('#IdSRCImagen').val('');
                        $('#IdFirmaBase64').val('');
                        var img = document.createElement('img');
                        img.src = "Principal/images/user.png";
                        document.getElementById("IdImagen").src = img.src;

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
        });
    });

    $(document).on('click', '.SetFormulario', function () {
        //LimpiarCampos();
        $('#Id').val($(this).data('id'));
        $('#IdTipoDocOld').val($(this).data('tipodoc'));
        $('#IdTipoDoc').val($(this).data('tipodoc'));
        $('#IdCedulaOld').val($(this).data('cedula'));
        $('#IdCedula').val($(this).data('cedula'));
        $('#IdNombreOld').val($(this).data('nombre'));
        $('#IdNombre').val($(this).data('nombre'));
        $('#IdApellidoOld').val($(this).data('apellido'));
        $('#IdApellido').val($(this).data('apellido'));
        $('#IdEmpresaOld').val($(this).data('empresa'));
        $('#IdEmpresa').val($(this).data('empresa'));
        $('#IdCargo').val($(this).data('cargo'));
        $('#IdCentroCostoOld').val($(this).data('centrocosto'));
        $('#IdCentroCosto').val($(this).data('centrocosto'));
        $('#IdConsumeOld').val($(this).data('consume'));
        $('#IdConsume').val($(this).data('consume'));
        $('#IdGrupoConsumoOld').val($(this).data('grupoconsumo'));
        $('#IdGrupoConsumo').val($(this).data('grupoconsumo'));
        $('#IdObservacionOld').val($(this).data('observacion'));
        $('#IdObservacion').val($(this).data('observacion'));
        //Cargamos las Imagenes (Huellas, Foto y firma) y Templates de las personas selecionada
        LoadImagenes($(this).data('id'));
        LoadTemplate($(this).data('id'));

        //datos de imagnes foto
//        $('#IdSRCImagen').val($(this).data('foto'));
//        var f = $(this).data('foto');
//        var img = document.createElement('img');
//        img.src = f;
//        document.getElementById("IdImagen").src = img.src;
//        //datos de imagnes firma
//        $('#IdFirmaBase64').val($(this).data('firma'));
//        //datos de imagnes huellas
//        $('#IdHuella_0').val($(this).data('huella_0'));
//        $('#IdHuella_1').val($(this).data('huella_1'));
//        $('#IdHuella_2').val($(this).data('huella_2'));
//        $('#IdHuella_3').val($(this).data('huella_3'));
//        $('#IdHuella_4').val($(this).data('huella_4'));
//        $('#IdHuella_5').val($(this).data('huella_5'));
//        $('#IdHuella_6').val($(this).data('huella_6'));
//        $('#IdHuella_7').val($(this).data('huella_7'));
//        $('#IdHuella_8').val($(this).data('huella_8'));
//        $('#IdHuella_9').val($(this).data('huella_9'));
//        //datos de templates
//        $('#IdTemplate').val("[" + $(this).data('idtemplate') + "]");
//        $('#IdTemplate_10').val($(this).data('template10'));
    });

    function LoadImagenes(Id_Persona) {
        if (Id_Persona > 0) {
            var Frm = "ImagenesJSP";
            var IdPersonas = Id_Persona;
            var Accion = "Search";
            var data = {
                frm: Frm,
                accion: Accion,
                idpersona: IdPersonas,
            };
            $.ajax({
                type: "POST",
                url: "ServletAlohaTiempos",
                data: data,
                success: function (resul, textStatus, jqXHR) {
                    AsiganmosValoresImagenes(resul);
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

    //Cargamos los Templates de id Seleccionado
    function LoadTemplate(Id_Persona) {
        if (Id_Persona > 0) {
            var Frm = "TemplateJSP";
            var IdPersonas = Id_Persona;
            var Accion = "Search";
            var data = {
                frm: Frm,
                accion: Accion,
                idpersona: IdPersonas,
            };
            $.ajax({
                type: "POST",
                url: "ServletAlohaTiempos",
                data: data,
                success: function (resul, textStatus, jqXHR) {
                    AsiganmosValoresTemplates(resul);
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

    function AsiganmosValoresImagenes(resul)
    {
        var imagenes = resul;
        if (Object.keys(resul).length !== 0)
        {
            for (var i = 0; i < imagenes.length; i++)
            {
                var imagen = imagenes[i];
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
                    document.getElementById("IdImagen").src = img.src;
                }
                if (imagen.numero_imagen === 30)
                {
                    $('#IdFirmaBase64').val(imagen.imagen);
                }
            }
        }
    }
    function AsiganmosValoresTemplates(resul)
    {
        var idtemplates;
        var templat10;
        var c = 0;
        var lista_template = resul;
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


    $(document).on('click', '.SetFormularioId', function () {
        $('#Id').val($(this).data('id'));
        if ($(this).data('idhoteleria') !== 0)
        {
            $('#IdHoteleria').val($(this).data('idhoteleria'));
            $('#IdAdicional').val($(this).data('idadicional'));
            $('#IdConsumeHoteleria').val($(this).data('idconsumehoteleria'));
            $('#IdValorHoteleria').val($(this).data('idvalorhoteleria'));
            $('#IdConsumeAdicional').val($(this).data('idconsumeadicional'));
            $('#IdValorAdicional').val($(this).data('idvaloradicional'));
        } else
        {
            $('#IdHoteleria').val('');
            $('#IdAdicional').val('');
            $('#IdConsumeHoteleria').val('1HN');
            $('#IdValorHoteleria').val('25000');
            $('#IdConsumeAdicional').val('1AN');
            $('#IdValorAdicional').val('25000');
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

    function ValidaCampo() {
        var res = false;
        if ($('#IdTipoDoc').val() !== null)
        {
            if ($('#IdEmpresa').val() !== "0")
            {
                if ($('#IdCargo').val() !== "0")
                {
                    if ($('#IdCentroCosto').val() !== "0")
                    {
                        if ($('#IdGrupoConsumo').val() !== "0")
                        {
                            if ($('#IdConsume').val() !== null)
                            {
                                if ($('#IdCedula').val() !== "")
                                {
                                    if ($('#IdNombre').val() !== "")
                                    {
                                        if ($('#IdApellido').val() !== "")
                                        {
                                            if ($('#IdConsume').val() !== "0")
                                            {
                                                if ($('#IdGrupoConsumo').val() !== "0")
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
            }
        }
        return res;
    }

    function  LimpiarCampos() {
        $('#Id').val('');
        $('#IdTipoDoc').val(0);
        $('#IdTipoDocOld').val('');
        $('#IdCedula').val('');
        $('#IdCedulaOld').val('');
        $('#IdNombre').val('');
        $('#IdNombreOld').val('');
        $('#IdApellido').val('');
        $('#IdApellidoOld').val('');
        $('#IdEmpresa').val(0);
        $('#IdEmpresaOld').val('');
        $('#IdCargo').val(0);
        $('#IdCargoOld').val('');
        $('#IdCentroCosto').val(0);
        $('#IdCentroCostoOld').val('');
        $('#IdConsume').val(0);
        $('#IdConsumeOld').val('');
        $('#IdGrupoConsumo').val(0);
        $('#IdGrupoConsumoOld').val('');
        $('#IdObservacion').val('');
        $('#IdObservacionOld').val('');

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

        $('#IdTemplate').val('');
        $('#IdTemplate_10').val('');

        $('#IdSRCImagen').val('');
        var img = document.createElement('img');
        img.src = "Principal/images/user.png";
        document.getElementById("IdImagen").src = img.src;
        $('#IdImagen').append(img)
    }

    $('#IdAgregar').click(function (e) {
        LimpiarCampos();
    });

    $('#IdGuardar').click(function (e) {
        if (ValidaCampo() === true) {
            var NamUs = document.getElementById('usering').innerHTML
            var Frm = "PersonasJSP";
            var Id = $('#Id').val();
            var TipoDoc = $('#IdTipoDoc').val();
            var Cedula = $('#IdCedula').val();
            var Nombre = $('#IdNombre').val();
            var Apellido = $('#IdApellido').val();
            var Empresa = $('#IdEmpresa').val();
            var Cargo = $('#IdCargo').val();
            var CentroCosto = $('#IdCentroCosto').val();
            var Consumo = $('#IdConsume').val();
            var GrupoConsumo = $('#IdGrupoConsumo').val();
            var Observacion = $('#IdObservacion').val();
            //Datos multimedia
            var Huella_0 = $('#IdHuella_0').val();
            var Huella_1 = $('#IdHuella_1').val();
            var Huella_2 = $('#IdHuella_2').val();
            var Huella_3 = $('#IdHuella_3').val();
            var Huella_4 = $('#IdHuella_4').val();
            var Huella_5 = $('#IdHuella_5').val();
            var Huella_6 = $('#IdHuella_6').val();
            var Huella_7 = $('#IdHuella_7').val();
            var Huella_8 = $('#IdHuella_8').val();
            var Huella_9 = $('#IdHuella_9').val();
            var Templates_10 = $('#IdTemplate_10').val();
            var IdTemplates = $('#IdTemplate').val();
            var Foto = $('#IdSRCImagen').val();
            var Firma = $('#IdFirmaBase64').val();
            var Tipo_Persona = "EMPLEADO";
            var Accion = "Upload";
            var Modulo = "Casino";
            var data = {
                frm: Frm,
                id: Id,
                tipodoc: TipoDoc,
                cedula: Cedula,
                nombre: Nombre,
                apellido: Apellido,
                empresa: Empresa,
                cargo: Cargo,
                centrocosto: CentroCosto,
                consumo: Consumo,
                grupoconsumo: GrupoConsumo,
                observacion: Observacion,
                huella0: Huella_0,
                huella1: Huella_1,
                huella2: Huella_2,
                huella3: Huella_3,
                huella4: Huella_4,
                huella5: Huella_5,
                huella6: Huella_6,
                huella7: Huella_7,
                huella8: Huella_8,
                huella9: Huella_9,
                templates10: Templates_10,
                idtemplates: IdTemplates,
                foto: Foto,
                firma: Firma,
                tipopersona: Tipo_Persona,
                accion: Accion,
                nombreU: NamUs,
                modulo: Modulo
            };
            enableGif();
            $.ajax({
                type: "POST",
                url: "ServletAlohaTiempos",
                data: data,
                success: function (resul, textStatus, jqXHR) {

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

    $('#IdIniServicio').click(function (e) {
        var Frm = "CargosJSP";
        var Id = $('#Id').val();
        var IdHoteleria = $('#IdHoteleria').val();
        var IdAdicional = $('#IdAdicional').val();
        var ConsumoHoteleria = $('#IdConsumeHoteleria').val();
        var ValorHoteleria = $('#IdValorHoteleria').val();
        var ConsumoAdicional = $('#IdConsumeAdicional').val();
        var ValorAdicional = $('#IdValorAdicional').val();
        var Accion = "IniServicio";
        var data = {
            frm: Frm,
            id: Id,
            idhoteleria: IdHoteleria,
            idadicional: IdAdicional,
            consumohoteleria: ConsumoHoteleria,
            valorhoteleria: ValorHoteleria,
            consumoadicional: ConsumoAdicional,
            valoradicional: ValorAdicional,
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
    });

    $('#IdFinServicio').click(function (e) {
        var Frm = "CargosJSP";
        var Id = $('#Id').val();
        var IdHoteleria = $('#IdHoteleria').val();
        var IdAdicional = $('#IdAdicional').val();
        var Accion = "FinServicio";
        var data = {
            frm: Frm,
            id: Id,
            idhoteleria: IdHoteleria,
            idadicional: IdAdicional,
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
    });

    $(document).on('click', '.SetEliminar', function () {

        $('#Id').val($(this).data('id'));
        var Frm = "PersonasJSP";
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
                            text: resul
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

    function auditoriaReg(modo) {
        var Observacion = "";
        var NamUs = document.getElementById('usering').innerHTML
        var Id = $('#Id').val();
        var Frm = "Auditoria";
        var TipoDoc = $("#IdTipoDoc option:selected").text();
        var TipoDocOld = $('#IdTipoDocOld').val();
        var Cedula = $('#IdCedula').val();
        var CedulaOld = $('#IdCedulaOld').val();
        var Nombre = $('#IdNombre').val();
        var NombreOld = $('#IdNombreOld').val();
        var Apellido = $('#IdApellido').val();
        var ApellidoOld = $('#IdApellidoOld').val();
        var Empresa = $('#IdEmpresa').val();
        var EmpresaOld = $('#IdEmpresaOld').val();
        var CentroCosto = $('#IdCentroCosto').val();
        var CentroCostoOld = $('#IdCentroCostoOld').val();
        var Consumo = $("#IdConsume option:selected").text();
        var ConsumoOld = $('#IdConsumeOld').val();
        var GrupoConsumo = $('#IdGrupoConsumo').val();
        var GrupoConsumoOld = $('#IdGrupoConsumoOld').val();
        var Observa = $('#IdObservacion').val();
        var ObservacionOld = $('#IdObservacionOld').val();
        var Accion = "Insert";
        var Operacion;
        if (TipoDocOld === "1") {
            TipoDocOld = "Cedula de Ciudadania";
        } else if (TipoDocOld === "2") {
            TipoDocOld = "Tarjeta de Identidad";
        } else if (TipoDocOld === "3") {
            TipoDocOld = "Cedula de Extranjeria";
        }

        if (ConsumoOld === "1") {
            ConsumoOld = "No";
        } else if (ConsumoOld === "2") {
            ConsumoOld = "Si";
        }


        if (modo === "actualizar") {
            if (TipoDoc != TipoDocOld) {
                Observacion = "TipoDoc: " + TipoDocOld + " > " + TipoDoc + " ";
            }

            if (Cedula != CedulaOld) {
                Observacion += "Cedula: " + CedulaOld + " > " + Cedula + " ";
            }

            if (Nombre != NombreOld) {
                Observacion += "Nombre: " + NombreOld + " > " + Nombre + " ";
            }

            if (Apellido != ApellidoOld) {
                Observacion += "Apellido: " + ApellidoOld + " > " + Apellido + " ";
            }

            if (Empresa != EmpresaOld) {
                Observacion += "Empresa: " + EmpresaOld + " > " + Empresa + " ";
            }

            if (CentroCosto != CentroCostoOld) {
                Observacion += "CentroCosto: " + CentroCostoOld + " > " + CentroCosto + " ";
            }

            if (Consumo != ConsumoOld) {
                Observacion += "Consumo: " + ConsumoOld + " > " + Consumo + " ";
            }

            if (GrupoConsumo != GrupoConsumoOld) {
                Observacion += "GrupoConsumo: " + GrupoConsumoOld + " > " + GrupoConsumo + " ";
            }

            if (Observa != ObservacionOld) {
                Observacion += "Observacion: " + ObservacionOld + " > " + Observa;
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
            tabla: "persona",
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
                disableGif();
                LoadTabla();
                //console.log("Auditoria realizada");
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

    $("#IdEliminar").click(function (e) {
        if (ValidaCampo() === true) {

            $('#Id').val($(this).data('id'));
            var Frm = "PersonasJSP";
            var Id = $('#Id').val();
            var Accion = "Delete";
            var data = {
                frm: Frm,
                id: Id,
                accion: Accion
            };
            enableGif();
            $.ajax({
                type: "POST",
                url: "ServletAlohaTiempos",
                data: data,
                success: function (resul, textStatus, jqXHR) {

                    disableGif();
                    Swal.fire({
                        icon: 'success',
                        title: 'Eliminado',
                        text: resul
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

    function LoadTabla() {

        var Frm = "PersonasJSP";
        var Accion = "Read";
        var Modulo = "Casino";
        var data = {
            frm: Frm,
            modulo: Modulo,
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

    function enableGif() {

        window.onload = document.getElementById("espera").style = "display: block";
        window.onload = document.getElementById("Principal").style = "display: none"
    }

    function disableGif() {

        window.onload = document.getElementById("espera").style = "display: none";
        window.onload = document.getElementById("Principal").style = "display: enable"
    }
});
