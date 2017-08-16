package com.example.rshc4u.appv3.data_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rshc4u on 8/16/17.
 */

public class MenuInfo {

    @SerializedName("background_color")
    @Expose
    private String background_color;

    @SerializedName("text_color")
    @Expose
    private String text_color;

    @SerializedName("home_link")
    @Expose
    private String home_link;

    @SerializedName("cart_count")
    @Expose
    private String cart_count;

    @SerializedName("home_link_icon")
    @Expose
    private String home_link_icon;


    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

    public String getHome_link() {
        return home_link;
    }

    public void setHome_link(String home_link) {
        this.home_link = home_link;
    }

    public String getCart_count() {
        return cart_count;
    }

    public void setCart_count(String cart_count) {
        this.cart_count = cart_count;
    }

    public String getHome_link_icon() {
        return home_link_icon;
    }

    public void setHome_link_icon(String home_link_icon) {
        this.home_link_icon = home_link_icon;
    }
}
