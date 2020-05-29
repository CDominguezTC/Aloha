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
        //LoadTabla();
        //validacionBtn();
    });

//BUSCA LA PERSONA EN LA BASE DE DATOS Y LLENA LOS CAMPOS CORRESPONDIENTES CON SUS HUELLAS E IMAGENES
    $(document).ready(function () {

        $("#IdDocumento").blur(function () {
            var Frm = "PersonasJSP";
            var Cedula = $('#IdDocumento').val();
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
                    if (resul.id !== 0) {
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
                        /*$('#Id').val('');
                         $('#IdNombre').val('');
                         $('#IdApellido').val('');
                         $('#IdEmpresa').val('0');
                         $('#IdCentroCosto').val('0');
                         $('#IdConsume').val('0');
                         $('#IdGrupoConsumo').val('0');
                         $('#IdObservacion').val('');*/
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


    //DEPENDIENDO DEL TIPO DE VISITA ASI MISMO VALIDA QUE TIPO DE INGORMACION TENDRA EN CUENTA PARA LA PERSONA
    $(document).ready(function () {

        $("#tipo_visitante").blur(function () {
            var Frm = "RegistroVisitaJSP";
            var Tipo_Visita = $('#tipo_visitante').val();
            var Accion = "Tipo_Visita";
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
                data: data,
                success: function (resul, textStatus, jqXHR)
                {
                    disableGif();
                    if (resul.id !== 0) {
                        
                        
                        
                        Swal.fire({
                            icon: 'error',
                            title: 'Advertencia',
                            text: 'Registro Guardado Satisfactoriamente.'
                        });

                    } else
                    {
                        /*$('#Id').val('');
                         $('#IdNombre').val('');
                         $('#IdApellido').val('');
                         $('#IdEmpresa').val('0');
                         $('#IdCentroCosto').val('0');
                         $('#IdConsume').val('0');
                         $('#IdGrupoConsumo').val('0');
                         $('#IdObservacion').val('');*/
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

    function tipoVisita() {


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
                    img.width = 200;
                    img.height = 200;
                    document.getElementById("FotoPersona").src = img.src;
                }
                if (imagen.numero_imagen === 30)
                {
                    $('#IdFirmaBase64').val(imagen.imagen);
                }
            }
        }
    }


});



