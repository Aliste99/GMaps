package com.example.thirtyseven.gmaps;

/**
 * Created by ThirtySeven on 25.04.2017.
 */

class Flat {
    private String address;
    private int area;
    private int roomCount;
    private String description;
    private int price;
    private String idKey;
    private Double lng;
    private Double lat;

    public Flat() {
    }

    public Flat(String address, int area, int roomCount, String description, int price) {

        this.address = address;
        this.area = area;
        this.roomCount = roomCount;
        this.description = description;
        this.price = price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public int getArea() {
        return area;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {

        this.price = price;
    }

    public void setIdKey(String idKey) {
        this.idKey = idKey;
    }

    public String getIdKey() {
        return idKey;

    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLng() {
        return lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {

        this.lat = lat;
    }
}
