<%-- 
    Document   : login
    Created on : 17-11-2016, 12:29:41
    Author     : Iwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Side</title>
        <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form name="loginformular" action="Login.do" method="POST">            
            <label title="username">Brugernavn: </label><br>
            <input type="text" name="username" value="" /><br>
            <label title="password">Adgangskode: </label><br>
            <input type="password" name="password" value="" /><br>
            <input type="submit" value="Log in" name="submit"/>
        </form>
    </body>
</html>
