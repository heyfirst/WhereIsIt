/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Repo.FoundRepo;
import Repo.Repo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Found;
import model.Post;
import model.User;

/**
 *
 * @author Huag
 */
public class ConfirmPostPendingServlet extends HttpServlet {

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
         if(request.getCharacterEncoding() == null){
            request.setCharacterEncoding("UTF-8");
        }
         User user = (User)request.getSession().getAttribute("loggedInUser");
         Post owner = null;
         Found founder = new Found();
         HttpSession session = request.getSession();
         String postId = request.getParameter("postId");
         String found_item = request.getParameter("found_item");
         String found_date = request.getParameter("found_date");
         String found_time = request.getParameter("found_time");
         String found_place= request.getParameter("found_place");
         String ownerAnswer = request.getParameter("ownerAnswer");
         String foundDescription = "item=" +found_item + ","
                                                 +"date="+found_date + ","
                                                 +"time=" + found_time + ","
                                                 + "place="+found_place;
         if(user != null ){
             // Founder send information about  lost item
             // founder user from session  
             if(ownerAnswer.equalsIgnoreCase("owner")){
                 String discard = (String)request.getParameter("discard");
                 String confirm = (String)request.getParameter("confirm");
                 if(discard != null && discard.equalsIgnoreCase("discard")){
                        boolean updateToClosed = Repo.updateToPostPending(0, Integer.parseInt(postId));
                 }
                 else if(confirm != null && confirm.equalsIgnoreCase("confirm")){
                     boolean updateToClosed = Repo.updateToPostPending(2, Integer.parseInt(postId));
                 }
             }
             else if(ownerAnswer.equalsIgnoreCase("founder")){
                 if(found_item != null || found_date !=null || found_time != null || found_place != null){
                    boolean foundInsert = FoundRepo.insertFounder(user.getUserId(), Integer.parseInt(postId), foundDescription);
                    if(foundInsert){
                        boolean pendingPost = Repo.updateToPostPending(1,Integer.parseInt(postId));
                        if(pendingPost){
                                //          ส่งแจ้งเตือน               
                            
                        }
                    
                }
             }
             
                
             }
              response.sendRedirect(getServletContext().getContextPath()+"/pages/");
         }
        
         
         
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
