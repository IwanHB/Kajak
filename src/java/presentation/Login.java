package presentation;

import dataaccess.KajakMapper;
import dataaccess.UserMapper;
import domain.Kajak;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Nov 16, 2016
 */
@WebServlet(name="Login", urlPatterns={"/Login"})
public class Login extends HttpServlet {
   
 
    

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
        KajakMapper km = new KajakMapper();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean couldBeLoggedIn = um.authenticateUser(username, password);
        if(couldBeLoggedIn){     
            try{
            List<Kajak> kajakker = km.getAllKajaks();
            int userid = um.getUserIDByName(username);
            HttpSession session = request.getSession();
            session.setAttribute("kajakker", kajakker);
            session.setAttribute("username", username);
            session.setAttribute("userid", userid);
            response.sendRedirect("showkajaks.jsp");
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else{
            response.getWriter().print("Du kunne ikke logges ind");
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
