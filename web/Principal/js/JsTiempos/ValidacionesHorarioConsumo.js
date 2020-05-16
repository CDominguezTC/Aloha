$(function(){
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
    $('#IdNombreOld').val($(this).data('nombre'));
    $('#IdNombre').val($(this).data('nombre'));

    $('#IdCodigoOld').val($(this).data('codigo'));
    $('#IdCodigo').val($(this).data('codigo'));

    $('#IdHoraInicioOld').val($(this).data('horaincio'));
    $('#IdHoraInicio').val($(this).data('horaincio'));

    $('#IdHoraFinOld').val($(this).data('horafin'));
    $('#IdHoraFin').val($(this).data('horafin'));

    $('#IdNoConsumosOld').val($(this).data('cantidadconsumos'));
    $('#IdNoConsumos').val($(this).data('cantidadconsumos'));

    $('#IdTipoConsumoOld').val($(this).data('tipoconsumo'));
    $('#IdTipoConsumo').val($(this).data('tipoconsumo'));

    $('#IdLunesOld').val($(this).data('lunes'));
    $('#IdLunes').val($(this).data('lunes'));

    $('#IdMartesOld').val($(this).data('martes'));
    $('#IdMartes').val($(this).data('martes'));

    $('#IdMiercolesOld').val($(this).data('miercoles'));
    $('#IdMiercoles').val($(this).data('miercoles'));

    $('#IdJuevesOld').val($(this).data('jueves'));
    $('#IdJueves').val($(this).data('jueves'));

    $('#IdViernesOld').val($(this).data('viernes'));
    $('#IdViernes').val($(this).data('viernes'));

    $('#IdSabadoOld').val($(this).data('sabado'));
    $('#IdSabado').val($(this).data('sabado'));

    $('#IdDomingoOld').val($(this).data('domingo'));
    $('#IdDomingo').val($(this).data('domingo'));

    $('#IdFestivoOld').val($(this).data('festivo'));
    $('#IdFestivo').val($(this).data('festivo'));
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
      if ($('#IdCodigo').val() !== "")
      {
          if ($('#IdNombre').val() !== "")
          {
              res = true;
          }
      }
      return res;
  }

  function LimpiarCampos(){
        $('#Id').val('');
        $('#IdCodigo').val('');
        $('#IdNombre').val('');
        $('#IdHoraInicio').val('');
        $('#IdHoraFin').val('');
        $('#IdNoConsumos').val('');
        $('#IdTipoConsumo').val('-1');
        $('#IdLunes').val('N');
        $('#IdMartes').val('N');
        $('#IdMiercoles').val('N');
        $('#IdJueves').val('N');
        $('#IdViernes').val('N');
        $('#IdSabado').val('N');
        $('#IdDomingo').val('N');
        $('#IdFestivo').val('N');
  }

  $('#IdAgregar').click(function(e){

      LimpiarCampos();
  });

  $('#IdGuardar').click(function(e){

      if (ValidaCampo() === true){

        var NamUs = document.getElementById('usering').innerHTML
        var Frm = "HorarioConsumoJSP";
        var Id = $('#Id').val();
        var Codigo = $('#IdCodigo').val();
        var Nombre = $('#IdNombre').val();
        var HoraInicio = $('#IdHoraInicio').val();
        var HoraFin = $('#IdHoraFin').val();
        var NoConsumo = $('#IdNoConsumos').val();
        var TipoConsumo = $('#IdTipoConsumo').val();
        var Lunes = $('#IdLunes').val();
        var Martes = $('#IdMartes').val();
        var Miercoles = $('#IdMiercoles').val();
        var Jueves = $('#IdJueves').val();
        var Viernes = $('#IdViernes').val();
        var Sabado = $('#IdSabado').val();
        var Domingo = $('#IdDomingo').val();
        var Festivo = $('#IdFestivo').val();
        var Accion = "Upload";
        var data = {
            frm: Frm,
            id: Id,
            codigo: Codigo,
            nombre: Nombre,
            hora_inicio: HoraInicio,
            hora_fin: HoraFin,
            cantidad_consumo: NoConsumo,
            lunes: Lunes,
            martes: Martes,
            miercoles: Miercoles,
            jueves: Jueves,
            viernes: Viernes,
            sabado: Sabado,
            domingo: Domingo,
            festivo: Festivo,
            tipo_consumo: TipoConsumo,
            nombreU: NamUs,
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

    $('#Id').val($(this).data('id'));
    var Frm = "HorarioConsumoJSP";
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
                    text: 'Registro Eliminado Satisfactoriamente.'
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
    var Nombre = $('#IdNombre').val();
    var NombreOld = $('#IdNombreOld').val();

    var CodigoOld = $('#IdCodigoOld').val();
    var Codigo = $('#IdCodigo').val();

    var HoraInicioOld = $('#IdHoraInicioOld').val();
    var HoraInicio = $('#IdHoraInicio').val();

    var HoraFinOld = $('#IdHoraFinOld').val();
    var HoraFin = $('#IdHoraFin').val();

    var NoConsumosOld = $('#IdNoConsumosOld').val();
    var NoConsumos = $('#IdNoConsumos').val();

    var TipoConsumoOld = $('#IdTipoConsumoOld').val();
    var TipoConsumo = $('#IdTipoConsumo').val();

    var LunesOld = $('#IdLunesOld').val();
    var Lunes = $('#IdLunes').val();

    var MartesOld = $('#IdMartesOld').val();
    var Martes = $('#IdMartes').val();

    var MiercolesOld = $('#IdMiercolesOld').val();
    var Miercoles = $('#IdMiercoles').val();

    var JuevesOld = $('#IdJuevesOld').val();
    var Jueves = $('#IdJueves').val();

    var ViernesOld = $('#IdViernesOld').val();
    var Viernes = $('#IdViernes').val();

    var SabadoOld = $('#IdSabadoOld').val();
    var Sabado = $('#IdSabado').val();

    var DomingoOld = $('#IdDomingoOld').val();
    var Domingo = $('#IdDomingo').val();

    var FestivoOld = $('#IdFestivoOld').val();
    var Festivo = $('#IdFestivo').val();

    var Accion = "Insert";
    var Operacion;

    if (modo === "actualizar") {
      if(Codigo != CodigoOld){
        Observacion = "Codigo: "+ CodigoOld + " > " + Codigo + " ";
      }

      if(Nombre != NombreOld){
        Observacion += "Nombre: "+ NombreOld + " > " + Nombre + " ";
      }

      if(HoraInicio != HoraInicioOld){
        Observacion += "HoraInicio: "+ HoraInicioOld + " > " + HoraInicio + " ";
      }

      if(HoraFin != HoraFinOld){
        Observacion += "HoraFin: "+ HoraFinOld + " > " + HoraFin + " ";
      }

      if(NoConsumos != NoConsumosOld){
        Observacion += "NoConsumos: "+ NoConsumosOld + " > " + NoConsumos + " ";
      }

      if(TipoConsumo != TipoConsumoOld){
        Observacion += "TipoConsumo: "+ TipoConsumoOld + " > " + TipoConsumo + " ";
      }

      if(Lunes != LunesOld){
        Observacion += "Lunes: "+ LunesOld + " > " + Lunes + " ";
      }

      if(Martes != MartesOld){
        Observacion += "Martes: "+ MartesOld + " > " + Martes + " ";
      }

      if(Miercoles != MiercolesOld){
        Observacion += "Miercoles: "+ MiercolesOld + " > " + Miercoles + " ";
      }

      if(Jueves != JuevesOld){
        Observacion += "Jueves: "+ JuevesOld + " > " + Jueves + " ";
      }

      if(Viernes != ViernesOld){
        Observacion += "Viernes: "+ ViernesOld + " > " + Viernes + " ";
      }

      if(Sabado != SabadoOld){
        Observacion += "Sabado: "+ SabadoOld + " > " + Sabado + " ";
      }

      if(Domingo != DomingoOld){
        Observacion += "Domingo: "+ DomingoOld + " > " + Domingo + " ";
      }

      if(Festivo != FestivoOld){
        Observacion += "Festivo: "+ FestivoOld + " > " + Festivo;
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
        tabla: "horario_consumo",
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

          console.log("Auditoria realizada");
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
          var Frm = "ComercialJSP";
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
              success: function(resul, textStatus, jqXHR)
              {
                  disableGif();
                  Swal.fire({
                      icon: 'success',
                      title: 'Eliminado',
                      text: 'Registro Eliminado Satisfactoriamente.'
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

  function LoadTabla(){

      var Frm = "HorarioConsumoJSP";
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

  function enableGif(){

      window.onload = document.getElementById("espera").style = "display: block";
      window.onload = document.getElementById("Principal").style = "display: none"
  }

  function disableGif(){

        window.onload = document.getElementById("espera").style = "display: none";
        window.onload = document.getElementById("Principal").style = "display: enable"
    }
});
