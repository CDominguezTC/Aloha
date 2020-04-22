$(function(){
	$("#header").load("Principal/Head.html");
	$("#script").load("Principal/Script.html");
});

$(function(){

	function validoPermiso(perm, modu){
		var usrac = $("#idusera").text();

		var Frm = "Permisos";
		var User = usrac;
		var Accion = perm;
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

				if (dt === "true"){

						location.href = modu;
						//evt.preventDefault();
				}
				else{

						//alert("No tiene permisos para abrir el modulo de " + modu.replace('.jsp',''));
						Swal.fire({
								icon: 'warning',
								title: 'Alerta',
								text: 'No tiene permisos para abrir el modulo de ' + modu.replace('.jsp','')
						});
						//location.href = "index.jsp";
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
        
    //Registrar Visita
    $('#idregisvisita').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("RegistrarVisita.Abrir", "RegistrarVisita.jsp");

    });
         

    $('#idempre').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("Empresa.Abrir", "Empresa.jsp");

    });
    
    $('#idaudito').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("Auditoria.Abrir", "Auditoria.jsp");

    });

    $('#idusers').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("Usuarios.Abrir", "Usuarios.jsp");

    });

    $('#idpermi').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("Permisos.Abrir", "Permisos.jsp");

    });

    $('#idccosto').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("CentroCostos.Abrir", "CentroCostos.jsp");

    });

    $('#iddispo').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("Dispositivo.Abrir", "Dispositivo.jsp");

    });

    $('#idfest').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("Festivos.Abrir", "Festivos.jsp");
    });

    $('#idtipoc').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("TipoConsumo.Abrir", "TipoConsumo.jsp");
    });

    $('#idhconsu').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("HorarioConsumo.Abrir", "HorarioConsumo.jsp");
    });

    $('#idgconsu').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("GrupoConsumo.Abrir", "GrupoConsumo.jsp");
    });

    $('#idagconsu').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("AsocGrupoConsumo.Abrir", "AsocGrupoConsumo.jsp");
    });

    $('#idperso').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("PersonasCasino.Abrir", "PersonasCasino.jsp");
    });

    $('#idlcasin').click(function (e) {

        //alert("HolaEmpresa");
        validoPermiso("LiquidacionCasino.Abrir", "LiquidacionCasino.jsp");
    });

	$('#idcambiarpw').click(function(e){

		var namUs = document.getElementById('usering').innerHTML;

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
	});

	$(document).ready(function(){

		editoNomU();

		/*function editoBot() {

				var botonEnviar = document.getElementById("idsave");
				botonEnviar.disabled === true;
		}*/
	});

	function editoNomU() {
		//alert("HolaIn");
		/**/
		$.ajax({
			type: "POST",
			url: "LoginServlet",
			data: "nombreU",
			success: function(data, textStatus, jqXHR)
			{
				var dt = data;
				//alert("name: " + dt);
				if (dt === "false"){
					//alert("");
					Swal.fire({
							icon: 'warning',
							title: 'Alerta',
							text: 'Por favor inicie sesion primero.'
					}).then((result) => {
						if (result.value) {
							location.href = "index.jsp";
						}
					});

					/*var path = window.location.pathname;
					var page = path.split("/").pop();
					//alert(page);
					alert(page.replace('.jsp',''));*/

				}else{
					var path = window.location.pathname;
					var page = path.split("/").pop();
					//alert("page " + page);
					//alert(page.replace('.jsp',''));

					var Frm = "Permisos";
					var User = dt;
					var Accion = page.replace('.jsp','') + ".Abrir";
					//alert("Accion " + Accion);
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

							var dat = data;
							if(Accion === "Dashboard.Abrir"){
								dat = "true";
							}
							//alert("dt: " + dt);

							if (dat != "true"){

								//alert("No tiene permisos para abrir el modulo de " + page.replace('.jsp',''));
								Swal.fire({
										icon: 'warning',
										title: 'Alerta',
										text: 'No tiene permisos para abrir el modulo de ' + page.replace('.jsp','')
								}).then((result) => {
									if (result.value) {
										location.href = "Dashboard.jsp";
									}
								});

									//evt.preventDefault();
							}else{

								//var res = "<img id="idimg" src="Principal/images/user.png" alt="">";
								document.getElementById("usering").innerHTML = dt;
								cargarUserAct(dt);
								//document.getElementById("idusera").innerHTML = dt;
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

	function cargarUserAct(user){

		//alert("Entro metodo: " + user);
		var Frm = "UtilidadesJSP";
		var Accion = "UserActivo";
		var data = {
				frm: Frm,
				usr: user,
				accion: Accion
		};
		//enableGif();
		$.ajax({
				type: "POST",
				url: "ServletAlohaTiempos",
				dataType: 'html',
				data: data,
				success: function(resul, textStatus, jqXHR)
				{
						//disableGif();
						$('#idusera').html(resul);

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

	function validoLog() {

		/**/
		$.ajax({
			type: "GET",
			url: "ServletAlohaTiempos",
			data: "nombreUs",
			success: function(data, textStatus, jqXHR){

				var dt = data;
				//alert("dt: " + dt);

				if (dt === "true"){

						//location.href = "Dashboard.jsp";
						//evt.preventDefault();
				}
				else{

						alert("Por favor inicie sesion primero.");
						location.href = "index.jsp";
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

	function editoUrl() {

		document.getElementById("idtipoc").href = "#";
	}

});
