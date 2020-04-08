$(function () {

    $(document).on('ready', funcionprincipal);

    function  funcionprincipal()
    {
        $("#btnNuevo").on('click', fucnionNuevoAlineamineto);
        $("body").on('click', ".btn-danger", funcionEliminarFila);
        $("#btnAgregar").on('click', funcionAgregarHerramienta);
        //$("#IdAplicarAccion").on('click',fucionEjecutarAccion);
    }

    $('#IdbtnDelete').click(function (e)
    {
        var resConfir = confirm("Desea eliminar los items selecionados");
        //$('#Tabla input[type=checkbox]:not(:checked').parents('tr').remove();
        $('#Tabla input[type=checkbox]:checked').parents('tr').remove();
        if (resConfir)
        {
            $('#IdAccion').val("DeletePreCarga");
            var data = $('#IdAlohaHerramientasJSP').serialize();
            $.post("InsertarHerramienta", data, function (res, est, jqHXR)
            {
                console.log(res);
                alert(res);
                location.href = "dashboard.jsp";
                evt.preventDefault();
            });
        } else
        {
            alert("Accion Cancelada");
        }
    });

    $('#IdAplicarAccion').click(function (e)
    {
//        $('#Tabla input[type=checkbox]:not(:checked').parents('tr').remove();
//        var ValCampo = $('#IdEmpleado').val();
//        if (ValidaCampo(ValCampo))
//        {
            $('#Idfrmm').val('CargosJSP');
            $('#IdAccion').val('Upload');            
            var resConfir = confirm("Desea aplicar la accicon");
            if (resConfir)
            {
                var data = $('#IdRegistroCargosJSP').serialize();
                console.log(data);
                $.post("ServletAlohaTiempos", data, function (res, est, jqHXR)
                {
                    alert(res);
                    location.href = "RegistroCargos.jsp";
                    //evt.preventDefault();
                });
            } else
            {
                alert("Accion Cancelada");
            }
//        }
    });

    function soloNumeros(e)
    {
        var key = window.event ? window.event.keyCode : e.which;
        //alert(key);
        var teclado = String.fromCharCode(key);
        //alert(teclado);
        var numeros = "0123456789";
        var especiales = "0-8-37-38-46-13";
        var teclado_especial = false;
        for (var i in especiales)
        {
            if (key == especiales[i])
            {
                teclado_especial = true;
            }
        }
        if (numeros.indexOf(teclado) == -1 && !teclado_especial)
        {
            return false;
        }
    }

//function fucionEjecutarAccion()
//{
//    alert("hola accion");
//    var data = $('IdAlohaHerramientasJSP').serialize();
//    console.log(data);
//}

    function funcionAgregarHerramienta(val, text)
    {
        var ValCampo = $('#IdHerramientaRetirar').val();
        var datos = ValCampo.split(',');
        //var id = datos[0];
        var Cantidad = datos[1];
        //alert("Cantidad " + Cantidad)
        //var Nombre = datos[2];
        //alert($('#IdNumeroItems').val());
        var NumeroItems = $('#IdNumeroItems').val();
        //alert("Numero Iems " +NumeroItems);    
        if (NumeroItems != "")
        {
            if (parseInt(Cantidad) >= parseInt(NumeroItems))
            {
                $("#Tabla")
                        .append
                        (
                                $('<tr>')
                                .append
                                (
                                        $('<td>')
                                        .append
                                        (
                                                $('<input type="text" placeholder="Id" class="form-control Id" id="Id" name="Id" value="' + datos[0] + '"/>')
                                                )
                                        )
                                .append
                                (
                                        $('<td>')
                                        .append
                                        (
                                                $('<input type="text" placeholder="Saldo" class="form-control Id" id="Saldo" name="Saldo" value="' + datos[1] + '"/>')
                                                )
                                        )
                                .append
                                (
                                        $('<td>')
                                        .append
                                        (
                                                $('<input type="text" placeholder="Descripcion" class="form-control Descripcion" id="NirCon" name="Descripcion" value="' + datos[2] + '"/>')
                                                )
                                        )
                                .append
                                (
                                        $('<td>')
                                        .append
                                        (
                                                $('<input type="number" placeholder="Cantidad" class="form-control Cantidad" id="Saldo" name="Cantidad" value="' + $('#IdNumeroItems').val() + '" onkeypress="return soloNumeros(event)"/>')
                                                )
                                        )
                                //.append
                                //(
                                //$('<td>').addClass('text-center')
                                // .append
                                //(
                                //        $('<div class="btn btn-danger" id="btnEliminar">Eliminar</div>')
                                //        )
                                // )
                                );
                $('input[id="IdNumeroItems"]').val('');
            } else
            {
                alert("El Numero de items a retirar es mayor al saldo en el sistema , Saldo : " + Cantidad);
            }

        } else
        {
            alert("Favor de completar la informacion de los campo ")
        }

    }

    function funcionEliminarFila()
    {
        $(this).parent().parent().fadeOut("slow", function () {
            $(this).remove()
        });
    }

    function fucnionNuevoAlineamineto()
    {
        tabla = $('#Tabla');
        tr = $('tr:first', tabla);
        tr.clone().appendTo(tabla).find(':text').val('');

//        $("#Tabla")
//                .append
//                (
//                        $('<tr>')
//                        .append
//                        (
//                                $('<td>')
//                                .append
//                                (
//                                        $('<input type="checkbox" class="custom-control-input"  id="IdSelect" name="Select" value=""/>')
//                                        )
//                                )
//                        .append
//                        (
//                                $('<td>')
//                                .append
//                                (
//                                        $('<div class="col-md-12 col-sm-12 col-xs-12 form-group"><label for="tipo_id">Consume Casino</label><select id="IdConsume" class="form-control" required><option value="0" selected disabled>Seleccione</option><%LinkedList<ModeloCargos> modeloCargos;ControladorCargos controladorCargos = new ControladorCargos ();modeloCargos = controladorCargos.getModelo ();for (ModeloCargos modelo : modeloCargos){%><option value="<%=modelo.getId ()%>"><%=modelo.getTipoCargo ()%></option><%}%></select></div>')
//                                        )
//                                )
//                        //.append
//                        //(
//                        // $('<td>').addClass('text-center')
//                        // .append
//                        // (
//                        // $('<div class="btn btn-danger" id="btnEliminar">Eliminar</div>')
//                        //  )
//                        //)
//                        );
    }
});
