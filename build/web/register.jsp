<%-- 
    Document   : register
    Created on : 19-11-2016, 20:38:49
    Author     : Iwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registreringsside</title>
        <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form name="registreringsformular" action="Register.do" method="POST">
            <label title="username">Brugernavn: </label><br>
            <input type="text" name="brugernavn" value="" pattern="[^0-9]+"/><br>
            <label title="password">Adgangskode: </label>*(minimum 6 tegn)<br>
            <input type="password" name="adgangskode" value="" pattern="\w{5,}"/><br>
            <input type="submit" value="Tilmeld" name="tilmeld"/> 
        </form>
    </body>
</html>
