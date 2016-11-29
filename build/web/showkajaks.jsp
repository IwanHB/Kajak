<%-- 
    Document   : showusers
    Created on : Nov 23, 2016, 5:48:35 PM
    Author     : Thomas Hartmann - tha@cphbusiness.dk
--%>

<%@page import="domain.User, java.util.List, domain.Kajak"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            th, td{
                border: 1px solid black;
            }
            .loggedin {
                margin: 0 0 0 90%;
                text-align: center;
            }
            #boks {
                border: 2px solid #000000;
            }
        </style>
    </head>
        <%
            if (session.getAttribute("username") == null) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        %>   
    <body>
        <%
         String username = (String) session.getAttribute("username");
         out.println("<div class=\"loggedin\">Du er logget ind som: <span id=\"boks\">" + username + "</span></div>");
        %>
        <h1>Book en kajak:</h1>
        <table>  
            <tr>
                <th>Navn</th>
                <th>Model</th>
                <th>Beskrivelse</th>
                <th>Farve</th>
                <th>Længde</th>
                <th>Dato</th>
                <th>Bestil</th>
            </tr>
                
                <%                     
                    List<Kajak> kajakker = (List<Kajak>) session.getAttribute("kajakker");
                    int brugerIdNummeret = (int) session.getAttribute("userid");
                    for (Kajak kajak : kajakker) {
                        int kajakIdNummeret = kajak.getKajakId();                        
                        out.println("<form action=\"BookingServlet\" method=\"POST\"><tr><td>" + kajak.getName() + "</td><td>" + 
                                kajak.getModel() + "</td><td>"
                                + kajak.getDescription() + "</td><td>"
                                + kajak.getColor() + "</td><td>"
                                + kajak.getLength() + "</td><td>"
                        + "<input type=\"date\" name=\"datoboks\" value=\"mm/dd/yyyy\" /></td><td>"
                        + "<input type=\"submit\" value=\"Bestil\" name=\"submit\"/>"
                        + "</form>");
                        
                    }
                %>
            
        </table>
                <form action="Login" method="POST">
                <input type="hidden" name="origin" value="logout" >
                <input type="submit" value="Logout">
            </form>
    </body>
</html>
