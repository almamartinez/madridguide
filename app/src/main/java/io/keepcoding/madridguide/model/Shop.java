package io.keepcoding.madridguide.model;

import io.keepcoding.madridguide.util.map.MapPinsAdder;

public class Shop extends BaseModel implements MapPinsAdder.MapPinnable {
    private String name;
    private String imageUrl;
    private String logoImgUrl;
    private String address;
    private String url;
    private String description;
    private float latitude;
    private float longitude;

    public Shop(long id, String name) {
        super.setId(id);
        this.name = name;
    }

    private Shop() {
    }

    public String getName() {
        return name;
    }

    public Shop setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Shop setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    public Shop setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Shop setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Shop setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getPinImageUrl() {
        return getLogoImgUrl();
    }

    public Shop setDescription(String description) {
        this.description = description;
        return this;
    }

    public float getLatitude() {
        return latitude;
    }

    public Shop setLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public Shop setLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }
}
