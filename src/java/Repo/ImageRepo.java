/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Image;
import util.ConnectionBuilder;

/**
 *
 * @author Huag
 */
public class ImageRepo {
    
     public synchronized static Image findImageById(int imgId){
         Image img = null;
        try{
            Connection con = ConnectionBuilder.getMySqlCond();
            String sql = "select * from wil_image where image_id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, imgId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                img = new Image();
                Repo.ormImage(rs, img);
            }
            
        }catch(Exception x){
            x.printStackTrace();
        }
        return img;
     }
     
     
     
}
