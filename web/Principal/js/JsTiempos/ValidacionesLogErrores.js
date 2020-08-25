//$(function(){
//  $("#header").load("Principal/Head.html");
//  $("#script").load("Principal/Script.html");
//});

$(function(){


  $("#header").load("Principal/Head.html");
  $("#script").load("Principal/Script.html");

  /*$('#myDatepicker2').datetimepicker({

    maxDate: new Date()
  });*/

  $(document).ready(function(){

    LimpiarCampos();
    LoadTabla();
      //alert('Hola');
  });

  function LoadTabla(){

    var Frm = "LogErrorJSP";
    var FechaInicio = '';
    var FechaFin = '';
    var Accion = "Read";
    var data = {
      frm: Frm,
      fechai: FechaInicio,
      fechaf: FechaFin,
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
        //$('#datatable-responsive').html(resul);
        var datos = JSON.parse(resul);

        $('#datatableLogError').DataTable({

          data : datos,
          responsive: true,
          processing: true,
          order: [[ 1, 'desc' ]],
          columns: [
            //{"data" : {"usuario" : "login"}},
            {"data" : "usuario.login"},
            {"data" : "fecha"},
            {"data" : "error"}
         ],
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

  $('#IdVer').click(function(e){

    if (ValidaCampo() === true){

      var Frm = "LogErrorJSP";
      var FechaInicio = $('#IdFechaInicio').val();
      var FechaFin = $('#IdFechaFin').val();
      var Accion = "Read";
      if(FechaFin >= FechaInicio){

        var data = {
            frm: Frm,
            fechai: FechaInicio,
            fechaf: FechaFin,
            accion: Accion
        };
        enableGif();
        $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            dataType: 'html',
            data: data,
            success: function(resul, textStatus, jqXHR){

              disableGif();
              var datos = JSON.parse(resul);
              $('#datatableLogError').DataTable({

                data : datos,
                responsive: true,
                processing: true,
                order: [[ 1, 'desc' ]],
                columns: [
                  //{"data" : {"usuario" : "login"}},
                  {"data" : "usuario.login"},
                  {"data" : "fecha"},
                  {"data" : "error"}
               ],
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
        Swal.fire({
            icon: 'warning',
            title: 'La fecha inicial',
            text: 'No puede ser mayor que la fecha final.',
            showConfirmButton: false,
            timer: 3000
        });
      }
    }else{
      Swal.fire({
          icon: 'warning',
          title: 'Por favor seleccione',
          text: 'Fechas validas.',
          showConfirmButton: false,
          timer: 3000
      });
    }
  });

  /*function LoadTabla(){

      var Frm = "LogErrorJSP";
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
              $('#datatableLogError').html(resul);
              $('#datatableLogError').dataTable({
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
  }*/

  function traigoUserAc(){

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
              editoBotonG(usuariof);
              editoBotonE(usuariof);
              editoBotonB(usuariof);
              //return dt;
              //alert("uf metodo: " + usuariof);
          }
          else{

              alert("Ocurrio un error al traer el nombre del usuario activo.");
              location.href = "Dashboard.jsp";
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

  function ValidaCampo(){

      var res = false;
      if ($('#IdFechaInicio').val() !== ""){

        if ($('#IdFechaFin').val() !== ""){

          res = true;
        }
      }
      return res;
  }

  function  LimpiarCampos(){

    $('#IdFechaInicio').val('');
    $('#IdFechaFin').val('');

  }

  function auditoriaReg(modo){

    var Observacion = "";
    var NamUs = document.getElementById('usering').innerHTML
    var Id = $('#Id').val();

    var Frm = "Auditoria";
    var NitOld = $('#IdNitOld').val();
    var Nit = $('#IdNit').val();
    var NombreOld = $('#IdNombreOld').val();
    var Nombre = $('#IdNombre').val();
    var DirOld = $('#IdDirOld').val();
    var Direccion = $('#IdDireccion').val();
    var ContactoOld = $('#IdContactoOld').val();
    var Contacto = $('#IdContacto').val();
    var TelefonoOld = $('#IdTelefonoOld').val();
    var Telefono = $('#IdTelefono').val();
    var ExtensionOld = $('#IdExtensionOld').val();
    var Extension = $('#IdExtension').val();
    var EmailOld = $('#IdEmailOld').val();
    var Email = $('#IdEmail').val();
    var ObservacionOld = $('#IdObservacionOld').val();
    var ObservacionA = $('#IdObservacion').val();
    var Accion = "Insert";
    var Operacion;

    if (modo === "actualizar") {

      if(Nit != NitOld){
        Observacion = "Nit: "+ NitOld + " > " + Nit + " ";
      }

      if(Nombre != NombreOld){
        Observacion += "Nombre: "+ NombreOld + " > " + Nombre + " ";
      }

      if(Direccion != DirOld){
        Observacion += "Direccion: "+ DirOld + " > " + Direccion + " ";
      }

      if(Contacto != ContactoOld){
        Observacion += "Contacto: "+ ContactoOld + " > " + Contacto + " ";
      }

      if(Telefono != TelefonoOld){
        Observacion += "Telefono: "+ TelefonoOld + " > " + Telefono + " ";
      }

      if(Extension != ExtensionOld){
        Observacion += "Extension: "+ ExtensionOld + " > " + Extension + " ";
      }

      if(Email != EmailOld){
        Observacion += "Email: "+ EmailOld + " > " + Email + " ";
      }

      if(ObservacionA != ObservacionOld){
        Observacion += "Observacion: "+ ObservacionOld + " > " + ObservacionA;
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
        tabla: "empresa",
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

  function enableGif(){

      window.onload = document.getElementById("espera").style = "display: block";
      window.onload = document.getElementById("Principal").style = "display: none";
  }

  function disableGif(){

      window.onload = document.getElementById("espera").style = "display: none";
      window.onload = document.getElementById("Principal").style = "display: enable";
  }

});
