package com.example.firstcode.third_chapter;

/**
 * 说明：$
 * <p>
 * date: 2019-11-06 21:46
 *
 * @author syd
 * @version 1.0
 */
public class Fruit {
    private String name;
    private int imgId;

    public  Fruit(String name,int imgId){
        this.name = name;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {

        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
