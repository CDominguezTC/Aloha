$(function () {
    $("#header").load("Principal/Head.html");
    $("#script").load("Principal/Script.html");
});

$(function (){

  $(document).ready(function () {
      LoadTabla();
      validacionBtn();
  });

  $(document).ready(function (){

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
                  if (resul.id !== 0) {
                      $('#Id').val(resul.id);
                      $('#IdTipoDoc').val(resul.tipoIdentificacion);
                      $('#IdCedula').val(resul.identificacion);
                      $('#IdNombre').val(resul.nombres);
                      $('#IdApellido').val(resul.apellidos);
                      $('#IdEmpresa').val(resul.modeloEmpresa.id);
                      $('#IdCentroCosto').val(resul.modeloCentroCosto.id);
                      $('#IdConsume').val(resul.consumocasino);
                      $('#IdGrupoConsumo').val(resul.modeloGrupoConsumo.id);
                      $('#IdObservacion').val(resul.observaciones);
                  }else
                  {
                      $('#Id').val('');
                      $('#IdNombre').val('');
                      $('#IdApellido').val('');
                      $('#IdEmpresa').val('0');
                      $('#IdCentroCosto').val('0');
                      $('#IdConsume').val('0');
                      $('#IdGrupoConsumo').val('0');
                      $('#IdObservacion').val('');
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
      $('#Id').val($(this).data('id'));
      $('#IdTipoDoc').val($(this).data('tipodoc'));
      $('#IdCedula').val($(this).data('cedula'));
      $('#IdNombre').val($(this).data('nombre'));
      $('#IdApellido').val($(this).data('apellido'));
      $('#IdEmpresa').val($(this).data('empresa'));
      $('#IdCentroCosto').val($(this).data('centrocosto'));
      $('#IdConsume').val($(this).data('consume'));
      $('#IdGrupoConsumo').val($(this).data('grupoconsumo'));
      $('#IdObservacion').val($(this).data('observacion'));
  });

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

  function ValidaCampo(){

      var res = false;
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
      return res;
  }

  function  LimpiarCampos(){

      $('#Id').val('');
      $('#IdTipoDoc').val(0);
      $('#IdCedula').val('');
      $('#IdNombre').val('');
      $('#IdApellido').val('');
      $('#IdEmpresa').val(0);
      $('#IdCentroCosto').val(0);
      $('#IdConsume').val(0);
      $('#IdGrupoConsumo').val(0);
      $('#IdObservacion').val('');
  }

  $('#IdAgregar').click(function (e){

      LimpiarCampos();
  });

  $('#IdGuardar').click(function (e){

      if (ValidaCampo() === true)
      {
          var Frm = "PersonasJSP";
          var Id = $('#Id').val();
          var TipoDoc = $('#IdTipoDoc').val();
          var Cedula = $('#IdCedula').val();
          var Nombre = $('#IdNombre').val();
          var Apellido = $('#IdApellido').val();
          var Empresa = $('#IdEmpresa').val();
          var CentroCosto = $('#IdCentroCosto').val();
          var Consumo = $('#IdConsume').val();
          var GrupoConsumo = $('#IdGrupoConsumo').val();
          var Observacion = $('#IdObservacion').val();
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
              centrocosto: CentroCosto,
              consumo: Consumo,
              grupoconsumo: GrupoConsumo,
              observacion: Observacion,
              accion: Accion,
              modulo: Modulo
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

  $('#IdIniServicio').click(function (e){

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

  $('#IdFinServicio').click(function (e){

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

  $("#IdEliminar").click(function (e) {
      if (ValidaCampo() === true)
      {
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
              success: function (resul, textStatus, jqXHR)
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

  function LoadTabla()
  {
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
//                $('#datatable').dataTable({
//                    responsive: true,
//                    language: {
//                        "decimal": "",
//                        "emptyTable": "No hay información",
//                        "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
//                        "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
//                        "infoFiltered": "(Filtrado de _MAX_ total entradas)",
//                        "infoPostFix": "",
//                        "thousands": ",",
//                        "lengthMenu": "Mostrar _MENU_ Entradas",
//                        "loadingRecords": "Cargando...",
//                        "processing": "Procesando...",
//                        "search": "Buscar:",
//                        "zeroRecords": "Sin resultados encontrados",
//                        "paginate": {
//                            "first": "Primero",
//                            "last": "Ultimo",
//                            "next": "Siguiente",
//                            "previous": "Anterior"
//                        }
//                    }
//                    , "autoWidth": false
//                    , "destroy": true
//                    , "info": true
//                    , "JQueryUI": true
//                    , "ordering": true
//                    , "paging": true
//                    , "scrollY": "500px"
//                    , "scrollCollapse": true
//
//                });
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

  function enableGif(){

      window.onload = document.getElementById("espera").style = "display: block";
      window.onload = document.getElementById("Principal").style = "display: none"
  }

  function disableGif(){

      window.onload = document.getElementById("espera").style = "display: none";
      window.onload = document.getElementById("Principal").style = "display: enable"
  }
});
