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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    public static List<Post> queryPost(String sql,String param){
        Connection con = null;
        List<Post> listPost = null;
        List<Tag>   listTag = null;
        List<Image> listImage = null;
        User user = null;
        Post post = null;
        try {
            con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement("Select * from wil_post");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                if(listPost == null){
                    listPost = new ArrayList<Post>();
                }
               user = new User();
               post = new Post(user);
               ormPost(rs,post);
               listPost.add(post);
            }
            rs.close();
            pstmt.close();
            if(listPost != null){
                listImage = queryImageByPost(con,listPost , listImage);
                listTag     = queryTagByPost(con,listPost,listTag);
                List<User> listUser  = queryUserByPost(con,listPost); 
                for(int i=0;i < listPost.size();i++ ){
                    for (int j = 0; j <listUser.size(); j++) {
                        if(listPost.get(i).getUser().getUserId() == listUser.get(j).getUserId()){
                            listPost.get(i).setUser(listUser.get(j));
                            break;
                        }
                    }
                   
                }
                
            }
            con.close();
            }catch (ClassNotFoundException ex) {
                Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
            }catch (SQLException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPost;
    }
    
    
    public static List<Image> queryImageByPost(Connection con,List<Post> listPost, List<Image> listImage){
          try {
            int check = 0;
            int index = 0;
            Image img = null;
            con = ConnectionBuilder.getMySqlCond();
            String  sqlWhere = "(";
              for (int i = 0; i < listPost.size(); i++) {
                  String    comma = "";    
                  if(i < listPost.size()-1)
                      comma = ",";
                  sqlWhere += String.valueOf(listPost.get(i).getPostId())+comma;   
              }
              sqlWhere += ")";
            PreparedStatement pstmt = con.prepareStatement("Select post_id, img.image_id, img.src "
                    + "from wil_post_image poimg "
                    + "JOIN wil_image img "
                    + "ON poimg.image_id = img.image_id "
                    + "Where post_id IN "  + sqlWhere + " "
                     + "Order By post_id ASC " );
     
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int post_id = rs.getInt("post_id"); 
                 img = new Image();
                ormImage(rs,img);
                if(listImage == null){
                    listImage = new ArrayList<Image>();
                     check = post_id;
                }
               
                if(check == post_id){
                    listImage.add(img);
                }
                else if(check != post_id){
                   listPost.get(index).setImage((ArrayList<Image>)listImage);
                   listImage = new ArrayList<Image>();
                   listImage.add(img);
                   index++;
                }
               if(rs.isLast()){
                    listPost.get(index).setImage((ArrayList<Image>)listImage);
               }
               check = listPost.get(index).getPostId();  
            }
          
          }catch(Exception ex){
              ex.printStackTrace();
          }
        return  listImage;
    }
    
    
    public static List<Tag> queryTagByPost(Connection con,List<Post> listPost, List<Tag> listTag){
            try {
            int check = 0;
            int index = 0;
            Tag tag = null;
            con = ConnectionBuilder.getMySqlCond();
            String  sqlWhere = "(";
              for (int i = 0; i < listPost.size(); i++) {
                  String    comma = "";    
                  if(i < listPost.size()-1)
                      comma = ",";
                  sqlWhere += String.valueOf(listPost.get(i).getPostId())+comma;   
              }
              sqlWhere += ")";
            PreparedStatement pstmt = con.prepareStatement("Select post_id, potag.tag_id, tag.name "
                    + "from wil_post_tag potag "
                    + "JOIN wil_tag tag "
                    + "ON potag.tag_id = tag.tag_id "
                    + "Where post_id IN "  + sqlWhere + " "
                     + "Order By post_id ASC " );
     
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int post_id = rs.getInt("post_id"); 
                 tag = new Tag();
                ormTag(rs,tag);
                if(listTag == null){
                    listTag = new ArrayList<Tag>();
                     check = post_id;
                }
               
                if(check == post_id){
                    listTag.add(tag);
                }
                else if(check != post_id){
                   listPost.get(index).setTag((ArrayList<Tag>)listTag);
                   listTag = new ArrayList<Tag>();
                   listTag.add(tag);
                   index++;
                }
               if(rs.isLast()){
                    listPost.get(index).setTag((ArrayList<Tag>)listTag);
               }
               check = listPost.get(index).getPostId();  
            }
          
          }catch(Exception ex){
              ex.printStackTrace();
          }
        return  listTag;
    }

    public static List<User> queryUserByPost(Connection con,List<Post> listPost){
        List<User> listUser = null;
        int index = 0;
          try { 
             String  sqlWhere = "(";
              for (int i = 0; i < listPost.size(); i++) {
                  String    comma = "";    
                  if(i < listPost.size()-1)
                      comma = ",";
                  sqlWhere += String.valueOf(listPost.get(i).getUser().getUserId())+comma;   
              }
              sqlWhere += ")";
            con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement("Select user_id, u.image_id \"user_image_id\", password,fname,lname,gender,citizen_id,tel,faculty,address,email  "
                    + "from wil_user u "      
                    + "Where  user_id IN  "+ sqlWhere );
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                if(listUser == null)
                    listUser = new ArrayList<User>(); 
                Image img = new Image();      
                ormImage(rs,img);
                 User user = new User(img);
                ormUser(rs,user);

                listUser.add(user);
            }
          }catch(Exception ex){
              System.out.println("\nListUser\n");
              ex.printStackTrace();
          }
          return listUser;
    }
    
    public static Post ormPost(ResultSet rs,Post post) throws SQLException{
        post.setPostId(rs.getInt("post_id"));
        post.getUser().setUserId(rs.getInt("user_id"));
        post.setPostName(rs.getString("name"));
        post.setPostDescription(rs.getString("description"));
        post.setStatus(rs.getInt("status"));
        
        try{
            post.setLat(rs.getBigDecimal("lost_lat").doubleValue());
            post.setLon(rs.getBigDecimal("lost_lon").doubleValue());        
        }
        catch(Exception ex){
               System.out.println(ex);
        }
      
        return post;
    }
    
    public static Tag ormTag(ResultSet rs,Tag tag) throws SQLException{
        tag.setTagId(rs.getInt("tag_id"));
        tag.setTagName(rs.getString("name"));
        return tag;
    }
    
    public static Image ormImage(ResultSet rs,Image img) {

           try{
                img.setImageId(rs.getInt("image_id"));     
                img.setSrc(rs.getString("src"));
           }catch(Exception ex){
               img.setImageId(0);
               img.setSrc(null);
           }
        return img;
    }
    
    public static User ormUser(ResultSet rs,User user)  {
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
            user.setEmail(rs.getString("email"));

        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return user;
    }
        
    
    
    public static List<Post>findPostByName(String param){
        List<Post> listPost = null;
        Connection con = null;
        
        try {
            con = ConnectionBuilder.getMySqlCond();
//            PreparedStatement pstmt = con.prepareStatement(FIND_POST_BY_NAME);
//            pstmt.setString(1, param.toLowerCase()+"%");
//            ResultSet rs = pstmt.executeQuery();
//      ---- //
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
//            con = ConnectionBuilder.getMySqlCond();
//            PreparedStatement pstmt = con.prepareStatement(FIND_POST_BY_ID);
//            pstmt.setInt(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            -----  //
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  listPost;
    }
}
  

    