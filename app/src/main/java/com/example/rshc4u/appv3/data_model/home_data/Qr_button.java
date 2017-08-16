package com.example.rshc4u.appv3.data_model.home_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rshc4u on 8/16/17.
 */

public class Qr_button {

    @SerializedName("background")
    @Expose
    private String background;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("url")
    @Expose
    private String url;


    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
