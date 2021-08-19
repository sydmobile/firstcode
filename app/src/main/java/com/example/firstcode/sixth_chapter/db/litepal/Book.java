package com.example.firstcode.sixth_chapter.db.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * 说明：对应 Book 表
 * <p>
 * date: 2020-02-20 16:01
 *
 * @author syd
 * @version 1.0
 */
public class Book extends LitePalSupport {
    private int id;
    private String author;
    private double price;
    private int pages;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
