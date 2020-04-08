//$(function(){
//  $("#header").load("Principal/Head.html");
//  $("#script").load("Principal/Script.html");
//});

$(function(){


  $("#header").load("Principal/Head.html");
  $("#script").load("Principal/Script.html");

  $(document).ready(function(){

      //alert("Hola");
    traigoUserAc();
    LoadTabla();
  });

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

  function editoBotonG(usuariof) {

    var Frm = "Permisos";
    var User = usuariof;
    //alert("User guardar: " + traigoUserAc());
    var Accion = "Empresa.Guardar";
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

  function editoBotonE(usuariof) {

      var Frm = "Permisos";
  		var User = usuariof;
  		var Accion = "Empresa.Editar";
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

  function editoBotonB(usuariof) {

    var Frm = "Permisos";
		var User = usuariof;
		var Accion = "Empresa.Borrar";
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

  $(document).on('click', '.SetFormulario', function()
  {
      $('#Id').val($(this).data('id'));
      $('#IdNit').val($(this).data('nit'));
      $('#IdNombre').val($(this).data('nombre'));
      $('#IdDireccion').val($(this).data('direccion'));
      $('#IdContacto').val($(this).data('contacto'));
      $('#IdTelefono').val($(this).data('telefono'));
      $('#IdExtension').val($(this).data('extension'));
      $('#IdEmail').val($(this).data('email'));
      $('#IdObservacion').val($(this).data('observacion'));
  });

  function ValidaCampo(){

      var res = false;
      if ($('#IdNit').val() !== "")
      {
          if ($('#IdNombre').val() !== "")
          {
              res = true;
          }
      }
      return res;
  }

  function  LimpiarCampos(){

      $('#Id').val('');
      $('#IdNit').val('');
      $('#IdNombre').val('');
      $('#IdDireccion').val('');
      $('#IdContacto').val('');
      $('#IdTelefono').val('');
      $('#IdExtension').val('');
      $('#IdEmail').val('');
      $('#IdObservacion').val('');
  }

  $('#IdAgregar').click(function(e){

      LimpiarCampos();
  });

  $('#IdGuardar').click(function(e){

      if (ValidaCampo() === true)
      {
          var Frm = "EmpresaJSP";
          var Id = $('#Id').val();
          var Nit = $('#IdNit').val();
          var Nombre = $('#IdNombre').val();
          var Direccion = $('#IdDireccion').val();
          var Contacto = $('#IdContacto').val();
          var Telefono = $('#IdTelefono').val();
          var Extension = $('#IdExtension').val();
          var Email = $('#IdEmail').val();
          var Observacion = $('#IdObservacion').val();
          var Accion = "Upload";
          var data = {
              frm: Frm,
              id: Id,
              nit: Nit,
              nombre: Nombre,
              direccion: Direccion,
              contacto: Contacto,
              telefono: Telefono,
              extension: Extension,
              email: Email,
              observacion: Observacion,
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
                      text: resul
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
          alert("Favor de completar todos los campos");
      }
  });

  $(document).on('click', '.SetEliminar', function() {
      var Frm = "EmpresaJSP";
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
          var Frm = "EmpresaJSP";
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

      var Frm = "EmpresaJSP";
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
  }

  function LoadTabla(){

      var Frm = "EmpresaJSP";
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
              $('#datatable-responsive').html(resul);
              $('#datatable-responsive').dataTable({
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

  function enableGif(){

      window.onload = document.getElementById("espera").style = "display: block";
      window.onload = document.getElementById("Principal").style = "display: none";
  }

  function disableGif(){

      window.onload = document.getElementById("espera").style = "display: none";
      window.onload = document.getElementById("Principal").style = "display: enable";
  }

});
