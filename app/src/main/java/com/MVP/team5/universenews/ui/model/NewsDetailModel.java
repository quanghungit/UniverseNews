package com.MVP.team5.universenews.ui.model;

public class NewsDetailModel {
    int id;
    String tilte;
    String desc;
    String html;

    public NewsDetailModel() {
    }

    public NewsDetailModel(String tilte, String desc, String html) {
        this.tilte = tilte;
        this.desc = desc;
        this.html = html;
    }

    public NewsDetailModel(int id, String tilte, String desc, String html) {
        this.id = id;
        this.tilte = tilte;
        this.desc = desc;
        this.html = html;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
