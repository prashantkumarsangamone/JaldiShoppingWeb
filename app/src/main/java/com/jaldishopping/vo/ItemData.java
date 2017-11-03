package com.jaldishopping.vo;

/**
 * Created by Deepak D on 04-09-2017.
 */

public class ItemData {


    private String title;
    private int imageUrl;



    public String getTitle() {
        return title;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ItemData(String title, int imageUrl){

        this.title = title;
        this.imageUrl = imageUrl;
    }
    // getters & setters
}