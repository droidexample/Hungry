
package com.example.rshc4u.appv3.data_model.home_data;

import com.example.rshc4u.appv3.data_model.menu_content.Pullup;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FrontPageJson {

    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("background_color")
    @Expose
    private String backgroundColor;
    @SerializedName("bar_color")
    @Expose
    private String barColor;
    @SerializedName("background_image")
    @Expose
    private Object backgroundImage;
    @SerializedName("notify_url")
    @Expose
    private String notifyUrl;
    @SerializedName("welcome_text")
    @Expose
    private String welcomeText;
    @SerializedName("text_color")
    @Expose
    private String textColor;
    @SerializedName("bottom_bar_bg")
    @Expose
    private String bottomBarBg;
    @SerializedName("bottom_bar_text")
    @Expose
    private String bottomBarText;
    @SerializedName("bottom_bar_color")
    @Expose
    private String bottomBarColor;
    @SerializedName("bottom_bar_url")
    @Expose
    private String bottomBarUrl;
    @SerializedName("cart_url")
    @Expose
    private String cartUrl;
    @SerializedName("cart_total")
    @Expose
    private String cartTotal;
    @SerializedName("cart_total_formatted")
    @Expose
    private String cartTotalFormatted;
    @SerializedName("cart_background")
    @Expose
    private String cartBackground;
    @SerializedName("cart_color")
    @Expose
    private String cartColor;
    @SerializedName("menu_icon")
    @Expose
    private String menuIcon;
    @SerializedName("right_reveal_icon")
    @Expose
    private String rightRevealIcon;
    @SerializedName("telephone_format")
    @Expose
    private String telephoneFormat;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("about_url")
    @Expose
    private String aboutUrl;
    @SerializedName("about_text")
    @Expose
    private String aboutText;
    @SerializedName("pullup")
    @Expose
    private List<Pullup> pullup = new ArrayList<Pullup>();

    /**
     *
     * @return
     * The storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     *
     * @param storeName
     * The store_name
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     *
     * @return
     * The logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     *
     * @param logo
     * The logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     *
     * @return
     * The banner
     */
    public String getBanner() {
        return banner;
    }

    /**
     *
     * @param banner
     * The banner
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

    /**
     *
     * @return
     * The backgroundColor
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     *
     * @param backgroundColor
     * The background_color
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     *
     * @return
     * The barColor
     */
    public String getBarColor() {
        return barColor;
    }

    /**
     *
     * @param barColor
     * The bar_color
     */
    public void setBarColor(String barColor) {
        this.barColor = barColor;
    }

    /**
     *
     * @return
     * The backgroundImage
     */
    public Object getBackgroundImage() {
        return backgroundImage;
    }

    /**
     *
     * @param backgroundImage
     * The background_image
     */
    public void setBackgroundImage(Object backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     *
     * @return
     * The notifyUrl
     */
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /**
     *
     * @param notifyUrl
     * The notify_url
     */
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    /**
     *
     * @return
     * The welcomeText
     */
    public String getWelcomeText() {
        return welcomeText;
    }

    /**
     *
     * @param welcomeText
     * The welcome_text
     */
    public void setWelcomeText(String welcomeText) {
        this.welcomeText = welcomeText;
    }

    /**
     *
     * @return
     * The textColor
     */
    public String getTextColor() {
        return textColor;
    }

    /**
     *
     * @param textColor
     * The text_color
     */
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    /**
     *
     * @return
     * The bottomBarBg
     */
    public String getBottomBarBg() {
        return bottomBarBg;
    }

    /**
     *
     * @param bottomBarBg
     * The bottom_bar_bg
     */
    public void setBottomBarBg(String bottomBarBg) {
        this.bottomBarBg = bottomBarBg;
    }

    /**
     *
     * @return
     * The bottomBarText
     */
    public String getBottomBarText() {
        return bottomBarText;
    }

    /**
     *
     * @param bottomBarText
     * The bottom_bar_text
     */
    public void setBottomBarText(String bottomBarText) {
        this.bottomBarText = bottomBarText;
    }

    /**
     *
     * @return
     * The bottomBarColor
     */
    public String getBottomBarColor() {
        return bottomBarColor;
    }

    /**
     *
     * @param bottomBarColor
     * The bottom_bar_color
     */
    public void setBottomBarColor(String bottomBarColor) {
        this.bottomBarColor = bottomBarColor;
    }

    /**
     *
     * @return
     * The bottomBarUrl
     */
    public String getBottomBarUrl() {
        return bottomBarUrl;
    }

    /**
     *
     * @param bottomBarUrl
     * The bottom_bar_url
     */
    public void setBottomBarUrl(String bottomBarUrl) {
        this.bottomBarUrl = bottomBarUrl;
    }

    /**
     *
     * @return
     * The cartUrl
     */
    public String getCartUrl() {
        return cartUrl;
    }

    /**
     *
     * @param cartUrl
     * The cart_url
     */
    public void setCartUrl(String cartUrl) {
        this.cartUrl = cartUrl;
    }

    /**
     *
     * @return
     * The cartTotal
     */
    public String getCartTotal() {
        return cartTotal;
    }

    /**
     *
     * @param cartTotal
     * The cart_total
     */
    public void setCartTotal(String cartTotal) {
        this.cartTotal = cartTotal;
    }

    /**
     *
     * @return
     * The cartTotalFormatted
     */
    public String getCartTotalFormatted() {
        return cartTotalFormatted;
    }

    /**
     *
     * @param cartTotalFormatted
     * The cart_total_formatted
     */
    public void setCartTotalFormatted(String cartTotalFormatted) {
        this.cartTotalFormatted = cartTotalFormatted;
    }

    /**
     *
     * @return
     * The cartBackground
     */
    public String getCartBackground() {
        return cartBackground;
    }

    /**
     *
     * @param cartBackground
     * The cart_background
     */
    public void setCartBackground(String cartBackground) {
        this.cartBackground = cartBackground;
    }

    /**
     *
     * @return
     * The cartColor
     */
    public String getCartColor() {
        return cartColor;
    }

    /**
     *
     * @param cartColor
     * The cart_color
     */
    public void setCartColor(String cartColor) {
        this.cartColor = cartColor;
    }

    /**
     *
     * @return
     * The menuIcon
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     *
     * @param menuIcon
     * The menu_icon
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    /**
     *
     * @return
     * The rightRevealIcon
     */
    public String getRightRevealIcon() {
        return rightRevealIcon;
    }

    /**
     *
     * @param rightRevealIcon
     * The right_reveal_icon
     */
    public void setRightRevealIcon(String rightRevealIcon) {
        this.rightRevealIcon = rightRevealIcon;
    }

    /**
     *
     * @return
     * The telephoneFormat
     */
    public String getTelephoneFormat() {
        return telephoneFormat;
    }

    /**
     *
     * @param telephoneFormat
     * The telephone_format
     */
    public void setTelephoneFormat(String telephoneFormat) {
        this.telephoneFormat = telephoneFormat;
    }

    /**
     *
     * @return
     * The telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     *
     * @param telephone
     * The telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     *
     * @return
     * The aboutUrl
     */
    public String getAboutUrl() {
        return aboutUrl;
    }

    /**
     *
     * @param aboutUrl
     * The about_url
     */
    public void setAboutUrl(String aboutUrl) {
        this.aboutUrl = aboutUrl;
    }

    /**
     *
     * @return
     * The aboutText
     */
    public String getAboutText() {
        return aboutText;
    }

    /**
     *
     * @param aboutText
     * The about_text
     */
    public void setAboutText(String aboutText) {
        this.aboutText = aboutText;
    }

    /**
     *
     * @return
     * The pullup
     */
    public List<Pullup> getPullup() {
        return pullup;
    }

    /**
     *
     * @param pullup
     * The pullup
     */
    public void setPullup(List<Pullup> pullup) {
        this.pullup = pullup;
    }

}
