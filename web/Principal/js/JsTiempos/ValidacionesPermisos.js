$(function(){
  $("#header").load("Principal/Head.html");
  $("#script").load("Principal/Script.html");
});

$(function(){


  $(document).ready(function() {
      cargarComboUsr();

  });

  function cargarComboUsr(){

      var Frm = "PermisosJSP";
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
              $('#IdUsuarios').html(resul);

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

  function cargarPermisosU(strUser){


    //var namUs = document.getElementById('usering').innerHTML;
    //alert(namUs);
    var Frm = "PermisosJSP";
    var Accion = "ReadPU";
    var data = {
        frm: Frm,
        user: strUser,
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
            $('#IdPermisosAsig').html(resul);

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

  function cargarPermisosNoAsig(strUser){

    //var namUs = document.getElementById('usering').innerHTML;
    //alert(namUs);
    var Frm = "PermisosJSP";
    var Accion = "ReadPNoU";
    var data = {
        frm: Frm,
        user: strUser,
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
            $('#IdPermisosNoAsig').html(resul);

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

  function listarTodosPermisos(selec){

    //var namUs = document.getElementById('usering').innerHTML;
    //alert(namUs);
    var Frm = "PermisosJSP";
    var Accion = "ReadTodosP";
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
            $('#'+selec).html(resul);

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

  $('#IdPermisos').click(function(e){

    var e = document.getElementById('IdUsuarios');
    var strUser = e.options[e.selectedIndex].value;
    //alert(strUser);
    if(strUser != ""){

      cargarPermisosU(strUser);
      cargarPermisosNoAsig(strUser);
    }else{
      Swal.fire({
          icon: 'warning',
          title: 'Alerta',
          text: 'Por favor seleccione un usuario.',
          showConfirmButton: false,
          timer: 3000
      });
    }


  });

  $('#IdQuitoTodos').click(function(e){

    var select = document.getElementById('IdPermisosAsig');
    var length = select.options.length;
    for (i = length-1; i >= 0; i--) {
      select.options[i] = null;
    }
    listarTodosPermisos("IdPermisosNoAsig");

    /*var sele = document.getElementById('IdPermisosAsig');

    for (var i = 1; i<=5; i++){

      var opt = document.createElement('option');
      opt.value = "H " + i;
      opt.innerHTML = "H " + i;
      sele.appendChild(opt);
    }*/


  });

  $('#IdQuitoUno').click(function(e){

    $("#IdPermisosAsig option:selected").each(function () {

      var sele = document.getElementById('IdPermisosNoAsig');
      var $this = $(this);
      if ($this.length) {
        var selText = $this.text();
        var opt = document.createElement('option');
        //opt.value = val;
        opt.innerHTML = selText;
        sele.appendChild(opt);
        //console.log(selText);
        $this.remove();
      }
    });

  /*  var combo = document.getElementById('IdPermisosAsig');
    //alert(combo.length);
    //
    //alert(strUs);
    var cnt = 0;
    for (var i=0;i<combo.options.length;i++){
      if(combo[i].selected){
        var strUs = combo.options[combo.selectedIndex].text;

        cnt++;
      }
    }
    alert(cnt);
    alert(strUs);*/

    /*select = document.getElementById('IdPermisosAsig');
    var opt = document.createElement('option');
    opt.value = "Hola";
    opt.innerHTML = "Hola";
    select.appendChild(opt);*/

  });

  $('#IdPasoTodos').click(function(e){

    var select = document.getElementById('IdPermisosNoAsig');
    var length = select.options.length;
    for (i = length-1; i >= 0; i--) {
      select.options[i] = null;
    }
    listarTodosPermisos("IdPermisosAsig");
    /*var sele = document.getElementById('IdPermisosAsig');
    //select = document.getElementById('IdPermisosAsig');
    alert(sele.length);
    for (var i = 1; i<=sele.length; i++){
      if (selectobject.options[i].value == 'A')
      sele.remove(i);
    }*/


  });

  $('#IdPasoUno').click(function(e){

    $("#IdPermisosNoAsig option:selected").each(function () {

      var sele = document.getElementById('IdPermisosAsig');
      var $this = $(this);
      if ($this.length) {
        var selText = $this.text();
        var opt = document.createElement('option');
        //opt.value = val;
        opt.innerHTML = selText;
        sele.appendChild(opt);
        //console.log(selText);
        $this.remove();
      }
    });
    /*var e = document.getElementById('IdPermisosNoAsig');
    //var strUser = e.options[e.selectedIndex].value;
    var strU = e.options[e.selectedIndex].text;

    alert(strU);*/


  });

  $('#IdGuardar').click(function(e){

    var e = document.getElementById('IdUsuarios');
    var strUser = e.options[e.selectedIndex].value;
    //alert(can);
    var i = 0;
    var elemen = [];
    $("#IdPermisosAsig option").each(function () {
      //var $this = $(this);
      //alert($(this).text());
      elemen[i] = $(this).text();
      i++;

    });
    /*for (i = 0; i < can; i++) {
      console.log(elemen[i]);
    }*/

    var Frm = "PermisosJSP";
    var Accion = "LeoItems";
    var data = {
        frm: Frm,
        //elements: JSON.stringify(elemen),
        elements: elemen,
        usr: strUser,
        accion: Accion
    };
    $.ajax({
      type: "POST",
      url: "ServletAlohaTiempos",
      data: data,
      success: function(data, textStatus, jqXHR){

        var dt = data;
        //alert("dt: " + dt);
        if (dt != "false"){

          Swal.fire({
              icon: 'success',
              title: 'Se actualizaron los permisos.',
              text: 'Por favor cierre sesion e ingrese de nuevo.'
          });
        }
        else{

          Swal.fire({
              icon: 'error',
              title: 'Ocurrio un error.',
              text: 'Por favor intente de nuevo.',
              showConfirmButton: false,
              timer: 3000
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


  });

  function enableGif(){

      window.onload = document.getElementById("espera").style = "display: block";
      window.onload = document.getElementById("Principal").style = "display: none";
  }

  function disableGif(){

      window.onload = document.getElementById("espera").style = "display: none";
      window.onload = document.getElementById("Principal").style = "display: enable";
  }

});
