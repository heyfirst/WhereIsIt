/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Image;
import model.User;
import util.ConnectionBuilder;

/**
 *
 * @author pingpongsz
 */
public class UserRepo {
    public synchronized static boolean createUser(String email, String password, String fname, String lname, int gender, 
            String citizenId, String tel, String faculty, String address){
        boolean isSuccess = false;
        if(isExistUser(email)){
            return isSuccess;
        }
        try {
            
            String createUser = "INSERT INTO wil_user (email, password, fname, lname, gender, citizen_id, tel, faculty, address, created_at, updated_at) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,now(),now())";
            Connection con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement(createUser);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, fname);
            pstmt.setString(4, lname);
            pstmt.setInt(5, gender);
            pstmt.setString(6, citizenId);
            pstmt.setString(7, tel);
            pstmt.setString(8, faculty);
            pstmt.setString(9, address);
            if(pstmt.executeUpdate() > 0){
                isSuccess = true;
            }
            
            pstmt.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }
    
    public synchronized static User getUser(String em, String pw){
        User userInfor = null;
        try {
            
            String getUserInfor = "SELECT * FROM wil_user WHERE email = ? AND password = ?";
            Connection con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement(getUserInfor);
            pstmt.setString(1, em);
            pstmt.setString(2, pw);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int userId = rs.getInt("user_id");
                int imageId;
                try{
                     imageId = rs.getInt("image_id");
                }
                catch(Exception x){
                    imageId = 0;
                }
                String email = rs.getString("email");
                String password = rs.getString("password");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                int gender = rs.getInt("gender");
                String citizenId = rs.getString("citizen_id");
                String tel = rs.getString("tel");
                String faculty = rs.getString("faculty");
                String address;
                  try{
                     address = rs.getString("address");
                }
                catch(Exception x){
                    address = null;
                }
                
                
                
                Image image = new Image(); // need ImageRepo to query by Id
                userInfor = new User(userId, image, email, password, fname, lname, gender, citizenId, tel, faculty, address);
            }
                              
            pstmt.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userInfor;
    }
    
    public synchronized static boolean isExistUser(String email){
        boolean isExist = false;
        try {
            
            String getUserByEmail = "SELECT * FROM wil_user WHERE email = ?";
            Connection con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement(getUserByEmail);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                isExist = true;
            }
                              
            pstmt.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
    }
}
