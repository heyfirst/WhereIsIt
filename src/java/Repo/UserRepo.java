/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ConnectionBuilder;

/**
 *
 * @author pingpongsz
 */
public class UserRepo {
    public synchronized static boolean createUser(String email, String password, String fname, String lname, int gender, 
            String citizenId, String tel, String faculty, String address){
        boolean isSuccess = false;
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
}
