/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function()
{
    $('#IdIngresar').click(function(e)
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
                    success: function(data, textStatus, jqXHR)
                    {
                        var dt = data;
                        if (dt === "true")
                        {
                            location.href = "Dashboard.jsp";
                            evt.preventDefault();
                        }
                        else
                        {
                            alert("Por favor verifique el usuario y la contraseña");
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
            else
            {
                alert("Ingrese el Password");
            }
        }
        else
        {
            alert("Ingrese el Usuario");
        }
    });
});
