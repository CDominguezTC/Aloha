$(function() {
    $("#header").load("Principal/Head.html");
    $("#script").load("Principal/Script.html");
});
$(function(){

  $(document).ready(function() {

    LoadTabla();
    validacionBtn();
  });

  $(document).on('click', '.SetFormulario', function() {

    $('#Id').val($(this).data('id'));
    $('#IdNoDispositivo').val($(this).data('nodispositivo'));
    $('#IdNoDispoOld').val($(this).data('nodispositivo'));

    $('#IdNombre').val($(this).data('nombre'));
    $('#IdNombreOld').val($(this).data('nombre'));

    $('#IdIp').val($(this).data('ip'));
    $('#IdIpOld').val($(this).data('ip'));

    $('#IdPuertoDispositivo').val($(this).data('puertodispositivo'));
    $('#IdPuertoDispoOld').val($(this).data('puertodispositivo'));

    $('#IdModo').val($(this).data('modo'));
    $('#IdModoOld').val($(this).data('modo'));

    $('#IdTipoLector').val($(this).data('tipolector'));
    $('#IdTipoLectorOld').val($(this).data('tipolector'));

    $('#IdActivo').val($(this).data('activo'));
    $('#IdActivoOld').val($(this).data('activo'));

    $('#IdSerie').val($(this).data('serie'));
    $('#IdSerieOld').val($(this).data('serie'));

    $('#IdLicencia').val($(this).data('licencia'));
    $('#IdLicenciaOld').val($(this).data('licencia'));

    $('#IdImpresora').val($(this).data('impresora'));
    $('#IdImpresoraOld').val($(this).data('impresora'));

    $('#IdEncabezadoImpresion').val($(this).data('encabezadoimpresion'));
    $('#IdEncabezadoImpresionOld').val($(this).data('encabezadoimpresion'));

    $('#IdUtilizaMenu').val($(this).data('utilizamenu'));
    $('#IdUtilizaMenuOld').val($(this).data('utilizamenu'));

    $('#IdIpControladora').val($(this).data('ipcontroladora'));
    $('#IdIpControladoraOld').val($(this).data('ipcontroladora'));

    $('#IdPuertoControladora').val($(this).data('puertocontroladora'));
    $('#IdPuertoControladoraOld').val($(this).data('puertocontroladora'));

    $('#IdEvento').val($(this).data('evento'));
    $('#IdEventoOld').val($(this).data('evento'));

    });

  function validacionBtn(){

    //alert("validacionBtn");
    var usuariof = "";
    $.ajax({
        type: "POST",
        url: "LoginServlet",
        data: "nombreU",
        success: function(data, textStatus, jqXHR){

          var dt = data;
          //alert("dt: " + dt);

          if (dt != "false"){

              usuariof = dt;
              var path = window.location.pathname;
              var page = path.split("/").pop();
              //alert("validob page: " + page);
              editoBotonG(usuariof, page);
              editoBotonE(usuariof, page);
              editoBotonB(usuariof, page);
              //return dt;
              //alert("uf metodo: " + usuariof);
          }
          else{

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

  function editoBotonG(usuariof, page) {

    var Frm = "Permisos";
    var User = usuariof;
    //alert("User guardar: " + traigoUserAc());
    //var Accion = "Empresa.Guardar";
    var Accion = page.replace('.jsp','') + ".Guardar";
    var data = {
        frm: Frm,
        user: User,
        accion: Accion
    };
    $.ajax({
      type: "POST",
      url: "ServletAlohaTiempos",
      data: data,
      success: function(data, textStatus, jqXHR){

        var dt = data;
        //alert("dt: " + dt);

        if (dt != "true"){

            $("#IdGuardar").attr("disabled", "disabled");
            //evt.preventDefault();
        }

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
    /*var botonEnviar = document.getElementById("");
    botonEnviar.disabled === true;*/
  }

  function editoBotonE(usuariof, page) {

      var Frm = "Permisos";
      var User = usuariof;
      //var Accion = "Empresa.Editar";
      var Accion = page.replace('.jsp','') + ".Editar";
      var data = {
          frm: Frm,
          user: User,
          accion: Accion
      };
      $.ajax({
        type: "POST",
        url: "ServletAlohaTiempos",
        data: data,
        success: function(data, textStatus, jqXHR){

          var dt = data;
          //alert("dt ed: " + dt);

          if (dt != "true"){

            //alert("Entro if editar");
            //$("#IdModificar").attr("disabled", "disabled");
            $(".SetFormulario").addClass("disabled").prop("disabled", true);
            //evt.preventDefault();
          }

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
      /*var botonEnviar = document.getElementById("");
      botonEnviar.disabled === true;*/
  }

  function editoBotonB(usuariof, page) {

      var Frm = "Permisos";
      var User = usuariof;
      //var Accion = "Empresa.Borrar";
      var Accion = page.replace('.jsp','') + ".Borrar";
      var data = {
          frm: Frm,
          user: User,
          accion: Accion
      };
      $.ajax({
        type: "POST",
        url: "ServletAlohaTiempos",
        data: data,
        success: function(data, textStatus, jqXHR){

          var dt = data;
          //alert("dt ed: " + dt);

          if (dt != "true"){

            //alert("Entro if editar");
            //$("#IdModificar").attr("disabled", "disabled");
            $(".SetEliminar").addClass("disabled").prop("disabled", true);
            //evt.preventDefault();
          }

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
      /*var botonEnviar = document.getElementById("");
        botonEnviar.disabled === true;*/
    }

  function ValidaCampo(){

    var res = false;
    if ($('#IdNoDispositivo').val() !== "")
    {
        if ($('#IdNombre').val() !== "")
        {
            if ($('#IdIp').val() !== "")
            {
                if ($('#IdPuertoDispositivo').val() !== "")
                {
                    res = true;
                }
            }
        }
    }
    return res;
  }

  function  LimpiarCampos(){

    $('#Id').val('');
    $('#IdNoDispositivo').val('');
    $('#IdNoDispoOld').val('');
    $('#IdNombre').val('');
    $('#IdNombreOld').val('');
    $('#IdIp').val('');
    $('#IdIpOld').val('');
    $('#IdPuertoDispositivo').val('');
    $('#IdPuertoDispoOld').val('');
    $('#IdModo').val(0);
    $('#IdModoOld').val('');
    $('#IdTipoLector').val(0);
    $('#IdTipoLectorOld').val('');
    $('#IdActivo').val(0);
    $('#IdActivoOld').val('');
    $('#IdSerie').val('');
    $('#IdSerieOld').val('');
    $('#IdLicencia').val('');
    $('#IdLicenciaOld').val('');
    $('#IdImpresora').val('');
    $('#IdImpresoraOld').val('');
    $('#IdEncabezadoImpresion').val('');
    $('#IdEncabezadoImpresionOld').val('');
    $('#IdUtilizaMenu').val(0);
    $('#IdUtilizaMenuOld').val('');
    $('#IdIpControladora').val('');
    $('#IdIpControladoraOld').val('');
    $('#IdPuertoControladora').val('');
    $('#IdPuertoControladoraOld').val('');
    $('#UtilizaMenu').val(0);
    $('#UtilizaMenuOld').val('');
    $('#IdEvento').val(0);
    $('#IdEventoOld').val('');

    document.getElementById("IdNoDispositivo").focus();
  }

  $('#IdAgregar').click(function(e)
  {
      LimpiarCampos();
  });

  $('#IdGuardar').click(function(e){

      if (ValidaCampo() === true){
        var NamUs = document.getElementById('usering').innerHTML
        var Frm = "DispositivosJSP";
        var Id = $('#Id').val();
        var NoDispositivo = $('#IdNoDispositivo').val();
        var Nombre = $('#IdNombre').val();
        var Ip = $('#IdIp').val();
        var PuertoDispositivo = $('#IdPuertoDispositivo').val();
        var Modo = $('#IdModo').val();
        var TipoLector = $('#IdTipoLector').val();
        var Activo = $('#IdActivo').val();
        var Serie = $('#IdSerie').val();
        var Licencia = $('#IdLicencia').val();
        var Impresora = $('#IdImpresora').val();
        var EncabezadoImpresion = $('#IdEncabezadoImpresion').val();
        var UtilizaMenu = $('#IdUtilizaMenu').val();
        var IpControladora = $('#IdIpControladora').val();
        var PuertoControladora = $('#IdPuertoControladora').val();
        var UtilizaMenu = $('#IdUtilizaMenu').val();
        var Evento = $('#IdEvento').val();
        var Accion = "Upload";
        var data = {
            frm: Frm,
            id: Id,
            nodispositivo: NoDispositivo,
            nombre: Nombre,
            ip: Ip,
            puertoDispositivo: PuertoDispositivo,
            modo: Modo,
            tipolector: TipoLector,
            activo: Activo,
            serie: Serie,
            licencia: Licencia,
            impresora: Impresora,
            encabezadoimpresion: EncabezadoImpresion,
            utilizamenu: UtilizaMenu,
            ipcontroladora: IpControladora,
            puertocontroladora: PuertoControladora,
            utilizamenu: UtilizaMenu,
            evento: Evento,
            nombreU: NamUs,
            accion: Accion
        };
        enableGif();
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            data: data,
            success: function(resul, textStatus, jqXHR){

              Swal.fire({
                  icon: 'success',
                  title: 'Guardado',
                  text: 'Registro Guardado Satisfactoriamente.'
              });
              if(Id != ""){
                //console.log("Ingreso a id no null " + Id + "!");
                auditoriaReg("actualizar");
              }
              disableGif();
              //alert(resul);
              LimpiarCampos();
              LoadTabla();
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
      }
      else{

        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Verifica todos los campos.'
        });
          //alert("Favor de completar todos los campos");
      }
  });

  $(document).on('click', '.SetEliminar', function() {

    $('#Id').val($(this).data('id'));
    var Frm = "DispositivosJSP";
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
                    success: function(resul, textStatus, jqXHR)
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
        }
    });
  });

  function auditoriaReg(modo){

    var Observacion = "";
    var NamUs = document.getElementById('usering').innerHTML
    var Id = $('#Id').val();

    var Frm = "Auditoria";
    var NoDispositivo = $('#IdNoDispositivo').val();
    var NoDispositivoOld = $('#IdNoDispoOld').val();

    var Nombre = $('#IdNombre').val();
    var NombreOld = $('#IdNombreOld').val();

    var Ip = $('#IdIp').val();
    var IpOld = $('#IdIpOld').val();

    var PuertoDispo = $('#IdPuertoDispositivo').val();
    var PuertoDispoOld = $('#IdPuertoDispoOld').val();

    var Modo = $("#IdModo option:selected").text();
    var ModoOld = $('#IdModoOld').val();

    var TipoLector = $("#IdTipoLector option:selected").text();
    var TipoLectorOld = $('#IdTipoLectorOld').val()

    var Activo = $("#IdActivo option:selected").text();
    var ActivoOld = $('#IdActivoOld').val();

    var Serie = $('#IdSerie').val();
    var SerieOld = $('#IdSerieOld').val();

    var Licencia = $('#IdLicencia').val();
    var LicenciaOld = $('#IdLicenciaOld').val();

    var Impresora = $('#IdImpresora').val();
    var ImpresoraOld = $('#IdImpresoraOld').val();

    var EncabezadoImpresion = $('#IdEncabezadoImpresion').val();
    var EncabezadoImpresionOld = $('#IdEncabezadoImpresionOld').val();

    var UtilizaMenu = $("#IdUtilizaMenu option:selected").text();
    var UtilizaMenuOld = $('#IdUtilizaMenuOld').val();

    var Controladora = $('#IdIpControladora').val();
    var ControladoraOld = $('#IdIpControladoraOld').val();

    var PuertoControladora = $('#IdPuertoControladora').val();
    var PuertoControladoraOld = $('#IdPuertoControladoraOld').val();

    var Evento = $("#IdEvento option:selected").text();
    var EventoOld = $('#IdEventoOld').val();

    var Accion = "Insert";
    var Operacion;

    if(ModoOld === "1"){
      ModoOld = "ENTRADA";
    }else if (ModoOld === "2") {
      ModoOld = "SALIDA";
    }

    if(TipoLectorOld === "1"){
      TipoLectorOld = "Biometrico";
    }else if (TipoLectorOld === "2") {
      TipoLectorOld = "Barras";
    }else if (TipoLectorOld === "3"){
      TipoLectorOld = "Proximidad";
    }

    if(ActivoOld === "1"){
      ActivoOld = "Si";
    }else if (ActivoOld === "2") {
      ActivoOld = "No";
    }

    if(UtilizaMenuOld === "1"){
      UtilizaMenuOld = "Si";
    }else if (ActivoOld === "2") {
      UtilizaMenuOld = "No";
    }

    if(EventoOld === "1"){
      EventoOld = "Impresora";
    }else if (EventoOld === "2") {
      EventoOld = "Torniquete";
    }

    if (modo === "actualizar") {
      if(NoDispositivo != NoDispositivoOld){
        Observacion = "NoDispositivo: "+ NoDispositivoOld + " > " + NoDispositivo + " ";
      }

      if(Nombre != NombreOld){
        Observacion += "Nombre: "+ NombreOld + " > " + Nombre + " ";
      }

      if(Ip != IpOld){
        Observacion += "Ip: "+ IpOld + " > " + Ip + " ";
      }

      if(PuertoDispo != PuertoDispoOld){
        Observacion += "PuertoDispo: "+ PuertoDispoOld + " > " + PuertoDispo + " ";
      }

      if(Modo != ModoOld){
        Observacion += "Modo: "+ ModoOld + " > " + Modo + " ";
      }

      if(TipoLector != TipoLectorOld){

        Observacion += "TipoLector: "+ TipoLectorOld + " > " + TipoLector + " ";
      }

      if(Activo != ActivoOld){
        Observacion += "Activo: "+ ActivoOld + " > " + Activo + " ";
      }

      if(Serie != SerieOld){
        Observacion += "Serie: "+ SerieOld + " > " + Serie + " ";
      }

      if(Licencia != LicenciaOld){
        Observacion += "Licencia: "+ LicenciaOld + " > " + Licencia + " ";
      }

      if(Impresora != ImpresoraOld){
        Observacion += "Impresora: "+ ImpresoraOld + " > " + Impresora + " ";
      }

      if(EncabezadoImpresion != EncabezadoImpresionOld){
        Observacion += "EncabezadoImpresion: "+ EncabezadoImpresionOld + " > " + EncabezadoImpresion + " ";
      }

      if(UtilizaMenu != UtilizaMenuOld){
        Observacion += "UtilizaMenu: "+ UtilizaMenuOld + " > " + UtilizaMenu + " ";
      }

      if(Controladora != ControladoraOld){
        Observacion += "Controladora: "+ ControladoraOld + " > " + Controladora + " ";
      }

      if(PuertoControladora != PuertoControladoraOld){
        Observacion += "PuertoControladora: "+ PuertoControladoraOld + " > " + PuertoControladora + " ";
      }

      if(Evento != EventoOld){
        Observacion += "Evento: "+ EventoOld + " > " + Evento;
      }
      Operacion = "actualizar";

    }else if (modo === "eliminar") {
      Observacion = "Se elimino el registro."
      Operacion = "eliminar";
      //console.log("Id: " + Id);
    }

    var data = {
        frm: Frm,
        operacion: Operacion,
        tabla: "dispositivo",
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
        success: function(resul, textStatus, jqXHR){

          //console.log("Auditoria realizada");
            /*Swal.fire({
                icon: 'success',
                title: 'Guardado',
                text: 'Auditoria realizada.'
            });*/

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

  }

  $("#IdEliminar").click(function(e) {
        if (ValidaCampo() === true)
        {
            var Frm = "DispositivosJSP";
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
                url: "ServletSunchemical",
                data: data,
                success: function(resul, textStatus, jqXHR)
                {
                    disableGif();
                    alert(resul);
                    LimpiarCampos();
                    LoadTabla();
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
        } else
        {
            alert("Favor de completar todos los campos");
        }

    });

  function LoadTabla(){

    var Frm = "DispositivosJSP";
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
        success: function(resul, textStatus, jqXHR)
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
            //$('#datatable').dataTable().fnDestroy();

  //                    alert(resul);
  //                    LimpiarCampos();
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
