$(function(){
	$('#idsalir').click(function(e){

		//alert("Salio");
		var Accion = "Salir";
		var data = {
			accion: Accion
		};
		$.ajax({
			type: "GET",
			url: "LoginServlet",
			data: data,
			success: function(data, textStatus, jqXHR){

				var dt = data;
				//alert("dt: " + dt);
				if (dt === "true"){

					location.href = "index.jsp";
					//evt.preventDefault();
				}
				else{

					alert("No se pudo cerrar la session.");
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

});
