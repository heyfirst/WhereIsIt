/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Repo.UserRepo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author pingpongsz
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        String message = "";
        if(loggedInUser != null){
            response.sendRedirect(getServletContext().getContextPath());
        }else{
            getServletContext().getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }
        
                
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        HttpSession session = request.getSession(false);
        String message = "";
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if(loggedInUser != null){
            response.sendRedirect(getServletContext().getContextPath());
//                getServletContext().getRequestDispatcher("/pages/index.jsp").forward(request, response);
        }else{
            
            String em = request.getParameter("email");
            String pw = request.getParameter("password");
            loggedInUser = UserRepo.getUser(em, pw);
            
            if(loggedInUser != null){
                message = "Logged in! Welcome!";
                
            }else{
                message = "Your Email or Password is not collect!";
                request.setAttribute("message", message);
                
            }
            session.setAttribute("loggedInUser", loggedInUser);
        }
        request.setAttribute("message", message);
        response.sendRedirect(getServletContext().getContextPath()+"/pages/");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
