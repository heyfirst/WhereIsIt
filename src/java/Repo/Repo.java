/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Image;
import model.Post;
import model.Tag;
import model.User;
import util.ConnectionBuilder;

/**
 *
 * @author Huag
 */
public class Repo {
    // DecimalFormat for lat, lon    
    private static DecimalFormat dmf = new DecimalFormat("0.#######");
    private static String FIND_POST_BY_NAME = "SELECT   * "
                 + "FROM  wil_post  po "
                 + "JOIN    wil_post_tag pt "
                 + "ON      po.post_id = pt.post_id "
                 + "JOIN    wil_tag ta "
                 + "ON      pt.tag_id = ta.tag_id "
                 + "JOIN    wil_post_image poimg "
                 + "ON      po.post_id = poimg.post_id  "
                 + "JOIN    wil_image img "
                 + "ON      poimg.image_id = img.image_id "
                 + "JOIN    wil_user u "
                 + "ON      po.user_id = u.user_id "
                 + "Where  post_name like ? ";
    private static String FIND_POST_BY_ID =  "SELECT   * "
                 + "FROM  wil_post  po "
                 + "JOIN    wil_post_tag pt "
                 + "ON      po.post_id = pt.post_id "
                 + "JOIN    wil_tag ta "
                 + "ON      pt.tag_id = ta.tag_id "
                 + "JOIN    wil_post_image poimg "
                 + "ON      po.post_id = poimg.post_id  "
                 + "JOIN    wil_image img "
                 + "ON      poimg.image_id = img.image_id "
                 + "JOIN    wil_user u "
                 + "ON      po.user_id = u.user_id "
                 + "Where  po.user_id=? ";
    
    public static List<Post> allPost(){
        List<Post> listPost = null;
        List<Tag> listTag = null;
         Image img =null;
         Tag tag = new Tag();
        Connection con = null;
        Post post = null;
        int index = 0;
        int check = 0;
        try{
            con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement(
                    "SELECT   * "
                 + "FROM  wil_post  po "
                 + "JOIN    wil_post_tag pt "
                 + "ON      po.post_id = pt.post_id "
                 + "JOIN    wil_tag ta "
                 + "ON      pt.tag_id = ta.tag_id "
                 + "JOIN    wil_post_image poimg "
                 + "ON      po.post_id = poimg.post_id  "
                 + "JOIN    wil_image img "
                 + "ON      poimg.image_id = img.image_id "
                 + "JOIN    wil_user u "
                 + "ON      po.user_id = u.user_id "
            );
             ResultSet rs = pstmt.executeQuery();
             listPost = queryPost(rs,listPost);             
            con.close();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return listPost;
    }
    
    public static Post ormPost(ResultSet rs,Post post) throws SQLException{
        post.setPostId(rs.getInt("post_id"));
        post.setPostName(rs.getString("post_name"));
        post.setPostDescription(rs.getString("post_description"));
        post.setStatus(rs.getInt("status"));
        try{
            post.setLat(rs.getBigDecimal("lost_lat").doubleValue());
            post.setLon(rs.getBigDecimal("lost_lon").doubleValue());
        }
        catch(Exception ex){
            if(rs.getString("lost_lon") != null)
                post.setLon(rs.getBigDecimal("lost_lon").doubleValue());
        }
      
        return post;
    }
    
    public static Tag ormTag(ResultSet rs,Tag tag) throws SQLException{
        tag.setTagId(rs.getInt("tag_id"));
        tag.setTagName(rs.getString("tag_name"));
        return tag;
    }
    
    public static Image ormImage(ResultSet rs,Image img){
        try {
            img.setSrc(rs.getString("src"));
            img.setImageId(rs.getInt("image_id"));     
        } catch (SQLException ex) {
           if(String.valueOf(img.getImageId()) == null){
                img.setImageId(0);
                img.setSrc("");
           }
                
        }
        return img;
    }
    public static User ormUser(ResultSet rs,User user) {
        try{
            user.setUserId(rs.getInt("user_id"));
            user.setCitizenId(rs.getString("citizen_id"));
            user.setFname(rs.getString("fname"));
            user.setLname(rs.getString("lname"));
            user.setGender(rs.getInt("gender"));
            user.setFaculty(rs.getString("faculty"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            
            user.setAddress(rs.getString("address"));
            user.getImage().setImageId(rs.getInt("image_id"));
            user.getImage().setSrc(rs.getString("src"));
        }
        catch(SQLException ex){
            if(user.getAddress() == null)
                user.setAddress("");
            try {
                if((Object)rs.getInt("image_id") == null)
                    user.getImage().setSrc("");
            } catch (SQLException ex1) {
                Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex1);
            }
                    user.getImage().setImageId(-1);
        }
        return user;
    }
        
    public static List<Post>findPostByName(String param){
        List<Post> listPost = null;
        Connection con = null;
        
        try {
            con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement(FIND_POST_BY_NAME);
            pstmt.setString(1, param.toLowerCase()+"%");
            ResultSet rs = pstmt.executeQuery();
            listPost = queryPost(rs,listPost);
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  listPost;
    }
    
    public static List<Post> findPostByUserId(int id){
        List<Post> listPost = null;
        Connection con = null;
        
        try {
            con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement(FIND_POST_BY_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            listPost = queryPost(rs,listPost);
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  listPost;
    }
    
    
    private static List<Post> queryPost(ResultSet rs,List<Post> listPost) throws SQLException{
        int check =0;
        List<Tag> listTag = null;
        Image img =null;
        Tag tag = null;
        User user = null;
        Post post = null;
        int index = 0;
         while(rs.next()){
                 post = new Post();
                 int input = rs.getInt("post_id");
                 if(listPost == null){
                     listPost = new ArrayList<Post>();
                     listTag = new ArrayList<Tag>();
                     tag = new Tag();
                     img = new Image();
                     user = new User();
                     ormPost(rs,post);
                     ormTag(rs,tag);
                     ormImage(rs,img);
                     ormUser(rs,user);
                     listTag.add(tag);
                     post.setTag((ArrayList<Tag>) listTag);
                     post.setImage(img);
                     post.setUser(user);
                     listPost.add(post);
                     check = post.getPostId();
               }
                 else if(check == input && check != -1){
                     tag = new Tag();
                     ormTag(rs,tag);               
                     listPost.get(index).getTag().add(tag);
                 }
                 else{
                      index++;
                     listTag = new ArrayList<Tag>();
                     if(listTag.isEmpty()){
                        tag = new Tag();
                        img = new Image();
                        user = new User();
                        ormPost(rs,post);
                        ormTag(rs,tag);
                        ormImage(rs,img);
                        ormUser(rs,user);
                        listTag.add(tag);
                        post.setUser(user);
                        post.setTag((ArrayList<Tag>) listTag);
                        post.setImage(img);
                        listPost.add(post);
                     }
                     
                     check = post.getPostId();
                 }         
        }
          return listPost;
    }
    
    
    

}
