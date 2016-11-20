package Repo;

import static Repo.UserRepo.getUserByUserId;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Found;
import model.Image;
import model.User;
import util.ConnectionBuilder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Huag
 */
public class FoundRepo {
    
    public static synchronized  boolean insertFounder(int userId,int postId,String description){
        boolean success = false;
         String sql = "INSERT INTO `wil_found`(`user_id`, `post_id`, `description`, `created_at`, `updated_at`) "
                            + "VALUES (?,?,?,now(),now())";
         try{
             Connection con = ConnectionBuilder.getMySqlCond();
             PreparedStatement pstmt  = con.prepareStatement(sql);
             pstmt.setInt(1, userId);
             pstmt.setInt(2, postId);
             pstmt.setString(3, description);
             int insert = pstmt.executeUpdate();
             if(insert > 0){
    
                        
                         success = true;
                 
                 
                 
             }
             con.close();
         }catch(Exception x){
             x.printStackTrace();
         }
         return success;
    }
    
    
    public static synchronized  Found findFounderByPostId(int postId){
        Found founder = null;
        String sql = "select * from wil_found where post_id=?";
        try{
            Connection con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, postId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                founder = new Found(new User());
               ormFound(rs,founder);
               founder.setUser(getUserByUserId(founder.getUser().getUserId()));
            }
            con.close();
        }
        catch(Exception x){
            x.printStackTrace();
        }
        return founder;
    }
    
    private static Found  ormFound(ResultSet rs,Found founder) throws SQLException{
        founder.setFoundId(rs.getInt("found_id"));
        founder.setPostId(rs.getInt("post_id"));
        founder.setFoundDescription(rs.getString("description"));
        founder.getUser().setUserId(rs.getInt("user_id"));
        return founder;
    }
    
    public static synchronized  Found findFounderByUserId(int userId){
        Found founder = null;
        String sql = "select * from wil_found where user_id=?";
        try{
            Connection con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                founder = new Found(new User());
               ormFound(rs,founder);
               founder.setUser(getUserByUserId(founder.getUser().getUserId()));
            }
            con.close();
        }
        catch(Exception x){
            x.printStackTrace();
        }
        return founder;
    }
    
}
