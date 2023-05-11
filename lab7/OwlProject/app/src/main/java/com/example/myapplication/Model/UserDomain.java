package com.example.myapplication.Model;

import java.io.Serializable;

public class UserDomain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private Double price;
    private Double star;
    private int time;
    private int cat_id;
    private int numberInHour;
    private String role;
    private int exp;


    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }



    public String getTitle() {
        return title;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getNumberInHour() {
        return numberInHour;
    }

    public void setNumberInHour(int numberInHour) {
        this.numberInHour = numberInHour;
    }

    public UserDomain(String title, String pic, String description, Double price, Double star,
                      int time, int cat_id, int numberInHour, String role,int exp) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.star = star;
        this.time = time;
        this.cat_id = cat_id;
        this.numberInHour = numberInHour;
        this.role=role;
        this.exp= exp;
    }
}
