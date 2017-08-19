package com.example.rshc4u.appv3.utils;

/**
 * Created by pavle on 2.4.16..
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieStore;

public class JSONParser {

    static InputStream is = null;
    static JSONArray jObj = null;
    static String json = "";

    private DefaultHttpClient httpclient;
    private BasicHttpContext localContext;
    private CookieStore cookieJar;

    // constructor
    public JSONParser() {

    }

    public JSONArray getJSONFromUrl(String url, Activity a) {

        // Making HTTP request
        try {

            String SharedPrefName = "menubus";
            SharedPreferences preferences = a.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
            String notifierId  = preferences.getString("cookie", "");

           if(notifierId.equals("")){
               Log.d("NOTIFIER", "getJSONFromUrl: EMPTY");
           }else
               Log.d("NOTIFIER", "getJSONFromUrl: "+notifierId);

            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Cookie",notifierId);


            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            json = sb.toString();
            Log.d("PARSER", "getJSONFromUrl: "+json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }

}