package com.MVP.team5.universenews.ui.fragment.gamek.model;

/**
 * Created by B350M on 12/1/2017.
 */

public class Gamek_PC_Content {
    private String title;
    private String link;
    private String des;
    private String img;

    public Gamek_PC_Content(String title, String link, String des, String img) {
        this.title = title;
        this.link = link;
        this.des = des;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
