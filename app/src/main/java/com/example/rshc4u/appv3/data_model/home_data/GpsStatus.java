package com.example.rshc4u.appv3.data_model.home_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rshc4u on 9/15/17.
 */

public class GpsStatus {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("frequency")
    @Expose
    private String frequency;

    @SerializedName("update_url")
    @Expose
    private String update_url;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }
}
