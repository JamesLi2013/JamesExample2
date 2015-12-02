package com.example.james.weixinContact;

/**
 * Created by Administrator on 2015/12/1.
 */
public class Person {
    //name,名字;imgUrl图片url;letter 首字母;
    private String name,imgUrl,letter;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
}
