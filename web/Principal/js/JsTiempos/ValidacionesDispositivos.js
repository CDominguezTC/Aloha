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
        $('#IdNombre').val($(this).data('nombre'));
        $('#IdIp').val($(this).data('ip'));
        $('#IdPuertoDispositivo').val($(this).data('puertodispositivo'));
        $('#IdModo').val($(this).data('modo'));
        $('#IdTipoLector').val($(this).data('tipolector'));
        $('#IdActivo').val($(this).data('activo'));
        $('#IdSerie').val($(this).data('serie'));
        $('#IdLicencia').val($(this).data('licencia'));
        $('#IdImpresora').val($(this).data('impresora'));
        $('#IdEncabezadoImpresion').val($(this).data('encabezadoimpresion'));
        $('#IdUtilizaMenu').val($(this).data('utilizamenu'));
        $('#IdIpControladora').val($(this).data('ipcontroladora'));
        $('#IdPuertoControladora').val($(this).data('puertocontroladora'));
        $('#IdEvento').val($(this).data('evento'));
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

  function ValidaCampo()
  {
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

  function  LimpiarCampos()
  {
      $('#Id').val('');
      $('#IdNoDispositivo').val('');
      $('#IdNombre').val('');
      $('#IdIp').val('');
      $('#IdPuertoDispositivo').val('');
      $('#IdModo').val(0);
      $('#IdTipoLector').val(0);
      $('#IdActivo').val(0);
      $('#IdSerie').val('');
      $('#IdLicencia').val('');
      $('#IdImpresora').val('');
      $('#IdEncabezadoImpresion').val('');
      $('#IdUtilizaMenu').val(0);
      $('#IdIpControladora').val('');
      $('#IdPuertoControladora').val('');
      $('#UtilizaMenu').val(0);
      $('#IdEvento').val(0);
  }

  $('#IdAgregar').click(function(e)
  {
      LimpiarCampos();
  });

  $('#IdGuardar').click(function(e)
  {
      if (ValidaCampo() === true)
      {
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
              accion: Accion
          };
          enableGif();
          $.ajax({
              type: "POST",
              url: "ServletAlohaTiempos",
              data: data,
              success: function(resul, textStatus, jqXHR)
              {
                  Swal.fire({
                      icon: 'success',
                      title: 'Guardado',
                      text: 'Registro Guardado Satisfactoriamente.'
                  });
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
      else
      {
          Swal.fire({
              icon: 'error',
              title: 'Error',
              text: 'Verifica todos los campos.'
          });
          //alert("Favor de completar todos los campos");
      }
  });

  $(document).on('click', '.SetEliminar', function() {
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
