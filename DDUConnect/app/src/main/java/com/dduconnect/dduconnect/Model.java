package com.dduconnect.dduconnect;

import java.util.List;

public class Model {
    public static final int IMAGE_TYPE=1;
    public String title,subtitle,Image,date;
    public List<Integer> category;
    public String categori,link;
    public int type,id;
    public String content;
    public Model(int id,int mtype, String mtitle, String msubtitle, String image, String date, List<Integer> category, String categori, String link){
        this.title=mtitle;
        this.subtitle=msubtitle;
        this.type=mtype;
        this.Image=image;
        this.date=date;
        this.categori=categori;
        this.category=category;
        this.link=link;
        this.id=id;
    }

    public Model(String title, String image, String date, int id, String content) {
        this.title = title;
        Image = image;
        this.date = date;
        this.id = id;
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory(List<Integer> category) {
        this.category = category;
    }

    public void setCategori(String categori) {
        this.categori = categori;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getImageType() {
        return IMAGE_TYPE;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImage() {
        return Image;
    }

    public String getDate() {
        return date;
    }

    public List<Integer> getCategory() {
        return category;
    }

    public String getCategori() {
        return categori;
    }

    public String getLink() {
        return link;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public Integer getId() {
        return id;
    }

    public Model(String content){
        this.content=content;
 }
}
