
package com.example.rshc4u.appv3.data_model.menu_content;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NavigationJSON {

    @SerializedName("background_color")
    @Expose
    private String backgroundColor;
    @SerializedName("text_color")
    @Expose
    private String textColor;
    @SerializedName("home_link")
    @Expose
    private String homeLink;
    @SerializedName("cart_count")
    @Expose
    private String cartCount;
    @SerializedName("home_link_icon")
    @Expose
    private String homeLinkIcon;
    @SerializedName("in_session")
    @Expose
    private Integer inSession;

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
     * The homeLink
     */
    public String getHomeLink() {
        return homeLink;
    }

    /**
     *
     * @param homeLink
     * The home_link
     */
    public void setHomeLink(String homeLink) {
        this.homeLink = homeLink;
    }

    /**
     *
     * @return
     * The cartCount
     */
    public String getCartCount() {
        return cartCount;
    }

    /**
     *
     * @param cartCount
     * The cart_count
     */
    public void setCartCount(String cartCount) {
        this.cartCount = cartCount;
    }

    /**
     *
     * @return
     * The homeLinkIcon
     */
    public String getHomeLinkIcon() {
        return homeLinkIcon;
    }

    /**
     *
     * @param homeLinkIcon
     * The home_link_icon
     */
    public void setHomeLinkIcon(String homeLinkIcon) {
        this.homeLinkIcon = homeLinkIcon;
    }

    /**
     *
     * @return
     * The inSession
     */
    public Integer getInSession() {
        return inSession;
    }

    /**
     *
     * @param inSession
     * The in_session
     */
    public void setInSession(Integer inSession) {
        this.inSession = inSession;
    }

}