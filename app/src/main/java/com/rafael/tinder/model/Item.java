package com.rafael.tinder.model;

public class Item {

    private String image;
    private String address;
    private String price;
    private String owner;
    private String businessType;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBussinessType() {
        return businessType;
    }

    public void setBussinessType(String bussinessType) {
        this.businessType = bussinessType;
    }
}
