<%-- 
    Document   : pruebas
    Created on : 25-mar-2020, 8:29:33
    Author     : Carlos A Dominguez D
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="Principal/js/JsTiempos/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/JsTiempos/pruebas.js" ></script>     
    </head>
    <body>    
        <!--form action="prueba" method="GET"-->
        <form action="HolaMundo" method="GET">
            <input type="text" id="texto1" name="id" value="Primer textbox">
            <input type="text" id="texto2" value="Segundo textbox">
            <h3>esto es una respues request ${id}</h3>
            <button type="submit"></button>
        </form>

    </body>
</html>
