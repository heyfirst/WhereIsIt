/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repo;

import java.math.BigDecimal;
import java.util.List;
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
        // TODO code application logic here
        List<Post> lp = Repo.findPostByName("");
        System.out.println(lp);
    }
    
}
