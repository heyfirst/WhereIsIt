/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.Image;
import model.Post;

/**
 *
 * @author Huag
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here^
        List<Post> lp = Repo.findPostByName("โทร");
//        List<Post> lp = Repo.queryPost("select * from wil_post","");

//        System.out.println(lp.get(0).getUser().getImage().getImageId());
        Post p = Repo.findPostByUserId(3);
         System.out.println(p);
            

    }
    
}
