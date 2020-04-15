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

						alert("No tiene permisos para abrir el modulo de " + modu.replace('.jsp',''));
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

	$('#idempre').click(function(e){

		//alert("HolaEmpresa");
		validoPermiso("Empresa.Abrir","Empresa.jsp");

	});

  $('#idusers').click(function(e){

		//alert("HolaEmpresa");
		validoPermiso("Usuarios.Abrir","Usuarios.jsp");

	});

	$('#idccosto').click(function(e){

		//alert("HolaEmpresa");
		validoPermiso("CentroCostos.Abrir","CentroCostos.jsp");

	});

	$('#iddispo').click(function(e){

		//alert("HolaEmpresa");
		validoPermiso("Dispositivo.Abrir","Dispositivo.jsp");

	});

	$('#idfest').click(function(e){

		//alert("HolaEmpresa");
		validoPermiso("Festivos.Abrir","Festivos.jsp");
	});

	$('#idtipoc').click(function(e){

		//alert("HolaEmpresa");
		validoPermiso("TipoConsumo.Abrir","TipoConsumo.jsp");
	});

	$('#idhconsu').click(function(e){

		//alert("HolaEmpresa");
		validoPermiso("HorarioConsumo.Abrir","HorarioConsumo.jsp");
	});

	$('#idgconsu').click(function(e){

		//alert("HolaEmpresa");
		validoPermiso("GrupoConsumo.Abrir","GrupoConsumo.jsp");
	});

	$('#idagconsu').click(function(e){

		//alert("HolaEmpresa");
		validoPermiso("AsocGrupoConsumo.Abrir","AsocGrupoConsumo.jsp");
	});

	$('#idperso').click(function(e){

		//alert("HolaEmpresa");
		validoPermiso("PersonasCasino.Abrir","PersonasCasino.jsp");
	});

	$('#idlcasin').click(function(e){

		//alert("HolaEmpresa");
		validoPermiso("LiquidacionCasino.Abrir","LiquidacionCasino.jsp");
	});

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
						/*var path = window.location.pathname;
						var page = path.split("/").pop();
						//alert(page);
						alert(page.replace('.jsp',''));*/
						location.href = "index.jsp";
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

									alert("No tiene permisos para abrir el modulo de " + page.replace('.jsp',''));
									location.href = "Dashboard.jsp";
										//evt.preventDefault();
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
