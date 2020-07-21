package com.example.smallexease.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CollectionBean {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String author;
    private String pic;
    @Generated(hash = 1260766567)
    public CollectionBean(Long id, String title, String author, String pic) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pic = pic;
    }
    @Generated(hash = 1423617684)
    public CollectionBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
}
