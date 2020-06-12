$(function(){
  $("#header").load("Principal/Head.html");
  $("#script").load("Principal/Script.html");
});

$(function(){


  $(document).ready(function() {
      LoadTabla();
      validacionBtn();
      cargarComboRol();
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

  function cargarComboRol(){

      var Frm = "PermisosJSP";
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
              $('#IdRol').html(resul);

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

    var idu = $(this).data('id');
    //alert("Id: " + idu);
    if(idu != "1"){

      $('#Id').val($(this).data('id'));
      $('#IdNombre').val($(this).data('nombre'));
      $('#IdNombreOld').val($(this).data('nombre'));
      $('#IdLogin').val($(this).data('login'));
      $('#IdLogOld').val($(this).data('login'));
      $('#IdRol').val($(this).data('rol'));
      $('#IdRolOld').val($(this).data('nrol'));

      var Frm = "Password";
      var Pw = $(this).data('password');
      var data = {
          frm: Frm,
          pass: Pw,
          accion: "Descifrar"
      };
      $.ajax({
        type: "POST",
        url: "ServletAlohaTiempos",
        data: data,
        success: function(data, textStatus, jqXHR){

          var dt = data;
          //alert("dt contra: " + dt);
          if (dt != "false"){

              $('#IdPassword').val(dt);
              $('#IdPasswordOld').val(dt);
              document.getElementById('IdPassword').disabled = true;
              document.getElementById("IdNombre").focus();

          }
          else{
            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Ocurrio un error al descifrar pw: ' + dt
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
    }else{
      Swal.fire({
          icon: 'warning',
          title: 'Alerta',
          text: 'No puede editarse el usuario de TECNO CONTROL.',
          showConfirmButton: false,
          timer: 3000
      });
    }
  });

  $(document).on('click', '.SetEliminar', function() {

      var Frm = "UsuariosJSP";
      var Id = $(this).data('id');
      $('#Id').val($(this).data('id'));
      var Accion = "Delete";

      if(Id != "1"){

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
                    success: function(resul, textStatus, jqXHR){

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
        }else{
          Swal.fire({
              icon: 'warning',
              title: 'Alerta',
              text: 'No puede borrarse el usuario de TECNO CONTROL.'
          });
        }

    });

  $('#IdCambiarPw').click(function(e){

    if (ValidaCampo() === true){
      Swal.fire({
          title: "Por favor introduzca la contraseña anterior:",
          input: "password",
          showCancelButton: true,
          confirmButtonText: "Validar",
          cancelButtonText: "Cancelar",
          inputValidator: nombre => {
              // Si el valor es válido, debes regresar undefined. Si no, una cadena
              if (!nombre) {
                  return "Por favor escribe una contraseña.";
              } else {
                  return undefined;
              }
          }
      })
      .then(resultado => {
          if (resultado.value) {
              let nombre = resultado.value;
              //console.log("Hola, " + nombre);
              validarPw(nombre);
              /*Swal.fire({
                  icon: 'success',
                  title: 'informacion',
                  text: 'Cambio correcto.',
                  showConfirmButton: false,
                  timer: 2000
              });*/
          }
      });
    }else{
      //alert("Hola Alerta");
      Swal.fire({
          icon: 'warning',
          title: 'Alerta',
          text: 'Debes seleccionar primero un usuario.'
      });
    }
    /**/

  });

  $('#IdAgregar').click(function(e){

      LimpiarCampos();
  });

  $('#IdGuardar').click(function(e){

    if (ValidaCampo() === true){
      var Frm = "Password";
      var Log = document.getElementById("IdLogin").value;
      var idUsua = document.getElementById("Id").value;
      //alert(Log);
      //var Log = $(this).data('login');

      if(idUsua != ''){

        if(document.getElementById("IdLogin").value === document.getElementById("IdLogOld").value){
          guardarRegistro();
        }else{
          var data = {
              frm: Frm,
              login: Log,
              accion: "ValidarUsr"
          };
          $.ajax({
            type: "POST",
            url: "ServletAlohaTiempos",
            data: data,
            success: function(data, textStatus, jqXHR){

              var dt = data;
              //alert("Id: " + idUsua);
              if (dt != "true"){

                  guardarRegistro();
              }
              else{

                Swal.fire({
                    icon: 'warning',
                    title: 'Alerta',
                    text: 'El login ya existe en el sistema.',
                    showConfirmButton: false,
                    timer: 3000
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

      }else{

        var data = {
            frm: Frm,
            login: Log,
            accion: "ValidarUsr"
        };
        $.ajax({
          type: "POST",
          url: "ServletAlohaTiempos",
          data: data,
          success: function(data, textStatus, jqXHR){

            var dt = data;
            //alert("Id: " + idUsua);
            if (dt != "true"){

                guardarRegistro();
            }
            else{

              Swal.fire({
                  icon: 'warning',
                  title: 'Alerta',
                  text: 'El login ya existe en el sistema.',
                  showConfirmButton: false,
                  timer: 3000
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
    }else{
      //alert("Hola Alerta");
      Swal.fire({
          icon: 'warning',
          title: 'Alerta',
          text: 'Verifica todos los campos.'
      });
    }

  });

  $("#IdCancelar").click(function(e) {

    $('#Id').val('');
    $('#IdNombre').val('');
    $('#IdLogin').val('');
    $('#IdLogOld').val('');
    $('#IdPassword').val('');
    $('#IdRol').val('');
    $('#IdRolOld').val('');
    document.getElementById('IdPassword').disabled = false;
  });

  function validarPw(contra){

    if (ValidaCampo() === true){

      var Frm = "Password";
      var IdUs = document.getElementById("Id").value;
      //var IdUs = $(this).data('id');
      //alert("IdUs: " + IdUs);
      var data = {
          frm: Frm,
          idus: IdUs,
          passw: contra,
          accion: "ValidarPw"
      };
      $.ajax({
        type: "POST",
        url: "ServletAlohaTiempos",
        data: data,
        success: function(data, textStatus, jqXHR){

          var dt = data;
          //alert("dt contra: " + dt);
          if (dt != "false"){

              actualizarPw();
          }
          else{

            Swal.fire({
                icon: 'warning',
                title: 'Alerta',
                text: 'Las contraseñas no coinciden.',
                showConfirmButton: false,
                timer: 3000
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
    }else{
      //alert("Hola Alerta");
      Swal.fire({
          icon: 'warning',
          title: 'Alerta',
          text: 'Verifica todos los campos.'
      });
    }
  }

  function actualizarPw(){

    Swal.fire({
        title: "Por favor introduzca la nueva contraseña:",
        input: "password",
        showCancelButton: true,
        confirmButtonText: "Cambiar",
        cancelButtonText: "Cancelar",
        inputValidator: nombre => {
            // Si el valor es válido, debes regresar undefined. Si no, una cadena
            if (!nombre) {
                return "Por favor escribe una contraseña.";
            } else {
                return undefined;
            }
        }
    })
    .then(resultado => {
        if (resultado.value) {
            let nombre = resultado.value;
            var Frm = "Password";
            var IdUs = document.getElementById("Id").value;
            var data = {
                frm: Frm,
                passwo: nombre,
                idusu: IdUs,
                accion: "CambiarPw"
            };
            $.ajax({
              type: "POST",
              url: "ServletAlohaTiempos",
              data: data,
              success: function(data, textStatus, jqXHR){

                var dt = data;
                //alert("dt contra: " + dt);
                if (dt != "false"){

                  Swal.fire({
                      icon: 'success',
                      title: 'Informacion',
                      text: 'La contraseña se cambio exitosamente.'
                  });
                  auditoriaReg("actualizar");
                  LimpiarCampos();
                  LoadTabla();
                }
                else{

                  Swal.fire({
                      icon: 'error',
                      title: 'Alerta',
                      text: 'No se pudo actualizar la contraseña.',
                      showConfirmButton: false,
                      timer: 3000
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
    });


  }

  function guardarRegistro(){

    if (ValidaCampo() === true){

      var NamUs = document.getElementById('usering').innerHTML
      var Frm = "UsuariosJSP";
      var Id = $('#Id').val();
      var Nombre = $('#IdNombre').val();
      var Login = $('#IdLogin').val();
      var Password = $('#IdPassword').val();
      var Rol = $('#IdRol').val();
      var Accion = "Upload";
      var data = {
          frm: Frm,
          id: Id,
          nombre: Nombre,
          login: Login,
          password: Password,
          rol: Rol,
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
              validacionBtn();
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
      //alert("Hola Alerta");
      Swal.fire({
          icon: 'warning',
          title: 'Alerta',
          text: 'Verifica todos los campos.'
      });
    }
  }

  function auditoriaReg(modo){

    var Observacion = "";
    var NamUs = document.getElementById('usering').innerHTML
    var Id = $('#Id').val();

    var Frm = "Auditoria";
    var Nombre = $('#IdNombre').val();
    var NombreOld = $('#IdNombreOld').val();
    var Login = $('#IdLogin').val();
    var LoginOld = $('#IdLogOld').val();
    var Rol = $("#IdRol option:selected").text();
    var RolOld = $('#IdRolOld').val();
    var Accion = "Insert";
    var Operacion;

    if (modo === "actualizar") {
      if(Nombre != NombreOld){
        Observacion = "Nombre: "+ NombreOld + " > " + Nombre + " ";
      }

      if(Login != LoginOld){
        Observacion += "Login: "+ LoginOld + " > " + Login;
      }

      if(Rol != RolOld){
        Observacion += "Rol: "+ RolOld + " > " + Rol;
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
        tabla: "usuario",
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

  function LimpiarCampos(){

    $('#Id').val('');
    $('#IdNombre').val('');
    $('#IdNombreOld').val('');
    $('#IdLogin').val('');
    $('#IdLogOld').val('');
    $('#IdPassword').val('');
    $('#IdPasswordOld').val('');
    $('#IdRol').val('');
    $('#IdRolOld').val('');

    document.getElementById('IdPassword').disabled = false;
    document.getElementById("IdNombre").focus();
  }

  function enableGif(){

      window.onload = document.getElementById("espera").style = "display: block";
      window.onload = document.getElementById("Principal").style = "display: none"
  }

  function disableGif(){

      window.onload = document.getElementById("espera").style = "display: none";
      window.onload = document.getElementById("Principal").style = "display: enable"
  }

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
});
