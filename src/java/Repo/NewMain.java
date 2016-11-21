/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.Found;
import model.Image;
import model.Post;
import model.Tag;

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
//        List<Post> lp = Repo.queryPost(0,12);
//         List<Post> lp = Repo.queryPost("select * from wil_post", "");
//        List<Post> lp = Repo.queryPost("select * from wil_post","");
//        List<Tag> tag = Repo.queryTag("select * from wil_tag where tag_name IN(select tag_name like 'ของใช้ทั่วไป%')");
//        System.out.println(tag);
//        System.out.println(lp.get(0).getUser().getImage().getImageId());
//        Post p = Repo.findPostByUserId(3);
//         System.out.println(lp);
//        System.out.println(lp);
//            Found founder = FoundRepo.findFounderByPostId(38);
        String founder ="item=aaa,date=bbb,time=cccc,place=dddd";
        int next2 = 0;
 
            int index = founder.indexOf("=",next2);
        int comma2 = founder.indexOf(",",next2);
        
        String item2 = founder.substring(index+1,comma2);
        index = founder.indexOf("=",comma2);
        comma2 = founder.indexOf(",",index);
        String date = founder.substring(index+1,comma2);
       
              
              int next = 0;
               int equal = 0;
                int comma =0;
                for (int i = 0; i < 4; i++) {
            
        
                   equal = founder.indexOf("=",next);
                   comma = founder.indexOf(",",equal);
                    System.out.println(equal);
                    System.out.println(comma);
                   String a;
                   if(comma == -1)
                       a = founder.substring(equal+1);
                   else
                        a= founder.substring(equal+1, comma);
                   next = equal+1;
                    System.out.println(a);
                }
           
        }
        

    }
    

