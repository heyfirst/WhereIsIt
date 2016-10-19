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
public class RegisterServlet extends HttpServlet {

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
         String postName = request.getParameter("postName");
         String[] tagName = request.getParameterValues("tag");
         String postDescription = request.getParameter("postDescription");
         String[] src = request.getParameterValues("postImage");
         ArrayList<Tag> allTag = (ArrayList<Tag>) Repo.queryTag("select * from wil_tag");
         ArrayList<Image> listImage = null;
         double lat;
         double lon;
         try{
            lat = Double.valueOf(request.getParameter("lat"));
            lon = Double.valueOf(request.getParameter("lon"));
         }catch(Exception ex){
            lat =  -100d;
            lon = -100d;
         }
         String msg = "message";
         Post post = null;
         
         boolean success = false;
         /*  requirement data */
        if((postName != null && (!postName.equalsIgnoreCase(""))) && tagName != null && postDescription != null && src != null && postDescription != null){
            msg = "Invalid add Post";
             listImage = new ArrayList<Image>();
            if((!src[0].equalsIgnoreCase("") ) && (!tagName[0].equalsIgnoreCase("") )){     
                 for (int i = 0; i < src.length; i++) {
                    listImage.add(new Image(src[i]));
                 }
            }
            
            ArrayList<Tag> listTag = new ArrayList<Tag>();
            for (int i = 0; i < tagName.length; i++) {
                Tag t = new Tag();
                 t.setTagId(Integer.valueOf(tagName[i]));
                 
                listTag.add(t);
            }
           
             post = new Post(listImage,listTag);
                post.setPostName(postName);
                post.setPostDescription(postDescription);
                post.setStatus(1);
                if(lat == -100){
                    post.setLat(lat);
                }
                if(lon == -100){
                    post.setLon(lon);
                }
                // USER IMAGEIN
                User user = new User();
                user.setUserId(5);
                post.setUser(user);
                success = Repo.insertPost(post);
                if(success)
                    msg = "ADD POST SUCCESS";
            request.setAttribute("tag", listTag);
            request.setAttribute("message", msg);
            request.setAttribute("check", "IF");
            
        }else{
            
            request.setAttribute("tag", tagName);
            request.setAttribute("check", "else");
        }
        
         request.setAttribute("postName", postName);
            request.setAttribute("postDescription", postDescription); 
            request.setAttribute("postImage",listImage);
        request.setAttribute("success", success);
         request.setAttribute("listTag",allTag);
        getServletContext().getRequestDispatcher("/pages/Register.jsp").forward(request, response);
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
