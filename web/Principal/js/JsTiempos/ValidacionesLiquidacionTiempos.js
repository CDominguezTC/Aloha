$(function () {

    var id_persona = "";

    $(document).ready(function () {
        validoMarcaciones();
        
    });

    

    $(document).on('click', '.datoEmp', function() {
        
        var FechaIn = $('#IdFechaInicioPeriodo').val();
        var FechaFi = $('#IdFechaFinPeriodo').val();

        if(FechaIn != "" || FechaFi != ""){
            /*alert(fechain + " " + fechafi);

            var cedulap = $(this).data('ced');
            var idpersona = $(this).data('idpersona');
            alert(idpersona + " " + cedulap);*/

            var Frm = "MarcacionesJSP";
            id_persona = $(this).data('idpersona');
            var IdEmpleado = $(this).data('idpersona');
            var FechaInC = FechaIn + " 00:00:00";
            var FechaFiC = FechaFi + " 23:59:59";
            var Ver_Invalidos = "false";
            var Accion = "ReadM";
            var data = {
                frm: Frm,
                idpersona: IdEmpleado,
                fecha_inicial: FechaInC,
                fecha_final: FechaFiC,
                ver_invalidos: Ver_Invalidos,                
                accion: Accion
            };
            $.ajax({
                type: "POST",
                url: "ServletAlohaTiempos",
                dataType: 'html',
                data: data,
                success: function (resul, textStatus, jqXHR){
                
                    $('#tablemarcaciones').html(resul);
                },
                error: function (jqXHR, textStatus, errorThrown) {
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
                        Swal.fire({
                            icon: 'error',
                            title: 'Ocurrio un error',
                            text: 'En el proceso: ' + jqXHR.responseText
                        });
                        //alert('Uncaught Error: ' + jqXHR.responseText);
                    }
                }
            });

        }else{
            Swal.fire({
                icon: 'warning',
                title: 'Por favor seleccione',
                text: 'Las fechas a visualizar.'
            });
        }        
    });


    $('#IdPeriodo').on('change', function () {

        var selected = $(this).find('option:selected');
        var fechain = selected.data('fini');
        var fechafi = selected.data('ffin');
        //alert('Fecha inicio: ' + extra + " fin: " + extra2);
        $('#IdFechaInicioPeriodo').val(fechain);
        $('#IdFechaFinPeriodo').val(fechafi);
        /*var selected = $(this).find('option:selected');                
        $('#IdFechaInicioPeriodo').val(selected.data('fechainicio'));
        $('#IdFechaFinPeriodo').val(selected.data('fechafin'));*/
    });

    $(document).on('click', '.SetEmpleado', function() {
        //alert("Hola: " + $(this).data('cedula'));
    
        $('#Tipo_Id').val($(this).data('tipodoc'));
        $('#Identificacion').val($(this).data('cedula'));
        //var nombrecompleto = $(this).data('nombre') + " " + $(this).data('apellido');
        $('#Nombre').val($(this).data('nombre'));
        $('#Apellido').val($(this).data('apellido'));
        $('#codigo').val($(this).data('codigonomina'));
        $('#Cargo_Persona').val($(this).data('cargo'));
        $('#Empresa').val($(this).data('empresa'));
        $('#Dependencia').val($(this).data('dependencia'));
        $('#Area').val($(this).data('area'));
        $('#Grupo_Horario').val($(this).data('grupohorario'));
        $('#Ciudad').val($(this).data('ciudad'));
        $('#Observacion').val($(this).data('observacion'));
        //alert(nombrecompleto);
        $("#modalDetalleEmpleado").modal();
        
    });

    $(document).on('click', '.editarMar', function() {

        //alert("hola: " + $(this).data('sentidom'));        
        
        $('#IdMarcacionE').val($(this).data('idm'));
        $('#IdFechaM').val($(this).data('fecham'));
        $('#IdHoraM').val($(this).data('horam'));
        $('#IdSentidoM').val($(this).data('sentidom'));
        $('#IdObservacionM').val($(this).data('observacionm'));
        $("#IdEliminarM").prop('disabled', false);

        $("#modalEditarMarcacion").modal();

    });

    $('#tableempleados').DataTable({
        "scrollCollapse": false,
        "paging": false,
        "autoWidth": false,
        "destroy": false,
        "info": true,
        "JQueryUI": false,
        "ordering": true,
        "paging": false,
        "scrollX": false,
        "scrollY": 300,
        "searching": false
    });

    $('#tablemarcaciones').DataTable({
        "scrollCollapse": false,
        "paging": false,
        "autoWidth": false,
        "destroy": false,
        "info": true,
        "JQueryUI": false,
        "ordering": false,
        "paging": false,
        "scrollX": false,
        "scrollY": 416,
        "searching": false
    });

    $('#tableliquidacion').DataTable({
        "scrollCollapse": false,
        "paging": false,
        "autoWidth": false,
        "destroy": false,
        "info": true,
        "JQueryUI": false,
        "ordering": false,
        "paging": false,
        "scrollX": true,
        "scrollY": 416,
        "searching": false
    });

    function validoMarcaciones(){

        var Frm = "MarcacionesJSP";
        var data = {

            frm: Frm,
            accion: "validarmarcaciones"
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            data: data,
            success: function(data, textStatus, jqXHR){
                

                var dt = data;
                //alert("dt: " + dt);
                if (dt == "true"){
                    var datos = {
                        
                        frm: Frm,
                        accion: "procesarmarcaciones"
                    };
                    Swal.fire({
                        
                        title: 'Existen datos sin procedar',
                        text: "¿desea hacerlo ahora?",
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'Sí',
                        cancelButtonText: 'No'
                    }).then((result) => {
                        
                        if (result.value) {

                            //enableGif();
                            $.ajax({
                                type: "POST",
                                url: "ServletAlohaTiempos",
                                data: datos,
                                success: function(resul, textStatus, jqXHR){
                                    
                                    //alert("resul: " + resul);
                                    //disableGif();
                                    if(resul == 'true'){

                                        Swal.fire({
                                            icon: 'success',
                                            title: 'Proceso terminado',
                                            text: 'exitosamente.'
                                        }).then((result) => {
                                            
                                            $("#IdModalFiltroPersonas").modal();
                                            LoadEmpresas();
                                            LoadDependencia();
                                            LoadCentroCosto();
                                            LoadArea();
                                            LoadGrupoHorario();
                                            LoadCiudad();
                                            LoadPeriodos();
                                            
                                        });
                                        
                                    }else{
                                        Swal.fire({
                                            icon: 'error',
                                            title: 'No se pudo completar',
                                            text: 'el proceso exitosamente.'
                                        });
                                    }
                                    
                                },
                                error: function(jqXHR, textStatus, errorThrown) {
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
                        }else{
                            $("#IdModalFiltroPersonas").modal();
                            LoadEmpresas();
                            LoadDependencia();
                            LoadCentroCosto();
                            LoadArea();
                            LoadGrupoHorario();
                            LoadCiudad();
                            LoadPeriodos();
                        }
                    });                

                }else{
                    $("#IdModalFiltroPersonas").modal();
                    LoadEmpresas();
                    LoadDependencia();
                    LoadCentroCosto();
                    LoadArea();
                    LoadGrupoHorario();
                    LoadCiudad();
                    LoadPeriodos();
                }
                
              /*else{

                Swal.fire({
                    icon: 'warning',
                    title: 'Alerta',
                    text: 'El login ya existe en el sistema.',
                    showConfirmButton: false,
                    timer: 3000
                });
              }*/

            },
            error: function(jqXHR, textStatus, errorThrown) {
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

    function LoadEmpresas() {
        var Frm = "EmpresaJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdEmpresa').html(resul);
            },
            error: function (jqXHR, textStatus, errorThrown) {
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

    function LoadDependencia() {
        var Frm = "DependenciasJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdDependencia').html(resul);
            },
            error: function (jqXHR, textStatus, errorThrown) {
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

    function LoadCentroCosto() {
        var Frm = "CentroCostoJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdCentroCosto').html(resul);
            },
            error: function (jqXHR, textStatus, errorThrown) {
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

    function LoadArea() {

        var Frm = "AreasJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdArea').html(resul);
            },
            error: function (jqXHR, textStatus, errorThrown) {
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

    function LoadGrupoHorario() {
        var Frm = "GrupoHorarioJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdGrupoHorario').html(resul);
            },
            error: function (jqXHR, textStatus, errorThrown) {
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

    function LoadCiudad() {
        var Frm = "CiudadJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdCiudad').html(resul);
            },
            error: function (jqXHR, textStatus, errorThrown) {
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

    function LoadPeriodos() {
        var Frm = "PeriodosJSP";
        var Evento = "Select";
        var Accion = "Read";
        var data = {
            frm: Frm,
            evento: Evento,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdPeriodo').html(resul);
            },
            error: function (jqXHR, textStatus, errorThrown) {
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

    $('#IdAgregarMar').click(function(e){

        if(id_persona != ""){
            $("#modalEditarMarcacion").find('input,textarea,select').val('').end();
            $("#IdEliminarM").prop('disabled', true);
            $("#modalEditarMarcacion").modal();
        }else{
            Swal.fire({
                icon: 'warning',
                title: 'Por favor seleccione',
                text: 'Un empleado.'
            });
        }
    });

    $('#IdEliminarM').click(function(e){
        

        //alert("Eliminar: " + $('#IdMarcacionE').val());
        var FechaIn = $('#IdFechaInicioPeriodo').val();
        var FechaFi = $('#IdFechaFinPeriodo').val();
        var Frm = "MarcacionesJSP";
        var IdMarcacion = $('#IdMarcacionE').val();
        var IdEmpleado = id_persona;
        var FechaInC = FechaIn + " 00:00:00";
        var FechaFiC = FechaFi + " 23:59:59";
        var Ver_Invalidos = "false";
        var Accion = "Delete";
        var data = {
            frm: Frm,
            idmarcacion: IdMarcacion,
            idpersona: IdEmpleado,
            fecha_inicial: FechaInC,
            fecha_final: FechaFiC,
            ver_invalidos: Ver_Invalidos,              
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR){
            
                var dt = resul;
                //alert("dt contra: " + resul);
                if (dt != "false"){
                    Swal.fire({
                      icon: 'success',
                      title: 'La marcacion',
                      text: 'Se elimino exitosamente.'
                    }).then((result) => {

                        $("#modalEditarMarcacion").modal('hide');
                        $('#tablemarcaciones').html(resul);
                        
                        
                    });


                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
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
                    Swal.fire({
                        icon: 'error',
                        title: 'Ocurrio un error',
                        text: 'En el proceso: ' + jqXHR.responseText
                    });
                    //alert('Uncaught Error: ' + jqXHR.responseText);
                }
            }
        });

    });

    $('#IdGuardarM').click(function(e){
        
        
        var Id = $('#IdMarcacionE').val();
        //alert("Guardar IdMar: " + Id);
        var PersonaId = id_persona;
        var FechaIn = $('#IdFechaInicioPeriodo').val();
        var FechaFi = $('#IdFechaFinPeriodo').val();
        var FechaInC = FechaIn + " 00:00:00";
        var FechaFiC = FechaFi + " 23:59:59";
        var Ver_Invalidos = "false";

        var FechaM = $('#IdFechaM').val();
        var HoraM = $('#IdHoraM').val();
        var FechaCompleta = FechaM + " " + HoraM;
        var SentidoM = $('#IdSentidoM').val();
        var ObservacionP = $('#IdObservacionM').val();
        var Frm = "MarcacionesJSP";
        var Accion = "Insert";

        var data = {
            frm: Frm,
            id: Id,           
            idpersona: PersonaId,
            fecharegistro: FechaCompleta,
            estadomarcacion: SentidoM,
            observacion: ObservacionP,
            fecha_inicial: FechaInC,
            fecha_final: FechaFiC,
            ver_invalidos: Ver_Invalidos,                        
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR){
            
                var dt = resul;
                //alert("dt: " + resul);
                if (dt != "false"){
                    Swal.fire({
                      icon: 'success',
                      title: 'La marcacion',
                      text: 'Se guardo exitosamente.'
                    }).then((result) => {

                        $("#modalEditarMarcacion").modal('hide');
                        $('#tablemarcaciones').html(resul);
                        
                        
                    });

                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
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
                    Swal.fire({
                        icon: 'error',
                        title: 'Ocurrio un error',
                        text: 'En el proceso: ' + jqXHR.responseText
                    });
                    //alert('Uncaught Error: ' + jqXHR.responseText);
                }
            }
        });

    });

    $('#IdSeleccionarMa').click(function(e){
        
        $("#IdModalFiltroPersonas").modal();

    });

    $('#IdSeleccionarLi').click(function(e){
        
        $("#IdModalFiltroPersonas").modal();

    });

    $('#IdFiltrarEmpleados').click(function (e){
    
        var Frm = "PersonasJSP";
        var IdEmpresa = $('#IdEmpresa').val();
        var IdDependencia = $('#IdDependencia').val();
        var IdCentroCosto = $('#IdCentroCosto').val();
        var IdArea = $('#IdArea').val();
        var IdGrupoHorario = $('#IdGrupoHorario').val();
        var IdCiudad = $('#IdCiudad').val();
        var Evento = "SelectPersonasLiquidacionTiempos";
        var Modulo = "Tiempos";
        var Accion = "Read";
        var data = {
            frm: Frm,
            idempresa: IdEmpresa,
            idempresa: IdDependencia,
            idcentrocosto: IdCentroCosto,
            idarea: IdArea,
            idgrupohorario: IdGrupoHorario,
            idciudad: IdCiudad,
            evento: Evento,
            modulo: Modulo,
            accion: Accion
        };
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                $('#IdTablaEmpleados').html(resul);
            },
            error: function (jqXHR, textStatus, errorThrown) {
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