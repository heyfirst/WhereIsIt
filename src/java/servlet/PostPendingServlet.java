/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Repo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
         Found founder = new Found();
         HttpSession session = request.getSession();
         List<Post> listPost = (List<Post>)session.getAttribute("posts");
         String postId = query.substring(index+1);
         String found_item = request.getParameter("found_item");
         String found_date = request.getParameter("found_date");
         String found_time = request.getParameter("found_time");
         String found_place= request.getParameter("found_place");
         String ownerAnswer ;
        if(user != null && listPost != null){
            if(found_item != null && found_date != null && found_time != null && found_place != null){
                        founder.setUser(user);
                        owner = Repo.findPostById(Integer.parseInt(postId));
                        ownerAnswer = "founder";
            }
            else{
                int pid = Integer.parseInt(postId);
                founder = FoundRepo.findFounderByPostId(pid);
                owner = Repo.findPostById(pid);
                
                ownerAnswer = "owner";
                String des = founder.getFoundDescription();
                ArrayList<String> descrip = new ArrayList<String>(); 
                int next = 0;
                int equal = 0;
                int comma =0;
                for (int i = 0; i < 4; i++) {
                    equal = des.indexOf("=",next);
                   comma = des.indexOf(",",equal);
                    System.out.println(equal);
                    System.out.println(comma);
                   String item;
                   if(comma == -1)
                       item = des.substring(equal+1);
                   else
                        item= des.substring(equal+1, comma);
                   next = equal+1;
                   descrip.add(item);
                }
                found_item = descrip.get(0);
                found_date = descrip.get(1);
                found_time = descrip.get(2);
                found_place = descrip.get(3);
                System.out.println(found_item);
                System.out.println(found_date);
                System.out.println(found_time);
                System.out.println(found_place);
                System.out.println(founder);
            }
                        request.setAttribute("postId",postId);
                        request.setAttribute("ownerPost",owner);
                        request.setAttribute("founderPost", founder);
                        request.setAttribute("found_item", found_item);
                        request.setAttribute("found_date", found_date);
                        request.setAttribute("found_time", found_time);
                        request.setAttribute("found_place", found_place);
                        request.setAttribute("ownerAnswer",  ownerAnswer);
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
