$(function(){
  $("#header").load("Principal/Head.html");
  $("#script").load("Principal/Script.html");
});

$(function(){


  $(document).ready(function() {
      cargarComboUsr();
      loadTabla();

  });

  $('#IdVer').click(function(e){

    var e = document.getElementById('IdUsuariosA');
    var strUser = e.options[e.selectedIndex].value;
    //alert(strUser);
    if(strUser != ""){
      if (ValidaCampo() === true){

        var Frm = "Auditoria";
        var FechaInicio = $('#IdFechaInicio').val();
        var FechaFin = $('#IdFechaFin').val();
        var Accion = "ReadReg";
        if(FechaFin >= FechaInicio){

          var data = {
              frm: Frm,
              usuario: strUser,
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
                  $('#datatableAu').html(resul);
                  $('#datatableAu').dataTable({
                    order: [[ 2, 'desc' ]],
                    responsive: true,
                    language: {
                        "decimal": "",
                        "emptyTable": "No hay información",
                        "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
                        "infoEmpty": "Mostrando 0 a 0 de 0 Entradas",
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
        }else{
          Swal.fire({
              icon: 'warning',
              title: 'Alerta',
              text: 'La fecha inicial no puede ser mayor que la fecha final.',
              showConfirmButton: false,
              timer: 3000
          });
        }


      }else{
        Swal.fire({
            icon: 'warning',
            title: 'Alerta',
            text: 'Por favor seleccione fechas validas.',
            showConfirmButton: false,
            timer: 3000
        });
      }

    }else{
      Swal.fire({
          icon: 'warning',
          title: 'Alerta',
          text: 'Por favor seleccione un usuario.',
          showConfirmButton: false,
          timer: 3000
      });
    }


  });

  function cargarComboUsr(){

      var Frm = "Auditoria";
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
              $('#IdUsuariosA').html(resul);

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

  function loadTabla(){

    var Frm = "Auditoria";
    var FechaInicio = "";
    var FechaFin = "";
    var Accion = "ReadReg";
    var data = {
        frm: Frm,
        usuario: "",
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
            $('#datatableAu').html(resul);
            $('#datatableAu').dataTable({
              order: [[ 2, 'desc' ]],
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
  }

  function ValidaCampo(){

    var res = false;

    if ($('#IdUsuariosA').val() !== ""){

      if ($('#IdFechaInicio').val() !== ""){

        if ($('#IdFechaFin').val() !== ""){

          res = true;
        }
      }
    }
    return res;
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
