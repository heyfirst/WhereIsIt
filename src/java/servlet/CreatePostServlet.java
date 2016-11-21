/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Repo.Repo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Image;
import model.Post;
import model.Tag;
import model.User;

/**
 *
 * @author Huag
 */
public class CreatePostServlet extends HttpServlet {

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
         if(request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        // Post Only        
        String postName = request.getParameter("postName");
        String[] tagfromInput    = request.getParameterValues("tag");
        String postDesciption = request.getParameter("postDescription");
        String yes = request.getParameter("question");
        String lat = request.getParameter("map_lat");
        String lon = request.getParameter("map_lng");
        
        User user = (User) request.getSession().getAttribute("loggedInUser");
        if(user != null){
            if(yes.equalsIgnoreCase("yes") && postName != null && postDesciption != null){
                int userId = user.getUserId();
                 int status = 0;
                 
                  
                 ArrayList<Tag> listTag =  new ArrayList<Tag>();
                 for (int i = 0; i < tagfromInput.length; i++) {
                     int tagId = Integer.parseInt(tagfromInput[i]);
                    Tag tag = Repo.queryTagByTagId(tagId);
                    listTag.add(tag);
                }
                 
                  ArrayList<Image> listImage =  new ArrayList<Image>();
                 

                    
                
                 
                 
                 Post post = new Post(listImage,listTag);
                 post.setUser(user);
                 post.setPostName(postName);
                 post.setPostDescription(postDesciption);
                 post.setStatus(0);
                 try{
                    post.setLon(Double.parseDouble(lon));
                    post.setLat(Double.parseDouble(lat));
                 }catch(Exception x){
                    post.setLon(0);
                    post.setLat(0);
                 }
          
                 
                 boolean success = Repo.insertPost(post);
                 if(success){
                     Post p = Repo.findLastPostByUserId(userId);
                     request.setAttribute("post", p);
                     getServletContext().getRequestDispatcher("/pages/create_post_success.jsp").forward(request, response);
                 }
                 else{
                     ArrayList<Tag> allTag = (ArrayList<Tag>) Repo.queryTag();
                     request.setAttribute("message","Create Post Fail Please try again");
                     request.setAttribute("tag", allTag);
                     getServletContext().getRequestDispatcher("/pages/create_post.jsp").forward(request, response);
                 }
                     
            }
            
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
        List<Tag>tag = (ArrayList<Tag>)Repo.queryTag();
        request.setAttribute("tag", tag);
       getServletContext().getRequestDispatcher("/pages/create_post.jsp").forward(request, response);
        
      
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
