$(function(){
/*
	$('#idempre').click(function(e){

		//alert("HolaEmpresa");
		var Frm = "Permisos";
		var User = "Tecno";
		var Pass = "";
		var Accion = "Empresa.Abrir";
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
			success: function(data, textStatus, jqXHR){

				var dt = data;
				//alert("dt: " + dt);

				if (dt === "true"){

						location.href = "Empresa.jsp";
						//evt.preventDefault();
				}
				else{

						alert("No tiene permisos para abrir el modulo de Empresa.");
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
	});

	$('#idccosto').click(function(e){

		//alert("HolaEmpresa");
		var Frm = "Permisos";
		var User = "Tecno";
		var Pass = "";
		var Accion = "CentroCosto.Abrir";
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
			success: function(data, textStatus, jqXHR){

				var dt = data;
				//alert("dt: " + dt);

				if (dt === "true"){

						location.href = "CentroCostos.jsp";
						//evt.preventDefault();
				}
				else{

						alert("No tiene permisos para abrir el modulo de CentroCostos.");
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
	});

	$('#iddispo').click(function(e){

		//alert("HolaEmpresa");
		var Frm = "Permisos";
		var User = "Tecno";
		var Pass = "";
		var Accion = "Dispositivo.Abrir";
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
			success: function(data, textStatus, jqXHR){

				var dt = data;
				//alert("dt: " + dt);

				if (dt === "true"){

						location.href = "Dispositivo.jsp";
						//evt.preventDefault();
				}
				else{

						alert("No tiene permisos para abrir el modulo de Dispositivos.");
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
	});

	$('#idfest').click(function(e){

		//alert("HolaEmpresa");
		var Frm = "Permisos";
		var User = "Tecno";
		var Pass = "";
		var Accion = "Festivos.Abrir";
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
			success: function(data, textStatus, jqXHR){

				var dt = data;
				//alert("dt: " + dt);

				if (dt === "true"){

						location.href = "Festivos.jsp";
						//evt.preventDefault();
				}
				else{

						alert("No tiene permisos para abrir el modulo de Festivos.");
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
	});

	$('#idtipoc').click(function(e){

		//alert("HolaEmpresa");
		var Frm = "Permisos";
		var User = "Tecno";
		var Pass = "";
		var Accion = "TipoConsumo.Abrir";
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
			success: function(data, textStatus, jqXHR){

				var dt = data;
				//alert("dt: " + dt);

				if (dt === "true"){

						location.href = "TipoConsumo.jsp";
						//evt.preventDefault();
				}
				else{

						alert("No tiene permisos para abrir el modulo de TipoConsumo.");
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
	});

	$('#idhconsu').click(function(e){

		//alert("HolaEmpresa");
		var Frm = "Permisos";
		var User = "Tecno";
		var Pass = "";
		var Accion = "HorarioConsumo.Abrir";
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
			success: function(data, textStatus, jqXHR){

				var dt = data;
				//alert("dt: " + dt);

				if (dt === "true"){

						location.href = "HorarioConsumo.jsp";
						//evt.preventDefault();
				}
				else{

						alert("No tiene permisos para abrir el modulo de HorarioConsumo.");
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
	});

	$('#idgconsu').click(function(e){

		//alert("HolaEmpresa");
		var Frm = "Permisos";
		var User = "Tecno";
		var Pass = "";
		var Accion = "GrupoConsumo.Abrir";
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
			success: function(data, textStatus, jqXHR){

				var dt = data;
				//alert("dt: " + dt);

				if (dt === "true"){

						location.href = "GrupoConsumo.jsp";
						//evt.preventDefault();
				}
				else{

						alert("No tiene permisos para abrir el modulo de GrupoConsumo.");
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
	});

	$('#idagconsu').click(function(e){

		//alert("HolaEmpresa");
		var Frm = "Permisos";
		var User = "Tecno";
		var Pass = "";
		var Accion = "AsocGrupoConsumo.Abrir";
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
			success: function(data, textStatus, jqXHR){

				var dt = data;
				//alert("dt: " + dt);

				if (dt === "true"){

						location.href = "AsocGrupoConsumo.jsp";
						//evt.preventDefault();
				}
				else{

						alert("No tiene permisos para abrir el modulo de AsocGrupoConsumo.");
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
	});

	$('#idperso').click(function(e){

		//alert("HolaEmpresa");
		var Frm = "Permisos";
		var User = "Tecno";
		var Pass = "";
		var Accion = "PersonasCasino.Abrir";
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
			success: function(data, textStatus, jqXHR){

				var dt = data;
				//alert("dt: " + dt);

				if (dt === "true"){

						location.href = "PersonasCasino.jsp";
						//evt.preventDefault();
				}
				else{

						alert("No tiene permisos para abrir el modulo de PersonasCasino.");
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
	});

	$('#idlcasin').click(function(e){

		//alert("HolaEmpresa");
		var Frm = "Permisos";
		var User = "Tecno";
		var Pass = "";
		var Accion = "LiquidacionCasino.Abrir";
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
			success: function(data, textStatus, jqXHR){

				var dt = data;
				//alert("dt: " + dt);

				if (dt === "true"){

						location.href = "LiquidacionCasino.jsp";
						//evt.preventDefault();
				}
				else{

						alert("No tiene permisos para abrir el modulo de LiquidacionCasino.");
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
	});
*/
	$(document).ready(function(){
	//var slideIndex = 1;
		editoNomU();
		//editoUrl();
		//validoLog();

		function validoLog() {

			/**/
			$.ajax({
				type: "GET",
				url: "ServletAlohaTiempos",
				data: "nombreUs",
				success: function(data, textStatus, jqXHR){

					var dt = data;
					alert("dt: " + dt);

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
						alert("Por favor inicie sesion primero.");
						var path = window.location.pathname;
						var page = path.split("/").pop();
						//alert(page);
						alert(page.replace('.jsp',''));
						location.href = "index.jsp";
					}else{


						document.getElementById("usering").innerHTML = dt;
						document.getElementById("idusera").innerHTML = dt;
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

		/*function editoBot() {

				var botonEnviar = document.getElementById("idsave");
				botonEnviar.disabled === true;
		}*/


	});

});
