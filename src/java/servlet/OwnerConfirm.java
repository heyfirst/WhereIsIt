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
import model.Found;
import model.Post;

/**
 *
 * @author Huag
 */
public class OwnerConfirm extends HttpServlet {

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
                String found_item;
                String found_date;
                String found_time;
                String found_place;
                String found_address;
                 /// Owner Confirm status pending
                //    owner data form session
                String postId = "9";
                 Post owner = Repo.findPostById(Integer.parseInt(postId));
                 Found founder = FoundRepo.findFounderByPostId(Integer.parseInt(postId));
                 // User from Session
                 String fdes = founder.getFoundDescription();
                 int count = 1;
                 int equal = fdes.indexOf("=");
                 while(equal != -1){
                     int indexComma = fdes.indexOf(",");
                     switch(count){
                         case 1 :   found_item = fdes.substring(equal+1,indexComma);
                                        count++;
                                        break;
                        case 2 :   found_date = fdes.substring(equal+1,indexComma);
                                        count++;
                                        break;
                        case 3 :   found_time = fdes.substring(equal+1,indexComma);
                                        count++;
                                        break;
                        case 4 :   found_place = fdes.substring(equal+1,indexComma);
                                        count++;
                                        break;
                       case 5 :   found_address = fdes.substring(equal+1,fdes.length());
                                        count++;
                                        break;
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
