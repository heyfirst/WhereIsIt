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
import java.util.Date;
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
            PreparedStatement pstmt = con.prepareStatement(sql);
            try{
                if (Integer.parseInt(param) != 0){
                    pstmt.setInt(1,Integer.parseInt(param));
                }
             
            }catch(Exception ex){
                //String
                if(!param.isEmpty())
                    pstmt.setString(1,param);
                
            }
            
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
                    
                   if(listPost.get(i).getImage() == null){
                          ArrayList<Image> ai = new ArrayList<Image>();
                          ai.add(new Image());
                              listPost.get(i).setImage(ai);
                              
                             listPost.get(i).getImage().get(0).setSrc("/assets/img/post1.png");
                   }
                  if(listPost.get(i).getUser().getImage().getSrc() == null){
                       if(listPost.get(i).getUser().getGender() == 1)
                            listPost.get(i).getUser().getImage().setSrc("/assets/img/user1.png");
                       else
                           listPost.get(i).getUser().getImage().setSrc("/assets/img/user2.png");
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
    
    
    public  static List<Image> queryImageByPost(Connection con,List<Post> listPost, List<Image> listImage){
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

    public static List<User> queryUserByPost(Connection con,List<Post> listPost) throws ClassNotFoundException, SQLException{
        List<User> listUser = null;
        int index = 0;
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
                    + "from wil_user  u "   
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
            if(listUser != null){
                setImageByUser(con,listUser);
            }
   
          return listUser;
    }
    
    private static void setImageByUser(Connection con,List<User> listUser) {

        try {
            String  sqlWhere = "(";
            for (int i = 0; i < listUser.size(); i++) {
                String    comma = "";
                if(i < listUser.size()-1)
                    comma = ",";
                if(listUser.get(i).getImage().getImageId() !=0)
                    sqlWhere += String.valueOf(listUser.get(i).getImage().getImageId())+comma;
            }
            sqlWhere += ")";
            con = ConnectionBuilder.getMySqlCond();

            PreparedStatement pstmt = con.prepareStatement("Select image_id,src  "
                    + "from wil_image "
                    + "Where  image_id IN  "+ sqlWhere 
                    +" Order By image_id ASC");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Image img = new Image();
                ormImage(rs,img);
                for(int i =0;i< listUser.size(); i++){
                    if(listUser.get(i).getImage().getImageId() !=0 && listUser.get(i).getImage().equals(img)){
                        listUser.get(i).setImage(img);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            
        }
           
   
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
             post.setLat(0);
            post.setLon(0);
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
                int imgId = rs.getInt("image_id");
                img.setImageId(imgId);     
                if(imgId != 0)
                    img.setSrc(rs.getString("src"));
           }catch(Exception ex){
               img.setImageId(0);
               img.setSrc(null);
          
           }
        return img;
    }
    
    public synchronized static User ormUser(ResultSet rs,User user) throws SQLException  {
        try{
            user.setUserId(rs.getInt("user_id"));  
            user.setCitizenId(rs.getString("citizen_id"));
            user.setFname(rs.getString("fname"));
            user.setLname(rs.getString("lname"));
            user.setGender(rs.getInt("gender"));
            user.setFaculty(rs.getString("faculty"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));    
            user.setEmail(rs.getString("email"));
            user.setAddress(rs.getString("address"));
            user.getImage().setImageId(rs.getInt("user_image_id"));
        }
        catch(SQLException ex){
            user.setUserId(rs.getInt("user_id"));  
            user.setCitizenId(rs.getString("citizen_id"));
            user.setFname(rs.getString("fname"));
            user.setLname(rs.getString("lname"));
            user.setGender(rs.getInt("gender"));
            user.setFaculty(rs.getString("faculty"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));    
            user.setEmail(rs.getString("email"));
            user.setAddress(rs.getString("address"));
            
        }
        return user;
    }
        
    public static List<Post>findPostByName(String param){
        List<Post> listPost = null;
        Connection con = null;
        param = "%"+param.toLowerCase()+"%";
        String sql = "select * from wil_post where lower(name) like ?";
        try{
            listPost = queryPost(sql,param);
        }catch(Exception ex){
            System.out.println("Find Post By Name : "+ex);
        }
        
        return  listPost;
    }
    
     public static Post findPostById(int postId){
         List<Post> listPost = null;
        Post   post = null;
        Connection con = null;
        String sql = "select * from wil_post where post_id=?";
        try{
            listPost = queryPost(sql,String.valueOf(postId));
            if(listPost.size() == 1){
                post = new Post(listPost.get(0).getImage(), listPost.get(0).getTag(), listPost.get(0).getUser());
                post.setLat((listPost.get(0).getLat()));
                post.setLon((listPost.get(0).getLon()));
                post.setPostDescription((listPost.get(0).getPostDescription()));
                post.setPostId((listPost.get(0).getPostId()));
                post.setStatus((listPost.get(0).getStatus()));
                post.setPostName(listPost.get(0).getPostName());
            }
        }catch(Exception ex){
            System.out.println("Find Post By Name : "+ex);
        }

        return  post;
    }
     
       public synchronized static List<Post> findPostByUserId(int id){
         List<Post> listPost = null;
        Post   post = null;
        Connection con = null;
        String sql = "select * from wil_post where user_id=?";
        try{
            listPost = queryPost(sql,String.valueOf(id));
        }catch(Exception ex){
            System.out.println("Method In findPostByUserID : Find Post By Name : "+ex);
        }

        return  listPost;
    }
   
    
     public static List<Tag> queryTag(){
        List<Tag> listTag = null;
        Connection con = null;
        String sql = "select * from wil_tag";
        try {            
            con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                if(listTag == null)
                    listTag = new ArrayList<Tag>();
                Tag tag = new Tag();
                ormTag(rs,tag);
                listTag.add(tag);
            }
            con.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTag;
    }
     
     public static Tag queryTagByTagId(int id){
         Tag tag = null;
        Connection con = null;
        String sql = "select * from wil_tag where tag_id = ?";
        try {            
            con = ConnectionBuilder.getMySqlCond();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                 tag = new Tag();
                ormTag(rs,tag);
            }
            con.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tag;
    }
     
     
    public synchronized static boolean insertPost(Post post){
        boolean success = false;
        boolean insertTag = false;
        boolean insertImage = false;
        Connection con =null;
        String insertPost = "insert into wil_post (user_id, name, description, lost_lat, lost_lon, status, created_at, updated_at) "
                                  + "values (?,?,?,?,?,?,now(),now())";
        
        ArrayList<Integer> listImageId = null;
        int postId = 0;
        try {
            
            // 8 
            con =  ConnectionBuilder.getMySqlCond();
             con.setAutoCommit(false);
             PreparedStatement setUpFK = con.prepareStatement("set foreign_key_checks=0");
             setUpFK.executeUpdate();
            PreparedStatement pstmt = con.prepareStatement(insertPost);
            
            pstmt.setInt(1,post.getUser().getUserId());
            pstmt.setString(2,post.getPostName());
            pstmt.setString(3,post.getPostDescription());
            try{
                 if(post.getLat() == -100 && post.getLon() == -100){
                     pstmt.setString(4,null);
                     pstmt.setString(5,null);
                }
                 else{
                      pstmt.setBigDecimal(4,BigDecimal.valueOf(post.getLat()));
                     pstmt.setBigDecimal(5,BigDecimal.valueOf(post.getLon()));
                 }
            }catch(Exception ex){
                pstmt.setString(4, null);
                pstmt.setString(5, null);
            }
            pstmt.setInt(6,post.getStatus());
            pstmt.executeUpdate();
            pstmt.close();
            
            if(!post.getImage().isEmpty()){
                boolean check  = false;
                if(post.getImage() != null)
                    check   =   true;
                if(check){
                    listImageId = new ArrayList<Integer>();
                    PreparedStatement pstmt_image = con.prepareStatement("select * from wil_image where created_at = now()");
                    ResultSet rs = pstmt_image.executeQuery();
                    while(rs.next()){
                        listImageId.add(rs.getInt("image_id"));
                    }
                    // GET LASTEST POSTID
                    if(insertTagByPost(con,post.getTag()) && insertImageByPost(con,postId,listImageId))
                        success = true;
                }
    
            }
            else{ 
                if(insertTagByPost(con,post.getTag()))
                        success = true;
            }
            setUpFK = con.prepareStatement("set foreign_key_checks=1");
            setUpFK.executeUpdate();
             con.commit();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
      
        return  success;
    }
    
    public static boolean insertImage(Connection con,ArrayList<Image> img) throws SQLException{
        boolean success = false;
            String sql = "insert into wil_image(src,created_at,updated_at) values(?,now(),now())";
           
            for (int i = 0; i < img.size(); i++) {
                 PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, img.get(i).getSrc());
                int exeUpdate = pstmt.executeUpdate();
                if(exeUpdate > 0)
                    success = true;   
             }
            return success;
    }
    
    private synchronized static boolean insertTagByPost(Connection con,ArrayList<Tag> tag) throws SQLException{
            boolean success = false;
            String sql = "insert into wil_post_tag (post_id,tag_id) values ((select max(post_id) from wil_post) ,?)";
           PreparedStatement pstmt = con.prepareStatement(sql);
            for (int i = 0; i < tag.size(); i++) {       
                pstmt.setInt(1, tag.get(i).getTagId());
                int exeUpdate = pstmt.executeUpdate();
                if(exeUpdate > 0)
                    success = true;   
             }
            return success;
    }
    
    
    private synchronized static boolean insertImageByPost(Connection con,int postId,ArrayList<Integer> imgId) throws SQLException{
        
        boolean success = false;
        String sql = "insert into wil_post_image (image_id,post_id) values(?,(select max(post_id) from wil_post))";
            PreparedStatement pstmt = con.prepareStatement(sql);
            for (int i = 0; i < imgId.size(); i++) {
                pstmt.setInt(1, imgId.get(i));
                if(pstmt.executeUpdate() > 0)
                    success = true;   
             }
        
        return  success;
    }
    
    
   public synchronized static Post findLastPostByUserId(int id){
       Connection con = null;
       PreparedStatement pstmt = null;
       List<Post> listPost = null;
       Post post = null;
       String sql = "select * from wil_post where user_id = ?";
       try{
           con = ConnectionBuilder.getMySqlCond();
           listPost = queryPost(sql,String.valueOf(id));
           int last = listPost.size()-1;
            if(listPost.size() >0){
                post = new Post(listPost.get(last).getImage(), listPost.get(last).getTag(), listPost.get(last).getUser());
                post.setLat((listPost.get(last).getLat()));
                post.setLon((listPost.get(last).getLon()));
                post.setPostDescription((listPost.get(last).getPostDescription()));
                post.setPostId((listPost.get(last).getPostId()));
                post.setStatus((listPost.get(last).getStatus()));
                post.setPostName(listPost.get(last).getPostName());
            }
       }
       catch(Exception x){
           x.printStackTrace();
       }
          return post;     
   } 
   
    public static List<Post>findPostByNameAndUserId(String param,int id){
        List<Post> listPost = null;
        Connection con = null;
        param = "%"+param.toLowerCase()+"%";
        String sql = "select * from wil_post where lower(name) like ? and user_id="+id;
        System.out.println(sql);
        try{
            listPost = queryPost(sql,param);
        }catch(Exception ex){
            System.out.println("Find Post By Name : "+ex);
        }
        
        return  listPost;
    }
    
     public static List<Post>findPostByStatusAndUserId(int status,int id){
        List<Post> listPost = null;
        Connection con = null;
        String sql = "select * from wil_post where user_id = ? and status ="+status;
        System.out.println(sql);
        try{
            listPost = queryPost(sql,String.valueOf(id));
        }catch(Exception ex){
            System.out.println("Find Post By Name : "+ex);
        }
        
        return  listPost;
    }
     
     public synchronized static boolean updateToPostPending(int status, int postId){
         boolean success = false;
         String sql = "update wil_post set status=? where post_id=?";
         try{
             Connection con = ConnectionBuilder.getMySqlCond();
             PreparedStatement pstmt  = con.prepareStatement(sql);
             pstmt.setInt(1, status);
             pstmt.setInt(2,postId);
             int update =pstmt.executeUpdate();
             if(update > 0){
                 success = true;
             }
             con.close();
         }catch(Exception x){
             x.printStackTrace();
         }
         
         
         return success;
     }
     
     public synchronized static List<Post> findPostByTagId(List<Tag> listTag){
         List<Post> listPost = null;
         String tag_id = "";
         for (int i = 0; i < listTag.size(); i++) {
             String comma = ",";
             if( i != listTag.size()-1){
                tag_id += listTag.get(i).getTagId()+comma;
             }else{
                 tag_id += listTag.get(i).getTagId();
             }
         }
         String sql  = "select wp.* from wil_post wp "
                            + "join wil_post_tag   wpt "
                            + "on  wp.post_id = wpt.post_id "
                            + "where wpt.tag_id IN (" + tag_id +")";
         
         try{
             listPost = queryPost(sql, "");
         }
         catch(Exception x){
             x.printStackTrace();
         }
         return listPost;
     }
     
      public synchronized static List<Post> findPostByStatus(int status){
         List<Post> listPost = null;
         String sql  = "select wp.* from wil_post wp where status = ?" ;
         
         try{
             listPost = queryPost(sql, String.valueOf(status));
         }
         catch(Exception x){
             x.printStackTrace();
         }
         return listPost;
     }
     
}
  

    