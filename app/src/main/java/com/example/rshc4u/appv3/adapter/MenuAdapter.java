package com.example.rshc4u.appv3.adapter;

/**
 * Created by pavle on 2.4.16..
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rshc4u.appv3.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

//Adapter class extends with BaseAdapter and implements with OnClickListener
public class MenuAdapter extends BaseAdapter {


    private Activity activity;
    private String[] data;
    JSONArray json;
    private static LayoutInflater inflater=null;
    List<String> attrs = new ArrayList<String>();


    public MenuAdapter(Activity a, JSONArray json, List<String> attrs) {
        activity = a;
        this.attrs = attrs;
        this.json = json;
        inflater = (LayoutInflater)activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    public MenuAdapter(Activity a) {
        activity = a;

        inflater = (LayoutInflater)activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        if(json == null)
            return 0;
        else
        return json.length()+1;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView title;
        public TextView counter;

        public ImageView icon;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        ViewHolder holder;

        if(convertView==null){

            vi = inflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.title = (TextView) vi.findViewById(R.id.navTitle);
            holder.counter=(TextView)vi.findViewById(R.id.navCounter);
            holder.icon=(ImageView)vi.findViewById(R.id.navIcon);
            vi.setTag( holder );
            holder.title.setTextColor(Color.parseColor(attrs.get(1)));
        }
        else
            holder=(ViewHolder)vi.getTag();

        String title="";
        String imgUrl="";
        String notice;
        String url="";
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = preferences.edit();

        if(position==0){

            title = attrs.get(2);
            imgUrl = attrs.get(3);
            Picasso.with(activity).load(imgUrl).into(holder.icon);
            holder.title.setText(title);
            holder.counter.setText("");
        }else{



        try {

            title  = json.getJSONObject(position-1).getString("title");
            imgUrl  = json.getJSONObject(position-1).getString("icon");
            url  = json.getJSONObject(position-1).getString("url");
            notice  = json.getJSONObject(position-1).getString("notice");
            holder.counter.setBackgroundResource(R.drawable.layout_bg);
            holder.title.setText(title);
            Picasso.with(activity).load(imgUrl).into(holder.icon);
            holder.counter.setText(notice);

            editor.putString("url_"+(position), url);
            editor.apply();
        } catch (JSONException e) {

            holder.title.setText(title);
            Picasso.with(activity).load(imgUrl).into(holder.icon);
            holder.counter.setText("");
            editor.putString("url_"+(position), url);
            editor.apply();

        }
        }



        return vi;
    }


}