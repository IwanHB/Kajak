/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import dataaccess.UserMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Iwan
 */
public class Registration extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserMapper um = new UserMapper();
        String username = request.getParameter("brugernavn");
        String password = request.getParameter("adgangskode");
        boolean existsAllready = um.userAllreadyExists(username);
        PrintWriter out = response.getWriter();
        
        if(existsAllready){                          
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Databaseforespørgsel</title>");
            out.println("<link href=\"css/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Der findes allerede en bruger med dette brugernavn!</p>");
            out.println("<p>Log dig venligst ind via Log-in-siden ved at klikke ");
            out.println("<a href=\"login.jsp\">HER</a></p>");
            out.println("</body>");
            out.println("</html>");
            
        }else{
            um.createAndInsertNewUser(username, password);
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<link href=\"css/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\" />");
                out.println("<title>Tilmelding</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Du er nu tilmeldt med de følgende oplysninger:</h1>");
                out.println("<p>Brugernavn: " +username+ ". Adgangskode: "+password+ "</p>");
                out.println("<p>Du kan nu klikke <a href=\"login.jsp\">HER</a> og logge dig ind.</p>");                
                out.println("</body>");
                out.println("</html>");
        }
        
        String origin = request.getParameter("origin");
        if(origin != null){
            if(origin.equals("logout"))
            logout(request);
        }
    }

    private void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
    
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
