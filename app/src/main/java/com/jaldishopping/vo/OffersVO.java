package com.jaldishopping.vo;

public class OffersVO {
    private String name;
    private String texts;


    public OffersVO() {
    }

    public OffersVO(String name, String texts) {
        this.name = name;
        this.texts = texts;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }


}