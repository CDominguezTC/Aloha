$(function(){
  $("#header").load("Principal/Head.html"); 
  $("#script").load("Principal/Script.html"); 
});

$(function()
{
    $(document).ready(function() {
        LoadTabla();
    });

    $(document).on('click', '.SetFormulario', function() {
        $('#Id').val($(this).data('id'));
        $('#IdNombre').val($(this).data('nombre'));
        $('#IdDescripcion').val($(this).data('descripcion'));
        $('#IdCodReloj').val($(this).data('codReloj'));
    });

    function ValidaCampo()
    {
        var res = false;
        if ($('#IdNombre').val() !== "")
        {           
          if ($('#IdDescripcion').val() !== "")
            { 
                if ($('#IdCodReloj').val() !== "")
                {
                    res = true;
                }
            }    
        }
        return res;
    }
    
     function  LimpiarCampos()
    {
        $('#Id').val('');
        $('#IdNombre').val('');
        $('#IdDescripcion').val('');
        $('#IdCodReloj').val('');
    }

    $('#IdAgregar').click(function(e)
    {
        LimpiarCampos();
    });
    
    $('#IdGuardar').click(function(e)
    {
        
        if (ValidaCampo() === true)
        {
            var Frm = "FuncionesJSP";
            var Id = $('#Id').val();
            var Nombre = $('#IdNombre').val();
            var Descripcion = $('#IdDescripcion').val();
            var CodReloj = $('#IdCodReloj').val();
            var Accion = "Upload";
            var data = {
                frm: Frm,
                id: Id,
                nombre: Nombre,
                descripcion: Descripcion,
                codReloj: CodReloj,
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
                        text: 'Registro Guardado Satisfactoriamente.'
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
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Verifica todos los campos.'
            });
            //alert("Favor de completar todos los campos");
        }
    });
    
     $(document).on('click', '.SetEliminar', function() {
//        if (ValidaCampo() === true)
//        {
        var Frm = "FuncionesJSP";
        var Id = $(this).data('id');
        var Nombre = $('#IdNombre').val();
        var Descripcion = $('#IdDescripcion').val();
        var CodReloj = $('#IdCodReloj').val();
        var Accion = "Delete";
        var data = {
            frm: Frm,
            id: Id,
            nombre: Nombre,
            descripcion: Descripcion,
            codReloj: CodReloj,
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
                            text: 'Registro Eliminado Satisfactoriamente.'
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
            var Frm = "ComercialJSP";
            var Id = $('#Id').val();
            var Nombre = $('#IdNombre').val();
            var Descripcion = $('#IdDescripcion').val();
            var CodReloj = $('#IdCodReloj').val();
            var Notas = $('#IdNotas').val();
            var Accion = "Delete";
            var data = {
                frm: Frm,
                id: Id,
                nombre: Nombre,
                descripcion: Descripcion,
                codReloj: CodReloj,
                notas: Notas,
                accion: Accion
            };
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
                        text: 'Registro Eliminado Satisfactoriamente.'
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
        else
        {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Verifica todos los campos.'
            });
            //alert("Favor de completar todos los campos");
        }
    });
    
      function LoadTabla()
    {
        var Frm = "FuncionesJSP";
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
    function enableGif()
    {
        window.onload = document.getElementById("espera").style = "display: block";
        window.onload = document.getElementById("Principal").style = "display: none"
    }
    function disableGif()
    {
        window.onload = document.getElementById("espera").style = "display: none";
        window.onload = document.getElementById("Principal").style = "display: enable"
    }
});