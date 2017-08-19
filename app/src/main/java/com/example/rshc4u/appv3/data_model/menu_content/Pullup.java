
package com.example.rshc4u.appv3.data_model.menu_content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pullup {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 
     * @return
     *     The img
     */
    public String getImg() {
        return img;
    }

    /**
     * 
     * @param img
     *     The img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
