package com.example.rshc4u.appv3.data_model.nav_content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rshc4u on 8/16/17.
 */

public class MenuContent {


    @SerializedName("menuInfo")
    @Expose
    private MenuInfo menuInfo;

    @SerializedName("menuItems")
    @Expose
    private ArrayList<MenuItems> menuItems;

    public MenuInfo getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(MenuInfo menuInfo) {
        this.menuInfo = menuInfo;
    }

    public ArrayList<MenuItems> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItems> menuItems) {
        this.menuItems = menuItems;
    }
}
