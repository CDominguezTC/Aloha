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
                        //$('#Id').val(resul.id);
                        $('#IdTipoDoc').val(resul.tipoIdentificacion);
                        $('#IdDocumento').val(resul.identificacion);
                        $('#IdNombre').val(resul.nombres + ' ' + resul.apellidos);
                        /*$('#IdApellido').val(resul.apellidos);
                         $('#IdEmpresa').val(resul.modeloEmpresa.id);
                         $('#IdCentroCosto').val(resul.modeloCentroCosto.id);
                         $('#IdConsume').val(resul.consumocasino);
                         $('#IdGrupoConsumo').val(resul.modeloGrupoConsumo.id);
                         $('#IdObservacion').val(resul.observaciones); */
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

    function enableGif() {

        window.onload = document.getElementById("espera").style = "display: block";
        window.onload = document.getElementById("Principal").style = "display: none";
    }

    function disableGif() {

        window.onload = document.getElementById("espera").style = "display: none";
        window.onload = document.getElementById("Principal").style = "display: enable";
    }


});



