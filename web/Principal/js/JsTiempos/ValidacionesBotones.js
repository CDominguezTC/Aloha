//$(function(){
//  $("#header").load("Principal/Head.html");
//  $("#script").load("Principal/Script.html");
//});

$(function(){


  //$("#header").load("Principal/Head.html");
  //$("#script").load("Principal/Script.html");

  $(document).ready(function(){

    alert("Hola!");
    traigoUserAc();
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
