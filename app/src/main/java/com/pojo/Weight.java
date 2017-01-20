package com.pojo;

/**
 * Created by elsaidel on 1/17/2017.
 */

public class Weight {

    private byte[] userImage;
    private String date, email;
    private double weight;


    public Weight() {
    }

    public Weight(byte[] userImage, String date, double weight, String email) {
        this.userImage = userImage;
        this.date = date;
        this.weight = weight;
        this.email = email;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
