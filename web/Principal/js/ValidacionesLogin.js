$(document).ready(function(){
	//var slideIndex = 1;
	editoNomU();
	editoUrl();
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
