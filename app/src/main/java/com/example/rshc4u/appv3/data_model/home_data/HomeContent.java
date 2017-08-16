package com.example.rshc4u.appv3.data_model.home_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by linux64 on 10/26/16.
 */

public class HomeContent {

    @SerializedName("hide_statusbar")
    @Expose
    private String hide_statusbar;

    @SerializedName("store_name")
    @Expose
    private String store_name;

    @SerializedName("logo")
    @Expose
    private String logo;

    @SerializedName("banner")
    @Expose
    private String banner;


    @SerializedName("background_color")
    @Expose
    private String background_color;

    @SerializedName("bar_color")
    @Expose
    private String bar_color;

    @SerializedName("notify_url")
    @Expose
    private String notify_url;

    @SerializedName("welcome_text")
    @Expose
    private String welcome_text;


    @SerializedName("text_color")
    @Expose
    private String text_color;

    @SerializedName("bottom_bar_bg")
    @Expose
    private String bottom_bar_bg;

    @SerializedName("bottom_bar_text")
    @Expose
    private String bottom_bar_text;

    @SerializedName("bottom_bar_color")
    @Expose
    private String bottom_bar_color;


    @SerializedName("bottom_bar_url")
    @Expose
    private String bottom_bar_url;

    @SerializedName("cart_url")
    @Expose
    private String cart_url;

    @SerializedName("cart_total")
    @Expose
    private String cart_total;

    @SerializedName("cart_total_formatted")
    @Expose
    private String cart_total_formatted;


    @SerializedName("cart_background")
    @Expose
    private String cart_background;

    @SerializedName("cart_color")
    @Expose
    private String cart_color;

    @SerializedName("menu_icon")
    @Expose
    private String menu_icon;

    @SerializedName("right_reveal_icon")
    @Expose
    private String right_reveal_icon;


    @SerializedName("telephone_format")
    @Expose
    private String telephone_format;

    @SerializedName("telephone")
    @Expose
    private String telephone;

    @SerializedName("about_url")
    @Expose
    private String about_url;

    @SerializedName("about_text")
    @Expose
    private String about_text;


    @SerializedName("go_button")
    @Expose
    private Go_button go_button;

    @SerializedName("qr_button")
    @Expose
    private Go_button qr_button;

    @SerializedName("pullup")
    @Expose
    private ArrayList<Pullup> pullup;


    public String getHide_statusbar() {
        return hide_statusbar;
    }

    public void setHide_statusbar(String hide_statusbar) {
        this.hide_statusbar = hide_statusbar;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getBar_color() {
        return bar_color;
    }

    public void setBar_color(String bar_color) {
        this.bar_color = bar_color;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getWelcome_text() {
        return welcome_text;
    }

    public void setWelcome_text(String welcome_text) {
        this.welcome_text = welcome_text;
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

    public String getBottom_bar_bg() {
        return bottom_bar_bg;
    }

    public void setBottom_bar_bg(String bottom_bar_bg) {
        this.bottom_bar_bg = bottom_bar_bg;
    }

    public String getBottom_bar_text() {
        return bottom_bar_text;
    }

    public void setBottom_bar_text(String bottom_bar_text) {
        this.bottom_bar_text = bottom_bar_text;
    }

    public String getBottom_bar_color() {
        return bottom_bar_color;
    }

    public void setBottom_bar_color(String bottom_bar_color) {
        this.bottom_bar_color = bottom_bar_color;
    }

    public String getBottom_bar_url() {
        return bottom_bar_url;
    }

    public void setBottom_bar_url(String bottom_bar_url) {
        this.bottom_bar_url = bottom_bar_url;
    }

    public String getCart_url() {
        return cart_url;
    }

    public void setCart_url(String cart_url) {
        this.cart_url = cart_url;
    }

    public String getCart_total() {
        return cart_total;
    }

    public void setCart_total(String cart_total) {
        this.cart_total = cart_total;
    }

    public String getCart_total_formatted() {
        return cart_total_formatted;
    }

    public void setCart_total_formatted(String cart_total_formatted) {
        this.cart_total_formatted = cart_total_formatted;
    }

    public String getCart_background() {
        return cart_background;
    }

    public void setCart_background(String cart_background) {
        this.cart_background = cart_background;
    }

    public String getCart_color() {
        return cart_color;
    }

    public void setCart_color(String cart_color) {
        this.cart_color = cart_color;
    }

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }

    public String getRight_reveal_icon() {
        return right_reveal_icon;
    }

    public void setRight_reveal_icon(String right_reveal_icon) {
        this.right_reveal_icon = right_reveal_icon;
    }

    public String getTelephone_format() {
        return telephone_format;
    }

    public void setTelephone_format(String telephone_format) {
        this.telephone_format = telephone_format;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAbout_url() {
        return about_url;
    }

    public void setAbout_url(String about_url) {
        this.about_url = about_url;
    }

    public String getAbout_text() {
        return about_text;
    }

    public void setAbout_text(String about_text) {
        this.about_text = about_text;
    }

    public Go_button getGo_button() {
        return go_button;
    }

    public void setGo_button(Go_button go_button) {
        this.go_button = go_button;
    }

    public Go_button getQr_button() {
        return qr_button;
    }

    public void setQr_button(Go_button qr_button) {
        this.qr_button = qr_button;
    }

    public ArrayList<Pullup> getPullup() {
        return pullup;
    }

    public void setPullup(ArrayList<Pullup> pullup) {
        this.pullup = pullup;
    }
}
