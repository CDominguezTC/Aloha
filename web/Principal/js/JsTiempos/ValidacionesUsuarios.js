$(function(){
  $("#header").load("Principal/Head.html");
  $("#script").load("Principal/Script.html");
});

$(function(){

    $(document).ready(function() {
        LoadTabla();
    });

    function LoadTabla(){

        var Frm = "UsuariosJSP";
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

    $(document).on('click', '.SetFormulario', function() {
        $('#Id').val($(this).data('id'));
        $('#IdNombre').val($(this).data('nombre'));
        $('#IdLogin').val($(this).data('login'));
        $('#IdPassword').val($(this).data('password'));
    });

    $(document).on('click', '.SetEliminar', function() {

        var Frm = "UsuariosJSP";
        var Id = $(this).data('id');
        var Nombre = $(this).data('nombre');
        var Login = $(this).data('login');
        var Password = $(this).data('password');
        var Accion = "Delete";
        var data = {
            frm: Frm,
            id: Id,
            nombre: Nombre,
            login: Login,
            password: Password,
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

    $('#IdAgregar').click(function(e){

        LimpiarCampos();
    });

    $('#IdGuardar').click(function(e){

        if (ValidaCampo() === true){

          var Frm = "UsuariosJSP";
          var Id = $('#Id').val();
          var Nombre = $('#IdNombre').val();
          var Login = $('#IdLogin').val();
          var Password = $('#IdPassword').val();
          var Accion = "Upload";
          var data = {
              frm: Frm,
              id: Id,
              nombre: Nombre,
              login: Login,
              password: Password,
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
        }
    });

    $("#IdEliminar").click(function(e) {
          if (ValidaCampo() === true){

              var Frm = "UsuariosJSP";
              var Id = $('#Id').val();
              var Nombre = $('#IdNombre').val();
              var Login = $('#IdLogin').val();
              var Password = $('#IdPassword').val();
              var Accion = "Delete";
              var data = {
                  frm: Frm,
                  id: Id,
                  nombre: Nombre,
                  login: Login,
                  password: Password,
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

    function ValidaCampo(){

        var res = false;
        if ($('#IdNombre').val() !== ""){

            if ($('#IdLogin').val() !== ""){

              if ($('#IdPassword').val() !== ""){

                  res = true;
              }

            }
        }
        return res;
    }

    function  LimpiarCampos(){

        $('#Id').val('');
        $('#IdNombre').val('');
        $('#IdLogin').val('');
        $('#IdPassword').val('');
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
