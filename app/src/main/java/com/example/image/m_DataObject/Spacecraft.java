package com.example.image.m_DataObject;

public class Spacecraft {



    String id;
    String name;
    String imageUrl;
    String floor_Shop;
    String website;
    String opentime;
    String closetime;
    String description_Shop;
    String tel;
    String picMap;
    String typeShopName;
    String groupName;

    public Spacecraft(String id, String name, String imageUrl, String floor_Shop, String website, String opentime, String closetime, String description_Shop, String tel, String picMap, String typeShopName, String groupName) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.floor_Shop = floor_Shop;
        this.website = website;
        this.opentime = opentime;
        this.closetime = closetime;
        this.description_Shop = description_Shop;
        this.tel = tel;
        this.picMap = picMap;
        this.typeShopName = typeShopName;
        this.groupName = groupName;
    }






    public String getPicMap() {
        return picMap;
    }

    public void setPicMap(String picMap) {
        this.picMap = picMap;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFloor_Shop() {
        return floor_Shop;
    }

    public void setFloor_Shop(String floor_Shop) {
        this.floor_Shop = floor_Shop;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }

    public String getDescription_Shop() {
        return description_Shop;
    }

    public void setDescription_Shop(String description_Shop) {
        this.description_Shop = description_Shop;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTypeShopName() {
        return typeShopName;
    }

    public void setTypeShopName(String typeShopName) {
        this.typeShopName = typeShopName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
