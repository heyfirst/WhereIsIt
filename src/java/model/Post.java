/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Huag
 */
public class Post {
    private int postId;
    private Image image;
    private User user;
    private ArrayList<Tag> tag;
    private String postName;
    private String postDescription;
    private double lat;
    private double lon;
    private int status;
    
    public Post() {
        
    }


    public Post(Image image) {
        this.image = image;
    }

    public Post(int postId, Image image, ArrayList<Tag> tag, String postName, String postDescription, double lat, double lon, int status) {
        this.postId = postId;
        this.image = image;
        this.tag = tag;
        this.postName = postName;
        this.postDescription = postDescription;
        this.lat = lat;
        this.lon = lon;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ArrayList<Tag> getTag() {
        return tag;
    }

    public void setTag(ArrayList<Tag> tag) {
        this.tag = tag;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", image=" + image + ", tag=" + tag + ", postName=" + postName + ", postDescription=" + postDescription + ", lat=" + lat + ", lon=" + lon + ", status=" + status + "'\n'" +" user= "+user;
    }

   
    
}
