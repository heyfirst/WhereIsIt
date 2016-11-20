/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Repo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class PostPendingServlet extends HttpServlet {
    
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
         String query = request.getQueryString();
          int index = query.indexOf("=");
         User user = (User)request.getSession().getAttribute("loggedInUser");
         Post owner = null;
         Found founder = null;
         HttpSession session = request.getSession();
         String postId = query.substring(index+1);
         String found_item = request.getParameter("found_item");
         String found_date = request.getParameter("found_date");
         String found_time = request.getParameter("found_time");
         String found_place= request.getParameter("found_place");
         String found_address = request.getParameter("found_address");
         String foundDescription = "item=" +found_item + ","
                                                 +"date="+found_date + ","
                                                 +"time=" + found_time + ","
                                                 + "place="+found_place + ","
                                                 + "address="+found_address;
         if(user != null && session.getAttribute("posts") != null){
             // Founder send information about  lost item
             // founder user from session             
             if(found_item != null || found_date !=null || found_time != null || found_place != null || found_address != null){
                boolean foundInsert = FoundRepo.insertFounder(user.getUserId(), Integer.parseInt(postId), foundDescription);
                if(foundInsert){
                    boolean pendingPost = Repo.updateToPostPending(1,Integer.parseInt(postId));
                    if(pendingPost){
                        owner = Repo.findPostById(Integer.parseInt(postId));
                    //        User from session
                        request.setAttribute("founder",user);
                    }
                    
                }
                
             }
             else{
                 /// Owner Confirm status pending
                //    owner data form session
                 owner = Repo.findPostById(Integer.parseInt(postId));
                 founder = FoundRepo.findFounderByPostId(Integer.parseInt(postId));
                 // User from Session
                 request.setAttribute("founder", founder);
                 
             }
             
                        
                        request.setAttribute("owner",owner);
                        request.setAttribute("found_item", found_item);
                        request.setAttribute("found_date", found_date);
                        request.setAttribute("found_time", found_time);
                        request.setAttribute("found_place", found_place);
                        request.setAttribute("found_address", found_address);
             
             getServletContext().getRequestDispatcher("/pages/post_pending.jsp").forward(request, response);
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
