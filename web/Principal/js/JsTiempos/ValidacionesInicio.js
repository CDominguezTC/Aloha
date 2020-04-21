/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 $(function(){
   $("#header").load("Principal/Head.html");
   $("#script").load("Principal/Script.html");
 });

$(function (){

    $('#IdIngresar').click(function (e)
    {
        if ($('#Idusuario').val() !== "")
        {
            if ($('#Idpassword').val() !== "")
            {
                var Frm = "IndexJSP";
                var User = $('#Idusuario').val();
                var Pass = $('#Idpassword').val();
                var Accion = "Login";
                var data = {
                    frm: Frm,
                    user: User,
                    pass: Pass,
                    accion: Accion
                };
                $.ajax({
                    type: "POST",
                    url: "ServletAlohaTiempos",
                    data: data,
                    success: function (data, textStatus, jqXHR)
                    {
                        var dt = data;
                        //alert("dt: " + dt);
                        if (dt === "true")
                        {
                            location.href = "Dashboard.jsp";
                            //evt.preventDefault();
                        } else
                        {
                            //alert("Por favor verifique el usuario y la contraseña");
                            Swal.fire({
                                icon: 'warning',
                                title: 'Alerta',
                                text: 'Por favor verifique el usuario y la contraseña.'
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
            } else{

              Swal.fire({
                  icon: 'warning',
                  title: 'Alerta',
                  text: 'Ingrese el password.',
                  showConfirmButton: false,
                  timer: 3000
              });
            }
        } else{

          Swal.fire({
              icon: 'warning',
              title: 'Alerta',
              text: 'Ingrese el usuario.',
              showConfirmButton: false,
              timer: 3000
          });

        }
    });

    /*$('#IdIngresar').click(function(e){

  		var namUs = "tecno";

  		Swal.fire({
  				title: "Por favor introduzca la nueva contraseña:",
  				input: "password",
  				showCancelButton: true,
  				confirmButtonText: "Cambiar",
  				cancelButtonText: "Cancelar",
  				confirmButtonColor: '#3085d6',
  				cancelButtonColor: '#d33',
  				inputValidator: nombre => {
  						// Si el valor es válido, debes regresar undefined. Si no, una cadena
  						if (!nombre) {
  								return "Por favor escribe una contraseña.";
  						} else {
  								return undefined;
  						}
  				}
  		}).then(resultado => {

  				if (resultado.value) {

  						let nombre = resultado.value;
  						var Frm = "Password";
  						var data = {
  								frm: Frm,
  								usuario: namUs,
  								npass: nombre,
  								accion: "CambiarPwUs"
  						};
  						$.ajax({
  							type: "POST",
  							url: "ServletAlohaTiempos",
  							data: data,
  							success: function(data, textStatus, jqXHR){

  								var dt = data;

  								if (dt === "true"){
  									Swal.fire({
  											icon: 'success',
  											title: 'Se cambio la contraseña exitosamente.',
  											text: 'Por favor cierre sesion e ingrese de nuevo.'
  									});
  								}else{
  									Swal.fire({
  											icon: 'warning',
  											title: 'Ocurrio un error al actualizar la contraseña.',
  											showConfirmButton: false,
  			                timer: 3000
  									})
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

  		/*Swal.fire({
  				icon: 'warning',
  				title: 'Alerta',
  				text: 'Ocurrio un error al traer el nombre del usuario activo.'
  		}).then((result) => {
  			if (result.value) {
  				location.href = "Dashboard.jsp";
  			}
  		});*/
  	});*/

});
