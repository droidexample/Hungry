package com.example.rshc4u.appv3.data_model;

import java.util.ArrayList;

/**
 * Created by rshc4u on 8/16/17.
 */

public class MenuContent {


    private MenuInfo menuInfo;


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
