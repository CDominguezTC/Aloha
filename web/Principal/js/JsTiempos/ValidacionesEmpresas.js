//$(function(){
//  $("#header").load("Principal/Head.html");
//  $("#script").load("Principal/Script.html");
//});

$(function(){

  
  $("#header").load("Principal/Head.html");
  $("#script").load("Principal/Script.html");

  $(document).on('click', '.editar', function() {
    alert('ho ho ho');
  });

  $(document).ready(function(){

      //alert("Hola");
      //traigoUserAc();
      LoadTabla();
      validacionBtn();

  });


  //Funcion que me permite dar clic a un registro y mandar datos a los campos
  $('#datatable-responsive tbody').on( 'click', 'tr', function () {

    var table = $('#datatable-responsive').DataTable();
    //console.log( table.row( this ).data() );

    var arr = table.row( this ).data();
    var mydata = JSON.parse(JSON.stringify(arr));


    $('#Id').val(mydata.id);
    $('#IdNitOld').val(mydata.nit);
    $('#IdNit').val(mydata.nit);
    $('#IdNombreOld').val(mydata.nombre);
    $('#IdNombre').val(mydata.nombre);
    $('#IdDirOld').val(mydata.direccion);
    $('#IdDireccion').val(mydata.direccion);
    $('#IdContactoOld').val(mydata.contacto);
    $('#IdContacto').val(mydata.contacto);
    $('#IdTelefonoOld').val(mydata.telefono);
    $('#IdTelefono').val(mydata.telefono);
    $('#IdExtensionOld').val(mydata.ext);
    $('#IdExtension').val(mydata.ext);
    $('#IdEmailOld').val(mydata.email);
    $('#IdEmail').val(mydata.email);
    $('#IdObservacionOld').val(mydata.observacion);
    $('#IdObservacion').val(mydata.observacion);    
    

  } );

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

  /*function LoadTabla(){

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
        //$('#datatable-responsive').html(resul);
        var datos = JSON.parse(resul);
        $('#datatable-responsive').dataTable({
          
          data : datos,
          responsive: true,
          order: [[ 1, 'asc' ]],
          columns: [
            {"data" : "nit"},
            {"data" : "nombre"},
            {"data" : "direccion"},
            {"data" : "contacto"},
            {"data" : "telefono"},
            {"data" : "ext"},
            {"data" : "email"}
            /*{
              data: null,
              className: "center",
              defaultContent: '<a href="#" class="editar">Editar</a> / <a href="#" class="editor_remove">Borrar</a>'
            }  */         
         /* ],              
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
    validacionBtn();
  }
  /*function LoadTabla(){
    var data = [];
    for ( var i=0 ; i<50000 ; i++ ) {
      data.push( [ i, i, i, i, i, i, i ] );
    }
     
    $('#datatable-responsive').dataTable( {
        data: data,
        deferRender: true,
        scrollY: 200,
        scrollCollapse: true,
        scroller: true,
        destroy: true
    });
  }*/
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
      validacionBtn();
  }

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

  $(document).on('click', '.SetFormulario', function(){

      $('#Id').val($(this).data('id'));
      $('#IdNitOld').val($(this).data('nit'));
      $('#IdNit').val($(this).data('nit'));
      $('#IdNombreOld').val($(this).data('nombre'));
      $('#IdNombre').val($(this).data('nombre'));
      $('#IdDirOld').val($(this).data('direccion'));
      $('#IdDireccion').val($(this).data('direccion'));
      $('#IdContactoOld').val($(this).data('contacto'));
      $('#IdContacto').val($(this).data('contacto'));
      $('#IdTelefonoOld').val($(this).data('telefono'));
      $('#IdTelefono').val($(this).data('telefono'));
      $('#IdExtensionOld').val($(this).data('extension'));
      $('#IdExtension').val($(this).data('extension'));
      $('#IdEmailOld').val($(this).data('email'));
      $('#IdEmail').val($(this).data('email'));
      $('#IdObservacionOld').val($(this).data('observacion'));
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
    $('#IdNitOld').val('');
    $('#IdNit').val('');
    $('#IdNombreOld').val('');
    $('#IdNombre').val('');
    $('#IdDirOld').val('');
    $('#IdDireccion').val('');
    $('#IdContactoOld').val('');
    $('#IdContacto').val('');
    $('#IdTelefonoOld').val('');
    $('#IdTelefono').val('');
    $('#IdExtensionOld').val('');
    $('#IdExtension').val('');
    $('#IdEmailOld').val('');
    $('#IdEmail').val('');
    $('#IdObservacionOld').val('');
    $('#IdObservacion').val('');

    document.getElementById("IdNit").focus();
  }

  $('#IdAgregar').click(function(e){

      LimpiarCampos();
  });

  $('#IdGuardar').click(function(e){

      if (ValidaCampo() === true){

        var NamUs = document.getElementById('usering').innerHTML
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
            ext: Extension,
            email: Email,
            observacion: Observacion,
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
                    text: resul
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
          alert("Favor de completar todos los campos");
      }
  });

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

  $(document).on('click', '.SetEliminar', function() {
      var Frm = "EmpresaJSP";
      var Id = $(this).data('id');
      $('#Id').val($(this).data('id'));
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

  /*function LoadTabla(){

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
  }*/

  function enableGif(){

      window.onload = document.getElementById("espera").style = "display: block";
      window.onload = document.getElementById("Principal").style = "display: none";
  }

  function disableGif(){

      window.onload = document.getElementById("espera").style = "display: none";
      window.onload = document.getElementById("Principal").style = "display: enable";
  }
});
