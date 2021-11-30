package com.example.sellhouse;

public class House {
    public String address1, address2, city, province, country, postCode, propertyType, noOfBedrooms, noOfWashrooms,
            price, description, imageOneUrl, imageTwoUrl, imageThreeUrl;

    public House(String address1, String address2, String city, String province, String country, String postCode, String propertyType, String noOfBedrooms, String noOfWashrooms, String price, String description, String imageOneUrl, String imageTwoUrl, String imageThreeUrl) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postCode = postCode;
        this.propertyType = propertyType;
        this.noOfBedrooms = noOfBedrooms;
        this.noOfWashrooms = noOfWashrooms;
        this.price = price;
        this.description = description;
        this.imageOneUrl = imageOneUrl;
        this.imageTwoUrl = imageTwoUrl;
        this.imageThreeUrl = imageThreeUrl;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public void setNoOfBedrooms(String noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    public String getNoOfWashrooms() {
        return noOfWashrooms;
    }

    public void setNoOfWashrooms(String noOfWashrooms) {
        this.noOfWashrooms = noOfWashrooms;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageOneUrl() {
        return imageOneUrl;
    }

    public void setImageOneUrl(String imageOneUrl) {
        this.imageOneUrl = imageOneUrl;
    }

    public String getImageTwoUrl() {
        return imageTwoUrl;
    }

    public void setImageTwoUrl(String imageTwoUrl) {
        this.imageTwoUrl = imageTwoUrl;
    }

    public String getImageThreeUrl() {
        return imageThreeUrl;
    }

    public void setImageThreeUrl(String imageThreeUrl) {
        this.imageThreeUrl = imageThreeUrl;
    }
}
