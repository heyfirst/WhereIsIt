/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author Huag
 */
public class Image {
    private int imageId;
    private String src;

    public Image() {
    }

    public Image(String src) {
        this.src = src;
    }

    
    public Image(int imageId, String src) {
        this.imageId = imageId;
        this.src = src;
    }

    

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Image){
               Image img = (Image)obj;
               if(imageId == img.getImageId()){
                   return true;
               }
        }
        return false;
    }
 
    
    
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    
    @Override
    public String toString() {
        return "Image{" + "imageId=" + imageId + ", src=" + src + '}';
    }
    
}
