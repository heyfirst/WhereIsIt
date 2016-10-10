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
import util.ConnectionBuilder;

/**
 *
 * @author Huag
 */
public class Repo {
    // DecimalFormat for lat, lon    
    private static DecimalFormat dmf = new DecimalFormat("0.#######");
    
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
                 + "ON      poimg.image_id = img.image_id"
            );
             ResultSet rs = pstmt.executeQuery();
             while(rs.next()){
                 post = new Post();
                 int input = rs.getInt("post_id");
                 if(listPost == null){
                     listPost = new ArrayList<Post>();
                     listTag = new ArrayList<Tag>();
                     tag = new Tag();
                     img = new Image();
                     ormPost(rs,post);
                     ormTag(rs,tag);
                     ormImage(rs,img);
                     listTag.add(tag);
                     post.setTag((ArrayList<Tag>) listTag);
                     post.setImage(img);
                     listPost.add(post);
                     check = post.getPostId();
               }
                 else if(check == input && check != 0){
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
                        ormPost(rs,post);
                        ormTag(rs,tag);
                        ormImage(rs,img);
                        listTag.add(tag);
                        post.setTag((ArrayList<Tag>) listTag);
                        post.setImage(img);
                        listPost.add(post);
                     }
                     
                     check = post.getPostId();
                    
                 }       
             }
            
            
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
            img.setImageId(0);
        }
        return img;
    }
    
}
