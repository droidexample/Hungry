package com.example.rshc4u.appv3.data_model.nav_content;

import java.util.ArrayList;

/**
 * Created by rshc4u on 8/16/17.
 */

public class MenuContent {


    private MenuInfo menuInfo;


    private MenuItems[] menuItems;

    public MenuInfo getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(MenuInfo menuInfo) {
        this.menuInfo = menuInfo;
    }

    public MenuItems[] getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(MenuItems[] menuItems) {
        this.menuItems = menuItems;
    }
}
