/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Huag
 */
public class User {
    private int userId;
    private Image image;
    private String email;
    private String password;
    private String fname;
    private String lname;
    private int gender;
    private String citizenId;
    private String tel;
    private String faculty;
    private String address;

    public User() {
    }

    public User(int userId, Image image, String email, String password, String fname, String lname, int gender, String citizenId, String tel, String faculty, String address) {
        this.userId = userId;
        this.image = image;
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.citizenId = citizenId;
        this.tel = tel;
        this.faculty = faculty;
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", image=" + image + ", email=" + email + ", password=" + password + ", fname=" + fname + ", lname=" + lname + ", gender=" + gender + ", citizenId=" + citizenId + ", tel=" + tel + ", faculty=" + faculty + ", address=" + address + '}';
    }
    
}
